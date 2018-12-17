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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.ApplicationRepository.java.</p>
 * <b>Description:</b><p> Interface that provides CRUD functionality for the ApplicationValet entity..</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>10/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 10/12/2018.
 */
package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Interface that provides CRUD functionality for the ApplicationValet entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 10/12/2018.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface ApplicationValetRepository extends JpaRepository<ApplicationValet, Long> {

	/**
	 * Method that obtains from the persistence an application identified by its primary key.
	 *
	 * @param id Long that represents the primary key of the application in the persistence.
	 * @return Object that represents an application from the persistence.
	 */
	ApplicationValet findByIdApplication(Long id);

	/**
	 * Method that obtains form the persistence a application identified by its identificator.
	 *
	 * @param identificator String that represents the identificator of the application
	 * @return Object that represents an application from the persistence.
	 */
	ApplicationValet findByIdentificator(String identificator);

}
