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
 * @version 2.0, 19/09/2023.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.net.URI;
import java.security.KeyStoreException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.springframework.beans.BeansException;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.audit.access.EventsCollectorConstants;
import es.gob.valet.audit.utils.CommonsCertificatesAuditTraces;
import es.gob.valet.audit.utils.CommonsTslAuditTraces;
import es.gob.valet.certificates.CertificateCacheManager;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCRL;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsOCSP;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.persistence.configuration.model.utils.AlarmIdConstants;
import es.gob.valet.rest.services.TslMappingConstants;
import es.gob.valet.service.impl.KeystoreServiceImpl;
import es.gob.valet.spring.config.ApplicationContextProvider;
import es.gob.valet.tsl.certValidation.CertificateExtension;
import es.gob.valet.tsl.certValidation.QCCertificateConstants;
import es.gob.valet.tsl.certValidation.QCResult;
import es.gob.valet.tsl.certValidation.ResultQSCDDetermination;
import es.gob.valet.tsl.certValidation.ResultQualifiedCertificate;
import es.gob.valet.tsl.certValidation.ResultServiceInformation;
import es.gob.valet.tsl.certValidation.SIResult;
import es.gob.valet.tsl.certValidation.TSLStatusConstants;
import es.gob.valet.tsl.certValidation.TspServiceQualifier;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidator;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.exceptions.TSLQualificationEvalProcessException;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.DigitalID;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.tsl.parsing.impl.common.ServiceInformation;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;
import es.gob.valet.tsl.parsing.impl.common.extensions.AdditionalServiceInformation;
import es.gob.valet.tsl.parsing.impl.common.extensions.CriteriaList;
import es.gob.valet.tsl.parsing.impl.common.extensions.QualificationElement;
import es.gob.valet.tsl.parsing.impl.common.extensions.Qualifications;
import es.gob.valet.utils.TSLBuilderConstants;
import es.gob.valet.utils.TSLCommonURIs;
import es.gob.valet.utils.UtilsHTTP;
import es.gob.valet.utils.ValidatorResultConstants;

/**
 * <p>
 * Abstract class that represents a TSL validator with the principal functions
 * regardless it implementation.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 2.0, 19/09/2023.
 */
