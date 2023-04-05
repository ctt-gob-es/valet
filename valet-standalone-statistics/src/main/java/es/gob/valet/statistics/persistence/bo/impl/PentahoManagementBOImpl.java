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
 * <b>File:</b><p>es.gob.valet.statistics.PentahoManagementBOImpl.java.</p>
 * <b>Description:</b><p> Class that implements all the operations related with de database schema.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.3, 03/04/2023.
 */
package es.gob.valet.statistics.persistence.bo.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;


import es.gob.valet.statistics.ValetStatisticsException;
import es.gob.valet.statistics.i18n.Language;
import es.gob.valet.statistics.i18n.StandaloneStatisticsLogConstants;
import es.gob.valet.statistics.persistence.bo.interfaz.IPentahoManagementBO;
import es.gob.valet.statistics.persistence.dto.TransactionDTO;
import es.gob.valet.statistics.persistence.dto.ValidationDTO;
import es.gob.valet.statistics.persistence.em.PentahoDWEntityManager;
import es.gob.valet.statistics.persistence.pojo.CCodResultsPOJO;
import es.gob.valet.statistics.persistence.pojo.CServiceTypesPOJO;
import es.gob.valet.statistics.persistence.pojo.DimApplicationPOJO;
import es.gob.valet.statistics.persistence.pojo.DimDatePOJO;
import es.gob.valet.statistics.persistence.pojo.DimNodePOJO;
import es.gob.valet.statistics.persistence.pojo.FctTransactionPOJO;
import es.gob.valet.statistics.persistence.pojo.FctValidationPOJO;

/** 
 * <p>Class that implements all the operations related with de database schema.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 03/04/2023.
 */
public final class PentahoManagementBOImpl implements IPentahoManagementBO {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 4637966829862368079L;
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(PentahoManagementBOImpl.class);
	/**
	 * Constant that represents the attribute name associated to filename field.
	 */
	private static final String FILENAME_PARAM = "filename";
	/**
	 * Constant that represents the day of month of log.
	 */
	private static final String DAY_OF_MONTH_PARAM = "day";

	/**
	 * Constant that represents the month of log.
	 */
	private static final String MONTH_PARAM = "month";

	/**
	 * Constant that represents the year of log.
	 */
	private static final String YEAR_PARAM = "year";

	/**
	 * Constant that represents the attribute name associated to applicationId field.
	 */
	private static final String APPLICATION_ID_PARAM = "identificator";

	/**
	 * Constant that represents the attribute name associated to datePk field.
	 */
	private static final String CODE_RESULT_DESCRIPTION_PARAM = "resultDescription";

	/**
	 * Constant that represents the attribute name associated to applicationId field.
	 */
	private static final String SERVICE_TYPE_ID_PARAM = "serviceTypePk";

	/**
	 * Constant that represents the attribute name associated to node identifier field.
	 */
	private static final String ID_NODE_PARAM = "idNode";

	/**
	 * Constant that represents the name of the query used to find a "ApplicationPOJO" entity.
	 */
	private static final String QUERYNAME_FIND_APPLICATION = "findDimApplication";

	/**
	 * Constant that represents the name of the query used to find a "DimDatePojo" entity.
	 */
	private static final String QUERYNAME_FIND_DATE = "findDateByDayMonthYear";

	/**
	 * Constant that represents the name of the query used to find a "CodResultsPOJO" entity.
	 */
	private static final String QUERYNAME_FIND_CODE_RESULT = "findCodeResult";

	/**
	 * Constant that represents the name of the query used to find a "CServiceTypePOJO" entity.
	 */
	private static final String QUERYNAME_FIND_SERVICE_TYPE = "findServiceType";

	/**
	 * Constant that represents the name of the query used to remove node of "DIM_NODE" table  by filename.
	 */
	private static final String QUERYNAME_FIND_NODE_BY_FILENAME = "findDimNodeByFilename";

	/**
	 * Constant that represents the name of the query used to remove transactions by node identifier.
	 */
	private static final String QUERYNAME_REMOVE_TRANSACTIONS_BY_NODE = "removeTransactionsByNode";
	
	/**
	 * Constant that represents the name of the query used to remove validations by node identifier.
	 */
	private static final String QUERYNAME_REMOVE_VALIDATIONS_BY_NODE = "removeValidationsByNode";


	/**
	 * Attribute that represents the unique instance of this class.
	 */
	private static PentahoManagementBOImpl instance = null;

