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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.DigitalIdentitiesProcessor.java.</p>
 * <b>Description:</b><p>Class that represents a Digital Identities Processor.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 30/10/2024.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.w3.x2000.x09.xmldsig.DSAKeyValueType;
import org.w3.x2000.x09.xmldsig.KeyValueType;
import org.w3.x2000.x09.xmldsig.RSAKeyValueType;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.tsl.parsing.impl.common.DigitalID;

/**
 * <p>Class that represents a Digital Identities Processor.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 30/10/2024.
 */
public class DigitalIdentitiesProcessor {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(DigitalIdentitiesProcessor.class);

	/**
	 * Constant attribute that represents the string 'DSA'.
	 */
	private static final String DSA = "DSA";

	/**
	 * Constant attribute that represents the string 'RSA'.
	 */
	private static final String RSA = "RSA";

	/**
	 * Attribute that represents the list of X509Certificate obtained from the digital identities.
	 */
	private List<X509Certificate> x509certList = null;

	/**
	 * Attribute that represents the list of X509 Subject Name obtained from the digital identities.
	 */
	private List<String> x509SubjectNameList = null;

	/**
	 * Attribute that represents the list of Public Keys obtained from the digital identities.
	 */
	private List<PublicKey> x509publicKeysList = null;

	/**
	 * Attribute that represents the list of X509 Subject Key Identifiers (array of bytes) obtained from the digital identities.
	 */
	private List<byte[ ]> x509ski = null;

	/**
	 * Constructor method for the class DigitalIdentitiesProcessor.java.
	 */
	public DigitalIdentitiesProcessor() {
		super();
		x509certList = new ArrayList<X509Certificate>();
		x509SubjectNameList = new ArrayList<String>();
		x509publicKeysList = new ArrayList<PublicKey>();
		x509ski = new ArrayList<byte[ ]>();
	}

	/**
	 * Constructor method for the class DigitalIdentitiesProcessor.java.
	 * @param digitalIdentitiesList List of Digital Identities to process.
	 */
	public DigitalIdentitiesProcessor(List<DigitalID> digitalIdentitiesList) {

		this();
		// Si la lista de identidades no es nula ni vacía, la procesamos.
		if (digitalIdentitiesList != null && !digitalIdentitiesList.isEmpty()) {
			processDigitalIdentities(digitalIdentitiesList);
		}

	}

	/**
	 * Gets the value of the attribute {@link #x509certList}.
	 * @return the value of the attribute {@link #x509certList}.
	 */
	public final List<X509Certificate> getX509certList() {
		return x509certList;
	}

	/**
	 * Checks if there is some X509 Certificate Digital Identity.
	 * @return <code>true</code> if there is at least one X509 Certificate Digital Identity,
	 * otherwise <code>false</code>.
	 */
	public final boolean isThereSomeX509CertificateDigitalIdentity() {
		return !x509certList.isEmpty();
	}

	/**
	 * Gets the value of the attribute {@link #x509SubjectNameList}.
	 * @return the value of the attribute {@link #x509SubjectNameList}.
	 */
	public final List<String> getX509SubjectNameList() {
		return x509SubjectNameList;
	}

	/**
	 * Checks if there is some X509 Subject Name Digital Identity.
	 * @return <code>true</code> if there is at least one X509 Subject Name Digital Identity,
	 * otherwise <code>false</code>.
	 */
	public final boolean isThereSomeX509SubjectNameDigitalIdentity() {
		return !x509SubjectNameList.isEmpty();
	}

	/**
	 * Gets the value of the attribute {@link #x509publicKeysList}.
	 * @return the value of the attribute {@link #x509publicKeysList}.
	 */
	public final List<PublicKey> getX509publicKeysList() {
		return x509publicKeysList;
	}

	/**
	 * Checks if there is some X509 Public Key Digital Identity.
	 * @return <code>true</code> if there is at least one X509 Public Key Digital Identity,
	 * otherwise <code>false</code>.
	 */
	public final boolean isThereSomeX509PublicKeyDigitalIdentity() {
		return !x509publicKeysList.isEmpty();
	}

	/**
	 * Gets the value of the attribute {@link #x509ski}.
	 * @return the value of the attribute {@link #x509ski}.
	 */
	public final List<byte[ ]> getX509ski() {
		return x509ski;
	}

	/**
	 * Checks if there is some X509 Subject Key Identifier Digital Identity.
	 * @return <code>true</code> if there is at least one X509 Subject Key Identifier Digital Identity,
	 * otherwise <code>false</code>.
	 */
	public final boolean isThereSomeX509skiDigitalIdentity() {
		return !x509ski.isEmpty();
	}

	/**
	 * Checks if there is some digital identity.
	 * @return <code>true</code> if there is some type of digital identity, otherwise <code>false</code>.
	 */
	public final boolean isThereSomeDigitalIdentity() {
		return isThereSomeX509CertificateDigitalIdentity() || isThereSomeX509PublicKeyDigitalIdentity() || isThereSomeX509SubjectNameDigitalIdentity() || isThereSomeX509skiDigitalIdentity();
	}

