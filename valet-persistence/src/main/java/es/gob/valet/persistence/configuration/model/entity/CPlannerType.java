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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.CPlannerType.java.</p>
 * <b>Description:</b><p> Class that represents the representation of the <i>C_PLANNER_TYPE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 02/10/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that represents the representation of the <i>C_PLANNER_TYPE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 02/10/2018.
 */
@Entity
@Table(name = "C_PLANNER_TYPE")
public class CPlannerType implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 3710288918662199270L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idPlannerType;

	/**
	 * Attribute that represents the name of the token with the description stored in properties file for internationalization.
	 */
	private String tokenName;

	/**
	 * Gets the value of the attribute {@link #idPlannerType}.
	 * @return the value of the attribute {@link #idPlannerType}.
	 */
	@Id
	@Column(name = "ID_PLANNER_TYPE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	public Long getIdPlannerType() {
		return idPlannerType;
	}

	/**
	 * Sets the value of the attribute {@link #idPlannerType}.
	 * @param idPlannerTypeParam The value for the attribute {@link #idPlannerType}.
	 */
	public void setIdPlannerType(Long idPlannerTypeParam) {
		this.idPlannerType = idPlannerTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	@Column(name = "TOKEN_NAME", nullable = false, length = NumberConstants.NUM30)
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param tokenNameParam The value for the attribute {@link #tokenName}.
	 */
	public void setTokenName(String tokenNameParam) {
		this.tokenName = tokenNameParam;
	}

}
