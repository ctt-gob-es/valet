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
 * <b>File:</b><p>es.gob.valet.dto.ConfTslRegDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/06/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.util.ArrayList;
import java.util.List;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.configuration.model.entity.ConfTslReg;

/** 
 * <p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/06/2025.
 */
public class ConfTslRegDTO {

	/**
     * Identifier for the registered TSL configuration.
     */
    private Long idConfTslReg;

    /**
     * Indicates whether TSL registration is enabled.
     * Defaults to {@code false}.
     */
    private Boolean tslRegEnabled = Boolean.FALSE;

    /**
     * Identifier representing the selected TSL registration mode.
     */
    private int idModeReg;

    /**
     * Identifier representing the selected type of TSL filter registration.
     */
    private int idTypeFilterReg;

    /**
     * List of available TSL registration modes.
     */
    private List<TslModeRegDTO> listTslModeRegDTO;

    /**
     * List of available TSL filter registration types.
     */
    private List<TslTypeFilterRegDTO> listTslTypeFilterRegDTO;

    /**
     * Default constructor.
     */
    public ConfTslRegDTO() {}

    /**
     * Constructs a new DTO based on the provided TSL configuration entity.
     *
     * @param confTslReg the {@link ConfTslReg} entity from which to copy data.
     */
    public ConfTslRegDTO(ConfTslReg confTslReg) {
        if (null != confTslReg) {
            this.idConfTslReg = confTslReg.getIdConfTslReg();
            if(null == confTslReg.getTslRegEnabled()) {
            	this.tslRegEnabled = Boolean.FALSE;
            } else {
            	this.tslRegEnabled = confTslReg.getTslRegEnabled();
            }
            if (null != confTslReg.getModeRegTokenName()) {
                this.idModeReg = Integer.valueOf(
                    confTslReg.getModeRegTokenName()
                              .substring(confTslReg.getModeRegTokenName().length() - NumberConstants.NUM1)
                );
            }
            if (null != confTslReg.getTypeFilterRegTokenName()) {
                this.idTypeFilterReg = Integer.valueOf(
                    confTslReg.getTypeFilterRegTokenName()
                              .substring(confTslReg.getTypeFilterRegTokenName().length() - NumberConstants.NUM1)
                );
            }
        }

        // Initialize the list of registration modes
        listTslModeRegDTO = new ArrayList<>();
        for (int i = 1; i <= NumberConstants.NUM3; i++) {
            TslModeRegDTO tslModeRegDTO = new TslModeRegDTO(i, "MODE_TSL_REG_" + i);
            listTslModeRegDTO.add(tslModeRegDTO);
        }

        // Initialize the list of filter registration types
        listTslTypeFilterRegDTO = new ArrayList<>();
        for (int i = 1; i <= NumberConstants.NUM2; i++) {
            TslTypeFilterRegDTO tslTypeFilterRegDTO = new TslTypeFilterRegDTO(i, "TYPE_FILTER_REG_" + i);
            listTslTypeFilterRegDTO.add(tslTypeFilterRegDTO);
        }
    }

	/**
	 * Gets the value of the attribute {@link #idConfTslReg}.
	 * @return the value of the attribute {@link #idConfTslReg}.
	 */
	public Long getIdConfTslReg() {
		return idConfTslReg;
	}

	/**
	 * Sets the value of the attribute {@link #idConfTslReg}.
	 * @param idConfTslReg The value for the attribute {@link #idConfTslReg}.
	 */
	public void setIdConfTslReg(Long idConfTslReg) {
		this.idConfTslReg = idConfTslReg;
	}

	/**
	 * Gets the value of the attribute {@link #tslRegEnabled}.
	 * @return the value of the attribute {@link #tslRegEnabled}.
	 */
	public Boolean getTslRegEnabled() {
		return tslRegEnabled;
	}

	/**
	 * Sets the value of the attribute {@link #tslRegEnabled}.
	 * @param tslRegEnabled The value for the attribute {@link #tslRegEnabled}.
	 */
	public void setTslRegEnabled(Boolean tslRegEnabled) {
		this.tslRegEnabled = tslRegEnabled;
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
	 * Gets the value of the attribute {@link #listTslModeRegDTO}.
	 * @return the value of the attribute {@link #listTslModeRegDTO}.
	 */
	public List<TslModeRegDTO> getListTslModeRegDTO() {
		return listTslModeRegDTO;
	}

	/**
	 * Sets the value of the attribute {@link #listTslModeRegDTO}.
	 * @param listTslModeRegDTO The value for the attribute {@link #listTslModeRegDTO}.
	 */
	public void setListTslModeRegDTO(List<TslModeRegDTO> listTslModeRegDTO) {
		this.listTslModeRegDTO = listTslModeRegDTO;
	}

	/**
	 * Gets the value of the attribute {@link #listTslTypeFilterRegDTO}.
	 * @return the value of the attribute {@link #listTslTypeFilterRegDTO}.
	 */
	public List<TslTypeFilterRegDTO> getListTslTypeFilterRegDTO() {
		return listTslTypeFilterRegDTO;
	}

	/**
	 * Sets the value of the attribute {@link #listTslTypeFilterRegDTO}.
	 * @param listTslTypeFilterRegDTO The value for the attribute {@link #listTslTypeFilterRegDTO}.
	 */
	public void setListTslTypeFilterRegDTO(List<TslTypeFilterRegDTO> listTslTypeFilterRegDTO) {
		this.listTslTypeFilterRegDTO = listTslTypeFilterRegDTO;
	}

}
