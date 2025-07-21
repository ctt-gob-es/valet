package es.gob.valet.rest.controller;

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
import es.gob.valet.form.CertificateValidationForm;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;


@RestController
@RequestMapping("/validateCertificate")
public class CertificateValidationRestController {

	@Autowired
	private IValetServerService valetServerService;

	@Autowired
	private RestTemplate restTemplate;

	private static final String VAL_URL = "rest/tsl/detectCertInTslInfoAndValidation";

	@PostMapping("/advanced")
	public ResponseEntity<?> validateAdvancedCertificate(@ModelAttribute CertificateValidationForm form) {
		return processCertificateValidation(form, true);
	}

	@PostMapping("/simple")
	public ResponseEntity<?> validateSimpleCertificate(@ModelAttribute CertificateValidationForm form) {
		return processCertificateValidation(form, false);
	}

	private ResponseEntity<?> processCertificateValidation(CertificateValidationForm form, boolean isAdvanced) {
		try {
			//Obtenemos el objeto ValetServer
			ValetServer valetServer = valetServerService.findById(form.getValetServerId()).get();
			if (valetServer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor ValET no encontrado.");
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
			params.add("application", valetServer.getApplication());
			params.add("certificate", base64Cert);
			
			// PARAMETRO de Obtener mapeos
			params.add("getInfo", String.valueOf(form.isFetchMappings()));
			
			// PARAMETROS AVANZADOS
			if (isAdvanced) {
				// params.add("delegatedApp", "");
				// params.add("crlsByteArray", "");
				// params.add("basicOcspResponsesByteArray", "");
				
				// PARAMETRO de Ubicacion de TSLs
				if (form.getTslLocation() != null && !form.getTslLocation().isEmpty()) {
					params.add("tslLocation", form.getTslLocation() != null ? form.getTslLocation() : "");
				}
				
				// PARAMETRO de Fecha de validacion
				if (form.getDetectionDate() != null && !form.getDetectionDate().isEmpty()) {
					params.add("detectionDate", form.getDetectionDate() != null ? form.getDetectionDate() : "");
				}
				
				// PARAMETRO de Obtener Cadena de Certificacion
				if (form.isReturnCertificateChain()) {
					params.add("returnCertificateChain", String.valueOf(form.isReturnCertificateChain()));
				}
				
				// PARAMETRO de Obtener Estado Revocacion
				if (form.isCheckRevocationStatus()) {
					params.add("checkRevocationStatus", String.valueOf(form.isCheckRevocationStatus()));
				}
				
				// PARAMETRO de Obtener Evidencia de Revocacion
				if (form.isReturnRevocationEvidence()) {
					params.add("returnRevocationEvidence", String.valueOf(form.isReturnRevocationEvidence()));
				}
			} else {
				// PARAMETRO de Obtener Estado Revocacion y Obtener Evidencia de Revocacion
				//En caso de no estar en Formulario avanzado se marca el valor a true siempre
				params.add("checkRevocationStatus", "true");
				params.add("returnRevocationEvidence", "true");
				params.add("returnCertificateChain", "true");
			}
			
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestEntity, String.class);
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error procesando la validacion: " + e.getMessage());
		}
	}
}