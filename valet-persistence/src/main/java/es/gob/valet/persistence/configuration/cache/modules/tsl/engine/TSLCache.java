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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.tsl.engine.TSLCache.java.</p>
 * <b>Description:</b><p>Class to handle the TSL configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.persistence.configuration.cache.modules.tsl.engine;

import java.io.Serializable;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.PersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLLocationAndIdRelationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Class to handle the TSL configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public final class TSLCache extends ConfigurationCache {

	/**
	 * Constant attribute that represents the string to identify the base path inside the TreeCache for TSL.
	 */
	private static final String PATH_BASE = "TSL";

	/**
	 * Constant attribute that represents the string to identify a subpath in the TSL TreeCache for Countries/Regions.
	 */
	private static final String PATH_COUNTRY_REGIONS = "CountriesRegions";

	/**
	 * Constant attribute that represents the string to identify a subpath in the TSL TreeCache for TSL Data.
	 */
	private static final String PATH_TSL_DATA = "TSLData";

	/**
	 * Constant attribute that represents the string to identify a subpath in the TSL TreeCache for the relation
	 * between the URL TSL Location to its IDs.
	 */
	private static final String PATH_TSL_URLLOCATION_TSLID = "TSLLocationToID";

	/**
	 * Constant attribute that represents a separator token for the TSL identifier.
	 */
	private static final String SEPARATOR_IDENTIFIER = " - IDENTIFIER: ";

	/**
	 * Constant attribute that represents a separator token for the type country region cache object.
	 */
	private static final String TYPE_COUNTRY_REGION = "TYPE: COUNTRY/REGION - ";

	/**
	 * Constant attribute that represents a separator token for the type country region mapping cache object.
	 */
	private static final String TYPE_MAPPING = "TYPE: MAPPING - ";

	/**
	 * Constant attribute that represents a separator token for the type TSL data cache object.
	 */
	private static final String TYPE_TSL_DATA = "TYPE: TSL DATA - ";

	/**
	 * Constant attribute that represents an unknown type TSL cache object.
	 */
	private static final String TYPE_UNKNOWN = "TYPE: Unknown";

	/**
	 * Constant attribute that represents a separator token for the type TSL dates to version cache object.
	 */
	private static final String TYPE_LOCATION_IDS = "TYPE: LOCATION IDS - ";

	/**
	 * Constant attribute that represents the unique instance for the TSL clustered cache.
	 */
	private static TSLCache instance = null;

	/**
	 * Constructor method for the class TSLCache.java.
	 */
	private TSLCache() {
		super();
	}

	/**
	 * Gets the unique instance of the TSLCache Clustered Cache.
	 * @return the unique instance of the TSLCache Clustered Cache.
	 */
	public static TSLCache getInstance() {
		if (instance == null) {
			instance = new TSLCache();
		}
		return instance;
	}

	/**
	 * Method that adds a TSL Country/Region to the configuration cache. This method also will check that whether the
	 * TSL Country/Region already exists in the cache.
	 * @param tcr Parameter that represents the TSL Country Region object from the data base.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link TSLCountryRegionCacheObject}.
	 * @throws TSLCacheException In case of some error adding in cache the country/region.
	 */
	public TSLCountryRegionCacheObject addTSLCountryRegion(TslCountryRegion tcr, boolean inLoadingCache) throws TSLCacheException {

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (tcr == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG094));
		}

		TSLCountryRegionCacheObject tcrco = new TSLCountryRegionCacheObject(tcr);
		tcrco = addTSLCountryRegion(tcrco, inLoadingCache);
		return tcrco;

	}

	/**
	 * Method that adds a TSL Country/Region to the configuration cache. This method also will check that whether the
	 * TSL Country/Region already exists in the cache.
	 * @param tcrco Parameter that represents the TSL Country/Region cahe object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link TSLCountryRegionCacheObject}.
	 * @throws TSLCacheException In case of some error adding in cache the country/region.
	 */
	public TSLCountryRegionCacheObject addTSLCountryRegion(TSLCountryRegionCacheObject tcrco, boolean inLoadingCache) throws TSLCacheException {

		TSLCountryRegionCacheObject result = null;

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (tcrco == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG094));
		}

		// Se construye la ruta en la caché mediante el código del país/región.
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_COUNTRY_REGIONS;
		path[2] = tcrco.getCode();

		try {
			result = (TSLCountryRegionCacheObject) addConfigurationCacheObject(path, tcrco, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

		return result;

	}

	/**
	 * Gets the TSL Country Region identified by the input parameter from the cache.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return A {@link TSLCountryRegionCacheObject} that represents the TSL Country/Region to get. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting from cache the TSL Country/Region.
	 */
	public TSLCountryRegionCacheObject getTSLCountryRegion(String countryRegionCode, boolean inLoadingCache) throws TSLCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_COUNTRY_REGIONS;
		path[2] = countryRegionCode;

		try {
			return (TSLCountryRegionCacheObject) getConfigurationCacheObject(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * Removes the TSL Country/Region identified by the input parameter from the cache.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error removing from cache the TSL Country/Code.
	 */
	public void removeTSLCountryRegion(String countryRegionCode, boolean inLoadingCache) throws TSLCacheException {

		// Obtenemos el objeto que representa el país/región en la caché.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegion(countryRegionCode, inLoadingCache);

		// Si no existe, no hay nada que eliminar.
		if (tcrco != null) {

			// Construimos la ruta en la caché.
			String[ ] path = new String[NumberConstants.NUM3];
			path[0] = PATH_BASE;
			path[1] = PATH_COUNTRY_REGIONS;
			path[2] = countryRegionCode;

			// Eliminamos el país/región.
			try {
				clearObjectPathFromConfigurationCache(path, inLoadingCache);
			} catch (ConfigurationCacheException e) {
				throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
			}

		}

	}

	/**
	 * Method that adds a TSL Data to the configuration cache. This method also will check that whether the
	 * TSL Data already exists in the cache.
	 * @param td Parameter that represents the TSL data object from the data base.
	 * @param tslObjectSerializable TSL Object that represents the parsed XML.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link TSLDataCacheObject}.
	 * @throws TSLCacheException In case of some error adding in cache the TSL data.
	 */
	public TSLDataCacheObject addTSLData(TslData td, Serializable tslObjectSerializable, boolean inLoadingCache) throws TSLCacheException {

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (td == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG095));
		}

		TSLDataCacheObject tdco = new TSLDataCacheObject(td, tslObjectSerializable);
		tdco = addTSLData(tdco, inLoadingCache);
		return tdco;

	}

	/**
	 * Method that adds a TSL Data to the configuration cache. This method also will check that whether the
	 * TSL Data already exists in the cache.
	 * @param tdco Parameter that represents the TSL data cahe object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link TSLDataCacheObject}.
	 * @throws TSLCacheException In case of some error adding in cache the TSL data.
	 */
	public TSLDataCacheObject addTSLData(TSLDataCacheObject tdco, boolean inLoadingCache) throws TSLCacheException {

		TSLDataCacheObject result = null;

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (tdco == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG095));
		}

		// Se construye la ruta en la caché mediante ID de la TSL.
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_TSL_DATA;
		path[2] = String.valueOf(tdco.getTslDataId());

		try {
			result = (TSLDataCacheObject) addConfigurationCacheObject(path, tdco, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

		// Almacenamos también la relación entre TSL Location y sus datos.
		addURLTSLLocationAndID(result, inLoadingCache);

		return result;

	}

	/**
	 * Gets the TSL Data identified by the input parameter from the cache.
	 * @param tslDataId TSL Data ID.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return A {@link TSLDataCacheObject} that represents the TSL Data to get. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting from cache the TSL Data.
	 */
	public TSLDataCacheObject getTSLData(long tslDataId, boolean inLoadingCache) throws TSLCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_TSL_DATA;
		path[2] = String.valueOf(tslDataId);

		try {
			return (TSLDataCacheObject) getConfigurationCacheObject(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * Removes the TSL Data identified by the input parameter from the cache.
	 * @param tslDataId TSL Data ID.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error removing from cache the the TSL Data.
	 */
	public void removeTSLData(long tslDataId, boolean inLoadingCache) throws TSLCacheException {

		// Obtenemos el objeto que representa los datos de la TSL.
		TSLDataCacheObject tdco = getTSLData(tslDataId, inLoadingCache);

		// Si no existe, no hay nada que eliminar.
		if (tdco != null) {

			// Construimos la ruta en la caché.
			String[ ] path = new String[NumberConstants.NUM3];
			path[0] = PATH_BASE;
			path[1] = PATH_TSL_DATA;
			path[2] = String.valueOf(tslDataId);

			// Eliminamos la TSL.
			try {
				clearObjectPathFromConfigurationCache(path, inLoadingCache);
			} catch (ConfigurationCacheException e) {
				throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
			}

			// Eliminamos también la relación entre TSL location y sus datos.
			removeURLTSLLocationAndID(tdco, inLoadingCache);

		}

	}

	/**
	 * This method remove all the TSL information from the cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error while is cleaning the TSL cache.
	 */
	public void clearTSLCache(boolean inLoadingCache) throws TSLCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[1];
		path[0] = PATH_BASE;

		// Se elimina toda la información relacionada con TSL de la caché.
		try {
			clearNodePathFromConfigurationCache(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * Gets the cache object that represents the map with the relation between the URL TSL Location and its information.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return map with the relation between the URL TSL Location and its information. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting from cache the object.
	 */
	public TSLLocationAndIdRelationCacheObject getTSLLocationAndIdRelationCacheObject(boolean inLoadingCache) throws TSLCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = PATH_TSL_URLLOCATION_TSLID;

		try {
			return (TSLLocationAndIdRelationCacheObject) getConfigurationCacheObject(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * Sets the cache object that represents the map with the relation between the URL TSL Location and its information.
	 * @param tlairco cache object that represents the map with the relation between the URL TSL Location and its information.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error setting into cache the object.
	 */
	public void setTSLLocationAndIdRelationCacheObject(TSLLocationAndIdRelationCacheObject tlairco, boolean inLoadingCache) throws TSLCacheException {

		// Se construye la ruta en la caché.
		String[ ] path = new String[2];
		path[0] = PATH_BASE;
		path[1] = PATH_TSL_URLLOCATION_TSLID;

		try {
			addConfigurationCacheObject(path, tlairco, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, e.getErrorDescription(), e.getException());
		}

	}

	/**
	 * Method that adds a TSL Data information sorting by URL location.
	 * @param tdco Parameter that represents the TSL data cahe object with the information to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error adding in cache the TSL Data information sorting by URL location.
	 */
	private void addURLTSLLocationAndID(TSLDataCacheObject tdco, boolean inLoadingCache) throws TSLCacheException {

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (tdco == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG064));
		}

		// Obtenemos el objeto con las relaciones de la caché.
		TSLLocationAndIdRelationCacheObject tlairco = getTSLLocationAndIdRelationCacheObject(inLoadingCache);

		// Si es nulo, lo inicializamos.
		if (tlairco == null) {
			tlairco = new TSLLocationAndIdRelationCacheObject();
		}

		// Añadimos la información.
		tlairco.addUpdateRelation(tdco);

		// Lo actualizamos en la caché.
		setTSLLocationAndIdRelationCacheObject(tlairco, inLoadingCache);

	}

	/**
	 * Method that removes a TSL Data information sorted by URL location.
	 * @param tdco Parameter that represents the TSL data cahe object with the information to remove.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws TSLCacheException In case of some error removing from cache the TSL Data information sorted by URL location.
	 */
	private void removeURLTSLLocationAndID(TSLDataCacheObject tdco, boolean inLoadingCache) throws TSLCacheException {

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (tdco == null) {
			throw new TSLCacheException(ValetExceptionConstants.COD_191, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG065));
		}

		// Obtenemos el objeto con las relaciones de la caché.
		TSLLocationAndIdRelationCacheObject tlairco = getTSLLocationAndIdRelationCacheObject(inLoadingCache);

		// Si no es nulo, continuamos.
		if (tlairco != null) {

			// Eliminamos la información
			tlairco.removeRelation(tdco);

			// Lo actualizamos en la caché.
			setTSLLocationAndIdRelationCacheObject(tlairco, inLoadingCache);

		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getTypeElement()
	 */
	@Override
	protected String getTypeElement() {
		return Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG070);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getObjectNameIdentifier(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getObjectNameIdentifier(ConfigurationCacheObject cco) {

		String result = TYPE_UNKNOWN;

		if (cco instanceof TSLCountryRegionCacheObject) {
			TSLCountryRegionCacheObject tcrco = (TSLCountryRegionCacheObject) cco;
			result = TYPE_COUNTRY_REGION + SEPARATOR_ID + tcrco.getCountryRegionId() + SEPARATOR_IDENTIFIER + tcrco.getCode();
		} else if (cco instanceof TSLCountryRegionMappingCacheObject) {
			TSLCountryRegionMappingCacheObject tcrmco = (TSLCountryRegionMappingCacheObject) cco;
			result = TYPE_MAPPING + SEPARATOR_ID + tcrmco.getMappingId() + SEPARATOR_IDENTIFIER + tcrmco.getIdentificator();
		} else if (cco instanceof TSLDataCacheObject) {
			TSLDataCacheObject tdco = (TSLDataCacheObject) cco;
			result = TYPE_TSL_DATA + SEPARATOR_ID + tdco.getTslDataId() + SEPARATOR_IDENTIFIER + tdco.getSequenceNumber();
		} else if (cco instanceof TSLLocationAndIdRelationCacheObject) {
			result = TYPE_LOCATION_IDS + SEPARATOR_ID + TSLLocationAndIdRelationCacheObject.TOKEN_ID;
		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#checkUpdateCondition(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected boolean checkUpdateCondition(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {
		// Siempre se debe actualizar.
		return true;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getMsgNotConditionForUpdateAccomplished(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getMsgNotConditionForUpdateAccomplished(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {

		return Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_TSL_CACHE_LOG093);

	}

}
