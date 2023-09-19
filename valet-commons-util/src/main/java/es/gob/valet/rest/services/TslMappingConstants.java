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
 * <b>File:</b><p>es.gob.valet.rest.services.ITslMappingConstants.java.</p>
 * <b>Description:</b><p>Interface that defines all the commons constants related with the mappings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.3, 19/09/2023.
 */
package es.gob.valet.rest.services;

/**
 * <p>Class that defines all the commons constants related with the mappings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 19/09/2023.
 */
public class TslMappingConstants {

	/**
	 * Constant attribute that represents the mapping key 'clasificacion'.
	 */
	public static final String MAPPING_KEY_CERT_CLASIFICACION = "clasificacion";

	/**
	 * Constant attribute that represents the mapping key 'certQualified'.
	 */
	public static final String MAPPING_KEY_CERT_QUALIFIED = "certQualified";

	/**
	 * Constant attribute that represents the mapping key 'certClassification'.
	 */
	public static final String MAPPING_KEY_CERT_CLASSIFICATION = "certClassification";

	/**
	 * Constant attribute that represents the mapping key 'ETSI_Result'.
	 */
	public static final String MAPPING_KEY_ETSI_RESULT = "ETSI_Result";

	/**
	 * Constant attribute that represents the mapping key 'qscd'.
	 */
	public static final String MAPPING_KEY_QSCD = "qscd";

	/**
	 * Constant attribute that represents the mapping value 'YES'.
	 */
	public static final String MAPPING_VALUE_YES = "YES";

	/**
	 * Constant attribute that represents the mapping value 'NO'.
	 */
	public static final String MAPPING_VALUE_NO = "NO";

	/**
	 * Constant attribute that represents the mapping value 'UNKNOWN'.
	 */
	public static final String MAPPING_VALUE_UNKNOWN = "UNKNOWN";

	/**
	 * Constant attribute that represents the mapping value 'NATURAL_PERSON'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON = "NATURAL_PERSON";

	/**
	 * Constant attribute that represents the mapping value 'LEGAL_PERSON'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_LEGALPERSON = "LEGAL_PERSON";

	/**
	 * Constant attribute that represents the mapping value 'ESEAL'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_ESEAL = "ESEAL";

	/**
	 * Constant attribute that represents the mapping value 'ESIG'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_ESIG = "ESIG";

	/**
	 * Constant attribute that represents the mapping value 'WSA'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_WSA = "WSA";

	/**
	 * Constant attribute that represents the mapping value 'TSA'.
	 */
	public static final String MAPPING_VALUE_CLASSIFICATION_TSA = "TSA";

	/**
	 * Constant attribute that represents the mapping value 'ASINCERT'.
	 */
	public static final String MAPPING_VALUE_ASINCERT = "ASINCERT";

	/**
	 * Constant attribute that represents the mapping value 'YES_MANAGED_ON_BEHALF'.
	 */
	public static final String MAPPING_VALUE_QSCD_YES_MANAGEDONBEHALF = "YES_MANAGED_ON_BEHALF";

	/**
	 * Constant attribute that represents the mapping value 'Not_Qualified_For_eSig'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_NQ_ESIG = "Not_Qualified_For_eSig";

	/**
	 * Constant attribute that represents the mapping value 'Not_Qualified_For_eSeal'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_NQ_ESEAL = "Not_Qualified_For_eSeal";

	/**
	 * Constant attribute that represents the mapping value 'Not_QWAC'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_NQ_WSA = "Not_QWAC";
	/**
	 * Constant attribute that represents the mapping value 'QC_For_eSig'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_Q_ESIG = "QC_For_eSig";

	/**
	 * Constant attribute that represents the mapping value 'QC_For_eSeal'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_Q_ESEAL = "QC_For_eSeal";

	/**
	 * Constant attribute that represents the mapping value 'QWAC'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_Q_WSA = "QWAC";
	/**
	 * Constant attribute that represents the mapping value 'INDET_QC_For_eSig'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_INDET_ESIG = "INDET_QC_For_eSig";

	/**
	 * Constant attribute that represents the mapping value 'INDET_QC_For_eSeal'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_INDET_ESEAL = "INDET_QC_For_eSeal";

	/**
	 * Constant attribute that represents the mapping value 'INDET_QWAC'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_INDET_WSA = "INDET_QWAC";
	/**
	 * Constant attribute that represents the mapping value 'INDETERMINATE'.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_INDET = "INDETERMINATE";
	/**
	 * Constant attribute that represents the symbol '-'.
	 */
	public static final String HYPHEN_SYMBOL = " - ";
	
