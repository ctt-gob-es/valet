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
 * <b>File:</b><p>es.gob.valet.persistence.utils.ConstantsUtils.java.</p>
 * <b>Description:</b><p>Class with utilities for import processes.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 03/04/2023.
 */
package es.gob.valet.persistence.utils;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.UtilsFile;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceGeneralMessages;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.exceptions.ImportException;

/**
 * <p>Class with utilities for import processes.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 03/04/2023
 */
public class ImportUtils {

	/**
	 * Attribute that represents the logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);
	
	/**
	 * Constant attribute that represents the extension used to define JSON files.
	 */
	private static final String JSON_EXTENSION = ".json";
	
	/**
	 * Method that check if a file contain json extension.
	 * 
	 * @param originalFilename parameter that contain name of file.
	 * @throws ImportException If the parameters are invalid.
	 */
	public static void checkIsJsonExtension(String originalFilename) throws ImportException {
		if(!originalFilename.toLowerCase().contains(JSON_EXTENSION)) {
			throw new ImportException(Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_EXTENSION_INCORRECT));
		}
	}
	
	/**
	 * Method that check if a file contain max file size permited.
	 * 
	 * @param originalFilename parameter that contain the name file. 
	 * @param file parameter that contain file represents with bytes.
	 * @param maxFileSize parameter that contain max size file.
	 * @throws ImportException If the parameters are invalid.
	 */
	public static void checkIsFileMaxSize(String originalFilename, byte[] file, Integer maxFileSize) throws ImportException {
		if(file.length > maxFileSize) {
			throw new ImportException(Language.getFormatResWebGeneral(IWebGeneralMessages.WRONG_FILE_SIZE, new Object[ ] { originalFilename, UtilsFile.getStringSizeLengthFile(file.length), UtilsFile.getStringSizeLengthFile(maxFileSize) }));
		}
	}

	/**
	 * Method that evaluate if file is null or empty.
	 * 
	 * @param file parameter that contain file represents with bytes.
	 * @param msg parameter that contain message a show error in interface. 
	 * @throws ImportException If the parameters are invalid.
	 */
	public static void checkIsFileNotNull(byte[] file, String msg) throws ImportException {
		if (file == null || file.length == 0) {
			LOGGER.warn(msg);
			throw new ImportException(msg);
		}
	}
	
}
