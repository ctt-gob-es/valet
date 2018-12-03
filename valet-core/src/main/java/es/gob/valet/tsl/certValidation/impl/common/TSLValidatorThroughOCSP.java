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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.TSLValidatorThroughOCSP.java.</p>
 * <b>Description:</b><p>Class that represents a TSL validation operation process through a CRL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 03/12/2018.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CRLReason;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.CertificateStatus;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.cert.ocsp.OCSPReqBuilder;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RevokedStatus;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.cert.ocsp.UnknownStatus;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsProviders;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.tsl.access.TSLProperties;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod;
import es.gob.valet.tsl.parsing.impl.common.DigitalID;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;
import es.gob.valet.utils.UtilsHTTP;

/**
 * <p>Class that represents a TSL validation operation process through a CRL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 03/12/2018.
 */
public class TSLValidatorThroughOCSP implements ITSLValidatorThroughSomeMethod {

	/**
	 * Constant attribute that represents the name of the parameter to identify the OCSP request <code>content-type</code>.
	 */
	private static final String OCSP_REQUEST_CONTENTTYPE = "application/ocsp-request";

	/**
	 * Constant attribute that represents the name of the parameter to identify the OCSP response <code>content-type</code>.
	 */
	private static final String OCSP_RESPONSE_CONTENTTYPE = "application/ocsp-response";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'SUCCESSFUL'.
	 */
	private static final String OCSP_RESPONSE_STATUS_SUCCESSFUL = "SUCCESSFUL";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'MALFORMED_REQUEST'.
	 */
	private static final String OCSP_RESPONSE_STATUS_MALFORMED_REQUEST = "MALFORMED_REQUEST";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'INTERNAL_ERROR'.
	 */
	private static final String OCSP_RESPONSE_STATUS_INTERNAL_ERROR = "INTERNAL_ERROR";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'TRY_LATER'.
	 */
	private static final String OCSP_RESPONSE_STATUS_TRY_LATER = "TRY_LATER";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'SIG_REQUIRED'.
	 */
	private static final String OCSP_RESPONSE_STATUS_SIG_REQUIRED = "SIG_REQUIRED";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'UNAUTHORIZED'.
	 */
	private static final String OCSP_RESPONSE_STATUS_UNAUTHORIZED = "UNAUTHORIZED";

