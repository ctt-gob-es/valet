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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.ValetServerService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for ValetServer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.persistence.configuration.services.impl;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.model.repository.ValetServerRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * <p>Class that implements the communication with the operations of the persistence layer for ValetServer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@Service
public class ValetServerService implements IValetServerService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private ValetServerRepository repository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IValetServerService#save(es.gob.valet.persistence.configuration.model.entity.ValetServer)
	 */
	@Override
	public ValetServer save(ValetServer entity) {
	    return repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IValetServerService#findById(java.lang.Long)
	 */
	@Override
	public Optional<ValetServer> findById(Long id) {
	    return repository.findById(id);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IValetServerService#findAll()
	 */
	@Override
	public List<ValetServer> findAll() {
	    return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IValetServerService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
	    repository.deleteById(id);
	}
}