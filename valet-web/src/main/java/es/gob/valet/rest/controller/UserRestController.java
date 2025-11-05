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
 * <b>Description:</b><p>Class that manages the REST requests related to the Users administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.6, 05/11/2025.
 */
package es.gob.valet.rest.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.validation.constraints.NotEmpty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.UserForm;
import es.gob.valet.form.UserFormPassword;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;

/**
 * <p>Class that manages the REST requests related to the Users administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 05/11/2025.
 */
@RestController
public class UserRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);
	
	/**
	 * Constant that represents the parameter 'login'.
	 */
	private static final String FIELD_LOGIN = "login";

	/**
	 * Constant that represents the parameter 'name'.
	 */
	private static final String FIELD_NAME = "name";

	/**
	 * Constant that represents the parameter 'surnames'.
	 */
	private static final String FIELD_SURNAMES = "surnames";

	/**
	 * Constant that represents the parameter 'email'.
	 */
	private static final String FIELD_EMAIL = "email";

	/**
	 * Constant that represents the parameter 'passwordAdd'.
	 */
	private static final String FIELD_PASSWORD_ADD = "passwordAdd";

	/**
	 * Constant that represents the parameter 'password'.
	 */
	private static final String FIELD_PASSWORD= "password";

	/**
	 * Constant that represents the parameter 'confirmPasswordAdd'.
	 */
	private static final String FIELD_CONFIRM_PASSWORD_ADD = "confirmPasswordAdd";

	/**
	 * Constant that represents the parameter 'confirmPassword'.
	 */
	private static final String FIELD_CONFIRM_PASSWORD = "confirmPassword";

	/**
	 * Constant that represents the parameter 'oldPassword'.
	 */
	private static final String FIELD_OLD_PASSWORD = "oldPassword";
	
	/** 
	 * Regular expression pattern used to validate email addresses. 
	 */
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	
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
	public DataTablesOutput<UserValet> users(@NotEmpty DataTablesInput input) {
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
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
	public String deleteUser(@RequestParam("id") Long userId, @RequestParam("index") String index, @RequestParam("remoteUser") String remoteUser) {
		
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		UserValet userValet = userService.getUserValetById(userId);
		if(userValet !=null && !userValet.getLogin().equals(remoteUser)){
			userService.deleteUserValet(userId);
		}else{
			index = "-1";
		}

		return index;
	}
	
	/**
	 * Handles POST requests to save or update a user record.
	 * Validates the provided user data and persists it if no errors are found.
	 * 
	 * @param userForm the form data containing user information
	 * @return a JSON string containing either validation errors or the saved user data
	 * @throws IOException if an error occurs while generating the JSON response
	 */
	@RequestMapping(value = "/saveuseredit", method = RequestMethod.POST)
	public @ResponseBody String saveEdit(@RequestBody UserForm userForm) throws IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode responseNode = objectMapper.createObjectNode();
		JSONObject json = new JSONObject();
		UserValet userValet = null;
		boolean error = Boolean.FALSE;
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		boolean searchLogin = Boolean.FALSE;
		boolean validatePass = Boolean.FALSE;
		
		if (userForm.getIdUserValet() != null) {
			userValet = userService.getUserValetById(userForm.getIdUserValet());
			
			if(!userValet.getLogin().equals(userForm.getLogin())) {
				searchLogin = Boolean.TRUE;
			}
		} else {
			userValet = new UserValet();
			searchLogin = Boolean.TRUE;
			validatePass = Boolean.TRUE;
		}
		
		validateUser(userForm, json, userService, searchLogin, validatePass);
		
		if (json.length() > 0) {
			error = Boolean.TRUE;
			responseNode.set("error", objectMapper.readTree(json.toString()));
		}
		
		if (!error) {
			String pwd = userForm.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);
			
			userValet.setAttemptsNumber(NumberConstants.NUM0);
			userValet.setEmail(userForm.getEmail());
			userValet.setIsBlocked(Boolean.FALSE);
			userValet.setLastAccess(null);
			userValet.setLastIpAccess(null);
			userValet.setName(userForm.getName());
			userValet.setSurnames(userForm.getSurnames());
			userValet.setLogin(userForm.getLogin());
			userValet.setPassword(hashPwd);
			
			UserValet user = userService.saveUserValet(userValet);
			
			responseNode.set("data", objectMapper.valueToTree(user));
		}
		
		return objectMapper.writeValueAsString(responseNode);
		
	}

	/**
	 * Validates the user form data and populates the provided JSON object with error messages if any fields are invalid.
	 * Checks NIF format and uniqueness, as well as length and format constraints for name, surnames, and email.
	 *
	 * @param userForm the form containing user input data
	 * @param json the JSON object to store validation error messages
	 * @param userService the service used to query existing users
	 * @param searchLogin flag indicating whether to check for duplicate Login values
	 * @param validatePass flag indicating whether to check password
	 */
	private void validateUser(UserForm userForm, JSONObject json, IUserValetService userService, boolean searchLogin, boolean validatePass) {
		if (UtilsStringChar.isNullOrEmpty(userForm.getName()) || !(userForm.getName().length() >= NumberConstants.NUM3 && userForm.getName().length() <= NumberConstants.NUM15)) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_001);
			LOGGER.error(msgError);
			json.put(FIELD_NAME + "_span", msgError);
		} 
		if (UtilsStringChar.isNullOrEmpty(userForm.getSurnames()) || !(userForm.getSurnames().length() >= NumberConstants.NUM3 &&  userForm.getSurnames().length() <= NumberConstants.NUM30)) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_002);
			LOGGER.error(msgError);
			json.put(FIELD_SURNAMES + "_span", msgError);
		}
		if (UtilsStringChar.isNullOrEmpty(userForm.getLogin())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_003);
			LOGGER.error(msgError);
			json.put(FIELD_LOGIN + "_span", msgError);
		} else if(searchLogin && null != userService.getUserValetByLogin(userForm.getLogin())) { 
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_007);
			LOGGER.error(msgError);
			json.put(FIELD_LOGIN + "_span", msgError);
		}
		if (validatePass && (UtilsStringChar.isNullOrEmpty(userForm.getPassword()) || !(userForm.getPassword().length() >= NumberConstants.NUM8 &&  userForm.getPassword().length() <= NumberConstants.NUM30))) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_004);
			LOGGER.error(msgError);
			json.put(FIELD_PASSWORD_ADD + "_span", msgError);
		}
		if (validatePass && (UtilsStringChar.isNullOrEmpty(userForm.getConfirmPassword()) || !userForm.getPassword().equals(userForm.getConfirmPassword()))) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_005);
			LOGGER.error(msgError);
			json.put(FIELD_CONFIRM_PASSWORD_ADD + "_span", msgError);
		}
		if (UtilsStringChar.isNullOrEmpty(userForm.getEmail()) || !isValidEmail(userForm.getEmail())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_006);
			LOGGER.error(msgError);
			json.put(FIELD_EMAIL + "_span", msgError);
		}
	}

	/**
	 * Method that changes the password.
	 *
	 * @param userFormPassword Object that represents the backup form fot the user's password modification.
	 * @param bindingResult Object that represents the form validation result.
	 * @return String result
	 */
	@RequestMapping(value = "/saveuserpassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String savePassword(@RequestBody UserFormPassword userFormPassword) {
		String result = UtilsStringChar.EMPTY_STRING;
		JSONObject json = new JSONObject();
		boolean error = Boolean.FALSE;
		
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		UserValet userValet = userService.getUserValetById(userFormPassword.getIdUserValetPass());
		
		validatePassword(userFormPassword, json, userValet);
		
		
		if (json.length() > 0) {
			error = Boolean.TRUE;
		}
		
		if (!error) {
			String pwd = userFormPassword.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);
			userValet.setPassword(hashPwd);
			
			userService.saveUserValet(userValet);
			
			result = "0";
		} else {
			result = json.toString();
		}
		
		return result;
	}
	
	/**
	 * Validates the password change form fields.
	 *
	 * <p>This method performs the following checks:
	 * <ul>
	 *   <li>Verifies that the old password is not empty and matches the user's current encoded password.</li>
	 *   <li>Ensures the new password is not empty and has a valid length (between 8 and 30 characters).</li>
	 *   <li>Confirms that the confirmation password matches the new password.</li>
	 * </ul>
	 * Any validation errors are logged and added to the provided JSON object.
	 *
	 * @param userFormPassword the form object containing old, new, and confirmation passwords
	 * @param json the JSON object to store validation error messages
	 * @param userValet the user entity containing the current encoded password
	 */
	private void validatePassword(UserFormPassword userFormPassword, JSONObject json, UserValet userValet) {
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		
		if (UtilsStringChar.isNullOrEmpty(userFormPassword.getOldPassword()) || !bc.matches(userFormPassword.getOldPassword(), userValet.getPassword())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_008);
			LOGGER.error(msgError);
			json.put(FIELD_OLD_PASSWORD + "_span", msgError);
		}
		if (UtilsStringChar.isNullOrEmpty(userFormPassword.getPassword()) || !(userFormPassword.getPassword().length() >= NumberConstants.NUM8 &&  userFormPassword.getPassword().length() <= NumberConstants.NUM30)) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_004);
			LOGGER.error(msgError);
			json.put(FIELD_PASSWORD + "_span", msgError);
		}
		if (UtilsStringChar.isNullOrEmpty(userFormPassword.getConfirmPassword()) || !userFormPassword.getPassword().equals(userFormPassword.getConfirmPassword())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.URC_005);
			LOGGER.error(msgError);
			json.put(FIELD_CONFIRM_PASSWORD + "_span", msgError);
		}
		
	}

	/**
	 * Validates the format of an email address using a predefined regular expression pattern.
	 *
	 * @param email the email address to validate
	 * @return {@code true} if the email format is valid; {@code false} otherwise
	 */
	public static boolean isValidEmail(String email) {
		if (email == null)
			return false;
		return EMAIL_PATTERN.matcher(email).matches();
	}

}
