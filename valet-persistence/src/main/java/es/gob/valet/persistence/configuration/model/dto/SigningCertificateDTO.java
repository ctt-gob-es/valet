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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.dto.SigningCertificateDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Signing Certificate DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.2, 28/03/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the Signing Certificate DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 28/03/2025.
 */
public class SigningCertificateDTO {

	 /** The ID of the signing certificate. */
    private Long idSigningCertificate;

    /** The issuer of the signing certificate. */
    private String issuer;

    /** The subject of the signing certificate. */
    private String subject;

    /** The serial number of the signing certificate. */
    private String serialNumber;

    /** The expiration date of the signing certificate. */
    private String dateExpired;

    /** The Base64 encoded certificate. */
    private String certificateB64;

    /** The password for the keystore associated with the signing certificate. */
    private String passwordKeystore;

    /** Error message if any occurred. */
    private String error;

    /**  
     * The start date from which the certificate is valid.  
     * This date indicates when the certificate becomes active.  
     */  
    private String validFrom;  

    /**  
     * The expiration date of the certificate.  
     * After this date, the certificate is no longer considered valid.  
     */  
    private String validTo;  

    /**  
     * The country associated with the certificate's subject.  
     * This value is typically extracted from the X.509 certificate's "C" (Country) field.  
     */  
    private String country;
    
    /**
     * Default constructor.
     */
    public SigningCertificateDTO(){}

    /**
     * Constructor to initialize a SigningCertificateDTO with certificate details.
     *
     * @param idSigningCertificate The ID of the signing certificate.
     * @param issuer The issuer of the signing certificate.
     * @param subject The subject of the signing certificate.
     * @param serialNumber The serial number of the signing certificate.
     * @param dateExpired The expiration date of the signing certificate.
     * @param certificateB64 The Base64 encoded certificate.
     */
    public SigningCertificateDTO(Long idSigningCertificate, String issuer, String subject, String serialNumber, String dateExpired, String certificateB64) {
        this.idSigningCertificate = idSigningCertificate;
        this.issuer = issuer;
        this.subject = subject;
        this.serialNumber = serialNumber;
        this.dateExpired = dateExpired;
        this.certificateB64 = certificateB64;
    }
   
    /**
     * Constructor to initialize a SigningCertificateDTO with certificate details.
     *
     * @param issuer The issuer of the signing certificate.
     * @param subject The subject of the signing certificate.
     * @param certificateB64 The Base64 encoded certificate.
     * @param validFrom The start date from which the certificate is valid.
     * @param validTo The expiration date of the certificate.
     * @param country The country associated with the certificate's subject.
     */
	public SigningCertificateDTO(String issuer, String subject, String certificateB64, String validFrom, String validTo, String country) {
		this.issuer = issuer;
        this.subject = subject;
        this.certificateB64 = certificateB64;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.country = country;
	}

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
	public String getDateExpired() {
		return dateExpired;
	}

	/**
	 * Sets the value of the attribute {@link #dateExpired}.
	 * @param dateExpired The value for the attribute {@link #dateExpired}.
	 */
	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}

	/**
	 * Gets the value of the attribute {@link #certificateB64}.
	 * @return the value of the attribute {@link #certificateB64}.
	 */
	public String getCertificateB64() {
		return certificateB64;
	}

	/**
	 * Sets the value of the attribute {@link #certificateB64}.
	 * @param certificateB64 The value for the attribute {@link #certificateB64}.
	 */
	public void setCertificateB64(String certificateB64) {
		this.certificateB64 = certificateB64;
	}

	/**
	 * Gets the value of the attribute {@link #passwordKeystore}.
	 * @return the value of the attribute {@link #passwordKeystore}.
	 */
	public String getPasswordKeystore() {
		return passwordKeystore;
	}

	/**
	 * Sets the value of the attribute {@link #passwordKeystore}.
	 * @param passwordKeystore The value for the attribute {@link #passwordKeystore}.
	 */
	public void setPasswordKeystore(String passwordKeystore) {
		this.passwordKeystore = passwordKeystore;
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
	 * Gets the value of the attribute {@link #validFrom}.
	 * @return the value of the attribute {@link #validFrom}.
	 */
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
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public String getCountry() {
		return country;
	}

	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
}
