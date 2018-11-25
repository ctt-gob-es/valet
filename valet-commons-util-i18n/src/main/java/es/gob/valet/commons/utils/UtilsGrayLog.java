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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsGrayLog.java.</p>
 * <b>Description:</b><p>Utilities class for the use of GrayLog.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.commons.utils;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import biz.paluch.logging.gelf.intern.GelfMessage;
import biz.paluch.logging.gelf.intern.GelfSender;
import biz.paluch.logging.gelf.intern.sender.GelfUDPSender;
import es.gob.valet.i18n.Language;

/**
 * <p>Utilities class for the use of GrayLog.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public final class UtilsGrayLog {

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_000</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_000 = "UTILS_GRAYLOG_000";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_001</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_001 = "UTILS_GRAYLOG_001";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_002</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_002 = "UTILS_GRAYLOG_002";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_003</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_003 = "UTILS_GRAYLOG_003";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_004</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_004 = "UTILS_GRAYLOG_004";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_005</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_005 = "UTILS_GRAYLOG_005";

	/**
	 * Constant attribute that represents the property key <code>UTILS_GRAYLOG_006</code> belonging to the file commonUtils/general_xx_YY.properties.
	 */
	private static final String UTILS_GRAYLOG_006 = "UTILS_GRAYLOG_006";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilsGrayLog.class);

	/**
	 * Constant attribute that represents the token key 'EVENT_CODE' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_EVENT_CODE = "EVENT_CODE";

	/**
	 * Constant attribute that represents the token key 'RESOURCE_INFO' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_RESOURCE_INFO = "RESOURCE_INFO";

	/**
	 * Constant attribute that represents the token key 'MESSAGE' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_MESSAGE = "MESSAGE";

	/**
	 * Constant attribute that represents the event code 'ERROR_CON'.
	 */
	public static final String TOKEN_VALUE_EVENT_CODE_ERROR_CON = "ERROR_CON";

	/**
	 * Constant attribute that represents the event code 'INITIALIZATION'.
	 */
	public static final String TOKEN_VALUE_EVENT_CODE_INITIALIZATION = "INITIALIZATION";

	/**
	 * Constant attribute that represents the level FATAL for the GrayLogger.
	 */
	public static final int LEVEL_FATAL = 0;

	/**
	 * Constant attribute that represents the level ERROR for the GrayLogger.
	 */
	public static final int LEVEL_ERROR = 1;

	/**
	 * Constant attribute that represents the level WARN for the GrayLogger.
	 */
	public static final int LEVEL_WARN = 2;

	/**
	 * Constant attribute that represents the level INFO for the GrayLogger.
	 */
	public static final int LEVEL_INFO = 3;

	/**
	 * Constant attribute that represents the level DEBUG for the GrayLogger.
	 */
	public static final int LEVEL_DEBUG = 4;

	/**
	 * Flag that indicates if the configuration of Gray Log has been initialized (with or without errors).
	 */
	private static boolean initialized = false;

	/**
	 * Flag that indicates if there is some error in the initialization of Gray Log properties.
	 */
	private static boolean initializationError = false;

	/**
	 * Flag that indicates if Gray Log is enabled.
	 */
	private static boolean grayLogEnabled = false;

	/**
	 * Attribute that represents the destination host of the Gray Log Server.
	 */
	private static String grayLogHost = null;

	/**
	 * Attribute that represents the destination port of the Gray Log Server.
	 */
	private static int grayLogPort = -1;

	/**
	 * Attribute that represents the set of static declared fields to use in the
	 * messages to Gray Log.
	 */
	private static Map<String, String> grayLogDeclaredFields = null;

	/**
	 * Attribute that represents the Gray Log Messages Sender.
	 */
	private static GelfSender grayLogMessageSender = null;

	/**
	 * Constructor method for the class UtilsGrayLog.java.
	 */
	private UtilsGrayLog() {
		super();
	}

	/**
	 * Loads all the Gray Log configuration.
	 */
	public static synchronized void loadGrayLogConfiguration() {

		initializationError = false;
		loadIfGrayLogIsEnabled();
		if (grayLogEnabled) {

			loadGrayLogServerHost();
			loadGrayLogServerPort();
			loadGrayLogMessagesSender();
			loadGrayLogDeclaredFields();

			if (initializationError) {
				grayLogEnabled = false;
				LOGGER.error(Language.getResCommonsUtilGeneral(UTILS_GRAYLOG_004));
			} else {
				LOGGER.info(Language.getResCommonsUtilGeneral(UTILS_GRAYLOG_005));
			}

		} else {

			LOGGER.info(Language.getResCommonsUtilGeneral(UTILS_GRAYLOG_006));

		}

		initialized = true;

	}

	/**
	 * Load if the GrayLog is enabled in the 'staticValetConfig.properties' configuration file.
	 */
	private static void loadIfGrayLogIsEnabled() {
		String isGrayLogEnabled = StaticValetConfig.getProperty(StaticValetConfig.GRAYLOG_ENABLED);
		grayLogEnabled = Boolean.parseBoolean(isGrayLogEnabled);
	}

	/**
	 * Load the host of the Gray Log destination server.
	 */
	private static void loadGrayLogServerHost() {
		String result = StaticValetConfig.getProperty(StaticValetConfig.GRAYLOG_DESTINATION_HOST);
		grayLogHost = UtilsStringChar.isNullOrEmptyTrim(result) ? null : result;
	}

	/**
	 * Load the port of the Gray Log destination port.
	 */
	private static void loadGrayLogServerPort() {
		int result = -1;
		String portString = StaticValetConfig.getProperty(StaticValetConfig.GRAYLOG_DESTINATION_PORT);
		if (!UtilsStringChar.isNullOrEmptyTrim(portString)) {
			result = Integer.parseInt(portString);
		}
		grayLogPort = result;
	}

	/**
	 * Load the Gray Log message sender manager.
	 */
	private static void loadGrayLogMessagesSender() {

		if (grayLogMessageSender != null) {
			grayLogMessageSender.close();
			grayLogMessageSender = null;
		}

		if (grayLogHost != null && grayLogPort > 0) {

			try {
				grayLogMessageSender = new GelfUDPSender(grayLogHost, grayLogPort, Log4jErrorReporter.getInstance());
			} catch (IOException e) {
				grayLogMessageSender = null;
				initializationError = true;
				LOGGER.error(Language.getResCommonsUtilGeneral(UTILS_GRAYLOG_001), e);
			}

		}

	}

	/**
	 * Load the declared fields to add in the messages to Gray Log.
	 */
	private static void loadGrayLogDeclaredFields() {

		grayLogDeclaredFields = new ConcurrentHashMap<String, String>();
		Properties props = StaticValetConfig.getProperties(StaticValetConfig.GRAYLOG_FIELDS_PREFIX);
		if (props != null && !props.isEmpty()) {

			Set<Object> keySet = props.keySet();
			for (Object key: keySet) {
				String keyString = (String) key;
				String value = props.getProperty(keyString);
				grayLogDeclaredFields.put(keyString.substring(StaticValetConfig.GRAYLOG_FIELDS_PREFIX.length()), value);
			}

		}

	}

	/**
	 * This method only works if the Gray Log has been initialized and is enabled in the configuration.
	 * Writes a event-message and send to the GrayLog Server with the following structure/fields:
	 *     EVENT_CODE=[{eventCode}];RESOURCE_INFO=[{resourceInfo}];MESSAGE=[{message}]
	 * @param level Level to assign to the event message. It could be some of:
	 * 		<ul>
	 * 			<li>{@link UtilsGrayLog#LEVEL_FATAL}</li>
	 * 			<li>{@link UtilsGrayLog#LEVEL_ERROR}</li>
	 * 			<li>{@link UtilsGrayLog#LEVEL_WARN}</li>
	 * 			<li>{@link UtilsGrayLog#LEVEL_INFO}</li>
	 * 			<li>{@link UtilsGrayLog#LEVEL_DEBUG}</li>
	 * 		</ul>
	 * If it is specified another value, then this method do nothing.
	 * @param eventCode Event code for the event message. If this is <code>null</code> or empty, or not is defined,
	 * then this method do nothing.
	 * @param resourceInfo Additional info for the event-message. It could be <code>null</code> or empty.
	 * @param message Message to add in the event-message. If this is <code>null</code> or empty, or not is defined,
	 * then this method do nothing.
	 */
	public static void writeMessageInGrayLog(int level, String eventCode, String resourceInfo, String message) {

		// Si no está inicializado, forzamos su carga.
		if (!initialized) {
			loadGrayLogConfiguration();
		}

		// Si el GrayLog está habilitado en el sistema...
		if (grayLogEnabled) {

			// Si ni el código de evento ni el mensaje son cadenas nulas o
			// vacías...
			if (!UtilsStringChar.isNullOrEmptyTrim(eventCode) && !UtilsStringChar.isNullOrEmptyTrim(message)) {

				GelfMessage gm = new GelfMessage();
				gm.setShortMessage(message);
				String fullMessage = getParsedEventMessageForGrayLog(eventCode, resourceInfo, message);
				gm.setFullMessage(fullMessage);
				gm.setJavaTimestamp(Calendar.getInstance().getTimeInMillis());
				gm.setLevel(String.valueOf(level));
				gm.addField(TOKEN_KEY_EVENT_CODE, eventCode);
				gm.addField(TOKEN_KEY_RESOURCE_INFO, resourceInfo);
				gm.addField(TOKEN_KEY_MESSAGE, message);
				gm.addFields(grayLogDeclaredFields);
				grayLogMessageSender.sendMessage(gm);

			} else {

				LOGGER.warn(Language.getFormatResCommonsUtilGeneral(UTILS_GRAYLOG_003, new Object[ ] { eventCode, message }));

			}

		} else {

			LOGGER.debug(Language.getResCommonsUtilGeneral(UTILS_GRAYLOG_002));

		}

	}

	/**
	 * Builds the event-message from the input parameters.
	 * @param eventCode Event code for the event message.
	 * @param resourceInfo Additional info for the event-message. It could be <code>null</code> or empty.
	 * @param message Message to add in the event-message.
	 * @return Event message builded from the input parameters.
	 */
	private static String getParsedEventMessageForGrayLog(String eventCode, String resourceInfo, String message) {

		if (UtilsStringChar.isNullOrEmptyTrim(resourceInfo)) {
			return Language.getFormatResCommonsUtilGeneral(UTILS_GRAYLOG_000, new Object[ ] { eventCode, UtilsStringChar.EMPTY_STRING, message });
		} else {
			return Language.getFormatResCommonsUtilGeneral(UTILS_GRAYLOG_000, new Object[ ] { eventCode, resourceInfo, message });
		}

	}

}
