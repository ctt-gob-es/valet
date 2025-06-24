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
 * <b>File:</b><p>es.gob.valet.rest.controller.TslPendValRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the TSL Pending Validation administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/06/2025.
 */
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

/**
 * <p>Class that manages the REST request related to the TSL Pending Validation administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 29.05/2025.
 */
@RestController
public class TslPendValRestController {

	/**
	 * Service interface for managing pending TSL validations.
	 */
	@Autowired
	private ITslPendValService iTslPendValService;

	/**
	 * Returns a JSON list of all pending TSL validation DTOs for the datatable.
	 * 
	 * @return List of TslPendValDTO representing all pending TSL validations.
	 */
	@RequestMapping(path = "/tslpendvaldatatable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TslPendValDTO> tslPendValDatatable() {
	    List<TslPendValDTO> listTslPendValDTO = iTslPendValService.obtainAllTslPendVal();
	    return listTslPendValDTO;
	}

	/**
	 * Confirms the pending TSL validation identified by the given ID.
	 * 
	 * @param idTslPendVal The ID of the pending TSL validation to confirm.
	 */
	@RequestMapping(path = "/confirmtslpendval", method = RequestMethod.POST)
	public void confirmTslPendVal(@RequestParam("idTslPendVal") Long idTslPendVal) {
	    iTslPendValService.confirmTslPendVal(idTslPendVal);
	}

	/**
	 * Declines the pending TSL validation identified by the given ID.
	 * 
	 * @param idTslPendVal The ID of the pending TSL validation to decline.
	 */
	@RequestMapping(path = "/confirmdeclinetslpendval", method = RequestMethod.POST)
	public void confirmDeclineTslPendVal(@RequestParam("idTslPendVal") Long idTslPendVal) {
	    iTslPendValService.declineTslPendVal(idTslPendVal);
	}

}
