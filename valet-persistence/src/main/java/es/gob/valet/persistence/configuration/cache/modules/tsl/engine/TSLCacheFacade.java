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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.tsl.engine.TSLCacheFacade.java.</p>
 * <b>Description:</b><p>Facade for all the TSL configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/11/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.tsl.engine;

import java.io.Serializable;
import java.util.Set;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLLocationAndIdRelationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Facade for all the TSL configuration cache objects operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/11/2018.
 */
public final class TSLCacheFacade {

	/**
	 * Attribute that represents the unique instance of the TSL cache facade.
	 */
	private static TSLCacheFacade instance = null;

	/**
	 * Constructor method for the class TSLCacheFacade.java.
	 */
	private TSLCacheFacade() {
		super();
	}

	/**
	 * Gets the unique instance of the TSL cache facade.
	 * @return the unique instance of the TSL cache facade.
	 */
	public static TSLCacheFacade getInstance() {

		if (instance == null) {
			instance = new TSLCacheFacade();
		}
		return instance;

	}

	/**
	 * Gets the TSL Country/Region representation from the configuration cache. If it does not exists, try to get from the data base.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return A object representation of the TSL Country/Region in the configuration cache. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting the TSL Country/Region from the configuration cache.
	 * @throws BusinessObjectException In case of some error getting the information about TSL from the data base.
	 * @throws DatabaseConnectionException In case of some error with the data base connection.
	 */
	public TSLCountryRegionCacheObject getTSLCountryRegionCacheObject(String countryRegionCode) throws TSLCacheException {

		TSLCountryRegionCacheObject result = null;

		// Pasamos a mayúsculas el código de país/región.
		String countryRegionCodeUpperCase = countryRegionCode == null ? null : countryRegionCode.toUpperCase();

		// Tratamos de recuperarlo de la caché.
		result = TSLCache.getInstance().getTSLCountryRegion(countryRegionCodeUpperCase, false);

		// Si no lo hemos encontrado...
		if (result == null) {

			// Lo extraemos de base de datos.
			TslCountryRegion tcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionByCode(countryRegionCodeUpperCase, true);

			// Si existe en base de datos...
			if (tcr != null) {

				// Creamos su representación en caché.
				TSLCountryRegionCacheObject tcrco = new TSLCountryRegionCacheObject(tcr);

				// Lo añadimos en la caché y lo devolvemos.
				result = TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

			}

		}

		return result;

	}

	/**
	 * Adds or updates the basic configuration about a country/region for TSL in the configuration cache.
	 * @param tcrco TSL Country/Region object representetation for the cache.
	 * @return The added/updated country/region object representation.
	 * @throws TSLCacheException In case of some error adding/updating the TSL country/region in the configuration cache.
	 */
	public TSLCountryRegionCacheObject addUpdateBasicTSLCountryRegion(TSLCountryRegionCacheObject tcrco) throws TSLCacheException {

		return TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

	}

	/**
	 * Removes the TSL country/region, specified by its code, from the configuration cache.
	 * Also removes all the TSL Data associated.
	 * @param countryRegionCode TSL Country/Region code.
	 * @throws TSLCacheException In case of some error removing the TSL country/region from the configuration cache.
	 */
	public void removeTSLCountryRegion(String countryRegionCode) throws TSLCacheException {

		// Pasamos a mayúsculas el código de país/región.
		String countryRegionCodeUpperCase = countryRegionCode == null ? null : countryRegionCode.toUpperCase();

		// Tratamos de recuperarlo de la caché.
		TSLCountryRegionCacheObject tcrco = TSLCache.getInstance().getTSLCountryRegion(countryRegionCodeUpperCase, false);

		// Si lo hemos encontrado...
		if (tcrco != null) {

			// Hay que eliminar todos los mapeos y TSL asociada.
			// Los mapeos se borran al borrar el país/región.
			// Recuperamos el ID de las TSL asociada para borrarla.
			Long tslDataId = tcrco.getTslDataId();
			// Si hay alguna TSL asignada, la eliminamos.
			if (tslDataId != null) {
				TSLCache.getInstance().removeTSLData(tslDataId, false);
			}
			// Se elimina el país/región.
			TSLCache.getInstance().removeTSLCountryRegion(countryRegionCodeUpperCase, false);

		}

	}

