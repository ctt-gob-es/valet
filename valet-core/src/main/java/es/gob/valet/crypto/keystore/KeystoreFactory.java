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
 * <b>Description:</b><p>Class that manages the generation of the class which manages the keystores in the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 06/11/2018.
 */
package es.gob.valet.crypto.keystore;

import java.util.HashMap;
import java.util.Map;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.exceptions.CryptographyException;
import es.gob.valet.persistence.utils.CryptographyValidationUtils;

/**
 * <p>Class that manages the generation of the class which manages the keystores in the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 06/11/2018.
 */
public final class KeystoreFactory {

	/**
	 * Attribute that represents a map with the keystores facade already loaded.
	 */
	private static Map<Long, IKeystoreFacade> keystoresMap = new HashMap<Long, IKeystoreFacade>();

	/**
	 * Constructor method for the class KeystoreFactory.java.
	 */
	private KeystoreFactory() {
		super();
	}

	/**
	 * Method that obtains an instance of a specified keystore.
	 * @param idKeystore Parameter that represents the ID of the keystore to get.
	 * @return an instance of the keystore facade.
	 * @throws CryptographyException If the method fails.
	 */
	public static synchronized IKeystoreFacade getKeystoreInstance(Long idKeystore) throws CryptographyException {

		// Comprobamos que no sea nulo...
		if (idKeystore == null) {
			throw new CryptographyException(IValetException.COD_190, Language.getResCoreGeneral(ICoreGeneralMessages.KEYSTORE_FACTORY_001));
		}

		// Lo intentamos obtener del map...
		IKeystoreFacade result = keystoresMap.get(idKeystore);
		// Si no lo hemos encontrado...
		if (result == null) {

			// Obtenemos el almacén de claves de la caché.
			KeystoreCacheObject kco = null;
			try {
				kco = ConfigurationCacheFacade.keystoreGetKeystoreCacheObject(idKeystore);
			} catch (KeystoreCacheException e) {
				throw new CryptographyException(IValetException.COD_190, Language.getFormatResCoreGeneral(ICoreGeneralMessages.KEYSTORE_FACTORY_002, new Object[ ] { idKeystore }), e);
			}

			// Comprobamos que no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(kco, Language.getFormatResCoreGeneral(ICoreGeneralMessages.KEYSTORE_FACTORY_002, new Object[ ] { idKeystore }));

			// Comprobamos si vamos a acceder a un Keystore hardware o no...
			if (kco.isHardware()) {
				// TODO Aún no contemplamos el uso de HSM en valET.
			} else {
				result = new StandardKeystoreFacade(kco);
			}
			
			// Lo añadimos en el map.
			keystoresMap.put(idKeystore, result);

		}

		return result;

	}
	
	/**
	 * Clears the map where it is cached all the keystore facades representations.
	 */
	public static void forceReloadKeystoreFactory() {
		keystoresMap.clear();
	}

}
