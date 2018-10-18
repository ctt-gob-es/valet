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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.ISystemCertificateService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the SystemCertificate entity.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18 sept. 2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the SystemCertificate entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18 sept. 2018.
 */	
public interface ISystemCertificateService {
	/**
	 * Method that gets all the certificates for a specified keystore.
	 * @param input DataTableInput with filtering, paging and sorting configuration.
	 * @param idKeystore Long that represents Keystore identifier.
	 * @return List of SystemCertificates by keystore 
	 */
	DataTablesOutput<SystemCertificate> getAllByKeystore(DataTablesInput input, Long idKeystore);
	/**
	 * Method that gets all system certificates by keystore of the system.
	 * @param keystore Long that represents Keystore identifier.
	 * @return a {@link Iterable<SystemCertificate>} with the information of all the SystemCertificates by keystore of the system.
	 */
	Iterable<SystemCertificate> getAllByKeystore(Keystore keystore);
	
	/**
	 * Method that stores a new SystemCertificate in the system.
	 * 
	 * @param systemCertificateParam Parameter that represents the new SystemCertificate.
	 * @return {@link SystemCertificate} an object that represents the SystemCertificate.
	 */
	SystemCertificate saveSystemCertificate( SystemCertificate systemCertificateParam);
	
	/**
	 * Method that obtains a System Certificate by its identifier.
	 * @param idSystemCertificate The system certificate identifier.
	 * @return {@link SystemCertificate} an object that represents the system certificate.
	 */
	SystemCertificate getSystemCertificateById(Long idSystemCertificate);
	
	/**
	 * Method that deletes a system certificate in the persistence.
	 * @param idSystemCertificate {@link Integer} that represents the certificate identifier to delete.
	 */
	void deleteSystemCertificate(Long idSystemCertificate);
}
