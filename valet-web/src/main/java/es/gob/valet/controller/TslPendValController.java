package es.gob.valet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.service.ifaces.ITslPendValService;

@Controller
public class TslPendValController {
	
	@Autowired
	private ITslPendValService iTslPendValService;
	
	@RequestMapping(value = "tslpendvaladmin", method = RequestMethod.GET)
	public String tslPendValAdmin(Model model) {
		return "fragments/tslpendvaladmin.html";
	}

	@RequestMapping(value = "viewinfotsl", method = RequestMethod.POST)
	public String viewInfoTsl(@RequestParam("idTslPendVal") Long idTslPendVal,  Model model) {
		List<TslPendValDTO> listTslPendValDTO = iTslPendValService.obtainAllTslPendVal();
		TslPendValDTO tslPendValDTO = listTslPendValDTO.stream().filter(p -> p.getIdTslPendVal() == idTslPendVal).findAny().orElse(null);
		model.addAttribute("tslPendValDTO", tslPendValDTO);
		return "modal/tslPendVal/infoTslPendVal";
	}
	
	@RequestMapping(value = "declinetslpendval", method = RequestMethod.GET)
	public String declineTslPendVal(Model model) {
		return "modal/tslPendVal/declinetslpendval.html";
	}
}
