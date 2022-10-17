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
 * <b>File:</b><p>es.gob.valet.persistence.exceptions.ImportException.java.</p>
 * <b>Description:</b><p>Class that represents an exception related to import process.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/10/2022.
 */
package es.gob.valet.persistence.exceptions;

/**
 * <p>Class that represents an exception related to import process.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * @version 1.0, 17/10/2022.
 */
public class ImportException extends Exception {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 1926781440556734283L;

	/**
	 * Constructor method for the class ExportException.java.
	 */
	public ImportException() {
		super();

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param message Error message.
	 */
	public ImportException(String message) {
		super(message);

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param cause Error cause.
	 */
	public ImportException(Throwable cause) {
		super(cause);

	}

	/**
	 * Constructor method for the class ExportException.java.
	 * @param message Error message.
	 * @param cause Error cause.
	 */
	public ImportException(String message, Throwable cause) {
		super(message, cause);

	}

}
