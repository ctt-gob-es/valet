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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert.java.</p>
 * <b>Description:</b><p>Wrapper class for a X.509v3 Certificate. This class provides methods to
 * calculate/extract some information of the certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 22/02/2023.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;

import es.gob.valet.commons.utils.CertificateConstants;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;

/**
 * <p>Wrapper class for a X.509v3 Certificate. This class provides methods to
 * calculate/extract some information of the certificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 22/02/2023.
 */
public class WrapperX509Cert {


	/**
	 * Attribute that represents the X.509 Certificate (Java).
	 */
	private X509Certificate x509Cert = null;

	/**
	 * Attribute that represents the X.509 Certificate (Bouncy Castle Provider).
	 */
	private Certificate x509CertBC = null;

	/**
	 * Attribute that represents an extension analyzer for the certificate.
	 */
	private TSLCertificateExtensionAnalyzer certExtAnalyzer = null;

	/**
	 * Constructor method for the class WrapperX509Cert.java.
	 */
	private WrapperX509Cert() {
		super();
	}

	/**
	 * Constructor method for the class WrapperX509Cert.java.
	 * @param cert X.509 certificate to wrap.
	 * @throws TSLCertificateValidationException In case of some error parsing
	 * the input certificate with Bouncy Castle provider.
	 */
	public WrapperX509Cert(X509Certificate cert) throws TSLCertificateValidationException {
		this();
		x509Cert = cert;
		try {
			x509CertBC = UtilsCertificate.getBouncyCastleCertificate(cert);
		} catch (CommonUtilsException e) {
			throw new TSLCertificateValidationException(IValetException.COD_187, e.getMessage(), e);
		}
		certExtAnalyzer = new TSLCertificateExtensionAnalyzer(x509CertBC);
	}

	/**
	 * Gets the certificate X.509 Version.
	 * @return the certificate X.509 Version.
	 */
	public String getX509CertVersion() {
		return Integer.toString(x509CertBC.getVersionNumber());
	}

	/**
	 * Gets the certificate subject canonicalized.
	 * @return the certificate subject canonicalized or <code>null</code> if
	 * there is some error obtaining it.
	 */
	public String getSubject() {
		String result = null;
		try {
			result = UtilsCertificate.getCertificateId(x509Cert);
		} catch (CommonUtilsException e) {
			result = null;
		}
		return result;

	}

	/**
	 * Gets the certificate issuer canonicalized.
	 * @return the certificate issuer canonicalized or <code>null</code> if
	 * there is some error obtaining it.
	 */
	public String getIssuer() {
		String result = null;
		try {
			result = UtilsCertificate.getCertificateIssuerId(x509Cert);
		} catch (CommonUtilsException e) {
			result = null;
		}
		return result;
	}

	/**
	 * Gets the decimal representation of the serial number.
	 * @return the decimal representation of the serial number.
	 */
	public String getSerialNumber() {
		return x509Cert.getSerialNumber().toString();
	}

	/**
	 * Gets the signature algorithm name.
	 * @return the signature algorithm name.
	 */
	public String getSignatureAlgorithmName() {
		return x509Cert.getSigAlgName();
	}

	/**
	 * Gets the signature algorithm OID.
	 * @return the signature algorithm OID.
	 */
	public String getSignatureAlgorithmOID() {
		return x509Cert.getSigAlgOID();
	}

	/**
	 * Gets the notBefore date from the validity period of the certificate.
	 * @return the notBefore date from the validity period of the certificate.
	 */
	public String getValidFrom() {
		return UtilsCertificate.getValidFrom(x509Cert);
	}

	/**
	 * Gets the notAfter date from the validity period of the certificate.
	 * @return the notAfter date from the validity period of the certificate.
	 */
	public String getValidTo() {
		return UtilsCertificate.getValidTo(x509Cert);
	}

	/**
	 * Gets the OIDs of the Certificates Policies (splitted with commas).
	 * @return the OIDs of the Certificates Policies (splitted with commas) or <code>null</code>
	 * if there is not.
	 */
	public String getExtensionCertPoliciesInformationOIDs() {
		return canonicalizeStringList(certExtAnalyzer.getPolicyInformationsOids());
	}

