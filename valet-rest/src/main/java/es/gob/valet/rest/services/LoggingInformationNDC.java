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
 * <b>File:</b><p>es.gob.valet.rest.services.LoggingInformationNDC.java.</p>
 * <b>Description:</b><p>Class that manages the ThreadContext information added in the logs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.1, 03/04/2023.
 */
package es.gob.valet.rest.services;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;

import es.gob.valet.commons.utils.UtilsIdentifiersGenerator;
import es.gob.valet.commons.utils.UtilsStringChar;

/**
 * <p>Class that manages the ThreadContext information added in the logs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 03/04/2023.
 */
public final class LoggingInformationNDC {

	/**
	 * Constant attribute that represents the token to open the transaction identificator information.
	 */
	private static final String OPEN_ID_TOKEN = "[ID=";

	/**
	 * Constant attribute that represents the token to open the client host/ip/name information.
	 */
	private static final String OPEN_CLIENT_TOKEN = "[Client=";

	/**
	 * Constant attribute that represents the token to open the service name information.
	 */
	private static final String OPEN_SERVICE_TOKEN = "[Service=";

	/**
	 * Constant attribute that represents the HTTP Header key 'X-Forwarded-For'.
	 */
	private static final String HTTP_HEADER_KEY_X_FORWARDED_FOR = "X-Forwarded-For";

	/**
	 * Constant attribute that represents the token '(fwd)=>'.
	 */
	private static final String FWD_TOKEN = "(fwd)=>";

	/**
	 * Constructor method for the class LoggingInformationNDC.java.
	 */
	private LoggingInformationNDC() {
		super();
	}

	/**
	 * Generates and return a new unique number to use for the transaction. Also resgisters this number,
	 * the client host and the service name in the ThreadContext logging.
	 * @param httpServletRequest HTTP Servlet Request information.
	 * @param serviceName Name of the service.
	 * @return a new unique number to use for the transaction.
	 */
	public static String registerNdcInfAndGetTransactionNumber(HttpServletRequest httpServletRequest, String serviceName) {

		String result = UtilsIdentifiersGenerator.generateNumbersUniqueId();

		ThreadContext.push(OPEN_CLIENT_TOKEN + getClientHostName(httpServletRequest) + UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);
		ThreadContext.push(OPEN_ID_TOKEN + result + UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);
		ThreadContext.push(OPEN_SERVICE_TOKEN + serviceName + UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);

		return result;

	}

	/**
	 * This method tries to get/build the host/ip/name of the client that is running the input request.
	 * @param httpServletRequest HTTP Servlet request to analyze.
	 * @return the host/ip/name of the client that is running the input request.
	 */
	private static String getClientHostName(HttpServletRequest httpServletRequest) {
		String xForwardedFor = httpServletRequest.getHeader(HTTP_HEADER_KEY_X_FORWARDED_FOR);
		xForwardedFor = xForwardedFor != null && xForwardedFor.contains(UtilsStringChar.SYMBOL_COMMA_STRING) ? xForwardedFor.split(UtilsStringChar.SYMBOL_COMMA_STRING)[0] : xForwardedFor;
		String remoteHost = httpServletRequest.getRemoteHost();
		String remoteAddr = httpServletRequest.getRemoteAddr();
		int remotePort = httpServletRequest.getRemotePort();
		StringBuilder sb = new StringBuilder();
		if (!UtilsStringChar.isNullOrEmpty(remoteHost) && !remoteHost.equals(remoteAddr)) {
			sb.append(remoteHost).append(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
		}
		if (!UtilsStringChar.isNullOrEmpty(xForwardedFor)) {
			sb.append(xForwardedFor).append(FWD_TOKEN);
		}
		if (!UtilsStringChar.isNullOrEmpty(remoteAddr)) {
			sb.append(remoteAddr).append(UtilsStringChar.SYMBOL_COLON_STRING).append(remotePort);
		}
		return sb.toString();
	}

	/**
	 * This method removes all the registered ThreadContext information to this thread could be cleaned.
	 */
	public static void unregisterNdcInf() {
		ThreadContext.removeStack();
	}

}
