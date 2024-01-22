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
 * @version 2.1, 19/01/2024.
 */
package es.gob.valet.rest.services;

import java.io.IOException;
import java.io.Serializable;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.audit.access.IEventsCollectorConstants;
import es.gob.valet.audit.utils.CommonsCertificatesAuditTraces;
import es.gob.valet.audit.utils.CommonsServicesAuditTraces;
import es.gob.valet.audit.utils.CommonsTslAuditTraces;
import es.gob.valet.commons.utils.UtilsCRL;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IRestGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.model.utils.IKeystoreIdConstants;
import es.gob.valet.rest.elements.CertDetectedInTSL;
import es.gob.valet.rest.elements.Certificate;
import es.gob.valet.rest.elements.CertificateChain;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.ResultTslInfVal;
import es.gob.valet.rest.elements.TslInformation;
import es.gob.valet.rest.elements.TslInformationResponse;
import es.gob.valet.rest.elements.TslInformationVersionsResponse;
import es.gob.valet.rest.elements.TslRevocationStatus;
import es.gob.valet.rest.elements.TspServiceHistoryInf;
import es.gob.valet.rest.elements.TspServiceInformation;
import es.gob.valet.rest.elements.json.ByteArrayB64;
import es.gob.valet.rest.elements.json.DateString;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.access.TSLProperties;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.impl.common.CertificateChainValidator;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Class that represents the statistics restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.0, 19/09/2023.
 */
