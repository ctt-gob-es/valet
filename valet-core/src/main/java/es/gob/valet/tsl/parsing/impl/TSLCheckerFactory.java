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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.TSLCheckerFactory.java.</p>
 * <b>Description:</b><p>Class that represents a TSL Data Checker Factory for all differents
 * specification and versions of TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.tsl.parsing.impl;

import es.gob.valet.tsl.parsing.ifaces.ITSLChecker;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.ifaces.ITSLSpecificationsVersions;

/**
 * <p>Class that represents a TSL Data Checker Factory for all differents
 * specification and versions of TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public final class TSLCheckerFactory {

	/**
	 * Constructor method for the class TSLCheckerFactory.java.
	 */
	private TSLCheckerFactory() {
		super();
	}

	/**
	 * Factory method that creates a new instance of a TSL Data Checker for a concrete
	 * TSL Object based on its specification and version.
	 * @param tslObject TSL object representation from which cretate the TSL Data Checker.
	 * @return a TSL Data Checker object representation. If the input parameter is <code>null</code>, then the return
	 * also is <code>null</code>.
	 */
	public static ITSLChecker createTSLChecker(ITSLObject tslObject) {

		ITSLChecker result = null;

		if (tslObject != null) {

			if (ITSLSpecificationsVersions.SPECIFICATION_119612.equals(tslObject.getSpecification())) {

				if (ITSLSpecificationsVersions.VERSION_020101.equals(tslObject.getSpecificationVersion())) {
					result = new es.gob.valet.tsl.parsing.impl.ts119612.v020101.TSLChecker(tslObject);
				}

			}

		}

		return result;

	}

}
