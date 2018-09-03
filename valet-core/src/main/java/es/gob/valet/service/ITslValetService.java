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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25 jun. 2018.
 */
package es.gob.valet.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslValet;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25 jun. 2018.
 */
public interface ITslValetService {
	
	/**
	 * Method that returns a list of TSLs to be showed in DataTable
	 * @param input DataTableInput with filtering, paging and sorting configuration.
	 * @return A set of DataTable rows that matches the query.
	 */
	DataTablesOutput<TslValet> findAllTsl(DataTablesInput input);

	
	
	/**
	 * Method that obtains a TSL by its identifier.
	 * @param tslId The TSL identifier.
	 * @return {@link TslValet}
	 */
	TslValet getTslValetById(Long tslId);
	
	Iterable<TslValet> getAllTSL();
	
	TslValet saveTSL( TslValet tslParam);
	
	 void deleteTslValet(Long idTslValet);
	 TslValet findByCountry(TslCountryRegion tslCountryRegion);
	
	
}
