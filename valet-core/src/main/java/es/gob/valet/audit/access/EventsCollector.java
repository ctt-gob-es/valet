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
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.4, 03/06/2024.
 */
package es.gob.valet.audit.access;

import java.util.Calendar;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import es.gob.valet.audit.utils.CommonsCertificatesAuditTraces;
import es.gob.valet.audit.utils.CommonsServicesAuditTraces;
import es.gob.valet.audit.utils.CommonsTslAuditTraces;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.rest.elements.json.DateString;

/**
 * <p>Class that represents an audit events collector. This class must be
 * used to register all the audit traces occurred in the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 03/06/2024.
 */
public final class EventsCollector {

	/**
	 * Constant attribute that represents the name for the audit logger.
	 */
	private static final String AUDIT_LOGGER_NAME = "Valet-Audit";

	/**
	 * Attribute that represents the audit logger to use.
	 */
	private static final Logger AUDIT_LOGGER = LogManager.getLogger(AUDIT_LOGGER_NAME);

	/**
	 * Constants attribute that represents the separator used in the event file for separating the field identifier and value.
	 */
	private static final char TOKEN_SEPARATOR = UtilsStringChar.SYMBOL_EQUAL;

	/**
	 * Constants that represents the separator used in the event file for separating of audit fields.
	 */
	private static final char SEPARATOR = UtilsStringChar.SYMBOL_SEMICOLON;

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
	 * @param signTransactionId Sign transaction identifier.
	 * @param serviceId	Service identifier.
	 * @param hashAlgorithm Hash algorithm applied to calculate the hash message.
	 * @param hashMessageB64 Hash message Base 64 representation.
	 */
	public static void openTransaction(String transactionId, String signTransactionId, int serviceId, String hashAlgorithm, String hashMessageB64) {

		AUDIT_LOGGER.info(createTrace(transactionId, signTransactionId, serviceId, EventsCollectorConstants.OPERATION_SERVICE_OPEN_TRACE, null, new DateString(Calendar.getInstance().getTime()), hashMessageB64, hashAlgorithm));

	}

	/**
	 * Method that indicates the end of an audit transaction.
	 * @param transactionId	Audit transaction identifier. If this parameter is not properly defined, then
	 * this method do nothing.
	 * @param hashAlgorithm Hash algorithm applied to calculate the hash message.
	 * @param hashMessageB64 Hash message Base 64 representation.
	 */
	public static void closeTransaction(String transactionId, String hashAlgorithm, String hashMessageB64) {

		AUDIT_LOGGER.info(createTrace(transactionId, null, 0, EventsCollectorConstants.OPERATION_SERVICE_CLOSE_TRACE, null, new DateString(Calendar.getInstance().getTime()), hashMessageB64, hashAlgorithm));

	}

	/**
	 * Add an audit trace to the supplied transaction.
	 * @param transactionId	Identifier of the transaction that owns the trace.
	 * @param operationId Operation identifier.
	 * @param fieldsValues List of the audit fields contained in the trace.
	 */
	public static void addTrace(String transactionId, int operationId, String... fieldsValues) {

		// Continuamos si el id de transacción no es nulo o vacío,
		// y se dispone de un listado de campos.
		if (!UtilsStringChar.isNullOrEmptyTrim(transactionId) && fieldsValues != null && fieldsValues.length > 0) {

			AUDIT_LOGGER.info(createTrace(transactionId, null, 0, operationId, createAuditFields(operationId, fieldsValues), new DateString(Calendar.getInstance().getTime()), null, null));

		}

	}

	/**
	 * Creates a list of {@link AuditField} with names and values.
	 * @param operationId Operation identifier.
	 * @param fieldsValues list with values of fields.
	 * @return a list with names and values of fields.
	 */
	private static AuditField[ ] createAuditFields(int operationId, String... fieldsValues) {

		AuditField[ ] result = null;

		// Si la lista de valores no es nula ni vacía...
		if (fieldsValues != null && fieldsValues.length > 0) {

			// Obtenemos los nombres de los campos...
			String[ ] orderedFieldNames = getOrderedFieldsNamesForOperation(operationId);
			// Si la lista de ids no es nula ni vacía...
			if (orderedFieldNames != null && orderedFieldNames.length > 0) {

				// Creamos el array resultante...
				result = new AuditField[Math.min(orderedFieldNames.length, fieldsValues.length)];

				// Vamos recorriendo los ids y asignándoles el valor
				// correspondiente...
				for (int index = 0; index < orderedFieldNames.length && index < fieldsValues.length; index++) {
					result[index] = new AuditField(orderedFieldNames[index], encodeStringForAuditField(fieldsValues[index]));
				}

			}

		}

		return result;

	}

