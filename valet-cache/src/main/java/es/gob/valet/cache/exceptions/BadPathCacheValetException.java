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
 * <b>File:</b><p>es.gob.valet.cache.exceptions.BadPathCacheValetException.java.</p>
 * <b>Description:</b><p>Class that manages exceptions produced by an incorrect path in the <i>Cache</i>.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/09/2018.
 */
package es.gob.valet.cache.exceptions;

/**
 * <p>Class that manages exceptions produced by an incorrect path in the <i>Cache</i>.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/09/2018.
 */
public class BadPathCacheValetException extends CacheValetException {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -4424030978661279495L;

	/**
	 * Constructor method for the class BadPathCacheValetException.java.
	 */
	public BadPathCacheValetException() {
		super();
	}

	/**
	 * Constructor method for the class BadPathCacheValetException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 */
	public BadPathCacheValetException(String errorCode, String errorDesc) {
		super(errorCode, errorDesc);
	}

	/**
	 * Constructor method for the class BadPathCacheValetException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 * @param exception Exception that causes the error.
	 */
	public BadPathCacheValetException(String errorCode, String errorDesc, Exception exception) {
		super(errorCode, errorDesc, exception);
	}

}
