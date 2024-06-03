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
 * <b>File:</b><p>es.gob.valet.audit.IEventsCollectorConstants.java.</p>
 * <b>Description:</b><p>int interface that defines all the public constants needed to work with
 * audit transactions.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.6, 03/06/2024.
 */
package es.gob.valet.audit.access;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that defines all the public constants needed to work with
 * audit transactions.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 03/06/2024.
 */
public class EventsCollectorConstants {

	/**
	 * Constant attribute that represents the id for the rest service: Get TSL Information.
	 */
	public static final int  SERVICE_GET_TSL_INFORMATION_ID = 1;

	/**
	 * Constant attribute that represents the id for the rest service: Detect Certificate in TSL and Validate.
	 */
	public static final int SERVICE_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION_ID = 2;
	/**
	 * Constant attribute that represents the id for the rest service: Get TSL Info Versions.
	 */
	public static final int SERVICE_GET_TSL_INFO_VERSIONS_ID = 3;

	/**
	 * Constant attribute that represents the oepration to open a new trace/transaction.
	 */
	public static final int OPERATION_SERVICE_OPEN_TRACE = 0;

	/**
	 * Constant attribute that represents the operation to close a trace/transaction.
	 */
	public static final int OPERATION_SERVICE_CLOSE_TRACE = 1;

	/**
	 * Constant attribute that represents the operation to start a Rest Service.
	 */
	public static final int OPERATION_SERVICE_START_RS = 2;

	/**
	 * Constant attribute that represents the operation to end a Rest Service.
	 */
	public static final int OPERATION_SERVICE_END_RS = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents the operation for show parameters of the Rest Service: detectCertint staticslInfoAndValidation.
	 */
	public static final int OPERATION_SERVICE_DCITIV_PARAMS = NumberConstants.NUM4;

	/**
	 * Constant attribute that represents the operation to show the information about the input certificate.
	 */
	public static final int OPERATION_CERT_INFO = NumberConstants.NUM5;

	/**
	 * Constant attribute that represents the operation to show the information about the input certificate.
	 */
	public static final int OPERATION_CERT_ISTSA = NumberConstants.NUM6;

	/**
	 * Constant attribute that represents the operation to show the information about the basic ocsp
	 * response used to validate the certificate.
	 */
	public static final int OPERATION_CERT_BASICOCSPRESP_INFO = NumberConstants.NUM7;

	/**
	 * Constant attribute that represents the operation to show the information about the CRL used to validate
	 * the certificate.
	 */
	public static final int OPERATION_CERT_CRL_INFO = NumberConstants.NUM8;

	/**
	 * Constant attribute that represents the operation to show the mapping fields obtained for a specific certificate.
	 */
	public static final int OPERATION_CERT_MAPPING_FIELDS = NumberConstants.NUM9;

	/**
	 * Constant attribute that represents the operation to show the TSL Location used to choose a TSL.
	 */
	public static final int OPERATION_TSL_TSLLOCATION = NumberConstants.NUM10;

	/**
	 * Constant attribute that represents the operation to show the TSL Country/Region used to choose a TSL.
	 */
	public static final int OPERATION_TSL_COUNTRY_REGION = NumberConstants.NUM11;

	/**
	 * Constant attribute that represents the operation to show if the TSL has been finded and its information.
	 */
	public static final int OPERATION_TSL_FINDED = NumberConstants.NUM12;

	/**
	 * Constant attribute that represents the operation to show if the certificate has been detected in the TSL.
	 */
	public static final int OPERATION_TSL_CERT_DETECTED = NumberConstants.NUM13;

	/**
	 * Constant attribute that represents the operation to show if the certificate has been validated with the TSL.
	 */
	public static final int OPERATION_TSL_CERT_VALIDATED = NumberConstants.NUM14;

	/**
	 * Constant attribute that represents the code for the rest service result: 'OK'.
	 */
	public static final String RESULT_CODE_SERVICE_OK = "OK";

	/**
	 * Constant attribute that represents the code for the rest service result: 'ERROR'.
	 */
	public static final String RESULT_CODE_SERVICE_ERROR = "ERROR";

	/**
	 * Constant attribute that represents the field name 'ID'.
	 */
	public static final String FIELD_NAME_ID = "ID";

	/**
	 * Constant attribute that represents the field name 'SV'.
	 */
	public static final String FIELD_NAME_SV = "SV";

	/**
	 * Constant attribute that represents the field name 'OP'.
	 */
	public static final String FIELD_NAME_OP = "OP";

	/**
	 * Constant attribute that represents the field name 'HA'.
	 */
	public static final String FIELD_NAME_HA = "HA";

