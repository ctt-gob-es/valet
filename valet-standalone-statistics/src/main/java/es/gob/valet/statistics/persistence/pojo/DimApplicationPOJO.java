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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.DimApplicationPOJO.java.</p>
 * <b>Description:</b><p>The persistence class for the DIM_APPLICATIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/10/2019.
 */
package es.gob.valet.statistics.persistence.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;

/** 
 * <p>The persistence class for the DIM_APPLICATIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/10/2019.
 */
@Entity
@Table(name = "DIM_APPLICATIONS")
public class DimApplicationPOJO implements Serializable {

	/**
	 * Attribute that represents the serial version. 
	 */
	private static final long serialVersionUID = 2026895264200674156L;

	/**
	 * Attribute that represents the PK.
	 */
	private Long idApplication;

	/**
	 * Attribute that represents the application identifier.
	 */
	private String identificator;

	/**
	 * Attribute that represents the date of registration of the application in the system.
	 */
	private DimDatePOJO registrationDate;

	
	/**
	 * Gets the value of the attribute {@link #idApplication}.
	 * @return the value of the attribute {@link #idApplication}.
	 */
	@Id
	@Column(name = "APPLICATIONPK", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getIdApplication() {
		return idApplication;
	}

	
	/**
	 * Sets the value of the attribute {@link #idApplication}.
	 * @param idApplicationParam The value for the attribute {@link #idApplication}.
	 */
	public void setIdApplication(Long idApplicationParam) {
		this.idApplication = idApplicationParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #identificator}.
	 * @return the value of the attribute {@link #identificator}.
	 */
	@Column(name = "IDENTIFICATOR", unique=true)
	public String getIdentificator() {
		return identificator;
	}

	
	/**
	 * Sets the value of the attribute {@link #identificator}.
	 * @param identificatorParam The value for the attribute {@link #identificator}.
	 */
	public void setIdentificator(String identificatorParam) {
		this.identificator = identificatorParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #registrationDate}.
	 * @return the value of the attribute {@link #registrationDate}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "REGISTRATION_DATE", nullable = true)
	public DimDatePOJO getRegistrationDate() {
		return registrationDate;
	}

	
	/**
	 * Sets the value of the attribute {@link #registrationDate}.
	 * @param registrationDateParam The value for the attribute {@link #registrationDate}.
	 */
	public void setRegistrationDate(DimDatePOJO registrationDateParam) {
		this.registrationDate = registrationDateParam;
	}
}
