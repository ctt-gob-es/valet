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
 * <b>File:</b><p>es.gob.valet.commons.utils.connection.UtilsConnection.java.</p>
 * <b>Description:</b><p>Utilities class relating to general connections properties and operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.commons.utils.connection;

import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;

/**
 * <p>Utilities class relating to general connections properties and operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public final class UtilsConnection {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilsConnection.class);

	/**
	 * Constructor method for the class UtilsConnection.java.
	 */
	private UtilsConnection() {
		super();
	}

	/**
	 * Gets the maximum sixe allowed for resource connections.
	 * @return The maximum size allowed for resource connections (in bytes).
	 */
	public static int getMaxSizeConnection() {

		int result = NumberConstants.NUM5242880;
		try {
			String value = StaticValetConfig.getProperty(StaticValetConfig.CONECTION_MAXSIZE);
			if (UtilsStringChar.isNullOrEmptyTrim(value)) {
				LOGGER.warn(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CONNECTION_001, new Object[ ] { StaticValetConfig.CONECTION_MAXSIZE, result }));
			} else {
				try {
					result = Integer.parseInt(value);
				} catch (Exception e) {
					LOGGER.warn(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CONNECTION_000, new Object[ ] { StaticValetConfig.CONECTION_MAXSIZE, result }));
				}
			}
		} catch (Exception e) {
			LOGGER.warn(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CONNECTION_001, new Object[ ] { StaticValetConfig.CONECTION_MAXSIZE, result }));
		}
		return result;

	}

}
