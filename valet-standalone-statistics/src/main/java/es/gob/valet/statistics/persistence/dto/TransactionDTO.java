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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.dto.TransactionDTO.java.</p>
 * <b>Description:</b><p>Class that represents the information of a transaction.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/10/2019.
 */
package es.gob.valet.statistics.persistence.dto;

import java.io.Serializable;
import java.time.LocalDate;


/** 
 * <p>Class that represents the information of a transaction.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/10/2019.
 */
public class TransactionDTO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -192492309368430021L;
	/**
	 * Attribute that represents the transaction ID.
	 */
	private Long transactionId;
	/**
	 * Attribute that represents the date of the importation.
	 */
	private LocalDate date;

	/**
	 * Attribute that represents the.
	 */
	private Long service;

	/**
	 * Attribute that represents the application ID.
	 */
	private String application;

	/**
	 * Attribute that represents the delegated application ID.
	 */
	private String delegatedApplication;
	/**
	 * Attribute that represents result code of the process.
	 */
	private String codResult;
	
	/**
	 *  Attribute that represents numbers of transactions.
	 */
	private Integer numTrans = 0;

	
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
	 * @param dateParam The value for the attribute {@link #date}.
	 */
	public void setDate(LocalDate dateParam) {
		this.date = dateParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #service}.
	 * @return the value of the attribute {@link #service}.
	 */
	public Long getService() {
		return service;
	}


	
	/**
	 * Sets the value of the attribute {@link #service}.
	 * @param serviceParam The value for the attribute {@link #service}.
	 */
	public void setService(Long serviceParam) {
		this.service = serviceParam;
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
	 * Gets the value of the attribute {@link #codResult}.
	 * @return the value of the attribute {@link #codResult}.
	 */
	public String getCodResult() {
		return codResult;
	}


	
	/**
	 * Sets the value of the attribute {@link #codResult}.
	 * @param codResultParam The value for the attribute {@link #codResult}.
	 */
	public void setCodResult(String codResultParam) {
		this.codResult = codResultParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #numTrans}.
	 * @return the value of the attribute {@link #numTrans}.
	 */
	public Integer getNumTrans() {
		return numTrans;
	}


	
	/**
	 * Sets the value of the attribute {@link #numTrans}.
	 * @param numTransParam The value for the attribute {@link #numTrans}.
	 */
	public void setNumTrans(Integer numTransParam) {
		this.numTrans = numTransParam;
	}



	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [transactionId=" + transactionId + ", date=" + date + ", sv=" + service + ", appId=" + application + ", delappId=" + delegatedApplication + "]";
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
		result = prime * result + ((codResult == null) ? 0 : codResult.hashCode());
		result = prime * result + ((delegatedApplication == null) ? 0 : delegatedApplication.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		TransactionDTO other = (TransactionDTO) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (codResult == null) {
			if (other.codResult != null)
				return false;
		} else if (!codResult.equals(other.codResult))
			return false;
		if (delegatedApplication == null) {
			if (other.delegatedApplication != null)
				return false;
		} else if (!delegatedApplication.equals(other.delegatedApplication))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}





}
