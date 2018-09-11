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
 * <b>File:</b><p>es.gob.valet.i18n.Language.java.</p>
 * <b>Description:</b><p> Class that manages the access to the properties files used for generation messages in the plataform.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15/06/2018.
 */
package es.gob.valet.i18n;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.utils.UtilsTomcat;

/**
 * 
 * <p>Class that manages the access to the properties files used for generation messages in the plataform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/06/2018.
 */
public final class Language {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(Language.class);

	/**
	 * Attribute that represents the list of messages.
	 */
	private static ResourceBundle webvalet;

	/**
	 * Attribute that represents the list of messages.
	 */
	private static ResourceBundle restvalet;

	/**
	 * Attribute that represents the locale specified in the configuration.
	 */
	private static Locale currentLocale;

	/**
	 * Attribute that represents the url class loader for the messages files.
	 */
	private static URLClassLoader urlClassLoaderMsg;

	/**
	 * Constant attribute that represents the name of messages directory inside configuration directory.
	 */
	private static final String MSG_DIRECTORY = "messages";

	/**
	 * Constant attribute that represents the string to identify the the bundle name for the file with the application language.
	 */
	private static final String BUNDLENAME_LNG = "Language";

	/**
	 * Constant attribute that represents the key for the configured locale for the platform.
	 */
	private static final String LANGUAGE_KEY = "LANGUAGE";

	/**
	 * Attribute that represents the location of file that contains the messages of system. 
	 */
	private static final String CONTENT_WEB_PATH = "valet-web.webvalet";

	/**
	 * Attribute that represents the location of file that contains the messages of system. 
	 */
	private static final String CONTENT_REST_PATH = "valet-rest.restvalet";

	/**
	 * Constructor method for the class Language.java.
	 */
	private Language() {
	}

	static {
		// Preparamos el URLClassLoader que hará referencia
		// al directorio de los mensajes de logs dentro de la configuración.
		try {
			final File configDirFile = new File(UtilsTomcat.createAbsolutePath(UtilsTomcat.getTomcatConfigDir(), MSG_DIRECTORY));
			urlClassLoaderMsg = AccessController.doPrivileged(new PrivilegedAction<URLClassLoader>() {

				/**
				 * Run method.
				 * {@inheritDoc}
				 * @see java.security.PrivilegedAction#run()
				 */
				public URLClassLoader run() {
					try {
						return new URLClassLoader(new URL[ ] { configDirFile.toURI().toURL() });
					} catch (MalformedURLException e) {
						throw new RuntimeException(e);
					}
				}
			});
			reloadMessagesConfiguration();
		} catch (RuntimeException e) {
			LOGGER.error(e);
		}

	}

	/**
	 * Method that loads the configured locale and reload the text messages.
	 */
	public static void reloadMessagesConfiguration() {

		boolean takeDefaultLocale = false;
		String propLocale = null;

		// Cargamos el recurso que determina el locale.
		ResourceBundle resLocale = ResourceBundle.getBundle(BUNDLENAME_LNG, Locale.getDefault(), urlClassLoaderMsg);
		if (resLocale == null) {
			takeDefaultLocale = true;
		} else {
			propLocale = resLocale.getString(LANGUAGE_KEY);
		}

		// Tratamos de inicializar el Locale.
		if (propLocale == null) {

			takeDefaultLocale = true;

		} else {

			propLocale = propLocale.trim();
			String[ ] localeSplit = propLocale.split("_");
			if (localeSplit == null || localeSplit.length != 2) {

				takeDefaultLocale = true;

			} else {

				currentLocale = new Locale(localeSplit[0], localeSplit[1]);

			}

		}

		// Si hay que tomar el locale por defecto...
		if (takeDefaultLocale) {

			LOGGER.error("No property was obtained correctly determining the Locale for log messages. Will take the default locale.");
			currentLocale = Locale.getDefault();

		}

		// Se informa en el log del Locale selecccionado.
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Take the next locale for messages logs: " + currentLocale.toString());
		}

		webvalet = ResourceBundle.getBundle(CONTENT_WEB_PATH, currentLocale, urlClassLoaderMsg);

		restvalet = ResourceBundle.getBundle(CONTENT_REST_PATH, currentLocale, urlClassLoaderMsg);

	}

	/**
	 * Gets the message with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResWebValet(final String key, final Object... values) {
		return new MessageFormat(webvalet.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the message with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResWebValet(final String key) {
		return webvalet.getString(key);
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
