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
 * <b>File:</b><p>es.gob.valet.utils.UtilsCache.java.</p>
 * <b>Description:</b><p>Utilities class for local cache operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 19/09/2023.
 */
package es.gob.valet.utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.tsl.access.TSLManager;

/**
 * <p>Utilities class for local cache operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 19/09/2023.
 */
public final class UtilsCache {

	/**
	 * Constructor method for the class UtilsCache.java.
	 */
	private UtilsCache() {
		super();
	}

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilsCache.class);

	/**
	 * This methods reload the configuration local cache following the steps:
	 * 1) Reload all the configuration cache in a new named clustered cache (only if the process is complete).
	 * 2) Stops the old clustered cache (only if the process is complete).
	 * 3) Sets as principal the new loaded cache (only if the process is complete).
	 * 4) Reload the proxy configuration.
	 * 5) Reload the Trusted Responders for OCSP requests.
	 * 6) Reload the TSL.
	 * 7) Reload the differents cached object in the differents manager.
	 * @param completeProcess Flag that when is <code>true</code>, the process of reloading the cache
	 * is going to be executed fully, if it is <code>false</code>, then steps 1, 2 and 3 are ommited.
	 * @throws ConfigurationCacheException In case of some error during the process.
	 */
	public static void reloadConfigurationLocalCache(boolean completeProcess) throws ConfigurationCacheException {

		// Guardamos el momento de inicio de la operativa.
		long initTime = Calendar.getInstance().getTimeInMillis();

		try {

			LOGGER.info(Language.getResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_005));

			// Ejecutamos la recarga completa de la caché si se ha solicitado
			// la ejecución completa del proceso.
			if (completeProcess) {
				ConfigurationCacheFacade.reloadConfigurationCache();
			}

			// Recargamos los datos del proxy.
			LOGGER.info(Language.getResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_008));
			long startOperationTime = Calendar.getInstance().getTimeInMillis();
			UtilsProxy.loadProxyConfiguration();
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_021, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));

			// TODO Recargamos los certificados de confianza.
			// LOGGER.info(Language.getResCoreGeneral(LOG_COREUTILS_009));
			// startOperationTime = Calendar.getInstance().getTimeInMillis();
			// TrustedRespondersOCSPManager.getInstance().reloadTrustedCertsInformation();
			// LOGGER.info(Language.getFormatResCoreGeneral(LOG_COREUTILS_022,
			// new Object[ ] { Calendar.getInstance().getTimeInMillis() -
			// startOperationTime }));

			// Cargamos las TSL.
			LOGGER.info(Language.getResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_010));
			startOperationTime = Calendar.getInstance().getTimeInMillis();
			TSLManager.getInstance().reloadTSLCache();
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_023, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));

			// Recargamos las distintas listas de objetos cacheadas en memoria.
			loadAllAdditionalCachedObjectList();

			// TODO Se marca el nodo como sincronizado.
			// if (!UtilsClusteredCacheOperations.isInClusteredMode()) {
			// ManagerBOs.getInstance().getMPMManagerBOs().getMPMManager().markAsSync(ClusterManager.getInstance().getThisNodeName());
			// }

			LOGGER.info(Language.getResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_011));

		} catch (ConfigurationCacheException e) {

			String errorMsg = Language.getResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_000);
			LOGGER.error(errorMsg);
			throw new ConfigurationCacheException(IValetException.COD_191, errorMsg, e);

		} finally {

			// Obtenemos el momento de finalización de la operativa.
			long endTime = Calendar.getInstance().getTimeInMillis();
			// Obtenemos el tiempo en milisegundos empleado.
			long totalTime = endTime - initTime;
			// Parseamos a cadena.
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime), TimeUnit.MILLISECONDS.toMinutes(totalTime) % TimeUnit.HOURS.toMinutes(1), TimeUnit.MILLISECONDS.toSeconds(totalTime) % TimeUnit.MINUTES.toSeconds(1));
			// Lo imprimimos.
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.UTILS_CACHE_018, new Object[ ] { hms }));

		}

	}

	/**
	 * Reload all the cached object in differents manager.
	 */
	public static void loadAllAdditionalCachedObjectList() {

		// TODO Completar cuando sea necesario.

	}

}
