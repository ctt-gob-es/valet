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
 * <b>File:</b><p>es.gob.valet.tsl.access.TSLProperties.java.</p>
 * <b>Description:</b><p>Class that provides access to the differents properties associated to the TSL operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.tsl.access;

import es.gob.valet.commons.utils.StaticValetConfig;

/**
 * <p>Class that provides access to the differents properties associated to the TSL operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public final class TSLProperties {

	/**
	 * Constructor method for the class TSLProperties.java.
	 */
	private TSLProperties() {
		super();
	}

	/**
	 * Checks if it is necessary to check the structure of the TSL signature.
	 * @return <code>true</code> if it is necessary to check the structure of the TSL signature,
	 * otherwise <code>false</code>.
	 */
	public static boolean isRequiredToCheckTslSignatureStructure() {
		return Boolean.parseBoolean(StaticValetConfig.getProperty(StaticValetConfig.TSL_SIGNATURE_VERIFY_STRUCTURE));
	}

	/**
	 * Checks if it is necessary to check the TSL signature by its specification.
	 * @return <code>true</code> if it is necessary to check the TSL signature by its specification,
	 * otherwise <code>false</code>.
	 */
	public static boolean isRequiredToCheckTslSignatureByItsSpecification() {
		return Boolean.parseBoolean(StaticValetConfig.getProperty(StaticValetConfig.TSL_SIGNATURE_VERIFY_SPECIFICATION));
	}

}
