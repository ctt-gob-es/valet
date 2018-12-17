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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.repository.IApplicationValetService.java.</p>
 * <b>Description:</b><p> Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>10/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 10/12/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 10/12/2018.
 */
public interface IApplicationValetService {

	/**
	 * Method that obtains an application by its identifier.
	 * @param applicationId The application identifier.
	 * @return {@link ApplicationValet}
	 */
	ApplicationValet getApplicationById(Long applicationId);

	/**
	 * Method that obtains an application by the identificator of application.
	 * @param identificator The application identifier.
	 * @return {@link ApplicationValet}
	 */
	ApplicationValet getApplicationByIdentificator(String identificator);

	/**
	 * Method that stores an application in the persistence.
	 *
	 * @param application a {@link ApplicationValet} with the information of the application.
	 * @return {@link ApplicationValet} The application.
	 */
	ApplicationValet saveApplicationValet(ApplicationValet application);

	/**
	 * Method that deletes an application in the persistence.
	 * @param applicationId {@link Integer} that represents the application identifier to delete.
	 */
	void deleteApplicationValet(Long applicationId);

	/**
	 * Method that gets all the applications from the persistence.
	 * @return a {@link Iterable<ApplicationValet>} with the information of all applications.
	 */
	Iterable<ApplicationValet> getAllApplication();


	/**
	 * Method that gets the list for the given {@link DataTablesInput}.
	 * @param input the {@link DataTablesInput} mapped from the Ajax request.
	 * @return {@link DataTablesOutput}
	 */
	DataTablesOutput<ApplicationValet> getAllApplication(DataTablesInput input);
}
