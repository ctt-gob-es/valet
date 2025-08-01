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
 * <b>File:</b><p>es.gob.valet.form.ValetServerForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form to do a Certificate Validation request.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.form;

import org.springframework.web.multipart.MultipartFile;

/** 
 * <p>Class that represents the backing form to do a Certificate Validation request.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
public class CertificateValidationForm {

	/**
	 * Attribute that represents the ID of the ValetServer selected in the form.
	 */
	private Long valetServerId;

	/**
	 * Attribute that represents the uploaded certificate file in the form.
	 */
	private MultipartFile certFile;

	/**
	 * Attribute that indicates whether to fetch additional mapping information during validation.
	 */
	private boolean fetchMappings;

	/**
	 * Attribute that represents the optional TSL (Trusted Service List) location in the form.
	 */
	private String tslLocation;

	/**
	 * Attribute that represents the optional validation date provided in the form.
	 */
	private String detectionDate;

	/**
	 * Attribute that indicates whether to check the revocation status of the certificate.
	 */
	private boolean checkRevocationStatus;

	/**
	 * Attribute that indicates whether to return revocation evidence with the validation result.
	 */
	private boolean returnRevocationEvidence;

	/**
	 * Attribute that indicates whether to return the certificate chain in the validation result.
	 */
	private boolean returnCertificateChain;

	/**
	 * Gets the value of the attribute {@link #valetServerId}.
	 * @return the value of the attribute {@link #valetServerId}.
	 */
	public Long getValetServerId() {
		return valetServerId;
	}

	/**
	 * Sets the value of the attribute {@link #valetServerId}.
	 * @param valetServerId The value for the attribute {@link #valetServerId}.
	 */
	public void setValetServerId(Long valetServerId) {
		this.valetServerId = valetServerId;
	}

	/**
	 * Gets the value of the attribute {@link #certFile}.
	 * @return the value of the attribute {@link #certFile}.
	 */
	public MultipartFile getCertFile() {
		return certFile;
	}

	/**
	 * Sets the value of the attribute {@link #certFile}.
	 * @param certFile The value for the attribute {@link #certFile}.
	 */
	public void setCertFile(MultipartFile certFile) {
		this.certFile = certFile;
	}

	/**
	 * Gets the value of the attribute {@link #fetchMappings}.
	 * @return the value of the attribute {@link #fetchMappings}.
	 */
	public boolean isFetchMappings() {
		return fetchMappings;
	}

	/**
	 * Sets the value of the attribute {@link #fetchMappings}.
	 * @param fetchMappings The value for the attribute {@link #fetchMappings}.
	 */
	public void setFetchMappings(boolean fetchMappings) {
		this.fetchMappings = fetchMappings;
	}

	/**
	 * Gets the value of the attribute {@link #tslLocation}.
	 * @return the value of the attribute {@link #tslLocation}.
	 */
	public String getTslLocation() {
		return tslLocation;
	}

	/**
	 * Sets the value of the attribute {@link #tslLocation}.
	 * @param tslLocation The value for the attribute {@link #tslLocation}.
	 */
	public void setTslLocation(String tslLocation) {
		this.tslLocation = tslLocation;
	}

	/**
	 * Gets the value of the attribute {@link #detectionDate}.
	 * @return the value of the attribute {@link #detectionDate}.
	 */
	public String getDetectionDate() {
		return detectionDate;
	}

	/**
	 * Sets the value of the attribute {@link #detectionDate}.
	 * @param detectionDate The value for the attribute {@link #detectionDate}.
	 */
	public void setDetectionDate(String detectionDate) {
		this.detectionDate = detectionDate;
	}

	/**
	 * Gets the value of the attribute {@link #checkRevocationStatus}.
	 * @return the value of the attribute {@link #checkRevocationStatus}.
	 */
	public boolean isCheckRevocationStatus() {
		return checkRevocationStatus;
	}

	/**
	 * Sets the value of the attribute {@link #checkRevocationStatus}.
	 * @param checkRevocationStatus The value for the attribute {@link #checkRevocationStatus}.
	 */
	public void setCheckRevocationStatus(boolean checkRevocationStatus) {
		this.checkRevocationStatus = checkRevocationStatus;
	}

	/**
	 * Gets the value of the attribute {@link #returnRevocationEvidence}.
	 * @return the value of the attribute {@link #returnRevocationEvidence}.
	 */
	public boolean isReturnRevocationEvidence() {
		return returnRevocationEvidence;
	}

	/**
	 * Sets the value of the attribute {@link #returnRevocationEvidence}.
	 * @param returnRevocationEvidence The value for the attribute {@link #returnRevocationEvidence}.
	 */
	public void setReturnRevocationEvidence(boolean returnRevocationEvidence) {
		this.returnRevocationEvidence = returnRevocationEvidence;
	}

	/**
	 * Gets the value of the attribute {@link #returnCertificateChain}.
	 * @return the value of the attribute {@link #returnCertificateChain}.
	 */
	public boolean isReturnCertificateChain() {
		return returnCertificateChain;
	}

	/**
	 * Sets the value of the attribute {@link #returnCertificateChain}.
	 * @param returnCertificateChain The value for the attribute {@link #returnCertificateChain}.
	 */
	public void setReturnCertificateChain(boolean returnCertificateChain) {
		this.returnCertificateChain = returnCertificateChain;
	}
}