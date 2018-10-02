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
 * <b>Description:</b><p> Class that provides methods for managing certificates.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>21 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 21 sept. 2018.
 */
package es.gob.valet.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import es.gob.valet.exceptions.CommonUtilsException;

/** 
 * <p>Class that provides methods for managing certificates.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 21 sept. 2018.
 */
public final class UtilsCertificate {
	/**
	 * Constant that represents a "x509" Certificate type.
	 */
	private static final String CERTIFICATE_TYPE = "x509";
	/**
	 * Constant that represents a coma separator.
	 */
	private static final String COMA_SEPARATOR = ",";

	/**
	 * Constant that represents a equals character.
	 */
	private static final String EQUALS_CHAR = "=";

	/**
	 * Constant that defines a default hash algorithm (SHA-256) used to calculate certificate hash.
	 */
	public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
	/**
	 * Creates a X509Certificate given its content.
	 *
	 * @param certificate Certificate content.
	 * @return X509Certificate jce X509Certificate.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static X509Certificate getCertificate(byte[ ] certificate) throws CommonUtilsException {
		InputStream is = new ByteArrayInputStream(certificate);
		try {
			return (X509Certificate) CertificateFactory.getInstance(CERTIFICATE_TYPE).generateCertificate(is);
		} catch (CertificateException e) {
			throw new CommonUtilsException(CommonUtilsException.UTILS_CERTIFICATE_CODE_001,e.getMessage(), e);
		}
	}


	/**
	 * Creates a Iaik X509Certificate from a java X509Certificate.
	 * @param x509cert Certificate to transform.
	 * @return Iaik X509Certificate.
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static iaik.x509.X509Certificate getIaikCertificate(Certificate x509cert) throws CommonUtilsException {

		if (x509cert == null) {
			return null;
		} else if (x509cert instanceof iaik.x509.X509Certificate) {
			return (iaik.x509.X509Certificate) x509cert;
		} else {
			try {
				return getIaikCertificate(x509cert.getEncoded());
			} catch (CertificateEncodingException e) {
				throw new CommonUtilsException(CommonUtilsException.UTILS_CERTIFICATE_CODE_001, e.getMessage(), e);
			}
		}

	}
	
	/**
	 * Creates a Iaik X509Certificate given its content.
	 *
	 * @param certificate Certificate content.
	 * @return Iaik X509Certificate
	 * @throws CommonUtilsException Exception thrown if there is any problem creating the certificate.
	 */
	public static iaik.x509.X509Certificate getIaikCertificate(byte[ ] certificate) throws CommonUtilsException {
		try {
			return new iaik.x509.X509Certificate(certificate);
		} catch (CertificateException e) {
			throw new CommonUtilsException(CommonUtilsException.UTILS_CERTIFICATE_CODE_001,e.getMessage(), e);
		}
	}
	
	/**
	 * Gets certificate´s identifier (canonicalized subject).
	 *
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
		if (idCertificado.indexOf(EQUALS_CHAR) != -1) {
			String[ ] campos = idCertificado.split(COMA_SEPARATOR);
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
				while (i < campos.length - 1 && !campos[i + 1].contains(EQUALS_CHAR)) {
					campos[currentIndex] += COMA_SEPARATOR + campos[i + 1];
					i++;
				}
				sb = new StringBuffer();
				pair = campos[currentIndex].trim().split(EQUALS_CHAR);
				sb.append(pair[0].toLowerCase());
				sb.append(EQUALS_CHAR);
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
				sb.append(COMA_SEPARATOR);
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
	 * Gets certificate from validation date.
	 * 
	 * @return Certificate from validation date.
	 */

	public static String getValidFrom(X509Certificate x509Certificate) {
		String validFrom = null;
		if (x509Certificate != null && x509Certificate.getNotBefore() != null) {
			validFrom = x509Certificate.getNotBefore().toString();
		}
		return validFrom;
	}

	/**
	 * Gets certificate to validation date.
	 * 
	 * @return Certificate to validation date.
	 */
	
	public static String getValidTo(X509Certificate x509Certificate) {
	
		String validTo = null;
		if (x509Certificate != null && x509Certificate.getNotAfter() != null) {
			validTo = x509Certificate.getNotAfter().toString();
		}
		return validTo;
	}

	
	
}
