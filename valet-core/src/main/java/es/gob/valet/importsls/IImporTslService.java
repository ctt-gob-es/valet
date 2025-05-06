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
 * <b>File:</b><p>es.gob.valet.importsls.IImporTslService.java.</p>
 * <b>Description:</b><p>interface that contains all the methods necessary to carry out the import of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/05/2025.
 */
package es.gob.valet.importsls;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.persistence.exceptions.ImportException;

/**
 * <p>interface that contains all the methods necessary to carry out the import of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/05/2025.
 */
public interface IImporTslService {

	/**
	 * Starts the TSL import process using the provided ZIP file and overwrite option.
	 * Initializes all process-related variables and loads the file content.
	 *
	 * @param tslsFile  the uploaded ZIP file containing TSL data
	 * @param overwrite whether to overwrite existing TSL entries
	 * @throws IOException      if an I/O error occurs while reading the file
	 * @throws ImportException  if an error occurs during the import process
	 */
	void startProcessImport(MultipartFile tslsFile, boolean overwrite) throws IOException, ImportException;
	
	/**
	 * Returns the progress percentage for the given import step.
	 *
	 * @param step the step number (1-based index)
	 * @return the progress percentage for the specified step
	 */
	int getStepProgress(int i);

	/**
	 * Indicates whether the import process is currently running.
	 *
	 * @return {@code true} if the process is running; {@code false} otherwise
	 */
	boolean isRunning();

	/**
	 * Indicates whether an error occurred during the import process.
	 *
	 * @return {@code true} if an error occurred; {@code false} otherwise
	 */
	boolean isError();

	/**
	 * Returns the current step of the import process.
	 *
	 * @return the current step as an integer
	 */
	int getCurrentStep();
	
	/**
	 * Returns the error message associated with the import process.
	 *
	 * @return the error message, or {@code null} if no error occurred
	 */
	String getMessageError();
	
	/**
	 * Returns the number of successfully imported TSLs.
	 *
	 * @return the number of imported TSLs
	 */
	int getNumTslImp();
		
	/**
	 * Performs the TSL import process in a new transaction, including importing TSL data, 
	 * mapping by TSL, and mapping by service.
	 * Each step updates the current step and handles the respective import operation.
	 *
	 * @throws ImporTslsException if an error occurs during the import process
	 */
	void imporTslsUniqueTransaction() throws ImporTslsException;
	
	/**
	 * Sets the current step of the import process.
	 *
	 * @param i the step number to set as the current step
	 */
	void setCurrentStep(int i);

	/**
	 * Disables TSL-related tasks by stopping specific scheduled tasks.
	 * It checks if the tasks exist and stops them accordingly. 
	 * Logs progress and handles errors during task stopping.
	 *
	 * @throws ImporTslsException if an error occurs while stopping the tasks
	 */
	void disableTslRelatedTask() throws ImporTslsException;

	/**
	 * Enables TSL-related tasks by adding or updating specific tasks.
	 * It retrieves the tasks by their tokens and updates their status. 
	 * Logs progress and handles errors during the task enabling.
	 *
	 * @throws ImporTslsException if an error occurs while enabling the tasks
	 */
	void enableTslRelatedTask() throws ImporTslsException;
	
	/**
	 * Sets the error message for the import process.
	 *
	 * @param messageError the error message to set
	 */
	void setMessageError(String messageError);
	
	/**
	 * Sets the error state for the import process.
	 *
	 * @param isError the error state to set
	 */
	void setError(boolean isError);
	
	/**
	 * Sets the running state for the import process.
	 *
	 * @param isRunning the running state to set
	 */
	void setRunning(boolean isRunning);
	
	/**
	 * Generates a summary of the import process, including information about TSL data,
	 * mappings by TSL and service, and any issues encountered during the import. 
	 * The summary is then encoded in Base64 format.
	 *
	 * @return a Base64-encoded string representing the import summary
	 */
	String getSbSummaryImport();
	
	/**
	 * Returns a formatted message listing the countries whose TSL data was not imported
	 * due to having a lower version than the existing one.
	 *
	 * @return a formatted message with the affected countries, or {@code null} if none
	 */
	String getListTslDataNotImpByVersionMinor();
	
	/**
	 * Returns the number of mappings imported by TSL (Trusted Service List).
	 *
	 * @return the count of TSL-based mappings successfully imported
	 */
	int getNumMappingByTslImp();

	/**
	 * Returns the number of service-based mappings successfully imported.
	 *
	 * @return the count of imported mappings by service
	 */
	int getNumMappingByServImp();

	/**
	 * Returns the number of TSL data entries that were not imported.
	 *
	 * @return the count of TSL entries not imported
	 */
	int getNumTslDataNotImp();

	/**
	 * Returns the number of mappings by TSL that were not imported.
	 *
	 * @return the count of TSL-based mappings not imported
	 */
	int getNumMappingByTslNotImp();

    /**
     * Returns the number of service-based mappings that were not imported.
     *
     * @return the count of mappings by service not imported
     */
	int getNumMappingByServNotImp();
}
