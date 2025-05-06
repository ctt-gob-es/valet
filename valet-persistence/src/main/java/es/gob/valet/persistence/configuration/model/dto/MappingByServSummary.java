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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.dto.MappingByServSummary.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the mapping by service for summary TSLs DTO.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/05/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the mapping by service for summary TSLs DTO.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/05/2025.
 */
public class MappingByServSummary {

	 /** The country related to the mapping */
    private String country;

    /** The name of the Trust Service Provider (TSP) */
    private String nameTsp;

    /** The name of the service associated with the mapping */
    private String nameService;

    /** The type of association for the mapping */
    private String associationType;

    /** The ID of the logical field in the mapping */
    private String idLogicalField;

    /** The value of the logical field in the mapping */
    private String valueLogicalField;

    /**
     * Constructs a new MappingByServSummary with the specified details.
     * 
     * @param country the country related to the mapping
     * @param nameTsp the name of the TSP (Trust Service Provider)
     * @param nameService the name of the service
     * @param associationType the type of association for the mapping
     * @param idLogicalField the ID of the logical field in the mapping
     * @param valueLogicalField the value of the logical field in the mapping
     */
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
