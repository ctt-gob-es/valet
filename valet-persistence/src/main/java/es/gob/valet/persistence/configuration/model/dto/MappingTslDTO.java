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
 * @version 1.1, 11/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.util.List;

import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.entity.TslMapping;

/** 
 * <p>Class that represents an object that relates the code of a to the Mapping TSL DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 11/10/2022.
 */
public class MappingTslDTO implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 2501269410550865226L;

	
	private Long idTslMapping;
	
	/**
	 * Attribute that represents the tsl service DTO.
	 */
	private TslServiceDTO tslServiceDTO;
	
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
	 * Attribute that represents list with association type DTO.
	 */
	private List<CAssociationTypeDTO> listCAssociationTypeDTO;

	/**
	 * Attribute that represents list with values to asscociation type simple DTO.
	 */
	private List<ConstantsDTO> listValuesAssocSimple;
	
	/**
	 * Attribute that represents the action realized for the user.
	 */
	private String action;
	
	/**
	 * Attribute that represents the action to add mapping.
	 */
	public static final String ADD = "add";
	
	/**
	 * Attribute that represents the action to merge mapping.
	 */
	public static final String MERGE = "merge";
	
	/**
	 * Attribute that represents the identificator auxiliar of logical field.
	 */
	private String logicalFieldIdAux;

	/**
	 * Constructor method for the class MappingTslDTO.java.
	 */
	public MappingTslDTO() {
		this.tslServiceDTO = new TslServiceDTO();
		this.cAssociationTypeDTO = new CAssociationTypeDTO();
	}
	
	/**
	 * Constructor method for the class MappingTslDTO.java.
	 * 
	 * @param tslMapping parameter that contain tsl mapping dto.
	 * @throws CommonUtilsException If the method fails.
	 */
	public MappingTslDTO(TslMapping tslMapping) throws CommonUtilsException {
		this.idTslMapping = tslMapping.getIdTslMapping();
		this.tslServiceDTO = new TslServiceDTO(tslMapping.getTslService());
		this.cAssociationTypeDTO = new CAssociationTypeDTO(tslMapping.getcAssociationType());
		this.logicalFieldId = tslMapping.getLogicalFieldId();
		this.logicalFieldIdAux = tslMapping.getLogicalFieldId();
		this.logicalFieldValue = tslMapping.getLogicalFieldValue();
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
	 * Gets the value of the attribute {@link #logicalFieldId}.
	 * @return the value of the attribute {@link #logicalFieldId}.
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
	 * Gets the value of the attribute {@link #action}.
	 * @return the value of the attribute {@link #action}.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the value of the attribute {@link #action}.
	 * @param action The value for the attribute {@link #action}.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * Gets the value of the attribute {@link #idTslMapping}.
	 * @return the value of the attribute {@link #idTslMapping}.
	 */
	public Long getIdTslMapping() {
		return idTslMapping;
	}

	/**
	 * Sets the value of the attribute {@link #idTslMapping}.
	 * @param idTslMapping The value for the attribute {@link #idTslMapping}.
	 */
	public void setIdTslMapping(Long idTslMapping) {
		this.idTslMapping = idTslMapping;
	}
	
	/**
	 * Gets the value of the attribute {@link #logicalFieldIdAux}.
	 * @return the value of the attribute {@link #logicalFieldIdAux}.
	 */
	public String getLogicalFieldIdAux() {
		return logicalFieldIdAux;
	}

	/**
	 * Sets the value of the attribute {@link #logicalFieldIdAux}.
	 * @param logicalFieldIdAux The value for the attribute {@link #logicalFieldIdAux}.
	 */
	public void setLogicalFieldIdAux(String logicalFieldIdAux) {
		this.logicalFieldIdAux = logicalFieldIdAux;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((cAssociationTypeDTO == null) ? 0 : cAssociationTypeDTO.hashCode());
		result = prime * result + ((idTslMapping == null) ? 0 : idTslMapping.hashCode());
		result = prime * result + ((listCAssociationTypeDTO == null) ? 0 : listCAssociationTypeDTO.hashCode());
		result = prime * result + ((listValuesAssocSimple == null) ? 0 : listValuesAssocSimple.hashCode());
		result = prime * result + ((logicalFieldId == null) ? 0 : logicalFieldId.hashCode());
		result = prime * result + ((logicalFieldIdAux == null) ? 0 : logicalFieldIdAux.hashCode());
		result = prime * result + ((logicalFieldValue == null) ? 0 : logicalFieldValue.hashCode());
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
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (cAssociationTypeDTO == null) {
			if (other.cAssociationTypeDTO != null)
				return false;
		} else if (!cAssociationTypeDTO.equals(other.cAssociationTypeDTO))
			return false;
		if (idTslMapping == null) {
			if (other.idTslMapping != null)
				return false;
		} else if (!idTslMapping.equals(other.idTslMapping))
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
		if (logicalFieldId == null) {
			if (other.logicalFieldId != null)
				return false;
		} else if (!logicalFieldId.equals(other.logicalFieldId))
			return false;
		if (logicalFieldIdAux == null) {
			if (other.logicalFieldIdAux != null)
				return false;
		} else if (!logicalFieldIdAux.equals(other.logicalFieldIdAux))
			return false;
		if (logicalFieldValue == null) {
			if (other.logicalFieldValue != null)
				return false;
		} else if (!logicalFieldValue.equals(other.logicalFieldValue))
			return false;
		if (tslServiceDTO == null) {
			if (other.tslServiceDTO != null)
				return false;
		} else if (!tslServiceDTO.equals(other.tslServiceDTO))
			return false;
		return true;
	}

}