	/**
	 * Constant attribute that represents the field name 'HM'.
	 */
	public static final String FIELD_NAME_HM = "HM";

	/**
	 * Constant attribute that represents the field name 'APPID'.
	 */
	public static final String FIELD_NAME_APPID = "APPID";

	/**
	 * Constant attribute that represents the field name 'DELAPPID'.
	 */
	public static final String FIELD_NAME_DELAPPID = "DELAPPID";

	/**
	 * Constant attribute that represents the field name 'RS_RES_CODE'.
	 */
	public static final String FIELD_NAME_RS_RES_CODE = "RS_RES_CODE";

	/**
	 * Constant attribute that represents the field name 'RS_RES_DESC'.
	 */
	public static final String FIELD_NAME_RS_RES_DESC = "RS_RES_DESC";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_GETINFO'.
	 */
	public static final String FIELD_NAME_RS_PARAM_GETINFO = "RS_PARAM_GETINFO";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_CHECKREVSTATUS'.
	 */
	public static final String FIELD_NAME_RS_PARAM_CHECKREVSTATUS = "RS_PARAM_CHECKREVSTATUS";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_RETREVEVID'.
	 */
	public static final String FIELD_NAME_RS_PARAM_RETREVEVID = "RS_PARAM_RETREVEVID";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_CRLS'.
	 */
	public static final String FIELD_NAME_RS_PARAM_CRLS = "RS_PARAM_CRLS";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_OCSPS'.
	 */
	public static final String FIELD_NAME_RS_PARAM_OCSPS = "RS_PARAM_OCSPS";

	/**
	 * Constant attribute that represents the field name 'RS_PARAM_GETXMLDATA'.
	 */
	public static final String FIELD_NAME_RS_PARAM_GET_XML_DATA = "RS_PARAM_GETXMLDATA";

	/**
	 * Constant attribute that represents the field name 'CERT_ISCA'.
	 */
	public static final String FIELD_NAME_CERT_ISCA = "CERT_ISCA";

	/**
	 * Constant attribute that represents the field name 'CERT_COUNTRY'.
	 */
	public static final String FIELD_NAME_CERT_COUNTRY = "CERT_COUNTRY";

	/**
	 * Constant attribute that represents the field name 'CERT_ISSUER'.
	 */
	public static final String FIELD_NAME_CERT_ISSUER = "CERT_ISSUER";

	/**
	 * Constant attribute that represents the field name 'CERT_SUBJECT'.
	 */
	public static final String FIELD_NAME_CERT_SUBJECT = "CERT_SUBJECT";

	/**
	 * Constant attribute that represents the field name 'CERT_SN'.
	 */
	public static final String FIELD_NAME_CERT_SERIAL_NUMBER = "CERT_SN";

	/**
	 * Constant attribute that represents the field name 'CERT_FROM'.
	 */
	public static final String FIELD_NAME_CERT_VALID_FROM = "CERT_FROM";

	/**
	 * Constant attribute that represents the field name 'CERT_TO'.
	 */
	public static final String FIELD_NAME_CERT_VALID_TO = "CERT_TO";

	/**
	 * Constant attribute that represents the field name 'CERT_ISTSA'.
	 */
	public static final String FIELD_NAME_CERT_ISTSA = "CERT_ISTSA";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_URL'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_URL = "CERT_REVEVID_URL";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_HA'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_HA = "CERT_REVEVID_HA";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_HASH'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_HASH = "CERT_REVEVID_HASH";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_OCSP_NONCE'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_OCSP_NONCE = "CERT_REVEVID_OCSP_NONCE";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_OCSP_RESPID'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_OCSP_RESPID = "CERT_REVEVID_OCSP_RESPID";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_OCSP_PRODUCEDAT'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_OCSP_PRODUCEDAT = "CERT_REVEVID_OCSP_PRODUCEDAT";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_CRL_ISSUER'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_CRL_ISSUER = "CERT_REVEVID_CRL_ISSUER";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_CRL_NUMBER'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_CRL_CRLNUMBER = "CERT_REVEVID_CRL_NUMBER";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_CRL_ISSUED'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_CRL_ISSUEDDATE = "CERT_REVEVID_CRL_ISSUED";

	/**
	 * Constant attribute that represents the field name 'CERT_REVEVID_CRL_NEXTUPDATE'.
	 */
	public static final String FIELD_NAME_CERT_REVEVID_CRL_NEXTUPDATEDATE = "CERT_REVEVID_CRL_NEXTUPDATE";

	/**
	 * Constant attribute that represents the constant value field 'CERT_FIELDS'.
	 */
	public static final String FIELD_NAME_CERT_FIELDS = "CERT_FIELDS";