	/**
	 * Attribute that allows to execute operations with the database where to import the configuration values.
	 */
	private PentahoDWEntityManager em;

	/**
	 * Gets the unique instance of the BO.
	 * @return unique instance of the BO.
	 */
	public static PentahoManagementBOImpl getInstance() {
		if (instance == null) {
			instance = new PentahoManagementBOImpl();
		}
		return instance;
	}

	/**
	 * Constructor method for the class ConfigurationBO.java.
	 * @throws EmergencyDDBBException If there is an error initializing {@link #em} 
	 */
	private PentahoManagementBOImpl() {
		// Se establece la conexión con la BBDD.
		em = PentahoDWEntityManager.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.statistics.persistence.bo.interfaz.IPentahoManagementBO#saveTransaction(java.util.List, java.util.List, java.util.Date, java.lang.String)
	 */
	@Override
	public void saveTransactions(List<TransactionDTO> listTransactions, List<ValidationDTO> listValidations, Date dateStart, String logFilename) throws ValetStatisticsException {
		EntityTransaction tx = null;
		if (listTransactions == null || listTransactions.isEmpty()) {
			LOGGER.info(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG005));
			throw new ValetStatisticsException(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG005));
		}

		if(em != null && em.getEntityManager() != null){
		try {
			
			tx = em.getEntityManager().getTransaction();
			tx.begin();

			// se comprueba si existe ya dimDate
			LocalDate creationDateTrans = listTransactions.get(0).getDate();

			DimDatePOJO dimDate = getDimDate(creationDateTrans);

			
			DimNodePOJO dimNode = getDimNodeAndRemoveTransactions(logFilename, dateStart);
			
			//se guarda toda la información obtenida de las transacciones que aparecen en el fichero de log.
			for (TransactionDTO transaction: listTransactions) {
				FctTransactionPOJO fctTransaction = createFctTransaction(transaction, dimDate, dimNode);
				em.persist(fctTransaction);
			}
			for (ValidationDTO validation: listValidations) {
				FctValidationPOJO fctValidation = createFctValidation(validation, dimDate, dimNode);
				em.persist(fctValidation);
			}

			tx.commit();
		} catch (Exception e) {
			String msg = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG004, new Object[ ] { e.getMessage() });
			LOGGER.error(msg, e);
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			em.getEntityManager().clear();
			tx.commit();
		} finally {
			safeCloseEntityManager();
		}
		}else{
			LOGGER.info(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG007));
		}

	}


	/**
	 * Method that checks if a node object with the same file name is registered, if it exists, updates the dates of the node, and eliminates the transactions associated with the node.
	 * 
	 * @param filename Parameter that represents the name of the file being processed.
	 * @param startDate Parameter representing the start date of the process.
	 * @return {@link DimNodePojo} obtained.
	 * @throws ValetStatisticsException If the method fails.
	 */
	private DimNodePOJO getDimNodeAndRemoveTransactions(String filename, Date startDate) throws ValetStatisticsException {
		DimNodePOJO dimNode = null;
		dimNode = findNodeByFilename(filename);

		if (dimNode == null) {
			dimNode = new DimNodePOJO();
			dimNode.setFilename(filename);
			dimNode.setStartDate(startDate);
			dimNode.setEndDate(new Date());
		} else {
			// se actualiza node
			dimNode.setStartDate(startDate);
			dimNode.setEndDate(new Date());
			// se elimina las transacciones que hubiera registradas.
			removeTransactionsByNode(dimNode.getNodePk());
			removeValidationsByNode(dimNode.getNodePk());
		}
		return dimNode;
	}


	/**
	 *  Method that gets the {@link DimNodePOJO} that matches with the supplied name of file.
	 * 
	 * @param filename Parameter that represents name of file.
	 * @return DimApplicationsPOJO If the method fails.
	 */
	
	private DimNodePOJO findNodeByFilename(String filename) {
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put(FILENAME_PARAM, filename);
		return (DimNodePOJO) em.namedQuerySingleResult(QUERYNAME_FIND_NODE_BY_FILENAME, parameters);
	}
	/**
	 * Method that gets the {@link DimDatePOJO} that matches with the supplied date, if it does not exists, creates a new object.
	 * 
	 * @param creationDate Creation date of the transaction.
	 * @return DimDatePOJO
	 */
	private DimDatePOJO getDimDate(LocalDate creationDate) {
		DimDatePOJO dimDate = null;

		dimDate = findDate(creationDate);
		if (dimDate == null) {
			dimDate = new DimDatePOJO();
			dimDate.setDay(creationDate.getDayOfMonth());
			dimDate.setMonth(creationDate.getMonthValue());
			dimDate.setYear(creationDate.getYear());
		}
		return dimDate;
	}

	/**
	 * Method that gets the {@link DimDatesPOJO} that matches with the supplied date.
	 * 
	 * @param creationDate Creation date of the transaction.
	 * @return DimDatesPOJO
	 */
	private DimDatePOJO findDate(LocalDate creationDate) {
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();

		creationDate.getYear();
		creationDate.getMonth();
		creationDate.getDayOfMonth();
		parameters.put(DAY_OF_MONTH_PARAM, creationDate.getDayOfMonth());
		parameters.put(MONTH_PARAM, creationDate.getMonthValue());
		parameters.put(YEAR_PARAM, creationDate.getYear());

		return (DimDatePOJO) em.namedQuerySingleResult(QUERYNAME_FIND_DATE, parameters);
	}

	/**
	 * Method that gets the {@link DimApplicationPOJO} that matches with the supplied application identifier, if it does not exists, creates a new object.
	 * 
	 * @param application Application identifier.
	 * @param dimDate Transaction registration date.
	 * @return DimApplicationsPOJO
	 */
	private DimApplicationPOJO getApplicationPOJO(String application, DimDatePOJO dimDate) {
		DimApplicationPOJO dimApp = findApplication(application);
		if (dimApp == null) {
			dimApp = new DimApplicationPOJO();
			dimApp.setIdentificator(application);
			dimApp.setRegistrationDate(dimDate);
		}
		return dimApp;
	}

	/**
	 *  Method that gets the {@link DimApplicationsPOJO} that matches with the supplied identifier application.
	 * 
	 * @param application Identifier application.
	 * @return DimApplicationsPOJO
	 */

	private DimApplicationPOJO findApplication(String application) {
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put(APPLICATION_ID_PARAM, application);
		return (DimApplicationPOJO) em.namedQuerySingleResult(QUERYNAME_FIND_APPLICATION, parameters);
	}

	/**
	 * Method that gets the {@link CCodeResultsPOJO} that matches with the supplied process result code.
	 * 
	 * @param codeResult Process result code.
	 * @return CCodeResultsPOJO.
	 */
	private CCodResultsPOJO getCodeResult(String codeResult) {
		return findCodeResult(codeResult);
	}

	/**
	 * Method that gets the {@link CCodeResultsPOJO} that matches with the supplied process result code.
	 * 
	 * @param codeResult Process result code.
	 * @return CCodeResultsPOJO.
	 */
	private CCodResultsPOJO findCodeResult(String codeResult) {
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put(CODE_RESULT_DESCRIPTION_PARAM, codeResult);
		return (CCodResultsPOJO) em.namedQuerySingleResult(QUERYNAME_FIND_CODE_RESULT, parameters);
	}

	/**
	 * Method that gets the {@link CServiceTypesPOJO} that matches with the supplied identifier of the type of service.
	 * 
	 * @param serviceTypeId Identifier of the type of service.
	 * @return CServiceTypesPOJO
	 */
	private CServiceTypesPOJO getCServiceTypes(Long serviceTypeId) {
		CServiceTypesPOJO serviceType = null;
		serviceType = findServiceType(serviceTypeId);
		if (serviceType == null) {}
		return serviceType;

	}

	/**
	 * Method that gets the {@link CServiceTypesPOJO} that matches with the supplied identifier of the type of service.
	 * 
	 * @param serviceTypeId Identifier of the type of service.
	 * @return CServiceTypesPOJO
	 */
	private CServiceTypesPOJO findServiceType(Long serviceTypeId) {
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put(SERVICE_TYPE_ID_PARAM, serviceTypeId);
		return (CServiceTypesPOJO) em.namedQuerySingleResult(QUERYNAME_FIND_SERVICE_TYPE, parameters);
	}

	/**
	 * Method that create an instance of {@link FctTransactionPOJO}.
	 * @param transaction Parameter where all the information of a transaction is stored.
	 * @param dimDate Parameter that represents the date of the generation of the transactions.
	 * @param dimNode Parameter that represents the information on the execution of the process to obtain the statistics. 
	 * @return {@link FctTransactionPOJO} created.
	 * @throws ValetStatisticsException If the method fails
	 */
	private FctTransactionPOJO createFctTransaction(TransactionDTO transaction, DimDatePOJO dimDate, DimNodePOJO dimNode) throws ValetStatisticsException {
		FctTransactionPOJO fctTransaction = new FctTransactionPOJO();
		try {
			// se obtiene la app asociada
			fctTransaction.setApplication(getApplicationPOJO(transaction.getApplication(), dimDate));
			// se obtiene la aplicación delegada
			fctTransaction.setDelegatedApplication(getApplicationPOJO(transaction.getDelegatedApplication(), dimDate));
			// se obtiene servicio asociado
			fctTransaction.setServiceType(getCServiceTypes(transaction.getService()));
			// se obtiene el código de resultado
			fctTransaction.setCodResults(getCodeResult(transaction.getCodResult()));

			fctTransaction.setNumberTransactions(transaction.getNumTrans());
			fctTransaction.setDate(dimDate);
			fctTransaction.setNode(dimNode);
		} catch (Exception e) {
			String error = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG001, new Object[ ] { e.getMessage() });
			LOGGER.error(error);
			throw new ValetStatisticsException(error, e);
		}
		return fctTransaction;
	}

	/**
	 * Method that create an instance of {@link FctValidationPOJO}.
	 * @param validation Parameter where all the information of a validation is stored.
	 * @param dimDate Parameter that represents the date of the generation of the transactions.
	 * @param dimNode Parameter that represents the information on the execution of the process to obtain the statistics.
	 * @return {@link FctTransactionPOJO} created.
	 * @throws ValetStatisticsException If the method fails
	 */
	private FctValidationPOJO createFctValidation(ValidationDTO validation, DimDatePOJO dimDate, DimNodePOJO dimNode) throws ValetStatisticsException {
		FctValidationPOJO fctValidation = new FctValidationPOJO();

		try {
			// se obtiene la app asociada
			fctValidation.setApplication(getApplicationPOJO(validation.getApplication(), dimDate));
			// se obtiene la aplicación delegada
			fctValidation.setDelegatedApplication(getApplicationPOJO(validation.getDelegatedApplication(), dimDate));
			fctValidation.setCountry(validation.getCountry());
			fctValidation.setTspName(validation.getTspName());
			fctValidation.setTspService(validation.getTspService());
			fctValidation.setTspServiceHistoric(validation.getTspServiceHistoric());

			fctValidation.setNumberValidations(validation.getNumValidations());
			fctValidation.setDate(dimDate);
			fctValidation.setNode(dimNode);
			
		} catch (Exception e) {
			String error = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG006, new Object[ ] { e.getMessage() });
			LOGGER.error(error);
			throw new ValetStatisticsException(error, e);
		}
		return fctValidation;
	}

	/**
	 * Method that handles the closing of a {@link #em}.
	 */
	private void safeCloseEntityManager() {
		PentahoDWEntityManager.getInstance().close();
	}
	

	/**
	 * Method that removes those transactions that are associated with the node passed by parameter.
	 * 
	 * @param idNode Parameter that represents node identifier.
	 * @throws ValetStatisticsException If the method fails.
	 */
	private void removeTransactionsByNode(Long idNode) throws ValetStatisticsException {
		if (idNode == null) {		
			throw new ValetStatisticsException(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG003));
		}
		try {
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();
			parameters.put(ID_NODE_PARAM, idNode);
			em.executeNamedQuery(QUERYNAME_REMOVE_TRANSACTIONS_BY_NODE, parameters);
		} catch (Exception e) {
			String error = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG002, new Object[] {idNode});
			LOGGER.error(error);
			throw new ValetStatisticsException(error, e);
		}
	}
	
	
	
	/**
	 * Method that removes those transactions of validations that are associated with the node passed by parameter.
	 * 
	 * @param idNode Parameter that represents node identifier.
	 * @throws ValetStatisticsException If the method fails.
	 */
	private void removeValidationsByNode(Long idNode) throws ValetStatisticsException {
		if (idNode == null) {		
			throw new ValetStatisticsException(Language.getResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG003));
		}
		try {
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();
			parameters.put(ID_NODE_PARAM, idNode);
			em.executeNamedQuery(QUERYNAME_REMOVE_VALIDATIONS_BY_NODE, parameters);
		} catch (Exception e) {
			String error = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.PMBOI_LOG002, new Object[] {idNode});
			LOGGER.error(error);
			throw new ValetStatisticsException(error, e);
		}
	}
	
	
	
	
}
