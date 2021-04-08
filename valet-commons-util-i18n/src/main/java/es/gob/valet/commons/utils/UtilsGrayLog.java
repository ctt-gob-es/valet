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
 * @version 1.2, 07/04/2021.
 */
package es.gob.valet.commons.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;

/**
 * <p>Utilities class for the use of GrayLog.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 07/04/2021.
 */
public final class UtilsGrayLog {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilsGrayLog.class);

	/**
	 * Constant attribute that represents the token key 'cod_error' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_COD_ERROR = "cod_error";

	/**
	 * Constant attribute that represents the token key 'RESOURCE_INFO' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_RESOURCE_INFO = "resource_info";

	/**
	 * Constant attribute that represents the token key 'MESSAGE' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_MESSAGE = "message";

	/**
	 * Constant attribute that represents the token key 'source' for a Gray Log Message Field.
	 */
	private static final String TOKEN_KEY_SOURCE = "source";

	/**
	 * Constant attribute that represents the event code 'ERROR_CON'.
	 */
	public static final String TOKEN_VALUE_CODERROR_ERROR_CON = "ERROR_CON";

	/**
	 * Constant attribute that represents the event code 'INITIALIZATION'.
	 */
	public static final String TOKEN_VALUE_CODERROR_INITIALIZATION = "INITIALIZATION";

	/**
	 * Constant attribute that represents the event code 'UNKNOWN'.
	 */
	public static final String TOKEN_VALUE_UNKNWON = "UNKNOWN";

	/**
	 * Constant attribute that represents the level FATAL for the GrayLogger.
	 */
	public static final int LEVEL_FATAL = 2;

	/**
	 * Constant attribute that represents the level ERROR for the GrayLogger.
	 */
	public static final int LEVEL_ERROR = 3;

	/**
	 * Constant attribute that represents the level WARN for the GrayLogger.
	 */
	public static final int LEVEL_WARN = 4;

	/**
	 * Constant attribute that represents the level INFO for the GrayLogger.
	 */
	public static final int LEVEL_INFO = 6;

	/**
	 * Constant attribute that represents the level DEBUG for the GrayLogger.
	 */
	public static final int LEVEL_DEBUG = 7;

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
	 * Attribute that represents the maximum level of traces allowed for GrayLog messages.
	 */
	private static int maxLevelTraceAllowed = LEVEL_ERROR;

	/**
	 * Attribute that represents the set of static declared fields to use in the
	 * messages to Gray Log.
	 */
	private static Map<String, String> grayLogDeclaredFields = null;

	/**
	 * Attribute that represents the source host name from which the messages are sended.
	 */
	private static String grayLogSourceHostName = null;

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
			loadGrayLogMaximumLevelTracesAllowed();
			loadGrayLogMessagesSender();
			loadGrayLogDeclaredFields();
			loadGrayLogSourceHostName();

			if (!initializationError) {
				grayLogEnabled = false;
				LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_004));
			} else {
				LOGGER.info(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_005));
			}

		} else {

			LOGGER.info(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_006));

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
	 * Load the maximum level of traces allowed for the Gray Log messages.
	 */
	private static void loadGrayLogMaximumLevelTracesAllowed() {
		int result = LEVEL_ERROR;
		String maxLevelTraceAllowedString = StaticValetConfig.getProperty(StaticValetConfig.GRAYLOG_MAX_LEVEL_TRACE_ALLOWED);
		if (!UtilsStringChar.isNullOrEmptyTrim(maxLevelTraceAllowedString)) {
			result = Integer.parseInt(maxLevelTraceAllowedString);
		}
		maxLevelTraceAllowed = result;
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
				LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_001), e);
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
	 * Load the hostname to add as source field in the GrayLog messages.
	 */
	private static void loadGrayLogSourceHostName() {

		// Se calcula el hostname para informar el campo "source".
		try {

			InetAddress ip = InetAddress.getLocalHost();
			grayLogSourceHostName = ip.getHostName();

		} catch (UnknownHostException e) {

			LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_007), e);
			grayLogSourceHostName = TOKEN_VALUE_UNKNWON;

		}

	}

	/**
	 * Gets the HostName used to identify this instance in GrayLog.
	 * @return the HostName used to identify this instance in GrayLog.
	 */
	public static String getHostName() {
		return grayLogSourceHostName;
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
	 * @param errorCode Error code for the event message. If this is <code>null</code> or empty, or not is defined,
	 * then this method do nothing.
	 * @param resourceInfo Additional info for the event-message. It could be <code>null</code> or empty.
	 * @param message Message to add in the event-message. If this is <code>null</code> or empty, or not is defined,
	 * then this method do nothing.
	 */
	public static void writeMessageInGrayLog(int level, String errorCode, String resourceInfo, String message) {

		// Si no está inicializado, forzamos su carga.
		if (!initialized) {
			loadGrayLogConfiguration();
		}

		// Si el GrayLog está habilitado en el sistema...
		if (grayLogEnabled) {

			// Si el nivel del mensaje es menor o igual al permitido...
			if (level <= maxLevelTraceAllowed) {

				// Si ni el código de evento ni el mensaje son cadenas nulas o
				// vacías...
				if (!UtilsStringChar.isNullOrEmptyTrim(errorCode) && !UtilsStringChar.isNullOrEmptyTrim(message)) {

					GelfMessage gm = new GelfMessage();
					gm.setShortMessage(message);
					String fullMessage = getParsedEventMessageForGrayLog(errorCode, resourceInfo, message);
					gm.setFullMessage(fullMessage);
					gm.setJavaTimestamp(Calendar.getInstance().getTimeInMillis());
					gm.setLevel(String.valueOf(level));
					gm.addField(TOKEN_KEY_COD_ERROR, errorCode);
					gm.addField(TOKEN_KEY_RESOURCE_INFO, resourceInfo);
					gm.addField(TOKEN_KEY_MESSAGE, message);
					gm.addFields(grayLogDeclaredFields);
					gm.addField(TOKEN_KEY_SOURCE, grayLogSourceHostName);

					if (grayLogMessageSender != null) {
						grayLogMessageSender.sendMessage(gm);
					}else{
						LOGGER.error(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_008));
					}

				} else {

					LOGGER.warn(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_003, new Object[ ] { errorCode, message }));

				}

			}

		} else {

			LOGGER.debug(Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_002));

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
			return Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_000, new Object[ ] { eventCode, UtilsStringChar.EMPTY_STRING, message });
		} else {
			return Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_GRAYLOG_000, new Object[ ] { eventCode, resourceInfo, message });
		}

	}

}
