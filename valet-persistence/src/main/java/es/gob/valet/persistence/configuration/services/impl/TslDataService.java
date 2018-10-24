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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.TslDataService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer related to TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/10/2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.TslDataTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer related to TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/10/2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TslDataService implements ITslDataService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslDataRepository repository;

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslDataTablesRepository dtRepository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getAllTsl(org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 */
	@Override
	public DataTablesOutput<TslData> getAllTsl(DataTablesInput input) {
		return dtRepository.findAll(input);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslDataById(java.lang.Long)
	 */
	@Override
	public TslData getTslDataById(Long tslId) {
		return repository.findByIdTslData(tslId);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getAllTSL()
	 */
	public Iterable<TslData> getAllTSL() {
		return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#saveTSL(es.gob.valet.persistence.configuration.model.entity.TslData)
	 */
	public TslData saveTSL(TslData tslData) {
		return repository.save(tslData);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#deleteTslData(java.lang.Long)
	 */
	@Override
	public void deleteTslData(Long idTslData) {
		repository.deleteById(idTslData);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslByCountryRegion(es.gob.valet.persistence.configuration.model.entity.TslCountryRegion)
	 */
	public TslData getTslByCountryRegion(TslCountryRegion tslCountryRegion) {
		return repository.findByCountry(tslCountryRegion);
	}

}
