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
 * <b>File:</b><p>es.gob.valet.cache.impl.ACacheValet.java.</p>
 * <b>Description:</b><p>Abstract class that represents a {@link ICacheValet} with the principal functions
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 18/10/2018.
 */
package es.gob.valet.cache.impl;

import org.apache.log4j.Logger;

import es.gob.valet.cache.exceptions.BadPathCacheValetException;
import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.cache.exceptions.ManagingObjectCacheValetException;
import es.gob.valet.cache.ifaces.ICacheValet;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICacheGeneralMessages;

/**
 * <p>Abstract class that represents a {@link ICacheValet} with the principal functions
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 18/10/2018.
 */
public abstract class ACacheValet implements ICacheValet {

	/**
	 * Attribute that represents the singleton instance of the cache.
	 */
	private static ICacheValet instance = null;

	/**
	 * Flag that indicates if already a cache instance has been created
	 * correctly (<code>true</code>) or not (<code>false</code>).
	 */
	private static boolean creationFlag = false;

	/**
	 * Constructor method for the class ACacheValet.java.
	 */
	public ACacheValet() {
		super();
	}

	/**
	 * Gets the logger of the implementation cache.
	 * @return Logger instance of the implementation cache.
	 */
	protected abstract Logger getLogger();

	/**
	 * Gets the value of the attribute {@link #instance}.
	 * @return the value of the attribute {@link #instance}.
	 */
	public static final ICacheValet getInstance() {
		return instance;
	}

	/**
	 * Sets the value of the attribute {@link #instance}.
	 * @param instanceParam The value for the attribute {@link #instance}.
	 */
	public static final void setInstance(ICacheValet instanceParam) {
		instance = instanceParam;
	}

	/**
	 * Gets the value of the attribute {@link #creationFlag}.
	 * @return the value of the attribute {@link #creationFlag}.
	 */
	public static final boolean isCreationFlag() {
		return creationFlag;
	}

	/**
	 * Sets the value of the attribute {@link #creationFlag}.
	 * @param creationFlagParam The value for the attribute {@link #creationFlag}.
	 */
	public static final void setCreationFlag(boolean creationFlagParam) {
		creationFlag = creationFlagParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#stopCache()
	 */
	@Override
	public void stopCache() throws CacheValetException {
		if (instance != null) {
			stopCacheImplementation();
		}
	}

	/**
	 * Stops the specified implementation cache.
	 * @throws CacheValetException In case of some error stopping the cache.
	 */
	protected abstract void stopCacheImplementation() throws CacheValetException;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#addObject(java.lang.String[], java.lang.Object, boolean)
	 */
	@Override
	public void addObject(String[ ] path, Object obj, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		// Construimos el path completo a partir del array recibido.
		String fullPath = buildPath(path, false);

		// Añadimos en el path indicado el objeto recibido.
		addObject(fullPath, path[path.length - 1], obj, inLoadingCache);

	}

	/**
	 * Private method that constructs the string that represents the path to access to the cache.
	 * @param path String array that determine the path in which the indicated object is going to be constructed.
	 * @param isNodePath Flag that indicates if the path represents the route to a node (<code>true</code>) or a object (<code>false</code>).
	 * @return string that represents completely the path indicated in the parameter of entry.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 */
	protected final String buildPath(String[ ] path, boolean isNodePath) throws BadPathCacheValetException {

		if (path == null) {
			throw new BadPathCacheValetException(IValetException.COD_155, Language.getResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_003));
		}

		if (path.length == 0) {
			throw new BadPathCacheValetException(IValetException.COD_155, Language.getResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_005));
		}

