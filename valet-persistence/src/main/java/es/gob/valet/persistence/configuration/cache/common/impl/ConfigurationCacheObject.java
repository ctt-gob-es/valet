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
 * @version 1.1, 15/09/2023.
 */
package es.gob.valet.persistence.configuration.cache.common.impl;

import java.io.Serializable;

/**
 * <p>Abstract class that represents a configuration object in the cache.</p>
 * <p>All the objects that extends this class must implements the interfaces {@link Cloneable} and {@link Serializable}.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 15/09/2023.
 */
public abstract class ConfigurationCacheObject implements Serializable {

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
}
