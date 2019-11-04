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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.dto.ValidationDTO.java.</p>
 * <b>Description:</b><p>Class that represents the information of a validation transaction.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>28/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/10/2019.
 */
package es.gob.valet.statistics.persistence.dto;

import java.io.Serializable;
import java.time.LocalDate;


/** 
 * <p>Class that represents the information of a validation transaction.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 28/10/2019.
 */
public class ValidationDTO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -8779991181894907302L;
	
	/**
	 * Attribute that represents the transaction ID.
	 */
	private Long transactionId;
	/**
	 * Attribute that represents the date of the importation.
	 */
	private LocalDate date;
	/**
	 * Attribute that represents the application ID.
	 */
	private String application;

	/**
	 * Attribute that represents the delegated application ID.
	 */
	private String delegatedApplication;
	
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
	 *  Attribute that represents numbers of transactions of validation.
	 */
	private Integer numValidations = 0;


	
	/**
	 * Gets the value of the attribute {@link #transactionId}.
	 * @return the value of the attribute {@link #transactionId}.
	 */
	public Long getTransactionId() {
		return transactionId;
	}


	
	/**
	 * Sets the value of the attribute {@link #transactionId}.
	 * @param transactionIdParam The value for the attribute {@link #transactionId}.
	 */
	public void setTransactionId(Long transactionIdParam) {
		this.transactionId = transactionIdParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #date}.
	 * @return the value of the attribute {@link #date}.
	 */
	public LocalDate getDate() {
		return date;
	}


	
	/**
	 * Sets the value of the attribute {@link #date}.
	 * @param date The value for the attribute {@link #date}.
	 */
	public void setDate(LocalDate dateParam) {
		this.date = dateParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #application}.
	 * @return the value of the attribute {@link #application}.
	 */
	public String getApplication() {
		return application;
	}


	
	/**
	 * Sets the value of the attribute {@link #application}.
	 * @param applicationParam The value for the attribute {@link #application}.
	 */
	public void setApplication(String applicationParam) {
		this.application = applicationParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #delegatedApplication}.
	 * @return the value of the attribute {@link #delegatedApplication}.
	 */
	public String getDelegatedApplication() {
		return delegatedApplication;
	}


	
	/**
	 * Sets the value of the attribute {@link #delegatedApplication}.
	 * @param delegatedApplicationParam The value for the attribute {@link #delegatedApplication}.
	 */
	public void setDelegatedApplication(String delegatedApplicationParam) {
		this.delegatedApplication = delegatedApplicationParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
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
	 * Gets the value of the attribute {@link #numValidations}.
	 * @return the value of the attribute {@link #numValidations}.
	 */
	public Integer getNumValidations() {
		return numValidations;
	}


	
	/**
	 * Sets the value of the attribute {@link #numValidations}.
	 * @param numValidationsParam The value for the attribute {@link #numValidations}.
	 */
	public void setNumValidations(Integer numValidationsParam) {
		this.numValidations = numValidationsParam;
	}



	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((delegatedApplication == null) ? 0 : delegatedApplication.hashCode());
		result = prime * result + ((tspName == null) ? 0 : tspName.hashCode());
		result = prime * result + ((tspService == null) ? 0 : tspService.hashCode());
		result = prime * result + ((tspServiceHistoric == null) ? 0 : tspServiceHistoric.hashCode());
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
		ValidationDTO other = (ValidationDTO) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (delegatedApplication == null) {
			if (other.delegatedApplication != null)
				return false;
		} else if (!delegatedApplication.equals(other.delegatedApplication))
			return false;
		if (tspName == null) {
			if (other.tspName != null)
				return false;
		} else if (!tspName.equals(other.tspName))
			return false;
		if (tspService == null) {
			if (other.tspService != null)
				return false;
		} else if (!tspService.equals(other.tspService))
			return false;
		if (tspServiceHistoric == null) {
			if (other.tspServiceHistoric != null)
				return false;
		} else if (!tspServiceHistoric.equals(other.tspServiceHistoric))
			return false;
		return true;
	}
	

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [transactionId=" + transactionId + ", date=" + date + ", appId=" + application + ", delappId=" + delegatedApplication + ", country=" + country + ", tspName=" + tspName +", tspService=" + tspService +  "]";
	}

}
