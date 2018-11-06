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
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 *<p>Class that maps the <i>SYSTEM_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
@Entity
@Table(name = "SYSTEM_CERTIFICATE")
public class SystemCertificate implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -5360652040333180484L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idSystemCertificate;

	/**
	 * Attribute that represents the alias of the certificate.
	 */
	private String alias;

	/**
	 * Attribute that represents the keystore where this system certificate is stored.
	 */
	private Keystore keystore;

	/**
	 * Attribute that indicates if the alias is about a key (true) or a certificate (false).
	 */
	private Boolean isKey;

	/**
	 * Attribute that represents the issuer of the certificate.
	 */
	private String issuer;

	/**
	 * Attribute that represents the subject of the certificate.
	 */
	private String subject;

	/**
	 * Attribute that represents the status certificate for this certificate type.
	 */
	private CStatusCertificate statusCert;

	/**
	 * Attribute that represents the SHA-1 hash of the certificate encoded on Base 64 concatenates with the SHA-1 hash of the private key for the certificate
	 * encoded on Base 64. This attribute is used only when the key pairs is stored inside of a HSM.
	 */
	private String hash;

	/**
	 * Gets the value of the attribute {@link #idSystemCertificate}.
	 * @return the value of the attribute {@link #idSystemCertificate}.
	 */
	@Id
	@Column(name = "ID_SYSTEM_CERTIFICATE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_system_certificate_valet")
	@GenericGenerator(name = "sq_system_certificate_valet", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_SYSTEM_CERTIFICATE"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdSystemCertificate() {
		return idSystemCertificate;
	}

	/**
	 * Sets the value of the attribute {@link #idSystemCertificate}.
	 * @param idSystemCertificateParam The value for the attribute {@link #idSystemCertificate}.
	 */
	public void setIdSystemCertificate(Long idSystemCertificateParam) {
		this.idSystemCertificate = idSystemCertificateParam;
	}

	/**
	 * Gets the value of the attribute {@link #alias}.
	 * @return the value of the attribute {@link #alias}.
	 */
	@Column(name = "ALIAS", nullable = false, length = NumberConstants.NUM4000)
	@JsonView(DataTablesOutput.View.class)
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the value of the attribute {@link #alias}.
	 * @param aliasParam The value for the attribute {@link #alias}.
	 */
	public void setAlias(String aliasParam) {
		this.alias = aliasParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystore}.
	 * @return the value of the attribute {@link #keystore}.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_KEYSTORE", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public Keystore getKeystore() {
		return keystore;
	}

	/**
	 * Sets the value of the attribute {@link #keystore}.
	 * @param keystoreParam The value for the attribute {@link #keystore}.
	 */
	public void setKeystore(Keystore keystoreParam) {
		this.keystore = keystoreParam;
	}

	/**
	 * Gets the value of the attribute {@link #isKey}.
	 * @return the value of the attribute {@link #isKey}.
	 */
	@Column(name = "IS_KEY", nullable = false, precision = 1)
	@Type(type = "yes_no")
	public Boolean getIsKey() {
		return isKey;
	}

	/**
	 * Sets the value of the attribute {@link #isKey}.
	 * @param isKeyParam The value for the attribute {@link #isKey}.
	 */
	public void setIsKey(Boolean isKeyParam) {
		this.isKey = isKeyParam;
	}

	/**
	 * Gets the value of the attribute {@link #issuer}.
	 * @return the value of the attribute {@link #issuer}.
	 */
	@Column(name = "ISSUER", length = NumberConstants.NUM4000, nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public String getIssuer() {
		return issuer;
	}

	/**
	 * Sets the value of the attribute {@link #issuer}.
	 * @param issuerParam The value for the attribute {@link #issuer}.
	 */
	public void setIssuer(String issuerParam) {
		this.issuer = issuerParam;
	}

	/**
	 * Gets the value of the attribute {@link #subject}.
	 * @return the value of the attribute {@link #subject}.
	 */
	@Column(name = "SUBJECT", length = NumberConstants.NUM4000, nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the value of the attribute {@link #subject}.
	 * @param subjectParam The value for the attribute {@link #subject}.
	 */
	public void setSubject(String subjectParam) {
		this.subject = subjectParam;
	}

	/**
	 * Gets the value of the attribute {@link #statusCert}.
	 * @return the value of the attribute {@link #statusCert}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUS_CERT", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	public CStatusCertificate getStatusCert() {
		return statusCert;
	}

	/**
	 * Sets the value of the attribute {@link #statusCert}.
	 * @param statusCertParam The value for the attribute {@link #statusCert}.
	 */
	public void setStatusCert(CStatusCertificate statusCertParam) {
		this.statusCert = statusCertParam;
	}

	/**
	 * Gets the value of the attribute {@link #hash}.
	 * @return the value of the attribute {@link #hash}.
	 */
	@Column(name = "HASH", length = NumberConstants.NUM100)
	public String getHash() {
		return hash;
	}

	/**
	 * Sets the value of the attribute {@link #hash}.
	 * @param hashParam The value for the attribute {@link #hash}.
	 */
	public void setHash(String hashParam) {
		this.hash = hashParam;
	}

}
