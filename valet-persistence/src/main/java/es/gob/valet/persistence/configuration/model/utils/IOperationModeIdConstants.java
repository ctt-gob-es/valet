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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.IOperationModeIdConstants.java.</p>
 * <b>Description:</b><p>Interface that contains all the IDs of the authentication types for the
 * connections.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>Interface that contains all the IDs of the association types for the
 * mappings of the system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public interface IOperationModeIdConstants {

	/**
	 * Constant attribute that represents the ID to identify the basic connection type.
	 */
	Long ID_NONE = 1L;

	/**
	 * Constant attribute that represents the ID to identify the none authentication connection type.
	 */
	Long ID_NONE_AUTHENTICATION = 2L;

	/**
	 * Constant attribute that represents the ID to identify the basic authentication connection type.
	 */
	Long ID_BASIC_AUTHENTICATION = 3L;

	/**
	 * Constant attribute that represents the ID to identify the NTLM authentication connection type.
	 */
	Long ID_NTLM_AUTHENTICATION = 4L;

	/**
	 * Constant attribute that represents the ID to identify the basic connection type.
	 */
	int ID_NONE_INTVALUE = 1;

	/**
	 * Constant attribute that represents the ID to identify the none authentication connection type.
	 */
	int ID_NONE_AUTHENTICATION_INTVALUE = 2;

	/**
	 * Constant attribute that represents the ID to identify the basic authentication connection type.
	 */
	int ID_BASIC_AUTHENTICATION_INTVALUE = 3;

	/**
	 * Constant attribute that represents the ID to identify the NTLM authentication connection type.
	 */
	int ID_NTLM_AUTHENTICATION_INTVALUE = 4;

}
