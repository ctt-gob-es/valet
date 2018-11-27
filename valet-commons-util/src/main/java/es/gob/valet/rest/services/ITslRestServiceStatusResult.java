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
 * <b>File:</b><p>es.gob.valet.rest.services.ITslRestServiceStatusResult.java.</p>
 * <b>Description:</b><p>Interface that defines the constants for the result status of the services.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 26/11/2018.
 */
package es.gob.valet.rest.services;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Interface that defines the constants for the result status of the services.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 26/11/2018.
 */
public interface ITslRestServiceStatusResult {

	/**
	 * Constant attribute that represents the value for the general result status service
	 * when there is an error checking the input parameters.
	 */
	Integer STATUS_ERROR_INPUT_PARAMETERS = Integer.valueOf(0);

	/**
	 * Constant attribute that represents the value for the general result status service
	 * when there is an error executing the service.
	 */
	Integer STATUS_ERROR_EXECUTING_SERVICE = Integer.valueOf(1);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'getTslInformation' when the TSL information has been finded.
	 */
	Integer STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_FINDED = Integer.valueOf(NumberConstants.NUM10);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'getTslInformation' when the TSL information has NOT been finded.
	 */
	Integer STATUS_SERVICE_GETTSLINFORMATION_TSL_INFORMATION_NOT_FINDED = Integer.valueOf(NumberConstants.NUM11);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is not a TSL to use with
	 * the input certificate.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_NOT_FINDED = Integer.valueOf(NumberConstants.NUM20);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and the service has been executed.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED = Integer.valueOf(NumberConstants.NUM21);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate but it has not been detected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_NOT_DETECTED = Integer.valueOf(NumberConstants.NUM22);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED = Integer.valueOf(NumberConstants.NUM23);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has NOT been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED = Integer.valueOf(NumberConstants.NUM24);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED = Integer.valueOf(NumberConstants.NUM25);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The revocation status
	 * information has NOT been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_NOT_COLLECTED = Integer.valueOf(NumberConstants.NUM26);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The revocation status
	 * information has been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_REVSTATUS_COLLECTED = Integer.valueOf(NumberConstants.NUM27);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has NOT been collected. The revocation status
	 * information has NOT been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_NOT_COLLECTED = Integer.valueOf(NumberConstants.NUM28);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has NOT been collected. The revocation status
	 * information has been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_NOT_COLLECTED_REVSTATUS_COLLECTED = Integer.valueOf(NumberConstants.NUM29);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has been collected. The revocation status
	 * information has NOT been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_NOT_COLLECTED = Integer.valueOf(NumberConstants.NUM30);

	/**
	 * Constant attribute that represents the value for the result status service
	 * 'detectCertInTslInfoAndValidation' when there is a TSL to use with
	 * the input certificate and it has been detected. The certificate mapping
	 * information has been collected. The revocation status
	 * information has been collected.
	 */
	Integer STATUS_SERVICE_DETECTCERTINTSLINFOVALIDATION_TSL_FINDED_CERT_DETECTED_INFO_COLLECTED_REVSTATUS_COLLECTED = Integer.valueOf(NumberConstants.NUM31);

}
