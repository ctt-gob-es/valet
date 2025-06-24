package es.gob.valet.persistence.configuration.model.dto;

public class TslPendValDTO {

	private Long idTslPendVal;

	private String countryRegionName;

	private Integer sequenceNumber;

	private String issueDate;

	private String urlDistributionPoint;

	private String tslName;

	private String tslResponsible;

	private String expirationDate;

	private SigningCertificateDTO signingCertificateDTO;

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
