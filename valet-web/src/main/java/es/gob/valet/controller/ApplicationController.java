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
 * <b>File:</b><p>es.gob.valet.controller.ApplicationController.java.</p>
 * <b>Description:</b><p>Class that manages the request related to the Applications administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.form.ApplicationForm;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Class that manages the request related to the Applications administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
@Controller
public class ApplicationController {

	/**
	 * Constant that represents the parameter 'idApplication'.
	 */
	private static final String FIELD_ID_APPLICATION = "idApplication";
	
	/**
	 * Constant that represents the parameter 'appform'.
	 */
	private static final String APPFORM_ATTR = "appform";

	/**
	 * Method that maps the list applicatios to the controller and forwards the list of Applications to the view.
	 *
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "appadmin", method = RequestMethod.GET)
	public String index(Model model) {
		ApplicationForm appForm = new ApplicationForm();
		model.addAttribute(APPFORM_ATTR, appForm);
		return "fragments/appadmin.html";
	}

	/**
	 * Method that maps the add application to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 * @throws IOException If the method fails.
	 */
	@RequestMapping(value = "/addapp")
	public String addApplication(Model model) throws IOException {
		ApplicationForm appForm = new ApplicationForm();
		model.addAttribute(APPFORM_ATTR, appForm);
		return "modal/application/appForm.html";
	}

	/**
	 * Method that loads the information of the selected application.
	 * @param idApplication Parameter that represents the ID of the application.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the viwe to foward.
	 */
	@RequestMapping(value = "/loadApplicationByid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadApplicationById(@RequestParam(FIELD_ID_APPLICATION) Long idApplication, Model model) {
		ApplicationForm appForm = new ApplicationForm();

		ApplicationValet appValet = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().getApplicationById(idApplication);
		appForm.setIdApplication(idApplication);
		appForm.setIdentifier(appValet.getIdentificator());
		appForm.setName(appValet.getName());
		appForm.setResponsibleName(appValet.getResponsibleName());
		appForm.setResponsibleSurnames(appValet.getResponsibleSurnames());
		appForm.setResponsibleMail(appValet.getResponsibleMail());
		appForm.setResponsiblePhone(appValet.getResponsiblePhone());
		model.addAttribute(APPFORM_ATTR, appForm);
		return "modal/application/appEditForm.html";

	}

	/**
	 *  Method that loads the necessary information to show the confirmation modal to remove a selected application.
	 * @param idApplication Parameter that represetns ID of application.
	 * @param rowIndexApplication Parameter that represents the index of the row of the selected application.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadconfirmdeleteapp",  method = RequestMethod.GET)
	public String deleteConfirmCertificate(@RequestParam(FIELD_ID_APPLICATION) Long idApplication, @RequestParam("rowindex") String rowIndexApplication, Model model){
		ApplicationForm appForm = new ApplicationForm();
		appForm.setIdApplication(idApplication);
		appForm.setRowIndexApplication(rowIndexApplication);
		model.addAttribute("deleteapplicationform", appForm);
		return "modal/application/appDelete.html";
	}

}
