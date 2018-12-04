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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsDeploymentType.java.</p>
 * <b>Description:</b><p>Utilities class to check/manage the deployment type.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 04/12/2018.
 */
package es.gob.valet.commons.utils;

/**
 * <p>Utilities class to check/manage the deployment type.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 04/12/2018.
 */
public final class UtilsDeploymentType {

	/**
	 * Attribute that represents a flag to determine is this is a Rest Services Deployment (<code>true</code>)
	 * or not (<code>false</code>).
	 */
	private static Boolean isDeployedServices = null;

	/**
	 * Attribute that represents a flag to determine is this is a Web Administration Deployment (<code>true</code>)
	 * or not (<code>false</code>).
	 */
	private static Boolean isDeployedWebAdmin = null;

	/**
	 * Constructor method for the class UtilsDeploymentType.java.
	 */
	private UtilsDeploymentType() {
		super();
	}

	/**
	 * Checks if this is a Rest Services Deployment (<code>true</code>) or not (<code>false</code>).
	 * @return if this is a Rest Services Deployment (<code>true</code>), otherwise (<code>false</code>).
	 */
	public static boolean isDeployedServices() {

		if (isDeployedServices == null) {

			try {
				Class.forName("es.gob.valet.rest.services.TslRestServiceApplication", false, UtilsDeploymentType.class.getClassLoader());
				isDeployedServices = Boolean.TRUE;
			} catch (ClassNotFoundException e) {
				isDeployedServices = Boolean.FALSE;
			}

		}

		return isDeployedServices;

	}

	/**
	 * Checks if this is a Web Administration Deployment (<code>true</code>) or not (<code>false</code>).
	 * @return if this is a Web Administration Deployment (<code>true</code>), otherwise (<code>false</code>).
	 */
	public static boolean isDeployedWebAdmin() {

		if (isDeployedWebAdmin == null) {

			try {
				Class.forName("es.gob.valet.controller.WebAdminController", false, UtilsDeploymentType.class.getClassLoader());
				isDeployedWebAdmin = Boolean.TRUE;
			} catch (ClassNotFoundException e) {
				isDeployedWebAdmin = Boolean.FALSE;
			}

		}

		return isDeployedWebAdmin;

	}

	/**
	 * Checks if this deployment has the web administration and the rest services.
	 * @return if this deployment has the web administration and the rest services returns <code>true</code>,
	 * otherwise <code>false</code>.
	 */
	public static boolean isDeployedBoth() {

		return isDeployedServices() && isDeployedWebAdmin();

	}

}
