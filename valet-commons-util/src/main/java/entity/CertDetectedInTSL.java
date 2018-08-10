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
 * <b>File:</b><p>entity.CertDetectedInTSL.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>7 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 7 ago. 2018.
 */
package entity;

import java.io.Serializable;
import java.util.Map;

/** 
 * <p>Class that represents structure of a certificated detected in TSL.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.0, 7/8/2018.
 */
public class CertDetectedInTSL implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = -4698735337546155613L;

	/**
	 * Attribute that represents the TSP name in certificated detected in TSL.
	 */
	private String tspName;

	/**
	 * Attribute that represents the TSP service name in certificated detected in TSL.
	 */
	private String tspServiceName;

	/**
	 * Attribute that represents the TSP service type in certificated detected in TSL.
	 */
	private String tspServiceType;

	/**
	 * Attribute that represents the TSP service status in certificated detected in TSL.
	 */
	private String tspServiceStatus;

	/**
	 * Attribute that represents the certificate information in certificated detected in TSL.
	 */
	private Map<String, String> certInfo;

	/**
	 * Attribute that represents the TSL revocation status in certificated detected in TSL.
	 */
	private TslRevocationStatus tslRevocStatus;

	/**
	 * Gets the value of the attribute {@link #tspName}.
	 * @return the value of the attribute {@link #tspName}.
	 */
	public String getTspName() {
		return tspName;
	}

	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspNameParam The value for the attribute {@link #tspName}.
	 */
	public void setTspName(final String tspNameParam) {
		this.tspName = tspNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public String getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceNameP The value for the attribute {@link #tspServiceName}.
	 */
	public void setTspServiceName(final String tspServiceNameP) {
		this.tspServiceName = tspServiceNameP;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceType}.
	 * @return the value of the attribute {@link #tspServiceType}.
	 */
	public String getTspServiceType() {
		return tspServiceType;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceType}.
	 * @param tspServiceTypeP The value for the attribute {@link #tspServiceType}.
	 */
	public void setTspServiceType(final String tspServiceTypeP) {
		this.tspServiceType = tspServiceTypeP;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceStatus}.
	 * @return the value of the attribute {@link #tspServiceStatus}.
	 */
	public String getTspServiceStatus() {
		return tspServiceStatus;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceStatus}.
	 * @param tspServiceStatusP The value for the attribute {@link #tspServiceStatus}.
	 */
	public void setTspServiceStatus(final String tspServiceStatusP) {
		this.tspServiceStatus = tspServiceStatusP;
	}

	/**
	 * Gets the value of the attribute {@link #certInfo}.
	 * @return the value of the attribute {@link #certInfo}.
	 */
	public Map<String, String> getCertInfo() {
		return certInfo;
	}

	/**
	 * Sets the value of the attribute {@link #certInfo}.
	 * @param certInfoParam The value for the attribute {@link #certInfo}.
	 */
	public void setCertInfo(final Map<String, String> certInfoParam) {
		this.certInfo = certInfoParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslRevocStatus}.
	 * @return the value of the attribute {@link #tslRevocStatus}.
	 */
	public TslRevocationStatus getTslRevocStatus() {
		return tslRevocStatus;
	}

	/**
	 * Sets the value of the attribute {@link #tslRevocStatus}.
	 * @param tslRevocStatus The value for the attribute {@link #tslRevocStatus}.
	 */
	public void setTslRevocStatus(final TslRevocationStatus tslRevocStatus) {
		this.tslRevocStatus = tslRevocStatus;
	}

}
