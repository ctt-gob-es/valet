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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>23 jul. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 23 jul. 2018.
 */
package es.gob.valet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.TSLCountryRegion;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.service.ITslCountryRegionService;


/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 23 jul. 2018.
 */
@Service
public class TSLCountryRegionService implements ITslCountryRegionService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private TslCountryRegionRepository repository;
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ITslCountryRegionService#getTSLCountryRegionById()
	 */
	@Override
	public TSLCountryRegion getTSLCountryRegionById(Long idCountry) {
		
		return repository.findByIdTSLCountryRegion(idCountry);
	}

}
