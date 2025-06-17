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
 * <b>File:</b><p>es.gob.valet.dto.TslModeRegDTO.java.</p>
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
public class TslModeRegDTO {
	
	/**
     * Identifier of the TSL registration mode.
     */
    private int idModeReg;

    /**
     * Token name used to represent the TSL registration mode (e.g., for internal use or lookups).
     */
    private String tslModeRegTokenName;

    /**
     * Localized display value for the TSL registration mode, resolved from resource bundles.
     */
    private String tslModeRegTokenValue;

    /**
     * Constructs a new {@code TslModeRegDTO} with the given mode ID and token name.
     *
     * <p>The token value is automatically resolved using the resource key {@code MODE_TSL_REG_<id>}.</p>
     *
     * @param idModeReg the identifier of the registration mode
     * @param tslModeRegTokenName the token name associated with the registration mode
     */
    public TslModeRegDTO(int idModeReg, String tslModeRegTokenName) {
        this.idModeReg = idModeReg;
        this.tslModeRegTokenName = tslModeRegTokenName;
        this.tslModeRegTokenValue = Language.getResWebGeneral("MODE_TSL_REG_" + idModeReg);
    }

	
	/**
	 * Gets the value of the attribute {@link #idModeReg}.
	 * @return the value of the attribute {@link #idModeReg}.
	 */
	public int getIdModeReg() {
		return idModeReg;
	}

	
	/**
	 * Sets the value of the attribute {@link #idModeReg}.
	 * @param idModeReg The value for the attribute {@link #idModeReg}.
	 */
	public void setIdModeReg(int idModeReg) {
		this.idModeReg = idModeReg;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslModeRegTokenName}.
	 * @return the value of the attribute {@link #tslModeRegTokenName}.
	 */
	public String getTslModeRegTokenName() {
		return tslModeRegTokenName;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslModeRegTokenName}.
	 * @param tslModeRegTokenName The value for the attribute {@link #tslModeRegTokenName}.
	 */
	public void setTslModeRegTokenName(String tslModeRegTokenName) {
		this.tslModeRegTokenName = tslModeRegTokenName;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslModeRegTokenValue}.
	 * @return the value of the attribute {@link #tslModeRegTokenValue}.
	 */
	public String getTslModeRegTokenValue() {
		return tslModeRegTokenValue;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslModeRegTokenValue}.
	 * @param tslModeRegTokenValue The value for the attribute {@link #tslModeRegTokenValue}.
	 */
	public void setTslModeRegTokenValue(String tslModeRegTokenValue) {
		this.tslModeRegTokenValue = tslModeRegTokenValue;
	}

	
}
