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
 * <b>File:</b><p>es.gob.valet.rest.controller.CertificateValidationRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.rest.controller;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.gob.afirma.core.misc.Base64;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.form.CertificateValidationForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;
import es.gob.valet.utils.CertificateValidationConstants;

/**
 * <p>Class that manages the REST request related to the Certificate Validation interface.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@RestController
@RequestMapping("/validateCertificate")
public class CertificateValidationRestController {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(CertificateValidationRestController.class);

	/**
	 * Attribute that represents the class IValetServerService. 
	 */
	@Autowired
	private IValetServerService valetServerService;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Constant that represents the endpoint of the certificate validation in the VALET services.
	 */
	private static final String VAL_URL = "rest/tsl/detectCertInTslInfoAndValidation";

	/**
	 * Handles the validation request for an advanced digital certificate.
	 * <p>
	 * This endpoint accepts a form containing the certificate and other optional
	 * parameters for advanced validation. These may include TSL location, custom
	 * validation date, and revocation evidence preferences.
	 * </p>
	 *
	 * @param form the form containing the certificate and optional advanced parameters
	 * @return a {@link ResponseEntity} containing the validation result or an error message
	 */
	@PostMapping("/advanced")
	public ResponseEntity<?> validateAdvancedCertificate(@ModelAttribute CertificateValidationForm form) {
		return processCertificateValidation(form, true);
	}

	/**
	 * Handles the validation request for a simple digital certificate.
	 * <p>
	 * This endpoint accepts a form with the certificate to be validated. It uses
	 * a simplified validation process with default revocation and chain settings.
	 * </p>
	 *
	 * @param form the form containing the certificate to validate
	 * @return a {@link ResponseEntity} containing the validation result or an error message
	 */
	@PostMapping("/simple")
	public ResponseEntity<?> validateSimpleCertificate(@ModelAttribute CertificateValidationForm form) {
		return processCertificateValidation(form, false);
	}

	/**
	 * Internal method to process certificate validation requests.
	 * <p>
	 * This method builds a request to an external ValET server, including
	 * encoding the certificate in Base64 and attaching any necessary parameters
	 * for either simple or advanced validation. It supports configuration of
	 * revocation checks, certificate chain retrieval, TSL location, detection date,
	 * and more, depending on the type of request.
	 * </p>
	 *
	 * @param form       the form with certificate data and validation options
	 * @param isAdvanced flag indicating whether advanced validation is required
	 * @return a {@link ResponseEntity} with the response from the ValET server,
	 *         or an error message in case of failure
	 */
	private ResponseEntity<?> processCertificateValidation(CertificateValidationForm form, boolean isAdvanced) {
		try {
			//Obtenemos el objeto ValetServer
			ValetServer valetServer = valetServerService.findById(form.getValetServerId()).get();
			if (valetServer == null) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.LOG_VCR002));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Language.getResWebGeneral(WebGeneralMessages.LOG_VCR002));
			}

			String endpointUrl = valetServer.getServerUrl();
			if (!endpointUrl.endsWith("/")) {
				endpointUrl += "/";
			}
			endpointUrl += VAL_URL;

			// Conversion de certificado a Base64
			byte[] certBytes = form.getCertFile().getBytes();
			String base64Cert = Base64.encode(certBytes);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			
			// PARAMETROS BASICOS
			params.add(CertificateValidationConstants.PARAM_APPLICATION, valetServer.getApplication());
			params.add(CertificateValidationConstants.PARAM_CERTIFICATE, base64Cert);
			
			// PARAMETRO de Obtener mapeos
			params.add(CertificateValidationConstants.PARAM_GET_INFO, String.valueOf(form.isFetchMappings()));
			
			DateTimeFormatter inputFormatter  = DateTimeFormatter.ofPattern(UtilsDate.FORMAT_DATE_TIME_CRL_ISSUE_TIME);
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(UtilsDate.FORMAT_DATE_TIME_JSON);
			
			// PARAMETROS AVANZADOS
			if (isAdvanced) {
				// params.add("delegatedApp", "");
				// params.add("crlsByteArray", "");
				// params.add("basicOcspResponsesByteArray", "");
				
				// PARAMETRO de Ubicacion de TSLs
				if (form.getTslLocation() != null && !form.getTslLocation().isEmpty()) {
					params.add(CertificateValidationConstants.PARAM_TSL_LOCATION, form.getTslLocation() != null ? form.getTslLocation() : "");
				}
				
				// PARAMETRO de Fecha de validacion
				if (form.getDetectionDate() != null && !form.getDetectionDate().isEmpty()) {
			        // 1) Parse incoming string sin segundos ni offset
			        LocalDateTime localDt = LocalDateTime.parse(form.getDetectionDate(), inputFormatter);
			        // 2) Agregamos un offset
			        OffsetDateTime offsetDt = localDt.atOffset(ZoneOffset.UTC);
			        // 3) Formateamos con milisegundos y offset
			        String formattedDate = offsetDt.format(outputFormatter);
			        params.add(CertificateValidationConstants.PARAM_DETECTION_DATE, formattedDate);
			    }
				
				// PARAMETRO de Obtener Cadena de Certificacion
				params.add(CertificateValidationConstants.PARAM_RETURN_CERT_CHAIN, String.valueOf(form.isReturnCertificateChain()));
				
				// PARAMETRO de Obtener Estado Revocacion
				params.add(CertificateValidationConstants.PARAM_CHECK_REVOCATION_STATUS, String.valueOf(form.isCheckRevocationStatus()));
				
				// PARAMETRO de Obtener Evidencia de Revocacion
				params.add(CertificateValidationConstants.PARAM_RETURN_REVOCATION_EVIDENCE, String.valueOf(form.isReturnRevocationEvidence()));
			} else {
				// PARAMETRO de Obtener Estado Revocacion y Obtener Evidencia de Revocacion
				//En caso de no estar en Formulario avanzado se marca el valor a false siempre
				params.add(CertificateValidationConstants.PARAM_CHECK_REVOCATION_STATUS, CertificateValidationConstants.FALSE_VALUE);
				params.add(CertificateValidationConstants.PARAM_RETURN_REVOCATION_EVIDENCE, CertificateValidationConstants.FALSE_VALUE);
				params.add(CertificateValidationConstants.PARAM_RETURN_CERT_CHAIN, CertificateValidationConstants.FALSE_VALUE);
			}
			
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestEntity, String.class);
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VCR001, e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VCR001, e.getMessage()));
		}
	}
}