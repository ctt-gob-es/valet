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
 * <b>File:</b><p>es.gob.valet.service.impl.TslCountryRegionService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for TslCountryRegion.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>23/07/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 25/11/2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for TslCountryRegion.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 25/11/2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TslCountryRegionService implements ITslCountryRegionService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslCountryRegionRepository repository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#getTslCountryRegionById(java.lang.Long, boolean)
	 */
	@Override
	@Transactional // TODO: ¿es necesario si ya está puesta esta anotación en la
	// interfaz?
	public TslCountryRegion getTslCountryRegionById(Long idCountry, boolean loadMappings) {
		TslCountryRegion result = repository.findByIdTslCountryRegion(idCountry);
		if (result != null && loadMappings && result.getListTslCountryRegionMapping() != null) {
			result.getListTslCountryRegionMapping().size();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#getTslCountryRegionByCode(java.lang.String, boolean)
	 */
	@Override
	@Transactional // TODO: ¿es necesario si ya está puesta esta anotación en la
	// interfaz?
	public TslCountryRegion getTslCountryRegionByCode(String countryRegionCode, boolean loadMappings) {
		TslCountryRegion result = repository.findByCountryRegionCode(countryRegionCode);
		if (result != null && loadMappings && result.getListTslCountryRegionMapping() != null) {
			result.getListTslCountryRegionMapping().size();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#getNameCountryRegionById(java.lang.Long)
	 */
	@Override
	public String getNameCountryRegionById(Long idCountry) {
		TslCountryRegion tslCountryRegion = repository.findByIdTslCountryRegion(idCountry);
		return tslCountryRegion.getCountryRegionName();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#getAllTslCountryRegion(boolean)
	 */
	@Override
	@Transactional // TODO: ¿es necesario si ya está puesta esta anotación en la
	// interfaz?
	public List<TslCountryRegion> getAllTslCountryRegion(boolean loadMappings) {
		List<TslCountryRegion> result = repository.findAll();
		if (result != null && !result.isEmpty() && loadMappings) {
			for (TslCountryRegion tslCountryRegion: result) {
				tslCountryRegion.getListTslCountryRegionMapping().size();
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#updateSaveTslCountryRegion(es.gob.valet.persistence.configuration.model.entity.TslCountryRegion)
	 */
	@Override
	public TslCountryRegion updateSaveTslCountryRegion(TslCountryRegion tslCountryRegion) {
		return tslCountryRegion == null ? null : repository.save(tslCountryRegion);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService#deleteTslCountryRegionById(java.lang.Long)
	 */
	@Override
	public void deleteTslCountryRegionById(Long idTslCountryRegion) {
		if (idTslCountryRegion != null) {
			repository.deleteById(idTslCountryRegion);
		}
	}

}
