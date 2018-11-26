/*
/*******************************************************************************
 * Copyright (C) 2018 MINHAFP, Gobierno de España
 * This program is licensed and may be used, modified and redistributed under the  terms
 * of the European Public License (EUPL), either version 1.1 or (at your option)
 * any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * more details.
 * You should have received a copy of the EUPL1.1 license
 * along with this program; if not, you may find it at
 * http:joinup.ec.europa.eu/software/page/eupl/licence-eupl
 ******************************************************************************/

/**
 * <b>File:</b><p>es.gob.valet.tasks.FindNewTSLRevisionsTask.java.</p>
 * <b>Description:</b><p>Class that checks the new versions of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 26/11/2018.
 */
package es.gob.valet.tasks;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.ifaces.ITSLOtherConstants;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.utils.UtilsHTTP;

/**
 * <p>Class that checks the new versions of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 26/11/2018.
 */
public class FindNewTSLRevisionsTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(FindNewTSLRevisionsTask.class);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected final void initialMessage() {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_INIT_MSG));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected final void doActionOfTheTask() throws Exception {

		try {

			// Obtenemos la lista de códigos de los países/regiones de TSL.
			List<String> tslsCountryRegionCodes = TSLManager.getInstance().getAllTSLCountriesRegionsCodes();

			// Si la lista no es vacía...
			if (tslsCountryRegionCodes != null) {

				// Obtenemos la que es reconocida como última especificación en base de datos.
				List<CTslImpl> tslImplList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getAllCTSLImpl();
				if (tslImplList!=null && !tslImplList.isEmpty()) {

					CTslImpl lastTslImpl = tslImplList.get(tslImplList.size()-1);

					// La recorremos...
					for (String tslCountryRegion: tslsCountryRegionCodes) {

						// Obtenemos la TSL asociada.
						TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(tslCountryRegion);
						// Si está definida...
						if (tsldco != null) {

							// Comprobamos si existe alguna actualización.
							checkIfExistsNewVersionForTSL(tsldco, tslCountryRegion, lastTslImpl);

						}

					}

				}

			}

		} catch (TSLManagingException e) {
			// getAfirmaMessages().showErrorMessage(null, ERROR_LOADING_TREE,
			// null, e.getException(), LOGGER);
			e.printStackTrace();
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected final void endMessage() {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_END_MSG));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#prepareParametersForTheTask(java.util.Map)
	 */
	@Override
	protected final void prepareParametersForTheTask(Map<String, Object> dataMap) throws TaskValetException {
		return;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#getDataResult()
	 */
	@Override
	protected final Map<String, Object> getDataResult() throws TaskValetException {
		return null;
	}

	/**
	 * Attribute that represents a map with the HTTP headers to set in every connection to download
	 * the TSLs.
	 */
	private static Map<String, String> httpHeadersMap = null;

	static {

		httpHeadersMap = new HashMap<String, String>();
		httpHeadersMap.put(HttpHeaders.CONTENT_TYPE, ITSLOtherConstants.TSL_APPLICATION_TYPE);
		httpHeadersMap.put(HttpHeaders.USER_AGENT, UtilsHTTP.HTTP_HEADER_USER_AGENT_HTTPCLIENT);

	}

	/**
	 * Method that checks if a new TSL is available.
	 * @param tsldco Object that represents the TSL in the clustered cache.
	 * @param tslCountryRegion Tsl country/region code.
	 * @param lastTslImpl Last TSL specification/implementation recognized in data base.
	 */
	private void checkIfExistsNewVersionForTSL(TSLDataCacheObject tsldco, String tslCountryRegion, CTslImpl lastTslImpl) {

		ByteArrayInputStream bais = null;
		String distributionPoint = UtilsStringChar.EMPTY_STRING;

		try {

			// Si la TSL no está marcada ya como disponible, la analizamos...
			if (!tsldco.getNewTSLAvailable().equals(IFindNewTslRevisionsTaskConstants.NEW_TSL_AVAILABLE)) {

				// Se obtiene el número de secuencia de la TSL.
				int secuenceNumber = tsldco.getSequenceNumber();
				// Obtenemos la URI de donde descargar la TSL.
				distributionPoint = tsldco.getTslLocationUri();

				// Obtenemos la especificación y su versión.
				CTslImpl actualTslImpl = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getCTSLImpById(tsldco.getTslImplId());

				// Obtenemos la TSL...
				byte[ ] fullTSLxml = UtilsHTTP.getDataFromURI(distributionPoint, NumberConstants.NUM10000, NumberConstants.NUM10000, null, null, httpHeadersMap);
				// Abrimos un InputStream para el array de bytes.
				bais = new ByteArrayInputStream(fullTSLxml);

				// Creamos un objeto que representará la TSL descargada.
				ITSLObject tslObject = new TSLObject(actualTslImpl.getSpecification(), actualTslImpl.getVersion());
				try {
					tslObject.buildTSLFromXMLcheckValues(bais, false);
				} catch (Exception e) {

					// Si no la hemos conseguido parsear, lo intentamos con la última
					// especificación disponible (siempre que no fuera esta ya)...
					if (lastTslImpl!=null && !actualTslImpl.getIdTSLImpl().equals(lastTslImpl.getIdTSLImpl())) {

						LOGGER.warn(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_000, new Object[ ] { actualTslImpl.getSpecification(), actualTslImpl.getVersion(), lastTslImpl.getSpecification(), lastTslImpl.getVersion() }));
						tslObject = new TSLObject(lastTslImpl.getSpecification(), lastTslImpl.getVersion());
						UtilsResources.safeCloseInputStream(bais);
						bais = new ByteArrayInputStream(fullTSLxml);
						tslObject.buildTSLFromXMLcheckValues(bais, false);


					} else {

						throw e;

					}

				}

				// Si su número de secuencia es mayor al actual, es que hay que
				// actualizarla.
				if (tslObject.getSchemeInformation().getTslSequenceNumber() > secuenceNumber) {
					// Existe una nueva versión de la TSL.
					TSLManager.getInstance().updateNewAvaliableTSLData(tsldco.getTslDataId(), IFindNewTslRevisionsTaskConstants.NEW_TSL_AVAILABLE);
					// TODO Pendiente del desarrollo del módulo de alarmas.
					//					String alarmMsg = Language.getFormatResCoreTSL(ALARM001, new Object[ ] { tslCountry });
					//					AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
					TSLManager.getInstance().updateLastNewAvaliableTSLFindData(tsldco.getTslDataId(), Calendar.getInstance().getTime());
				}

			} else {

				// Si ha pasado una semana desde que se marcó como Y se vuelve a
				// enviar la alarma
				checkAndUpdateLastAlarm(tsldco, tslCountryRegion);

			}

		} catch (CommonUtilsException e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM002, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} catch (TSLArgumentException e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM003, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} catch (TSLParsingException e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM003, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} catch (TSLMalformedException e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM003, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} catch (TSLManagingException e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM004, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} catch (Exception e) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM003, new Object[ ] { distributionPoint });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_001, new Object [] { tslCountryRegion }), e);
		} finally {
			// Aunque falle, cerramos el InputStream.
			UtilsResources.safeCloseInputStream(bais);
		}
	}

	/**
	 * Method that checks if the last alarm sent is 7 days old.
	 * @param tsldco Object that represents the TSL in the clustered cache.
	 * @param tslCountryRegion Tsl country/region code.
	 * @throws TSLManagingException if update fail.
	 */
	private void checkAndUpdateLastAlarm(TSLDataCacheObject tsldco, String tslCountryRegion) throws TSLManagingException {
		Date dateToCheck = UtilsDate.getDateAddingDays(tsldco.getLastNewTSLAvailableFind(), NumberConstants.NUM7);
		Date actualDate = Calendar.getInstance().getTime();
		if (dateToCheck.before(actualDate)) {
			// TODO Pendiente del desarrollo del módulo de alarmas.
			//			String alarmMsg = Language.getFormatResCoreTSL(ALARM001, new Object[ ] { tslCountry });
			//			AlarmsModuleManager.getInstance().registerAlarm(IAlarmNameConstants.ALARM_049, IModuleIdentifiers.MOD_VALIDACION, alarmMsg);
			TSLManager.getInstance().updateLastNewAvaliableTSLFindData(tsldco.getTslDataId(), actualDate);
		}
	}

}
