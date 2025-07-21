package es.gob.valet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.gob.valet.form.CertificateValidationForm;

@Controller
public class CertificateValidationController {

	@GetMapping("/loadCertificateValidation")
	public String loadCertificateValidation(Model model) {
		CertificateValidationForm certificateValidationForm = new CertificateValidationForm();
		
		model.addAttribute("certificateValidationForm", certificateValidationForm);
		
		return "fragments/certificateValidation/admin.html";
	}
	
	@GetMapping("/loadCertificateValidationResponse")
	public String loadCertificateValidationResponse(Model model) {
		return "fragments/certificateValidation/validationResponse.html";
	}
}
