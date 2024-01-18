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
 * <b>File:</b><p>es.gob.valet.controller.KeystoreController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the Keystore administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.7, 16/01/2024.
 */
package es.gob.valet.controller;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.form.SystemCertificateForm;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;
import es.gob.valet.persistence.configuration.services.ifaces.ISystemCertificateService;
import es.gob.valet.persistence.exceptions.CryptographyException;

/**
 * <p>
 * Class that manages the requests related to the Keystore administration.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.7, 16/01/2024.
 */
@Controller
public class KeystoreController {

	/**
	 * Constant that represents the parameter 'idKeystore'.
	 */
	private static final String FIELD_ID_KEYSTORE = "idKeystore";

	/**
	 * Constant that represents the parameter 'idSystemCertificate'.
	 */
	private static final String FIELD_ID_SYSTEM_CERTIFICATE = "idSystemCertificate";

	/**
	 * Method that load the list of certificates stored in the specified
	 * keystore.
	 *
	 * @param idKeystore
	 *            Parameter that represents ID of kestore.
	 * @param model
	 *            Holder object form model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadcertificatesdatatable", method = RequestMethod.GET)
	public String loadCertificatesDatatable(@RequestParam(FIELD_ID_KEYSTORE) Long idKeystore, Model model) {

		SystemCertificateForm systemCertificateForm = new SystemCertificateForm();
		systemCertificateForm.setIdKeystore(idKeystore);
		IKeystoreService keystoreService = ManagerPersistenceServices.getInstance()
				.getManagerPersistenceConfigurationServices().getKeystoreService();
		systemCertificateForm.setNameKeystore(keystoreService.getNameKeystoreById(idKeystore));
		model.addAttribute("keystoreform", systemCertificateForm);
		return "fragments/keystoreadmin.html";

	}

	/**
	 * Method that maps the add TSL web request to the controller and sets the
	 * backing form.
	 * 
	 * @param model
	 *            Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 * @throws IOException
	 *             If the method fails.
	 */

	@RequestMapping(value = "/addcertificate")
	public String addCertificate(Model model) throws IOException {
		SystemCertificateForm systemCertificateForm = new SystemCertificateForm();
		model.addAttribute("addcertificateform", systemCertificateForm);
		return "modal/keystore/systemCertificateForm.html";
	}

	/**
	 * Method that maps the editing of system certificate to the controller and
	 * sets the backing form.
	 * 
	 * @param idSystemCertificate
	 *            Parameter that represetns ID of system certificate.
	 * @param model
	 *            Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadeditcertificate", method = RequestMethod.GET)
	public String editCertificate(@RequestParam("id") Long idSystemCertificate, Model model) {
		ISystemCertificateService systemCertificateService = ManagerPersistenceServices.getInstance()
				.getManagerPersistenceConfigurationServices().getSystemCertificateService();
		SystemCertificate certificateToEdit = systemCertificateService.getSystemCertificateById(idSystemCertificate);
		SystemCertificateForm certificateForm = new SystemCertificateForm();
		// obtenemos el certificado
		try {
			Long idKeystoreSelected = certificateToEdit.getKeystore().getIdKeystore();

			// obtengo el keystore
			Keystore ksEntity = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(String.valueOf(idKeystoreSelected));
			Certificate certificate =ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getCertificate(certificateToEdit.getAlias(), ksEntity);
			if (certificate != null) {
				X509Certificate cert = UtilsCertificate.getX509Certificate(certificate.getEncoded());
				// se obtiene las fechas hasta y desde
				certificateForm.setValidFrom(UtilsCertificate.getValidFrom(cert));
				certificateForm.setValidTo(UtilsCertificate.getValidTo(cert));
			}

		} catch (CryptographyException | CertificateEncodingException | CommonUtilsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		certificateForm.setAlias(certificateToEdit.getAlias());
		certificateForm.setSubject(certificateToEdit.getSubject());
		certificateForm.setIssuer(certificateToEdit.getIssuer());
		certificateForm.setIdKeystore(certificateToEdit.getKeystore().getIdKeystore());
		certificateForm.setIdSystemCertificate(idSystemCertificate);
		certificateForm.setCountry(certificateToEdit.getCountry());
		certificateForm.setValidationCert(certificateToEdit.getValidationCert());
		
		model.addAttribute("editcertificateform", certificateForm);
		return "modal/keystore/systemCertificateEditForm.html";
	}

	/**
	 * Method that loads the necessary information to show the confirmation
	 * modal to remove a selected certificate.
	 * 
	 * @param idSystemCertificate
	 *            Parameter that represetns ID of system certificate.
	 * @param rowIndexCertificate
	 *            Parameter that represents the index of the row of the selected
	 *            certificate.
	 * @param model
	 *            Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadconfirmdeletecertificate", method = RequestMethod.GET)
	public String deleteConfirmCertificate(@RequestParam(FIELD_ID_SYSTEM_CERTIFICATE) Long idSystemCertificate,
			@RequestParam("rowindex") String rowIndexCertificate, Model model) {
		SystemCertificateForm certificateForm = new SystemCertificateForm();
		certificateForm.setIdSystemCertificate(idSystemCertificate);
		certificateForm.setRowIndexCertificate(rowIndexCertificate);
		model.addAttribute("deletecertificateform", certificateForm);
		return "modal/keystore/systemCertificateDelete.html";
	}

}
