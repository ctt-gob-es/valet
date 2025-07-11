package es.gob.valet.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import es.gob.afirma.core.misc.Base64;
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

	@PostMapping("/simple")
	public ResponseEntity<?> validateSimpleCertificate(
	        @RequestParam("valetServerSelect") Long valetServerId,
	        @RequestParam("certFile") MultipartFile certFile,
	        @RequestParam(value = "fetchMappings", required = false, defaultValue = "false") boolean fetchMappings) {

	    return processCertificateValidation(valetServerId, certFile, false, fetchMappings);
	}

	@PostMapping("/advanced")
	public ResponseEntity<?> validateAdvancedCertificate(
	        @RequestParam("valetServerId") Long valetServerId,
	        @RequestParam("certFile") MultipartFile certFile) {

	    return processCertificateValidation(valetServerId, certFile, true, true); // Se asume que avanzada siempre activa flags
	}

	private ResponseEntity<?> processCertificateValidation(Long valetServerId, MultipartFile certFile, boolean advancedMode, boolean fetchMappings) {
	    try {
	        ValetServer valetServer = valetServerService.findById(valetServerId).get();
	        if (valetServer == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor ValET no encontrado.");
	        }

	        String endpointUrl = valetServer.getServerUrl();
	        if (!endpointUrl.endsWith("/")) {
	            endpointUrl += "/";
	        }
	        endpointUrl += VAL_URL;

	        byte[] certBytes = certFile.getBytes();
	        String base64Cert = Base64.encode(certBytes);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
	        formParams.add("application", valetServer.getApplication());
	        formParams.add("delegatedApp", ""); // opcional
	        formParams.add("tslLocation", ""); // opcional
	        formParams.add("certificate", base64Cert);
	        formParams.add("detectionDate", ""); // opcional, usar formato dd/MM/yyyy HH:mm:ss si lo deseas
	        formParams.add("getInfo", "true");
	        formParams.add("checkRevocationStatus", "true");
	        formParams.add("returnRevocationEvidence", "true");
	        formParams.add("crlsByteArray", ""); // opcional
	        formParams.add("basicOcspResponsesByteArray", ""); // opcional
	        formParams.add("returnCertificateChain", "");

	        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formParams, headers);

	        ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestEntity, String.class);
	        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Error procesando la validacion: " + e.getMessage());
	    }
	}


}