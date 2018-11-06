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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException.java.</p>
 * <b>Description:</b><p>Class that manages the errors related with the Keystores of the Configuration Cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions;

import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;

/**
 * <p>Class that manages the errors related with the Keystores of the Configuration Cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public class KeystoreCacheException extends ConfigurationCacheException {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -5083754707408914184L;

	/**
	 * Constructor method for the class KeystoreCacheException.java.
	 */
	public KeystoreCacheException() {
		super();
	}

	/**
	 * Constructor method for the class KeystoreCacheException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Error description.
	 */
	public KeystoreCacheException(String errorCode, String errorDesc) {
		super(errorCode, errorDesc);
	}

	/**
	 * Constructor method for the class KeystoreCacheException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Error description.
	 * @param exception Exception that causes the error.
	 */
	public KeystoreCacheException(String errorCode, String errorDesc, Exception exception) {
		super(errorCode, errorDesc, exception);
	}

}
