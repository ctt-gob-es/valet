package es.gob.valet.importsls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
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
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
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
import es.gob.valet.tasks.IFindNewTslRevisionsTaskConstants;
import es.gob.valet.tasks.TasksManager;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ImporTslService implements IImporTslService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ImporTslService.class);
	
	private static final String TASK_TASK01 = "TASK01";
	private static final String TASK_TASK02 = "TASK02";
	public static final int TOTAL_STEPS = 5;
	
	private final AtomicInteger currentStep = new AtomicInteger(0);
	private final int[ ] stepProgress = new int[TOTAL_STEPS];
	private volatile boolean isRunning = false;
	private volatile boolean isError = false;
	private String messageError;
	private InputStream contentFile;
	private boolean overwrite;
	private List<Object> listSerializedElements;
	private List<TslData> listTslDataNotUpdate;
	private int numTslImp;
	private int numMappingByTslImp;
	private int numMappingByServImp;
	private int numTslDataNotImp;
	private int numMappingByTslNotImp;
	private int numMappingByServNotImp;
	private List<String> listTslDataNotImpByNotOverrite;
	private List<String> listTslDataNotImpByVersionMinor;
	private List<String> listMappingByTslNotImpByNotOverrite;
	private List<String> listMappingByServNotImpByNotOverrite;
	private List<TslDataSummary> lisTslDataSummary;
	private List<MappingByTslSummary> listMappingByTslSummary;
	private List<MappingByServSummary> listMappingByServDTO;
	private StringBuilder sbSummaryImport;
	
	@Autowired
	private ITslCountryRegionService iTslCountryRegionService;
	
	@Autowired
	private TslCountryRegionMappingRepository tslCountryRegionMappingRepository;
	
	@Autowired
	private CAssociationTypeRepository cAssociationTypeRepository;
	
	@Autowired
	private TslCountryRegionRepository tslCountryRegionRepository;
	
	@Autowired
	private ITaskService iTaskService;
	
	@Autowired
	private TslMappingRepository tslMappingRepository;
	
	@Autowired
	private TslDataRepository tslDataRepository;
	
	@Autowired
	private CTslImplRepository cTslImplRepository;
	
	@Autowired
	private TslServiceRepository tslServiceRepository;
	
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
		
		// Inicializamos el string buffer
		sbSummaryImport = new StringBuilder();
	}

	private void resetProcess() {
		LOGGER.info("Reseteamos los valores principales del proceso de importacion");
		isRunning = false;
		isError = false;
		currentStep.set(1);
		for (int i = 0; i < TOTAL_STEPS; i++) {
			stepProgress[i] = 0;
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { ImporTslsException.class, Exception.class })
	public void imporTslsUniqueTransaction() throws ImporTslsException {
		currentStep.set(NumberConstants.NUM2);
		importDataTsl();
	
		currentStep.set(NumberConstants.NUM3);
		importMappingByTsl();
		
		currentStep.set(NumberConstants.NUM4);
		importMappingByService();
	}

	public void enableTslRelatedTask() throws ImporTslsException {
		LOGGER.info("habilitando tareas relacionadas con las TSL");
		try {
			Task task1 = iTaskService.getTaskByToken(TASK_TASK01);
			TasksManager.addOrUpdateTask(task1);
			getAllProgress()[NumberConstants.NUM4] = NumberConstants.NUM50;
			Task task2 = iTaskService.getTaskByToken(TASK_TASK02);
			TasksManager.addOrUpdateTask(task2);
			getAllProgress()[NumberConstants.NUM4] = NumberConstants.NUM100;
		} catch (TaskValetException e) {
			LOGGER.error(e);
			throw new ImporTslsException("Se ha producido un fallo al iniciar las tareas relacionadas con las TSL");
		}
	}
	
	private void importMappingByService() {
		LOGGER.info("Comenzamos con la importación de mapeos por certificado");
		
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
	
	private void importMappingByTsl() {
		LOGGER.info("Comenzamos con la importación de mapeos para las tsl");
		
		List<CAssociationType> listCAssociationType = cAssociationTypeRepository.findAll();
		
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
					TslCountryRegionMapping tslCountryRegionMapping = tslCountryRegionMappingRepository.findByMappingIdentificator(mappingIdentificator);
					if(tslCountryRegionMapping != null) {
						if(overwrite) {							
							tslCountryRegionMapping.setAssociationType(cAssociationType);
							tslCountryRegionMapping.setMappingDescription(tslCountryRegionMappingDTO.getMappingDescription());
							tslCountryRegionMapping.setMappingValue(tslCountryRegionMappingDTO.getMappingValue());
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
						tslCountryRegionMapping.setMappingValue(tslCountryRegionMappingDTO.getMappingValue());
						TslCountryRegion tslCountryRegion = tslCountryRegionRepository.findByCountryRegionCode(tslCountryRegionDTO.getCountryRegionCode());
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
	
	private void importDataTsl() throws ImporTslsException {
		LOGGER.info("Comenzamos con la importanción de los datos para las TSL");
		
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

	private void saveNewTsl(TslDataDTO tslDataDTO, String countryRegionCode, TslCountryRegion tslCountryRegion) throws ImporTslsException {
		try {
			
			byte[ ] tslXMLbytes = Base64.getDecoder().decode(tslDataDTO.getXmlDocument());
			String urlTsl = tslDataDTO.getUriTslLocation();
			String specificationTsl = tslDataDTO.getcTslImplDTO().getSpecification();
			String versionTsl = tslDataDTO.getcTslImplDTO().getVersion();

			// Obtenemos la TSL
			ITSLObject tslObject = TSLManager.getInstance().obtainTslAndCertFromSign(urlTsl, specificationTsl, versionTsl, tslXMLbytes);

			// Tratamos de obtener el nombre del país primero.
			String countryRegionName = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(countryRegionCode);

			// Construimos el POJO y lo almacenamos en base de datos.
			tslCountryRegion = new TslCountryRegion();
			tslCountryRegion.setCountryRegionCode(countryRegionCode);
			tslCountryRegion.setCountryRegionName(countryRegionName);

			tslCountryRegionRepository.save(tslCountryRegion);
							
			CTslImpl cTslImpl = cTslImplRepository.findAll().stream().filter(p -> p.getSpecification().equals(tslDataDTO.getcTslImplDTO().getSpecification())).findAny().orElse(null);

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
			tslData.setUriTslLocation(uriTslLocation);
			tslData.setXmlDocument(tslXMLbytes);
			tslData.setIssueDate(tslObject.getSchemeInformation().getListIssueDateTime());
			tslData.setExpirationDate(tslObject.getSchemeInformation().getNextUpdate());
			tslData.setSequenceNumber(tslObject.getSchemeInformation().getTslSequenceNumber());
			tslData.setNewTSLAvailable(IFindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);
			
			// Lo añadimos en base de datos.
			tslDataRepository.save(tslData);
			
			// Y ahora lo añadimos en la caché compartida.
			ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);

			// se actualiza la información en los datos del arbol de mapeos de TSLs.
			TSLManager.getInstance().updateMapTslMappingTree(tslData.getTslCountryRegion().getCountryRegionCode(), tslData.getSequenceNumber().toString(), tslObject);
			
			TslDataSummary tslDataSummary = new TslDataSummary(tslCountryRegion.getCountryRegionName()+"("+tslCountryRegion.getCountryRegionCode()+")", tslData.getSequenceNumber(), tslData.getResponsible(), tslDataDTO.getIssueDate(), tslDataDTO.getExpirationDate(), tslData.getUriTslLocation());
			lisTslDataSummary.add(tslDataSummary);
			
		} catch (TSLManagingException e) {
			LOGGER.error("Se ha producido un fallo al añadir los datos de la tsl", e);
			throw new ImporTslsException();
		} catch (TSLCacheException e) {
			LOGGER.error("Se ha producido un fallo en la cache de las tsls", e);
			throw new ImporTslsException();
		}
		
	}

	private void updateTsls(TslDataDTO tslDataDTO, TslCountryRegion tslCountryRegion) throws ImporTslsException {
		try {
			TslData tslData = tslCountryRegion.getTslData();
			
			tslData.setNewTSLAvailable(IFindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);
			tslData.setLastNewTSLAvailableFind(null);
			tslData.setSequenceNumber(tslDataDTO.getSequenceNumber());
			tslData.setResponsible(tslDataDTO.getResponsible());
			tslData.setIssueDate(UtilsDate.transformDate(tslDataDTO.getIssueDate(), UtilsDate.FORMAT_DATE_TIME_STANDARD));
			tslData.setExpirationDate(tslDataDTO.getExpirationDate() != null && !tslDataDTO.getExpirationDate().isEmpty() ? UtilsDate.transformDate(tslDataDTO.getExpirationDate(), UtilsDate.FORMAT_DATE_TIME_STANDARD) : null);
			tslData.setXmlDocument(Base64.getDecoder().decode(tslDataDTO.getXmlDocument()));
			
			// Actualizamos la TSL en BD
			tslDataRepository.save(tslData);
			
			// Actualizamos la TSL en cache
			TSLDataCacheObject tdco = TSLManager.getInstance().getTSLDataCacheObject(tslCountryRegion.getTslData().getIdTslData());
			ITSLObject tslObject = (ITSLObject) tdco.getTslObject();
			ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);
			
			// Actualizamos las TSL del arbol de mapeos
			TSLManager.getInstance().updateMapTslMappingTree(tslData.getTslCountryRegion().getCountryRegionCode(), tslData.getSequenceNumber().toString(), tslObject);
			
			TslDataSummary tslDataSummary = new TslDataSummary(tslCountryRegion.getCountryRegionName()+"("+tslCountryRegion.getCountryRegionCode()+")", tslData.getSequenceNumber(), tslData.getResponsible(), tslDataDTO.getIssueDate(), tslDataDTO.getExpirationDate(), tslData.getUriTslLocation());
			lisTslDataSummary.add(tslDataSummary);
			
		} catch (TSLManagingException e) {
			LOGGER.error("Se ha producido un fallo al actualizar los datos de la tsl", e);
			throw new ImporTslsException();
		} catch (ParseException e) {
			LOGGER.error("Se ha producido un fallo al parsear las fechas de la tsl", e);
			throw new ImporTslsException();
		} catch (TSLCacheException e) {
			LOGGER.error("Se ha producido un fallo al obtener la tsl serializable de la cache", e);
			throw new ImporTslsException();
		}
	}
	
	public int versionToInt(String version) {
	    String[] parts = version.split("\\.");
	    StringBuilder number = new StringBuilder();

	    for (String part : parts) {
	        // Normalizamos a 2 dígitos por segmento (ajustable)
	        number.append(String.format("%02d", Integer.parseInt(part)));
	    }

	    return Integer.parseInt(number.toString());
	}

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
	        LOGGER.error("Se ha producido un error al procesar el ZIP", e);
	        throw new ImportException();
	    }

	    LOGGER.info("Cargamos los datos deserializados en una lista resultante");
	    listSerializedElements.add(listTslDataDTO);
	    listSerializedElements.add(listTslCountryRegionDTO);
	    listSerializedElements.add(listTslServiceDTO);
	}



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
					LOGGER.info("Entrada "+ entry.getName() + " deserializada");
				} else if (entry.getName().startsWith("tslCountryRegionMapping/")) {
					listTslCountryRegionDTO.add(mapper.readValue(((ByteArrayOutputStream) output).toByteArray(), TslCountryRegionDTO.class));
					LOGGER.info("Entrada "+ entry.getName() + " deserializada");
				} else if (entry.getName().startsWith("tslMapping/")) {
					listTslServiceDTO.add(mapper.readValue(((ByteArrayOutputStream) output).toByteArray(), TslServiceDTO.class));
					LOGGER.info("Entrada "+ entry.getName() + " deserializada");
				}
			} catch (IOException e) {
				LOGGER.error("Se ha producido un fallo al leer una entrada del zip", e);
				throw new ImportException();
			} finally {
				// Cerramos recursos
				UtilsResources.safeCloseOutputStream(output);
			}
		}
		
	}
	
	public void disableTslRelatedTask() throws ImporTslsException {
		LOGGER.info("deshabilitando tareas relacionadas con las TSL");
		
		// Obtenemos una instancia del scheduler.
		TasksScheduler tasksScheduler = TasksScheduler.getInstance();
		
		String taskTslSyncro = Language.getResPersistenceConstants(TASK_TASK01);
		String taskExternalAccess = Language.getResPersistenceConstants(TASK_TASK02);
		try {
			if (tasksScheduler.checkIfExistsTask(taskTslSyncro)) {
				tasksScheduler.stopTask(taskTslSyncro);
			} else {
				LOGGER.warn("La tarea "+taskTslSyncro+" no existe");
			}
			getAllProgress()[NumberConstants.NUM0] = NumberConstants.NUM50;
			if (tasksScheduler.checkIfExistsTask(taskExternalAccess)) {
				tasksScheduler.stopTask(taskExternalAccess);
			} else {
				LOGGER.warn("La tarea "+taskExternalAccess+" no existe");
			}
			getAllProgress()[NumberConstants.NUM0] = NumberConstants.NUM100;
		}  catch (ValetSchedulerException e) {
			LOGGER.error(e);
			throw new ImporTslsException("Se ha producido un fallo al detener las tareas relacionadas con las TSL");
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public boolean isError() {
		return isError;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public int getCurrentStep() {
		return currentStep.get();
	}
	
	public void setCurrentStep(int i) {
		currentStep.set(i);
	}

	public int getStepProgress(int step) {
		return stepProgress[step - 1];
	}

	public synchronized int[ ] getAllProgress() {
		return stepProgress;
	}
	
	public String getMessageError() {
		return messageError;
	}
	
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public int getNumTslImp() {
		return numTslImp;
	}

	public String getSbSummaryImport() {
		if(this.overwrite) {
			sbSummaryImport.append("RESUMEN IMPORTACIÓN CON SOBREESCRITURA\n");
		} else {
			sbSummaryImport.append("RESUMEN IMPORTACIÓN SIN SOBREESCRITURA\n");
		}
		
		if(!lisTslDataSummary.isEmpty() && lisTslDataSummary.size() > NumberConstants.NUM0) {
			sbSummaryImport.append("TSLS IMPORTADAS:\n");
			
			for (TslDataSummary tslDataSummary: lisTslDataSummary) {
				sbSummaryImport.append("\tPAIS: "+tslDataSummary.getCountry()+"\n");
				sbSummaryImport.append("\t\tNº SECUENCIA: "+tslDataSummary.getNumberSequence()+"\n");
				sbSummaryImport.append("\t\tRESPONSABLE: "+tslDataSummary.getResponsible()+"\n");
				sbSummaryImport.append("\t\tFECHA DE EMISIÓN: "+tslDataSummary.getIssueDate()+"\n");
				sbSummaryImport.append("\t\tFECHA DE CADUCIDAD: "+tslDataSummary.getExpireDate()+"\n");
				sbSummaryImport.append("\t\tPUNTO DE DISTRIBUCIÓN: "+tslDataSummary.getDistributionPoint()+"\n");
			}
		}
		
		if(!listMappingByTslSummary.isEmpty() && listMappingByTslSummary.size() > NumberConstants.NUM0) {
			sbSummaryImport.append("MAPEOS IMPORTADOS POR TLS:\n");
			
			Map<String, List<MappingByTslSummary>> groupByCountry =
				    listMappingByTslSummary.stream()
				        .collect(Collectors.groupingBy(MappingByTslSummary::getCountry));

			for (Map.Entry<String, List<MappingByTslSummary>> entry : groupByCountry.entrySet()) {
				String country = entry.getKey();
			    List<MappingByTslSummary> listMappingByTslSummary = entry.getValue();
			    
			    sbSummaryImport.append("\tPAIS: "+country+"\n");
			    
			    for (MappingByTslSummary mappingByTslSummary: listMappingByTslSummary) {
			    	sbSummaryImport.append("\t\tIDENTIFICADOR: "+mappingByTslSummary.getIdentificator()+"\n");
			    	sbSummaryImport.append("\t\t\tTIPO DE ASOCIACIÓN: "+mappingByTslSummary.getAssociationType()+"\n");
					sbSummaryImport.append("\t\t\tVALOR: "+mappingByTslSummary.getValue()+"\n");
			    }
			}
		}
		
		if(!listMappingByServDTO.isEmpty() && listMappingByServDTO.size() > NumberConstants.NUM0) {
			sbSummaryImport.append("MAPEOS IMPORTADOS POR SERVICIOS:\n");
			
			Map<String, List<MappingByServSummary>> groupByCountry =
					listMappingByServDTO.stream()
				        .collect(Collectors.groupingBy(MappingByServSummary::getCountry));
		
			for (Map.Entry<String, List<MappingByServSummary>> entry : groupByCountry.entrySet()) {
				String country = entry.getKey();
				
				sbSummaryImport.append("\tPAIS: "+country+"\n");
				
				Map<String, List<MappingByServSummary>> groupByTspAndService =
						entry.getValue().stream()
					        .collect(Collectors.groupingBy(item -> 
					            item.getNameTsp() + "&" + item.getNameService()
				));

				for (Map.Entry<String, List<MappingByServSummary>> entry2 : groupByTspAndService.entrySet()) {
					sbSummaryImport.append("\t\tNOMBRE DEL TSP: "+entry2.getKey().split("&")[0]+"\n");		
					sbSummaryImport.append("\t\t\tNOMBRE DEL SERVICIO TSP: "+entry2.getKey().split("&")[1]+"\n");
				
					List<MappingByServSummary> listMappingByServSummary = entry2.getValue();
					
					for (MappingByServSummary mappingByServSummary: listMappingByServSummary) {
						sbSummaryImport.append("\t\t\t\tID CAMPO LÓGICO: "+mappingByServSummary.getIdLogicalField()+"\n");
						sbSummaryImport.append("\t\t\t\t\tTIPO DE ASOCIACIÓN: "+mappingByServSummary.getAssociationType()+"\n");
						sbSummaryImport.append("\t\t\t\t\tVALOR CAMPO LÓGICO: "+mappingByServSummary.getValueLogicalField()+"\n");
					}
				}
				
			}
		}
		
		if(this.overwrite) {
			String message = this.getListTslDataNotImpByVersionMinor();
			if(!message.isEmpty()) {
				sbSummaryImport.append("TSLs no importadas:\n");
				sbSummaryImport.append(message);
			}
		} else {
			if(listTslDataNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append("TSLs no importadas por existir previamente en el sistema y tener desactivada la opción de sobreescritura:\n");
				sbSummaryImport.append(String.join(", ", listTslDataNotImpByNotOverrite)+"\n\n");
			}
			if(listTslDataNotImpByVersionMinor.size()>NumberConstants.NUM0) {
				sbSummaryImport.append("TSLs no importadas por tener una version inferior a la registrada en el sistema:\n");
				sbSummaryImport.append(String.join(", ", listTslDataNotImpByVersionMinor)+"\n\n");
			}
			if(listMappingByTslNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append("Mapeos por TSL NO importados por existir previamente en el sistema y tener desactivada la opción de sobreescritura:\n");
				sbSummaryImport.append(listMappingByTslNotImpByNotOverrite.stream().distinct().collect(Collectors.joining(", "))+"\n\n");
			}
			if(listMappingByServNotImpByNotOverrite.size()>NumberConstants.NUM0) {
				sbSummaryImport.append("Mapeos por Servicio NO importados por existir previamente en el sistema y tener desactivada la opción de sobreescritura:\n");
				sbSummaryImport.append(String.join(", ", listMappingByServNotImpByNotOverrite)+"\n\n");
			}
		}
		
		return Base64.getEncoder().encodeToString(sbSummaryImport.toString().getBytes());
	}
	
	public String getListTslDataNotImpByVersionMinor() {
		String message = null;
		if(listTslDataNotImpByVersionMinor != null && !listTslDataNotImpByVersionMinor.isEmpty()) {
			String countries = String.join(", ", listTslDataNotImpByVersionMinor);
			message = "Se ha intentando importar (" + countries + "), pero su version es inferior a la registrada en el sistema.";
		}
		return message;
	}

	public int getNumMappingByTslImp() {
		return numMappingByTslImp;
	}

	public int getNumMappingByServImp() {
		return numMappingByServImp;
	}

	public int getNumTslDataNotImp() {
		return numTslDataNotImp;
	}

	public int getNumMappingByTslNotImp() {
		return numMappingByTslNotImp;
	}

	public int getNumMappingByServNotImp() {
		return numMappingByServNotImp;
	}
}
