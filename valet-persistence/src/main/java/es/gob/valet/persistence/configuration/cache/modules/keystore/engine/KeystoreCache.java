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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.keystore.engine.KeystoreCache.java.</p>
 * <b>Description:</b><p>Class to handle the Keystore configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.keystore.engine;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/**
 * <p>Class to handle the Keystore configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public class KeystoreCache extends ConfigurationCache {

	/**
	 * Constant attribute that represents the string to identify the base path inside the TreeCache for KeyStores .
	 */
	private static final String PATH_BASE = "KeyStores";

	/**
	 * Constant attribute that represents a separator token for the keystore ID.
	 */
	private static final String SEPARATOR_ID = "- ID: ";

	/**
	 * Constant attribute that represents a separator token for the keystore version.
	 */
	private static final String SEPARATOR_VERSION = " - Version: ";

	/**
	 * Constant attribute that represents the unique instance for the keystore clustered cache.
	 */
	private static KeystoreCache instance = null;

	/**
	 * Constructor method for the class KeystoreCache.java.
	 */
	public KeystoreCache() {
		super();
	}

	/**
	 * Gets the unique instance of the Keystore Clustered Cache.
	 * @return the unique instance of the Keystore Clustered Cache.
	 */
	public static KeystoreCache getInstance() {
		if (instance == null) {
			instance = new KeystoreCache();
		}
		return instance;
	}

	/**
	 * Method that adds a Keystore to the configuration cache. This method also will check that whether the Keystore already exists in the cache.
	 * The new Keystore to add must have a version number greater than the Keystore already exists.
	 * @param ks Parameter that represents the keystore pojo object from the data base.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link KeystoreCacheObject}. If the version cached is greater, then returns the cached object.
	 * @throws KeystoreCacheException If the method fails.
	 */
	public KeystoreCacheObject addKeystore(Keystore ks, boolean inLoadingCache) throws KeystoreCacheException {

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (ks == null) {
			throw new KeystoreCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG000));
		}

		KeystoreCacheObject kco = new KeystoreCacheObject(ks);
		kco = addKeystore(kco, inLoadingCache);
		return kco;

	}

	/**
	 * Method that adds a Keystore to the configuration cache. This method also will check that whether the Keystore already exists in the cache.
	 * The new Keystore to add must have a version number greater than the Keystore already exists.
	 * @param kco Parameter that represents the keystore cahe object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link KeystoreCacheObject}. If the version cached is greater, then returns the cached object.
	 * @throws KeystoreCacheException If the method fails.
	 */
	public KeystoreCacheObject addKeystore(KeystoreCacheObject kco, boolean inLoadingCache) throws KeystoreCacheException {

		KeystoreCacheObject result = null;

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (kco == null) {
			throw new KeystoreCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG000));
		}

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = String.valueOf(kco.getIdKeystore());

		try {
			result = (KeystoreCacheObject) addConfigurationCacheObject(path, kco, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new KeystoreCacheException(IValetException.COD_191, e.getErrorDescription(), e.getException());
		}

		return result;

	}

	/**
	 * Gets the Keystore identified by the input parameter from the caché.
	 * @param idKeystore Keystore identifier.
	 * @return A {@link KeystoreCacheObject} that represents the keystore to get. <code>null</code> if it does not exist.
	 * @throws KeystoreCacheException If the method fails.
	 */
	public KeystoreCacheObject getKeystore(long idKeystore) throws KeystoreCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = String.valueOf(idKeystore);

		try {
			return (KeystoreCacheObject) getConfigurationCacheObject(path, false);
		} catch (ConfigurationCacheException e) {
			throw new KeystoreCacheException(IValetException.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * This method remove all the keystores from the cache.
	 * @throws KeystoreCacheException In case of some error while is cleaning the keystores cache.
	 */
	public void clearKeystoreCache() throws KeystoreCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[1];
		path[0] = PATH_BASE;

		// Se eliminan todos los keystores de la cache.
		try {
			clearNodePathFromConfigurationCache(path, false);
		} catch (ConfigurationCacheException e) {
			throw new KeystoreCacheException(IValetException.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getTypeElement()
	 */
	@Override
	protected String getTypeElement() {
		return Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG003);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getObjectNameIdentifier(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getObjectNameIdentifier(ConfigurationCacheObject cco) {
		KeystoreCacheObject kco = (KeystoreCacheObject) cco;
		return kco.getName() + SEPARATOR_ID + kco.getIdKeystore() + SEPARATOR_VERSION + kco.getVersion();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#checkUpdateCondition(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected boolean checkUpdateCondition(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {

		boolean result = false;
		KeystoreCacheObject cachedKco = (KeystoreCacheObject) cachedCco;
		KeystoreCacheObject kco = (KeystoreCacheObject) cco;

		// Si el número de versión de lo que hay actualmente en caché es
		// inferior al que se quiere insertar,
		// es sobrescrito el elemento de la caché.
		if (cachedKco.getVersion() <= kco.getVersion()) {

			result = true;

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getMsgNotConditionForUpdateAccomplished(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getMsgNotConditionForUpdateAccomplished(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {
		KeystoreCacheObject cachedKco = (KeystoreCacheObject) cachedCco;
		KeystoreCacheObject kco = (KeystoreCacheObject) cco;
		return Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG001, new Object[ ] { kco.getName(), kco.getIdKeystore(), kco.getVersion(), cachedKco.getVersion() });
	}

}
