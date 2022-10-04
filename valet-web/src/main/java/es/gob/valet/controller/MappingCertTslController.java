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
 * @version 1.3, 04/10/2022.
 */
package es.gob.valet.controller;

import static es.gob.valet.rest.controller.MappingCertTslRestController.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.dto.TSLServiceDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.tsl.access.TslInformationTree;

/**
 * <p>Class that manages the requests related to the mappings of certificates TSL administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 04/10/2022.
 */
@Controller
@RequestMapping(value = "/mappingCertTsl")
public class MappingCertTslController {
	
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
	 *  
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param model Holder object form model attributes.
	 * @param response parameter that represents posibility errors in process.
	 * @return view modal of view certificate tsl.
	 */
	@PostMapping(value = "/viewCertificateTsl")
	public String viewCertificateTsl(@RequestParam(TSPSERVICENAMESELECTTREE) String tspServiceNameSelectTree, Model model, HttpServletResponse response) {
		String res;
		TSLServiceDTO tslServiceDTO = null;
		try {
			tslServiceDTO = iMappingCertTslService.obtainTspServiceNameSelectTree(tspServiceNameSelectTree);
			model.addAttribute(TSLSERVICEDTO, tslServiceDTO);
			res = "modal/mappingcerttsl/viewCertificateTslForm.html";
		} catch (CommonUtilsException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		}
		
		return res;
	}
}
