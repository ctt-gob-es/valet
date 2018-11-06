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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.utils.IStatusCertificateIdConstants.java.</p>
 * <b>Description:</b><p>Interface that contains all the status certificates IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>Interface that contains all the status certificates IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public interface IStatusCertificateIdConstants {

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Correct.
	 */
	Long ID_SC_CORRECT = 0L;

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Expired.
	 */
	Long ID_SC_EXPIRED = 1L;

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Revoked.
	 */
	Long ID_SC_REVOKED = 2L;

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Other.
	 */
	Long ID_SC_OTHER = 3L;

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Not Exist.
	 */
	Long ID_SC_NOTEXIST = 4L;

	/**
	 * Constant attribute that represents the ID to identify the Status Certificate - Not Valid Yet.
	 */
	Long ID_SC_NOTVALIDYET = 5L;

}
