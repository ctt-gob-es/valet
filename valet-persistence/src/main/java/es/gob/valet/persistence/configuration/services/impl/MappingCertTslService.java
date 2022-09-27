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
 * @version 1.1, 27/09/2022.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.persistence.configuration.model.dto.MappingCertTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Mapping Certificate TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 27/09/2022.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MappingCertTslService implements IMappingCertTslService {

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
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService#createDatatableMappingCertTsls(java.lang.Long)
	 */
	@Override
	public DataTablesOutput<MappingCertTslsDTO> createDatatableMappingCertTsls(Long idMappingCertTsl) {
		DataTablesOutput<MappingCertTslsDTO> dataTablesOutput = new DataTablesOutput<MappingCertTslsDTO>();
		
		List<MappingCertTslsDTO> listMappingCertTslsDTO = new ArrayList<MappingCertTslsDTO>();
		
		MappingCertTslsDTO mappingCertTslsDTO1 = new MappingCertTslsDTO();
		mappingCertTslsDTO1.setName("Nombre");
		mappingCertTslsDTO1.setIdMappingCertTsl(1L);
		
		MappingCertTslsDTO mappingCertTslsDTO2 = new MappingCertTslsDTO();
		mappingCertTslsDTO2.setName("Apellidos");
		mappingCertTslsDTO2.setIdMappingCertTsl(2L);
		
		listMappingCertTslsDTO.add(mappingCertTslsDTO1);
		listMappingCertTslsDTO.add(mappingCertTslsDTO2);
		
		dataTablesOutput.setData(listMappingCertTslsDTO);
		
		return dataTablesOutput;
	}

}