	/**
	 * Modify the input string removing the characters not allowed in audit fields values
	 * and replacing these with others.
	 * @param auditFieldValue String to analyze.
	 * @return Input string modified.
	 */
	private static String encodeStringForAuditField(String auditFieldValue) {

		String result = null;

		if (auditFieldValue != null) {

			StringBuilder sb = new StringBuilder(auditFieldValue);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SPECIAL_LINE_BREAK_STRING, UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SYMBOL_SEMICOLON_STRING, UtilsStringChar.SYMBOL_PIPE_STRING);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SYMBOL_OPEN_SQUARE_BRACKET_STRING, UtilsStringChar.SYMBOL_HYPHEN_STRING);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING, UtilsStringChar.SYMBOL_HYPHEN_STRING);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING, UtilsStringChar.SYMBOL_HYPHEN_STRING);
			encodeStringForAuditFieldReplaceString(sb, UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING, UtilsStringChar.SYMBOL_HYPHEN_STRING);
			result = sb.toString();

		}

		return result;

	}

	/**
	 * Auxiliar method to replace a string with other in a StringBuilder.
	 * @param sb String Builder instance to check and update.
	 * @param oldString String that must be replaced.
	 * @param newString String to add instead of.
	 */
	private static void encodeStringForAuditFieldReplaceString(StringBuilder sb, String oldString, String newString) {

		int index = sb.indexOf(oldString);
		while (index >= 0) {
			sb.delete(index, index + oldString.length());
			sb.insert(index, newString);
			index = sb.indexOf(oldString, index + newString.length());
		}

	}

	/**
	 * Gets the fields names needed for the input operation.
	 * @param operationId Operation id to search.
	 * @return the fields names needed for the input operation. <code>null</code> if there is not,
	 * or is not recognized the operation.
	 */
	private static String[ ] getOrderedFieldsNamesForOperation(int operationId) {

		String[ ] result = null;

		switch (operationId) {
			// Si es una operación de servicio...
			case EventsCollectorConstants.OPERATION_SERVICE_OPEN_TRACE:
			case EventsCollectorConstants.OPERATION_SERVICE_CLOSE_TRACE:
			case EventsCollectorConstants.OPERATION_SERVICE_START_RS:
			case EventsCollectorConstants.OPERATION_SERVICE_END_RS:
			case EventsCollectorConstants.OPERATION_SERVICE_DCITIV_PARAMS:
				result = CommonsServicesAuditTraces.getOperationFieldsNames(operationId);
				break;

			// Si es una operación de certificado...
			case EventsCollectorConstants.OPERATION_CERT_INFO:
			case EventsCollectorConstants.OPERATION_CERT_ISTSA:
			case EventsCollectorConstants.OPERATION_CERT_BASICOCSPRESP_INFO:
			case EventsCollectorConstants.OPERATION_CERT_CRL_INFO:
			case EventsCollectorConstants.OPERATION_CERT_MAPPING_FIELDS:
				result = CommonsCertificatesAuditTraces.getOperationFieldsNames(operationId);
				break;

			// Si es una operación de TSL...
			case EventsCollectorConstants.OPERATION_TSL_TSLLOCATION:
			case EventsCollectorConstants.OPERATION_TSL_COUNTRY_REGION:
			case EventsCollectorConstants.OPERATION_TSL_FINDED:
			case EventsCollectorConstants.OPERATION_TSL_CERT_DETECTED:
			case EventsCollectorConstants.OPERATION_TSL_CERT_VALIDATED:
				result = CommonsTslAuditTraces.getOperationFieldsNames(operationId);
				break;

			default:
				break;
		}

		return result;

	}

	/**
	 * Create a string representation of a trace audit. The trace matches with the pattern:
	 * <pre>'ID=Transaction_id;SV=Service_Id;OP=operationId;fieldsNames=fieldsValues;date(in format: {@value UtilsDate#FORMAT_DATE_TIME_JSON})'</pre>
	 * @param transactionId Transaction identifier.
	 * @param serviceId Service identifier.
	 * @param operationId Operation identifier.
	 * @param fields Array with fields (ids and values).
	 * @param dateString Date to assign to this trace.
	 * @param messageHashInBase64 Message hash in Base 64.
	 * @param hashAlgorithmApplied Hash algorithm applied to the message.
	 * @return a string representation of a trace audit.
	 */
	private static String createTrace(String transactionId, String signTransactionId, int serviceId, int operationId, AuditField[ ] fields, DateString dateString, String messageHashInBase64, String hashAlgorithmApplied) {

		// Creamos el StringBuilder que construye la traza.
		StringBuilder traceSb = new StringBuilder();

		// Siempre se añade el identificador de transacción.
		traceSb.append(EventsCollectorConstants.FIELD_NAME_ID).append(TOKEN_SEPARATOR).append(transactionId).append(SEPARATOR);
		
		//Se añade el identificador de @firma si no es null
		if(signTransactionId != null) {
			traceSb.append(EventsCollectorConstants.FIELD_NAME_TRASN_ID).append(TOKEN_SEPARATOR).append(signTransactionId).append(SEPARATOR);
		}

		// Según la operación...
		switch (operationId) {

			// Si se trata de apertura de traza, indicamos el servicio,
			// el nombre de la operación 'open', y el hash de la petición.
			case EventsCollectorConstants.OPERATION_SERVICE_OPEN_TRACE:
				traceSb.append(EventsCollectorConstants.FIELD_NAME_SV).append(TOKEN_SEPARATOR).append(serviceId).append(SEPARATOR);
				traceSb.append(EventsCollectorConstants.FIELD_NAME_OP).append(TOKEN_SEPARATOR).append(EventsCollectorConstants.FIELD_VALUE_OPEN_TRACE).append(SEPARATOR);
				if (messageHashInBase64 != null) {
					traceSb.append(EventsCollectorConstants.FIELD_NAME_HA).append(TOKEN_SEPARATOR).append(hashAlgorithmApplied).append(SEPARATOR);
					traceSb.append(EventsCollectorConstants.FIELD_NAME_HM).append(TOKEN_SEPARATOR).append(messageHashInBase64).append(SEPARATOR);
				}
				break;

			// Si se trata de apertura de traza, indicamos el nombre de la
			// operación 'close', y el hash de la respuesta.
			case EventsCollectorConstants.OPERATION_SERVICE_CLOSE_TRACE:
				traceSb.append(EventsCollectorConstants.FIELD_NAME_OP).append(TOKEN_SEPARATOR).append(EventsCollectorConstants.FIELD_VALUE_CLOSE_TRACE).append(SEPARATOR);
				if (messageHashInBase64 != null) {
					traceSb.append(EventsCollectorConstants.FIELD_NAME_HA).append(TOKEN_SEPARATOR).append(hashAlgorithmApplied).append(SEPARATOR);
					traceSb.append(EventsCollectorConstants.FIELD_NAME_HM).append(TOKEN_SEPARATOR).append(messageHashInBase64).append(SEPARATOR);
				}
				break;

			// Si no es ni apertura ni cierre, simplemente indicamos
			// la operación.
			default:
				traceSb.append(EventsCollectorConstants.FIELD_NAME_OP).append(TOKEN_SEPARATOR).append(operationId).append(SEPARATOR);
				break;
		}

		// Se añaden la lista de campos si no es nula o vacía.
		if (fields != null && fields.length > 0) {
			for (AuditField auditField: fields) {
				if (!UtilsStringChar.isNullOrEmpty(auditField.getFieldValue())) {
					traceSb.append(auditField.getFieldName()).append(TOKEN_SEPARATOR).append(auditField.getFieldValue()).append(SEPARATOR);
				}
			}
		}

		// Finalmente añadimos la fecha.
		traceSb.append(dateString.toString());

		// Construimos el resultado.
		String result = traceSb.toString();

		// Limpiamos el StringBuffer.
		traceSb.setLength(0);
		traceSb.trimToSize();

		// Se devuelve el resultado.
		return result;

	}

}
