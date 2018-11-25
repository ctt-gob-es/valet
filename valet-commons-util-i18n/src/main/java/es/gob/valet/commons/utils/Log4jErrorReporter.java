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
 * <b>File:</b><p>es.gob.valet.commons.utils.Log4jErrorReporter.java.</p>
 * <b>Description:</b><p>Log4j implementation for the error logger to use in Gray Log.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.commons.utils;

import org.apache.log4j.Logger;

import biz.paluch.logging.gelf.intern.ErrorReporter;

/**
 * <p>Log4j implementation for the error logger to use in Gray Log.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public final class Log4jErrorReporter implements ErrorReporter {

	/**
	 * Attribute that represents the logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(Log4jErrorReporter.class);

	/**
	 * Attribute that represents the singleton unique instance for this class.
	 */
	private static Log4jErrorReporter instance = null;

	/**
	 * Constructor method for the class Log4jErrorReporter.java.
	 */
	private Log4jErrorReporter() {
		super();
	}

	/**
	 * Gets the singleton unique instance of this class.
	 * @return the singleton unique instance of this class.
	 */
	public static Log4jErrorReporter getInstance() {
		if (instance == null) {
			instance = new Log4jErrorReporter();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 * @see biz.paluch.logging.gelf.intern.ErrorReporter#reportError(java.lang.String, java.lang.Exception)
	 */
	@Override
	public void reportError(String message, Exception e) {
		LOGGER.error(message, e);
	}

}
