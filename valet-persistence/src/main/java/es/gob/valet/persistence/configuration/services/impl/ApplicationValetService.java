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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.ApplicationValetService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>10/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 10/12/2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.model.repository.ApplicationValetRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ApplicationValetDataTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService;


/**
 * <p>Class that implements the communication with the operations of the persistence layer .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 10/12/2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ApplicationValetService implements IApplicationValetService {

	/**
	 * Attribute that represents the injected interface that proves CRUD operations for the persistence.
	 */
	@Autowired
	private ApplicationValetRepository repository;

	/**
	 * Attribute that represents the injected interface that proves CRUD operations for the persistence.
	 */
	@Autowired
	private ApplicationValetDataTablesRepository dtRepository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#getApplicationById(java.lang.Long)
	 */
	@Override
	public ApplicationValet getApplicationById(Long applicationId) {
		return repository.findByIdApplication(applicationId);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#getApplicationByIdentificator(java.lang.String)
	 */
	@Override
	public ApplicationValet getApplicationByIdentificator(String identificator) {
		return repository.findByIdentificator(identificator);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#findAll(org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 */
	@Override
	public DataTablesOutput<ApplicationValet> getAllApplication(DataTablesInput input) {

		return dtRepository.findAll(input);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#saveApplicationValet(es.gob.valet.persistence.configuration.model.entity.ApplicationValet)
	 */
	@Override
	public ApplicationValet saveApplicationValet(ApplicationValet application) {
		return repository.save(application);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#deleteApplicationValet(java.lang.Long)
	 */
	@Override
	public void deleteApplicationValet(Long applicationId) {
		repository.deleteById(applicationId);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService#getAllApplication()
	 */
	@Override
	public Iterable<ApplicationValet> getAllApplication() {
		return repository.findAll();
	}

}
