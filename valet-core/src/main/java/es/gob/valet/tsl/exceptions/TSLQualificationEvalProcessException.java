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
 * <b>File:</b><p>es.gob.valet.tsl.exceptions.TSLQualificationEvalProcessException.java.</p>
 * <b>Description:</b><p>Class that represents an exception in the qualification extension evaluation process.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>19/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/10/2018.
 */
package es.gob.valet.tsl.exceptions;

/**
 * <p>Class that represents an exception in the qualification extension evaluation process.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 19/10/2018.
 */
public class TSLQualificationEvalProcessException extends TSLException {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -7288449960448937384L;

	/**
	 * Constructor method for the class TSLQualificationEvalProcessException.java.
	 */
	public TSLQualificationEvalProcessException() {
		super();
	}

	/**
	 * Constructor method for the class TSLQualificationEvalProcessException.java.
	 * @param errorCodeParam Error code.
	 * @param errorDescParam Error description.
	 */
	public TSLQualificationEvalProcessException(String errorCodeParam, String errorDescParam) {
		super(errorCodeParam, errorDescParam);
	}

	/**
	 * Constructor method for the class TSLQualificationEvalProcessException.java.
	 * @param errorCodeParam Error code.
	 * @param errorDescParam Error description.
	 * @param exceptionParam Error cause.
	 */
	public TSLQualificationEvalProcessException(String errorCodeParam, String errorDescParam, Exception exceptionParam) {
		super(errorCodeParam, errorDescParam, exceptionParam);
	}

}
