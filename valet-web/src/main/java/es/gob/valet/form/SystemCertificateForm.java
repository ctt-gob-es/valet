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
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/09/2018
 */
package es.gob.valet.form;

import java.security.cert.X509Certificate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.form.MappingTslForm.View;

/** 
 * <p>Class that represents the backing form for adding/editing certificates in a keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/09/2018.
 */
public class SystemCertificateForm {
	/**
	 * Attribute that represents the ID of the certificate.
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
	 * Attribute that represents file certificate.
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
	 * Attribute that represents the country of certificate
	 */
	private String country;
	
	/**
	 * Gets the value of the attribute {@link #idSystemCertificate}.
	 * @return the value of the attribute {@link #idSystemCertificate}.
	 */
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
	 * Gets the value of the attribute {@link #idKeystore}.
	 * @return the value of the attribute {@link #idKeystore}.
	 */
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
	 * Gets the value of the attribute {@link #nameKeystore}.
	 * @return the value of the attribute {@link #nameKeystore}.
	 */
	public String getNameKeystore() {
		return nameKeystore;
	}

	
	/**
	 * Sets the value of the attribute {@link #nameKeystore}.
	 * @param nameKeystoreParam The value for the attribute {@link #nameKeystore}.
	 */
	public void setNameKeystore(String nameKeystoreParam) {
		this.nameKeystore = nameKeystoreParam;
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
	 * @param aliasParam The value for the attribute {@link #alias}.
	 */
	public void setAlias(String aliasParam) {
		this.alias = aliasParam;
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
	 * @param certificateFileParam The value for the attribute {@link #certificateFile}.
	 */
	public void setCertificateFile(MultipartFile certificateFileParam) {
		this.certificateFile = certificateFileParam;
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
	 * @param subjectParam The value for the attribute {@link #subject}.
	 */
	public void setSubject(String subjectParam) {
		this.subject = subjectParam;
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
	 * @param issuerParam The value for the attribute {@link #issuer}.
	 */
	public void setIssuer(String issuerParam) {
		this.issuer = issuerParam;
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
	 * @param numberSerialParam The value for the attribute {@link #numberSerial}.
	 */
	public void setNumberSerial(String numberSerialParam) {
		this.numberSerial = numberSerialParam;
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
	 * @param validFromParam The value for the attribute {@link #validFrom}.
	 */
	public void setValidFrom(String validFromParam) {
		this.validFrom = validFromParam;
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
	 * @param validToParam The value for the attribute {@link #validTo}.
	 */
	public void setValidTo(String validToParam) {
		this.validTo = validToParam;
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
	 * @param publicKeyParam The value for the attribute {@link #publicKey}.
	 */
	public void setPublicKey(String publicKeyParam) {
		this.publicKey = publicKeyParam;
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
	 * @param rowIndexCertificateParam The value for the attribute {@link #rowIndexCertificate}.
	 */
	public void setRowIndexCertificate(String rowIndexCertificateParam) {
		this.rowIndexCertificate = rowIndexCertificateParam;
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
	 * @param x509CertificateParam The value for the attribute {@link #x509Certificate}.
	 */
	public void setX509Certificate(X509Certificate x509CertificateParam) {
		this.x509Certificate = x509CertificateParam;
	}

	
	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public String getCountry() {
		return country;
	}


	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param x509CertificateParam The value for the attribute {@link #country}.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}
