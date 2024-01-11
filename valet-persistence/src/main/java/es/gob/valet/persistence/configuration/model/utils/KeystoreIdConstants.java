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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.utils.IKeystoreIdConstants.java.</p>
 * <b>Description:</b><p>Class that contains all the system keystores IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 11/01/2024.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>class that contains all the system keystores IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 11/01/2024.
 */
public class KeystoreIdConstants {

	/**
	 * Constant attribute that represents the ID to identify the SSL - TrustStore.
	 */
	public static final Long ID_SSL_TRUSTSTORE = 5L;

	/**
	 * Constant attribute that represents the ID to identify the TSL - TrustStore.
	 */
	public static final Long ID_TSL_TRUSTSTORE = 17L;
	/**
	 * Constant attribute that represents the ID to identify the CA - TrustStore.
	 */
	public static final Long ID_CA_TRUSTSTORE = 18L;
	/**
	 * Constant attribute that represents the ID to identify the OCSP - TrustStore.
	 */
	public static final Long ID_OCSP_TRUSTSTORE = 19L;

}
