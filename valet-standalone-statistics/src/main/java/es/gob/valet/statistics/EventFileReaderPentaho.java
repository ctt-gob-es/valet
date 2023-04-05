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
 * <b>File:</b><p>es.gob.valet.statistics.EventFileReaderPentaho.java.</p>
 * <b>Description:</b><p> Class for reading the event file and registering the information contained in this file in the Pentaho database schemas.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.2, 03/04/2023.
 */
package es.gob.valet.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.statistics.i18n.Language;
import es.gob.valet.statistics.i18n.StandaloneStatisticsLogConstants;
import es.gob.valet.statistics.persistence.bo.impl.PentahoManagementBOImpl;
import es.gob.valet.statistics.persistence.bo.interfaz.IPentahoManagementBO;
import es.gob.valet.statistics.persistence.dto.TransactionDTO;
import es.gob.valet.statistics.persistence.dto.ValidationDTO;

/** 
 * <p>Class for reading the event file and registering the information contained in this file in the Pentaho database schemas .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 03/04/2023.
 */
public final class EventFileReaderPentaho {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(EventFileReaderPentaho.class);

	/**
	 * Attribute that represents the event file path.
	 */
	private static String pathLogFileToRead = null;

	/**
	 * Attribute that represents the date of the log parsed.
	 */
	private static LocalDate logDate;

	/**
	 * Attribute that represents the audit ID.
	 */
	private static String logFilename;

	/**
	 * Attribute that represents the property start date.
	 */
	private static Date dateStart;

	/**
	 * Attribute to count the number of transactions with errors. 
	 */
	private static int tranErrors = 0;

	/**
	 * Attribute to count the number of total transactions.
	 */
	private static int totalTransactionsLog = 0;

	/**
	 * Attribute that represents the text 'OP=open'. 
	 */
	private static final String COD_OP_OPEN = IPentahoManagementBO.TOKEN_OP + IPentahoManagementBO.TOKEN_SEPARATOR + IPentahoManagementBO.OPEN_TRACE_TOKEN;

	/**
	 * Attribute that represents the text 'OP=2'. 
	 */
	private static final String COD_OP_2 = IPentahoManagementBO.TOKEN_OP + IPentahoManagementBO.TOKEN_SEPARATOR + NumberConstants.NUM2;
	/**
	 * Attribute that represents the text 'OP=3'. 
	 */
	private static final String COD_OP_3 = IPentahoManagementBO.TOKEN_OP + IPentahoManagementBO.TOKEN_SEPARATOR + NumberConstants.NUM3;
	/**
	 * Attribute that represents the text 'OP=13'. 
	 */
	private static final String COD_OP_13 = IPentahoManagementBO.TOKEN_OP + IPentahoManagementBO.TOKEN_SEPARATOR + NumberConstants.NUM13;

	/**
	 * Attribute that represents the text 'OP=close'. 
	 */
	private static final String COD_OP_CLOSE = IPentahoManagementBO.TOKEN_OP + IPentahoManagementBO.TOKEN_SEPARATOR + IPentahoManagementBO.CLOSE_TRACE_TOKEN;
	
	/**
	 * Attribute representing the value 'UNDEFINED' result code.
	 */
	private static final String COD_RESULT_UNDEFINED = "UNDEFINED";

	/**
	 * Attribute that represents the transactions map. The key is a Transaction Object. The value is the number of transactions with the same.
	 * @see es.gob.valet.statistics.TransactionDTO#equals(Object o)
	 */
	private static Map<TransactionDTO, Integer> transactionMap = new HashMap<TransactionDTO, Integer>();

	/**
	 * Attribute that represents the validations map. The key is a ValidationDTO Object. The value is the number of validations with the same.
	 * @see es.gob.valet.statistics.ValidationDTO#equals(Object o)
	 */
	private static Map<ValidationDTO, Integer> validationMap = new HashMap<ValidationDTO, Integer>();

	/**
	 * Attribute that represents the list of pending transactions.
	 */
	private static Map<Long, TransactionDTO> pendingTransactionMap = new HashMap<Long, TransactionDTO>();

	/**
	 * Attribute that represents the list of pending validations.
	 */
	private static Map<Long, ValidationDTO> pendingValidationMap = new HashMap<Long, ValidationDTO>();

