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
 * <b>File:</b><p>es.gob.valet.rest.controller.MappingCertTslRestController.java.</p>
 * <b>Description:</b><p> Class that manages the REST request related to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.2, 28/09/2022.
 */
package es.gob.valet.rest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.persistence.configuration.model.dto.MappingCertTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.tsl.access.TslInformationTree;

/**
 * <p>Class that manages the REST request related to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 28/09/2022.
 */
@RestController
@RequestMapping(value = "/mappingCertTslRest")
public class MappingCertTslRestController {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_AFIRMA_LOG);
	
	/**
	 * Attribute that represents the service object for accessing the repository of mapping certificate tsls.
	 */
	@Autowired
	IMappingCertTslService iMappingCertTslService;
	
	/**
	 * Attribute that represents bean spring for accessing the map of mapping certificate tsls.
	 */
	@Autowired
	TslInformationTree tslInformationTree;
	
	/**
	 * Method that obtain the list of data to datatable in part front. This information be represent in object Datatable.
	 * 
	 * @param idMappingCertTsl parameter that contain id of mapping certificate tsls.
	 * @return the list of data to datatable in part front. This information be represent in object Datatable.
	 */
	@PostMapping(value = "/loadingDatatableMappingCertTsls")
	public DataTablesOutput<MappingCertTslsDTO> loadingDatatableMappingCertTsls(@RequestParam("idMappingCertTsl") Long idMappingCertTsl) {
		
		DataTablesOutput<MappingCertTslsDTO> dataTablesOutput =  iMappingCertTslService.createDatatableMappingCertTsls(idMappingCertTsl);
		
		return dataTablesOutput;
	}
	
	/**
	 * Method that search in tree value enter for user in searching. 
	 * 
	 * @param valueSearch parameter that contain value enter for user in searching.
	 * @param response parameter that represents posibility errors in process.
	 * @return tree with nodes found.
	 */
	@SuppressWarnings("static-access")
	@PostMapping(value = "/searchInTree")
	public String searchInTree(@RequestParam("valueSearch") String valueSearch, HttpServletResponse response) {
		Map<String, List<TslMappingDTO>> mapTsl = tslInformationTree.getMapTslMappingTree();
		List<BootstrapTreeNode> listBootstrapTreeNode = iMappingCertTslService.createTreeMappingCertTsl(mapTsl, valueSearch);
		ObjectMapper mapper = new ObjectMapper();
		String res = null;
		try {
			res = mapper.writeValueAsString(listBootstrapTreeNode);
		} catch (JsonProcessingException e) {
			res = e.getMessage();
			LOGGER.error(res, e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return res;
		
	}
}
