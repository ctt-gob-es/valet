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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents a TSL Country/Region Info in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 29/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.tsl.elements;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectCloneException;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectStreamException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;

/**
 * <p>Class that represents a TSL Country/Region Info in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 29/10/2018.
 */
public class TSLCountryRegionCacheObject extends ConfigurationCacheObject {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -1635544443978610345L;

	/**
	 * Attribute that represents the country/region ID.
	 */
	private long countryRegionId = -1;

	/**
	 * Attribute that represents the country/region code.
	 */
	private String code = null;

	/**
	 * Attribute that represents the country/region name.
	 */
	private String name = null;

	/**
	 * Attribute that represents the TSL Data Id associated to this country/region.
	 */
	private Long tslDataId = null;

	/**
	 * Attribute that represents the mapping associated to this country/region.
	 */
	private Set<TSLCountryRegionMappingCacheObject> mappingSet = null;

	/**
	 * Constructor method for the class TSLCountryRegionCacheObject.java.
	 */
	public TSLCountryRegionCacheObject() {
		super();
		mappingSet = new TreeSet<TSLCountryRegionMappingCacheObject>();
	}

	/**
	 * Constructor method for the class TSLCountryRegionCacheObject.java.
	 * @param tcr Object that represents a TSL Country/Region.
	 * @throws TSLCacheException If the input POJO is <code>null</code>.
	 */
	public TSLCountryRegionCacheObject(TslCountryRegion tcr) throws TSLCacheException {

		this();

		// Si el pojo recibido es nulo, se lanza una excepción ya que no se
		// puede inicializar el objeto.
		if (tcr == null) {
			throw new TSLCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_TSL_CACHE_LOG092));
		} else {

			setCountryRegionId(tcr.getIdTslCountryRegion().longValue());
			setCode(tcr.getCountryRegionCode());
			setName(tcr.getCountryRegionName());

			// Obtenemos la lista de mapeos.
			List<TslCountryRegionMapping> tcrmList = tcr.getListTslCountryRegionMapping();
			// Si no es nula ni vacía, la recorremos creando los objectos de
			// caché
			// para finalmente establecerla.
			if (tcrmList != null && !tcrmList.isEmpty()) {

				Set<TSLCountryRegionMappingCacheObject> tcrmcoSet = new TreeSet<TSLCountryRegionMappingCacheObject>();
				for (TslCountryRegionMapping tcrm: tcrmList) {
					tcrmcoSet.add(new TSLCountryRegionMappingCacheObject(tcrm));
				}
				setMappingSet(tcrmcoSet);

			}

		}

	}

	/**
	 * Gets the value of the attribute {@link #countryRegionId}.
	 * @return the value of the attribute {@link #countryRegionId}.
	 */
	public final long getCountryRegionId() {
		return countryRegionId;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionId}.
	 * @param countryRegionIdParam The value for the attribute {@link #countryRegionId}.
	 */
	public final void setCountryRegionId(long countryRegionIdParam) {
		this.countryRegionId = countryRegionIdParam;
	}

	/**
	 * Gets the value of the attribute {@link #code}.
	 * @return the value of the attribute {@link #code}.
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * Sets the value of the attribute {@link #code}.
	 * @param codeParam The value for the attribute {@link #code}.
	 */
	public final void setCode(String codeParam) {
		this.code = codeParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public final void setName(String nameParam) {
		this.name = nameParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslDataId}.
	 * @return the value of the attribute {@link #tslDataId}.
	 */
	public final Long getTslDataId() {
		return tslDataId;
	}

	/**
	 * Sets the value of the attribute {@link #tslDataId}.
	 * @param tslDataIdParam The value for the attribute {@link #tslDataId}.
	 */
	public final void setTslDataId(Long tslDataIdParam) {
		this.tslDataId = tslDataIdParam;
	}

	/**
	 * Gets the value of the attribute {@link #mappingSet}.
	 * @return the value of the attribute {@link #mappingSet}.
	 */
	public final Set<TSLCountryRegionMappingCacheObject> getMappingSet() {
		return mappingSet;
	}

	/**
	 * Sets the value of the attribute {@link #mappingSet}.
	 * @param mappingSetParam The value for the attribute {@link #mappingSet}.
	 */
	public final void setMappingSet(Set<TSLCountryRegionMappingCacheObject> mappingSetParam) {
		this.mappingSet = mappingSetParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#writeReplace()
	 */
	@Override
	protected Object writeReplace() throws ConfigurationCacheObjectStreamException {

		try {
			return this.clone();
		} catch (CloneNotSupportedException e) {
			throw new ConfigurationCacheObjectStreamException(e.getMessage());
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#readResolve()
	 */
	@Override
	protected Object readResolve() throws ConfigurationCacheObjectStreamException {

		try {
			return this.clone();
		} catch (CloneNotSupportedException e) {
			throw new ConfigurationCacheObjectStreamException(e.getMessage());
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#clone()
	 */
	@Override
	public ConfigurationCacheObject clone() throws ConfigurationCacheObjectCloneException {

		TSLCountryRegionCacheObject tcrco = new TSLCountryRegionCacheObject();

		tcrco.setCountryRegionId(getCountryRegionId());
		tcrco.setCode(getCode());
		tcrco.setName(getName());
		tcrco.setTslDataId(getTslDataId().longValue());

		for (TSLCountryRegionMappingCacheObject tcrmco: getMappingSet()) {
			tcrco.getMappingSet().add((TSLCountryRegionMappingCacheObject) tcrmco.clone());
		}

		return tcrco;

	}

}