public abstract class ATSLValidator implements ITSLValidator {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ATSLValidator.class);

	/**
	 * Constant attribute that represents a token for a TSP Service Name when
	 * the validation has been executed using the Distribution Point of the
	 * certificate to validate.
	 */
	public static final String TSP_SERVICE_NAME_FOR_DIST_POINT = "TSPService-Certificate-DistributionPoint";

	/**
	 * Attribute that represents the TSL object to use for validate
	 * certificates.
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
	 * 
	 * @param tslObject
	 *            TSL to use for validate certificates.
	 */
	protected ATSLValidator(ITSLObject tslObject) {
		this();
		tsl = tslObject;
	}

	/**
	 * Gets the TSL object used to validate certificates.
	 * 
	 * @return the TSL object used to validate certificates.
	 */
	protected final ITSLObject getTSLObject() {
		return tsl;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidator#validateCertificateWithTSL(java.lang.String,
	 *      java.security.cert.X509Certificate, boolean, boolean,
	 *      java.util.Date, boolean)
	 */
	@Override
	public ITSLValidatorResult validateCertificateWithTSL(String auditTransNumber, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation)
			throws TSLArgumentException, TSLValidationException {

		// Comprobamos que el certificado de entrada no sea nulo.
		if (cert == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL107));
		}

		// Comprobamos que la fecha de entrada no sea nula.
		if (validationDate == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL144));
		}

		// Inicializamos el resultado a devolver.
		TSLValidatorResult result = new TSLValidatorResult(cert, getTSLObject());

		// Establecemos si se trata de una norma Europea o externa.
		result.setEuropean(checkIfTSLisFromEuropeanMember());

		// Comprobamos si el tipo de la TSL determina si se trata de una lista
		// de listas...
		if (checkIfTSLisListOfLists(tsl.getSchemeInformation().getTslType().toString())) {

			// Si se trata de una lista de listas...
			validateCertificateWithListOfLists(cert, isCACert, isTsaCertificate, validationDate, checkStatusRevocation,
					result);

		} else {

			// Si no es una lista de listas, continuamos con la validación.
			validateCertificate(auditTransNumber, cert, isCACert, isTsaCertificate, validationDate,
					checkStatusRevocation, result);

		}

		return result;

	}

	/**
	 * Checks if the TSL is from an European Member.
	 * 
	 * @return <code>true</code> if the TSL is from European Member, otherwise
	 *         <code>false</code>.
	 */
	protected abstract boolean checkIfTSLisFromEuropeanMember();

	/**
	 * Checks if the TSL is a List of Lists.
	 * 
	 * @param tslType
	 *            String that represents the TSL type to analyze.
	 * @return <code>true</code> if the TSL is a list of lists, otherwise
	 *         <code>false</code>.
	 */
	protected abstract boolean checkIfTSLisListOfLists(String tslType);

	/**
	 * Validates the input certificate knowing this TSL is a List of Lists.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation
	 *            Flag that indicates if only try to detect the input
	 *            certificate (<code>false</code>) or also checks the revocation
	 *            status of this (<code>true</code>).
	 * @param validationResult
	 *            Object where stores the validation result data.
	 */
	private void validateCertificateWithListOfLists(X509Certificate cert, boolean isCACert, boolean isTsaCertificate,
			Date validationDate, boolean checkStatusRevocation, TSLValidatorResult validationResult) {

		// TODO De momento no se consideran las listas de listas.
		// Si se trata de una lista de listas, la ignoramos y concluímos que no
		// se puede validar el certificado
		// indicando como no detectado (valor por defecto en la respuesta).
		LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL202));

	}

	/**
	 * Method where the qualification of a certificate is obtained according to
	 * the ETSI TS 119 615 v.1.1.1
	 * 
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param tspList
	 *            List of TrustServiceProvider.
	 * @param resultQC
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 * @throws TSLQualificationEvalProcessException
	 * @throws TSLValidationException
	 */
	private void validateCertificateETSI(X509Certificate cert, boolean isCACert, boolean isTsaCertificate,
			Date validationDate, boolean checkStatusRevocation, List<TrustServiceProvider> tspList,
			ResultQualifiedCertificate resultQC, ResultQSCDDetermination resultQSCD)
			throws TSLQualificationEvalProcessException, TSLValidationException {

		procEUQualifiedCertificateDetermination(resultQC, cert, isCACert, isTsaCertificate, validationDate, tspList,
				false);

		if (!resultQC.isEndProcedure()) {

			if (resultQC.getQcStatus().equals(TSLStatusConstants.PROCESS_PASSED)) {
				// PRO-4.4.4-34 se vuelve a llamar al método pasándole la
				// fecha de emisión del certificado como fecha de
				// validación.
				LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL397,
						new Object[] { cert.getNotBefore().toString() }));
				ResultQualifiedCertificate resultQCDateIssue = new ResultQualifiedCertificate(cert);
				procEUQualifiedCertificateDetermination(resultQCDateIssue, cert, isCACert, isTsaCertificate,
						cert.getNotBefore(), tspList, true);

				// PRO-4.4.4-35
				if (resultQCDateIssue.getQcStatus().equals(TSLStatusConstants.PROCESS_FAILED)) {
					// PRO-4.4.4-35 a)
					resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
					// PRO-4.4.4-35 b)
					resultQC.setQcSubStatus(resultQCDateIssue.getQcSubStatus());
					resultQC.setInfoQcResult(resultQCDateIssue.getInfoQcResult());
					resultQC.setQcResults(resultQCDateIssue.getQcResults());
					resultQC.setEndProcedure(Boolean.TRUE);
				} else {
					if (!resultQCDateIssue.isEndProcedure()) {
						// PRO-4.4.4-36 a)
						if (!checkIdenticalQualifiers(resultQC.getQcResults(), resultQCDateIssue.getQcResults())) {
							// PRO-4.4.4-36 a) 1)
							resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
							// PRO-4.4.4-36 a) 2)
							resultQC.getQcSubStatus().add(Language.getResCoreTsl(CoreTslMessages.ERROR_QC_SUBSTATUS2));
							resultQC.setInfoQcResult(resultQCDateIssue.getInfoQcResult());
							resultQC.getQcResults().clear();
							resultQC.setEndProcedure(Boolean.TRUE);
						}
						// PRO-4.4.4-36 b)
						if (checkQCSubStatusWarning(resultQCDateIssue.getQcSubStatus())) {
							// PRO-4.4.4-36 b) 1)
							resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED_WITH_WARNING);
							// PRO-4.4.4-36 b) 2)
							resultQC.getQcSubStatus().add(resultQCDateIssue.getQcStatus());
							resultQC.getQcSubStatus().addAll(resultQCDateIssue.getQcSubStatus());
						}

					} else {
						// si llega hasta aqui, es porque no se ha obtenido
						// ningún TSPService con la fecha de emisión, segun
						// el estandar es NO CUALIFICADO
						resultQC.setEndProcedure(Boolean.TRUE);
						resultQC.setQcStatus(resultQCDateIssue.getQcStatus());
						resultQC.setQcSubStatus(resultQCDateIssue.getQcSubStatus());
						resultQC.setQcResults(resultQCDateIssue.getQcResults());
						resultQC.setEndProcedure(Boolean.TRUE);

					}
				}
			}
		}
		// se obtiene Qscd PRO5
		obtainQscdDetermination(validationDate, cert, resultQC, resultQSCD);

	}

	/**
	 * Validates the input certificate knowing this TSL is not list of lists.
	 * 
	 * @param auditTransNumber
	 *            Audit transaction number.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation
	 *            Flag that indicates if only try to detect the input
	 *            certificate (<code>false</code>) or also checks the revocation
	 *            status of this (<code>true</code>).
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @throws TSLValidationException
	 *             If there is some error or inconsistency in the certificate
	 *             validation.
	 */
	private void validateCertificate(String auditTransNumber, X509Certificate cert, boolean isCACert,
			boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation,
			TSLValidatorResult validationResult) throws TSLValidationException {

		// Comprobamos que el "Status Determination Approach" no sea
		// "delinquent" o equivalente.
		if (checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(
				tsl.getSchemeInformation().getStatusDeterminationApproach().toString())) {

			throw new TSLValidationException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL108));

		} else {
			// Recuperamos la lista de TSP y se va analizando de uno en uno.
			List<TrustServiceProvider> tspList = tsl.getTrustServiceProviderList();
			ResultQualifiedCertificate resultQC = new ResultQualifiedCertificate(cert);
			ResultQSCDDetermination resultQSCD = new ResultQSCDDetermination();
			try {
				validateCertificateETSI(cert, isCACert, isTsaCertificate, validationDate, checkStatusRevocation,
						tspList, resultQC, resultQSCD);
				// el certificado ha sido detectado en un tspService QC o en
				// QTST
				if (resultQC.getInfoQcResult().isCertificateDetected()
						|| resultQC.getInfoQcResult().isTspServiceTSADetected()) {
					// detectado pero desconocido
					validationResult.setResult(ValidatorResultConstants.RESULT_DETECTED_STATE_UNKNOWN);

					ServiceHistoryInstance shiSelected = resultQC.getInfoQcResult().getShiSelected();
					if (shiSelected != null) {
						// Se establece el resultado según el estado.
						setStatusResultInAccordanceWithTSPServiceCurrentStatus(isCACert,
								shiSelected.getServiceStatus().toString(), shiSelected.getServiceStatusStartingTime(),
								validationDate, validationResult);
						// Guardamos la información del servicio histórico
						// usado.
						if (resultQC.getInfoQcResult().isHistoricServiceInf()) {
							assignTSPServiceHistoryInformationNameForDetectToResult(validationResult, shiSelected);
							validationResult.setTSPServiceHistoryInformationInstanceForDetect(shiSelected);
						}
					}

					TSPService tspServiceSelected = resultQC.getInfoQcResult().getTspServiceDetected();
					// Almacenamos el nombre del TSP Service usado.
					assignTSPServiceNameForDetectToResult(validationResult, tspServiceSelected);
					// Y el servicio.
					validationResult.setTSPServiceForDetect(tspServiceSelected);

					TrustServiceProvider tspSelected = resultQC.getInfoQcResult().getTspDetected();
					// Se almacena el nombre del TSP seleccionado.
					assignTSPandNameToResult(validationResult, tspSelected);

					// se almacena la información del certificado emisor en el
					// resultado.
					assingIssuerCertificateToResult(validationResult, resultQC, cert);
					// Auditoría: Certificado detectado.
					CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, true,
							validationResult.getTslCountryRegionCode(), getTSPName(tspSelected),
							validationResult.getTSPServiceNameForDetect(),
							validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
					// Si el estado no es desconocido, significa que ya se ha
					// determinado la validez del certificado,
					// por lo que asignamos el mismo nombre de servicio al
					// resultado de la validación (y el servicio).
					if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
						LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL215));
						validationResult.setResultFromServiceStatus(Boolean.TRUE);
						validationResult.setResultFromDPorAIA(Boolean.FALSE);
						validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
						validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
						validationResult.setTspServiceHistoryInformationInstanceNameForValidate(
								validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
						validationResult.setTspServiceHistoryInformationInstanceForValidate(
								validationResult.getTSPServiceHistoryInformationInstanceForDetect());
						// Indicamos que se considera validado por el servicio
						// en auditoría.
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(),
								true, false, null, null, null, null);
					}

					// se actualiza los mapeos obtenidos.
					updateMappingValidationResult(validationResult, resultQC, resultQSCD);

				}

				// Si hay que comprobar el estado de revocación y aún no se ha
				// determinado o se trata de un certificado detectado de CA no
				// root...
				if (checkStatusRevocation && (validationResult.hasBeenDetectedTheCertificateWithUnknownState()
						|| validationResult.hasBeenDetectedTheCertificate() && isCACert
								&& !UtilsCertificate.isSelfSigned(cert))) {

					// Tratamos de validar el estado de revocación mediante los
					// puntos de distribución
					// establecidos en el propio certificado.
					LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL216,
							new Object[] { validationDate.toString() }));
					validateCertificateUsingDistributionPoints(cert, isCACert, isTsaCertificate, validationDate,
							validationResult, resultQC.getInfoQcResult().getTspDetected());

					// Si el estado no es desconocido, significa que ya se ha
					// determinado la validez del certificado haciendo uso del
					// DistributionPoint, por lo que lo indicamos en el
					// resultado.
					if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()
							&& validationResult.isResultFromDPorAIA()) {
						validationResult.setResultFromServiceStatus(Boolean.FALSE);
						validationResult.setTSPServiceNameForValidate(TSP_SERVICE_NAME_FOR_DIST_POINT);
						validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
						if (validationResult.getTSPServiceHistoryInformationInstanceForDetect() != null) {
							validationResult.setTspServiceHistoryInformationInstanceNameForValidate(
									TSP_SERVICE_NAME_FOR_DIST_POINT);
							validationResult.setTspServiceHistoryInformationInstanceForValidate(
									validationResult.getTSPServiceHistoryInformationInstanceForDetect());
						}
						// Indicamos en auditoría la información del elemento de
						// revocación usado según haya sido OCSP o CRL.
						if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
							CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(auditTransNumber,
									validationResult.getRevocationValueURL(),
									validationResult.getRevocationValueBasicOCSPResponse());
						} else {
							CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(auditTransNumber,
									validationResult.getRevocationValueURL(), validationResult.getRevocationValueCRL());
						}
						// Indicamos en auditoría que hemos obtenido el
						// resultado
						// mediante DP/AIA.
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(),
								false, true, null, null, null, null);
					}
					// Si no es así, hay que tratar de hacerlo mediante los
					// servicios de la TSL (siempre y cuando no sea un
					// certificado
					// de sello de tiempo ni de CA)
					else if (!isTsaCertificate && !isCACert) {

						// LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL217));
						//
						// // Almacenamos en una variable si el certificado es
						// // cualificado o no.
						boolean isCertQualified = validationResult
								.getMappingType() == ValidatorResultConstants.MAPPING_TYPE_QUALIFIED;

						for (int indexTsp = 0; indexTsp < tspList.size()
								&& !validationResult.hasBeenDetectedTheCertificate(); indexTsp++) {
							// Obtenemos la lista de servicios.
							// Almacenamos en una variable el TSP a tratar.
							TrustServiceProvider tsp = tspList.get(indexTsp);

							// Obtenemos la lista de servicios.
							List<TSPService> tspServiceList = tsp.getAllTSPServices();
							// Si la lista no es nula ni vacía...
							if (tspServiceList != null && !tspServiceList.isEmpty()) {

								// Recorremos la lista buscando servicios que
								// permitan
								// validar
								// el estado del certificado.
								// Seguimos intentándolo mientras el estado siga
								// siendo
								// detectado pero Unknown.
								for (int index = 0; index < tspServiceList.size()
										&& validationResult.hasBeenDetectedTheCertificateWithUnknownState(); index++) {
									// Almacenamos en una variable el servicio a
									// analizar en
									// esta vuelta.
									TSPService tspService = tspServiceList.get(index);

									// Validamos el certificado con la
									// información que haya
									// en
									// el servicio TSP.
									validateCertificateWithTSPService(cert, validationDate, isCertQualified,
											validationResult, tspService);

									// Si el estado no es desconocido, significa
									// que ya se
									// ha determinado la validez del
									// certificado,
									// así que indicamos en el resultado el
									// nombre del
									// servicio usado para ello (y el servicio).
									if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
										validationResult.setResultFromServiceStatus(Boolean.FALSE);
										assignTSPServiceNameForValidateToResult(validationResult, tspService);
										validationResult.setTSPServiceForValidate(tspService);
										if (validationResult
												.getTSPServiceHistoryInformationInstanceForValidate() != null) {
											assignTSPServiceHistoryInformationNameForValidateToResult(validationResult,
													validationResult
															.getTSPServiceHistoryInformationInstanceForValidate());
										}
										// Indicamos en auditoría la información
										// del
										// elemento de revocación usado según
										// haya sido OCSP
										// o CRL.
										if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
											CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(
													auditTransNumber, validationResult.getRevocationValueURL(),
													validationResult.getRevocationValueBasicOCSPResponse());
										} else {
											CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(
													auditTransNumber, validationResult.getRevocationValueURL(),
													validationResult.getRevocationValueCRL());
										}
										// Indicamos en auditoría que hemos
										// obtenido el
										// resultado
										// mediante un servicio.
										CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true,
												validationResult.getResult(), false, false,
												validationResult.getTslCountryRegionCode(), getTSPName(tsp),
												validationResult.getTSPServiceNameForValidate(), validationResult
														.getTSPServiceHistoryInformationInstanceNameForValidate());
									}
								}
							}

						}

					}

					// Si el estado de revocación es desconocido...
					if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, false, null, null, null, null, null,
								null, null);
					}

				}

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
				LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL250));
				LOGGER.error(e.getMessage(), e.getException());
				// Limpiamos toda la información acumulada hasta el
				// momento.
				validationResult.resetAllData();

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
	 * Method that checks whether the QC-Sub-Status returned by the process run
	 * in PRO-4.4.4-34 contains one or more "warning" indications.
	 * 
	 * @param qcSubStatus
	 *            List of SubStatus.
	 * @return true, if the list of Status contains one or more "warning"
	 *         indications.
	 */
	private boolean checkQCSubStatusWarning(List<String> qcSubStatus) {

		// PRO-4.4.4-36 b)
		boolean hasWarnings = Boolean.FALSE;
		for (String qcss : qcSubStatus) {
			if (qcss.contains(TSLStatusConstants.QC_SUBSTATUS_WARNING)) {
				hasWarnings = Boolean.TRUE;
				break;
			}
		}
		return hasWarnings;
	}

	/**
	 * Method that compares the values of QC-Results obtained after the first
	 * run of process 4.4 (with Date-time from the input) and after the second
	 * run of process 4.4 (with the NotBeforeDate).
	 * 
	 * @param qcResults
	 *            List of QCResult obtained in the detection of the certificate
	 *            according to the validation date.
	 * @param qcResults2
	 *            List of QCResult obtained in the detection of the certificate
	 *            according to its issue date.
	 * @return True, if they are identicals.
	 */
	private boolean checkIdenticalQualifiers(List<QCResult> qcResults, List<QCResult> qcResults2) {
		// PRO-4.4.4-36 a)
		boolean identical = Boolean.TRUE;
		if ((qcResults.size() != qcResults2.size()) || !qcResults.containsAll(qcResults2)
				|| !qcResults2.containsAll(qcResults2)) {
			identical = Boolean.FALSE;
		}

		return identical;

	}

	/**
	 * Method that determine whether an EU qualified certificate is confirmed by
	 * the applicable EUMS trusted list to have had its private key residing in
	 * a QSCD at a specific date and time.
	 * 
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void obtainQscdDetermination(Date validationDate, X509Certificate cert, ResultQualifiedCertificate resultQC,
			ResultQSCDDetermination resultQSCD) {

		boolean endProc = Boolean.FALSE;
		// PRO-4.5.4-02
		if (resultQC.getQcStatus().equals(TSLStatusConstants.PROCESS_FAILED)) {
			resultQSCD.setQscdResult(TSLStatusConstants.PROCESS_FAILED);
			resultQSCD.getQscdSubStatus().add(resultQC.getQcStatus());
			resultQSCD.getQscdSubStatus().addAll(resultQC.getQcSubStatus());
			endProc = Boolean.TRUE;
		}
		if (!endProc) {
			String tslDateTimeString = StaticValetConfig.getProperty(StaticValetConfig.TSL_DATE_TIME);
			Date tslDateTime = null;
			try {
				tslDateTime = UtilsDate.transformDate(tslDateTimeString, UtilsDate.FORMAT_DATE_TIME_TSL);

			} catch (ParseException e) {
				endProc = true;
			}
			if (!endProc) {
				if (tslDateTime != null && validationDate.before(tslDateTime)) {
					// PRO-4.5.4-03
					procQscdQCForEsig(cert, resultQC, resultQSCD);
					if (resultQC.getQcStatus() != null) {
						// el proceso ha termindao
						endProc = Boolean.TRUE;
					}

				} else {
					if (checkQCResultsEsigOrEseal(resultQC.getQcResults())) {
						// PRO-4.5.4-04
						procQscdQCForEsigOrQCForEseal(cert, resultQC, resultQSCD);
					} else {
						resultQSCD.setQscdResult(TSLStatusConstants.QSCD_INDETERMINATE);
						resultQSCD.setQscdStatus(TSLStatusConstants.PROCESS_PASSED);
					}
				}
			}

		}
	}

	/**
	 * Method that checks if QC_Results includes the value "QC_For_eSig" or
	 * "QC_For_eSeal".
	 * 
	 * @param qcResults
	 *            List of QCResult obtained in the detection of the certificate
	 *            according to the validation date.
	 * @return
	 */
	private boolean checkQCResultsEsigOrEseal(List<QCResult> qcResults) {
		return qcResults.contains(QCResult.QC_FOR_ESIG) || qcResults.contains(QCResult.QC_FOR_ESEAL);
	}

	/**
	 * Method that checks if value "QC_For_eSig" or "QC_For_eSeal", then
	 * considering respectively CHECK_1_SET-OF_QE or CHECK_2_SET-OF_QE as part
	 * of the outputs of the process run in PRO-4.5.4-01
	 * 
	 * @param qcSubStatus
	 *            List of SubStatus.
	 */
	private void procQscdQCForEsigOrQCForEseal(X509Certificate cert, ResultQualifiedCertificate resultQC,
			ResultQSCDDetermination resultQSCD) {
		boolean endProc = Boolean.FALSE;
		List<String> listQualifiersUri = new ArrayList<String>();
		List<QualificationElement> listQE = new ArrayList<QualificationElement>();

		if (resultQC.getQcResults().contains(QCResult.QC_FOR_ESIG)) {
			listQE.addAll(resultQC.getCheck1ListOfQE());
		} else if (resultQC.getQcResults().contains(QCResult.QC_FOR_ESEAL)) {
			listQE.addAll(resultQC.getCheck2ListOfQE());
		}

		if (!listQE.isEmpty()) {
			for (QualificationElement qe : listQE) {
				if (qe.isThereSomeQualifierUri()) {

					for (URI qualifierUri : qe.getQualifiersList()) {
						listQualifiersUri.add(qualifierUri.toString());
					}
				}
			}
		}

		// PRO-4.5.4-04 b)
		if (!listQualifiersUri.isEmpty() && checkQSCDIndeterminateEsigEseal(listQualifiersUri)) {
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_INDETERMINATE);
			resultQSCD.setQscdStatus(TSLStatusConstants.QSCD_STATUS_WARNING);
			resultQSCD.getQscdSubStatus().add(TSLStatusConstants.QSCD_SUBSTATUS_WARNING_2);
			endProc = Boolean.TRUE;
		}

		if (!endProc) {
			// PRO-4.5.4-04 d)
			proc_getQSCDStatusRegulationRegime(listQualifiersUri, resultQC.getInfoQcResult().getCertExtension(),
					resultQSCD);
			// }

			// PRO-4.5.4-04 e)
			resultQSCD.setQscdStatus(TSLStatusConstants.PROCESS_PASSED);
			endProc = Boolean.TRUE;

		}

	}

	/**
	 * Method to obtain QSCD according to Table 6: QSCD status check (Directive
	 * regime).
	 * 
	 * @param listQualifiersUri
	 *            List of qualifiers.
	 * @param certExtension
	 *            Object that represetn the extensions contained in the
	 *            certificate.
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void proc_getQSCDStatusRegulationRegime(List<String> listQualifiersUri, CertificateExtension certExtension,
			ResultQSCDDetermination resultQSCD) {
		// PRO-4.5.4-03 a) 4)
		if (checkQCQSCDManagedOnBehalfOrQCWithQSCD(listQualifiersUri)) {
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_YES);
		} else if (listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOSSCD)) {
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_NO);
		} else if (listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDSTATUSASINCERT)
				|| listQualifiersUri.isEmpty()) {
			// obtenemos la fila
			String row = certExtension.getRowQSCDRegulationRegime();
			if (row != null) {
				if (row.equalsIgnoreCase(QCCertificateConstants.QC_ROW1)) {
					resultQSCD.setQscdResult(TSLStatusConstants.QSCD_YES);
				} else {
					resultQSCD.setQscdResult(TSLStatusConstants.QSCD_NO);
				}
			}
		}

	}

	/**
	 * Method that checks if the value "QSCD" is undetermined.
	 * 
	 * @param listQualifiersUri
	 *            List of qualifiers.
	 * @return true, if is indeterminate.
	 */
	private boolean checkQCQSCDManagedOnBehalfOrQCWithQSCD(List<String> listQualifiersUri) {
		// PRO-4.5.4-03 a) 2) iii)
		return listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDMANAGEDONBEHALF)
				|| listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHQSCD);
	}

	/**
	 * Method that checks if the certificate has undetermined qscd.
	 * 
	 * @param listQualifiersUri
	 *            List of qualifiers.
	 * @return true, if is indeterminate.
	 */
	private boolean checkQSCDIndeterminateEsigEseal(List<String> listQualifiersUri) {
		// PRO-4.5.4-04 b)
		boolean indeterminate = Boolean.FALSE;
		if (listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHQSCD)
				&& listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOQSCD)) {
			indeterminate = Boolean.TRUE;
		} else if (listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDMANAGEDONBEHALF)
				&& listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOQSCD)) {
			indeterminate = Boolean.TRUE;
		} else if (listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDSTATUSASINCERT)
				&& !listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOQSCD)
				&& !listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHQSCD)
				&& !listQualifiersUri.contains(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDMANAGEDONBEHALF)) {
			indeterminate = Boolean.TRUE;
		}
		return indeterminate;
	}

	/**
	 * Method to obtain the QSCD value for the validation date before
	 * '2016-06-30T22:00:00Z'.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void procQscdQCForEsig(X509Certificate cert, ResultQualifiedCertificate resultQC,
			ResultQSCDDetermination resultQSCD) {
		boolean endProc = Boolean.FALSE;
		// PRO-4.5.4-03 a)
		if (resultQC.getQcResults().contains(QCResult.QC_FOR_ESIG)) {
			// PRO-4.5.4-03 a) 1)
			if (checkQSCDIndeterminate(resultQC.getInfoQcResult().getQualifierCheck1())) {
				resultQSCD.setQscdResult(TSLStatusConstants.QSCD_INDETERMINATE);
				resultQSCD.setQscdStatus(TSLStatusConstants.QSCD_STATUS_WARNING);
				resultQSCD.getQscdSubStatus().add(TSLStatusConstants.QSCD_SUBSTATUS_WARNING_1);
				endProc = Boolean.TRUE;
			}

			if (!endProc) {
				// PRO-4.5.4-03 a) 4)
				proc_getQSCDStatusDirectiveRegime(resultQC.getInfoQcResult().getQualifierCheck1(),
						resultQC.getInfoQcResult().getCertExtension(), resultQSCD);
				// PRO-4.5.4-03 a) 5)
				resultQSCD.setQscdStatus(TSLStatusConstants.PROCESS_PASSED);
				// PRO-4.5.4-13 a) 6)
				endProc = Boolean.TRUE;

			}
		}
		if (!endProc) {
			// PRO-4.5.4-03 b)c)d)
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_INDETERMINATE);
			resultQSCD.setQscdStatus(TSLStatusConstants.PROCESS_PASSED);
			endProc = Boolean.TRUE;
		}

	}

	/**
	 * Method that checks if the certificate has undetermined qscd.
	 * 
	 * @param qualifierCheck1
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @return true, if is indeterminate
	 */
	private boolean checkQSCDIndeterminate(TspServiceQualifier qualifierCheck1) {
		// PRO-4.5.4-03 a) 1)
		return checkQCWithSSCDAndQCNoSSCD(qualifierCheck1) || checkQCSSCDStatusAsInCertAndQCWithSSCD(qualifierCheck1)
				|| checkQCSSCDStatusAsInCertAndQCNoSSCD(qualifierCheck1);
	}

	/**
	 * Checks if the values 'QCSSCDStatusAsInCert' and 'QCNoSSCD'are found in
	 * QSCD-Results;
	 * 
	 * @param tspServiceQualifier
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @return True if it has the indicated elements.
	 */

	private boolean checkQCSSCDStatusAsInCertAndQCNoSSCD(TspServiceQualifier tspServiceQualifier) {
		// PRO-4.5.4-03 a) 2)iii)
		return tspServiceQualifier.isQcSSCDStatusAsInCert() && tspServiceQualifier.isQcNoSSCD();
	}

	/**
	 * Checks if the values 'QCSSCDStatusAsInCert' and 'QCWithSSCD are found in
	 * QSCD-Results;
	 * 
	 * @param tspServiceQualifier
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @return True if it has the indicated elements.
	 */
	private boolean checkQCSSCDStatusAsInCertAndQCWithSSCD(TspServiceQualifier tspServiceQualifier) {
		// PRO-4.5.4-03 a) 2)ii)
		return tspServiceQualifier.isQcSSCDStatusAsInCert() && tspServiceQualifier.isQcWithSSCD();
	}

	/**
	 * Checks if the values 'QCWithSSCD' and 'QCNoSSCD' are found in
	 * QSCD-Results;
	 * 
	 * @param tspServiceQualifier
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @return True if it has the indicated elements.
	 */
	private boolean checkQCWithSSCDAndQCNoSSCD(TspServiceQualifier tspServiceQualifier) {
		// PRO-4.5.4-03 a) 2)i)
		return tspServiceQualifier.isQcWithSSCD() && tspServiceQualifier.isQcNoSSCD();
	}

	/**
	 * Method to obtain the QSCD value according to Table 6: QSCD status check
	 * (Directive regime).
	 * 
	 * @param qualifierCheck1
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @param certExtension
	 *            Object that represetn the extensions contained in the
	 *            certificate.
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void proc_getQSCDStatusDirectiveRegime(TspServiceQualifier qualifierCheck1,
			CertificateExtension certExtension, ResultQSCDDetermination resultQSCD) {
		if (qualifierCheck1.isQcWithSSCD()) {
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_YES);
		} else if (qualifierCheck1.isQcNoSSCD()) {
			resultQSCD.setQscdResult(TSLStatusConstants.QSCD_NO);
		} else {
			// depende de la fila
			String row = certExtension.getRowQSCDDirectiveRegime();
			if (row.equalsIgnoreCase(QCCertificateConstants.QC_ROW1)) {
				resultQSCD.setQscdResult(TSLStatusConstants.QSCD_YES);
			} else {
				resultQSCD.setQscdResult(TSLStatusConstants.QSCD_NO);
			}
		}

	}

	/**
	 * Method that executes the procedure 4.4.EU qualified certificate
	 * determination of ETSI TS 119 615 v.1.1.1. to obtain the qualification of
	 * the certificate.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param tspList
	 *            List of TrustServiceProvider.
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void procEUQualifiedCertificateDetermination(ResultQualifiedCertificate resultQC, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, Date validationDate, List<TrustServiceProvider> tspList,
			boolean isDateIssue) throws TSLQualificationEvalProcessException {

		boolean endProc = Boolean.FALSE;
		if (tspList != null && !tspList.isEmpty()) {
			// se llama al PROC3. Obtaining listed services matching a
			// certificate
			ResultServiceInformation resultSI = new ResultServiceInformation();
			procListedServiceMachingCertificate(resultSI, cert, isCACert, isTsaCertificate, validationDate, tspList,
					isDateIssue);

			// PRO-4.4.4-04
			if (resultSI.getSiStatus().equals(TSLStatusConstants.PROCESS_FAILED)) {
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
				resultQC.getQcSubStatus().add(resultSI.getSiStatus());
				resultQC.getQcSubStatus().addAll(resultSI.getSiSubStatus());
				endProc = Boolean.TRUE;
				resultQC.setEndProcedure(endProc);

				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL356));
			}

			// PRO-4.4.4-05
			if (!endProc && resultSI.getSiResults().isEmpty()) {
				// no se ha obtenido TSPService que identifican all certificado.
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED);
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_ERROR_1);
				resultQC.getQcResults().add(QCResult.NOT_QUALIFIED);

				// se consulta si es una TSA
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL357));
				if (isTsaCertificate) {
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL395));
					SIResult siResultTsa = resultSI.getInfoSIResult().getSiResultTSA();

					if (siResultTsa != null) {
						resultQC.getInfoQcResult().setTspServiceDetected(siResultTsa.getTspService());
						resultQC.getInfoQcResult().setTspName(siResultTsa.getTspName());
						resultQC.getInfoQcResult().setTspDetected(siResultTsa.getTspDetected());
						resultQC.getInfoQcResult().setTspServiceTSADetected(Boolean.TRUE);
						// seguimos con el proceso pero ya se ha indicado que el
						// certificado es reconocido por un servicio de sello de
						// tiempo.
						LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL391));
						resultQC.getInfoQcResult().setInfoCertificateIssuer(resultSI.getInfoCertificateIssuer());
					} else {
						LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL400));
					}
				}
				endProc = Boolean.TRUE;
				resultQC.setEndProcedure(endProc);

			}

			// PRO-4.4.4-06
			if (!endProc && checkErrorTSPNameInconsistency(resultSI, cert)) {
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
				resultQC.getQcSubStatus().add((TSLStatusConstants.QC_SUBSTATUS_ERROR_2));
				resultQC.getQcResults().add(QCResult.INDETERMINATE);
				endProc = Boolean.TRUE;
				resultQC.setEndProcedure(endProc);
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL359));

			}

			if (!endProc) {
				// PRO-4.4.4-07
				// se obtiene la fecha inicial de validación del fichero de
				// propiedades.
				String tslDateTimeString = StaticValetConfig.getProperty(StaticValetConfig.TSL_DATE_TIME);
				Date tslDateTime = null;
				try {
					tslDateTime = UtilsDate.transformDate(tslDateTimeString, UtilsDate.FORMAT_DATE_TIME_TSL);

				} catch (ParseException e) {
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL360));
					endProc = Boolean.TRUE;
					resultQC.setEndProcedure(endProc);

				}

				if (!endProc) {

					// si se ha llegado hasta aqui es que el certificado ha sido
					// detectado en la TSL.
					resultQC.getInfoQcResult().setCertificateDetected(Boolean.TRUE);
					assignResultSItoResultQC(resultSI, resultQC);

					// obtenemos extensiones del certificado que se utilizará a
					// lo largo
					// del proceso de obtención de cualificación de certificado
					// y
					// determinación del QSCD.
					// PRO-4.4.4-13/21/29/33 i)
					CertificateExtension ce = checkAndAnalyzerExtensionCert(resultQC.getTslCertExtAnalyzer());
					resultQC.getInfoQcResult().setCertExtension(ce);

					if (tslDateTime != null && !validationDate.before(tslDateTime)) {
						// PRO-4.4.4-08
						proc_check1(resultQC, resultSI, cert);

					} else {
						// PRO-4.4.4-33
						proc_check1NotQualifiedForEsealAndNotQWac(resultQC, resultSI, cert);
					}

				}

			}

		}

	}

	/**
	 * Method to store the result in the ResultQualifiedCertificate variable.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 */
	private void assignResultSItoResultQC(ResultServiceInformation resultSI, ResultQualifiedCertificate resultQC) {
		if (resultSI.getSiStatus().equals(TSLStatusConstants.PROCESS_PASSED)) {
			SIResult siResultSelected = null;
			siResultSelected = resultSI.getSiResults().get(0);
			if (siResultSelected != null) {
				resultQC.getInfoQcResult().setTspServiceDetected(siResultSelected.getTspService());
				resultQC.getInfoQcResult().setTspDetected(siResultSelected.getTspDetected());
				resultQC.getInfoQcResult().setHistoricServiceInf(siResultSelected.isHistoricServiceInf());
				resultQC.getInfoQcResult().setShiSelected(siResultSelected.getSiAtDateTime());
			}
			resultQC.getInfoQcResult().setInfoCertificateIssuer(resultSI.getInfoCertificateIssuer());

		}
	}

	/**
	 * Method to obtain the value of the CHECK1 variable necessary to obtain the
	 * qualification of the certificate.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void proc_check1NotQualifiedForEsealAndNotQWac(ResultQualifiedCertificate resultQC,
			ResultServiceInformation resultSI, X509Certificate cert) throws TSLQualificationEvalProcessException {
		boolean endProc = false;
		// PRO-4.4.4-33 a)
		String check2 = QCResult.NOT_QUALIFIED_FOR_ESEAL.toString();
		// PRO-4.4.4-33 b)
		String check3 = QCResult.NOT_QWAC.toString();
		// PRO-4.4.4-33 e)
		String check1 = null;

		// PRO-4.4.4-33 c) d) /si devuelve true, es que se cumple 4.4.4-33 d)
		// hay dos o más valores de 'Service previous status' no identico, se
		// para el proceso.
		if (checkSiResultsIdenticalOrNot(resultQC, resultSI)) {
			resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_ERROR_3);
			resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
			endProc = true;
		}

		if (!endProc && checkServiceStatus(resultSI)) {
			// PRO-4.4.4-33 f)

			// PRO-4.4.4-33 f) 1)
			check1 = QCResult.NOT_QUALIFIED_FOR_ESIG.toString();
			resultQC.getInfoQcResult().setCheck1(check1);
			// resultQC.getListQualifiers().add(check1);
			resultQC.getInfoQcResult().setCheck2(check2);
			// resultQC.getListQualifiers().add(check2);
			resultQC.getInfoQcResult().setCheck3(check3);
			// resultQC.getListQualifiers().add(check3);

			// PRO-4.4.4-33 f) 2)
			resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED);
			// PRO-4.4.4-33 f) 3)
			endProc = true;
		}

		if (!endProc) {
			// PRO-4.4.4-33 g)
			// check1 sigue siendo nulo
			// se obtiene CHECK_1_SET-OF_QE
			procSettingsCheck1SetOfQE(resultQC, resultSI, cert);
			// PRO-4.4.4-33 h)
			if (checkIndeterminateQC(resultQC.getInfoQcResult().getQualifierCheck1())) {
				// PRO-4.4.4-33 h) 2) i)
				resultQC.getInfoQcResult().setCheck1(QCResult.INDET_QC_FOR_ESIG.toString());
				// PRO-4.4.4-33 h) 2) ii)
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_ERROR_4);
				// PRO-4.4.4-33 h) 2) iii)
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
				endProc = Boolean.FALSE;

			}

			if (!endProc) {
				// PRO-4.4.4-33 i)Este proceso se realiza previamente en el
				// método
				// procEUQualifiedCertificateDetermination(...)

				// PRO-4.4.4-33 j)k)
				getCheck1Dir1999_93_EC(resultQC);

				// PRO-4.4.4-33 l)
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED);
				// PRO-4.4.4-33 m)
				// Se comprueba si no existe, para no tener elementos
				// repetidos...

				if (!resultQC.getQcResults().contains(check1)) {
					resultQC.getQcResults().add(QCResult.getQCResult(check1));
				}
				if (!resultQC.getQcResults().contains(check2)) {
					resultQC.getQcResults().add(QCResult.getQCResult(check2));
				}
				if (!resultQC.getQcResults().contains(check3)) {
					resultQC.getQcResults().add(QCResult.getQCResult(check3));
				}
			}
		}

		resultQC.setEndProcedure(endProc);

	}

	/**
	 * Method to obtain the value of the CHECK1 variable necessary to obtain the
	 * qualification of the certificate in Table 5: QC-For-eSig determination
	 * under Directive 1999/93/EC [i.7]
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void getCheck1Dir1999_93_EC(ResultQualifiedCertificate resultQC) {
		// PRO-4.4.4-33 j)k)
		String column = null;
		if (resultQC.getInfoQcResult().getQualifierCheck1() != null) {
			column = resultQC.getInfoQcResult().getQualifierCheck1().getColumnCheck1Dir1999_93_EC();

		} else {
			column = QCCertificateConstants.QC_CHECK_COLUMN1;
		}

		String row = resultQC.getInfoQcResult().getCertExtension().getRowCheck1Dir1999_93_EC();

		switch (column) {
		case QCCertificateConstants.QC_CHECK_COLUMN1:
			if (row.equals(QCCertificateConstants.QC_ROW5)) {
				resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			} else {
				resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN2:
			resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN3:
			resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());
			break;
		default:
			break;
		}

	}

	private boolean checkIndeterminateQC(TspServiceQualifier tsq) {
		return checkQCForESealAndQCForWSA(tsq)
				&& (checkNonQualifiedAndQCStatement(tsq) || checkNonQualifiedOrQCStatement(tsq));
	}

	/**
	 * Method that checks that the qualifiers include the URIs
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/NotQualified' or
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCStatement'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkNonQualifiedOrQCStatement(TspServiceQualifier tsq) {
		// PRO-4.4.4-33 f)
		return tsq.isNotQualified() || tsq.isQcStatement();
	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT from all SI-at-Date-time elements of the SI-Results
	 * tuples (from the process run in PRO-4.4.4-03).
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void procSettingsCheck1SetOfQE(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		for (SIResult si : resultSI.getSiResults()) {
			obtainQualificationsCheck1(cert, si, resultQC);
		}

	}

	/**
	 * Method that check whether the 'Service previous status' field of (any of)
	 * the SI-at-Date-time element(s) of the SI-Results tuples has one of the
	 * values
	 * "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/supervisionceased",
	 * "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/supervisionrevoked",
	 * "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/accreditationceased",
	 * or
	 * "http://uri.etsi.org/TrstSvc/TrustedList/Svcstatus/accreditationrevoked",
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @return
	 */
	private boolean checkServiceStatus(ResultServiceInformation resultSI) {
		// PRO-4.4.4-33 f)
		boolean result = false;
		for (ServiceHistoryInstance si : resultSI.getInfoSIResult().getListSiAtDateTime()) {
			if (si.getServiceStatus().toString()
					.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_SUPERVISIONCEASED)) {
				result = true;
				break;
			}
			if (si.getServiceStatus().toString()
					.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_SUPERVISIONREVOKED)) {
				result = true;
				break;
			}
			if (si.getServiceStatus().toString()
					.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_ACCREDITATIONCEASED)) {
				result = true;
				break;
			}
			if (si.getServiceStatus().toString()
					.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_ACCREDITATIONREVOKED)) {
				result = true;
				break;
			}

		}
		return result;
	}

	/**
	 * Method that checks if two or more SIResults contain identical 'Service
	 * status'.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @return true, if two or more SIResults contain identical 'Service
	 *         status'.
	 */
	private boolean checkSiResultsIdenticalOrNot(ResultQualifiedCertificate resultQC,
			ResultServiceInformation resultSI) {
		// PRO-4.4.4-33 c)
		String status = null;
		boolean encNotIdentical = false;
		boolean encIdentical = false;
		if (resultSI.getInfoSIResult().getListSiAtDateTime().size() > 1) {
			// se comprueba si son iguales o difentes
			for (ServiceHistoryInstance si : resultSI.getInfoSIResult().getListSiAtDateTime()) {
				if (status != null) {
					// PRO-4.4.4-33 c)
					if (!encIdentical && status.equalsIgnoreCase(si.getServiceStatus().toString())) {
						resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_8);
						encIdentical = true;
					} else {
						if (!status.equalsIgnoreCase(si.getServiceStatus().toString())) {
							encNotIdentical = true;
							// según PRO-4.4.4-33 d) si hay dos no identicos, el
							// proceso falla y se para.
							break;
						}
					}
				} else {
					status = si.getServiceStatus().toString();
				}
			}
		}
		return encNotIdentical;
	}

	/**
	 * Method to obtain the value of the CHECK1 variable necessary to obtain the
	 * qualification of the certificate.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void proc_check1(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-08
		String check1 = null;
		boolean endProc = Boolean.FALSE;
		// PRO-4.4.4-09
		if (resultSI.getSiSubStatus().contains(TSLStatusConstants.SI_ERROR_T1_DUPLICATION)) {
			check1 = QCResult.INDET_QC_FOR_ESIG.toString();
			resultQC.getInfoQcResult().setCheck1(check1);
			// PRO-4.4.4-16
			proc_check2(resultQC, resultSI, cert);
			endProc = Boolean.TRUE;
		}

		// PRO-4.4.4-10
		if (!endProc && checkSiAtDateTimeForeSignatures(resultQC, resultSI, cert)) {
			check1 = QCResult.NOT_QUALIFIED_FOR_ESIG.toString();
			resultQC.getInfoQcResult().setCheck1(check1);
			// PRO-4.4.4-16
			proc_check2(resultQC, resultSI, cert);
			endProc = Boolean.TRUE;
		}

		if (!endProc) {
			// PRO-4.4.4-11
			procSettingsCheck1SetOfQEEsig(resultQC, resultSI, cert);

			// PRO-4.4.4-12
			if (resultQC.getInfoQcResult().getQualifierCheck1() != null
					&& checkIndeterminateEsig(resultQC.getInfoQcResult().getQualifierCheck1())) {
				check1 = QCResult.INDET_QC_FOR_ESIG.toString();
				resultQC.getInfoQcResult().setCheck1(check1);
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_1);
				// PRO-4.4.4-16
				proc_check2(resultQC, resultSI, cert);
				endProc = Boolean.TRUE;
			}
		}

		// PRO-4.4.4-13 Este proceso se realiza previamente en el método
		// procEUQualifiedCertificateDetermination(...)
		if (!endProc) {
			// PRO-4.4.4-14
			if (checkResultQcType(resultQC.getInfoQcResult().getCertExtension())) {
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_2);
			}

			// PRO-4.4.4-15 b)c)d) //Se obtiene el valor check1
			getCheck1(resultQC);

			// PRO-4.4.4-16
			proc_check2(resultQC, resultSI, cert);
		}
	}

	/**
	 * Method that obtains the CHECK1 value depending on the values obtained in
	 * the previous procedures.
	 * 
	 * @param resultQC
	 */
	private void getCheck1(ResultQualifiedCertificate resultQC) {
		// PRO-4.4.4-15 b)

		String column = null;

		if (resultQC.getInfoQcResult().getQualifierCheck1() != null) {
			column = resultQC.getInfoQcResult().getQualifierCheck1().getColumnCheck1();

		} else {
			column = QCCertificateConstants.QC_CHECK_COLUMN1;
		}
		String row = resultQC.getInfoQcResult().getCertExtension().getRowCheck();
		// se guarda para obtener en los procesos siguiente el valor de CHECK2 y
		// CHECK3.
		resultQC.getInfoQcResult().setSelectRow(row);

		// PRO-4.4.4-15 d)
		if (row.equals(QCCertificateConstants.QC_ROW8) && column.equals(QCCertificateConstants.QC_CHECK_COLUMN3)) {
			resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_3);
		}

		// PRO-4.4.4-15 c)
		switch (column) {
		case QCCertificateConstants.QC_CHECK_COLUMN1:
			if (row.equals(QCCertificateConstants.QC_ROW1)) {
				resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());

			} else if (row.equals(QCCertificateConstants.QC_ROW4) || row.equals(QCCertificateConstants.QC_ROW5)
					|| row.equals(QCCertificateConstants.QC_ROW7)) {
				resultQC.getInfoQcResult().setCheck1(QCResult.INDET_QC_FOR_ESIG.toString());
			} else {
				resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN2:
			resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN3:
			if (row.equals(QCCertificateConstants.QC_ROW1) || row.equals(QCCertificateConstants.QC_ROW9)) {
				resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());
			} else if (row.equals(QCCertificateConstants.QC_ROW2) || row.equals(QCCertificateConstants.QC_ROW3)
					|| row.equals(QCCertificateConstants.QC_ROW6) || row.equals(QCCertificateConstants.QC_ROW10)
					|| row.equals(QCCertificateConstants.QC_ROW11) || row.equals(QCCertificateConstants.QC_ROW14)) {
				resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			} else {
				resultQC.getInfoQcResult().setCheck1(QCResult.INDET_QC_FOR_ESIG.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN4:
			if (resultQC.getInfoQcResult().getCertExtension().isQcCompliance()) {
				resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());
			} else {
				resultQC.getInfoQcResult().setCheck1(QCResult.NOT_QUALIFIED_FOR_ESIG.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN5:
			resultQC.getInfoQcResult().setCheck1(QCResult.QC_FOR_ESIG.toString());
			break;
		default:
			break;
		}

	}

	/**
	 * Method that checks for inconsistency between the certificate and the
	 * QCType qualifiers.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @return
	 */
	private boolean checkResultQcType(CertificateExtension cerExt) {
		boolean result = Boolean.FALSE;
		int numQcType = 0;
		if (cerExt.isQcType1()) {
			numQcType++;
		}
		if (cerExt.isQcType2()) {
			numQcType++;
		}
		if (cerExt.isQcType3()) {
			numQcType++;
		}

		if (numQcType > 1) {
			result = Boolean.TRUE;
		}
		return result;
	}

	/**
	 * Method that checks if the certificate is 'INDET_QC_For_eSeal'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return true if the certificate is 'INDET_QC_For_eSeal'.
	 */
	private boolean checkIndeterminateESeal(TspServiceQualifier tsq) {
		return checkNonQualifiedAndQCStatement(tsq) || checkQCForESigAndQCForESeal(tsq)
				|| checkQCForESigAndQCForWSA(tsq) || checkQCForESealAndQCForWSA(tsq);

	}

	/**
	 * Method that checks if the certificate is 'INDET_QWAC'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return true if the certificate is 'INDET_QWAC'.
	 */
	private boolean checkIndeterminateWSA(TspServiceQualifier tsq) {
		return checkNonQualifiedAndQCStatement(tsq) || checkQCForESigAndQCForESeal(tsq)
				|| checkQCForESigAndQCForWSA(tsq) || checkQCForESealAndQCForWSA(tsq);

	}

	/**
	 * Method that checks if the certificate is 'INDET_QC_For_eSig'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return true if the certificate is 'INDET_QC_For_eSig'.
	 */
	private boolean checkIndeterminateEsig(TspServiceQualifier tsq) {
		return checkNonQualifiedAndQCStatement(tsq) || checkQCForESigAndQCForESeal(tsq)
				|| checkQCForESigAndQCForWSA(tsq) || checkQCForESealAndQCForWSA(tsq) || checkQCForLegalPerson(tsq);

	}

	/**
	 * Method that checks that the qualifiers include the URIs
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/NotQualified' and
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCStatement'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkNonQualifiedAndQCStatement(TspServiceQualifier tsq) {
		// PRO-4.4.4-12 a)1)
		return tsq.isNotQualified() && tsq.isQcStatement();
	}

	/**
	 * Method that checks that the qualifiers include the URIs
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForESig' and
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForESeal'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkQCForESigAndQCForESeal(TspServiceQualifier tsq) {
		// PRO-4.4.4-12 a)2)
		return tsq.isQcForESig() && tsq.isQcForESeal();
	}

	/**
	 * Method that checks that the qualifiers include the URIs
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForESig' and
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForWSA'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkQCForESigAndQCForWSA(TspServiceQualifier tsq) {
		// PRO-4.4.4-12 a)3)
		return tsq.isQcForESig() && tsq.isQcForWSA();
	}

	/**
	 * Method that checks that the qualifiers include the URIs
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForESeal' and
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForWSA'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkQCForESealAndQCForWSA(TspServiceQualifier tsq) {
		// PRO-4.4.4-12 a)4)
		return tsq.isQcForESeal() && tsq.isQcForWSA();
	}

	/**
	 * Method that checks that the qualifiers include the URI
	 * 'http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/QCForLegalPerson'
	 * 
	 * @param tsq
	 *            Object that represents the information obtained from analyzing
	 *            the qualifiers of the TSPService that identifies the
	 *            certificate.
	 * @return
	 */
	private boolean checkQCForLegalPerson(TspServiceQualifier tsq) {
		// PRO-4.4.4-12 a)5)
		return tsq.isQcForLegalPerson();
	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT from all SI-at-Date-time elements of the SI-Results
	 * tuples (from the process run in PRO-4.4.4-03) that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForeSeals".
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void procSettingsCheck2SetOfQE(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-11
		for (SIResult si : resultSI.getSiResults()) {
			if (si.isAsiForESeal()) {
				obtainQualificationsCheck2(cert, si, resultQC);
			}
		}

	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT from all SI-at-Date-time elements of the SI-Results
	 * tuples (from the process run in PRO-4.4.4-03) that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForeSignatures".
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void procSettingsCheck1SetOfQEEsig(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-11
		for (SIResult si : resultSI.getSiResults()) {
			if (si.isAsiForESIG()) {
				obtainQualificationsCheck1(cert, si, resultQC);
			}
		}

	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT of the SI-Result that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForeSignatures".
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param si
	 * @param resultQC
	 * @throws TSLQualificationEvalProcessException
	 */
	private void obtainQualificationsCheck1(X509Certificate cert, SIResult si, ResultQualifiedCertificate resultQC)
			throws TSLQualificationEvalProcessException {
		List<IAnyTypeExtension> extensionsList = si.getSiAtDateTime().getServiceInformationExtensions();
		if (extensionsList != null && !extensionsList.isEmpty()) {
			// Recorremos la lista buscando el elemento Qualifications.
			for (IAnyTypeExtension extension : extensionsList) {
				// Si es del tipo Qualifications...
				if (extension.getImplementationExtension() == TSLBuilderConstants.IMPL_QUALIFICATIONS) {
					// Obtenemos el objeto Qualifications Extension.
					Qualifications qualificationsExtension = (Qualifications) extension;
					try {
						// Iniciamos la comprobación según los criteria.
						List<QualificationElement> listQE = getQualificationsExtensionsDetectCert(cert,
								qualificationsExtension);
						resultQC.getCheck1ListOfQE().addAll(listQE);

					} catch (TSLQualificationEvalProcessException e) {
						// Si la extensión es crítica, propagamos la
						// excepción.
						if (qualificationsExtension.isCritical()) {
							throw e;
						} else {
							// Al no ser crítica, simplemente lo
							// notificamos con
							// un warn y continuamos.
							LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL251));
						}
					}
				}
			}
		}

		if (!resultQC.getCheck1ListOfQE().isEmpty()) {
			for (QualificationElement qe : resultQC.getCheck1ListOfQE()) {
				// Si hay algún qualifier...
				if (qe.isThereSomeQualifierUri()) {
					for (URI qualifierUri : qe.getQualifiersList()) {
						analyzeQuelifier(resultQC.getInfoQcResult().getQualifierCheck1(), qualifierUri.toString());
					}
				}
			}
		}

	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT of the SI-Result that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForeSeals".
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param si
	 * @param resultQC
	 * @throws TSLQualificationEvalProcessException
	 */
	private void obtainQualificationsCheck2(X509Certificate cert, SIResult si, ResultQualifiedCertificate resultQC)
			throws TSLQualificationEvalProcessException {
		List<IAnyTypeExtension> extensionsList = si.getSiAtDateTime().getServiceInformationExtensions();
		if (extensionsList != null && !extensionsList.isEmpty()) {
			// Recorremos la lista buscando el elemento Qualifications.
			for (IAnyTypeExtension extension : extensionsList) {
				// Si es del tipo Qualifications...
				if (extension.getImplementationExtension() == TSLBuilderConstants.IMPL_QUALIFICATIONS) {
					// Obtenemos el objeto Qualifications Extension.
					Qualifications qualificationsExtension = (Qualifications) extension;
					try {
						// Iniciamos la comprobación según los criteria.
						List<QualificationElement> listQE = getQualificationsExtensionsDetectCert(cert,
								qualificationsExtension);
						resultQC.getCheck2ListOfQE().addAll(listQE);

					} catch (TSLQualificationEvalProcessException e) {
						// Si la extensión es crítica, propagamos la
						// excepción.
						if (qualificationsExtension.isCritical()) {
							throw e;
						} else {
							// Al no ser crítica, simplemente lo
							// notificamos con
							// un warn y continuamos.
							LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL251));
						}
					}
				}
			}
		}

		if (!resultQC.getCheck2ListOfQE().isEmpty()) {
			for (QualificationElement qe : resultQC.getCheck2ListOfQE()) {
				// Si hay algún qualifier...
				if (qe.isThereSomeQualifierUri()) {
					for (URI qualifierUri : qe.getQualifiersList()) {
						analyzeQuelifier(resultQC.getInfoQcResult().getQualifierCheck2(), qualifierUri.toString());
					}
				}
			}
		}

	}

	/**
	 * Method that will store in a variable the result of analyzing the
	 * 'Qualifications' extensions obtained for its use to obtain the CHECK1,
	 * CHECK2 or CHECK3 variables.
	 * 
	 * @param tspServiceQualifier
	 *            Object the qualifiers contained in a TSPservice that
	 *            identifies a certificate.
	 * @param qualifierUriString
	 */
	protected abstract void analyzeQuelifier(TspServiceQualifier tspServiceQualifier, String qualifierUriString);

	/**
	 * Checks if the input certificate is detected by the criterias on the
	 * Qualifications Extension of the input TSP Service.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to detect.
	 * @param qualificationsExtension
	 *            Qualifications Extension to use.
	 * @return List of QualificationElement.
	 * @throws TSLQualificationEvalProcessException
	 */
	private List<QualificationElement> getQualificationsExtensionsDetectCert(X509Certificate cert,
			Qualifications qualificationsExtension) throws TSLQualificationEvalProcessException {

		List<QualificationElement> result = new ArrayList<QualificationElement>();

		// Recorremos la lista de Qualifications mientras no encontremos uno que
		// encaje con el certificado.
		for (QualificationElement qe : qualificationsExtension.getQualificationsList()) {

			// Primero analizamos si se cumplen los criteria para detectar el
			// certificado.
			// Obtenemos la lista de criterios.
			CriteriaList cl = qe.getCriteriaList();
			// Analizamos el certificado.
			if (cl.checkCertificate(cert)) {
				// Ya seguro que al menos un criteria ha identificado el
				// certificado.
				result.add(qe);
			}

		}

		return result;

	}

	/**
	 * Method to check if the certificate is 'Not_Qualified_For_eSig'.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private boolean checkSiAtDateTimeForeSignatures(ResultQualifiedCertificate resultQC,
			ResultServiceInformation resultSI, X509Certificate cert) {
		boolean isNotQualified = Boolean.FALSE;
		boolean foundForeSignature = Boolean.FALSE;
		for (int index = 0; index < resultSI.getSiResults().size(); index++) {
			SIResult si = resultSI.getSiResults().get(index);
			if (si.isAsiForESIG()) {
				foundForeSignature = Boolean.TRUE;
				if (si.getServiceStatus().equals(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_WITHDRAWN)) {
					isNotQualified = Boolean.TRUE;
					break;
				}
			}

		}
		if (!foundForeSignature) {
			isNotQualified = Boolean.TRUE;
		}
		return isNotQualified;
	}

	/**
	 * Method to check if the certificate is 'Not_Qualified_For_eSeal'.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private boolean checkSiAtDateTimeForeSeals(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) {
		// PRO-4.4.4-18
		boolean isNotQualified = Boolean.FALSE;
		boolean foundForeSeal = Boolean.FALSE;
		for (int index = 0; index < resultSI.getSiResults().size(); index++) {
			SIResult si = resultSI.getSiResults().get(index);
			if (si.isAsiForESeal()) {
				foundForeSeal = Boolean.TRUE;
				if (si.getServiceStatus().equals(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_WITHDRAWN)) {
					isNotQualified = Boolean.TRUE;
					break;
				}
			}

		}
		if (!foundForeSeal) {
			isNotQualified = Boolean.TRUE;
		}
		return isNotQualified;
	}

	/**
	 * Method to check if the certificate is 'Not_Qualified_For_WSA'.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private boolean checkSiAtDateTimeForeWSA(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) {
		// PRO-4.4.4-18
		boolean isNotQualified = Boolean.FALSE;
		boolean foundForeWSA = Boolean.FALSE;
		for (int index = 0; index < resultSI.getSiResults().size(); index++) {
			SIResult si = resultSI.getSiResults().get(index);
			if (si.isAsiForWSA()) {
				foundForeWSA = Boolean.TRUE;
				if (si.getServiceStatus().equals(TSLCommonURIs.TSL_SERVICECURRENTSTATUS_WITHDRAWN)) {
					isNotQualified = Boolean.TRUE;
					break;
				}
			}

		}
		if (!foundForeWSA) {
			isNotQualified = Boolean.TRUE;
		}
		return isNotQualified;
	}

	/**
	 * Method to obtain the value of the CHECK2 variable necessary to obtain the
	 * qualification of the certificate.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void proc_check2(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-16
		String check2 = null;
		boolean endProc = Boolean.FALSE;
		// PRO-4.4.4-17
		if (resultSI.getSiSubStatus().contains(TSLStatusConstants.SI_ERROR_T2_DUPLICATION)) {
			check2 = QCResult.INDET_QC_FOR_ESEAL.toString();
			resultQC.getInfoQcResult().setCheck2(check2);
			// PRO-4.4.4-24
			proc_check3(resultQC, resultSI, cert);
			endProc = Boolean.TRUE;
		}

		// PRO-4.4.4-18
		if (!endProc && checkSiAtDateTimeForeSeals(resultQC, resultSI, cert)) {
			check2 = QCResult.NOT_QUALIFIED_FOR_ESEAL.toString();
			resultQC.getInfoQcResult().setCheck2(check2);
			// PRO-4.4.4-24
			proc_check3(resultQC, resultSI, cert);
			endProc = Boolean.TRUE;
		}

		if (!endProc) {
			// PRO-4.4.4-19
			procSettingsCheck2SetOfQE(resultQC, resultSI, cert);

			// PRO-4.4.4-20
			if (resultQC.getInfoQcResult().getQualifierCheck2() != null
					&& checkIndeterminateESeal(resultQC.getInfoQcResult().getQualifierCheck2())) {
				check2 = QCResult.INDET_QC_FOR_ESEAL.toString();
				resultQC.getInfoQcResult().setCheck2(check2);
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_4);
				// PRO-4.4.4-24
				proc_check3(resultQC, resultSI, cert);
				endProc = Boolean.TRUE;
			}
		}

		// PRO-4.4.4-21 Este proceso se realiza previamente en el método
		// procEUQualifiedCertificateDetermination(...)

		// PRO-4.4.4-22
		if (!endProc) {
			if (checkResultQcType(resultQC.getInfoQcResult().getCertExtension())) {
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_2);
			}

			// PRO-4.4.4-23 b)c)d) //Se obtiene el valor check2
			getCheck2(resultQC);

			// PRO-4.4.4-24
			proc_check3(resultQC, resultSI, cert);
		}
	}

	/**
	 * Method that obtains the CHECK2 value depending on the values obtained in
	 * the previous procedures.
	 * 
	 * @param resultQC
	 */
	private void getCheck2(ResultQualifiedCertificate resultQC) {
		String column = null;
		if (resultQC.getInfoQcResult().getQualifierCheck2() != null) {
			column = resultQC.getInfoQcResult().getQualifierCheck2().getColumnCheck2();
		} else {
			column = QCCertificateConstants.QC_CHECK_COLUMN1;
		}

		String row = resultQC.getInfoQcResult().getSelectRow();
		if (row == null) {
			row = resultQC.getInfoQcResult().getCertExtension().getRowCheck();
			resultQC.getInfoQcResult().setSelectRow(row);
		}
		// se guarda para obtener en los procesos siguiente el valor de CHECK2 y
		// CHECK3.

		if (row.equals(QCCertificateConstants.QC_ROW8) && column.equals(QCCertificateConstants.QC_CHECK_COLUMN3)) {
			resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_5);
		}

		switch (column) {
		case QCCertificateConstants.QC_CHECK_COLUMN1:
			if (row.equals(QCCertificateConstants.QC_ROW2)) {
				resultQC.getInfoQcResult().setCheck2(QCResult.QC_FOR_ESEAL.toString());
			} else if (row.equals(QCCertificateConstants.QC_ROW4) || row.equals(QCCertificateConstants.QC_ROW6)
					|| row.equals(QCCertificateConstants.QC_ROW7)) {
				resultQC.getInfoQcResult().setCheck2(QCResult.INDET_QC_FOR_ESEAL.toString());
			} else {
				resultQC.getInfoQcResult().setCheck2(QCResult.NOT_QUALIFIED_FOR_ESEAL.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN2:
			resultQC.getInfoQcResult().setCheck2(QCResult.NOT_QUALIFIED_FOR_ESEAL.toString());
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN3:
			if (row.equals(QCCertificateConstants.QC_ROW2) || row.equals(QCCertificateConstants.QC_ROW10)) {
				resultQC.getInfoQcResult().setCheck2(QCResult.QC_FOR_ESEAL.toString());
			} else if (row.equals(QCCertificateConstants.QC_ROW1) || row.equals(QCCertificateConstants.QC_ROW3)
					|| row.equals(QCCertificateConstants.QC_ROW5) || row.equals(QCCertificateConstants.QC_ROW9)
					|| row.equals(QCCertificateConstants.QC_ROW11) || row.equals(QCCertificateConstants.QC_ROW13)) {
				resultQC.getInfoQcResult().setCheck2(QCResult.NOT_QUALIFIED_FOR_ESEAL.toString());
			} else {
				resultQC.getInfoQcResult().setCheck2(QCResult.INDET_QC_FOR_ESEAL.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN4:
			if (resultQC.getInfoQcResult().getCertExtension().isQcCompliance()) {
				resultQC.getInfoQcResult().setCheck2(QCResult.QC_FOR_ESEAL.toString());
			} else {
				resultQC.getInfoQcResult().setCheck2(QCResult.INDET_QC_FOR_ESEAL.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN5:
			resultQC.getInfoQcResult().setCheck2(QCResult.QC_FOR_ESEAL.toString());
			break;
		default:
			break;
		}

	}

	/**
	 * Method to obtain the value of the CHECK3 variable necessary to obtain the
	 * qualification of the certificate.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void proc_check3(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-24
		String check3 = null;
		boolean endProc = Boolean.FALSE;
		// PRO-4.4.4-25
		if (resultSI.getSiSubStatus().contains(TSLStatusConstants.SI_ERROR_T3_DUPLICATION)) {
			check3 = QCResult.INDET_QWAC.toString();
			resultQC.getInfoQcResult().setCheck3(check3);
			// PRO-4.4.4-32
			proc_compareChecks(resultQC);
			endProc = Boolean.TRUE;

		}

		// PRO-4.4.4-26
		if (!endProc && checkSiAtDateTimeForeWSA(resultQC, resultSI, cert)) {
			check3 = QCResult.NOT_QWAC.toString();
			resultQC.getInfoQcResult().setCheck3(check3);
			// PRO-4.4.4-32
			proc_compareChecks(resultQC);
			endProc = Boolean.TRUE;
		}

		if (!endProc) {
			// PRO-4.4.4-27
			procSettingsCheck3SetOfQE(resultQC, resultSI, cert);

			// PRO-4.4.4-28
			if (resultQC.getInfoQcResult().getQualifierCheck3() != null
					&& checkIndeterminateWSA(resultQC.getInfoQcResult().getQualifierCheck3())) {
				check3 = QCResult.INDET_QWAC.toString();
				resultQC.getInfoQcResult().setCheck3(check3);
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_6);
				// PRO-4.4.4-32
				proc_compareChecks(resultQC);
				endProc = Boolean.TRUE;
			}
		}

		// PRO-4.4.4-29 Este proceso se realiza previamente en el método
		// procEUQualifiedCertificateDetermination(...)

		// PRO-4.4.4-30
		if (!endProc) {
			if (checkResultQcType(resultQC.getInfoQcResult().getCertExtension())) {
				resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_2);
			}

			// PRO-4.4.4-31 b)c)d) //Se obtiene el valor check3
			getCheck3(resultQC);

			// PRO-4.4.4-32
			proc_compareChecks(resultQC);
		}
	}

	/**
	 * Method that compares two by two values CHECK1, CHECK2 and CHECK3 in Table
	 * 4: QC status check
	 * 
	 * @param resultQC
	 */
	private void proc_compareChecks(ResultQualifiedCertificate resultQC) {
		// PRO-4.4.4-32
		String check1 = resultQC.getInfoQcResult().getCheck1();
		String check2 = resultQC.getInfoQcResult().getCheck2();
		String check3 = resultQC.getInfoQcResult().getCheck3();

		// PRO-4.4.4-32 a)
		if (!checkError(check1, check2, check3)) {

			// PRO-4.4.4-32 b)
			resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED);
			// PRO-4.4.4-32 c)
			// Se comprueba si no existe, para no tener elementos repetidos...
			if (!resultQC.getQcResults().contains(check1)) {
				resultQC.getQcResults().add(QCResult.getQCResult(check1));
			}
			if (!resultQC.getQcResults().contains(check2)) {
				resultQC.getQcResults().add(QCResult.getQCResult(check2));
			}
			if (!resultQC.getQcResults().contains(check3)) {
				resultQC.getQcResults().add(QCResult.getQCResult(check3));
			}

			// PRO-4.4.4-32 d) when the comparison results in one or more
			// "warning" indications. Es warning cuando check1, check2 o check 3
			// es Indeterminado
			if (checkIndeterminate(check1, check2, check3)) {
				resultQC.setQcStatus(TSLStatusConstants.PROCESS_PASSED_WITH_WARNING);
				resultQC.getQcSubStatus().add(Language.getFormatResCoreTsl(CoreTslMessages.WARNING_QC_SUBSTATUS,
						new Object[] { check1, check2, check3 }));
			}

		} else {
			// termina el proceso.
			// PRO-4.4.4-32 a)
			resultQC.setQcStatus(TSLStatusConstants.PROCESS_FAILED);
			resultQC.getQcSubStatus().add(Language.getFormatResCoreTsl(CoreTslMessages.WARNING_QC_SUBSTATUS,
					new Object[] { check1, check2, check3 }));
		}

	}

	/**
	 * Method that checks if CHECK1, CHECK or CHECK 3 are undetermined.
	 * 
	 * @param check1
	 *            Value obtained in the variable CHECK1
	 * @param check2
	 *            Value obtained in the variable CHECK2
	 * @param check3
	 *            Value obtained in the variable CHECK3
	 * @return True if the result is determinate.
	 */
	private boolean checkIndeterminate(String check1, String check2, String check3) {
		boolean result = false;
		if ((check1 != null && check1.equals(QCResult.INDET_QC_FOR_ESIG))
				|| (check2 != null && check2.equals(QCResult.INDET_QC_FOR_ESEAL))
				|| (check3 != null && check3.equals(QCResult.INDET_QWAC))) {
			result = true;
		}
		return result;
	}

	/**
	 * Check if according to Table 4: Checking the quality control status e)
	 * processing should go to PRO-4.4.4-34. and the values CHECK1, CHECK2, and
	 * CHECK3 result in an error.
	 * 
	 * @param check1
	 *            Value obtained in the variable CHECK1
	 * @param check2
	 *            Value obtained in the variable CHECK2
	 * @param check3
	 *            Value obtained in the variable CHECK3
	 * @return True, if the result is an error.
	 */
	private boolean checkError(String check1, String check2, String check3) {
		boolean error = false;
		if ((check1 != null && check1.equals(TSLStatusConstants.CHECK1_ESIG_QL))) {
			if ((check2 != null && check2.equals(TSLStatusConstants.CHECK2_ESEAL_QL))
					|| (check3 != null && check3.equals(TSLStatusConstants.CHECK3_QWAC))) {
				error = true;
			}
		} else if (check2 != null && check2.equals(TSLStatusConstants.CHECK2_ESEAL_QL)) {
			if ((check1 != null && check1.equals(TSLStatusConstants.CHECK1_ESIG_QL))
					|| (check3 != null && check3.equals(TSLStatusConstants.CHECK3_QWAC))) {
				error = true;
			}
		} else if (check3 != null && check3.equals(TSLStatusConstants.CHECK3_QWAC)) {
			if ((check1 != null && check1.equals(TSLStatusConstants.CHECK1_ESIG_QL))
					|| (check2 != null && check2.equals(TSLStatusConstants.CHECK2_ESEAL_QL))) {
				error = true;
			}
		}
		return error;
	}

	/**
	 * Method that obtains the CHECK3 value depending on the values obtained in
	 * the previous procedures.
	 * 
	 * @param resultQC
	 */
	private void getCheck3(ResultQualifiedCertificate resultQC) {
		// PRO-4.4.4-31 b)
		String column = null;
		if (resultQC.getInfoQcResult().getQualifierCheck3() != null) {
			column = resultQC.getInfoQcResult().getQualifierCheck3().getColumnCheck3();
		} else {
			column = QCCertificateConstants.QC_CHECK_COLUMN1;
		}

		String row = resultQC.getInfoQcResult().getSelectRow();
		if (row == null) {
			row = resultQC.getInfoQcResult().getCertExtension().getRowCheck();
			resultQC.getInfoQcResult().setSelectRow(row);
		}

		// PRO-4.4.4-31 d)
		if (row.equals(QCCertificateConstants.QC_ROW8) && column.equals(QCCertificateConstants.QC_CHECK_COLUMN3)) {
			resultQC.getQcSubStatus().add(TSLStatusConstants.QC_SUBSTATUS_WARNING_7);
		}
		// PRO-4.4.4-31 c)
		switch (column) {
		case QCCertificateConstants.QC_CHECK_COLUMN1:
			if (row.equals(QCCertificateConstants.QC_ROW3)) {
				resultQC.getInfoQcResult().setCheck3(QCResult.QWAC.toString());
			} else if (row.equals(QCCertificateConstants.QC_ROW5) || row.equals(QCCertificateConstants.QC_ROW6)
					|| row.equals(QCCertificateConstants.QC_ROW7)) {
				resultQC.getInfoQcResult().setCheck3(QCResult.INDET_QWAC.toString());
			} else {
				resultQC.getInfoQcResult().setCheck3(QCResult.NOT_QWAC.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN2:
			resultQC.getInfoQcResult().setCheck3(QCResult.NOT_QWAC.toString());
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN3:
			if (row.equals(QCCertificateConstants.QC_ROW3) || row.equals(QCCertificateConstants.QC_ROW11)) {
				resultQC.getInfoQcResult().setCheck3(QCResult.QWAC.toString());
			} else if (row.equals(QCCertificateConstants.QC_ROW1) || row.equals(QCCertificateConstants.QC_ROW2)
					|| row.equals(QCCertificateConstants.QC_ROW4) || row.equals(QCCertificateConstants.QC_ROW9)
					|| row.equals(QCCertificateConstants.QC_ROW10) || row.equals(QCCertificateConstants.QC_ROW12)) {
				resultQC.getInfoQcResult().setCheck3(QCResult.NOT_QWAC.toString());
			} else {
				resultQC.getInfoQcResult().setCheck3(QCResult.INDET_QWAC.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN4:
			if (resultQC.getInfoQcResult().getCertExtension().isQcCompliance()) {
				resultQC.getInfoQcResult().setCheck3(TslMappingConstants.MAPPING_VALUE_ETSI_RESULT_Q_ESEAL);
			} else {
				resultQC.getInfoQcResult().setCheck3(QCResult.NOT_QWAC.toString());
			}
			break;
		case QCCertificateConstants.QC_CHECK_COLUMN5:
			resultQC.getInfoQcResult().setCheck3(QCResult.QWAC.toString());
			break;
		default:
			break;
		}

	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT from all SI-at-Date-time elements of the SI-Results
	 * tuples (from the process run in PRO-4.4.4-03) that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForWebSiteAuthentication".
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @throws TSLQualificationEvalProcessException
	 */
	private void procSettingsCheck3SetOfQE(ResultQualifiedCertificate resultQC, ResultServiceInformation resultSI,
			X509Certificate cert) throws TSLQualificationEvalProcessException {
		// PRO-4.4.4-27
		for (SIResult si : resultSI.getSiResults()) {
			if (si.isAsiForWSA()) {
				obtainQualificationsCheck3(cert, si, resultQC);
			}
		}

	}

	/**
	 * Method that obtain the set of qualifiers whose 'CriteriaList' element
	 * identifies CERT of the SI-Result that include an
	 * 'additionalServiceInformation' extension (see clause 5.5.9.4 of ETSI TS
	 * 119 612 [1]) having the value
	 * "http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForWebSiteAuthentication".
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param si
	 * @param resultQC
	 * @throws TSLQualificationEvalProcessException
	 */
	private void obtainQualificationsCheck3(X509Certificate cert, SIResult si, ResultQualifiedCertificate resultQC)
			throws TSLQualificationEvalProcessException {
		List<IAnyTypeExtension> extensionsList = si.getSiAtDateTime().getServiceInformationExtensions();
		if (extensionsList != null && !extensionsList.isEmpty()) {
			// Recorremos la lista buscando el elemento Qualifications.
			for (IAnyTypeExtension extension : extensionsList) {
				// Si es del tipo Qualifications...
				if (extension.getImplementationExtension() == TSLBuilderConstants.IMPL_QUALIFICATIONS) {
					// Obtenemos el objeto Qualifications Extension.
					Qualifications qualificationsExtension = (Qualifications) extension;
					try {
						// Iniciamos la comprobación según los criteria.
						List<QualificationElement> listQE = getQualificationsExtensionsDetectCert(cert,
								qualificationsExtension);
						resultQC.getCheck3ListOfQE().addAll(listQE);

					} catch (TSLQualificationEvalProcessException e) {
						// Si la extensión es crítica, propagamos la
						// excepción.
						if (qualificationsExtension.isCritical()) {
							throw e;
						} else {
							// Al no ser crítica, simplemente lo
							// notificamos con
							// un warn y continuamos.
							LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL251));
						}
					}
				}
			}
		}

		if (!resultQC.getCheck3ListOfQE().isEmpty()) {
			for (QualificationElement qe : resultQC.getCheck3ListOfQE()) {
				// Si hay algún qualifier...
				if (qe.isThereSomeQualifierUri()) {
					for (URI qualifierUri : qe.getQualifiersList()) {
						analyzeQuelifier(resultQC.getInfoQcResult().getQualifierCheck3(), qualifierUri.toString());
					}
				}
			}
		}

	}

	/**
	 * Method to obtain the extensions included in the certificate being
	 * validated.
	 * 
	 * @param tslCertExtAnalyzer
	 *            TSL Certificate Extension Analyzer with the certificate to
	 *            check.
	 * @return CertificateExtension Object that represent information of
	 *         certificate.
	 */
	protected abstract CertificateExtension checkAndAnalyzerExtensionCert(
			TSLCertificateExtensionAnalyzer tslCertExtAnalyzer);

	/**
	 * Method checks if there is an inconsistency between the certificate and
	 * the TSL.
	 * 
	 * @param resultSI
	 *            Object that represents the result obtained to obtain the list
	 *            of TSPServices that identify the certificate.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return true, if exist an inconsistency between the certificate and the
	 *         TSL.
	 */
	private boolean checkErrorTSPNameInconsistency(ResultServiceInformation resultSI, X509Certificate cert) {
		boolean error = Boolean.FALSE;
		LOGGER.debug(Language.getResCoreTsl(CoreTslMessages.LOGMTSL390));
		WrapperX509Cert wrapperX509cert;
		try {
			wrapperX509cert = new WrapperX509Cert(cert);

			String organizationName = wrapperX509cert.getOrganizationNameCertificate();
			String commonName = wrapperX509cert.getCommonNameIssuer();

			// PRO-4.4.4-06 a)
			if (!UtilsStringChar.isNullOrEmpty(organizationName)) {
				// si existe el atributo OrganizationCertificate en el campo
				// 'issuerName' se obtiene la lista de TSPNames de SI-Results
				if (!UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPNames(),
						organizationName)
						&& !UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPTradeNames(),
								organizationName)
						&& !UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPNamesCountry(),
								organizationName)) {
					error = Boolean.TRUE;

				}

			} else {
				// PRO-4.4.4-06 b)
				if (!verifyIssuerWithTSPNameorTSPTradeName(resultSI, commonName)) {
					error = Boolean.TRUE;
				}
			}
		} catch (TSLCertificateValidationException e) {
			LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL358, new Object[] { e.getMessage() }));
		}
		return error;
	}

	/**
	 * Method to verify that the legal or physical person issuing the
	 * certificate is identified by one of the TSPName or TSPTradeNames
	 * obtained.
	 * 
	 * @param resultSI
	 *            Object that represents the result obtained to obtain the list
	 *            of TSPServices that identify the certificate.
	 * @param commonName
	 *            Common name of certificate.
	 * @return true, if that the legal or physical person issuing the
	 *         certificate is identified by one of the TSPName or TSPTradeNames.
	 */
	private boolean verifyIssuerWithTSPNameorTSPTradeName(ResultServiceInformation resultSI, String commonName) {
		boolean verify = Boolean.FALSE;
		if (!UtilsStringChar.isNullOrEmpty(commonName)) {

			if ((UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPNames(), commonName)
					|| UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPTradeNames(),
							commonName)
					|| UtilsStringChar.listContainingString(resultSI.getInfoSIResult().getListTSPNamesCountry(),
							commonName))) {
				verify = Boolean.TRUE;
			}

		}

		return verify;

	}

	/**
	 * Method that executes the procedure 4.3. Obtaining listed services
	 * matching a certificate of ETSI TS 119 615 v.1.1.1. to obtain the
	 * qualification of the certificate
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param tspList
	 *            List of TrustServiceProvider.
	 * @param isDateIssue
	 *            Flag taht indicates if the validation is being done using the
	 *            date of issue of the certificate.
	 */
	private void procListedServiceMachingCertificate(ResultServiceInformation resultSI, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, Date validationDate, List<TrustServiceProvider> tspList,
			boolean isDateIssue) {
		LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL385));
		obtainListServicesMatchingCertificate(resultSI, cert, isCACert, isTsaCertificate, validationDate, tspList);

		// indicamos en el log resultado del proceso.
		if (resultSI.getSiResults().size() != 0) {
			LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL386,
					new Object[] { resultSI.getSiStatus(), resultSI.getSiResults().size() }));
		}

		if (!isDateIssue && resultSI.getSiStatus().equals(TSLStatusConstants.PROCESS_PASSED)
				&& resultSI.getSiResults().isEmpty()) {
			// si el proceso no ha fallado pero no se ha encontrado tspServices
			// y estamos en la primera vuelta del proceso, donde se valida con
			// la fecha de validación y no la fecha de emisión,
			// intentamos obtener el emisor del certificado y volvemos a
			// realizar la
			// búsqueda.
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL387));

			// comprobamos con su emisor
			X509Certificate issuerCert = getX509CertificateIssuer(cert);

			// si se ha encontrado, se comprueba si es raíz.
			if (issuerCert != null && !UtilsCertificate.isSelfSigned(issuerCert)) {
				// Si no es raíz volvemos a lanzar la búsqueda pasando el
				// emisor.
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL392));
				ResultServiceInformation resultSIIssuer = new ResultServiceInformation();

				procListedServiceMachingCertificate(resultSIIssuer, issuerCert, isCACert, isTsaCertificate,
						validationDate, tspList, isDateIssue);

				if (resultSIIssuer.getSiStatus().equals(TSLStatusConstants.PROCESS_PASSED)
						&& !resultSIIssuer.getSiResults().isEmpty()) {
					resultSI.removeAllData();
					resultSI.setSiResults(resultSIIssuer.getSiResults());
					resultSI.setSiStatus(resultSIIssuer.getSiStatus());
					resultSI.setSiSubStatus(resultSIIssuer.getSiSubStatus());
					resultSI.setInfoSIResult(resultSIIssuer.getInfoSIResult());
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL375));

				}

			} else if (issuerCert != null && UtilsCertificate.isSelfSigned(issuerCert)) {
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL389));
			}

		}

		// se obtiene SI-SubStatus
		procObtainSiSubStatus(resultSI);

	}

	/**
	 * Method to obtain the value of list of indications supplementing SI-Status
	 * indication of the process, after executing procedure 3.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procObtainSiSubStatus(ResultServiceInformation resultSI) {
		// PRO-4.3.4-05
		procSiSubStatusForESIGIdentical(resultSI);
		// PRO-4.3.4-06
		procSiSubStatusForESIGNoIdentical(resultSI);
		// PRO-4.3.4-07
		procSiSubStatusForESEALIdentical(resultSI);
		// PRO-4.3.4-08
		procSiSubStatusForESEALNoIdentical(resultSI);
		// PRO-4.3.4-09
		procSiSubStatusForWSAIdentical(resultSI);
		// PRO-4.3.4-10
		procSiSubStatusForWSANoIdentical(resultSI);
		// PRO-4.3.4-11
		procSiSubStatusAccordingTSPName(resultSI);

	}

	/**
	 * Method that checks if SISubStatus returns 'WARNING_T1_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForESIGIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-05
		int numESIG = 0;
		String statusESIG = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForESIG()) {
				if (numESIG == 0) {
					statusESIG = sr.getServiceStatus();
					numESIG++;
				} else {
					if (statusESIG.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_WARNING_T1_DUPLICATION);
						break;
					}
				}

			}
		}

	}

	/**
	 * Method that checks if SISubStatus returns 'ERROR_T1_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForESIGNoIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-06
		int numESIG = 0;
		String statusESIG = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForESIG()) {
				if (numESIG == 0) {
					statusESIG = sr.getServiceStatus();
					numESIG++;
				} else {
					if (!statusESIG.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_ERROR_T1_DUPLICATION);
						break;
					}
				}

			}
		}

	}

	/**
	 * Method that checks if SISubStatus returns 'WARNING_T2_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForESEALIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-07
		int numESeal = 0;
		String statusESeal = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForESeal()) {
				if (numESeal == 0) {
					statusESeal = sr.getServiceStatus();
					numESeal++;
				} else {
					if (statusESeal.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_WARNING_T2_DUPLICATION);
						break;
					}
				}

			}
		}

	}

	/**
	 * Method that checks if SISubStatus returns 'ERROR_T2_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForESEALNoIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-08
		int numESeal = 0;
		String statusESeal = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForESeal()) {
				if (numESeal == 0) {
					statusESeal = sr.getServiceStatus();
					numESeal++;
				} else {
					if (!statusESeal.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_ERROR_T2_DUPLICATION);
						break;
					}
				}

			}
		}

	}

	/**
	 * Method that checks if SISubStatus returns 'WARNING_T3_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForWSAIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-09
		int numWSA = 0;
		String statusWSA = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForWSA()) {
				if (numWSA == 0) {
					statusWSA = sr.getServiceStatus();
					numWSA++;
				} else {
					if (statusWSA.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_WARNING_T3_DUPLICATION);
						break;
					}
				}

			}
		}

	}

	/**
	 * Method that checks if SISubStatus returns 'ERROR_T3_DUPLICATION' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusForWSANoIdentical(ResultServiceInformation resultSI) {
		// PRO-4.3.4-10
		int numWSA = 0;
		String statusWSA = null;
		for (SIResult sr : resultSI.getSiResults()) {
			if (sr.isAsiForWSA()) {
				if (numWSA == 0) {
					statusWSA = sr.getServiceStatus();
					numWSA++;
				} else {
					if (!statusWSA.equalsIgnoreCase(sr.getServiceStatus())) {
						resultSI.getSiSubStatus().add(TSLStatusConstants.SI_ERROR_T3_DUPLICATION);
						break;
					}
				}

			}
		}
	}

	/**
	 * Method that checks if SISubStatus returns 'ERROR_TSP_CONFLICT' error.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 */
	private void procSiSubStatusAccordingTSPName(ResultServiceInformation resultSI) {
		// PRO-4.3.4-11
		if (resultSI.getInfoSIResult().getListTSPNames() != null
				&& resultSI.getInfoSIResult().getListTSPNames().size() > 1) {
			resultSI.getSiSubStatus().add(TSLStatusConstants.SI_ERROR_TSP_CONFLICT);
			resultSI.setSiStatus(TSLStatusConstants.PROCESS_FAILED);
		}

	}

	/**
	 * Method that goes through the list of TSPServices to obtain those that use
	 * the certificate.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param tspList
	 *            List of TrustServiceProvider.
	 * @param validationResult
	 *            Object where stores the validation result data.
	 */
	private void obtainListServicesMatchingCertificate(ResultServiceInformation resultSI, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, Date validationDate, List<TrustServiceProvider> tspList) {

		// obtenemos todos aquellos servicios que cumplan que sean QC y entre
		// sus identidades digitales se encuentre el certificado o alguno de los
		// certificados de su cadena de certificación
		// si la lista no es nula ni vacía...
		// La vamos recorriendo mientras no se termine y no se haya
		// detectado el certificado.
		LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL361));
		// obtenemos el pais del certificado necesario en el procc 4.4.4-06
		String countryCert = UtilsCertificate.getCountryOfTheCertificateString(cert);

		for (int index = 0; index < tspList.size(); index++) {

			// Almacenamos en una variable el TSP a tratar.
			TrustServiceProvider tsp = tspList.get(index);

			// Comprobamos si detectamos el certificado con los
			// servicios del TSP.
			try {
				// PRO-4.3.4-03
				searchListServicesMatchingCertificate(resultSI, cert, isCACert, isTsaCertificate, validationDate, tsp,
						countryCert);

			} catch (Exception e) {
				LOGGER.error(
						Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL362, new Object[] { e.getMessage() }));
				resultSI.setSiStatus(TSLStatusConstants.PROCESS_FAILED);
				break;
			}
		}

	}

	/**
	 * Method that searches for those TSPServices that identify the certificate.
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param tsp
	 * @param validationResult
	 *            Object where stores the validation result data.
	 */
	private void searchListServicesMatchingCertificate(ResultServiceInformation resultSI, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, Date validationDate, TrustServiceProvider tsp,
			String countryCert) {
		// Obtenemos la lista de servicios.
		List<TSPService> tspServiceList = tsp.getAllTSPServices();
		resultSI.setSiStatus(TSLStatusConstants.PROCESS_PASSED);

		// Si la lista no es nula ni vacía...
		if (tspServiceList != null && !tspServiceList.isEmpty()) {
			// se obtiene el país del certificado

			// La vamos recorriendo mientras no se termine
			for (int index = 0; index < tspServiceList.size(); index++) {

				// Almacenamos en una variable el servicio a analizar en esta
				// vuelta.
				TSPService tspService = tspServiceList.get(index);
				ServiceInformation si = tspService.getServiceInformation();
				String tspServiceType = si.getServiceTypeIdentifier().toString();

				// PRO-4.3.4-03 i).
				if (checkIfTSPServiceTypeIsCAQC(tspServiceType)) {
					// PRO-4.3.4-03 ii).
					if (checkIfDigitalIdentitiesMatchesCertificate(si.getAllDigitalIdentities(), cert, resultSI)) {

						SIResult siResult = new SIResult();

						// PRO-4.3.4-03 b)
						selectSiAtDateTime(siResult, tspService, validationDate);

						if (siResult.isError()) {
							// PROC-4.3.4-03 b) 3) i) ii)
							resultSI.setSiStatus(TSLStatusConstants.PROCESS_FAILED);
							break;

						} else if (siResult.getSiAtDateTime() == null) {
							LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL403,
									new Object[] { validationDate }));
						} else {

							// se continúa con el proceso.
							// PRO-4.3.4-03 a)
							siResult.setSiFull(si);
							siResult.setTspService(tspService);
							siResult.setServiceStatus(si.getServiceStatus().toString());
							String tspName = getTSPName(tsp);
							String tspNameCountry = getTSPNameCountry(tsp, countryCert);
							LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL371,
									new Object[] { tspName }));
							siResult.setTspName(tspName);
							siResult.setTspNameCountry(tspNameCountry);
							siResult.setListTspTradeName(getTSPTradeName(tsp));
							siResult.setTspDetected(tsp);

							// se obtiene informacion sobre las extensiones
							// del
							// TSPService que harán falta para obtener el
							// valor
							// de siSubStatus.
							getTSPServiceAdditionalServiceInformationExtensionsDetectCert(siResult);
							updateResultSI(resultSI, siResult);
							resultSI.setSiStatus(TSLStatusConstants.PROCESS_PASSED);
						}

					}

				} else if (isTsaCertificate && checkIfTSPServiceTypeIsTSAQualified(tspServiceType)) {
					// se comprueba si está entre los TSPService de sello de
					// tiempo.
					if (checkIfDigitalIdentitiesMatchesCertificate(si.getAllDigitalIdentities(), cert, resultSI)) {
						// Si se ha encontrado, lo indicamos en el log.

						// LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL212));

						// se ha detectado el certificado en un TSPService de
						// sello de tiempo, se guarda la información para
						// indicar que es reconocido por la TSL.
						SIResult siResultTSA = new SIResult();
						selectSiAtDateTime(siResultTSA, tspService, validationDate);

						if (siResultTSA.getSiAtDateTime() != null) {
							siResultTSA.setServiceTypeIsTSAQualified(Boolean.TRUE);
							siResultTSA.setTspService(tspService);
							String tspName = getTSPName(tsp);
							String tspNameCountry = getTSPNameCountry(tsp, countryCert);
							siResultTSA.setTspName(tspName);
							siResultTSA.setTspNameCountry(tspNameCountry);
							siResultTSA.setListTspTradeName(getTSPTradeName(tsp));
							siResultTSA.setTspDetected(tsp);
							updateResultSI(resultSI, siResultTSA);
						}
					}
				}
			}

		}

	}

	/**
	 * Checks if some of the input CA identities detect the input X509v3
	 * certificate and then set its information on the result.
	 * 
	 * @param digitalIdentitiesList
	 *            List of CA digital identities.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @return <code>true</code> if the certificate is issued by some of the
	 *         input identities, otherwise <code>false</code>.
	 */
	private boolean checkIfDigitalIdentitiesMatchesCertificate(List<DigitalID> digitalIdentitiesList,
			X509Certificate cert, ResultServiceInformation resutlSI) {
		// Por defecto consideramos que no coincide con ninguna identidad,
		// y a la primera identidad que coincida, se le cambia el resultado.
		boolean result = false;

		// Si la lista de identidades no es nula ni vacía...
		if (digitalIdentitiesList != null && !digitalIdentitiesList.isEmpty()) {

			// Creamos el procesador de identidades digitales.
			DigitalIdentitiesProcessor dip = new DigitalIdentitiesProcessor(digitalIdentitiesList);
			// Procesamos el certificado a validar y modificamos el resultado si
			// fuera necesario.

			result = dip.checkIfDigitalIdentitiesMatchesCertificate(cert);
			// si no se encuentra el certificado, se comprueba si está el emisor
			// del mismo.
			if (!result) {
				result = dip.checkIfCertificateIsIssuedBySomeIdentity(cert, resutlSI);
			}

		}
		return result;

	}

	/**
	 * Method that updates the value of Result Service Information
	 * 
	 * @param resultSI
	 *            Result obtained when executing the procedure 4.3.Obtaining
	 *            listed services matching a certificate of ETSI TS 119 615
	 *            v.1.1.1.
	 * @param siResult
	 *            Information obtained from each TSPService that identifies the
	 *            certificate.
	 */
	private void updateResultSI(ResultServiceInformation resultSI, SIResult siResult) {
		// LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL372));

		if (!siResult.isServiceTypeIsTSAQualified()) {
			resultSI.getSiResults().add(siResult);
			resultSI.getInfoSIResult().getListSiAtDateTime().add(siResult.getSiAtDateTime());
			if (!resultSI.getInfoSIResult().getListTSPNames().contains(siResult.getTspName())) {
				resultSI.getInfoSIResult().getListTSPNames().add(siResult.getTspName());
			}

			if (!resultSI.getInfoSIResult().getListTSPNamesCountry().contains(siResult.getTspNameCountry())) {
				resultSI.getInfoSIResult().getListTSPNamesCountry().add(siResult.getTspNameCountry());
			}

			for (String tspTradeName : siResult.getListTspTradeName()) {
				if (!resultSI.getInfoSIResult().getListTSPTradeNames().contains(tspTradeName)) {
					resultSI.getInfoSIResult().getListTSPTradeNames().add(tspTradeName);
				}
			}

		} else {
			resultSI.getInfoSIResult().setSiResultTSA(siResult);
		}

	}

	/**
	 * Checks if the certificate is detected by the differents Additional
	 * Service Extension of the input TSP Service.
	 * 
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param shi
	 *            Trust Service Provider Service History-Information to use for
	 *            detect the status of the input certificate.
	 * @return <code>null</code> if there is not any
	 *         AdditionalServiceInformation Extension defined,
	 *         {@link Boolean#TRUE} if the certificate has the extensions that
	 *         matches with the defined AdditionalService Extension values,
	 *         otherwise {@link Boolean#FALSE}.
	 */
	protected abstract void getTSPServiceAdditionalServiceInformationExtensionsDetectCert(SIResult siResult);

	/**
	 * Auxiliar method to extract a TSP trade name from the TSP provider.
	 * 
	 * @param tsp
	 *            TSP provider from which extracts the name.
	 * @return TSP Trade name from the TSP provider.
	 */
	private List<String> getTSPTradeName(TrustServiceProvider tsp) {

		List<String> result = new ArrayList<String>();
		// Los vamos recorriendo...
		Map<String, List<String>> tradeNamesMap = tsp.getTspInformation().getAllTSPTradeNames();
		// Recuperamos el correspondiente al idioma inglés por defecto.
		List<String> tradeNamesList = tradeNamesMap.get(Locale.UK.getLanguage());
		for (String tradeName : tradeNamesList) {

			// Si no es una cadena vacía, es válido.
			boolean validTradeName = !UtilsStringChar.isNullOrEmptyTrim(tradeName);

			// Si es válido y no hemos encontrado aún uno con la
			// estructura del identificador oficial,
			// lo comprobamos.
			if (validTradeName) {
				result.add(tradeName);
			}

		}
		return result;
	}

	/**
	 * Method to get the object representing the XML section corresponding to
	 * the SI-at-Date-time element defined as an XML section corresponding to
	 * the Service Information (Current)' element related to date and time.
	 * 
	 * @param siResult
	 *            Information obtained from each TSPService that identifies the
	 *            certificate.
	 * @param tspService
	 *            TSPService that identifies the certificate.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 */
	private void selectSiAtDateTime(SIResult siResult, TSPService tspService, Date validationDate) {
		// PRO-4.3.4-03 b)
		boolean error = false;
		ServiceHistoryInstance shi = null;
		boolean isHistoricServiceInf = false;
		if (tspService != null) {
			// PRO-4.3.4-03 b) 1)
			if (!tspService.isThereSomeServiceHistory() || (tspService.isThereSomeServiceHistory()
					&& tspService.getServiceInformation().getServiceStatusStartingTime().before(validationDate))) {
				shi = tspService.getServiceInformation();
			} else {
				// PRO-4.3.4-03 b) 2)
				if (tspService.isThereSomeServiceHistory()) {
					// se verifica si la lista está ordenada
					List<ServiceHistoryInstance> shiList = tspService.getAllServiceHistory();
					// PRO-4.3.4-03 c) 3)
					if (verifyAllServiceHistory(shiList)) {
						for (ServiceHistoryInstance shiFromList : shiList) {
							if (shiFromList.getServiceStatusStartingTime().before(validationDate)) {
								if (shiFromList.isServiceValidAndUsable()) {
									shi = shiFromList;
									isHistoricServiceInf = true;
								}
								break;
							}
						}
					} else {
						error = true;
						// ha fallado la verificación de comprobar que los shi
						// están ordenados y se acaba el proceso.//PROC-4.3.4-03
						// b) 3) i) ii)
						String tspName = tspService.getServiceInformation()
								.getServiceNameInLanguage(Locale.UK.getLanguage());
						LOGGER.error(
								Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL369, new Object[] { tspName }));
						siResult.setError(Boolean.TRUE);
					}

				}

			}
		}
		if (!error) {
			siResult.setSiAtDateTime(shi);
			siResult.setHistoricServiceInf(isHistoricServiceInf);
			siResult.setServiceStatus(TSLStatusConstants.PROCESS_PASSED);
		}

	}

	/**
	 * Method to verify that the 'Service history instance' elements are
	 * correctly ordered.
	 * 
	 * @param shiList
	 * @return
	 */
	private boolean verifyAllServiceHistory(List<ServiceHistoryInstance> shiList) {
		boolean verify = Boolean.TRUE;
		// PRO-4.3.4-03 b) 3)
		if (shiList.size() > 1) {
			Iterator<ServiceHistoryInstance> it = shiList.iterator();
			ServiceHistoryInstance current, previous = (ServiceHistoryInstance) it.next();
			while (it.hasNext()) {
				current = it.next();
				if (!current.getServiceStatusStartingTime().before(previous.getServiceStatusStartingTime())
						|| current.getServiceStatusStartingTime().equals(previous.getServiceStatusStartingTime())) {
					verify = Boolean.FALSE;
					break;
				}
				previous = current;
			}
		}
		return verify;
	}

	/**
	 * Shows in log the result of the validation.
	 * 
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param checkStatusRevocation
	 *            Flag that indicates if only try to detect the input
	 *            certificate (<code>false</code>) or also checks the revocation
	 *            status of this (<code>true</code>).
	 */
	private void showInLogResultOfValidation(TSLValidatorResult validationResult, boolean checkStatusRevocation) {

		// Si el certificado ha sido detectado...
		if (validationResult.getTSPServiceForDetect() != null) {

			String detectedWithShiMsg = UtilsStringChar.EMPTY_STRING;
			if (validationResult.getTSPServiceForDetect().getServiceInformation() == validationResult
					.getTSPServiceHistoryInformationInstanceForDetect()) {
				detectedWithShiMsg = Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL260,
						new Object[] { validationResult.getTSPServiceHistoryInformationInstanceNameForDetect(),
								validationResult.getTSPServiceHistoryInformationInstanceForDetect()
										.getServiceStatusStartingTime() });
			}

			// Lo analizamos en función de si se ha comprobado su estado de
			// revocación.
			// Si se desconoce el estado del certificado...
			if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {

				// Si había que validarlo...
				if (checkStatusRevocation) {

					// El certificado ha sido detectado pero no validado.
					LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL204,
							new Object[] { validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect(),
									detectedWithShiMsg }));
					// Lanzamos la alarma correspondiente...
					AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM001_UNKNOWN_REV_STATUS,
							Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM001_EVENT_000,
									new Object[] { validationResult.getTslCountryRegionCode(),
											validationResult.getTSPName(),
											validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg }));

				}
				// Si no había que validarlo, sino solo detectarlo.
				else {

					LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL228, new Object[] {
							validationResult.getTSPName(), validationResult.getTSPServiceNameForDetect() }));

				}

			}
			// Si el certificado ha sido detectado y validado...
			else {

				if (UtilsStringChar.isNullOrEmptyTrim(validationResult.getTSPServiceNameForValidate())) {

					// En función del resultado exacto...
					switch (validationResult.getResult()) {

					case ValidatorResultConstants.RESULT_DETECTED_STATE_VALID:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL206,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL207) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_REVOKED:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL206,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL208) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL206,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL209) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL206,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL210) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL206,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL211) }));
						break;

					default:
						break;

					}

				} else {

					String validatedWithShiMsg = UtilsStringChar.EMPTY_STRING;
					if (validationResult.getTSPServiceForValidate().getServiceInformation() == validationResult
							.getTSPServiceHistoryInformationInstanceForValidate()) {
						validatedWithShiMsg = Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL260,
								new Object[] {
										validationResult.getTSPServiceHistoryInformationInstanceNameForValidate(),
										validationResult.getTSPServiceHistoryInformationInstanceForValidate()
												.getServiceStatusStartingTime() });
					}

					// En función del resultado exacto...
					switch (validationResult.getResult()) {

					case ValidatorResultConstants.RESULT_DETECTED_STATE_VALID:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL205,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL207) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_REVOKED:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL205,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL208) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL205,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL209) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL205,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL210) }));
						break;

					case ValidatorResultConstants.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS:
						LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL205,
								new Object[] { validationResult.getTSPName(),
										validationResult.getTSPServiceNameForDetect(), detectedWithShiMsg,
										validationResult.getTSPServiceNameForValidate(), validatedWithShiMsg,
										Language.getResCoreTsl(CoreTslMessages.LOGMTSL211) }));
						break;

					default:
						break;

					}

				}

			}

		} else {

			// El certificado no ha sido detectado por la TSL.
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL203));

		}

	}

	/**
	 * Check if the Status Determination Approach of the TSL is set to
	 * Delinquent or equivalent.
	 * 
	 * @param statusDeterminationApproach
	 *            String that represents the Status Determination Approach to
	 *            check.
	 * @return <code>true</code> if the Status Determination Approach of the TSL
	 *         is set to Delinquent or equivalent, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(
			String statusDeterminationApproach);

	/**
	 * Method that assign the TSP and its name to the validation result.
	 * 
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param tsp
	 *            Trust Service Provider to use for validate the status of the
	 *            input certificate.
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
	 * 
	 * @param tsp
	 *            TSP provider from which extracts the name.
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
	 * Auxiliar method to extract a TSP name from the TSP provider.
	 * 
	 * @param tsp
	 *            TSP provider from which extracts the name.
	 * @return TSP name from the TSP provider.
	 */
	private String getTSPNameCountry(TrustServiceProvider tsp, String country) {

		String result = null;
		if (country != null) {
			// Verificamos que haya algún nombre asignado al TSP.
			if (tsp.getTspInformation().isThereSomeName()) {

				// Recuperamos el correspondiente al idioma inglés por defecto.
				List<String> tspNamesCountry = tsp.getTspInformation().getTSPNamesForLanguage(country.toLowerCase());

				// Si lo hemos obtenido, asignamos el nombre al resultado.
				if (tspNamesCountry != null && !tspNamesCountry.isEmpty()) {

					result = tspNamesCountry.get(0);

				}

			}
		}

		return result;

	}

	/**
	 * Checks if the input TSP Service Type is CA PKC.
	 * 
	 * @param tspServiceType
	 *            TSP Service type URI to check.
	 * @return <code>true</code> if it represents a CA PKC TSP Service,
	 *         otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCAPKC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is CA QC.
	 * 
	 * @param tspServiceType
	 *            TSP Service type URI to check.
	 * @return <code>true</code> if it represents a CA QC TSP Service, otherwise
	 *         <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCAQC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is National Root CA for Qualified
	 * Certificates.
	 * 
	 * @param tspServiceType
	 *            TSP Service type URI to check.
	 * @return <code>true</code> if it represents a National Root CA QC TSP
	 *         Service, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsNationalRootCAQC(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is for a qualified TSA.
	 * 
	 * @param tspServiceType
	 *            TSP Service type URI to check.
	 * @return <code>true</code> if it represents a Qualified TSA TSP Service,
	 *         otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsTSAQualified(String tspServiceType);

	/**
	 * Checks if the input TSP Service Type is for a non qualified TSA.
	 * 
	 * @param tspServiceType
	 *            TSP Service type URI to check.
	 * @return <code>true</code> if it represents a Non Qualified TSA TSP
	 *         Service, otherwise <code>false</code>.
	 */
	protected abstract boolean checkIfTSPServiceTypeIsTSANonQualified(String tspServiceType);

	/**
	 * Method that assign the TSP Service name used to detect the certificate to
	 * the validation result.
	 * 
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param tspService
	 *            TSP Service used for detect the input certificate.
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
	 * Method that assign the TSP Service name used to validate the certificate
	 * to the validation result.
	 * 
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param tspService
	 *            TSP Service used for validate the input certificate.
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
	 * Method that assign the TSP Service name used to detect the certificate to
	 * the validation result.
	 * 
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param shi
	 *            TSP Service History Information used for detect the input
	 *            certificate.
	 */
	private void assignTSPServiceHistoryInformationNameForDetectToResult(TSLValidatorResult validationResult,
			ServiceHistoryInstance shi) {

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
	 * Method that assign the TSP Service History Information used to validate
	 * the certificate to the validation result.
	 * 
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param shi
	 *            TSP Service History Information used for validate the input
	 *            certificate.
	 */
	private void assignTSPServiceHistoryInformationNameForValidateToResult(TSLValidatorResult validationResult,
			ServiceHistoryInstance shi) {

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
	 * Checks if the certificate is detected by the differents Additional
	 * Service Extension of the input TSP Service.
	 * 
	 * @param validationResult
	 *            Object where stores the validation result data.
	 * @param shi
	 *            Trust Service Provider Service History-Information to use for
	 *            detect the status of the input certificate.
	 * @return <code>null</code> if there is not any
	 *         AdditionalServiceInformation Extension defined,
	 *         {@link Boolean#TRUE} if the certificate has the extensions that
	 *         matches with the defined AdditionalService Extension values,
	 *         otherwise {@link Boolean#FALSE}.
	 */
	protected abstract Boolean checkIfTSPServiceAdditionalServiceInformationExtensionsDetectCert(
			TSLValidatorResult validationResult, ServiceHistoryInstance shi);

	/**
	 * Analyze the qualifier URI and set the mapping in the validation result
	 * object.
	 * 
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param qualifierUriString
	 *            Qualifier URI String to analyze.
	 */
	protected abstract void analyzeQualifierToSetMapping(TSLValidatorResult validationResult,
			String qualifierUriString);

	/**
	 * Checks (in function of the TSL Specification) if the input certificate
	 * obey the conditions to be detected without need the Qualifications
	 * Extension.
	 * 
	 * @param tslCertExtAnalyzer
	 *            TSL Certificate Extension Analyzer with the certificate to
	 *            check.
	 * @return <code>true</code> if the input certificate obey the conditions to
	 *         be detected without need the Qualifications Extension, otherwise
	 *         <code>false</code>.
	 */
	protected abstract boolean checkIfCertificateObeyWithConditionsToBeDetected(
			TSLCertificateExtensionAnalyzer tslCertExtAnalyzer);

	/**
	 * Checks if the input service status URI defines an OK status.
	 * 
	 * @param serviceStatus
	 *            Service Status URI string to check.
	 * @return <code>true</code> if represents an OK status, otherwise
	 *         <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceStatusIsOK(String serviceStatus);

	/**
	 * Sets the status result according to the service status.
	 * 
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param serviceStatus
	 *            TSP Service Status URI string to analyze.
	 * @param serviceStatusStartingTime
	 *            TSP Service Status starting time.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 */
	protected abstract void setStatusResultInAccordanceWithTSPServiceCurrentStatus(boolean isCACert,
			String serviceStatus, Date serviceStatusStartingTime, Date validationDate,
			TSLValidatorResult validationResult);

	/**
	 * Tries to validate the input certificate with the input Trust Service
	 * Provider Service information.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate to validate is
	 *            qualified or not.
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param tspService
	 *            Trust Service Provider Service to use for validate the status
	 *            of the input certificate.
	 */
	private void validateCertificateWithTSPService(X509Certificate cert, Date validationDate, boolean isCertQualified,
			TSLValidatorResult validationResult, TSPService tspService) {

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
				for (ServiceHistoryInstance shiFromList : shiList) {
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
			validateCertificateWithTSPServiceHistoryInstance(cert, validationDate, isCertQualified, validationResult,
					tspService, shi, isHistoricServiceInf);
		}

	}

	/**
	 * Tries to validate the input certificate with the input Trust Service
	 * Provider Service information.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate to validate is
	 *            qualified or not.
	 * @param validationResult
	 *            Object where is stored the validation result data.
	 * @param tspService
	 *            Trust Service Provider Service to use for validate the status
	 *            of the input certificate.
	 * @param shi
	 *            Trust Service Provider Service History Instance to use for
	 *            validate the status of the input certificate.
	 * @param isHistoricServiceInf
	 *            Flag that indicates if the input Service Information is from
	 *            an Historic Service (<code>true</code>) or not
	 *            (<code>false</code>).
	 */
	private void validateCertificateWithTSPServiceHistoryInstance(X509Certificate cert, Date validationDate,
			boolean isCertQualified, TSLValidatorResult validationResult, TSPService tspService,
			ServiceHistoryInstance shi, boolean isHistoricServiceInf) {

		// Comprobamos que el estado del servicio es OK,
		// y que su fecha de comienzo del estado es anterior
		// a la fecha de validación, si no, lo ignoramos.
		if (checkIfTSPServiceStatusIsOK(shi.getServiceStatus().toString())
				&& shi.getServiceStatusStartingTime().before(validationDate)) {

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

				tslValidatorMethod.validateCertificate(cert, validationDate, tspService, shi, isHistoricServiceInf,
						validationResult);

			}

		}

	}

	/**
	 * Checks if the service type is a CRL compatible type. It will be
	 * compatible when the certificate was qualified, and this service was for
	 * qualified certificates, or the certificate is not qualified, and the
	 * service is for not qualified.
	 * 
	 * @param shi
	 *            TSP Service History Information to analyze.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate is qualified
	 *            (<code>true</code>) or not (<code>false</code>).
	 * @return <code>true</code> if the Service type is CRL compatible with the
	 *         certificate to validate. otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsCRLCompatible(ServiceHistoryInstance shi, boolean isCertQualified);

	/**
	 * Checks if the service type is a OCSP compatible type. It will be
	 * compatible when the certificate was qualified, and this service was for
	 * qualified certificates, or the certificate is not qualified, and the
	 * service is for not qualified.
	 * 
	 * @param shi
	 *            TSP Service History Information to analyze.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate is qualified
	 *            (<code>true</code>) or not (<code>false</code>).
	 * @return <code>true</code> if the Service type is OCSP compatible with the
	 *         certificate to validate. otherwise <code>false</code>.
	 */
	public abstract boolean checkIfTSPServiceTypeIsOCSPCompatible(ServiceHistoryInstance shi, boolean isCertQualified);

	/**
	 * Auxiliar method to search if exists an Additional Service Information
	 * Extensions in the input TSP Service with the input URI.
	 * 
	 * @param tspService
	 *            TSP Service where to search the URI in the Additional Service
	 *            Information Extensions.
	 * @param uriToSearch
	 *            URI to search in the Additional Service Information
	 *            Extensions.
	 * @return <code>true</code> if the URI is defined, otherwise
	 *         <code>false</code>.
	 */
	protected final boolean existsAdditionalServiceInformationExtensionURIinService(TSPService tspService,
			String uriToSearch) {

		boolean result = false;

		// Si hay extensiones definidas...
		if (tspService.getServiceInformation().isThereSomeServiceInformationExtension()) {

			// Recuperamos las extensiones del servicio.
			List<IAnyTypeExtension> serviceExtensionsList = tspService.getServiceInformation()
					.getServiceInformationExtensions();

			// Las vamos recorriendo mientras no hayamos encontrado la URI.
			for (int index = 0; !result && index < serviceExtensionsList.size(); index++) {

				IAnyTypeExtension anyTypeExtension = serviceExtensionsList.get(index);
				if (anyTypeExtension
						.getImplementationExtension() == TSLBuilderConstants.IMPL_ADDITIONAL_SERVICE_INFORMATION) {

					AdditionalServiceInformation asi = (AdditionalServiceInformation) anyTypeExtension;
					result = uriToSearch.equals(asi.getUri().toString());

				}

			}

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidator#verifiesRevocationValuesForX509withTSL(java.lang.String,
	 *      java.security.cert.X509Certificate, boolean, boolean,
	 *      java.security.cert.X509CRL[],
	 *      org.bouncycastle.cert.ocsp.BasicOCSPResp[], java.util.Date)
	 */
	@Override
	public ITSLValidatorResult verifiesRevocationValuesForX509withTSL(String auditTransNumber, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, X509CRL[] crls, BasicOCSPResp[] ocsps, Date validationDate)
			throws TSLArgumentException, TSLValidationException {

		// Comprobamos que el certificado de entrada no sea nulo.
		if (cert == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL107));
		}

		// Comprobamos que la fecha de entrada no sea nula.
		if (validationDate == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL144));
		}

		// Comprobamos que haya al menos un valor de revocación.
		if ((crls == null || crls.length == 0) && (ocsps == null || ocsps.length == 0)) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL177));
		}

		BasicOCSPResp basicOcspResponse = null;
		// Si hay respuestas OCSP definidas...
		if (ocsps != null && ocsps.length > 0) {

			// Obtenemos la respuesta OCSP acorde al certificado a validar.
			try {
				basicOcspResponse = UtilsOCSP.getOCSPResponse(cert, ocsps, validationDate);
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL270));
			} catch (Exception e) {
				throw new TSLValidationException(ValetExceptionConstants.COD_187,
						Language.getResCoreTsl(CoreTslMessages.LOGMTSL178), e);
			}

		}

		X509CRL crlSelected = null;
		// Si hay CRLs definidas...
		if (crls != null && crls.length > 0) {

			// Obtenemos aquella que coincida con el emisor (o a algún elemento
			// de la cadena de certificación)y que sea acorde a la fecha
			// de validación o posterior.
			crlSelected = UtilsCRL.getCRLforCertificate(cert, crls, validationDate);
			if (crlSelected != null) {
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL271));
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
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL179));
		}

		// Comprobamos si el tipo de la TSL determina si se trata de una
		// lista
		// de listas...
		if (checkIfTSLisListOfLists(tsl.getSchemeInformation().getTslType().toString())) {

			// Si se trata de una lista de listas...
			searchRevocationValuesForCertificateAccordingTSLWithListOfLists(cert, isTsaCertificate, basicOcspResponse,
					crlSelected, validationDate, result);

		} else {

			// Si no es una lista de listas, continuamos con la búsqueda.
			searchRevocationValuesForCertificateAccordingTSL(auditTransNumber, cert, isCACert, isTsaCertificate,
					basicOcspResponse, crlSelected, validationDate, result);

		}

		return result;

	}

	/**
	 * Search if the input certificate is detected by the TSL and then, if some
	 * of the revocation values are valid to check the revocation status of the
	 * certificate.
	 * 
	 * @param cert
	 *            X509v3 certificate to analyze and that must be checked its
	 *            revocation status.
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param basicOcspResponse
	 *            Basic OCSP response to check if it is compatible with the TSL.
	 *            It can be <code>null</code>.
	 * @param crl
	 *            CRL to check if is compatible with the TSL to check the
	 *            revocation status of the certificate. It can be
	 *            <code>null</code>.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param validationResult
	 *            Object in which stores the validation result data.
	 */
	private void searchRevocationValuesForCertificateAccordingTSLWithListOfLists(X509Certificate cert,
			boolean isTsaCertificate, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate,
			TSLValidatorResult validationResult) {

		// TODO De momento no se consideran las listas de listas.
		// Si se trata de una lista de listas, la ignoramos y concluímos que no
		// se ha obtenido ningún valor de revocación válido.
		LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL219));

	}

	/**
	 * Search if the input certificate is detected by the TSL and then, if some
	 * of the revocation values are valid to check the revocation status of the
	 * certificate.
	 * 
	 * @param auditTransNumber
	 *            Audit transaction number.
	 * @param cert
	 *            X509v3 certificate to analyze and that must be checked its
	 *            revocation status.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param basicOcspResponse
	 *            Basic OCSP response to check if it is compatible with the TSL.
	 *            It can be <code>null</code>.
	 * @param crl
	 *            CRL to check if is compatible with the TSL to check the
	 *            revocation status of the certificate. It can be
	 *            <code>null</code>.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param validationResult
	 *            Object in which stores the validation result data.
	 * @throws TSLValidationException
	 *             In case of the TSL has an invalid status determination
	 *             approach.
	 */
	private void searchRevocationValuesForCertificateAccordingTSL(String auditTransNumber, X509Certificate cert,
			boolean isCACert, boolean isTsaCertificate, BasicOCSPResp basicOcspResponse, X509CRL crl,
			Date validationDate, TSLValidatorResult validationResult) throws TSLValidationException {

		// Comprobamos que el "Status Determination Approach" no sea
		// "delinquent" o equivalente.
		if (checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(
				tsl.getSchemeInformation().getStatusDeterminationApproach().toString())) {

			throw new TSLValidationException(ValetExceptionConstants.COD_187,
					Language.getResCoreTsl(CoreTslMessages.LOGMTSL108));

		} else {

			// Recuperamos la lista de TSP y se va analizando de uno en uno.
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL271));
			List<TrustServiceProvider> tspList = tsl.getTrustServiceProviderList();
			ResultQualifiedCertificate resultQC = new ResultQualifiedCertificate(cert);
			ResultQSCDDetermination resultQSCD = new ResultQSCDDetermination();
			try {
				validateCertificateETSI(cert, isCACert, isTsaCertificate, validationDate, false, tspList, resultQC,
						resultQSCD);

				if (resultQC.getInfoQcResult().isCertificateDetected()
						|| resultQC.getInfoQcResult().isTspServiceTSADetected()) {

					// detectado pero desconocido
					validationResult.setResult(ValidatorResultConstants.RESULT_DETECTED_STATE_UNKNOWN);

					ServiceHistoryInstance shiSelected = resultQC.getInfoQcResult().getShiSelected();
					if (shiSelected != null) {
						// Se establece el resultado según el estado.
						setStatusResultInAccordanceWithTSPServiceCurrentStatus(isCACert,
								shiSelected.getServiceStatus().toString(), shiSelected.getServiceStatusStartingTime(),
								validationDate, validationResult);
						// Guardamos la información del servicio histórico
						// usado.
						if (resultQC.getInfoQcResult().isHistoricServiceInf()) {
							assignTSPServiceHistoryInformationNameForDetectToResult(validationResult, shiSelected);
							validationResult.setTSPServiceHistoryInformationInstanceForDetect(shiSelected);
						}
					}

					TSPService tspServiceSelected = resultQC.getInfoQcResult().getTspServiceDetected();
					// Almacenamos el nombre del TSP Service usado.
					assignTSPServiceNameForDetectToResult(validationResult, tspServiceSelected);
					// Y el servicio.
					validationResult.setTSPServiceForDetect(tspServiceSelected);

					// se almacena la información del certificado emisor en el
					// resultado.
					assingIssuerCertificateToResult(validationResult, resultQC, cert);

					TrustServiceProvider tspSelected = resultQC.getInfoQcResult().getTspDetected();
					// Se almacena el nombre del TSP seleccionado.
					assignTSPandNameToResult(validationResult, tspSelected);

					// Auditoría: Certificado detectado.
					CommonsTslAuditTraces.addTslCertDetected(auditTransNumber, true,
							validationResult.getTslCountryRegionCode(), getTSPName(tspSelected),
							validationResult.getTSPServiceNameForDetect(),
							validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());

					// Si el estado no es desconocido, significa que ya se ha
					// determinado la validez del certificado,
					// por lo que asignamos el mismo nombre de servicio al
					// resultado de la validación (y el servicio).
					if (!validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
						LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL215));
						validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
						validationResult.setTSPServiceForValidate(tspServiceSelected);
						validationResult.setTspServiceHistoryInformationInstanceNameForValidate(
								validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
						validationResult.setTspServiceHistoryInformationInstanceForValidate(
								validationResult.getTSPServiceHistoryInformationInstanceForDetect());
						// Indicamos que se considera validado por el servicio
						// en auditoría.
						CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(),
								true, false, null, null, null, null);
					}

					// se actualiza los mapeos obtenidos.
					updateMappingValidationResult(validationResult, resultQC, resultQSCD);
				}

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
				LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL250));
				LOGGER.error(e.getMessage(), e.getException());
				// Limpiamos toda la información acumulada hasta el
				// momento.
				validationResult.resetAllData();
			}

			// Si el certificado ha sido detectado...
			if (validationResult.hasBeenDetectedTheCertificate()) {

				// Pero el estado es desconocido aún...
				if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {

					// Solo si tenemos una respuesta OCSP y/o una CRL,
					// procedemos a analizar
					// los valores de revocación en la TSL.
					if (basicOcspResponse != null || crl != null) {
						LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL280));
						// Comprobamos entre los distintos servicios CRL
						// y
						// OCSP del TSP, si alguno detecta
						// alguno de los valores de revocación.

						searchCompatibleRevocationValuesInTSP(auditTransNumber, cert, basicOcspResponse, crl,
								validationDate, validationResult, resultQC.getInfoQcResult().getTspDetected());

						// En función de si hemos validado el
						// certificado, escribimos en auditoría.
						if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()) {
							CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, false, null, null, null, null,
									null, null, null);
						} else {
							validationResult.setResultFromServiceStatus(Boolean.FALSE);
							validationResult.setResultFromDPorAIA(Boolean.FALSE);
							CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true,
									validationResult.getResult(), validationResult.isResultFromServiceStatus(),
									validationResult.isResultFromDPorAIA(), validationResult.getTslCountryRegionCode(),
									getTSPName(resultQC.getInfoQcResult().getTspDetected()),
									validationResult.getTSPServiceNameForValidate(),
									validationResult.getTSPServiceHistoryInformationInstanceNameForValidate());
						}

					}

				}
				// En caso contrario es que viene determinado por el
				// estado del servicio...
				else {
					validationResult.setResultFromServiceStatus(Boolean.TRUE);
					validationResult.setResultFromDPorAIA(Boolean.FALSE);
					CommonsTslAuditTraces.addTslCertValidated(auditTransNumber, true, validationResult.getResult(),
							validationResult.isResultFromServiceStatus(), validationResult.isResultFromDPorAIA(),
							validationResult.getTslCountryRegionCode(),
							getTSPName(resultQC.getInfoQcResult().getTspDetected()),
							validationResult.getTSPServiceNameForValidate(),
							validationResult.getTSPServiceHistoryInformationInstanceNameForValidate());

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
		showInLogResultOfValidation(validationResult, true);

	}

	/**
	 * Search in the input TSP some service that verifies some of the input
	 * revocation values.
	 * 
	 * @param auditTransNumber
	 *            Audit transaction number.
	 * @param cert
	 *            X509v3 certificate to check if it is detected by the input
	 *            TSP.
	 * @param basicOcspResponse
	 *            Basic OCSP response to check if it is compatible with the TSL.
	 *            It can be <code>null</code>.
	 * @param crl
	 *            CRL to check if is compatible with the TSL to check the
	 *            revocation status of the certificate. It can be
	 *            <code>null</code>.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param validationResult
	 *            Object in which is stored the validation result data.
	 * @param tsp
	 *            Trust Service Provider that must check if detect the input
	 *            certificate.
	 */
	private void searchCompatibleRevocationValuesInTSP(String auditTransNumber, X509Certificate cert,
			BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSLValidatorResult validationResult,
			TrustServiceProvider tsp) {

		// Primero comprobamos si el mismo emisor que identifica al certificado
		// es el firmante de la respuesta OCSP o de alguna de las CRL.
		// Si hemos obtenido el servicio con el que se detecta al certificado a
		// validar...
		if (validationResult.getTSPServiceForDetect() != null) {

			ServiceHistoryInstance shiForDetect = validationResult
					.getTSPServiceHistoryInformationInstanceForDetect() == null
							? validationResult.getTSPServiceForDetect().getServiceInformation()
							: validationResult.getTSPServiceHistoryInformationInstanceForDetect();

			ITSLValidatorThroughSomeMethod tslValidatorMethod = null;
			// Primero lo intentamos mediante respuestas OCSP (si las hay).
			if (basicOcspResponse != null) {
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL281));
				// Creamos el validador.
				tslValidatorMethod = new TSLValidatorThroughOCSP();
				// Ejecutamos la comprobación.
				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate,
						shiForDetect, validationResult);

			}

			// Si no hemos obtenido resultado, lo intentamos con las CRL,
			// si es que las hay.
			if (validationResult.getRevocationValueBasicOCSPResponse() == null && crl != null) {
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL282));
				// Creamos el validador.
				tslValidatorMethod = new TSLValidatorThroughCRL();
				// Ejecutamos la comprobación.
				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate,
						shiForDetect, validationResult);

			}

		}

		// En caso de haber encontrado el valor de revocación, podemos decir
		// que se ha usado el mismo servicio que para identificar el
		// certificado.
		if (validationResult.getRevocationValueBasicOCSPResponse() != null
				|| validationResult.getRevocationValueCRL() != null) {

			validationResult.setTSPServiceNameForValidate(validationResult.getTSPServiceNameForDetect());
			validationResult.setTSPServiceForValidate(validationResult.getTSPServiceForDetect());
			validationResult.setTspServiceHistoryInformationInstanceNameForValidate(
					validationResult.getTSPServiceHistoryInformationInstanceNameForDetect());
			validationResult.setTspServiceHistoryInformationInstanceForValidate(
					validationResult.getTSPServiceHistoryInformationInstanceForDetect());
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL220));

		}
		// Si no, hay que tratar de buscar un servicio dentro del mismo TSP que
		// pueda
		// validar los elementos de revocación.
		else {

			// Almacenamos en una variable si el certificado es cualificado o
			// no.
			boolean isCertQualified = validationResult.getMappingType() == ValidatorResultConstants.MAPPING_TYPE_QUALIFIED;

			// Obtenemos la lista de servicios.
			List<TSPService> tspServiceList = tsp.getAllTSPServices();

			// Recorremos la lista buscando servicios que permitan comprobar si
			// alguno
			// de los valores de revocación es compatible.
			// Seguimos intentándolo mientras los valores respuesta OCSP y CRL
			// sean
			// nulos.
			for (int index = 0; index < tspServiceList.size()
					&& validationResult.getRevocationValueBasicOCSPResponse() == null
					&& validationResult.getRevocationValueCRL() == null; index++) {

				// Almacenamos en una variable el servicio a analizar en
				// esta vuelta.
				TSPService tspService = tspServiceList.get(index);

				// Validamos el certificado con la información que haya en
				// el servicio TSP.
				searchCompatibleRevocationValuesInTSPService(cert, validationDate, isCertQualified, validationResult,
						tspService, basicOcspResponse, crl);

				// Si hemos encontrado un servicio que detecta alguno de los
				// valores de revocación,
				// asignamos el nombre del servicio que detecta dicho valor
				// (y el servicio).
				if (validationResult.getRevocationValueBasicOCSPResponse() != null
						|| validationResult.getRevocationValueCRL() != null) {
					assignTSPServiceNameForValidateToResult(validationResult, tspService);
					validationResult.setTSPServiceForValidate(tspService);
					if (validationResult.getTSPServiceHistoryInformationInstanceForValidate() != null) {
						assignTSPServiceHistoryInformationNameForValidateToResult(validationResult,
								validationResult.getTSPServiceHistoryInformationInstanceForValidate());
					}
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL220));
				}

			}

		}

		// Incluimos en auditoría las evidencias usadas.
		if (validationResult.getRevocationValueBasicOCSPResponse() != null) {
			CommonsCertificatesAuditTraces.addCertValidatedWithBasicOcspResponseTrace(auditTransNumber,
					EventsCollectorConstants.FIELD_VALUE_FROM_REQUEST,
					validationResult.getRevocationValueBasicOCSPResponse());
		} else if (validationResult.getRevocationValueCRL() != null) {
			CommonsCertificatesAuditTraces.addCertValidatedWithCRLTrace(auditTransNumber,
					EventsCollectorConstants.FIELD_VALUE_FROM_REQUEST, validationResult.getRevocationValueCRL());
		}

	}

	/**
	 * If the input TSP Service is OCSP or CRL compatible with the certificate
	 * to validate, then tries to verify with its information some of the input
	 * revocation values, and then, use it to check the revocation status of the
	 * certificate.
	 * 
	 * @param cert
	 *            X509v3 certificate to validate.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate to validate has been
	 *            detected.
	 * @param validationResult
	 *            Object in which is stored the validation result data.
	 * @param tspService
	 *            TSP Service to analyze.
	 * @param basicOcspResponse
	 *            Basic OCSP response to check if it is compatible with the TSL.
	 *            It can be <code>null</code>.
	 * @param crl
	 *            CRL to check if is compatible with the TSL to check the
	 *            revocation status of the certificate. It can be
	 *            <code>null</code>.
	 */
	private void searchCompatibleRevocationValuesInTSPService(X509Certificate cert, Date validationDate,
			boolean isCertQualified, TSLValidatorResult validationResult, TSPService tspService,
			BasicOCSPResp basicOcspResponse, X509CRL crl) {

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
				for (ServiceHistoryInstance shiFromList : shiList) {
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
			searchCompatibleRevocationValuesInTSPService(cert, validationDate, isCertQualified, validationResult, shi,
					basicOcspResponse, crl);
			// Si hemos detectado el servicio histórico que valida
			// los elementos de revocación, lo almacenamos.
			if (isHistoricServiceInf && validationResult.getRevocationValueBasicOCSPResponse() != null
					|| validationResult.getRevocationValueCRL() != null) {
				validationResult.setTspServiceHistoryInformationInstanceForValidate(shi);
			}

		}

	}

	/**
	 * If the input TSP Service is OCSP or CRL compatible with the certificate
	 * to validate, then tries to verify with its information some of the input
	 * revocation values, and then, use it to check the revocation status of the
	 * certificate.
	 * 
	 * @param cert
	 *            X509v3 certificate to validate.
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param isCertQualified
	 *            Flag that indicates if the certificate to validate has been
	 *            detected.
	 * @param validationResult
	 *            Object in which is stored the validation result data.
	 * @param shi
	 *            TSP Service History Information to analyze.
	 * @param basicOcspResponse
	 *            Basic OCSP response to check if it is compatible with the TSL.
	 *            It can be <code>null</code>.
	 * @param crl
	 *            CRL to check if is compatible with the TSL to check the
	 *            revocation status of the certificate. It can be
	 *            <code>null</code>.
	 */
	private void searchCompatibleRevocationValuesInTSPService(X509Certificate cert, Date validationDate,
			boolean isCertQualified, TSLValidatorResult validationResult, ServiceHistoryInstance shi,
			BasicOCSPResp basicOcspResponse, X509CRL crl) {

		// Comprobamos que el estado del servicio es OK,
		// y que su fecha de comienzo del estado es anterior
		// a la fecha de validación, si no, lo ignoramos.
		if (checkIfTSPServiceStatusIsOK(shi.getServiceStatus().toString())
				&& shi.getServiceStatusStartingTime().before(validationDate)) {

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

				tslValidatorMethod.searchRevocationValueCompatible(cert, basicOcspResponse, crl, validationDate, shi,
						validationResult);

			}

		}

	}

	/**
	 * Validates the certificate revocation status using the distribution points
	 * set on it. First try the OCSP distribution points, and then the CRL
	 * distribution points.
	 * 
	 * @param cert
	 *            X509v3 Certificate to validate.
	 * @param isCACert
	 *            Flag that indicates if the input certificate has the Basic
	 *            Constraints with the CA flag activated (<code>true</code>) or
	 *            not (<code>false</code>).
	 * @param isTsaCertificate
	 *            Flag that indicates if the input certificate has the
	 *            id-kp-timestamping key purpose (<code>true</code>) or not
	 *            (<code>false</code>).
	 * @param validationDate
	 *            Validation date to check the certificate status revocation.
	 * @param validationResult
	 *            Object in which is stored the validation result data.
	 * @param tsp
	 *            Trust Service Provider to use for checks the issuer of the
	 *            CRL/OCSP Response.
	 */
	private void validateCertificateUsingDistributionPoints(X509Certificate cert, boolean isCACert,
			boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult,
			TrustServiceProvider tsp) {

		// Creamos un validador mediante OCSP para analizar los distribution
		// points de este tipo (AIA).
		ITSLValidatorThroughSomeMethod tslValidatorMethod = new TSLValidatorThroughOCSP();
		boolean certValidated = tslValidatorMethod.validateCertificateUsingDistributionPoints(cert, isCACert,
				isTsaCertificate, validationDate, validationResult, tsp, this);

		// Si aún no se ha podido verificar el estado de revocación del
		// certificado, lo intentamos con los de tipo CRL.
		if (validationResult.hasBeenDetectedTheCertificateWithUnknownState()
				|| !certValidated && isCACert && !UtilsCertificate.isSelfSigned(cert)) {

			tslValidatorMethod = new TSLValidatorThroughCRL();
			tslValidatorMethod.validateCertificateUsingDistributionPoints(cert, isCACert, isTsaCertificate,
					validationDate, validationResult, tsp, this);

		}

	}

	/**
	 * Method to obtain the issuer certificate.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private X509Certificate getX509CertificateIssuer(X509Certificate cert) {
		X509Certificate issuerCert = null;

		// Se procede a obtener el certificado emisor a partir del atributo
		// SubjectAltName

		String issuerAltName = getIssuerAltName(cert);

		if (issuerAltName != null) {
			LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL394, new Object[] { issuerAltName }));

			issuerCert = getIssuerFromIssuerAltName(issuerAltName);

		}

		// si no se ha encontrado a través de la información del certificado
		if (issuerCert == null) {
			// se busca el emisor en el almacén de confianza del sistema
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL393));
			issuerCert = getX509CertificateIssuerKeystore(cert);

		}

		if (issuerCert == null && issuerAltName != null && !UtilsCertificate.isSelfSigned(issuerCert)) {
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL374));
			// Lanzamos la alarma correspondiente...
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM007_ERROR_GETTING_ISSUER_KEYSTORE,
					Language.getResCoreGeneral(CoreGeneralMessages.ALM007_EVENT_001));
		}

		return issuerCert;
	}

	/**
	 * Method that gets issuer alternive name.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private String getIssuerAltName(X509Certificate cert) {
		String result = null;
		try {
			WrapperX509Cert wrapperX509Cert = new WrapperX509Cert(cert);
			result = wrapperX509Cert.getIssuerAlternativeName();

		} catch (TSLCertificateValidationException e) {
			LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL377, new Object[] { e.getMessage() }));
		}
		return result;
	}

	/**
	 * Method to obtain the issuer certificate from the CA Trust Store.
	 * 
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @return
	 */
	private X509Certificate getX509CertificateIssuerKeystore(X509Certificate cert) {
		X509Certificate certIssuer = null;
		List<X509Certificate> listX509 = CertificateCacheManager.getListCertificateCA();
		if (listX509 != null && !listX509.isEmpty()) {
			try {
				certIssuer = UtilsCertificate.getIssuerCertificate(cert, listX509);

			} catch (CommonUtilsException e) {
				LOGGER.error(
						Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL376, new Object[] { e.getMessage() }));
			}
		}

		return certIssuer;
	}

	/**
	 * Method to download the issuer certificate
	 * 
	 * @param subjectAltName
	 * @return
	 * @throws TSLValidationException
	 */
	private X509Certificate getIssuerFromIssuerAltName(String issuerAltName) {
		boolean error = Boolean.FALSE;
		X509Certificate issuerCert = null;

		HttpGet httpGet = new HttpGet(issuerAltName);
		if (httpGet.getURI() == null || httpGet.getURI().getHost() == null) {
			error = Boolean.TRUE;
		} else {
			issuerCert = getCertificateFromHTTPURI(httpGet.getURI(), NumberConstants.NUM10000,
					NumberConstants.NUM10000);
			if (issuerCert == null) {
				error = Boolean.TRUE;
			}

		}
		if (error) {

			LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL373, new Object[] { issuerAltName }));
			// Lanzamos la alarma correspondiente...
			AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM006_ERROR_GETTING_CERT_SUBJECT_ALT_NAME,
					Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM006_EVENT_001,
							new Object[] { issuerAltName }));

		} else {
			// registramos el certificado en el sistema.issuerCert.getEncoded(),
			// alias, primero se consulta si es Raiz
			if (!UtilsCertificate.isSelfSigned(issuerCert)) {

				String alias = httpGet.getURI().getHost() + "_" + issuerCert.getSerialNumber().toString() + "_cer";
				try {

					ApplicationContextProvider.getApplicationContext().getBean(KeystoreServiceImpl.class)
							.saveCertificateKeystoreCA(issuerCert.getEncoded(), alias);

					// se registra el certificado y se lanza alarma.
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL378));
					// Lanzamos la alarma correspondiente...
					AlarmsManager.getInstance().registerAlarmEvent(AlarmIdConstants.ALM008_REGISTER_KEYSTORE_CA,
							Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM008_EVENT_001,
									new Object[] { alias }));
				} catch (BeansException | CertificateEncodingException e) {
					LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL384,
							new Object[] { alias, e.getMessage() }));

				} catch (KeyStoreException e) {
					LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL383));
				}
			}

		}

		return issuerCert;

	}

	/**
	 * Method that obtains the certificate from a URI passed by parameter.
	 * 
	 * @param uri
	 * @param readTimeout
	 * @param connectionTimeout
	 * @return
	 */
	public X509Certificate getCertificateFromHTTPURI(URI uri, int readTimeout, int connectionTimeout) {
		X509Certificate result = null;

		String httpUri = null;

		try {

			// Obtenemos la URI completa.
			httpUri = uri.toString();

			// Descargamos el certificadoen un array de bytes.
			byte[] buffer = UtilsHTTP.getDataFromURI(httpUri, connectionTimeout, readTimeout, null, null, null);

			if (buffer != null) {

				// Una vez leido el certificado creamos el objeto
				// X509Certificate.
				result = UtilsCertificate.getX509Certificate(buffer);
			}

		} catch (CommonUtilsException e) {

			LOGGER.warn(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL370, new Object[] { httpUri }));

		}
		return result;
	}

	/**
	 * Method to obtain the mapping obtained in the qualification process and
	 * obtaining the qscd of the certificate.
	 */
	public void updateMappingValidationResult(TSLValidatorResult result, ResultQualifiedCertificate resultQC,
			ResultQSCDDetermination resultQSCD) {
		// // Establecemos primero si el certificado es qualified o no.
		getMappingCertQualified(result, resultQC);
		// se establece la clasificación del certificado.l
		getMappingCertClassification(result, resultQC);
		// se establece el valor qscd
		getMappingQscd(result, resultQSCD);
	}

	/**
	 * Method to obtain the value obtained from qscd.
	 * 
	 * @param result
	 * @param resultQSCD
	 *            Result obtained when executing the procedure 4.5.QSCD
	 *            determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void getMappingQscd(TSLValidatorResult result, ResultQSCDDetermination resultQSCD) {

		// result.setMappingQSCD()
		if (resultQSCD != null && resultQSCD.getQscdResult() != null) {
			switch (resultQSCD.getQscdResult()) {
				case TSLStatusConstants.QSCD_YES:
					result.setMappingQSCD(ValidatorResultConstants.MAPPING_QSCD_YES);
					break;
				case TSLStatusConstants.QSCD_NO:
					result.setMappingQSCD(ValidatorResultConstants.MAPPING_QSCD_NO);
					break;
				case TSLStatusConstants.QSCD_INDETERMINATE:
					result.setMappingQSCD(ValidatorResultConstants.MAPPING_QSCD_UNKNOWN);
					break;
				default:
					// Se supone que todos los casos estan contemplados y no hay ninguno por defecto.
					break;
			}
		}
	}

	/**
	 * Method to obtain the value of 'classification'
	 * 
	 * @param result
	 * @param resultQC
	 */
	private void getMappingCertClassification(TSLValidatorResult result, ResultQualifiedCertificate resultQC) {
		// si es cualificado
		if (result.getMappingType() == ValidatorResultConstants.MAPPING_TYPE_QUALIFIED) {
			for (QCResult qc : resultQC.getQcResults()) {
				if (qc.equals(QCResult.QC_FOR_ESIG)) {
					result.setMappingClassification(ValidatorResultConstants.MAPPING_CLASSIFICATION_ESIG);
					break;
				}
				if (qc.equals(QCResult.QC_FOR_ESEAL)) {
					result.setMappingClassification(ValidatorResultConstants.MAPPING_CLASSIFICATION_ESEAL);
					break;
				}
				if (qc.equals(QCResult.QWAC)) {
					result.setMappingClassification(ValidatorResultConstants.MAPPING_CLASSIFICATION_WSA);
					break;
				}
			}
		} else {

			// si no es cualificado cerClassification es UNKOWN
			result.setMappingClassification(ValidatorResultConstants.MAPPING_CLASSIFICATION_OTHER_UNKNOWN);

		}
	}

	/**
	 * Method to establish if the certificate is qualified or not according to
	 * the result obtained.
	 * 
	 * @param result
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 */
	private void getMappingCertQualified(TSLValidatorResult result, ResultQualifiedCertificate resultQC) {
		boolean endProc = Boolean.FALSE;
		for (QCResult qc : resultQC.getQcResults()) {
			if (qc.equals(QCResult.NOT_QUALIFIED)) {
				result.setMappingType(ValidatorResultConstants.MAPPING_TYPE_NONQUALIFIED);
				result.setMappingETSIResult(qc.toString());
				endProc = Boolean.TRUE;
				break;
			}
			if (qc.equals(QCResult.QC_FOR_ESIG) || qc.equals(QCResult.QC_FOR_ESEAL) || qc.equals(QCResult.QWAC)) {
				result.setMappingType(ValidatorResultConstants.MAPPING_TYPE_QUALIFIED);
				result.setMappingETSIResult(qc.toString());
				endProc = Boolean.TRUE;
				break;
			}
			if (qc.equals(QCResult.INDETERMINATE)) {
				result.setMappingType(ValidatorResultConstants.MAPPING_TYPE_UNKNOWN);
				result.setMappingETSIResult(qc.toString());
				endProc = Boolean.TRUE;
				break;
			}
		}

		if (!endProc) {
			// Aún no se ha establecido si es cualificado o no, se sigue
			// consultando los resultados.
			if (resultQC.getQcResults().contains(QCResult.NOT_QUALIFIED_FOR_ESIG)
					&& resultQC.getQcResults().contains(QCResult.NOT_QUALIFIED_FOR_ESEAL)
					&& resultQC.getQcResults().contains(QCResult.NOT_QWAC)) {
				result.setMappingType(ValidatorResultConstants.MAPPING_TYPE_NONQUALIFIED);
				result.setMappingETSIResult(TslMappingConstants.MAPPING_VALUE_ETSI_RESULT_ALL_NQ);
			} else if (resultQC.getQcResults().contains(QCResult.INDET_QC_FOR_ESIG)
					&& resultQC.getQcResults().contains(QCResult.INDET_QC_FOR_ESEAL)
					&& resultQC.getQcResults().contains(QCResult.INDET_QWAC)) {
				result.setMappingType(ValidatorResultConstants.MAPPING_TYPE_UNKNOWN);
				result.setMappingETSIResult(TslMappingConstants.MAPPING_VALUE_ETSI_RESULT_ALL_INDET);
			}
		}

	}

	/**
	 * Method that gets the certificate issuer of the certificate and updates
	 * the result of the validation.
	 * 
	 * @param resultQC
	 *            Result obtained when executing the procedure 4.4.EU qualified
	 *            certificate determination of ETSI TS 119 615 v.1.1.1.
	 * @param cert
	 *            Certificate X509 v3 to validate.
	 * @param validationResult
	 *            Object where stores the validation result data.
	 */
	private void assingIssuerCertificateToResult(TSLValidatorResult validationResult,
			ResultQualifiedCertificate resultQC, X509Certificate cert) {
		if (resultQC.getInfoQcResult().getInfoCertificateIssuer() != null
				&& resultQC.getInfoQcResult().getInfoCertificateIssuer().getIssuerCert() != null) {
			validationResult.setIssuerCert(resultQC.getInfoQcResult().getInfoCertificateIssuer().getIssuerCert());
			validationResult
					.setIssuerPublicKey(resultQC.getInfoQcResult().getInfoCertificateIssuer().getIssuerPublicKey());
			validationResult
					.setIssuerSubjectName(resultQC.getInfoQcResult().getInfoCertificateIssuer().getIssuerSubjectName());
			validationResult
					.setIssuerSKIbytes(resultQC.getInfoQcResult().getInfoCertificateIssuer().getIssuerSKIbytes());
		} else {
			X509Certificate issuerCert = getX509CertificateIssuerKeystore(cert);
			if (issuerCert == null) {
				issuerCert = getX509CertificateIssuer(cert);
			}

			if (issuerCert != null) {
				validationResult.setIssuerCert(issuerCert);
				validationResult.setIssuerPublicKey(issuerCert.getPublicKey());
				try {
					validationResult.setIssuerSubjectName(UtilsCertificate.getCertificateId(issuerCert));
				} catch (CommonUtilsException e) {
					LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL182));
				}

				try {
					if (!UtilsCertificate.isSelfSigned(cert)) {
						SubjectKeyIdentifier ski = SubjectKeyIdentifier.fromExtensions(UtilsCertificate
								.getBouncyCastleCertificate(issuerCert).getTBSCertificate().getExtensions());
						validationResult.setIssuerSKIbytes(ski.getKeyIdentifier());
					}
				} catch (Exception e) {
					LOGGER.warn(Language.getResCoreTsl(CoreTslMessages.LOGMTSL183));
				}
			}
		}
	}
}
