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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.dto.MappingByTslSummary.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the mapping by TSL for summary TSLs DTO.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/05/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the mapping by service for summary TSLs DTO.</p>
 * <b>Project:</b><p>Class that represents an object that relates the code of a to the mapping by TSL for summary TSLs DTO.</p>
 * @version 1.1, 06/05/2025.
 */
public class MappingByTslSummary {
	
	/** The country related to the mapping */
    private String country;

    /** The type of association defined for the mapping */
    private String associationType;

    /** The logical identifier of the mapping */
    private String identificator;

    /** The value associated with the logical identifier */
    private String value;

    /**
     * Constructs a new {@code MappingByTslSummary} instance with the specified details.
     *
     * @param country the country associated with the mapping
     * @param associationType the type of association used
     * @param identificator the logical field identifier
     * @param value the value corresponding to the logical field
     */
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
