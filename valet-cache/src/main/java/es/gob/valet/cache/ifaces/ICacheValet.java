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
 * <b>File:</b><p>es.gob.valet.cache.ifaces.ICacheValet.java.</p>
 * <b>Description:</b><p>Interface that represents a cache manager regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/09/2018.
 */
package es.gob.valet.cache.ifaces;

import es.gob.valet.cache.exceptions.BadPathCacheValetException;
import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.cache.exceptions.ManagingObjectCacheValetException;

/**
 * <p>Interface that represents a cache manager regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/09/2018.
 */
public interface ICacheValet {

	/**
	 * Gets the separator used in the specified implemented cache for the path.
	 * @return a string that represents the separator used in the specified implemented cache for the path.
	 */
	String getSeparator();

	/**
	 * Method entrusted to stop the functioning of the <i>Cache</i> of <b>Valet</b>.
	 * @throws CacheValetException In case of some error stopping the cache.
	 */
	void stopCache() throws CacheValetException;

	/**
	 * Method that adds an object in the path indicated in the <i>Cache</i>.
	 * @param path String array that determine the path in which the indicated object is going to be added.
	 * The last position of the array must be the key for the object to add.
	 * @param obj Object that will be added in the <i>Cache</i>.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException In case of some error when is adding the object in the cache.
	 */
	void addObject(String[ ] path, Object obj, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException;

	/**
	 * Method that obtains the <i>Cache</i> object indicated by the path.
	 * @param path String array that determine the path in which the indicated object is going to be obtained.
	 * The last position of the array must be the key for the object to add.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object placed in the cache, in the indicated path, or <code>null</code> if it does not exist.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException If the method fails.
	 */
	Object getObject(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException;

	/**
	 * Method that eliminates the object contained in the indicated path of the <i>Cache</i>.
	 * @param path String array that determine the path in which the indicated object is going to be removed.
	 * The last position of the array must be the key for the object to remove.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return The object removed from the cache, in the indicated path, or <code>null</code> if it does not exist.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException In case of some error while is removing the entry from the cache.
	 */
	Object removeObject(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException;

	/**
	 * Method that eliminates the node contained in the input path of the <i>Cache</i>.
	 * @param path {@link String} with the access path in the cache.
	 * @param inLoadingCache Flag that indicates if the operation must be executed on the loading auxiliar cache.
	 * @return <code>true</code> if the node has been removed, otherwise <code>false</code>.
	 * @throws BadPathCacheValetException If the path is bad constructed.
	 * @throws ManagingObjectCacheValetException In case of some error while is removing the node from the cache.
	 */
	boolean removeNode(String[ ] path, boolean inLoadingCache) throws BadPathCacheValetException, ManagingObjectCacheValetException;

	/**
	 * Gets the actual name of the clustered configuration cache.
	 * @return actual name of the clustered configuration cache.
	 */
	String getCacheName();

	/**
	 * Starts the auxiliar clustered configuration cache for reloading all the configuration.
	 */
	void startsAuxiliarCache();

	/**
	 * Assigns the auxiliar clustered configuration cache as principal, an then stop the oldest.
	 * @param millisecondsBeforeStopCache number of milliseconds before stop the old cache.
	 * Also inserts the new name in the default cache.
	 */
	void assignAsPrincipalAuxiliarCache(int millisecondsBeforeStopCache);

	/**
	 * Checks if actually is reloading the configuration cache.
	 * @return <code>true</code> if configuration cache is reloading at the moment, otherwise <code>false</code>.
	 */
	boolean isReloadingCacheAtTheMoment();

}
