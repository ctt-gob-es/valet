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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18 sept. 2018.
 */
package es.gob.valet.controller;

import java.io.IOException;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.keystore.IKeystoreFacade;
import es.gob.valet.crypto.keystore.KeystoreFacade;
import es.gob.valet.form.SystemCertificateForm;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;
import es.gob.valet.persistence.configuration.services.ifaces.ISystemCertificateService;

/** 
 * <p>Class that manages the requests related to the Keystore administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18 sept. 2018.
 */
@Controller
public class KeystoreController {
	
	/**
	 * Constant that represents the parameter 'idTslValet'.
	 */
	private static final String FIELD_ID_KEYSTORE = "idKeystore";
	
	/**
	 * Constant that represents the parameter 'idSystemCertificate'.
	 */
	private static final String FIELD_ID_SYSTEM_CERTIFICATE = "idSystemCertificate";
	
	/**
	 * Attribute that represents the service object for acceding to KeystoreRepository.
	 */
	@Autowired
	private IKeystoreService keystoreService;
	
	
	
	/**
	 * Attribute that represents the service object for acceding to SystemCertificateRepository.
	 */
	@Autowired
	private ISystemCertificateService systemCertificateService;
	/**
	 * Method that load the list of certificates stored in the specified keystore. 
	 * 
	 * @param idKeystore Parameter that represents ID of kestore.
	 * @param model Holder object form model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value="/loadcertificatesdatatable", method = RequestMethod.GET)
	public String loadCertificatesDatatable(@RequestParam(FIELD_ID_KEYSTORE) Long idKeystore, Model model){
		SystemCertificateForm systemCertificateForm = new SystemCertificateForm();
		systemCertificateForm.setIdKeystore(idKeystore);
		systemCertificateForm.setNameKeystore(keystoreService.getNameKeystoreById(idKeystore));
		model.addAttribute("keystoreform", systemCertificateForm);
		return "fragments/keystoreadmin.html";
		
	}
	
	/**
	 * Method that maps the add TSL web request to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 * @throws IOException 
	 */

	@RequestMapping(value = "/addcertificate")
	public String addCertificate(Model model) throws IOException {
		SystemCertificateForm systemCertificateForm = new SystemCertificateForm();	
		model.addAttribute("addcertificateform", systemCertificateForm);			
		return "modal/keystore/systemCertificateForm.html";
	}
	
	
	/**
	 * Method that maps the editing of system certificate to the controller and sets the backing form.
	 * @param idSystemCertificate Parameter that represetns ID of system certificate.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadeditcertificate",  method = RequestMethod.GET)
	public String editCertificate(@RequestParam("id") Long idSystemCertificate, Model model) {
		SystemCertificate certificateToEdit = systemCertificateService.getSystemCertificateById(idSystemCertificate);
		SystemCertificateForm certificateForm = new SystemCertificateForm();
		//obtenemos el certificado
		Long idKeystoreSelected = certificateToEdit.getKeystore().getIdKeystore();
		IKeystoreFacade keystore = new KeystoreFacade(keystoreService.getKeystoreById(Long.valueOf(idKeystoreSelected)));
		try {
			X509Certificate cert = keystore.getCertificate(certificateToEdit.getAlias());
			//se obtiene las fechas hasta y desde
			certificateForm.setValidFrom(UtilsCertificate.getValidFrom(cert));
			certificateForm.setValidTo(UtilsCertificate.getValidTo(cert));
		} catch (CryptographyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		certificateForm.setAlias(certificateToEdit.getAlias());
		certificateForm.setSubject(certificateToEdit.getSubject());
		certificateForm.setIssuer(certificateToEdit.getIssuer());
		certificateForm.setIdKeystore(certificateToEdit.getKeystore().getIdKeystore());
		certificateForm.setIdSystemCertificate(idSystemCertificate);
		
		model.addAttribute("editcertificateform", certificateForm);
		return "modal/keystore/systemCertificateEditForm.html";
	}
	
	
	/**
	 *  Method that loads the necessary information to show the confirmation modal to remove a selected certificate.
	 * 
	 * @param idSystemCertificate Parameter that represetns ID of system certificate.
	 * @param rowIndexCertificate Parameter that represents the index of the row of the selected certificate.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadconfirmdeletecertificate",  method = RequestMethod.GET)
	public String deleteConfirmCertificate(@RequestParam(FIELD_ID_SYSTEM_CERTIFICATE) Long idSystemCertificate, @RequestParam("rowindex") String rowIndexCertificate, Model model){
		SystemCertificateForm certificateForm = new SystemCertificateForm();
		certificateForm.setIdSystemCertificate(idSystemCertificate);
		certificateForm.setRowIndexCertificate(rowIndexCertificate);
		
		model.addAttribute("deletecertificateform", certificateForm);
		return "modal/keystore/systemCertificateDelete.html";
	}

}
