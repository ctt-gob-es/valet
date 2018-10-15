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
	 * Constant attribute that represents the name of the property <code>errorSaveCertificate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_SAVE_CERTIFICATE = "errorSaveCertificate";

	/**
	 * Constant attribute that represents the name of the property <code>errorSaveTslWeb</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_SAVE_CERTIFICATE_WEB = "errorSaveCertificateWeb";
	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankAlias</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_BLANK_ALIAS = "errorNotBlankAlias";

	/**
	 * Constant attribute that represents the name of the property <code>errorSpecialCharAlias</code> belonging to the
	 * file webvalet.properties.
	 */
	public static final String ERROR_SPECIAL_CHAR_ALIAS = "errorSpecialCharAlias";
	
	
	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankAlias</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_NOT_CERTIFICATE_FILE = "errorNotCertificateFile";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorNotBlankAlias</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_CERT_ADD = "infoCertificateAdd";
	

	/**
	 * Constant attribute that represents the name of the property <code>errorUpdateCertificateWeb</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_UPDATE_CERTIFICATE_WEB = "errorUpdateCertificateWeb";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorUpdateCertificate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_UPDATE_CERTIFICATE = "errorUpdateCertificate";
	/**
	 * Constant attribute that represents the name of the property <code>infoCertificateUpdated</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_CERTIFICATE_UPDATED = "infoCertificateUpdated";
	/**
	 * Constant attribute that represents the name of the property <code>errorDownloadCertificate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_DOWNLOAD_CERTIFICATE = "errorDownloadCertificate";
	/**
	 * Constant attribute that represents the name of the property <code>errorDownloadCertificate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_DOWNLOAD_CERTIFICATE_WEB = "errorDownloadCertificateWeb";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorGetCertificate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_GET_CERTIFICATE = "errorGetCertificate";
	/**
	 * Constant attribute that represents the name of the property <code>task.findNewTslRev.initMsg</code> belonging to the
	 * file webvalet.properties.
	 */
	String TASK_FIND_NEW_TSL_REV_INIT_MSG = "task.findNewTslRev.initMsg";

	/**
	 * Constant attribute that represents the name of the property <code>task.findNewTslRev.endMsg</code> belonging to the
	 * file webvalet.properties.
	 */
	String TASK_FIND_NEW_TSL_REV_END_MSG = "task.findNewTslRev.endMsg";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorUpdateTask</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_UPDATE_TASK = "errorUpdateTask";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorUpdateTaskWeb</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_UPDATE_TASK_WEB = "errorUpdateTaskWeb";
	/**
	 * Constant attribute that represents the name of the property <code>errorActiveTask</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_ACTIVE_TASK = "errorActiveTask";
	/**
	 * Constant attribute that represents the name of the property <code>errorGetClassTask</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_GET_CLASS_TASK = "errorGetClassTask";
	/**
	 * Constant attribute that represents the name of the property <code>errorParseDate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_PARSE_DATE = "errorParseDate";
	
	/**
	 * Constant attribute that represents the name of the property <code>infoUpdateTaskOk</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_TASK_ACTIVE_OK = "infoTaskActiveOk";
	
	/**
	 * Constant attribute that represents the name of the property <code>infoUpdateTaskOk</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_TASK_STOP = "infoTaskStop";
	
	/**
	 * Constant attribute that represents the name of the property <code>infoUpdateTaskOk</code> belonging to the
	 * file webvalet.properties.
	 */
	String INFO_UPDATE_TASK_OK = "infoUpdateTaskOk";
	
	/**
	 * Constant attribute that represents the name of the property <code>errorValidateDate</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_VALIDATE_DATE ="errorValidateDate";
	
	
	/**
	 * Constant attribute that represents the name of the property <code>errorValidateDateEmpty</code> belonging to the
	 * file webvalet.properties.
	 */
	String ERROR_VALIDATE_DATE_EMPTY ="errorValidateDateEmpty";
	

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

	/**
	 * Constant attribute that represents the name of the property <code>REST005</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG005 = "REST005";

	/**
	 * Constant attribute that represents the name of the property <code>REST006</code> belonging to the
	 * file restvalet.properties.
	 */
	String REST_LOG006 = "REST006";

}
