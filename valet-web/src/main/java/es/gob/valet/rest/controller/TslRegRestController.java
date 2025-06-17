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
 * <b>File:</b><p>es.gob.valet.rest.controller.TslRegRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the configuration register TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/06/2025.
 */
package es.gob.valet.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.persistence.configuration.model.dto.ConfTslRegDTO;
import es.gob.valet.service.ifaces.IConfTslRegService;

/**
 * <p>Class that manages the REST request related to the configuration register TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/06/2025.
 */
@RestController
public class TslRegRestController {
	
	/**
	 * Service for managing the TSL registration configuration.
	 */
	@Autowired
	private IConfTslRegService iConfTslRegService;

	/**
	 * Handles HTTP POST requests to save the TSL registration configuration.
	 *
	 * <p>Consumes a JSON representation of {@link ConfTslRegDTO} from the request body
	 * and delegates the save operation to the service layer.</p>
	 *
	 * @param confTslRegDTO the configuration DTO to be saved
	 */
	@RequestMapping(value = "/saveConfTslReg", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void saveConfTslReg(@RequestBody ConfTslRegDTO confTslRegDTO) {
	    iConfTslRegService.saveConfTslReg(confTslRegDTO);
	}

}
