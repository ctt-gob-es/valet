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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsCertificate.java.</p>
 * <b>Description:</b><p>Class that provides methods for managing certificates.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.9, 30/10/2024.
 */
package es.gob.valet.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;

import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;

/**
 * <p>Class that provides methods for managing certificates.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.9, 30/10/2024.
 */
public final class UtilsCertificate {

	/**
	 * Constructor method for the class UtilsCertificate.java.
	 */
	private UtilsCertificate() {
		super();
	}

	/**
	 * Constant that represents a "X.509" Certificate type.
	 */
	public static final String X509_TYPE = "X.509";

	/**
	 * Creates a X509Certificate given its content.
	 * @param certificate Certificate content.
	 * @return X509Certificate jce X509Certificate.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static X509Certificate getX509Certificate(byte[ ] certificate) throws CommonUtilsException {
		InputStream is = new ByteArrayInputStream(certificate);
		try {
			X509Certificate x509 = (X509Certificate) CertificateFactory.getInstance(X509_TYPE).generateCertificate(is);
			if(x509 != null){
				return x509;
			}else{
				throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_000));
			}
			
		} catch (CertificateException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_000), e);
		} catch (Exception e){
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_000), e);
		} 
		finally {
			UtilsResources.safeCloseInputStream(is);
		}
	}

	/**
	 * Creates a BouncyCastle X509Certificate from a java X509Certificate.
	 * @param x509cert Certificate to transform.
	 * @return BouncyCastle X509Certificate.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static org.bouncycastle.asn1.x509.Certificate getBouncyCastleCertificate(Certificate x509cert) throws CommonUtilsException {

		if (x509cert == null) {
			return null;
		} else {
			try {
				return getBouncyCastleCertificate(x509cert.getEncoded());
			} catch (CertificateEncodingException e) {
				throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_000), e);
			}
		}

	}

	/**
	 * Creates a BouncyCastle X509Certificate given its content.
	 * @param certificate Certificate content.
	 * @return BouncyCastle X509Certificate.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static org.bouncycastle.asn1.x509.Certificate getBouncyCastleCertificate(byte[ ] certificate) throws CommonUtilsException {
		try {
			return org.bouncycastle.asn1.x509.Certificate.getInstance(certificate);
		} catch (Exception e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_000), e);
		}
	}

	/**
	 * Gets certificate´s identifier (canonicalized subject).
	 * @param cert Certificate to get the identifier.
	 * @return Certificate identifier.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static String getCertificateId(X509Certificate cert) throws CommonUtilsException {
		if (cert == null) {
			return null;
		}
		String id = UtilsASN1.toString(cert.getSubjectX500Principal());
		return canonicalizarIdCertificado(id);
	}

	/**
	 * Method that canonicalizes the identifier of a certificate.
	 * @param idCertificado Parameter that represents the identifier of a certificate.
	 * @return the canonicalized identifier of the certificate.
	 */
	public static String canonicalizarIdCertificado(String idCertificado) {
		if (idCertificado.indexOf(UtilsStringChar.SYMBOL_EQUAL_STRING) != -1) {
			String[ ] campos = idCertificado.split(UtilsStringChar.SYMBOL_COMMA_STRING);
			Set<String> ordenados = new TreeSet<String>();
			StringBuffer sb = new StringBuffer();
			String[ ] pair;
			int i = 0;
			while (i < campos.length) {
				/*Puede darse el caso de que haya campos que incluyan comas, ejemplo:
				 *[OU=Class 3 Public Primary Certification Authority, O=VeriSign\\,  Inc., C=US]
				 */
				int currentIndex = i;
				// Lo primero es ver si estamos en el campo final y si el
				// siguiente campo no posee el símbolo igual, lo
				// concatenamos al actual
				while (i < campos.length - 1 && !campos[i + 1].contains(UtilsStringChar.SYMBOL_EQUAL_STRING)) {
					campos[currentIndex] += UtilsStringChar.SYMBOL_COMMA_STRING + campos[i + 1];
					i++;
				}
				sb = new StringBuffer();
				pair = campos[currentIndex].trim().split(UtilsStringChar.SYMBOL_EQUAL_STRING);
				sb.append(pair[0].toLowerCase());
				sb.append(UtilsStringChar.SYMBOL_EQUAL_STRING);
				if (pair.length == 2) {
					sb.append(pair[1]);
				}
				ordenados.add(sb.toString());
				i++;
			}
			Iterator<String> it = ordenados.iterator();
			sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(it.next());
				sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			// No es un identificador de certificado, no se canonicaliza.
			return idCertificado;
		}
	}

	/**
	 * Method that obtains the canonicalized identifier of the issuer of a certificate.
	 * @param cert Parameter that represents the certificate.
	 * @return the canonicalized identifier of the issuer of the certificate.
	 * @throws CommonUtilsException If the method fails.
	 */
	public static String getCertificateIssuerId(X509Certificate cert) throws CommonUtilsException {
		if (cert == null) {
			return null;
		}
		return canonicalizarIdCertificado(UtilsASN1.toString(cert.getIssuerX500Principal()));
	}

