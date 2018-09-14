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
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to TslValet.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25 jun. 2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslValet;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer related to TslValet.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25 jun. 2018.
 */
public interface ITslValetService {
	
	/**
	 * Method that returns a list of TSLs to be showed in DataTable.
	 * @param input DataTableInput with filtering, paging and sorting configuration.
	 * @return A set of DataTable rows that matches the query.
	 */
	DataTablesOutput<TslValet> getAllTsl(DataTablesInput input);

	
	
	/**
	 * Method that obtains a TSL by its identifier.
	 * @param tslId The TSL identifier.
	 * @return {@link TslValet} an object that represents the TSL.
	 */
	TslValet getTslValetById(Long tslId);
	
	/**
	 * Method that gets all TSL of the system.
	 * @return a {@link Iterable<TslValet>} with the information of all the TSLs of the system.
	 */
	Iterable<TslValet> getAllTSL();
	
	/**
	 * Method that stores a new TSL in the system.
	 * 
	 * @param tslParam Parameter that represents the new TSL.
	 * @return {@link TslValet} an object that represents the TSL.
	 */
	TslValet saveTSL( TslValet tslParam);
	
	
	/**
	 * Method that removes a TSL from the system.
	 * 
	 * @param idTslValet Parameter that represent the ID of the TSL to delete.
	 */
	 void deleteTslValet(Long idTslValet);
	 
	 /**
	  * Method that obtains a TSL by country 
	  * 
	  * @param tslCountryRegion Parameter that represents the country/region.
	  * @return {@link TslValet} an object that represents the TSL obtained.
	  */
	 TslValet getTslByCountryRegion(TslCountryRegion tslCountryRegion);
	
	
}
