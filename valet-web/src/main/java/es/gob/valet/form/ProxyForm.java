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
 * <b>File:</b><p>es.gob.valet.form.ProxyForm.java.</p>
 * <b>Description:</b><p> Class that represents the backing form for editing proxy settings.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15 oct. 2018.
 */
package es.gob.valet.form;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.form.TslForm.View;

/** 
 * <p>Class that represents the backing form for editing proxy settings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15 oct. 2018.
 */
public class ProxyForm {

	/**
	 * Attribute that represents the proxy identifier.
	 */
	private Long idProxy;
	/**
	 * Attribute that represents the identifier of the selected operating mode.
	 */
	private Long idOperationMode;
	/**
	 * Attribute that represents the proxy host.
	 */
	private String host;
	/**
	 * Attribute that represents the proxy port.
	 */
	private Long port;
	/**
	 * Attribute that represents the authentication user.
	 */
	private String user;
	/**
	 * Attribute that represents the authentication password.
	 */
	private String password;
	/**
	 * Attribute that represents the user's domain.
	 */
	private String userDomain;
	/**
	 * Attribute that represents the list of addresses that the proxy will not use.
	 */
	private String addressList;
	/**
	 * Attribute that indicates if the route is localor not.
	 */
	private Boolean isLocalAddress;
	/**
	 * Attribute that represents the variable where the ok messages will be stored.
	 */
	private String msgOk;

	/**
	 * Attribute that represents the variable where the error messages will be stored.
	 */
	private String error;

	
	/**
	 * Constructor method for the class ProxyForm.java. 
	 */
	public ProxyForm() {
		super();
	}


	/**
	 * Gets the value of the attribute {@link #idProxy}.
	 * @return the value of the attribute {@link #idProxy}.
	 */
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
	 * Gets the value of the attribute {@link #idOperationMode}.
	 * @return the value of the attribute {@link #idOperationMode}.
	 */
	public Long getIdOperationMode() {
		return idOperationMode;
	}

	
	/**
	 * Sets the value of the attribute {@link #idOperationMode}.
	 * @param idOperationModeParam The value for the attribute {@link #idOperationMode}.
	 */
	public void setIdOperationMode(Long idOperationModeParam) {
		this.idOperationMode = idOperationModeParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #host}.
	 * @return the value of the attribute {@link #host}.
	 */
	public String getHost() {
		return host;
	}

	
	/**
	 * Sets the value of the attribute {@link #host}.
	 * @param hostParam The value for the attribute {@link #host}.
	 */
	public void setHost(String hostParam) {
		this.host = hostParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #port}.
	 * @return the value of the attribute {@link #port}.
	 */
	public Long getPort() {
		return port;
	}

	
	/**
	 * Sets the value of the attribute {@link #port}.
	 * @param portParam The value for the attribute {@link #port}.
	 */
	public void setPort(Long portParam) {
		this.port = portParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #user}.
	 * @return the value of the attribute {@link #user}.
	 */
	public String getUser() {
		return user;
	}
	public interface View {
	}	
	
	/**
	 * Sets the value of the attribute {@link #user}.
	 * @param userParam The value for the attribute {@link #user}.
	 */
	public void setUser(String userParam) {
		this.user = userParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #password}.
	 * @return the value of the attribute {@link #password}.
	 */
	public String getPassword() {
		return password;
	}

	
	/**
	 * Sets the value of the attribute {@link #password}.
	 * @param passwordParam The value for the attribute {@link #password}.
	 */
	public void setPassword(String passwordParam) {
		this.password = passwordParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #userDomain}.
	 * @return the value of the attribute {@link #userDomain}.
	 */
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

	
	/**
	 * Gets the value of the attribute {@link #msgOk}.
	 * @return the value of the attribute {@link #msgOk}.
	 */
	public String getMsgOk() {
		return msgOk;
	}

	
	/**
	 * Sets the value of the attribute {@link #msgOk}.
	 * @param msgOkParam The value for the attribute {@link #msgOk}.
	 */
	public void setMsgOk(String msgOkParam) {
		this.msgOk = msgOkParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #error}.
	 * @return the value of the attribute {@link #error}.
	 */
	@JsonView(View.class)
	public String getError() {
		return error;
	}

	
	/**
	 * Sets the value of the attribute {@link #error}.
	 * @param errorParam The value for the attribute {@link #error}.
	 */
	public void setError(String errorParam) {
		this.error = errorParam;
	}

}
