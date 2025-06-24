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
 * <b>File:</b><p>es.gob.valet.dto.TslPendValDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the TSL Pending Validation DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/06/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the TSL Pending Validation DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/06/2025.
 */
public class TslPendValDTO {

	/** 
	 * Unique identifier of the pending TSL validation entry.
	 */
	private Long idTslPendVal;

	/** 
	 * Name of the country or region associated with the TSL.
	 */
	private String countryRegionName;

	/** 
	 * Sequence number of the TSL.
	 */
	private Integer sequenceNumber;

	/** 
	 * Issue date of the TSL in string format.
	 */
	private String issueDate;

	/** 
	 * URL of the TSL distribution point.
	 */
	private String urlDistributionPoint;

	/** 
	 * Name of the TSL.
	 */
	private String tslName;

	/** 
	 * Name of the responsible entity or operator of the TSL.
	 */
	private String tslResponsible;

	/** 
	 * Expiration date of the TSL in string format.
	 */
	private String expirationDate;

	/** 
	 * DTO containing information about the certificate used to sign the TSL.
	 */
	private SigningCertificateDTO signingCertificateDTO;

	/**
	 * Constructs a new {@code TslPendValDTO} with the provided TSL metadata and signing certificate information.
	 *
	 * @param idTslPendVal         Unique identifier of the pending TSL validation entry.
	 * @param countryRegionName    Name of the country or region associated with the TSL.
	 * @param sequenceNumber       Sequence number of the TSL.
	 * @param issueDate            Issue date of the TSL.
	 * @param uriTslLocation       URL of the TSL distribution point.
	 * @param tslName              Name of the TSL.
	 * @param tslResponsible       Responsible entity or operator of the TSL.
	 * @param expirationDate       Expiration date of the TSL.
	 * @param signingCertificateDTO DTO containing details of the TSL signing certificate.
	 */
	public TslPendValDTO(Long idTslPendVal, String countryRegionName, int sequenceNumber, String issueDate, String uriTslLocation, String tslName, String tslResponsible, String expirationDate, SigningCertificateDTO signingCertificateDTO) {
		this.idTslPendVal = idTslPendVal;
		this.countryRegionName = countryRegionName;
		this.sequenceNumber = sequenceNumber;
		this.issueDate = issueDate;
		this.urlDistributionPoint = uriTslLocation;
		this.tslName = tslName;
		this.tslResponsible = tslResponsible;
		this.expirationDate = expirationDate;
		this.signingCertificateDTO = signingCertificateDTO;
	}

	/**
	 * Gets the value of the attribute {@link #idTslPendVal}.
	 * @return the value of the attribute {@link #idTslPendVal}.
	 */
	public Long getIdTslPendVal() {
		return idTslPendVal;
	}

	/**
	 * Sets the value of the attribute {@link #idTslPendVal}.
	 * @param idTslPendVal The value for the attribute {@link #idTslPendVal}.
	 */
	public void setIdTslPendVal(Long idTslPendVal) {
		this.idTslPendVal = idTslPendVal;
	}

	/**
	 * Gets the value of the attribute {@link #countryRegionName}.
	 * @return the value of the attribute {@link #countryRegionName}.
	 */
	public String getCountryRegionName() {
		return countryRegionName;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionName}.
	 * @param countryRegionName The value for the attribute {@link #countryRegionName}.
	 */
	public void setCountryRegionName(String countryRegionName) {
		this.countryRegionName = countryRegionName;
	}

	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumber The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Gets the value of the attribute {@link #issueDate}.
	 * @return the value of the attribute {@link #issueDate}.
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * Sets the value of the attribute {@link #issueDate}.
	 * @param issueDate The value for the attribute {@link #issueDate}.
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Gets the value of the attribute {@link #urlDistributionPoint}.
	 * @return the value of the attribute {@link #urlDistributionPoint}.
	 */
	public String getUrlDistributionPoint() {
		return urlDistributionPoint;
	}

	/**
	 * Sets the value of the attribute {@link #urlDistributionPoint}.
	 * @param urlDistributionPoint The value for the attribute {@link #urlDistributionPoint}.
	 */
	public void setUrlDistributionPoint(String urlDistributionPoint) {
		this.urlDistributionPoint = urlDistributionPoint;
	}

	/**
	 * Gets the value of the attribute {@link #tslName}.
	 * @return the value of the attribute {@link #tslName}.
	 */
	public String getTslName() {
		return tslName;
	}

	/**
	 * Sets the value of the attribute {@link #tslName}.
	 * @param tslName The value for the attribute {@link #tslName}.
	 */
	public void setTslName(String tslName) {
		this.tslName = tslName;
	}

	/**
	 * Gets the value of the attribute {@link #tslResponsible}.
	 * @return the value of the attribute {@link #tslResponsible}.
	 */
	public String getTslResponsible() {
		return tslResponsible;
	}

	/**
	 * Sets the value of the attribute {@link #tslResponsible}.
	 * @param tslResponsible The value for the attribute {@link #tslResponsible}.
	 */
	public void setTslResponsible(String tslResponsible) {
		this.tslResponsible = tslResponsible;
	}

	/**
	 * Gets the value of the attribute {@link #expirationDate}.
	 * @return the value of the attribute {@link #expirationDate}.
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the value of the attribute {@link #expirationDate}.
	 * @param expirationDate The value for the attribute {@link #expirationDate}.
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the value of the attribute {@link #signingCertificateDTO}.
	 * @return the value of the attribute {@link #signingCertificateDTO}.
	 */
	public SigningCertificateDTO getSigningCertificateDTO() {
		return signingCertificateDTO;
	}

	/**
	 * Sets the value of the attribute {@link #signingCertificateDTO}.
	 * @param signingCertificateDTO The value for the attribute {@link #signingCertificateDTO}.
	 */
	public void setSigningCertificateDTO(SigningCertificateDTO signingCertificateDTO) {
		this.signingCertificateDTO = signingCertificateDTO;
	}

}
