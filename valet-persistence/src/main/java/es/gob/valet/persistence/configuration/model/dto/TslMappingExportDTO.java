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
 * <b>File:</b><p>es.gob.valet.dto.TslMappingExportDTO.java.</p>
 * <b>Description:</b><p>Class that represents the information needed to export a tsl service select for the user in the tree.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>13/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 13/10/2022
 */
package es.gob.valet.persistence.configuration.model.dto;

import es.gob.valet.persistence.configuration.model.entity.TslMapping;

/** 
 * <p>Class that represents the information needed to export a tsl service select for the user in the tree.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 13/10/2022
 */
public class TslMappingExportDTO {
	/**
	 * Attribute that represents the id of the logical field.
	 */
	private String logicalFieldId;

	/**
	 * Attribute that represents the logic field value.
	 */
	private String logicalFieldValue;

	/**
	 * Attribute that represents the association type DTO.
	 */
	private CAssociationTypeDTO cAssociationTypeDTO;

	/**
	 * Constructor method for the class TslMappingExportDTO.java. 
	 */
	public TslMappingExportDTO() {
	}

	/**
	 * Constructor method for the class TslMappingExportDTO.java. 
	 * 
	 * @param tslMapping parameter that contain tsl mapping obtain from BD.
	 */
	public TslMappingExportDTO(TslMapping tslMapping) {
		this.cAssociationTypeDTO = new CAssociationTypeDTO(tslMapping.getcAssociationType());
		this.logicalFieldId = tslMapping.getLogicalFieldId();
		this.logicalFieldValue = tslMapping.getLogicalFieldValue();
	}

	/**
	 * Gets the value of the attribute {@link #logicalFieldId}.
	 * @return the value of the attribute {@link #logicalFieldId}.
	 */
	public String getLogicalFieldId() {
		return logicalFieldId;
	}

	/**
	 * Sets the value of the attribute {@link #logicalFieldId}.
	 * @param logicalFieldId The value for the attribute {@link #logicalFieldId}.
	 */
	public void setLogicalFieldId(String logicalFieldId) {
		this.logicalFieldId = logicalFieldId;
	}

	/**
	 * Gets the value of the attribute {@link #logicalFieldValue}.
	 * @return the value of the attribute {@link #logicalFieldValue}.
	 */
	public String getLogicalFieldValue() {
		return logicalFieldValue;
	}

	/**
	 * Sets the value of the attribute {@link #logicalFieldValue}.
	 * @param logicalFieldValue The value for the attribute {@link #logicalFieldValue}.
	 */
	public void setLogicalFieldValue(String logicalFieldValue) {
		this.logicalFieldValue = logicalFieldValue;
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
