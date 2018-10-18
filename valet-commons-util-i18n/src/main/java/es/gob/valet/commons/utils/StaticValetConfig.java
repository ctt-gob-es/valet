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
 * <b>File:</b><p>es.gob.valet.commons.utils.StaticValetConfig.java.</p>
 * <b>Description:</b><p> Class contains static properties of valET. This properties are immutable
 * and they can be modified only restarted the server context.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>20/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/09/2018.
 */
package es.gob.valet.commons.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IQuartzMessages;
import es.gob.valet.i18n.utils.UtilsTomcat;

/**
 * <p>Class contains static properties of valET. This properties are immutable
 * and they can be modified only restarted the server context.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 02/10/2018.
 */
public final class StaticValetConfig {

	/**
	 * Attribute that represents set of properties of valET.
	 */
	private static Properties staticProperties;

	/**
	 * Constant attribute that represents the log manager of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(StaticValetConfig.class);

	/**
	 * Constant attribute that represents name of properties file.
	 */
	public static final String STATIC_VALET_FILENAME = "staticValetConfig.properties";

	/**
	 * Constant attribute that represents the key for the property that indicates the implementation
	 * to use for the cache.
	 */
	public static final String CACHE_IMPLEMENTATION = "cache.implementation";

	/**
	 * Constant attribute that represents name for property <code>character.special</code>.
	 */
	public static final String LIST_CHARACTER_SPECIAL = "character.special";
	
	/**
	 * Attribute that represents the Padding algorithm for the AES cipher.
	 */
	public static final String AES_PADDING_ALG = "aes.padding.alg";

	/**
	 * Attribute that represents the AES algorithm name.
	 */
	public static final String AES_ALGORITHM = "aes.algorithm";

	/**
	 * Attribute that represents the password for the system keystores.
	 */
	public static final String AES_PASSWORD = "aes.password";
	/**
	 * Constructor method for the class StaticValetConfig.java.
	 */
	private StaticValetConfig() {
		super();
	}

	/**
	 * Gets all properties from original file.
	 * @return all properties
	 */
	public static Properties getProperties() {
		if (staticProperties == null) {
			reloadStaticValetConfigProperties();
		}
		return staticProperties;
	}

	/**
	 * Method that load/reload the static valET properties.
	 * @return <code>true</code> if the properties file has been loaded,
	 * otherwise <code>false</code>.
	 */
	public static boolean reloadStaticValetConfigProperties() {

		boolean result = false;

		synchronized (StaticValetConfig.class) {
			if (staticProperties == null) {
				staticProperties = new Properties();
				FileInputStream configStream = null;
				try {
					LOGGER.info(Language.getFormatResQuartzValet(IQuartzMessages.LOG2, new Object[ ] { STATIC_VALET_FILENAME }));
					configStream = new FileInputStream(UtilsTomcat.createAbsolutePath(UtilsTomcat.getTomcatConfigDir(), STATIC_VALET_FILENAME));
					staticProperties.load(configStream);
					LOGGER.info(Language.getFormatResQuartzValet(IQuartzMessages.LOG2, new Object[ ] { staticProperties }));
					result = true;
				} catch (IOException e) {
					LOGGER.error(Language.getFormatResQuartzValet(IQuartzMessages.LOG3, new Object[ ] { STATIC_VALET_FILENAME, e.getMessage() }), e);
				} finally {
					UtilsResources.safeCloseInputStream(configStream);
				}
			}
		}

		return result;

	}

	/**
	 * Returns the value of property given.
	 * @param propertyName name of valET property.
	 * @return the value of property given.
	 */
	public static String getProperty(final String propertyName) {
		return (String) getProperties().get(propertyName);
	}

	/**
	 * Obtains a collection of static properties which key name start with the prefix given.
	 * @param prefix word placed in the beginning of the key name of property.
	 * @return a collection of static properties.
	 */
	public static Properties getProperties(final String prefix) {
		Properties result = new Properties();
		if (prefix != null) {
			for (Object key: getProperties().keySet()) {
				if (key != null && key.toString().startsWith(prefix)) {
					result.put(key, getProperties().get(key));
				}
			}
		}
		return result;
	}

}

