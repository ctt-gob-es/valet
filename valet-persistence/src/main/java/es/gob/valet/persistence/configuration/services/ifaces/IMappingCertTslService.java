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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Alarm entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 27/09/2022.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.dto.MappingCertTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.utils.BootstrapTreeNode;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the mapping certficate tsl entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 27/09/2022.
 */
public interface IMappingCertTslService {

	/**
	 * Method that create tree of mapping certificate tsls.
	 * @param mapTsl parameter that contain map with all information about tsls.
	 * @param valueSearch parameter that contain value search enter for the user.
	 * 
	 * @return tree of mapping certificate tsls.
	 */
	List<BootstrapTreeNode> createTreeMappingCertTsl(Map<String, List<TslMappingDTO>> mapTsl, String valueSearch);

	/**
	 * Method that create datatable of mapping certificate tsls. Only contain paramenters referents to data of datatable.
	 * 
	 * @return tree of mapping certificate tsls.
	 */
	DataTablesOutput<MappingCertTslsDTO> createDatatableMappingCertTsls(Long idMappingCertTsl);

}
