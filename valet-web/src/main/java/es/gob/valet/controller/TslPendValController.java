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
 * <b>File:</b><p>es.gob.valet.controller.TslPendValController.java.</p>
 * <b>Description:</b><p> Class that manages the requests related to the TSL Pending Validation administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/06/2025.
 */
package es.gob.valet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.service.ifaces.ITslPendValService;

/**
 * <p> Class that manages the requests related to the TSL Pending Validation administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/06/2025.
 */
@Controller
public class TslPendValController {
	
	/**
	 * Service interface for managing pending TSL validations.
	 */
	@Autowired
	private ITslPendValService iTslPendValService;

	/**
	 * Displays the TSL pending validation administration page.
	 *
	 * @param model the model to populate view attributes
	 * @return the path to the TSL pending validation admin fragment
	 */
	@RequestMapping(value = "tslpendvaladmin", method = RequestMethod.GET)
	public String tslPendValAdmin(Model model) {
		return "fragments/tslpendvaladmin.html";
	}

	/**
	 * Loads and displays the information of a TSL pending validation.
	 *
	 * @param idTslPendVal the ID of the TSL to display
	 * @param model the model to populate view attributes
	 * @return the path to the modal fragment with TSL pending validation info
	 */
	@RequestMapping(value = "viewinfotsl", method = RequestMethod.POST)
	public String viewInfoTsl(@RequestParam("idTslPendVal") Long idTslPendVal,  Model model) {
		List<TslPendValDTO> listTslPendValDTO = iTslPendValService.obtainAllTslPendVal();
		TslPendValDTO tslPendValDTO = listTslPendValDTO.stream().filter(p -> p.getIdTslPendVal() == idTslPendVal).findAny().orElse(null);
		model.addAttribute("tslPendValDTO", tslPendValDTO);
		return "modal/tslPendVal/infoTslPendVal";
	}

	/**
	 * Opens the confirmation modal for a pending TSL validation.
	 *
	 * @param model the model to populate view attributes
	 * @return the path to the confirmation modal fragment
	 */
	@RequestMapping(value = "confirmtslpendval", method = RequestMethod.GET)
	public String confirmTslPendVal(Model model) {
		return "modal/tslPendVal/confirmtslpendval.html";
	}

	/**
	 * Opens the declination modal for a pending TSL validation.
	 *
	 * @param model the model to populate view attributes
	 * @return the path to the declination modal fragment
	 */
	@RequestMapping(value = "declinetslpendval", method = RequestMethod.GET)
	public String declineTslPendVal(Model model) {
		return "modal/tslPendVal/declinetslpendval.html";
	}

}
