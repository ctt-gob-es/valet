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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.SigningCertificate.java.</p>
 * <b>Description:</b><p>Class that maps the <i>SIGNING_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/03/2025.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>SIGNING_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 05/12/2018.
 */
@Entity
@Table(name = "SIGNING_CERTIFICATE")
public class SigningCertificate implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = -8120003697304598775L;

	/** The unique ID of the signing certificate. */
	@Id
	@Column(name = "ID_SIGNING_CERTIFICATE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	private Long idSigningCertificate;

	/** The issuer of the signing certificate. */
	@Column(name = "ISSUER", length = NumberConstants.NUM4000, nullable = false)
	private String issuer;

	/** The subject of the signing certificate. */
	@Column(name = "SUBJECT", length = NumberConstants.NUM4000, nullable = false)
	private String subject;

	/** The serial number of the signing certificate. */
	@Column(name = "SERIAL_NUMBER", length = NumberConstants.NUM64, nullable = false)
	private String serialNumber;

	/** The expiration date of the signing certificate. */
	@Column(name = "DATE_EXPIRED", nullable = false)
	private Date dateExpired;

	/** The keystore associated with the signing certificate, stored as a string. */
	@Column(name = "KEYSTORE", nullable = false)
	private String keystore;

	/** The password for the keystore. */
	@Column(name = "KEYSTORE_PASSWORD", nullable = false)
	private String keystorePassword;

	/**
	 * Gets the value of the attribute {@link #idSigningCertificate}.
	 * @return the value of the attribute {@link #idSigningCertificate}.
	 */
	public Long getIdSigningCertificate() {
		return idSigningCertificate;
	}

	/**
	 * Sets the value of the attribute {@link #idSigningCertificate}.
	 * @param idSigningCertificate The value for the attribute {@link #idSigningCertificate}.
	 */
	public void setIdSigningCertificate(Long idSigningCertificate) {
		this.idSigningCertificate = idSigningCertificate;
	}

	/**
	 * Gets the value of the attribute {@link #issuer}.
	 * @return the value of the attribute {@link #issuer}.
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * Sets the value of the attribute {@link #issuer}.
	 * @param issuer The value for the attribute {@link #issuer}.
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * Gets the value of the attribute {@link #subject}.
	 * @return the value of the attribute {@link #subject}.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the value of the attribute {@link #subject}.
	 * @param subject The value for the attribute {@link #subject}.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the value of the attribute {@link #serialNumber}.
	 * @return the value of the attribute {@link #serialNumber}.
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the value of the attribute {@link #serialNumber}.
	 * @param serialNumber The value for the attribute {@link #serialNumber}.
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the value of the attribute {@link #dateExpired}.
	 * @return the value of the attribute {@link #dateExpired}.
	 */
	public Date getDateExpired() {
		return dateExpired;
	}

	/**
	 * Sets the value of the attribute {@link #dateExpired}.
	 * @param dateExpired The value for the attribute {@link #dateExpired}.
	 */
	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	/**
	 * Gets the value of the attribute {@link #keystore}.
	 * @return the value of the attribute {@link #keystore}.
	 */
	public String getKeystore() {
		return keystore;
	}

	/**
	 * Sets the value of the attribute {@link #keystore}.
	 * @param keystore The value for the attribute {@link #keystore}.
	 */
	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	/**
	 * Gets the value of the attribute {@link #keystorePassword}.
	 * @return the value of the attribute {@link #keystorePassword}.
	 */
	public String getKeystorePassword() {
		return keystorePassword;
	}

	/**
	 * Sets the value of the attribute {@link #keystorePassword}.
	 * @param keystorePassword The value for the attribute {@link #keystorePassword}.
	 */
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

}
