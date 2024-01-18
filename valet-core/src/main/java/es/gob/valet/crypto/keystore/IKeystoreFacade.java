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
 * <b>File:</b><p>es.gob.valet.crypto.keystore.IKeystoreFacade.java.</p>
 * <b>Description:</b><p>Interface that defines the methods to manage operations with keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 19/12/2023.
 */
package es.gob.valet.crypto.keystore;

import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.utils.IStatusCertificateIdConstants;
import es.gob.valet.persistence.exceptions.CryptographyException;

/**
 * <p>Interface that defines the methods to manage operations with keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 11/01/2024.
 */
public interface IKeystoreFacade {

	/**
	 * Method that obtains a list with all the aliases of the certificates stored inside of a keystore.
	 * @return a list with all the aliases of the certificates stored inside of a keystore.
	 * @throws CryptographyException If the method fails.
	 */
	List<String> getAllCertificateAlias() throws CryptographyException;

	/**
	 * Method that obtains a X509 certificate from the alias.
	 * @param alias Parameter that represents the alias of the certificate to obtain.
	 * @return an object that represents the certificate.
	 * @throws CryptographyException If the method fails.
	 */
	Certificate getCertificate(String alias) throws CryptographyException;

	/**
	 * Method that obtains a list with all the certificates stored inside of a keystore.
	 * @return a list with all the certificates stored inside of a keystore.
	 * @throws CryptographyException If the method fails.
	 */
	List<Certificate> getAllCertificates() throws CryptographyException;

	/**
	 * Method that obtains a private key from the alias.
	 * @param alias Parameter that represents the alias of the private key to obtain.
	 * @return an object that represents the private.
	 * @throws CryptographyException If the method fails.
	 */
	PrivateKey getPrivateKey(String alias) throws CryptographyException;

	/**
	 * Method that stores a new entry into a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @param certificate Parameter that represents the certificate to store.
	 * @param key Parameter that represents the key to store.
	 * @param statusCert Value that represents the status of the certificate to store. 
	 * It could be one of the following:
	 * <ul>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_CORRECT}</li>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_EXPIRED}</li>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_REVOKED}</li>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_OTHER}</li>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_NOTEXIST}</li>
	 *   <li>{@link IStatusCertificateIdConstants#ID_SC_NOTVALIDYET}</li>
	 * </ul>
	 * If this is not specified, by default is taken {@link IStatusCertificateIdConstants#ID_SC_CORRECT}.
	 * @param validationCert parameter that contain if certificate is valid.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	KeystoreCacheObject storeCertificate(String alias, Certificate certificate, Key key, Long statusCert, boolean validationCert) throws CryptographyException;

	/**
	 * Method that updates an entry into a keystore.
	 * @param oldEntryAlias Parameter that represents the old alias of the entry.
	 * @param newEntryAlias Parameter that represents the new alias of the entry.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	KeystoreCacheObject updateCertificateAlias(String oldEntryAlias, String newEntryAlias) throws CryptographyException;

	/**
	 * Method that deletes a certificate entry from a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	KeystoreCacheObject removeEntry(String alias) throws CryptographyException;

	/**
	 * Method that deletes an entries list of a keystore.
	 * @param aliasList Parameter that represents the list of aliases to delete.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	KeystoreCacheObject removeEntriesList(List<String> aliasList) throws CryptographyException;

	/**
	 * Method that obtains a keystore.
	 * @return an object that represents the keystore.
	 * @throws CryptographyException If the method fails.
	 */
	KeyStore getKeystore() throws CryptographyException;

	/**
	 * Method that gets the name of the keystore.
	 * @return The name of the keystore.
	 * @throws KeystoreCacheException In case of some error managing the keystore clustered cache.
	 * @throws CryptographyException In case of some error in a cryptographic operation.
	 */
	String getKeystoreName() throws KeystoreCacheException, CryptographyException;

	/**
	 * Method that obtains a keystore as bytes array.
	 * @return a keystore.
	 * @throws CryptographyException If the method fails.
	 */
	byte[ ] getKeystoreBytes() throws CryptographyException;

	/**
	 * Method that obtains the decoded password of a keystore.
	 * @return the decoded password of a keystore.
	 * @throws CryptographyException If the method fails.
	 */
	String getKeystorePassword() throws CryptographyException;

	/**
	 * Method that updates the password of a keystore.
	 * @param newPassword Parameter that represents the new password of the keystore.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	KeystoreCacheObject updateKeystorePassword(String newPassword) throws CryptographyException;

	/**
	 * Method that obtains the cryptography provider for the keystore.
	 * @return an object that represents the cryptography provider for the keystore.
	 * @throws CryptographyException If the method fails.
	 */
	Provider getProvider() throws CryptographyException;

	/**
	 * Method that obtains the information about the keystore from the cache system.
	 * @return an object that represents the information about the keystore from the cache system.
	 */
	KeystoreCacheObject getKeystoreCacheObject();

	/**
	 * Method that obtains a list with all the certificates stored inside of a keystore.
	 * @return a list with all the certificates stored inside of a keystore.
	 * @throws CryptographyException If the method fails.
	 */
	List<X509Certificate> getAllX509Certificates() throws CryptographyException;
	
	/**
	 * Method that obtains a list with all the alias/certificates stored inside of a keystore.
	 * @return a map with all the alias/certificates stored inside of a keystore.
	 */
	Map<String, X509Certificate> getAllAliasWithX509Certificates();

}
