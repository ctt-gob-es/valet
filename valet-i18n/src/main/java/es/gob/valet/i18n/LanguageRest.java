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
 * <b>File:</b><p>org.valet.i18n.LanguageRest.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>7 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 7 ago. 2018.
 */
package es.gob.valet.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/** 
 * <p>Class responsible for managing the access to language resources in rest server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 7 ago. 2018.
 */
public final class LanguageRest {

	/**
	 * Attribute that represents the list of messages.
	 */
	private static ResourceBundle restvalet;

	/**
	 * Attribute that represents the locale specified in the configuration.
	 */
	private static Locale currentLocale;

	/**
	 * Attribute that represents the name of property file for language configuration. 
	 */
	private static final String FILE_PROP_NAME = "Language";

	/**
	 * Attribute that represents the property that indicates the language used by the system. 
	 */
	private static final String LANGUAGE_ATT = "LANGUAGE";

	/**
	 * Attribute that represents the location of file that contains the messages of system. 
	 */
	private static final String CONTENT_REST_PATH = "messages.restvalet";

	/**
	 * Constructor method for the class LanguageRest.java. 
	 */
	private LanguageRest() {
	}

	static {
		currentLocale = new Locale(ResourceBundle.getBundle(FILE_PROP_NAME).getString(LANGUAGE_ATT));

		restvalet = ResourceBundle.getBundle(CONTENT_REST_PATH, currentLocale);
	}

	/**
	 * Gets the message with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResRestValet(final String key, final Object... values) {
		return new MessageFormat(restvalet.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the message with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResRestValet(final String key) {
		return restvalet.getString(key);
	}

}
