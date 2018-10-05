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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IMailService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Mail entity.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.Mail;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Mail entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */
public interface IMailService {

	/**
	 *  Gets the list of mails.
	 * 
	 * @return List of mails.
	 */
	List<Mail> getAllMail();

	/**
	 * Method that gets mail by ID of Mail.
	 * @param idMail Id of Mail
	 * @return {@link Mail} an object that represents the Mail.
	 */
	Mail getMailById(Long idMail);

	/**
	* Method that saves Mail.
	* @param Mail to update.
	* @return {@link Mail} an object that represents the Mail.
	*/
	Mail saveMail(Mail mail);

	/**
	 * Method that delete a Mail.
	 * @param idMail Id of Mail
	 */
	void deleteMail(Long idMail);

	/**
	 * Method that gets the list for the given {@link DataTablesInput}.
	 * 
	 * @param input the {@link DataTablesInput} mapped from the Ajax request.
	 * @return {@link DataTablesOutput}
	 */
	DataTablesOutput<Mail> getAllMail(DataTablesInput input);

	/**
	 * Create a Set of e-mails from a String
	 * 
	 * @param concatString String of e-mails
	 * @return  {@link} Set<Mail>
	 */
	Set<Mail> splitMails(String concatString);

}
