package es.gob.valet.persistence.configuration.model.dto;


public class TslDataSummary {
	
	private String country;
	
	private int numberSequence;
	
	private String responsible;
	
	private String issueDate;
	
	private String expireDate;
	
	private String distributionPoint;

	public TslDataSummary(String country, int numberSequence, String responsible, String issueDate, String expireDate, String distributionPoint) {
		this.country = country;
		this.numberSequence = numberSequence;
		this.responsible = responsible;
		this.issueDate = issueDate;
		this.expireDate = expireDate;
		this.distributionPoint = distributionPoint;
	}
	
	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public String getCountry() {
		return country;
	}

	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	/**
	 * Gets the value of the attribute {@link #numberSequence}.
	 * @return the value of the attribute {@link #numberSequence}.
	 */
	public int getNumberSequence() {
		return numberSequence;
	}

	
	/**
	 * Sets the value of the attribute {@link #numberSequence}.
	 * @param numberSequence The value for the attribute {@link #numberSequence}.
	 */
	public void setNumberSequence(int numberSequence) {
		this.numberSequence = numberSequence;
	}

	
	/**
	 * Gets the value of the attribute {@link #responsible}.
	 * @return the value of the attribute {@link #responsible}.
	 */
	public String getResponsible() {
		return responsible;
	}

	
	/**
	 * Sets the value of the attribute {@link #responsible}.
	 * @param responsible The value for the attribute {@link #responsible}.
	 */
	public void setResponsible(String responsible) {
		this.responsible = responsible;
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
	 * Gets the value of the attribute {@link #expireDate}.
	 * @return the value of the attribute {@link #expireDate}.
	 */
	public String getExpireDate() {
		return expireDate;
	}

	
	/**
	 * Sets the value of the attribute {@link #expireDate}.
	 * @param expireDate The value for the attribute {@link #expireDate}.
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	
	/**
	 * Gets the value of the attribute {@link #distributionPoint}.
	 * @return the value of the attribute {@link #distributionPoint}.
	 */
	public String getDistributionPoint() {
		return distributionPoint;
	}

	
	/**
	 * Sets the value of the attribute {@link #distributionPoint}.
	 * @param distributionPoint The value for the attribute {@link #distributionPoint}.
	 */
	public void setDistributionPoint(String distributionPoint) {
		this.distributionPoint = distributionPoint;
	}
	
	
	
}
