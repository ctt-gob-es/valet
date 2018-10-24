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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents a TSL mapping in the configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.tsl.elements;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectCloneException;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectStreamException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.utils.IAssociationTypeIdConstants;

/**
 * <p>Class that represents a TSL mapping in the configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/10/2018.
 */
public class TSLCountryRegionMappingCacheObject extends ConfigurationCacheObject implements Comparable<TSLCountryRegionMappingCacheObject> {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -2144231443937453825L;

	/**
	 * Attribute that represents the mapping ID.
	 */
	private long mappingId = -1;

	/**
	 * Attribute that represents the mapping identificator.
	 */
	private String identificator = null;

	/**
	 * Attribute that represents the mapping description.
	 */
	private String description = null;

	/**
	 * Attribute that represents the mapping value.
	 */
	private String value = null;

	/**
	 * Attribute that represents the association type.
	 * It only can be {@link IAssociationTypeIdConstants#ID_FREE_ASSOCIATION} or {@link IAssociationTypeIdConstants#ID_SIMPLE_ASSOCIATION}.
	 */
	private long associationType = -1;

	/**
	 * Constructor method for the class TSLCountryRegionMappingCacheObject.java.
	 */
	public TSLCountryRegionMappingCacheObject() {
		super();
	}

	/**
	 * Constructor method for the class TSLCountryRegionMappingCacheObject.java.
	 * @param tcrm Object that represents a TSL Mapping assigned to a specific country/region.
	 * @throws TSLCacheException If the input POJO is <code>null</code>.
	 */
	public TSLCountryRegionMappingCacheObject(TslCountryRegionMapping tcrm) throws TSLCacheException {

		this();

		// Si el pojo recibido es nulo, se lanza una excepción ya que no se
		// puede inicializar el objeto.
		if (tcrm == null) {
			throw new TSLCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_TSL_CACHE_LOG090));
		} else {

			setMappingId(tcrm.getIdTslCountryRegionMapping().longValue());
			setIdentificator(tcrm.getMappingIdentificator());
			setDescription(tcrm.getMappingDescription());
			setValue(tcrm.getMappingValue());
			setAssociationType(tcrm.getAssociationType().getIdAssociationType().longValue());

		}

	}

	/**
	 * Gets the value of the attribute {@link #mappingId}.
	 * @return the value of the attribute {@link #mappingId}.
	 */
	public final long getMappingId() {
		return mappingId;
	}

	/**
	 * Sets the value of the attribute {@link #mappingId}.
	 * @param mappingIdParam The value for the attribute {@link #mappingId}.
	 */
	public final void setMappingId(long mappingIdParam) {
		this.mappingId = mappingIdParam;
	}

	/**
	 * Gets the value of the attribute {@link #identificator}.
	 * @return the value of the attribute {@link #identificator}.
	 */
	public final String getIdentificator() {
		return identificator;
	}

	/**
	 * Sets the value of the attribute {@link #identificator}.
	 * @param identificatorParam The value for the attribute {@link #identificator}.
	 */
	public final void setIdentificator(String identificatorParam) {
		this.identificator = identificatorParam;
	}

	/**
	 * Gets the value of the attribute {@link #description}.
	 * @return the value of the attribute {@link #description}.
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the attribute {@link #description}.
	 * @param descriptionParam The value for the attribute {@link #description}.
	 */
	public final void setDescription(String descriptionParam) {
		this.description = descriptionParam;
	}

	/**
	 * Gets the value of the attribute {@link #value}.
	 * @return the value of the attribute {@link #value}.
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * Sets the value of the attribute {@link #value}.
	 * @param valueParam The value for the attribute {@link #value}.
	 */
	public final void setValue(String valueParam) {
		this.value = valueParam;
	}

	/**
	 * Gets the value of the attribute {@link #associationType}.
	 * @return the value of the attribute {@link #associationType}.
	 */
	public final long getAssociationType() {
		return associationType;
	}

	/**
	 * Sets the value of the attribute {@link #associationType}.
	 * @param associationTypeParam The value for the attribute {@link #associationType}.
	 */
	public final void setAssociationType(long associationTypeParam) {
		this.associationType = associationTypeParam;
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

		TSLCountryRegionMappingCacheObject tcrmco = new TSLCountryRegionMappingCacheObject();

		tcrmco.setMappingId(getMappingId());
		tcrmco.setIdentificator(getIdentificator());
		tcrmco.setDescription(getDescription());
		tcrmco.setValue(getValue());
		tcrmco.setAssociationType(getAssociationType());

		return tcrmco;

	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TSLCountryRegionMappingCacheObject o) {

		return getIdentificator().compareTo(o.getIdentificator());

	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		int result = 1;
		result = NumberConstants.NUM31 * result + (int) (associationType ^ associationType >>> NumberConstants.NUM32);
		result = NumberConstants.NUM31 * result + (description == null ? 0 : description.hashCode());
		result = NumberConstants.NUM31 * result + (identificator == null ? 0 : identificator.hashCode());
		result = NumberConstants.NUM31 * result + (int) (mappingId ^ mappingId >>> NumberConstants.NUM32);
		result = NumberConstants.NUM31 * result + (value == null ? 0 : value.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {

		boolean result = false;

		if (obj != null && obj instanceof TSLCountryRegionMappingCacheObject) {

			TSLCountryRegionMappingCacheObject tcrmco = (TSLCountryRegionMappingCacheObject) obj;
			result = getMappingId() == tcrmco.getMappingId();

		}

		return result;

	}

}
