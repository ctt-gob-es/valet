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
 * @version 1.0, 31/01/2019.
 */
package es.gob.valet.rest.services;

/**
 * <p>Interface that defines all the commons constants related with the mappings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/01/2019.
 */
public interface ITslMappingConstants {

	/**
	 * Constant attribute that represents the mapping key 'clasificacion'.
	 */
	String MAPPING_KEY_CERT_CLASIFICACION = "clasificacion";

	/**
	 * Constant attribute that represents the mapping key 'certQualified'.
	 */
	String MAPPING_KEY_CERT_QUALIFIED = "certQualified";

	/**
	 * Constant attribute that represents the mapping key 'certClassification'.
	 */
	String MAPPING_KEY_CERT_CLASSIFICATION = "certClassification";

	/**
	 * Constant attribute that represents the mapping key 'qscd'.
	 */
	String MAPPING_KEY_QSCD = "qscd";

	/**
	 * Constant attribute that represents the mapping value 'YES'.
	 */
	String MAPPING_VALUE_YES = "YES";

	/**
	 * Constant attribute that represents the mapping value 'NO'.
	 */
	String MAPPING_VALUE_NO = "NO";

	/**
	 * Constant attribute that represents the mapping value 'UNKNOWN'.
	 */
	String MAPPING_VALUE_UNKNOWN = "UNKNOWN";

	/**
	 * Constant attribute that represents the mapping value 'NATURAL_PERSON'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON = "NATURAL_PERSON";

	/**
	 * Constant attribute that represents the mapping value 'LEGAL_PERSON'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_LEGALPERSON = "LEGAL_PERSON";

	/**
	 * Constant attribute that represents the mapping value 'ESEAL'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_ESEAL = "ESEAL";

	/**
	 * Constant attribute that represents the mapping value 'ESIG'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_ESIG = "ESIG";

	/**
	 * Constant attribute that represents the mapping value 'WSA'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_WSA = "WSA";

	/**
	 * Constant attribute that represents the mapping value 'TSA'.
	 */
	String MAPPING_VALUE_CLASSIFICATION_TSA = "TSA";

	/**
	 * Constant attribute that represents the mapping value 'ASINCERT'.
	 */
	String MAPPING_VALUE_ASINCERT = "ASINCERT";

	/**
	 * Constant attribute that represents the mapping value 'YES_MANAGED_ON_BEHALF'.
	 */
	String MAPPING_VALUE_QSCD_YES_MANAGEDONBEHALF = "YES_MANAGED_ON_BEHALF";

}
