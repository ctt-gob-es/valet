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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.ICTSLImplService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/07/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import es.gob.valet.persistence.configuration.model.entity.CTslImpl;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 10/09/2018.
 */
public interface ICTslImplService {

	/**
	 * Gets the list of available specifications of TSL.
	 * @return List of data constants in data base that represents the differents specifications and versions of TSL.
	 */
	Iterable<CTslImpl> getAllCTSLImpl();
	
	/**
	 * Gets a map with the relation between specification and versions in TSL.
	 * @return map with the relation between specification and versions in TSL.
	 */
	Map<String, Set<String>> getsTSLRelationSpecificatioAndVersion();
	
	/**
	 * Gets a specific data representation of one particular TSL specification and version.
	 * @param id Identifier in database of the particular TSL specification and version to get.
	 * @return {@link CTslImpl} object that represents the particular TSL specification and version.
	 */
	CTslImpl getCTSLImpById(Long id);
	
	/**
	 * Gets a list of all the TSL specifications availables.
	 * @return list of all the TSL specifications availables.
	 */
	List<String> getAllSpecifications();
	
	/**
	 * Gets a specific TSL specification and version persistence data.
	 * @param specification TSL specification to search.
	 * @param version TSL version specification to search.
	 * @return specific TSL specification and version persistence data.
	 */
	CTslImpl getCTSLImplBySpecificationVersion(String specification, String version);

}
