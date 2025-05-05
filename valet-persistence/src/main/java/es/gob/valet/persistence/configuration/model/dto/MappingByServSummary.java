package es.gob.valet.persistence.configuration.model.dto;


public class MappingByServSummary {

	private String country;
	
	private String nameTsp;
	
	private String nameService;
	
	private String associationType;
	
	private String idLogicalField;
	
	private String valueLogicalField;

	public MappingByServSummary(String country, String nameTsp, String nameService, String associationType, String idLogicalField, String valueLogicalField) {
		this.country = country;
		this.nameTsp = nameTsp;
		this.nameService = nameService;
		this.associationType = associationType;
		this.idLogicalField = idLogicalField;
		this.valueLogicalField = valueLogicalField;
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
	 * Gets the value of the attribute {@link #nameTsp}.
	 * @return the value of the attribute {@link #nameTsp}.
	 */
	public String getNameTsp() {
		return nameTsp;
	}

	
	/**
	 * Sets the value of the attribute {@link #nameTsp}.
	 * @param nameTsp The value for the attribute {@link #nameTsp}.
	 */
	public void setNameTsp(String nameTsp) {
		this.nameTsp = nameTsp;
	}

	
	/**
	 * Gets the value of the attribute {@link #nameService}.
	 * @return the value of the attribute {@link #nameService}.
	 */
	public String getNameService() {
		return nameService;
	}

	
	/**
	 * Sets the value of the attribute {@link #nameService}.
	 * @param nameService The value for the attribute {@link #nameService}.
	 */
	public void setNameService(String nameService) {
		this.nameService = nameService;
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
	 * Gets the value of the attribute {@link #idLogicalField}.
	 * @return the value of the attribute {@link #idLogicalField}.
	 */
	public String getIdLogicalField() {
		return idLogicalField;
	}

	
	/**
	 * Sets the value of the attribute {@link #idLogicalField}.
	 * @param idLogicalField The value for the attribute {@link #idLogicalField}.
	 */
	public void setIdLogicalField(String idLogicalField) {
		this.idLogicalField = idLogicalField;
	}

	
	/**
	 * Gets the value of the attribute {@link #valueLogicalField}.
	 * @return the value of the attribute {@link #valueLogicalField}.
	 */
	public String getValueLogicalField() {
		return valueLogicalField;
	}

	
	/**
	 * Sets the value of the attribute {@link #valueLogicalField}.
	 * @param valueLogicalField The value for the attribute {@link #valueLogicalField}.
	 */
	public void setValueLogicalField(String valueLogicalField) {
		this.valueLogicalField = valueLogicalField;
	}
	
	
	
}
