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
 * <b>File:</b><p>es.gob.valet.service.ITslService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to TslData.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.7, 26/06/2025.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO;
import es.gob.valet.persistence.configuration.model.dto.TslDataDTO;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Interface that provides communication with the operations of the persistence layer related to TslData.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.7, 26/06/2025.
 */
public interface ITslDataService {

	/**
	 * Method that returns a list of TSLs to be showed in DataTable.
	 * @param input DataTableInput with filtering, paging and sorting configuration.
	 * @return A set of DataTable rows that matches the query.
	 */
	DataTablesOutput<TslData> obtainAllTslToDatatable(DataTablesInput input);

	/**
	 * Method that obtains a TSL by its identifier.
	 * @param tslId The TSL identifier.
	 * @param loadXmlDocument Flag that indicates if it is necessary to load the XML document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param loadLegibleDocument Flag that indicates if it is necessary to load the legible document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @return {@link TslData} an object that represents the TSL.
	 */
	@Transactional
	TslData getTslDataById(Long tslId, boolean loadXmlDocument, boolean loadLegibleDocument);

	/**
	 * Method that gets all TSL of the system.
	 * @return a {@link Iterable<TslData>} with the information of all the TSLs of the system.
	 */
	Iterable<TslData> getAllTSL();

	/**
	 * Method that stores a new TSL in the system.
	 * @param tslData Parameter that represents the new TSL.
	 * @return {@link TslData} an object that represents the TSL.
	 */
	@Transactional
	TslData saveTSL(TslData tslData);

	/**
	 * Method that removes a TSL from the system.
	 * @param idTslData Parameter that represent the ID of the TSL to delete.
	 */
	@Transactional
	void deleteTslData(Long idTslData);

	/**
	 * Method that obtains a TSL by country.
	 * @param tslCountryRegion Parameter that represents the country/region.
	 * @param loadXmlDocument Flag that indicates if it is necessary to load the XML document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param loadLegibleDocument Flag that indicates if it is necessary to load the legible document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @return {@link TslData} an object that represents the TSL obtained.
	 */
	@Transactional
	TslData getTslByCountryRegion(TslCountryRegion tslCountryRegion, boolean loadXmlDocument, boolean loadLegibleDocument);

	/**
	 * Method that obtains a TSL by its TSL Location.
	 * @param tslLocation Parameter that represents the TSL Location to find.
	 * @param loadXmlDocument Flag that indicates if it is necessary to load the XML document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param loadLegibleDocument Flag that indicates if it is necessary to load the legible document
	 * (<code>true</code>) or not (<code>false</code>).
	 * @return {@link TslData} an object that represents the TSL obtained.
	 */
	@Transactional
	TslData getTslByTslLocation(String tslLocation, boolean loadXmlDocument, boolean loadLegibleDocument);
	
	
	/**
	 * Method to obtain the list of information about the version of each registered TSL that is enabled.
	 * @return Object that represents a list of TslCountryVersionDTO.
	 */
	List<TslCountryVersionDTO> getTslCountryVersionAvailable();

	/**
	 * Retrieves a {@link TslData} entity based on the specified country/region and sequence number.
	 *
	 * @param tcrp The {@link TslCountryRegion} entity representing the country or region.
	 * @param tslSequenceNumber The sequence number of the TSL data.
	 * @return The {@link TslData} entity matching the given criteria, or {@code null} if no match is found.
	 */
	TslData getTslByCountryAndSequenceNumber(TslCountryRegion tcrp, int tslSequenceNumber);

	/**
	 * Returns a list of all TSL entries as {@link TslDataDTO} objects.
	 * Delegates the retrieval to the repository query method.
	 *
	 * @return a list of {@link TslDataDTO} with data from TSL and associated country/region
	 */
	List<TslDataDTO> obtainAllTslDTO();

	/**
	 * Retrieves all TSL data entries that are marked as LOTL and maps them to DTOs.
	 *
	 * @return List of TslDataDTO representing LOTL TSL entries.
	 */
	List<TslDataDTO> obtainAllLotlDTO();

}
