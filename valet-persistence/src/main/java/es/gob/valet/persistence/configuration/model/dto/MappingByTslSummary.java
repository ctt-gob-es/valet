package es.gob.valet.persistence.configuration.model.dto;


public class MappingByTslSummary {
	
	private String country;
	
	private String associationType;
	
	private String identificator;
	
	private String value;

	public MappingByTslSummary(String country, String associationType, String identificator, String value) {
		this.country = country;
		this.associationType = associationType;
		this.identificator = identificator;
		this.value = value;
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
	 * Gets the value of the attribute {@link #associationType}.
	 * @return the value of the attribute {@link #associationType}.
	 */
	public String getAssociationType() {
		return associationType;
	}

	
	/**
	 * Sets the value of the attribute {@link #associationType}.
	 * @param associationType The value for the attribute {@link #associationType}.
	 */
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	
	/**
	 * Gets the value of the attribute {@link #identificator}.
	 * @return the value of the attribute {@link #identificator}.
	 */
	public String getIdentificator() {
		return identificator;
	}

	
	/**
	 * Sets the value of the attribute {@link #identificator}.
	 * @param identificator The value for the attribute {@link #identificator}.
	 */
	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	
	/**
	 * Gets the value of the attribute {@link #value}.
	 * @return the value of the attribute {@link #value}.
	 */
	public String getValue() {
		return value;
	}

	
	/**
	 * Sets the value of the attribute {@link #value}.
	 * @param value The value for the attribute {@link #value}.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
