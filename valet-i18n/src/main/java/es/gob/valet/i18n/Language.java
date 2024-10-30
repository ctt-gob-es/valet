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
 * @version 1.10, 30/10/2024.
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
 * <p>Class that manages the access to the properties files used for generation messages in the plataform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.10, 30/10/2024.
 */
public final class Language {

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
	 * Attribute that represents the location of the file that contains the general messages for the web module.
	 */
	private static final String CONTENT_WEB_GENERAL_PATH = "valet-web.general";

	/**
	 * Attribute that represents the location of the file that contains the general messages for the rest module.
	 */
	private static final String CONTENT_REST_GENERAL_PATH = "valet-rest.general";

	/**
	 * Attribute that represents the location of the file that contains the tasks messages for the rest module.
	 */
	private static final String CONTENT_REST_TASKS_PATH = "valet-rest.tasks";

	/**
	 * Attribute that represents the location of the file that contains the general messages for the commons-utils module.
	 */
	private static final String CONTENT_COMMONS_UTIL_GENERAL_PATH = "valet-commons-util.general";

	/**
	 * Attribute that represents the location of file that contains the general messages quartz module.
	 */
	private static final String CONTENT_QUARTZ_GENERAL_PATH = "valet-quartz.general";

	/**
	 * Attribute that represents the location of file that contains the general messages cache module.
	 */
	private static final String CONTENT_CACHE_GENERAL_PATH = "valet-cache.general";

	/**
	 * Attribute that represents the location of file that contains the general messages core module.
	 */
	private static final String CONTENT_CORE_GENERAL_PATH = "valet-core.general";

	/**
	 * Attribute that represents the location of file that contains the TSL messages core module.
	 */
	private static final String CONTENT_CORE_TSL_PATH = "valet-core.tsl";

	/**
	 * Attribute that represents the location of file that contains the TSL messages core module.
	 */
	private static final String CONTENT_CORE_TASKS_PATH = "valet-core.tasks";

	/**
	 * Attribute that represents the location of file that contains the constants messages persistence module.
	 */
	private static final String CONTENT_PERSISTENCE_CONSTANTS_PATH = "valet-persistence.constants";

	/**
	 * Attribute that represents the location of file that contains the cache messages persistence module.
	 */
	private static final String CONTENT_PERSISTENCE_CACHE_PATH = "valet-persistence.cache";

	/**
	 * Attribute that represents the location of file that contains the general messages persistence module.
	 */
	private static final String CONTENT_PERSISTENCE_GENERAL_PATH = "valet-persistence.general";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(Language.class);

	/**
	 * Attribute that represents the resource boundle with the general messages for the web module.
	 */
	private static ResourceBundle webGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the rest module.
	 */
	private static ResourceBundle restGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the tasks messages for the rest module.
	 */
	private static ResourceBundle restTasks = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the commons-util module.
	 */
	private static ResourceBundle commonsUtilGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the quartz module.
	 */
	private static ResourceBundle quartzGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the cache module.
	 */
	private static ResourceBundle cacheGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the core module.
	 */
	private static ResourceBundle coreGeneral = null;

	/**
	 * Attribute that represents the resource boundle with the TSL messages for the core module.
	 */
	private static ResourceBundle coreTsl = null;

	/**
	 * Attribute that represents the resource boundle with the tasks messages for the core module.
	 */
	private static ResourceBundle coreTasks = null;

	/**
	 * Attribute that represents the properties for the locale of the constants from the tables of the configuration schema.
	 */
	private static ResourceBundle persistenceConstants = null;

	/**
	 * Attribute that represents the resource boundle with the cache messages for the persistence module.
	 */
	private static ResourceBundle persistenceCache = null;

	/**
	 * Attribute that represents the resource boundle with the general messages for the persistence module.
	 */
	private static ResourceBundle persistenceGeneral = null;
	
	/**
	 * Attribute that represents the resource boundle with the general messages for the standalone statistics module.
	 */
	private static ResourceBundle standaloneStatisticsGeneral = null;

	/**
	 * Attribute that represents the locale specified in the configuration.
	 */
	private static Locale currentLocale;

	/**
	 * Attribute that represents the url class loader for the messages files.
	 */
	private static URLClassLoader urlClassLoaderMsg;

	/**
	 * Constructor method for the class Language.java.
	 */
	private Language() {
		super();
	}

