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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsResources.java.</p>
 * <b>Description:</b><p>Class that provides functionality to control resources.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>01/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

/**
 * <p>Class that provides functionality to control resources.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 10/09/2018.
 */
public class UtilsResources {

	/**
	 * Constant attribute that represents the token 'text/plain'.
	 */
	private static final String TOKEN_TEXT_PLAIN = "text/plain";

	/**
	 * Tries to get the MIME type of the input data.
	 * @param data Byte array to analyze.
	 * @return String representation of the MIME type of the input data. If there is some error,
	 * then {@value #TOKEN_TEXT_PLAIN} is returned.
	 */
	public static String getMimeType(byte[ ] data) {
		Tika t = new Tika();
		InputStream is = new ByteArrayInputStream(data);
		try {
			return t.detect(is);
		} catch (IOException e) {
			// TODO
			// LOGGER.error(Language.getFormatResCoreGeneral(LOG02, new Object[
			// ] { }), e);

		} finally {
			safeCloseInputStream(is);
		}
		return TOKEN_TEXT_PLAIN;
	}

	/**
	 * Method that handles the closing of a {@link InputStream} resource.
	 * @param is Parameter that represents a {@link InputStream} resource.
	 */
	public static void safeCloseInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO
				// LOGGER.error(Language.getFormatResCoreGeneral(LOG01, new
				// Object[ ] { is.getClass().getName(), e.getMessage() }));
			}
		}
	}

	/**
	 * Autodetects extension given a mime type.
	 * @param mimeType Mime Type to autodetect its extension.
	 * @return Extension associated to given mime type.
	 */
	public static String getExtension(String mimeType) {
		MimeType m;
		try {
			m = MimeTypes.getDefaultMimeTypes().forName(mimeType);
			return m.getExtension();
		} catch (MimeTypeException e) {
			// TODO
			// LOGGER.error(Language.getFormatResCoreGeneral(LOG03, new Object[
			// ] { }), e);
		}
		return UtilsStringChar.EMPTY_STRING;
	}

	/**
	 * To free resources, this method sets the length of the input {@link StringBuilder} to
	 * zero and then trim it.
	 * @param sb String builder to clean. If it is <code>null</code>, this method do nothing.
	 */
	public static void cleanStringBuilder(StringBuilder sb) {

		if (sb != null) {
			sb.setLength(0);
			sb.trimToSize();
		}

	}

}
