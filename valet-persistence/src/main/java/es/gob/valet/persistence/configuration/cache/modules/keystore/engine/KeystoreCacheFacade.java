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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.keystore.engine.KeystoreCacheFacade.java.</p>
 * <b>Description:</b><p>Facade for all the Keystore configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.persistence.configuration.cache.modules.keystore.engine;

import java.util.List;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.PersistenceCacheMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/**
 * <p>Facade for all the Keystore configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public final class KeystoreCacheFacade {

	/**
	 * Attribute that represents the unique instance of the class.
	 */
	private static KeystoreCacheFacade instance = null;

	/**
	 * Constructor method for the class KeystoreCacheFacade.java.
	 */
	private KeystoreCacheFacade() {
		super();
	}

	/**
	 * Gets the unique instance of the TSL cache facade.
	 * @return the unique instance of the TSL cache facade.
	 */
	public static KeystoreCacheFacade getInstance() {

		if (instance == null) {
			instance = new KeystoreCacheFacade();
		}
		return instance;

	}

	/**
	 * Adds or update the keystore in the configuration cache.
	 * @param kco Object reprensentation of the keystore in the configuration cache.
	 * @return Keystore cache object added/updated in the configuration cache.
	 * @throws KeystoreCacheException In case of some error adding/updating the keystore in the cache.
	 */
	public KeystoreCacheObject addUpdateKeystore(KeystoreCacheObject kco) throws KeystoreCacheException {
		return KeystoreCache.getInstance().addKeystore(kco, false);
	}

	/**
	 * Gets the keystore representation from the configuration cache.
	 * @param idKeystore Keystore identifier.
	 * @return A object representation of the TSL data in the configuration cache. <code>null</code> if it does not exist.
	 * @throws KeystoreCacheException In case of some error getting the keystore from the configuration cache.
	 */
	public KeystoreCacheObject getKeystoreCacheObject(long idKeystore) throws KeystoreCacheException {

		// Lo intentamos obtener de la caché.
		KeystoreCacheObject result = KeystoreCache.getInstance().getKeystore(idKeystore);

		// Si no lo hemos obtenido de la caché, lo buscamos en base de datos...
		if (result == null) {

			// Lo extraemos de base de datos.
			Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(idKeystore, false);

			// Si existe en base de datos...
			if (ks != null) {

				// Creamos su representación en caché.
				KeystoreCacheObject kco = new KeystoreCacheObject(ks);

				// Lo añadimos en la caché y lo devolvemos.
				result = KeystoreCache.getInstance().addKeystore(kco, false);

			}

		}

		return result;
	}

	/**
	 * Load all the keystores in the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws KeystoreCacheException In case of some error adding the keystores in the configuration cache.
	 */
	public void initializeAllKeystores(boolean inLoadingCache) throws KeystoreCacheException {

		// Obtenemos de base de datos todos los keystores.
		List<Keystore> keystoreList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getAllKeystore();

		// Si la lista de keystores no es nula ni vacía...
		if (keystoreList != null && !keystoreList.isEmpty()) {

			// Recorremos la lista y vamos añadiendo los keystores...
			for (Keystore keystore: keystoreList) {
				KeystoreCache.getInstance().addKeystore(keystore, inLoadingCache);
			}

		} else {

			throw new KeystoreCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG007));

		}

	}

}