	/**
	 * Attribute that represents the List of POJOs.
	 */
	private static List<TransactionDTO> transactionsList = new ArrayList<TransactionDTO>();
	/**
	 * Attribute that represents the List of transactions of service:'DetectCertInTslInfoAndValidation'.
	 */
	private static List<ValidationDTO> validationsList = new ArrayList<ValidationDTO>();

	/**
	 * Constructor method for the class EventFileReaderPentaho.java.
	 * @param path Path to event file.
	 */
	public EventFileReaderPentaho(String path) {
		pathLogFileToRead = path;
	}

	/**
	 * Reads the event file and registers the information contained in this file.
	 * @throws ValetStatisticsException 
	 */
	public void readAndSave() throws ValetStatisticsException {

		// fecha para comprobar el tiempo de ejecutión del proceso
		dateStart = new Date();

		// se borran toda la información que hubiera en el mapa y en las listas
		// de transacciones que se usan el proceso
		transactionMap.clear();
		pendingTransactionMap.clear();
		transactionsList.clear();
		validationsList.clear();

		// se obtiene el nombre del fichero de log
		setupLogFilename();
		// se obtiene la información necesaria de las transaccciones y se guarda
		// en un mapa
		fillTransactionMap();
		// se calcula el número de transacciones agrupadas
		calculateNumberTransactions();

		// se calcula el número de transacciones de validación agrupadas
		calculateNumberValidations();

		// si queda alguna transacción en la lista de transacciones pendientes,
		// es que no está correctamente terminada, se informa en el log
		if (pendingTransactionMap.size() != 0) {
			LOGGER.warn(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG002, new Object[ ] { pendingTransactionMap.size() }));
			pendingTransactionMap.clear();
		}

