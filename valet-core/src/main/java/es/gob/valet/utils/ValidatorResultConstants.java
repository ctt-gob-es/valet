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
 * <b>File:</b><p>es.gob.valet.commons.utils.ValidatorResultConstants.java.</p>
 * <b>Description:</b><p>Class that contain constants for validator results.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.utils;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that contain constants for TSL validator results .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class ValidatorResultConstants {
	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has not been detected with the TSL.
	 */
	public static final int RESULT_NOT_DETECTED = 0;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL, but its state is unknown.
	 */
	public static final int RESULT_DETECTED_STATE_UNKNOWN = 1;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL and its state is valid.
	 */
	public static final int RESULT_DETECTED_STATE_VALID = 2;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL and its state is revoked.
	 */
	public static final int RESULT_DETECTED_STATE_REVOKED = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL and its certificate chain is not valid (expired).
	 */
	public static final int RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID = NumberConstants.NUM4;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL and the service status is revoked.
	 */
	public static final int RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS = NumberConstants.NUM5;

	/**
	 * Constant attribute that represents a validation result:
	 * The certificate has been detected by the TSL and the service status is not valid.
	 */
	public static final int RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS = NumberConstants.NUM6;
	
	/**
	 * Constant attribute that represents the value for a mapping certificate type unknown.
	 */
	public static final int MAPPING_TYPE_UNKNOWN = 0;

	/**
	 * Constant attribute that represents the value for a mapping certificate type non qualified.
	 */
	public static final int MAPPING_TYPE_NONQUALIFIED = 1;

	/**
	 * Constant attribute that represents the value for a mapping certificate type qualified.
	 */
	public static final int MAPPING_TYPE_QUALIFIED = 2;
	
	/**
	 * Constant attribute that represents the value for a mapping certificate classification other/unknown.
	 */
	public static final int MAPPING_CLASSIFICATION_OTHER_UNKNOWN = 0;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification natural person.
	 */
	public static final int MAPPING_CLASSIFICATION_NATURAL_PERSON = 1;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification legal person.
	 */
	public static final int MAPPING_CLASSIFICATION_LEGALPERSON = 2;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification ESEAL.
	 */
	public static final int MAPPING_CLASSIFICATION_ESEAL = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification ESIG.
	 */
	public static final int MAPPING_CLASSIFICATION_ESIG = NumberConstants.NUM4;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification WSA.
	 */
	public static final int MAPPING_CLASSIFICATION_WSA = NumberConstants.NUM5;

	/**
	 * Constant attribute that represents the value for a mapping certificate classification TSA.
	 */
	public static final int MAPPING_CLASSIFICATION_TSA = NumberConstants.NUM6;
	
	/**
	 * Constant attribute that represents the value for a mapping certificate QSCD unknown.
	 */
	public static final int MAPPING_QSCD_UNKNOWN = 0;

	/**
	 * Constant attribute that represents the value for a mapping certificate no QSCD.
	 */
	public static final int MAPPING_QSCD_NO = 1;

	/**
	 * Constant attribute that represents the value for a mapping certificate QSCD.
	 */
	public static final int MAPPING_QSCD_YES = 2;

	/**
	 * Constant attribute that represents the value for a mapping certificate QSCD specified in the attributes.
	 */
	public static final int MAPPING_QSCD_ASINCERT = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents the value for a mapping certificate QSCD managed by TSP.
	 */
	public static final int MAPPING_QSCD_YES_MANAGEDONBEHALF = NumberConstants.NUM4;
}
