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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository.java.</p>
 * <b>Description:</b><p>Interface that provides CRUD functionality for the TslCountryRegion entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>23/07/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 30/08/2021.
 */
package es.gob.valet.persistence.configuration.model.repository;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;

/**
 * <p>Interface that provides CRUD functionality for the TslCountryRegion entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 30/08/2021.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslCountryRegionRepository extends JpaRepository<TslCountryRegion, Long> {

	/**
	 * Method that obtains from the persistence a country/region identified by its primary key.
	 * @param id Long that represents the primary key of the country in the persistence.
	 * @return Object that represents a country/region from the persistence.
	 */
	TslCountryRegion findByIdTslCountryRegion(Long id);

	/**
	 * Method that obtains from the persistence a country/region identified by its code.
	 * @param countryRegionCode {@link String} that represents the country/region code to find.
	 * @return Object that represents a country/region from the persistence.
	 */
	TslCountryRegion findByCountryRegionCode(String countryRegionCode);
	
	/**
	 * Returns the list of all countries ordered by their code.
	 * @return All countries ordered by their country/region name.
	 */
	List<TslCountryRegion> findAllByOrderByCountryRegionCodeAsc();
	
	
	/**
	 * Method to obtain the list of codes of the countries of the TSLs stored in the system.
	 * @return Object that represents a list of country code.
	 */
	@Query("SELECT c.countryRegionCode FROM TslCountryRegion c")
	List<String> findAllByOrder();

}