	/**
	 * Constant attribute that represents the token for OCSP Response Status 'UNKNOWN'.
	 */
	private static final String OCSP_RESPONSE_STATUS_UNKNOWN = "UNKNOWN";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TSLValidatorThroughOCSP.class);

	/**
	 * Attribute that represents the digital identities processor.
	 */
	private DigitalIdentitiesProcessor dip = null;

	/**
	 * Constructor method for the class TSLValidatorThroughOCSP.java.
	 */
	public TSLValidatorThroughOCSP() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#validateCertificate(java.security.cert.X509Certificate, java.util.Date, es.gob.valet.tsl.parsing.impl.common.TSPService, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult)
	 */
	@Override
	public void validateCertificate(X509Certificate cert, Date validationDate, TSPService tspService, TSLValidatorResult validationResult) {

		// Obtenemos los datos que identificarán al firmante de la respuesta
		// OCSP.
		extractOCSPResponseSignerData(tspService);

		// Si hemos obtenido al menos una identidad digital, continuamos.
		if (dip.isThereSomeDigitalIdentity()) {

			// Ahora obtenemos el listado de puntos de distribución.
			List<URI> supplyPointsURIList = tspService.getServiceInformation().getServiceSupplyPoints();

			// Si la lista no es nula ni vacía los analizamos.
			// Si la lista está vacía, no hacemos nada.
			if (supplyPointsURIList != null && !supplyPointsURIList.isEmpty()) {

				// Creamos el CertificateID que se usará en las peticiones OCSP.
				CertificateID certificateId = createCertificateID(cert, validationResult.getIssuerCert(), validationResult.getIssuerSubjectName(), validationResult.getIssuerPublicKey());

				// Si hemos podido generar el CertID, continuamos.
				if (certificateId != null) {

					// Obtenemos las propiedades que determinan los
					// timeouts de lectura y conexión configurados.
					int readTimeout = TSLProperties.getOcspReadTimeout();
					int connectionTimeout = TSLProperties.getOcspConnectionTimeout();
					int timeIntervalAllowed = TSLProperties.getOcspTimeIntervalAllowed();

					// Iniciamos la variable que contendrá la respuesta OCSP.
					OCSPResp ocspResponse = null;

					// Iniciamos la variable que contendrá el valor
					// del Nonce.
					byte[ ] nonceByteArray = null;

					// Recorremos los distintos puntos de distribución hasta que
					// obtengamos una respuesta OCSP.
					String ocspUri = null;
					for (URI uri: supplyPointsURIList) {

						// Creamos el valor para la extensión Nonce.
						nonceByteArray = buildNonce();

						// Construimos y mandamos la petición OCSP.
						LOGGER.debug(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL255, new Object[ ] { uri }));
						ocspResponse = buildAndSendOCSPRequest(certificateId, nonceByteArray, uri, readTimeout, connectionTimeout);

						// Si la respuesta no es nula, comprobamos quien la
						// firma.
						if (ocspResponse != null) {
							if (checkOCSPResponseIsValid(ocspResponse, nonceByteArray, validationDate, validationResult, true, null, null)) {
								LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL256, new Object[ ] { uri }));
								ocspUri = uri.toString();
								break;
							} else {
								ocspResponse = null;
								LOGGER.debug(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL223));
							}
						}

					}

					// Si hemos obtenido una respuesta...
					if (ocspResponse != null) {

						checkCertificateInOCSPResponse(certificateId, validationDate, ocspResponse, ocspUri, timeIntervalAllowed, validationResult);

					} else {

						LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL134));

					}

				}

			}

		}

	}

	/**
	 * Creates a new Nonce Value to add in the OCSP Request.
	 * @return a byte array that represents the Nonce value. If there is some problem
	 * building, then returns <code>null</code>.
	 */
	private byte[ ] buildNonce() {

		byte[ ] result = new byte[NumberConstants.NUM16];

		SecureRandom sRandom = UtilsProviders.getSecureRandomForSeedAlgorithmProviderName(UtilsProviders.SEED_ALGORITHM_SHA1PRNG, null);
		if (sRandom != null) {
			sRandom.nextBytes(result);
		} else {
			result = null;
		}

		return result;

	}

	/**
	 * Extracts the information about the OCSP Response Signer from the TSP Service.
	 * @param tspService TSL - TSP Service from which extract the information to validate the certificate.
	 */
	private void extractOCSPResponseSignerData(TSPService tspService) {

		// Obtenemos la lista de identidades digitales para analizarlas.
		List<DigitalID> identitiesList = tspService.getServiceInformation().getAllDigitalIdentities();

		// Creamos el procesador de identidades digitales.
		dip = new DigitalIdentitiesProcessor(identitiesList);

	}

	/**
	 * Builds the CertificateID to use in a OCSP Request. It can be builded from the certificate to validate and
	 * the certifite of its issuer, or the subject name and public key of the issuer.
	 * @param cert X509v3 Certificate to validate.
	 * @param issuerCert X509v3 certificate of the issuer.
	 * @param issuerSubjectName Subject Name of the issuer of the certificate to validate.
	 * @param issuerPublicKey Public Key of the certificate of the certificate to validate.
	 * @return CertificateID to use for the OCSP requests, or <code>null</code> if there are note sufficient information
	 * to build it.
	 */
	private CertificateID createCertificateID(X509Certificate cert, X509Certificate issuerCert, String issuerSubjectName, PublicKey issuerPublicKey) {

		CertificateID result = null;

		try {

			// Si tenemos el certificado del emisor del certificado...
			if (issuerCert != null) {

				DigestCalculatorProvider bcDigestProvider = new BcDigestCalculatorProvider();
				DigestCalculator dc = bcDigestProvider.get(CertificateID.HASH_SHA1);
				result = new CertificateID(dc, new X509CertificateHolder(issuerCert.getEncoded()), cert.getSerialNumber());

			}
			// Si tenemos el subject name y la clave pública del emisor...
			else if (!UtilsStringChar.isNullOrEmptyTrim(issuerSubjectName) && issuerPublicKey != null) {

				DigestCalculatorProvider bcDigestProvider = new BcDigestCalculatorProvider();
				DigestCalculator dc = bcDigestProvider.get(CertificateID.HASH_SHA1);

				X500Name issuerSubjectX500Name = new X500Name(issuerSubjectName);

				OutputStream dcOs = dc.getOutputStream();
				dcOs.write(issuerSubjectX500Name.getEncoded(ASN1Encoding.DER));
				dcOs.close();
				ASN1OctetString issuerNameHash = new DEROctetString(dc.getDigest());

				PublicKeyDataObject issuerPublicKeyDataObject = PublicKeyDataObject.getInstance(issuerPublicKey);
				dcOs.write(issuerPublicKeyDataObject.getEncoded(ASN1Encoding.DER));
				dcOs.close();
				ASN1OctetString issuerPublicKeyHash = new DEROctetString(dc.getDigest());

				CertID certId = new CertID(CertificateID.HASH_SHA1, issuerNameHash, issuerPublicKeyHash, new ASN1Integer(cert.getSerialNumber()));
				result = new CertificateID(certId);

			}

		} catch (Exception e) {

			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL126), e);
			result = null;

		}

		return result;

	}

	/**
	 * Builds and send a OCSP Request with the input CertificateID to the input URI. Then parses and returns the response.
	 * @param certificateId {@link CertificateID} to send in the OCSP request. It can not be <code>null</code>.
	 * @param nonceByteArray Nonce to assign in the extensions in the OCSP Request. It can not be <code>null</code>.
	 * @param uri URI where send the request. It can not be <code>null</code>.
	 * @param readTimeout Read timeout in milliseconds.
	 * @param connectionTimeout Connection timeout in milliseconds.
	 * @return The parsed OCSP response, or <code>null</code> if there is some problem.
	 */
	private OCSPResp buildAndSendOCSPRequest(CertificateID certificateId, byte[ ] nonceByteArray, URI uri, int readTimeout, int connectionTimeout) {

		OCSPResp result = null;

		// Si alguno de los parámetros de entrada es nulo, devolvemos null
		// directamente.
		if (certificateId != null && nonceByteArray != null && uri != null) {

			try {

				// Se construye la petición OCSP...
				OCSPReqBuilder ocspReqBuilder = new OCSPReqBuilder();
				ocspReqBuilder.addRequest(certificateId);
				// Creamos las extensiones necesarias.
				List<Extension> extensionList = new ArrayList<Extension>();
				// El tipo de respuestas aceptadas: Basic OCSP Response.
				ASN1Sequence acceptableResponseTypesSequence = new DERSequence(OCSPObjectIdentifiers.id_pkix_ocsp_basic);
				Extension acceptableResponseTypesExtension = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_response, false, acceptableResponseTypesSequence.getEncoded());
				extensionList.add(acceptableResponseTypesExtension);
				// Extensión Nonce: La añadimos si no es nula.
				if (nonceByteArray != null) {
					// Iniciamos la variable que contendrá el valor de la
					// extensión Nonce.
					Extension nonceExtension = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false, nonceByteArray);
					// La añadimos al grupo de extensiones.
					extensionList.add(nonceExtension);
				}
				Extensions ocspExtensions = new Extensions(extensionList.toArray(new Extension[extensionList.size()]));
				ocspReqBuilder.setRequestExtensions(ocspExtensions);
				OCSPReq ocspReq = ocspReqBuilder.build();

				// Pasamos a cadena la URI a la que mandar la petición.
				String ocspUri = uri.toString();

				// Se realiza la petición HTTP.
				byte[ ] ocspResponseBytes = UtilsHTTP.getResponseByHttpPostWithEntity(ocspUri, connectionTimeout, readTimeout, ocspReq.getEncoded(), OCSP_REQUEST_CONTENTTYPE, OCSP_RESPONSE_CONTENTTYPE, null, null);

				// Creamos la respuesta.
				result = new OCSPResp(ocspResponseBytes);

			} catch (Exception e) {
				LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL128, new Object[ ] { uri.toString() }), e);
				result = null;
			}

		}

		return result;

	}

	/**
	 * Checks if the OCSP response is valid and its signed by one of the collected digital identities.
	 * @param ocspResponse OCSP response to analyze. It can not be null.
	 * @param nonce Byte array that represents the nonce.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where must be stored the validation result data.
	 * @param checkOcspResponseSigner Flag that indicates if it must be checked if the signer of the ocsp response
	 * is valid.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 * @param tslValidator TSL validator to verify if some TSP service is accomplished with the qualified (or not) certificate to check the OCSP response.
	 * @return <code>true</code> if the OCSP response is valid, otherwise <code>false</code>.
	 */
	private boolean checkOCSPResponseIsValid(OCSPResp ocspResponse, byte[ ] nonce, Date validationDate, TSLValidatorResult validationResult, boolean checkOcspResponseSigner, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		boolean result = false;

		// Primero comprobamos el estado de la respuesta.
		if (ocspResponse.getStatus() == OCSPResp.SUCCESSFUL) {

			try {

				// Obtenemos la respuesta básica que es la única soportada.
				if (ocspResponse.getResponseObject() instanceof BasicOCSPResp) {

					// Obtenemos el objeto que representa a la respuesta básica.
					BasicOCSPResp basicOcspResponse = (BasicOCSPResp) ocspResponse.getResponseObject();

					// Comprobamos el Nonce.
					if (checkOCSPResponseNonce(basicOcspResponse, nonce)) {

						// Solo comprobamos el firmante si así se indica en el
						// método.
						if (checkOcspResponseSigner) {

							// Comprobamos si esta ha sido generada por un
							// responder
							// (incluye los certificados).
							X509CertificateHolder[ ] basicOcspResponseSignerCerts = basicOcspResponse.getCerts();
							if (basicOcspResponseSignerCerts != null && basicOcspResponseSignerCerts.length > 0) {

								result = checkOCSPResponseIssuedBySomeDigitalIdentity(basicOcspResponse, basicOcspResponseSignerCerts, validationResult, tsp, tslValidator);

							}
							// Si no incluye los certificados, tenemos que
							// comprobar
							// si
							// está emitida
							// por el mismo emisor del certificado a validar.
							else {

								result = checkOCSPResponseIssuerSameThanCertificateToValidate(basicOcspResponse, validationResult);

							}

						} else {

							result = true;

						}

					}

				} else {

					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL136));

				}

			} catch (OCSPException e) {
				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL136));
			}

		} else {

			LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL135, new Object[ ] { translateOcspResponseStatusToString(ocspResponse.getStatus()) }));

		}

		return result;

	}

	/**
	 * Checks if the extension Nonce matches with the input Nonce. If the input nonce is <code>null</code>, or
	 * the basic OCSP response has not the nonce extension, then returns <code>true</code>.
	 * @param basicOcspResponse Basic OCSP response to check.
	 * @param nonce Nonce to compare.
	 * @return <code>true</code> if the input nonce is <code>null</code>, or does not exist the nonce extension
	 * in the basic ocsp response, or matches one to other. Otherwise <code>false</code>.
	 */
	private boolean checkOCSPResponseNonce(BasicOCSPResp basicOcspResponse, byte[ ] nonce) {

		boolean result = true;

		// Si el nonce de entrada no es nulo...
		if (nonce != null && basicOcspResponse.hasExtensions()) {

			// Obtenemos la extensión.
			Extension nonceExtension = basicOcspResponse.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);

			// Si no es nula...
			if (nonceExtension != null) {

				// Obtenemos su valor.
				byte[ ] responseNonce = nonceExtension.getExtnValue().getOctets();
				// Si no es nulo...
				if (responseNonce != null) {

					// Comparamos que ambos sean iguales.
					result = Arrays.equals(nonce, responseNonce);

				}

			}

		}

		return result;

	}

	/**
	 * Checks if the OCSP response is signed by the issuer of the certificate to validate.
	 * @param basicOcspResponse Basic OCSP Response to check.
	 * @param basicOcspResponseSignerCerts Array with the certificates in the Basic OCSP Response to check. If this parameter
	 * is <code>null</code>, then is extracted from the basic OCSP response.
	 * @param validationResult Validation Result Object representation that contains the information about the certificate to validate.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 * @param tslValidator TSL validator to verify if some TSP service is accomplished with the qualified (or not) certificate to check the OCSP response.
	 * @return <code>true</code> if the basic ocspe response is signed by the issuer of the certificate
	 * to validate, otherwise <code>false</code>.
	 */
	private boolean checkOCSPResponseIssuedBySomeDigitalIdentity(BasicOCSPResp basicOcspResponse, X509CertificateHolder[ ] basicOcspResponseSignerCerts, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		boolean result = false;

		// Recorremos los X509CertificateHolder hasta encontrar el firmante...
		X509CertificateHolder[ ] basicOcspResponseSignerCertsArray = basicOcspResponseSignerCerts;
		if (basicOcspResponseSignerCertsArray == null) {
			basicOcspResponseSignerCertsArray = basicOcspResponse.getCerts();
		}
		Certificate signerCert = null;
		if (basicOcspResponseSignerCertsArray != null) {
			for (X509CertificateHolder signerCertX509CertHolder: basicOcspResponseSignerCertsArray) {
				try {
					if (basicOcspResponse.isSignatureValid(new JcaContentVerifierProviderBuilder().build(signerCertX509CertHolder))) {
						signerCert = signerCertX509CertHolder.toASN1Structure();
						break;
					}
				} catch (OperatorCreationException | CertificateException
						| OCSPException e) {
					continue;
				}
			}
		}

		// Si lo hemos encontrado...
		if (signerCert != null) {

			try {
				// En caso de disponer del certificado emisor del certificado a
				// validar, comprobamos si es el que firma la respuesta.
				result = validationResult.getIssuerCert() != null && signerCert.equals(UtilsCertificate.getBouncyCastleCertificate(validationResult.getIssuerCert()));
			} catch (CommonUtilsException e) {
				// Consideramos que no se ha verificado.
				result = false;
			}

			// Si no se ha verificado, y el certificado firmante tiene la
			// extensión necesaria para
			// firmar peticiones OCSP, comprobamos las identidades digitales.
			if (!result && checkIfSignerCertCanSignOCSPResponses(signerCert)) {

				result = checkIfSignerCertIsEqualToSomeDigitalIdentity(signerCert, validationResult, tsp, tslValidator);

			}

		}

		return result;

	}

	/**
	 * Checks if the input certificate has the OCSPSigning extended key usage enabled.
	 * @param cert x509v3 certificate to check.
	 * @return <code>true</code> if the input certificate has the OCSPSigning extended key usage
	 * enabled, otherwise <code>false</code>.
	 */
	private boolean checkIfSignerCertCanSignOCSPResponses(Certificate cert) {

		boolean result = false;

		// Recuperamos la extensión "ExtendedKeyUsage".
		ExtendedKeyUsage extendedKeyUsage = ExtendedKeyUsage.fromExtensions(cert.getTBSCertificate().getExtensions());

		// Si la hemos recuperado...
		if (extendedKeyUsage != null) {

			// Obtenemos los propósitos del certificado, y buscamos entre
			// estos el que determina que está habilitado para firmar respuestas
			// OCSP.
			KeyPurposeId[ ] keyPurposeIdsArray = extendedKeyUsage.getUsages();

			for (KeyPurposeId keyPurposeId: keyPurposeIdsArray) {

				if (KeyPurposeId.id_kp_OCSPSigning.equals(keyPurposeId)) {
					result = true;
					break;
				}

			}

		}

		return result;

	}

	/**
	 * Checks if the input certificate matches with some digital identity.
	 * @param cert X509v3 certificate to check.
	 * @param validationResult Validation Result Object representation that contains the information about the certificate to validate.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 * @param tslValidator TSL validator to verify if some TSP service is accomplished with the qualified (or not) certificate to check the OCSP response.
	 * @return <code>true</code> if the input certificate matches with some digital identity., otherwise <code>false</code>.
	 */
	private boolean checkIfSignerCertIsEqualToSomeDigitalIdentity(Certificate cert, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		boolean result = false;

		// Si se ha recibido un TSP y un validador para buscar los servicios de
		// tipo OCSP compatibles con la respuesta OCSP, hacemos uso de este.
		if (tsp != null && tsp.isThereSomeTSPService() && tslValidator != null) {

			// Almacenamos en una variable si el certificado es cualificado o
			// no.
			boolean isCertQualified = validationResult.getMappingType() == ITSLValidatorResult.MAPPING_TYPE_QUALIFIED;

			// Obtenemos la lista de servicios.
			List<TSPService> tspServiceList = tsp.getAllTSPServices();

			// Recorremos la lista buscando servicios de tipo OCSP que
			// concuerden con si el certificado es "qualified" o no,
			// hasta encontrar uno que verifique la respuesta OCSP,
			// o que en su defecto, se acaben.
			for (int index = 0; !result && index < tspServiceList.size(); index++) {

				// Almacenamos en una variable el servicio a analizar en
				// esta vuelta.
				TSPService tspService = tspServiceList.get(index);

				// Si el servicio es de tipo OCSP y es acorde con el tipo del
				// certificado... (qualified o no)...
				if (tslValidator.checkIfTSPServiceTypeIsOCSPCompatible(tspService, isCertQualified)) {

					// Construimos un procesador de identidad digital con este.
					DigitalIdentitiesProcessor dipAux = new DigitalIdentitiesProcessor(tspService.getServiceInformation().getAllDigitalIdentities());

					// Lo usamos para tratar de verificar la CRL.
					result = checkIfSignerCertIsEqualToSomeDigitalIdentityUsingDigitalIdentitiesProcessor(cert, dipAux);

				}

			}

		}
		// Si no, hacemos uso del que se cargó inicialmente junto con la clase.
		else {

			result = checkIfSignerCertIsEqualToSomeDigitalIdentityUsingDigitalIdentitiesProcessor(cert, dip);

		}

		return result;

	}

	/**
	 * Checks if the input certificate matches with some digital identity.
	 * @param cert X509v3 certificate to check.
	 * @param dipToUse Digital Identities Processor to use for search some identity that matches with the input certificate.
	 * @return <code>true</code> if the input certificate matches with some digital identity., otherwise <code>false</code>.
	 */
	private boolean checkIfSignerCertIsEqualToSomeDigitalIdentityUsingDigitalIdentitiesProcessor(Certificate cert, DigitalIdentitiesProcessor dipToUse) {

		boolean result = false;

		// Parseamos el certificado.
		X509Certificate x509cert = null;
		try {
			x509cert = UtilsCertificate.getX509Certificate(cert.getEncoded());
		} catch (CommonUtilsException | IOException e) {
			x509cert = null;
		}

		if (x509cert != null) {

			// Comparamos primero los certificados.
			if (dipToUse.isThereSomeX509CertificateDigitalIdentity()) {

				List<X509Certificate> certDiList = dipToUse.getX509certList();
				for (X509Certificate certDi: certDiList) {
					try {
						if (UtilsCertificate.equals(x509cert, certDi)) {
							result = true;
							break;
						}
					} catch (CommonUtilsException e) {
						result = false;
					}
				}

			}

			// Por último, si aún no hemos encontrado coincidencia, tratamos de
			// comprobarlo verificando clave pública y subject.
			if (!result && dipToUse.isThereSomeX509PublicKeyDigitalIdentity() && dipToUse.isThereSomeX509SubjectNameDigitalIdentity()) {

				try {
					// Primero buscamos si alguna clave pública coincide con la
					// del
					// certificado.
					boolean publicKeyFinded = false;
					byte[ ] certPublicKeyBytes = x509cert.getPublicKey().getEncoded();
					List<PublicKey> publicKeyDiList = dipToUse.getX509publicKeysList();
					for (PublicKey publicKeyDi: publicKeyDiList) {
						if (Arrays.equals(certPublicKeyBytes, publicKeyDi.getEncoded())) {
							publicKeyFinded = true;
							break;
						}
					}

					// Si hemos encontrado la clave pública, buscamos un subject
					// que
					// coincida.
					if (publicKeyFinded) {

						String certSubject = UtilsCertificate.getCertificateId(x509cert);
						List<String> subjectNameDiList = dipToUse.getX509SubjectNameList();
						for (String subjectNameDi: subjectNameDiList) {
							if (certSubject.equals(UtilsCertificate.canonicalizarIdCertificado(subjectNameDi))) {
								result = true;
								break;
							}
						}

					}
				} catch (CommonUtilsException e) {
					result = false;
				}

			}

			// IMPORTANTE: Para verificar la respuesta OCSP, al tratarse de un
			// recurso externo, no podemos confiar
			// que en comparar el SubjectKeyIdentifier sea suficiente. Tan solo
			// la
			// daremos por válida
			// si lo verificamos por alguno de los dos métodos anteriores: X509
			// o
			// PublicKey.

			// // Si no hemos encontrado coincidencia, probamos con el
			// // SubjectNameKeyIdentifier.
			// if (!result && dipToUse.isThereSomeX509skiDigitalIdentity()) {
			//
			// // Recuperamos el SubjectKeyIdentifier del certificado.
			// SubjectKeyIdentifier certSki = null;
			// try {
			// certSki = (SubjectKeyIdentifier)
			// cert.getExtension(SubjectKeyIdentifier.oid);
			// } catch (X509ExtensionInitException e) {
			// certSki = null;
			// }
			//
			// // Si lo hemos recuperado, lo comparamos con los de las
			// identidades
			// // digitales.
			// if (certSki != null) {
			//
			// byte[ ] certSkiBytes = certSki.get();
			// List<byte[ ]> skiDiList = dipToUse.getX509ski();
			// for (byte[ ] skiDi: skiDiList) {
			// if (Arrays.equals(skiDi, certSkiBytes)) {
			// result = true;
			// break;
			// }
			// }
			//
			// }
			//
			// }

		}

		return result;

	}

	/**
	 * Checks if the OCSP response is signed by the issuer of the certificate to validate.
	 * @param basicOcspResponse Basic OCSP Response to check.
	 * @param validationResult Validation Result Object representation that contains the information about the certificate to validate.
	 * @return <code>true</code> if the basic ocspe response is signed by the issuer of the certificate
	 * to validate, otherwise <code>false</code>.
	 */
	private boolean checkOCSPResponseIssuerSameThanCertificateToValidate(BasicOCSPResp basicOcspResponse, TSLValidatorResult validationResult) {

		boolean result = false;

		// Declaramos la variable donde guardaremos la clave pública para
		// verificar
		// la respuesta OCSP.
		PublicKey publicKey = null;

		// Comprobamos si está definido el emisor del certificado a validar.
		if (validationResult.getIssuerCert() != null) {
			publicKey = validationResult.getIssuerCert().getPublicKey();
		}
		// Si no, comprobamos si tenemos su clave pública.
		else if (validationResult.getIssuerPublicKey() != null) {
			publicKey = validationResult.getIssuerPublicKey();
		}

		try {

			// Si hemos recuperado una clave pública...
			if (publicKey != null) {

				result = basicOcspResponse.isSignatureValid(new JcaContentVerifierProviderBuilder().build(publicKey));

			} else {

				LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL138));

			}

		} catch (Exception e) {
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL137));
		}

		return result;

	}

	/**
	 * Translate the input status for the OCSP response to a {@link String}.
	 * @param status Number that represents the status of a OCSP response to translate.
	 * @return OCSP response status in {@link String} value.
	 */
	private String translateOcspResponseStatusToString(int status) {

		String result = null;

		switch (status) {
			case OCSPResp.SUCCESSFUL:
				result = OCSP_RESPONSE_STATUS_SUCCESSFUL;
				break;

			case OCSPResp.MALFORMED_REQUEST:
				result = OCSP_RESPONSE_STATUS_MALFORMED_REQUEST;
				break;

			case OCSPResp.INTERNAL_ERROR:
				result = OCSP_RESPONSE_STATUS_INTERNAL_ERROR;
				break;

			case OCSPResp.TRY_LATER:
				result = OCSP_RESPONSE_STATUS_TRY_LATER;
				break;

			case OCSPResp.SIG_REQUIRED:
				result = OCSP_RESPONSE_STATUS_SIG_REQUIRED;
				break;

			case OCSPResp.UNAUTHORIZED:
				result = OCSP_RESPONSE_STATUS_UNAUTHORIZED;
				break;

			default:
				result = OCSP_RESPONSE_STATUS_UNKNOWN;
				break;
		}

		return result;

	}

	/**
	 * Checks in the OCSP response the revocation status of the certificate and sets the result.
	 * @param certificateId CertificateID of the certificate to check its revocation status.
	 * @param validationDate Validation date to use for check the revocation status.
	 * @param uri {@link String} that represents the URI that localizes the OCSP server from which has
	 * been obtained the Basic OCSP Response.
	 * @param ocspResponse OCSP response to analyze.
	 * @param timeIntervalAllowed Time interval allowed (in seconds) for the OCSP response.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	private void checkCertificateInOCSPResponse(CertificateID certificateId, Date validationDate, OCSPResp ocspResponse, String uri, int timeIntervalAllowed, TSLValidatorResult validationResult) {

		try {

			// Obtenemos el objeto que representa a la respuesta básica.
			BasicOCSPResp basicOcspResponse = (BasicOCSPResp) ocspResponse.getResponseObject();

			// Continuamos el proceso en otro método.
			checkCertificateInOCSPResponse(certificateId, validationDate, basicOcspResponse, uri, timeIntervalAllowed, validationResult);

		} catch (OCSPException e) {

			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL142), e);

		}

	}

	/**
	 * Checks in the OCSP response the revocation status of the certificate and sets the result.
	 * @param certificateId CertificateID of the certificate to check its revocation status.
	 * @param validationDate Validation date to use for check the revocation status.
	 * @param basicOcspResponse Basic OCSP response to analyze.
	 * @param uri {@link String} that represents the URI that localizes the OCSP server from which has
	 * been obtained the Basic OCSP Response.
	 * @param timeIntervalAllowed Time interval allowed (in seconds) for the OCSP response.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	private void checkCertificateInOCSPResponse(CertificateID certificateId, Date validationDate, BasicOCSPResp basicOcspResponse, String uri, int timeIntervalAllowed, TSLValidatorResult validationResult) {

		try {

			// Obtenemos todos los SingleResponses contenidos.
			SingleResp[ ] singleResponsesArray = basicOcspResponse.getResponses();

			// Si hay, los recorremos y buscamos el correspondiente al
			// certificado a validar.
			SingleResp singleResponse = null;
			if (singleResponsesArray != null) {
				for (SingleResp singleResp: singleResponsesArray) {

					CertificateID singleRespCertificateId = singleResp.getCertID();
					if (singleRespCertificateId != null && singleRespCertificateId.equals(certificateId)) {
						singleResponse = singleResp;
						break;
					}

				}
			}

			// La almacenamos en la respuesta.
			validationResult.setRevocationValueBasicOCSPResponse(basicOcspResponse);
			validationResult.setRevocationValueURL(uri);

			// Comprobamos el estado de revocación del certificado.
			checkCertificateInOCSPResponse(validationDate, singleResponse, timeIntervalAllowed, validationResult);

		} catch (Exception e) {

			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL142), e);

		}

	}

	/**
	 * Checks in the Basic OCSP response the revocation status of the certificate and sets the result.
	 * @param validationDate Validation date to use for check the revocation status.
	 * @param singleResponse Single Response from the basic OCSP response to analyze.
	 * @param timeIntervalAllowed Time interval allowed (in seconds) for the OCSP response.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	private void checkCertificateInOCSPResponse(Date validationDate, SingleResp singleResponse, int timeIntervalAllowed, TSLValidatorResult validationResult) {

		try {

			// Si la hemos obtenido, y el intervalo temporal es válido...
			if (singleResponse != null && checkTimeIntervalAllowed(singleResponse, timeIntervalAllowed, validationDate)) {

				// Extraemos el estado de revocación de la respuesta.
				CertificateStatus certStatus = singleResponse.getCertStatus();

				// Lo analizamos según su valor.

				// Si es revocado...
				if (certStatus instanceof RevokedStatus) {

					// Instanciamos el objeto con la información de estado.
					RevokedStatus revokedCertStatus = (RevokedStatus) certStatus;

					// Por defecto, indicamos como resultado que el
					// certificado se encuentra revocado.
					validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED);

					// Si además contiene información sobre la revocación...
					if (revokedCertStatus.hasRevocationReason()) {

						// Extraemos la fecha de revocación y la razón.
						Date revocationDate = revokedCertStatus.getRevocationTime();
						int reasonCode;
						try {
							reasonCode = revokedCertStatus.getRevocationReason();
						} catch (Exception e) {
							reasonCode = -1;
						}

						// Si la fecha de revocación es posterior a la fecha
						// de validación,
						// o el motivo es eliminado de la CRL,
						// el certificado es válido.
						if (revocationDate != null && revocationDate.after(validationDate) || reasonCode >= 0 && reasonCode == CRLReason.REMOVE_FROM_CRL.ordinal()) {

							validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_VALID);

						} else {

							// Establecemos la fecha y motivo de revocación.
							validationResult.setRevocationDate(revocationDate);
							if (reasonCode >= 0) {
								validationResult.setRevocationReason(reasonCode);
							}

						}
					}

				}
				// Si es desconocido...
				else if (certStatus instanceof UnknownStatus) {

					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL141));

				}
				// Si es good...
				else {

					// Indicamos como resultado que el certificado es
					// válido.
					validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_VALID);

				}

			}

		} catch (Exception e) {
			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL142), e);
		}

	}

	/**
	 * Checks if time interval allowed is OK in the single response OCSP.
	 * @param singleResponse Single OCSP Response.
	 * @param timeIntervalAllowedSeconds Time Interval Allowed in seconds.
	 * @param validationDate Validation date to use for check the revocation status.
	 * @return <code>true</code> if the Single OCSP Response is valid regards
	 * the time interval allowed, otherwise <code>false</code>.
	 */
	private boolean checkTimeIntervalAllowed(SingleResp singleResponse, int timeIntervalAllowedSeconds, Date validationDate) {

		boolean result = true;

		// Calculamos la fecha actual y el intervalo permitido en milisegundos.
		long validationDateMillisSeconds = validationDate.getTime();
		long timeIntervalAllowedMilliSeconds = Integer.valueOf(timeIntervalAllowedSeconds).longValue() * Integer.valueOf(NumberConstants.NUM1000).longValue();

		// Extraemos la fecha hasta cuando es válida la respuesta.
		Date nextUpdate = singleResponse.getNextUpdate();
		// Si la hemos obtenido y la fecha de próxima actualización es anterior
		// a la actual menos el intervalo permitido, significa que la respuesta
		// no
		// es válida.
		if (nextUpdate != null && nextUpdate.getTime() < validationDateMillisSeconds - timeIntervalAllowedMilliSeconds) {
			result = false;
		}

		// Si aún es válida la respuesta, comprobamos la fecha de emisión de
		// esta.
		if (result) {

			Date thisUpdate = singleResponse.getThisUpdate();
			// Si no la hemos obtenido, o es posterior a la fecha actual más el
			// intervalo permitido, no pude ser válida al venir del futuro.
			if (thisUpdate == null || thisUpdate.getTime() > validationDateMillisSeconds + timeIntervalAllowedMilliSeconds) {
				result = false;
			}

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#searchRevocationValueCompatible(java.security.cert.X509Certificate, org.bouncycastle.cert.ocsp.BasicOCSPResp, java.security.cert.X509CRL, java.util.Date, es.gob.valet.tsl.parsing.impl.common.TSPService, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult)
	 */
	@Override
	public void searchRevocationValueCompatible(X509Certificate cert, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSPService tspService, TSLValidatorResult validationResult) {

		// Obtenemos los datos que identificarán al firmante de la respuesta
		// OCSP.
		extractOCSPResponseSignerData(tspService);

		// Si hemos obtenido al menos una identidad digital, y
		// el firmante de la respuesta OCSP coincide con alguna de estas,
		// o es el mismo emisor del certificado a validar,
		// significa que la respuesta es compatible con la TSL.
		if (dip.isThereSomeDigitalIdentity() && (checkOCSPResponseIssuedBySomeDigitalIdentity(basicOcspResponse, null, validationResult, null, null) || checkOCSPResponseIssuerSameThanCertificateToValidate(basicOcspResponse, validationResult))) {

			// Asignamos la respuesta OCSP al resultado.
			validationResult.setRevocationValueBasicOCSPResponse(basicOcspResponse);

			// Hacemos uso de esta para comprobar el estado de revocación del
			// certificado.
			checkCertificateInBasicOCSPResponseRevocationValue(cert, validationDate, basicOcspResponse, null, validationResult);

		}

	}

	/**
	 * Checks in the Basic OCSP response the revocation status of the certificate and sets the result.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param validationDate Validation date to use for check the revocation status.
	 * @param basicOcspResponse Basic OCSP response to analyze.
	 * @param uri {@link String} that represents the URI that localizes the OCSP server from which has
	 * been obtained the Basic OCSP Response.
	 * @param validationResult Object in which must be stored the validation result data.
	 */
	private void checkCertificateInBasicOCSPResponseRevocationValue(X509Certificate cert, Date validationDate, BasicOCSPResp basicOcspResponse, String uri, TSLValidatorResult validationResult) {

		try {

			// Generamos el CertificateID.
			CertificateID certificateId = createCertificateID(cert, validationResult.getIssuerCert(), validationResult.getIssuerSubjectName(), validationResult.getIssuerPublicKey());
			// Obtenemos la propiedad que determina el intervalo permitido.
			int timeIntervalAllowed = TSLProperties.getOcspTimeIntervalAllowed();
			// Finalmente comprobamos el estado de revocación del certificado en
			// la respuesta OCSP.
			checkCertificateInOCSPResponse(certificateId, validationDate, basicOcspResponse, uri, timeIntervalAllowed, validationResult);

		} catch (Exception e) {
			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL125), e);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#validateCertificateUsingDistributionPoints(java.security.cert.X509Certificate, boolean, java.util.Date, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult, es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider, es.gob.valet.tsl.certValidation.impl.common.ATSLValidator)
	 */
	@Override
	public void validateCertificateUsingDistributionPoints(X509Certificate cert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		// Recuperamos la información de acceso a los servicios disponibles en
		// la autoridad.
		AuthorityInformationAccess aia = null;
		try {
			aia = AuthorityInformationAccess.fromExtensions(UtilsCertificate.getBouncyCastleCertificate(cert).getTBSCertificate().getExtensions());
		} catch (Exception e) {
			LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL186), e);
		}

		// Si la información recuperada no es nula, y al menos hay un
		// elemento...
		if (aia != null && aia.getAccessDescriptions() != null && aia.getAccessDescriptions().length > 0) {

			// Creamos el CertificateID que se usará en las peticiones OCSP.
			CertificateID certificateId = createCertificateID(cert, validationResult.getIssuerCert(), validationResult.getIssuerSubjectName(), validationResult.getIssuerPublicKey());

			// Si hemos podido generar el CertificateID, continuamos.
			if (certificateId != null) {

				// Obtenemos las propiedades que determinan los
				// timeouts
				// de lectura y conexión configurados.
				int readTimeout = TSLProperties.getOcspReadTimeout();
				int connectionTimeout = TSLProperties.getOcspConnectionTimeout();
				int timeIntervalAllowed = TSLProperties.getOcspTimeIntervalAllowed();

				// Iniciamos la variable que contendrá la respuesta OCSP.
				OCSPResp ocspResponse = null;
				// Iniciamos la variable que contendrá el valor de la
				// extensión Nonce.
				byte[ ] nonce = null;

				// Los vamos recorriendo uno a uno hasta que encontremos un
				// servicio OCSP que se pueda usar.
				AccessDescription[ ] accessDescArray = aia.getAccessDescriptions();
				String uri = null;
				for (AccessDescription accessDescription: accessDescArray) {

					if (OCSPObjectIdentifiers.id_pkix_ocsp.equals(accessDescription.getAccessMethod())) {

						// Obtenemos la URI de acceso al OCSP.
						GeneralName accessLocationGeneralName = accessDescription.getAccessLocation();
						if (accessLocationGeneralName.getTagNo() == GeneralName.uniformResourceIdentifier) {

							String ocspUriString = ((DERIA5String) accessLocationGeneralName.getName()).getString();

							// La convertimos a objeto URI.
							URI ocspUri = URI.create(ocspUriString);

							// Creamos el valor para la extensión Nonce.
							nonce = buildNonce();

							// Construimos y mandamos la petición OCSP.
							ocspResponse = buildAndSendOCSPRequest(certificateId, nonce, ocspUri, readTimeout, connectionTimeout);

							// Si la respuesta no es nula, comprobamos si está
							// bien
							// formada y el nonce.
							if (ocspResponse != null) {
								if (!checkOCSPResponseIsValid(ocspResponse, nonce, validationDate, validationResult, !isTsaCertificate, tsp, tslValidator)) {
									ocspResponse = null;
									LOGGER.debug(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL223));
								} else {
									uri = ocspUriString;
									break;
								}
							}

						}

					}

				}

				// Si hemos obtenido una respuesta...
				if (ocspResponse != null) {

					checkCertificateInOCSPResponse(certificateId, validationDate, ocspResponse, uri, timeIntervalAllowed, validationResult);

				} else {

					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL134));

				}

			}

		}

	}

}
