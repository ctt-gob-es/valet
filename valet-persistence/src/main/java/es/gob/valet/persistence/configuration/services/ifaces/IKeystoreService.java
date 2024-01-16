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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Keystore entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 16/01/2024.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.security.Key;
import java.security.cert.Certificate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.utils.StatusCertificateIdConstants;
import es.gob.valet.persistence.exceptions.CryptographyException;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Keystore entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
public interface IKeystoreService {

	/**
	 * Method that gets the list of keystores.
	 * @return List of keystores.
	 */
	List<Keystore> getAllKeystore();

	/**
	 * Method that gets name of keystore by ID of Keystore.
	 * @param idKeystore Id of keystore.
	 * @return String that represents name of keystore.
	 */
	@Transactional
	String getNameKeystoreById(Long idKeystore);

	/**
	 * Method that gets keystore by ID of Keystore.
	 * @param idKeystore Id of keystore.
	 * @param loadSystemCertificates Flag that indicates if it is necessary to load the
	 * relation with the SystemCertificates (<code>true</code>) or not (<code>false</code>).
	 * @return {@link Keystore} an object that represents the Keystore.
	 */
	Keystore getKeystoreById(Long idKeystore, boolean loadSystemCertificates);

	/**
	 * Method that saves keystore.
	 * @param keystore Keystore to update.
	 * @return {@link Keystore} an object that represents the Keystore.
	 */
	Keystore saveKeystore(Keystore keystore);
	
	/**
	 * Method that gets keystore by ID of Keystore.
	 * @param idKeystore Id of keystore.
	 * @return {@link Keystore} an object that represents the Keystore.
	 */
	Keystore getKeystoreById(String idKeystore);
	
	/**
	 * Method that stores a new entry into a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @param certificate Parameter that represents the certificate to store.
	 * @param key Parameter that represents the key to store.
	 * @param statusCert Value that represents the status of the certificate to store.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * It could be one of the following:
	 * <ul>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_CORRECT}</li>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_EXPIRED}</li>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_REVOKED}</li>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_OTHER}</li>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_NOTEXIST}</li>
	 *   <li>{@link StatusCertificateIdConstants#ID_SC_NOTVALIDYET}</li>
	 * </ul>
	 * If this is not specified, by default is taken {@link StatusCertificateIdConstants#ID_SC_CORRECT}.
	 * @param validationCert parameter that contain if certificate is valid.
	 * @return the updated keystore  object representation.
	 * @throws CryptographyException If the method fails.
	 */
	void storeCertificate(String alias, Certificate certificate, Key key, Long statusCert, boolean validationCert, Keystore ksEntity) throws CryptographyException;

	/**
	 * Method that updates an entry into a keystore.
	 * @param oldEntryAlias Parameter that represents the old alias of the entry.
	 * @param newEntryAlias Parameter that represents the new alias of the entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @return the updated keystore  object representation.
	 * @throws CryptographyException If the method fails.
	 */
	void updateCertificateAlias(String oldEntryAlias, String newEntryAlias, Keystore ksEntity) throws CryptographyException;

	/**
	 * Method that deletes a certificate entry from a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @return the updated keystore  object representation.
	 * @throws CryptographyException If the method fails.
	 */
	void removeEntry(String alias, Keystore ksEntity) throws CryptographyException;

	/**
	 * Method that obtains a X509 certificate from the alias.
	 * @param alias Parameter that represents the alias of the certificate to obtain.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @return an object that represents the certificate.
	 * @throws CryptographyException If the method fails.
	 */
	Certificate getCertificate(String alias, Keystore ksEntity) throws CryptographyException;
}
