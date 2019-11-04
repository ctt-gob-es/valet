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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.FctValidationPOJO.java.</p>
 * <b>Description:</b><p>The persistence class for the FCT_VALIDATIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/10/2019.
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
 * <p>The persistence class for the FCT_VALIDATIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/10/2019.
 */
@Entity
@Table(name = "FCT_VALIDATIONS")
public class FctValidationPOJO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 7430019344321432186L;
	
	/**
	 * Attribute that represents the primary key.
	 */
	private Long validationPK;

	/**
	 * Attribute that represents the date of creation of the transaction.
	 */
	private DimDatePOJO date;

	/**
	 * Attribute that represents the application used in the transaction.
	 */
	private DimApplicationPOJO application;

	/**
	 * Attribute that represents the delegated application used in the transaction.
	 */
	private DimApplicationPOJO delegatedApplication;
	
	/**
	 * Attribute that represents the country issuing the TSL.
	 */
	private String country;
	
	/**
	 * Attribute that represents the Trust Service Provider in which the certificate was detected.
	 */
	private String tspName;
	
	/**
	 * Attribute that represents the Trust Service Provider Service in which the certificate was detected.
	 */
	private String tspService;
	
	/**
	 * Attribute that represents the Trust Service Provider Service Historic in which the certificate was detected.
	 */
	private String tspServiceHistoric;
	

	/**
	 *Attribute that represents number of matching validations.
	 */
	private Integer numberValidations;

	/**
	 * Attribute that represents the auditory ID.
	 */
	private DimNodePOJO node;

	
	/**
	 * Gets the value of the attribute {@link #validationPK}.
	 * @return the value of the attribute {@link #validationPK}.
	 */
	@Id
	@Column(name = "VALIDATIONPK", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getValidationPK() {
		return validationPK;
	}

	
	/**
	 * Sets the value of the attribute {@link #validationPK}.
	 * @param validationPKParam The value for the attribute {@link #validationPK}.
	 */
	public void setValidationPK(Long validationPKParam) {
		this.validationPK = validationPKParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #date}.
	 * @return the value of the attribute {@link #date}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "ID_DATE", nullable = false)
	public DimDatePOJO getDate() {
		return date;
	}

	
	/**
	 * Sets the value of the attribute {@link #date}.
	 * @param dateParam The value for the attribute {@link #date}.
	 */
	public void setDate(DimDatePOJO dateParam) {
		this.date = dateParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #application}.
	 * @return the value of the attribute {@link #application}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "ID_APPLICATION", nullable = false)
	public DimApplicationPOJO getApplication() {
		return application;
	}

	
	/**
	 * Sets the value of the attribute {@link #application}.
	 * @param applicationParam The value for the attribute {@link #application}.
	 */
	public void setApplication(DimApplicationPOJO applicationParam) {
		this.application = applicationParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #delegatedApplication}.
	 * @return the value of the attribute {@link #delegatedApplication}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "ID_DELEGATED_APPLICATION", nullable = true)
	public DimApplicationPOJO getDelegatedApplication() {
		return delegatedApplication;
	}

	
	/**
	 * Sets the value of the attribute {@link #delegatedApplication}.
	 * @param delegatedApplicationParam The value for the attribute {@link #delegatedApplication}.
	 */
	public void setDelegatedApplication(DimApplicationPOJO delegatedApplicationParam) {
		this.delegatedApplication = delegatedApplicationParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param countryParam The value for the attribute {@link #country}.
	 */
	public void setCountry(String countryParam) {
		this.country = countryParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #tspName}.
	 * @return the value of the attribute {@link #tspName}.
	 */
	@Column(name = "TSP_NAME")
	public String getTspName() {
		return tspName;
	}

	
	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspNameParam The value for the attribute {@link #tspName}.
	 */
	public void setTspName(String tspNameParam) {
		this.tspName = tspNameParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #tspService}.
	 * @return the value of the attribute {@link #tspService}.
	 */
	@Column(name = "TSP_SERVICE")
	public String getTspService() {
		return tspService;
	}

	
	/**
	 * Sets the value of the attribute {@link #tspService}.
	 * @param tspServiceParam The value for the attribute {@link #tspService}.
	 */
	public void setTspService(String tspServiceParam) {
		this.tspService = tspServiceParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #tspServiceHistoric}.
	 * @return the value of the attribute {@link #tspServiceHistoric}.
	 */
	@Column(name = "TSP_SERVICE_HIST")
	public String getTspServiceHistoric() {
		return tspServiceHistoric;
	}

	
	/**
	 * Sets the value of the attribute {@link #tspServiceHistoric}.
	 * @param tspServiceHistoricParam The value for the attribute {@link #tspServiceHistoric}.
	 */
	public void setTspServiceHistoric(String tspServiceHistoricParam) {
		this.tspServiceHistoric = tspServiceHistoricParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #numberValidations}.
	 * @return the value of the attribute {@link #numberValidations}.
	 */
	@Column(name = "MATCHES")
	public Integer getNumberValidations() {
		return numberValidations;
	}

	
	/**
	 * Sets the value of the attribute {@link #numberValidations}.
	 * @param numberValidationsParam The value for the attribute {@link #numberValidations}.
	 */
	public void setNumberValidations(Integer numberValidationsParam) {
		this.numberValidations = numberValidationsParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #node}.
	 * @return the value of the attribute {@link #node}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional=false)
	@JoinColumn(name = "ID_NODE", nullable = false)
	public DimNodePOJO getNode() {
		return node;
	}

	
	/**
	 * Sets the value of the attribute {@link #node}.
	 * @param nodeParam The value for the attribute {@link #node}.
	 */
	public void setNode(DimNodePOJO nodeParam) {
		this.node = nodeParam;
	}
	

}
