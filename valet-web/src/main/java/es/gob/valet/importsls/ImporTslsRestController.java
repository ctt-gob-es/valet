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
 * <b>File:</b><p>es.gob.valet.rest.controller.ImporTslsRestController.java.</p>
 * <b>Description:</b><p> Class that manages the REST request related to the Import Tsls administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.3, 29.05/2025.
 */
package es.gob.valet.importsls;

import java.io.DataInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.ImporTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.ValetVersionDTO;
import es.gob.valet.persistence.exceptions.ImportException;
import es.gob.valet.sign.ExportFileValidator;

/**
 * <p>Class that manages the REST request related to the Import Tsls administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 29.05/2025.
 */
@RestController
public class ImporTslsRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ImporTslsRestController.class);

	/**  
	 * Constant representing the field ID for file import.  
	 */  
	private static final String FIELD_ID_FILE_IMPORT = "idFileImport";
	
	/**
	 * Constant attribute that represents the extension used to define JSON files.
	 */
	private static final String ZIP_EXTENSION = ".zip";
	
	/**  
	 * Maximum allowed file size, retrieved from application properties.  
	 */  
	@Value("${max.fileSize}")  
	private long maxFileSize;

	/**
	 * Service used for asynchronous import operations.
	 * This service handles tasks related to importing data in an asynchronous manner.
	 */
	@Autowired
	private IAsyncImportService iAsyncImportService;

	/**
	 * Service used for importing TSL (Time Stamping List) data.
	 * This service is responsible for managing the import process of TSL-related data.
	 */
	@Autowired
	private IImporTslService iImporTslService;

	
	/**  
	 * Handles the upload of a TSLS file, validating its content and returning a response.  
	 *  
	 * @param tslsFile The uploaded TSLS file.  
	 * @return An {@link ImporTslsDTO} containing validation results or errors.  
	 */  
	@RequestMapping(value = "/uploadFileImporTsls", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  
	public @ResponseBody ImporTslsDTO updateSigningCert(@RequestPart("tslsFile") final MultipartFile tslsFile, HttpSession httpSession) {  
	    ImporTslsDTO imporTslsDTO = new ImporTslsDTO();  
	    JSONObject json = new JSONObject();  

	    this.validateInputsUpdate(tslsFile, json, httpSession);  
	    if (json.length() > 0) {  
	        imporTslsDTO.setError(json.toString());  
	    }  

	    return imporTslsDTO;  
	}

	/**  
	 * Validates the uploaded TSLS file by checking its size, format, and integrity.  
	 * If validation fails, an error message is added to the provided JSON object.  
	 *  
	 * @param tslsFile The uploaded TSLS file.  
	 * @param json The JSON object where validation errors will be recorded.  
	 * @param httpSession 
	 */  
	private void validateInputsUpdate(MultipartFile tslsFile, JSONObject json, HttpSession httpSession) {
	    // Inicializamos el mensaje de error a null
	    String msgError = null;

	    // Verificamos las condiciones en un único bloque secuencial
	    if (tslsFile.getSize() > maxFileSize) {
	    	double megas = maxFileSize / 1_000_000.0;
	    	msgError = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP023, new Object[ ] { megas });
	        LOGGER.error(msgError);
	    } 
	    // Si la extensión no es ZIP
	    else if (!tslsFile.getOriginalFilename().toLowerCase().contains(ZIP_EXTENSION)) {
	        msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP024);
	        LOGGER.error(msgError);
	    } 
	    // Si el encabezado del archivo no es válido (solo si las condiciones anteriores son correctas)
	    else if (validateNotFileZip(tslsFile)) {
	        msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP025);
	        LOGGER.error(msgError);
	    } else {
	    	 // Validación final: si no hay errores hasta este punto, realizamos la validación de la firma
	    	 try {
		            byte[] content = IOUtils.toByteArray(tslsFile.getInputStream());
		            // Validación del archivo exportado
		            X509Certificate signingCertificate = ExportFileValidator.validateExportFile(content);
		            
		         // Validaremos la version del fichero
		         if(!ExportFileValidator.version.equals(ValetVersionDTO.VersionEnum.V1.getVersion())) {
		        	 msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP027);
		    	     LOGGER.error(msgError);
		         } else {
		        	// Todo ok
		            // Almacenamos el certificado de la firma en la session para recuperarlo en el endpoint /viewInfoSigningCert
		            httpSession.setAttribute("signingCertificate", signingCertificate);
		         }
	    	 } catch (Exception e) {
		            LOGGER.error(e);
		            msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP026);
		     }
	    }

	    // Si se ha detectado un error, registramos el mensaje en el JSON
 	    if (msgError != null) {
 	        json.put(FIELD_ID_FILE_IMPORT + "_span", msgError);
 	    }
	}

	/**  
	 * Checks whether the provided file is not a valid ZIP file by verifying its signature.  
	 *  
	 * @param tslsFile The uploaded TSLS file.  
	 * @return {@code true} if the file is not a valid ZIP, {@code false} otherwise.  
	 */  
	private boolean validateNotFileZip(MultipartFile tslsFile) {
	    try (DataInputStream dis = new DataInputStream(tslsFile.getInputStream())) {
	        if (dis.readInt() != 0x504b0304) {
	            return true;
	        }
	    } catch (IOException e) {
	        LOGGER.error(e);
	        return true;
	    }
	    return false;
	}
	
	/**
	 * Starts the import process using the provided TSL file and overwrite flag.
	 * 
	 * @param tslsFile the TSL file to be imported
	 * @param overwrite flag indicating whether to overwrite existing data
	 * @param httpSession the HTTP session associated with the request
	 * @throws IOException if an I/O error occurs during the file handling
	 * @throws ImportException if an error occurs during the import process
	 */
	@RequestMapping(value = "/startprocessimport", method = RequestMethod.POST)
	public void startProcess(@RequestParam("tslsFile") MultipartFile tslsFile, @RequestParam("overwrite") boolean overwrite, HttpSession httpSession) throws IOException, ImportException {
	    iImporTslService.startProcessImport(tslsFile, overwrite);
	    iAsyncImportService.executeProcessImport();
	}

	/**
	 * Retrieves the status of the import process, including progress of each step and error information.
	 * 
	 * @param httpSession the HTTP session associated with the request
	 * @return a map containing the status information, such as step progress, error status, and the number of imported items
	 */
	@RequestMapping(value = "/statusimport", method = RequestMethod.GET)
	public Map<String, Object> getStatus(HttpSession httpSession) {
	    Map<String, Object> statusMap = new HashMap<>();
	    statusMap.put("isRunning", iImporTslService.isRunning());
	    statusMap.put("messageError", iImporTslService.getMessageError());
	    statusMap.put("numTslImpSpan", iImporTslService.getNumTslImp());
	    statusMap.put("numMappingByTslImpSpan", iImporTslService.getNumMappingByTslImp());
	    statusMap.put("numMappingByServImpSpan", iImporTslService.getNumMappingByServImp());
	    statusMap.put("reasonTslData1", iImporTslService.getListTslDataNotImpByVersionMinor());
	    statusMap.put("numTslDataNotImpSpan", iImporTslService.getNumTslDataNotImp());
	    statusMap.put("numMappingByTslNotImpSpan", iImporTslService.getNumMappingByTslNotImp());
	    statusMap.put("numMappingByServNotImpSpan", iImporTslService.getNumMappingByServNotImp());
	    int currentStep = iImporTslService.getCurrentStep();
	    statusMap.put("currentStep", currentStep);
	    statusMap.put("isError", iImporTslService.isError());
	    for (int i = 1; i <= currentStep; i++) {
	        statusMap.put("step" + i, iImporTslService.getStepProgress(i));
	    }
	    return statusMap;
	}

	/**
	 * Retrieves the summary of the import process, including details about the imported TSL data.
	 * 
	 * @return a string containing the summary of the import process
	 */
	@RequestMapping(value = "/obtainfilesummary", method = RequestMethod.POST)
	public String obtainFileSummary() {
	    return iImporTslService.getSbSummaryImport().toString();
	}
	
}
