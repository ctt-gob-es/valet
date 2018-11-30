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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Proxy.java.</p>
 * <b>Description:</b><p>Class that maps the <i>PROXY</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15/10/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>PROXY</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15/10/2018.
 */
@Entity
@Table(name = "PROXY")
public class Proxy implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 1098990694077374096L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idProxy;

	/**
	 * Attribute that represents the operation mode in the proxy configuration.
	 */
	private COperationMode operationMode;

	/**
	 * Attribute that represents the proxy host.
	 */
	private String hostProxy;

	/**
	 * Attribute that represents the proxy port.
	 */
	private Long portProxy;

	/**
	 * Attribute that represents the user.
	 */
	private String userProxy;

	/**
	 * Attribute that represents the password.
	 */
	private String passwordProxy;

	/**
	 * Attribute that represents the user's domain.
	 */
	private String userDomain;

	/**
	 * Attribute that represents the list of addresses that the proxy will not use.
	 */
	private String addressList;

	/**
	 * Attribute that indicates if the route is local or not.
	 */
	private Boolean isLocalAddress;

	/**
	 * Gets the value of the attribute {@link #idProxy}.
	 * @return the value of the attribute {@link #idProxy}.
	 */
	@Id
	@Column(name = "ID_PROXY", unique = true, nullable = false, precision = NumberConstants.NUM19)
	public Long getIdProxy() {
		return idProxy;
	}

	/**
	 * Sets the value of the attribute {@link #idProxy}.
	 * @param idProxyParam The value for the attribute {@link #idProxy}.
	 */
	public void setIdProxy(Long idProxyParam) {
		this.idProxy = idProxyParam;
	}

	/**
	 * Gets the value of the attribute {@link #operationMode}.
	 * @return the value of the attribute {@link #operationMode}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OPERATION_MODE", nullable = false)
	public COperationMode getOperationMode() {
		return operationMode;
	}

	/**
	 * Sets the value of the attribute {@link #operationMode}.
	 * @param operationModeParam The value for the attribute {@link #operationMode}.
	 */
	public void setOperationMode(COperationMode operationModeParam) {
		this.operationMode = operationModeParam;
	}

	/**
	 * Gets the value of the attribute {@link #hostProxy}.
	 * @return the value of the attribute {@link #hostProxy}.
	 */
	@Column(name = "HOST_PROXY", nullable = false, precision = NumberConstants.NUM200)
	public String getHostProxy() {
		return hostProxy;
	}

	/**
	 * Sets the value of the attribute {@link #hostProxy}.
	 * @param hostProxyParam The value for the attribute {@link #hostProxy}.
	 */
	public void setHostProxy(String hostProxyParam) {
		this.hostProxy = hostProxyParam;
	}

	/**
	 * Gets the value of the attribute {@link #portProxy}.
	 * @return the value of the attribute {@link #portProxy}.
	 */
	@Column(name = "PORT_PROXY", nullable = false, precision = NumberConstants.NUM10)
	public Long getPortProxy() {
		return portProxy;
	}

	/**
	 * Sets the value of the attribute {@link #portProxy}.
	 * @param portProxyParam The value for the attribute {@link #portProxy}.
	 */
	public void setPortProxy(Long portProxyParam) {
		this.portProxy = portProxyParam;
	}

	/**
	 * Gets the value of the attribute {@link #userProxy}.
	 * @return the value of the attribute {@link #userProxy}.
	 */
	@Column(name = "USER_PROXY", nullable = true, precision = NumberConstants.NUM200)
	public String getUserProxy() {
		return userProxy;
	}

	/**
	 * Sets the value of the attribute {@link #userProxy}.
	 * @param userProxyParam The value for the attribute {@link #userProxy}.
	 */
	public void setUserProxy(String userProxyParam) {
		this.userProxy = userProxyParam;
	}

	/**
	 * Gets the value of the attribute {@link #passwordProxy}.
	 * @return the value of the attribute {@link #passwordProxy}.
	 */
	@Column(name = "PASSWORD_PROXY", nullable = true, precision = NumberConstants.NUM200)
	public String getPasswordProxy() {
		return passwordProxy;
	}

	/**
	 * Sets the value of the attribute {@link #passwordProxy}.
	 * @param passwordProxyParam The value for the attribute {@link #passwordProxy}.
	 */
	public void setPasswordProxy(String passwordProxyParam) {
		this.passwordProxy = passwordProxyParam;
	}

	/**
	 * Gets the value of the attribute {@link #userDomain}.
	 * @return the value of the attribute {@link #userDomain}.
	 */
	@Column(name = "USER_DOMAIN", nullable = true, precision = NumberConstants.NUM200)
	public String getUserDomain() {
		return userDomain;
	}

	/**
	 * Sets the value of the attribute {@link #userDomain}.
	 * @param userDomainParam The value for the attribute {@link #userDomain}.
	 */
	public void setUserDomain(String userDomainParam) {
		this.userDomain = userDomainParam;
	}

	/**
	 * Gets the value of the attribute {@link #addressList}.
	 * @return the value of the attribute {@link #addressList}.
	 */
	@Column(name = "ADDRESS_LIST", nullable = true, precision = NumberConstants.NUM200)
	public String getAddressList() {
		return addressList;
	}

	/**
	 * Sets the value of the attribute {@link #addressList}.
	 * @param addressListParam The value for the attribute {@link #addressList}.
	 */
	public void setAddressList(String addressListParam) {
		this.addressList = addressListParam;
	}

	/**
	 * Gets the value of the attribute {@link #isLocalAddress}.
	 * @return the value of the attribute {@link #isLocalAddress}.
	 */
	@Column(name = "IS_LOCAL_ADDRESS", nullable = false, precision = 1)
	@Type(type = "yes_no")
	public Boolean getIsLocalAddress() {
		return isLocalAddress;
	}

	/**
	 * Sets the value of the attribute {@link #isLocalAddress}.
	 * @param isLocalAddressParam The value for the attribute {@link #isLocalAddress}.
	 */
	public void setIsLocalAddress(Boolean isLocalAddressParam) {
		this.isLocalAddress = isLocalAddressParam;
	}

}
