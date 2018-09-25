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
 * <b>File:</b><p>es.gob.valet.cache.impl.javaConcurrentMap.ConcurrentMapCacheValet.java.</p>
 * <b>Description:</b><p>Implementation of the {@link ICacheValet} that extends the {@link ACacheValet}
 * implemented with the Java Concurrent HashMap.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/09/2018.
 */
package es.gob.valet.cache.impl.javaConcurrentMap;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.cache.ifaces.ICacheValet;
import es.gob.valet.cache.impl.ACacheValet;
import es.gob.valet.cache.utils.GiveSomeTimeBeforeStopCacheThread;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICacheMessages;

/**
 * <p>Implementation of the {@link ICacheValet} that extends the {@link ACacheValet}
 * implemented with the Java Concurrent HashMap.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/09/2018.
 */
public class ConcurrentMapCacheValet extends ACacheValet {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConcurrentMapCacheValet.class);

	/**
	 * Attribute that represents and manages the cache.
	 */
	private static ConcurrentMap<Object, Object> concurrentMapCache = null;

	/**
	 * Attribute that represents the name of the actual cache.
	 */
	private static String concurrentMapCacheName = null;

	/**
	 * Attribute that represents and manages an auxiliar cache to reload all the configuration.
	 */
	private static ConcurrentMap<Object, Object> concurrentMapCacheReloading = null;

	/**
	 * Attribute that represents the name of the cache that is reloading.
	 */
	private static String concurrentMapCacheReloadingName = null;

	/**
	 * Constructor method for the class ConcurrentMapCacheValet.java.
	 */
	public ConcurrentMapCacheValet() {

		LOGGER.debug(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_001));

		// Creamos la TreeCache.
		if (concurrentMapCache == null) {

			try {

				// Generamos el nombre de la caché.
				concurrentMapCacheName = Long.toString(System.currentTimeMillis());

				// Creamos la caché.
				concurrentMapCache = new ConcurrentHashMap<Object, Object>();

			} catch (Exception e) {

				concurrentMapCacheName = null;
				concurrentMapCache = null;

			}

		}

		// Si finalmente se ha obtenido...
		if (concurrentMapCache != null) {

			LOGGER.debug(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_002));

			// Levantamos la bandera indicando que se ha creado de forma
			// correcta la Caché.
			setCreationFlag(true);

		} else {

			LOGGER.error(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_003));

		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#getLogger()
	 */
	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#stopCacheImplementation()
	 */
	@Override
	protected void stopCacheImplementation() throws CacheValetException {

		LOGGER.debug(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_004));
		// Si no se ha creado aún, lanzamos una excepción.
		if (!isCreationFlag()) {
			throw new CacheValetException(IValetException.COD_153, Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_005));
		}
		setCreationFlag(false);
		setInstance(null);
		concurrentMapCache.clear();
		concurrentMapCache = null;
		LOGGER.debug(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_JCM_006));

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#getSeparator()
	 */
	@Override
	public String getSeparator() {
		return File.separator;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#addObjectImplCache(java.lang.String, java.lang.Object, java.lang.Object, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void addObjectImplCache(String path, Object key, Object value, boolean inLoadingCache) throws CacheValetException {
		ConcurrentMap<Object, Object> actualConcMapCache = (ConcurrentMap<Object, Object>) getConcurrentMapCache(inLoadingCache);
		ConcurrentMap<Object, Object> mapWhereAddValue = (ConcurrentMap<Object, Object>) actualConcMapCache.get(path);
		if (mapWhereAddValue == null) {
			mapWhereAddValue = new ConcurrentHashMap<Object, Object>();
			actualConcMapCache.put(path, mapWhereAddValue);
		}
		mapWhereAddValue.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#getObjectImplCache(java.lang.String, java.lang.Object, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getObjectImplCache(String path, Object key, boolean inLoadingCache) throws CacheValetException {
		Object result = null;
		ConcurrentMap<Object, Object> mapWhereReadValue = (ConcurrentMap<Object, Object>) getConcurrentMapCache(inLoadingCache).get(path);
		if (mapWhereReadValue != null) {
			result = mapWhereReadValue.get(key);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#removeObjectImplCache(java.lang.String, java.lang.Object, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object removeObjectImplCache(String path, Object key, boolean inLoadingCache) throws CacheValetException {
		Object result = null;
		ConcurrentMap<Object, Object> mapWhereReadValue = (ConcurrentMap<Object, Object>) getConcurrentMapCache(inLoadingCache).get(path);
		if (mapWhereReadValue != null) {
			result = mapWhereReadValue.remove(key);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.impl.ACacheValet#removeNodeImplCache(java.lang.String, boolean)
	 */
	@Override
	protected boolean removeNodeImplCache(String fullPath, boolean inLoadingCache) throws Exception {
		return getConcurrentMapCache(inLoadingCache).remove(fullPath) != null;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#getCacheName()
	 */
	@Override
	public String getCacheName() {
		return concurrentMapCacheName;
	}

	/**
	 * Gets the normal Cache or the auxiliar loading cache.
	 * @param getAuxiliarLoadingCache Flag to choose if gets the normal (<code>false</code>) or auxiliar cache (<code>true</code>).
	 * @return the normal Cache or the auxiliar loading cache.
	 */
	private ConcurrentMap<Object, Object> getConcurrentMapCache(boolean getAuxiliarLoadingCache) {
		if (getAuxiliarLoadingCache) {
			return concurrentMapCacheReloading;
		} else {
			return concurrentMapCache;
		}
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#startsAuxiliarCache()
	 */
	@Override
	public void startsAuxiliarCache() {

		if (concurrentMapCacheReloading != null) {
			concurrentMapCacheReloading.clear();
			concurrentMapCacheReloading = null;
			concurrentMapCacheReloadingName = null;
		}

		// Creamos la caché.
		concurrentMapCacheReloadingName = Long.toString(System.currentTimeMillis());
		concurrentMapCacheReloading = new ConcurrentHashMap<Object, Object>();

		LOGGER.info(Language.getFormatResCacheValet(ICacheMessages.CACHE_IMPL_JCM_007, new Object[ ] { concurrentMapCacheReloadingName }));

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#assignAsPrincipalAuxiliarCache(int)
	 */
	@Override
	public void assignAsPrincipalAuxiliarCache(int millisecondsBeforeStopCache) {

		if (concurrentMapCacheReloading != null) {

			ConcurrentMap<Object, Object> cmAux = concurrentMapCache;
			String cmNameAux = concurrentMapCacheName;

			concurrentMapCache = concurrentMapCacheReloading;
			concurrentMapCacheName = concurrentMapCacheReloadingName;

			concurrentMapCacheReloading = null;
			concurrentMapCacheReloadingName = null;

			// Detenemos la caché anterior.
			giveSomeTimeBeforeStopCache(cmAux, cmNameAux, millisecondsBeforeStopCache);

		}

	}

	/**
	 * Auxiliar method that creates a new thread that waits some time before stop the input auxiliar cache.
	 * @param cm Auxiliar ConcurrentMap Cache that must be stopped.
	 * @param cmName Auxiliar ConcurrentMap Cache Name that must be stopped.
	 * @param millisecondsBeforeStopCache number of milliseconds before stop the old cache.
	 */
	private void giveSomeTimeBeforeStopCache(ConcurrentMap<Object, Object> cm, String cmName, int millisecondsBeforeStopCache) {

		GiveSomeTimeBeforeStopCacheThread gstbsct = new GiveSomeTimeBeforeStopCacheThread(cm, cmName, millisecondsBeforeStopCache);
		gstbsct.start();

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.cache.ifaces.ICacheValet#isReloadingCacheAtTheMoment()
	 */
	@Override
	public boolean isReloadingCacheAtTheMoment() {
		return concurrentMapCacheReloading != null;
	}

}
