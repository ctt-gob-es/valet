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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.LogicalField.java.</p>
 * <b>Description:</b><p>Class that maps the <i>LOGICAL_FIELD</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 7/10/2022.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.configuration.model.dto.LogicalFieldDTO;

/**
 * <p>Class that maps the <i>LOGICAL_FIELD</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 7/10/2022.
 */
@Entity
@Table(name = "LOGICAL_FIELD")
public class LogicalField implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 5007734727778391567L;

	/**
	 * Attribute that represents the object ID.
	 */
	@Id
	@Column(name = "ID_LOGICAL_FIELD", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_logical_field")
	@GenericGenerator(name = "sq_logical_field", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SQ_LOGICAL_FIELD"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long idLogicalField;

	/**
	 * Attribute that represents the description of the identificator.
	 */
	@Column(name = "IDENTIFICATOR", nullable = false, length = NumberConstants.NUM255)
	private String identificator;
	
	/**
	 * Attribute that represents the description of the logical field.
	 */
	@OneToMany(mappedBy = "logicalField", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	private List<TslMapping> tslMapping;
	
	/**
	 * Attribute that represents the value of the logical field.
	 */
	@Column(name = "LOGICAL_FIELD_VALUE", nullable = false, length = NumberConstants.NUM256)
	private String logicalFieldValue;
	
	/**
	 * Constructor method for the class LogicalField.java.
	 */
	public LogicalField(){}
	
	/**
	 * Constructor method for the class LogicalField.java.
	 * 
	 * @param logicalFieldDTO parameter that contain logical field DTO to transform a entity.
	 */
	public LogicalField(LogicalFieldDTO logicalFieldDTO) {
		this.idLogicalField = logicalFieldDTO.getIdLogicalField();
		this.identificator = logicalFieldDTO.getIdentificator();
		this.logicalFieldValue = logicalFieldDTO.getLogicalFieldValue();
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
	 * @param idLogicalField The value for the attribute {@link #identificator}.
	 */
	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	/**
	 * Gets the value of the attribute {@link #tslMapping}.
	 * @return the value of the attribute {@link #tslMapping}.
	 */
	public List<TslMapping> getTslMapping() {
		return tslMapping;
	}

	/**
	 * Sets the value of the attribute {@link #tslMapping}.
	 * @param tslMapping The value for the attribute {@link #tslMapping}.
	 */
	public void setTslMapping(List<TslMapping> tslMapping) {
		this.tslMapping = tslMapping;
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
		result = prime * result + ((tslMapping == null) ? 0 : tslMapping.hashCode());
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
		LogicalField other = (LogicalField) obj;
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
		if (tslMapping == null) {
			if (other.tslMapping != null)
				return false;
		} else if (!tslMapping.equals(other.tslMapping))
			return false;
		return true;
	}

}
