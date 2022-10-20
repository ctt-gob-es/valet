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
 * @version 1.0, 21/09/2022.
 */
package es.gob.valet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;

/**
 * <p>Class that manages the requests related to the mappings of certificates TSL administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/09/2022.
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
	 * Method that return view admin of mapping certificate tsls.
	 * 
	 * @param model Holder object form model attributes.
	 * @return view admin of mapping certificate tsls.
	 */
	@GetMapping(value = "/viewMappingCertTsl")
	public String viewMappingCertTsl(Model model) {
		List<BootstrapTreeNode> listBootstrapTreeNode = iMappingCertTslService.createTreeMappingCertTsl();
		model.addAttribute("listBootstrapTreeNode", listBootstrapTreeNode);
		return "fragments/mappingcerttsltadmin.html";
	}
}
