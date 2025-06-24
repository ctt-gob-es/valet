package es.gob.valet.service.ifaces;

import java.util.List;

import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;

public interface ITslPendValService {

	List<TslPendValDTO> obtainAllTslPendVal();

	void confirmTslPendVal(Long idTslPendVal);

	void declineTslPendVal(Long idTslPendVal);
	

}
