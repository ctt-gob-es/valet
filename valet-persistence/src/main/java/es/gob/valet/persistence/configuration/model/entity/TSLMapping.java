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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslMapping.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_MAPPING</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.configuration.model.dto.CAssociationTypeDTO;
import es.gob.valet.persistence.configuration.model.dto.LogicalFieldDTO;
import es.gob.valet.persistence.configuration.model.dto.TslServiceDTO;

/**
 * <p>Class that maps the <i>TSL_MAPPING</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.1, 07/10/2022.
 */
@Entity
@Table(name = "TSL_MAPPING")
public class TslMapping implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = -5150100957528946006L;

	/**
	 * Attribute that represents constant to id service.
	 */
	private static final String ID_SERVICE = "ID_SERVICE";

	/**
	 * Attribute that represents constant to id logical field.
	 */
	private static final String ID_LOGICAL_FIELD = "ID_LOGICAL_FIELD";

	/**
	 * Attribute that represents constant to id association type.
	 */
	private static final String ID_ASSOCIATION_TYPE = "ID_ASSOCIATION_TYPE";

	/**
	 * Attribute that represents the pk.
	 */
	@Id
	@Column(name = "ID_TSL_MAPPING", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_mapping")
	@GenericGenerator(name = "sq_tsl_mapping", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_MAPPING"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long idTslMapping;

	/**
	 * Attribute that represents the entity to tsl service.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ID_SERVICE, nullable = false)
	private TslService tslService;

	/**
	 * Attribute that represents the entity to logical field.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ID_LOGICAL_FIELD, nullable = false)
	private LogicalField logicalField;
	
	/**
	 * Attribute that represents the entity to associatiion type.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ID_ASSOCIATION_TYPE, nullable = false)
	private CAssociationType cAssociationType;

	/**
	 * Constructor method for the class LogicalField.java.
	 */
	public TslMapping(){}
	
	/**
	 * Constructor method for the class LogicalField.java.
	 * 
	 * @param tslServiceDTO parameter that contain tsl service DTO to transform a entity.
	 * @param logicalFieldDTO parameter that contain logical field DTO to transform a entity.
	 * @param cAssociationTypeDTO parameter that contain association type DTO to transform a entity.
	 */
	public TslMapping(TslServiceDTO tslServiceDTO, LogicalFieldDTO logicalFieldDTO, CAssociationTypeDTO cAssociationTypeDTO) {
		this.logicalField = new LogicalField(logicalFieldDTO);
		this.cAssociationType = new CAssociationType(cAssociationTypeDTO);
		this.tslService = new TslService(tslServiceDTO);
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
	 * Gets the value of the attribute {@link #tslService}.
	 * @return the value of the attribute {@link #tslService}.
	 */
	public TslService getTslService() {
		return tslService;
	}

	/**
	 * Sets the value of the attribute {@link #tslService}.
	 * @param tslService The value for the attribute {@link #tslService}.
	 */
	public void setTslService(TslService tslService) {
		this.tslService = tslService;
	}

	/**
	 * Gets the value of the attribute {@link #logicalField}.
	 * @return the value of the attribute {@link #logicalField}.
	 */
	public LogicalField getLogicalField() {
		return logicalField;
	}

	/**
	 * Sets the value of the attribute {@link #logicalField}.
	 * @param logicalField The value for the attribute {@link #logicalField}.
	 */
	public void setLogicalField(LogicalField logicalField) {
		this.logicalField = logicalField;
	}

	/**
	 * Gets the value of the attribute {@link #cAssociationType}.
	 * @return the value of the attribute {@link #cAssociationType}.
	 */
	public CAssociationType getcAssociationType() {
		return cAssociationType;
	}

	/**
	 * Sets the value of the attribute {@link #cAssociationType}.
	 * @param cAssociationType The value for the attribute {@link #cAssociationType}.
	 */
	public void setcAssociationType(CAssociationType cAssociationType) {
		this.cAssociationType = cAssociationType;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cAssociationType == null) ? 0 : cAssociationType.hashCode());
		result = prime * result + ((idTslMapping == null) ? 0 : idTslMapping.hashCode());
		result = prime * result + ((logicalField == null) ? 0 : logicalField.hashCode());
		result = prime * result + ((tslService == null) ? 0 : tslService.hashCode());
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
		TslMapping other = (TslMapping) obj;
		if (cAssociationType == null) {
			if (other.cAssociationType != null)
				return false;
		} else if (!cAssociationType.equals(other.cAssociationType))
			return false;
		if (idTslMapping == null) {
			if (other.idTslMapping != null)
				return false;
		} else if (!idTslMapping.equals(other.idTslMapping))
			return false;
		if (logicalField == null) {
			if (other.logicalField != null)
				return false;
		} else if (!logicalField.equals(other.logicalField))
			return false;
		if (tslService == null) {
			if (other.tslService != null)
				return false;
		} else if (!tslService.equals(other.tslService))
			return false;
		return true;
	}

}