		for (int index = 0; index < path.length; index++) {
			if (UtilsStringChar.isNullOrEmptyTrim(path[index])) {
				throw new BadPathCacheValetException(IValetException.COD_155, Language.getResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_006));
			}
		}

		StringBuffer pathCompleto = new StringBuffer(UtilsStringChar.EMPTY_STRING);
		int endIndex = isNodePath ? path.length : path.length - 1;
		for (int index = 0; index < endIndex; index++) {
			pathCompleto.append(getSeparator());
			pathCompleto.append(path[index].trim());
		}

		return pathCompleto.toString();

	}

	/**
	 * Method that adds an object in the path indicated with the input key in the <i>Cache</i>.
	 * @param path {@link String} with the path where to add the object in the cache.
	 * @param key Key for the object to add.
	 * @param value Object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws BadPathCacheValetException In case of the path to access in the TreeCache is incorrect.
	 * @throws ManagingObjectCacheValetException In case of some error while is adding the object in the Cache.
	 */
	private void addObject(String path, Object key, Object value, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		checkPathAndKey(path, key);

		try {

			// Añadimos en el path indicado el objeto recibido.
			addObjectImplCache(path, key, value, inLoadingCache);

			getLogger().debug(Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_002, new Object[ ] { path + getSeparator() + key }));

		} catch (Exception e) {
			throw new ManagingObjectCacheValetException(IValetException.COD_154, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_001, new Object[ ] { e }));
		}

	}

	/**
	 * Method that checks if the path and the key are consistent to use in
	 * the cache.
	 * @param path {@link String} with the access path in the cache.
	 * @param key {@link Object} that represents the key to get/store a object
	 * in the cache.
	 * @throws BadPathCacheValetException In case of the path or the key was incorrect.
	 */
	protected final void checkPathAndKey(String path, Object key) throws BadPathCacheValetException {

		if (UtilsStringChar.isNullOrEmptyTrim(path)) {
			throw new BadPathCacheValetException(IValetException.COD_155, Language.getResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_003));
		} else if (!path.startsWith(getSeparator())) {
			throw new BadPathCacheValetException(IValetException.COD_155, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_004, new Object[ ] { getSeparator() }));
		} else if (key == null) {
			throw new BadPathCacheValetException(IValetException.COD_155, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_004, new Object[ ] { path }));
		}

	}

	/**
	 * Method that adds an object in the path indicated with the input key in the implemented <i>Cache</i>.
	 * @param path {@link String} with the path where to add the object in the TreeCache.
	 * @param key Key for the object to add.
	 * @param value Object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws CacheValetException In case of some error while is adding the object in the Cache.
	 */
	protected abstract void addObjectImplCache(String path, Object key, Object value, boolean inLoadingCache) throws CacheValetException;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#getObject(java.lang.String[], boolean)
	 */
	@Override
	public Object getObject(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		// Construimos el path completo a partir del array recibido.
		String fullPath = buildPath(path, false);

		// Recuperamos el objeto (si es que existe) del path indicado.
		return getObject(fullPath, path[path.length - 1], inLoadingCache);

	}

	/**
	 * Method that obtains the <i>Cache</i> object indicated by the path and the key.
	 * @param path {@link String} with the access path in the cache.
	 * @param key {@link Object} that represents the key to get a object.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object placed in the cache, in the indicated path, or null if it does not exist.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException In case of some error getting the object from the cache.
	 */
	protected final Object getObject(String path, Object key, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		checkPathAndKey(path, key);

		Object result = null;

		try {
			result = getObjectImplCache(path, key, inLoadingCache);
		} catch (Exception e) {
			throw new ManagingObjectCacheValetException(IValetException.COD_154, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_007, new Object[ ] { e }));
		}

		if (result == null) {
			getLogger().debug(Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_008, new Object[ ] { path + getSeparator() + key }));
		} else {
			getLogger().debug(Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_009, new Object[ ] { path + getSeparator() + key }));
		}

		return result;

	}

	/**
	 * Method that obtains from the implemented <i>Cache</i> object indicated by the path and the key.
	 * @param path {@link String} with the access path in the cache.
	 * @param key {@link Object} that represents the key to get a object.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object placed in the cache, in the indicated path, or null if it does not exist.
	 * @throws CacheValetException In case of some error getting the object from the implemented cache.
	 */
	protected abstract Object getObjectImplCache(String path, Object key, boolean inLoadingCache) throws CacheValetException;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#removeObject(java.lang.String[], boolean)
	 */
	@Override
	public Object removeObject(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		// Si el path tan solo contiene la ruta base.
		if (path.length == 1) {

			// Se indica que sobre el raiz se elimine el elemento base.
			return removeObject(getSeparator(), path[0], inLoadingCache);

		} else {
			// En caso contrario:
			// Construimos el path completo a partir del array recibido.
			String fullPath = buildPath(path, false);
			// Eliminamos el objeto de la caché.
			return removeObject(fullPath, path[path.length - 1], inLoadingCache);

		}

	}

	/**
	 * Method that eliminates the object contained in the indicated path of the <i>Cache</i>.
	 * @param path {@link String} with the access path in the cache.
	 * @param key {@link Object} that represents the key to remove a object.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object removed from the cache, in the indicated path, or null if it does not exist.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException In case of some error while is removing the entry from the cache.
	 */
	protected final Object removeObject(String path, Object key, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		checkPathAndKey(path, key);

		Object result = null;

		try {
			result = removeObjectImplCache(path, key, inLoadingCache);
		} catch (Exception e) {
			throw new ManagingObjectCacheValetException(IValetException.COD_154, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_010, new Object[ ] { e }));
		}

		getLogger().debug(Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_011, new Object[ ] { path + getSeparator() + key }));

		return result;

	}

	/**
	 * Method that eliminates the object contained in the indicated path of the implemented <i>Cache</i>.
	 * @param path {@link String} with the access path in the cache.
	 * @param key {@link Object} that represents the key to remove a object.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object removed from the cache, in the indicated path, or null if it does not exist.
	 * @throws CacheValetException In case of some error while is removing the entry from the implementedcache.
	 */
	protected abstract Object removeObjectImplCache(String path, Object key, boolean inLoadingCache) throws CacheValetException;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#removeNode(java.lang.String[], boolean)
	 */
	@Override
	public boolean removeNode(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException {

		boolean result = false;

		// Construimos el path completo a partir del array recibido.
		String fullPath = buildPath(path, true);

		try {
			result = removeNodeImplCache(fullPath, inLoadingCache);
		} catch (Exception e) {
			throw new ManagingObjectCacheValetException(IValetException.COD_154, Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_012, new Object[ ] { e }));
		}

		getLogger().debug(Language.getFormatResCacheGeneral(ICacheGeneralMessages.CACHE_IMPL_013, new Object[ ] { fullPath }));

		return result;

	}

	/**
	 * Method that eliminates the node contained in the input path of the implemented <i>Cache</i>.
	 * @param fullPath {@link String} with the access path in the cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return <code>true</code> if the node has been removed, otherwise <code>false</code>.
	 * @throws Exception In case of some error while is removing the node from the implemented cache.
	 */
	protected abstract boolean removeNodeImplCache(String fullPath, boolean inLoadingCache) throws Exception;

}
