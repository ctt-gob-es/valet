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
 * <b>File:</b><p>es.gob.valet.controller.CertificateValidationController.java.</p>
 * <b>Description:</b><p>Class that manages the request related to the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.gob.valet.form.CertificateValidationForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;

/** 
 * <p>Class that manages the request related to the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@Controller
public class CertificateValidationController {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(CertificateValidationController.class);
	
	/**
	 * Loads the certificate validation form view.
	 * <p>
	 * Initializes a new {@link CertificateValidationForm} and attaches it to the model.
	 * Returns the path to the HTML fragment for the certificate validation form.
	 * </p>
	 *
	 * @param model the Spring {@link Model} used to pass attributes to the view
	 * @return the path to the certificate validation admin HTML fragment
	 */
	@GetMapping("/loadCertificateValidation")
	public String loadCertificateValidation(Model model) {
		try {
			CertificateValidationForm certificateValidationForm = new CertificateValidationForm();
			model.addAttribute("certificateValidationForm", certificateValidationForm);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VCR003, e.getMessage()), e);
		}
		return "fragments/certificateValidation/admin.html";
	}

	/**
	 * Loads the view displaying the response of a certificate validation.
	 * <p>
	 * This view is typically used to render the result of a certificate validation
	 * request, such as success or error details.
	 * </p>
	 *
	 * @param model the Spring {@link Model} used to pass attributes to the view
	 * @return the path to the certificate validation response HTML fragment
	 */
	@GetMapping("/loadCertificateValidationResponse")
	public String loadCertificateValidationResponse(Model model) {
		return "fragments/certificateValidation/validationResponse.html";
	}

}