	/**
	 * Constant attribute that represents the mapping value 'INDET_QC_For_eSig - INDET_QC_For_eSeal - INDET_QWAC '.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_ALL_INDET = MAPPING_VALUE_ETSI_RESULT_INDET_ESIG+HYPHEN_SYMBOL+MAPPING_VALUE_ETSI_RESULT_INDET_ESIG+HYPHEN_SYMBOL+MAPPING_VALUE_ETSI_RESULT_INDET_WSA;
	
	/**
	 * Constant attribute that represents the mapping value 'Not_Qualified_For_eSig - Not_Qualified_For_eSeal - Not_QWAC '.
	 */
	public static final String MAPPING_VALUE_ETSI_RESULT_ALL_NQ = MAPPING_VALUE_ETSI_RESULT_NQ_ESIG+HYPHEN_SYMBOL+MAPPING_VALUE_ETSI_RESULT_NQ_ESEAL+HYPHEN_SYMBOL+MAPPING_VALUE_ETSI_RESULT_NQ_WSA;

	/**
	 * Constant attribute that represents the token parameter 'application'.
	 */
	public static final String PARAM_APPLICATION = "application";

	/**
	 * Constant attribute that represents the token parameter 'delegatedApp'.
	 */
	public static final String PARAM_DELEGATED_APP = "delegatedApp";

	/**
	 * Constant attribute that represents the token parameter 'tslLocation'.
	 */
	public static final String PARAM_TSL_LOCATION = "tslLocation";

	/**
	 * Constant attribute that represents the token parameter 'certificate'.
	 */
	public static final String PARAM_CERTIFICATE = "certificate";

	/**
	 * Constant attribute that represents the token parameter 'detectionDate'.
	 */
	public static final String PARAM_DETECTION_DATE = "detectionDate";

	/**
	 * Constant attribute that represents the token parameter 'getInfo'.
	 */
	public static final String PARAM_GET_INFO = "getInfo";

	/**
	 * Constant attribute that represents the token parameter 'checkRevocationStatus'.
	 */
	public static final String PARAM_CHECK_REV_STATUS = "checkRevocationStatus";

	/**
	 * Constant attribute that represents the token parameter 'returnRevocationEvidence'.
	 */
	public static final String PARAM_RETURN_REV_EVID = "returnRevocationEvidence";

	/**
	 * Constant attribute that represents the token parameter 'countryRegionCode'.
	 */
	public static final String PARAM_COUNTRY_REGION_CODE = "countryRegionCode";

	/**
	 * Constant attribute that represents the token parameter 'getTslXmlData'.
	 */
	public static final String PARAM_GET_TSL_XML_DATA = "getTslXmlData";

	/**
	 * Constant attribute that represents the token parameter 'crlsByteArray'.
	 */
	public static final String PARAM_CRLS_BYTE_ARRAY = "crlsByteArray";

	/**
	 * Constant attribute that represents the token parameter 'basicOcspResponsesByteArray'.
	 */
	public static final String PARAM_BASIC_OCSP_RESPONSES_BYTE_ARRAY = "basicOcspResponsesByteArray";

	/**
	 * Constant attribute that represents the token parameter 'returnCertificateChain'.
	 */
	public static final String PARAM_RETURN_CERT_CHAIN = "returnCertificateChain";

	/**
	 * Constant attribute that represents the token parameter 'detectCertInTslInfoAndValidation'.
	 */
	public static final String SERVICENAME_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION = "detectCertInTslInfoAndValidation";

	/**
	 * Constant attribute that represents the token parameter 'getTslInformation'.
	 */
	public static final String SERVICENAME_GET_TSL_INFORMATION = "getTslInformation";

	/**
	 * Constant attribute that represents the token parameter 'getTslInfoVersions'.
	 */
	public static final String SERVICENAME_GET_TSL_INFORMATION_VERSIONS = "getTslInfoVersions";

}
