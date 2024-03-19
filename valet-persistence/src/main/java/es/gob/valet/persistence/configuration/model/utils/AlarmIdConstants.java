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
 * <b>Description:</b><p>Class that contains all the IDs of the alarms.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.4, 12/01/2024.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>Class that contains all the IDs of the alarms.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 12/01/2024.
 */
public class AlarmIdConstants {

	/**
	 * Constant attribute that represents the ID for the Alarm 001: Unknown revocation status for a certificate.
	 */
	public static final String ALM001_UNKNOWN_REV_STATUS = "ALM001";

	/**
	 * Constant attribute that represents the ID for the Alarm 002: Error while trying to get/parse a TSL.
	 */
	public static final String ALM002_ERROR_GETTING_PARSING_TSL = "ALM002";

	/**
	 * Constant attribute that represents the ID for the Alarm 003:Error getting/using a CRL.
	 */
	public static final String ALM003_ERROR_GETTING_USING_CRL = "ALM003";

	/**
	 * Constant attribute that represents the ID for the Alarm 004: Error getting/using a OCSP response.
	 */
	public static final String ALM004_ERROR_GETTING_USING_OCSP = "ALM004";

	/**
	 * Constant attribute that represents the ID for the Alarm 005: New TSL version detected.
	 */
	public static final String ALM005_NEW_TSL_DETECTED = "ALM005";

	/**
	 * Constant attribute that represents the ID for the Alarm 006:Error al obtener el certificado a partir de SubjectAltName
	 */
	public static final String ALM006_ERROR_GETTING_CERT_SUBJECT_ALT_NAME = "ALM006";

	/**
	 * Constant attribute that represents the ID for the Alarm 007: No ha sido posible recuperar el certificado emisor del Almacén de Confianza CA
	 */
	public static final String ALM007_ERROR_GETTING_ISSUER_KEYSTORE = "ALM007";

	/**
	 * Constant attribute that represents the ID for the Alarm 008: Se ha registrado un certificado emisor en el Almacén de Confianza CA.
	 */
	public static final String ALM008_REGISTER_KEYSTORE_CA = "ALM008";
	
	/**
	 * Constant attribute that represents the ID for the Alarm 009: No se confía en la respuesta OCPS. Se ha registrado certificado en Almacén de Confianza OCSP pendiente de validar.
	 */
	public static final String ALM009_TRUESTOREOCSP_PENDING_VALIDATION = "ALM009";
	
	/**
	 * Constant attribute that represents the ID for the Alarm 010: No se confía en la respuesta OCPS, su emisor está registrado y aún no ha sido validado.
	 */
	public static final String ALM010_OCSP_RESPONSE_NOT_TRUSTED = "ALM010";
	
	/**
	 * Constant attribute that represents the ID for the Alarm 011: Conexiones fallidas.
	 */
	public static final String ALM011_CONNECTION_FAIL = "ALM011";
}