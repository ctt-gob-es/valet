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
 * @version 1.4, 20/07/2021.
 */
package es.gob.valet.persistence.configuration.model.repository;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Interface that provides CRUD functionality for the TslData entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4,  20/07/2021.
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

}
