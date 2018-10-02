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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsProviders.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>24 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 24 sept. 2018.
 */
package es.gob.valet.commons.utils;

import java.security.Provider;
import java.security.Security;

import org.apache.log4j.Logger;

import iaik.security.provider.IAIK;

/** 
 * <p>Utilities class for manage the cryptographic providers.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 24 sept. 2018.
 */
public final class UtilsProviders {
	/**
	 * Constant attribute that represents the IAIK Provider.
	 */
	public static final Provider IAIK_PROVIDER = IAIK.getInstance();

	/**
	 * Constant attribute that represents the string to identify the name of the IAIK provider.
	 */
	public static final String IAIK_PROVIDER_TOKEN_NAME = IAIK_PROVIDER.getName();


	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilsProviders.class);

	/**
	 * Constructor method for the class UtilsProviders.java.
	 */
	private UtilsProviders() {
		super();
	}

	/**
	 * Method that initializes the providers
	 */
	public static void initializeProviders() {

		// Eliminamos (por si ya existiera) y añadimos el proveedor IAIK en la
		// posición 1.
		Security.removeProvider(IAIK_PROVIDER_TOKEN_NAME);
		Security.insertProviderAt(IAIK_PROVIDER, 1);
		
	}
}
