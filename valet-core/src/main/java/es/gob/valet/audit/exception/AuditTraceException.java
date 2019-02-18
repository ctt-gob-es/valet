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
 * <b>File:</b><p>es.gob.valet.audit.exception.AuditTraceException.java.</p>
 * <b>Description:</b><p>Class that represents an exception for an audit trace generation or save.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/02/2019.
 */
package es.gob.valet.audit.exception;

import es.gob.valet.exceptions.ValetException;

/**
 * <p>Class that represents an exception for an audit trace generation or save.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/02/2019.
 */
public class AuditTraceException extends ValetException {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 5650008599599929476L;

	/**
	 * Constructor method for the class AuditTraceException.java.
	 */
	public AuditTraceException() {
		super();
	}

	/**
	 * Constructor method for the class AuditTraceException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 */
	public AuditTraceException(String errorCode, String errorDesc) {
		super(errorCode, errorDesc);
	}

	/**
	 * Constructor method for the class AuditTraceException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Description for the error.
	 * @param exception Exception that causes the error.
	 */
	public AuditTraceException(String errorCode, String errorDesc, Exception exception) {
		super(errorCode, errorDesc, exception);
	}

}
