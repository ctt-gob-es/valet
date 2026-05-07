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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.TslDataRepository.java.</p>
 * <b>Description:</b><p>Interface that provides CRUD functionality for the TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.9, 08/10/2025.
 */
package es.gob.valet.persistence.configuration.model.repository;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO;
import es.gob.valet.persistence.configuration.model.dto.TslDataDTO;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Interface that provides CRUD functionality for the TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.9, 08/10/2025.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslDataRepository extends JpaRepository<TslData, Long> {

	/**
	 * Method that obtains from the persistence a TSL identified by its primary key.
	 * @param id String that represents the primary key of the TSL in the persistence.
	 * @return Object that represents a TSL from the persistence.
	 */
	TslData findByIdTslData(Long id);

	/**
	 * Method that obtains from the persistence a TSL object by country.
	 * @param tslContryRegion Country/Region of the TSL.
	 * @return Object that represents a TslData from the persistence.
	 */
	TslData findByTslCountryRegion(TslCountryRegion tslContryRegion);

	/**
	 * Method that obtains from the persistence a TSL object by its TSL Location.
	 * @param uriTslLocation URI that represents the TSL Location to find.
	 * @return Object that represents a TslData from the persistence.
	 */
	TslData findByUriTslLocation(String uriTslLocation);
	
	/**
	 * Method to obtain the list of information about the version of each registered TSL that is enabled.
	 * @return Object that represents a list of TslCountryVersionDTO.
	 */
	@Query("SELECT new es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO(tsl.sequenceNumber, c.countryRegionCode) FROM TslData tsl, TslCountryRegion c WHERE tsl.tslCountryRegion.idTslCountryRegion = c.idTslCountryRegion")
	List<TslCountryVersionDTO> findTslCountryVersionAvailable();

	/**
	 * Retrieves a {@link TslData} entity based on the given country/region ID and sequence number.
	 *
	 * @param idCountryRegion The ID of the country or region associated with the TSL data.
	 * @param sequenceNumber The sequence number of the TSL data.
	 * @return The {@link TslData} entity matching the given criteria, or {@code null} if no match is found.
	 */
	@Query("SELECT t FROM TslData t WHERE t.tslCountryRegion.idTslCountryRegion = ?1 AND t.sequenceNumber = ?2")
	TslData findTslDataByCountryAndSqNumber(Long idCountryRegion, Integer sequenceNumber);

	/**
	 * Retrieves all TSL records as {@link TslDataDTO} objects.
	 * This query joins each TSL entry with its associated country or region.
	 *
	 * @return a list of {@link TslDataDTO} containing selected fields from TSL and country/region entities
	 */
	@Query("SELECT new es.gob.valet.persistence.configuration.model.dto.TslDataDTO(tsl.idTslData, countryRegion.idTslCountryRegion, countryRegion.countryRegionName, tsl.sequenceNumber,tsl.issueDate,tsl.expirationDate, countryRegion.countryRegionCode, tsl.tslImpl.version)  FROM TslData tsl JOIN tsl.tslCountryRegion countryRegion LEFT JOIN tsl.tslLotlData tslLotlData WHERE tslLotlData IS NULL")
	List<TslDataDTO> findAllTslDataDTO();

	/**
	 * Retrieves all TSL data entries that are part of the List of Trusted Lists (LOTL),
	 * returning them as DTOs with selected fields.
	 *
	 * @return A list of {@link TslDataDTO} containing filtered TSL data and country region information.
	 */
	@Query("SELECT new es.gob.valet.persistence.configuration.model.dto.TslDataDTO(tsl.idTslData, countryRegion.idTslCountryRegion, countryRegion.countryRegionName, tsl.sequenceNumber,tsl.issueDate,tsl.expirationDate, countryRegion.countryRegionCode, tsl.tslImpl.version)  FROM TslData tsl JOIN tsl.tslCountryRegion countryRegion JOIN tsl.tslLotlData tslLotlData")
	List<TslDataDTO> findAllTslLotlDataDTO();

	/**
	 * Retrieves all full {@link TslData} entities that are associated with LOTL entries.
	 *
	 * @return A list of {@link TslData} objects linked to the List of Trusted Lists.
	 */
	@Query("SELECT tsl FROM TslData tsl JOIN tsl.tslCountryRegion countryRegion JOIN tsl.tslLotlData tslLotlData")
	List<TslData> findAllTslDataWithTslLotlData();
}
