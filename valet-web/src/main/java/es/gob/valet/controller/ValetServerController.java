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
 * <b>File:</b><p>es.gob.valet.controller.ValetServerController.java.</p>
 * <b>Description:</b><p>Class that manages the request related to the ValetServer administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.form.ValetServerForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

/** 
 * <p>Class that manages the request related to the ValetServer administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@Controller
public class ValetServerController {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ValetServerController.class);
	
	/**
	 * Attribute that represents the class IValetServerService. 
	 */
	@Autowired
	private IValetServerService valetServerService;

	/**
	 * Loads the form view for adding a new ValetServer.
	 * <p>
	 * Initializes a new {@link ValetServerForm} and attaches it to the model,
	 * then returns the path to the add server modal HTML template.
	 * </p>
	 *
	 * @param model the Spring {@link Model} used to pass attributes to the view
	 * @return the path to the add server form HTML template
	 */
	@GetMapping("/addValetServer")
	public String loadAddValetServer(Model model) {
		try {
			ValetServerForm valetServerForm = new ValetServerForm();
			model.addAttribute("valetServerForm", valetServerForm);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE008, e.getMessage()), e);
		}
		return "modal/valetServer/addForm.html";
	}
	
	/**
	 * Loads the form view for editing an existing ValetServer.
	 * <p>
	 * Fetches the ValetServer by ID and wraps it in a {@link ValetServerForm}.
	 * If the entity is not found, logs a warning and initializes an empty form.
	 * </p>
	 *
	 * @param id the ID of the ValetServer to edit
	 * @param model the Spring {@link Model} used to pass attributes to the view
	 * @return the path to the edit server form HTML template
	 */
	@GetMapping("/editValetServer")
	public String loadEditValetServer(@RequestParam Long id, Model model) {
		ValetServerForm valetServerForm;
		try {
			Optional<ValetServer> opt = valetServerService.findById(id);
			if (opt.isPresent()) {
				valetServerForm = new ValetServerForm(opt.get());
			} else {
				valetServerForm = new ValetServerForm();
				LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE010, id.toString()));
			}
			model.addAttribute("valetServerForm", valetServerForm);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE009, id.toString(), e.getMessage()), e);
			model.addAttribute("valetServerForm", new ValetServerForm());
		}
		return "modal/valetServer/editForm.html";
	}
	
	/**
	 * Loads the form view for deleting an existing ValetServer.
	 * <p>
	 * Fetches the ValetServer by ID and wraps it in a {@link ValetServerForm}.
	 * If the entity is not found, logs a warning and initializes an empty form.
	 * </p>
	 *
	 * @param id the ID of the ValetServer to delete
	 * @param model the Spring {@link Model} used to pass attributes to the view
	 * @return the path to the delete server form HTML template
	 */
	@GetMapping("/deleteValetServer")
	public String loadDeleteValetServer(@RequestParam Long id, Model model) {
		ValetServerForm valetServerForm;
		try {
			Optional<ValetServer> opt = valetServerService.findById(id);
			if (opt.isPresent()) {
				valetServerForm = new ValetServerForm(opt.get());
			} else {
				valetServerForm = new ValetServerForm();
				LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE010, id.toString()));
			}
			model.addAttribute("valetServerForm", valetServerForm);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE009, id.toString(), e.getMessage()), e);
			model.addAttribute("valetServerForm", new ValetServerForm());
		}
		return "modal/valetServer/deleteForm.html";
	}
}
