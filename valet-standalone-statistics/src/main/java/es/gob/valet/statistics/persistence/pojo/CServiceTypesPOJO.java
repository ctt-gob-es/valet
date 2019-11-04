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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.CServiceTypes.java.</p>
 * <b>Description:</b><p>The persistence class for the C_SERVICETYPES database table .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/10/2019.
 */
package es.gob.valet.statistics.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>The persistence class for the C_SERVICETYPES database table .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/10/2019.
 */
@Entity
@Table(name = "C_SERVICETYPES")
public class CServiceTypesPOJO implements Serializable {

	/**
	 * Attribute that represents the serial versio UID. 
	 */
	private static final long serialVersionUID = 444902067616368515L;
	
	/**
	 * Attribute that represents the primary key.
	 */
	private Long serviceTypePk;
	
	/**
	 * Attribute that represents the description of the type of service.
	 */
	private String description;

	
	/**
	 * Gets the value of the attribute {@link #serviceTypePk}.
	 * @return the value of the attribute {@link #serviceTypePk}.
	 */
	@Id
	@Column(name = "SERVICETYPEPK", unique = true, nullable = false, precision = NumberConstants.NUM4)
	public Long getServiceTypePk() {
		return serviceTypePk;
	}

	
	/**
	 * Sets the value of the attribute {@link #serviceTypePk}.
	 * @param serviceTypePkParam The value for the attribute {@link #serviceTypePk}.
	 */
	public void setServiceTypePk(Long serviceTypePkParam) {
		this.serviceTypePk = serviceTypePkParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #description}.
	 * @return the value of the attribute {@link #description}.
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}

	
	/**
	 * Sets the value of the attribute {@link #description}.
	 * @param descriptionParam The value for the attribute {@link #description}.
	 */
	public void setDescription(String descriptionParam) {
		this.description = descriptionParam;
	}
	
	
	

}
