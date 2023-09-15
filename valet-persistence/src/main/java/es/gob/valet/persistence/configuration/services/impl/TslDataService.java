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
 * @version 1.4,  07/06/2021.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.TslDataTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer related to TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4,  07/06/2021.
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
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#obtainAllTslToDatatable(org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 */
	@Override
	public DataTablesOutput<TslData> obtainAllTslToDatatable(DataTablesInput input) {
		return dtRepository.findAll(input);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslDataById(java.lang.Long, boolean, boolean)
	 */
	@Override
	@Transactional // TODO ¿Es necesario al haberlo puesto ya en la interfaz?
	public TslData getTslDataById(Long tslId, boolean loadXmlDocument, boolean loadLegibleDocument) {
		TslData result = repository.findByIdTslData(tslId);
		if (result != null) {
			if (loadXmlDocument) {
				@SuppressWarnings("unused")
				byte byteZero = result.getXmlDocument()[0];
			}
			if (loadLegibleDocument && result.getLegibleDocument() != null) {
				@SuppressWarnings("unused")
				byte byteZero = result.getLegibleDocument()[0];
			}
		}
		return result;
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
	@Transactional
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
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslByCountryRegion(es.gob.valet.persistence.configuration.model.entity.TslCountryRegion, boolean, boolean)
	 */
	@Override
	@Transactional // TODO ¿Es necesario al haberlo puesto ya en la interfaz?
	public TslData getTslByCountryRegion(TslCountryRegion tslCountryRegion, boolean loadXmlDocument, boolean loadLegibleDocument) {
		TslData result = repository.findByTslCountryRegion(tslCountryRegion);
		if (result != null) {
			if (loadXmlDocument) {
				@SuppressWarnings("unused")
				byte byteZero = result.getXmlDocument()[0];
			}
			if (loadLegibleDocument && result.getLegibleDocument() != null) {
				@SuppressWarnings("unused")
				byte byteZero = result.getLegibleDocument()[0];
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslByTslLocation(java.lang.String, boolean, boolean)
	 */
	@Override
	@Transactional // TODO ¿Es necesario al haberlo puesto ya en la interfaz?
	public TslData getTslByTslLocation(String tslLocation, boolean loadXmlDocument, boolean loadLegibleDocument) {
		TslData result = repository.findByUriTslLocation(tslLocation);
		if (result != null) {
			if (loadXmlDocument) {
				@SuppressWarnings("unused")
				byte byteZero = result.getXmlDocument()[0];
			}
			if (loadLegibleDocument && result.getLegibleDocument() != null) {
				@SuppressWarnings("unused")
				byte byteZero = result.getLegibleDocument()[0];
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslDataService#getTslCountryVersionAvailable()
	 */
	@Override
	public List<TslCountryVersionDTO> getTslCountryVersionAvailable() {
		return repository.findTslCountryVersionAvailable();
	}

}
