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
 * <b>File:</b><p>es.gob.valet.rest.TslRestService.java.</p>
 * <b>Description:</b><p>Class that represents the TSL restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.4, 26/11/2018.
 */
package es.gob.valet.rest.services;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IRestGeneralMessages;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.rest.elements.CertDetectedInTSL;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.ResultTslInfVal;
import es.gob.valet.rest.elements.TslInformation;
import es.gob.valet.rest.elements.TslInformationResponse;
import es.gob.valet.rest.elements.TslRevocationStatus;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.impl.common.ATSLValidator;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Class that represents the statistics restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 26/11/2018.
 */
@Path("/tsl")
public class TslRestService implements ITslRestService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TslRestService.class);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#detectCertInTslInfoAndValidation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(@FormParam(PARAM_APPLICATION) final String application, @FormParam(PARAM_DELEGATED_APP) final String delegatedApp, @FormParam(PARAM_TSL_LOCATION) final String tslLocation, @FormParam(PARAM_CERTIFICATE) final String certificate, @FormParam(PARAM_DETECTION_DATE) final String detectionDate, @FormParam(PARAM_GET_INFO) final Boolean getInfo, @FormParam(PARAM_CHECK_REV_STATUS) final Boolean checkRevStatus, @FormParam(PARAM_RETURN_REV_EVID) final Boolean returnRevocationEvidence) throws ValetRestException {
		// CHECKSTYLE:ON

		// Indicamos la recepción del servicio junto con los parámetros de
		// entrada.
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG001, new Object[ ] { application, delegatedApp, tslLocation, certificate, detectionDate, getInfo, checkRevStatus, returnRevocationEvidence }));

		// Inicialmente consideramos que todo es OK para proceder.
		boolean allIsOk = true;

		// Creamos el objeto que representa la respuesta.
		DetectCertInTslInfoAndValidationResponse result = null;

		// Comprobamos los parámetros obligatorios de entrada.
		String resultCheckParams = checkParamsDetectCertInTslInfoAndValidationResponse(application, certificate, getInfo, checkRevStatus, returnRevocationEvidence);
		if (resultCheckParams != null) {
			allIsOk = false;
			LOGGER.error(resultCheckParams);
			result = new DetectCertInTslInfoAndValidationResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
			result.setDescription(resultCheckParams);
		}

		// Comprobamos que se parsea correctamente el certificado a detectar.
		X509Certificate x509cert = null;
		if (allIsOk) {
			try {
				x509cert = UtilsCertificate.getX509Certificate(Base64.decodeBase64(certificate));
			} catch (CommonUtilsException e) {
				allIsOk = false;
				LOGGER.error(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG012));
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG012));
			}
		}

		// El parámetro 'returnRevocationEvidence' solo puede ser true
		// si 'checkRevStatus' es también true.
		if (allIsOk && returnRevocationEvidence && !checkRevStatus) {
			allIsOk = false;
			LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG004));
			result = new DetectCertInTslInfoAndValidationResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
			result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG004));
		}

		// Comprobamos que el formato de la fecha sea adecuado,
		// si es que se proporciona.
		Date detectionDateAux = null;
		if (allIsOk) {
			if (!UtilsStringChar.isNullOrEmptyTrim(detectionDate)) {
				try {
					detectionDateAux = UtilsDate.transformDate(detectionDate.trim(), UtilsDate.FORMAT_DATE_TIME_STANDARD);
				} catch (ParseException e) {
					allIsOk = false;
					LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG006, new Object[ ] { detectionDate }));
					result = new DetectCertInTslInfoAndValidationResponse();
					result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
					result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG006, new Object[ ] { detectionDate }));
				}
			} else {
				detectionDateAux = Calendar.getInstance().getTime();
			}
		}

		// Si todo ha ido bien, continuamos con el proceso de ejecución del
		// servicio.
		if (allIsOk) {

			try {
				result = executeServiceDetectCertInTslInfoAndValidation(application, delegatedApp, tslLocation, x509cert, detectionDateAux, getInfo.booleanValue(), checkRevStatus.booleanValue(), returnRevocationEvidence);
			} catch (TSLManagingException e) {
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_EXECUTING_SERVICE);
				result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG009, new Object[ ] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }));
				LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG010, new Object[ ] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }), e);
			} catch (Exception e) {
				throw new ValetRestException(IValetException.COD_200, Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG011, new Object[ ] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }), e);
			}

		}

		return result;
	}

	/**
	 * Method that checks required parameters for {@link es.gob.valet.rest.services.TslRestService#detectCertInTslInfoAndValidation} method.
	 * @param application Application identifier.
	 * @param certificate certificate Certificate to detect (byte[] in Base64 encoded).
	 * @param getInfo Flag that indicates if it is necessary to get the certificate information in response.
	 * @param checkRevStatus Check revocation status Flag that indicates if it is necessary to check the revocation status of the input certificate.
	 * @param returnRevoEvid Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevocationStatus} is <code>true</code>).
	 * @return {@link String} with the parameter that not are correctly defined, otherwise <code>null</code>.
	 */
	private String checkParamsDetectCertInTslInfoAndValidationResponse(final String application, final String certificate, final Boolean getInfo, final Boolean checkRevStatus, final Boolean returnRevoEvid) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG003, new Object[ ] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }));
		boolean checkError = false;

		// Check received parameters
		if (UtilsStringChar.isNullOrEmptyTrim(application)) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_APPLICATION);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (UtilsStringChar.isNullOrEmptyTrim(certificate)) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_CERTIFICATE);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (getInfo == null) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_GET_INFO);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (checkRevStatus == null) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_CHECK_REV_STATUS);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		} else if (returnRevoEvid == null) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_RETURN_REV_EVID);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (checkError) {
			return result.toString();
		} else {
			return null;
		}

	}

	/**
	 * After check the input parameters, this method execute the service 'detectCertInTslInfoAndValidation'.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param x509cert X.509 certificate to detect.
	 * @param detectionDate Date to use to detect and validate the input certificate.
	 * @param getInfo Flag that indicates if it is necessary to get the certificate information in response.
	 * @param checkRevStatus Flag that indicates if it is necessary to check the revocation status of the input certificate.
	 * @param returnRevocationEvidence Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevStatus} is <code>true</code>).
	 * @return Structure of DetectCertInTslInfoAndValidationResponse.
	 * @throws TSLManagingException In case of some error detecting or validating the certificate with the TSL.
	 * @throws IOException In case of some error decoding a Basic OCSP Response.
	 * @throws CRLException Incase of some error decoding a CRL.
	 */
	private DetectCertInTslInfoAndValidationResponse executeServiceDetectCertInTslInfoAndValidation(String application, String delegatedApp, String tslLocation, X509Certificate x509cert, Date detectionDate, boolean getInfo, boolean checkRevStatus, Boolean returnRevocationEvidence) throws TSLManagingException, CRLException, IOException {

		// Inicializamos el resultado a devolver.
		DetectCertInTslInfoAndValidationResponse result = new DetectCertInTslInfoAndValidationResponse();

		// En función de si se ha especificado un TSLLocation o no, se intenta
		// detectar el certificado.
		ITSLValidatorResult tslValidatorResult = null;
		if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
			tslValidatorResult = TSLManager.getInstance().validateX509withTSL(x509cert, detectionDate, checkRevStatus, getInfo);
		} else {
			tslValidatorResult = TSLManager.getInstance().validateX509withTSL(x509cert, tslLocation, detectionDate, checkRevStatus, getInfo);
		}

		// Si el resultado es nulo, significa que no se ha encontrado TSL
		// para detectar el certificado.
		if (tslValidatorResult == null) {

			String msg = null;
			if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG013, UtilsCertificate.getIssuerCountryOfTheCertificateString(x509cert));
			} else {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG014, tslLocation);
			}
			LOGGER.info(msg);
			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_NOT_FINDED);
			result.setDescription(msg);

		} else {

			// Si el resultado no es nulo lo indicamos y empezamos a construir
			// la respuesta del servicio.
			String msg = null;
			if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG015, UtilsCertificate.getIssuerCountryOfTheCertificateString(x509cert));
			} else {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG016, tslLocation);
			}
			LOGGER.info(msg);
			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED);
			result.setDescription(msg);

			// Obtenemos el objeto de caché que representa la TSL.
			TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(tslValidatorResult.getTslCountryRegionCode());

			// Completamos los datos básicos en la respuesta.
			ResultTslInfVal resultTslInfVal = new ResultTslInfVal();
			TslInformation tslInformation = new TslInformation();
			tslInformation.setCountryRegion(tslValidatorResult.getTslCountryRegionCode());
			tslInformation.setSequenceNumber(tslValidatorResult.getTslSequenceNumber());
			tslInformation.setTslLocation(tsldco.getTslLocationUri());
			tslInformation.setIssued(tslValidatorResult.getTslIssueDate());
			tslInformation.setNextUpdate(tslValidatorResult.getTslNextUpdate());
			tslInformation.setTslXmlData(null);
			resultTslInfVal.setTslInformation(tslInformation);
			result.setResultTslInfVal(resultTslInfVal);

			// Si se ha solicitado información del certificado o comprobar el
			// estado de revocación...
			if (getInfo || checkRevStatus) {

				// Si no se ha detectado el certificado en la TSL...
				if (tslValidatorResult.getResult() == ITSLValidatorResult.RESULT_NOT_DETECTED) {

					// Lo marcamos en la respuesta.
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG017);
					LOGGER.info(msg);
					result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_NOT_DETECTED);
					result.setDescription(msg);

				}
				// Si se ha detectado el certificado en la TSL...
				else {

					// Lo marcamos en la respuesta.
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG018);
					LOGGER.info(msg);
					result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED);
					result.setDescription(msg);

					// Creamos el objeto que representa la información común
					// para ambos casos.
					CertDetectedInTSL certDetectedInTsl = new CertDetectedInTSL();
					certDetectedInTsl.setTspName(tslValidatorResult.getTSPName());
					certDetectedInTsl.setTspServiceName(tslValidatorResult.getTSPServiceNameForDetect());
					certDetectedInTsl.setTspServiceType(tslValidatorResult.getTSPServiceForDetect().getServiceInformation().getServiceTypeIdentifier().toString());
					certDetectedInTsl.setTspServiceStatus(tslValidatorResult.getTSPServiceForDetect().getServiceInformation().getServiceStatus().toString());

					// Si se ha solicitado obtener información del
					// certificado...
					if (getInfo) {
						Map<String, String> mappings = tslValidatorResult.getMappings();
						certDetectedInTsl.setCertInfo(mappings);
					}

					// Si se ha solicitado comprobar el estado de revocación del
					// certificado...
					if (checkRevStatus) {

						// Construimos el objeto que contendrá la información de
						// revocación.
						TslRevocationStatus tslRevocationStatus = new TslRevocationStatus();

						// Asignamos el resultado de comprobación de estado de
						// revocación.
						tslRevocationStatus.setRevocationStatus(tslValidatorResult.getResult());

						// En función del resultado (sabemos que ha sido
						// detectado)...
						switch (tslRevocationStatus.getRevocationStatus()) {
							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG021);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(null);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_VALID:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG022);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(Boolean.FALSE);
								addRevocationInfoInResult(tslRevocationStatus, tslValidatorResult);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG023);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(tslValidatorResult.getTSPServiceNameForDetect().equals(tslValidatorResult.getTSPServiceNameForValidate()));
								if (!tslRevocationStatus.getIsFromServStat()) {
									addRevocationInfoInResult(tslRevocationStatus, tslValidatorResult);
								}
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG024);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(Boolean.TRUE);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED_SERVICESTATUS:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG025);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(Boolean.TRUE);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID_SERVICESTATUS:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG026);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								tslRevocationStatus.setIsFromServStat(Boolean.TRUE);
								break;
							default:
								break;
						}

						// Añadimos toda la información de revocación en la
						// respuesta.
						certDetectedInTsl.setTslRevocStatus(tslRevocationStatus);

					}

					// Asignamos la información de detección del certificado a
					// la respuesta final.
					resultTslInfVal.setCertDetectedInTSL(certDetectedInTsl);

					// Establecemos el resultado general en función de si se ha
					// solicitado
					// información del certificado y/o su estado de revocación,
					// y lo que finalmente
					// se pudo obtener.
					setGeneralStatusResponseGetInfoRevocationStatus(result, getInfo, checkRevStatus);

				}

			}

		}

		return result;

	}

	/**
	 *
	 * @param result
	 * @param getInfo
	 * @param checkRevStatus
	 */
	private void setGeneralStatusResponseGetInfoRevocationStatus(DetectCertInTslInfoAndValidationResponse result, boolean getInfo, boolean checkRevStatus) {

		// Obtenemos el objeto que contiene la información de haber detectado
		// el certificado en la TSL.
		CertDetectedInTSL certDetectedInTsl = result.getResultTslInfVal().getCertDetectedInTSL();

		// Cadena donde se almacenará el mensaje descriptivo a asignar
		// finalmente.
		String msg = null;

		// Si se ha solicitado información del certificado...
		if (getInfo) {

			// Comprobamos si se ha obtenido la información solicitada.
			boolean infoCertObtained = certDetectedInTsl.getCertInfo() != null && !certDetectedInTsl.getCertInfo().isEmpty();

			// Si también se ha solicitado la información de revocación...
			if (checkRevStatus) {

				// Comprobamos si se ha obtenido la información de revocación.
				boolean revStatusInfoObtained = certDetectedInTsl.getTslRevocStatus().getRevocationStatus() != ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN;

				if (infoCertObtained) {

					if (revStatusInfoObtained) {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG032);
						result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_COLLECTED);
					} else {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG031);
						result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_NOT_COLLECTED);
					}

				} else {

					if (revStatusInfoObtained) {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG030);
						result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_COLLECTED);
					} else {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG029);
						result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_NOT_COLLECTED);
					}

				}

			}
			// Si entramos aquí significa que solo se solicitó la información
			// del certificado.
			else {

				if (infoCertObtained) {

					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG020);
					result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED);

				} else {

					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG019);
					result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED);

				}

			}

		}
		// Si no se ha solicitado información del certificado significa que al
		// menos
		// se ha solicitado la información de revocación.
		else {

			// Comprobamos si se ha obtenido la información de revocación.
			boolean revStatusInfoObtained = certDetectedInTsl.getTslRevocStatus().getRevocationStatus() != ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN;

			if (revStatusInfoObtained) {

				msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG027);
				result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_COLLECTED);

			} else {

				msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG028);
				result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_NOT_COLLECTED);

			}

		}

		// Lo asignamos al resultado y pintamos en el log.
		result.setDescription(msg);
		LOGGER.info(msg);

	}

	/**
	 * Add the revocation information in the result.
	 * @param tslRevocationStatus TSL revocation status information to return.
	 * @param tslValidatorResult TSL validation process result to analyze.
	 * @throws IOException In case of some error decoding a Basic OCSP Response.
	 * @throws CRLException Incase of some error decoding a CRL.
	 */
	private void addRevocationInfoInResult(TslRevocationStatus tslRevocationStatus, ITSLValidatorResult tslValidatorResult) throws IOException, CRLException {

		// Establecemos la URL de donde se haya obtenido la evidencia de
		// revocación.
		tslRevocationStatus.setUrl(tslValidatorResult.getRevocationValueURL());
		// Consultamos si se ha obtenido mediante el DistributionPoint / AIA del
		// certificado.
		tslRevocationStatus.setDpAia(ATSLValidator.TSP_SERVICE_NAME_FOR_DIST_POINT.equals(tslValidatorResult.getTSPServiceNameForValidate()));
		// Si no ha sido por el DP / AIA, es por un servicio...
		if (!tslRevocationStatus.getDpAia()) {
			tslRevocationStatus.setTspServiceName(tslValidatorResult.getTSPServiceNameForValidate());
			tslRevocationStatus.setTspServiceType(tslValidatorResult.getTSPServiceForValidate().getServiceInformation().getServiceTypeIdentifier().toString());
			tslRevocationStatus.setTspServiceStatus(tslValidatorResult.getTSPServiceForValidate().getServiceInformation().getServiceStatus().toString());
		}

		// En función del tipo de evidencia...
		// Si es OCSP...
		if (tslValidatorResult.getRevocationValueBasicOCSPResponse() != null) {
			tslRevocationStatus.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_OCSP);
			tslRevocationStatus.setEvidence(tslValidatorResult.getRevocationValueBasicOCSPResponse().getEncoded());
			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus().intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new UtilsDate(tslValidatorResult.getRevocationDate()).toString(UtilsDate.FORMAT_DATE_TIME_STANDARD));
			}
		}
		// Si es CRL...
		else if (tslValidatorResult.getRevocationValueCRL() != null) {
			tslRevocationStatus.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_CRL);
			tslRevocationStatus.setEvidence(tslValidatorResult.getRevocationValueCRL().getEncoded());
			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus().intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new UtilsDate(tslValidatorResult.getRevocationDate()).toString(UtilsDate.FORMAT_DATE_TIME_STANDARD));
			}
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#getTslInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TslInformationResponse getTslInformation(@FormParam(PARAM_APPLICATION) final String application, @FormParam(PARAM_DELEGATED_APP) final String delegatedApp, @FormParam(PARAM_COUNTRY_REGION_CODE) final String countryRegionCode, @FormParam(PARAM_TSL_LOCATION) final String tslLocation, @FormParam(PARAM_GET_TSL_XML_DATA) final Boolean getTslXmlData) throws ValetRestException {
		// CHECKSTYLE:ON

		// Indicamos la recepción del servicio junto con los parámetros de
		// entrada.
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG002, new Object[ ] { application, delegatedApp, countryRegionCode, tslLocation, getTslXmlData }));

		// Inicialmente consideramos que todo es OK para proceder.
		boolean allIsOk = true;

		// Creamos el objeto que representa la respuesta.
		TslInformationResponse result = null;

		// Comprobamos los parámetros obligatorios de entrada.
		String resultCheckParams = checkParamsGetTslInformation(application, getTslXmlData);
		if (resultCheckParams != null) {
			allIsOk = false;
			LOGGER.error(resultCheckParams);
			result = new TslInformationResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
			result.setDescription(resultCheckParams);
		}

		// Comprobamos los parámetros opcionales.
		// Sólo se debe especificar el país/región o la localización de la TSL.
		if (allIsOk && countryRegionCode != null && tslLocation != null) {
			allIsOk = false;
			LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG005));
			result = new TslInformationResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
			result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG005));
		}

		// Si todo ha ido bien, continuamos con el proceso de ejecución del
		// servicio.
		if (allIsOk) {

			try {
				result = executeServiceGetTslInformation(application, delegatedApp, countryRegionCode, tslLocation, getTslXmlData);
			} catch (TSLManagingException e) {
				result = new TslInformationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_EXECUTING_SERVICE);
				result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG009, new Object[ ] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }));
				LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG010, new Object[ ] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }), e);
			} catch (Exception e) {
				throw new ValetRestException(IValetException.COD_200, Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG011, new Object[ ] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }), e);
			}

		}

		return result;
	}

	/**
	 * Method that checks required parameters for {@link es.gob.valet.rest.services.TslRestService#getTslInformation} method.
	 * @param application Application identifier to check.
	 * @param getTslXmlData Flag that indicates if it is necessary to get the XML data.
	 * @return {@link String} with the parameter that not are correctly defined, otherwise <code>null</code>.
	 */
	private String checkParamsGetTslInformation(final String application, final Boolean getTslXmlData) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG003, new Object[ ] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }));
		boolean checkError = false;

		if (UtilsStringChar.isNullOrEmptyTrim(application)) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_APPLICATION);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (getTslXmlData == null) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_GET_TSL_XML_DATA);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (checkError) {
			return result.toString();
		} else {
			return null;
		}

	}

	/**
	 * After check the input parameters, this method execute the service 'executeServiceGetTslInformation'.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param countryRegionCode Country/Region code that represents the TSL.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param getTslXmlData Flag that indicates if it is necessary to return the XML data that represents the TSL.
	 * @return Structure of TSL information.
	 * @throws TSLManagingException In case of some error getting the TSL information.
	 */
	private TslInformationResponse executeServiceGetTslInformation(String application, String delegatedApp, String countryRegionCode, String tslLocation, boolean getTslXmlData) throws TSLManagingException {

		// Inicializamos el resultado a devolver.
		TslInformationResponse result = new TslInformationResponse();

		// En función de si tenemos el código de región/país o
		// el TSLLocation, buscamos la TSL.
		TSLDataCacheObject tsldco = null;
		if (UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {
			tsldco = TSLManager.getInstance().getTSLDataFromTSLLocation(tslLocation);
		} else {
			tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(countryRegionCode);
		}

		// Construimos la respuesta en función de si se ha encontrado o no.
		if (tsldco == null) {

			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_NOT_FINDED);
			result.setDescription(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG007));

		} else {

			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_FINDED);
			result.setDescription(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG008));

			ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();

			TslInformation tslInformation = new TslInformation();
			tslInformation.setCountryRegion(tslObject.getSchemeInformation().getSchemeTerritory());
			tslInformation.setSequenceNumber(tsldco.getSequenceNumber());
			tslInformation.setTslLocation(tsldco.getTslLocationUri());
			tslInformation.setIssued(tsldco.getIssueDate());
			tslInformation.setNextUpdate(tsldco.getNextUpdateDate());
			if (getTslXmlData) {
				tslInformation.setTslXmlData(TSLManager.getInstance().getTSLDataXMLDocument(tsldco.getTslDataId()));
			}
			result.setTslInformation(tslInformation);

		}

		// Devolvemos el resultado.
		return result;

	}

}