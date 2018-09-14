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
 * <b>File:</b><p>es.gob.valet.service.impl.TslCountryRegionMappingService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for TslCountryRegionMapping .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>8 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 8 ago. 2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionMappingRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.TslCountryRegionMappingDataTablesRespository;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer for TslCountryRegionMapping.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 8 ago. 2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TslCountryRegionMappingService implements ITslCountryRegionMappingService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslCountryRegionMappingRepository repository;

	/**
	 * Attribute that represnts the injected interface that provides 
	 */
	@Autowired
	private TslCountryRegionMappingDataTablesRespository dtRepository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#getTslValetById(java.lang.Long)
	 */
	@Override
	public TslCountryRegionMapping getTslCountryRegionMappingById(Long idTslCountryRegionMapping) {
		return repository.findByIdTslCountryRegionMapping(idTslCountryRegionMapping);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#getAllMappingByIdCountry(java.lang.Long)
	 */
	public List<TslCountryRegionMapping> getAllMappingByIdCountry(Long idTslCountryRegion) {
		List<TslCountryRegionMapping> listMapping = new ArrayList<TslCountryRegionMapping>();
		List<TslCountryRegionMapping> listAllMapping = repository.findAll();
		for (TslCountryRegionMapping tslcrm: listAllMapping) {
			if (tslcrm.getTslCountryRegion().getIdTslCountryRegion().equals(idTslCountryRegion)) {
				listMapping.add(tslcrm);
			}
		}
		return listMapping;

	}

	public boolean existIdentificator(String identificator, Long idCRM) {
		boolean existId = false;
		List<String> listIdentificators = new ArrayList<String>();
		listIdentificators = getIdentificatorsByIdCountry(idCRM);
		if (listIdentificators.contains(identificator)) {
			existId = true;
		}

		return existId;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#findAll(org.springframework.data.jpa.datatables.mapping.DataTablesInput)
	 */
	public DataTablesOutput<TslCountryRegionMapping> findAll(DataTablesInput input) {
		return dtRepository.findAll(input);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#save(es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping)
	 */
	public TslCountryRegionMapping save(TslCountryRegionMapping tslCRMParam) {
		return repository.save(tslCRMParam);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#deleteTslCountryRegionMapping(java.lang.Long)
	 */
	public void deleteTslCountryRegionMapping(Long idTslCountryRegionMapping) {
		repository.deleteById(idTslCountryRegionMapping);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService#deleteTslCountryRegionMappingByInBatch(java.util.List)
	 */
	public void deleteTslCountryRegionMappingByInBatch(List<TslCountryRegionMapping> listMapping) {
		repository.deleteInBatch(listMapping);
	}

	
	/**
	 * Method that returns the list of identifiers of the mappings of a country.
	 * 
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.  
	 * @return List of identifiers of the mappings.
	 */
	private List<String> getIdentificatorsByIdCountry(Long idTslCountryRegion) {
		List<TslCountryRegionMapping> lcrm = getAllMappingByIdCountry(idTslCountryRegion);
		List<String> listIdent = new ArrayList<String>();
		for (TslCountryRegionMapping tslcrm: lcrm) {
			listIdent.add(tslcrm.getMappingIdentificator());
		}
		return listIdent;
	}

}
