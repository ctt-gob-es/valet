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
 * <b>File:</b><p>es.gob.valet.exceptions.CommonUtilsException.java.</p>
 * <b>Description:</b><p> Class that creates an exception that occurs in CommonsUtils module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/11/2018.
 */
package es.gob.valet.exceptions;

/**
 * <p>Class that creates an exception that occurs in CommonsUtils module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/11/2018.
 */
public class CommonUtilsException extends ValetException {

	/**
	 * Attribute that represents serialVersionUID.
	 */
	private static final long serialVersionUID = -7931386877131764009L;

	/**
	 * Exception i18n occurred in utilsAsn1(CertificateException).
	 */
	public static final String UTILS_ASN1_CODE_001 = "UTILS_ASN1_CODE_001";

	/**
	 * Constructor method for the class CommonUtilsException.java.
	 */
	public CommonUtilsException() {
		super();
	}

	/**
	 * Constructor method for the class CommonUtilsException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Error description.
	 */
	public CommonUtilsException(String errorCode, String errorDesc) {
		super(errorCode, errorDesc);
	}

	/**
	 * Constructor method for the class CommonUtilsException.java.
	 * @param errorCode Error code.
	 * @param errorDesc Error description.
	 * @param exception Exception that cause the error.
	 */
	public CommonUtilsException(String errorCode, String errorDesc, Exception exception) {
		super(errorCode, errorDesc, exception);
	}

}
