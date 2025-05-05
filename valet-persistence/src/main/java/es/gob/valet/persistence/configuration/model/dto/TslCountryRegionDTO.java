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
 * <b>File:</b><p>es.gob.valet.dto.TslCountryRegionDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the TSL Country Region DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/03/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.util.ArrayList;
import java.util.List;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;

/** 
 * <p>Class that represents an object that relates the code of a to the TSL Country Region DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/03/2025.
 */
public class TslCountryRegionDTO {

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslCountryRegion;

	/**
	 * Attribute that represents the country/region code for a TSL (ISO 3166).
	 */
	private String countryRegionCode;

	/**
	 * Attribute that represents the country/region name.
	 */
	private String countryRegionName;

	/**
	 * Attribute that represents the list with country region mappings.
	 */
	private List<TslCountryRegionMappingDTO> listTslCountryRegionMappingDTO;

	public TslCountryRegionDTO() {}
	
	/**
	 * Constructs a TslCountryRegionDTO from a TslCountryRegion entity.
	 * 
	 * @param tslCountryRegion the TslCountryRegion entity to create the DTO from
	 * @param loadCountryRegionMapping flag to indicate if country region mappings should be loaded
	 */
	public TslCountryRegionDTO(TslCountryRegion tslCountryRegion, boolean loadCountryRegionMapping) {
		this.idTslCountryRegion = tslCountryRegion.getIdTslCountryRegion();
		this.countryRegionCode = tslCountryRegion.getCountryRegionCode();
		this.countryRegionName = tslCountryRegion.getCountryRegionName();

		if (loadCountryRegionMapping && tslCountryRegion.getListTslCountryRegionMappings() != null) {
			listTslCountryRegionMappingDTO = new ArrayList<TslCountryRegionMappingDTO>();
			for (TslCountryRegionMapping tslCountryRegionMapping: tslCountryRegion.getListTslCountryRegionMappings()) {
				listTslCountryRegionMappingDTO.add(new TslCountryRegionMappingDTO(tslCountryRegionMapping));
			}
		}
	}

	/**
	 * Gets the value of the attribute {@link #idTslCountryRegion}.
	 * @return the value of the attribute {@link #idTslCountryRegion}.
	 */
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
	 * Gets the value of the attribute {@link #countryRegionCode}.
	 * @return the value of the attribute {@link #countryRegionCode}.
	 */
	public String getCountryRegionCode() {
		return countryRegionCode;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionCode}.
	 * @param countryRegionCode The value for the attribute {@link #countryRegionCode}.
	 */
	public void setCountryRegionCode(String countryRegionCode) {
		this.countryRegionCode = countryRegionCode;
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
	 * Gets the value of the attribute {@link #listTslCountryRegionMappingDTO}.
	 * @return the value of the attribute {@link #listTslCountryRegionMappingDTO}.
	 */
	public List<TslCountryRegionMappingDTO> getListTslCountryRegionMappingDTO() {
		return listTslCountryRegionMappingDTO;
	}

	/**
	 * Sets the value of the attribute {@link #listTslCountryRegionMappingDTO}.
	 * @param listTslCountryRegionMappingDTO The value for the attribute {@link #listTslCountryRegionMappingDTO}.
	 */
	public void setListTslCountryRegionMappingDTO(List<TslCountryRegionMappingDTO> listTslCountryRegionMappingDTO) {
		this.listTslCountryRegionMappingDTO = listTslCountryRegionMappingDTO;
	}

}
