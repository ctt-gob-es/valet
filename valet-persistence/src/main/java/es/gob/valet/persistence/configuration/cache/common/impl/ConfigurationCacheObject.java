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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject.java.</p>
 * <b>Description:</b><p>Abstract class that represents a configuration object in the cache.</p>
 * <p>All the objects that extends this class must implements the interfaces {@link Cloneable} and {@link Serializable}.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 22/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.common.impl;

import java.io.Serializable;

import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectCloneException;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectStreamException;

/**
 * <p>Abstract class that represents a configuration object in the cache.</p>
 * <p>All the objects that extends this class must implements the interfaces {@link Cloneable} and {@link Serializable}.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/10/2018.
 */
public abstract class ConfigurationCacheObject implements Cloneable, Serializable {

	/**
	 * Constant attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 141089319780845424L;

	/**
	 * Constructor method for the class ConfigurationCacheObject.java.
	 */
	public ConfigurationCacheObject() {
		super();
	}

	/**
	 * Method that builds a new {@link ConfigurationCacheObject} object so contain the same configuration object that the object where is executed.
	 * This method is executed before serialize the object.
	 * @return an object that represents the object that invokes the method, but this new object can be serialized.
	 * @throws ConfigurationCacheObjectStreamException If the method fails in the data streaming.
	 */
	protected abstract Object writeReplace() throws ConfigurationCacheObjectStreamException;

	/**
	 * Method that builds the configuration object from the bytes array that represents it. This method is executed before to unserialize the object.
	 * @return an object that represents the object that invokes the method, but with the restored data configuration.
	 * @throws ConfigurationCacheObjectStreamException If the method fails in the data streaming.
	 */
	protected abstract Object readResolve() throws ConfigurationCacheObjectStreamException;

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract ConfigurationCacheObject clone() throws ConfigurationCacheObjectCloneException;

}
