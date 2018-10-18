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
 * <b>File:</b><p>es.gob.valet.rest.elements.TslRevocationStatus.java.</p>
 * <b>Description:</b><p>Class that represents structure of a TSL revocation status.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

/**
 * <p>Class that represents structure of a TSL revocation status.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.1, 10/09/2018.
 */
public class TslRevocationStatus implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -1849035772641320825L;

	/**
	 * Attribute that represents the revocation status in TSL revocation status.
	 */
	private Integer revocationStatus;

	/**
	 * Attribute that represents the revocation description in TSL revocation status.
	 */
	private String revocationDesc;

	/**
	 * Attribute that represents if is from service status in TSL revocation status.
	 */
	private Boolean isFromServStat;

	/**
	 * Attribute that represents the URL in TSL revocation status.
	 */
	private String url;

	/**
	 * Attribute that represents the DP-AIA in TSL revocation status.
	 */
	private Boolean dpAia;

	/**
	 * Attribute that represents the TSP service name in TSL revocation status.
	 */
	private String tspServiceName;

	/**
	 * Attribute that represents the TSP service type in TSL revocation status.
	 */
	private String tspServiceType;

	/**
	 * Attribute that represents the TSP service status in TSL revocation status.
	 */
	private String tspServiceStatus;

	/**
	 * Attribute that represents the evidence type in TSL revocation status.
	 */
	private Integer evidenceType;

	/**
	 * Attribute that represents the evidence in TSL revocation status.
	 */
	private byte[ ] evidence;

	/**
	 * Gets the value of the attribute {@link #revocationStatus}.
	 * @return the value of the attribute {@link #revocationStatus}.
	 */
	public final Integer getRevocationStatus() {
		return revocationStatus;
	}

	/**
	 * Sets the value of the attribute {@link #revocationStatus}.
	 * @param revocationStatusP The value for the attribute {@link #revocationStatus}.
	 */
	public final void setRevocationStatus(final Integer revocationStatusP) {
		this.revocationStatus = revocationStatusP;
	}

	/**
	 * Gets the value of the attribute {@link #revocationDescription}.
	 * @return the value of the attribute {@link #revocationDescription}.
	 */
	public final String getRevocationDesc() {
		return revocationDesc;
	}

	/**
	 * Sets the value of the attribute {@link #revocationDesc}.
	 * @param revocationDescP The value for the attribute {@link #revocationDesc}.
	 */
	public final void setRevocationDesc(final String revocationDescP) {
		this.revocationDesc = revocationDescP;
	}

	/**
	 * Gets the value of the attribute {@link #isFromServStat}.
	 * @return the value of the attribute {@link #isFromServStat}.
	 */
	public final Boolean getIsFromServStat() {
		return isFromServStat;
	}

	/**
	 * Sets the value of the attribute {@link #isFromServStat}.
	 * @param isFromServStatP The value for the attribute {@link #isFromServStat}.
	 */
	public final void setIsFromServStat(final Boolean isFromServStatP) {
		this.isFromServStat = isFromServStatP;
	}

	/**
	 * Gets the value of the attribute {@link #url}.
	 * @return the value of the attribute {@link #url}.
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * Sets the value of the attribute {@link #url}.
	 * @param urlParam The value for the attribute {@link #url}.
	 */
	public final void setUrl(final String urlParam) {
		this.url = urlParam;
	}

	/**
	 * Gets the value of the attribute {@link #dpAia}.
	 * @return the value of the attribute {@link #dpAia}.
	 */
	public Boolean getDpAia() {
		return dpAia;
	}

	/**
	 * Sets the value of the attribute {@link #dpAia}.
	 * @param dpAiaParam The value for the attribute {@link #dpAia}.
	 */
	public void setDpAia(final Boolean dpAiaParam) {
		this.dpAia = dpAiaParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public final String getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceNameP The value for the attribute {@link #tspServiceName}.
	 */
	public final void setTspServiceName(final String tspServiceNameP) {
		this.tspServiceName = tspServiceNameP;
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
	 * @param tspServiceTypeP The value for the attribute {@link #tspServiceType}.
	 */
	public final void setTspServiceType(final String tspServiceTypeP) {
		this.tspServiceType = tspServiceTypeP;
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
	 * @param tspServiceStatusP The value for the attribute {@link #tspServiceStatus}.
	 */
	public final void setTspServiceStatus(final String tspServiceStatusP) {
		this.tspServiceStatus = tspServiceStatusP;
	}

	/**
	 * Gets the value of the attribute {@link #evidenceType}.
	 * @return the value of the attribute {@link #evidenceType}.
	 */
	public final Integer getEvidenceType() {
		return evidenceType;
	}

	/**
	 * Sets the value of the attribute {@link #evidenceType}.
	 * @param evidenceTypeParam The value for the attribute {@link #evidenceType}.
	 */
	public final void setEvidenceType(final Integer evidenceTypeParam) {
		this.evidenceType = evidenceTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #evidence}.
	 * @return the value of the attribute {@link #evidence}.
	 */
	public final byte[ ] getEvidence() {
		return evidence;
	}

	/**
	 * Sets the value of the attribute {@link #evidence}.
	 * @param evidenceParam The value for the attribute {@link #evidence}.
	 */
	public final void setEvidence(final byte[ ] evidenceParam) {
		if (evidenceParam != null) {
			this.evidence = evidenceParam.clone();
		}
	}

}