@Path("/tsl")
public class TslRestService implements ITslRestService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TslRestService.class);

	/**
	 * Attribute that represents the identifier of the application that will be
	 * used for auditing in the 'getTslInformation' service
	 */
	private static final String INTERNAL_TASK_APP = "APP_INTERNAL_TASK";

	/**
	 * Attribute that represents the identifier of the delegated application
	 * that will be used for auditing in the 'getTslInformation' service
	 */
	private static final String INTERNAL_TASK_DELEGATE_APP = "DELAPP_INTERNAL_TASK";

	/**
	 * Attribute that represents the HTTP Servlet Request.
	 */
	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * {@inheritDoc}
	 * 
	 * @see es.gob.valet.rest.services.ITslRestService#detectCertInTslInfoAndValidation(java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      es.gob.valet.rest.elements.json.ByteArrayB64,
	 *      es.gob.valet.rest.elements.json.DateString, java.lang.Boolean,
	 *      java.lang.Boolean, java.lang.Boolean, java.util.List,
	 *      java.util.List)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(
			@FormParam(PARAM_APPLICATION) final String application,
			@FormParam(PARAM_DELEGATED_APP) final String delegatedApp,
			@FormParam(PARAM_TSL_LOCATION) final String tslLocationB64,
			@FormParam(PARAM_CERTIFICATE) final ByteArrayB64 certByteArrayB64,
			@FormParam(PARAM_DETECTION_DATE) final DateString detectionDate,
			@FormParam(PARAM_GET_INFO) final Boolean getInfo,
			@FormParam(PARAM_CHECK_REV_STATUS) final Boolean checkRevStatus,
			@FormParam(PARAM_RETURN_REV_EVID) final Boolean returnRevocationEvidence,
			@FormParam(PARAM_CRLS_BYTE_ARRAY) List<ByteArrayB64> crlsByteArrayB64List,
			@FormParam(PARAM_BASIC_OCSP_RESPONSES_BYTE_ARRAY) List<ByteArrayB64> basicOcspResponsesByteArrayB64List,
			@FormParam(PARAM_RETURN_CERT_CHAIN) Boolean returnCertificateChain) throws ValetRestException {
		// CHECKSTYLE:ON
		long startOperationTime = Calendar.getInstance().getTimeInMillis();
		// Añadimos la información NDC al log y obtenemos un número único
		// para la transacción.
		String auditTransNumber = LoggingInformationNDC.registerNdcInfAndGetTransactionNumber(httpServletRequest,
				ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION);

		// Si no se ha especificado la aplicación delegada, establecemos el
		// token 'NOT_SPECIFIED'.
		String delegatedAppAux = delegatedApp == null ? IEventsCollectorConstants.FIELD_VALUE_DELAPPID_NOTSPECIFIED
				: delegatedApp;

		// Miramos el número de CRLs y OCSPs recibidos para el log.
		int numCRLs = crlsByteArrayB64List == null ? 0 : crlsByteArrayB64List.size();
		int numOCSPs = basicOcspResponsesByteArrayB64List == null ? 0 : basicOcspResponsesByteArrayB64List.size();

		// tslLocation si no es nulo o vacío viene codificado en Base64, se
		// decodifica.
		String tslLocation = null;
		if (!UtilsStringChar.isNullOrEmpty(tslLocationB64)) {

			// se decodifica
			byte[] tslLocationBytes = Base64.getDecoder().decode(tslLocationB64);
			String tslLocationTmp = new String(tslLocationBytes);
			tslLocation = tslLocationTmp.replace("\"", "");

		}

		// Indicamos la recepción del servicio junto con los parámetros de
		// entrada.
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG001,
				new Object[] { application, delegatedAppAux, tslLocation, certByteArrayB64, detectionDate, getInfo,
						checkRevStatus, returnRevocationEvidence, numCRLs, numOCSPs }));

		// Inicialmente consideramos que todo es OK para proceder.
		boolean allIsOk = true;

		// Creamos el objeto que representa la respuesta.
		DetectCertInTslInfoAndValidationResponse result = null;

		// Comprobamos los parámetros obligatorios de entrada.
		String resultCheckParams = checkParamsDetectCertInTslInfoAndValidationResponse(application, certByteArrayB64,
				getInfo, checkRevStatus, returnRevocationEvidence);
		if (resultCheckParams != null) {
			allIsOk = false;
			LOGGER.error(resultCheckParams);
			result = new DetectCertInTslInfoAndValidationResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
			result.setDescription(resultCheckParams);
		}

		// Se comprueba que la aplicación recibida se encuentra entre las
		// dadas de alta en la plataforma.
		if (allIsOk) {

			ApplicationValet app = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().getApplicationByIdentificator(application);

			if (app == null) {

				allIsOk = false;
				String errorMsg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG037,
						new Object[] { application });
				LOGGER.error(errorMsg);
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(errorMsg);

			}

		}

		// Comprobamos que se parsea correctamente el certificado a detectar.
		X509Certificate x509cert = null;
		if (allIsOk) {
			try {
				x509cert = UtilsCertificate.getX509Certificate(certByteArrayB64.getByteArray());
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
			boolean thereIsSomeRevocationEvidence = crlsByteArrayB64List != null && !crlsByteArrayB64List.isEmpty();
			thereIsSomeRevocationEvidence = thereIsSomeRevocationEvidence
					|| basicOcspResponsesByteArrayB64List != null && !basicOcspResponsesByteArrayB64List.isEmpty();
			if (!checkRevStatus && !thereIsSomeRevocationEvidence) {
				allIsOk = false;
				LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG004));
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG004));
			}
		}

		// Comprobamos que el formato de la fecha sea adecuado,
		// si es que se proporciona.
		Date detectionDateAux = null;
		if (allIsOk) {

			// Si no es nula, hay que parsearla y comprobar que no sobrepasa
			// hacia el futuro respecto al intervalo permitido.
			if (detectionDate != null) {

				try {

					// Parseamos la fecha.
					detectionDateAux = detectionDate.getDate();

					// Calculamos la fecha límite.
					int timeGapInMilliseconds = TSLProperties
							.getServiceDetectCertInTslInfoAndValidationParamValDateTimeGap();
					Calendar limitDateCal = Calendar.getInstance();
					limitDateCal.add(Calendar.MILLISECOND, timeGapInMilliseconds);
					Date limitDate = limitDateCal.getTime();

					// Comparamos la fecha respecto a la límite.
					// Si la fecha límite es anterior a la fecha de validación,
					// devolvemos
					// error en los parámetros de entrada.
					if (limitDate.before(detectionDateAux)) {

						allIsOk = false;
						String errorMsg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG006,
								new Object[] { detectionDate });
						LOGGER.error(errorMsg);
						result = new DetectCertInTslInfoAndValidationResponse();
						result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
						result.setDescription(errorMsg);

					}

				} catch (ParseException e) {

					allIsOk = false;
					String errorMsg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG033,
							new Object[] { detectionDate, UtilsDate.FORMAT_DATE_TIME_JSON });
					LOGGER.error(errorMsg);
					result = new DetectCertInTslInfoAndValidationResponse();
					result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
					result.setDescription(errorMsg);

				}

			}
			// Se establece la fecha actual como fecha de validación.
			else {
				detectionDateAux = Calendar.getInstance().getTime();
			}
		}

		// Si todo es OK y se han recibido CRLs...
		X509CRL[] crlArray = null;
		if (allIsOk && crlsByteArrayB64List != null && !crlsByteArrayB64List.isEmpty()) {

			// Tratamos de parsearlas.
			try {
				List<X509CRL> crlList = new ArrayList<X509CRL>();
				for (ByteArrayB64 crlByteArrayB64 : crlsByteArrayB64List) {
					if (crlByteArrayB64.getByteArray() != null) {
						crlList.add(UtilsCRL.buildX509CRLfromByteArray(crlByteArrayB64.getByteArray()));
					}
				}
				if (!crlList.isEmpty()) {
					crlArray = crlList.toArray(new X509CRL[0]);
				}
			} catch (CommonUtilsException e) {

				allIsOk = false;
				String errorMsg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG034);
				LOGGER.error(errorMsg);
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(errorMsg);

			}

		}

		// Si todo es OK y se han recibido respuestas OCSP básicas...
		BasicOCSPResp[] basicOcspRespArray = null;
		if (allIsOk && basicOcspResponsesByteArrayB64List != null && !basicOcspResponsesByteArrayB64List.isEmpty()) {

			// Tratamos de parsearlas.
			try {
				List<BasicOCSPResp> basicOcspRespList = new ArrayList<BasicOCSPResp>();
				for (ByteArrayB64 basicOcspResponsesByteArrayB64 : basicOcspResponsesByteArrayB64List) {
					if (basicOcspResponsesByteArrayB64 != null) {
						OCSPResp ocspResp = new OCSPResp(basicOcspResponsesByteArrayB64.getByteArray());
						basicOcspRespList.add((BasicOCSPResp) ocspResp.getResponseObject());
					}
				}
				if (!basicOcspRespList.isEmpty()) {
					basicOcspRespArray = basicOcspRespList.toArray(new BasicOCSPResp[0]);
				}
			} catch (IOException | OCSPException e) {

				allIsOk = false;
				String errorMsg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG035);
				LOGGER.error(errorMsg);
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(errorMsg);

			}

		}

		if (returnCertificateChain == null) {
			returnCertificateChain = Boolean.FALSE;
		}

		// Si todo ha ido bien, continuamos con el proceso de ejecución del
		// servicio.
		if (allIsOk) {

			try {
				// Si se ha comprobado que todos los parámetros son correctos,
				// abrimos la transacción
				// en auditoría.
				CommonsServicesAuditTraces.addOpenTransactionTrace(auditTransNumber,
						IEventsCollectorConstants.SERVICE_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION_ID,
						extractRequestByteArray());
				CommonsServicesAuditTraces.addStartRSTrace(auditTransNumber, application, delegatedAppAux);
				result = executeServiceDetectCertInTslInfoAndValidation(auditTransNumber, application, delegatedAppAux,
						tslLocation, x509cert, detectionDateAux, getInfo.booleanValue(), checkRevStatus.booleanValue(),
						returnRevocationEvidence, crlArray, basicOcspRespArray, returnCertificateChain);
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_OK, result.getDescription());
				// Calculamos la representación en bytes del resultado, y si la
				// obtenemos correctamente, cerramos la transacción.
				byte[] resultByteArray = buildResultByteArray(result);
				CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);
			} catch (TSLManagingException e) {
				result = new DetectCertInTslInfoAndValidationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_EXECUTING_SERVICE);
				result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG009,
						new Object[] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }));
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, result.getDescription());
				// Calculamos la representación en bytes del resultado, y si la
				// obtenemos correctamente, cerramos la transacción.
				byte[] resultByteArray = buildResultByteArray(result);
				CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);
				LOGGER.error(
						Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG010,
								new Object[] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }),
						e);
			} catch (Exception e) {
				e.printStackTrace();
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, e.getMessage());
				LoggingInformationNDC.unregisterNdcInf();
				throw new ValetRestException(IValetException.COD_200,
						Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG011,
								new Object[] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }),
						e);
			}

		}

		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG041,
				new Object[] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));
		// Limpiamos la información NDC.
		LoggingInformationNDC.unregisterNdcInf();
		return result;

	}

	/**
	 * Gets the byte array that represents the request.
	 * 
	 * @return byte array that represents the request.
	 * @throws JsonProcessingException
	 *             In case of some error extracting the byte array that
	 *             represents the input request parameters.
	 *
	 */
	private byte[] extractRequestByteArray() throws JsonProcessingException {

		byte[] result = null;
		ObjectMapper om = new ObjectMapper();
		try {
			result = om.writeValueAsBytes(httpServletRequest.getParameterMap());
		} catch (JsonProcessingException e) {
			LOGGER.error(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG039));
			throw e;
		}
		return result;

	}

	/**
	 * Gets the byte array that represents the JSon response.
	 * 
	 * @param resultObjectService
	 *            Object that represents the result object of the service.
	 * @return the byte array that represents the JSon response.
	 */
	private byte[] buildResultByteArray(Serializable resultObjectService) {

		byte[] result = null;
		ObjectMapper om = new ObjectMapper();
		try {
			result = om.writeValueAsBytes(resultObjectService);
		} catch (JsonProcessingException e) {
			LOGGER.error(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG038), e);
		}
		return result;

	}

	/**
	 * Method that checks required parameters for
	 * {@link es.gob.valet.rest.services.TslRestService#detectCertInTslInfoAndValidation}
	 * method.
	 * 
	 * @param application
	 *            Application identifier.
	 * @param certByteArray
	 *            Certificate to detect (byte[]).
	 * @param getInfo
	 *            Flag that indicates if it is necessary to get the certificate
	 *            information in response.
	 * @param checkRevStatus
	 *            Check revocation status Flag that indicates if it is necessary
	 *            to check the revocation status of the input certificate.
	 * @param returnRevoEvid
	 *            Flag that indicates if it is necessary to return the
	 *            revocation evidence (only if {@code checkRevocationStatus} is
	 *            <code>true</code>).
	 * @return {@link String} with the parameter that not are correctly defined,
	 *         otherwise <code>null</code>.
	 */
	private String checkParamsDetectCertInTslInfoAndValidationResponse(final String application,
			final ByteArrayB64 certByteArray, final Boolean getInfo, final Boolean checkRevStatus,
			final Boolean returnRevoEvid) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG003,
				new Object[] { ITslRestService.SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION }));
		boolean checkError = false;

		// Check received parameters
		if (UtilsStringChar.isNullOrEmptyTrim(application)) {
			checkError = true;
			result.append(UtilsStringChar.EMPTY_STRING);
			result.append(UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING);
			result.append(ITslRestService.PARAM_APPLICATION);
			result.append(UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING);
		}

		if (certByteArray == null) {
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
	 * After check the input parameters, this method execute the service
	 * 'detectCertInTslInfoAndValidation'.
	 * 
	 * @param auditTransNumber
	 *            Audit transaction number.
	 * @param application
	 *            Application identifier.
	 * @param delegatedApp
	 *            Delegated application identifier.
	 * @param tslLocation
	 *            TSL location to use. It could be <code>null</code>.
	 * @param x509cert
	 *            X.509 certificate to detect.
	 * @param detectionDate
	 *            Date to use to detect and validate the input certificate.
	 * @param getInfo
	 *            Flag that indicates if it is necessary to get the certificate
	 *            information in response.
	 * @param checkRevStatus
	 *            Flag that indicates if it is necessary to check the revocation
	 *            status of the input certificate.
	 * @param returnRevocationEvidence
	 *            Flag that indicates if it is necessary to return the
	 *            revocation evidence (only if {@code checkRevStatus} is
	 *            <code>true</code>).
	 * @param crlArray
	 *            List of {@link X509CRL} that could be used like revocation
	 *            evidence. It could be <code>null</code>. If this is defined,
	 *            then {@code checkRevStatus} is considered <code>true</code>.
	 * @param basicOcspRespArray
	 *            List of {@link BasicOCSPResp} that could be used like
	 *            revocation evidence. It could be <code>null</code>. If this is
	 *            defined, then {@code checkRevStatus} is considered
	 *            <code>true</code>.
	 * @param returnCertificateChain
	 *            Flag that indicates if it is necessary to return chain
	 *            certificate.
	 * @return Structure of DetectCertInTslInfoAndValidationResponse.
	 * @throws TSLManagingException
	 *             In case of some error detecting or validating the certificate
	 *             with the TSL.
	 * @throws IOException
	 *             In case of some error decoding a Basic OCSP Response.
	 * @throws CRLException
	 *             Incase of some error decoding a CRL.
	 * @throws ValetRestException
	 */
	private DetectCertInTslInfoAndValidationResponse executeServiceDetectCertInTslInfoAndValidation(
			String auditTransNumber, String application, String delegatedApp, String tslLocation,
			X509Certificate x509cert, Date detectionDate, boolean getInfo, boolean checkRevStatus,
			Boolean returnRevocationEvidence, X509CRL[] crlArray, BasicOCSPResp[] basicOcspRespArray,
			Boolean returnCertificateChain) throws TSLManagingException, CRLException, IOException, ValetRestException {

		// Inicializamos el resultado a devolver.
		DetectCertInTslInfoAndValidationResponse result = new DetectCertInTslInfoAndValidationResponse();

		// Ponemos en auditoría los parámetros relevantes del servicio.
		boolean thereIsCRLs = crlArray != null && crlArray.length > 0;
		boolean thereIsBasicOcspResponses = basicOcspRespArray != null && basicOcspRespArray.length > 0;
		CommonsServicesAuditTraces.addRSDetectCertParamsInfo(auditTransNumber, getInfo, checkRevStatus,
				returnRevocationEvidence, thereIsCRLs, thereIsBasicOcspResponses);

		// Ponemos en auditoría la información del certificado a evaluar.
		CommonsCertificatesAuditTraces.addCertInfoOperationTrace(auditTransNumber, x509cert);

		ITSLValidatorResult tslValidatorResult = null;
		// Si disponemos de evidencias de revocación a usar...
		if (thereIsCRLs || thereIsBasicOcspResponses) {
			// En función de si se ha especificado un TSLLocation o no, se
			// intenta
			// detectar el certificado.
			if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
				tslValidatorResult = TSLManager.getInstance().validateX509withTSLandRevocationValues(auditTransNumber,
						x509cert, detectionDate, crlArray, basicOcspRespArray, getInfo);
			} else {
				CommonsTslAuditTraces.addTslLocationOperationTrace(auditTransNumber, tslLocation, null);
				tslValidatorResult = TSLManager.getInstance().validateX509withTSLLocationAndRevocationValues(
						auditTransNumber, x509cert, detectionDate, crlArray, basicOcspRespArray, tslLocation, getInfo);
			}
		}
		// Si no tenemos evidencias de revocación a usar...
		else {
			// En función de si se ha especificado un TSLLocation o no, se
			// intenta
			// detectar el certificado.
			if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
				tslValidatorResult = TSLManager.getInstance().validateX509withTSL(auditTransNumber, x509cert,
						detectionDate, checkRevStatus, getInfo);
			} else {
				CommonsTslAuditTraces.addTslLocationOperationTrace(auditTransNumber, tslLocation, null);
				tslValidatorResult = TSLManager.getInstance().validateX509withTSL(auditTransNumber, x509cert,
						tslLocation, detectionDate, checkRevStatus, getInfo);
			}
		}

		// Si el resultado es nulo, significa que no se ha encontrado TSL
		// para detectar el certificado.
		if (tslValidatorResult == null) {

			String msg = null;
			if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG013,
						UtilsCertificate.getCountryOfTheCertificateString(x509cert));
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
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG015,
						new Object[] { UtilsCertificate.getCountryOfTheCertificateString(x509cert), detectionDate });
			} else {
				msg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG016,
						new Object[] { tslLocation, detectionDate });
			}
			// LOGGER.info(msg);
			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED);
			result.setDescription(msg);

			// Obtenemos el objeto de caché que representa la TSL.
			TSLDataCacheObject tsldco = TSLManager.getInstance()
					.getTSLDataFromCountryRegion(tslValidatorResult.getTslCountryRegionCode());

			// Completamos los datos básicos en la respuesta.
			ResultTslInfVal resultTslInfVal = new ResultTslInfVal();
			TslInformation tslInformation = new TslInformation();
			tslInformation.setEtsiSpecificationAndVersion(tslValidatorResult.getTslEtsiSpecificationAndVersion());
			tslInformation.setCountryRegion(tslValidatorResult.getTslCountryRegionCode());
			tslInformation.setSequenceNumber(tslValidatorResult.getTslSequenceNumber());
			tslInformation.setTslLocation(tsldco.getTslLocationUri());
			tslInformation.setIssued(new DateString(tslValidatorResult.getTslIssueDate()));
			tslInformation.setNextUpdate(new DateString(tslValidatorResult.getTslNextUpdate()));
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
					result.setStatus(
							ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_NOT_DETECTED);
					result.setDescription(msg);

				}
				// Si se ha detectado el certificado en la TSL...
				else {
					if (tslValidatorResult.getTSPServiceForDetect() != null) {
						// Lo marcamos en la respuesta.
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG018);
						// LOGGER.info(msg);
						result.setStatus(
								ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED);
						result.setDescription(msg);

						// Creamos el objeto que representa la información común
						// para ambos casos.
						CertDetectedInTSL certDetectedInTsl = new CertDetectedInTSL();
						certDetectedInTsl.setTspName(tslValidatorResult.getTSPName());

						// Creamos el objeto que define la información del
						// TSP-Service.
						TspServiceInformation tspServiceInformation = new TspServiceInformation();
						tspServiceInformation.setTspServiceName(tslValidatorResult.getTSPServiceNameForDetect());
						tspServiceInformation.setTspServiceType(tslValidatorResult.getTSPServiceForDetect()
								.getServiceInformation().getServiceTypeIdentifier().toString());
						tspServiceInformation.setTspServiceStatus(tslValidatorResult.getTSPServiceForDetect()
								.getServiceInformation().getServiceStatus().toString());
						tspServiceInformation.setTspServiceStatusStartingDate(new DateString(tslValidatorResult
								.getTSPServiceForDetect().getServiceInformation().getServiceStatusStartingTime()));

						// Si se ha hecho uso de la información del histórico
						// del
						// servicio...
						if (tslValidatorResult.getTSPServiceHistoryInformationInstanceNameForDetect() != null) {

							// Creamos el objeto que representa la información
							// del
							// histórico del servicio.
							TspServiceHistoryInf tspServiceHistoryInf = new TspServiceHistoryInf();
							tspServiceHistoryInf.setTspServiceName(
									tslValidatorResult.getTSPServiceHistoryInformationInstanceNameForDetect());
							tspServiceHistoryInf.setTspServiceType(
									tslValidatorResult.getTSPServiceHistoryInformationInstanceForDetect()
											.getServiceTypeIdentifier().toString());
							tspServiceHistoryInf.setTspServiceStatus(tslValidatorResult
									.getTSPServiceHistoryInformationInstanceForDetect().getServiceStatus().toString());
							tspServiceHistoryInf.setTspServiceStatusStartingDate(
									new DateString(tslValidatorResult.getTSPServiceHistoryInformationInstanceForDetect()
											.getServiceStatusStartingTime()));
							// Lo asignamos a la información del servicio.
							tspServiceInformation.setTspServiceHistoryInf(tspServiceHistoryInf);

						}

						// Lo establecemos en la información de detección del
						// certificado.
						certDetectedInTsl.setTspServiceInformation(tspServiceInformation);

						// Si se ha solicitado obtener información del
						// certificado...
						if (getInfo) {
							Map<String, String> mappings = tslValidatorResult.getMappings();
							certDetectedInTsl.setCertInfo(mappings);
						}

						// Si se ha solicitado comprobar el estado de revocación
						// del
						// certificado...
						if (checkRevStatus) {

							// Construimos el objeto que contendrá la
							// información de
							// revocación.
							TslRevocationStatus tslRevocationStatus = new TslRevocationStatus();

							// Asignamos el resultado de comprobación de estado
							// de
							// revocación.
							tslRevocationStatus.setRevocationStatus(tslValidatorResult.getResult());
							tslRevocationStatus.setIsFromServStat(tslValidatorResult.isResultFromServiceStatus());

							// En función del resultado (sabemos que ha sido
							// detectado)...
							switch (tslRevocationStatus.getRevocationStatus()) {
							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG021);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_VALID:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG022);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								addRevocationInfoInResult(tslRevocationStatus, tslValidatorResult,
										returnRevocationEvidence);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG023);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								if (!tslRevocationStatus.getIsFromServStat()) {
									addRevocationInfoInResult(tslRevocationStatus, tslValidatorResult,
											returnRevocationEvidence);
								}
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG024);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED_SERVICESTATUS:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG025);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								break;

							case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID_SERVICESTATUS:
								msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG026);
								LOGGER.info(msg);
								tslRevocationStatus.setRevocationDesc(msg);
								break;
							default:
								break;
							}

							// Añadimos toda la información de revocación en la
							// respuesta.
							certDetectedInTsl.setTslRevocStatus(tslRevocationStatus);

						}

						// si se ha solicitado obtener la cadena de
						// certificación y las evidencias de revocación
						if (returnCertificateChain) {
							LOGGER.info(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG048));
							CertificateChain certChain = null;
							List<Certificate> listCertificates = new ArrayList<Certificate>();

							listCertificates = getCertificateChainAndRevocationStatus(x509cert, detectionDate,
									tslValidatorResult.getTslCountryRegionCode());
							if (listCertificates != null && !listCertificates.isEmpty()) {
								certChain = new CertificateChain();
								certChain.setCertificates(listCertificates);

							}

							certDetectedInTsl.setCertificateChain(certChain);

							if (certChain != null && certChain.getCertificates() != null
									&& !certChain.getCertificates().isEmpty()) {
								LOGGER.info(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG049));
							} else {
								LOGGER.warn(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG047));
							}

						}

						// Asignamos la información de detección del certificado
						// a
						// la respuesta final.
						resultTslInfVal.setCertDetectedInTSL(certDetectedInTsl);

						// Establecemos el resultado general en función de si se
						// ha
						// solicitado
						// información del certificado y/o su estado de
						// revocación,
						// y lo que finalmente
						// se pudo obtener.
						setGeneralStatusResponseGetInfoRevocationStatus(result, getInfo, checkRevStatus);

					}

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
	private void setGeneralStatusResponseGetInfoRevocationStatus(DetectCertInTslInfoAndValidationResponse result,
			boolean getInfo, boolean checkRevStatus) {

		// Obtenemos el objeto que contiene la información de haber detectado
		// el certificado en la TSL.
		CertDetectedInTSL certDetectedInTsl = result.getResultTslInfVal().getCertDetectedInTSL();

		// Cadena donde se almacenará el mensaje descriptivo a asignar
		// finalmente.
		String msg = null;

		// Si se ha solicitado información del certificado...
		if (getInfo) {

			// Comprobamos si se ha obtenido la información solicitada.
			boolean infoCertObtained = certDetectedInTsl.getCertInfo() != null
					&& !certDetectedInTsl.getCertInfo().isEmpty();

			// Si también se ha solicitado la información de revocación...
			if (checkRevStatus) {

				// Comprobamos si se ha obtenido la información de revocación.
				boolean revStatusInfoObtained = certDetectedInTsl.getTslRevocStatus()
						.getRevocationStatus() != ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN;

				if (infoCertObtained) {

					if (revStatusInfoObtained) {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG032);
						result.setStatus(
								ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_COLLECTED);
					} else {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG031);
						result.setStatus(
								ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_NOT_COLLECTED);
					}

				} else {

					if (revStatusInfoObtained) {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG030);
						result.setStatus(
								ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_COLLECTED);
					} else {
						msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG029);
						result.setStatus(
								ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_NOT_COLLECTED);
					}

				}

			}
			// Si entramos aquí significa que solo se solicitó la información
			// del certificado.
			else {

				if (infoCertObtained) {

					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG020);
					result.setStatus(
							ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED);

				} else {

					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG019);
					result.setStatus(
							ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED);

				}

			}

		}
		// Si no se ha solicitado información del certificado significa que al
		// menos
		// se ha solicitado la información de revocación.
		else {

			// Comprobamos si se ha obtenido la información de revocación.
			boolean revStatusInfoObtained = certDetectedInTsl.getTslRevocStatus()
					.getRevocationStatus() != ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN;

			if (revStatusInfoObtained) {

				msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG027);
				result.setStatus(
						ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_COLLECTED);

			} else {

				msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG028);
				result.setStatus(
						ITslRestServiceStatusResult.STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_NOT_COLLECTED);

			}

		}

		// Lo asignamos al resultado y pintamos en el log.
		result.setDescription(msg);
		LOGGER.info(msg);

	}

	/**
	 * Add the revocation information in the result.
	 * 
	 * @param tslRevocationStatus
	 *            TSL revocation status information to return.
	 * @param tslValidatorResult
	 *            TSL validation process result to analyze.
	 * @param returnRevocationEvidence
	 *            Flag that indicates if it is necessary to return the
	 *            revocation evidence (only if {@code checkRevStatus} is
	 *            <code>true</code>).
	 * @throws IOException
	 *             In case of some error decoding a Basic OCSP Response.
	 * @throws CRLException
	 *             Incase of some error decoding a CRL.
	 */
	private void addRevocationInfoInResult(TslRevocationStatus tslRevocationStatus,
			ITSLValidatorResult tslValidatorResult, boolean returnRevocationEvidence) throws IOException, CRLException {

		// Establecemos la URL de donde se haya obtenido la evidencia de
		// revocación.
		tslRevocationStatus.setUrl(tslValidatorResult.getRevocationValueURL());
		// Consultamos si se ha obtenido mediante el DistributionPoint / AIA del
		// certificado.
		tslRevocationStatus.setDpAia(tslValidatorResult.isResultFromDPorAIA());
		// Si no ha sido por el DP / AIA, es por un servicio...
		if (!tslRevocationStatus.getDpAia()) {

			// Creamos el objeto que define la información del TSP-Service.
			TspServiceInformation tspServiceInformation = new TspServiceInformation();
			tspServiceInformation.setTspServiceName(tslValidatorResult.getTSPServiceNameForValidate());
			tspServiceInformation.setTspServiceType(tslValidatorResult.getTSPServiceForValidate()
					.getServiceInformation().getServiceTypeIdentifier().toString());
			tspServiceInformation.setTspServiceStatus(tslValidatorResult.getTSPServiceForValidate()
					.getServiceInformation().getServiceStatus().toString());
			tspServiceInformation.setTspServiceStatusStartingDate(new DateString(tslValidatorResult
					.getTSPServiceForValidate().getServiceInformation().getServiceStatusStartingTime()));

			// Si se ha hecho uso de la información del histórico del
			// servicio...
			if (tslValidatorResult.getTSPServiceHistoryInformationInstanceNameForValidate() != null) {

				// Creamos el objeto que representa la información del
				// histórico del servicio.
				TspServiceHistoryInf tspServiceHistoryInf = new TspServiceHistoryInf();
				tspServiceHistoryInf
						.setTspServiceName(tslValidatorResult.getTSPServiceHistoryInformationInstanceNameForValidate());
				tspServiceHistoryInf.setTspServiceType(tslValidatorResult
						.getTSPServiceHistoryInformationInstanceForValidate().getServiceTypeIdentifier().toString());
				tspServiceHistoryInf.setTspServiceStatus(tslValidatorResult
						.getTSPServiceHistoryInformationInstanceForValidate().getServiceStatus().toString());
				tspServiceHistoryInf.setTspServiceStatusStartingDate(new DateString(tslValidatorResult
						.getTSPServiceHistoryInformationInstanceForValidate().getServiceStatusStartingTime()));
				// Lo asignamos a la información del servicio.
				tspServiceInformation.setTspServiceHistoryInf(tspServiceHistoryInf);

			}

			// Lo establecemos en la información de revocación del certificado.
			tslRevocationStatus.setTspServiceInformation(tspServiceInformation);

		}

		// En función del tipo de evidencia...
		// Si es OCSP...
		if (tslValidatorResult.getRevocationValueBasicOCSPResponse() != null) {
			if (returnRevocationEvidence) {
				tslRevocationStatus
						.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_OCSP);
				tslRevocationStatus.setEvidence(
						new ByteArrayB64(tslValidatorResult.getRevocationValueBasicOCSPResponse().getEncoded()));
			}
			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus()
					.intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new DateString(tslValidatorResult.getRevocationDate()));
			}
		}
		// Si es CRL...
		else if (tslValidatorResult.getRevocationValueCRL() != null) {
			if (returnRevocationEvidence) {
				tslRevocationStatus.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_CRL);
				tslRevocationStatus
						.setEvidence(new ByteArrayB64(tslValidatorResult.getRevocationValueCRL().getEncoded()));
			}
			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus()
					.intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new DateString(tslValidatorResult.getRevocationDate()));
			}
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see es.gob.valet.rest.services.ITslRestService#getTslInformation(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Boolean)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TslInformationResponse getTslInformation(@FormParam(PARAM_APPLICATION) final String application,
			@FormParam(PARAM_DELEGATED_APP) final String delegatedApp,
			@FormParam(PARAM_COUNTRY_REGION_CODE) final String countryRegionCode,
			@FormParam(PARAM_TSL_LOCATION) final String tslLocationB64,
			@FormParam(PARAM_GET_TSL_XML_DATA) final Boolean getTslXmlData) throws ValetRestException {
		// CHECKSTYLE:ON
		long startOperationTime = Calendar.getInstance().getTimeInMillis();
		// Generamos el identificador de transacción.
		String auditTransNumber = LoggingInformationNDC.registerNdcInfAndGetTransactionNumber(httpServletRequest,
				ITslRestService.SERVICENAME_GET_TSL_INFORMATION);

		// Si no se ha especificado la aplicación delegada, establecemos el
		// token 'NOT_SPECIFIED'.
		String delegatedAppAux = delegatedApp == null ? IEventsCollectorConstants.FIELD_VALUE_DELAPPID_NOTSPECIFIED
				: delegatedApp;

		// tslLocation si no es nulo o vacío viene codificado en Base64, se
		// decodifica.
		String tslLocation = null;
		if (!UtilsStringChar.isNullOrEmpty(tslLocationB64)) {

			// se decodifica
			byte[] tslLocationBytes = Base64.getDecoder().decode(tslLocationB64);
			String tslLocationTmp = new String(tslLocationBytes);
			tslLocation = tslLocationTmp.replace("\"", "");

		}

		// Indicamos la recepción del servicio junto con los parámetros de
		// entrada.
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG002,
				new Object[] { application, delegatedAppAux, countryRegionCode, tslLocation, getTslXmlData }));

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

		// Se comprueba que la aplicación recibida se encuentra entre las
		// dadas de alta en la plataforma.
		if (allIsOk) {

			ApplicationValet app = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().getApplicationByIdentificator(application);

			if (app == null) {

				allIsOk = false;
				String errorMsg = Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG037,
						new Object[] { application });
				LOGGER.error(errorMsg);
				result = new TslInformationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_INPUT_PARAMETERS);
				result.setDescription(errorMsg);

			}

		}

		// Comprobamos los parámetros opcionales.
		// Sólo se debe especificar el país/región o la localización de la TSL.
		if (allIsOk && ((UtilsStringChar.isNullOrEmpty(countryRegionCode) && UtilsStringChar.isNullOrEmpty(tslLocation))
				|| (!UtilsStringChar.isNullOrEmpty(countryRegionCode)
						&& !UtilsStringChar.isNullOrEmpty(tslLocation)))) {
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
				// Si se ha comprobado que todos los parámetros son correctos,
				// abrimos la transacción
				// en auditoría.
				CommonsServicesAuditTraces.addOpenTransactionTrace(auditTransNumber,
						IEventsCollectorConstants.SERVICE_GET_TSL_INFORMATION_ID, extractRequestByteArray());
				CommonsServicesAuditTraces.addStartRSTrace(auditTransNumber, application, delegatedAppAux);
				result = executeServiceGetTslInformation(auditTransNumber, application, delegatedAppAux,
						countryRegionCode, tslLocation, getTslXmlData);
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_OK, result.getDescription());
				// Calculamos la representación en bytes del resultado, y si la
				// obtenemos correctamente, cerramos la transacción.
				byte[] resultByteArray = buildResultByteArray(result);
				CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);

			} catch (TSLManagingException e) {
				result = new TslInformationResponse();
				result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_EXECUTING_SERVICE);
				result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG009,
						new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }));
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, result.getDescription());
				// Calculamos la representación en bytes del resultado, y si la
				// obtenemos correctamente, cerramos la transacción.
				byte[] resultByteArray = buildResultByteArray(result);
				CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);
				LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG010,
						new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }), e);
			} catch (Exception e) {
				CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
						IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, e.getMessage());
				LoggingInformationNDC.unregisterNdcInf();
				throw new ValetRestException(IValetException.COD_200,
						Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG011,
								new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }),
						e);
			}

		}
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG042,
				new Object[] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));
		// Limpiamos la información NDC.
		LoggingInformationNDC.unregisterNdcInf();

		return result;
	}

	/**
	 * Method that checks required parameters for
	 * {@link es.gob.valet.rest.services.TslRestService#getTslInformation}
	 * method.
	 * 
	 * @param application
	 *            Application identifier to check.
	 * @param getTslXmlData
	 *            Flag that indicates if it is necessary to get the XML data.
	 * @return {@link String} with the parameter that not are correctly defined,
	 *         otherwise <code>null</code>.
	 */
	private String checkParamsGetTslInformation(final String application, final Boolean getTslXmlData) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG003,
				new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION }));
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
	 * After check the input parameters, this method execute the service
	 * 'executeServiceGetTslInformation'.
	 * 
	 * @param auditTransNumber
	 *            Audit transaction number.
	 * @param application
	 *            Application identifier.
	 * @param delegatedApp
	 *            Delegated application identifier.
	 * @param countryRegionCode
	 *            Country/Region code that represents the TSL.
	 * @param tslLocation
	 *            TSL location to use. It could be <code>null</code>.
	 * @param getTslXmlData
	 *            Flag that indicates if it is necessary to return the XML data
	 *            that represents the TSL.
	 * @return Structure of TSL information.
	 * @throws TSLManagingException
	 *             In case of some error getting the TSL information.
	 */
	private TslInformationResponse executeServiceGetTslInformation(String auditTransNumber, String application,
			String delegatedApp, String countryRegionCode, String tslLocation, boolean getTslXmlData)
			throws TSLManagingException {

		// Inicializamos el resultado a devolver.
		TslInformationResponse result = new TslInformationResponse();

		// En función de si tenemos el código de región/país o
		// el TSLLocation, buscamos la TSL.
		TSLDataCacheObject tsldco = null;

		if (UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {
			CommonsTslAuditTraces.addTslLocationOperationTrace(auditTransNumber, tslLocation, getTslXmlData);
			tsldco = TSLManager.getInstance().getTSLDataFromTSLLocation(tslLocation);
		} else {
			CommonsTslAuditTraces.addTslCountryRegionTrace(auditTransNumber, countryRegionCode, getTslXmlData);
			tsldco = TSLManager.getInstance().getTSLDataFromCountryRegion(countryRegionCode);
		}

		// Construimos la respuesta en función de si se ha encontrado o no.
		if (tsldco == null) {

			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_NOT_FINDED);
			result.setDescription(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG007));
			// Añadimos la traza de auditoría indicando que no se ha encontrado.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, false, null, null, null, null);

		} else {

			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_FINDED);
			result.setDescription(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG008));

			ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();

			TslInformation tslInformation = new TslInformation();
			tslInformation.setEtsiSpecificationAndVersion(tslObject.getSpecification()
					+ UtilsStringChar.SPECIAL_BLANK_SPACE_STRING + tslObject.getSpecificationVersion());
			tslInformation.setCountryRegion(tslObject.getSchemeInformation().getSchemeTerritory());
			tslInformation.setSequenceNumber(tsldco.getSequenceNumber());
			tslInformation.setTslLocation(tsldco.getTslLocationUri());
			DateString tslIssued = new DateString(tsldco.getIssueDate());
			tslInformation.setIssued(tslIssued);
			DateString tslNextUpdate = new DateString(tsldco.getNextUpdateDate());
			tslInformation.setNextUpdate(tslNextUpdate);
			if (getTslXmlData) {
				tslInformation.setTslXmlData(
						new ByteArrayB64(TSLManager.getInstance().getTSLDataXMLDocument(tsldco.getTslDataId())));
			}
			result.setTslInformation(tslInformation);

			// Añadimos la traza de auditoría indicando que ha encontrado
			// junto con la información asociada.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, true, tslInformation.getCountryRegion(),
					tslInformation.getSequenceNumber(), tslIssued, tslNextUpdate);

		}

		// Devolvemos el resultado.
		return result;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ValetRestException
	 * @see es.gob.valet.rest.services.ITslRestService#getTslInfoVersions()
	 */
	@Override
	@POST
	@Path("/getTslInfoVersions")
	@Produces(MediaType.APPLICATION_JSON)
	public TslInformationVersionsResponse getTslInfoVersions() throws ValetRestException {
		long startOperationTime = Calendar.getInstance().getTimeInMillis();
		// Se inicia el resultado a devolver
		TslInformationVersionsResponse result = null;
		// Generamos el identificador de transacción.
		String auditTransNumber = LoggingInformationNDC.registerNdcInfAndGetTransactionNumber(httpServletRequest,
				ITslRestService.SERVICENAME_GET_TSL_INFORMATION_VERSIONS);
		LOGGER.info(Language.getResRestGeneral(IRestGeneralMessages.REST_LOG043));
		try {
			// Se abre la transacción de auditoría
			CommonsServicesAuditTraces.addOpenTransactionTrace(auditTransNumber,
					IEventsCollectorConstants.SERVICE_GET_TSL_INFO_VERSIONS_ID, extractRequestByteArray());
			CommonsServicesAuditTraces.addStartRSTrace(auditTransNumber, INTERNAL_TASK_APP, INTERNAL_TASK_DELEGATE_APP);
			Map<String, Integer> tslCountryVersion = TSLManager.getInstance().getTslInfoVersions();
			result = new TslInformationVersionsResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_SERVICE_TSLINFOVERSIONS_OK);
			int numTsl = tslCountryVersion != null ? tslCountryVersion.size() : 0;
			result.setDescription(
					Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG040, new Object[] { numTsl }));
			result.setTslVersionsMap(tslCountryVersion);
			// se calcula la representación en bytes del resultado, y si la
			// obtenemos correctamente, cerramos la transacción.
			byte[] resultByteArray = buildResultByteArray(result);
			CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);
		} catch (TSLManagingException e) {
			result = new TslInformationVersionsResponse();
			result.setStatus(ITslRestServiceStatusResult.STATUS_ERROR_EXECUTING_SERVICE);
			result.setDescription(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG009,
					new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION_VERSIONS }));
			CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
					IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, result.getDescription());
			// Calculamos la representación en bytes del resultado, y si la
			// obtenemos correctamente, cerramos la transacción.
			byte[] resultByteArray = buildResultByteArray(result);
			CommonsServicesAuditTraces.addCloseTransactionTrace(auditTransNumber, resultByteArray);
			LOGGER.error(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG010,
					new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION_VERSIONS }), e);
		} catch (Exception e) {
			CommonsServicesAuditTraces.addEndRSTrace(auditTransNumber,
					IEventsCollectorConstants.RESULT_CODE_SERVICE_ERROR, e.getMessage());
			LoggingInformationNDC.unregisterNdcInf();
			throw new ValetRestException(IValetException.COD_200,
					Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG011,
							new Object[] { ITslRestService.SERVICENAME_GET_TSL_INFORMATION_VERSIONS }),
					e);
		}
		LOGGER.info(Language.getFormatResRestGeneral(IRestGeneralMessages.REST_LOG044,
				new Object[] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));
		// devolvemos el resultado
		// Limpiamos la información NDC.
		LoggingInformationNDC.unregisterNdcInf();
		return result;
	}

	/**
	 * Method that obtains the certification chain and the revocation status of
	 * the chain.
	 * 
	 * @param x509cert
	 *            Certificate where to get the certification chain
	 * @return certification chain and the revocation status of the chain.
	 * @throws ValetRestException
	 *             If the method fails.
	 */
	private List<Certificate> getCertificateChainAndRevocationStatus(X509Certificate x509cert, Date validationDate,
			String countryCode) throws ValetRestException {

		List<Certificate> listCertificates = new ArrayList<Certificate>();
		Vector<X509Certificate> certificateChain = null;
		try {

			List<X509Certificate> listX509 = new ArrayList<X509Certificate>();

			List<X509Certificate> listX509CA = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getListCertificateCA();
			if (listX509CA != null) {
				listX509.addAll(listX509CA);
			}
			
			List<X509Certificate> listX509TSL = TSLManager.getInstance().getListCertificateTSL(countryCode);
			if (listX509TSL != null) {
				listX509.addAll(listX509TSL);
			}

			certificateChain = UtilsCertificate.getCertificateChainIssuer(x509cert, listX509);

			if (certificateChain != null && certificateChain.size() > 0) {
				for (int i = 0; i < certificateChain.size(); i++) {
					// for (X509Certificate x509cer: certificateChain) {
					X509Certificate x509cer = certificateChain.get(i);
					X509Certificate issuerX509Cer = null;
					boolean isRoot = UtilsCertificate.isSelfSigned(x509cer);

					Certificate certificate = new Certificate();
					certificate.setIsRoot(isRoot);
					certificate.setCertificateValue(new ByteArrayB64(x509cer.getEncoded()));

					// Si no es root, se obtienen las evidencias de revocación
					if (!isRoot) {
						issuerX509Cer = certificateChain.get(i + 1);
						CertificateChainValidator ccv = new CertificateChainValidator();
						TslRevocationStatus tvrTemp = ccv.validateCertificateUsingDistributionPointsCertificateChain(
								x509cer, issuerX509Cer, certificateChain, UtilsCertificate.isCA(x509cer),
								UtilsCertificate.hasCertKeyPurposeTimeStamping(x509cer), validationDate);
						certificate.setTslRevocStatus(tvrTemp);
					}

					listCertificates.add(certificate);

				}
			}

		} catch (CommonUtilsException | CertificateEncodingException e) {
			throw new ValetRestException(IValetException.COD_190, Language.getFormatResCoreGeneral(
					IRestGeneralMessages.REST_LOG045, new Object[] { IKeystoreIdConstants.ID_CA_TRUSTSTORE }), e);
		} catch (Exception e) {
			throw new ValetRestException(IValetException.COD_205,
					Language.getFormatResCoreGeneral(IRestGeneralMessages.REST_LOG046, new Object[] { e.getMessage() }),
					e);
		}

		return listCertificates;
	}

}
