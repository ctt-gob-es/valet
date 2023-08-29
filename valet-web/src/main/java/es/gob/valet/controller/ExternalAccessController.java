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
 * <b>File:</b><p>es.gob.valet.controller.ExternalAccessController.java.</p>
 * <b>Description:</b><p>Class that manages the request related to the Applications administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 11/12/2018.
 */
package es.gob.valet.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.gob.valet.form.ApplicationForm;
import es.gob.valet.form.ExternalAccessForm;
import es.gob.valet.form.UserFormEdit;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;
import es.gob.valet.service.ifaces.IExternalAccessService;

/**
 * <p>Class that manages the request related to the Applications administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 11/12/2018.
 */
@Controller
public class ExternalAccessController {

	/**
	 * Constant that represents the parameter 'idApplication'.
	 */
	private static final String FIELD_ID_APPLICATION = "idApplication";


	/**
	 * Attribute that represents the injected interface that provides CRUD
	 * operations for the persistence.
	 */
	@Autowired
	private IExternalAccessService iExternalAccessService;
	
	/**
	 * Method that maps the list applicatios to the controller and forwards the list of Applications to the view.
	 *
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "externalAccessAdmin", method = RequestMethod.GET)
	public String externalAccessAdmin(Model model) {
		ExternalAccessForm externalAccessForm = new ExternalAccessForm();
		model.addAttribute("externalAccessform", externalAccessForm);
		return "fragments/externalAccessAdmin.html";
	}

	
	/**
	 * Method that maps the add application to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 * @throws IOException If the method fails.
	 */
	@RequestMapping(value = "/addexternalAccess" )
	public String addexternalAccess(Model model) throws IOException {
		ExternalAccessForm externalAccessForm = new ExternalAccessForm();
		model.addAttribute("externalAccessform", externalAccessForm);
		return "fragments/externalAccessAdmin.html";
	}

	
	/*
	 * @RequestMapping(value = "tryConnModel") public String
	 * tryConnModel(@RequestParam("valores") String valores, Model model) { String
	 * view="modal/externalAccess/externalAccessTryConnModel"; List<Long> ids =
	 * Arrays.asList(valores.split(",")).stream().map(s ->
	 * Long.parseLong(s.trim())).collect(Collectors.toList());
	 * 
	 * List<ExternalAccessDTO> listExternalAccess =
	 * iExternalAccessService.getListDTObyId(ids);
	 * model.addAttribute("externalAccessList", listExternalAccess); return view;
	 * 
	 * }
	 */
	@RequestMapping(value = "tryConnModel")
	public  String tryConnModel(//@RequestParam(name="valores") String valores, 
			Model model) {
		String view="modal/externalAccess/externalAccessTryConnModel";
	   //List<Long> ids = Arrays.asList(valores.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

		//List<ExternalAccessDTO> listExternalAccess =  iExternalAccessService.getListDTObyId(ids);
		//model.addAttribute("externalAccessList", listExternalAccess);
		return view;
		
	}
		

}
