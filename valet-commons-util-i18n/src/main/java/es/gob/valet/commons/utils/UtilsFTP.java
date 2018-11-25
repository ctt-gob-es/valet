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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsFTP.java.</p>
 * <b>Description:</b><p>Utilities class relating to connections and FTP/S protocol.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.commons.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Utilities class relating to connections and FTP/S protocol.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public final class UtilsFTP {

	/**
	 * Constant attribute that represents the representation string of the scheme http.
	 */
	public static final String FTP_SCHEME = "ftp";

	/**
	 * Constructor method for the class UtilsFTP.java.
	 */
	private UtilsFTP() {
		super();
	}

	/**
	 * This method determines whether a given URI scheme is FTP.
	 * @param uriString String representation of the URI to analyze.
	 * @return <i>true</i> if the scheme of the URI is FTP, otherwise <i>false</i>.
	 */
	public static boolean isUriOfSchemeFTP(String uriString) {

		boolean result = false;

		if (!UtilsStringChar.isNullOrEmptyTrim(uriString)) {

			try {

				URI uri = new URI(uriString);
				result = isUriOfSchemeFTP(uri);

			} catch (URISyntaxException e) {
				result = false;
			}

		}

		return result;

	}

	/**
	 * This method determines whether a given URI scheme is FTP.
	 * @param uri Representation of the URI to analyze.
	 * @return <i>true</i> if the scheme of the URI is FTP, otherwise <i>false</i>.
	 */
	public static boolean isUriOfSchemeFTP(URI uri) {

		boolean result = false;

		if (uri != null) {

			String scheme = uri.getScheme();
			if (!UtilsStringChar.isNullOrEmptyTrim(scheme) && scheme.equalsIgnoreCase(FTP_SCHEME)) {
				result = true;
			}

		}

		return result;

	}

}
