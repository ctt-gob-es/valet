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
 * <b>File:</b><p>es.gob.valet.service.ITslCountryRegionService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to TslCountryRegion entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>23/07/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 24/10/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;

/**
 * <p>Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Interface that provides communication with the operations of the persistence layer related to TslCountryRegion entity.</p>
 * @version 1.1, 24/10/2018.
 */
public interface ITslCountryRegionService {

	/**
	 * Method that obtains the TSL country/region by the identifier.
	 * @param idCountry Parameter that represents identifier of Country/Region.
	 * @param loadMappings Flag that indicates if it is necesary to load the associated mappings (<code>true</code>)
	 * or not (<code>false</code>).
	 * @return TSL Country Region data representation from the data base.
	 */
	@Transactional
	TslCountryRegion getTslCountryRegionById(Long idCountry, boolean loadMappings);

	/**
	 * Method that obtains the TSL country/region by its code.
	 * @param countryRegionCode Parameter that represents the Country/Region code to get.
	 * @param loadMappings Flag that indicates if it is necesary to load the associated mappings (<code>true</code>)
	 * or not (<code>false</code>).
	 * @return TSL Country Region data representation from the data base.
	 */
	@Transactional
	TslCountryRegion getTslCountryRegionByCode(String countryRegionCode, boolean loadMappings);

	/**
	 * Method that obtains the name of the country/region.
	 * @param idCountry Parameter that represents identifier of Country/Region.
	 * @return Name of country/region.
	 */
	String getNameCountryRegionById(Long idCountry);

}
