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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.TslServiceRepository.java.</p>
 * <b>Description:</b><p> Interface that provides CRUD functionality for the ApplicationValet entity..</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>28/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.3, 11/10/2022.
 */
package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslService;

/**
 * <p>Interface that provides CRUD functionality for the ApplicationValet entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 11/10/2022.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslServiceRepository extends PagingAndSortingRepository<TslService, Long>, JpaRepository<TslService, Long> {
	
	/**
	 * Method that obtains from the persistence an application identified by its primary key.
	 *
	 * @param id Long that represents the primary key of the application in the persistence.
	 * @return Object that represents an application from the persistence.
	 */
	TslService findByIdTslService(Long idTslService);

	/**
	 * Method that obtains from the persistence an application identified by its tsp service name.
	 * 
	 * @param tspServiceName that represents the tsp service name of the application in the persistence.
	 * @return Object that represents an application from the persistence.
	 */
	TslService findByTspServiceName(String tspServiceName);
	
	/**
	 * Query that obtain a tsl service if this have tsp service name and logic field id.
	 * 
	 * @param tspServiceName parameter that represents the tsp service name of the application in the persistence.
	 * @param logicalFieldId parameter that represents the identificator to logic field.
	 * @return  tsl service if this have tsp service name and logic field id.
	 */
	@Query(value = "SELECT TS FROM TslService TS, TslMapping TM WHERE TS.idTslService = TM.tslService.idTslService AND TS.tspServiceName = ?1 AND TM.logicalFieldId = ?2")
	TslService findByTspServiceNameAndLogicFieldId(String tspServiceName, String logicalFieldId);
}