	/**
	 * Analyze the non empty digital identitites list and gets from it the differents
	 * X509v3 certificates, subject names, public keys and subject keys identifiers.
	 * @param digitalIdentitiesList Digital Identities list to analyze.
	 */
	private void processDigitalIdentities(List<DigitalID> digitalIdentitiesList) {

		// Las recorremos y vamos obteniendo sus datos.
		for (DigitalID digitalId: digitalIdentitiesList) {

			// Según el tipo, actuamos de una manera u otra.
			switch (digitalId.getType()) {

				case DigitalID.TYPE_X509CERTIFICATE:
					x509certList.add(digitalId.getX509cert());
					break;

				case DigitalID.TYPE_X509SUBJECTNAME:
					x509SubjectNameList.add(digitalId.getX509SubjectName());
					break;

				case DigitalID.TYPE_KEYVALUE:

					// Declaramos la clave pública.
					PublicKey publicKey = null;

					// Obtenemos el KeyValue.
					KeyValueType keyValue = digitalId.getKeyValue();
					// En función de si es DSA o RSA obtenemos la clave pública.
					if (keyValue.isSetDSAKeyValue()) {

						try {

							// Construimos la clave pública a partir de los
							// atributos DSA.
							DSAKeyValueType kvtDSA = keyValue.getDSAKeyValue();
							KeyFactory keyFactory = KeyFactory.getInstance(DSA);
							BigInteger y = new BigInteger(kvtDSA.getY());
							BigInteger p = new BigInteger(kvtDSA.getP());
							BigInteger q = new BigInteger(kvtDSA.getQ());
							BigInteger g = new BigInteger(kvtDSA.getG());
							DSAPublicKeySpec dsaPublicKeySpec = new DSAPublicKeySpec(y, p, q, g);
							publicKey = keyFactory.generatePublic(dsaPublicKeySpec);

						} catch (Exception e) {
							// En caso de error volvemos a indicar que la clave
							// pública es nula.
							publicKey = null;
						}

					} else if (keyValue.isSetRSAKeyValue()) {

						try {

							RSAKeyValueType kvtRSA = keyValue.getRSAKeyValue();
							KeyFactory keyFactory = KeyFactory.getInstance(RSA);
							BigInteger modulus = new BigInteger(kvtRSA.getModulus());
							BigInteger publicExponent = new BigInteger(kvtRSA.getExponent());
							RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
							publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

						} catch (Exception e) {
							// En caso de error volvemos a indicar que la clave
							// pública es nula.
							publicKey = null;
						}

					}

					// Si finalmente hemos obtenido la clave pública, la
					// guardamos.
					if (publicKey != null) {
						x509publicKeysList.add(publicKey);
					}

					break;

				case DigitalID.TYPE_X509SKI:

					x509ski.add(digitalId.getSki().getKeyIdentifier());
					break;

				case DigitalID.TYPE_OTHER:
					// TODO Este tipo lo ignoramos al no saber qué puede
					// contener.
					break;

				default:
					break;
			}

		}

	}

	/**
	 * Checks if the input certificate is issued by some of the identities and sets its in the result.
	 * @param cert X509v3 certificate to check.
	 * @param validationResult Object where is stored the validation result data.
	 * @return <code>true</code> if the certificate is issued by some of the input identities, otherwise <code>false</code>.
	 */
	public final boolean checkIfCertificateIsIssuedBySomeIdentity(X509Certificate cert, TSLValidatorResult validationResult) {

		// Por defecto, indicamos que el certificado no está emitido
		// por ninguno de los contenidos en las identidades digitales.
		boolean result = false;

		// Creamos una resultado parcial para cada subanálisis.
		boolean partialResult = false;

		// Comprobamos primero los certificados X509v3.
		for (X509Certificate issuerCert: x509certList) {

			// Comprobamos si la clave pública del certificado de la CA
			// firma el certificado.
			try {
				partialResult = UtilsCertificate.verify(issuerCert, cert);
			} catch (CommonUtilsException e) {
				LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL110));
			}

