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
 * <b>File:</b><p>es.gob.valet.controller.UserController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the Users administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 26/12/2018.
 */
package es.gob.valet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.form.UserForm;
import es.gob.valet.form.UserFormEdit;
import es.gob.valet.form.UserFormPassword;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;

/**
 * <p>Class that manages the requests related to the Users administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 26/12/2018.
 */
@Controller
public class UserController {


	/**
	 * Method that maps the list users web requests to the controller and
	 * forwards the list of users to the view.
	 *
	 * @param model
	 *            Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "useradmin")
	public String index(Model model) {
		model.addAttribute("userFormPassword", new UserFormPassword());
		model.addAttribute("userformEdit", new UserFormEdit());
		return "fragments/useradmin.html";
	}

	/**
	 * Method that maps the add user web request to the controller and sets the
	 * backing form.
	 *
	 * @param model
	 *            Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "adduser", method = RequestMethod.POST)
	public String addUser(Model model) {
		model.addAttribute("userform", new UserForm());
		model.addAttribute("accion", "add");
		return "modal/user/userForm";
	}

	/**
	 * Method that maps the change password to the controller and sets the backing form.
	 *
	 * @param login User requesting the password change.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "menupass")
	public String menuPass(@RequestParam("login") String login, Model model) {
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		UserValet userValet = userService.getUserValetByNif(login);
		UserFormPassword userFormPassword = new UserFormPassword();
		if (userValet != null) {
			userFormPassword.setIdUserValetPass(userValet.getIdUserValet());
			userFormPassword.setNameUser(userValet.getName());
		}
		model.addAttribute("userFormPassword", userFormPassword);
		return "modal/user/userFormPass.html";
	}

	/**
	 * Method that maps the editing of user data to the controller and sets the backing form.
	 *
	 * @param username User whose information will be modified.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "menuedit")
	public String menuEdit(@RequestParam("username") String username, Model model) {
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		UserValet userValet = userService.getUserValetByNif(username);
		UserFormEdit userFormEdit = new UserFormEdit();

		userFormEdit.setIdUserValetEdit(userValet.getIdUserValet());
		userFormEdit.setNameEdit(userValet.getName());
		userFormEdit.setSurnamesEdit(userValet.getSurnames());
		userFormEdit.setEmailEdit(userValet.getEmail());
		userFormEdit.setNifEdit(userValet.getNif());

		model.addAttribute("userformEdit", userFormEdit);
		return "modal/user/userFormEdit.html";
	}

}
