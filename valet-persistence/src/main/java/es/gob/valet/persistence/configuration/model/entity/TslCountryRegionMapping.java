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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping.java.</p>
 * <b>Description:</b><p> Class that represents the representation of the <i>TSL_COUNTRY_REGION_MAPPING</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>8 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 8 ago. 2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utilidades.NumberConstants;

/** 
 * <p>Class that represents the representation of the <i>TSL_COUNTRY_REGION_MAPPING</i> database table as a Plain Old Java Object</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 8 ago. 2018.
 */
@Entity
@Table(name = "TSL_COUNTRY_REGION_MAPPING")
public class TslCountryRegionMapping implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 8746633965104933856L;
	

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslCountryRegionMapping;
	
	/**
	 * Attribute that represents the TSL country region for this mapping. 
	 */
	private TslCountryRegion tslCountryRegion;

	/**
	 * Attribute that represents the identificator for the logical mapping.
	 */
	private String mappingIdentificator;

	/**
	 * Attribute that represents the description for the logical mapping. 
	 */
	private String mappingDescription;
	
	/**
	 * Attribute that represents the value for the mapping. 
	 */
	private String mappingValue;

	
	/**
	 * Gets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @return the value of the attribute {@link #idTslCountryRegionMapping}.
	 */
	@Id
	@Column(name = "ID_TSL_COUNTRY_REGION_MAPPING", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_country_region_mapping")
	@GenericGenerator(name = "sq_tsl_country_region_mapping", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_COUNTRY_REGION_MAPPING"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTslCountryRegionMapping() {
		return idTslCountryRegionMapping;
	}

	
	/**
	 * Sets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @param idTslCountryRegionMapping The value for the attribute {@link #idTslCountryRegionMapping}.
	 */
	public void setIdTslCountryRegionMapping(Long idTslCountryRegionMapping) {
		this.idTslCountryRegionMapping = idTslCountryRegionMapping;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslCountryRegion}.
	 * @return the value of the attribute {@link #tslCountryRegion}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COUNTRY_REGION")
	@JsonView(DataTablesOutput.View.class)
	public TslCountryRegion getTslCountryRegion() {
		return tslCountryRegion;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslCountryRegion}.
	 * @param tslCountryRegion The value for the attribute {@link #tslCountryRegion}.
	 */
	public void setTslCountryRegion(TslCountryRegion tslCountryRegion) {
		this.tslCountryRegion = tslCountryRegion;
	}

	
	/**
	 * Gets the value of the attribute {@link #mappingIdentificator}.
	 * @return the value of the attribute {@link #mappingIdentificator}.
	 */
	@Column(name = "MAPPING_IDENTIFICATOR", nullable = false, length = NumberConstants.NUM30)
	@JsonView(DataTablesOutput.View.class)
	public String getMappingIdentificator() {
		return mappingIdentificator;
	}

	
	/**
	 * Sets the value of the attribute {@link #mappingIdentificator}.
	 * @param mappingIdentificator The value for the attribute {@link #mappingIdentificator}.
	 */
	public void setMappingIdentificator(String mappingIdentificator) {
		this.mappingIdentificator = mappingIdentificator;
	}

	
	/**
	 * Gets the value of the attribute {@link #mappingDescription}.
	 * @return the value of the attribute {@link #mappingDescription}.
	 */
	@Column(name = "MAPPING_DESCRIPTION", nullable = true, length = NumberConstants.NUM255)
	@JsonView(DataTablesOutput.View.class)
	public String getMappingDescription() {
		return mappingDescription;
	}

	
	/**
	 * Sets the value of the attribute {@link #mappingDescription}.
	 * @param mappingDescription The value for the attribute {@link #mappingDescription}.
	 */
	public void setMappingDescription(String mappingDescription) {
		this.mappingDescription = mappingDescription;
	}

	
	/**
	 * Gets the value of the attribute {@link #mappingValue}.
	 * @return the value of the attribute {@link #mappingValue}.
	 */
	@Column(name = "MAPPING_VALUE", nullable = false, length = NumberConstants.NUM500)
	@JsonView(DataTablesOutput.View.class)
	public String getMappingValue() {
		return mappingValue;
	}

	
	/**
	 * Sets the value of the attribute {@link #mappingValue}.
	 * @param mappingValue The value for the attribute {@link #mappingValue}.
	 */
	public void setMappingValue(String mappingValue) {
		this.mappingValue = mappingValue;
	}


	
	

}