	/**
	 * Gets the OIDs of the QcStatement Extension (splitted with commas).
	 * @return the OIDs of the QcStatement Extension (splitted with commas) or <code>null</code>
	 * if there is not.
	 */
	public String getExtensionQcStatementsOIDs() {
		return canonicalizeStringList(certExtAnalyzer.getQcStatementsOids());
	}

	/**
	 * Gets the OIDs of the QcStatement EUType Extension (splitted with commas).
	 * @return the OIDs of the QcStatement EUType Extension (splitted with commas) or <code>null</code>
	 * if there is not.
	 */
	public String getExtensionQcStatementExtEuTypeOids() {
		return canonicalizeStringList(certExtAnalyzer.getQcStatementExtEuTypeOids());
	}

	/**
	 * Auxiliar method that transforms a {@link List} of {@link String} in a unique {@link String}
	 * with all the elements with a comma like separator.
	 * @param stringList {@link List} of {@link String} merge.
	 * @return a {@link List} of {@link String} in a unique {@link String}
	 * with all the elements with a comma like separator, or <code>null</code> if there is no element.
	 */
	private String canonicalizeStringList(List<String> stringList) {

		String result = null;

		if (stringList != null && !stringList.isEmpty()) {

			StringBuilder sb = new StringBuilder();
			for (String string: stringList) {
				if (string != null) {
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
					sb.append(string);
				}
			}
			if (sb.length() > 0) {
				result = sb.substring(1, sb.length());
				sb.setLength(0);
				sb.trimToSize();
			}

		}

		return result;

	}

	/**
	 * Gets the Subject Alternative Name Extension if it is defined.
	 * @return the Subject Alternative Name Extension if it is defined, otherwise <code>null</code>.
	 */
	public String getExtensionSubjectAltName() {

		String result = null;

		if (x509CertBC.getTBSCertificate().getExtensions() != null) {

			GeneralNames subjectAltNameExt = GeneralNames.fromExtensions(x509CertBC.getTBSCertificate().getExtensions(), Extension.subjectAlternativeName);
			if (subjectAltNameExt != null) {

				GeneralName[ ] genNamesArray = subjectAltNameExt.getNames();
				if (genNamesArray != null && genNamesArray.length > 0) {

					StringBuilder sb = new StringBuilder();
					for (int index = 0; index < genNamesArray.length; index++) {
						String genNameString = genNamesArray[index].toString();
						if (genNameString != null && genNameString.length() > NumberConstants.NUM3) {
							sb.append(UtilsStringChar.SYMBOL_OPEN_SQUARE_BRACKET_STRING);
							sb.append(genNameString.substring(NumberConstants.NUM3));
							sb.append(UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);
						}
					}
					if (sb.length() > 0) {
						result = sb.toString();
						sb.setLength(0);
						sb.trimToSize();
					}

				}

			}

		}

		return result;

	}

	/**
	 * Gets a flag that indicates if the BasicConstraint Extension is defined, so the certificate
	 * represents a CA.
	 * @return <code>true</code> if the BasicConstraint Extension is defined, so the certificate
	 * represents a CA, otherwise <code>false</code>.
	 */
	public String getExtensionBasicConstrainstIsCA() {
		return Boolean.toString(UtilsCertificate.isCA(x509Cert));
	}

