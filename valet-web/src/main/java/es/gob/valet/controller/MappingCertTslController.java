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
 * <b>File:</b><p>es.gob.valet.controller.MappingCertTslController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the mappings of certificates TSL administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.6, 17/10/2022.
 */
package es.gob.valet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.dto.MappingTslDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.persistence.utils.ConstantsUtils;
import es.gob.valet.tsl.access.TslInformationTree;

import static es.gob.valet.persistence.configuration.model.dto.MappingTslDTO.ADD;
import static es.gob.valet.persistence.configuration.model.dto.MappingTslDTO.MERGE;
import static es.gob.valet.rest.controller.MappingCertTslRestController.REQ_PARAM_ID_TSL_MAPPING;

/**
 * <p>Class that manages the requests related to the mappings of certificates TSL administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 17/10/2022.
 */
@Controller
@RequestMapping(value = "/mappingCertTsl")
public class MappingCertTslController {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);
	
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
	 * Attribute that represents the service object for accessing the repository of mapping certificate tsls.
	 */
	@Autowired
	ICAssociationTypeService iCAssociationTypeService;
	
	/**
	 * Method that return view admin of mapping certificate tsls.
	 * 
	 * @param model Holder object form model attributes.
	 * @return view admin of mapping certificate tsls.
	 */
	@GetMapping(value = "/viewMappingCertTsl")
	public String viewMappingCertTsl(Model model) {
		@SuppressWarnings("static-access")
		Map<String, List<TslMappingDTO>> mapTsl = tslInformationTree.getMapTslMappingTree();
		List<BootstrapTreeNode> listBootstrapTreeNode = iMappingCertTslService.createTreeMappingCertTsl(mapTsl, null);
		model.addAttribute("listBootstrapTreeNode", listBootstrapTreeNode);
		return "fragments/mappingcerttsltadmin.html";
	}
	
	/**
	 * Method that return view modal of update certificate tsls. 
	 * 
	 * @param model Holder object form model attributes.
	 * @return view modal of update certificate tsls. 
	 */
	@PostMapping(value = "/viewUpdateCertificateTsl")
	public String viewUpdateCertificateTsl(Model model) {
		return "modal/mappingcerttsl/updateCertificateTslForm.html";
	}
	
	/**
	 * Method that return view modal of view certificate tsl.
	 * @return view modal of view certificate tsl.
	 */
	@PostMapping(value = "/viewCertificateTsl")
	public String viewCertificateTsl() {
		return "modal/mappingcerttsl/viewCertificateTslForm.html";
	}
	
	/**
	 * Method that return view modal of view add mapping logical field.
	 * 
	 * @return view modal of view add mapping logical field.
	 */
	@PostMapping(value = "/viewAddLogicField")
	public String viewAddLogicField(Model model) {
		MappingTslDTO mappingTslDTO = new MappingTslDTO();
		mappingTslDTO.setListCAssociationTypeDTO(iCAssociationTypeService.getAllAssociationTypeDTO());
		mappingTslDTO.setListValuesAssocSimple(ConstantsUtils.loadSimpleAssociationValues());
		mappingTslDTO.setAction(ADD);
		model.addAttribute("mappingTslDTO", mappingTslDTO);
		return "modal/mappingcerttsl/addMappingLogicalFieldForm.html";
	}
	
	/**
	 * Method that redirect to view of edit mapping.
	 * 
	 * @param idTslMapping parameter that contain id to tsl mapping.
	 * @param model Holder object form model attributes.
	 * @param response parameter that represents posibility errors in process.
	 * @return string with view of edit mapping.
	 */
	@PostMapping(value = "/viewEditMapping")
	public String viewEditMapping(@RequestParam(REQ_PARAM_ID_TSL_MAPPING) Long idTslMapping, Model model, HttpServletResponse response) {
		String res = "modal/mappingcerttsl/addMappingLogicalFieldForm.html";
		try {
			MappingTslDTO mappingTslDTO = iMappingCertTslService.obtainMappingLogicalField(idTslMapping);
			mappingTslDTO.setListCAssociationTypeDTO(iCAssociationTypeService.getAllAssociationTypeDTO());
			mappingTslDTO.setListValuesAssocSimple(ConstantsUtils.loadSimpleAssociationValues());
			mappingTslDTO.setAction(MERGE);
			model.addAttribute("mappingTslDTO", mappingTslDTO);
		} catch (CommonUtilsException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		}
		return res;
	}
	
	/**
	 * Method that redirect to view of delete mapping.
	 * @return string with view of delete mapping.
	 */
	@PostMapping(value = "/viewDeleteMapping")
	public String viewDeleteMapping() {
		return "modal/mappingcerttsl/deleteMappingLogicalFieldForm.html";
	}
	
	/**
	 * Method that redirect to view of monitoring process import.
	 * @return string with view of monitoring process import.
	 */
	@PostMapping(value = "/viewMonitorImportTsl")
	public String viewMonitorImportTsl() {
		return "modal/mappingcerttsl/monitorImportMappingLogicalFieldForm.html";
	}
}
