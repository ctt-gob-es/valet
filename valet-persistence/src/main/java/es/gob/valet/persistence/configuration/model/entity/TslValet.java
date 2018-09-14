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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslValet.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_VALET</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25 jun. 2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/** 
 * <p>Class that maps the <i>TSL_VALET</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25 jun. 2018.
 */
@Entity
@Table(name = "TSL_VALET")
public class TslValet implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 3936139266630802541L;
	/**
	 * Attribute that represents the country/region for this TSL.
	 */
	private Long idTslValet;

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
	private TslCountryRegion country;

	/**
	 * Attribute that represents if a new TSL are avaliable.
	 */
	private String newTSLAvaliable;

	/**
	 * Attribute that represents the last new TSL avaliable are find.
	 */
	private Date lastNewTSAvaliableFind;
	
	/**
	 * Attribute that represents the alias for new TSL.
	 */
	private String alias;

	/**
	 * Gets the value of the attribute {@link #idTslValet}.
	 * @return the value of the attribute {@link #idTslValet}.
	 */
	@Id
	@Column(name = "ID_TSL_VALET", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_valet")
	@GenericGenerator(name = "sq_tsl_valet", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_VALET"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTslValet() {
		return idTslValet;
	}

	/**
	 * Sets the value of the attribute {@link #idTslValet}.
	 * @param idTslValet The value for the attribute {@link #idTslValet}.
	 */
	public void setIdTslValet(Long idTslValet) {
		this.idTslValet = idTslValet;
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
	 * @param sequenceNumber The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Gets the value of the attribute {@link #responsible}.
	 * @return the value of the attribute {@link #responsible}.
	 */
	@Column(name = "RESPONSIBLE", unique = true, nullable = false, length = NumberConstants.NUM128)
	@JsonView(DataTablesOutput.View.class)
	public String getResponsible() {
		return responsible;
	}

	/**
	 * Sets the value of the attribute {@link #responsible}.
	 * @param responsible The value for the attribute {@link #responsible}.
	 */
	public void setResponsible(String responsible) {
		this.responsible = responsible;
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
	 * @param issueDate The value for the attribute {@link #issueDate}.
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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
	 * @param expirationDate The value for the attribute {@link #expirationDate}.
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	 * @param uriTslLocation The value for the attribute {@link #uriTslLocation}.
	 */
	public void setUriTslLocation(String uriTslLocation) {
		this.uriTslLocation = uriTslLocation;
	}

	/**
	 * Gets the value of the attribute {@link #xmlDocument}.
	 * @return the value of the attribute {@link #xmlDocument}.
	 */
	@Lob()
	@Column(name = "XML_DOCUMENT", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public byte[ ] getXmlDocument() {
		return xmlDocument;
	}

	/**
	 * Sets the value of the attribute {@link #xmlDocument}.
	 * @param xmlDocument The value for the attribute {@link #xmlDocument}.
	 */
	public void setXmlDocument(byte[ ] xmlDocument) {
		this.xmlDocument = xmlDocument;
	}

	/**
	 * Gets the value of the attribute {@link #legibleDocument}.
	 * @return the value of the attribute {@link #legibleDocument}.
	 */
	@Lob()
	@Column(name = "LEGIBLE_DOCUMENT")
	@JsonView(DataTablesOutput.View.class)
	public byte[ ] getLegibleDocument() {
		return legibleDocument;
	}

	/**
	 * Sets the value of the attribute {@link #legibleDocument}.
	 * @param legibleDocument The value for the attribute {@link #legibleDocument}.
	 */
	public void setLegibleDocument(byte[ ] legibleDocument) {
		this.legibleDocument = legibleDocument;
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
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COUNTRY_REGION")
	@JsonView(DataTablesOutput.View.class)
	public TslCountryRegion getCountry() {
		return country;
	}

	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(TslCountryRegion country) {
		this.country = country;
	}

	/**
	 * Gets the value of the attribute {@link #newTSLAvaliable}.
	 * @return the value of the attribute {@link #newTSLAvaliable}.
	 */

	@Column(name = "NEW_TSL_AVALIABLE", nullable = false, length = 1)
	public String getNewTSLAvaliable() {
		return newTSLAvaliable;
	}

	/**
	 * Sets the value of the attribute {@link #newTSLAvaliable}.
	 * @param newTSLAvaliableParam The value for the attribute {@link #newTSLAvaliable}.
	 */

	public void setNewTSLAvaliable(String newTSLAvaliableParam) {

		this.newTSLAvaliable = newTSLAvaliableParam;
	}

	/**
	 * Gets the value of the attribute {@link #nextUpdateDate}.
	 * @return the value of the attribute {@link #nextUpdateDate}.
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_TSLA_FIND", nullable = true)
	public Date getLastNewTSAvaliableFind() {

		return lastNewTSAvaliableFind;
	}

	/**
	 * Sets the value of the attribute {@link #lastNewTSAvaliableFind}.
	 * @param lastNewTSAvaliableFindParam The value for the attribute {@link #lastNewTSAvaliableFind}.
	 */

	public void setLastNewTSAvaliableFind(Date lastNewTSAvaliableFindParam) {
		this.lastNewTSAvaliableFind = lastNewTSAvaliableFindParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #alias}.
	 * @return the value of the attribute {@link #alias}.
	 */
	@Column(name = "ALIAS", unique = true, nullable = false, length = NumberConstants.NUM128)
	public String getAlias() {
		return alias;
	}

	
	/**
	 * Sets the value of the attribute {@link #alias}.
	 * @param alias The value for the attribute {@link #alias}.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
