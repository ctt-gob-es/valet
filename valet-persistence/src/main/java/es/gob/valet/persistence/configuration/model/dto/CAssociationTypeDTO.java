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
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;

import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.utils.ConstantsUtils;

/** 
 * <p>Class that represents an object that relates the code of a to the Association Type DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 07/10/2022.
 */
public class CAssociationTypeDTO implements Serializable {

	/**
	 * Constant attribute that represents the serial versio UID.
	 */
	private static final long serialVersionUID = -2980946641002079483L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idAssociationType;

	/**
	 * Attribute that represents the name of the token with the description
	 * stored in properties file for internationalization.
	 */
	private String tokenName;

	/**
	 * Constructor method for the class CAssociationType.java.
	 */
	public CAssociationTypeDTO(){}
	
	/**
	 * Constructor method for the class CAssociationTypeDTO.java.
	 * 
	 * @param cAssociationType parameter that contain entity for cAssociationType.
	 */
	public CAssociationTypeDTO(CAssociationType cAssociationType) {
		this.idAssociationType = cAssociationType.getIdAssociationType();
		this.tokenName = ConstantsUtils.getConstantsValue(cAssociationType.getTokenName());
	}
	
	/**
	 * Gets the value of the attribute {@link #idAssociationType}.
	 * @return the value of the attribute {@link #idAssociationType}.
	 */
	public Long getIdAssociationType() {
		return idAssociationType;
	}

	/**
	 * Sets the value of the attribute {@link #idAssociationType}.
	 * @param idAssociationType The value for the attribute {@link #idAssociationType}.
	 */
	public void setIdAssociationType(Long idAssociationType) {
		this.idAssociationType = idAssociationType;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param tokenName The value for the attribute {@link #tokenName}.
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAssociationType == null) ? 0 : idAssociationType.hashCode());
		result = prime * result + ((tokenName == null) ? 0 : tokenName.hashCode());
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
		CAssociationTypeDTO other = (CAssociationTypeDTO) obj;
		if (idAssociationType == null) {
			if (other.idAssociationType != null)
				return false;
		} else if (!idAssociationType.equals(other.idAssociationType))
			return false;
		if (tokenName == null) {
			if (other.tokenName != null)
				return false;
		} else if (!tokenName.equals(other.tokenName))
			return false;
		return true;
	}

}