	/**
	 * Gets the notBefore date from the validity period of the certificate.
	 * @param x509Certificate certificate to obtain your valid date from.
	 * @return String Date from the validity period of the certificate.
	 */
	public static String getValidFrom(X509Certificate x509Certificate) {
		String validFrom = null;
		if (x509Certificate != null && x509Certificate.getNotBefore() != null) {
			Date validFromDate = x509Certificate.getNotBefore();
			validFrom = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, validFromDate);
		}
		return validFrom;
	}

	/**
	 * Gets the notAfter date from the validity period of the certificate.
	 * @param x509Certificate certificate to obtain your valid date from.
	 * @return String Date from the validity period of the certificate.
	 */
	public static String getValidTo(X509Certificate x509Certificate) {
		String validTo = null;
		if (x509Certificate != null && x509Certificate.getNotAfter() != null) {
			Date validToDate = x509Certificate.getNotAfter();
			validTo = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, validToDate);
		}
		return validTo;
	}

	/**
	 * Method that indicates whether some other certificate is "equal to" this one (<code>true</code>) or not (<code>false</code>).
	 * @param cert1 Parameter that represents the first certificate to compare.
	 * @param cert2 Parameter that represents the second certificate to compare.
	 * @return a boolean that indicates whether some other certificate is "equal to" this one (<code>true</code>) or not (<code>false</code>).
	 * @throws CommonUtilsException If there is some error getting de issuer information from the input certificates.
	 */
	public static boolean equals(X509Certificate cert1, X509Certificate cert2) throws CommonUtilsException {
		boolean res = false;
		if (cert1 != null && cert2 != null) {
			if (cert1.getPublicKey().equals(cert2.getPublicKey())) {
				String idEmisor1 = getCertificateIssuerId(cert1);
				String idEmisor2 = getCertificateIssuerId(cert2);
				if (idEmisor1 != null && idEmisor2 != null && idEmisor1.equalsIgnoreCase(idEmisor2)) {
					if (cert1.getSerialNumber() != null && cert2.getSerialNumber() != null && cert1.getSerialNumber().equals(cert2.getSerialNumber())) {
						res = true;
					} else {
						res = false;
					}
				}
			} else {
				res = false;
			}
		}
		return res;
	}

	/**
	 * Checks if a given certificate is issued by another one.
	 * @param certIssuer Issuer to check
	 * @param cert Certificate to be checked.
	 * @return <code>true</code> if the input certificate is issued by the specified issuer certificate,
	 * otherwise <code>false</code>.
	 * @throws CommonUtilsException Exception thrown if there is any problem verifying the certificates.
	 */
	public static boolean verify(X509Certificate certIssuer, X509Certificate cert) throws CommonUtilsException {
		boolean result = certIssuer != null && cert != null;
		return result && verify(certIssuer.getPublicKey(), cert) && getCertificateId(certIssuer).equals(getCertificateIssuerId(cert));
	}

	/**
	 * Checks if a given public key corresponds to the private key that signed the input certificate.
	 * @param publicKey Public key to use to verify the certificate.
	 * @param cert Certificate to check.
	 * @return <code>true</code> if the public key verifies the certificate.
	 * @throws CommonUtilsException if there is any problem verifying the certificate.
	 */
	public static boolean verify(PublicKey publicKey, X509Certificate cert) throws CommonUtilsException {
		if (publicKey == null || cert == null) {
			return false;
		}
		try {
			cert.verify(publicKey);
		} catch (InvalidKeyException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_001), e);
		} catch (CertificateException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_002), e);
		} catch (NoSuchAlgorithmException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_003), e);
		} catch (NoSuchProviderException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_004), e);
		} catch (IllegalArgumentException e) {
			throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_006), e);
		
		} catch (SignatureException e) {
			// La firma no coincide.
			return false;
		}
		return true;
	}

	/**
	 * Method that checks whether a certificate is self-signed (<code>true</code>) or not (<code>false</code>).
	 * @param cert Parameter that represents the certificate.
	 * @return a boolean that indicates whether a certificate is self-signed (<code>true</code>) or not (<code>false</code>).
	 */
	public static boolean isSelfSigned(X509Certificate cert) {
		try {
			return verify(cert, cert);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the country specified in the issuer name of the input certificate.
	 * @param x509cert X509 Certificate to analyze to obtain the country of its issuer name.
	 * @return String with the representation of the country of the certificate issuer.
	 * in ISO 3166-1. <code>null</code> in case of some error or the country is not defined.
	 */
	public static String getIssuerCountryOfTheCertificateString(X509Certificate x509cert) {

		String result = null;

		if (x509cert != null) {

			result = getRDNFirstValueFromX500Principal(x509cert.getIssuerX500Principal(), X509ObjectIdentifiers.countryName);

		}

		return result;

	}

	/**
	 * Gets the country specified in the subject name of the input certificate.
	 * @param x509cert X509 Certificate to analyze to obtain the country of its subject name.
	 * @return String with the representation of the country of the subject certificate.
	 * in ISO 3166-1. <code>null</code> in case of some error or the country is not defined.
	 */
	public static String getSubjectCountryOfTheCertificateString(X509Certificate x509cert) {

		String result = null;

		if (x509cert != null) {

			result = getRDNFirstValueFromX500Principal(x509cert.getSubjectX500Principal(), X509ObjectIdentifiers.countryName);

		}

		return result;

	}

	/**
	 * Gets the country specified in the subject name of the input certificate if it is a CA certificate, or from
	 * the issuer if it is a end certificate.
	 * @param x509cert X509 Certificate to analyze to obtain the country.
	 * @return String with the representation of the country of the certificate.
	 * in ISO 3166-1. <code>null</code> in case of some error or the country is not defined.
	 */
	public static String getCountryOfTheCertificateString(X509Certificate x509cert) {

		String result = null;

		if (x509cert != null) {

			if (isCA(x509cert)) {

				result = getSubjectCountryOfTheCertificateString(x509cert);

			} else {

				result = getIssuerCountryOfTheCertificateString(x509cert);

			}

		}

		return result;

	}

	/**
	 * Gets the RDN First ocurrence value with the OID indicated from the input X500Name.
	 * @param x500Name X.500 Name to analyze.
	 * @param rdnAsn1ObjectIdentifier Object Identifier that represents the RDN to search.
	 * @return the RDN First ocurrence value with the OID indicated from the input X500Name.
	 * <code>null</code> if some of the input parameters are <code>null</code> or there is some
	 * error analyzing the X.500 Name.
	 */
	public static String getRDNFirstValueFromX500Name(X500Name x500Name, ASN1ObjectIdentifier rdnAsn1ObjectIdentifier) {

		String result = null;

		if (x500Name != null && rdnAsn1ObjectIdentifier != null) {

			RDN[ ] rdnArray = x500Name.getRDNs(rdnAsn1ObjectIdentifier);
			if (rdnArray != null && rdnArray.length > 0) {
				result = IETFUtils.valueToString(rdnArray[0].getFirst().getValue());
			}

		}

		return result;

	}

	/**
	 * Gets the RDN First ocurrence value with the OID indicated from the input X.500 Principal.
	 * @param x500Principal X.500 Principal to analyze.
	 * @param rdnAsn1ObjectIdentifier Object Identifier that represents the RDN to search.
	 * @return the RDN First ocurrence value with the OID indicated from the input X.500 Principal.
	 * <code>null</code> if some of the input parameters are <code>null</code> or there is some
	 * error analyzing the X.500 Principal.
	 */
	public static String getRDNFirstValueFromX500Principal(X500Principal x500Principal, ASN1ObjectIdentifier rdnAsn1ObjectIdentifier) {

		String result = null;

		if (x500Principal != null && rdnAsn1ObjectIdentifier != null) {

			X500Name x500Name = X500Name.getInstance(x500Principal.getEncoded());
			result = getRDNFirstValueFromX500Name(x500Name, rdnAsn1ObjectIdentifier);

		}

		return result;

	}

	/**
	 * Checks if the input certificate has the key purpose for TimeStamping.
	 * @param cert X509v3 certificate to check.
	 * @return <code>true</code> if the input certificate has the key purpose for timestamping, otherwise <code>false</code>.
	 * @throws CommonUtilsException In case of some error extracting the keyPurpose extension from the certificate.
	 */
	public static boolean hasCertKeyPurposeTimeStamping(X509Certificate cert) throws CommonUtilsException {

		boolean result = false;

		// Si el certificado no es nulo...
		if (cert != null) {

			try {

				// Obtenemos la lista de KeyPurpose del certificado.
				List<String> keyPurposeList = cert.getExtendedKeyUsage();

				// Si la lista no es nula ni vacía...
				if (keyPurposeList != null && !keyPurposeList.isEmpty()) {

					// Recorremos los OIDs declarados en la lista...
					for (String keyPurpose: keyPurposeList) {

						// Si es igual al OID de id_kp_timestamping, es que lo
						// hemos encontrado.
						if (KeyPurposeId.id_kp_timeStamping.getId().equals(keyPurpose)) {
							result = true;
							break;
						}

					}

				}

			} catch (CertificateParsingException e) {
				throw new CommonUtilsException(IValetException.COD_200, Language.getResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_CERTIFICATE_005), e);
			}

		}

		return result;

	}

	/**
	 * Checks if the input certificate is a CA (it has the flag in the basic constraint).
	 * @param cert X509v3 certificate that must be checked.
	 * @return <code>true</code> if the input certificate has the BasicConstraint extension
	 * with the CA flag, otherwise <code>false</code> (including if the input is <code>null</code>).
	 */
	public static boolean isCA(X509Certificate cert) {

		return cert != null && cert.getBasicConstraints() != -1;

	}

}