	/**
	 * Constant attribute that represents the field name 'TSL_TSLLOCATION'.
	 */
	public static final String FIELD_NAME_TSL_TSLLOCATION = "TSL_TSLLOCATION";

	/**
	 * Constant attribute that represents the field name 'TSL_CR'.
	 */
	public static final String FIELD_NAME_TSL_COUNTRYREGION = "TSL_CR";

	/**
	 * Constant attribute that represents the field name 'TSL_FINDED'.
	 */
	public static final String FIELD_NAME_TSL_FINDED = "TSL_FINDED";

	/**
	 * Constant attribute that represents the field name 'TSL_SEQNUMBER'.
	 */
	public static final String FIELD_NAME_TSL_SEQNUMBER = "TSL_SEQNUMBER";

	/**
	 * Constant attribute that represents the field name 'TSL_ISSUED'.
	 */
	public static final String FIELD_NAME_TSL_ISSUED = "TSL_ISSUED";

	/**
	 * Constant attribute that represents the field name 'TSL_NEXTUPDATE'.
	 */
	public static final String FIELD_NAME_TSL_NEXTUPDATE = "TSL_NEXTUPDATE";

	/**
	 * Constant attribute that represents the field name 'TSL_CERTDETECTED'.
	 */
	public static final String FIELD_NAME_TSL_CERT_DETECTED = "TSL_CERTDETECTED";

	/**
	 * Constant attribute that represents the field name 'TSL_TSPNAME'.
	 */
	public static final String FIELD_NAME_TSL_TSP_NAME = "TSL_TSPNAME";

	/**
	 * Constant attribute that represents the field name 'TSL_TSPSERVICENAME'.
	 */
	public static final String FIELD_NAME_TSL_TSP_SERVICE_NAME = "TSL_TSPSERVICENAME";

	/**
	 * Constant attribute that represents the field name 'TSL_TSPSERVICEHISTNAME'.
	 */
	public static final String FIELD_NAME_TSL_TSP_SERVICE_HISTORIC_NAME = "TSL_TSPSERVICEHISTNAME";

	/**
	 * Constant attribute that represents the field name 'TSL_CERTVALIDATED'.
	 */
	public static final String FIELD_NAME_TSL_CERT_VALIDATED = "TSL_CERTVALIDATED";

	/**
	 * Constant attribute that represents the field name 'TSL_CERTSTATUS'.
	 */
	public static final String FIELD_NAME_TSL_CERT_STATUS = "TSL_CERTSTATUS";

	/**
	 * Constant attribute that represents the field name 'TSL_CERTSTATUSBYTSPSERVICE'.
	 */
	public static final String FIELD_NAME_TSL_CERT_STATUS_BY_TSPSERVICE = "TSL_CERTSTATUSBYTSPSERVICE";

	/**
	 * Constant attribute that represents the field name 'TSL_CERTSTATUSBYDPAIA'.
	 */
	public static final String FIELD_NAME_TSL_CERT_STATUS_BY_DPAIA = "TSL_CERTSTATUSBYDPAIA";

	/**
	 * Constant attribute that represents the constant value field 'ERROR'.
	 */
	public static final String FIELD_VALUE_ERROR = "ERROR";

	/**
	 * Constant attribute that represents the constant value field 'OK'.
	 */
	public static final String FIELD_VALUE_OK = "OK";

	/**
	 * Constant attribute that represents the constant value field 'UNKNOWN'.
	 */
	public static final String FIELD_VALUE_UNKNOWN = "UNKNOWN";

	/**
	 * Constant attribute that represents the constant value field 'REVOKED'.
	 */
	public static final String FIELD_VALUE_REVOKED = "REVOKED";

	/**
	 * Constant attribute that represents the constant value field 'CERTCHAINNOTVALID'.
	 */
	public static final String FIELD_VALUE_CERTCHAINNOTVALID = "CERTCHAINNOTVALID";

	/**
	 * Constant attribute that represents the constant value field 'FROM_REQUEST'.
	 */
	public static final String FIELD_VALUE_FROM_REQUEST = "FROM_REQUEST";

	/**
	 * Constant attribute that represents the constant value field 'open'.
	 */
	public static final String FIELD_VALUE_OPEN_TRACE = "open";

	/**
	 * Constant attribute that represents the constant value field 'close'.
	 */
	public static final String FIELD_VALUE_CLOSE_TRACE = "close";

	/**
	 * Constant attribute that represents the constant value field 'NOT_SPECIFIED'.
	 */
	public static final String FIELD_VALUE_DELAPPID_NOTSPECIFIED = "NOT_SPECIFIED";
	
	/**
	 * Constant attribute that represents the field name 'ID'.
	 */
	public static final String FIELD_NAME_TRASN_ID = "TRASN_ID";

}
