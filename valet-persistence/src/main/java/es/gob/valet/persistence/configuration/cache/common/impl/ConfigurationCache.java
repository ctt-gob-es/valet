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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache.java.</p>
 * <b>Description:</b><p>Class to handle the configuration objects in the ValET Cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 22/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.common.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import es.gob.valet.cache.FactoryCacheValet;
import es.gob.valet.cache.exceptions.BadPathCacheValetException;
import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.cache.exceptions.ManagingObjectCacheValetException;
import es.gob.valet.cache.ifaces.ICacheValet;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;

/**
 * <p>Class to handle the configuration objects in the ValET Cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/10/2018.
 */
public abstract class ConfigurationCache {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConfigurationCache.class);

	/**
	 * Constant attribute that represents a separator token for the TSA platform ID.
	 */
	protected static final String SEPARATOR_ID = "ID: ";

	/**
	 * Constant attribute that represents the string to identify the base path inside the cache for the Configuration.
	 */
	private static final String PATH_BASE = "Configuration";

	/**
	 * Constant attribute that represents the string to identify the subpath inside the cache for the Configuration flag initialization.
	 */
	private static final String PATH_INIT = "InitializationFlag";

	/**
	 * Constructor method for the class ConfigurationCache.java.
	 */
	protected ConfigurationCache() {
		super();
	}

	/**
	 * Gets the unique instance of the configuration cache.
	 * @throws ConfigurationCacheException In case of some error initializating the configuration cache.
	 * @return the unique instance of the configuration cache.
	 */
	private ICacheValet getCacheValet() throws ConfigurationCacheException {

		try {
			return FactoryCacheValet.getCacheValetInstance();
		} catch (CacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG000), e);
		}

	}

	/**
	 * Method that adds a Configuration Object to the Cache. This method also will checks whether the object already exists in the cache.
	 * The new object to add must satisfy some conditions specified in {@link #checkUpdateCondition(ConfigurationCacheObject, ConfigurationCacheObject)}.
	 * @param path Path inside the cache where add the object.
	 * @param cco Parameter that represents the configuration cahe object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link ConfigurationCacheObject}. If the condition is accomplished, then returns the cached object.
	 * @throws ConfigurationCacheException If the method fails adding the new object in the cache.
	 */
	protected final ConfigurationCacheObject addConfigurationCacheObject(String[ ] path, ConfigurationCacheObject cco, boolean inLoadingCache) throws ConfigurationCacheException {

		ConfigurationCacheObject result = null;

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (cco == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG001));
		} else if (path == null) {
			// Comprobamos que la ruta de entrada no sea nula.
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG002));
		}

		// Se indica en el log que se va a añadir un nuevo elemento a la caché
		// de configuración.
		LOGGER.debug(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG006, new Object[ ] { getTypeElement() + UtilsStringChar.SPECIAL_BLANK_SPACE_STRING + getObjectNameIdentifier(cco) }));

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		if (addConfigurationCacheObjectInPath(newPath, cco, inLoadingCache)) {
			LOGGER.debug(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG003, new Object[ ] { getTypeElement() + UtilsStringChar.SPECIAL_BLANK_SPACE_STRING + getObjectNameIdentifier(cco) }));
			result = cco;
		} else {
			result = getConfigurationCacheObjectFromPath(newPath, inLoadingCache);
		}

		return result;

	}

	/**
	 * Method that add a number ({@link Long} identifier) in a specific path inside the configuration cache.
	 * @param path Path inside the configuration cache where add the identifier.
	 * @param identifier {@link Long} number that represents a identifier.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error while is adding the identifier in the configuration cache.
	 */
	protected final void addIdentifier(String[ ] path, long identifier, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG002));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Se añade el identificador en la ruta indicada.
		try {
			getCacheValet().addObject(newPath, Long.valueOf(identifier), inLoadingCache);
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		}

	}

	/**
	 * Method that add a {@link String} in a specific path inside the configuration cache.
	 * @param path Path inside the configuration cache where add the {@link String}.
	 * @param string String to add in the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error while is adding the {@link String}.
	 */
	protected final void addString(String[ ] path, String string, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG002));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Se añade la cadena en la ruta indicada.
		try {
			getCacheValet().addObject(newPath, string, inLoadingCache);
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		}

	}

	/**
	 * Method that add a {@link Map} in a specific path inside the configuration cache.
	 * @param path Path inside the configuration cache where add the {@link Map}.
	 * @param map Map to add in the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error while is adding the {@link Map}.
	 */
	protected final void addMap(String[ ] path, Map<?, ?> map, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG002));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Se añade la cadena en la ruta indicada.
		try {
			getCacheValet().addObject(newPath, map, inLoadingCache);
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);
		}

	}

	/**
	 * Gets the type element name to show in log.
	 * @return String with the element type name.
	 */
	protected abstract String getTypeElement();

	/**
	 * Method that gets a {@link String} that identifies the Configuration Cache Object for show in the logs.
	 * @param cco Configuration Cache Object from where extract the information,
	 * @return Identifier in a string format of the configuration cache object.
	 */
	protected abstract String getObjectNameIdentifier(ConfigurationCacheObject cco);

	/**
	 * Method that adds a {@link ConfigurationCacheObject} object in the Cache in the appropriate path.
	 * @param path Parameter that represents the path inside the Cache where to store the Configuration Object.
	 * @param cco Parameter that represents the configuration object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return <code>true</code> if the configuration object has been added, otherwise <code>false</code>.
	 * @throws ConfigurationCacheException If the method fails.
	 */
	private boolean addConfigurationCacheObjectInPath(String[ ] path, ConfigurationCacheObject cco, boolean inLoadingCache) throws ConfigurationCacheException {

		boolean result = false;

		try {

			// Buscamos el ConfigurationObject dentro de la caché a ver si ya lo
			// tenemos.
			ConfigurationCacheObject actualCco = null;
			Object cacheObject = getCacheValet().getObject(path, inLoadingCache);
			if (cacheObject != null) {
				actualCco = (ConfigurationCacheObject) cacheObject;
			}

			// Si aún no existe en la caché, insertamos el nuevo
			// ConfigurationObject.
			if (actualCco == null) {

				getCacheValet().addObject(path, cco, inLoadingCache);
				result = true;

			} else {

				// En caso de existir ya en la caché, comprobamos que la
				// condición establecida por el objeto para ser actualizado.
				// Si la condición no se cumple, el mismo método debe mostrar el
				// correspondiente mensaje de advertencia.
				if (checkUpdateCondition(actualCco, cco)) {

					getCacheValet().addObject(path, cco, inLoadingCache);
					result = true;

				} else {

					LOGGER.warn(getMsgNotConditionForUpdateAccomplished(actualCco, cco));

				}

			}

		} catch (BadPathCacheValetException e) {

			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);

		} catch (ManagingObjectCacheValetException e) {

			throw new ConfigurationCacheException(IValetException.COD_191, e.getMessage(), e);

		}

		return result;

	}

	/**
	 * This method checks if the actual Configuration Cache Object must be updated by the new version of it.
	 * @param cachedCco Actual Configuration Cache Object in cache.
	 * @param cco Configuration Cache Object that we want insert in the cache.
	 * @return <code>true</code> if the object must be updated, otherwise <code>false</code>.
	 */
	protected abstract boolean checkUpdateCondition(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco);

	/**
	 * Gets the message to warn in the log that the update condition has not been accomplished.
	 * @param cachedCco Actual Configuration Cache Object in cache.
	 * @param cco Configuration Cahce Object that we want insert in the cache.
	 * @return {@link String} with the message to warn in the log that the update condition has not been accomplished.
	 */
	protected abstract String getMsgNotConditionForUpdateAccomplished(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco);

	/**
	 * Gets the Configuration Object from the specified path inside the cache.
	 * @param path Array of {@link String} that represents the path inside the cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The configuration object obtained from the specified path. <code>null</code> if that path
	 * does not exist.
	 * @throws ConfigurationCacheException If the method fails.
	 */
	protected final ConfigurationCacheObject getConfigurationCacheObject(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG004));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Obtenemos el objeto de la ruta.
		return getConfigurationCacheObjectFromPath(newPath, inLoadingCache);

	}

	/**
	 * Gets the Configuration Object from the path in the configuration cache.
	 * @param path Path inside the cache of the configuration object to get.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return {@link ConfigurationCacheObject} that represents the configuration object in the cache. <code>null</code> if it does not exist.
	 * @throws ConfigurationCacheException In case of some error while is getting the configuration object from the cache.
	 */
	private ConfigurationCacheObject getConfigurationCacheObjectFromPath(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		ConfigurationCacheObject kco = null;
		try {
			Object cacheObject = getCacheValet().getObject(path, inLoadingCache);
			if (cacheObject != null) {
				kco = (ConfigurationCacheObject) cacheObject;
			}
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

		return kco;

	}

	/**
	 * Gets the identifier from the path in the configuration cache.
	 * @param path Path inside the configuration cache of the identifier to get.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return a long number that represents the identifier. -1 if it does not exist.
	 * @throws ConfigurationCacheException In case of some error getting the identifier from the configuration cache.
	 */
	protected final long getIdentifierFromPath(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		long result = -1;

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG004));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Obtenemos el identificador de la caché.
		try {
			Object cacheObject = getCacheValet().getObject(newPath, inLoadingCache);
			if (cacheObject != null) {
				result = ((Long) cacheObject).longValue();
			}
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

		return result;

	}

	/**
	 * Gets the {@link String} from the path in the configuration cache.
	 * @param path Path inside the configuration cache of the identifier to get.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return a long number that represents the identifier. <code>null</code> if it does not exist.
	 * @throws ConfigurationCacheException In case of some error getting the identifier from the configuration cache.
	 */
	protected final String getStringFromPath(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		String result = null;

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG004));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Obtenemos el identificador de la caché.
		try {
			Object cacheObject = getCacheValet().getObject(newPath, inLoadingCache);
			if (cacheObject != null) {
				result = (String) cacheObject;
			}
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

		return result;

	}

	/**
	 * Gets the {@link Map} from the path in the configuration cache.
	 * @param path Path inside the configuration cache of the map to get.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return a map. <code>null</code> if it does not exist.
	 * @throws ConfigurationCacheException In case of some error getting the map from the configuration cache.
	 */
	protected final Map<?, ?> getMapFromPath(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		Map<?, ?> result = null;

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG004));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Obtenemos el map de la caché.
		try {
			Object cacheObject = getCacheValet().getObject(newPath, inLoadingCache);
			if (cacheObject != null) {
				result = (Map<?, ?>) cacheObject;
			}
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

		return result;

	}

	/**
	 * Method that clear a specified object path from the configuration cache.
	 * @param path Path where is the object to remove in the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error removing the object from the cache.
	 */
	protected final void clearObjectPathFromConfigurationCache(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (path == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG005));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[path.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(path, 0, newPath, 1, path.length);

		// Eliminamos el objeto situado en la ruta de la caché.
		removeObjectPathFromConfigurationCache(newPath, inLoadingCache);

	}

	/**
	 * Private method that removes all the configuration cache object inside the input path.
	 * @param path Path in the configuration cache to the object to be removed.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error while is cleaning the path inside the cache.
	 */
	private void removeObjectPathFromConfigurationCache(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		try {
			getCacheValet().removeObject(path, inLoadingCache);
			LOGGER.debug(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG007, new Object[ ] { buildFullPathString(path) }));
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

	}

	/**
	 * Method that clear a specified node from the configuration cache.
	 * @param nodePath Path to remove in the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error cleaning the path inside the configuration cache.
	 */
	protected final void clearNodePathFromConfigurationCache(String[ ] nodePath, boolean inLoadingCache) throws ConfigurationCacheException {

		// Comprobamos que la ruta de entrada no sea nula.
		if (nodePath == null) {
			throw new ConfigurationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG005));
		}

		// Se construye la ruta en la caché.
		String[ ] newPath = new String[nodePath.length + 1];
		newPath[0] = PATH_BASE;
		System.arraycopy(nodePath, 0, newPath, 1, nodePath.length);

		// Eliminamos el nodo (y los subnodos) de la ruta indicada de la caché.
		removeNodePathFromConfigurationCache(newPath, inLoadingCache);

	}

	/**
	 * Private method that removes all the configuration cache object inside the input path.
	 * @param path Path in the configuration cache to be removed.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error while is cleaning the path inside the cache.
	 */
	private void removeNodePathFromConfigurationCache(String[ ] path, boolean inLoadingCache) throws ConfigurationCacheException {

		try {
			getCacheValet().removeNode(path, inLoadingCache);
			LOGGER.debug(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG034, new Object[ ] { buildFullPathString(path) }));
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

	}

	/**
	 * Method that clear all the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error cleaning the configuration configuration cache.
	 */
	protected final void clearAllConfigurationCache(boolean inLoadingCache) throws ConfigurationCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[1];
		path[0] = PATH_BASE;

		// Eliminamos el nodo (y los subnodos) de la ruta indicada de la caché.
		removeNodePathFromConfigurationCache(path, inLoadingCache);

	}

	/**
	 * Private auxiliar method to build a string path from an array of string.
	 * @param path Array of {@link String} that represents the path.
	 * @return String obtained from the input array.
	 */
	private String buildFullPathString(String[ ] path) {

		String result = UtilsStringChar.EMPTY_STRING;

		if (path != null && path.length > 0) {
			for (String subpath: path) {
				result += FactoryCacheValet.getCacheValetSeparator() + subpath;
			}
		}

		return result;

	}

	/**
	 * Check if the configuration cache has ben initialized.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return <code>true</code> if the configuration cache has been initialized, otherwise <code>false</code>.
	 * @throws ConfigurationCacheException In case of some error getting from the configuration cache the flag that
	 * indicates if the configuration cache has been initialized.
	 */
	public final boolean isInitialized(boolean inLoadingCache) throws ConfigurationCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = PATH_INIT;

		Boolean result = null;
		try {
			Object treeCacheObject = getCacheValet().getObject(path, inLoadingCache);
			if (treeCacheObject == null) {
				result = Boolean.FALSE;
			} else {
				result = (Boolean) treeCacheObject;
			}

		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

		return result.booleanValue();

	}

	/**
	 * Sets the value of the flag that indicates if the configuration cache has been initialized.
	 * @param isInitialized value to assign to the flag.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws ConfigurationCacheException In case of some error setting the flag value.
	 */
	public final void setInitializedFlag(boolean isInitialized, boolean inLoadingCache) throws ConfigurationCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = PATH_INIT;

		try {
			getCacheValet().addObject(path, Boolean.valueOf(isInitialized), inLoadingCache);
		} catch (BadPathCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		} catch (ManagingObjectCacheValetException e) {
			throw new ConfigurationCacheException(IValetException.COD_191, e.toString(), e);
		}

	}

	/**
	 * Method that starts the auxiliar configuration cache for reloading all the configuration.
	 * @throws ConfigurationCacheException In case of some error initializing the configuration cache.
	 */
	public final void startsAuxiliarCache() throws ConfigurationCacheException {

		getCacheValet().startsAuxiliarCache();

	}

	/**
	 * Method that assigns the auxiliar configuration cache as principal, an then stop the oldest.
	 * @param millisecondsBeforeStopCache number of milliseconds before stop the old cache.
	 * @throws ConfigurationCacheException In case of some error initializing the configuration cache.
	 */
	public final void assignAsPrincipalAuxiliarCache(int millisecondsBeforeStopCache) throws ConfigurationCacheException {

		getCacheValet().assignAsPrincipalAuxiliarCache(millisecondsBeforeStopCache);

	}

	/**
	 * Gets the actual name of the configuration cache.
	 * @return the actual name of the configuration cache.
	 * @throws ConfigurationCacheException In case of some error initializing the configuration cache.
	 */
	public final String getCacheName() throws ConfigurationCacheException {

		return getCacheValet().getCacheName();

	}

	/**
	 * Checks if actually is reloading the configuration cache.
	 * @return <code>true</code> if configuration cache is reloading at the moment, otherwise <code>false</code>.
	 * @throws ConfigurationCacheException In case of some error initializing the configuration cache.
	 */
	public final boolean isReloadingCacheAtTheMoment() throws ConfigurationCacheException {

		return getCacheValet().isReloadingCacheAtTheMoment();

	}

}
