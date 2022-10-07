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
 * <b>File:</b><p>es.gob.valet.dto.CAssociationTypeDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Constants DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;

/** 
 * <p>Class that represents an object that relates the code of a to the Constants DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 07/10/2022.
 */
public class ConstantsDTO implements Serializable {
	/**
	 * Constant attribute that represents the serial versio UID.
	 */
	private static final long serialVersionUID = -8306883476522638725L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idConstant;

	/**
	 * Attribute that represents the value of logic field.
	 */
	private String value;

	/**
	 * Constructor method for the class ConstantsDTO.java. 
	 */
	public ConstantsDTO() {
		super();
	}
	
	/**
	 * Constructor method for the class ConstantsDTO.java.
	 * @param idConstantParam Constant id from db;
	 * @param valueParam  Constant value
	 */
	public ConstantsDTO(Long idConstantParam, String valueParam) {
		this.idConstant = idConstantParam;
		this.value = valueParam;
	}
	
	/**
	 * Gets the value of the attribute {@link #idConstant}.
	 * @return the value of the attribute {@link #idConstant}.
	 */
	public Long getIdConstant() {
		return idConstant;
	}

		
	/**
	 * Gets the value of the attribute {@link #value}.
	 * @return the value of the attribute {@link #value}.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConstant == null) ? 0 : idConstant.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ConstantsDTO other = (ConstantsDTO) obj;
		if (idConstant == null) {
			if (other.idConstant != null)
				return false;
		} else if (!idConstant.equals(other.idConstant))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
