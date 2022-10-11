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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.TslMappingRepository.java.</p>
 * <b>Description:</b><p> Interface that provides CRUD functionality for the ApplicationValet entity..</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>10/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 11/10/2022.
 */
package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslMapping;

/**
 * <p>Interface that provides CRUD functionality for the ApplicationValet entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 11/10/2022.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslMappingRepository extends PagingAndSortingRepository<TslMapping, Long>, JpaRepository<TslMapping, Long> {

	/**
	 * Query that obtain tsl mapping by id.
	 * 
	 * @param idTslMapping parameter that contain id to tsl mapping.
	 * @return tsl mapping found.
	 */
	TslMapping findByIdTslMapping(Long idTslMapping);
	
}
