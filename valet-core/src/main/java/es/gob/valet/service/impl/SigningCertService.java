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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.SigningCertService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for Signing Certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.2, 28/03/2025.
 */
package es.gob.valet.service.impl;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.commons.utils.AESCipher;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsKeystore;
import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;
import es.gob.valet.persistence.configuration.model.entity.SigningCertificate;
import es.gob.valet.persistence.configuration.model.repository.SigningCertificateRepository;
import es.gob.valet.service.ifaces.ISigningCertService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for ExternalAccess.</p>
 * <b>Project:</b><p> Class that implements the communication with the operations of the persistence layer for Signing Certificate.</p>
 * @version 1.2, 28/03/2025.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SigningCertService implements ISigningCertService {
	
	/**
	 * Constant that represents the parameter 'idProxy'.
	 */
	private static final Long ID_SIGNING_CERTIFICATE = 1L;

	/**
	 * Repository for managing SigningCertificate entities. This is automatically injected by Spring.
     */
	@Autowired
	private SigningCertificateRepository signingCertificateRepository;
	
	/**
	 * Constant representing the issuer of the certificate.
	 */
	public static final String ISSUER = "issuer";

	/**
	 * Constant representing the subject of the certificate.
	 */
	public static final String SUBJECT = "subject";

	/**
	 * Constant representing the serial number of the certificate.
	 */
	public static final String SERIAL_NUMBER = "serialNumber";

	/**
	 * Constant representing the expiration date of the certificate.
	 */
	public static final String DATE_EXPIRED = "dateExpired";

	/**
	 * Constant representing the start date (valid from) of the certificate.
	 */
	public static final String VALID_FROM = "validFrom";

	/**
	 * Constant representing the end date (valid to) of the certificate.
	 */
	public static final String VALID_TO = "validTo";

	/**
	 * Constant representing the country from the certificate's subject.
	 */
	public static final String COUNTRY = "country";

	/**
	 * Constant representing the Base64 encoded certificate.
	 */
	public static final String CERTIFICATE_B64 = "certificateB64";
	    
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.es.gob.valet.service.ifaces.ISigningCertService#obtainSigningCertificate()
	 */
	public SigningCertificateDTO obtainSigningCertificate() throws CipherException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, CommonUtilsException {
		SigningCertificateDTO signingCertificateDTO = new SigningCertificateDTO();
		SigningCertificate signingCertificate = signingCertificateRepository.findByIdSigningCertificate(ID_SIGNING_CERTIFICATE);
		if(signingCertificate != null) {
			byte[] byteKeystore = Base64.getDecoder().decode(signingCertificate.getKeystore());
			String passwordKeystore = AESCipher.getInstance().decryptMessageBC(signingCertificate.getKeystorePassword());
			KeyStore keyStore = UtilsKeystore.loadKsPKCS12(byteKeystore, passwordKeystore);
			X509Certificate x509Certificate = UtilsKeystore.listAllX509Certificate(keyStore).get(NumberConstants.NUM0);
			Map<String, String> mapCertificate = this.getCertificateDetailsMap(x509Certificate);
			signingCertificateDTO = new SigningCertificateDTO(signingCertificate.getIdSigningCertificate(), mapCertificate.get(ISSUER), mapCertificate.get(SUBJECT), mapCertificate.get(SERIAL_NUMBER), mapCertificate.get(DATE_EXPIRED), mapCertificate.get(CERTIFICATE_B64));
		}
		return signingCertificateDTO;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ISigningCertService#obtainSigningCertificate(java.security.cert.X509Certificate)
	 */
	public SigningCertificateDTO obtainSigningCertificate(X509Certificate signingCertificate) throws CertificateEncodingException, CommonUtilsException {
		Map<String, String> mapCertificate = this.getCertificateDetailsMap(signingCertificate);
		SigningCertificateDTO signingCertificateDTO  = new SigningCertificateDTO(mapCertificate.get(ISSUER), mapCertificate.get(SUBJECT), mapCertificate.get(CERTIFICATE_B64), mapCertificate.get(VALID_FROM), mapCertificate.get(VALID_TO), mapCertificate.get(COUNTRY));
		return signingCertificateDTO;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ISigningCertService#getCertificateDetailsMap(java.security.cert.X509Certificate)
	 */
    public Map<String, String> getCertificateDetailsMap(X509Certificate x509Certificate) throws CommonUtilsException, CertificateEncodingException {
        Map<String, String> certificateDetails = new HashMap<>();
        
        certificateDetails.put(ISSUER, UtilsCertificate.getCertificateIssuerId(x509Certificate));
        certificateDetails.put(SUBJECT, UtilsCertificate.getCertificateId(x509Certificate));
        certificateDetails.put(SERIAL_NUMBER, UtilsCertificate.getCertificateSerialNumber(x509Certificate).toString());
        certificateDetails.put(DATE_EXPIRED, new SimpleDateFormat(UtilsDate.FORMAT_DATE_INVERTED).format(x509Certificate.getNotAfter()));
        certificateDetails.put(VALID_FROM, new SimpleDateFormat(UtilsDate.FORMAT_DATE_TIME_STANDARD).format(x509Certificate.getNotBefore()));
        certificateDetails.put(VALID_TO, new SimpleDateFormat(UtilsDate.FORMAT_DATE_TIME_STANDARD).format(x509Certificate.getNotAfter()));
        certificateDetails.put(COUNTRY, UtilsCertificate.getRDNFirstValueFromX500Principal(x509Certificate.getSubjectX500Principal(), X509ObjectIdentifiers.countryName));
        certificateDetails.put(CERTIFICATE_B64, Base64.getEncoder().encodeToString(x509Certificate.getEncoded()));

        return certificateDetails;
    }

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.es.gob.valet.service.ifaces.ISigningCertService#updateSigningCert(java.lang.String, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public SigningCertificateDTO updateSigningCert(String passwordKeystore, MultipartFile keystoreFile) throws CipherException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, CommonUtilsException {
		SigningCertificate signingCertificate = signingCertificateRepository.findByIdSigningCertificate(ID_SIGNING_CERTIFICATE);
		if(signingCertificate == null) {
			signingCertificate = new SigningCertificate();
			signingCertificate.setIdSigningCertificate(ID_SIGNING_CERTIFICATE);
		}
		
		KeyStore keyStore = UtilsKeystore.loadKsPKCS12(keystoreFile.getBytes(), passwordKeystore);
		X509Certificate x509Certificate = UtilsKeystore.listAllX509Certificate(keyStore).get(NumberConstants.NUM0);
		String issuer = UtilsCertificate.getCertificateIssuerId(x509Certificate);
		String subject = UtilsCertificate.getCertificateId(x509Certificate);
		String serialNumber = UtilsCertificate.getCertificateSerialNumber(x509Certificate).toString();
		String dateExpired = new SimpleDateFormat(UtilsDate.FORMAT_DATE_TIME_STANDARD).format(x509Certificate.getNotAfter());
		String certificateB64 = Base64.getEncoder().encodeToString(x509Certificate.getEncoded());
		
		signingCertificate.setDateExpired(x509Certificate.getNotAfter());
		signingCertificate.setIssuer(issuer);
		signingCertificate.setSubject(subject);
		signingCertificate.setSerialNumber(serialNumber);
		signingCertificate.setKeystore(Base64.getEncoder().encodeToString(UtilsKeystore.getKeyStoreBytes(keyStore, passwordKeystore.toCharArray())));
		signingCertificate.setKeystorePassword(AESCipher.getInstance().encryptMessageWithBC(passwordKeystore));
		
		signingCertificateRepository.save(signingCertificate);
		
		SigningCertificateDTO signingCertificateDTO = new SigningCertificateDTO(signingCertificate.getIdSigningCertificate(), issuer, subject, serialNumber, dateExpired, certificateB64);
		
		return signingCertificateDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.es.gob.valet.service.ifaces.ISigningCertService#deleteSigningCert()
	 */
	@Transactional
	public void deleteSigningCert() {
		signingCertificateRepository.deleteByIdSigningCertificate(NumberConstants.NUM1_LONG);
	}
	
}
