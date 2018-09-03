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
 * <b>File:</b><p>org.valet.i18n.LogMessages.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15 jun. 2018.
 */
package es.gob.valet.i18n;


/** 
 * <p>Interface that contains the keys to th log valet</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15 jun. 2018.
 */
public interface LogMessages {


	/**
	 * Message key declared in the 'webvalet.properties' file. 
	 */			
	String ERROR_NOT_NULL_FILE_IMPL_TSL = "errorNotNullFileImplTsl";
	
	/**
	 * Message key declared in the 'webvalet.properties' file. 
	 */			
	String ERROR_NOT_BLANK_SPECIFICATION = "errorNotBlankSpecification";
	
	String ERROR_NOT_BLANK_IDENTIFICATOR ="errorNotBlankIdentificator";
	String ERROR_NOT_BLANK_VALUE ="errorNotBlankValue";
	String ERROR_EXISTS_TSL_COUNTRY = "errorExistsTslCountry";
	/**
	 * Message key declared in the 'webvalet.properties' file. 
	 */			
	String ERROR_NOT_BLANK_VERSION = "errorNotBlankVersion";
	String ERROR_ADD_TSL = "errorAddingTsl";
	
	String INFO_NOT_UPDATE_FILE_IMPL_TSL = "infoNotUpdateFileTsl";
	String ERROR_IDENTIFICATOR_DUPLICATE = "errorIdentificatorDuplicate";
	String ERROR_EDIT_MAPPING ="errorEditMapping";
	String ERROR_UPDATE_TSL="errorUpdateTsl";
	String ERROR_COUNTRY_INVALID="errorCountryInvalid";	
	String ERROR_SAVE_TSL = "errorSaveTsl";
	String ERROR_SAVE_TSL_WEB = "errorSaveTslWeb";
	String ERROR_EDIT_TSL = "errorEditTsl";
	String ERROR_EDIT_TSL_WEB = "errorEditTslWeb";
	
}
