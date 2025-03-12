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
 * <b>File:</b><p>es.gob.valet.controller.SigningCertController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the configuration of the Signing Certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/03/2025.
 */
package es.gob.valet.controller;

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
 * <p>Class that manages the requests related to the configuration of the Signing Certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/03/2025.
 */
@Controller
public class SigningCertController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(SigningCertController.class);
	
	/**
	 * Service for handling operations related to signing certificates.
	 * 
	 * This service is injected by Spring and provides methods for managing and retrieving
	 * signing certificates.
	 */
	@Autowired
	private ISigningCertService iSigningCertService;
	
	/**
     * Loads the signing certificate details and adds them to the model.
     * 
     * @param model The Spring Model object to hold attributes for the view.
     * @return The name of the view template to render, in this case "fragments/signingcertadmin.html".
     */
    @RequestMapping(value = "/loadSigningCert", method = RequestMethod.GET)
    public String loadSigningCert(final Model model) {
        SigningCertificateDTO signingCertificateDTO = null; 
        try {
            signingCertificateDTO = iSigningCertService.obtainSigningCertificate();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        model.addAttribute("signingCertificateDTO", signingCertificateDTO);
        return "fragments/signingcertadmin.html";
    }

    /**
     * Displays the form to add a new signing certificate.
     * 
     * @param model The Spring Model object to hold attributes for the view.
     * @return The name of the view template to render, in this case "modal/configuration/addsigningcert.html".
     */
    @RequestMapping(value = "/viewAddSigningCert", method = RequestMethod.POST)
    public String viewAddSigningCert(final Model model) {
        model.addAttribute("signingCertificateDTO", new SigningCertificateDTO());
        return "modal/configuration/addsigningcert.html";
    }

    /**
     * Displays the confirmation modal to delete a signing certificate.
     * 
     * @param model The Spring Model object to hold attributes for the view.
     * @return The name of the view template to render, in this case "modal/configuration/deletesigningcert.html".
     */
    @RequestMapping(value = "/viewDeleteSigningCert", method = RequestMethod.POST)
    public String viewDeleteSigningCert(final Model model) {
        return "modal/configuration/deletesigningcert.html";
    }
}