			// Si se ha encontrado el emisor del certificado y no lo habíamos
			// detectado ya,
			// lo indicamos en el resultado.
			if (partialResult) {
				result = true;
				validationResult.setIssuerCert(issuerCert);
				validationResult.setIssuerPublicKey(issuerCert.getPublicKey());
				try {
					validationResult.setIssuerSubjectName(UtilsCertificate.getCertificateId(issuerCert));
				} catch (CommonUtilsException e) {
					LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL182));
				}
				try {
					if (!UtilsCertificate.isSelfSigned(cert)) {
						SubjectKeyIdentifier ski = SubjectKeyIdentifier.fromExtensions(UtilsCertificate.getBouncyCastleCertificate(issuerCert).getTBSCertificate().getExtensions());
						validationResult.setIssuerSKIbytes(ski.getKeyIdentifier());
					}
				} catch (Exception e) {
					LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL183));
				}
				break;
			}

		}

		// Si ya hemos encontrado un emisor que verifica el certificado, tenemos
		// todos los datos.
		// En caso contrario, continuamos examinando las demás identidades
		// digitales.
		if (!result || validationResult.getIssuerSubjectName() == null || validationResult.getIssuerSKIbytes() == null) {

			partialResult = false;
			// Comprobamos las claves públicas.
			for (PublicKey issuerPublicKey: x509publicKeysList) {

				try {
					partialResult = UtilsCertificate.verify(issuerPublicKey, cert);
				} catch (CommonUtilsException e) {
					LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL112));
				}
				// Si se ha encontrado el emisor del certificado y no
				// lo habíamos detectado ya,
				// lo indicamos en el resultado.
				if (partialResult) {
					result = true;
					validationResult.setIssuerPublicKey(issuerPublicKey);
					break;
				}

			}

			// IMPORTANTE: Consideramos que comparar el emisor del certificado a
			// validar
			// con los asuntos recuperados, no es garantía suficiente para
			// determinar que
			// estas identidades digitales representan al emisor del
			// certificado.
			partialResult = false;
			// Comprobamos los Subject Names.
			for (String issuerSubjectName: x509SubjectNameList) {

				// Comprobamos que el subject de la CA coincide con el emisor
				// del
				// certificado.
				String caSubject = null;
				try {
					caSubject = UtilsCertificate.canonicalizarIdCertificado(issuerSubjectName);
					String certIssuer = UtilsCertificate.getCertificateIssuerId(cert);
					partialResult = caSubject.equals(certIssuer);
				} catch (CommonUtilsException e) {
					LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL111));
				}
				// Si se ha detectado el emisor del certificado y no lo habíamos
				// detectado
				// ya, lo indicamos en el resultado.
				if (partialResult) {

					// No se considera prueba suficiente para determinar que
					// este sea el emisor del certificado.
					//result = true;
					validationResult.setIssuerSubjectName(caSubject);
					break;
				}

			}

			// Y por último comprobamos los Subject Key Identifier si el
			// certificado no es autoemitido.
			if (partialResult && validationResult.getIssuerSKIbytes() == null && !UtilsCertificate.isSelfSigned(cert)) {

				// IMPORTANTE: Consideramos que comparar el emisor del
				// certificado a validar
				// con los asuntos recuperados, no es garantía suficiente para
				// determinar que
				// estas identidades digitales representan al emisor del
				// certificado.

				byte[ ] akiBytes = null;
				try {

					// Obtenemos el AuthorityKeyIdentifier en array de
					// bytes.
					AuthorityKeyIdentifier aki = null;
					byte[ ] authKeyIdent = cert.getExtensionValue(Extension.authorityKeyIdentifier.getId());
					if (authKeyIdent != null) {
						aki = AuthorityKeyIdentifier.getInstance(ASN1OctetString.getInstance(authKeyIdent).getOctets());
					}
					if (aki != null) {
						akiBytes = aki.getKeyIdentifier();
					}

				} catch (Exception e) {
					LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL114));
				}

				if (akiBytes != null) {

					partialResult = false;
					for (byte[ ] issuerSKI: x509ski) {

						// Comparamos los arrays...
						partialResult = Arrays.equals(issuerSKI, akiBytes);

						// Si se ha encontrado el emisor del certificado y no lo
						// habíamos
						// detectado ya, lo indicamos en el resultado.
						if (partialResult) {
							// No se considera prueba suficiente para determinar
							// que
							// este sea
							// el emisor del certificado.
							result = true;
							validationResult.setIssuerSKIbytes(issuerSKI);
							break;
						}

					}

				}

			}

		}

		return result;

	}

	/**
	 * Checks if the input certificate is some of the identities and sets its in the result.
	 * @param cert X509v3 certificate to check.
	 * @return <code>true</code> if the certificate is some of the identities, otherwise <code>false</code>.
	 */
	public final boolean checkIfDigitalIdentitiesMatchesCertificate(java.security.cert.X509Certificate cert) {

		// Por defecto, indicamos que no se detecta el certificado.
		boolean result = false;

		// Comprobamos primero los certificados X509v3.
		for (X509Certificate certDI: x509certList) {

			// Comprobamos si se trata del mismo certificado.
			try {
				result = UtilsCertificate.equals(cert, certDI);
				if (result) {
					break;
				}
			} catch (CommonUtilsException e) {
				LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL196));
			}

		}

		// Consideramos que para identificar el certificado, no nos
		// valen el resto de identidades digitales,
		// tiene que coincidir exactamente con alguno de los certificados
		// declarados.

		return result;

	}

}
