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
 * @version 1.9, 19/09/2023.
 */
package es.gob.valet.tasks;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.utils.AlarmIdConstants;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.utils.TSLOtherConstants;
import es.gob.valet.utils.UtilsHTTP;

/**
 * <p>Class that checks the new versions of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.9, 19/09/2023.
 */
public class FindNewTSLRevisionsTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(FindNewTSLRevisionsTask.class);

	/**
	 * Attribute that will save the moment the task starts.
	 */
	private static long startOperationTime = 0L;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected final void initialMessage() {

		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_INIT_MSG));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected final void doActionOfTheTask() throws Exception {
		setStartOperationTime(Calendar.getInstance().getTimeInMillis());
		try {

			// Obtenemos la lista de códigos de los países/regiones de TSL.
			List<String> tslsCountryRegionCodes = TSLManager.getInstance().getAllTSLCountriesRegionsCodes();

			// Si la lista no es vacía...
			if (tslsCountryRegionCodes != null) {
				LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_005, new Object[ ] { tslsCountryRegionCodes.size() }));
				// Obtenemos la que es reconocida como última especificación en
				// base de datos.
				List<CTslImpl> tslImplList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getAllCTSLImpl();
				if (tslImplList != null && !tslImplList.isEmpty()) {

					CTslImpl lastTslImpl = tslImplList.get(tslImplList.size() - 1);

					// La recorremos...
					for (String tslCountryRegion: tslsCountryRegionCodes) {

						// Obtenemos la TSL asociada.
						TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(tslCountryRegion);
						// Si está definida...
						if (tsldco != null) {
							LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_006, new Object[ ] { tslCountryRegion }));
							// se comprueba, si no existe fecha de nueva
							// actualización, como ocurre con la TSL de Reino
							// Unido al no publicarse más, no se tiene en cuenta
							// en la tarea.
							if (tsldco.getNextUpdateDate() != null) {
								// Comprobamos si existe alguna actualización.
								checkIfExistsNewVersionForTSL(tsldco, tslCountryRegion, lastTslImpl);
							} else {
								LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_004, new Object[ ] { tslCountryRegion }));
							}

						}

					}

				}

			}

		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_013, new Object[ ] { e.getMessage() }));
			//e.printStackTrace();
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected final void endMessage() {
		LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_END_MSG, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));
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
		httpHeadersMap.put(HttpHeaders.CONTENT_TYPE, TSLOtherConstants.TSL_APPLICATION_TYPE);
		httpHeadersMap.put(HttpHeaders.USER_AGENT, UtilsHTTP.HTTP_HEADER_USER_AGENT_HTTPCLIENT);

	}

	/**
	 * Method that checks if a new TSL is available.
	 * @param tsldco Object that represents the TSL in the clustered cache.
	 * @param tslCountryRegion Tsl country/region code.
	 * @param lastTslImpl Last TSL specification/implementation recognized in data base.
	 */
	private void checkIfExistsNewVersionForTSL(TSLDataCacheObject tsldco, String tslCountryRegion, CTslImpl lastTslImpl) {

		// Se obtiene el número de secuencia de la TSL.
		int sequenceNumber = tsldco.getSequenceNumber();
		// Creamos la variable que almacenará el número de secuencia de la TSL
		// descargada.
		int sequenceNumberNewTsl = -1;
		// Obtenemos la URI de donde descargar la TSL.
		String distributionPoint = tsldco.getTslLocationUri();

		try {
			HttpGet method = new HttpGet(distributionPoint);
			if (distributionPoint == null || method.getURI().getHost() == null) {
					// se lanza una alarma indicando que no se puede comprobar
					// si
					// existe una nueva versión al no disponer de la url de
					// descarga.
					LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_010, new Object[ ] { tslCountryRegion }));
					AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_002, new Object[ ] { tslCountryRegion }));
			
			} else {
				// Si la TSL no está marcada ya como disponible, la
				// analizamos...
				if (!tsldco.getNewTSLAvailable().equals(FindNewTslRevisionsTaskConstants.NEW_TSL_AVAILABLE)) {
					LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_007, new Object[ ] { tslCountryRegion }));

					// Obtenemos la especificación y su versión.
					CTslImpl actualTslImpl = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getCTSLImpById(tsldco.getTslImplId());

					// Obtenemos la TSL...
					byte[ ] fullTSLxml = UtilsHTTP.getDataFromURI(distributionPoint, NumberConstants.NUM10000, NumberConstants.NUM10000, null, null, httpHeadersMap);
					
					// Creamos un objeto que representará la TSL descargada.
					ITSLObject tslObject = new TSLObject(actualTslImpl.getSpecification(), actualTslImpl.getVersion());
					// Abrimos un InputStream para el array de bytes.
					try (ByteArrayInputStream bais = new ByteArrayInputStream(fullTSLxml)){
						tslObject.buildTSLFromXMLcheckValues(bais, false, false);
					} catch (Exception e1) {

						// Si no la hemos conseguido parsear, lo intentamos con
						// la
						// última
						// especificación disponible (siempre que no fuera esta
						// ya)...
						if (lastTslImpl != null && !actualTslImpl.getIdTSLImpl().equals(lastTslImpl.getIdTSLImpl())) {
							LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_000, new Object[ ] { actualTslImpl.getSpecification(), actualTslImpl.getVersion(), lastTslImpl.getSpecification(), lastTslImpl.getVersion() }));
							tslObject = new TSLObject(lastTslImpl.getSpecification(), lastTslImpl.getVersion());
							try (ByteArrayInputStream bais = new ByteArrayInputStream(fullTSLxml)){
								tslObject.buildTSLFromXMLcheckValues(bais, false, false);
							} catch (Exception e2) {
								throw e2;
							}
						} else {
							throw e1;
						}

					}

					// Si su número de secuencia es mayor al actual, es que hay
					// que
					// actualizarla.
					sequenceNumberNewTsl = tslObject.getSchemeInformation().getTslSequenceNumber();
					if (sequenceNumberNewTsl > sequenceNumber) {
						// Existe una nueva versión de la TSL.
						TSLManager.getInstance().updateNewAvaliableTSLData(tsldco.getTslDataId(), FindNewTslRevisionsTaskConstants.NEW_TSL_AVAILABLE);
						TSLManager.getInstance().updateLastNewAvaliableTSLFindData(tsldco.getTslDataId(), Calendar.getInstance().getTime());
						LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_002, tslCountryRegion, distributionPoint, sequenceNumberNewTsl, sequenceNumber));
						AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM005_NEW_TSL_DETECTED, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM005_EVENT_000, new Object[ ] { tslCountryRegion, distributionPoint, sequenceNumberNewTsl, sequenceNumber }));
					} else {
						LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_003, new Object[ ] { tslCountryRegion, sequenceNumber }));
					}

				} else {

					// Si ha pasado una semana desde que se marcó como Y se
					// vuelve a
					// enviar la alarma.
					checkAndUpdateLastAlarm(tsldco, tslCountryRegion);

				}
			}

		} catch (CommonUtilsException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_011, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_000, new Object[ ] { tslCountryRegion, distributionPoint }));
		} catch (TSLArgumentException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_012, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_001, new Object[ ] { tslCountryRegion, distributionPoint }));
		} catch (TSLParsingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_012, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_001, new Object[ ] { tslCountryRegion, distributionPoint }));
		} catch (TSLMalformedException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_012, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_001, new Object[ ] { tslCountryRegion, distributionPoint }));
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_012, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_001, new Object[ ] { tslCountryRegion, distributionPoint, sequenceNumberNewTsl, sequenceNumber }));
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_012, new Object[ ] { tslCountryRegion, distributionPoint }), e);
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_003, new Object[ ] { tslCountryRegion, distributionPoint }));
		}
	}

	/**
	 * Method that checks if the last alarm sent is 7 days old.
	 * @param tsldco Object that represents the TSL in the clustered cache.
	 * @param tslCountryRegion Tsl country/region code.
	 * @throws TSLManagingException if update fail.
	 */
	private void checkAndUpdateLastAlarm(TSLDataCacheObject tsldco, String tslCountryRegion) throws TSLManagingException {
		Integer daysReminder = NumberConstants.NUM7;
		if(!UtilsStringChar.isNullOrEmpty(StaticValetConfig.getProperty(StaticValetConfig.DAYS_REMINDER_ALARM))){
			daysReminder = Integer.valueOf(StaticValetConfig.getProperty(StaticValetConfig.DAYS_REMINDER_ALARM));
		}
		Date dateToCheck = UtilsDate.getDateAddingDays(tsldco.getLastNewTSLAvailableFind(), daysReminder);
		Date actualDate = Calendar.getInstance().getTime();
		LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_008, new Object[ ] { tsldco.getLastNewTSLAvailableFind(), tslCountryRegion }));
		if (dateToCheck.before(actualDate)) {
			LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_009, new Object[] {daysReminder.toString()}));
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM005_NEW_TSL_DETECTED, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM005_EVENT_002, new Object[ ] { tslCountryRegion, tsldco.getSequenceNumber() }));
			// se actualiza la nueva fecha de notificación
			TSLManager.getInstance().updateLastNewAvaliableTSLFindData(tsldco.getTslDataId(), actualDate);
		}
	}

	
	/**
	 * Sets the value of the attribute {@link #startOperationTime}.
	 * @param startOperationTime The value for the attribute {@link #startOperationTime}.
	 */
	public static void setStartOperationTime(long startOperationTime) {
		FindNewTSLRevisionsTask.startOperationTime = startOperationTime;
	}

	
}
