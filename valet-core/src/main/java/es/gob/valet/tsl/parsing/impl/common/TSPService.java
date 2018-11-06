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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.TSPService.java.</p>
 * <b>Description:</b><p>Class that defines a TSP Service with all its information not dependent
 * of the specification or TSL version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.tsl.parsing.impl.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class that defines a TSP Service with all its information not dependent
 * of the specification or TSL version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public class TSPService implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -5510858990815063070L;

	/**
	 * Attribute that represents all the service information associated to the TSP.
	 */
	private ServiceInformation serviceInformation = null;

	/**
	 * Attribute that represents all the service history information about this TSP.
	 */
	private List<ServiceHistoryInstance> shiList = null;

	/**
	 * Constructor method for the class TSPService.java.
	 */
	public TSPService() {
		super();
		serviceInformation = new ServiceInformation();
		shiList = new ArrayList<ServiceHistoryInstance>();
	}

	/**
	 * Gets the value of the attribute {@link #serviceInformation}.
	 * @return the value of the attribute {@link #serviceInformation}.
	 */
	public final ServiceInformation getServiceInformation() {
		return serviceInformation;
	}

	/**
	 * Sets the value of the attribute {@link #serviceInformation}.
	 * @param serviceInformationParam The value for the attribute {@link #serviceInformation}.
	 */
	public final void setServiceInformation(ServiceInformation serviceInformationParam) {
		this.serviceInformation = serviceInformationParam;
	}

	/**
	 * Gets an array of service information about the history of the TSP.
	 * @return List of service information about the history of the TSP.
	 * <code>null</code> if there is not.
	 */
	public final List<ServiceHistoryInstance> getAllServiceHistory() {

		if (shiList.isEmpty()) {
			return null;
		} else {
			return shiList;
		}

	}

	/**
	 * Adds a new service history information for this TSP.
	 * @param shi Service History information to add.
	 */
	public final void addNewServiceHistory(ServiceHistoryInstance shi) {

		if (shi != null) {
			shiList.add(shi);
		}

	}

	/**
	 * Checks if there is some service history information for this TSP.
	 * @return <code>true</code> if there is, otherwise <code>false</code>.
	 */
	public final boolean isThereSomeServiceHistory() {
		return !shiList.isEmpty();
	}

}
