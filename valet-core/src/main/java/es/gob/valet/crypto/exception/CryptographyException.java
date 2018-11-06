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
 * <b>File:</b><p>es.gob.valet.crypto.exception.CryptographyException.java.</p>
 * <b>Description:</b><p>Class that manages the errors related with the management of keystores in the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/11/2018.
 */
package es.gob.valet.crypto.exception;

import es.gob.valet.exceptions.ValetException;

/**
 * <p>Class that manages the errors related with the management of keystores in the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/11/2018.
 */
public class CryptographyException extends ValetException {

	/**
	 * Attribute that represents class serial version.
	 */
	private static final long serialVersionUID = -149578704699248361L;

	/**
	 * Constructor method for the class CryptographyException.java.
	 */
	public CryptographyException() {
		super();
	}

	/**
	 * Constructor method for the class CryptographyException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 */
	public CryptographyException(String errorCode, String errorDesc) {
		super(errorCode, errorDesc);
	}

	/**
	 * Constructor method for the class CryptographyException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 * @param exception Exception that causes the error.
	 */
	public CryptographyException(String errorCode, String errorDesc, Exception exception) {
		super(errorCode, errorDesc, exception);
	}

}
