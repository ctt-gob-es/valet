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
 * <b>File:</b><p>es.gob.valet.cache.utils.GiveSomeTimeBeforeStopCacheThread.java.</p>
 * <b>Description:</b><p>Utility class that controls in a independent thread the operation of stopping a Cache
 * giving some time of living previosly.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/09/2018.
 */
package es.gob.valet.cache.utils;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICacheMessages;

/**
 * <p>Utility class that controls in a independent thread the operation of stopping a Cache
 * giving some time of living previosly.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/09/2018.
 */
public class GiveSomeTimeBeforeStopCacheThread extends Thread {

	/**
	 * Constant attribute that represents the name for the thread.
	 */
	private static final String THREAD_NAME = "STOPPING_CACHE_THREAD";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GiveSomeTimeBeforeStopCacheThread.class);

	/**
	 * Attribute that represents the time (in milliseconds) to wait before stop the cache.
	 */
	private int timeBeforeStopCacheInMilliSeconds = 0;

	/**
	 * Constant attribute that represents the Concurrent Map Cache that must be stopped.
	 */
	private ConcurrentMap<Object, Object> concurrentMapCache = null;

	/**
	 * Constant attribute that represents the Concurrent Map Cache Name that must be stopped.
	 */
	private String concurrentMapCacheName = null;

	/**
	 * Constructor method for the class GiveSomeTimeBeforeStopCacheThread.java.
	 */
	private GiveSomeTimeBeforeStopCacheThread() {
		super(THREAD_NAME);
	}

	/**
	 * Constructor method for the class GiveSomeTimeBeforeStopCacheThread.java.
	 * @param cm Map that represents the cache that must be stopped.
	 * @param cmName Name associated to the cache that must be stopped.
	 * @param millisecondsBeforeStopCache Time (in milliseconds) to wait before stop the cache.
	 */
	public GiveSomeTimeBeforeStopCacheThread(ConcurrentMap<Object, Object> cm, String cmName, int millisecondsBeforeStopCache) {
		this();
		this.concurrentMapCache = cm;
		this.concurrentMapCacheName = cmName;
		this.timeBeforeStopCacheInMilliSeconds = millisecondsBeforeStopCache;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {

		// Detenemos la caché implementada por ConcurrentMap.
		stopConcurrentMapCache();

		// Procedemos a forzar que el recolector de basura limpie los
		// objetos de la caché antigua.
		System.gc();

	}

	/**
	 * Stops the Cache implemented with Java Concurrent Map.
	 */
	@SuppressWarnings("unchecked")
	private void stopConcurrentMapCache() {

		if (concurrentMapCache != null) {

			// Realizamos la espera configurada.
			checkTimeToWait();

			LOGGER.debug(Language.getFormatResCacheValet(ICacheMessages.CACHE_IMPL_016, new Object[ ] { concurrentMapCacheName }));

			if (!concurrentMapCache.isEmpty()) {

				Set<Object> keys = concurrentMapCache.keySet();

				for (Object key: keys) {

					if (concurrentMapCache.get(key) instanceof ConcurrentMap<?, ?>) {

						ConcurrentMap<Object, Object> cm = (ConcurrentMap<Object, Object>) concurrentMapCache.get(key);
						cm.clear();

					}

				}

				concurrentMapCache.clear();
				concurrentMapCache = null;

			}

			LOGGER.info(Language.getFormatResCacheValet(ICacheMessages.CACHE_IMPL_017, new Object[ ] { concurrentMapCacheName }));
			concurrentMapCacheName = null;

		}

	}

	/**
	 * Checks the time to wait before stops the cache, and wait it.
	 */
	private void checkTimeToWait() {

		// Si el tiempo de espera es mayor que 0...
		if (timeBeforeStopCacheInMilliSeconds > 0) {

			LOGGER.debug(Language.getFormatResCacheValet(ICacheMessages.CACHE_IMPL_014, new Object[ ] { timeBeforeStopCacheInMilliSeconds }));
			try {
				Thread.sleep(timeBeforeStopCacheInMilliSeconds);
			} catch (InterruptedException e) {
				LOGGER.error(Language.getResCacheValet(ICacheMessages.CACHE_IMPL_015), e);
			}

		}

	}

}
