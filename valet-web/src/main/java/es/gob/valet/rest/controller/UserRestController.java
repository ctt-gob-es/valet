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
 * @version 1.5, 04/11/2025.
 */
package es.gob.valet.rest.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotEmpty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.UserForm;
import es.gob.valet.form.UserFormPassword;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;
import es.gob.valet.rest.exception.OrderedValidation;
import es.gob.valet.utils.GeneralConstantsValetWeb;

/**
 * <p>Class that manages the REST requests related to the Users administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 04/11/2025.
 */
@RestController
public class UserRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);
	
	/**
	 * Constant that represents the parameter 'nif'.
	 */
	private static final String FIELD_NIF = "nif";

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
		if(userValet !=null && !userValet.getNif().equals(remoteUser)){
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
		boolean searchNif = Boolean.FALSE;
		
		if (userForm.getIdUserValet() != null) {
			userValet = userService.getUserValetById(userForm.getIdUserValet());
			
			if(!userValet.getNif().equals(userForm.getNif())) {
				searchNif = Boolean.TRUE;
			}
		} else {
			userValet = new UserValet();
			searchNif = Boolean.TRUE;
		}
		
		validateUser(userForm, json, userService, searchNif);
		
		if (json.length() > 0) {
			error = Boolean.TRUE;
			responseNode.set("error", objectMapper.readTree(json.toString()));
		}
		
		if (!error) {
			
			userValet.setAttemptsNumber(NumberConstants.NUM0);
			userValet.setEmail(userForm.getEmail());
			userValet.setIsBlocked(Boolean.FALSE);
			userValet.setLastAccess(null);
			userValet.setLastIpAccess(null);
			userValet.setName(userForm.getName());
			userValet.setSurnames(userForm.getSurnames());
			userValet.setNif(userForm.getNif());

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
	 * @param searchNif flag indicating whether to check for duplicate NIF values
	 */
	private void validateUser(UserForm userForm, JSONObject json, IUserValetService userService, boolean searchNif) {
		if (UtilsStringChar.isNullOrEmpty(userForm.getNif()) || !validateNif(userForm.getNif())) {
			String msgError = Language.getResWebGeneral(WebGeneralMessages.URC_001);
			LOGGER.error(msgError);
			json.put(FIELD_NIF + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
		} else if(searchNif && null != userService.getUserValetByNif(userForm.getNif())) { 
			String msgError = Language.getResWebGeneral(WebGeneralMessages.URC_005);
			LOGGER.error(msgError);
			json.put(FIELD_NIF + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
		}
		if (UtilsStringChar.isNullOrEmpty(userForm.getName()) || userForm.getName().length() < NumberConstants.NUM3 &&  userForm.getName().length() > NumberConstants.NUM15) {
			String msgError = Language.getResWebGeneral(WebGeneralMessages.URC_002);
			LOGGER.error(msgError);
			json.put(FIELD_NAME + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
		} 
		if (UtilsStringChar.isNullOrEmpty(userForm.getSurnames()) || userForm.getSurnames().length() < NumberConstants.NUM3 &&  userForm.getSurnames().length() > NumberConstants.NUM30) {
			String msgError = Language.getResWebGeneral(WebGeneralMessages.URC_003);
			LOGGER.error(msgError);
			json.put(FIELD_SURNAMES + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
		} 
		if (UtilsStringChar.isNullOrEmpty(userForm.getEmail()) || !isValidEmail(userForm.getEmail())) {
			String msgError = Language.getResWebGeneral(WebGeneralMessages.URC_004);
			LOGGER.error(msgError);
			json.put(FIELD_EMAIL + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
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
	public String savePassword(@Validated(OrderedValidation.class) @RequestBody UserFormPassword userFormPassword, BindingResult bindingResult) {
		String result = UtilsStringChar.EMPTY_STRING;
		IUserValetService userService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getUserValetService();
		UserValet userValet = userService.getUserValetById(userFormPassword.getIdUserValetPass());

		if (bindingResult.hasErrors()) {
			JSONObject json = new JSONObject();
			for (FieldError o: bindingResult.getFieldErrors()) {
				json.put(o.getField() + GeneralConstantsValetWeb.SPAN_ELEMENT, o.getDefaultMessage());
			}
			result = json.toString();
		} else {
			String oldPwd = userFormPassword.getOldPassword();
			String pwd = userFormPassword.getPassword();

			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);

		}

		return result;
	}
	
	/**
	 * Validates a Spanish NIF (Número de Identificación Fiscal).
	 * Ensures the format consists of 8 digits followed by an uppercase letter
	 * and verifies that the letter matches the numeric part according to NIF rules.
	 *
	 * @param nif the NIF string to validate
	 * @return {@code true} if the NIF is valid; {@code false} otherwise
	 */
	public boolean validateNif(String nif) {
	    if (nif == null) return false;

	    // Expresión regular para 8 dígitos seguidos de una letra
	    Pattern pattern = Pattern.compile("^[0-9]{8}[A-Z]$");
	    Matcher matcher = pattern.matcher(nif);
	    if (!matcher.matches()) {
	        return false;
	    }

	    String letras = "TRWAGMYFPDXBNJZSQVHLCKET";
	    String numeroStr = nif.substring(0, 8);
	    char letra = nif.charAt(8);
	    int numero = Integer.parseInt(numeroStr);
	    char letraCorrecta = letras.charAt(numero % 23);

	    return letra == letraCorrecta;
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
