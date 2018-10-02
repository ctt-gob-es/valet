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
 * <b>File:</b><p>es.gob.valet.form.SystemCertificateForm.java.</p>
 * <b>Description:</b><p> Class that represents the backing form for adding/editing certificates in a keystore.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>19 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19 sept. 2018.
 */
package es.gob.valet.form;

import java.security.cert.X509Certificate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.form.MappingTslForm.View;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/** 
 * <p>Class that represents the backing form for adding/editing certificates in a keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 19 sept. 2018.
 */
public class SystemCertificateForm {
	/**
	 * Attribute that represetns the ID of the certificate.
	 */
	private Long idSystemCertificate;
	
	/**
	 * Attribute that represents the id of keystore where this system certificate is stored.
	 */
	private Long idKeystore;

	
	/**
	 * Attribute that represents the name of the keystore.
	 */
	private String nameKeystore;
	
	/**
	 * Attribute that represents the alias of the certificate.
	 */
	private String alias;
	
	/**
	 * Attribute that represents 
	 */
	private MultipartFile certificateFile;
	
	/**
	 * Attribute that represents the subject of the certificate.
	 */
	private String subject;
	/**
	 * Attribute that represents the issuer of the certificate.
	 */
	private String issuer;
	/**
	 * Attribute that represents the number serial of the certificate.
	 */
	
	private String numberSerial;
	
	/**
	 * Attribute that represents the date since the certificate is valid.
	 */
	private String validFrom;
	
	/**
	 * Attribute that represents the date To the certificate is valid.
	 */
	private String validTo;
	
	/**
	 * Attribute that represents public key of the certificate.
	 */
	private String publicKey; 
	/**
	 * Attribute that represents index of the row of the selected certificate.
	 */
	private String rowIndexCertificate;

	/**
	 * Attribute that represents index of the row of the selected certificate.
	 */
	private X509Certificate x509Certificate;

	
	/**
	 * Gets the value of the attribute {@link #idSystemCertificate}.
	 * @return the value of the attribute {@link #idSystemCertificate}.
	 */
	public Long getIdSystemCertificate() {
		return idSystemCertificate;
	}

	
	/**
	 * Sets the value of the attribute {@link #idSystemCertificate}.
	 * @param idSystemCertificate The value for the attribute {@link #idSystemCertificate}.
	 */
	public void setIdSystemCertificate(Long idSystemCertificate) {
		this.idSystemCertificate = idSystemCertificate;
	}

	
	/**
	 * Gets the value of the attribute {@link #idKeystore}.
	 * @return the value of the attribute {@link #idKeystore}.
	 */
	public Long getIdKeystore() {
		return idKeystore;
	}

	
	/**
	 * Sets the value of the attribute {@link #idKeystore}.
	 * @param idKeystore The value for the attribute {@link #idKeystore}.
	 */
	public void setIdKeystore(Long idKeystore) {
		this.idKeystore = idKeystore;
	}

	
	/**
	 * Gets the value of the attribute {@link #nameKeystore}.
	 * @return the value of the attribute {@link #nameKeystore}.
	 */
	public String getNameKeystore() {
		return nameKeystore;
	}

	
	/**
	 * Sets the value of the attribute {@link #nameKeystore}.
	 * @param nameKeystore The value for the attribute {@link #nameKeystore}.
	 */
	public void setNameKeystore(String nameKeystore) {
		this.nameKeystore = nameKeystore;
	}

	
	/**
	 * Gets the value of the attribute {@link #alias}.
	 * @return the value of the attribute {@link #alias}.
	 */
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

	
	/**
	 * Gets the value of the attribute {@link #certificateFile}.
	 * @return the value of the attribute {@link #certificateFile}.
	 */
	public MultipartFile getCertificateFile() {
		return certificateFile;
	}

	
	/**
	 * Sets the value of the attribute {@link #certificateFile}.
	 * @param certificateFile The value for the attribute {@link #certificateFile}.
	 */
	public void setCertificateFile(MultipartFile certificateFile) {
		this.certificateFile = certificateFile;
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
	 * Gets the value of the attribute {@link #numberSerial}.
	 * @return the value of the attribute {@link #numberSerial}.
	 */
	public String getNumberSerial() {
		return numberSerial;
	}


	
	/**
	 * Sets the value of the attribute {@link #numberSerial}.
	 * @param numberSerial The value for the attribute {@link #numberSerial}.
	 */
	public void setNumberSerial(String numberSerial) {
		this.numberSerial = numberSerial;
	}


	
	/**
	 * Gets the value of the attribute {@link #validFrom}.
	 * @return the value of the attribute {@link #validFrom}.
	 */
	@JsonView(View.class)
	public String getValidFrom() {
		return validFrom;
	}


	
	/**
	 * Sets the value of the attribute {@link #validFrom}.
	 * @param validFrom The value for the attribute {@link #validFrom}.
	 */
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}


	
	/**
	 * Gets the value of the attribute {@link #validTo}.
	 * @return the value of the attribute {@link #validTo}.
	 */
	@JsonView(View.class)
	public String getValidTo() {
		return validTo;
	}


	
	/**
	 * Sets the value of the attribute {@link #validTo}.
	 * @param validTo The value for the attribute {@link #validTo}.
	 */
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}




	/**
	 * Gets the value of the attribute {@link #publicKey}.
	 * @return the value of the attribute {@link #publicKey}.
	 */
	public String getPublicKey() {
		return publicKey;
	}


	
	/**
	 * Sets the value of the attribute {@link #publicKey}.
	 * @param publicKey The value for the attribute {@link #publicKey}.
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}


	
	/**
	 * Gets the value of the attribute {@link #rowIndexCertificate}.
	 * @return the value of the attribute {@link #rowIndexCertificate}.
	 */
	@JsonView(View.class)
	public String getRowIndexCertificate() {
		return rowIndexCertificate;
	}


	
	/**
	 * Sets the value of the attribute {@link #rowIndexCertificate}.
	 * @param rowIndexCertificate The value for the attribute {@link #rowIndexCertificate}.
	 */
	public void setRowIndexCertificate(String rowIndexCertificate) {
		this.rowIndexCertificate = rowIndexCertificate;
	}


	
	/**
	 * Gets the value of the attribute {@link #x509Certificate}.
	 * @return the value of the attribute {@link #x509Certificate}.
	 */
	@JsonView(View.class)
	public X509Certificate getX509Certificate() {
		return x509Certificate;
	}


	
	/**
	 * Sets the value of the attribute {@link #x509Certificate}.
	 * @param x509Certificate The value for the attribute {@link #x509Certificate}.
	 */
	public void setX509Certificate(X509Certificate x509Certificate) {
		this.x509Certificate = x509Certificate;
	}
	

	
	
	

}
