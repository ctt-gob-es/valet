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
 * @version 1.10, 20/10/2022.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceGeneralMessages;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
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
 * @version 1.10, 20/10/2022.
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BootstrapTreeNode> createTreeMappingCertTsl(Map<String, List<TslMappingDTO>> mapTsl, String valueSearch) {
		List<BootstrapTreeNode> listBootstrapTreeNode = new ArrayList<>();
		for (Map.Entry entry: mapTsl.entrySet()) {
			// Obtenemos la lista de TslMappingDTO
			List<TslMappingDTO> listTslMappingDTO = (List<TslMappingDTO>) entry.getValue();

			// Añadimos el pais a la lista resultados
			BootstrapTreeNode bootstrapTreeNodeCountry = new BootstrapTreeNode();
			bootstrapTreeNodeCountry.setText(entry.getKey().toString());
			bootstrapTreeNodeCountry.setIcon(BootstrapTreeNode.ICON_PAWN);
			bootstrapTreeNodeCountry.setNodeId(entry.getKey().toString());
			bootstrapTreeNodeCountry.setSelectable(false);
			// Se añade el nuevo array list
			bootstrapTreeNodeCountry.setNodes(new ArrayList<>());

			// Recorremos la lista con los mappings tsls obtenida previamente
			for (TslMappingDTO tslMappingDTO: listTslMappingDTO) {
				if (null != valueSearch) {
					if (tslMappingDTO.getTspName().toLowerCase().contains(valueSearch.toLowerCase()) || tslMappingDTO.getTspServiceName().toLowerCase().contains(valueSearch.toLowerCase()) || tslMappingDTO.getExpirationDate().substring(NumberConstants.NUM0, NumberConstants.NUM10).contains(valueSearch.toLowerCase())) {
						this.joinNode(bootstrapTreeNodeCountry, tslMappingDTO);
					}
				} else {
					this.joinNode(bootstrapTreeNodeCountry, tslMappingDTO);
				}
			}
			listBootstrapTreeNode.add(bootstrapTreeNodeCountry);
		}
		return listBootstrapTreeNode;
	}

	/**
	 * Method that join all nodes in tree of type BootstrapTreeNode to visualize in interface.
	 * 
	 * @param bootstrapTreeNodeCountry parameter that contain node of country.
	 * @param tslMappingDTO parameter that contain the tree in other structure.
	 */
	private void joinNode(BootstrapTreeNode bootstrapTreeNodeCountry, TslMappingDTO tslMappingDTO) {
		// Evaluamos que ya exista el tsp name en arbol.
		BootstrapTreeNode bTspNameFound = bootstrapTreeNodeCountry.getNodes().stream().filter(bTNC -> bTNC.getNodeId().equals(tslMappingDTO.getTspName())).findAny().orElse(null);

		if (null == bTspNameFound) {
			// Añadimos el TSPName a la lista resultado
			BootstrapTreeNode bootstrapTreeNodeTSPName = new BootstrapTreeNode();
			bootstrapTreeNodeTSPName.setText(tslMappingDTO.getTspName());
			bootstrapTreeNodeTSPName.setIcon(BootstrapTreeNode.ICON_HOME);
			bootstrapTreeNodeTSPName.setNodeId(tslMappingDTO.getTspName());
			bootstrapTreeNodeTSPName.setParentId(tslMappingDTO.getCodeCountry());
			bootstrapTreeNodeTSPName.setSelectable(false);

			// Añadimos el TSPService a la lista resultado
			BootstrapTreeNode bootstrapTreeNodeTSPService = new BootstrapTreeNode();
			String TspServiceNameWithDateExpiration = tslMappingDTO.getTspServiceName() + " - " + Language.getResWebGeneral(IWebGeneralMessages.END) + ": " + tslMappingDTO.getExpirationDate().substring(NumberConstants.NUM0, NumberConstants.NUM10);
			bootstrapTreeNodeTSPService.setText(TspServiceNameWithDateExpiration);
			bootstrapTreeNodeTSPService.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
			bootstrapTreeNodeTSPService.setNodeId(tslMappingDTO.getTspServiceName());
			bootstrapTreeNodeTSPService.setParentId(tslMappingDTO.getTspName());
			bootstrapTreeNodeTSPService.setSelectable(true);
			bootstrapTreeNodeTSPName.setNodes(new ArrayList<>());
			bootstrapTreeNodeTSPName.getNodes().add(bootstrapTreeNodeTSPService);

			bootstrapTreeNodeCountry.getNodes().add(bootstrapTreeNodeTSPName);
		} else {
			// Añadimos el TSPService a la lista resultado
			BootstrapTreeNode bootstrapTreeNodeTSPService = new BootstrapTreeNode();
			String TspServiceNameWithDateExpiration = tslMappingDTO.getTspServiceName() + " - " + Language.getResWebGeneral(IWebGeneralMessages.END) + ": " + tslMappingDTO.getExpirationDate().substring(NumberConstants.NUM0, NumberConstants.NUM10);
			bootstrapTreeNodeTSPService.setText(TspServiceNameWithDateExpiration);
			bootstrapTreeNodeTSPService.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
			bootstrapTreeNodeTSPService.setNodeId(tslMappingDTO.getTspServiceName());
			bootstrapTreeNodeTSPService.setParentId(tslMappingDTO.getTspName());
			bootstrapTreeNodeTSPService.setSelectable(true);
			bTspNameFound.getNodes().add(bootstrapTreeNodeTSPService);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#updateCertificateOrSaveTslService
	 */
	public TslService updateCertificateOrSaveTslService(Map<String, List<TslMappingDTO>> mapTslMappingDTO, String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree, byte[ ] fileCertificateTsl) throws ParseException {

		// Buscamos el tsp service name seleccionado por el usuario en el arbol
		TslService tslServiceFound = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);

		// Si no se ha encontrado creamos un tsp service name nuevo.
		if (null == tslServiceFound) {
			TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, fileCertificateTsl);

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
	private TslService createTspServiceNew(Map<String, List<TslMappingDTO>> mapTslMappingDTO, String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree, byte[ ] fileCertificateTsl) throws ParseException {
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
		if (null == mappingTslDTO.getTslServiceDTO().getIdTslService()) {
			tslMapping = new TslMapping();
			tslMapping.setTslService(tslServiceRepository.save(this.createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, null)));
			tslMapping.setcAssociationType(new CAssociationType(mappingTslDTO.getcAssociationTypeDTO()));
			tslMapping.setLogicalFieldId(mappingTslDTO.getLogicalFieldId());
			tslMapping.setLogicalFieldValue(mappingTslDTO.getLogicalFieldValue());
		} else {
			tslMapping = new TslMapping(mappingTslDTO);
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
		if (null != tslService) {
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

		for (TslMapping tslMapping: listTslMappings) {
			TslMappingExportDTO tslMappingExportDTO = new TslMappingExportDTO(tslMapping);
			listTslMappingExportDTO.add(tslMappingExportDTO);
		}

		// Si el usuario desea exportar el certificado de ejemplo se incluyen
		// los bytes en base 64
		Map<String, Object> mTslMapping = new HashMap<String, Object>();
		if (exportCertificate) {
			mTslMapping.put(MAP_KEY_CERTIFICATE, new String(Base64.getEncoder().encode(tslService.getCertificate())));
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
	public void importMappingLogicFieldFromJson(String originalFilename, byte[ ] importMappingLogicalfieldFile, String tspServiceNameSelectTree, String tspNameSelectTree, String countrySelectTree, Map<String, List<TslMappingDTO>> mapTslMappingDTO) throws ImportException, JsonMappingException, IOException, ParseException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		this.percentage = NumberConstants.NUM20;
		// Comprobamos que se ha indicado el archivo JSON con los mapeos a
		// importar.
		ImportUtils.checkIsFileNotNull(importMappingLogicalfieldFile, Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_FILE_EMPTY));
		// Evaluamos que el fichero no supere el tamaño máximo
		Integer maxFileSize = Integer.parseInt(env.getProperty("max.fileSize"));
		ImportUtils.checkIsFileMaxSize(originalFilename, importMappingLogicalfieldFile, maxFileSize);
		// Evaluamos que el fichero contenga un formato json correcto.
		ImportUtils.checkIsJsonExtension(originalFilename);

		this.percentage = NumberConstants.NUM60;
		Map<String, Object> mTslMapping = new HashMap<String, Object>();
		List<TslMapping> listTslMappingNew = new ArrayList<TslMapping>();
		byte[ ] certificate = null;
		List<LinkedHashMap> listLinkedHashMap;
		try {
			mTslMapping = objectMapper.readValue(importMappingLogicalfieldFile, HashMap.class);

			TslService tslService = tslServiceRepository.findByTspServiceName(tspServiceNameSelectTree);
			this.percentage = NumberConstants.NUM80;

			if (null == tslService && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, certificate);
				tslService = tslServiceRepository.save(tlsServiceNew);
			} else if (null == tslService && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = Base64.getDecoder().decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				TslService tlsServiceNew = createTspServiceNew(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, certificate);
				tslService = tslServiceRepository.save(tlsServiceNew);
			} else if (null != tslService && null == tslService.getCertificate() && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				if (tslService.getTslMapping() != null && !tslService.getTslMapping().isEmpty()) {
					tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				}
				tslService.setTslMapping(null);
			} else if (null != tslService && null != tslService.getCertificate() && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = Base64.getDecoder().decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				tslService.setCertificate(certificate);
				tslService = tslServiceRepository.save(tslService);
				if (tslService.getTslMapping() != null && !tslService.getTslMapping().isEmpty()) {
					tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				}
				tslService.setTslMapping(null);
			} else if (null != tslService && null == tslService.getCertificate() && Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				certificate = Base64.getDecoder().decode(mTslMapping.get(MAP_KEY_CERTIFICATE).toString());
				tslService.setCertificate(certificate);
				tslService = tslServiceRepository.save(tslService);
				if (tslService.getTslMapping() != null && !tslService.getTslMapping().isEmpty()) {
					tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				}
				tslService.setTslMapping(null);
			} else if (null != tslService && null != tslService.getCertificate() && !Boolean.parseBoolean(mTslMapping.get(MAP_KEY_EXPORT_CERTIFICATE).toString())) {
				if (tslService.getTslMapping() != null && !tslService.getTslMapping().isEmpty()) {
					tslMappingRepository.deleteAllById(tslService.getTslMapping().stream().map(TslMapping::getIdTslMapping).collect(Collectors.toList()));
				}
				tslService.setTslMapping(null);
			}

			// Se sobreescriben los mappings de campos lógicos
			listLinkedHashMap = (List<LinkedHashMap>) mTslMapping.get(MAP_KEY_LIST_MAPPINGS);
			for (LinkedHashMap linkedHashMap: listLinkedHashMap) {
				TslMappingExportDTO tslMappingExportDTO = objectMapper.convertValue(linkedHashMap, TslMappingExportDTO.class);
				TslMapping tslMapping = new TslMapping(tslService, tslMappingExportDTO);
				listTslMappingNew.add(tslMapping);
			}
		} catch (JsonParseException e) { // No es un archivo con formato json
			String msgError = Language.getResPersistenceGeneral(IPersistenceGeneralMessages.ERROR_IMPORTING_JSON_MAPPING_FORMAT_INCORRECT);
			LOGGER.error(msgError);
			throw new ImportException(msgError);
		} catch (UnrecognizedPropertyException e) { // No es un json con
													// propiedades de la
													// instancia
													// TslMappingImportDTO
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
