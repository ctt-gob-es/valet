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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IValetServerService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ValetServer entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;

import java.util.List;
import java.util.Optional;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ValetServer entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
public interface IValetServerService {
	/**
	 * Method that stores a new {@link ValetServer} entity in the system or updates an existing one.
	 *
	 * @param entity {@link ValetServer} object that represents the server to save or update.
	 * @return {@link ValetServer} the saved or updated ValetServer entity.
	 */
	ValetServer save(ValetServer entity);

	/**
	 * Method that retrieves a {@link ValetServer} entity by its identifier.
	 *
	 * @param id {@link Long} that represents the identifier of the ValetServer.
	 * @return {@link Optional<ValetServer>} an optional containing the ValetServer if found, or empty if not.
	 */
	Optional<ValetServer> findById(Long id);

	/**
	 * Method that retrieves all {@link ValetServer} entities stored in the system.
	 *
	 * @return {@link List<ValetServer>} a list with all the ValetServer entities.
	 */
	List<ValetServer> findAll();

	/**
	 * Method that deletes a {@link ValetServer} entity by its identifier.
	 *
	 * @param id {@link Long} that represents the identifier of the ValetServer to delete.
	 */
	void deleteById(Long id);
}