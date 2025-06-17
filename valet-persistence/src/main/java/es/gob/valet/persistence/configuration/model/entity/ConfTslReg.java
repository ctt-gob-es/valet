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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.ConfTslReg.java.</p>
 * <b>Description:</b><p>Class that maps the <i>CONF_TSL_REGISTRATION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/06/2025.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>CONF_TSL_REGISTRATION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/06/2025.
 */
@Entity
@Table(name = "CONF_TSL_REGISTRATION")
public class ConfTslReg implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = -4032507659067640666L;

	/**
	 * Identifier of the TSL configuration entity.
	 *
	 * <p>Mapped to the database column {@code ID_CONF_TSL_REGISTRATION} and generated using a sequence.</p>
	 */
	@Id
	@Column(name = "ID_CONF_TSL_REGISTRATION", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_conf_tsl_registration")
	@GenericGenerator(
	    name = "sq_conf_tsl_registration",
	    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	    parameters = {
	        @Parameter(name = "sequence_name", value = "SQ_CONF_TSL_REGISTRATION"),
	        @Parameter(name = "initial_value", value = "1"),
	        @Parameter(name = "increment_size", value = "1")
	    }
	)
	private Long idConfTslReg;

	/**
	 * Indicates whether the TSL registration is enabled.
	 *
	 * <p>Mapped to the database column {@code TSL_REG_ENABLED} using Hibernate's {@code yes_no} Boolean mapping.</p>
	 */
	@Column(name = "TSL_REG_ENABLED", nullable = true, precision = NumberConstants.NUM1)
	@Type(type = "yes_no")
	private Boolean tslRegEnabled;

	/**
	 * Token name representing the selected TSL registration mode.
	 *
	 * <p>Mapped to the database column {@code MODE_REG_TOKEN_NAME}.</p>
	 */
	@Column(name = "MODE_REG_TOKEN_NAME", nullable = true, precision = NumberConstants.NUM30)
	private String modeRegTokenName;

	/**
	 * Token name representing the selected type of TSL filter registration.
	 *
	 * <p>Mapped to the database column {@code TYPE_FILTER_REG_TOKEN_NAME}.</p>
	 */
	@Column(name = "TYPE_FILTER_REG_TOKEN_NAME", nullable = true, precision = NumberConstants.NUM40)
	private String typeFilterRegTokenName;


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
	 * Gets the value of the attribute {@link #modeRegTokenName}.
	 * @return the value of the attribute {@link #modeRegTokenName}.
	 */
	public String getModeRegTokenName() {
		return modeRegTokenName;
	}

	/**
	 * Sets the value of the attribute {@link #modeRegTokenName}.
	 * @param modeRegTokenName The value for the attribute {@link #modeRegTokenName}.
	 */
	public void setModeRegTokenName(String modeRegTokenName) {
		this.modeRegTokenName = modeRegTokenName;
	}

	/**
	 * Gets the value of the attribute {@link #typeFilterRegTokenName}.
	 * @return the value of the attribute {@link #typeFilterRegTokenName}.
	 */
	public String getTypeFilterRegTokenName() {
		return typeFilterRegTokenName;
	}

	/**
	 * Sets the value of the attribute {@link #typeFilterRegTokenName}.
	 * @param typeFilterRegTokenName The value for the attribute {@link #typeFilterRegTokenName}.
	 */
	public void setTypeFilterRegTokenName(String typeFilterRegTokenName) {
		this.typeFilterRegTokenName = typeFilterRegTokenName;
	}

}
