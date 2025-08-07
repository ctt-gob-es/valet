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
 * <b>File:</b><p>es.gob.valet.utils.CertificateValidationConstants.java.</p>
 * <b>Description:</b><p>Class that contains the values used in the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.utils;

/** 
 * <p>Class that contains the values used in the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
public class CertificateValidationConstants {
	/**
	 * HTTP request parameter name for the application identifier.
	 */
	public static final String PARAM_APPLICATION = "application";

	/**
	 * HTTP request parameter name for the Base64-encoded certificate.
	 */
	public static final String PARAM_CERTIFICATE = "certificate";

	/**
	 * HTTP request parameter name to indicate whether to fetch mappings.
	 */
	public static final String PARAM_GET_INFO = "getInfo";

	/**
	 * HTTP request parameter name for the TSL (Trusted Service List) location.
	 */
	public static final String PARAM_TSL_LOCATION = "tslLocation";

	/**
	 * HTTP request parameter name for the certificate validation date.
	 */
	public static final String PARAM_DETECTION_DATE = "detectionDate";

	/**
	 * HTTP request parameter name to indicate whether to return the certificate chain.
	 */
	public static final String PARAM_RETURN_CERT_CHAIN = "returnCertificateChain";

	/**
	 * HTTP request parameter name to indicate whether to check revocation status.
	 */
	public static final String PARAM_CHECK_REVOCATION_STATUS = "checkRevocationStatus";

	/**
	 * HTTP request parameter name to indicate whether to return revocation evidence.
	 */
	public static final String PARAM_RETURN_REVOCATION_EVIDENCE = "returnRevocationEvidence";

	/**
	 * Constant string value for "false", used as default for certain parameters.
	 */
	public static final String FALSE_VALUE = "false";
	
	/**
	 * Constant string value for "true".
	 */
	public static final String TRUE_VALUE = "true";
}
