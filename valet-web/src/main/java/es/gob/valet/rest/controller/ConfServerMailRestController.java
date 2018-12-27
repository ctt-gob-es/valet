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
 * <b>File:</b><p>es.gob.valet.rest.controller.ConfServerMailRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST requests related to the ConfServerMails administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 27/12/2018.
 */
package es.gob.valet.rest.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.form.ConfServerMailForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.ConfServerMail;
import es.gob.valet.persistence.configuration.services.ifaces.IConfServerMailService;
import es.gob.valet.persistence.utils.UtilsAESCipher;

/**
 * <p>Class that manages the REST requests related to the ConfServerMails administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 27/12/2018.
 */
@RestController
public class ConfServerMailRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConfServerMailRestController.class);

	/**
	 * Method that maps the save configuration of server mail web request to the controller and saves
	 * it in the persistence.
	 * @param confServerMailForm
	 * Object that represents the backing configuration server mail form.
	 * @param bindingResult
	 * Object that represents the form validation result.
	 * @return {@link ConfServerMail}
	 */
	@RequestMapping(value = "/saveconfservermail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ConfServerMail save(@RequestBody ConfServerMailForm confServerMailForm, BindingResult bindingResult) {
		ConfServerMail confMail, result = new ConfServerMail();

		if (bindingResult.hasErrors()) {
			JSONObject json = new JSONObject();
			for (FieldError o: bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
		} else {
			try {
				IConfServerMailService confServerMailService = ManagerPersistenceConfigurationServices.getInstance().getConfServerMailService();
				if (confServerMailForm.getIdConfServerMail() != null) {
					confMail = confServerMailService.getConfServerMailById(confServerMailForm.getIdConfServerMail());
				} else {
					confMail = new ConfServerMail();
				}
				confMail.setIssuerMail(confServerMailForm.getIssuerMail());
				confMail.setHostMail(confServerMailForm.getHostMail());
				confMail.setPortMail(confServerMailForm.getPortMail());
				confMail.setUseAuthenticationMail(confServerMailForm.getUseAuthenticationMail());
				confMail.setUserMail(confServerMailForm.getUserMail());
				String pwd = confServerMailForm.getPasswordMail();
				if (pwd == null) {
					confMail.setPasswordMail(null);
				} else {
					confMail.setPasswordMail(new String(UtilsAESCipher.getInstance().encryptMessage(pwd)));
				}
				result = confServerMailService.saveConfServerMail(confMail);
			} catch (Exception e) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_MODIFY_PROXY), e);
			}
		}

		return result;
	}

}
