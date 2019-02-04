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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.application.engine.ApplicationCache.java.</p>
 * <b>Description:</b><p>Class to handle the Application configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 04/02/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.application.engine;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.application.elements.ApplicationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.application.exceptions.ApplicationCacheException;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Class to handle the Application configuration cache .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 04/02/2018.
 */
public final class ApplicationCache extends ConfigurationCache {

	/**
	 *  Constant attribute that represents a separator token for the application ID.
	 */
	protected static final String SEPARATOR_ID = "ID: ";

	/**
	 * Constant attribute that represents a separator token for the application identifier.
	 */
	private static final String SEPARATOR_IDENTIFIER = " - IDENTIFIER: ";

	/**
	 * Constant attribute that represents the string to identify the base path inside the TreeCache for Applications.
	 */
	private static final String PATH_BASE = "Apps";

	/**
	 * Constant attribute that represents the string to identify a subpath inside the TreeCache for Applications by identifiers.
	 */
	private static final String PATH_BY_IDENTIFIER = "ByIdentifier";

	/**
	 * Constant attribute that represents the string to identify a subpath inside the TreeCache for Applications by IDs.
	 */
	private static final String PATH_BY_ID = "ByID";

	/**
	 * Constant attribute that represents the unique instance for the TSL clustered cache.
	 */
	private static ApplicationCache instance = null;

	/**
	 * Constructor method for the class ApplicationCache.java.
	 */
	private ApplicationCache() {
		super();
	}

	/**
	 * Gets the unique instance of the TSLCache Clustered Cache.
	 * @return the unique instance of the TSLCache Clustered Cache.
	 */
	public static ApplicationCache getInstance() {
		if (instance == null) {
			instance = new ApplicationCache();
		}
		return instance;
	}

