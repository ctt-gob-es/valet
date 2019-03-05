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
 * <b>File:</b><p>es.gob.valet.audit.utils.CommonsServicesAuditTraces.java.</p>
 * <b>Description:</b><p>Class that provides methods for registering the most commons audit traces associated to the services of the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.1, 05/03/2019.
 */
package es.gob.valet.audit.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import es.gob.valet.audit.access.EventsCollector;
import es.gob.valet.audit.access.IEventsCollectorConstants;
import es.gob.valet.audit.exception.AuditTraceException;
import es.gob.valet.commons.utils.CryptographicConstants;
import es.gob.valet.commons.utils.UtilsCrypto;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;

/**
 * <p>Class that provides methods for registering the most commons audit traces associated to the services of the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 05/03/2019.
 */
public final class CommonsServicesAuditTraces {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(CommonsServicesAuditTraces.class);

	/**
	 * Attribute that represents the map that has the relations between the service operation id
	 * and the fields ids that must be covered.
	 */
	private static Map<Integer, String[ ]> operationFieldsNamesMap = new ConcurrentHashMap<Integer, String[ ]>();

	static {
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_SERVICE_START_RS, new String[ ] { IEventsCollectorConstants.FIELD_NAME_APPID, IEventsCollectorConstants.FIELD_NAME_DELAPPID });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_SERVICE_END_RS, new String[ ] { IEventsCollectorConstants.FIELD_NAME_RS_RES_CODE, IEventsCollectorConstants.FIELD_NAME_RS_RES_DESC });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_SERVICE_DCITIV_PARAMS, new String[ ] { IEventsCollectorConstants.FIELD_NAME_RS_PARAM_GETINFO, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_CHECKREVSTATUS, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_RETREVEVID, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_CRLS, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_OCSPS });
	}

	/**
	 * Constructor method for the class CommonsServicesAuditTraces.java.
	 */
	private CommonsServicesAuditTraces() {
		super();
	}

	/**
	 * Gets the fields names related with the input service operation.
	 * @param operationId Operation id to search.
	 * @return fields names related with the input service operation.
	 */
	public static String[ ] getOperationFieldsNames(Integer operationId) {
		return operationFieldsNamesMap.get(operationId);
	}

	/**
	 * Registers a trace to set the transaction opening in audit.
	 * @param transactionId	Audit transaction identifier. If this parameter is not properly defined, then
	 * this method do nothing.
	 * @param serviceId	Service identifier.
	 * @param messageByteArray Message byte array. A SHA-512 algorithm is going to be applied to this message
	 * to be stored in the audit file event.
	 * @throws AuditTraceException If the transactionId is <code>null</code>/empty or in case of some error computing the hash of the message.
	 */
	public static void addOpenTransactionTrace(String transactionId, int serviceId, byte[ ] messageByteArray) throws AuditTraceException {

		if (UtilsStringChar.isNullOrEmptyTrim(transactionId)) {
			throw new AuditTraceException(IValetException.COD_202, Language.getResCoreGeneral(ICoreGeneralMessages.CSAT_000));
		} else {
			String hashMessageInBase64 = null;
			if (messageByteArray != null && messageByteArray.length > 0) {
				try {
					hashMessageInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, messageByteArray, null);
					EventsCollector.openTransaction(transactionId, serviceId, CryptographicConstants.HASH_ALGORITHM_SHA512, hashMessageInBase64);
				} catch (CommonUtilsException e) {
					throw new AuditTraceException(IValetException.COD_202, e.getMessage(), e);
				}
			}
		}

	}

	/**
	 * Registers a trace associated to audit transaction closing.
	 * @param transactionId	Audit transaction identifier. If this parameter is not properly defined, then
	 * this method do nothing.
	 * @param messageByteArray Message byte array. A SHA-512 algorithm is going to be applied to this message
	 * to be stored in the audit file event. If there is some error computing the hash, then the transaction
	 * is not closed.
	 */
	public static void addCloseTransactionTrace(String transactionId, byte[ ] messageByteArray) {

		if (UtilsStringChar.isNullOrEmptyTrim(transactionId)) {
			LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.CSAT_002));
		} else {
			try {
				String hashMessageInBase64 = null;
				if (messageByteArray != null && messageByteArray.length > 0) {
					hashMessageInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, messageByteArray, null);
					EventsCollector.closeTransaction(transactionId, CryptographicConstants.HASH_ALGORITHM_SHA512, hashMessageInBase64);
				} else {
					LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.CSAT_001));
				}
			} catch (CommonUtilsException e) {
				LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.CSAT_001));
			}
		}

	}

	/**
	 * Registers a trace associated to a starting rest service transaction.
	 * @param transactionId	Audit transaction identifier.
	 * @param applicationId Application identifier used in the rest service.
	 * @param delegatedAppId Delegated application that identifies the client.
	 */
	public static void addStartRSTrace(String transactionId, String applicationId, String delegatedAppId) {
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_SERVICE_START_RS, applicationId, delegatedAppId);
	}

	/**
	 * Registers a trace with the result of the rest service.
	 * @param transactionId	Audit transaction identifier.
	 * @param resultCode Result code of the rest service. It could be {@value IEventsCollectorConstants#RESULT_CODE_SERVICE_OK} or {@value IEventsCollectorConstants#RESULT_CODE_SERVICE_ERROR}.
	 * @param resultDesc Description of the result of the rest service. It could be an error description.
	 */
	public static void addEndRSTrace(String transactionId, String resultCode, String resultDesc) {
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_SERVICE_END_RS, resultCode, resultDesc);
	}

	/**
	 * Registers a trace with some parameters information of the Rest Service: detectCertInTslInfoAndValidation.
	 * @param transactionId Audit transaction identifier.
	 * @param getInfo Flag that indicates if it is necessary to get the certificate information in response.
	 * @param checkRevStatus Flag that indicates if it is necessary to check the revocation status of the input certificate.
	 * @param returnRevocationEvidence Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevStatus} is <code>true</code>).
	 * @param thereIsCrls Flag that indicates if there is some CRL to use in the request.
	 * @param thereIsBasicOcspResponses Flag that indicates if there is some BasicOcspResponse to use in the request.
	 */
	public static void addRSDetectCertParamsInfo(String transactionId, boolean getInfo, boolean checkRevStatus, boolean returnRevocationEvidence, boolean thereIsCrls, boolean thereIsBasicOcspResponses) {
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_SERVICE_DCITIV_PARAMS, Boolean.toString(getInfo), Boolean.toString(checkRevStatus), Boolean.toString(returnRevocationEvidence), Boolean.toString(thereIsCrls), Boolean.toString(thereIsBasicOcspResponses));
	}

}
