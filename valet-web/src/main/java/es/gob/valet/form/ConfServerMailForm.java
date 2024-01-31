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
 * <b>File:</b><p>es.gob.valet.form.ConfServerMailForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form for adding/editing a server mail.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 30/01/2024.
 */
package es.gob.valet.form;

import javax.persistence.Column;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that represents the backing form for adding/editing a server mail.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 30/01/2024.
 */
public class ConfServerMailForm {

	/**
	 * Attribute that represents the value of the primary key as a hidden input
	 * in the form.
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
	 * the authentication.
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
	 * Attribute that represents the password.
	 */
	private Boolean newPassword = Boolean.FALSE;
	
	/**
	 * Attribute that represents the variable where the ok messages will be stored.
	 */
	private String msgOk;

	/**
	 * Attribute that represents the variable where the error messages will be stored.
	 */
	private String error;
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
	private Boolean tlsEnabled = false;

	/**
	 * Attribute that represents file of certificate.
	 */
	private byte[ ] certificateFile;

	/**
	 * Attribute that represents the original name file of certificate.
	 */
	private String originalNameFile;

	/**
	 * Gets the value of the attribute {@link #idConfServerMail}.
	 * @return the value of the attribute {@link #idConfServerMail}.
	 */
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
	 * Gets the value of the attribute {@link #issuer}.
	 * @return the value of the attribute {@link #issuer}.
	 */
	public String getIssuerMail() {
		return issuerMail;
	}

	/**
	 * Sets the value of the attribute {@link #issuer}.
	 * @param issuerMailParam The value for the attribute {@link #issuer}.
	 */
	public void setIssuerMail(String issuerMailParam) {
		this.issuerMail = issuerMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #host}.
	 * @return the value of the attribute {@link #host}.
	 */
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
	 * Gets the value of the attribute {@link #port}.
	 * @return the value of the attribute {@link #port}.
	 */
	public Long getPortMail() {
		return portMail;
	}

	/**
	 * Sets the value of the attribute {@link #port}.
	 * @param portMailParam The value for the attribute {@link #port}.
	 */
	public void setPortMail(Long portMailParam) {
		this.portMail = portMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #useAuthenticationMail}.
	 * @return the value of the attribute {@link #useAuthenticationMail}.
	 */
	public final Boolean getUseAuthenticationMail() {
		return useAuthenticationMail;
	}

	/**
	 * Sets the value of the attribute {@link #useAuthenticationMail}.
	 * @param useAuthenticationMailParam The value for the attribute {@link #useAuthenticationMail}.
	 */
	public final void setUseAuthenticationMail(Boolean useAuthenticationMailParam) {
		this.useAuthenticationMail = useAuthenticationMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #user}.
	 * @return the value of the attribute {@link #user}.
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * Sets the value of the attribute {@link #user}.
	 * @param userMailParam The value for the attribute {@link #user}.
	 */
	public void setUserMail(String userMailParam) {
		this.userMail = userMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #password}.
	 * @return the value of the attribute {@link #password}.
	 */
	public String getPasswordMail() {
		return passwordMail;
	}

	/**
	 * Sets the value of the attribute {@link #password}.
	 * @param passwordMailParam The value for the attribute {@link #password}.
	 */
	public void setPasswordMail(String passwordMailParam) {
		this.passwordMail = passwordMailParam;
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
	 * @param msgOk The value for the attribute {@link #msgOk}.
	 */
	public void setMsgOk(String msgOk) {
		this.msgOk = msgOk;
	}

	
	/**
	 * Gets the value of the attribute {@link #error}.
	 * @return the value of the attribute {@link #error}.
	 */
	public String getError() {
		return error;
	}

	
	/**
	 * Sets the value of the attribute {@link #error}.
	 * @param error The value for the attribute {@link #error}.
	 */
	public void setError(String error) {
		this.error = error;
	}


	
	
	/**
	 * Gets the value of the attribute {@link #newPassword}.
	 * @return the value of the attribute {@link #newPassword}.
	 */
	public Boolean getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the value of the attribute {@link #newPassword}.
	 * @param newPassword The value for the attribute {@link #newPassword}.
	 */
	public void setNewPassword(Boolean newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the value of the attribute {@link #connectionTimeout}.
	 * @return the value of the attribute {@link #connectionTimeout}.
	 */
	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * Sets the value of the attribute {@link #connectionTimeout}.
	 * @param connectionTimeout The value for the attribute {@link #connectionTimeout}.
	 */
	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * Gets the value of the attribute {@link #readingTimeout}.
	 * @return the value of the attribute {@link #readingTimeout}.
	 */
	public Integer getReadingTimeout() {
		return readingTimeout;
	}

	/**
	 * Sets the value of the attribute {@link #readingTimeout}.
	 * @param readingTimeout The value for the attribute {@link #readingTimeout}.
	 */
	public void setReadingTimeout(Integer readingTimeout) {
		this.readingTimeout = readingTimeout;
	}

	/**
	 * Gets the value of the attribute {@link #tlsEnabled}.
	 * @return the value of the attribute {@link #tlsEnabled}.
	 */
	public Boolean getTlsEnabled() {
		return tlsEnabled;
	}
	
	/**
	 * Sets the value of the attribute {@link #tlsEnabled}.
	 * @param tlsEnabledParam The value for the attribute {@link #tlsEnabled}.
	 */
	public void setTlsEnabled(Boolean tlsEnabledParam) {
		this.tlsEnabled = tlsEnabledParam;
	}

	/**
	 * Gets the value of the attribute {@link #certificateFile}.
	 * @return the value of the attribute {@link #certificateFile}.
	 */
	public byte[ ] getCertificateFile() {
		return certificateFile;
	}

	/**
	 * Sets the value of the attribute {@link #certificateFile}.
	 * @param certificateFile The value for the attribute {@link #certificateFile}.
	 */
	public void setCertificateFile(byte[ ] certificateFile) {
		this.certificateFile = certificateFile;
	}

	/**
	 * Gets the value of the attribute {@link #originalNameFile}.
	 * @return the value of the attribute {@link #originalNameFile}.
	 */
	public String getOriginalNameFile() {
		return originalNameFile;
	}

	/**
	 * Sets the value of the attribute {@link #originalNameFile}.
	 * @param originalNameFile The value for the attribute {@link #originalNameFile}.
	 */
	public void setOriginalNameFile(String originalNameFile) {
		this.originalNameFile = originalNameFile;
	}

}
