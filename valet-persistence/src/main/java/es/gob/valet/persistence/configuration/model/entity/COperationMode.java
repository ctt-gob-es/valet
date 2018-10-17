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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.COperationMode.java.</p>
 * <b>Description:</b><p>Class that maps the <i>C_OPERATION_MODE</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>15 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15 oct. 2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;

/** 
 * <p>Class that maps the <i>C_OPERATION_MODE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 15 oct. 2018.
 */
@Entity
@Table(name = "C_OPERATION_MODE")
public class COperationMode implements Serializable {

	/**
	 *  Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 2038893470104631485L;
	
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idCOperationMode;
	
	/**
	 * Attribute that represents the mode of operation.
	 */
	private String operationMode;

	
	/**
	 * Gets the value of the attribute {@link #idCOperationMode}.
	 * @return the value of the attribute {@link #idCOperationMode}.
	 */
	@Id
	@Column(name = "ID_OPERATION_MODE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	public Long getIdCOperationMode() {
		return idCOperationMode;
	}

	
	/**
	 * Sets the value of the attribute {@link #idCOperationMode}.
	 * @param idCOperationModeParam The value for the attribute {@link #idCOperationMode}.
	 */
	public void setIdCOperationMode(Long idCOperationModeParam) {
		this.idCOperationMode = idCOperationModeParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #operationMode}.
	 * @return the value of the attribute {@link #operationMode}.
	 */
	@Column(name = "OPERATION_MODE", nullable = false, length = NumberConstants.NUM30)
	public String getOperationMode() {
		return operationMode;
	}

	
	/**
	 * Sets the value of the attribute {@link #operationMode}.
	 * @param operationModeParam The value for the attribute {@link #operationMode}.
	 */
	public void setOperationMode(String operationModeParam) {
		this.operationMode = operationModeParam;
	}

}
