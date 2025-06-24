package es.gob.valet.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.service.ifaces.ITslPendValService;

@RestController
public class TslPendValRestController {

	@Autowired
	private ITslPendValService iTslPendValService;
	
	@RequestMapping(path = "/tslpendvaldatatable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TslPendValDTO> tslPendValDatatable() {
		List<TslPendValDTO> listTslPendValDTO = iTslPendValService.obtainAllTslPendVal();
		return listTslPendValDTO;
	}

	@RequestMapping(path = "/confirmtslpendval", method = RequestMethod.POST)
	public void confirmTslPendVal(@RequestParam("idTslPendVal") Long idTslPendVal) {
		iTslPendValService.confirmTslPendVal(idTslPendVal);
	}
	
	@RequestMapping(path = "/confirmdeclinetslpendval", method = RequestMethod.POST)
	public void confirmDeclineTslPendVal(@RequestParam("idTslPendVal") Long idTslPendVal) {
		iTslPendValService.declineTslPendVal(idTslPendVal);
	}
}
