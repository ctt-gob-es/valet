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
 * <b>File:</b><p>es.gob.valet.rest.elements.TspServiceHistoryInf.java.</p>
 * <b>Description:</b><p>Class that represents the structure of a TSP Service History Information.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>29/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.2, 06/02/2019.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

import es.gob.valet.rest.elements.json.DateString;

/**
 * <p>Class that represents the structure of a TSP Service History Information.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 06/02/2019.
 */
public class TspServiceHistoryInf implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -3066349570262510177L;

	/**
	 * Attribute that represents the TSP service name.
	 */
	private String tspServiceName;

	/**
	 * Attribute that represents the TSP service type.
	 */
	private String tspServiceType;

	/**
	 * Attribute that represents the TSP service status.
	 */
	private String tspServiceStatus;

	/**
	 * Attribute that represents the TSP service status starting date.
	 */
	private DateString tspServiceStatusStartingDate;

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public final String getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceNameParam The value for the attribute {@link #tspServiceName}.
	 */
	public final void setTspServiceName(String tspServiceNameParam) {
		this.tspServiceName = tspServiceNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceType}.
	 * @return the value of the attribute {@link #tspServiceType}.
	 */
	public final String getTspServiceType() {
		return tspServiceType;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceType}.
	 * @param tspServiceTypeParam The value for the attribute {@link #tspServiceType}.
	 */
	public final void setTspServiceType(String tspServiceTypeParam) {
		this.tspServiceType = tspServiceTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceStatus}.
	 * @return the value of the attribute {@link #tspServiceStatus}.
	 */
	public final String getTspServiceStatus() {
		return tspServiceStatus;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceStatus}.
	 * @param tspServiceStatusParam The value for the attribute {@link #tspServiceStatus}.
	 */
	public final void setTspServiceStatus(String tspServiceStatusParam) {
		this.tspServiceStatus = tspServiceStatusParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceStatusStartingDate}.
	 * @return the value of the attribute {@link #tspServiceStatusStartingDate}.
	 */
	public final DateString getTspServiceStatusStartingDate() {
		return tspServiceStatusStartingDate;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceStatusStartingDate}.
	 * @param tspServiceStatusStartingDateParam The value for the attribute {@link #tspServiceStatusStartingDate}.
	 */
	public final void setTspServiceStatusStartingDate(DateString tspServiceStatusStartingDateParam) {
		this.tspServiceStatusStartingDate = tspServiceStatusStartingDateParam;
	}

}
