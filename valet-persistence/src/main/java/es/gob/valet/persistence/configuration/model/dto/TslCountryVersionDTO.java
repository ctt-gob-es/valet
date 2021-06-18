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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.dto.TslCountryVersionDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a country of the TSL with the registered version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>28/05/2021.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/05/2021.
 */
package es.gob.valet.persistence.configuration.model.dto;


/** 
 * <p>Class that represents an object that relates the code of a country of the TSL with the registered version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 28/05/2021.
 */
public class TslCountryVersionDTO {
	
	
	/**
	 * Attribute that represents the sequence number of this TSL.
	 */
	private Integer sequenceNumber;
	

	/**
	 * Attribute that represents the country/region code for a TSL (ISO 3166).
	 */
	private String countryRegionCode;


	
	/**
	 * Constructor method for the class TslCountryVersionDTO.java.
	 * @param sequenceNumber
	 * @param countryRegionCode 
	 */
	public TslCountryVersionDTO(Integer sequenceNumber, String countryRegionCode) {
		super();
		this.sequenceNumber = sequenceNumber;
		this.countryRegionCode = countryRegionCode;
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

}
