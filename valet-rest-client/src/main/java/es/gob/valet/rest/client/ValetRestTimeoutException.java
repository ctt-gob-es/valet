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
 * <b>File:</b><p>es.gob.valet.rest.client.ValetRestTimeoutException.java.</p>
 * <b>Description:</b><p> Class that manages exceptions produced by network timeout in valet rest client.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>27/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 27/09/2018.
 */
package es.gob.valet.rest.client;

import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.UtilsXML;
import es.gob.valet.exceptions.ValetRestException;

/** 
 * <p>Class that manages exceptions produced by network timeout in valet rest client.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 27/09/2018.
 */
public class ValetRestTimeoutException extends ValetRestException {

	/**
	 * Constant  ttribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -8594306901440538861L;

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ValetRestTimeoutException.class);

	/**
	 * Attribute that represents the error code.
	 */
	private String errorCode;

	/**
	 * Attribute that represents a description associated to the error.
	 */
	private String errorDesc;

	/**
	 * Attribute that represents a java exception associated to the error. It is optional.
	 */
	private Exception exception;

	/**
	 * Constructor method for the class ValetRestTimeoutException.java.
	 */
	public ValetRestTimeoutException() {
		super();
	}

	/**
	 * Constructor method for the class ValetRestTimeoutException.java.
	 * @param errorCodeParam Error code.
	 * @param errorDescParam Description for the error.
	 */
	public ValetRestTimeoutException(final String errorCodeParam, final String errorDescParam) {
		super(errorDescParam);
		errorCode = errorCodeParam;
		errorDesc = errorDescParam;
		LOGGER.warn(errorDescParam);
	}

	/**
	 * Constructor method for the class ValetRestTimeoutException.java.
	 * @param errorCodeParam Error code.
	 * @param errorDescParam Description for the error.
	 * @param exceptionParam Exception that causes the error.
	 */
	public ValetRestTimeoutException(final String errorCodeParam, final String errorDescParam, final Exception exceptionParam) {
		super(errorDescParam);
		errorCode = errorCodeParam;
		errorDesc = errorDescParam;
		exception = exceptionParam;
		LOGGER.warn(errorDescParam);
	}

	/**
	 * Gets the value of the attribute {@link #errorCode}.
	 * @return the value of the attribute {@link #errorCode}.
	 */
	public final String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the value of the attribute {@link #errorCode}.
	 * @param errorCodePAram The value for the attribute {@link #errorCode}.
	 */
	public final void setErrorCode(final String errorCodePAram) {
		errorCode = errorCodePAram;
	}

	/**
	 * Gets the value of the attribute {@link #errorDesc}.
	 * @return the value of the attribute {@link #errorDesc}.
	 */
	public final String getErrorDescription() {
		return errorDesc;
	}

	/**
	 * Sets the value of the attribute {@link #errorDesc}.
	 * @param errorDescParam The value for the attribute {@link #errorDesc}.
	 */
	public final void setErrorDescription(final String errorDescParam) {
		errorDesc = UtilsXML.escapeXml10(errorDescParam);
	}

	/**
	 * Gets the value of the attribute {@link #exception}.
	 * @return the value of the attribute {@link #exception}.
	 */
	public final Exception getException() {
		return exception;
	}

	/**
	 * Sets the value of the attribute {@link #exception}.
	 * @param exceptionParam The value for the attribute {@link #exception}.
	 */
	public final void setException(final Exception exceptionParam) {
		exception = exceptionParam;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public final String toString() {

		if (exception == null) {
			return "EXCEPTION_CODE: [" + errorCode + "]; DESCRIPTION: [" + errorDesc + "]";
		} else {
			return "EXCEPTION_CODE: [" + errorCode + "]; DESCRIPTION: [" + errorDesc + "]; CAUSE: " + exception.toString();
		}

	}

}
