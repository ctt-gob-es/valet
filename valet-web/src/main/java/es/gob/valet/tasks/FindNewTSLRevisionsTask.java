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
 * @version 2.5, 01/06/2026.
 */
package es.gob.valet.tasks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCountryLanguage;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.dto.ConfTslRegDTO;
import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.entity.TslPendVal;
import es.gob.valet.persistence.configuration.model.utils.AlarmIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.service.ifaces.IConfTslRegService;
import es.gob.valet.service.ifaces.ITslPendValService;
import es.gob.valet.service.impl.ValetCacheVersionService;
import es.gob.valet.spring.config.ApplicationContextProvider;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLPointer;
import es.gob.valet.utils.TSLSpecificationsVersions;
import es.gob.valet.utils.UtilsHTTP;

/**
 * <p>Class that checks the new versions of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.5, 01/06/2026.
 */
public class FindNewTSLRevisionsTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(FindNewTSLRevisionsTask.class);

	/**
	 * Service for managing TSL registration configurations.
	 */
	private IConfTslRegService iConfTslRegService = ApplicationContextProvider.getApplicationContext().getBean(IConfTslRegService.class);

	/**
	 * Service for accessing and managing TSL data entities.
	 */
	private ITslDataService iTslDataService = ApplicationContextProvider.getApplicationContext().getBean(ITslDataService.class);

	/**
	 * Service handling TSL entries pending validation.
	 */
	private ITslPendValService iTslPendValService = ApplicationContextProvider.getApplicationContext().getBean(ITslPendValService.class);

	/**
	 * Service for managing country and region data related to TSLs.
	 */
	private ITslCountryRegionService iTslCountryRegionService = ApplicationContextProvider.getApplicationContext().getBean(ITslCountryRegionService.class);

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

		// Obtenemos la configuración sobre el registro de TSLs
		ConfTslRegDTO confTslRegDTO = iConfTslRegService.obtainConfTslReg();

		if (!confTslRegDTO.getTslRegEnabled()) {
			try {
				// Obtenemos la lista de códigos de los países/regiones de TSL.
				List<String> tslsCountryRegionCodes = TSLManager.getInstance().getAllTSLCountriesRegionsCodes();
				// Si la lista no es vacía...
				if (tslsCountryRegionCodes != null) {
					LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_005, new Object[ ] { tslsCountryRegionCodes.size() }));
					// Obtenemos la que es reconocida como última especificación
					// en
					// base de datos.
					List<CTslImpl> tslImplList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getAllCTSLImpl();
					if (tslImplList != null && !tslImplList.isEmpty()) {

						CTslImpl lastTslImpl = tslImplList.get(tslImplList.size()
								- 1);

						// La recorremos...
						for (String tslCountryRegion: tslsCountryRegionCodes) {

							// Obtenemos la TSL asociada.
							TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(tslCountryRegion);
							// Si está definida...
							if (tsldco != null) {
								LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_006, new Object[ ] { tslCountryRegion }));
								// se comprueba, si no existe fecha de nueva
								// actualización, como ocurre con la TSL de
								// Reino
								// Unido al no publicarse más, no se tiene en
								// cuenta
								// en la tarea.
								if (tsldco.getNextUpdateDate() != null) {
									// Comprobamos si existe alguna
									// actualización.
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
				// e.printStackTrace();
			}
		} else {
			try {
				// Actualizaremos todas las listas de listas a la versión mas
				// reciente si está disponible
				updatedLotlWithCurrentVersion(confTslRegDTO);
			} catch (Exception e) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_014), e);
			}

		}
	}

	/**
	 * Updates the LOTL (List of the Lists) and the system's TSLs (Trusted Service Lists) 
	 * if newer versions are found.
	 *
	 * <p>This method retrieves all LOTL entries from the database, downloads their
	 * corresponding TSLs, and compares the sequence number with the stored version. 
	 * If a newer version is detected, it updates the local data accordingly. 
	 * After that, it proceeds to update the system's TSLs listed in the downloaded LOTL.</p>
	 *
	 * @param confTslRegDTO DTO containing the LOTL update configuration, including
	 *                      registration mode and filter type.
	 */
	private void updatedLotlWithCurrentVersion(ConfTslRegDTO confTslRegDTO) {
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_014));
		// Obtenemos el modo de registro y los filtros
		int idModeReg = confTslRegDTO.getIdModeReg();
		int idTypeFilterReg = confTslRegDTO.getIdTypeFilterReg();
		AtomicInteger numTslUpdated = new AtomicInteger(0);

		List<TslData> listTslData = iTslDataService.obtainAllTslDataWithTslLotlData();
		if (listTslData.size() > NumberConstants.NUM0) {
			String url = null;
			// Recorremos todas las lista de listas
			for (TslData tslDataLotlBD: listTslData) {
				try {
					// Obtenemos la url de descarga
					url = tslDataLotlBD.getUriTslLocation();
					// Intentamos descargarnos la TSL...
					byte[ ] fullTSLxml = this.downloadTslFromUrl(url);
					ITSLObject iTSLObjectLotlDownload = obtainTSLObject(fullTSLxml);
					// Evaluamos si la versión de la LOTL es soportada por el
					// sistema
					if (!iTSLObjectLotlDownload.getSpecificationVersion().equals(TSLSpecificationsVersions.VERSION_020101)
							&& !iTSLObjectLotlDownload.getSpecificationVersion().equals(TSLSpecificationsVersions.VERSION_020301)) {
						LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_025, new Object[ ] { tslDataLotlBD.getTslCountryRegion().getCountryRegionCode() }));
						AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_004, new Object[ ] { tslDataLotlBD.getTslCountryRegion().getCountryRegionName() }));
						break; // finalizamos la ejecución de la actualización
							   // de lotl y tsls
					}
					// Comprobamos si la versión de la lista de listas
					// descargada es superior a la que ya tenemos en BD
					if (iTSLObjectLotlDownload.getSchemeInformation().getTslSequenceNumber() > tslDataLotlBD.getSequenceNumber()) {
						LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_015, new Object[ ] { tslDataLotlBD.getTslCountryRegion().getCountryRegionName() }));
						updatesTSLforLoggingMode(idModeReg, tslDataLotlBD, url, fullTSLxml, iTSLObjectLotlDownload, true, numTslUpdated);
					}
					// Actualizaremos las TSLs de nuestro sistema con las TSLs
					// descargadas a partir de la lista de listas
					this.updatedTSLCurrentVersionWithTSLLocationFromLotl(iTSLObjectLotlDownload, idTypeFilterReg, idModeReg, numTslUpdated);
				} catch (CertificateEncodingException
						| TSLManagingException e) {
					// Si se produce algun fallo en la actualizacion de BD
					// imprimimos el fallo
					LOGGER.error(e);
				} catch (TSLArgumentException | TSLParsingException
						| TSLMalformedException | IOException e) {
					// Si se produce algun fallo en la construccion de la TSL
					// imprimimos el fallo
					LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_022), e);
				} catch (CommonUtilsException e) {
					// Si se produce algun fallo en la descarga de la TSL
					// lanzamos la alarma
					LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_010, new Object[ ] { tslDataLotlBD.getTslCountryRegion().getCountryRegionName() }));
					AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_002, new Object[ ] { tslDataLotlBD.getTslCountryRegion().getCountryRegionName() }));
					break; // finalizamos la ejecución de la actualización de
						   // lotl y tsls
				} catch (Exception e) {
					String messageError = Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_027, new Object[ ] { url });
					LOGGER.error(messageError, e);
				}
			}

			// Si se ha actualizado alguna TSL según su modo de registro
			// cambiamos la version de la cache
			if (numTslUpdated.get() > NumberConstants.NUM0) {
				ApplicationContextProvider.getApplicationContext().getBean(ValetCacheVersionService.class).updateCacheVersion();
			}

			// Si se han establecido TSLs pendientes de validar, avisaremos
			// enviando una alarma
			if (iTslPendValService.exitsTslPendVal()) {
				LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_020));
				String countriesPendVal = iTslPendValService.obtainAllTslPendVal().stream().map(TslPendValDTO::getCountryRegionName).filter(Objects::nonNull).distinct().collect(Collectors.joining(", "));
				AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM012_EXISTING_TSL_PEND_VAL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM012_EVENT_001, new Object[ ] { countriesPendVal }));
			}
		} else {
			LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_024));
		}
	}

	/**
	 * Updates the current TSLs listed in the downloaded LOTL if newer versions are available.
	 *
	 * <p>This method iterates through the TSL pointers found in the given LOTL object. For each
	 * pointer referencing an XML-based TSL (non-PDF), it checks whether a TSL exists for the 
	 * corresponding country and whether the downloaded version has a higher sequence number 
	 * than the one stored. If so, it triggers an update.</p>
	 *
	 * @param iTSLObjectLotlDownload The LOTL object containing TSL pointers to process.
	 * @param idTypeFilterReg The registration filter type (e.g., whether to register all TSLs).
	 * @param idModeReg The registration mode to apply for updates.
	 * @param numTslUpdated An {@link AtomicInteger} used to track the number of TSLs successfully updated during this operation.
	 */
	private void updatedTSLCurrentVersionWithTSLLocationFromLotl(ITSLObject iTSLObjectLotlDownload, int idTypeFilterReg, int idModeReg, AtomicInteger numTslUpdated) {
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_016));
		// Recorremos los TSLPointer
		for (TSLPointer tSLPointer: iTSLObjectLotlDownload.getSchemeInformation().getPointersToOtherTSL()) {
			// Obtenemos el codigo del país
			String schemeTerritory = tSLPointer.getSchemeTerritory();
			// Obtenemos la url de descarga de la TSLLocation
			String tslLocation = tSLPointer.getTSLLocation().toString();
			// Solo realizaremos tratamiento para los TSLLocation que sean XML
			if (!tSLPointer.getMimeType().contains("application/pdf")) {
				try {
					// Intentamos descargarnos la TSL y ademas la obtenemos con
					// java para tratarla
					byte[ ] fullTSLxml = this.downloadTslFromUrl(tslLocation);
					ITSLObject iTSLObjectTslDownload = obtainTSLObject(fullTSLxml);
					// Evaluamos si la versión de la TSL descargada es soportada
					// por el sistema
					if (!iTSLObjectTslDownload.getSpecificationVersion().equals(TSLSpecificationsVersions.VERSION_020101)
							&& !iTSLObjectTslDownload.getSpecificationVersion().equals(TSLSpecificationsVersions.VERSION_020301)) {
						LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_025, new Object[ ] { schemeTerritory }));
						AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_004, new Object[ ] { schemeTerritory }));
					} else {
						// Comprobamos si se registran todas las TSLs
						if (idTypeFilterReg == NumberConstants.NUM1) {
							treatmentUpdateAllTsls(idModeReg, schemeTerritory, tslLocation, fullTSLxml, iTSLObjectTslDownload, numTslUpdated);
							// Comprobamos si solo tratamos TSLs registradas en
							// el sistema
						} else if (idTypeFilterReg == NumberConstants.NUM2) {
							treatmentUpdateTslRegisteredInSystem(idModeReg, schemeTerritory, tslLocation, fullTSLxml, iTSLObjectTslDownload, numTslUpdated);
						}
					}
				} catch (CertificateEncodingException
						| TSLManagingException e) {
					// Si se produce algun fallo en la actualizacion de BD
					// imprimimos el fallo
					LOGGER.error(e);
				} catch (TSLArgumentException | TSLParsingException
						| TSLMalformedException | IOException e) {
					// Si se produce algun fallo en la construccion de la TSL
					// imprimimos el fallo
					LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_022), e);
				} catch (CommonUtilsException e) {
					// Si se produce algun fallo en la descarga de la TSL
					// lanzamos la alarma
					LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_010, new Object[ ] { UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory) }));
					AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM002_ERROR_GETTING_PARSING_TSL, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM002_EVENT_002, new Object[ ] { UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory) }));
				} catch (Exception e) {
					String messageError = Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_026, new Object[ ] { UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory) });
					LOGGER.error(messageError, e);
				}
			}
		}
	}

	/**
	 * Updates the TSL entry in the system if a newer version is available for an already registered country.
	 *
	 * @param idModeReg Registration mode ID.
	 * @param schemeTerritory Country or region code (Scheme Territory).
	 * @param tslLocation Location (URL) of the TSL.
	 * @param fullTSLxml Raw byte content of the TSL file.
	 * @param iTSLObjectTslDownload Parsed TSL object from the downloaded file.
	 * @param numTslUpdated An {@link AtomicInteger} used to track the number of TSLs successfully updated during this operation.
	 * @throws TSLManagingException If a TSL management error occurs.
	 * @throws CommonUtilsException If a utility-related error occurs.
	 * @throws CertificateEncodingException If certificate encoding fails.
	 * @throws TSLArgumentException If a TSL argument is invalid.
	 * @throws TSLParsingException If parsing the TSL fails.
	 * @throws TSLMalformedException If the TSL structure is malformed.
	 * @throws IOException If an I/O error occurs.
	 */
	private void treatmentUpdateTslRegisteredInSystem(int idModeReg, String schemeTerritory, String tslLocation, byte[ ] fullTSLxml, ITSLObject iTSLObjectTslDownload, AtomicInteger numTslUpdated) throws TSLManagingException, CommonUtilsException, CertificateEncodingException, TSLArgumentException, TSLParsingException, TSLMalformedException, IOException {
		// si existe la TSL para el país que estamos tratando evaluaremos si
		// existe una versión nueva.
		if (iTslCountryRegionService.existsTslForThisCountry(schemeTerritory)) {
			// Obtenemos la TSL de este pais a partir BD
			TslCountryRegion tslCountryRegion = iTslCountryRegionService.getTslCountryRegionWithTslData(schemeTerritory);
			TslData tslDataBD = tslCountryRegion.getTslData();
			// Comprobamos si la versión de la TSL descargada es superior a la
			// que ya tenemos en BD
			if (iTSLObjectTslDownload.getSchemeInformation().getTslSequenceNumber() > tslDataBD.getSequenceNumber()) {
				LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_017, new Object[ ] { tslCountryRegion.getCountryRegionName() }));
				updatesTSLforLoggingMode(idModeReg, tslDataBD, tslLocation, fullTSLxml, iTSLObjectTslDownload, false, numTslUpdated);
			}
		}
	}

	/**
	 * Updates the TSL entry regardless of whether the country is already registered or not.
	 * If the country is not registered, it logs the action and proceeds with the update.
	 *
	 * @param idModeReg Registration mode ID.
	 * @param schemeTerritory Country or region code (Scheme Territory).
	 * @param tslLocation Location (URL) of the TSL.
	 * @param fullTSLxml Raw byte content of the TSL file.
	 * @param iTSLObjectTslDownload Parsed TSL object from the downloaded file.
	 * @param numTslUpdated An {@link AtomicInteger} used to track the number of TSLs successfully updated during this operation.
	 * @throws TSLManagingException If a TSL management error occurs.
	 * @throws CommonUtilsException If a utility-related error occurs.
	 * @throws CertificateEncodingException If certificate encoding fails.
	 * @throws TSLArgumentException If a TSL argument is invalid.
	 * @throws TSLParsingException If parsing the TSL fails.
	 * @throws TSLMalformedException If the TSL structure is malformed.
	 * @throws IOException If an I/O error occurs.
	 */
	private void treatmentUpdateAllTsls(int idModeReg, String schemeTerritory, String tslLocation, byte[ ] fullTSLxml, ITSLObject iTSLObjectTslDownload, AtomicInteger numTslUpdated) throws TSLManagingException, CommonUtilsException, CertificateEncodingException, TSLArgumentException, TSLParsingException, TSLMalformedException, IOException {
		TslData tslDataBD = null;
		TslCountryRegion tslCountryRegion = null;
		// si existe la TSL para el país que estamos tratando evaluaremos si
		// existe una versión nueva.
		if (iTslCountryRegionService.existsTslForThisCountry(schemeTerritory)) {
			// Obtenemos la TSL de este pais a partir BD
			tslCountryRegion = iTslCountryRegionService.getTslCountryRegionWithTslData(schemeTerritory);
			tslDataBD = tslCountryRegion.getTslData();
		}
		// si no existe el país o no tiene una TSL asociada
		if (tslDataBD == null) {
			LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_023, new Object[ ] { UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory) }));
			updatesTSLforLoggingMode(idModeReg, null, tslLocation, fullTSLxml, iTSLObjectTslDownload, false, numTslUpdated);
			return;

		}
		// Comprobamos si la versión de la TSL descargada es superior a la
		// que ya tenemos en BD
		if (iTSLObjectTslDownload.getSchemeInformation().getTslSequenceNumber() > tslDataBD.getSequenceNumber()) {
			LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_017, new Object[ ] { tslCountryRegion.getCountryRegionName() }));
			updatesTSLforLoggingMode(idModeReg, tslDataBD, tslLocation, fullTSLxml, iTSLObjectTslDownload, false, numTslUpdated);
		}

	}

	/**
	 * Updates a TSL based on the selected registration mode.
	 *
	 * <p>This method performs one of the following actions depending on the given registration mode:</p>
	 * <ul>
	 *   <li><b>Mode 1</b>: Directly updates the TSL with the new data. If it's a new TSL, it adds it as new.</li>
	 *   <li><b>Mode 2</b>: Compares the TSL signer certificate with the one in the database. 
	 *       If they match, the TSL is updated; otherwise, it's added to the pending validation queue.</li>
	 *   <li><b>Mode 3</b>: Always adds the TSL to the pending validation queue.</li>
	 * </ul>
	 *
	 * @param idModeReg The registration mode:
	 *                  <ul>
	 *                    <li>1 = Always update or insert as new.</li>
	 *                    <li>2 = Update only if signer certificate matches existing.</li>
	 *                    <li>3 = Defer update and store as pending validation.</li>
	 *                  </ul>
	 * @param tslDataLotlBD The current TSL data stored in the system (if any).
	 * @param url The download URL of the new TSL.
	 * @param fullTSLxml The downloaded TSL content in raw XML byte format.
	 * @param iTSLObjectLotlDownload The parsed TSL object from the downloaded data.
	 * @param lotl Boolean indicating whether the TSL is a LOTL (List of Trusted Lists). If {@code true}, it's handled as a LOTL.
	 * @param numTslUpdated An {@link AtomicInteger} used to track the number of TSLs successfully updated during this operation.
	 * @throws TSLManagingException If the update operation fails at the management layer.
	 * @throws CommonUtilsException If a utility error occurs (e.g., file or I/O).
	 * @throws CertificateEncodingException If there's a problem encoding or comparing certificates.
	 * @throws TSLArgumentException If there are invalid arguments in the TSL structure.
	 * @throws TSLParsingException If the TSL parsing fails.
	 * @throws TSLMalformedException If the TSL structure is malformed.
	 * @throws IOException If an I/O error occurs.
	 */
	private void updatesTSLforLoggingMode(int idModeReg, TslData tslDataLotlBD, String url, byte[ ] fullTSLxml, ITSLObject iTSLObjectLotlDownload, Boolean lotl, AtomicInteger numTslUpdated) throws TSLManagingException, CommonUtilsException, CertificateEncodingException, TSLArgumentException, TSLParsingException, TSLMalformedException, IOException {
		if (idModeReg == NumberConstants.NUM1) {
			if (!this.isNewTslData(tslDataLotlBD)) {
				TSLManager.getInstance().updateTSLData(tslDataLotlBD, fullTSLxml, url, tslDataLotlBD.getLegibleDocument());
			} else {
				TSLManager.getInstance().addNewTSLData(iTSLObjectLotlDownload, url, fullTSLxml, lotl);
			}

			numTslUpdated.incrementAndGet();

			// Dado que siempre registramos TSLs, si ya existe para este pais
			// alguna pendiente de validar la eliminaremos
			String country = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(iTSLObjectLotlDownload.getSchemeInformation().getSchemeTerritory());
			if (iTslPendValService.exitsTslPendVal(country)) {
				TslPendVal tslPendVal = iTslPendValService.obtainTslPendVal(country);
				iTslPendValService.declineTslPendVal(tslPendVal);
			}
		} else if (idModeReg == NumberConstants.NUM2) {
			if (!this.isNewTslData(tslDataLotlBD)) {
				X509Certificate x509CertSignFromTslDownload = iTSLObjectLotlDownload.getSignTsl().get();
				X509Certificate x509CertSignFromTslBD = this.obtainTSLObject(tslDataLotlBD.getXmlDocument()).getSignTsl().get();
				// Si es el mismo firmante actualizamos las TSLs
				if (Arrays.equals(x509CertSignFromTslDownload.getEncoded(), x509CertSignFromTslBD.getEncoded())) {

					TSLManager.getInstance().updateTSLData(tslDataLotlBD, fullTSLxml, url, tslDataLotlBD.getLegibleDocument());
					numTslUpdated.incrementAndGet();

				} else {
					// Si no es el mismo firmante lo añadimos a las TSLs
					// pendientes de registrar
					insertNewTslPendVal(fullTSLxml, iTSLObjectLotlDownload);
				}
			} else {
				insertNewTslPendVal(fullTSLxml, iTSLObjectLotlDownload);
			}
		} else if (idModeReg == NumberConstants.NUM3) {
			insertNewTslPendVal(fullTSLxml, iTSLObjectLotlDownload);
		}
	}

	/**
	 * Determines whether the given TSL data object represents a new (non-existing) entry.
	 *
	 * @param tslDataLotlBD The TSL data object to check.
	 * @return {@code true} if the TSL data is {@code null} (i.e., it's new and not yet stored); 
	 *         {@code false} if it already exists in the system.
	 */
	private boolean isNewTslData(TslData tslDataLotlBD) {
		if (tslDataLotlBD != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Inserts a new TSL entry into the pending validation queue if it does not already exist.
	 *
	 * <p>This method checks whether a TSL pending validation for the specified country
	 * is already registered. If not, it creates and adds a new pending validation record.</p>
	 *
	 * @param fullTSLxml The raw XML bytes of the downloaded TSL.
	 * @param iTSLObjectLotlDownload The parsed TSL object from which the country code is extracted.
	 */
	private void insertNewTslPendVal(byte[ ] fullTSLxml, ITSLObject iTSLObjectLotlDownload) {
		String country = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(iTSLObjectLotlDownload.getSchemeInformation().getSchemeTerritory());
		if (!iTslPendValService.exitsTslPendVal(country)) {
			LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_018, new Object[ ] { country }));
			TslPendVal tslPendVal = new TslPendVal();
			tslPendVal.setXmlDocument(fullTSLxml);
			tslPendVal.setCountry(country);
			iTslPendValService.addTslPendVal(tslPendVal);
		} else {
			LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_019, new Object[ ] { country }));
		}
	}

	/**
	 * Parses a TSL object from the given XML byte array.
	 *
	 * @param fullTSLxml The raw XML data of the TSL.
	 * @return The parsed ITSLObject instance.
	 * @throws TSLArgumentException If the input parameters are invalid.
	 * @throws TSLParsingException If the XML cannot be parsed correctly.
	 * @throws TSLMalformedException If the TSL XML is malformed.
	 * @throws IOException If an I/O error occurs during parsing.
	 */
	private ITSLObject obtainTSLObject(byte[ ] fullTSLxml) throws TSLArgumentException, TSLParsingException, TSLMalformedException, IOException {
		ITSLObject iTSLObjectLotlDownload;
		// Abrimos un InputStream para el array de bytes.
		try (ByteArrayInputStream bais = new ByteArrayInputStream(fullTSLxml)) {
			iTSLObjectLotlDownload = new TSLObject(TSLSpecificationsVersions.SPECIFICATION_119612);
			iTSLObjectLotlDownload.buildTSLFromXMLcheckValues(bais);
		}
		return iTSLObjectLotlDownload;
	}

	/**
	 * Downloads TSL data from the specified URL.
	 *
	 * @param url The URL to download the TSL XML from.
	 * @return The downloaded TSL content as a byte array.
	 * @throws CommonUtilsException If an error occurs during the HTTP request.
	 */
	private byte[ ] downloadTslFromUrl(String url) throws CommonUtilsException {
		return UtilsHTTP.getDataFromURI(url, NumberConstants.NUM10000, NumberConstants.NUM10000, null, null, httpHeadersMap);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected final void endMessage() {
		LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_END_MSG, new Object[ ] { Calendar.getInstance().getTimeInMillis()
				- startOperationTime }));
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
			if (distributionPoint == null
					|| method.getURI().getHost() == null) {
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
					ITSLObject tslObject = new TSLObject(actualTslImpl.getSpecification());
					// Abrimos un InputStream para el array de bytes.
					try (ByteArrayInputStream bais = new ByteArrayInputStream(fullTSLxml)) {
						tslObject.buildTSLFromXMLcheckValues(bais, false, false);
					} catch (Exception e1) {

						// Si no la hemos conseguido parsear, lo intentamos con
						// la
						// última
						// especificación disponible (siempre que no fuera esta
						// ya)...
						if (lastTslImpl != null
								&& !actualTslImpl.getIdTSLImpl().equals(lastTslImpl.getIdTSLImpl())) {
							LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_000, new Object[ ] { actualTslImpl.getSpecification(), actualTslImpl.getVersion(), lastTslImpl.getSpecification(), lastTslImpl.getVersion() }));
							tslObject = new TSLObject(lastTslImpl.getSpecification());
							try (ByteArrayInputStream bais = new ByteArrayInputStream(fullTSLxml)) {
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
		if (!UtilsStringChar.isNullOrEmpty(StaticValetConfig.getProperty(StaticValetConfig.DAYS_REMINDER_ALARM))) {
			daysReminder = Integer.valueOf(StaticValetConfig.getProperty(StaticValetConfig.DAYS_REMINDER_ALARM));
		}
		Date dateToCheck = UtilsDate.getDateAddingDays(tsldco.getLastNewTSLAvailableFind(), daysReminder);
		Date actualDate = Calendar.getInstance().getTime();
		LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_008, new Object[ ] { tsldco.getLastNewTSLAvailableFind(), tslCountryRegion }));
		if (dateToCheck.before(actualDate)) {
			LOGGER.info(Language.getFormatResWebGeneral(WebGeneralMessages.TASK_FIND_NEW_TSL_REV_LOG_009, new Object[ ] { daysReminder.toString() }));
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
