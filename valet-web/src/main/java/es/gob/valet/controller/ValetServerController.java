package es.gob.valet.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.form.ValetServerForm;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

@Controller
public class ValetServerController {
	
	@Autowired
	private IValetServerService valetServerService;

	@GetMapping("/addValetServer")
	public String loadAddValetServer(Model model) {
		ValetServerForm valetServerForm = new ValetServerForm();
		
		model.addAttribute("valetServerForm", valetServerForm);
		
		return "modal/valetServer/addForm.html";
	}
	
	@GetMapping("/editValetServer")
	public String loadEditValetServer(@RequestParam Long id, Model model) {
		Optional<ValetServer> opt = valetServerService.findById(id);
		
		ValetServerForm valetServerForm;
		
		if (opt.isPresent()) {
			valetServerForm = new ValetServerForm(opt.get());
		} else {
			valetServerForm = new ValetServerForm();
		}
		
		model.addAttribute("valetServerForm", valetServerForm);
		
		return "modal/valetServer/editForm.html";
	}
	
	@GetMapping("/deleteValetServer")
	public String loadDeleteValetServer(@RequestParam Long id, Model model) {
		Optional<ValetServer> opt = valetServerService.findById(id);
		
		ValetServerForm valetServerForm;
		
		if (opt.isPresent()) {
			valetServerForm = new ValetServerForm(opt.get());
		} else {
			valetServerForm = new ValetServerForm();
		}
		
		model.addAttribute("valetServerForm", valetServerForm);
		
		return "modal/valetServer/deleteForm.html";
	}
}
