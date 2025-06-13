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
 * <b>File:</b><p>es.gob.valet.controller.ImporTslsController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the Import Tsls administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.2, 29.05/2025.
 */
package es.gob.valet.importsls;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;
import es.gob.valet.service.ifaces.ISigningCertService;

/** 
 * <p>Class that maps the request for the import tsls form to the controller.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 29.05/2025.
 */
@Controller
public class ImporTslsController {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ImporTslsController.class);
	
	@Autowired
	private ISigningCertService iSigningCertService;
	
	/**
	 * Handles GET requests for "importslsadmin".
	 *
	 * @param model The {@link Model} to pass attributes to the view.
	 * @return The "fragments/importslsadmin.html" view.
	 */
	@RequestMapping(value = "importslsadmin", method = RequestMethod.GET)
	public String importslsadmin(final Model model) {
		return "fragments/importslsadmin.html";
	}
	
	/**
	 * Handles the request to view the information of the signing certificate.
	 *
	 * This method retrieves the signing certificate details using the service layer
	 * and adds them to the model to be displayed in the corresponding view.
	 *
	 * @param model The {@link Model} object used to pass attributes to the view.
	 * @return The path to the view template displaying the signing certificate information.
	 */
	@RequestMapping(value = "viewInfoSigningCert", method = RequestMethod.POST)
	public String viewInfoSigningCert(final Model model, HttpSession httpSession) {
		
		// Obtenemos el firmante del zip a partir de la sesion
		X509Certificate signingCertificate = (X509Certificate) httpSession.getAttribute("signingCertificate");
		
	    SigningCertificateDTO signingCertificateDTO = null; 
	    try {
	        signingCertificateDTO = iSigningCertService.obtainSigningCertificate(signingCertificate);
	    } catch (Exception e) {
	        LOGGER.error(e);
	    }
	    model.addAttribute("signingCertificateDTO", signingCertificateDTO);
	    return "modal/import/viewsigningcert.html";
	}

	@RequestMapping(value = "viewimportmanager", method = RequestMethod.GET)
	public String viewimportmanager(final Model model) {
		return "modal/import/importmanager.html";
	}
}
