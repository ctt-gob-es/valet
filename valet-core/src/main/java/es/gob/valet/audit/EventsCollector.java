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
 * <b>File:</b><p>es.gob.valet.audit.EventsCollector.java.</p>
 * <b>Description:</b><p>Class that represents an audit events collector. This class must be
 * used to register all the audit traces occurred in the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/02/2019.
 */
package es.gob.valet.audit;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.CryptographicConstants;
import es.gob.valet.commons.utils.UtilsCrypto;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.rest.elements.json.DateString;

/**
 * <p>Class that represents an audit events collector. This class must be
 * used to register all the audit traces occurred in the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/02/2019.
 */
public final class EventsCollector {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(EventsCollector.class);

	/**
	 * Constant attribute that represents the name for the audit logger.
	 */
	private static final String AUDIT_LOGGER_NAME = "Valet-Audit";

	/**
	 * Attribute that represents the audit logger to use.
	 */
	private static final Logger AUDIT_LOGGER = Logger.getLogger(AUDIT_LOGGER_NAME);

	/**
	 * Constant attribute that represents the token used to indicate the transaction identifier in the event file.
	 */
	private static final String TRANSACTION_IDENTIFIER_TOKEN = "ID";

	/**
	 * Constants attribute that represents the separator used in the event file for separating the field identifier and value.
	 */
	private static final char TOKEN_SEPARATOR = UtilsStringChar.SYMBOL_EQUAL;

	/**
	 * Constants that represents the separator used in the event file for separating of audit fields.
	 */
	private static final char SEPARATOR = UtilsStringChar.SYMBOL_SEMICOLON;

	/**
	 * Constant attribute that represents the token used to indicate the open transaction trace.
	 */
	private static final String OPEN_TRACE_TOKEN = "open";

	/**
	 * Constant attribute that represents the token used to indicate the close transaction trace.
	 */
	private static final String CLOSE_TRACE_TOKEN = "close";

	/**
	 * Constant attribute that represents the token used to indicate the service identifier in the event file.
	 */
	private static final String SERVICE_IDENTIFIER_TOKEN = "SV";

	/**
	 * Constant attribute that represents the token used to indicate the operation identifier in the event file.
	 */
	private static final String OPERATION_IDENTIFIER_TOKEN = "OP";

	/**
	 * Constant attribute that represents the token used to indicate the message hash algorithm identifier in the event file.
	 */
	private static final String MESSAGE_HASH_ALGORITHM_IDENTIFIER_TOKEN = "HA";

	/**
	 * Constant attribute that represents the token used to indicate the message hash identifier in the event file.
	 */
	private static final String MESSAGE_HASH_IDENTIFIER_TOKEN = "MH";

	/**
	 * Constructor method for the class EventsCollector.java.
	 */
	private EventsCollector() {
		super();
	}

	/**
	 * Method that indicates the start of an audit transaction.
	 * @param transactionId	Audit transaction identifier. If this parameter is not properly defined, then
	 * this method do nothing.
	 * @param serviceId	Service identifier.
	 * @param messageByteArray Message byte array. A SHA-512 algorithm is going to be applied to this message
	 * to be stored in the audit file event.
	 * @throws CommonUtilsException In case of some error computing the hash of the message.
	 */
	public static void openTransaction(String transactionId, int serviceId, byte[ ] messageByteArray) throws CommonUtilsException {

		if (UtilsStringChar.isNullOrEmptyTrim(transactionId)) {
			LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.EVENTS_COLLECTOR_000));
		} else {
			String messageHashInBase64 = null;
			if (messageByteArray != null && messageByteArray.length > 0) {
				messageHashInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, messageByteArray, null);
			}
			AUDIT_LOGGER.info(createTrace(transactionId, serviceId, OPEN_TRACE_TOKEN, null, new DateString(Calendar.getInstance().getTime()), messageHashInBase64, CryptographicConstants.HASH_ALGORITHM_SHA512));
		}

	}

	/**
	 * Method that indicates the end of an audit transaction.
	 * @param transactionId	Audit transaction identifier. If this parameter is not properly defined, then
	 * this method do nothing.
	 * @param messageByteArray Message byte array. A SHA-512 algorithm is going to be applied to this message
	 * to be stored in the audit file event. If there is some error computing the hash, then the transaction
	 * is not closed.
	 */
	public static void closeTransaction(String transactionId, byte[ ] messageByteArray) {

		if (UtilsStringChar.isNullOrEmptyTrim(transactionId)) {
			LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.EVENTS_COLLECTOR_000));
		} else {
			try {
				String messageHashInBase64 = null;
				if (messageByteArray != null && messageByteArray.length > 0) {
					messageHashInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, messageByteArray, null);
				}
				AUDIT_LOGGER.info(createTrace(transactionId, 0, CLOSE_TRACE_TOKEN, null, new DateString(Calendar.getInstance().getTime()), messageHashInBase64, CryptographicConstants.HASH_ALGORITHM_SHA512));
			} catch (CommonUtilsException e) {
				LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.EVENTS_COLLECTOR_001));
			}
		}

	}

	/**
	 * Create a string representation of a trace audit. The trace matches with the pattern:
	 * <pre>'ID=Transaction_id;SV=Service_Id;OP=operationId;fieldsNames=fieldsValues;date(in format: {@value UtilsDate#FORMAT_DATE_TIME_JSON})'</pre>
	 * @param transactionId Transaction identifier.
	 * @param serviceId Service identifier.
	 * @param operationId Operation identifier.
	 * @param fields list with fields (names and values).
	 * @param dateString Date to assign to this trace.
	 * @param messageHashInBase64 Message hash in Base 64.
	 * @param hashAlgorithmApplied Hash algorithm applied to the message.
	 * @return a string representation of a trace audit.
	 */
	private static String createTrace(String transactionId, int serviceId, String operationId, List<AuditField> fields, DateString dateString, String messageHashInBase64, String hashAlgorithmApplied) {
		StringBuilder traceSb = new StringBuilder();
		traceSb.append(TRANSACTION_IDENTIFIER_TOKEN).append(TOKEN_SEPARATOR).append(transactionId).append(SEPARATOR);
		if (OPEN_TRACE_TOKEN.equals(operationId)) {
			traceSb.append(SERVICE_IDENTIFIER_TOKEN).append(TOKEN_SEPARATOR).append(serviceId).append(SEPARATOR);
		}
		traceSb.append(OPERATION_IDENTIFIER_TOKEN).append(TOKEN_SEPARATOR).append(operationId).append(SEPARATOR);
		if ((OPEN_TRACE_TOKEN.equals(operationId) || CLOSE_TRACE_TOKEN.equals(operationId)) && messageHashInBase64 != null) {
			traceSb.append(MESSAGE_HASH_ALGORITHM_IDENTIFIER_TOKEN).append(TOKEN_SEPARATOR).append(hashAlgorithmApplied).append(SEPARATOR);
			traceSb.append(MESSAGE_HASH_IDENTIFIER_TOKEN).append(TOKEN_SEPARATOR).append(messageHashInBase64).append(SEPARATOR);
		}
		if (fields != null && !fields.isEmpty()) {
			for (AuditField auditField: fields) {
				if (!UtilsStringChar.isNullOrEmpty(auditField.getFieldValue())) {
					traceSb.append(auditField.getFieldId()).append(TOKEN_SEPARATOR).append(auditField.getFieldValue()).append(SEPARATOR);
				}
			}
		}
		traceSb.append(dateString.toString());
		String result = traceSb.toString();
		traceSb.setLength(0);
		traceSb.trimToSize();
		return result;
	}

}
