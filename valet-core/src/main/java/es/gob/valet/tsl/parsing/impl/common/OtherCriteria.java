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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.OtherCriteria.java.</p>
 * <b>Description:</b><p>Abstract class that represents a TSL Other Criteria with could contains
 * differents elements regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.tsl.parsing.impl.common;

import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.ifaces.IAnyTypeOtherCriteria;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.ifaces.ITSLSpecificationsVersions;

/**
 * <p>Abstract class that represents a TSL Other Criteria with could contains
 * differents elements regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public abstract class OtherCriteria implements IAnyTypeOtherCriteria {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 2921456592752357607L;

	/**
	 * Constructor method for the class OtherCriteria.java.
	 */
	public OtherCriteria() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.IAnyTypeOtherCriteria#checkOtherCriteriaValue(es.gob.valet.tsl.parsing.ifaces.ITSLObject)
	 */
	@Override
	public void checkOtherCriteriaValue(ITSLObject tsl) throws TSLMalformedException {

		// En función de la especificación y versión de esta, se actúa de una
		// manera u otra.
		String tslSpecification = tsl.getSpecification();
		String tslSpecificationVersion = tsl.getSpecificationVersion();

		// Según la especificación...
		switch (tslSpecification) {
			case ITSLSpecificationsVersions.SPECIFICATION_119612:
				// Según la versión...
				switch (tslSpecificationVersion) {
					case ITSLSpecificationsVersions.VERSION_020101:
						checkOtherCriteriaValueSpec119612Vers020101();
						break;
					default:
						break;
				}
				break;

			default:
				break;

		}

	}

	/**
	 * Checks if the Other Criteria has an appropiate value in the TSL for the specification ETSI 119612
	 * and version 2.1.1.
	 * @throws TSLMalformedException In case of the Other Criteria has not a correct value.
	 */
	protected abstract void checkOtherCriteriaValueSpec119612Vers020101() throws TSLMalformedException;

	/**
	 * Abstract method that returns the element name for this Other Criteria Type.
	 * @return element name for this Other Criteria Type.
	 */
	protected abstract String getOtherCriteriaType();

}
