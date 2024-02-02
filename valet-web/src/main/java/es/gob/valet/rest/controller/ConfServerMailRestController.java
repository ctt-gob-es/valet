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
 * @version 1.8, 30/01/2024.
 */
package es.gob.valet.rest.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.commons.utils.UtilsStringChar;
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
 * @version 1.8, 30/01/2024.
 */
@RestController
public class ConfServerMailRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConfServerMailRestController.class);
	/**
	 * Constant that represents the default text string of the password displayed in edit mode.
	 */
	private static final String PASSWORD_EDIT = "********";
	
	/**
	 * Constant that represents the parameter 'issuerMail'.
	 */
	private static final String FIELD_ISSUER_MAIL = "issuerMail";
	/**
	 * Constant that represents the parameter 'hostMail'.
	 */
	private static final String FIELD_HOST_MAIL = "hostMail";

	/**
	 * Constant that represents the parameter 'portMail'.
	 */
	private static final String FIELD_PORT_MAIL = "portMail";
	/**
	 * Constant that represents the parameter 'hostMail'.
	 */
	private static final String FIELD_USER_MAIL = "userMail";
	/**
	 * Constant that represents the parameter 'connectionTimeout'.
	 */
	private static final String FIELD_CONNECTION_TIMEOUT = "connectionTimeout";
	/**
	 * Constant that represents the parameter 'readingTimeout'.
	 */
	private static final String FIELD_READING_TIMEOUT= "readingTimeout";
	/**
	 * Constant that represents the parameter 'certificateFile'.
	 */
	private static final String FIELD_CERTIFICATE_FILE= "certificateFile";
	/**
	 * The request parameter name for the backing form object representing the configuration of the mail server.
	 */
	private static final String RQ_PARAM_CONFSERVERMAILFORM = "confServerMailForm";
	/**
	 * The request parameter name for the MultipartFile containing the certificate file to be associated with the configuration.
	 */
	private static final String RQ_PARAM_CERTIFICATEFILE = "certificateFile";
	

	/**
	 * Method that maps the save configuration of server mail web request to the controller and saves
	 * it in the persistence.
	 * @param confServerMailForm The backing form object representing the configuration of the mail server.
	 * @param certificateFile    The MultipartFile containing the certificate file to be associated with the configuration.
	 * @return A {@link ConfServerMailForm} representing the updated configuration or an error message in case of validation failure.
	 */
	@RequestMapping(value = "/saveconfservermail", method = RequestMethod.POST)
	public @ResponseBody ConfServerMailForm save(@RequestPart(RQ_PARAM_CONFSERVERMAILFORM) ConfServerMailForm confServerMailForm, @RequestPart(RQ_PARAM_CERTIFICATEFILE) MultipartFile certificateFile) {
		
		ConfServerMailForm csmFormUpdated = confServerMailForm;
		JSONObject json = new JSONObject();
		boolean error = Boolean.FALSE;
		

			try {
				validateConfServerMailParam(confServerMailForm, json, certificateFile);
				
				if (json.length() > 0) {
					error = Boolean.TRUE;
				}
				if (!error) {
				ConfServerMail confMail = null;
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
				confMail.setConnectionTimeout(confServerMailForm.getConnectionTimeout());
				confMail.setReadingTimeout(confServerMailForm.getReadingTimeout());
				confMail.setTlsEnabled(confServerMailForm.getTlsEnabled());
				
				if (certificateFile != null) {
					confMail.setCertificateFile(certificateFile.getBytes());
					confMail.setOriginalNameFile(certificateFile.getOriginalFilename());
					csmFormUpdated.setCertificateFile(certificateFile.getBytes());
					csmFormUpdated.setOriginalNameFile(certificateFile.getOriginalFilename());
				}
				
				if (confServerMailForm.getUseAuthenticationMail()) {
					confMail.setUserMail(confServerMailForm.getUserMail());
					//se comprueba si se ha modificado la contraseña
					String pwd = confServerMailForm.getPasswordMail();
					if (!UtilsStringChar.isNullOrEmpty(pwd) && !pwd.equals(PASSWORD_EDIT)) {
						confMail.setPasswordMail(new String(UtilsAESCipher.getInstance().encryptMessage(pwd)));
					}
					
				}else{
					confMail.setUserMail(null);
					confMail.setPasswordMail(null);
				}
								
				//se guarda en base de datos
				confServerMailService.saveConfServerMail(confMail);
				// se muestra un mensaje indicando que se ha actualizado
				// correctamente.
				LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.CMS_005));
				csmFormUpdated.setMsgOk(Language.getResWebGeneral(IWebGeneralMessages.CMS_005));
				}else{
					csmFormUpdated.setError(json.toString());
				}
			} catch (Exception e) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.CMS_006), e);
				if (confServerMailForm == null) {
					csmFormUpdated = new ConfServerMailForm();
				}
				csmFormUpdated.setError(Language.getFormatResWebGeneral(IWebGeneralMessages.CMS_007));
			}
		

		return csmFormUpdated;
	}

	

	/**
	 * Method to validate the mandatory fields to add a specific header for HTTP/S.
	 * 
	 * @param valmetForm Object that represents the backing Configuration Server Mail form.
	 * @param json Contains the error messages that have been generated.
	 * @param certificateFile    The MultipartFile containing the certificate file to be associated with the configuration.
	 * @return JSONObject Object JSON Object with the error messages that have been generated.
	 */
	private JSONObject validateConfServerMailParam(ConfServerMailForm csmform, JSONObject json, MultipartFile certificateFile) {

		if (UtilsStringChar.isNullOrEmpty(csmform.getIssuerMail())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_001);
			LOGGER.error(msgError);
			json.put(FIELD_ISSUER_MAIL + "_span", msgError);
		} 
		if (UtilsStringChar.isNullOrEmpty(csmform.getHostMail())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_002);
			LOGGER.error(msgError);
			json.put(FIELD_HOST_MAIL + "_span", msgError);
		} 
		if (csmform.getPortMail() == null) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_003);
			LOGGER.error(msgError);
			json.put(FIELD_PORT_MAIL + "_span", msgError);
		} 
		
		if(csmform.getUseAuthenticationMail()){
			if(UtilsStringChar.isNullOrEmpty(csmform.getUserMail()) || UtilsStringChar.isNullOrEmpty(csmform.getPasswordMail())){
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_004);
			LOGGER.error(msgError);
			json.put(FIELD_USER_MAIL + "_span", msgError);
			}
		}
		
		if (csmform.getConnectionTimeout() == null) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_008);
			LOGGER.error(msgError);
			json.put(FIELD_CONNECTION_TIMEOUT + "_span", msgError);
		} else{
			if(csmform.getConnectionTimeout().intValue() < 0){
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_010);
				LOGGER.error(msgError);
				json.put(FIELD_CONNECTION_TIMEOUT + "_span", msgError);
			}
		}
		
		if (csmform.getReadingTimeout() == null) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_009);
			LOGGER.error(msgError);
			json.put(FIELD_READING_TIMEOUT + "_span", msgError);
		} else {
			if(csmform.getReadingTimeout().intValue() < 0){
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_010);
				LOGGER.error(msgError);
				json.put(FIELD_READING_TIMEOUT + "_span", msgError);
			}
		}
		
		if(null != csmform.getTlsEnabled() && csmform.getTlsEnabled()  
				&& certificateFile.getOriginalFilename().equals("nofile")
				&& csmform.getHostMail().indexOf("gmail") == -1) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_011);
			LOGGER.error(msgError);
			json.put(FIELD_CERTIFICATE_FILE + "_span", msgError);
		}
		
		if(null != csmform.getTlsEnabled() && !csmform.getTlsEnabled() && csmform.getHostMail().indexOf("gmail") != -1) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.CMS_012);
			LOGGER.error(msgError);
			json.put(FIELD_CERTIFICATE_FILE + "_span", msgError);
		}
		
		return json;

	}

}