	/**
	 * Gets the Key Usages defined for this certificate.
	 * @return A comma separated string with all the key usage associted to this certificate. <code>null</code>
	 * if there is no one.
	 */
	public String getExtensionKeyUsage() {

		String result = null;

		if (x509CertBC.getTBSCertificate().getExtensions() != null) {

			KeyUsage keyUsage = KeyUsage.fromExtensions(x509CertBC.getTBSCertificate().getExtensions());
			if (keyUsage != null) {

				StringBuilder sb = new StringBuilder();
				if (keyUsage.hasUsages(KeyUsage.digitalSignature)) {
					sb.append("digitalSignature");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.nonRepudiation)) {
					sb.append("nonRepudiation");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.keyEncipherment)) {
					sb.append("keyEncipherment");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.dataEncipherment)) {
					sb.append("dataEncipherment");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.keyAgreement)) {
					sb.append("keyAgreement");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.keyCertSign)) {
					sb.append("keyCertSign");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.cRLSign)) {
					sb.append("cRLSign");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.encipherOnly)) {
					sb.append("encipherOnly");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (keyUsage.hasUsages(KeyUsage.decipherOnly)) {
					sb.append("decipherOnly");
					sb.append(UtilsStringChar.SYMBOL_COMMA_STRING);
				}
				if (sb.length() > 0) {
					sb.deleteCharAt(sb.length() - 1);
					result = sb.toString();
					sb.setLength(0);
					sb.trimToSize();
				}

			}

		}

		return result;

	}

	/**
	 * Gets the URIs defined in the CRLDistributionPoint Extension.
	 * @return the URIs defined in the CRLDistributionPoint Extension, <code>null</code> if there is not.
	 */
	public String getExtensionCRLDistributionPoints() {

		String result = null;

		if (x509CertBC.getTBSCertificate().getExtensions() != null) {

			// Recuperamos el listado de Distribution Points de tipo CRL.
			CRLDistPoint crlDps = null;
			ASN1InputStream dIn = null;
			try {
				Extension ext = x509CertBC.getTBSCertificate().getExtensions().getExtension(Extension.cRLDistributionPoints);
				byte[ ] octs = ext.getExtnValue().getOctets();
				dIn = new ASN1InputStream(octs);
				crlDps = CRLDistPoint.getInstance(dIn.readObject());
			} catch (Exception e1) {
				crlDps = null;
			} finally {
				if (dIn != null) {
					try {
						dIn.close();
					} catch (IOException e) {
						dIn = null;
					}
				}
			}

			// Si lo hemos obtenido...
			if (crlDps != null) {

				// Si la extensión no está vacía...
				DistributionPoint[ ] crlDpsArray = crlDps.getDistributionPoints();
				if (crlDpsArray != null && crlDpsArray.length > 0) {

					StringBuilder sb = new StringBuilder();

					// Los vamos recorriendo uno a uno...
					for (int indexDp = 0; indexDp < crlDpsArray.length; indexDp++) {

						// Obtenemos el name.
						DistributionPointName dpName = crlDpsArray[indexDp].getDistributionPoint();

						// Dentro del Distribution point el campo Name me
						// indica la CRL ---> Analizando
						if (dpName == null) {
							// Si no hay name en este punto de distribución
							// pruebo con otro.
							continue;
						}

						// Si se trata de un RelativeDistinguishedName,
						// entonces es un conjunto (SET)
						// de AttributeTypeAndValue, que a su vez es una
						// secuencia (SEQUENCE) de
						// pares (AttributeType, AttributeValue), que al
						// final son pares (OID, valor).
						if (dpName.getType() == DistributionPointName.NAME_RELATIVE_TO_CRL_ISSUER) {
							// Como no se conoce la especificación para
							// obtener los datos de la ruta CRL
							// a partir de los pares antes especificados, se
							// continúa con el siguiente DP.
							continue;
						}
						// En este punto, sabemos que tenemos un
						// "GeneralNames":
						// GeneralNames ::= SEQUENCE SIZE (1..MAX) OF
						// GeneralName
						//
						// GeneralName ::= CHOICE {
						// otherName [0] AnotherName,
						// rfc822Name [1] IA5String,
						// dNSName [2] IA5String,
						// x400Address [3] ORAddress,
						// directoryName [4] Name,
						// ediPartyName [5] EDIPartyName,
						// uniformResourceIdentifier [6] IA5String,
						// iPAddress [7] OCTET STRING,
						// registeredID [8] OBJECT IDENTIFIER }
						else {

							// La ruta CRL siempre vendrá en un
							// uniformResourceIdentifier (tipo 6 - IA5String)
							GeneralName[ ] generalNames = GeneralNames.getInstance(dpName.getName()).getNames();
							for (GeneralName gn: generalNames) {
								if (gn.getTagNo() == GeneralName.uniformResourceIdentifier) {
									String uriString = ((DERIA5String) gn.getName()).getString();
									sb.append(UtilsStringChar.SYMBOL_OPEN_SQUARE_BRACKET_STRING);
									sb.append(uriString);
									sb.append(UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);
								}
							}

						}

					}

					if (sb.length() > 0) {
						result = sb.toString();
						sb.setLength(0);
						sb.trimToSize();
					}

				}

			}

		}

		return result;

	}

	/**
	 * Gets the URIs defined in the AuthorityInformationAccess Extension.
	 * @return the URIs defined in the AuthorityInformationAccess Extension, <code>null</code> if there is not.
	 */
	public String getExtensionAuthorityInformationAccess() {

		String result = null;

		if (x509CertBC.getTBSCertificate().getExtensions() != null) {

			// Recuperamos la información de acceso a los servicios disponibles
			// en
			// la autoridad.
			AuthorityInformationAccess aia = AuthorityInformationAccess.fromExtensions(x509CertBC.getTBSCertificate().getExtensions());

			// Si la información recuperada no es nula, y al menos hay un
			// elemento...
			if (aia != null && aia.getAccessDescriptions() != null && aia.getAccessDescriptions().length > 0) {

				StringBuilder sb = new StringBuilder();

				// Los vamos recorriendo uno a uno...
				AccessDescription[ ] accessDescArray = aia.getAccessDescriptions();
				for (AccessDescription accessDescription: accessDescArray) {

					if (OCSPObjectIdentifiers.id_pkix_ocsp.equals(accessDescription.getAccessMethod())) {

						// Obtenemos la URI de acceso al OCSP.
						GeneralName accessLocationGeneralName = accessDescription.getAccessLocation();
						if (accessLocationGeneralName.getTagNo() == GeneralName.uniformResourceIdentifier) {

							String ocspUriString = ((DERIA5String) accessLocationGeneralName.getName()).getString();
							sb.append(UtilsStringChar.SYMBOL_OPEN_SQUARE_BRACKET_STRING);
							sb.append(ocspUriString);
							sb.append(UtilsStringChar.SYMBOL_CLOSE_SQUARE_BRACKET_STRING);

						}

					}

				}

				if (sb.length() > 0) {
					result = sb.toString();
					sb.setLength(0);
					sb.trimToSize();
				}

			}

		}

		return result;

	}

	/**
	 * Gets the information required from the certificate.
	 * @param infoCertCode One of the following:
	 * <ul>
	 * 	<li>- X509 Certificate Version: {@link WrapperX509Cert#INFOCERT_CERT_VERSION}.</li>
	 * 	<li>- Subject: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Issuer: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Serial Number: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Signature Algorithm Name: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Signature Algorithm OID: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Valid From (date): {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Valid To (date): {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Certificate Policies Information OIDs: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Qc Statements OIDs: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Qc Statements EuType OIDs: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Subject Alternative Name: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Basic Constraints: Is CA: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Key Usage: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: CRL Distribution Points: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * 	<li>- Extension: Authority Information Access: {@link WrapperX509Cert#INFOCERT_}.</li>
	 * </ul>
	 * @return the information required from the certificate or <code>null</code> if it can
	 * not be obtained.
	 */
	public String getCertificateInfo(final int infoCertCode) {

		String result = null;

		switch (infoCertCode) {
			case CertificateConstants.INFOCERT_CERT_VERSION:
				result = getX509CertVersion();
				break;
			case CertificateConstants.INFOCERT_SUBJECT:
				result = getSubject();
				break;
			case CertificateConstants.INFOCERT_ISSUER:
				result = getIssuer();
				break;
			case CertificateConstants.INFOCERT_SERIAL_NUMBER:
				result = getSerialNumber();
				break;
			case CertificateConstants.INFOCERT_SIGALG_NAME:
				result = getSignatureAlgorithmName();
				break;
			case CertificateConstants.INFOCERT_SIGALG_OID:
				result = getSignatureAlgorithmOID();
				break;
			case CertificateConstants.INFOCERT_VALID_FROM:
				result = getValidFrom();
				break;
			case CertificateConstants.INFOCERT_VALID_TO:
				result = getValidTo();
				break;
			case CertificateConstants.INFOCERT_CERTPOL_INFO_OIDS:
				result = getExtensionCertPoliciesInformationOIDs();
				break;
			case CertificateConstants.INFOCERT_QC_STATEMENTS_OIDS:
				result = getExtensionQcStatementsOIDs();
				break;
			case CertificateConstants.INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS:
				result = getExtensionQcStatementExtEuTypeOids();
				break;
			case CertificateConstants.INFOCERT_SUBJECT_ALT_NAME:
				result = getExtensionSubjectAltName();
				break;
			case CertificateConstants.INFOCERT_IS_CA:
				result = getExtensionBasicConstrainstIsCA();
				break;
			case CertificateConstants.INFOCERT_KEY_USAGE:
				result = getExtensionKeyUsage();
				break;
			case CertificateConstants.INFOCERT_CRL_DISTRIBUTION_POINTS:
				result = getExtensionCRLDistributionPoints();
				break;
			case CertificateConstants.INFOCERT_AUTHORITY_INFORMATION_ACCESS:
				result = getExtensionAuthorityInformationAccess();
				break;
			case CertificateConstants.INFOCERT_SURNAME:
				result = getSurname();
				break;
			case CertificateConstants.INFOCERT_COMMON_NAME:
				result = getCommonName();
				break;
			case CertificateConstants.INFOCERT_GIVEN_NAME:
				result = getGivenName();
				break;
			case CertificateConstants.INFOCERT_COUNTRY:
				result = getCountry();
				break;
			case CertificateConstants.INFOCERT_PSEUDONYM:
				result = getPseudonym();
				break;
			case CertificateConstants.INFOCERT_SUBJECT_SERIAL_NUMBER:
				result = getSubjectSerieNumber();
				break;
			default:
				break;
		}

		return result;

	}

	/**
	 * Gets the serie number of the certificate.
	 * @return
	 */
	public String getSubjectSerieNumber() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getSubject();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.SERIALNUMBER);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}

		}
		return result;
	}

	/**
	 * Gets the pseudonym of the certificate.
	 * @return
	 */
	public String getPseudonym() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getSubject();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.PSEUDONYM);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Gets the given name of the certificate.
	 * @return
	 */
	public String getGivenName() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getSubject();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.GIVENNAME);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Gets the common name of the certificate.
	 * @return
	 */
	public String getCommonName() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getSubject();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.CN);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}
		return result;
	}
	
	/**
	 * Gets the common name of the issuer certificate
	 */
	public String getCommonNameIssuer(){
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getIssuer();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.CN);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Gets the country of the certificate.
	 * @return
	 */
	public String getCountry() {
		String result = null;
		if (x509CertBC != null) {
			result = UtilsCertificate.getCountryOfTheCertificateString(x509Cert);
		}
		return result;
	}

	/**
	 * Gets the surname of the certificate.
	 * @return
	 */
	public String getSurname() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getSubject();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.SURNAME);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {

					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}

		return result;
	}
	
	
	/**
	 * Gets the organization name of the certificate.
	 * @return
	 */
	public String getOrganizationNameCertificate() {
		String result = null;
		if (x509CertBC != null) {
			X500Name x500name = x509CertBC.getTBSCertificate().getIssuer();
			RDN[ ] rndArray = x500name.getRDNs(BCStyle.O);
			if (rndArray != null && rndArray.length > 0) {
				if (rndArray[0].getFirst() != null) {
					result = IETFUtils.valueToString(rndArray[0].getFirst().getValue());
				}
			}
		}
		return result;
	}
	
	/**
	 * Get the issuer alternative name.
	 * @return
	 */
	public String getIssuerAlternativeName() {
		String result = null;
		if (x509CertBC.getTBSCertificate().getExtensions() != null) {
			AuthorityInformationAccess aia = AuthorityInformationAccess.fromExtensions(x509CertBC.getTBSCertificate().getExtensions());
			if (aia != null) {
				AccessDescription[ ] descriptions = aia.getAccessDescriptions();
				for (AccessDescription ad: descriptions) {
					if (ad.getAccessMethod().getId().equals(X509ObjectIdentifiers.id_ad_caIssuers.getId())){
						result = ad.getAccessLocation().getName().toString();
					}
				}
			}
		}

		return result;
	}

}
