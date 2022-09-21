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
 * @version 1.0, 21/09/2022.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.dto.MappingCertTslsDTO;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Mapping Certificate TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/09/2022.
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
	public List<BootstrapTreeNode> createTreeMappingCertTsl() {
		
		BootstrapTreeNode bootstrapTreeNode1_1 = new BootstrapTreeNode();
		bootstrapTreeNode1_1.setText("IT");
		bootstrapTreeNode1_1.setSelectable(false);
		bootstrapTreeNode1_1.setNodeId(0L);
		bootstrapTreeNode1_1.setIcon(BootstrapTreeNode.ICON_POLICY);
		
		
		BootstrapTreeNode bootstrapTreeNode1_2 = new BootstrapTreeNode();
		bootstrapTreeNode1_2.setText("TSPName (en)");
		bootstrapTreeNode1_2.setSelectable(false);
		bootstrapTreeNode1_2.setParentId(0L);
		bootstrapTreeNode1_2.setNodeId(1L);
		bootstrapTreeNode1_2.setIcon(BootstrapTreeNode.ICON_HOME);
		
		BootstrapTreeNode bootstrapTreeNode1_2_1 = new BootstrapTreeNode();
		bootstrapTreeNode1_2_1.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode1_2_1.setSelectable(true);
		bootstrapTreeNode1_2_1.setParentId(1L);
		bootstrapTreeNode1_2_1.setNodeId(11L);
		bootstrapTreeNode1_2_1.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
				
		BootstrapTreeNode bootstrapTreeNode1_2_2 = new BootstrapTreeNode();
		bootstrapTreeNode1_2_2.setText("TSPServiceName");
		bootstrapTreeNode1_2_2.setSelectable(true);
		bootstrapTreeNode1_2_2.setParentId(1L);
		bootstrapTreeNode1_2_2.setNodeId(22L);
		bootstrapTreeNode1_2_2.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
		
		BootstrapTreeNode bootstrapTreeNode2_1 = new BootstrapTreeNode();
		bootstrapTreeNode2_1.setText("TSPName (en) 2");
		bootstrapTreeNode2_1.setSelectable(false);
		bootstrapTreeNode2_1.setParentId(0L);
		bootstrapTreeNode2_1.setNodeId(33L);
		bootstrapTreeNode2_1.setIcon(BootstrapTreeNode.ICON_HOME);
		
		bootstrapTreeNode1_2.setNodes(new ArrayList<>());
		bootstrapTreeNode1_2.getNodes().add(bootstrapTreeNode1_2_1);
		bootstrapTreeNode1_2.getNodes().add(bootstrapTreeNode1_2_2);
		
		bootstrapTreeNode1_1.setNodes(new ArrayList<>());
		bootstrapTreeNode1_1.getNodes().add(bootstrapTreeNode1_2);
		bootstrapTreeNode1_1.getNodes().add(bootstrapTreeNode2_1);
		
		BootstrapTreeNode bootstrapTreeNode3_1 = new BootstrapTreeNode();
		bootstrapTreeNode3_1.setText("BE");
		bootstrapTreeNode3_1.setSelectable(false);
		bootstrapTreeNode3_1.setNodeId(1L);
		bootstrapTreeNode3_1.setIcon(BootstrapTreeNode.ICON_POLICY);
		
		BootstrapTreeNode bootstrapTreeNode3_2 = new BootstrapTreeNode();
		bootstrapTreeNode3_2.setText("TSPName (en)");
		bootstrapTreeNode3_2.setSelectable(false);
		bootstrapTreeNode3_2.setParentId(1L);
		bootstrapTreeNode3_2.setNodeId(1L);
		bootstrapTreeNode3_2.setIcon(BootstrapTreeNode.ICON_HOME);
		
		BootstrapTreeNode bootstrapTreeNode3_2_1 = new BootstrapTreeNode();
		bootstrapTreeNode3_2_1.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode3_2_1.setSelectable(true);
		bootstrapTreeNode3_2_1.setParentId(1L);
		bootstrapTreeNode3_2_1.setNodeId(11L);
		bootstrapTreeNode3_2_1.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
		
		BootstrapTreeNode bootstrapTreeNode3_2_2 = new BootstrapTreeNode();
		bootstrapTreeNode3_2_2.setText("TSPServiceName (en) - CAD: DD/MM/YYYY");
		bootstrapTreeNode3_2_2.setSelectable(true);
		bootstrapTreeNode3_2_2.setParentId(1L);
		bootstrapTreeNode3_2_2.setNodeId(22L);
		bootstrapTreeNode3_2_2.setIcon(BootstrapTreeNode.ICON_CERTIFICATE);
		
		bootstrapTreeNode3_2.setNodes(new ArrayList<>());
		bootstrapTreeNode3_2.getNodes().add(bootstrapTreeNode3_2_1);
		bootstrapTreeNode3_2.getNodes().add(bootstrapTreeNode3_2_2);
		
		bootstrapTreeNode3_1.setNodes(new ArrayList<>());
		bootstrapTreeNode3_1.getNodes().add(bootstrapTreeNode3_2);
		
		List<BootstrapTreeNode> l = new ArrayList<BootstrapTreeNode>();
		
		l.add(bootstrapTreeNode1_1);
		l.add(bootstrapTreeNode3_1);
		
		return l;
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
