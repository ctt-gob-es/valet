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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Keystore.java.</p>
 * <b>Description:</b><p>Class that maps the <i>KEYSTORE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>KEYSTORE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
@Entity
@Table(name = "KEYSTORE")
public class Keystore implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -5704821671476223968L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idKeystore;

	/**
	 * Attribute that represents the name of the keystore.
	 */
	private String name;

	/**
	 * Attribute that represents the name of the token with the description stored in properties file for internationalization.
	 */
	private String tokenName;

	/**
	 * Attribute that represents the list of system certificates stored in the keystore.
	 */
	private List<SystemCertificate> listSystemCertificates;

	/**
	 * Attribute that represents the keystore.
	 */
	private byte[ ] keystore;

	/**
	 * Attribute that represents the password of the keystore.
	 */
	private String password;

	/**
	 * Attribute that indicates if the keystore is hardware (true) or not (false).
	 */
	private Boolean isHardware;

	/**
	 * Attribute that represents the type of the keystore.
	 */
	private String keystoreType;

	/**
	 * Attribute that represents the number of modifications for the initial keystore.
	 */
	private Long version;

	/**
	 * Attribute that indicates if the entries of the hardware keystore must be stored into the database and the HSM (true) or only into the HSM (false).
	 */
	private Boolean hasBackup;

	/**
	 * Gets the value of the attribute {@link #idKeystore}.
	 * @return the value of the attribute {@link #idKeystore}.
	 */
	@Id
	@Column(name = "ID_KEYSTORE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	public Long getIdKeystore() {
		return idKeystore;
	}

	/**
	 * Sets the value of the attribute {@link #idKeystore}.
	 * @param idKeystoreParam The value for the attribute {@link #idKeystore}.
	 */
	public void setIdKeystore(Long idKeystoreParam) {
		this.idKeystore = idKeystoreParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	@Column(name = "NAME", nullable = false, length = NumberConstants.NUM150, unique = true)
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public void setName(String nameParam) {
		this.name = nameParam;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	@Column(name = "TOKEN_NAME", nullable = false, length = NumberConstants.NUM30)
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param tokenNameParam The value for the attribute {@link #tokenName}.
	 */
	public void setTokenName(String tokenNameParam) {
		this.tokenName = tokenNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #listSystemCertificates}.
	 * @return the value of the attribute {@link #listSystemCertificates}.
	 */
	@OneToMany(mappedBy = "keystore", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	public List<SystemCertificate> getListSystemCertificates() {
		return listSystemCertificates;
	}

	/**
	 * Sets the value of the attribute {@link #listSystemCertificates}.
	 * @param listSystemCertificatesParam The value for the attribute {@link #listSystemCertificates}.
	 */
	public void setListSystemCertificates(List<SystemCertificate> listSystemCertificatesParam) {
		this.listSystemCertificates = listSystemCertificatesParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystore}.
	 * @return the value of the attribute {@link #keystore}.
	 */
	@Lob()
	@Column(name = "KEYSTORE")
	public byte[ ] getKeystore() {
		return keystore;
	}

	/**
	 * Sets the value of the attribute {@link #keystore}.
	 * @param keystoreParam The value for the attribute {@link #keystore}.
	 */
	public void setKeystore(byte[ ] keystoreParam) {
		if (keystoreParam != null) {
			this.keystore = keystoreParam.clone();
		}
	}

	/**
	 * Gets the value of the attribute {@link #password}.
	 * @return the value of the attribute {@link #password}.
	 */
	@Column(name = "PASSWORD", nullable = false, length = NumberConstants.NUM255)
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
	 * Gets the value of the attribute {@link #isHardware}.
	 * @return the value of the attribute {@link #isHardware}.
	 */
	@Column(name = "IS_HARDWARE", nullable = false, precision = 1)
	@Type(type = "yes_no")
	public Boolean getIsHardware() {
		return isHardware;
	}

	/**
	 * Sets the value of the attribute {@link #isHardware}.
	 * @param isHardwareParam The value for the attribute {@link #isHardware}.
	 */
	public void setIsHardware(Boolean isHardwareParam) {
		this.isHardware = isHardwareParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystoreType}.
	 * @return the value of the attribute {@link #keystoreType}.
	 */
	public String getKeystoreType() {
		return keystoreType;
	}

	/**
	 * Sets the value of the attribute {@link #keystoreType}.
	 * @param keystoreTypeParam The value for the attribute {@link #keystoreType}.
	 */
	@Column(name = "KEYSTORE_TYPE", nullable = false, length = NumberConstants.NUM50)
	public void setKeystoreType(String keystoreTypeParam) {
		this.keystoreType = keystoreTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	@Column(name = "VERSION", nullable = false, precision = NumberConstants.NUM19)
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param versionParam The value for the attribute {@link #version}.
	 */
	public void setVersion(Long versionParam) {
		this.version = versionParam;
	}

	/**
	 * Gets the value of the attribute {@link #hasBackup}.
	 * @return the value of the attribute {@link #hasBackup}.
	 */
	@Column(name = "HAS_BACKUP", nullable = false, precision = 1)
	@Type(type = "yes_no")
	public Boolean getHasBackup() {
		return hasBackup;
	}

	/**
	 * Sets the value of the attribute {@link #hasBackup}.
	 * @param hasBackupParam The value for the attribute {@link #hasBackup}.
	 */
	public void setHasBackup(Boolean hasBackupParam) {
		this.hasBackup = hasBackupParam;
	}

}