	static {
		// Preparamos el URLClassLoader que hará referencia
		// al directorio de los mensajes de logs dentro de la configuración.
		try {
			final File configDirFile = new File(UtilsTomcat.createAbsolutePath(UtilsTomcat.getValetConfigDir(), MSG_DIRECTORY));
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

		webGeneral = ResourceBundle.getBundle(CONTENT_WEB_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		restGeneral = ResourceBundle.getBundle(CONTENT_REST_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		restTasks = ResourceBundle.getBundle(CONTENT_REST_TASKS_PATH, currentLocale, urlClassLoaderMsg);

		commonsUtilGeneral = ResourceBundle.getBundle(CONTENT_COMMONS_UTIL_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		quartzGeneral = ResourceBundle.getBundle(CONTENT_QUARTZ_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		cacheGeneral = ResourceBundle.getBundle(CONTENT_CACHE_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		coreGeneral = ResourceBundle.getBundle(CONTENT_CORE_GENERAL_PATH, currentLocale, urlClassLoaderMsg);

		coreTsl = ResourceBundle.getBundle(CONTENT_CORE_TSL_PATH, currentLocale, urlClassLoaderMsg);

		coreTasks = ResourceBundle.getBundle(CONTENT_CORE_TASKS_PATH, currentLocale, urlClassLoaderMsg);

		persistenceConstants = ResourceBundle.getBundle(CONTENT_PERSISTENCE_CONSTANTS_PATH, currentLocale, urlClassLoaderMsg);

		persistenceCache = ResourceBundle.getBundle(CONTENT_PERSISTENCE_CACHE_PATH, currentLocale, urlClassLoaderMsg);

		persistenceGeneral = ResourceBundle.getBundle(CONTENT_PERSISTENCE_GENERAL_PATH, currentLocale, urlClassLoaderMsg);
	

	}

	/**
	 * Gets the general message (web module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResWebGeneral(final String key, final Object... values) {
		return new MessageFormat(webGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the message general (web module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResWebGeneral(final String key) {
		return webGeneral.getString(key);
	}

	/**
	 * Gets the general message (rest module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResRestGeneral(final String key, final Object... values) {
		return new MessageFormat(restGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (rest module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResRestGeneral(final String key) {
		return restGeneral.getString(key);
	}

	/**
	 * Gets the tasks message (rest module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResRestTasks(final String key, final Object... values) {
		return new MessageFormat(restTasks.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the tasks message (rest module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResRestTasks(final String key) {
		return restTasks.getString(key);
	}

	/**
	 * Gets the general message (commons-util module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResCommonsUtilGeneral(final String key, final Object... values) {
		return new MessageFormat(commonsUtilGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (commons-util module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResCommonsUtilGeneral(final String key) {
		return commonsUtilGeneral.getString(key);
	}

	/**
	 * Gets the general message (quartz module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResQuartzGeneral(final String key, final Object... values) {
		return new MessageFormat(quartzGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (quartz module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResQuartzGeneral(final String key) {
		return quartzGeneral.getString(key);
	}

	/**
	 * Gets the general message (cache module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResCacheGeneral(final String key, final Object... values) {
		return new MessageFormat(cacheGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (cache module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResCacheGeneral(final String key) {
		return cacheGeneral.getString(key);
	}

	/**
	 * Gets the general message (core module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResCoreGeneral(final String key, final Object... values) {
		return new MessageFormat(coreGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (core module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResCoreGeneral(final String key) {
		return coreGeneral.getString(key);
	}

	/**
	 * Gets the TSL message (core module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResCoreTsl(final String key, final Object... values) {
		return new MessageFormat(coreTsl.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the TSL message (core module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResCoreTsl(final String key) {
		return coreTsl.getString(key);
	}

	/**
	 * Gets the Task message (core module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResCoreTasks(final String key, final Object... values) {
		return new MessageFormat(coreTasks.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the Task message (core module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResCoreTask(final String key) {
		return coreTasks.getString(key);
	}

	/**
	 * Method that gets the bundle message of the constants from the tables of the configuration schema for certain key.
	 * @param key Parameter that represents the key for obtain the message.
	 * @return The bundle message of the constants from the tables of the configuration schema for certain key.
	 */
	public static String getResPersistenceConstants(String key) {
		return persistenceConstants.getString(key);
	}

	/**
	 * Gets the cache message (persistence module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResPersistenceCache(final String key, final Object... values) {
		return new MessageFormat(persistenceCache.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the cache message (persistence module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResPersistenceCache(final String key) {
		return persistenceCache.getString(key);
	}

	/**
	 * Gets the general message (persistence module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResPersistenceGeneral(final String key, final Object... values) {
		return new MessageFormat(persistenceGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (persistence module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResPersistenceGeneral(final String key) {
		return persistenceGeneral.getString(key);
	}

	/**
	 * Gets the general message (standalone-statistics module) with the key and values indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @param values Values for insert in the message.
	 * @return String with the message well-formed.
	 */
	public static String getFormatResStandaloneStatisticsGeneral(final String key, final Object... values) {
		return new MessageFormat(standaloneStatisticsGeneral.getString(key), currentLocale).format(values);
	}

	/**
	 * Gets the general message (standalone-statistics module) with the key indicated as input parameters.
	 * @param key Key for obtain the message.
	 * @return String with the message.
	 */
	public static String getResStandaloneStatisticsGeneral(final String key) {
		return standaloneStatisticsGeneral.getString(key);
	}

}
