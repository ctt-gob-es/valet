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
 * <b>File:</b><p>es.gob.valet.rest.controller.KeystoreRestController.java.</p>
 * <b>Description:</b><p> Class that manages the REST request related to the Keystore's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2018.</p>
 * @author Gobierno de España.
 * @version 2.1, 19/12/2023.
 */
package es.gob.valet.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.certificates.CertificateCacheManager;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.keystore.IKeystoreFacade;
import es.gob.valet.crypto.keystore.KeystoreFactory;
import es.gob.valet.form.SystemCertificateForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.services.ifaces.ISystemCertificateService;
import es.gob.valet.persistence.configuration.services.impl.SystemCertificateService;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.utils.GeneralConstantsValetWeb;

/**
 * <p>Class that manages the REST request related to the Keystore's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.1, 19/12/2023.
 */
@RestController
public class KeystoreRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(KeystoreRestController.class);

	/**
	 * Constant that represents the parameter 'idKeystore'.
	 */
	private static final String FIELD_ID_KEYSTORE = "idKeystore";

	/**
	 * Constant that represents the parameter 'idSystemCertificate'.
	 */
	private static final String FIELD_ID_SYSTEM_CERTIFICATE = "idSystemCertificate";

	/**
	 * Constant that represents the parameter 'alias'.
	 */
	private static final String FIELD_ALIAS = "alias";

	/**
	 * Constant that represents the parameter 'certificateFile'.
	 */
	private static final String FIELD_CERTIFICATE_FILE = "certificateFile";

	/**
	 * Constant that represents the parameter 'certificateFile'.
	 */
	private static final String FIELD_ROW_INDEX_CERTIFICATE = "rowIndexCertificate";

	/**
	 * Constant that represents the key Json 'errorSaveTsl'.
	 */
	private static final String KEY_JS_ERROR_SAVE_CERTIFICATE = "errorSaveSystemCertificate";

	/**
	 * Constant that represents the key Json 'errorUpdateTsl'.
	 */
	private static final String KEY_JS_ERROR_UPDATE_CERTIFICATE = "errorUpdatesSystemCertificate";

	/**
	 * Default certificate filename: "Certificate.cer".
	 */
	protected static final String DEFAULT_CERTIFICATE_NAME = "Certificate.cer";

	/**
	 * X509Certificate file (.cer) header content-type.
	 */
	private static final String CERT_CONTENT_TYPE = "application/x-x509-ca-cert";

	/**
	 * Constant that represents the keystore OCSP. It´s number 19.
	 */
	public static final String TOKEN_KEYSTORE19 = "KEYSTORE19";
	
	/**
	 * Attribute that represents the service object for accessing the repository of system certificate service.
	 */
	@Autowired
	private ISystemCertificateService iSystemCertificateService;
	
	/**
	 * Attribute that represents the service object for accessing the repository of external access service.
	 */
	@Autowired
	private IExternalAccessService iExternalAccessService;
	
	/**
	 * Method to load the datatable with all the certificates stored in specified keystore.
	 * @param input Holder object for datatable attributes.
	 * @param idKeystore Parameter that represents a keystore identifier.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/loadcertificates", method = RequestMethod.GET)
	public DataTablesOutput<SystemCertificate> listCertificates(DataTablesInput input, @RequestParam("idKeystore") Long idKeystore) {
		return (DataTablesOutput<SystemCertificate>) ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(input, idKeystore);
	}

	/**
	 * Method that store a system certificate in selected keystore.
	 *
	 * @param idKeystore Parameter that represents a keystore identifier.
	 * @param alias Parameter that represents the alias of system certificate.
	 * @param certificateFile Parameter that represents the file with system certificate.
	 * @param validationCert parameter that contain if certificate is valid. 
	 * @return {@link DataTablesOutput<SystemCertificate>}
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savecertificate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataTablesOutput<SystemCertificate> saveCertificate(@RequestParam(FIELD_ID_KEYSTORE) String idKeystore, @RequestParam(FIELD_ALIAS) String alias, @RequestParam(FIELD_CERTIFICATE_FILE) MultipartFile certificateFile, @RequestParam("validationCert") Optional<Boolean> validationCert) {
		DataTablesOutput<SystemCertificate> dtOutput = new DataTablesOutput<>();
		boolean error = false;
		byte[ ] certificateFileBytes = null;
		JSONObject json = new JSONObject();
		List<SystemCertificate> listCertificates = new ArrayList<SystemCertificate>();
		// se obtiene el keystore

		Keystore keystore = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(Long.valueOf(idKeystore), false);
		try {

			// se comprueba que se han indicado todos los campos obligatorios

			if (UtilsStringChar.isNullOrEmpty(alias)) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_BLANK_ALIAS));
				json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_BLANK_ALIAS));
				error = true;
			}
			
			if(!error){
				// se comprueba que no contenga caracteres especiales
				String listChar = StaticValetConfig.getProperty(StaticValetConfig.LIST_CHARACTER_SPECIAL);
				if (!listChar.isEmpty()) {
					String[ ] characters = listChar.split(",");
					String res = UtilsStringChar.EMPTY_STRING;
					for (int i = 0; i < characters.length; i++) {
						int esta = alias.indexOf(characters[i]);
						if (esta >= 0) {
							char special = alias.charAt(esta);
							res += special + UtilsStringChar.SPECIAL_BLANK_SPACE_STRING;
						}
					}
					if (!res.isEmpty()) {
						LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_SPECIAL_CHAR_ALIAS, new Object[ ] { res, alias }));
						json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_SPECIAL_CHAR_ALIAS, new Object[ ] { res, alias }));
						error = true;
					}

				}
			}
			
			if(!error){
				// se comprueba que no exista un alias igual registrado en el
				// sistema
				SystemCertificate sc = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateByAliasAndKeystoreId(alias, Long.valueOf(idKeystore));
				if (sc != null) {
					String msgError = Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_EXIST_ALIAS, new Object[ ] { alias });
					LOGGER.error(msgError);
					json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
					error = true;
				}

			}
			if (certificateFile == null || certificateFile.getSize() == 0 || certificateFile.getBytes() == null || certificateFile.getBytes().length == 0) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_CERTIFICATE_FILE));
				json.put(FIELD_CERTIFICATE_FILE + GeneralConstantsValetWeb.SPAN_ELEMENT, Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_CERTIFICATE_FILE));
				error = true;
			}

			if (!error) {

				IKeystoreFacade keyStoreFacade = KeystoreFactory.getKeystoreInstance(Long.valueOf(idKeystore));
				certificateFileBytes = certificateFile.getBytes();
				// se obtiene X509Certificate
				X509Certificate certToAdd = UtilsCertificate.getX509Certificate(certificateFileBytes);

				// Lo añade al keystore.
				keyStoreFacade.storeCertificate(alias, certToAdd, null, null, validationCert.orElse(false));

				SystemCertificate newSystemCert = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateByAliasAndKeystoreId(alias, Long.valueOf(idKeystore));

				listCertificates.add(newSystemCert);
				dtOutput.setData(listCertificates);
				
				//recargamos caché
				CertificateCacheManager.getInstance().loadListCertificateCA();

			} 
			
			if(error){
				listCertificates = StreamSupport.stream(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(keystore).spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_SAVE_CERTIFICATE, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_SAVE_CERTIFICATE, Language.getResWebGeneral(WebGeneralMessages.ERROR_SAVE_CERTIFICATE_WEB));
			listCertificates = StreamSupport.stream(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(keystore).spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}
		dtOutput.setData(listCertificates);
		return dtOutput;
	}
	
	/**
	 * Method that download the selected system certificate.
	 *
	 * @param response Parameter that represents the response with information about file to download.
	 * @param idSystemCertificate Parameter that represents the identifier of system certificate.
	 * @throws IOException If the method fails.
	 */
	@RequestMapping(value = "/downloadcertificate", method = RequestMethod.GET, produces = CERT_CONTENT_TYPE)
	@ResponseBody
	public void downloadCertificate(HttpServletResponse response, @RequestParam("idSystemCertificate") Long idSystemCertificate) throws IOException {
		SystemCertificate systemCertificate = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateById(idSystemCertificate);
		byte[ ] certificateFile;
		String name = DEFAULT_CERTIFICATE_NAME;
		if (systemCertificate != null) {
			try {
				Long idKeystoreSelected = systemCertificate.getKeystore().getIdKeystore();
				IKeystoreFacade keystore = KeystoreFactory.getKeystoreInstance(idKeystoreSelected);
				Certificate cert = keystore.getCertificate(systemCertificate.getAlias());

				if (cert != null) {
					certificateFile = cert.getEncoded();

					InputStream in = new ByteArrayInputStream(certificateFile);
					response.setContentType(CERT_CONTENT_TYPE);
					response.setContentLength(certificateFile.length);
					response.setHeader("Content-Disposition", "attachment; filename=" + name);
					FileCopyUtils.copy(in, response.getOutputStream());
				}
			} catch (CertificateEncodingException e) {
				LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_DOWNLOAD_CERTIFICATE, new Object[ ] { e.getMessage() }));
			} catch (CryptographyException e) {
				LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_GET_CERTIFICATE, new Object[ ] { e.getMessage() }));
			}
		}
	}

	/**
	 * Method that updates a certificate.
	 *
	 * @param systemCertificateForm parameter that contains the form that allows updating the system certificate of a keystore.
	 * @return {@link DataTablesOutput<SystemCertificate>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updatesystemcertificate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<SystemCertificate> updateSystemCertificate(@RequestBody SystemCertificateForm systemCertificateForm) throws IOException {

		DataTablesOutput<SystemCertificate> dtOutput = new DataTablesOutput<SystemCertificate>();
		boolean error = false;

		JSONObject json = new JSONObject();
		List<SystemCertificate> listSystemCertificate = new ArrayList<SystemCertificate>();

		try {

			if (systemCertificateForm.getIdSystemCertificate() == null) {
				error = true;
			}
			// se comprueba el campo alias
			if (systemCertificateForm.getAlias() == null || systemCertificateForm.getAlias().isEmpty()) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_BLANK_ALIAS));
				json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, Language.getResWebGeneral(WebGeneralMessages.ERROR_NOT_BLANK_ALIAS));
				error = true;
			}
			if (!error) {
				// se comprueba que no contenga caracteres especiales
				String listChar = StaticValetConfig.getProperty(StaticValetConfig.LIST_CHARACTER_SPECIAL);
				if (!listChar.isEmpty()) {
					String[ ] characters = listChar.split(",");
					String res = UtilsStringChar.EMPTY_STRING;
					for (int i = 0; i < characters.length; i++) {
						int esta = systemCertificateForm.getAlias().indexOf(characters[i]);
						if (esta >= 0) {
							char special = systemCertificateForm.getAlias().charAt(esta);
							res += special + UtilsStringChar.SPECIAL_BLANK_SPACE_STRING;
						}
					}
					if (!res.isEmpty()) {
						LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_SPECIAL_CHAR_ALIAS, new Object[ ] { res, systemCertificateForm.getAlias() }));
						json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_SPECIAL_CHAR_ALIAS, new Object[ ] { res, systemCertificateForm.getAlias() }));
						error = true;
					}

				}
			}

			if (!error) {
				// obtengo el keystore
				IKeystoreFacade keyStoreFacade = KeystoreFactory.getKeystoreInstance(systemCertificateForm.getIdKeystore());
				ISystemCertificateService sysCerService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService();
				SystemCertificate oldCert = sysCerService.getSystemCertificateById(systemCertificateForm.getIdSystemCertificate());

				// Si el certificado pertenece al keystore OCSP actualizamos si es un certificado válido o no
				if(oldCert.getKeystore().getTokenName().equals(TOKEN_KEYSTORE19)) {
					oldCert.setValidationCert(systemCertificateForm.getValidationCert());
					iSystemCertificateService.saveSystemCertificate(oldCert);
				}
				
				if (!oldCert.getAlias().equals(systemCertificateForm.getAlias())) {
					// hay que comprobar que no exista en bbdd un alias igual
					// Comprobamos que no está incluido ese alias en el
					// keystore seleccionado
					boolean duplicate = false;

					List<SystemCertificate> certificates = (List<SystemCertificate>) sysCerService.getAllByKeystore(oldCert.getKeystore());

					if (certificates != null && !certificates.isEmpty()) {
						Iterator<SystemCertificate> certificatesIt = certificates.iterator();
						while (!duplicate && certificatesIt.hasNext()) {
							SystemCertificate certPojo = certificatesIt.next();
							if (certPojo != null && certPojo.getAlias() != null) {
								duplicate = certPojo.getAlias().equals(systemCertificateForm.getAlias());
							}
						}
					}
					if (!duplicate) {
						// Actualiza el alias del certificado
						keyStoreFacade.updateCertificateAlias(oldCert.getAlias(), systemCertificateForm.getAlias());

						// se añade a la lista de certificados,
						SystemCertificate newCert = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateByAliasAndKeystoreId(systemCertificateForm.getAlias(), systemCertificateForm.getIdKeystore());
						listSystemCertificate.add(newCert);
						dtOutput.setData(listSystemCertificate);
						
						
						//se actualiza la caché
						CertificateCacheManager.getInstance().loadListCertificateCA();

						
					}else{
						String msgError = Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_EXIST_ALIAS, new Object[ ] { systemCertificateForm.getAlias() });
						LOGGER.error(msgError);
						json.put(FIELD_ALIAS + GeneralConstantsValetWeb.SPAN_ELEMENT, msgError);
						error = true;
						
						
					}
				} else {
					// se deja la lista tal cual
					Keystore keystore = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(systemCertificateForm.getIdKeystore(), false);
					listSystemCertificate = StreamSupport.stream(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(keystore).spliterator(), false).collect(Collectors.toList());
				}

			} 
			
			if(error){
				// si ha ocurrido un error, se deja la lista de certificados
				// tal
				// y como estaba.
				Keystore keystore = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(systemCertificateForm.getIdKeystore(), false);
				listSystemCertificate = StreamSupport.stream(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(keystore).spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}
			

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_UPDATE_CERTIFICATE, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_UPDATE_CERTIFICATE, Language.getResWebGeneral(WebGeneralMessages.ERROR_UPDATE_CERTIFICATE_WEB));
			Keystore keystore = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(systemCertificateForm.getIdKeystore(), false);
			listSystemCertificate = StreamSupport.stream(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getAllByKeystore(keystore).spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}
		dtOutput.setData(listSystemCertificate);
		return dtOutput;
	}

	/**
	 * Method to remove a certificate.
	 * @param idSystemCertificate Parameter that represents ID of system certificate.
	 * @param index Parameter that represents the index of the row of the selected certificate.
	 * @return String that represents the index of the deleted row.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/deletecertificate", method = RequestMethod.POST)
	public String deleteCertificateById(@RequestParam(FIELD_ID_SYSTEM_CERTIFICATE) Long idSystemCertificate, @RequestParam(FIELD_ROW_INDEX_CERTIFICATE) String index) {

		String result = index;
		try {
			SystemCertificate systemCertificate = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateById(idSystemCertificate);
			Long idKeystore = systemCertificate.getKeystore().getIdKeystore();
			IKeystoreFacade keystoreFacade = KeystoreFactory.getKeystoreInstance(idKeystore);
			keystoreFacade.removeEntry(systemCertificate.getAlias());
			
			//se actualiza la caché
			CertificateCacheManager.getInstance().loadListCertificateCA();
		} catch (Exception e) {
			result = "-1";
		}
		return result;
	}

	@RequestMapping(value = "/keystoreRest/updateCertIsValid", method = RequestMethod.POST)
	public void updateCertIsValid(@RequestParam("idSystemCertificate") Long idSystemCertificate, @RequestParam("checkBox") boolean checkBox) {
		iExternalAccessService.searchCertAndUpdateIsValid(idSystemCertificate, checkBox);
	}
}
