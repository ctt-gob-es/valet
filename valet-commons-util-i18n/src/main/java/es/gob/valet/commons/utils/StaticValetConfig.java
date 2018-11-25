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
 * @version 1.6, 25/11/2018.
 */
package es.gob.valet.commons.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IQuartzGeneralMessages;
import es.gob.valet.i18n.utils.UtilsTomcat;

/**
 * <p>Class contains static properties of valET. This properties are immutable
 * and they can be modified only restarted the server context.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 25/11/2018.
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
	 * Constant attribute that represents the name of properties file.
	 */
	public static final String STATIC_VALET_FILENAME = "staticValetConfig.properties";

	/**
	 * Constant attribute that represents the key for the property that indicates the implementation
	 * to use for the cache.
	 */
	public static final String CACHE_IMPLEMENTATION = "cache.implementation";

	/**
	 * Constant attribute that represents the key for the property that indicates the idle time
	 * before stop/destroy a configuration cache.
	 */
	public static final String CACHE_IDLETIMEBEFORESTOPCACHE = "cache.idleTimeBeforeStopCache";

	/**
	 * Constant attribute that represents the key for the property that indicates a list
	 * of special characters.
	 */
	public static final String LIST_CHARACTER_SPECIAL = "character.special";

	/**
	 * Attribute that represents the key for the property that indicates the Padding algorithm for the AES cipher.
	 */
	public static final String AES_NO_PADDING_ALG = "aes.nopadding.alg";

	/**
	 * Attribute that represents the key for the property that indicates the AES algorithm name.
	 */
	public static final String AES_ALGORITHM = "aes.algorithm";

	/**
	 * Attribute that represents the key for the property that indicates the password for the AES algorithm.
	 */
	public static final String AES_PASSWORD = "aes.password";

	/**
	 * Attribute that represents the key for the property that indicates the flag to indicate if it is
	 * necessary to check the structure of the TSL signature.
	 */
	public static final String TSL_SIGNATURE_VERIFY_STRUCTURE = "tsl.signature.verify.structure";

	/**
	 * Attribute that represents the key for the property that indicates the flag to indicate if it is
	 * necessary to check the specification requirements for the TSL signature.
	 */
	public static final String TSL_SIGNATURE_VERIFY_SPECIFICATION = "tsl.signature.verify.specification";

	/**
	 * Attribute that represents the key for the property that indicates the initial date from which is
	 * allowed to use TSL to validate certificates.
	 */
	public static final String TSL_VALIDATION_INITIAL_DATE = "tsl.validation.initial.date";

	/**
	 * Attribute that represents the key for the property that indicates connection timeout for ocsp requests (milliseconds).
	 */
	public static final String TSL_VALIDATION_OCSP_TIMEOUT_CONNECTION = "tsl.validation.ocsp.timeout.connection";

	/**
	 * Constant attribute that represents the key for the property that indicates read timeout for ocsp requests (milliseconds).
	 */
	public static final String TSL_VALIDATION_OCSP_TIMEOUT_READ = "tsl.validation.ocsp.timeout.read";

	/**
	 * Constant attribute that represents the key for the property that indicates the interval allowed to accept a OCSP response
	 * by a specified validation date (seconds).
	 */
	public static final String TSL_VALIDATION_OCSP_INTERVAL_ALLOWED = "tsl.validation.ocsp.interval.allowed";

	/**
	 * Constant attribute that represents the key for the property that indicates connection timeout to get a CRL (milliseconds).
	 */
	public static final String TSL_VALIDATION_CRL_TIMEOUT_CONNECTION = "tsl.validation.crl.timeout.connection";

	/**
	 * Constant attribute that represents the key for the property that indicates read timeout to get a CRL (milliseconds).
	 */
	public static final String TSL_VALIDATION_CRL_TIMEOUT_READ = "tsl.validation.crl.timeout.read";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'Natural Person'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_NATURALPERSON = "tsl.mapping.certClassification.NATURAL_PERSON";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'Legal Person'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_LEGALPERSON = "tsl.mapping.certClassification.LEGAL_PERSON";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'Electronic Signature'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_ESIG = "tsl.mapping.certClassification.ESIG";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'Electronic Seal'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_ESEAL = "tsl.mapping.certClassification.ESEAL";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'Web Service Authentication'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_WSA = "tsl.mapping.certClassification.WSA";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate classification to 'TimeStamping Authority'.
	 */
	public static final String TSL_MAPPING_CERTCLASSIFICATION_TSA = "tsl.mapping.certClassification.TSA";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate qualified to 'YES'.
	 */
	public static final String TSL_MAPPING_CERTQUALIFIED_YES = "tsl.mapping.certQualified.YES";

	/**
	 * Constant attribute that represents the key for the property that indicates the set of values recognized
	 * for a certificate qualified to 'NO'.
	 */
	public static final String TSL_MAPPING_CERTQUALIFIED_NO = "tsl.mapping.certQualified.NO";

	/**
	 * Constant attribute that represents name for property <i>"graylog.enabled"</i>.
	 */
	public static final String GRAYLOG_ENABLED = "graylog.enabled";

	/**
	 * Constant attribute that represents name for property <i>"graylog.destination.host"</i>.
	 */
	public static final String GRAYLOG_DESTINATION_HOST = "graylog.destination.host";

	/**
	 * Constant attribute that represents name for property <i>"graylog.destination.port"</i>.
	 */
	public static final String GRAYLOG_DESTINATION_PORT = "graylog.destination.port";

	/**
	 * Constant attribute that represents name for property <i>"graylog.field."</i>.
	 */
	public static final String GRAYLOG_FIELDS_PREFIX = "graylog.field.";

	/**
	 * Constant attribute that represents name for property <i>"ssl.restricted.cipher.suites"</i>.
	 */
	public static final String SSL_RESTRICTED_CIPHER_SUITES = "ssl.restricted.cipher.suites";

	/**
	 * Constant attribute that represents name for property <i>"ssl.restricted.protocols"</i>.
	 */
	public static final String SSL_RESTRICTED_PROTOCOLS = "ssl.restricted.protocols";

	/**
	 * Constant attribute that represents name for property <i>"connection.MaxSize"</i>.
	 */
	public static final String CONECTION_MAXSIZE = "connection.MaxSize";

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
					LOGGER.info(Language.getFormatResQuartzGeneral(IQuartzGeneralMessages.LOG2, new Object[ ] { STATIC_VALET_FILENAME }));
					configStream = new FileInputStream(UtilsTomcat.createAbsolutePath(UtilsTomcat.getTomcatConfigDir(), STATIC_VALET_FILENAME));
					staticProperties.load(configStream);
					LOGGER.info(Language.getFormatResQuartzGeneral(IQuartzGeneralMessages.LOG2, new Object[ ] { staticProperties }));
					result = true;
				} catch (IOException e) {
					LOGGER.error(Language.getFormatResQuartzGeneral(IQuartzGeneralMessages.LOG3, new Object[ ] { STATIC_VALET_FILENAME, e.getMessage() }), e);
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
		String result = (String) getProperties().get(propertyName);
		if (result != null) {
			return result.trim();
		} else {
			return result;
		}
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
