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
 * <b>File:</b><p>es.gob.valet.crypto.keystore.KeystoreFactory.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 26 sept. 2018.
 */
package es.gob.valet.crypto.keystore;

import org.springframework.beans.factory.annotation.Autowired;

import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.utils.CryptographyValidationUtils;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreMessages;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;

/** 
 * <p>Class that manages the generation of the class which manages the keystores in the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 26 sept. 2018.
 */
public final class KeystoreFactory {
	
	/**
	 * Attribute that represents an instance of the class which manages all the operations related with keystores in the system.
	 */
	private IKeystoreFacade keystoreInstance = null;
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private IKeystoreService keystoreService; 
	
	/**
	 * Constructor method for the class KeystoreFactory.java.
	 * @param idKeystoreParam Parameter that represents the ID of the keystore in the database.
	 * @throws CryptographyException If the method fails.
	 */
	private KeystoreFactory(Long idKeystoreParam) throws CryptographyException {
			// Comprobamos que el ID del almacén de claves no es nulo
			if (idKeystoreParam == null) {
				throw new CryptographyException(Language.getResCoreValet(ICoreMessages.CRYPTO_012));
			}

			// Obtenemos el almacén de claves
			Keystore keystore = keystoreService.getKeystoreById(idKeystoreParam);
			// Comprobamos que el almacén de claves no es nulo
			CryptographyValidationUtils.checkIsNotNull(keystore, Language.getFormatResCoreValet(ICoreMessages.CRYPTO_013, new Object[ ] { idKeystoreParam }));

			keystoreInstance = new KeystoreFacade(keystore);
	}
	/**
	 * Method that obtains an instance of the class.
	 * @param idKeystoreParam Parameter that represents the ID of the keystore in the database.
	 * @return an instance of the class.
	 * @throws CryptographyException If the method fails.
	 */
	public static synchronized KeystoreFactory getInstance(Long idKeystoreParam) throws CryptographyException {
		return new KeystoreFactory(idKeystoreParam);
	}

	/**
	 * Method that obtains the concrete instance of {@link #keystoreInstance}.
	 * @return the concrete instance of {@link #keystoreInstance}.
	 */
	public IKeystoreFacade getKeystoreInstance() {
		return keystoreInstance;
	}

}
