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
 * <b>File:</b><p>es.gob.valet.i18n.messages.IPersistenceGeneralMessages.java.</p>
 * <b>Description:</b><p>Interface that defines all the token constants for the messages
 * in the persistence module of valET: cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 17/10/2022.
 */
package es.gob.valet.i18n.messages;

/**
 * <p>Interface that defines all the token constants for the messages
 * in the persistence module of valET: cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 17/10/2022.
 */
public interface IPersistenceGeneralMessages {

	/**
	 * Constant attribute that represents the name of the property <code>AESCIPHER001</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String CIPHER_LOG001 = "AESCIPHER001";

	/**
	 * Constant attribute that represents the name of the property <code>AESCIPHER002</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String CIPHER_LOG002 = "AESCIPHER002";

	/**
	 * Constant attribute that represents the name of the property <code>AESCIPHER003</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String CIPHER_LOG003 = "AESCIPHER003";

	/**
	 * Constant attribute that represents the name of the property <code>errorImportJSONMappingExtensionIncorrect</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String ERROR_IMPORTING_JSON_MAPPING_EXTENSION_INCORRECT = "errorImportJSONMappingExtensionIncorrect";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorImportJSONMappingFileEmpty</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String ERROR_IMPORTING_JSON_MAPPING_FILE_EMPTY = "errorImportJSONMappingFileEmpty";

	/**
	 * Constant attribute that represents the name of the property <code>errorImportJSONMappingFormatIncorrect</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String ERROR_IMPORTING_JSON_MAPPING_FORMAT_INCORRECT = "errorImportJSONMappingFormatIncorrect";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorImportJSONMappingTslMappingNotInstance</code> belonging to the
	 * file messages/valet-persistence/general_xx_YY.properties.
	 */
	String ERROR_IMPORTING_JSON_MAPPING_TSL_MAPPING_NOT_INSTANCE = "errorImportJSONMappingTslMappingNotInstance";
}
