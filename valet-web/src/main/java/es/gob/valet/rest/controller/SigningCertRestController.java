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
 * <b>File:</b><p>es.gob.valet.rest.controller.SigningCertRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the Signing Certificate Configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.2, 29.05/2025.
 */
package es.gob.valet.rest.controller;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsKeystore;
import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;
import es.gob.valet.service.ifaces.ISigningCertService;

/**
 * <p>Class that manages the REST request related to the proxy configuration.</p>
 * <b>Project:</b><p>Class that manages the REST request related to the Signing Certificate Configuration.</p>
 * @version 1.2, 29.05/2025.
 */
@RestController
public class SigningCertRestController {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(SigningCertRestController.class);
	
	/**
	 * Service for managing signing certificates.
	 */
	@Autowired
	private ISigningCertService iSigningCertService;
	
	/**
	 * Constant that represents the parameter 'keystoreFile'.
	 */
	private static final String FIELD_KEYSTORE_FILE = "keystoreFile";
	
	/**
	 * Constant that represents the parameter 'passwordKeystore'.
	 */
	private static final String FIELD_PASSWORD_KEYSTORE = "passwordKeystore";
	
	/**
	 * Endpoint to update a signing certificate.
	 * 
	 * This method handles the update process for a signing certificate. It receives a `SigningCertificateDTO` 
	 * and a `keystoreFile` as inputs, validates them, and if everything is valid, proceeds with the update process 
	 * by calling the service method `updateSigningCert`. If any validation errors occur, an error message will be 
	 * returned in the `SigningCertificateDTO`.
	 *
	 * @param signingCertificateDTO the data transfer object containing the signing certificate information
	 * @param keystoreFile the keystore file to be used for updating the signing certificate
	 * @return the updated `SigningCertificateDTO`, including any error messages if validation fails
	 * @throws ValetException if an exception occurs during the update process
	 */
	@RequestMapping(value = "/updateSigningCert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody SigningCertificateDTO updateSigningCert(@RequestPart("signingCertificateDTO") SigningCertificateDTO signingCertificateDTO, @RequestPart("keystoreFile") final MultipartFile keystoreFile) throws ValetException {
		JSONObject json = new JSONObject();
		this.validateInputsUpdate(signingCertificateDTO, keystoreFile, json);
		if (json.length() > 0) {
			signingCertificateDTO.setError(json.toString());
			return signingCertificateDTO;
		} else {
			try {
				signingCertificateDTO = iSigningCertService.updateSigningCert(signingCertificateDTO.getPasswordKeystore(), keystoreFile);
			} catch (CipherException | KeyStoreException | NoSuchAlgorithmException
					| CertificateException | CommonUtilsException | IOException e) {
				LOGGER.error(e);
				throw new ValetException(e);
			}		
		}
		return signingCertificateDTO;
	}
	
	/**
	 * Endpoint to delete a signing certificate.
	 * 
	 * This method deletes the signing certificate by calling the `deleteSigningCert` method from the service layer.
	 * No response is returned, indicating the certificate was successfully deleted.
	 */
	@RequestMapping(value = "/deleteSigningCert", method = RequestMethod.POST)
	public void deleteSigningCert() {
		iSigningCertService.deleteSigningCert();
	}
	
	/**
	 * Validates the inputs provided for updating the signing certificate.
	 * 
	 * This method checks whether the `keystoreFile` is empty, the password for the keystore is provided, 
	 * and that the keystore file contains a valid certificate. It also checks the certificate's validity and 
	 * whether it has expired or is not yet valid. If any issues are found, corresponding error messages are added 
	 * to the provided `json` object.
	 *
	 * @param signingCertificateDTO the data transfer object containing the signing certificate information
	 * @param keystoreFile the keystore file to be validated
	 * @param json the JSON object to store error messages
	 */
	private void validateInputsUpdate(SigningCertificateDTO signingCertificateDTO, MultipartFile keystoreFile, JSONObject json) {
		if(keystoreFile.isEmpty()) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF001);
			LOGGER.error(msgError);
			json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
		} else if(StringUtils.isEmpty(signingCertificateDTO.getPasswordKeystore())) {
			String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF002);
			LOGGER.error(msgError);
			json.put(FIELD_PASSWORD_KEYSTORE + "_span", msgError);
		} else {
			try {
				KeyStore keyStore = UtilsKeystore.loadKsPKCS12(keystoreFile.getInputStream(), signingCertificateDTO.getPasswordKeystore());
				List<X509Certificate> listX509Certificate = UtilsKeystore.listAllX509Certificate(keyStore);
				if(null == listX509Certificate || listX509Certificate.isEmpty()) {
					String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF003);
					LOGGER.error(msgError);
					json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
				} else if(listX509Certificate.size() > NumberConstants.NUM1_LONG) {
					String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF004);
					LOGGER.error(msgError);
					json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
				} else {
					listX509Certificate.get(NumberConstants.NUM0).checkValidity();
				}
			} catch (CertificateExpiredException e) {
				LOGGER.error(e);
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF009);
				json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
			} catch (CertificateNotYetValidException e) {
				LOGGER.error(e);
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF010);
				json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
			} catch (KeyStoreException e) {
				LOGGER.error(e);
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF005);
				json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
			} catch (NoSuchAlgorithmException e) {
				LOGGER.error(e);
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF006);
				json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
			} catch (CertificateException e) {
				LOGGER.error(e);
				String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF007);
				json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
			} catch (IOException e) {
				if(e.getCause() instanceof UnrecoverableKeyException) {
					String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF008);
					LOGGER.error(msgError);
					json.put(FIELD_PASSWORD_KEYSTORE + "_span", msgError);
				} else {
					String msgError = Language.getResWebGeneral(IWebGeneralMessages.LOG_CCF011);
					LOGGER.error(msgError);
					json.put(FIELD_KEYSTORE_FILE + "_span", msgError);
				}
			}
		}
	}
}
