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
 * <b>File:</b><p>es.gob.valet.controller.ExporTslsController.java.</p>
 * <b>Description:</b><p>Class that manages the request related to the Export Tsls administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/09/2025.
 */
package es.gob.valet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.gob.valet.persistence.configuration.model.dto.ExporTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.ValetVersionDTO;

/**
 * <p>Class that manages the request related to the Export Tsls administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/09/2025.
 */
@Controller
public class ExporTslsController {
	
	/**
	 * Handles the request to display the export TSLS admin page.
	 * Initializes the model with a new ExporTslsDTO and a list of ValetVersionDTO objects.
	 *
	 * @param model the model to be populated with attributes for the view
	 * @return the name of the view to be rendered ("fragments/exportslsadmin.html")
	 */
	@RequestMapping(value = "exportslsadmin", method = RequestMethod.GET)
	public String exportslsadmin(final Model model) {
		model.addAttribute("exporTslsDTO", new ExporTslsDTO());
		model.addAttribute("listValetVersionDTO", ValetVersionDTO.getVersionList());
		return "fragments/exportslsadmin.html";
	}
	
}
