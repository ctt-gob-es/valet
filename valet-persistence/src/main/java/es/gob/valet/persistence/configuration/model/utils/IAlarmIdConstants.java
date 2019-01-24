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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.utils.IAlarmIdConstants.java.</p>
 * <b>Description:</b><p>Interface that contains all the IDs of the alarms.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/01/2019.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>Interface that contains all the IDs of the alarms.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/01/2019.
 */
public interface IAlarmIdConstants {

	/**
	 * Constant attribute that represents the ID for the Alarm 001: Unknown revocation status for a certificate.
	 */
	String ALM001_UNKNOWN_REV_STATUS = "ALM001";

	/**
	 * Constant attribute that represents the ID for the Alarm 002: Error while trying to get/parse a TSL.
	 */
	String ALM002_ERROR_GETTING_PARSING_TSL = "ALM002";

	/**
	 * Constant attribute that represents the ID for the Alarm 003:Error getting/using a CRL.
	 */
	String ALM003_ERROR_GETTING_USING_CRL = "ALM003";

	/**
	 * Constant attribute that represents the ID for the Alarm 004: Error getting/using a OCSP response.
	 */
	String ALM004_ERROR_GETTING_USING_OCSP = "ALM004";

	/**
	 * Constant attribute that represents the ID for the Alarm 005: New TSL version detected.
	 */
	String ALM005_NEW_TSL_DETECTED = "ALM005";

}
