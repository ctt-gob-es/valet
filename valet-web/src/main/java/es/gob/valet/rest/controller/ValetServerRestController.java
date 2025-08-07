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
 * <b>File:</b><p>es.gob.valet.rest.controller.ValetServerRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the ValetServer administration and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.rest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.form.ValetServerForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

/**
 * <p>Class that manages the REST request related to the ValetServer and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@RestController
@RequestMapping("/valetServer")
public class ValetServerRestController {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ValetServerRestController.class);
	
	/**
	 * Attribute that represents the class IValetServerService. 
	 */
	@Autowired
	private IValetServerService valetServerService;
	
	/**
	 * Creates a new ValetServer entity.
	 * <p>
	 * This endpoint receives a JSON payload with the server details and persists it.
	 * The ID is explicitly set to null to ensure a new entity is created.
	 * </p>
	 *
	 * @param serverForm the form containing the details of the ValetServer to create
	 * @return a {@link ResponseEntity} containing the created ValetServer
	 */
	@PostMapping
	public ResponseEntity<ValetServer> createValetServer(@RequestBody ValetServerForm serverForm) {
		try {
			ValetServer entity = serverForm.toEntity();
			entity.setIdValetServer(null);
			ValetServer saved = valetServerService.save(entity);
			return ResponseEntity.ok(saved);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE001, e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Retrieves a ValetServer by its ID.
	 * <p>
	 * Returns the server if it exists, or a 404 Not Found status otherwise.
	 * </p>
	 *
	 * @param id the ID of the ValetServer to retrieve
	 * @return a {@link ResponseEntity} with the ValetServer or 404 if not found
	 */
	@GetMapping("/{id}")
    public ResponseEntity<ValetServer> findById(@PathVariable Long id) {
		try {
			return valetServerService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> {
					LOGGER.warn(Language.getResWebGeneral(WebGeneralMessages.LOG_VCR002));
					return ResponseEntity.notFound().build();
				});
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE002, id.toString(), e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

	/**
	 * Retrieves all existing ValetServer entities.
	 * <p>
	 * Returns a list of all ValetServer instances persisted in the system.
	 * </p>
	 *
	 * @return a {@link ResponseEntity} with a list of all ValetServers
	 */
    @GetMapping
    public ResponseEntity<List<ValetServer>> findAll() {
    	try {
    		return ResponseEntity.ok(valetServerService.findAll());
    	} catch (Exception e) {
    		LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE003, e.getMessage()), e);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    /**
     * Deletes a ValetServer by its ID.
     * <p>
     * If the ID is missing or invalid, returns a 400 Bad Request. Otherwise,
     * deletes the server and returns 204 No Content.
     * </p>
     *
     * @param id the ID of the ValetServer to delete
     * @return a {@link ResponseEntity} indicating the outcome of the operation
     */
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(required = true) Long id) {
    	if (id == null) {
    		LOGGER.warn(Language.getResWebGeneral(WebGeneralMessages.LOG_VSE004));
    		return ResponseEntity.badRequest().build();
    	}
    	try {
    		valetServerService.deleteById(id);
    		return ResponseEntity.noContent().build();
    	} catch (Exception e) {
    		LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE005, id.toString(), e.getMessage()), e);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    /**
     * Updates an existing ValetServer entity.
     * <p>
     * If the provided ID does not match any existing entity, returns 404 Not Found.
     * Otherwise, updates the entity with the data from the form.
     * </p>
     *
     * @param serverForm the form containing the updated ValetServer details
     * @return a {@link ResponseEntity} with the updated ValetServer
     */
    @PostMapping("/update")
	public ResponseEntity<ValetServer> updateValetServer(@RequestBody ValetServerForm serverForm) {
    	try {
    		Long id = serverForm.getIdValetServer();
    		if (id != null && !valetServerService.findById(id).isPresent()) {
    			LOGGER.warn(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE006, id.toString()));
    			return ResponseEntity.notFound().build();
    		}
    		ValetServer entity = serverForm.toEntity();
    		entity.setIdValetServer(id);
    		ValetServer updated = valetServerService.save(entity);
    		return ResponseEntity.ok(updated);
    	} catch (Exception e) {
    		LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.LOG_VSE007, e.getMessage()), e);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
	}
}
