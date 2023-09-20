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
 * <b>File:</b><p>es.gob.valet.rest.services.ITslRestServiceRevocationStatus.java.</p>
 * <b>Description:</b><p>Class that defines the constants for the result of revocation status of a certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>27/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.rest.services;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that defines the constants for the result of revocation status of a certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class TslRestServiceRevocationStatus {

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL, but its revocation status is unknown.
	 */
	public static final int RESULT_DETECTED_REVSTATUS_UNKNOWN = 1;

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL and its revocation status is valid.
	 */
	public static final int RESULT_DETECTED_REVSTATUS_VALID = 2;

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL and its revocation status is revoked.
	 */
	public static final int RESULT_DETECTED_REVSTATUS_REVOKED = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL and its certificate chain is not valid (expired).
	 */
	public static final int RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID = NumberConstants.NUM4;

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL and the service status is revoked.
	 */
	public static final int RESULT_DETECTED_REVSTATUS_REVOKED_SERVICESTATUS = NumberConstants.NUM5;

	/**
	 * Constant attribute that represents a revocation status of a certificate:
	 * The certificate has been detected by the TSL and the service status is not valid.
	 */
	public static final int RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID_SERVICESTATUS = NumberConstants.NUM6;

}
