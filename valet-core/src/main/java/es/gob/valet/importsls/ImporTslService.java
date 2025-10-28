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
 * <b>File:</b><p>es.gob.valet.importsls.ImporTslService.java.</p>
 * <b>Description:</b><p>Class that contains all the methods necessary to carry out the import of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.5, 28/10/2025.
 */
package es.gob.valet.importsls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCountryLanguage;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.dto.MappingByServSummary;
import es.gob.valet.persistence.configuration.model.dto.MappingByTslSummary;
import es.gob.valet.persistence.configuration.model.dto.MappingTslDTO;
import es.gob.valet.persistence.configuration.model.dto.TslCountryRegionDTO;
import es.gob.valet.persistence.configuration.model.dto.TslCountryRegionMappingDTO;
import es.gob.valet.persistence.configuration.model.dto.TslDataDTO;
import es.gob.valet.persistence.configuration.model.dto.TslDataSummary;
import es.gob.valet.persistence.configuration.model.dto.TslServiceDTO;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.entity.TslLotlData;
import es.gob.valet.persistence.configuration.model.entity.TslMapping;
import es.gob.valet.persistence.configuration.model.entity.TslService;
import es.gob.valet.persistence.configuration.model.repository.CAssociationTypeRepository;
import es.gob.valet.persistence.configuration.model.repository.CTslImplRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionMappingRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.TslMappingRepository;
import es.gob.valet.persistence.configuration.model.repository.TslServiceRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.exceptions.ImportException;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.scheduler.TasksScheduler;
import es.gob.valet.quartz.scheduler.ValetSchedulerException;
import es.gob.valet.service.impl.ExportService;
import es.gob.valet.tasks.IFindNewTslRevisionsTaskConstants;
import es.gob.valet.tasks.TasksManager;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.certValidation.impl.ts119612.v020101.TSLValidator;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>interface that contains all the methods necessary to carry out the import of TSLs.</p>
 * <b>Project:</b><p>Class that contains all the methods necessary to carry out the import of TSLs.</p>
 * @version 1.5, 28/10/2025.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ImporTslService implements IImporTslService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ImporTslService.class);
	
	/**
	 * Identifier for task TASK01.
	 * This constant is used to reference task number 01 within the task management system.
	 */
	private static final String TASK_TASK01 = "TASK01";
	
	/**
	 * Total number of steps in the import process.
	 * This constant defines how many steps the import process should have and is used to set
	 * the size of the progress array and to control the number of iterations in the process.
	 */
	public static final int TOTAL_STEPS = 5;

	/**
	 * Representation of a line break.
	 * This constant is used to insert line breaks in text strings and log messages,
	 * ensuring consistent formatting across different parts of the application.
	 */
	private static final String LINE_BREAK = "\n";

	/**
	 * Representation of a tab character.
	 * This constant is used to insert tab spaces in text strings and log messages,
	 * ensuring consistent indentation across different parts of the application.
	 */
	private static final String TAB = "\t";
	
	/**
	 * Atomic integer representing the current step in the process.
	 * This field is used to track the current step number in a multi-step process. It is thread-safe.
	 */
	private final AtomicInteger currentStep = new AtomicInteger(0);

	/**
	 * Array that holds the progress of each step in the process.
	 * This array stores the progress (as percentages) for each step in the process. 
	 * The length of this array is determined by the TOTAL_STEPS constant.
	 */
	private final int[] stepProgress = new int[TOTAL_STEPS];

	/**
	 * Flag indicating whether the process is currently running.
	 * This volatile field is used to track whether the process is in progress. 
	 * It can be accessed and modified by multiple threads.
	 */
	private volatile boolean isRunning = false;

	/**
	 * Flag indicating whether an error has occurred in the process.
	 * This volatile field tracks whether any error has been encountered during the process. 
	 * It can be accessed and modified by multiple threads.
	 */
	private volatile boolean isError = false;

	/**
	 * Message detailing the error encountered during the process.
	 * This field holds the error message, if any, that explains the issue encountered during the execution.
	 */
	private String messageError;

	/**
	 * InputStream for the file content to be processed.
	 * This field holds the content of a file that is being processed. It can be used to read the file data.
	 */
	private InputStream contentFile;

	/**
	 * Flag indicating whether to overwrite existing data during the process.
	 * This field is used to determine whether to overwrite existing data if a conflict occurs.
	 */
	private boolean overwrite;

	/**
	 * List of serialized elements to be processed.
	 * This field holds a list of objects that have been serialized, which will be deserialized and processed during the task.
	 */
	private List<Object> listSerializedElements;

	/**
	 * List of TSL data that has not been updated.
	 * This list contains TSL data records that have been retrieved but have not been updated yet.
	 */
	private List<TslData> listTslDataNotUpdate;

	/**
	 * Number of TSL data records imported.
	 * This field tracks the number of TSL data records that have been successfully imported.
	 */
	private int numTslImp;

	/**
	 * Number of TSL country-region mappings imported.
	 * This field tracks the number of TSL mappings related to country regions that have been successfully imported.
	 */
	private int numMappingByTslImp;

	/**
	 * Number of TSL service mappings imported.
	 * This field tracks the number of TSL mappings related to services that have been successfully imported.
	 */
	private int numMappingByServImp;

	/**
	 * Number of TSL data records that were not imported.
	 * This field tracks the number of TSL data records that were not imported due to certain conditions.
	 */
	private int numTslDataNotImp;

	/**
	 * Number of TSL country-region mappings that were not imported.
	 * This field tracks the number of TSL country-region mappings that were not imported due to certain conditions.
	 */
	private int numMappingByTslNotImp;

	/**
	 * Number of TSL service mappings that were not imported.
	 * This field tracks the number of TSL service mappings that were not imported due to certain conditions.
	 */
	private int numMappingByServNotImp;

	/**
	 * List of TSL data identifiers that were not imported due to the "not overwrite" condition.
	 * This list contains the identifiers of TSL data records that were not imported because the "overwrite" flag was not set to true.
	 */
	private List<String> listTslDataNotImpByNotOverrite;

	/**
	 * List of TSL data identifiers that were not imported due to a lower version number.
	 * This list contains the identifiers of TSL data records that were not imported because their version number is lower than the registered version.
	 */
	private List<String> listTslDataNotImpByVersionMinor;

	/**
	 * List of TSL country-region mapping identifiers that were not imported due to the "not overwrite" condition.
	 * This list contains the identifiers of TSL country-region mappings that were not imported because the "overwrite" flag was not set to true.
	 */
	private List<String> listMappingByTslNotImpByNotOverrite;

	/**
	 * List of TSL service mapping identifiers that were not imported due to the "not overwrite" condition.
	 * This list contains the identifiers of TSL service mappings that were not imported because the "overwrite" flag was not set to true.
	 */
	private List<String> listMappingByServNotImpByNotOverrite;

	/**
	 * List of TSL data summaries.
	 * This list contains summaries of the TSL data imported, typically including information like country, version, and location.
	 */
	private List<TslDataSummary> lisTslDataSummary;

	/**
	 * List of TSL country-region mapping summaries.
	 * This list contains summaries of TSL country-region mappings that were imported, including mapping identifiers and values.
	 */
	private List<MappingByTslSummary> listMappingByTslSummary;

	/**
	 * List of TSL service mapping summaries.
	 * This list contains summaries of TSL service mappings that were imported, including mapping identifiers, field values, and associated service names.
	 */
	private List<MappingByServSummary> listMappingByServDTO;

	/**
	 * StringBuilder used for building a summary of the import process.
	 * This field is used to accumulate a textual summary of the import process, such as the number of records imported or any errors encountered.
	 */
	private StringBuilder sbSummaryImport;

	
	/**
	 * Service for handling operations related to TSL country-region data.
	 * This service is responsible for managing TSL country-region data, including fetching and updating records.
	 */
	@Autowired
	private ITslCountryRegionService iTslCountryRegionService;

	/**
	 * Repository for managing TSL country-region mappings.
	 * This repository is responsible for performing CRUD operations on TSL country-region mapping records in the database.
	 */
	@Autowired
	private TslCountryRegionMappingRepository tslCountryRegionMappingRepository;

	/**
	 * Repository for managing association types in the TSL mapping process.
	 * This repository is responsible for performing CRUD operations on the association type records in the database.
	 */
	@Autowired
	private CAssociationTypeRepository cAssociationTypeRepository;

	/**
	 * Repository for managing TSL country-region records.
	 * This repository is responsible for performing CRUD operations on the TSL country-region records in the database.
	 */
	@Autowired
	private TslCountryRegionRepository tslCountryRegionRepository;

	/**
	 * Service for managing tasks in the import process.
	 * This service is responsible for handling task operations, including task creation, execution, and tracking.
	 */
	@Autowired
	private ITaskService iTaskService;

	
	/**
	 * Repository for managing TSL mappings.
	 * This repository is responsible for performing CRUD operations on the TSL mapping records in the database.
	 */
	@Autowired
	private TslMappingRepository tslMappingRepository;

	/**
	 * Repository for managing TSL data.
	 * This repository is responsible for performing CRUD operations on the TSL data records in the database.
	 */
	@Autowired
	private TslDataRepository tslDataRepository;

	/**
	 * Repository for managing TSL implementation details.
	 * This repository is responsible for performing CRUD operations on the TSL implementation records in the database.
	 */
	@Autowired
	private CTslImplRepository cTslImplRepository;

	/**
	 * Repository for managing TSL service data.
	 * This repository is responsible for performing CRUD operations on the TSL service records in the database.
	 */
	@Autowired
	private TslServiceRepository tslServiceRepository;
	
	/**
	 * Service responsible for handling export operations.
	 * <p>
	 * This service is automatically injected by Spring's dependency injection mechanism
	 * and provides methods to export data related to the application domain.
	 */
	@Autowired
	private ExportService exportService;
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#startProcessImport(org.springframework.web.multipart.MultipartFile, boolean)
	 */
	public void startProcessImport(MultipartFile tslsFile, boolean overwrite) throws IOException, ImportException {
		// Reseteamos todos los parametros pertenecientes al avance del proceso
		resetProcess();
		
		// Inicializamos las variables pertenecientes a la logica de negocio
		contentFile = tslsFile.getInputStream();
		this.overwrite = overwrite;
		
		// Inicializamos las variables pertenecientes al informe final de la importacion
		listTslDataNotUpdate = new ArrayList<>();
		numTslImp = NumberConstants.NUM0;
		numMappingByTslImp = NumberConstants.NUM0;
		numMappingByServImp = NumberConstants.NUM0;
		numTslDataNotImp = NumberConstants.NUM0;
		numMappingByTslNotImp = NumberConstants.NUM0;
		numMappingByServNotImp = NumberConstants.NUM0;
		listTslDataNotImpByNotOverrite = new ArrayList<String>();
		listTslDataNotImpByVersionMinor = new ArrayList<String>();
		listMappingByTslNotImpByNotOverrite = new ArrayList<String>();
		listMappingByServNotImpByNotOverrite = new ArrayList<String>();
		lisTslDataSummary = new ArrayList<TslDataSummary>();
		listMappingByTslSummary = new ArrayList<MappingByTslSummary>();
		listMappingByServDTO = new ArrayList<MappingByServSummary>();
		
		// Deserializamos y cargamos el contenido del fichero
		readZipFile();
	
	}

	/**
	 * Resets the process by clearing flags and progress values. This method is used to initialize or reset
	 * the state of the process before starting a new import.
	 */
	private void resetProcess() {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP001));
		isRunning = false;
		isError = false;
		currentStep.set(1);
		for (int i = 0; i < TOTAL_STEPS; i++) {
			stepProgress[i] = 0;
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#doImport()
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { ImporTslsException.class, Exception.class })
	public void doImport() throws ImporTslsException {
		for (int i = 1; i <= TOTAL_STEPS; i++) {
			switch (i) {
				case NumberConstants.NUM1:
					this.setCurrentStep(NumberConstants.NUM1);
					this.disableTslRelatedTask();
					break;
				case NumberConstants.NUM2:
					this.setCurrentStep(NumberConstants.NUM2);
					this.importDataTsl();
					break;
				case NumberConstants.NUM3:
					this.setCurrentStep(NumberConstants.NUM3);
					this.importMappingByTsl();
					break;
				case NumberConstants.NUM4:
					this.setCurrentStep(NumberConstants.NUM4);
					this.importMappingByService();
					break;
				case NumberConstants.NUM5:
					this.setCurrentStep(NumberConstants.NUM5);
					this.enableTslRelatedTask();
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#enableTslRelatedTask()
	 */
	public void enableTslRelatedTask() throws ImporTslsException {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP002));
		try {
			getAllProgress()[NumberConstants.NUM4] = NumberConstants.NUM50;
			Task task1 = iTaskService.getTaskByToken(TASK_TASK01);
			TasksManager.addOrUpdateTask(task1);
			getAllProgress()[NumberConstants.NUM4] = NumberConstants.NUM100;
		} catch (TaskValetException e) {
			LOGGER.error(e);
			throw new ImporTslsException(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP003));
		}
	}
	
	/**
	 * Imports the TSL service mapping data for each TSL service. It checks if a mapping already exists for each 
	 * service, and either updates or creates new mappings based on the provided DTOs. The progress of the import 
	 * is tracked and updated during the process.
	 */
	private void importMappingByService() {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP004));
		
		@SuppressWarnings("unchecked")
		List<TslServiceDTO> listTslServiceDTO = (List<TslServiceDTO>) listSerializedElements.get(NumberConstants.NUM2);
		
		List<CAssociationType> listCAssociationType = cAssociationTypeRepository.findAll();
		List<TslMapping> listTslMapping = tslMappingRepository.findAll();
		
		int totalTslServiceDTO = listTslServiceDTO.size();
        int processedTslServiceDTO = 0;
       
		for (TslServiceDTO tslServiceDTO: listTslServiceDTO) {
			TslData tslData = listTslDataNotUpdate.stream().filter(p -> p.getTslCountryRegion().getCountryRegionCode().equals(tslServiceDTO.getCountry())).findAny().orElse(null);
			if(tslData == null) {
				for (MappingTslDTO mappingTslDTO: tslServiceDTO.getListMappingTslDTO()) {
					String logicalFieldId = mappingTslDTO.getLogicalFieldId();
					String tspServiceName = tslServiceDTO.getTspServiceName();
					TslMapping tslMapping = listTslMapping.stream().filter(p -> p.getTslService().getTspServiceName().equals(tspServiceName) && p.getLogicalFieldId().equals(logicalFieldId)).findAny().orElse(null);
					CAssociationType cAssociationType = listCAssociationType.stream().filter(p -> Language.getResPersistenceConstants(p.getTokenName()).equals(mappingTslDTO.getcAssociationTypeDTO().getTokenName())).findAny().orElse(null);
					if(tslMapping != null) {
						if(overwrite) {
							TslService tslService = tslServiceRepository.findByTspServiceNameAndTspName(tslServiceDTO.getTspServiceName(), tslServiceDTO.getTspName());
							
							if(tslService == null) {
								tslService = new TslService();
								tslService.setCertificate(tslServiceDTO.getCertificate());
								tslService.setCountry(tslServiceDTO.getCountry());
								tslService.setTslVersion(tslServiceDTO.getTslVersion());
								tslService.setTspName(tslServiceDTO.getTspName());
								tslService.setTspServiceName(tslServiceDTO.getTspServiceName());
								tslService.setDigitalIdentityId(tslServiceDTO.getDigitalIdentityId());
								tslService.setDigitalIdentityCad(tslServiceDTO.getDigitalIdentityCad());
								tslServiceRepository.save(tslService);
							}
							
							tslMapping.setcAssociationType(cAssociationType);
							tslMapping.setLogicalFieldId(mappingTslDTO.getLogicalFieldId());
							tslMapping.setLogicalFieldValue(mappingTslDTO.getLogicalFieldValue());
							tslMapping.setTslService(tslService);
							tslMappingRepository.save(tslMapping);
							numMappingByServImp++;
							
							MappingByServSummary mappingByServDTO = new MappingByServSummary(UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(tslServiceDTO.getCountry())+"("+tslServiceDTO.getCountry()+")", tslServiceDTO.getTspName(), tslServiceDTO.getTspServiceName(), mappingTslDTO.getcAssociationTypeDTO().getTokenName(),mappingTslDTO.getLogicalFieldId(),mappingTslDTO.getLogicalFieldValue());
							listMappingByServDTO.add(mappingByServDTO);

						} else {
							listMappingByServNotImpByNotOverrite.add(mappingTslDTO.getLogicalFieldId());
							numMappingByServNotImp++;
						}
					} else {
						
						TslService tslService = tslServiceRepository.findByTspServiceNameAndTspName(tslServiceDTO.getTspServiceName(), tslServiceDTO.getTspName());
						
						if(tslService == null) {
							tslService = new TslService();
							tslService.setCertificate(tslServiceDTO.getCertificate());
							tslService.setCountry(tslServiceDTO.getCountry());
							tslService.setTslVersion(tslServiceDTO.getTslVersion());
							tslService.setTspName(tslServiceDTO.getTspName());
							tslService.setTspServiceName(tslServiceDTO.getTspServiceName());
							tslService.setDigitalIdentityId(tslServiceDTO.getDigitalIdentityId());
							tslService.setDigitalIdentityCad(tslServiceDTO.getDigitalIdentityCad());
							tslServiceRepository.save(tslService);
						}
						
						tslMapping = new TslMapping();
						tslMapping.setcAssociationType(cAssociationType);
						tslMapping.setLogicalFieldId(mappingTslDTO.getLogicalFieldId());
						tslMapping.setLogicalFieldValue(mappingTslDTO.getLogicalFieldValue());
						tslMapping.setTslService(tslService);
						tslMappingRepository.save(tslMapping);
						numMappingByServImp++;
						
						MappingByServSummary mappingByServDTO = new MappingByServSummary(UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(tslServiceDTO.getCountry())+"("+tslServiceDTO.getCountry()+")", tslServiceDTO.getTspName(), tslServiceDTO.getTspServiceName(), mappingTslDTO.getcAssociationTypeDTO().getTokenName(),mappingTslDTO.getLogicalFieldId(),mappingTslDTO.getLogicalFieldValue());
						listMappingByServDTO.add(mappingByServDTO);
					}
				}
			}
			// Calculamos el progreso
			getAllProgress()[NumberConstants.NUM3] = (processedTslServiceDTO * 100) / totalTslServiceDTO;
		}
		
		getAllProgress()[NumberConstants.NUM3] = NumberConstants.NUM100;
	}
	
	/**
	 * Imports the TSL mapping data for each country/region. It checks if a mapping already exists for each TSL 
	 * country/region, and either updates or creates new mappings based on the provided DTOs. The progress of the 
	 * import is tracked and updated during the process.
	 */
	private void importMappingByTsl() {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP005));
		
		List<CAssociationType> listCAssociationType = cAssociationTypeRepository.findAll();
		Map<Long, String> hashMapSimpleAssocValues = exportService.loadSimpleAssociationValues();
		List<TslCountryRegion> listTslCountryRegion = tslCountryRegionRepository.findAll();
		
		@SuppressWarnings("unchecked")
		List<TslCountryRegionDTO> listTslCountryRegionDTO = (List<TslCountryRegionDTO>) listSerializedElements.get(NumberConstants.NUM1);
		int totalTslCountryRegion = listTslCountryRegionDTO.size();
        int processedTslCountryRegion = 0;
        
		for (TslCountryRegionDTO tslCountryRegionDTO: listTslCountryRegionDTO) {
			TslData tslData = listTslDataNotUpdate.stream().filter(p -> p.getTslCountryRegion().getCountryRegionCode().equals(tslCountryRegionDTO.getCountryRegionCode())).findAny().orElse(null);
			if(tslData == null) {
				for (TslCountryRegionMappingDTO tslCountryRegionMappingDTO: tslCountryRegionDTO.getListTslCountryRegionMappingDTO()) {
					String mappingIdentificator = tslCountryRegionMappingDTO.getMappingIdentificator();
					CAssociationType cAssociationType = listCAssociationType.stream().filter(p -> Language.getResPersistenceConstants(p.getTokenName()).equals(tslCountryRegionMappingDTO.getcAssociationTypeDTO().getTokenName())).findAny().orElse(null);
					TslCountryRegion tslCountryRegion = listTslCountryRegion.stream().filter(p -> p.getCountryRegionCode().equals(tslCountryRegionDTO.getCountryRegionCode())).findAny().orElse(new TslCountryRegion());
					TslCountryRegionMapping tslCountryRegionMapping = tslCountryRegionMappingRepository.findMappingByIdentificatorAndCountryRegion(mappingIdentificator, tslCountryRegion.getIdTslCountryRegion());
					if(tslCountryRegionMapping != null) {
						if(overwrite) {							
							tslCountryRegionMapping.setAssociationType(cAssociationType);
							tslCountryRegionMapping.setMappingDescription(tslCountryRegionMappingDTO.getMappingDescription());
							
							if(cAssociationType.getIdAssociationType() == NumberConstants.NUM0) {
								Optional<Long> key = hashMapSimpleAssocValues.entrySet().stream().filter(p -> p.getValue().equals(tslCountryRegionMappingDTO.getMappingValue())).map(Map.Entry::getKey).findFirst();
								tslCountryRegionMapping.setMappingValue(key.get().toString());
							} else if(cAssociationType.getIdAssociationType() == NumberConstants.NUM4) {
								tslCountryRegionMapping.setMappingValue(tslCountryRegionMappingDTO.getMappingValue());
							}
							
							tslCountryRegionMappingRepository.save(tslCountryRegionMapping);
							numMappingByTslImp++;
							
							MappingByTslSummary mappingByTslSummary = new MappingByTslSummary(tslCountryRegionDTO.getCountryRegionName()+"("+tslCountryRegionDTO.getCountryRegionCode()+")",tslCountryRegionMappingDTO.getcAssociationTypeDTO().getTokenName(),tslCountryRegionMappingDTO.getMappingIdentificator(),tslCountryRegionMappingDTO.getMappingValue());
							listMappingByTslSummary.add(mappingByTslSummary);
							
						} else {
							listMappingByTslNotImpByNotOverrite.add(mappingIdentificator);
							numMappingByTslNotImp++;
						}
					} else {
						tslCountryRegionMapping = new TslCountryRegionMapping();
						tslCountryRegionMapping.setAssociationType(cAssociationType);
						tslCountryRegionMapping.setMappingDescription(tslCountryRegionMappingDTO.getMappingDescription());
						tslCountryRegionMapping.setMappingIdentificator(tslCountryRegionMappingDTO.getMappingIdentificator());
						if(cAssociationType.getIdAssociationType() == NumberConstants.NUM0) {
							Optional<Long> key = hashMapSimpleAssocValues.entrySet().stream().filter(p -> p.getValue().equals(tslCountryRegionMappingDTO.getMappingValue())).map(Map.Entry::getKey).findFirst();
							tslCountryRegionMapping.setMappingValue(key.get().toString());
						} else if(cAssociationType.getIdAssociationType() == NumberConstants.NUM4) {
							tslCountryRegionMapping.setMappingValue(tslCountryRegionMappingDTO.getMappingValue());
						}
						tslCountryRegionMapping.setTslCountryRegion(tslCountryRegion);
						tslCountryRegionMappingRepository.save(tslCountryRegionMapping);
						numMappingByTslImp++;
						
						MappingByTslSummary mappingByTslSummary = new MappingByTslSummary(tslCountryRegionDTO.getCountryRegionName()+"("+tslCountryRegionDTO.getCountryRegionCode()+")",tslCountryRegionMappingDTO.getcAssociationTypeDTO().getTokenName(),tslCountryRegionMappingDTO.getMappingIdentificator(),tslCountryRegionMappingDTO.getMappingValue());
						listMappingByTslSummary.add(mappingByTslSummary);
					}
				}
			}
			// Calculamos el progreso
			getAllProgress()[NumberConstants.NUM2] = (processedTslCountryRegion * 100) / totalTslCountryRegion;
		}
		
		getAllProgress()[NumberConstants.NUM2] = NumberConstants.NUM100;
	}
	
	/**
	 * Imports TSL (Trusted Service List) data by processing each {@link TslDataDTO}. 
	 * For each TSL, the method checks if the country/region is already registered, compares the versions, 
	 * and either updates or saves new data accordingly. It also tracks the progress of the import process.
	 * 
	 * @throws ImporTslsException if an error occurs during the import process
	 */
	private void importDataTsl() throws ImporTslsException {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP006));
		
		@SuppressWarnings("unchecked")
		List<TslDataDTO> listTslDataDTO = (List<TslDataDTO>) listSerializedElements.get(NumberConstants.NUM0);
		
		int totalTslData = listTslDataDTO.size();
        int processedTslData = 0;
        
		for (TslDataDTO tslDataDTO: listTslDataDTO) {
			processedTslData++;
             
			String countryRegionCode = tslDataDTO.getTslCountryRegionDTO().getCountryRegionCode();
			
			// Buscamos el pais dela tsl para evaluar si esta registrada
			TslCountryRegion tslCountryRegion = iTslCountryRegionService.getTslCountryRegionWithTslData(countryRegionCode);

			// Si esta registrada:
			if(tslCountryRegion != null && tslCountryRegion.getTslData() != null) {
				int versionFile = tslDataDTO.getSequenceNumber();
				int versionTslRegistered = tslCountryRegion.getTslData().getSequenceNumber();
				if(versionFile < versionTslRegistered) {
					listTslDataNotUpdate.add(tslCountryRegion.getTslData());
					listTslDataNotImpByVersionMinor.add(tslCountryRegion.getCountryRegionCode());
				} else if(versionFile >= versionTslRegistered) {
					if(overwrite) {
						updateTsls(tslDataDTO, tslCountryRegion);
						numTslImp++;
					} else {
						listTslDataNotImpByNotOverrite.add(tslCountryRegion.getCountryRegionCode());
						numTslDataNotImp++;
					}
				}
			// Si no esta registrada:
			} else {
				saveNewTsl(tslDataDTO, countryRegionCode, tslCountryRegion);
				numTslImp++;
			}
			// Calculamos el progreso
			getAllProgress()[NumberConstants.NUM1] = (processedTslData * 100) / totalTslData;
		}
	}

	/**
	 * Saves a new TSL (Trusted Service List) into the system, processing it from the provided {@link TslDataDTO}.
	 * This method decodes the XML document, retrieves the associated country/region, constructs the necessary objects
	 * (such as {@link TslCountryRegion}, {@link TslData}, and {@link ITSLObject}), and persists them in the database and cache.
	 * It also updates relevant mappings and summaries for the processed TSL data.
	 *
	 * @param tslDataDTO DTO containing the new TSL data to be saved
	 * @param countryRegionCode the country/region code for the TSL
	 * @param tslCountryRegion the existing {@link TslCountryRegion} object linked to the TSL data
	 * @throws ImporTslsException if an error occurs during processing or saving the TSL data
	 */
	private void saveNewTsl(TslDataDTO tslDataDTO, String countryRegionCode, TslCountryRegion tslCountryRegion) throws ImporTslsException {
		try {
			
			byte[ ] tslXMLbytes = Base64.getDecoder().decode(tslDataDTO.getXmlDocument());
			String urlTsl = tslDataDTO.getUriTslLocation();
			String specificationTsl = tslDataDTO.getcTslImplDTO().getSpecification();
			String versionTsl = tslDataDTO.getcTslImplDTO().getVersion();
			String responsible = tslDataDTO.getResponsible();
			
			// Obtenemos la TSL
			ITSLObject tslObject = TSLManager.getInstance().obtainTslAndCertFromSign(urlTsl, specificationTsl, versionTsl, tslXMLbytes);

			// Tratamos de obtener el nombre del país primero.
			String countryRegionName = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(countryRegionCode);

			// Construimos el POJO y lo almacenamos en base de datos.
			tslCountryRegion = new TslCountryRegion();
			tslCountryRegion.setCountryRegionCode(countryRegionCode);
			tslCountryRegion.setCountryRegionName(countryRegionName);

			tslCountryRegionRepository.save(tslCountryRegion);
							
			CTslImpl cTslImpl = cTslImplRepository.findAll().stream().filter(p -> p.getVersion().equals(tslDataDTO.getcTslImplDTO().getVersion())).findAny().orElse(null);

			// Counstruimos el TslDataPojo y vamos insertando los datos.
			TslData tslData = new TslData();
			tslData.setTslCountryRegion(tslCountryRegion);
			tslData.setTslImpl(cTslImpl);
			String uriTslLocation = TSLManager.TOKEN_UNKNOWN;
			if (!UtilsStringChar.isNullOrEmptyTrim(urlTsl)) {
				uriTslLocation = urlTsl;
			} else if (tslObject.getSchemeInformation().isThereSomeDistributionPoint()) {
				for (int i = 0; i < tslObject.getSchemeInformation().getDistributionPoints().size(); i++) {
					if (!tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
						uriTslLocation = tslObject.getSchemeInformation().getDistributionPoints().get(i).toString();
						break;
					}
				}
			}
			tslData.setResponsible(responsible);
			tslData.setUriTslLocation(uriTslLocation);
			tslData.setXmlDocument(tslXMLbytes);
			tslData.setIssueDate(tslObject.getSchemeInformation().getListIssueDateTime());
			tslData.setExpirationDate(tslObject.getSchemeInformation().getNextUpdate());
			tslData.setSequenceNumber(tslObject.getSchemeInformation().getTslSequenceNumber());
			tslData.setNewTSLAvailable(IFindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);
			
			// Evaluamos si la TSL es una lista de listas
			TSLValidator tSLValidator = new TSLValidator(tslObject);
			if(tSLValidator.checkIfTSLisListOfLists(tslObject.getSchemeInformation().getTslType().toString())) {
				// TODO: 921 - que campos insertaremos y como los insertaremos?¿?¿
				TslLotlData tslLotlData = new TslLotlData();
				tslLotlData.setSigningCertificate(tslObject.getSignTsl().get().getEncoded());
				// añadimos la información de la lista de listas a la TSL asociada
				tslData.setTslLotlData(tslLotlData);
			}
			
			// Lo añadimos en base de datos.
			tslDataRepository.save(tslData);
			
			// Y ahora lo añadimos en la caché compartida.
			ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);

			// se actualiza la información en los datos del arbol de mapeos de TSLs.
			TSLManager.getInstance().updateMapTslMappingTree(tslData.getTslCountryRegion().getCountryRegionCode(), tslData.getSequenceNumber().toString(), tslObject);
			
			TslDataSummary tslDataSummary = new TslDataSummary(tslCountryRegion.getCountryRegionName()+"("+tslCountryRegion.getCountryRegionCode()+")", tslData.getSequenceNumber(), tslData.getResponsible(), tslDataDTO.getIssueDate(), tslDataDTO.getExpirationDate(), tslData.getUriTslLocation());
			lisTslDataSummary.add(tslDataSummary);
			
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP007), e);
			throw new ImporTslsException();
		} catch (TSLCacheException e) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP008), e);
			throw new ImporTslsException();
		} catch (CertificateEncodingException e) {
			LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_002), e);
			throw new ImporTslsException();
		}
		
	}

	/**
	 * Updates an existing TSL (Trusted Service List) entity in the database and cache using
	 * the information provided in the given {@link TslDataDTO}. This method updates fields
	 * such as sequence number, responsible entity, issue and expiration dates, and the XML document.
	 * It also ensures that the updated data is correctly reflected in:
	 * <ul>
	 *   <li>The database via {@code tslDataRepository}</li>
	 *   <li>The in-memory cache through {@code TSLManager}</li>
	 *   <li>The mapping tree managed by the {@code TSLManager}</li>
	 *   <li>A summary list used to collect processed TSLs</li>
	 * </ul>
	 *
	 * @param tslDataDTO DTO containing updated TSL data
	 * @param tslCountryRegion the region object linked to the TSL entry to be updated
	 * @throws ImporTslsException if any issue occurs during update, conversion, or cache synchronization
	 */
	private void updateTsls(TslDataDTO tslDataDTO, TslCountryRegion tslCountryRegion) throws ImporTslsException {
		try {
			TslData tslData = tslCountryRegion.getTslData();
			byte [] byteTsl = Base64.getDecoder().decode(tslDataDTO.getXmlDocument());
			
			CTslImpl cTslImpl = cTslImplRepository.findAll().stream().filter(p -> p.getVersion().equals(tslDataDTO.getcTslImplDTO().getVersion())).findAny().orElse(null);
			tslData.setNewTSLAvailable(IFindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);
			tslData.setLastNewTSLAvailableFind(null);
			tslData.setSequenceNumber(tslDataDTO.getSequenceNumber());
			tslData.setResponsible(tslDataDTO.getResponsible());
			tslData.setIssueDate(UtilsDate.transformDate(tslDataDTO.getIssueDate(), UtilsDate.FORMAT_DATE_TIME_STANDARD));
			tslData.setExpirationDate(tslDataDTO.getExpirationDate() != null && !tslDataDTO.getExpirationDate().isEmpty() ? UtilsDate.transformDate(tslDataDTO.getExpirationDate(), UtilsDate.FORMAT_DATE_TIME_STANDARD) : null);
			tslData.setXmlDocument(byteTsl);
			tslData.setUriTslLocation(tslDataDTO.getUriTslLocation());
			tslData.setTslImpl(cTslImpl);
			
			// Obtenemos la TSL
			String specificationTsl = tslDataDTO.getcTslImplDTO().getSpecification();
			String versionTsl = tslDataDTO.getcTslImplDTO().getVersion();
			ITSLObject tslObject = TSLManager.getInstance().obtainTslAndCertFromSign(tslDataDTO.getUriTslLocation(), specificationTsl, versionTsl, byteTsl);
			
			// Evaluaremos si es una lista de listas
			TSLValidator tSLValidator = new TSLValidator(tslObject);
			if(tSLValidator.checkIfTSLisListOfLists(tslObject.getSchemeInformation().getTslType().toString())) {
				TslLotlData tslLotlData = tslData.getTslLotlData();
				tslLotlData.setSigningCertificate(tslObject.getSignTsl().get().getEncoded());
				// TODO: 921 - que campos actualizaremos y como los actualizaremos?¿?¿
			}
						
			// Actualizamos la TSL en BD
			tslDataRepository.save(tslData);
			
			// Actualizamos la TSL en cache
			ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);
			
			// Actualizamos las TSL del arbol de mapeos
			TSLManager.getInstance().updateMapTslMappingTree(tslData.getTslCountryRegion().getCountryRegionCode(), tslData.getSequenceNumber().toString(), tslObject);
			
			TslDataSummary tslDataSummary = new TslDataSummary(tslCountryRegion.getCountryRegionName()+"("+tslCountryRegion.getCountryRegionCode()+")", tslData.getSequenceNumber(), tslData.getResponsible(), tslDataDTO.getIssueDate(), tslDataDTO.getExpirationDate(), tslData.getUriTslLocation());
			lisTslDataSummary.add(tslDataSummary);
			
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP009), e);
			throw new ImporTslsException();
		} catch (ParseException e) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP010), e);
			throw new ImporTslsException();
		} catch (TSLCacheException e) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP011), e);
			throw new ImporTslsException();
		} catch (CertificateEncodingException e) {
			LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_002), e);
			throw new ImporTslsException();
		}
	}
	
	/**
	 * Converts a version string (e.g., "1.2.3") into an integer representation
	 * to allow easy comparison between versions. Each part of the version is
	 * normalized to two digits and concatenated to form the resulting integer.
	 *
	 * <p>Example:
	 * <ul>
	 *     <li>"1.2.3" → "010203" → 10203</li>
	 *     <li>"10.0" → "1000" → 1000</li>
	 * </ul>
	 *
	 * @param version the version string in dot-separated format (e.g., "1.2.3")
	 * @return an integer representation of the version
	 * @throws NumberFormatException if any part of the version is not a valid number
	 */
	public int versionToInt(String version) {
	    String[] parts = version.split("\\.");
	    StringBuilder number = new StringBuilder();

	    for (String part : parts) {
	        // Normalizamos a 2 dígitos por segmento (ajustable)
	        number.append(String.format("%02d", Integer.parseInt(part)));
	    }

	    return Integer.parseInt(number.toString());
	}

	/**
	 * Reads the ZIP file provided in the input stream and deserializes its content
	 * into three separate lists:
	 * <ul>
	 *     <li>TslDataDTO objects (tslData)</li>
	 *     <li>TslCountryRegionDTO objects (tslCountryRegionMapping)</li>
	 *     <li>TslServiceDTO objects (tslMapping)</li>
	 * </ul>
	 * Each recognized entry is processed using {@link #readZIPEntry}, and the resulting
	 * lists are stored in {@code listSerializedElements}.
	 *
	 * @throws ImportException if an error occurs while reading or processing the ZIP file
	 */
	private void readZipFile() throws ImportException {
	    /*
	     * Definimos una lista de listas con los objetos serializados. La lista se compondrá de 3 entradas:
	     * 1) tslData
	     * 2) tslCountryRegionMapping
	     * 3) tslMapping
	     */

	    LOGGER.info("Deserializando los ficheros");

	    listSerializedElements = new ArrayList<>();
	    List<TslDataDTO> listTslDataDTO = new ArrayList<>();
	    List<TslCountryRegionDTO> listTslCountryRegionDTO = new ArrayList<>();
	    List<TslServiceDTO> listTslServiceDTO = new ArrayList<>();
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    byte[] buffer = new byte[NumberConstants.NUM2048];

	    try (ZipInputStream zipStream = new ZipInputStream(contentFile)) {
	        ZipEntry entry;
	        while ((entry = zipStream.getNextEntry()) != null) {
	            readZIPEntry(entry, buffer, zipStream, mapper, listTslDataDTO, listTslCountryRegionDTO, listTslServiceDTO);
	        }
	    } catch (IOException e) {
	        LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP012), e);
	        throw new ImportException();
	    }

	    LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP013));
	    listSerializedElements.add(listTslDataDTO);
	    listSerializedElements.add(listTslCountryRegionDTO);
	    listSerializedElements.add(listTslServiceDTO);
	}

	/**
	 * Reads and processes a single entry from a ZIP archive. Depending on the entry name,
	 * the content is deserialized into the corresponding DTO and added to the appropriate list.
	 *
	 * @param entry the ZIP entry to read
	 * @param buffer the buffer used for reading data from the input stream
	 * @param stream the input stream of the ZIP file
	 * @param mapper the JSON object mapper used to deserialize the data
	 * @param listTslDataDTO the list to populate with TSL data DTOs
	 * @param listTslCountryRegionDTO the list to populate with TSL country-region mapping DTOs
	 * @param listTslServiceDTO the list to populate with TSL service mapping DTOs
	 * @throws ImportException if there is an error reading or processing the entry
	 */
	private void readZIPEntry(ZipEntry entry, byte[ ] buffer, InputStream stream, ObjectMapper mapper, List<TslDataDTO> listTslDataDTO, List<TslCountryRegionDTO> listTslCountryRegionDTO, List<TslServiceDTO> listTslServiceDTO) throws ImportException {
		// Comprobamos que la entrada no es un directorio
		if (!entry.isDirectory()) {
			OutputStream output = null;
			// Leemos el fichero
			try {
				output = new ByteArrayOutputStream();
				int len = 0;
				while ((len = stream.read(buffer)) > 0) {
					output.write(buffer, 0, len);
				}
				// En función del fichero, lo parseamos y metemos en la lista adecuada
				if (entry.getName().startsWith("tslData/")) {
					listTslDataDTO.add(mapper.readValue(((ByteArrayOutputStream) output).toByteArray(), TslDataDTO.class));
					LOGGER.info(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP014, new Object[ ] { entry.getName() }));
				} else if (entry.getName().startsWith("tslCountryRegionMapping/")) {
					listTslCountryRegionDTO.add(mapper.readValue(((ByteArrayOutputStream) output).toByteArray(), TslCountryRegionDTO.class));
					LOGGER.info(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP014, new Object[ ] { entry.getName() }));
				} else if (entry.getName().startsWith("tslMapping/")) {
					listTslServiceDTO.add(mapper.readValue(((ByteArrayOutputStream) output).toByteArray(), TslServiceDTO.class));
					LOGGER.info(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP014, new Object[ ] { entry.getName() }));
				}
			} catch (IOException e) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP015), e);
				throw new ImportException();
			} finally {
				// Cerramos recursos
				UtilsResources.safeCloseOutputStream(output);
			}
		}
		
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#disableTslRelatedTask()
	 */
	public void disableTslRelatedTask() throws ImporTslsException {
		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP016));
		
		// Obtenemos una instancia del scheduler.
		TasksScheduler tasksScheduler = TasksScheduler.getInstance();
		
		String taskTslSyncro = Language.getResPersistenceConstants(TASK_TASK01);
		try {
			getAllProgress()[NumberConstants.NUM0] = NumberConstants.NUM50;
			if (tasksScheduler.checkIfExistsTask(taskTslSyncro)) {
				tasksScheduler.stopTask(taskTslSyncro);
			} else {
				LOGGER.warn(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP017, new Object[ ] { taskTslSyncro }));
			}
			getAllProgress()[NumberConstants.NUM0] = NumberConstants.NUM100;
		}  catch (ValetSchedulerException e) {
			LOGGER.error(e);
			throw new ImporTslsException(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP018));
		}
	}
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#isRunning()
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#isError()
	 */
	public boolean isError() {
		return isError;
	}
	
	/**
	 * Sets the running status of the import process.
	 *
	 * @param isRunning {@code true} if the process is currently running, {@code false} otherwise
	 */
	public void setRunning(boolean isRunning) {
	    this.isRunning = isRunning;
	}


	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#setError(boolean)
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getCurrentStep()
	 */
	public int getCurrentStep() {
		return currentStep.get();
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#setCurrentStep(int)
	 */
	public void setCurrentStep(int i) {
		currentStep.set(i);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getStepProgress(int)
	 */
	public int getStepProgress(int step) {
		return stepProgress[step - 1];
	}

	/**
	 * Returns the array containing the progress percentage of each step.
	 * Each element represents a specific step in the import process.
	 *
	 * @return an array of integers indicating step progress
	 */
	public synchronized int[] getAllProgress() {
	    return stepProgress;
	}

	/**
	 * Returns the error message associated with the import process, if any.
	 *
	 * @return a string with the error message or {@code null} if no error occurred
	 */
	public String getMessageError() {
	    return messageError;
	}

	/**
	 * Sets the error message for the import process.
	 *
	 * @param messageError the error message to set
	 */
	public void setMessageError(String messageError) {
	    this.messageError = messageError;
	}

	/**
	 * Returns the number of TSL entries that were successfully imported.
	 *
	 * @return the count of imported TSL entries
	 */
	public int getNumTslImp() {
	    return numTslImp;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getSbSummaryImport()
	 */
	public String getSbSummaryImport() {
		// Inicializamos el string buffer
		sbSummaryImport = new StringBuilder();
		
		if(this.overwrite) {
			sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP019) + LINE_BREAK);
		} else {
			sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP020) + LINE_BREAK);
		}
		
		if(!lisTslDataSummary.isEmpty() && lisTslDataSummary.size() > NumberConstants.NUM0) {
			sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP021) + LINE_BREAK);
			
			for (TslDataSummary tslDataSummary: lisTslDataSummary) {
				sbSummaryImport.append(TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP022, new Object[ ] { tslDataSummary.getCountry() }) + LINE_BREAK);
				sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP023, new Object[ ] { tslDataSummary.getNumberSequence() }) + LINE_BREAK);
				sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP024, new Object[ ] { tslDataSummary.getResponsible() }) + LINE_BREAK);
				sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP025, new Object[ ] { tslDataSummary.getIssueDate() }) + LINE_BREAK);
				sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP026, new Object[ ] { tslDataSummary.getExpireDate() }) + LINE_BREAK);
				sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP027, new Object[ ] { tslDataSummary.getDistributionPoint() }) + LINE_BREAK);
			}
		}
		
		if(!listMappingByTslSummary.isEmpty() && listMappingByTslSummary.size() > NumberConstants.NUM0) {
			sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP028) + LINE_BREAK);
			
			Map<String, List<MappingByTslSummary>> groupByCountry =
				    listMappingByTslSummary.stream()
				        .collect(Collectors.groupingBy(MappingByTslSummary::getCountry));

			for (Map.Entry<String, List<MappingByTslSummary>> entry : groupByCountry.entrySet()) {
				String country = entry.getKey();
			    List<MappingByTslSummary> listMappingByTslSummary = entry.getValue();
			    
			    sbSummaryImport.append(TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP022, new Object[ ] { country }) + LINE_BREAK);
			    
			    for (MappingByTslSummary mappingByTslSummary: listMappingByTslSummary) {
			    	sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP029, new Object[ ] { mappingByTslSummary.getIdentificator() }) + LINE_BREAK);
			    	sbSummaryImport.append(TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP030, new Object[ ] { mappingByTslSummary.getAssociationType() }) + LINE_BREAK);
					sbSummaryImport.append(TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP031, new Object[ ] { mappingByTslSummary.getValue() }) + LINE_BREAK);
			    }
			}
		}
		
		if(!listMappingByServDTO.isEmpty() && listMappingByServDTO.size() > NumberConstants.NUM0) {
			sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP032) + LINE_BREAK);
			
			Map<String, List<MappingByServSummary>> groupByCountry =
					listMappingByServDTO.stream()
				        .collect(Collectors.groupingBy(MappingByServSummary::getCountry));
		
			for (Map.Entry<String, List<MappingByServSummary>> entry : groupByCountry.entrySet()) {
				String country = entry.getKey();
				
				sbSummaryImport.append(TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP022, new Object[ ] { country }) + LINE_BREAK);
				
				Map<String, List<MappingByServSummary>> groupByTspAndService =
						entry.getValue().stream()
					        .collect(Collectors.groupingBy(item -> 
					            item.getNameTsp() + "&" + item.getNameService()
				));

				for (Map.Entry<String, List<MappingByServSummary>> entry2 : groupByTspAndService.entrySet()) {
					sbSummaryImport.append(TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP033, new Object[ ] { entry2.getKey().split("&")[0] }) + LINE_BREAK);
					sbSummaryImport.append(TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP034, new Object[ ] { entry2.getKey().split("&")[1] }) + LINE_BREAK);
					
					List<MappingByServSummary> listMappingByServSummary = entry2.getValue();
					
					for (MappingByServSummary mappingByServSummary: listMappingByServSummary) {
						sbSummaryImport.append(TAB + TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP035, new Object[ ] { mappingByServSummary.getIdLogicalField() }) + LINE_BREAK);
						sbSummaryImport.append(TAB + TAB + TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP036, new Object[ ] { mappingByServSummary.getAssociationType() }) + LINE_BREAK);
						sbSummaryImport.append(TAB + TAB + TAB + TAB + TAB + Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP037, new Object[ ] { mappingByServSummary.getValueLogicalField() }) + LINE_BREAK);
					}
				}
				
			}
		}
		
		if(this.overwrite) {
			String message = this.getListTslDataNotImpByVersionMinor();
			if(message != null && message.length() > NumberConstants.NUM0) {
				sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP038) + LINE_BREAK);
				sbSummaryImport.append(message);
			}
		} else {
			if(listTslDataNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP039) + LINE_BREAK);
				sbSummaryImport.append(String.join(", ", listTslDataNotImpByNotOverrite)+ LINE_BREAK + LINE_BREAK);
			}
			if(listTslDataNotImpByVersionMinor.size()>NumberConstants.NUM0) {
				sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP040) + LINE_BREAK);
				sbSummaryImport.append(String.join(", ", listTslDataNotImpByVersionMinor)+LINE_BREAK+LINE_BREAK);
			}
			if(listMappingByTslNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP041) + LINE_BREAK);
				sbSummaryImport.append(listMappingByTslNotImpByNotOverrite.stream().distinct().collect(Collectors.joining(", "))+LINE_BREAK+LINE_BREAK);
			}
			if(listMappingByServNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP042) + LINE_BREAK);
				sbSummaryImport.append(String.join(", ", listMappingByServNotImpByNotOverrite)+LINE_BREAK+LINE_BREAK);
			}
		}
		
		return Base64.getEncoder().encodeToString(sbSummaryImport.toString().getBytes());
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getListTslDataNotImpByVersionMinor()
	 */
	public String getListTslDataNotImpByVersionMinor() {
		String message = null;
		if(listTslDataNotImpByVersionMinor != null && !listTslDataNotImpByVersionMinor.isEmpty()) {
			String countries = String.join(", ", listTslDataNotImpByVersionMinor);
			message = Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP043, new Object[ ] { countries });
		}
		return message;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getNumMappingByTslImp()
	 */
	public int getNumMappingByTslImp() {
		return numMappingByTslImp;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getNumMappingByServImp()
	 */
	public int getNumMappingByServImp() {
		return numMappingByServImp;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getNumTslDataNotImp()
	 */
	public int getNumTslDataNotImp() {
		return numTslDataNotImp;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getNumMappingByTslNotImp()
	 */
	public int getNumMappingByTslNotImp() {
		return numMappingByTslNotImp;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IImporTslService#getNumMappingByServNotImp()
	 */
	public int getNumMappingByServNotImp() {
		return numMappingByServNotImp;
	}
}
