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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.TSLValidatorFactory.java.</p>
 * <b>Description:</b><p>Class that represents a TSL Validator Factory for all differents
 * specification and versions of TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.certValidation.impl;

import es.gob.valet.tsl.certValidation.ifaces.ITSLValidator;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.utils.TSLSpecificationsVersions;

/**
 * <p>Class that represents a TSL Validator Factory for all differents
 * specification and versions of TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public final class TSLValidatorFactory {

	/**
	 * Constructor method for the class TSLValidatorFactory.java.
	 */
	private TSLValidatorFactory() {
		super();
	}

	/**
	 * Factory method that creates a new instance of a TSL Validator for a concrete
	 * TSL Object based on its specification and version.
	 * @param tslObject TSL object representation from which cretate the TSL Validator.
	 * @return a TSL Validator object representation. If the input parameter is <code>null</code>, then the return
	 * also is <code>null</code>.
	 */
	public static ITSLValidator createTSLValidator(ITSLObject tslObject) {

		ITSLValidator result = null;

		if (tslObject != null) {

			if (TSLSpecificationsVersions.SPECIFICATION_119612.equals(tslObject.getSpecification())) {

				if (TSLSpecificationsVersions.VERSION_020101.equals(tslObject.getSpecificationVersion())) {
					result = new es.gob.valet.tsl.certValidation.impl.ts119612.v020101.TSLValidator(tslObject);
				}

			}

		}

		return result;

	}

}
