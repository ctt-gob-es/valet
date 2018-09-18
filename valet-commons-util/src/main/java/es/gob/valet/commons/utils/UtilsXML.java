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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsXML.java.</p>
 * <b>Description:</b><p>Class that provides functionality for managing and transforming XML.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.commons.utils;

import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;

/** 
 * <p>Class that provides functionality for managing and transforming XML.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public final class UtilsXML {

	/**
	 * Attribute that represents the pattern of the XML 1.0 invalid characters.
	 * ref : http://www.w3.org/TR/REC-xml/#charsets
	 */
	private static Pattern xml10InvalidChars = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\x{10000}-\\x{10FFFF}]");

	/**
	 * Method that sanitizes an input XML string.
	 * @param xml XML string to sanitize.
	 * @return New XML string with the invalid characters deleted.
	 */
	public static String sanitizeXmlChars(String xml) {

		if (UtilsStringChar.isNullOrEmptyTrim(xml)) {
			return xml;
		} else {
			return xml10InvalidChars.matcher(xml).replaceAll(UtilsStringChar.EMPTY_STRING);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.commons.text.StringEscapeUtils#escapeXml10(java.lang.String)
	 */
	public static String escapeXml10(String textToEscape) {

		return StringEscapeUtils.escapeXml10(textToEscape);

	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.commons.text.StringEscapeUtils#escapeXml11(java.lang.String)
	 */
	public static String escapeXml11(String textToEscape) {

		return StringEscapeUtils.escapeXml11(textToEscape);

	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.commons.text.StringEscapeUtils#unescapeXml(java.lang.String)
	 */
	public static String unescapeXml(String textToUnescape) {
		
		return StringEscapeUtils.unescapeXml(textToUnescape);
		
	}
	
}