	/**
	 * Gets the TSL data representation from the configuration cache.
	 * This method does not load information from the data base.
	 * @param tslDataId TSL identifier.
	 * @return A object representation of the TSL data in the configuration cache. <code>null</code> if it does not exist.
	 * @throws TSLCacheException In case of some error getting the TSL data from the configuration cache.
	 */
	public TSLDataCacheObject getTSLDataCacheObject(long tslDataId) throws TSLCacheException {

		// Tratamos de recuperarlo de la caché.
		return TSLCache.getInstance().getTSLData(tslDataId, false);

	}

	/**
	 * Gets the TSL associated to a specific country/region.
	 * This method does not load the TSL Data from the data base.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return the TSL associated to the country/region specified if it is defined, otherwise <code>null</code>.
	 * @throws TSLCacheException In case of some error getting the TSL information from the clustered cache.
	 */
	public TSLDataCacheObject getTSLDataFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		TSLDataCacheObject result = null;

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos recuperado...
		if (tcrco != null) {

			// Obtenemos el id de la TSL asociada (si la hay).
			Long tslDataId = tcrco.getTslDataId();

			// Si no es nulo, lo obtenemos de base de datos.
			result = getTSLDataCacheObject(tslDataId);

		}

		return result;

	}

	/**
	 * Gets the TSL data with the specified TSL location.
	 * This method does not load the TSL Data from the data base.
	 * @param tslLocation TSL location to find.
	 * @return TSL data Cache Object with the specified TSL location, <code>null</code>
	 * if there is not.
	 * @throws TSLCacheException In case of some error getting the TSL Data from the configuration cache.
	 */
	public TSLDataCacheObject getTSLDataFromLocation(String tslLocation) throws TSLCacheException {

		TSLDataCacheObject result = null;

		// Si el parámetro de entrada es nulo, lanzamos excepción.
		if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
			throw new TSLCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_TSL_CACHE_LOG066));
		}

		// Obtenemos el objeto con las relaciones de la caché.
		TSLLocationAndIdRelationCacheObject tlairco = TSLCache.getInstance().getTSLLocationAndIdRelationCacheObject(false);

		// Si no es nulo, continuamos.
		if (tlairco != null) {

			// Recuperamos el id de la TSL de la localización indicada.
			Long tslDataId = tlairco.getTslDataIdFromLocation(tslLocation);

			// Si no es nulo...
			if (tslDataId != null) {

				// Obtenemos y recuperamos el TSL Data asociado.
				result = getTSLDataCacheObject(tslDataId);

			}

		}

		return result;

	}

	/**
	 * Adds/Updates a TSL Data in the configuration cache.
	 * If the TSL country/region does not exist, then do nothing.
	 * @param tslData TSL Data Object POJO representation to add in the configuration cache.
	 * @param tslObjectSerializable TSL parsed representation to add with the POJO representation in the cache.
	 * @return TSL Data cache object added in cache. <code>null</code> if the country/region does not exists.
	 * @throws TSLCacheException In case of some error adding/updating the TSL Data in the configuration cache.
	 */
	public TSLDataCacheObject addUpdateTSLData(TslData tslData, Serializable tslObjectSerializable) throws TSLCacheException {

		TSLDataCacheObject result = null;

		// Obtenemos el código de país/región.
		String countryRegionCode = tslData.getTslCountryRegion().getCountryRegionCode();

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos recuperado...
		if (tcrco != null) {

			// Generamos un nuevo objeto TSL Data para la caché.
			result = new TSLDataCacheObject(tslData, tslObjectSerializable);

			// Añadimos el ID de la TSL a los recogidos por el país/región.
			tcrco.setTslDataId(result.getTslDataId());

			// Añadimos/Actualizamos el objeto TSL Data en caché.
			result = TSLCache.getInstance().addTSLData(result, false);

			// Actualizamos el país/región en caché.
			TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

		}

		return result;

	}

	/**
	 * Removes a TSL Data from the configuration cache, identified by its country/region and ID. Also removes the relation with its country/region.
	 * @param countryRegionCode Country/Region code which hash the TSL data to remove.
	 * @throws TSLCacheException In case of some error removing the TSL Data in the configuration cache.
	 */
	public void removeTSLDataFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos recuperado correctamente...
		if (tcrco != null) {

			// Recuperamos el id del TSL Data a eliminar.
			Long tslDataId = tcrco.getTslDataId();

			// Si no es nulo, lo eliminamos de la caché...
			if (tslDataId != null) {
				TSLCache.getInstance().removeTSLData(tslDataId, false);
			}

			// Eliminamos la TSL del país/región.
			tcrco.setTslDataId(null);

			// Actualizamos el país/región en caché.
			TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

		}

	}

	/**
	 * Gets the mapping from the specified country/region code. <code>null</code> if the country/region is not defined.
	 * @param countryRegionCode TSL Country/Region code.
	 * @return Set of mapping from the specified country/region code.
	 * @throws TSLCacheException In case of some error getting the TSL Mapping from the configuration cache.
	 */
	public Set<TSLCountryRegionMappingCacheObject> getMappingFromCountryRegion(String countryRegionCode) throws TSLCacheException {

		Set<TSLCountryRegionMappingCacheObject> result = null;

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos recuperado correctamente...
		if (tcrco != null) {

			// Obtenemos el conjunto de mapeos asociados.
			result = tcrco.getMappingSet();

		}

		return result;

	}

	/**
	 * Adds or updates a TSL mapping for a specific country/region, and return it.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrm TSL Country/Region Mapping representation in the data base.
	 * @return the added/updated TSL mapping representation in the configuration cache.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public TSLCountryRegionMappingCacheObject addUpdateMappingToCountryRegion(String countryRegionCode, TslCountryRegionMapping tcrm) throws TSLCacheException {

		TSLCountryRegionMappingCacheObject result = null;

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos obtenido...
		if (tcrco != null) {

			// Construimos el objeto que en caché representará al mapeo.
			TSLCountryRegionMappingCacheObject tcrmco = new TSLCountryRegionMappingCacheObject(tcrm);

			// Lo eliminamos si es que ya existe.
			tcrco.getMappingSet().remove(tcrmco);
			// Lo añadimos en el país/región.
			tcrco.getMappingSet().add(tcrmco);

			// Actualizamos el país/región en la caché.
			TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

		}

		return result;

	}

	/**
	 * Removes a TSL mapping from a specific country/region.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrmco TSL Country/Region Mapping representation in the configuration cache.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public void removeMappingFromCountryRegion(String countryRegionCode, TSLCountryRegionMappingCacheObject tcrmco) throws TSLCacheException {

		if (tcrmco != null) {

			// Recuperamos la información del país/región.
			TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

			// Si la hemos obtenido...
			if (tcrco != null) {

				// Lo eliminamos si es que ya existe.
				tcrco.getMappingSet().remove(tcrmco);

				// Actualizamos el país/región en la caché.
				TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

			}

		}

	}

	/**
	 * Removes a TSL mapping from a specific country/region.
	 * @param countryRegionCode TSL Country/Region code.
	 * @param tcrmcoId TSL Country/Region Mapping ID.
	 * @throws TSLCacheException In case of some error adding/updating a TSL Mapping in the configuration cache.
	 */
	public void removeMappingFromCountryRegion(String countryRegionCode, long tcrmcoId) throws TSLCacheException {

		// Recuperamos la información del país/región.
		TSLCountryRegionCacheObject tcrco = getTSLCountryRegionCacheObject(countryRegionCode);

		// Si la hemos obtenido...
		if (tcrco != null) {

			// Vamos recorriendo el conjunto de mapeos hasta encontrar el que
			// corresponde con el ID.
			Set<TSLCountryRegionMappingCacheObject> mappingsSet = tcrco.getMappingSet();
			if (mappingsSet != null && !mappingsSet.isEmpty()) {

				TSLCountryRegionMappingCacheObject tslcrmcoFinded = null;
				for (TSLCountryRegionMappingCacheObject tslcrmco: mappingsSet) {

					if (tslcrmco.getMappingId() == tcrmcoId) {

						tslcrmcoFinded = tslcrmco;
						break;

					}

				}

				// Si lo hemos encontrado...
				if (tslcrmcoFinded != null) {

					// Lo eliminamos.
					mappingsSet.remove(tslcrmcoFinded);
					// Actualizamos el país/región en la caché.
					tcrco.setMappingSet(mappingsSet);
					TSLCache.getInstance().addTSLCountryRegion(tcrco, false);

				}

			}

		}

	}

	/**
	 * Method that removes all the TSL platform from the cache.
	 * @throws TSLCacheException If there was some error cleaning the TSL platforms cache.
	 */
	public void clearTSLCache() throws TSLCacheException {
		TSLCache.getInstance().clearTSLCache(false);
	}

}
