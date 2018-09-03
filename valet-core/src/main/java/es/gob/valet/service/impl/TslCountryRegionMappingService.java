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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>8 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 8 ago. 2018.
 */
package es.gob.valet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionMappingRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.TslCountryRegionMappingDataTablesRespository;
import es.gob.valet.service.ITslCountryRegionMappingService;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 8 ago. 2018.
 */
@Service
public class TslCountryRegionMappingService implements ITslCountryRegionMappingService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslCountryRegionMappingRepository repository;

	@Autowired
	private TslCountryRegionMappingDataTablesRespository dtRepository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ITslCountryRegionMappingService#getTslValetById(java.lang.Long)
	 */
	@Override
	public TslCountryRegionMapping getTslCountryRegionMappingById(Long idTslCountryRegionMapping) {
		return repository.findByIdTslCountryRegionMapping(idTslCountryRegionMapping);
	}

	public List<TslCountryRegionMapping> getAllMappingByIdCountry(Long idCRM) {
		List<TslCountryRegionMapping> listMapping = new ArrayList<TslCountryRegionMapping>();
		List<TslCountryRegionMapping> listAllMapping = repository.findAll();
		for (TslCountryRegionMapping tslcrm: listAllMapping) {
			if (tslcrm.getTslCountryRegion().getIdTslCountryRegion().equals(idCRM)) {
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

	public DataTablesOutput<TslCountryRegionMapping> findAll(DataTablesInput input) {
		return dtRepository.findAll(input);
	}

	public TslCountryRegionMapping save(TslCountryRegionMapping tslCRMParam) {
		return repository.save(tslCRMParam);
	}

	public void deleteTslCountryRegionMapping(Long idTslCountryRegionMapping) {
		repository.deleteById(idTslCountryRegionMapping);
	}

	public void deleteTslCountryRegionMappingByInBatch(List<TslCountryRegionMapping> listMapping) {
		repository.deleteInBatch(listMapping);
	}

	private List<String> getIdentificatorsByIdCountry(Long idCRM) {
		List<TslCountryRegionMapping> lcrm = getAllMappingByIdCountry(idCRM);
		List<String> listIdent = new ArrayList<String>();
		for (TslCountryRegionMapping tslcrm: lcrm) {
			listIdent.add(tslcrm.getMappingIdentificator());
		}
		return listIdent;
	}

}
