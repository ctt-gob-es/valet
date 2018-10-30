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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to TslCountryRegionMapping entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 29/10/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;


/**
 * <p>Interface that provides communication with the operations of the persistence layer related to TslCountryRegionMapping entity .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 29/10/2018.
 */
public interface ITslCountryRegionMappingService {

	/**
	 * Method that obtains a TslCountryRegionMapping by its identifier.
	 * @param idTslCountryRegionMapping The TslCountryRegionMapping identifier.
	 * @return {@link TslCountryRegionMapping}
	 */
	TslCountryRegionMapping getTslCountryRegionMappingById(Long idTslCountryRegionMapping);

	/**
	 * Method that gets all the mappings for a specified Country/Region.
	 * @param idCRM Long that represents CountryRegion identifier.
	 * @return List of TslContryRegionMapping by country
	 */
	List<TslCountryRegionMapping> getAllMappingByIdCountry(Long idCRM);


	/**
	 * Method that returns a list with all the TslCountryRegionMapping objects to be showed in Datatable.
	 * @param input DataTableInput with filtering, paging and sorting configuration.
	 * @return A set of DataTable rows that matches the query.
	 */
	DataTablesOutput<TslCountryRegionMapping> findAll(DataTablesInput input);
	/**
	 * Method that stores a new Mapping for a Country/Region representation for TSL.
	 * @param tslCountryRegionMapping Parameter that represents the new mapping for the Country/Region.
	 * @return {@link TslCountryRegionMapping} TslCountryRegionMapping stored.
	 */
	TslCountryRegionMapping save(TslCountryRegionMapping tslCountryRegionMapping);

	/**
	 * Method that removes a country/region Mapping.
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.
	 */
	void deleteTslCountryRegionMapping(Long idTslCountryRegionMapping);

	/**
	 * Method that checks if there is an identificator in the list of mappings belonging to a specified country.
	 * @param identificator Parameter that represents the identifier to be checked.
	 * @param idTslCountryRegion Parameter that represents the country/region of TSL.
	 * @return true, if it exists.
	 */
	boolean existIdentificator(String identificator, Long idTslCountryRegion);
}
