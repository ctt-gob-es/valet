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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.FctTransactionPOJO.java.</p>
 * <b>Description:</b><p>The persistence class for the FCT_TRANSACTIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/10/2019.
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
 * <p>The persistence class for the FCT_TRANSACTIONS database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/10/2019.
 */
@Entity
@Table(name = "FCT_TRANSACTIONS")
public class FctTransactionPOJO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -6337587384217644261L;

	/**
	 * Attribute that represents the primary key.
	 */

	private Long transactionPk;

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
	 * Attribute that represents the type of service of the transaction.
	 */
	private CServiceTypesPOJO serviceType;

	/**
	 * Attribute that represents the code of results of the transaction.
	 */
	private CCodResultsPOJO codResults;

	/**
	 *Attribute that represents number of matching transactions.
	 */
	private Integer numberTransactions;

	/**
	 * Attribute that represents the auditory ID.
	 */
	private DimNodePOJO node;

	/**
	 * Gets the value of the attribute {@link #transactionPk}.
	 * @return the value of the attribute {@link #transactionPk}.
	 */
	@Id
	@Column(name = "TRANSACTIONPK", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getTransactionPk() {
		return transactionPk;
	}

	/**
	 * Sets the value of the attribute {@link #transactionPk}.
	 * @param transactionPkParam The value for the attribute {@link #transactionPk}.
	 */
	public void setTransactionPk(Long transactionPkParam) {
		this.transactionPk = transactionPkParam;
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
	 * Gets the value of the attribute {@link #serviceType}.
	 * @return the value of the attribute {@link #serviceType}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_SERVICE_TYPE", nullable = false)
	public CServiceTypesPOJO getServiceType() {
		return serviceType;
	}

	/**
	 * Sets the value of the attribute {@link #serviceType}.
	 * @param serviceTypeParam The value for the attribute {@link #serviceType}.
	 */
	public void setServiceType(CServiceTypesPOJO serviceTypeParam) {
		this.serviceType = serviceTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #codResults}.
	 * @return the value of the attribute {@link #codResults}.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_CODRESULT", nullable = false)
	public CCodResultsPOJO getCodResults() {
		return codResults;
	}
	/**
	 * Sets the value of the attribute {@link #codResults}.
	 * @param codResultsParam The value for the attribute {@link #codResults}.
	 */
	public void setCodResults(CCodResultsPOJO codResultsParam) {
		this.codResults = codResultsParam;
	}

	/**
	 * Gets the value of the attribute {@link #numberTransactions}.
	 * @return the value of the attribute {@link #numberTransactions}.
	 */
	@Column(name = "MATCHES")
	public Integer getNumberTransactions() {
		return numberTransactions;
	}

	/**
	 * Sets the value of the attribute {@link #numberTransactions}.
	 * @param numberTransactionsParam The value for the attribute {@link #numberTransactions}.
	 */
	public void setNumberTransactions(Integer numberTransactionsParam) {
		this.numberTransactions = numberTransactionsParam;
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
	 * Sets the value of the attribute {@link #nodeControl}.
	 * @param nodeParam The value for the attribute {@link #nodeControl}.
	 */
	public void setNode(DimNodePOJO nodeParam) {
		this.node= nodeParam;
	}

}
