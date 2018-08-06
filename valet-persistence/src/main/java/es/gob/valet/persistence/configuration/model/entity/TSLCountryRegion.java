package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utilidades.NumberConstants;

@Entity
@Table(name = "TSL_COUNTRY_REGION")
public class TSLCountryRegion implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -832198836173869847L;
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTSLCountryRegion;
	
	
	/**
	 * Attribute that represents the country/region code for a TSL (ISO 3166).
	 */
	private String countryRegionCode;

	/**
	 * Attribute that represents the country/region name. 
	 */
	private String countryRegionName;

	

	
	/**
	 * Gets the value of the attribute {@link #idTSLCountryRegion}.
	 * @return the value of the attribute {@link #idTSLCountryRegion}.
	 */
	@Id
	@Column(name = "ID_COUNTRY_REGION", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTSLCountryRegion() {
		
		return idTSLCountryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #idTSLCountryRegion}.
	 * @param idTSLCountryRegionParam The value for the attribute {@link #idTSLCountryRegion}.
	 */
	public void setIdTSLCountryRegion(Long idTSLCountryRegionParam) {
		this.idTSLCountryRegion = idTSLCountryRegionParam;
	}

	/**
	 * Gets the value of the attribute {@link #countryRegionCode}.
	 * @return the value of the attribute {@link #countryRegionCode}.
	 */
	@Column(name = "COUNTRY_REGION_CODE", nullable = false, length = NumberConstants.NUM16)
	@JsonView(DataTablesOutput.View.class)
	public String getCountryRegionCode() {
		return countryRegionCode;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionCode}.
	 * @param countryRegionCodeParam The value for the attribute {@link #countryRegionCode}.
	 */
	
	public void setCountryRegionCode(String countryRegionCodeParam) {
		this.countryRegionCode = countryRegionCodeParam;
	}

	/**
	 * Gets the value of the attribute {@link #countryRegionName}.
	 * @return the value of the attribute {@link #countryRegionName}.
	 */
	@Column(name = "COUNTRY_REGION_NAME", nullable = false, length = NumberConstants.NUM128)
	@JsonView(DataTablesOutput.View.class)
	public String getCountryRegionName() {
		return countryRegionName;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionName}.
	 * @param countryRegionNameParam The value for the attribute {@link #countryRegionName}.
	 */
	public void setCountryRegionName(String countryRegionNameParam) {
		this.countryRegionName = countryRegionNameParam;
	}
	
	


	
}
