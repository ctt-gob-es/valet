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
 * <b>File:</b><p>es.gob.valet.dto.TslTypeFilterRegDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/06/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import es.gob.valet.i18n.Language;

/** 
 * <p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/06/2025.
 */
public class TslTypeFilterRegDTO {

	/**
     * Identifier of the type filter registration.
     */
    private int idTypeFilterReg;

    /**
     * Token name used to represent the type filter registration (e.g., for internal mapping or configuration).
     */
    private String typeFilterRegTokenName;

    /**
     * Localized display value for the type filter registration, resolved from resource bundles.
     */
    private String typeFilterRegTokenValue;

    /**
     * Constructs a new {@code TslTypeFilterRegDTO} with the given type filter ID and token name.
     *
     * <p>The token value is automatically resolved using the resource key {@code TYPE_FILTER_REG_<id>}.</p>
     *
     * @param idTypeFilterReg the identifier of the filter registration type
     * @param typeFilterRegTokenName the token name associated with the filter registration type
     */
    public TslTypeFilterRegDTO(int idTypeFilterReg, String typeFilterRegTokenName) {
        this.idTypeFilterReg = idTypeFilterReg;
        this.typeFilterRegTokenName = typeFilterRegTokenName;
        this.typeFilterRegTokenValue = Language.getResWebGeneral("TYPE_FILTER_REG_" + idTypeFilterReg);
    }

	/**
	 * Gets the value of the attribute {@link #idTypeFilterReg}.
	 * @return the value of the attribute {@link #idTypeFilterReg}.
	 */
	public int getIdTypeFilterReg() {
		return idTypeFilterReg;
	}

	/**
	 * Sets the value of the attribute {@link #idTypeFilterReg}.
	 * @param idTypeFilterReg The value for the attribute {@link #idTypeFilterReg}.
	 */
	public void setIdTypeFilterReg(int idTypeFilterReg) {
		this.idTypeFilterReg = idTypeFilterReg;
	}

	/**
	 * Gets the value of the attribute {@link #typeFilterRegTokenName}.
	 * @return the value of the attribute {@link #typeFilterRegTokenName}.
	 */
	public String getTypeFilterRegTokenName() {
		return typeFilterRegTokenName;
	}

	/**
	 * Sets the value of the attribute {@link #typeFilterRegTokenName}.
	 * @param typeFilterRegTokenName The value for the attribute {@link #typeFilterRegTokenName}.
	 */
	public void setTypeFilterRegTokenName(String typeFilterRegTokenName) {
		this.typeFilterRegTokenName = typeFilterRegTokenName;
	}

	/**
	 * Gets the value of the attribute {@link #typeFilterRegTokenValue}.
	 * @return the value of the attribute {@link #typeFilterRegTokenValue}.
	 */
	public String getTypeFilterRegTokenValue() {
		return typeFilterRegTokenValue;
	}

	/**
	 * Sets the value of the attribute {@link #typeFilterRegTokenValue}.
	 * @param typeFilterRegTokenValue The value for the attribute {@link #typeFilterRegTokenValue}.
	 */
	public void setTypeFilterRegTokenValue(String typeFilterRegTokenValue) {
		this.typeFilterRegTokenValue = typeFilterRegTokenValue;
	}

}
