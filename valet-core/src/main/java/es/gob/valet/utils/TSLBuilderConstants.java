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
 * <b>File:</b><p>es.gob.valet.utils.threads.EMailTimeLimitedOperation.java.</p>
 * <b>Description:</b><p>Class that contain contstants for TSL Builder.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2023.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/09/2023.
 */
package es.gob.valet.utils;

/**
 * <p>Class that contain contstants for TSL Builder.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/09/2023.
 */
public class TSLBuilderConstants {
	/**
	 * Constant attribute that identifies a extension how 'Schemne Extension'.
	 */
	public static final int TYPE_SCHEME = 0;

	/**
	 * Constant attribute that identifies a extension how 'TSP Information Extension'.
	 */
	public static final int TYPE_TSP_INFORMATION = 1;

	/**
	 * Constant attribute that identifies a extension how 'Service Information Extension'.
	 */
	public static final int TYPE_SERVICE_INFORMATION = 2;

	/**
	 * Constant attribute that represents the implementation extension: AdditionalServiceInformation.
	 */
	public static final int IMPL_ADDITIONAL_SERVICE_INFORMATION = 0;

	/**
	 * Constant attribute that represents the implementation extension: ExpiredCertsRevocationInfo.
	 */
	public static final int IMPL_EXPIRED_CERTS_REVOCATION_INFO = 1;

	/**
	 * Constant attribute that represents the implementation extension: Qualifications.
	 */
	public static final int IMPL_QUALIFICATIONS = 2;

	/**
	 * Constant attribute that represents the implementation extension: TakenOverBy.
	 */
	public static final int IMPL_TAKENOVERBY = 3;

	/**
	 * Constant attribute that represents the implementation extension: UnknownExtension.
	 */
	public static final int IMPL_UNKNOWN_EXTENSION = 4;
}
