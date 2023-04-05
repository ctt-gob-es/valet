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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade.java.</p>
 * <b>Description:</b><p>Facade for all the configuration cache objects of the configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.7, 03/04/2023.
 */
package es.gob.valet.persistence.configuration.cache.engine;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.persistence.configuration.cache.modules.application.elements.ApplicationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.application.engine.ApplicationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.application.exceptions.ApplicationCacheException;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.engine.KeystoreCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.engine.TSLCache;
import es.gob.valet.persistence.configuration.cache.modules.tsl.engine.TSLCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Facade for all the configuration cache objects of the configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.7, 03/04/2023.
 */
public final class ConfigurationCacheFacade {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ConfigurationCacheFacade.class);

	/**
	 * Constructor method for the class ConfigurationCacheFacade.java.
	 */
	private ConfigurationCacheFacade() {
		super();
	}

	/**
	 * Gets the unique instance for the TSL cache configuration facade.
	 * @return the unique instance for the TSL cache configuration facade.
	 */
	private static TSLCacheFacade getTSLCacheFacade() {
		return TSLCacheFacade.getInstance();
	}

	/**
	 * Clear the TSL Cache.
	 * @throws TSLCacheException In case of some error while clearing the cache.
	 */
	public static void tslClearTSLCache() throws TSLCacheException {

		getTSLCacheFacade().clearTSLCache();

	}

	/**
	 * Gets the TSL Country/Region representation from the configuration cache. If it does not exists, try to get from the data base.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return A object representation of the TSL Country/Region in the configuration cache. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting the TSL Country/Region from the configuration cache.
	 */
	public static TSLCountryRegionCacheObject tslGetTSLCountryRegionCacheObject(String countryRegionCode) throws TSLCacheException {

		return getTSLCacheFacade().getTSLCountryRegionCacheObject(countryRegionCode);

	}

	/**
	 * Adds or updates the basic configuration about a country/region for TSL in the configuration cache.
	 * @param tcrco TSL Country/Region object representation for the cache.
	 * @return The added/updated country/region object representation.
	 * @throws TSLCacheException In case of some error adding/updating the TSL country/region in the configuration cache.
	 */
	public static TSLCountryRegionCacheObject tslAddUpdateBasicTSLCountryRegion(TSLCountryRegionCacheObject tcrco) throws TSLCacheException {

		return getTSLCacheFacade().addUpdateBasicTSLCountryRegion(tcrco);

	}

	/**
	 * Removes the TSL country/region, specified by its code, from the configuration cache.
	 * Also removes all the TSL Data associated.
	 * @param countryRegionCode TSL Country/Region code.
	 * @throws TSLCacheException In case of some error removing the TSL country/region in the configuration cache.
	 */
	public static void tslRemoveTSLCountryRegion(String countryRegionCode) throws TSLCacheException {

		getTSLCacheFacade().removeTSLCountryRegion(countryRegionCode);

	}

	/**
	 * Gets the TSL data representation from the configuration cache.
	 * This method does not load information from the data base.
	 * @param tslDataId TSL identifier.
	 * @return A object representation of the TSL data in the configuration cache. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting the TSL data from the configuration cache.
	 */
	public static TSLDataCacheObject tslGetTSLDataCacheObject(long tslDataId) throws TSLCacheException {

		return getTSLCacheFacade().getTSLDataCacheObject(tslDataId);

	}

	/**
	 * Gets the TSL associated to a specific country/region.
	 * This method does not load the TSL Data from the data base.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return the TSL associated to the country/region specified. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting the TSL information from the configuration cache.
	 */
	public static TSLDataCacheObject tslGetTSLDataFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		return getTSLCacheFacade().getTSLDataFromCountryRegion(countryRegionCode);

	}

	/**
	 * Gets the TSL data with the specified TSL location.
	 * This method does not load the TSL Data from the data base.
	 * @param tslLocation TSL location to find.
	 * @return TSL data Cache Object with the specified TSL location, <code>null</code>
	 * if there is not.
	 * @throws TSLCacheException In case of some error getting the TSL Data from the configuration cache.
	 */
	public static TSLDataCacheObject tslGetTSLDataFromLocation(String tslLocation) throws TSLCacheException {

		return getTSLCacheFacade().getTSLDataFromLocation(tslLocation);

	}

	/**
	 * Adds/Updates a TSL Data in the configuration cache.
	 * @param td TSL Data Object POJO representation to add in the configuration cache.
	 * @param tslObjectSerializable TSL parsed representation to add with the POJO in the cache.
	 * @return TSL Data cache object added in cache. <code>null</code> if the country/region does not exists.
	 * @throws TSLCacheException In case of some error adding/updating the TSL Data in the configuration cache.
	 */
	public static TSLDataCacheObject tslAddUpdateTSLData(TslData td, Serializable tslObjectSerializable) throws TSLCacheException {

		return getTSLCacheFacade().addUpdateTSLData(td, tslObjectSerializable);

	}

	/**
	 * Removes a TSL Data from the cache, identified by its country/region and ID. Also removes the relation with its country/region.
	 * @param countryRegionCode Country/Region code which hash the TSL data to remove.
	 * @throws TSLCacheException In case of some error removing the TSL Data in the configuration cache.
	 */
	public static void tslRemoveTSLDataFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		getTSLCacheFacade().removeTSLDataFromCountryRegion(countryRegionCode);

	}

	/**
	 * Gets the mapping from the specified country/region code. <code>null</code> if the country/region is not defined.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return Set of mapping from the specified country/region code.
	 * @throws TSLCacheException In case of some error getting the TSL Mapping from the configuration cache.
	 */
	public static Set<TSLCountryRegionMappingCacheObject> tslGetMappingFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		return getTSLCacheFacade().getMappingFromCountryRegion(countryRegionCode);

	}

	/**
	 * Adds or updates a TSL mapping for a specific country/region, and return it.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrmp TSL Country/Region Mapping representation in the data base.
	 * @return the added/updated TSL mapping representation in the configuration cache.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public static TSLCountryRegionMappingCacheObject tslAddUpdateMappingToCountryRegion(String countryRegionCode, TslCountryRegionMapping tcrmp) throws TSLCacheException {

		return getTSLCacheFacade().addUpdateMappingToCountryRegion(countryRegionCode, tcrmp);

	}

	/**
	 * Removes a TSL mapping from a specific country/region.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrmco TSL Country/Region Mapping representation in the configuration cache.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public static void tslRemoveMappingFromCountryRegion(String countryRegionCode, TSLCountryRegionMappingCacheObject tcrmco) throws TSLCacheException {

		getTSLCacheFacade().removeMappingFromCountryRegion(countryRegionCode, tcrmco);

	}

	/**
	 * Removes a TSL mapping from a specific country/region.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrmcoId TSL Country/Region Mapping ID representation in the data base.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public static void tslRemoveMappingFromCountryRegion(String countryRegionCode, long tcrmcoId) throws TSLCacheException {

		getTSLCacheFacade().removeMappingFromCountryRegion(countryRegionCode, tcrmcoId);

	}

	/**
	 * Gets the unique instance for the keystore cache configuration facade.
	 * @return the unique instance for the keystore cache configuration facade.
	 */
	private static KeystoreCacheFacade getKeystoreCacheFacade() {
		return KeystoreCacheFacade.getInstance();
	}

	/**
	 * Adds or update the keystore in the configuration cache.
	 * @param kco Object reprensentation of the keystore in the configuration cache.
	 * @return Keystore cache object added/updated in the configuration cache.
	 * @throws KeystoreCacheException In case of some error adding/updating the keystore in the cache.
	 */
	public static KeystoreCacheObject keystoreAddUpdateKeystore(KeystoreCacheObject kco) throws KeystoreCacheException {
		return getKeystoreCacheFacade().addUpdateKeystore(kco);
	}

	/**
	 * Gets the Keystore representation from the configuration cache.
	 * @param idKeystore Keystore identifier.
	 * @return the Keystore cache object representation from the configuration cache.
	 * @throws KeystoreCacheException In case of some error getting the keystore from the configuration cache.
	 */
	public static KeystoreCacheObject keystoreGetKeystoreCacheObject(long idKeystore) throws KeystoreCacheException {
		return getKeystoreCacheFacade().getKeystoreCacheObject(idKeystore);
	}

	/**
	 * Gets the actual name of the clustered configuration cache.
	 * @return the actual name of the clustered configuration cache.
	 * @throws ConfigurationCacheException In case of some error initializing the clustered cache.
	 */
	public static String getCacheName() throws ConfigurationCacheException {

		return TSLCache.getInstance().getCacheName();

	}

	/**
	 * Checks if actually is reloading the configuration cache.
	 * @return <code>true</code> if configuration cache is reloading at the moment, otherwise <code>false</code>.
	 * @throws ConfigurationCacheException In case of some error initializing the clustered cache.
	 */
	public static boolean isReloadingCacheAtTheMoment() throws ConfigurationCacheException {

		return TSLCache.getInstance().isReloadingCacheAtTheMoment();

	}

	/**
	 * Initialize the configuration cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 */
	public static void initializeConfigurationCache(boolean inLoadingCache) {

		// Indicamos que se van a inicializar los datos de la caché compartida.
		LOGGER.info(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG010));

		try {
			// Comprobamos si ya está inicializada.
			if (TSLCache.getInstance().isInitialized(inLoadingCache)) {

				LOGGER.warn(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG011));

			} else {

				// Inicializamos los datos de configuración en la caché.
				initializeAllConfigurationCacheData(inLoadingCache);

				// Una vez se han cargado todos los datos, marcamos que la caché
				// ha sido inicializada.
				TSLCache.getInstance().setInitializedFlag(true, inLoadingCache);
				LOGGER.info(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG012));

			}

		} catch (ConfigurationCacheException e) {
			LOGGER.error(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG031), e);
		}

	}

	/**
	 * Private method that loads all the configuration data in the cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 */
	private static void initializeAllConfigurationCacheData(boolean inLoadingCache) {

		// Declaramos las variables a usar para indicar los tiempos de carga de
		// cada "módulo".
		long initTime = 0;
		long endTime = 0;

		// Se inicializan todas los almacenes de certificados en la caché de
		// configuración...
		LOGGER.info(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG050));
		initTime = Calendar.getInstance().getTimeInMillis();
		try {
			getKeystoreCacheFacade().initializeAllKeystores(inLoadingCache);
		} catch (Exception e) {
			LOGGER.error(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG051), e);
		}
		endTime = Calendar.getInstance().getTimeInMillis();
		LOGGER.info(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG052, new Object[ ] { Long.toString(endTime - initTime) }));

		// Se inicializan todas las aplicaciones en la caché
		// compartida...
		LOGGER.info(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG013));
		initTime = Calendar.getInstance().getTimeInMillis();
		try {
			getApplicationCacheFacade().initializeAllApplications(inLoadingCache);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG014), e);
		}
		endTime = Calendar.getInstance().getTimeInMillis();
		LOGGER.info(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG040, new Object[ ] { Long.toString(endTime - initTime) }));

	}

	/**
	 * This method reload the configuration cache in a new instance, and when finish, then
	 * replaces the actual.
	 * @throws ConfigurationCacheException In case of some error in the process.
	 */
	public static void reloadConfigurationCache() throws ConfigurationCacheException {

		// Iniciamos la caché auxiliar.
		LOGGER.info(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG038));
		TSLCache.getInstance().startsAuxiliarCache();

		// Recargamos toda la información de la caché.
		initializeConfigurationCache(true);

		// Sustituimos la actual por la auxiliar.
		int sleepTimeBeforeStopCache = getSleepTimeBeforeStopCache();
		LOGGER.info(Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG039, new Object[ ] { sleepTimeBeforeStopCache }));
		TSLCache.getInstance().assignAsPrincipalAuxiliarCache(sleepTimeBeforeStopCache);

	}

	/**
	 * Gets from the data base the property value that defines the sleep time before stop the
	 * local cache configuration.
	 * @return number of milliseconds to wait before stop the local cache configuration.
	 */
	public static int getSleepTimeBeforeStopCache() {

		// Por defecto devolvemos que no se espera para detener la caché.
		int result = 0;

		try {
			String valueInStaticProperty = StaticValetConfig.getProperty(StaticValetConfig.CACHE_IDLETIMEBEFORESTOPCACHE);
			result = Integer.parseInt(valueInStaticProperty) * NumberConstants.NUM1000;
		} catch (NumberFormatException e) {
			LOGGER.error(Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_CACHE_LOG036), e);
			result = NumberConstants.NUM30000;
		}

		// Devolvemos el valor calculado.
		return result;

	}

	/**
	 * Gets the unique instance for the Application cache configuration facade.
	 * @return the unique instance for the Application cache configuration facade.
	 */
	private static ApplicationCacheFacade getApplicationCacheFacade() {
		return ApplicationCacheFacade.getInstance();
	}

	/**
	 * Method that reloads an application in the cache.
	 *
	 * @param av Application pojo representation.
	 * @throws ApplicationCacheException In case of some error reloading in the cache.
	 */
	public static void applicationAddUpdateApplication(ApplicationValet av) throws ApplicationCacheException {
		// se comprueba que el parámetro de entrada no sea nulo y que se
		// encuentre definido el identificador
		if (av == null || av.getIdApplication() == null) {
			throw new ApplicationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG005));
		} else {
			// Se realiza la recarga.
			getApplicationCacheFacade().addUpdateApplication(av);
		}
	}

	/**
	 * Removes the application specified by the input identifier from the cache.
	 *
	 * @param applicationId Application identifier.
	 * @throws ApplicationCacheException In case of some error working with the cache.
	 */
	public static void applicationRemoveApplication(Long applicationId) throws ApplicationCacheException {
		// se obtiene la aplicación de la caché antes de eliminarla.
		ApplicationCacheObject aco = applicationGetApplication(applicationId);
		if (aco != null) {
			getApplicationCacheFacade().removeApplication(applicationId);
		}

	}

	/**
	 * Gets the application representation from the cache. If it does not exist, try to get from the data base.
	 * @param applicationId Application identifier
	 * @return A object representation of the application in the cache.
	 * @throws ApplicationCacheException In case of some error getting the application from the cache.
	 */
	public static ApplicationCacheObject applicationGetApplication(long applicationId) throws ApplicationCacheException {
		return ApplicationCacheFacade.getInstance().getApplicationCacheObject(applicationId);
	}
	
	/**
	 * Gets the application representation from the cache. If it does not exist, try to get from the data base.
	 * @param app Application identificator.
	 * @param checkDataBase Flag that indicates if, in case of the application to find is not defined in the cache, this method
	 * must check if it is defined in the data base (<code>true</code>), or not (<code>false</code>).
	 * @return A object representation of the application in the cache.
	 * @throws ApplicationCacheException In case of some error getting the application from the cache.
	 */
	public static ApplicationCacheObject applicationGetApplication(String app, boolean checkDataBase) throws ApplicationCacheException {
		return ApplicationCacheFacade.getInstance().getApplicationCacheObject(app, checkDataBase);
	}
	
}
