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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.dto.MappingTslDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Mapping TSL DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.util.List;

/** 
 * <p>Class that represents an object that relates the code of a to the Mapping TSL DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 07/10/2022.
 */
public class MappingTslDTO implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 2501269410550865226L;

	/**
	 * Attribute that represents the logical field DTO.
	 */
	private LogicalFieldDTO logicalFieldDTO;
	
	/**
	 * Attribute that represents the tsl service DTO.
	 */
	private TslServiceDTO tslServiceDTO;

	/**
	 * Attribute that represents the association type DTO.
	 */
	private CAssociationTypeDTO cAssociationTypeDTO;

	/**
	 * Attribute that represents list with association type DTO.
	 */
	private List<CAssociationTypeDTO> listCAssociationTypeDTO;

	/**
	 * Attribute that represents list with values to asscociation type simple DTO.
	 */
	private List<ConstantsDTO> listValuesAssocSimple;

	/**
	 * Constructor method for the class MappingTslDTO.java.
	 */
	public MappingTslDTO() {
		this.logicalFieldDTO = new LogicalFieldDTO();
		this.tslServiceDTO = new TslServiceDTO();
		this.cAssociationTypeDTO = new CAssociationTypeDTO();
	}

	/**
	 * Gets the value of the attribute {@link #logicalFieldDTO}.
	 * @return the value of the attribute {@link #logicalFieldDTO}.
	 */
	public LogicalFieldDTO getLogicalFieldDTO() {
		return logicalFieldDTO;
	}

	/**
	 * Sets the value of the attribute {@link #logicalFieldDTO}.
	 * @param logicalFieldDTO The value for the attribute {@link #logicalFieldDTO}.
	 */
	public void setLogicalFieldDTO(LogicalFieldDTO logicalFieldDTO) {
		this.logicalFieldDTO = logicalFieldDTO;
	}

	/**
	 * Gets the value of the attribute {@link #tslServiceDTO}.
	 * @return the value of the attribute {@link #tslServiceDTO}.
	 */
	public TslServiceDTO getTslServiceDTO() {
		return tslServiceDTO;
	}

	/**
	 * Sets the value of the attribute {@link #tslServiceDTO}.
	 * @param tslServiceDTO The value for the attribute {@link #tslServiceDTO}.
	 */
	public void setTslServiceDTO(TslServiceDTO tslServiceDTO) {
		this.tslServiceDTO = tslServiceDTO;
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

	/**
	 * Gets the value of the attribute {@link #listCAssociationTypeDTO}.
	 * @return the value of the attribute {@link #listCAssociationTypeDTO}.
	 */
	public List<CAssociationTypeDTO> getListCAssociationTypeDTO() {
		return listCAssociationTypeDTO;
	}

	/**
	 * Sets the value of the attribute {@link #listCAssociationTypeDTO}.
	 * @param listCAssociationTypeDTO The value for the attribute {@link #listCAssociationTypeDTO}.
	 */
	public void setListCAssociationTypeDTO(List<CAssociationTypeDTO> listCAssociationTypeDTO) {
		this.listCAssociationTypeDTO = listCAssociationTypeDTO;
	}

	/**
	 * Gets the value of the attribute {@link #listValuesAssocSimple}.
	 * @return the value of the attribute {@link #listValuesAssocSimple}.
	 */
	public List<ConstantsDTO> getListValuesAssocSimple() {
		return listValuesAssocSimple;
	}

	/**
	 * Sets the value of the attribute {@link #listValuesAssocSimple}.
	 * @param listValuesAssocSimple The value for the attribute {@link #listValuesAssocSimple}.
	 */
	public void setListValuesAssocSimple(List<ConstantsDTO> listValuesAssocSimple) {
		this.listValuesAssocSimple = listValuesAssocSimple;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cAssociationTypeDTO == null) ? 0 : cAssociationTypeDTO.hashCode());
		result = prime * result + ((listCAssociationTypeDTO == null) ? 0 : listCAssociationTypeDTO.hashCode());
		result = prime * result + ((listValuesAssocSimple == null) ? 0 : listValuesAssocSimple.hashCode());
		result = prime * result + ((logicalFieldDTO == null) ? 0 : logicalFieldDTO.hashCode());
		result = prime * result + ((tslServiceDTO == null) ? 0 : tslServiceDTO.hashCode());
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
		MappingTslDTO other = (MappingTslDTO) obj;
		if (cAssociationTypeDTO == null) {
			if (other.cAssociationTypeDTO != null)
				return false;
		} else if (!cAssociationTypeDTO.equals(other.cAssociationTypeDTO))
			return false;
		if (listCAssociationTypeDTO == null) {
			if (other.listCAssociationTypeDTO != null)
				return false;
		} else if (!listCAssociationTypeDTO.equals(other.listCAssociationTypeDTO))
			return false;
		if (listValuesAssocSimple == null) {
			if (other.listValuesAssocSimple != null)
				return false;
		} else if (!listValuesAssocSimple.equals(other.listValuesAssocSimple))
			return false;
		if (logicalFieldDTO == null) {
			if (other.logicalFieldDTO != null)
				return false;
		} else if (!logicalFieldDTO.equals(other.logicalFieldDTO))
			return false;
		if (tslServiceDTO == null) {
			if (other.tslServiceDTO != null)
				return false;
		} else if (!tslServiceDTO.equals(other.tslServiceDTO))
			return false;
		return true;
	}

}
