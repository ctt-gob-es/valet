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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.SystemCertificateRepository.java.</p>
 * <b>Description:</b><p>Interface that provides CRUD functionality for the SystemCertificate entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 10/08/2023.
 */
package es.gob.valet.persistence.configuration.model.repository;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;

/**
 * <p>Interface that provides CRUD functionality for the ExternalAccess entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 10/08/2023.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface ExternalAccessRepository extends JpaRepository<ExternalAccess, Long>, JpaSpecificationExecutor<ExternalAccess> {

	/**
	 *	Method that gets all the urls.
	 * @return List of urls.
	 */
	List<ExternalAccess> findAll();

	/**
	 * Method that obtains from the persistence a UrlData identified by its primary key.
	 * @param idUrlData Long that represents the primary key of the UrlData in the persistence.
	 * @return Object that represents a UrlData from the persistence.
	 */
	ExternalAccess findByIdUrl(Long idUrlData);
	
	/**
	 * Method that obtains from the persistence a UrlData identified by url.
	 * @param url String that represents the value url of the UrlData in the persistence.
	 * @return Object that represents a UrlData from the persistence.
	 */
	ExternalAccess findByUrl(String url);
	
	/**
	 * Method that obtain all external access contain in list of id.
	 * 
	 * @param listIdUrl parameter that contain id for realize test connection.
	 * @return list object found in BD.
	 */
	@Query("SELECT EA FROM ExternalAccess EA WHERE EA.idUrl IN (?1)")
	List<ExternalAccess> findByIdUrlInQuery(List<Long> listIdUrl);
	}
