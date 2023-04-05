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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsResources.java.</p>
 * <b>Description:</b><p>Class that provides functionality to control resources.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 03/04/2023.
 */
package es.gob.valet.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;

/**
 * <p>Class that provides functionality to control resources.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 03/04/2023.
 */
public final class UtilsResources {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(UtilsResources.class);

	/**
	 * Constructor method for the class UtilsResources.java.
	 */
	private UtilsResources() {
		super();
	}

	/**
	 * Method that handles the closing of a {@link InputStream} resource.
	 * @param is Parameter that represents a {@link InputStream} resource.
	 */
	public static void safeCloseInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { is.getClass().getName() }), e);
			}
		}
	}

	/**
	 * Method that handles the closing of a {@link OutputStream} resource.
	 * @param os Parameter that represents a {@link OutputStream} resource.
	 */
	public static void safeCloseOutputStream(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { os.getClass().getName() }), e);
			}
		}
	}

	/**
	 * To free resources, this method sets the length of the input {@link StringBuilder} to
	 * zero and then trim it.
	 * @param sb String builder to clean. If it is <code>null</code>, this method do nothing.
	 */
	public static void cleanStringBuilder(StringBuilder sb) {

		if (sb != null) {
			sb.setLength(0);
			sb.trimToSize();
		}

	}

	/**
	 * Method that handles the closing of a {@link Reader} resource.
	 * @param br Parameter that represents a {@link Reader} resource.
	 */
	public static void safeCloseReader(Reader br) {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { br.getClass().getName() }), e);
			}
		}
	}

	/**
	 * Method that handles the closing of a {@link Writer} resource.
	 * @param wr Parameter that represents a {@link Writer} resource.
	 */
	public static void safeCloseWriter(Writer wr) {
		if (wr != null) {
			try {
				wr.close();
			} catch (IOException e) {
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { wr.getClass().getName() }), e);
			}
		}
	}

}
