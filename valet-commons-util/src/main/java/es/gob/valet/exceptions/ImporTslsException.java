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
 * <b>File:</b><p>es.gob.valet.exceptions.ImporTslsException.java.</p>
 * <b>Description:</b><p> Class for encapsulate exceptions thrown by the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/03/2025.
 */
package es.gob.valet.exceptions;

/**
 * <p>Class for encapsulate exceptions thrown by the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/03/2025.
 */
public class ImporTslsException extends Exception {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -1573292739258778204L;

	/**
	 * Constructor method for the class ExportException.java.
	 */
	public ImporTslsException() {
		super();

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param message Error message.
	 */
	public ImporTslsException(String message) {
		super(message);

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param cause Error cause.
	 */
	public ImporTslsException(Throwable cause) {
		super(cause);

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param message Error message.
	 * @param cause Error cause.
	 */
	public ImporTslsException(String message, Throwable cause) {
		super(message, cause);

	}
}