	/**
	 * Method that adds an application to the caché. This method aso will check that wheter the application already exists in the cache.
	 *
	 * @param av Parameter that represents the application pojo object from the data base.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return Updated/added {@link ApplicationCacheObject}
	 * @throws ApplicationCacheException In case of some error adding in the cache the application.
	 */
	public ApplicationCacheObject addApplication(ApplicationValet av, boolean inLoadingCache) throws ApplicationCacheException {
		// si el parámetro de entrada es nulo, lanzamos excepción
		if (av == null) {
			throw new ApplicationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG004));
		} else {
			ApplicationCacheObject aco = new ApplicationCacheObject(av);
			aco = addApplication(aco, inLoadingCache);
			return aco;
		}
	}

	/**
	 * Method that adds/updates an application to the configuration cache. This method also will check that whether the application already exists in the cache.
	 *
	 * @param aco Parameter that represents the application cache object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loadin auxiliar cache.
	 * @return Updated/added {@link ApplicationCacheObject}.
	 * @throws ApplicationCacheException In case of some error adding in the cache the application.
	 */
	private ApplicationCacheObject addApplication(ApplicationCacheObject aco, boolean inLoadingCache) throws ApplicationCacheException {
		ApplicationCacheObject result = null;
		// si el parámetro de entrada es nulo, lanzamos excepción
		if (aco == null) {
			throw new ApplicationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG004));
		}
		// se construye la ruta en la caché mediante el identificador de la
		// aplicación
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_BY_IDENTIFIER;
		path[2] = aco.getIdentificator();

		// se construye la ruta en la caché para la relación identificador -
		// path de la aplicación
		String[ ] pathRelationIdPath = new String[NumberConstants.NUM3];
		pathRelationIdPath[0] = PATH_BASE;
		pathRelationIdPath[1] = PATH_BY_ID;
		pathRelationIdPath[2] = String.valueOf(aco.getApplicationId());

		try {
			addString(pathRelationIdPath, aco.getIdentificator(), inLoadingCache);
			result = (ApplicationCacheObject) addConfigurationCacheObject(path, aco, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e.getException());
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getTypeElement()
	 */
	@Override
	protected String getTypeElement() {
		return Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG002);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getObjectNameIdentifier(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getObjectNameIdentifier(ConfigurationCacheObject cco) {
		ApplicationCacheObject aco = (ApplicationCacheObject) cco;
		return SEPARATOR_ID + aco.getApplicationId() + SEPARATOR_IDENTIFIER + aco.getIdentificator();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#checkUpdateCondition(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected boolean checkUpdateCondition(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {
		// De momento no comparamos nada, siempre se debe
		// actualizar lo que haya
		// en caché.
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCache#getMsgNotConditionForUpdateAccomplished(es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject, es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject)
	 */
	@Override
	protected String getMsgNotConditionForUpdateAccomplished(ConfigurationCacheObject cachedCco, ConfigurationCacheObject cco) {
		ApplicationCacheObject aco = (ApplicationCacheObject) cco;
		return Language.getFormatResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG003, new Object[ ] { aco.getApplicationId() });

	}

	/**
	 * Gets the Application identified by the input parameter from the caché.
	 *
	 * @param idApplication Application identifier.
	 * @param inLoadingCache flag that indicates if the operation must be executed on the loadin auxiliar cache.
	 * @return a {@link ApplicationCacheObject} that represents the applicaiton to get.<code>null</code> if it does not exist.
	 * @throws ApplicationCacheException In case of some error getting from cache the application
	 */
	public ApplicationCacheObject getApplication(long idApplication, boolean inLoadingCache) throws ApplicationCacheException {
		ApplicationCacheObject result = null;
		// Se construye la ruta en la cache´
		String[ ] pathRelationIdPath = new String[NumberConstants.NUM3];
		pathRelationIdPath[0] = PATH_BASE;
		pathRelationIdPath[1] = PATH_BY_ID;
		pathRelationIdPath[2] = String.valueOf(idApplication);

		// obtenemos el path de la aplicación
		String applicationPath = null;
		try {
			applicationPath = getStringFromPath(pathRelationIdPath, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e);
		}

		// se recupera la aplicación a partir del path si este no es nulo
		if (applicationPath != null) {
			result = getApplication(applicationPath, inLoadingCache);
		}
		return result;
	}

	/**
	 * Gets the Application identified by the input parameter from the cache.
	 *
	 * @param applicationIdentifier Application identifier.
	 * @param inLoadingCache Flag the indicates if the operation must be executed on the loading auxiliar cache.
	 * @return A {@link ApplicationCacheObject} that represents the application to get. <code>null</code> if it does not exist.
	 * @throws ApplicationCacheException In case of some error getting from cache the application.
	 */
	public ApplicationCacheObject getApplication(String applicationIdentifier, boolean inLoadingCache) throws ApplicationCacheException {
		// se construye la ruta en la caché
		String[ ] path = new String[NumberConstants.NUM3];
		path[0] = PATH_BASE;
		path[1] = PATH_BY_IDENTIFIER;
		path[2] = applicationIdentifier;

		try {
			return (ApplicationCacheObject) getConfigurationCacheObject(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e);
		}

	}

	/**
	 * Removes the Application identified by the input parameter from the cache.
	 *
	 * @param applicationId Application identifier.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loadin auxiliar cache.
	 * @throws ApplicationCacheException In case of some error removing form cache the application.
	 */
	public void removeApplication(long applicationId, boolean inLoadingCache) throws ApplicationCacheException {
		// se contruye la ruta en la caché
		String[ ] pathRelationIdPath = new String[NumberConstants.NUM3];
		pathRelationIdPath[0] = PATH_BASE;
		pathRelationIdPath[1] = PATH_BY_ID;
		pathRelationIdPath[2] = String.valueOf(applicationId);

		// se obtiene la ruta de la aplicación
		String appPath = null;
		try {
			appPath = getStringFromPath(pathRelationIdPath, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e);
		}

		// si no existe, tampoco existe la aplicación
		if (appPath != null) {
			// Se construye la ruta de la aplicación.
			String[ ] path = new String[NumberConstants.NUM3];
			path[0] = PATH_BASE;
			path[1] = PATH_BY_IDENTIFIER;
			path[2] = String.valueOf(appPath);

			// eliminamos la relación y la aplicación
			try {
				clearObjectPathFromConfigurationCache(pathRelationIdPath, inLoadingCache);
				clearObjectPathFromConfigurationCache(path, inLoadingCache);
			} catch (ConfigurationCacheException e) {
				throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e);
			}

		}
	}

	/**
	 * This method remove all the applications from the cache.
	 *
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loadin auxiliar cache.
	 * @throws ApplicationCacheException In case of some error while is cleaning the applications cache.
	 */
	public void clearApplicationCache(boolean inLoadingCache) throws ApplicationCacheException {
		// se construye la ruta en la caché
		String[ ] path = new String[NumberConstants.NUM1];
		path[0] = PATH_BASE;

		// se eliminan todas las aplicaciones de la caché.
		try {
			clearNodePathFromConfigurationCache(path, inLoadingCache);
		} catch (ConfigurationCacheException e) {
			throw new ApplicationCacheException(IValetException.COD_191, e.getErrorDescription(), e);
		}

	}

}
