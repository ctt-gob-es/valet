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
 * <b>File:</b><p>es.gob.valet.service.ifaces.ITslPendValService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer in relation of the Tsl pending validation entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/06/2025.
 */
package es.gob.valet.service.ifaces;

import java.util.List;

import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;

/**
 * <p>Interface that provides communication with the operations of the persistence layer in relation of the Tsl pending validation entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/06/2025.
 */
public interface ITslPendValService {

	/**
	 * Retrieves a list of all pending TSLs from the database, parses their XML content,
	 * and builds a list of {@link TslPendValDTO} with summarized and relevant information
	 * for display or processing.
	 *
	 * @return a list of {@link TslPendValDTO} containing extracted data such as country, 
	 *         sequence number, issue date, distribution point, TSL name, operator name, 
	 *         expiration date, and signing certificate details.
	 */
	List<TslPendValDTO> obtainAllTslPendVal();

	/**
	 * Confirms a pending TSL validation by either updating an existing TSL record
	 * or adding it as new, and then removes the pending entry.
	 *
	 * <p>This method:
	 * <ul>
	 *   <li>Parses and extracts data from the pending TSL.</li>
	 *   <li>If a TSL with the same location exists, updates it; otherwise, creates a new one.</li>
	 *   <li>Deletes the pending validation entry after processing.</li>
	 * </ul>
	 *
	 * @param idTslPendVal the ID of the pending TSL to confirm
	 */
	void confirmTslPendVal(Long idTslPendVal);

	/**
	 * Declines a pending TSL validation by removing the entry from the database.
	 *
	 * @param idTslPendVal the ID of the pending TSL to decline
	 */
	void declineTslPendVal(Long idTslPendVal);
	

}
