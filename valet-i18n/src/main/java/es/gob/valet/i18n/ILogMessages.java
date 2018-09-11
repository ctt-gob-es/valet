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
 * <b>File:</b><p>es.gob.valet.i18n.ILogMessages.java.</p>
 * <b>Description:</b><p> Interface that contains the keys to the log valet.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15/06/2018.
 */
package es.gob.valet.i18n;

/** 
 * <p>Interface that contains the keys to the log valet</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15/06/2018.
 */
public interface ILogMessages {

	/**
	 * Constant attribute that represents the name of the property <code>errorNotNullFileImplTsl</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_NULL_FILE_IMPL_TSL = "errorNotNullFileImplTsl";

	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankSpecification</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_BLANK_SPECIFICATION = "errorNotBlankSpecification";

	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankVersion</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_BLANK_VERSION = "errorNotBlankVersion";

	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankIdentificator</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_BLANK_IDENTIFICATOR = "errorNotBlankIdentificator";

	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankValue</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_BLANK_VALUE = "errorNotBlankValue";

	/**
	 * Constant attribute that represents the name of the property <code>errorExistsTslCountry</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_EXISTS_TSL_COUNTRY = "errorExistsTslCountry";

	/**
	 * Constant attribute that represents the name of the property <code>infoNotUpdateFileTsl</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_NOT_UPDATE_FILE_IMPL_TSL = "infoNotUpdateFileTsl";

	/**
	 * Constant attribute that represents the name of the property <code>errorIdentificatorDuplicate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_IDENTIFICATOR_DUPLICATE = "errorIdentificatorDuplicate";

	/**
	 * Constant attribute that represents the name of the property <code>errorEditMapping</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_EDIT_MAPPING = "errorEditMapping";

	/**
	 * Constant attribute that represents the name of the property <code>errorUpdateTsl</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_UPDATE_TSL = "errorUpdateTsl";

	/**
	 * Constant attribute that represents the name of the property <code>errorCountryInvalid</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_COUNTRY_INVALID = "errorCountryInvalid";

	/**
	 * Constant attribute that represents the name of the property <code>errorSaveTsl</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_SAVE_TSL = "errorSaveTsl";

	/**
	 * Constant attribute that represents the name of the property <code>errorSaveTslWeb</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_SAVE_TSL_WEB = "errorSaveTslWeb";

	/**
	 * Constant attribute that represents the name of the property <code>errorEditTsl</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_EDIT_TSL = "errorEditTsl";

	/**
	 * Constant attribute that represents the name of the property <code>errorEditTslWeb</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_EDIT_TSL_WEB = "errorEditTslWeb";

	/**
	 * Constant attribute that represents the name of the property <code>REST001</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG001 = "REST001";

	/**
	 * Constant attribute that represents the name of the property <code>REST002</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG002 = "REST002";

	/**
	 * Constant attribute that represents the name of the property <code>REST003</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG003 = "REST003";

	/**
	 * Constant attribute that represents the name of the property <code>REST004</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG004 = "REST004";

}
