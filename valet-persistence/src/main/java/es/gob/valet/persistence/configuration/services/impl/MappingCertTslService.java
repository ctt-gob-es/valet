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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.MappingCertTslService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for mapping certificate TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.8, 18/10/2022.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.MappingTslDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingExportDTO;
import es.gob.valet.persistence.configuration.model.dto.TslServiceDTO;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.TslMapping;
import es.gob.valet.persistence.configuration.model.entity.TslService;
import es.gob.valet.persistence.configuration.model.repository.TslMappingRepository;
import es.gob.valet.persistence.configuration.model.repository.TslServiceRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.exceptions.ImportException;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.persistence.utils.ImportUtils;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Mapping Certificate TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 18/10/2022.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MappingCertTslService implements IMappingCertTslService {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	TslServiceRepository tslServiceRepository;
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	TslMappingRepository tslMappingRepository;
	
	/**
	 * Attribute that represents percentage process import completed.
	 */
	private int percentage = 0;
	
	/**
	 * Attribute that represents enviroments declarates in application.properties.
	 */
	@Autowired
	private Environment env;
	
	/**
	 * Attribute that represents map key to certificate.
	 */
	public static final String MAP_KEY_CERTIFICATE = "certificate";

	/**
	 * Attribute that represents map key to list mappings.
	 */
	private static final String MAP_KEY_LIST_MAPPINGS = "listTslMapping";

	/**
	 * Attribute that represents map key to export certificate.
	 */
	private static final String MAP_KEY_EXPORT_CERTIFICATE = "exportCertificate";
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#createTreeMappingCertTsl()
	 */
	@Override
	public List<BootstrapTreeNode> createTreeMappingCertTsl(Map<String, List<TslMappingDTO>> mapTsl, String valueSearch) {
		Set<BootstrapTreeNode> setBootstrapTreeNodeCountry = new HashSet<BootstrapTreeNode>();
		Set<BootstrapTreeNode> setBootstrapTreeNodeTSPName = new HashSet<BootstrapTreeNode>();
		Set<BootstrapTreeNode> setBootstrapTreeNodeTSPService = new HashSet<BootstrapTreeNode>();
		this.preparedListWithTslMappingDTO(mapTsl, setBootstrapTreeNodeCountry,setBootstrapTreeNodeTSPName,setBootstrapTreeNodeTSPService, valueSearch);
		this.joinElementsToNode2(setBootstrapTreeNodeTSPName, setBootstrapTreeNodeTSPService);
		this.joinElementsToNode1(setBootstrapTreeNodeCountry, setBootstrapTreeNodeTSPName);
		return new ArrayList<BootstrapTreeNode>(setBootstrapTreeNodeCountry); 
	}

	/**
	 * Method that join elements to node 1 to tree mappings certificate tsls.
	 * 
	 * @param setBootstrapTreeNodeCountry parameter that contain list set of countries.
	 * @param setBootstrapTreeNodeTSPName parameter that contain list set of TSPName.
	 */
	private void joinElementsToNode1(Set<BootstrapTreeNode> setBootstrapTreeNodeCountry, Set<BootstrapTreeNode> setBootstrapTreeNodeTSPName) {
		for (BootstrapTreeNode bootstrapTreeNodeTSPName : setBootstrapTreeNodeTSPName) {
			BootstrapTreeNode bootstrapTreeNodeCountry = setBootstrapTreeNodeCountry.stream().filter(bTNCountry -> bTNCountry.getNodeId().equals(bootstrapTreeNodeTSPName.getParentId())).findFirst().orElse(null);
			List<BootstrapTreeNode> listBootstrapTreeNode = bootstrapTreeNodeCountry.getNodes();
			if(null != listBootstrapTreeNode) {
				listBootstrapTreeNode.add(bootstrapTreeNodeTSPName);
			} else {
				listBootstrapTreeNode = new ArrayList<BootstrapTreeNode>();
				listBootstrapTreeNode.add(bootstrapTreeNodeTSPName);
				bootstrapTreeNodeCountry.setNodes(listBootstrapTreeNode);
			}
		}
	}

	/**
	 * Method that join elements to node 2 to tree mappings certificate tsls.
	 * 
	 * @param setBootstrapTreeNodeTSPName parameter that contain list set of TSPName.
	 * @param setBootstrapTreeNodeTSPService parameter that contain list set of TSPService.
	 */
	private void joinElementsToNode2(Set<BootstrapTreeNode> setBootstrapTreeNodeTSPName,
			Set<BootstrapTreeNode> setBootstrapTreeNodeTSPService) {
		for (BootstrapTreeNode bootstrapTreeNodeService : setBootstrapTreeNodeTSPService) {
			BootstrapTreeNode bootstrapTreeNodeTSPName = setBootstrapTreeNodeTSPName.stream().filter(bTNTSPname -> bTNTSPname.getNodeId().equals(bootstrapTreeNodeService.getParentId())).findFirst().orElse(null);
			List<BootstrapTreeNode> listBootstrapTreeNode = bootstrapTreeNodeTSPName.getNodes();
			if(null != listBootstrapTreeNode) {
				listBootstrapTreeNode.add(bootstrapTreeNodeService);
			} else {
				listBootstrapTreeNode = new ArrayList<BootstrapTreeNode>();
				listBootstrapTreeNode.add(bootstrapTreeNodeService);
				bootstrapTreeNodeTSPName.setNodes(listBootstrapTreeNode);
			}
		}
	}
	
	/**
	 * Method that prepared information to join nodes. 
	 * 
	 * @param mapTsl parameter that contain information with mapping certifcate tsls. 
	 * @param setBootstrapTreeNodeCountry parameter that contain list set of Country.
	 * @param setBootstrapTreeNodeTSPName parameter that contain list set of TSPName.
	 * @param setBootstrapTreeNodeTSPService parameter that contain list set of TSPService.
	 * @param valueSearch parameter that contain value to search.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void preparedListWithTslMappingDTO(Map<String, List<TslMappingDTO>> mapTsl,
			Set<BootstrapTreeNode> setBootstrapTreeNodeCountry, Set<BootstrapTreeNode> setBootstrapTreeNodeTSPName,
			Set<BootstrapTreeNode> setBootstrapTreeNodeTSPService, String valueSearch) {
		for (Map.Entry entry : mapTsl.entrySet()) {
			// Obtenemos la lista de TslMappingDTO
			List<TslMappingDTO> listTslMappingDTO =  (List<TslMappingDTO>) entry.getValue();
		    // Añadimos el pais a la lista resultados
		    BootstrapTreeNode bootstrapTreeNodeCountry = new BootstrapTreeNode();
		    bootstrapTreeNodeCountry.setText(entry.getKey().toString());
		    bootstrapTreeNodeCountry.setIcon(BootstrapTreeNode.ICON_PAWN);
		    bootstrapTreeNodeCountry.setNodeId(entry.getKey().toString());
		    bootstrapTreeNodeCountry.setSelectable(false);
		    setBootstrapTreeNodeCountry.add(bootstrapTreeNodeCountry);
		    // Recorremos la lista con los mappings tsls obtenida previamente		
		    for (TslMappingDTO tslMappingDTO: listTslMappingDTO) {
			    if(!UtilsStringChar.isNullOrEmpty(valueSearch)) {
					if (tslMappingDTO.getTspName().toLowerCase().contains(valueSearch.toLowerCase())
							|| tslMappingDTO.getTspServiceName().toLowerCase().contains(valueSearch.toLowerCase())) {
			    		addTSPNameAndTSPService(setBootstrapTreeNodeTSPName, setBootstrapTreeNodeTSPService,
								tslMappingDTO);
			    	}
			    } else {
			    	addTSPNameAndTSPService(setBootstrapTreeNodeTSPName, setBootstrapTreeNodeTSPService,
							tslMappingDTO);
			    }
		    }
		}
		// Se ordenan todas las listas
		setBootstrapTreeNodeCountry =  setBootstrapTreeNodeCountry.stream().sorted(Comparator.comparing(BootstrapTreeNode::getText)).collect(Collectors.toSet());
		setBootstrapTreeNodeTSPName = setBootstrapTreeNodeTSPName.stream().sorted(Comparator.comparing(BootstrapTreeNode::getText)).collect(Collectors.toSet());
		setBootstrapTreeNodeTSPService = setBootstrapTreeNodeTSPService.stream().sorted(Comparator.comparing(BootstrapTreeNode::getText)).collect(Collectors.toSet());
	}

	/**
	 * Method that add TSPName and TPService to list results.
	 * 
	 * @param setBootstrapTreeNodeTSPName parameter that contain list set of TSPName.
	 * @param setBootstrapTreeNodeTSPService parameter that contain list set of TSPService.
	 * @param tslMappingDTO parameter that contain tsl mapping dto.
	 */
	private void addTSPNameAndTSPService(Set<BootstrapTreeNode> setBootstrapTreeNodeTSPName,
			Set<BootstrapTreeNode> setBootstrapTreeNodeTSPService, TslMappingDTO tslMappingDTO) {
		// Añadimos el TSPName a la lista resultado
		BootstrapTreeNode bootstrapTreeNodeTSPName = new BootstrapTreeNode();
		bootstrapTreeNodeTSPName.setText(tslMappingDTO.getTspName());
		bootstrapTreeNodeTSPName.setIcon(BootstrapTreeNode.ICON_HOME);
		bootstrapTreeNodeTSPName.setNodeId(tslMappingDTO.getTspName());
		bootstrapTreeNodeTSPName.setParentId(tslMappingDTO.getCodeCountry());
		bootstrapTreeNodeTSPName.setSelectable(false);
		setBootstrapTreeNodeTSPName.add(bootstrapTreeNodeTSPName);
		// Añadimos el TSPService a la lista resultado		    	
		BootstrapTreeNode bootstrapTreeNodeTSPService = new BootstrapTreeNode();
		bootstrapTreeNodeTSPService.setText(tslMappingDTO.getTspServiceName());
		bootstrapTreeNodeTSPService.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
		bootstrapTreeNodeTSPService.setNodeId(tslMappingDTO.getTspServiceName());
		bootstrapTreeNodeTSPService.setParentId(tslMappingDTO.getTspName());
		bootstrapTreeNodeTSPService.setSelectable(true);
		setBootstrapTreeNodeTSPService.add(bootstrapTreeNodeTSPService);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#updateCertificateOrSaveTslService
	 */
	public TslService updateCertificateOrSaveTslService(Map<String, List<TslMappingDTO>> mapTslMappingDTO,
			String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree,
			byte[] fileCertificateTsl)
			throws ParseException {
		
		// Buscamos el tsp service name seleccionado por el usuario en el arbol 
		TslService tslServiceFound = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);
		
		// Si no se ha encontrado creamos un tsp service name nuevo.
		if(null == tslServiceFound) {
			TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree,
					tspNameSelectTree, countrySelectTree, fileCertificateTsl);
			
			return tslServiceRepository.save(tlsServiceNew);
		// Si existe se modifica su certificado por el nuevo introducido
		} else {
			tslServiceFound.setCertificate(fileCertificateTsl);
			return tslServiceRepository.save(tslServiceFound);
		}
	}

	/**
	 * Method that create a new tsp service.
	 * 
	 * @param mapTslMappingDTO parameter that contain tree of the mappings certificate tsl.
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param tspNameSelectTree parameter that contain of tsp name select for the user.
	 * @param countrySelectTree parameter that contain of country select for the user.
	 * @param fileCertificateTsl parameter that contain certificate select for the user.
	 * @return a new tsp service.
	 * @throws ParseException If the method fails.
	 */
	private TslService createTspServiceNew(Map<String, List<TslMappingDTO>> mapTslMappingDTO,
			String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree,
			byte[] fileCertificateTsl) throws ParseException {
		List<TslMappingDTO> listTslMappingDTO = mapTslMappingDTO.get(countrySelectTree.toString());
		TslMappingDTO tlsMappingDTOFound = listTslMappingDTO.stream().filter(tslMappingDTO -> tslMappingDTO.getTspName().equals(tspNameSelectTree.toString()) && tslMappingDTO.getTspServiceName().equals(tspServiceNameSelectTree.toString())).findAny().orElse(null);
		TslService tlsServiceNew = new TslService();
		tlsServiceNew.setCertificate(fileCertificateTsl);
		tlsServiceNew.setCountry(tlsMappingDTOFound.getCodeCountry());
		tlsServiceNew.setDigitalIdentityCad(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(tlsMappingDTOFound.getExpirationDate()));
		tlsServiceNew.setDigitalIdentityId(tlsMappingDTOFound.getDigitalIdentity());
		tlsServiceNew.setTslVersion(Long.parseLong(tlsMappingDTOFound.getVersion()));
		tlsServiceNew.setTspName(tlsMappingDTOFound.getTspName());
		tlsServiceNew.setTspServiceName(tlsMappingDTOFound.getTspServiceName());
		return tlsServiceNew;
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#obtainTspServiceNameSelectTree
	 */
	public TslServiceDTO obtainTspServiceNameSelectTree(String tspServiceNameSelectTree) throws CommonUtilsException {
		TslService tslService = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);
		TslServiceDTO tslServiceDTO = new TslServiceDTO(tslService);
		return tslServiceDTO;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#saveMappingLogicField
	 */
	public void addMappingLogicField(Map<String, List<TslMappingDTO>> mapTslMappingDTO, MappingTslDTO mappingTslDTO, String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree) throws ParseException {
		TslMapping tslMapping = null;
		// Si el tsl service no existe lo creamos por primera vez
		if(null == mappingTslDTO.getTslServiceDTO().getIdTslService()) {
			tslMapping =  new TslMapping();
			tslMapping.setTslService(tslServiceRepository.save(this.createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, null)));
			tslMapping.setcAssociationType(new CAssociationType(mappingTslDTO.getcAssociationTypeDTO()));
			tslMapping.setLogicalFieldId(mappingTslDTO.getLogicalFieldId());
			tslMapping.setLogicalFieldValue(mappingTslDTO.getLogicalFieldValue());
		} else {
			tslMapping =  new TslMapping(mappingTslDTO);
		}
		// Se almacena el mapping
		tslMappingRepository.save(tslMapping);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#obtainMappingLogicalField
	 */
	public MappingTslDTO obtainMappingLogicalField(Long idTslMapping) throws CommonUtilsException {
		TslMapping tslMapping = tslMappingRepository.findByIdTslMapping(idTslMapping);
		MappingTslDTO mappingTslDTO = new MappingTslDTO(tslMapping);
		return mappingTslDTO;
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#mergeMappingLogicField
	 */
	public void mergeMappingLogicField(MappingTslDTO mappingTslDTO) {
		TslMapping tslMapping = new TslMapping(mappingTslDTO);
		tslMappingRepository.save(tslMapping);
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#existsTspServiceNameAndIdentificator
	 */
	public boolean existsTspServiceNameAndIdentificator(String tspServiceName, String logicalFieldId) {
		TslService tslService = tslServiceRepository.findByTspServiceNameAndLogicFieldId(tspServiceName, logicalFieldId);
		if(null != tslService) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#deleteMappingLogicalField
	 */
	public void deleteMappingLogicalField(Long idTslMappingDelete) {
		tslMappingRepository.deleteById(idTslMappingDelete);
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#obtainJsonWithMappingsToTslService 
	 */
	public String obtainJsonWithMappingsToTslService(String tspServiceNameSelectTree, boolean exportCertificate) throws JsonProcessingException, CertificateEncodingException, CommonUtilsException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		TslService tslService = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);
		
		List<TslMapping> listTslMappings = tslService.getTslMapping();
		List<TslMappingExportDTO> listTslMappingExportDTO = new ArrayList<TslMappingExportDTO>();
		
		for (TslMapping tslMapping : listTslMappings) {
			TslMappingExportDTO tslMappingExportDTO = new TslMappingExportDTO(tslMapping);
			listTslMappingExportDTO.add(tslMappingExportDTO);
		}
		
		// Si el usuario desea exportar el certificado de ejemplo se incluyen los bytes en base 64
		Map<String, Object> mTslMapping = new HashMap<String, Object>();
		if(exportCertificate) {
			mTslMapping.put(MAP_KEY_CERTIFICATE, new String(new Base64(NumberConstants.NUM64).encode(tslService.getCertificate())));
			mTslMapping.put(MAP_KEY_LIST_MAPPINGS, listTslMappingExportDTO);
			mTslMapping.put(MAP_KEY_EXPORT_CERTIFICATE, exportCertificate);
			return objectMapper.writeValueAsString(mTslMapping);
		} else {
			mTslMapping.put(MAP_KEY_LIST_MAPPINGS, listTslMappingExportDTO);
			mTslMapping.put(MAP_KEY_EXPORT_CERTIFICATE, exportCertificate);
			return objectMapper.writeValueAsString(mTslMapping);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#importMappingLogicFieldFromJson 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void importMappingLogicFieldFromJson(String originalFilename, byte[] importMappingLogicalfieldFile, String tspServiceNameSelectTree,
			String tspNameSelectTree, String countrySelectTree, Map<String, List<TslMappingDTO>> mapTslMappingDTO) throws ImportException, JsonMappingException, IOException, ParseException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		
		this.percentage = NumberConstants.NUM20;
		// Comprobamos que se ha indicado el archivo JSON con los mapeos a importar.
		ImportUtils.checkIsFileNotNull(importMappingLogicalfieldFile, Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_FILE_EMPTY));
		// Evaluamos que el fichero no supere el tamaño máximo
		Integer maxFileSize = Integer.parseInt(env.getProperty("max.fileSize")); 
		ImportUtils.checkIsFileMaxSize(originalFilename, importMappingLogicalfieldFile, maxFileSize);
		// Evaluamos que el fichero contenga un formato json correcto.
		ImportUtils.checkIsJsonExtension(originalFilename);
		
		this.percentage = NumberConstants.NUM60;
		Map<String, Object> mTslMapping = new HashMap<String, Object>();
		List<TslMapping> listTslMappingNew = new ArrayList<TslMapping>();
		byte[] certificate = null;
		List<LinkedHashMap> listLinkedHashMap;
		try {
			mTslMapping = objectMapper.readValue(importMappingLogicalfieldFile, HashMap.class);
		
			TslService tslService = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);
			this.percentage = NumberConstants.NUM80;
			
			if(null == tslService && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, certificate);
				tslService = tslServiceRepository.save(tlsServiceNew);
			} else if (null == tslService && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = new Base64(NumberConstants.NUM64).decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, certificate);
				tslService = tslServiceRepository.save(tlsServiceNew);
			} else if (null != tslService &&  null == tslService.getCertificate() && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				tslService.setTslMapping(null);
			} else if (null != tslService &&  null != tslService.getCertificate() && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = new Base64(NumberConstants.NUM64).decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				tslService.setCertificate(certificate);
				tslService = tslServiceRepository.save(tslService);
				tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				tslService.setTslMapping(null);
			} else if (null != tslService &&  null == tslService.getCertificate() && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = new Base64(NumberConstants.NUM64).decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				tslService.setCertificate(certificate);
				tslService = tslServiceRepository.save(tslService);
				tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				tslService.setTslMapping(null);
			} else if (null != tslService &&  null != tslService.getCertificate() && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				tslService.setTslMapping(null);
			}
			
			// Se sobreescriben los mappings de campos lógicos
			listLinkedHashMap = (List<LinkedHashMap>) mTslMapping.get(MAP_KEY_LIST_MAPPINGS);
			for (LinkedHashMap linkedHashMap : listLinkedHashMap) {
				TslMappingExportDTO tslMappingExportDTO = objectMapper.convertValue(linkedHashMap, TslMappingExportDTO.class); 
				TslMapping tslMapping = new TslMapping(tslService, tslMappingExportDTO);
				listTslMappingNew.add(tslMapping);
			}
		} catch(JsonParseException e) { // No es un archivo con formato json
			String msgError = Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_FORMAT_INCORRECT);
			LOGGER.error(msgError);
			throw new ImportException(msgError);
		} catch(UnrecognizedPropertyException e) { // No es un json con propiedades de la instancia TslMappingImportDTO  
			String msgError = Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_TSL_MAPPING_NOT_INSTANCE);
			LOGGER.error(msgError);
			throw new ImportException(msgError);
		}
		tslMappingRepository.saveAll(listTslMappingNew);
		this.percentage = NumberConstants.NUM100;
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#getPercentage 
	 */
	public int getPercentage() {
		return percentage;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#setPercentage 
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
}
