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
 * <b>File:</b><p>es.gob.valet.rest.controller.UserRestController.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19 jun. 2018.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.UserForm;
import es.gob.valet.form.UserFormEdit;
import es.gob.valet.form.UserFormPassword;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;
import es.gob.valet.rest.exception.OrderedValidation;

/** 
 * <p>Class that manages the REST requests related to the Users administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19 jun. 2018.
 */
@RestController
public class UserRestController {
	
	/**
	 * Attribute that represents the service object for accessing the
	 * UserValetRespository.
	 */
	@Autowired
	private IUserValetService userService;

	/**
	 * Method that maps the list users web requests to the controller and
	 * forwards the list of users to the view.
	 * 
	 * @param input
	 *            Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/usersdatatable", method = RequestMethod.GET)
	public DataTablesOutput<UserValet> users(@Valid DataTablesInput input) {
		return (DataTablesOutput<UserValet>) userService.getAllUser(input);

	}

	/**
	 * Method that maps the delete user request from datatable to the controller
	 * and performs the delete of the user identified by its id.
	 * 
	 * @param userId
	 *            Identifier of the user to be deleted.
	 * @param index
	 *            Row index of the datatable.
	 * @return String that represents the name of the view to redirect.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/deleteuser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("id") Long userId, @RequestParam("index") String index) {
		userService.deleteUserValet(userId);

		return index;
	}

	/**
	 * Method that maps the save user web request to the controller and saves it
	 * in the persistence.
	 * 
	 * @param userForm
	 *            Object that represents the backing user form.
	 * @param bindingResult
	 *            Object that represents the form validation result.
	 * @return {@link DataTablesOutput<UserValet>}
	 */
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<UserValet> save(
			@Validated(OrderedValidation.class) @RequestBody UserForm userForm, BindingResult bindingResult) {
		DataTablesOutput<UserValet> dtOutput = new DataTablesOutput<>();
		UserValet userValet = null;
		List<UserValet> listNewUser = new ArrayList<UserValet>();

		if (bindingResult.hasErrors()) {
			listNewUser = StreamSupport.stream(userService.getAllUserValet().spliterator(), false)
					.collect(Collectors.toList());
			JSONObject json = new JSONObject();
			for (FieldError o : bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			dtOutput.setError(json.toString());
		} else {
			try {
				if (userForm.getIdUserValet() != null) {
					userValet = userService.getUserValetById(userForm.getIdUserValet());
				} else {
					userValet = new UserValet();
				}
				if (!UtilsStringChar.isNullOrEmpty(userForm.getPassword())) {
					String pwd = userForm.getPassword();
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					String hashPwd = bc.encode(pwd);

					userValet.setPassword(hashPwd);
				}

				userValet.setLogin(userForm.getLogin());
				userValet.setAttemptsNumber(NumberConstants.NUM0);
				userValet.setEmail(userForm.getEmail());
				userValet.setIsBlocked(Boolean.FALSE);
				userValet.setLastAccess(null);
				userValet.setLastIpAccess(null);
				userValet.setName(userForm.getName());
				userValet.setSurnames(userForm.getSurnames());

				UserValet user = userService.saveUserValet(userValet);

				listNewUser.add(user);
			} catch (Exception e) {
				listNewUser = StreamSupport.stream(userService.getAllUserValet().spliterator(), false)
						.collect(Collectors.toList());
				throw e;
			}
		}

		dtOutput.setData(listNewUser);

		return dtOutput;

	}

	/**
	 * Method that maps the save user web request to the controller and saves it
	 * in the persistence.
	 * 
	 * @param userForm Object that represents the backing user form.
	 * @param bindingResult  Object that represents the form validation result.
	 * @return  {@link DataTablesOutput<UserValet>}
	 */
	@RequestMapping(value = "/saveuseredit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<UserValet> saveEdit(
			@Validated(OrderedValidation.class) @RequestBody UserFormEdit userForm, BindingResult bindingResult) {
		DataTablesOutput<UserValet> dtOutput = new DataTablesOutput<>();
		UserValet userValet = null;
		List<UserValet> listNewUser = new ArrayList<UserValet>();

		if (bindingResult.hasErrors()) {
			listNewUser = StreamSupport.stream(userService.getAllUserValet().spliterator(), false)
					.collect(Collectors.toList());
			JSONObject json = new JSONObject();
			for (FieldError o : bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			dtOutput.setError(json.toString());
		} else {
			try {
				if (userForm.getIdUserValetEdit() != null) {
					userValet = userService.getUserValetById(userForm.getIdUserValetEdit());
				} else {
					userValet = new UserValet();
				}
				userValet.setLogin(userForm.getLoginEdit());
				userValet.setAttemptsNumber(NumberConstants.NUM0);
				userValet.setEmail(userForm.getEmailEdit());
				userValet.setIsBlocked(Boolean.FALSE);
				userValet.setLastAccess(null);
				userValet.setLastIpAccess(null);
				userValet.setName(userForm.getNameEdit());
				userValet.setSurnames(userForm.getSurnamesEdit());

				UserValet user = userService.saveUserValet(userValet);

				listNewUser.add(user);
			} catch (Exception e) {
				listNewUser = StreamSupport.stream(userService.getAllUserValet().spliterator(), false)
						.collect(Collectors.toList());
				throw e;
			}
		}

		dtOutput.setData(listNewUser);

		return dtOutput;

	}

	/**
	 * Method that changes the password.
	 * 
	 * @param userFormPassword Object that represents the backup form fot the user's password modification.
	 * @param bindingResult Object that represents the form validation result.
	 * @return String result
	 */
	@RequestMapping(value = "/saveuserpassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String savePassword(@Validated(OrderedValidation.class) @RequestBody UserFormPassword userFormPassword,
			BindingResult bindingResult) {
		String result = UtilsStringChar.EMPTY_STRING;
		UserValet userValet = userService.getUserValetById(userFormPassword.getIdUserValetPass());

		if (bindingResult.hasErrors()) {
			JSONObject json = new JSONObject();
			for (FieldError o : bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			result = json.toString();
		} else {
			String oldPwd = userFormPassword.getOldPassword();
			String pwd = userFormPassword.getPassword();

			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);

			try {
				if (bc.matches(oldPwd, userValet.getPassword())) {
					userValet.setPassword(hashPwd);

					userService.saveUserValet(userValet);
					result = "0";
				} else {
					result = "-1";
				}
			} catch (Exception e) {
				result = "-2";
				throw e;
			}
		}

		return result;
	}


	
	/**
	 * Method that edits the user.
	 * @param userForm Object that represents the backing user form.
	 * @param bindingResult  Object that represents the form validation result.
	 * @return String result.
	 */
	@RequestMapping(value = "/menueditsave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String saveEditMenu(@Validated(OrderedValidation.class) @RequestBody UserFormEdit userForm,
			BindingResult bindingResult) {
		UserValet userValet = null;
		String result = UtilsStringChar.EMPTY_STRING;;

		if (bindingResult.hasErrors()) {
			JSONObject json = new JSONObject();
			for (FieldError o : bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			result = json.toString();
		} else {
			try {
				if (userForm.getIdUserValetEdit() != null) {
					userValet = userService.getUserValetById(userForm.getIdUserValetEdit());
				} else {
					userValet = new UserValet();
				}
				userValet.setLogin(userForm.getLoginEdit());
				userValet.setAttemptsNumber(NumberConstants.NUM0);
				userValet.setEmail(userForm.getEmailEdit());
				userValet.setIsBlocked(Boolean.FALSE);
				userValet.setLastAccess(null);
				userValet.setLastIpAccess(null);
				userValet.setName(userForm.getNameEdit());
				userValet.setSurnames(userForm.getSurnamesEdit());

				userService.saveUserValet(userValet);

				result = "0";
			} catch (Exception e) {
				result = "-1";
				throw e;
			}
		}

		return result;
	}

}
