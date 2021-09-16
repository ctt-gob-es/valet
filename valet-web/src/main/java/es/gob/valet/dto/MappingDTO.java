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
 * <b>File:</b><p>es.gob.valet.dto.MappingDTO.java.</p>
 * <b>Description:</b><p>Class that represents each file in the mapping table associated with a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>05/04/2021.</p>
 * @author Gobierno de España.
 * @version 1.1, 16/09/2021.
 */
package es.gob.valet.dto;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;


/** 
 * <p>Class that represents each file in the mapping table associated with a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 16/09/2021.
 */
public class MappingDTO implements Comparable<MappingDTO> {


	/**
	 * Constructor method for the class MappingDTO.java.
	 * @param idTslCountryRegionMapping
	 * @param idTslCountryRegion
	 * @param mappingIdentificator
	 * @param associationType 
	 */
	public MappingDTO(Long idTslCountryRegionMapping, Long idTslCountryRegion, String mappingIdentificator, String associationType) {
		super();
		this.idTslCountryRegionMapping = idTslCountryRegionMapping;
		this.idTslCountryRegion = idTslCountryRegion;
		this.mappingIdentificator = mappingIdentificator;
		this.associationType = associationType;
	}


	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslCountryRegionMapping;

	/**
	 * Attribute that represents the TSL country region for this mapping.
	 */
	private Long idTslCountryRegion;

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
	 * Attribute that represents the association type for the mapping.
	 */
	private String associationType;

	
	/**
	 * Gets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @return the value of the attribute {@link #idTslCountryRegionMapping}.
	 */
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
	 * Gets the value of the attribute {@link #idTslCountryRegion}.
	 * @return the value of the attribute {@link #idTslCountryRegion}.
	 */
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTslCountryRegion() {
		return idTslCountryRegion;
	}

	
	/**
	 * Sets the value of the attribute {@link #idTslCountryRegion}.
	 * @param idTslCountryRegion The value for the attribute {@link #idTslCountryRegion}.
	 */
	public void setIdTslCountryRegion(Long idTslCountryRegion) {
		this.idTslCountryRegion = idTslCountryRegion;
	}

	
	/**
	 * Gets the value of the attribute {@link #mappingIdentificator}.
	 * @return the value of the attribute {@link #mappingIdentificator}.
	 */
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

	
	/**
	 * Gets the value of the attribute {@link #associationType}.
	 * @return the value of the attribute {@link #associationType}.
	 */
	@JsonView(DataTablesOutput.View.class)
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


	@Override
	public int compareTo(MappingDTO o) {
		if(idTslCountryRegionMapping < o.getIdTslCountryRegionMapping()){
			return -1;
		}
		if (idTslCountryRegionMapping > o.getIdTslCountryRegionMapping()){
			return 1;
		}
		return 0;
	}

}
