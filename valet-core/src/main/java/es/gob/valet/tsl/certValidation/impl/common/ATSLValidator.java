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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.ATSLValidator.java.</p>
 * <b>Description:</b><p>Abstract class that represents a TSL validator with the principal functions
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.8, 15/12/2020.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.net.URI;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.audit.access.IEventsCollectorConstants;
import es.gob.valet.audit.utils.CommonsCertificatesAuditTraces;
import es.gob.valet.audit.utils.CommonsTslAuditTraces;
import es.gob.valet.commons.utils.UtilsCRL;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsOCSP;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.persistence.configuration.model.utils.IAlarmIdConstants;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidator;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLQualificationEvalProcessException;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension;
import es.gob.valet.tsl.parsing.ifaces.ITSLCommonURIs;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.DigitalID;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;
import es.gob.valet.tsl.parsing.impl.common.extensions.AdditionalServiceInformation;
import es.gob.valet.tsl.parsing.impl.common.extensions.CriteriaList;
import es.gob.valet.tsl.parsing.impl.common.extensions.QualificationElement;
import es.gob.valet.tsl.parsing.impl.common.extensions.Qualifications;

/**
 * <p>Abstract class that represents a TSL validator with the principal functions
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 15/12/2020.
 */
public abstract class ATSLValidator implements ITSLValidator {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ATSLValidator.class);

	/**
	 * Constant attribute that represents a token for a TSP Service Name when the validation has been executed
	 * using the Distribution Point of the certificate to validate.
	 */
	public static final String TSP_SERVICE_NAME_FOR_DIST_POINT = "TSPService-Certificate-DistributionPoint";

	/**
	 * Attribute that represents the TSL object to use for validate certificates.
	 */
	private ITSLObject tsl = null;

	/**
	 * Constructor method for the class ATSLValidator.java.
	 */
	public ATSLValidator() {
		super();
	}

	/**
	 * Constructor method for the class ATSLValidator.java.
	 * @param tslObject TSL to use for validate certificates.
	 */
	protected ATSLValidator(ITSLObject tslObject) {
		this();
		tsl = tslObject;
	}

	/**
	 * Gets the TSL object used to validate certificates.
	 * @return the TSL object used to validate certificates.
	 */
	protected final ITSLObject getTSLObject() {
		return tsl;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidator#validateCertificateWithTSL(java.lang.String, java.security.cert.X509Certificate, boolean, boolean, java.util.Date, boolean)
	 */
	@Override
	public ITSLValidatorResult validateCertificateWithTSL(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation) throws TSLArgumentException, TSLValidationException {

		// Comprobamos que el certificado de entrada no sea nulo.
		if (cert == null) {
			throw new TSLArgumentException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL107));
		}

		// Comprobamos que la fecha de entrada no sea nula.
		if (validationDate == null) {
			throw new TSLArgumentException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL144));
		}

		// Inicializamos el resultado a devolver.
		TSLValidatorResult result = new TSLValidatorResult(cert, getTSLObject());

		// Establecemos si se trata de una norma Europea o externa.
		result.setEuropean(checkIfTSLisFromEuropeanMember());

		// Comprobamos si el tipo de la TSL determina si se trata de una lista
		// de listas...
		if (checkIfTSLisListOfLists(tsl.getSchemeInformation().getTslType().toString())) {

			// Si se trata de una lista de listas...
			validateCertificateWithListOfLists(cert, isCACert, isTsaCertificate, validationDate, checkStatusRevocation, result);

		} else {

			// Si no es una lista de listas, continuamos con la validación.
			validateCertificate(auditTransNumber, cert, isCACert, isTsaCertificate, validationDate, checkStatusRevocation, result);

		}

		return result;

	}

	/**
	 * Cehcks if the TSL is from an European Member.
	 * @return <code>true</code> if the TSL is from European Member, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSLisFromEuropeanMember();

	/**
	 * Checks if the TSL is a List of Lists.
	 * @param tslType String that represents the TSL type to analyze.
	 * @return <code>true</code> if the TSL is a list of lists, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSLisListOfLists(String tslType);

	/**
	 * Validates the input certificate knowing this TSL is a List of Lists.
	 * @param cert Certificate X509 v3 to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @param validationResult Object where stores the validation result data.
	 */
	private void validateCertificateWithListOfLists(X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation, TSLValidatorResult validationResult) {

		// TODO De momento no se consideran las listas de listas.
		// Si se trata de una lista de listas, la ignoramos y concluímos que no
		// se puede validar el certificado
		// indicando como no detectado (valor por defecto en la respuesta).
		LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL202));

	}

	/**
	 * Validates the input certificate knowing this TSL is not list of lists.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert Certificate X509 v3 to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @param validationResult Object where stores the validation result data.
	 * @throws TSLValidationException If there is some error or inconsistency in the certificate validation.
	 */
	private void validateCertificate(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation, TSLValidatorResult validationResult) throws TSLValidationException {

		// Comprobamos que el "Status Determination Approach" no sea
		// "delinquent" o equivalente.
		if (checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(tsl.getSchemeInformation().getStatusDeterminationApproach().toString())) {

			throw new TSLValidationException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL108));

		} else {

			// TODO: Aún no se hace nada con las extensiones del esquema. No se
			// identifica ninguna.
			// doSomethingWithSchemeExtensions();

			// Recuperamos la lista de TSP y vamos analizando uno a uno.
			List<TrustServiceProvider> tspList = tsl.getTrustServiceProviderList();
			// Si la lista no es nula ni vacía...
			if (tspList != null && !tspList.isEmpty()) {

				// La vamos recorriendo mientras no se termine y no se haya
				// modificado el resultado de la validación del certificado.
				for (int index = 0; index < tspList.size() && !validationResult.hasBeenDetectedTheCertificate(); index++) {

					// Almacenamos en una variable el TSP a tratar.
					TrustServiceProvider tsp = tspList.get(index);

					// Validamos el certificado respecto al TSP.
					try {
						validateCertificateWithTSP(auditTransNumber, cert, isCACert, isTsaCertificate, validationDate, validationResult, tsp, checkStatusRevocation);
					} catch (TSLQualificationEvalProcessException e) {

						// Si se produce esta excepción, significa que se
						// produjo un error
						// evaluando el certificado frente a un CriteriaList de
						// un QualificationExtension,
						// siendo esta una extensión crítica. En consecuencia se
						// debe considerar
						// como certificado no detectado, e impedir que se
						// continúen evaluando
						// otros servicios.
						// Mostramos en el log el motivo y la excepción.
						LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL250));
						LOGGER.error(e.getMessage(), e.getException());
						// Limpiamos toda la información acumulada hasta el
						// momento.
						validationResult.resetAllData();
						// Cancelamos que el proceso continúe.
						break;

					}

					// Si el certificado se ha detectado, almacenamos el TSP
					// usado
					// y su nombre.
					if (validationResult.hasBeenDetectedTheCertificate()) {
						assignTSPandNameToResult(validationResult, tsp);
					}

				}

			}

			// Si no ha sido detectado el certificado, lo indicamos en
			// auditoría.
			if (!validationResult.hasBeenDetectedTheCertificate()) {
				CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, false, null, null, null, null);
			}

		}

		// Mostramos en el log si el certificado ha sido detectado/validado y
		// por cual TSP y servicio.
		showInLogResultOfValidation(validationResult, checkStatusRevocation);

	}

	/**
	 * Shows in log the result of the validation.
	 * @param validationResult Object where is stored the validation result data.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 */
	private void showInLogResultOfValidation(TSLValidatorResult validationResult, boolean checkStatusRevocation) {

		// Si el certificado ha sido detectado...
		if (validationResult.hasBeenDetectedTheCertificate()) {

			String detectedWithShiMsg = UtilsStringChar.EMPTY_STRING;
			if (validationResult.getTSPServiceForDetect().getServiceInformation() == validationResult.getTSPServiceHistoryInformationInstanceForDetect()) {
				detectedWithShiMsg = Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL260, new Object[ ] { validationResult.getTSPServiceHistoryInformationInstanceNameForDetect(), validationResult.getTSPServiceHistoryInformationInstanceForDetect().getServiceStatusStartingTime() });
			}

			// Lo analizamos en función de si se ha comprobado su estado de
			// revocación.
			// Si se desconoce el estado del certificado...
			if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {

				// Si había que validarlo...
				if (checkStatusRevocation) {

					// El certificado ha sido detectado pero no validado.
					LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL204, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg }));
					// Lanzamos la alarma correspondiente...
					AlarmsManager.getInstance().registerAlarmEvent(IAlarmIdConstants.ALM001_UNKNOWN_REV_STATUS, Language.getFormatResCoreGeneral(ICoreGeneralMessages.ALM001_EVENT_000, new Object[ ] { validationResult.getTslCountryRegionCode(), validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg }));

				}
				// Si no había que validarlo, sino solo detectarlo.
				else {

					LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL228, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect() }));

				}

			}
			// Si el certificado ha sido detectado y validado...
			else {

				if (UtilsStringChar.isNullOrEmptyTrim(validationResult.getTSPServiceNameForValidate())) {

					// En función del resultado exacto...
					switch (validationResult.getResult()) {

						case ITSLValidatorResult.RESULT_DETECTED_STATE_VALID:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL206, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL207) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL206, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL208) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL206, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL209) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL206, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL210) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL206, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL211) }));
							break;

						default:
							break;

					}

				} else {

					String validatedWithShiMsg = UtilsStringChar.EMPTY_STRING;
					if (validationResult.getTSPServiceForValidate().getServiceInformation() == validationResult.getTSPServiceHistoryInformationInstanceForValidate()) {
						validatedWithShiMsg = Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL260, new Object[ ] { validationResult.getTSPServiceHistoryInformationInstanceNameForValidate(), validationResult.getTSPServiceHistoryInformationInstanceForValidate().getServiceStatusStartingTime() });
					}

					// En función del resultado exacto...
					switch (validationResult.getResult()) {

						case ITSLValidatorResult.RESULT_DETECTED_STATE_VALID:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL205, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL207) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL205, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL208) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL205, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL209) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL205, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL210) }));
							break;

						case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS:
							LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL205, new Object[ ] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg, validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL211) }));
							break;

						default:
							break;

					}

				}

			}

		} else {

			// El certificado no ha sido detectado por la TSL.
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL203));

		}

	}

	/**
	 * Check if the Status Determination Approach of the TSL is set to Delinquent or equivalent.
	 * @param statusDeterminationApproach String that represents the Status Determination Approach to check.
	 * @return <code>true</code> if the Status Determination Approach of the TSL is set to Delinquent or equivalent,
	 * otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(String statusDeterminationApproach);

	/**
	 * Method that assign the TSP and its name to the validation result.
	 * @param validationResult Object where stores the validation result data.
	 * @param tsp Trust Service Provider to use for validate the status of the input certificate.
	 */
	private void assignTSPandNameToResult(TSLValidatorResult validationResult, TrustServiceProvider tsp) {

		validationResult.setTSP(tsp);

		String tspName = getTSPName(tsp);

		if (tspName != null) {
			validationResult.setTSPName(tspName);
		}

	}

	/**
	 * Auxiliar method to extract a TSP name from the TSP provider.
	 * @param tsp TSP provider from which extracts the name.
	 * @return TSP name from the TSP provider.
	 */
	private String getTSPName(TrustServiceProvider tsp) {

		String result = null;

		// Verificamos que haya algún nombre asignado al TSP.
		if (tsp.getTspInformation().isThereSomeName()) {

			// Recuperamos el correspondiente al idioma inglés por defecto.
			List<String> tspNamesEnglish = tsp.getTspInformation().getTSPNamesForLanguage(Locale.UK.getLanguage());

			// Si lo hemos obtenido, asignamos el nombre al resultado.
			if (tspNamesEnglish != null && !tspNamesEnglish.isEmpty()) {

				result = tspNamesEnglish.get(0);

			} else {

				// Si no lo hemos obtenido, tomamos el primer nombre que
				// aparezca.
				Map<String, List<String>> tspNames = tsp.getTspInformation().getAllTSPNames();
				tspNamesEnglish = tspNames.values().iterator().next();
				// Si lo hemos obtenido, asignamos el nombre al resultado.
				if (tspNamesEnglish != null && !tspNamesEnglish.isEmpty()) {

					result = tspNamesEnglish.get(0);

				}

			}

		}

		return result;

	}

	/**
	 * Tries to validate the input certificate with the input Trust Service Provider information.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert Certificate X509 v3 to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where stores the validation result data.
	 * @param tsp Trust Service Provider to use for validate the status of the input certificate.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the Criteria List of a Qualification
	 * Extension over the input certificate, and being critical that Qualification Extension.
	 */
	private void validateCertificateWithTSP(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp, boolean checkStatusRevocation) throws TSLQualificationEvalProcessException {

		// TODO: Aún no se hace nada con las extensiones del TSP. No se
		// identifica ninguna.
		// doSomethingWithTSPExtensions();

		// Obtenemos la lista de servicios.
		List<TSPService> tspServiceList = tsp.getAllTSPServices();

		// Si la lista no es nula ni vacía...
		if (tspServiceList != null && !tspServiceList.isEmpty()) {

			// La vamos recorriendo mientras no se termine y no se haya
			// detectado el certificado.
			for (int index = 0; index < tspServiceList.size() && !validationResult.hasBeenDetectedTheCertificate(); index++) {

				// Almacenamos en una variable el servicio a analizar en esta
				// vuelta.
				TSPService tspService = tspServiceList.get(index);

				// Tratamos de detectar el certificado respecto al servicio y la fecha de emisión del certificado
				detectCertificateWithTSPService(cert, isCACert, isTsaCertificate, cert.getNotBefore(), validationResult, tspService);

				// Si el certificado se ha detectado...
				if (validationResult.hasBeenDetectedTheCertificate()) {

					// Almacenamos el nombre del TSP Service usado.
					assignTSPServiceNameForDetectToResult(validationResult, tspService);
					// Y el servicio.
					validationResult.setTSPServiceForDetect(tspService);

					// Auditoría: Certificado detectado.
					CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, true, validationResult.getTslCountryRegionCode(), getTSPName(tsp), validationResult.getTSPServiceNameForDetect(), validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());

					// Si el estado no es desconocido, significa que ya se ha
					// determinado la validez del certificado,
					// por lo que asignamos el mismo nombre de servicio al
					// resultado de la validación (y el servicio).
					if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
						LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL215));
						validationResult.setResultFromServiceStatus(Boolean.TRUE);
						validationResult.setResultFromDPorAIA(Boolean.FALSE);
						validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
						validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
						validationResult.setTspServiceHistoryInformationInstanceNameForValidate(validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
						validationResult.setTspServiceHistoryInformationInstanceForValidate(validationResult.getTSPServiceHistoryInformationInstanceForDetect());
						// Indicamos que se considera validado por el servicio
						// en auditoría.
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), true, false, null, null, null, null);
					}

				}

			}

			// Si hay que comprobar el estado de revocación y aún no se ha
			// determinado o se trata de un certificado detectado de CA no
			// root...
			if (checkStatusRevocation && (validationResult.hasBeenDetectedTheCertificateWithUnknownState() || validationResult.hasBeenDetectedTheCertificate() && isCACert && !UtilsCertificate.isSelfSigned(cert))) {

				// Tratamos de validar el estado de revocación mediante los
				// puntos de distribución
				// establecidos en el propio certificado.
				LOGGER.debug(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL216, new Object[] {validationDate.toString()}));
				validateCertificateUsingDistributionPoints(cert, isCACert, isTsaCertificate, validationDate, validationResult, tsp);

				// Si el estado no es desconocido, significa que ya se ha
				// determinado la validez del certificado haciendo uso del
				// DistributionPoint, por lo que lo indicamos en el resultado.
				if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState() && validationResult.isResultFromDPorAIA()) {
					validationResult.setResultFromServiceStatus(Boolean.FALSE);
					validationResult.setTSPServiceNameForValidate(TSP_SERVICE_NAME_FOR_DIST_POINT);
					validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
					if (validationResult.getTSPServiceHistoryInformationInstanceForDetect() != null) {
						validationResult.setTspServiceHistoryInformationInstanceNameForValidate(TSP_SERVICE_NAME_FOR_DIST_POINT);
						validationResult.setTspServiceHistoryInformationInstanceForValidate(validationResult.getTSPServiceHistoryInformationInstanceForDetect());
					}
					// Indicamos en auditoría la información del elemento de
					// revocación usado según haya sido OCSP o CRL.
					if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
						CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(auditTransNumber, validationResult.getRevocationValueURL(), validationResult.getRevocationValueBasicOCSPResponse());
					} else {
						CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(auditTransNumber, validationResult.getRevocationValueURL(), validationResult.getRevocationValueCRL());
					}
					// Indicamos en auditoría que hemos obtenido el resultado
					// mediante DP/AIA.
					CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), false, true, null, null, null, null);
				}
				// Si no es así, hay que tratar de hacerlo mediante los
				// servicios de la TSL (siempre y cuando no sea un certificado
				// de sello de tiempo ni de CA)
				else if (!isTsaCertificate && !isCACert) {

					LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL217));

					// Almacenamos en una variable si el certificado es
					// cualificado o no.
					boolean isCertQualified = validationResult.getMappingType() == ITSLValidatorResult.MAPPING_TYPE_QUALIFIED;

					// Recorremos la lista buscando servicios que permitan
					// validar
					// el estado del certificado.
					// Seguimos intentándolo mientras el estado siga siendo
					// detectado pero Unknown.
					for (int index = 0; index < tspServiceList.size() && validationResult.hasBeenDetectedTheCertificateWithUnknownState(); index++) {

						// Almacenamos en una variable el servicio a analizar en
						// esta vuelta.
						TSPService tspService = tspServiceList.get(index);

						// Validamos el certificado con la información que haya
						// en
						// el servicio TSP.
						validateCertificateWithTSPService(cert, validationDate, isCertQualified, validationResult, tspService);

						// Si el estado no es desconocido, significa que ya se
						// ha determinado la validez del certificado,
						// así que indicamos en el resultado el nombre del
						// servicio usado para ello (y el servicio).
						if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
							validationResult.setResultFromServiceStatus(Boolean.FALSE);
							assignTSPServiceNameForValidateToResult(validationResult, tspService);
							validationResult.setTSPServiceForValidate(tspService);
							if (validationResult.getTSPServiceHistoryInformationInstanceForValidate() != null) {
								assignTSPServiceHistoryInformationNameForValidateToResult(validationResult, validationResult.getTSPServiceHistoryInformationInstanceForValidate());
							}
							// Indicamos en auditoría la información del
							// elemento de revocación usado según haya sido OCSP
							// o CRL.
							if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
								CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(auditTransNumber, validationResult.getRevocationValueURL(), validationResult.getRevocationValueBasicOCSPResponse());
							} else {
								CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(auditTransNumber, validationResult.getRevocationValueURL(), validationResult.getRevocationValueCRL());
							}
							// Indicamos en auditoría que hemos obtenido el
							// resultado
							// mediante un servicio.
							CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), false, false, validationResult.getTslCountryRegionCode(), getTSPName(tsp), validationResult.getTSPServiceNameForValidate(), validationResult.getTSPServiceHistoryInformationInstanceNameForValidate());
						}

					}

				}

				// Si el estado de revocación es desconocido...
				if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
					CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, false, null, null, null, null, null, null, null);
				}

			}

		}

	}

	/**
	 * Tries to detect the input certificate with the input Trust Service Provider Service information.
	 * @param cert Certificate X509 v3 to detect.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where is stored the validation result data.
	 * @param tspService Trust Service Provider Service to use for detect the input certificate.
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the Criteria List of a Qualification
	 * Extension over the input certificate, and being critical that Qualification Extension.
	 */
	private void detectCertificateWithTSPService(X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TSPService tspService) throws TSLQualificationEvalProcessException {

		// Primero, en función de la fecha indicada, comprobamos
		// si tenemos que hacer uso de este servicio o de alguno
		// de sus históricos.
		ServiceHistoryInstance shi = null;
		boolean isHistoricServiceInf = false;
		
		if (tspService.getServiceInformation().getServiceStatusStartingTime().before(validationDate)) {

			if (tspService.getServiceInformation().isServiceValidAndUsable()) {
				shi = tspService.getServiceInformation();
			}

		} else {

			if (tspService.isThereSomeServiceHistory()) {

				List<ServiceHistoryInstance> shiList = tspService.getAllServiceHistory();
				for (ServiceHistoryInstance shiFromList: shiList) {
					if (shiFromList.getServiceStatusStartingTime().before(validationDate)) {
						if (shiFromList.isServiceValidAndUsable()) {
							shi = shiFromList;
							isHistoricServiceInf = true;
						}
						break;
					}
				}

			}

		}

		// Si hemos encontrado al menos uno, intentamos detectar el certificado
		// con esa información de servicio.
		if (shi != null) {
			detectCertificateWithTSPServiceHistoryInstance(cert, isCACert, isTsaCertificate, validationDate, validationResult, tspService, shi, isHistoricServiceInf);
		}

	}

	/**
	 * Tries to detect the input certificate with the input Trust Service Provider Service History Information.
	 * @param cert Certificate X509 v3 to detect.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where is stored the validation result data.
	 * @param tspService Trust Service Provider Service to use for detect the input certificate.
	 * @param shi Trust Service Provider Service History-Information to use for detect the input certificate.
	 * @param isHistoricServiceInf Flag that indicates if the input Service Information is from an Historic Service (<code>true</code>)
	 * or not (<code>false</code>).
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the Criteria List of a Qualification
	 * Extension over the input certificate, and being critical that Qualification Extension.
	 */
	private void detectCertificateWithTSPServiceHistoryInstance(X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TSPService tspService, ServiceHistoryInstance shi, boolean isHistoricServiceInf) throws TSLQualificationEvalProcessException {

		// Obtenemos el tipo del servicio.
		String tspServiceType = shi.getServiceTypeIdentifier().toString();

		// Si el certificado corresponde con uno de sello de tiempo, tendremos
		// en cuenta
		// tan solo los servicios de tipo TSA.
		if (isTsaCertificate) {
			//LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL274));

			// Comprobamos si el servicio es de tipo TSA (cualificado o no).
			if (checkIfTSPServiceTypeIsTSAQualified(tspServiceType) || checkIfTSPServiceTypeIsTSANonQualified(tspServiceType)) {
				// Comprobamos si dicho servicio identifica al certificado...
				if (checkIfDigitalIdentitiesMatchesCertificate(shi.getAllDigitalIdentities(), cert, isTsaCertificate)) {
					LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL275, new Object[] {tspService.getServiceInformation().getServiceTypeIdentifier().toString()}));
					// Establecemos la clasificación a sello de tiempo.
					validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_TSA);

					// Establecemos su tipo.
					// Si es una TSA "qualified"...
					if (checkIfTSPServiceTypeIsTSAQualified(tspServiceType)) {
						validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_QUALIFIED);
						LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL276, new Object[] {tspService.getServiceInformation().getServiceTypeIdentifier().toString()}));

					}
					// Si no...
					else if (checkIfTSPServiceTypeIsTSANonQualified(tspServiceType)) {

						validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_NONQUALIFIED);
						LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL277, new Object[] {tspService.getServiceInformation().getServiceTypeIdentifier().toString()}));

					}

					// Indicamos que es detectado.
					validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_UNKNOWN);

					// Se establece el resultado según el estado del servicio.
					setStatusResultInAccordanceWithTSPServiceCurrentStatus(isCACert, shi.getServiceStatus().toString(), shi.getServiceStatusStartingTime(), validationDate, validationResult);

					// Si se trata de un servicio histórico, guardamos la
					// información
					// de este.
					if (isHistoricServiceInf) {
						LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL278));
						assignTSPServiceHistoryInformationNameForDetectToResult(validationResult, shi);
						validationResult.setTSPServiceHistoryInformationInstanceForDetect(shi);
					}

				}

			}

		}
		// Si no, los servicios de tipo CA.
		else {

			// Comprobamos si el servicio es de tipo CA (certificados
			// cualificados o no).
			if (checkIfTSPServiceTypeIsCAQC(tspServiceType) || checkIfTSPServiceTypeIsCAPKC(tspServiceType) || checkIfTSPServiceTypeIsNationalRootCAQC(tspServiceType)) {

				// Comprobamos si dicho servicio identifica al certificado...
				// Si es una CA, comprobamos en sus identidades digitales que
				// coincida con alguna de las declaradas, si no,
				// que alguna de estas sea la emisora del certificado.
				if (checkIfCADigitalIdentitiesVerifyCertificateAndSetItInResult(shi.getAllDigitalIdentities(), cert, isCACert, validationResult)) {

					// Creamos una bandera que indica si de momento hemos
					// detectado el certificado.
					Boolean detectedCert = null;

					// Si se trata de una TSL de un miembro europeo y de una CA
					// para certificados "qualified"...
					if (checkIfTSLisFromEuropeanMember() && (checkIfTSPServiceTypeIsCAQC(tspServiceType) || checkIfTSPServiceTypeIsNationalRootCAQC(tspServiceType))) {

						// Si es el certificado de una CA...
						if (isCACert) {
							LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL284));
							// La consideramos detectada y cualificada.
							detectedCert = Boolean.TRUE;
							validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_QUALIFIED);

						}
						// Si es un tipo final...
						else {

							// Comprobamos que los valores de las extensiones
							// AdditionalServiceInformation concuerdan con
							// los del certificado. Esto depende de la
							// especificación.
							detectedCert = checkIfTSPServiceAdditionalServiceInformationExtensionsDetectCert(validationResult, shi);

							// Si se ha obtenido null, es porque no está
							// definida la
							// extensión
							// AdditionalServiceInformation, en cuyo caso
							// consideramos que el
							// certificado NO es cualificado (al menos de
							// momento).
							if (detectedCert == null) {

								// TODO Según se indica en la especificación y
								// así
								// confirma MINETUR,
								// la extensión AdditionalServiceInformation
								// debe
								// ser obligatoria (cumplirla) en este caso.
								// Por consenso con Dirección de Proyecto se
								// permite
								// su relajación.
								// Lo informamos en un mensaje de log.
								LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL247));
								validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_NONQUALIFIED);

							}
							// Si se ha encontrado la extensión, y el
							// certificado
							// encaja con su
							// definición, entonces lo consideramos cualificado.
							else if (detectedCert.booleanValue()) {
								LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL285));
								validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_QUALIFIED);

							}

							// Por último, si está definida la extensión pero el
							// certificado no encaja
							// con su definición, consideramos que desconocemos
							// si
							// es cualificado o no.

							// Comprobamos en la extensión qualifications si se
							// // detecta el tipo de certificado.
							boolean detectedInQualificationsExtension = checkIfTSPServiceQualificationsExtensionsDetectCert(cert, validationResult, shi);

							// Concluimos si consideramos detectado el
							// certificado
							// en función del valor que ya tuviera y el obtenido
							// analizando la extensión Qualifications.
							detectedCert = detectedCert == null ? detectedInQualificationsExtension : detectedCert || detectedInQualificationsExtension;

						}

					}

					// Si se trata de una CA/PKC, o el estado del servicio es  "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/withdrawn" sabemos que es un certificado
					// "non qualified".
					if (checkIfTSPServiceTypeIsCAPKC(tspServiceType) || isWithdrawnBeforeDateOfIssue(cert, tspService)) {
						if(checkIfTSPServiceTypeIsCAPKC(tspServiceType)){
							LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL286));
						}else {
							LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL287));
						}
						validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_NONQUALIFIED);
					}

					// Si finalmente hemos detectado el certificado, o es una
					// CA PKC:
					// 1 - establecemos el resultado como detectado.
					// 2 - lo modificamos según el estado del servicio.
					// define la clasificación del certificado
					// (si no se ha determinado ya).
					if (detectedCert != null || checkIfTSPServiceTypeIsCAPKC(tspServiceType)) {
						// Indicamos que es detectado.
						validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_UNKNOWN);
						// Se establece el resultado según el estado.
						setStatusResultInAccordanceWithTSPServiceCurrentStatus(isCACert, shi.getServiceStatus().toString(), shi.getServiceStatusStartingTime(), validationDate, validationResult);
						// Guardamos la información del servicio histórico
						// usado.
						if (isHistoricServiceInf) {
							assignTSPServiceHistoryInformationNameForDetectToResult(validationResult, shi);
							validationResult.setTSPServiceHistoryInformationInstanceForDetect(shi);
						}

					}

				}

			}

		}

	}

	/**
	 * Checks if the input TSP Service Type is CA PKC.
	 * @param tspServiceType TSP Service type URI to check.
	 * @return <code>true</code> if it represents a CA PKC TSP Service, otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCAPKC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is CA QC.
	 * @param tspServiceType TSP Service type URI to check.
	 * @return <code>true</code> if it represents a CA QC TSP Service, otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCAQC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is National Root CA for Qualified Certificates.
	 * @param tspServiceType TSP Service type URI to check.
	 * @return <code>true</code> if it represents a National Root CA QC TSP Service, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsNationalRootCAQC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is for a qualified TSA.
	 * @param tspServiceType TSP Service type URI to check.
	 * @return <code>true</code> if it represents a Qualified TSA TSP Service, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsTSAQualified(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is for a non qualified TSA.
	 * @param tspServiceType TSP Service type URI to check.
	 * @return <code>true</code> if it represents a Non Qualified TSA TSP Service, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsTSANonQualified(String tspServiceType);

	/**
	 * Checks if some of the input CA identities detect the input X509v3 certificate and then set its information
	 * on the result.
	 * @param digitalIdentitiesList List of CA digital identities.
	 * @param cert X509v3 certificate to check.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationResult Object where is stored the validation result data.
	 * @return <code>true</code> if the certificate is issued by some of the input identities, otherwise <code>false</code>.
	 */
	private boolean checkIfCADigitalIdentitiesVerifyCertificateAndSetItInResult(List<DigitalID> digitalIdentitiesList, X509Certificate cert, boolean isCACert, TSLValidatorResult validationResult) {

		// Por defecto consideramos que no lo detecta,
		// y a la primera identidad
		// que coincida, se le cambia el resultado.
		boolean result = false;

		// Si la lista de identidades no es nula ni vacía...
		if (digitalIdentitiesList != null && !digitalIdentitiesList.isEmpty()) {

			// Creamos el procesador de identidades digitales.
			DigitalIdentitiesProcessor dip = new DigitalIdentitiesProcessor(digitalIdentitiesList);
			// Procesamos el certificado a validar y modificamos el resultado si
			// fuera necesario.
			if (isCACert) {
				result = dip.checkIfDigitalIdentitiesMatchesCertificate(cert);
			} else {
				result = dip.checkIfCertificateIsIssuedBySomeIdentity(cert, validationResult);
			}

			// Si se ha encontrado el certificado, lo indicamos en el
			// log.
			if (result) {

				LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL213));

				// Además, si el certificado es de CA y autoemitido, podemos
				// definir
				// las propiedades de su emisor (él mismo).
				if (isCACert && UtilsCertificate.isSelfSigned(cert)) {

					validationResult.setIssuerCert(cert);
					validationResult.setIssuerPublicKey(cert.getPublicKey());
					try {
						validationResult.setIssuerSubjectName(UtilsCertificate.getCertificateId(cert));
					} catch (CommonUtilsException e) {
						LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL182));
					}

				}

			}

		} else {

			// Si no hay identidades digitales, se considera que no se ha
			// encontrado.
			result = false;

		}

		return result;

	}

	/**
	 * Checks if some of the input identities matches with the input X509v3 certificate.
	 * @param digitalIdentitiesList List of digital identities.
	 * @param cert X509v3 certificate to check.
	 * @param isTsaService Flag to indicate if the digital identities are from a TSA Service or a
	 * CA Service.
	 * @return <code>true</code> if the certificate matches with some of the input identities, otherwise <code>false</code>.
	 */
	private boolean checkIfDigitalIdentitiesMatchesCertificate(List<DigitalID> digitalIdentitiesList, X509Certificate cert, boolean isTsaService) {

		// Por defecto consideramos que no coincide con ninguna identidad,
		// y a la primera identidad que coincida, se le cambia el resultado.
		boolean result = false;

		// Si la lista de identidades no es nula ni vacía...
		if (digitalIdentitiesList != null && !digitalIdentitiesList.isEmpty()) {

			// Creamos el procesador de identidades digitales.
			DigitalIdentitiesProcessor dip = new DigitalIdentitiesProcessor(digitalIdentitiesList);
			// Procesamos el certificado a validar.
			result = dip.checkIfDigitalIdentitiesMatchesCertificate(cert);

			// Si se ha encontrado, lo indicamos en el log.
			if (result) {
				if (isTsaService) {
					LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL212));
				} else {
					LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL263));
				}
			}

		} else {

			// Si no hay identidades digitales, se considera que no se ha
			// encontrado.
			result = false;

		}

		return result;

	}

	/**
	 * Method that assign the TSP Service name used to detect the certificate to the validation result.
	 * @param validationResult Object where is stored the validation result data.
	 * @param tspService TSP Service used for detect the input certificate.
	 */
	private void assignTSPServiceNameForDetectToResult(TSLValidatorResult validationResult, TSPService tspService) {

		// Verificamos que haya algún nombre asignado al servicio.
		if (tspService.getServiceInformation().isThereSomeServiceName()) {

			// Recuperamos el correspondiente al idioma inglés por defecto.
			String serviceName = tspService.getServiceInformation().getServiceNameInLanguage(Locale.UK.getLanguage());

			// Si no lo hemos obtenido, tomamos el primer nombre que
			// aparezca.
			if (UtilsStringChar.isNullOrEmptyTrim(serviceName)) {
				Map<String, String> tspServiceNamesMap = tspService.getServiceInformation().getServiceNames();
				serviceName = tspServiceNamesMap.values().iterator().next();
			}

			// Si lo hemos obtenido, asignamos el nombre al resultado.
			if (!UtilsStringChar.isNullOrEmptyTrim(serviceName)) {
				validationResult.setTSPServiceNameForDetect(serviceName);
			}

		}

	}

	/**
	 * Method that assign the TSP Service name used to validate the certificate to the validation result.
	 * @param validationResult Object where stores the validation result data.
	 * @param tspService TSP Service used for validate the input certificate.
	 */
	private void assignTSPServiceNameForValidateToResult(TSLValidatorResult validationResult, TSPService tspService) {

		// Verificamos que haya algún nombre asignado al servicio.
		if (tspService.getServiceInformation().isThereSomeServiceName()) {

			// Recuperamos el correspondiente al idioma inglés por defecto.
			String serviceName = tspService.getServiceInformation().getServiceNameInLanguage(Locale.UK.getLanguage());

			// Si no lo hemos obtenido, tomamos el primer nombre que
			// aparezca.
			if (UtilsStringChar.isNullOrEmptyTrim(serviceName)) {
				Map<String, String> tspServiceNamesMap = tspService.getServiceInformation().getServiceNames();
				serviceName = tspServiceNamesMap.values().iterator().next();
			}

			// Si lo hemos obtenido, asignamos el nombre al resultado.
			if (!UtilsStringChar.isNullOrEmptyTrim(serviceName)) {
				validationResult.setTSPServiceNameForValidate(serviceName);
			}

		}

	}

	/**
	 * Method that assign the TSP Service name used to detect the certificate to the validation result.
	 * @param validationResult Object where is stored the validation result data.
	 * @param shi TSP Service History Information used for detect the input certificate.
	 */
	private void assignTSPServiceHistoryInformationNameForDetectToResult(TSLValidatorResult validationResult, ServiceHistoryInstance shi) {

		// Verificamos que haya algún nombre asignado al servicio.
		if (shi.isThereSomeServiceName()) {

			// Recuperamos el correspondiente al idioma inglés por defecto.
			String shiName = shi.getServiceNameInLanguage(Locale.UK.getLanguage());

			// Si no lo hemos obtenido, tomamos el primer nombre que
			// aparezca.
			if (UtilsStringChar.isNullOrEmptyTrim(shiName)) {
				Map<String, String> shiMap = shi.getServiceNames();
				shiName = shiMap.values().iterator().next();
			}

			// Si lo hemos obtenido, asignamos el nombre al resultado.
			if (!UtilsStringChar.isNullOrEmptyTrim(shiName)) {
				validationResult.setTSPServiceHistoryInformationInstanceNameForDetect(shiName);
			}

		}

	}

	/**
	 * Method that assign the TSP Service History Information used to validate the certificate to the validation result.
	 * @param validationResult Object where stores the validation result data.
	 * @param shi TSP Service History Information used for validate the input certificate.
	 */
	private void assignTSPServiceHistoryInformationNameForValidateToResult(TSLValidatorResult validationResult, ServiceHistoryInstance shi) {

		// Verificamos que haya algún nombre asignado al servicio.
		if (shi.isThereSomeServiceName()) {

			// Recuperamos el correspondiente al idioma inglés por defecto.
			String shiName = shi.getServiceNameInLanguage(Locale.UK.getLanguage());

			// Si no lo hemos obtenido, tomamos el primer nombre que
			// aparezca.
			if (UtilsStringChar.isNullOrEmptyTrim(shiName)) {
				Map<String, String> shiMap = shi.getServiceNames();
				shiName = shiMap.values().iterator().next();
			}

			// Si lo hemos obtenido, asignamos el nombre al resultado.
			if (!UtilsStringChar.isNullOrEmptyTrim(shiName)) {
				validationResult.setTspServiceHistoryInformationInstanceNameForValidate(shiName);
			}

		}

	}

	/**
	 * Checks if the certificate is detected by the differents Additional Service Extension of the input TSP Service.
	 * @param validationResult Object where stores the validation result data.
	 * @param shi Trust Service Provider Service History-Information to use for detect the status of the input certificate.
	 * @return <code>null</code> if there is not any AdditionalServiceInformation Extension defined, {@link Boolean#TRUE}
	 * if the certificate has the extensions that matches with the defined AdditionalService Extension values,
	 * otherwise {@link Boolean#FALSE}.
	 */
	protected abstract Boolean checkIfTSPServiceAdditionalServiceInformationExtensionsDetectCert(TSLValidatorResult validationResult, ServiceHistoryInstance shi);

	/**
	 * Checks if the input certificate is detected by the criterias on the Qualifications Extension of the input TSP Service.
	 * @param cert Certificate X509 v3 to detect.
	 * @param validationResult Object where stores the validation result data.
	 * @param shi Trust Service Provider Service History-Information to use for detect the status of the input certificate.
	 * @return <code>true</code> if the certificate is detected by the criterias on the Qualifications Extension of the input
	 * TSP Service, otherwise <code>false</code>.
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the criteria list of the Qualifications
	 * extension over the input certificate and the extension is critical.
	 */
	private boolean checkIfTSPServiceQualificationsExtensionsDetectCert(X509Certificate cert, TSLValidatorResult validationResult, ServiceHistoryInstance shi) throws TSLQualificationEvalProcessException {

		boolean result = false;

		// Creamos una bandera para indicar si ya se ha analizado
		// una extension Qualifications.
		boolean qualificationExtAlreadyChecked = false;

		// Recuperamos la lista de extensiones del servicio, y si no es nula ni
		// vacía, continuamos.
		List<IAnyTypeExtension> extensionsList = shi.getServiceInformationExtensions();
		if (extensionsList != null && !extensionsList.isEmpty()) {

			// Recorremos la lista buscando el elemento Qualifications.
			for (IAnyTypeExtension extension: extensionsList) {

				// Si es del tipo Qualifications...
				if (extension.getImplementationExtension() == IAnyTypeExtension.IMPL_QUALIFICATIONS) {

					// Indicamos que ya se analiza una extension Qualifications.
					qualificationExtAlreadyChecked = true;
					// Obtenemos el objeto Qualifications Extension.
					Qualifications qualificationsExtension = (Qualifications) extension;
					try {
						// Iniciamos la comprobación según los criteria.
						result = checkIfTSPServiceQualificationsExtensionsDetectCert(cert, validationResult, shi, qualificationsExtension);
					} catch (TSLQualificationEvalProcessException e) {
						// Si la extensión es crítica, propagamos la excepción.
						if (qualificationsExtension.isCritical()) {
							throw e;
						} else {
							// Al no ser crítica, simplemente lo notificamos con
							// un warn y continuamos.
							LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL251));
						}
					}

					// Dejamos de recorrer las extensiones.
					break;

				}

			}

		}

		// Si no se encontró ningún Qualifications Extensions...
		if (!qualificationExtAlreadyChecked) {

			// Según la especificación de TSL, puede considerarse detectado si
			// se cumplen una serie de condiciones en el certificado, como que
			// se pueda saber por sus atributos/extensiones si es QC, si está
			// almacenado en un SSCD/QSCD... etc.
			result = checkIfCertificateObeyWithConditionsToBeDetected(validationResult.getTslCertificateExtensionAnalyzer());

			// Si se comprueba, el certificado pasa a considerarse cualificado.
			if (result) {
				validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_QUALIFIED);
			} else {
				// TODO IMPORTANTE: Se ha decidido en coordinación con Dirección
				// de Proyecto suavizar esta condición, de modo que si no se ha
				// encontrado la extensión Qualifications, y aún así, el
				// certificado no cumple las condiciones necesarias, se
				// considere detectado.
				LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL214));
			}

		}

		return result;

	}

	/**
	 * Checks if the input certificate is detected by the criterias on the Qualifications Extension of the input TSP Service.
	 * @param cert Certificate X509 v3 to detect.
	 * @param validationResult Object where is stored the validation result data.
	 * @param shi Trust Service Provider Service History-Information to use for detect the status of the input certificate.
	 * @param qualificationsExtension Qualifications Extension to use.
	 * @return <code>true</code> if the certificate has been detected, otherwise <code>false</code>.
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the criteria list over
	 * the input certificate.
	 */
	private boolean checkIfTSPServiceQualificationsExtensionsDetectCert(X509Certificate cert, TSLValidatorResult validationResult, ServiceHistoryInstance shi, Qualifications qualificationsExtension) throws TSLQualificationEvalProcessException {

		boolean result = false;

		// Recorremos la lista de Qualifications mientras no encontremos uno que
		// encaje con el certificado.
		for (QualificationElement qe: qualificationsExtension.getQualificationsList()) {

			// Primero analizamos si se cumplen los criteria para detectar el
			// certificado.
			// Obtenemos la lista de criterios.
			CriteriaList cl = qe.getCriteriaList();
			// Analizamos el certificado.
			if (cl.checkCertificate(cert)) {

				// Ya seguro que al menos un criteria ha identificado el
				// certificado.
				result = true;
				// Si se cumplen, analizamos los Qualifiers para determinar
				// mapeos
				// del certificado.
				analyzeQualifiersToSetMappings(validationResult, qe);

				// No paramos el bucle porque es posible que cumpla otros
				// criterias que determinen
				// más qualifiers para los mapeos.

			}

		}

		return result;

	}

	/**
	 * Analyze the qualifiers and set the mapping in the validation result object.
	 * @param validationResult Object where is stored the validation result data.
	 * @param qe Qualification element to analyze.
	 */
	private void analyzeQualifiersToSetMappings(TSLValidatorResult validationResult, QualificationElement qe) {

		// Si hay algún qualifier...
		if (qe.isThereSomeQualifierUri()) {

			for (URI qualifierUri: qe.getQualifiersList()) {

				analyzeQualifierToSetMapping(validationResult, qualifierUri.toString());

			}

		}

	}

	/**
	 * Analyze the qualifier URI and set the mapping in the validation result object.
	 * @param validationResult Object where is stored the validation result data.
	 * @param qualifierUriString Qualifier URI String to analyze.
	 */
	protected abstract void analyzeQualifierToSetMapping(TSLValidatorResult validationResult, String qualifierUriString);

	/**
	 * Checks (in function of the TSL Specification) if the input certificate obey the conditions
	 * to be detected without need the Qualifications Extension.
	 * @param tslCertExtAnalyzer TSL Certificate Extension Analyzer with the certificate to check.
	 * @return <code>true</code> if the input certificate obey the conditions
	 * to be detected without need the Qualifications Extension, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfCertificateObeyWithConditionsToBeDetected(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer);

	/**
	 * Checks if the input service status URI defines an OK status.
	 * @param serviceStatus Service Status URI string to check.
	 * @return <code>true</code> if represents an OK status, otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceStatusIsOK(String serviceStatus);

	/**
	 * Sets the status result according to the service status.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param serviceStatus TSP Service Status URI string to analyze.
	 * @param serviceStatusStartingTime TSP Service Status starting time.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where is stored the validation result data.
	 */
	protected abstract void setStatusResultInAccordanceWithTSPServiceCurrentStatus(boolean isCACert, String serviceStatus, Date serviceStatusStartingTime, Date validationDate, TSLValidatorResult validationResult);

	/**
	 * Tries to validate the input certificate with the input Trust Service Provider Service information.
	 * @param cert Certificate X509 v3 to validate.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param isCertQualified Flag that indicates if the certificate to validate is qualified or not.
	 * @param validationResult Object where is stored the validation result data.
	 * @param tspService Trust Service Provider Service to use for validate the status of the input certificate.
	 */
	private void validateCertificateWithTSPService(X509Certificate cert, Date validationDate, boolean isCertQualified, TSLValidatorResult validationResult, TSPService tspService) {

		// Primero, en función de la fecha indicada, comprobamos
		// si tenemos que hacer uso de este servicio o de alguno
		// de sus históricos.
		ServiceHistoryInstance shi = null;
		boolean isHistoricServiceInf = false;
		if (tspService.getServiceInformation().getServiceStatusStartingTime().before(validationDate)) {

			shi = tspService.getServiceInformation();

		} else {

			if (tspService.isThereSomeServiceHistory()) {

				List<ServiceHistoryInstance> shiList = tspService.getAllServiceHistory();
				for (ServiceHistoryInstance shiFromList: shiList) {
					if (shiFromList.getServiceStatusStartingTime().before(validationDate)) {
						if (shiFromList.isServiceValidAndUsable()) {
							shi = shiFromList;
							isHistoricServiceInf = true;
						}
						break;
					}
				}

			}

		}

		// Si hemos encontrado al menos uno, intentamos detectar el certificado
		// con esa información de servicio.
		if (shi != null) {
			validateCertificateWithTSPServiceHistoryInstance(cert, validationDate, isCertQualified, validationResult, tspService, shi, isHistoricServiceInf);
		}

	}

	/**
	 * Tries to validate the input certificate with the input Trust Service Provider Service information.
	 * @param cert Certificate X509 v3 to validate.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param isCertQualified Flag that indicates if the certificate to validate is qualified or not.
	 * @param validationResult Object where is stored the validation result data.
	 * @param tspService Trust Service Provider Service to use for validate the status of the input certificate.
	 * @param shi Trust Service Provider Service History Instance to use for validate the status of the input certificate.
	 * @param isHistoricServiceInf Flag that indicates if the input Service Information is from an Historic Service (<code>true</code>)
	 * or not (<code>false</code>).
	 */
	private void validateCertificateWithTSPServiceHistoryInstance(X509Certificate cert, Date validationDate, boolean isCertQualified, TSLValidatorResult validationResult, TSPService tspService, ServiceHistoryInstance shi, boolean isHistoricServiceInf) {

		// Comprobamos que el estado del servicio es OK,
		// y que su fecha de comienzo del estado es anterior
		// a la fecha de validación, si no, lo ignoramos.
		if (checkIfTSPServiceStatusIsOK(shi.getServiceStatus().toString()) && shi.getServiceStatusStartingTime().before(validationDate)) {

			ITSLValidatorThroughSomeMethod tslValidatorMethod = null;

			// Comprobamos si el servicio es de tipo CRL u OCSP, y en función de
			// esto
			// generamos el validador correspondiente.
			if (checkIfTSPServiceTypeIsCRLCompatible(shi, isCertQualified)) {

				tslValidatorMethod = new TSLValidatorThroughCRL();

			} else if (checkIfTSPServiceTypeIsOCSPCompatible(shi, isCertQualified)) {

				tslValidatorMethod = new TSLValidatorThroughOCSP();

			}

			// Ejecutamos el proceso de validación según el tipo del método si
			// es que se
			// ha detectado un tipo válido.
			if (tslValidatorMethod != null) {

				tslValidatorMethod.validateCertificate(cert, validationDate, tspService, shi, isHistoricServiceInf, validationResult);

			}

		}

	}

	/**
	 * Checks if the service type is a CRL compatible type. It will be compatible when the certificate was qualified,
	 * and this service was for qualified certificates, or the certificate is not qualified, and the service is for not qualified.
	 * @param shi TSP Service History Information to analyze.
	 * @param isCertQualified Flag that indicates if the certificate is qualified (<code>true</code>) or not (<code>false</code>).
	 * @return <code>true</code> if the Service type is CRL compatible with the certificate to validate. otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCRLCompatible(ServiceHistoryInstance shi, boolean isCertQualified);

	/**
	 * Checks if the service type is a OCSP compatible type. It will be compatible when the certificate was qualified,
	 * and this service was for qualified certificates, or the certificate is not qualified, and the service is for not qualified.
	 * @param shi TSP Service History Information to analyze.
	 * @param isCertQualified Flag that indicates if the certificate is qualified (<code>true</code>) or not (<code>false</code>).
	 * @return <code>true</code> if the Service type is OCSP compatible with the certificate to validate. otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsOCSPCompatible(ServiceHistoryInstance shi, boolean isCertQualified);

	/**
	 * Auxiliar method to search if exists an Additional Service Information Extensions in the input TSP Service with
	 * the input URI.
	 * @param tspService TSP Service where to search the URI in the Additional Service Information Extensions.
	 * @param uriToSearch URI to search in the Additional Service Information Extensions.
	 * @return <code>true</code> if the URI is defined, otherwise <code>false</code>.
	 */
	protected final boolean existsAdditionalServiceInformationExtensionURIinService(TSPService tspService, String uriToSearch) {

		boolean result = false;

		// Si hay extensiones definidas...
		if (tspService.getServiceInformation().isThereSomeServiceInformationExtension()) {

			// Recuperamos las extensiones del servicio.
			List<IAnyTypeExtension> serviceExtensionsList = tspService.getServiceInformation().getServiceInformationExtensions();

			// Las vamos recorriendo mientras no hayamos encontrado la URI.
			for (int index = 0; !result && index < serviceExtensionsList.size(); index++) {

				IAnyTypeExtension anyTypeExtension = serviceExtensionsList.get(index);
				if (anyTypeExtension.getImplementationExtension() == IAnyTypeExtension.IMPL_ADDITIONAL_SERVICE_INFORMATION) {

					AdditionalServiceInformation asi = (AdditionalServiceInformation) anyTypeExtension;
					result = uriToSearch.equals(asi.getUri().toString());

				}

			}

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidator#verifiesRevocationValuesForX509withTSL(java.lang.String, java.security.cert.X509Certificate, boolean, boolean, java.security.cert.X509CRL[], org.bouncycastle.cert.ocsp.BasicOCSPResp[], java.util.Date)
	 */
	@Override
	public ITSLValidatorResult verifiesRevocationValuesForX509withTSL(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, X509CRL[ ] crls, BasicOCSPResp[ ] ocsps, Date validationDate) throws TSLArgumentException, TSLValidationException {

		// Comprobamos que el certificado de entrada no sea nulo.
		if (cert == null) {
			throw new TSLArgumentException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL107));
		}

		// Comprobamos que la fecha de entrada no sea nula.
		if (validationDate == null) {
			throw new TSLArgumentException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL144));
		}

		// Comprobamos que haya al menos un valor de revocación.
		if ((crls == null || crls.length == 0) && (ocsps == null || ocsps.length == 0)) {
			throw new TSLArgumentException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL177));
		}

		BasicOCSPResp basicOcspResponse = null;
		// Si hay respuestas OCSP definidas...
		if (ocsps != null && ocsps.length > 0) {

			// Obtenemos la respuesta OCSP acorde al certificado a validar.
			try {
				basicOcspResponse = UtilsOCSP.getOCSPResponse(cert, ocsps, validationDate);
				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL270));
			} catch (Exception e) {
				throw new TSLValidationException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL178), e);
			}

		}

		X509CRL crlSelected = null;
		// Si hay CRLs definidas...
		if (crls != null && crls.length > 0) {

			// Obtenemos aquella que coincida con el emisor (o a algún elemento
			// de la cadena de certificación)y que sea acorde a la fecha
			// de validación o posterior.
			crlSelected = UtilsCRL.getCRLforCertificate(cert, crls, validationDate);
			if(crlSelected!= null){
				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL271));
			}

		}

		// Inicializamos un resultado ficticio de validación que nos ayudará
		// para comprobar
		// si el certificado es detectado por cierto TSP.
		TSLValidatorResult result = new TSLValidatorResult(cert, getTSLObject());

		// Establecemos si se trata de una norma Europea o externa.
		result.setEuropean(checkIfTSLisFromEuropeanMember());

		// Si no se ha detectado ninguna respuesta OCSP o CRL compatible con
		// el certificado y fechas a usar en la validación, se indica en el log.
		if (basicOcspResponse == null && crlSelected == null) {
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL179));
		}

		// Comprobamos si el tipo de la TSL determina si se trata de una
		// lista
		// de listas...
		if (checkIfTSLisListOfLists(tsl.getSchemeInformation().getTslType().toString())) {

			// Si se trata de una lista de listas...
			searchRevocationValuesForCertificateAccordingTSLWithListOfLists(cert, isTsaCertificate, basicOcspResponse, crlSelected, validationDate, result);

		} else {

			// Si no es una lista de listas, continuamos con la búsqueda.
			searchRevocationValuesForCertificateAccordingTSL(auditTransNumber, cert, isCACert, isTsaCertificate, basicOcspResponse, crlSelected, validationDate, result);

		}

		return result;

	}

	/**
	 * Search if the input certificate is detected by the TSL and then, if some of the revocation values
	 * are valid to check the revocation status of the certificate.
	 * @param cert X509v3 certificate to analyze and that must be checked its revocation status.
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL. It can be <code>null</code>.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate. It can be <code>null</code>.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which stores the validation result data.
	 */
	private void searchRevocationValuesForCertificateAccordingTSLWithListOfLists(X509Certificate cert, boolean isTsaCertificate, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSLValidatorResult validationResult) {

		// TODO De momento no se consideran las listas de listas.
		// Si se trata de una lista de listas, la ignoramos y concluímos que no
		// se ha obtenido ningún valor de revocación válido.
		LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL219));

	}

	/**
	 * Search if the input certificate is detected by the TSL and then, if some of the revocation values
	 * are valid to check the revocation status of the certificate.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to analyze and that must be checked its revocation status.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL. It can be <code>null</code>.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate. It can be <code>null</code>.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which stores the validation result data.
	 * @throws TSLValidationException In case of the TSL has an invalid status determination approach.
	 */
	private void searchRevocationValuesForCertificateAccordingTSL(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSLValidatorResult validationResult) throws TSLValidationException {

		// Comprobamos que el "Status Determination Approach" no sea
		// "delinquent" o equivalente.
		if (checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(tsl.getSchemeInformation().getStatusDeterminationApproach().toString())) {

			throw new TSLValidationException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL108));

		} else {

			// TODO: Aún no se hace nada con las extensiones del esquema. No se
			// identifica ninguna.
			// doSomethingWithSchemeExtensions();

			// Recuperamos la lista de TSP y vamos analizando uno a uno.
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL271));
			List<TrustServiceProvider> tspList = tsl.getTrustServiceProviderList();
			// Si la lista no es nula ni vacía...
			if (tspList != null && !tspList.isEmpty()) {

				// La vamos recorriendo mientras no se termine y no se haya
				// detectado el certificado.
				for (int index = 0; index < tspList.size() && !validationResult.hasBeenDetectedTheCertificate(); index++) {

					// Almacenamos en una variable el TSP a tratar.
					TrustServiceProvider tsp = tspList.get(index);
					// Comprobamos si detectamos el certificado con los
					// servicios del TSP.
					try {
						detectCertificateWithTSP(auditTransNumber, cert, isCACert, isTsaCertificate, cert.getNotBefore(), validationResult, tsp);
					} catch (TSLQualificationEvalProcessException e) {

						// Si se produce esta excepción, significa que se
						// produjo un error
						// evaluando el certificado frente a un CriteriaList de
						// un QualificationExtension,
						// siendo esta una extensión crítica. En consecuencia se
						// debe considerar
						// como certificado no detectado, e impedir que se
						// continúen evaluando
						// otros servicios.
						// Mostramos en el log el motivo y la excepción.
						LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL250));
						LOGGER.error(e.getMessage(), e.getException());
						// Limpiamos toda la información acumulada hasta el
						// momento.
						validationResult.resetAllData();
						// Cancelamos que el proceso continúe.
						break;

					}

					// Si el certificado ha sido detectado...
					if (validationResult.hasBeenDetectedTheCertificate()) {

						// Pero el estado es desconocido aún...
						if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {

							// Solo si tenemos una respuesta OCSP y/o una CRL,
							// procedemos a analizar
							// los valores de revocación en la TSL.
							if (basicOcspResponse != null || crl != null) {
								LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL280));
								// Comprobamos entre los distintos servicios CRL
								// y
								// OCSP del TSP, si alguno detecta
								// alguno de los valores de revocación.
								searchCompatibleRevocationValuesInTSP(auditTransNumber, cert, basicOcspResponse, crl, validationDate, validationResult, tsp);

								// En función de si hemos validado el
								// certificado, escribimos en auditoría.
								if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
									CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, false, null, null, null, null, null, null, null);
								} else {
									validationResult.setResultFromServiceStatus(Boolean.FALSE);
									validationResult.setResultFromDPorAIA(Boolean.FALSE);									
									CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), validationResult.isResultFromServiceStatus(), validationResult.isResultFromDPorAIA(), validationResult.getTslCountryRegionCode(), getTSPName(tsp), validationResult.getTSPServiceNameForValidate(), validationResult.getTSPServiceHistoryInformationInstanceNameForValidate());
								}

							}

						}
						// En caso contrario es que viene determinado por el
						// estado del servicio...
						else {
							validationResult.setResultFromServiceStatus(Boolean.TRUE);
							validationResult.setResultFromDPorAIA(Boolean.FALSE);
							CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), validationResult.isResultFromServiceStatus(), validationResult.isResultFromDPorAIA(), validationResult.getTslCountryRegionCode(), getTSPName(tsp), validationResult.getTSPServiceNameForValidate(), validationResult.getTSPServiceHistoryInformationInstanceNameForValidate());

						}

					}

					// Si el certificado se ha detectado, almacenamos el TSP
					// usado
					// y su nombre.
					if (validationResult.hasBeenDetectedTheCertificate()) {
						assignTSPandNameToResult(validationResult, tsp);
					}

				}

				// Si no ha sido detectado el certificado, lo indicamos en
				// auditoría.
				if (!validationResult.hasBeenDetectedTheCertificate()) {
					CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, false, null, null, null, null);
				}

			}

		}

		// Mostramos en el log si el certificado ha sido detectado/validado y
		// por cual TSP y servicio.
		showInLogResultOfValidation(validationResult, true);

	}

	/**
	 * Tries to detect the input certificate with the TSP information.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to check if it is detected by the input TSP.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param tsp Trust Service Provider that must check if detect the input certificate.
	 * @throws TSLQualificationEvalProcessException In case of some error evaluating the Criteria List of a Qualification
	 * Extension over the input certificate, and being critical that Qualification Extension.
	 */
	private void detectCertificateWithTSP(String auditTransNumber, X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp) throws TSLQualificationEvalProcessException {

		// TODO: Aún no se hace nada con las extensiones del TSP.
		// No se identifica ninguna.
		// doSomethingWithTSPExtensions();

		// Obtenemos la lista de servicios.
		List<TSPService> tspServiceList = tsp.getAllTSPServices();
		
		
		// Si la lista no es nula ni vacía...
		if (tspServiceList != null && !tspServiceList.isEmpty()) {
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL272));
			// La vamos recorriendo mientras no se termine y no se haya
			// detectado el certificado.
			for (int index = 0; index < tspServiceList.size() && !validationResult.hasBeenDetectedTheCertificate(); index++) {

				// Almacenamos en una variable el servicio a analizar en esta
				// vuelta.
				TSPService tspService = tspServiceList.get(index);

				// Tratamos de detectar el certificado respecto al servicio...
				detectCertificateWithTSPService(cert, isCACert, isTsaCertificate, validationDate, validationResult, tspService);

				// Si el certificado se ha detectado...
				if (validationResult.hasBeenDetectedTheCertificate()) {

					// Almacenamos el nombre del servicio usado.
					assignTSPServiceNameForDetectToResult(validationResult, tspService);
					// Guardamos el servicio usado.
					validationResult.setTSPServiceForDetect(tspService);

					// Auditoría: Certificado detectado.
					CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, true, validationResult.getTslCountryRegionCode(), getTSPName(tsp), validationResult.getTSPServiceNameForDetect(), validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());

					// Si el estado no es desconocido, significa que ya se ha
					// determinado la validez del certificado,
					// por lo que asignamos el mismo nombre de servicio al
					// resultado de la validación (y el servicio).
					if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
						LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL215));
						validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
						validationResult.setTSPServiceForValidate(tspService);
						validationResult.setTspServiceHistoryInformationInstanceNameForValidate(validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
						validationResult.setTspServiceHistoryInformationInstanceForValidate(validationResult.getTSPServiceHistoryInformationInstanceForDetect());
						// Indicamos que se considera validado por el servicio
						// en auditoría.
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(), true, false, null, null, null, null);
					}

				}

			}

		}

	}

	/**
	 * Search in the input TSP some service that verifies some of the input revocation values.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to check if it is detected by the input TSP.
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL. It can be <code>null</code>.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate. It can be <code>null</code>.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param tsp Trust Service Provider that must check if detect the input certificate.
	 */
	private void searchCompatibleRevocationValuesInTSP(String auditTransNumber, X509Certificate cert, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp) {

		// Primero comprobamos si el mismo emisor que identifica al certificado
		// es el firmante de la respuesta OCSP o de alguna de las CRL.
		// Si hemos obtenido el servicio con el que se detecta al certificado a
		// validar...
		if (validationResult.getTSPServiceForDetect() != null) {

			ServiceHistoryInstance shiForDetect = validationResult.getTSPServiceHistoryInformationInstanceForDetect() == null ? validationResult.getTSPServiceForDetect().getServiceInformation() : validationResult.getTSPServiceHistoryInformationInstanceForDetect();

			ITSLValidatorThroughSomeMethod tslValidatorMethod = null;
			// Primero lo intentamos mediante respuestas OCSP (si las hay).
			if (basicOcspResponse != null) {
				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL281));
				// Creamos el validador.
				tslValidatorMethod = new TSLValidatorThroughOCSP();
				// Ejecutamos la comprobación.
				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate, shiForDetect, validationResult);

			}

			// Si no hemos obtenido resultado, lo intentamos con las CRL,
			// si es que las hay.
			if (validationResult.getRevocationValueBasicOCSPResponse() == null && crl != null) {
				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL282));
				// Creamos el validador.
				tslValidatorMethod = new TSLValidatorThroughCRL();
				// Ejecutamos la comprobación.
				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate, shiForDetect, validationResult);

			}

		}

		// En caso de haber encontrado el valor de revocación, podemos decir
		// que se ha usado el mismo servicio que para identificar el
		// certificado.
		if (validationResult.getRevocationValueBasicOCSPResponse() != null || validationResult.getRevocationValueCRL() != null) {

			validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
			validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
			validationResult.setTspServiceHistoryInformationInstanceNameForValidate(validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
			validationResult.setTspServiceHistoryInformationInstanceForValidate(validationResult.getTSPServiceHistoryInformationInstanceForDetect());
			LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL220));

		}
		// Si no, hay que tratar de buscar un servicio dentro del mismo TSP que
		// pueda
		// validar los elementos de revocación.
		else {

			// Almacenamos en una variable si el certificado es cualificado o
			// no.
			boolean isCertQualified = validationResult.getMappingType() == ITSLValidatorResult.MAPPING_TYPE_QUALIFIED;

			// Obtenemos la lista de servicios.
			List<TSPService> tspServiceList = tsp.getAllTSPServices();

			// Recorremos la lista buscando servicios que permitan comprobar si
			// alguno
			// de los valores de revocación es compatible.
			// Seguimos intentándolo mientras los valores respuesta OCSP y CRL
			// sean
			// nulos.
			for (int index = 0; index < tspServiceList.size() && validationResult.getRevocationValueBasicOCSPResponse() == null && validationResult.getRevocationValueCRL() == null; index++) {

				// Almacenamos en una variable el servicio a analizar en
				// esta vuelta.
				TSPService tspService = tspServiceList.get(index);

				// Validamos el certificado con la información que haya en
				// el servicio TSP.
				searchCompatibleRevocationValuesInTSPService(cert, validationDate, isCertQualified, validationResult, tspService, basicOcspResponse, crl);

				// Si hemos encontrado un servicio que detecta alguno de los
				// valores de revocación,
				// asignamos el nombre del servicio que detecta dicho valor
				// (y el servicio).
				if (validationResult.getRevocationValueBasicOCSPResponse() != null || validationResult.getRevocationValueCRL() != null) {
					assignTSPServiceNameForValidateToResult(validationResult, tspService);
					validationResult.setTSPServiceForValidate(tspService);
					if (validationResult.getTSPServiceHistoryInformationInstanceForValidate() != null) {
						assignTSPServiceHistoryInformationNameForValidateToResult(validationResult, validationResult.getTSPServiceHistoryInformationInstanceForValidate());
					}
					LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL220));
				}

			}

		}

		// Incluimos en auditoría las evidencias usadas.
		if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
			CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(auditTransNumber, IEventsCollectorConstants.FIELD_VALUE_FROM_REQUEST, validationResult.getRevocationValueBasicOCSPResponse());
		} else if (validationResult.getRevocationValueCRL() != null) {
			CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(auditTransNumber, IEventsCollectorConstants.FIELD_VALUE_FROM_REQUEST, validationResult.getRevocationValueCRL());
		}

	}

	/**
	 * If the input TSP Service is OCSP or CRL compatible with the certificate to validate, then tries to verify with
	 * its information some of the input revocation values, and then, use it to check the revocation status of the certificate.
	 * @param cert X509v3 certificate to validate.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param isCertQualified Flag that indicates if the certificate to validate has been detected.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param tspService TSP Service to analyze.
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL. It can be <code>null</code>.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate. It can be <code>null</code>.
	 */
	private void searchCompatibleRevocationValuesInTSPService(X509Certificate cert, Date validationDate, boolean isCertQualified, TSLValidatorResult validationResult, TSPService tspService, BasicOCSPResp basicOcspResponse, X509CRL crl) {

		// Primero, en función de la fecha indicada, comprobamos
		// si tenemos que hacer uso de este servicio o de alguno
		// de sus históricos.
		ServiceHistoryInstance shi = null;
		boolean isHistoricServiceInf = false;
		if (tspService.getServiceInformation().getServiceStatusStartingTime().before(validationDate)) {

			if (tspService.getServiceInformation().isServiceValidAndUsable()) {
				shi = tspService.getServiceInformation();
			}

		} else {

			if (tspService.isThereSomeServiceHistory()) {

				List<ServiceHistoryInstance> shiList = tspService.getAllServiceHistory();
				for (ServiceHistoryInstance shiFromList: shiList) {
					if (shiFromList.getServiceStatusStartingTime().before(validationDate)) {
						if (shiFromList.isServiceValidAndUsable()) {
							shi = shiFromList;
							isHistoricServiceInf = true;
						}
						break;
					}
				}

			}

		}

		// Si hemos encontrado al menos uno, lo analizamos...
		if (shi != null) {
			searchCompatibleRevocationValuesInTSPService(cert, validationDate, isCertQualified, validationResult, shi, basicOcspResponse, crl);
			// Si hemos detectado el servicio histórico que valida
			// los elementos de revocación, lo almacenamos.
			if (isHistoricServiceInf && validationResult.getRevocationValueBasicOCSPResponse() != null || validationResult.getRevocationValueCRL() != null) {
				validationResult.setTspServiceHistoryInformationInstanceForValidate(shi);
			}

		}

	}

	/**
	 * If the input TSP Service is OCSP or CRL compatible with the certificate to validate, then tries to verify with
	 * its information some of the input revocation values, and then, use it to check the revocation status of the certificate.
	 * @param cert X509v3 certificate to validate.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param isCertQualified Flag that indicates if the certificate to validate has been detected.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param shi TSP Service History Information to analyze.
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL. It can be <code>null</code>.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate. It can be <code>null</code>.
	 */
	private void searchCompatibleRevocationValuesInTSPService(X509Certificate cert, Date validationDate, boolean isCertQualified, TSLValidatorResult validationResult, ServiceHistoryInstance shi, BasicOCSPResp basicOcspResponse, X509CRL crl) {

		// Comprobamos que el estado del servicio es OK,
		// y que su fecha de comienzo del estado es anterior
		// a la fecha de validación, si no, lo ignoramos.
		if (checkIfTSPServiceStatusIsOK(shi.getServiceStatus().toString()) && shi.getServiceStatusStartingTime().before(validationDate)) {

			ITSLValidatorThroughSomeMethod tslValidatorMethod = null;

			// Comprobamos si el servicio es de tipo CRL u OCSP, y en función de
			// esto generamos el validador correspondiente.
			if (crl != null && checkIfTSPServiceTypeIsCRLCompatible(shi, isCertQualified)) {

				tslValidatorMethod = new TSLValidatorThroughCRL();

			} else if (basicOcspResponse != null && checkIfTSPServiceTypeIsOCSPCompatible(shi, isCertQualified)) {

				tslValidatorMethod = new TSLValidatorThroughOCSP();

			}

			// Ejecutamos el proceso de validación según el tipo del método si
			// es que se ha detectado un tipo válido.
			if (tslValidatorMethod != null) {

				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate, shi, validationResult);

			}

		}

	}

	/**
	 * Validates the certificate revocation status using the distribution points set on it. First try
	 * the OCSP distribution points, and then the CRL distribution points.
	 * @param cert X509v3 Certificate to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 */
	private void validateCertificateUsingDistributionPoints(X509Certificate cert, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp) {

		// Creamos un validador mediante OCSP para analizar los distribution
		// points de este tipo (AIA).
		ITSLValidatorThroughSomeMethod tslValidatorMethod = new TSLValidatorThroughOCSP();
		boolean certValidated = tslValidatorMethod.validateCertificateUsingDistributionPoints(cert, isCACert, isTsaCertificate, validationDate, validationResult, tsp, this);

		// Si aún no se ha podido verificar el estado de revocación del
		// certificado, lo intentamos con los de tipo CRL.
		if (validationResult.hasBeenDetectedTheCertificateWithUnknownState() || !certValidated && isCACert && !UtilsCertificate.isSelfSigned(cert)) {

			tslValidatorMethod = new TSLValidatorThroughCRL();
			tslValidatorMethod.validateCertificateUsingDistributionPoints(cert, isCACert, isTsaCertificate, validationDate, validationResult, tsp, this);

		}

	}
	
	/**
	 * Method that checks if the TSP service have "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/withdrawn" status
	 * in date of issue of the certificate.
	 * @param cert certificate to check date of issue.
	 * @param tspService service to be checked.
	 * @return
	 */
	private boolean isWithdrawnBeforeDateOfIssue(X509Certificate cert, TSPService tspService) {
		return !cert.getNotBefore().before(tspService.getServiceInformation().getServiceStatusStartingTime()) && tspService.getServiceInformation().getServiceStatus().toString().equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_WITHDRAWN);
		
	}


}
