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
 * <b>File:</b><p>es.gob.valet.rest.controller.ConfServerMailRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST requests related to the ExportTsls administration and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.3, 29.05/2025.
 */
package es.gob.valet.rest.controller;

import java.io.File;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import  es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ExportException;
import es.gob.valet.exceptions.ValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.i18n.utils.UtilsServer;
import es.gob.valet.persistence.configuration.model.dto.ExporTslsDTO;
import es.gob.valet.service.ifaces.IExportService;
import es.gob.valet.sign.cades.SignatureException;

/**
 * <p>Class that manages the REST requests related to the ExportTsls administration and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 29.05/2025.
 */
@RestController
public class ExportTslsRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExportTslsRestController.class);
	
	/**
	 * Flag indicating if the process is currently running.
	 */
	private boolean isRunning = false;

	/**
	 * Flag indicating if an error occurred during the process.
	 */
	private boolean isError = false;

	/**
	 * The current step in the process execution.
	 */
	private int currentStep = 0;

	/**
	 * Message providing information about the current step.
	 */
	private String messageInfoStep;

	/**
	 * The thread responsible for executing the process.
	 */
	private Thread currentThread;

	/**
	 * The total number of steps in the process.
	 */
	private static final int TOTAL_STEPS = 7;

	/**
	 * The folder where the TSLS valet files are stored.
	 */
	private File tslsValetFolder = null;

	/**
	 * The folder where META-INF files are stored.
	 */
	private File metaInfFolder = null;

	/**
	 * The properties object containing hash values for files.
	 */
	private Properties filesHashProperties = null;

	/**
	 * The folder containing the TSLS data files.
	 */
	private File tslDataFolder = null;

	/**
	 * The folder containing the TSLS country region mapping files.
	 */
	private File tslCountryRegionMappingFolder = null;

	/**
	 * The folder containing the TSLS certificate mapping files.
	 */
	private File tslMappingFolder = null;

	/**
	 * The byte array representing the hash of files.
	 */
	private byte[] hashByteArray;

	/**
	 * The byte array representing the calculated signature.
	 */
	private byte[] calculateSignature;

	/**
	 * The base64-encoded string representing the generated ZIP file for the export.
	 */
	private String zipValet;

	/**
	 * The DTO object containing export TSLS details.
	 */
	private ExporTslsDTO exporTslsDTO;

	/**
	 * The service responsible for handling the export process.
	 */
	@Autowired
	private IExportService iExportService;
	
	/**
	 * Starts the export process for generating TSLS data.
	 * <p>
	 * This method resets relevant flags, initializes the process with the provided {@link ExporTslsDTO}, 
	 * and starts a new thread to execute the process asynchronously. The thread will run through various steps 
	 * of the export process, updating the process state along the way.
	 * </p>
	 *
	 * @param exporTslsDTO the DTO containing the data required for the export process
	 * @return the message indicating the status of the current step
	 */
    @RequestMapping(value = "/startProcess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public synchronized String startProcess(@RequestBody ExporTslsDTO exporTslsDTO) {
        
    	// Reseteamos los valores
        isRunning = true;
        isError = false; 
        currentStep = 0;
        this.exporTslsDTO = exporTslsDTO;
        
        // Iniciamos el proceso
        currentThread = new Thread(this::executeProcess);
        currentThread.start();

        return messageInfoStep;
    }
    
    /**
     * Executes the export process in a series of steps.
     * <p>
     * This method runs through all the predefined steps of the export process. If the process is interrupted or stopped, 
     * it will break out of the loop and terminate. It also handles exceptions, logs errors, and sets flags indicating 
     * the success or failure of the process. After completion or failure, it updates the process state.
     * </p>
     *
     * @throws Exception if any error occurs during the execution of the steps
     */
    private void executeProcess() {
        try {
        	for (int i = 1; i <= TOTAL_STEPS; i++) {
        		 if (!isRunning || currentThread.isInterrupted()) {
        			 break;
        		 }
                executeStep(i);
            }
        } catch (ExportException e) {
        	messageInfoStep = e.getMessage();
            isError = true;
        } catch (Exception e) {
        	LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP001), e);
        	messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP002, currentStep);
            isError = true;
        } finally {
        	isRunning = false;
        }
    }
   
    /**
     * Executes a specific step of the export process.
     * <p>
     * This method executes a particular step based on the provided step number. It first checks if the current thread is 
     * interrupted and exits immediately if so. Then, it performs the necessary task associated with the step, such as 
     * creating directories, exporting data, generating hashes, or creating a ZIP file. If any error occurs during execution, 
     * an exception will be thrown.
     * </p>
     *
     * @param step The current step of the export process to execute.
     * @throws IOException if an I/O error occurs.
     * @throws NoSuchAlgorithmException if an algorithm is not found during cryptographic operations.
     * @throws InterruptedException if the current thread is interrupted during sleep.
     * @throws ValetException if a specific error occurs in the Valet process.
     * @throws UnrecoverableKeyException if an unrecoverable key is encountered.
     * @throws KeyStoreException if an error occurs with the key store.
     * @throws CertificateException if an error occurs with the certificate.
     * @throws SignatureException if an error occurs during the signing process.
     * @throws ExportException if an error occurs during the export process.
     * @throws CommonUtilsException if an error occurs with i18 language.
     * @throws CipherException if an error occurs with the cipher string.
     */
	@SuppressWarnings("static-access")
	private void executeStep(int step) throws IOException, NoSuchAlgorithmException, InterruptedException, ExportException, UnrecoverableKeyException, KeyStoreException, CertificateException, SignatureException, ExportException, CommonUtilsException, CipherException {
    	synchronized (this) {
    		currentStep = step;
            
    		if (currentThread.isInterrupted()) {
                // Verificamos si el hilo fue interrumpido y salimos inmediatamente
                messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP003, currentStep);
                return;
            }
    		
    		switch (step) {
    			case NumberConstants.NUM1:
    				messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP004, currentStep);
    				this.createDirectoryStructure();
    				currentThread.sleep(2000);
					break;
				case NumberConstants.NUM2:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP005, currentStep);
					iExportService.exportTslData(this.filesHashProperties, this.tslDataFolder);
					break;
				case NumberConstants.NUM3:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP006, currentStep);
					iExportService.exportMappingToTsls(this.filesHashProperties, this.tslCountryRegionMappingFolder);
					break;
				case NumberConstants.NUM4:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP007, currentStep);
					iExportService.exportMappingToService(this.filesHashProperties, this.tslMappingFolder);
					break;
				case NumberConstants.NUM5:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP008, new Object[ ] { currentStep, this.exporTslsDTO.getValetVersionDTO().getVersion() });
					hashByteArray = iExportService.addMetaInf(this.filesHashProperties, this.metaInfFolder, this.exporTslsDTO.getValetVersionDTO().getVersion(), this.tslsValetFolder);
					currentThread.sleep(2000);
					break;
				case NumberConstants.NUM6:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP009, currentStep);
					this.calculateSignature = iExportService.signHash(hashByteArray);
					break;
				case NumberConstants.NUM7:
					messageInfoStep = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_EXP010, currentStep);
					this.zipValet = iExportService.getZIPFileWithTsls(this.metaInfFolder, this.calculateSignature, this.tslsValetFolder);
					break;
				default:
					break;
			}
        }
    }

	/**
	 * Stops the running process and interrupts the associated thread if it is still active.
	 * <p>
	 * This method sets the {@code isRunning} flag to {@code false} to stop the logical process. It checks if the current 
	 * thread is alive and interrupts it if necessary. After stopping the process, it retrieves the current status of the 
	 * process and returns it as a map of key-value pairs.
	 * </p>
	 *
	 * @return A map containing the status of the process, including whether it is running, the current step, and other
	 *         relevant information.
	 */
    @RequestMapping(value = "/stopProcess", method = RequestMethod.POST)
    public synchronized Map<String, Object> stopProcess() {
        isRunning = false; // Detenemos el proceso lógico
        if (currentThread != null && currentThread.isAlive()) {
            currentThread.interrupt(); // Interrumpimos el hilo si sigue activo
        }

        return this.getStatus();
    }
    
    /**
     * Retrieves the current status of the running process.
     * <p>
     * This method checks the current state of the process, including whether it is running or stopped, the current step 
     * in the process, the progress percentage, any error state, and the current export file name and data.
     * The method returns a map with these details as key-value pairs.
     * </p>
     *
     * @return A map containing the status of the process, including:
     *         - "status": The current state of the process ("Running" or "Stopped").
     *         - "currentStep": The current step in the process.
     *         - "progress": The percentage of completion for the process.
     *         - "messageInfoStep": A message describing the current step.
     *         - "isError": A flag indicating if an error occurred.
     *         - "fileNameExport": The name of the file being exported.
     *         - "zipValet": A string representing the zip file data.
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public synchronized Map<String, Object> getStatus() {
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("status", isRunning ? "Running" : "Stopped");
        statusMap.put("currentStep", currentStep);
        statusMap.put("progress", calculateProgress());
        statusMap.put("messageInfoStep", messageInfoStep);
        statusMap.put("isError", isError);
        statusMap.put("fileNameExport", "tsls_valet.zip");
        statusMap.put("zipValet", zipValet);
        return statusMap;
    }
    
    /**
     * Calculates the current progress of the process as a percentage.
     * <p>
     * The progress is determined by the ratio of the current step to the total number of steps,
     * and it is expressed as a percentage of completion.
     * </p>
     *
     * @return The progress of the process as an integer percentage (0-100).
     */
    private int calculateProgress() {
        return (currentStep * 100) / TOTAL_STEPS;
    }
    
    /**
     * Creates the directory structure for the export process.
     * <p>
     * This method creates the necessary directories for the export, including:
     * <ul>
     *     <li>tsls_valet.zip</li>
     *     <li>tsls_valet/META-INF/files_hash.properties</li>
     *     <li>tsls_valet/tslData</li>
     *     <li>tsls_valet/tslCountryRegionMapping</li>
     *     <li>tsls_valet/tslMapping</li>
     * </ul>
     * The directories are created in the temporary directory of the WebLogic server.
     * </p>
     * 
     * @throws ExportException if the temporary directory path is not configured properly or if any other error occurs during directory creation.
     */
    private void createDirectoryStructure() throws ExportException {
		/*
		 * Creamos la estructura de ficheros, esto es
		 *
		 * tsls_valet.zip
		 * 				|
		 * 				|__ META-INF
		 * 						|
		 * 						|__ files_hash.properties
		 * 				|
		 * 				|__ tslData
		 * 				|
		 * 				|__ tslCountryRegionMapping
		 * 				|
		 * 				|__ tslMapping
		 *
		 */
		
    	LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP011));
    	
    	String pathTmp = UtilsServer.getTomcatServerTempDir();
    	if (pathTmp == null) {
    	    throw new ExportException(Language.getResWebGeneral(IWebGeneralMessages.LOG_EXP012));
    	}
    	
		File tempDirectory = new File(UtilsServer.getTomcatServerTempDir());
		tslsValetFolder = new File(tempDirectory, "tsls_valet");
		metaInfFolder = new File(tslsValetFolder, "META-INF");
		tslDataFolder = new File(tslsValetFolder, "tslData");
		tslCountryRegionMappingFolder = new File(tslsValetFolder, "tslCountryRegionMapping");
		tslMappingFolder = new File(tslsValetFolder, "tslMapping");
		filesHashProperties = new Properties();
	}
}
