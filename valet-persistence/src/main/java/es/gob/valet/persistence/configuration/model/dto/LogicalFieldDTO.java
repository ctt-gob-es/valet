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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.dto.LogicalFieldDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Logical Field DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.2, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;

import es.gob.valet.persistence.configuration.model.entity.LogicalField;

/** 
 * <p>Class that represents an object that relates the code of a to the Logical Field DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 07/10/2022.
 */
public class LogicalFieldDTO implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 4257343227542189744L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idLogicalField;

	/**
	 * Attribute that represents the description of the logical field.
	 */
	private String identificator;
	
	/**
	 * Attribute that represents the logic field value.
	 */
	private String logicalFieldValue;
	
	/**
	 * Constructor method for the class LogicalFieldDTO.java.
	 */
	public LogicalFieldDTO() {}
	
	/**
	 * Constructor method for the class LogicalFieldDTO.java.
	 * 
	 * @param logicalField parameter that contain logical field obtain from BD.
	 */
	public LogicalFieldDTO(LogicalField logicalField) {
		this.idLogicalField = logicalField.getIdLogicalField();
		this.identificator = logicalField.getIdentificator();
		this.logicalFieldValue = logicalField.getLogicalFieldValue();
	}

	/**
	 * Gets the value of the attribute {@link #idLogicalField}.
	 * @return the value of the attribute {@link #idLogicalField}.
	 */
	public Long getIdLogicalField() {
		return idLogicalField;
	}

	/**
	 * Sets the value of the attribute {@link #idLogicalField}.
	 * @param idLogicalField The value for the attribute {@link #idLogicalField}.
	 */
	public void setIdLogicalField(Long idLogicalField) {
		this.idLogicalField = idLogicalField;
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
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLogicalField == null) ? 0 : idLogicalField.hashCode());
		result = prime * result + ((identificator == null) ? 0 : identificator.hashCode());
		result = prime * result + ((logicalFieldValue == null) ? 0 : logicalFieldValue.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogicalFieldDTO other = (LogicalFieldDTO) obj;
		if (idLogicalField == null) {
			if (other.idLogicalField != null)
				return false;
		} else if (!idLogicalField.equals(other.idLogicalField))
			return false;
		if (identificator == null) {
			if (other.identificator != null)
				return false;
		} else if (!identificator.equals(other.identificator))
			return false;
		if (logicalFieldValue == null) {
			if (other.logicalFieldValue != null)
				return false;
		} else if (!logicalFieldValue.equals(other.logicalFieldValue))
			return false;
		return true;
	}

}
