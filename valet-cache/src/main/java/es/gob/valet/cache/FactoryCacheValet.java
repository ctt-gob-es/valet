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
 * <b>File:</b><p>es.gob.valet.cache.FactoryCacheValet.java.</p>
 * <b>Description:</b><p>Factory class that gets the unique instance of the valET Cache depending of
 * the implementation configured.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.cache;

import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.cache.ifaces.ICacheValet;
import es.gob.valet.cache.impl.ACacheValet;
import es.gob.valet.cache.impl.javaConcurrentMap.ConcurrentMapCacheValet;
import es.gob.valet.cache.utils.CacheValetConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsStringChar;

/**
 * <p>Factory class that gets the unique instance of the valET Cache depending of
 * the implementation configured.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public final class FactoryCacheValet {

	/**
	 * Attribute that represents the unique instance of the valET Cache independently of its implementation.
	 */
	private static ICacheValet cacheValetInstance = null;

	/**
	 * Attribute that represents the separator to use depending of the implementation.
	 */
	private static String cacheValetSeparator = null;

	/**
	 * Constructor method for the class FactoryCacheValet.java.
	 */
	private FactoryCacheValet() {
		super();
	}

	/**
	 * Gets the unique instance of the valET Cache independently of its implementation.
	 * @return unique instance of the valET Cache independently of its implementation.
	 * @throws CacheValetException In case of some error creating the cache.
	 */
	public static ICacheValet getCacheValetInstance() throws CacheValetException {

		// Si la instancia no ha sido inicializada...
		if (cacheValetInstance == null) {

			// Obtenemos la propiedad del fichero de propiedades estáticas.
			String valetCacheImpl = StaticValetConfig.getProperty(StaticValetConfig.CACHE_IMPLEMENTATION);

			// Si la cadena es nula o vacía le asignamos Java Concurrent Map por
			// defecto.
			if (UtilsStringChar.isNullOrEmpty(valetCacheImpl)) {
				valetCacheImpl = CacheValetConstants.VALET_CACHE_IMPL_JAVA_CONCURRENT_MAP;
			}

			// En función de la propiedad resolvemos...
			switch (valetCacheImpl) {
				case CacheValetConstants.VALET_CACHE_IMPL_JAVA_CONCURRENT_MAP:
					cacheValetInstance = getCacheValetJavaConcurrentMapImpl();
					break;

				default:
					// En caso de no reconocer la implementación, se opta por
					// Java Concurrent Map.
					cacheValetInstance = getCacheValetJavaConcurrentMapImpl();
					break;
			}

			if (cacheValetInstance != null) {
				cacheValetSeparator = cacheValetInstance.getSeparator();
			}

		}

		// Devolvemos la instancia única de la caché.
		return cacheValetInstance;

	}

	/**
	 * Gets the valET Cache Java Concurrent Map Implementation unique instance.
	 * @return the Afirma Cache Java Concurrent Map Implementation unique instance.
	 */
	private static ICacheValet getCacheValetJavaConcurrentMapImpl() {

		// Obtenemos la única instancia de esta implementación.
		ICacheValet result = ACacheValet.getInstance();

		// Si es nula, la creamos.
		if (result == null) {

			result = new ConcurrentMapCacheValet();
			if (result != null) {
				ACacheValet.setInstance(result);
			}

		}

		// La devolvemos.
		return result;

	}

	/**
	 * Gets the value of the attribute {@link #cacheValetSeparator}.
	 * @return the value of the attribute {@link #cacheValetSeparator}.
	 */
	public static String getCacheValetSeparator() {
		return cacheValetSeparator;
	}

}
