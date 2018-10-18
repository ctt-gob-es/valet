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
 * @version 1.0, 04/10/2018.
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

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>CONF_SERVER_MAIL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 04/10/2018.
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
	 * Attribute that represents the user.
	 */
	private String userMail;

	/**
	 * Attribute that represents the password.
	 */
	private String passwordMail;

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

}
