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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.items.tsl.elements.TSLCountryRegionMappingCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents a TSL mapping in the configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 22/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.items.tsl.elements;

import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectCloneException;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectStreamException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;


/** 
 * <p>Class that represents a TSL mapping in the configuration cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/10/2018.
 */
public class TSLCountryRegionMappingCacheObject extends ConfigurationCacheObject {

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
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#writeReplace()
	 */
	@Override
	protected Object writeReplace() throws ConfigurationCacheObjectStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#readResolve()
	 */
	@Override
	protected Object readResolve() throws ConfigurationCacheObjectStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#clone()
	 */
	@Override
	public ConfigurationCacheObject clone() throws ConfigurationCacheObjectCloneException {
		// TODO Auto-generated method stub
		return null;
	}

}
