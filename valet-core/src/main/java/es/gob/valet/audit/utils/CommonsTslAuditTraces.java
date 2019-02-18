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
 * <b>File:</b><p>es.gob.valet.audit.utils.CommonsTslAuditTraces.java.</p>
 * <b>Description:</b><p>Class that provides methods for registering the most commons audit traces associated to the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/02/2019.
 */
package es.gob.valet.audit.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import es.gob.valet.audit.access.EventsCollector;
import es.gob.valet.audit.access.IEventsCollectorConstants;
import es.gob.valet.rest.elements.json.DateString;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;

/**
 * <p>Class that provides methods for registering the most commons audit traces associated to the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/02/2019.
 */
public final class CommonsTslAuditTraces {

	/**
	 * Attribute that represents the map that has the relations between the TSL operation id
	 * and the fields ids that must be covered.
	 */
	private static Map<Integer, String[ ]> operationFieldsNamesMap = new ConcurrentHashMap<Integer, String[ ]>();

	static {
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_TSL_TSLLOCATION, new String[ ] { IEventsCollectorConstants.FIELD_NAME_TSL_TSLLOCATION, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_GET_XML_DATA });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_TSL_COUNTRY_REGION, new String[ ] { IEventsCollectorConstants.FIELD_NAME_TSL_COUNTRYREGION, IEventsCollectorConstants.FIELD_NAME_RS_PARAM_GET_XML_DATA });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_TSL_FINDED, new String[ ] { IEventsCollectorConstants.FIELD_NAME_TSL_FINDED, IEventsCollectorConstants.FIELD_NAME_TSL_COUNTRYREGION, IEventsCollectorConstants.FIELD_NAME_TSL_SEQNUMBER, IEventsCollectorConstants.FIELD_NAME_TSL_ISSUED, IEventsCollectorConstants.FIELD_NAME_TSL_NEXTUPDATE });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_TSL_CERT_DETECTED, new String[ ] { IEventsCollectorConstants.FIELD_NAME_TSL_CERT_DETECTED, IEventsCollectorConstants.FIELD_NAME_TSL_COUNTRYREGION, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_NAME, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_SERVICE_NAME, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_SERVICE_HISTORIC_NAME });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_TSL_CERT_VALIDATED, new String[ ] { IEventsCollectorConstants.FIELD_NAME_TSL_CERT_VALIDATED, IEventsCollectorConstants.FIELD_NAME_TSL_CERT_STATUS, IEventsCollectorConstants.FIELD_NAME_TSL_CERT_STATUS_BY_TSPSERVICE, IEventsCollectorConstants.FIELD_NAME_TSL_CERT_STATUS_BY_DPAIA, IEventsCollectorConstants.FIELD_NAME_TSL_COUNTRYREGION, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_NAME, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_SERVICE_NAME, IEventsCollectorConstants.FIELD_NAME_TSL_TSP_SERVICE_HISTORIC_NAME });
	}

	/**
	 * Gets the fields names related with the input certificate operation.
	 * @param operationId Operation id to search.
	 * @return fields names related with the input certificate operation.
	 */
	public static String[ ] getOperationFieldsNames(Integer operationId) {
		return operationFieldsNamesMap.get(operationId);
	}

	/**
	 * Constructor method for the class CommonsTslAuditTraces.java.
	 */
	private CommonsTslAuditTraces() {
		super();
	}

	/**
	 * Registers a trace that shows the TSL Location used to choose the TSL.
	 * @param transactionId Audit transaction identifier.
	 * @param tslLocation TSL location to use.
	 * @param getTslXmlData Flag to indicate if is required to obtain the XML that represents the TSL.
	 */
	public static void addTslLocationOperationTrace(String transactionId, String tslLocation, Boolean getTslXmlData) {
		String getTslXmlDataString = getTslXmlData == null ? null : getTslXmlData.toString();
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_TSLLOCATION, tslLocation, getTslXmlDataString);
	}

	/**
	 * Registers a trace that shows the TSL Location used to choose the TSL.
	 * @param transactionId Audit transaction identifier.
	 * @param tslCountryRegion TSL Country/Region to use.
	 * @param getTslXmlData Flag to indicate if is required to obtain the XML that represents the TSL.
	 */
	public static void addTslCountryRegionTrace(String transactionId, String tslCountryRegion, Boolean getTslXmlData) {
		String getTslXmlDataString = getTslXmlData == null ? null : getTslXmlData.toString();
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_COUNTRY_REGION, tslCountryRegion, getTslXmlDataString);
	}

	/**
	 * Registers a trace that shows if the TSL has been finded and its info.
	 * @param transactionId Audit transaction identifier.
	 * @param tslFinded Flag that indicates if the TSL has been finded.
	 * @param tslCountryRegion Only if the TSL has been finded. TSL Country/Region code.
	 * @param sequenceNumber Only if the TSL has been finded. Sequence number of the TSL.
	 * @param issued Only if the TSL has been finded. Issued date of the TSL.
	 * @param nextUpdate Only if the TSL has been finded. Next update date of the TSL.
	 */
	public static void addTslFindedTrace(String transactionId, boolean tslFinded, String tslCountryRegion, Integer sequenceNumber, DateString issued, DateString nextUpdate) {

		if (tslFinded) {
			EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_FINDED, Boolean.toString(tslFinded), tslCountryRegion, sequenceNumber.toString(), issued.toString(), nextUpdate.toString());
		} else {
			EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_FINDED, Boolean.toString(tslFinded));
		}

	}

	/**
	 * Registers a trace that shows if the certificate has been detected in the TSL.
	 * @param transactionId Audit transaction identifier.
	 * @param certDetected Flag that indicates if the certificate has been detectedin the TSL.
	 * @param tslCountryRegion Only if the certificate has been detected. TSL Country/Region.
	 * @param tspName Only if the certificate has been detected. TSL TSP Name.
	 * @param tspServiceName Only if the certificate has been detected. TSL TSP Service Name.
	 * @param tspServiceHistoricName Only if the certificate has been detected. TSL TSP Historic Service Name.
	 */
	public static void addTslCertDetected(String transactionId, boolean certDetected, String tslCountryRegion, String tspName, String tspServiceName, String tspServiceHistoricName) {
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_CERT_DETECTED, Boolean.toString(certDetected), tslCountryRegion, tspName, tspServiceName, tspServiceHistoricName);
	}

	/**
	 * Registers a trace that shows if the certificate has been detected in the TSL.
	 * @param transactionId Audit transaction identifier.
	 * @param certValidated Flag that indicates if the certificate has been validated.
	 * @param certStatus Only if the certificate has been validated. Certificate status.
	 * @param tspServiceStatus Only if the certificate has been validated. Flag that indicates if the status has been determined by the TSP-Service-Status.
	 * @param dpAiaStatus Only if the certificate has been validated. Flag that indicates if the status has been determined by the DP-AIA from the certificate.
	 * @param tslCountryRegion Only if the certificate has been detected. TSL Country/Region.
	 * @param tspName Only if the certificate has been detected. TSL TSP Name.
	 * @param tspServiceName Only if the certificate has been detected. TSL TSP Service Name.
	 * @param tspServiceHistoricName Only if the certificate has been detected. TSL TSP Historic Service Name.
	 */
	public static void addTslCertValidated(String transactionId, boolean certValidated, Integer certStatus, Boolean tspServiceStatus, Boolean dpAiaStatus, String tslCountryRegion, String tspName, String tspServiceName, String tspServiceHistoricName) {
		String tspServiceStatusString = tspServiceStatus == null ? null : Boolean.toString(tspServiceStatus);
		String dpAiaStatusString = dpAiaStatus == null ? null : Boolean.toString(dpAiaStatus);
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_TSL_CERT_VALIDATED, Boolean.toString(certValidated), translateCertStatusCode(certStatus), tspServiceStatusString, dpAiaStatusString, tslCountryRegion, tspName, tspServiceName, tspServiceHistoricName);
	}

	/**
	 * Translate the certificate status code to string.
	 * @param certStatus Certificate status code to translate.
	 * @return String representation of the input status code.
	 */
	private static String translateCertStatusCode(Integer certStatus) {

		String result = null;

		if (certStatus != null) {

			switch (certStatus.intValue()) {
				case ITSLValidatorResult.RESULT_NOT_DETECTED:
					result = IEventsCollectorConstants.FIELD_VALUE_UNKNOWN;
					break;

				case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED:
				case ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS:
					result = IEventsCollectorConstants.FIELD_VALUE_REVOKED;
					break;

				case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID:
				case ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS:
					result = IEventsCollectorConstants.FIELD_VALUE_CERTCHAINNOTVALID;
					break;

				case ITSLValidatorResult.RESULT_DETECTED_STATE_VALID:
				default:
					result = IEventsCollectorConstants.FIELD_VALUE_OK;
					break;
			}

		}

		return result;

	}

}
