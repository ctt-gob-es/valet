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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension.java.</p>
 * <b>Description:</b><p>Interface that defines the common method for any type extensions in a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.ifaces;

import java.io.Serializable;

import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;

/**
 * <p>Interface that defines the common method for any type extensions in a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public interface IAnyTypeExtension extends Serializable {

	/**
	 * Checks if the extension is critical or not.
	 * @return <code>true</code> if the extension is critical, otherwise <code>false</code>.
	 */
	boolean isCritical();

	/**
	 * Gets the implementation for the extension.
	 * @return Implementation for the extension.
	 */
	int getImplementationExtension();

	/**
	 * Checks if the extension has an appropiate value in the TSL.
	 * @param tsl TSL Object representation that contains the service and the extension.
	 * @param shi Service Information (or service history information) in which is declared the extension.
	 * @throws TSLMalformedException In case of the extension has not a correct value.
	 */
	void checkExtensionValue(ITSLObject tsl, ServiceHistoryInstance shi) throws TSLMalformedException;

}
