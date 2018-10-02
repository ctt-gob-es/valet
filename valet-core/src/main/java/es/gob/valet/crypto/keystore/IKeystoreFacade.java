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
 * <b>Description:</b><p> Interface that defines the methods to manage operations with keystores.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>26 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 26 sept. 2018.
 */
package es.gob.valet.crypto.keystore;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/** 
 * <p>Interface that defines the methods to manage operations with keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 26 sept. 2018.
 */
public interface IKeystoreFacade {
	/**
	 * Method that stores a new entry into a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @param certificate Parameter that represents the certificate to store.
	 * @param key Parameter that represents the key to store.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	Keystore storeCertificate(String alias, Certificate certificate, Key key) throws CryptographyException;
	
	/**
	 * Method that updates an entry into a keystore.
	 * @param oldEntryAlias Parameter that represents the old alias of the entry.
	 * @param newEntryAlias Parameter that represents the new alias of the entry.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	Keystore updateCertificate(String oldEntryAlias, String newEntryAlias) throws CryptographyException;
	
	/**
	 * Method that deletes a certificate entry from a keystore.
	 * @param alias Parameter that represents the alias of the entry.
	 * @return the updated keystore cache object representation.
	 * @throws CryptographyException If the method fails.
	 */
	Keystore deleteCertificate(String alias) throws CryptographyException;
	
	/**
	 * Method that gets the keystore type given a file name.
	 * @param nameFile The keystore file name
	 * @return String that represents the keystore type.
	 */
	String getKeystoreType(String nameFile);
	
	/**
	 * Method that lists the aliases of the certificates stored in the given keystore.
	 * @param ks The keystore whose aliases are listed.
	 * @return List<String> that represents the list of aliases of the given Keystore 
	 * @throws KeyStoreException If the method fails.
	 */
	List<String> listAllAliases(KeyStore ks) throws KeyStoreException;
	/**
	 * Method that obtains a certificate from the alias.
	 * @param alias Parameter that represents the alias of the certificate to obtain.
	 * @return an object that represents the certificate.
	 * @throws CryptographyException If the method fails.
	 */
	X509Certificate getCertificate(String alias) throws CryptographyException;
}
