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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.TslCountryRegionMappingRepository.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>8 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 8 ago. 2018.
 */
package es.gob.valet.persistence.configuration.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslValet;


/** 
 * <p>Interface that provides CRUD functionality for the TslCountryRegionMapping entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 8 ago. 2018.
 */
@Repository
public interface TslCountryRegionMappingRepository extends JpaRepository<TslCountryRegionMapping, Long> {
	/**
	  * Method that obtains from the persistence a TslCountryRegionMapping identified by its primary key. 
	 * @param id String that represents the primary key of the TslCountryRegionMapping in the persistence.
	 * @return Object that represents a TSL from the persistence. 
	 */
	TslCountryRegionMapping findByIdTslCountryRegionMapping(Long id);
	
	
}