		// se registran en base de datos
		persistTransactionsList();

	}

	/**
	 * Sets the value of logFilename with the value of the input filename.
	 * @throws ValetStatisticsException when filename.
	 */
	private static void setupLogFilename() throws ValetStatisticsException {
		LOGGER.debug(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG001));
		logFilename = new File(pathLogFileToRead).getName();
	}

	/**
	 * Fills the transaction map with the information provided by the reader.
	 * @throws ValetStatisticsException when an error has occurred.
	 */
	private static void fillTransactionMap() throws ValetStatisticsException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pathLogFileToRead));
		} catch (FileNotFoundException e1) {
			throw new ValetStatisticsException(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG003, new Object[ ] { e1.getMessage() }));
		}
		try {
			String line = reader.readLine();
			while (line != null) {
				LOGGER.debug(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG004, new Object[ ] { line }));
				// setup log date after reading first transaction
				if (logDate == null) {
					setupLogDate(line);
				}
				// se comprueba si la linea contiene "OP=open"
				if (line.contains(COD_OP_OPEN)) {
					LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG007));
					openTransaction(line);
				} else if (line.contains(COD_OP_2)) {
					LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG020));
					saveInfoApplication(line);

				} else if (line.contains(COD_OP_13)) {
					LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG008));
					// se obtiene información sobre la TSL
					saveInfoTSL(line);
				} else if (line.contains(COD_OP_3)) {
					LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG021));
					closeTransaction(line);
				} else if (line.contains(COD_OP_CLOSE)) {
					LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG021));
					closeTransaction(line);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			throw new ValetStatisticsException(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG005, new Object[ ] { e.getMessage() }));
		} finally {
			try {
				reader.close();
				if (tranErrors > 0) {
					LOGGER.debug(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG014, new Object[ ] { tranErrors, totalTransactionsLog }));
				}
			} catch (IOException e) {
				throw new ValetStatisticsException(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG006, new Object[ ] { e.getMessage() }));
			}
		}
	}

	/**
	 * Sets logDate with the date that appears on the current line.
	 * 
	 * @param line Line to be passed.
	 */
	private static void setupLogDate(String line) {
		String[ ] parts = line.split(IPentahoManagementBO.SEPARATOR);

		// set Log date on the first transaction
		if (logDate == null) {
			// example string: 2019-05-16 00:00:00,449
			String[ ] dateSplitted = parts[parts.length - 1].split("-");
			logDate = LocalDate.of(new Integer(dateSplitted[0]), new Integer(dateSplitted[1]), new Integer(dateSplitted[2].substring(0, 2)));
		}
	}

	/**
	 * Starts transaction when a message with OP=open has been detected.
	 * 	@param line Line that contains the information for opening the transaction.
	 */
	private static void openTransaction(String line) {
		LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG010));
		Map<String, String> infoTransaction = getTokens(line);
		Long transactionId = new Long(infoTransaction.remove(IPentahoManagementBO.TOKEN_ID_TRANSACION));
		String svString = infoTransaction.remove(IPentahoManagementBO.TOKEN_ID_SERVICE);

		TransactionDTO entry = new TransactionDTO();
		// se obtiene el servicio
		entry.setService(new Long(svString));
		// se le incluye la fecha del log
		entry.setDate(logDate);

		pendingTransactionMap.put(transactionId, entry);
		LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG011));
	}

	/**
	 * Extract the fields that contain information from the TSL.
	 * 
	 * @param line Line that contains the information of the TSL.
	 */
	private static void saveInfoTSL(String line) {
		LOGGER.trace(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG019));
		Map<String, String> infoTransaction = getTokens(line);
		String transactionIdString = infoTransaction.remove(IPentahoManagementBO.TOKEN_ID_TRANSACION);
		Long transactionId = Long.valueOf(transactionIdString);

		TransactionDTO transactionDTO = pendingTransactionMap.get(transactionId);

		ValidationDTO pendingValidationDTO = new ValidationDTO();
		pendingValidationDTO.setTransactionId(transactionDTO.getTransactionId());
		pendingValidationDTO.setApplication(transactionDTO.getApplication());
		pendingValidationDTO.setDelegatedApplication(transactionDTO.getDelegatedApplication());
		pendingValidationDTO.setDate(transactionDTO.getDate());

		// se obtiene la información de las TSL
		String country = infoTransaction.remove(IPentahoManagementBO.TOKEN_COUNTRY);
		String tspName = infoTransaction.remove(IPentahoManagementBO.TOKEN_TSP_NAME);
		String tspService = infoTransaction.remove(IPentahoManagementBO.TOKEN_TSP_SERVICE);
		String tspServiceHistoric = infoTransaction.remove(IPentahoManagementBO.TOKEN_TSP_SERVICE_HIST);

		pendingValidationDTO.setCountry(country);
		pendingValidationDTO.setTspName(tspName);
		pendingValidationDTO.setTspService(tspService);
		pendingValidationDTO.setTspServiceHistoric(tspServiceHistoric);

		pendingValidationMap.put(transactionId, pendingValidationDTO);

	}

	/**
	 * Extract the fields that contain the name of the application or delegated application that makes the request.
	 * 
	 * @param line Line that contains the information of the application and delegated application.
	 */
	private static void saveInfoApplication(String line) {
		Map<String, String> infoTransaction = getTokens(line);
		String transactionIdString = "";
		transactionIdString = infoTransaction.remove(IPentahoManagementBO.TOKEN_ID_TRANSACION);
		Long transactionId = Long.valueOf(transactionIdString);

		TransactionDTO pendingTransactionDTO = pendingTransactionMap.get(transactionId);

		// obtenemos del mapa de transacciones sin cerrar para completar su
		// información
		if (pendingTransactionDTO == null) {
			String error = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG015, new Object[ ] { transactionIdString });
			LOGGER.error(error);
		} else {
			String appId = infoTransaction.remove(IPentahoManagementBO.TOKEN_APP_ID);
			String delappId = infoTransaction.remove(IPentahoManagementBO.TOKEN_DEL_APP_ID);
			pendingTransactionDTO.setApplication(appId);
			pendingTransactionDTO.setDelegatedApplication(delappId);
			pendingTransactionMap.put(transactionId, pendingTransactionDTO);
		}

	}

	/**
	 * Extracts the audit fields from an audit file line.
	 * @param line	Line of the event file.
	 * @return	Map that contains the audit fields.
	 */
	private static Map<String, String> getTokens(String line) {
		Map<String, String> tokens = new LinkedHashMap<String, String>();
		String[ ] pairs = line.split(String.valueOf(";"));
		for (int i = 0; i < pairs.length - 1; i++) {
			int index = pairs[i].indexOf("=");
			// si index = -1 es que no existe '=' en el String, por algún error
			// en la línea del log.
			if (index > 0) {
				tokens.put(pairs[i].substring(0, index), pairs[i].substring(index + 1));
			}
		}
		int index = pairs[pairs.length - 1].indexOf(";");
		if (index > 0) {
			tokens.put(pairs[pairs.length - 1].substring(0, index), pairs[pairs.length - 1].substring(index + 1));
		}
		return tokens;
	}

	/**
	 * Method to calculate the number of transactions grouped by organizational unit, type of organizational unit, type of service, application and result.
	 */
	private static void calculateNumberTransactions() {
		for (Map.Entry<TransactionDTO, Integer> mapEntry: transactionMap.entrySet()) {
			TransactionDTO entry = mapEntry.getKey();
			entry.setNumTrans(mapEntry.getValue());
			transactionsList.add(entry);
		}
	}

	/**
	 * Method to calculate the number of transactions of service:'DetectCertInTslInfoAndValidation'.
	 */
	private static void calculateNumberValidations() {
		for (Map.Entry<ValidationDTO, Integer> mapEntry: validationMap.entrySet()) {
			ValidationDTO entry = mapEntry.getKey();
			entry.setNumValidations(mapEntry.getValue());
			validationsList.add(entry);
		}
	}

	/**
	 * Ends transaction when a message with OP=3 has been detected.
	 * @param line Line that contains the information for closing the transaction.
	 * @throws ValetStatisticsException When an error occurred. 
	 */
	private static void closeTransaction(String line) throws ValetStatisticsException {

		Map<String, String> infoTransaction = getTokens(line);

		String transactionIdString = "";

		try {
			transactionIdString = infoTransaction.remove(IPentahoManagementBO.TOKEN_ID_TRANSACION);
			LOGGER.debug(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG012, new Object[ ] { transactionIdString }));
			Long transactionId = new Long(transactionIdString);
			totalTransactionsLog++;

			TransactionDTO pendingTransactionDto = pendingTransactionMap.get(transactionId);

			if (pendingTransactionDto != null ) {
				if (line.contains(COD_OP_3)) {
					String codResult = infoTransaction.remove(IPentahoManagementBO.TOKEN_RESULT);
					pendingTransactionDto.setCodResult(codResult);

					pendingTransactionMap.remove(transactionId);

					// increment number of transactions of the same type
					if (transactionMap.containsKey(pendingTransactionDto)) {
						Integer numberOfTransactionsForEntry = transactionMap.get(pendingTransactionDto);
						transactionMap.put(pendingTransactionDto, numberOfTransactionsForEntry + 1);
						LOGGER.trace(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG016, new Object[ ] { pendingTransactionDto, numberOfTransactionsForEntry }));
					} else {
						transactionMap.put(pendingTransactionDto, 1);
					}
				} else if (line.contains(COD_OP_CLOSE)){
		
					if(pendingTransactionDto.getService().equals(NumberConstants.NUM3_LONG)){
						//se trata de una transacción del servicio 'getTslVersionInfo'
						pendingTransactionDto.setCodResult(COD_RESULT_UNDEFINED);
						// increment number of transactions of the same type
						if (transactionMap.containsKey(pendingTransactionDto)) {
							Integer numberOfTransactionsForEntry = transactionMap.get(pendingTransactionDto);			
							transactionMap.put(pendingTransactionDto, numberOfTransactionsForEntry + 1);
							LOGGER.trace(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG016, new Object[ ] { pendingTransactionDto, numberOfTransactionsForEntry }));
						} else {
							transactionMap.put(pendingTransactionDto, 1);
						}
						pendingTransactionMap.remove(transactionId);
					}
					
				}
			}

			// se comprueba si esta transacción es de validación
			ValidationDTO pendingValidationDto = pendingValidationMap.get(transactionId);
			if (pendingValidationDto != null) {
				if (validationMap.containsKey(pendingValidationDto)) {
					Integer numberOfValidationsForEntry = validationMap.get(pendingValidationDto);
					validationMap.put(pendingValidationDto, numberOfValidationsForEntry + 1);
				} else {
					validationMap.put(pendingValidationDto, 1);
				}
			}

		} catch (Exception e) {
			String msgError = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG017, new Object[ ] { transactionIdString, e.getMessage() });
			throw new ValetStatisticsException(msgError, e);
		}

	}

	/**
	 * Saves transactionsList in the DB.  
	 * @throws ValetStatisticsException If the method fails.
	 */
	private static void persistTransactionsList() throws ValetStatisticsException {
		try {
			PentahoManagementBOImpl.getInstance().saveTransactions(transactionsList, validationsList, dateStart, logFilename);

		} catch (Exception e) {
			throw new ValetStatisticsException(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.EFRP_LOG009, new Object[ ] { e.getMessage() }));
		}

	}

}
