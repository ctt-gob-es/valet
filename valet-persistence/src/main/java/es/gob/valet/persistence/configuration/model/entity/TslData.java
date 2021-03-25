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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslData.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_DATA</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.4, 24/03/2021.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>TSL_DATA</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 24/03/2021.
 */
@Entity
@Table(name = "TSL_DATA")
public class TslData implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -999355505652868226L;

	/**
	 * Attribute that represents the country/region for this TSL.
	 */
	private Long idTslData;

	/**
	 * Attribute that represents the sequence number of this TSL.
	 */
	private Integer sequenceNumber;

	/**
	 * Attribute that represents a TSL responsible.
	 */
	private String responsible;

	/**
	 * Attribute that represents the issue date of this TSL.
	 */
	private Date issueDate;

	/**
	 * Attribute that represents the expiration Date for this TSL.
	 */
	private Date expirationDate;

	/**
	 * Attribute that represents the URI where this TSL is officially located.
	 */
	private String uriTslLocation;

	/**
	 * Attribute that represents the XML document of this TSL.
	 */
	private byte[ ] xmlDocument;

	/**
	 * Attribute that represents the legible document of this TSL.
	 */
	private byte[ ] legibleDocument;

	/**
	 * Attribute that represents the ETSI TS specification and version of this TSL.
	 */
	private CTslImpl tslImpl;

	/**
	 * Attribute that represents the country/region for this TSL.
	 */
	private TslCountryRegion tslCountryRegion;

	/**
	 * Attribute that represents if a new TSL are available.
	 */
	private String newTSLAvailable;

	/**
	 * Attribute that represents the last new TSL available are find.
	 */
	private Date lastNewTSLAvailableFind;

	/**
	 * Gets the value of the attribute {@link #idTslData}.
	 * @return the value of the attribute {@link #idTslData}.
	 */
	@Id
	@Column(name = "ID_TSL_DATA", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_data")
	@GenericGenerator(name = "sq_tsl_data", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_DATA"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTslData() {
		return idTslData;
	}

	/**
	 * Sets the value of the attribute {@link #idTslData}.
	 * @param idTslDataParam The value for the attribute {@link #idTslData}.
	 */
	public void setIdTslData(Long idTslDataParam) {
		this.idTslData = idTslDataParam;
	}

	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	@Column(name = "SEQUENCE_NUMBER", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumberParam The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(Integer sequenceNumberParam) {
		this.sequenceNumber = sequenceNumberParam;
	}

	/**
	 * Gets the value of the attribute {@link #responsible}.
	 * @return the value of the attribute {@link #responsible}.
	 */
	@Column(name = "RESPONSIBLE", unique = true, nullable = true, length = NumberConstants.NUM128)
	@JsonView(DataTablesOutput.View.class)
	public String getResponsible() {
		return responsible;
	}

	/**
	 * Sets the value of the attribute {@link #responsible}.
	 * @param responsibleParam The value for the attribute {@link #responsible}.
	 */
	public void setResponsible(String responsibleParam) {
		this.responsible = responsibleParam;
	}

	/**
	 * Gets the value of the attribute {@link #issueDate}.
	 * @return the value of the attribute {@link #issueDate}.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ISSUE_DATE", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * Sets the value of the attribute {@link #issueDate}.
	 * @param issueDateParam The value for the attribute {@link #issueDate}.
	 */
	public void setIssueDate(Date issueDateParam) {
		this.issueDate = issueDateParam;
	}

	/**
	 * Gets the value of the attribute {@link #expirationDate}.
	 * @return the value of the attribute {@link #expirationDate}.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRATION_DATE", nullable = true)
	@JsonView(DataTablesOutput.View.class)
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the value of the attribute {@link #expirationDate}.
	 * @param expirationDateParam The value for the attribute {@link #expirationDate}.
	 */
	public void setExpirationDate(Date expirationDateParam) {
		this.expirationDate = expirationDateParam;
	}

	/**
	 * Gets the value of the attribute {@link #uriTslLocation}.
	 * @return the value of the attribute {@link #uriTslLocation}.
	 */
	@Column(name = "URI_TSL_LOCATION", nullable = false, length = NumberConstants.NUM512)
	@JsonView(DataTablesOutput.View.class)
	public String getUriTslLocation() {
		return uriTslLocation;
	}

	/**
	 * Sets the value of the attribute {@link #uriTslLocation}.
	 * @param uriTslLocationParam The value for the attribute {@link #uriTslLocation}.
	 */
	public void setUriTslLocation(String uriTslLocationParam) {
		this.uriTslLocation = uriTslLocationParam;
	}

	/**
	 * Gets the value of the attribute {@link #xmlDocument}.
	 * @return the value of the attribute {@link #xmlDocument}.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "XML_DOCUMENT", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public byte[ ] getXmlDocument() {
		return xmlDocument;
	}

	/**
	 * Sets the value of the attribute {@link #xmlDocument}.
	 * @param xmlDocumentParam The value for the attribute {@link #xmlDocument}.
	 */
	public void setXmlDocument(byte[ ] xmlDocumentParam) {
		this.xmlDocument = xmlDocumentParam;
	}

	/**
	 * Gets the value of the attribute {@link #legibleDocument}.
	 * @return the value of the attribute {@link #legibleDocument}.
	 */
	@Lob()
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "LEGIBLE_DOCUMENT")
	@JsonView(DataTablesOutput.View.class)
	public byte[ ] getLegibleDocument() {
		return legibleDocument;
	}

	/**
	 * Sets the value of the attribute {@link #legibleDocument}.
	 * @param legibleDocumentParam The value for the attribute {@link #legibleDocument}.
	 */
	public void setLegibleDocument(byte[ ] legibleDocumentParam) {
		this.legibleDocument = legibleDocumentParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslImpl}.
	 * @return the value of the attribute {@link #tslImpl}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TSL_IMPL", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public CTslImpl getTslImpl() {
		return tslImpl;
	}

	/**
	 * Sets the value of the attribute {@link #tslImpl}.
	 * @param tslImplParam The value for the attribute {@link #tslImpl}.
	 */
	public void setTslImpl(CTslImpl tslImplParam) {
		this.tslImpl = tslImplParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslCountryRegion}.
	 * @return the value of the attribute {@link #tslCountryRegion}.
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COUNTRY_REGION", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public TslCountryRegion getTslCountryRegion() {
		return tslCountryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #tslCountryRegion}.
	 * @param tslCountryRegionParam The value for the attribute {@link #tslCountryRegion}.
	 */
	public void setTslCountryRegion(TslCountryRegion tslCountryRegionParam) {
		this.tslCountryRegion = tslCountryRegionParam;
	}

	/**
	 * Gets the value of the attribute {@link #newTSLAvailable}.
	 * @return the value of the attribute {@link #newTSLAvailable}.
	 */
	@Column(name = "NEW_TSL_AVAILABLE", nullable = false, length = 1)
	public String getNewTSLAvailable() {
		return newTSLAvailable;
	}

	/**
	 * Sets the value of the attribute {@link #newTSLAvailable}.
	 * @param newTSLAvailableParam The value for the attribute {@link #newTSLAvailable}.
	 */
	public void setNewTSLAvailable(String newTSLAvailableParam) {
		this.newTSLAvailable = newTSLAvailableParam;
	}

	/**
	 * Gets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @return the value of the attribute {@link #lastNewTSLAvailableFind}.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_TSLA_FIND", nullable = true)
	public Date getLastNewTSLAvailableFind() {
		return lastNewTSLAvailableFind;
	}

	/**
	 * Sets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @param lastNewTSLAvailableFindParam The value for the attribute {@link #lastNewTSLAvailableFind}.
	 */

	public void setLastNewTSLAvailableFind(Date lastNewTSLAvailableFindParam) {
		this.lastNewTSLAvailableFind = lastNewTSLAvailableFindParam;
	}

}
