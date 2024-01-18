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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.ConfServerMail.java.</p>
 * <b>Description:</b><p>Class that maps the <i>CONF_SERVER_MAIL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 22/06/2023.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>CONF_SERVER_MAIL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 22/06/2023.
 */
@Entity
@Table(name = "CONF_SERVER_MAIL")
public class ConfServerMail implements Serializable {

	/**
	 * Constant attribute that defines the serial version UID.
	 */
	private static final long serialVersionUID = 5327848066348264949L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idConfServerMail;

	/**
	 * Attribute that represents the issuer.
	 */
	private String issuerMail;

	/**
	 * Attribute that represents the host.
	 */
	private String hostMail;

	/**
	 * Attribute that represents the port.
	 */
	private Long portMail;

	/**
	 * Attribute that represents a flag that indicates if it is necessary to use
	 * authentication. 
	 */
	private Boolean useAuthenticationMail = Boolean.FALSE;
	
	/**
	 * Attribute that represents the user.
	 */
	private String userMail;

	/**
	 * Attribute that represents the password.
	 */
	private String passwordMail;
		
	/**
	 * Attribute that represents the maximun time allowed, in milliseconds, for establishing the SMTP connection.
	 */
	private Integer connectionTimeout;
	/**
	 * Attribute that represents the maximun time allowed, in milliseconds,  for sending the mail messages.
	 */
	private Integer readingTimeout;
	/**
    * Attribute that represents a flag that indicates whether TLS encryption should be enabled or disabled.
    */
	private Boolean tslEnabled;

	/**
	 * Gets the value of the attribute {@link #idConfServerMail}.
	 * @return the value of the attribute {@link #idConfServerMail}.
	 */
	@Id
	@Column(name = "ID_CONF_SERVER_MAIL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_conf_server_mail")
	@GenericGenerator(name = "sq_conf_server_mail", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_CONF_SERVER_MAIL"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	public Long getIdConfServerMail() {
		return idConfServerMail;
	}

	/**
	 * Sets the value of the attribute {@link #idConfServerMail}.
	 * @param idConfServerMailParam The value for the attribute {@link #idConfServerMail}.
	 */
	public void setIdConfServerMail(Long idConfServerMailParam) {
		this.idConfServerMail = idConfServerMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #issuerMail}.
	 * @return the value of the attribute {@link #issuerMail}.
	 */
	@Column(name = "ISSUER_MAIL", nullable = false, precision = NumberConstants.NUM200)
	public String getIssuerMail() {
		return issuerMail;
	}

	/**
	 * Sets the value of the attribute {@link #issuer}.
	 * @param issuerMailParam The value for the attribute {@link #issuerMail}.
	 */
	public void setIssuerMail(String issuerMailParam) {
		this.issuerMail = issuerMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #hostMail}.
	 * @return the value of the attribute {@link #hostMail}.
	 */
	@Column(name = "HOST_MAIL", nullable = false, precision = NumberConstants.NUM200)
	public String getHostMail() {
		return hostMail;
	}

	/**
	 * Sets the value of the attribute {@link #host}.
	 * @param hostMailParam The value for the attribute {@link #host}.
	 */
	public void setHostMail(String hostMailParam) {
		this.hostMail = hostMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #portMail}.
	 * @return the value of the attribute {@link #portMail}.
	 */
	@Column(name = "PORT_MAIL", nullable = false, precision = NumberConstants.NUM10)
	public Long getPortMail() {
		return portMail;
	}

	/**
	 * Sets the value of the attribute {@link #port}.
	 * @param portMailParam The value for the attribute {@link #portMail}.
	 */
	public void setPortMail(Long portMailParam) {
		this.portMail = portMailParam;
	}
	
	/**
	 * Gets the value of the attribute {@link #useAuthenticationMail}.
	 * @return the value of the attribute {@link #useAuthenticationMail}.
	 */
	@Column(name = "USE_AUTHENTICATION_MAIL", nullable = true, precision = NumberConstants.NUM1)
	@Type(type = "yes_no")
	@JsonView(DataTablesOutput.View.class)
	public Boolean getUseAuthenticationMail() {
		return useAuthenticationMail;
	}
	
	/**
	 * Sets the value of the attribute {@link #useAuthenticationMail}.
	 * @param useAuthenticationMailParam The value for the attribute {@link #useAuthenticationMail}.
	 */
	public void setUseAuthenticationMail(Boolean useAuthenticationMailParam) {
		this.useAuthenticationMail = useAuthenticationMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #userMail}.
	 * @return the value of the attribute {@link #userMail}.
	 */
	@Column(name = "USER_MAIL", nullable = true, precision = NumberConstants.NUM200)
	public String getUserMail() {
		return userMail;
	}

	/**
	 * Sets the value of the attribute {@link #userMail}.
	 * @param userMailParam The value for the attribute {@link #userMail}.
	 */
	public void setUserMail(String userMailParam) {
		this.userMail = userMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #passwordMail}.
	 * @return the value of the attribute {@link #passwordMail}.
	 */
	@Column(name = "PASSWORD_MAIL", nullable = true, precision = NumberConstants.NUM200)
	public String getPasswordMail() {
		return passwordMail;
	}

	/**
	 * Sets the value of the attribute {@link #passwordMail}.
	 * @param passwordMailParam The value for the attribute {@link #passwordMail}.
	 */
	public void setPasswordMail(String passwordMailParam) {
		this.passwordMail = passwordMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #connectionTimeout}.
	 * @return the value of the attribute {@link #connectionTimeout}.
	 */
	@Column(name = "CONNECTION_TIMEOUT", nullable = false)
	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * Sets the value of the attribute {@link #connectionTimeout}.
	 * @param connectionTimeout The value for the attribute {@link #connectionTimeout}.
	 */
	public void setConnectionTimeout(Integer connectionTimeoutParam) {
		this.connectionTimeout = connectionTimeoutParam;
	}

	/**
	 * Gets the value of the attribute {@link #readingTimeout}.
	 * @return the value of the attribute {@link #readingTimeout}.
	 */
	@Column(name = "READING_TIMEOUT", nullable = false)
	public Integer getReadingTimeout() {
		return readingTimeout;
	}

	/**
	 * Sets the value of the attribute {@link #readingTimeout}.
	 * @param readingTimeout The value for the attribute {@link #readingTimeout}.
	 */
	public void setReadingTimeout(Integer readingTimeoutParam) {
		this.readingTimeout = readingTimeoutParam;
	}
	
    /**
     * Gets the value of the attribute {@link #tslEnabled}.
     * @return the value of the attribute {@link #tslEnabled}.
     */
    @Column(name = "TSL_ENABLED", nullable = true, precision = NumberConstants.NUM1)
    @Type(type = "yes_no")
    @JsonView(DataTablesOutput.View.class)
    public Boolean getTslEnabled() {
        return tslEnabled;
    }
    
    /**
     * Sets the value of the attribute {@link #tslEnabled}.
     * @param tslEnabledParam The value for the attribute {@link #tslEnabled}.
     */
    public void setTslEnabled(Boolean tslEnabledParam) {
        this.tslEnabled = tslEnabledParam;
    }

}
