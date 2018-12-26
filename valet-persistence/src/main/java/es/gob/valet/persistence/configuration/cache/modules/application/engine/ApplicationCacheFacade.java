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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.application.engine.ApplicationCacheFacade.java.</p>
 * <b>Description:</b><p> Facade for all the applications configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/12/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.application.engine;

import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.cache.modules.application.elements.ApplicationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.application.exceptions.ApplicationCacheException;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Facade for all the applications configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/12/2018.
 */
public final class ApplicationCacheFacade {

	/**
	 * Attribute that represents the unique instance of the application cache facade.
	 */
	private static ApplicationCacheFacade instance = null;

	/**
	 * Constructor method for the class ApplicationCacheFacade.java.
	 */
	private ApplicationCacheFacade() {
		super();
	}

	/**
	 * Gets the unique instance of the application cache facade.
	 *
	 * @return the unique instance of the application cache facade.
	 */
	public static ApplicationCacheFacade getInstance() {
		if (instance == null) {
			instance = new ApplicationCacheFacade();
		}
		return instance;
	}

	/**
	 * Gets the application representation from the clustered cache. If it does not exist, try to get form the database.
	 *
	 * @param applicationId Application identifier.
	 * @return A object representation of the application in the clustered cache. <code>null</code> if it does not exist.
	 * @throws ApplicationCacheException In case of some error getting the application form the clustered cache.
	 */
	public ApplicationCacheObject getApplicationCacheObject(long applicationId) throws ApplicationCacheException {
		ApplicationCacheObject result = null;

		// se intenta recuperar de caché
		result = ApplicationCache.getInstance().getApplication(applicationId, false);

		// si no existe, se obtiene de la base de datos
		if (result == null) {
			ApplicationValet app = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().getApplicationById(applicationId);
			// si existe en base de datos, se incluye en la caché
			if (app != null) {
				result = ApplicationCache.getInstance().addApplication(app, false);
			}
		}
		return result;
	}

	/**
	 * Gets the application representation from the clustered cache. If it does not exist, try to get form the database.
	 *
	 * @param applicationIdentifier Application identifier.
	 * @return A object representation of the application in the clustered cache. <code>null</code> if it does not exist.
	 * @throws ApplicationCacheException In case of some error getting the application form the clustered cache.
	 */
	public ApplicationCacheObject getApplicationCacheObject(String applicationIdentifier) throws ApplicationCacheException {
		ApplicationCacheObject result = null;

		// se intenta recuperar de caché
		result = ApplicationCache.getInstance().getApplication(applicationIdentifier, false);

		// si no existe, se obtiene de la base de datos
		if (result == null) {
			ApplicationValet app = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().getApplicationByIdentificator(applicationIdentifier);
			// si existe en base de datos, se incluye en la caché
			if (app != null) {
				ApplicationCache.getInstance().addApplication(app, false);
			}
		}
		return result;
	}

	/**
	 * Adds or update the application in the configuration cache.
	 * @param aco Object reprensentation of the application in the configuration cache.
	 * @return Application cache object added/updated in the configuration cache.
	 * @throws ApplicationCacheException In case of some error adding/updating the application in the cache.
	 */
	public ApplicationCacheObject addUpdateApplication(ApplicationCacheObject aco) throws ApplicationCacheException{
		return ApplicationCache.getInstance().addApplication(aco, false);
	}

	/**
	 * Method that removes all the applications from the cache.
	 * @throws ApplicationCacheException If there was some error cleaning the applications cache.
	 */
	public void clearApplicationCache() throws ApplicationCacheException {
		ApplicationCache.getInstance().clearApplicationCache(false);
	}

	/**
	 * Removes the application from the clustered cache.
	 * @param applicationId Application identifier.
	 * @throws ApplicationCacheException In case of some error removing the application from the clustered cache.
	 */
	public void removeApplication(long applicationId) throws ApplicationCacheException {

		ApplicationCache.getInstance().removeApplication(applicationId, false);

	}

	/**
	 * Reloads the application in the cache. If it does not exist, try to get from the data base.
	 *
	 * @param appId Application identifier.
	 * @return A object representation of the application in the clustered cache. <code>null</code> if it does not exist.
	 * @throws ApplicationCacheException In case of some error reloading the applicatin from the cache.
	 */
	public ApplicationCacheObject reloadApplication(long appId) throws ApplicationCacheException{
		removeApplication(appId);
		return getApplicationCacheObject(appId);
	}


}
