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
 * <b>File:</b><p>es.gob.valet.dto.TslCountryRegionMappingDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the TSL Country Region Mapping DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/03/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;

/** 
 * <p>Class that represents an object that relates the code of a to the TSL Country Region Mapping DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/03/2025.
 */
public class TslCountryRegionMappingDTO {

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslCountryRegionMapping;

	/**
	 * Attribute that represents the description for the logical mapping.
	 */
	private String mappingDescription;

	/**
	 * Attribute that represents the identificator for the logical mapping.
	 */
	private String mappingIdentificator;

	/**
	 * Attribute that represents the value for the mapping.
	 */
	private String mappingValue;

	/**
	 * Attribute that represents the association type for the mapping.
	 */
	private CAssociationTypeDTO cAssociationTypeDTO;

	/**
	 * Constructs a TslCountryRegionMappingDTO from a TslCountryRegionMapping entity.
	 * 
	 * @param tslCountryRegionMapping the TslCountryRegionMapping entity to create the DTO from
	 */
	public TslCountryRegionMappingDTO(TslCountryRegionMapping tslCountryRegionMapping) {
		idTslCountryRegionMapping = tslCountryRegionMapping.getIdTslCountryRegionMapping();
		mappingDescription = tslCountryRegionMapping.getMappingDescription();
		mappingIdentificator = tslCountryRegionMapping.getMappingIdentificator();
		mappingValue = tslCountryRegionMapping.getMappingValue();
		cAssociationTypeDTO = new CAssociationTypeDTO(tslCountryRegionMapping.getAssociationType());
	}

	/**
	 * Gets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @return the value of the attribute {@link #idTslCountryRegionMapping}.
	 */
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
	 * Gets the value of the attribute {@link #mappingDescription}.
	 * @return the value of the attribute {@link #mappingDescription}.
	 */
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
	 * Gets the value of the attribute {@link #mappingIdentificator}.
	 * @return the value of the attribute {@link #mappingIdentificator}.
	 */
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
	 * Gets the value of the attribute {@link #mappingValue}.
	 * @return the value of the attribute {@link #mappingValue}.
	 */
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
	 * Gets the value of the attribute {@link #cAssociationTypeDTO}.
	 * @return the value of the attribute {@link #cAssociationTypeDTO}.
	 */
	public CAssociationTypeDTO getcAssociationTypeDTO() {
		return cAssociationTypeDTO;
	}

	/**
	 * Sets the value of the attribute {@link #cAssociationTypeDTO}.
	 * @param cAssociationTypeDTO The value for the attribute {@link #cAssociationTypeDTO}.
	 */
	public void setcAssociationTypeDTO(CAssociationTypeDTO cAssociationTypeDTO) {
		this.cAssociationTypeDTO = cAssociationTypeDTO;
	}

}
