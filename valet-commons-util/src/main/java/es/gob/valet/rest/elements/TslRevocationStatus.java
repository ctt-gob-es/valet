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
 * @version 1.2, 31/01/2019.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

/**
 * <p>Class that represents structure of a TSL revocation status.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.2, 31/01/2019.
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
	 * Attribute that represents the TSP Service Information which has validated the certificate.
	 */
	private TspServiceInformation tspServiceInformation;

	/**
	 * Attribute that represents the evidence type in TSL revocation status.
	 */
	private Integer evidenceType;

	/**
	 * Attribute that represents the evidence in TSL revocation status.
	 */
	private byte[ ] evidence;

	/**
	 * Attribute that represents the revocation reason of the certificate.
	 */
	private Integer revocationReason;

	/**
	 * Attribute that represents the revocation date of the certificate.
	 */
	private String revocationDate;

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
	 * Gets the value of the attribute {@link #tspServiceInformation}.
	 * @return the value of the attribute {@link #tspServiceInformation}.
	 */
	public final TspServiceInformation getTspServiceInformation() {
		return tspServiceInformation;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceInformation}.
	 * @param tspServiceInformationParam The value for the attribute {@link #tspServiceInformation}.
	 */
	public final void setTspServiceInformation(TspServiceInformation tspServiceInformationParam) {
		this.tspServiceInformation = tspServiceInformationParam;
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

	/**
	 * Gets the value of the attribute {@link #revocationReason}.
	 * @return the value of the attribute {@link #revocationReason}.
	 */
	public final Integer getRevocationReason() {
		return revocationReason;
	}

	/**
	 * Sets the value of the attribute {@link #revocationReason}.
	 * @param revocationReasonParam The value for the attribute {@link #revocationReason}.
	 */
	public final void setRevocationReason(Integer revocationReasonParam) {
		this.revocationReason = revocationReasonParam;
	}

	/**
	 * Gets the value of the attribute {@link #revocationDate}.
	 * @return the value of the attribute {@link #revocationDate}.
	 */
	public final String getRevocationDate() {
		return revocationDate;
	}

	/**
	 * Sets the value of the attribute {@link #revocationDate}.
	 * @param revocationDateParam The value for the attribute {@link #revocationDate}.
	 */
	public final void setRevocationDate(String revocationDateParam) {
		this.revocationDate = revocationDateParam;
	}

}
