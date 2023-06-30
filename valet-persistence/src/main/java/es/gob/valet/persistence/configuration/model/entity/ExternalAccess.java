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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.SystemCertificate.java.</p>
 * <b>Description:</b><p>Class that maps the <i>SYSTEM_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 21/02/2022.
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 *<p>Class that maps the <i>EXTERNAL_ACCESS</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 21/02/2022.
 */
@Entity
@Table(name = "EXTERNAL_ACCESS")
public class ExternalAccess implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -5360652040333180484L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idUrl;

	/**
	 * Attribute that represents the url.
	 */
	private String url;

	/**
	 * Attribute that represents the url.
	 */
	private String originUrl;
	
	/**
	 * Attribute that indicates if the connection is OK (true) or not (false).
	 */
	private Boolean stateConn;

	/**
	 * Attribute that represents the last url connection.
	 */
	private Date lastConn;

	/**
	 * Gets the value of the attribute {@link #idUrl}.
	 * @return the value of the attribute {@link #idUrl}.
	 */
	@Id
	@Column(name = "ID_URL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_external_access")
	@GenericGenerator(name = "sq_external_access", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_EXTERNAL_ACCESS"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdUrl() {
		return idUrl;
	}

	/**
	 * Sets the value of the attribute {@link #idUrl}.
	 * @param idUrlParam The value for the attribute {@link #idUrl}.
	 */
	public void setIdUrl(Long idUrlParam) {
		this.idUrl = idUrlParam;
	}

	/**
	 * Gets the value of the attribute {@link #url}.
	 * @return the value of the attribute {@link #url}.
	 */
	@Column(name = "URL", nullable = false, length = NumberConstants.NUM4000)
	@JsonView(DataTablesOutput.View.class)
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the value of the attribute {@link #url}.
	 * @param urlParam The value for the attribute {@link #url}.
	 */
	public void setUrl(String urlParam) {
		this.url = urlParam;
	}
	/**
	 * Gets the value of the attribute {@link #originUrl}.
	 * @return the value of the attribute {@link #originUrl}.
	 */
	@Column(name = "ORIGIN_URL", nullable = false, length = NumberConstants.NUM4000)
	@JsonView(DataTablesOutput.View.class)
	public String getOriginUrl() {
		return originUrl;
	}

	/**
	 * Sets the value of the attribute {@link #originUrl}.
	 * @param aliasParam The value for the attribute {@link #originUrl}.
	 */
	public void setOriginUrl(String originUrlParam) {
		this.originUrl = originUrlParam;
	}
	
	/**
	 * Gets the value of the attribute {@link #stateConn}.
	 * @return the value of the attribute {@link #stateConn}.
	 */
	@Column(name = "STATE_CONN", nullable = false, precision = 1)
	@Type(type = "yes_no")
	public Boolean getStateConn() {
		return stateConn;
	}

	/**
	 * Sets the value of the attribute {@link #stateConn}.
	 * @param stateConnParam The value for the attribute {@link #stateConn}.
	 */
	public void setStateConn(Boolean stateConnParam) {
		this.stateConn = stateConnParam;
	}

	/**
	 * Gets the value of the attribute {@link #lastConn}.
	 * @return the value of the attribute {@link #lastConn}.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_CONN", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public Date getLastConn() {
		return lastConn;
	}

	/**
	 * Sets the value of the attribute {@link #lastConn}.
	 * @param lastConnParam The value for the attribute {@link #lastConn}.
	 */
	public void setLastConn(Date lastConnParam) {
		this.lastConn = lastConnParam;
	}

	
}
