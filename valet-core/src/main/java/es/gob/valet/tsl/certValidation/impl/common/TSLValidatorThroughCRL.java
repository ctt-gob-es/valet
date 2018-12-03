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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.TSLValidatorThroughCRL.java.</p>
 * <b>Description:</b><p>Class that represents a TSL validation operation process through a CRL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 03/12/2018.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.PublicKey;
import java.security.cert.CRLReason;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.commons.utils.UtilsCRL;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsFTP;
import es.gob.valet.commons.utils.UtilsLDAP;
import es.gob.valet.commons.utils.UtilsResources;
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
public class TSLValidatorThroughCRL implements ITSLValidatorThroughSomeMethod {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TSLValidatorThroughCRL.class);

	/**
	 * Constant attribute that represents the Sun property for the connection timeout.
	 */
	private static final String SUN_CONNECT_TIMEOUT_PROP = "sun.net.client.defaultConnectTimeout";

	/**
	 * Constant attribute that represents the Sun property for the connection timeout.
	 */
	private static final String SUN_READ_TIMEOUT_PROP = "sun.net.client.defaultReadTimeout";

	/**
	 * Attribute that represents the digital identities processor.
	 */
	private DigitalIdentitiesProcessor dip = null;

	/**
	 * Constructor method for the class TSLValidatorThroughCRL.java.
	 */
	public TSLValidatorThroughCRL() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#validateCertificate(java.security.cert.X509Certificate, java.util.Date, es.gob.valet.tsl.parsing.impl.common.TSPService, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult)
	 */
	@Override
	public void validateCertificate(X509Certificate cert, Date validationDate, TSPService tspService, TSLValidatorResult validationResult) {

		// Obtenemos los datos que identificarán al emisor de la CRL.
		extractCRLIssuerData(tspService);

		// Si hemos obtenido al menos una identidad digital, continuamos.
		if (dip.isThereSomeDigitalIdentity()) {

			// Ahora obtenemos el listado de puntos de distribución.
			List<URI> supplyPointsURIList = tspService.getServiceInformation().getServiceSupplyPoints();

			// Si la lista no es nula ni vacía los analizamos.
			// Si la lista está vacía, no hacemos nada.
			if (supplyPointsURIList != null && !supplyPointsURIList.isEmpty()) {

				// Obtenemos las propiedades que determinan los
				// timeouts
				// de lectura y conexión configurados.
				int timeoutRead = TSLProperties.getCrlTimeoutRead();
				int timeoutConnection = TSLProperties.getCrlTimeoutConnection();

				// Iniciamos la variable que contendrá la CRL.
				X509CRL crl = null;

				// Recorremos los distintos puntos de distribución hasta que
				// obtengamos la CRL.
				String uriSelected = null;
				for (URI uri: supplyPointsURIList) {

					try {

						// Tratamos de obtener la CRL.
						LOGGER.debug(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL252, new Object[ ] { uri }));
						crl = downloadCRLFromSupplyPoint(uri, timeoutConnection, timeoutRead);

						// Si hemos obtenido la CRL, y comprobamos que es válida
						// y que está emitida por alguna de las identidades
						// digitales
						// recolectadas, dejamos de buscar.
						if (crl != null) {
							if (checkCRLisValid(crl, validationDate, true, validationResult, null, null)) {
								LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL253, new Object[ ] { uri }));
								uriSelected = uri.toString();
								break;
							} else {
								LOGGER.debug(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL254, new Object[ ] { uri }));
								crl = null;
							}
						}

					} catch (NullPointerException e) {

						LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL122), e);

					}

				}

				// Si hemos encontrado una CRL.
				if (crl != null) {

					// Asignamos la CRL a aplicar.
					validationResult.setRevocationValueCRL(crl);
					validationResult.setRevocationValueURL(uriSelected);
					// Buscamos el certificado dentro de esta.
					searchCertInCRL(cert, validationDate, crl, validationResult);

				}

			}

		} else {
			// Si no se ha obtenido ninguna identidad digital, lo indicamos en
			// el log.
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL115));
		}

	}

	/**
	 * Extracts the information about the CRL issuer from the TSP Service.
	 * @param tspService TSL - TSP Service from which extract the information to validate the certificate.
	 */
	private void extractCRLIssuerData(TSPService tspService) {

		// Obtenemos la lista de identidades digitales para analizarlas.
		List<DigitalID> identitiesList = tspService.getServiceInformation().getAllDigitalIdentities();

		// Creamos el procesador de identidades digitales.
		dip = new DigitalIdentitiesProcessor(identitiesList);

	}

	/**
	 * Tries to download the CRL from the specified URI. First, search if that URI is defined in
	 * some validation method with authentication, and then, if exists, use it.
	 * @param uri URI where is the CRL to download.
	 * @param timeoutConnection Connection Timeout in milliseconds.
	 * @param timeoutRead Timeout Read in milliseconds.
	 * @return The downloaded CRL, or <code>null</code> if it was not possible to download.
	 */
	private X509CRL downloadCRLFromSupplyPoint(URI uri, int timeoutConnection, int timeoutRead) {

		X509CRL result = null;

		// En función del tipo de URI, obtenemos la CRL.
		if (UtilsLDAP.isUriOfSchemeLDAP(uri)) {

			// Obtenemos la CRL.
			result = getCRLFromLDAPURI(uri, timeoutRead, timeoutConnection);

		} else if (UtilsHTTP.isUriOfSchemeHTTP(uri, 0)) {

			// Obtenemos la CRL.
			result = getCRLFromHTTPURI(uri, timeoutRead, timeoutConnection);

		} else if (UtilsFTP.isUriOfSchemeFTP(uri)) {

			// Obtenemos la CRL.
			result = getCRLFromFTPURI(uri, timeoutRead, timeoutConnection);

		} else {

			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL120, new Object[ ] { uri }));

		}

		return result;

	}

	/**
	 * Private method that gets a CRL from a LDAP URI.
	 * @param uri URI to the CRL in a LDAP.
	 * @param readTimeout Read timeout for the connection in milliseconds.
	 * @param connectionTimeout Connection timeout in milliseconds.
	 * @return CRL obtained from the ldap.
	 */
	private X509CRL getCRLFromLDAPURI(URI uri, int readTimeout, int connectionTimeout) {

		X509CRL result = null;

		String fullUri = null;

		try {

			// Obtenemos el host, la ruta y los condicionantes de búsqueda.
			fullUri = uri.toString();
			String urlServer = uri.getHost();
			String pathLDAP = uri.getPath();
			String conditions = uri.getQuery();

			String searchFilter = null;
			if (!UtilsStringChar.isNullOrEmptyTrim(conditions)) {
				String[ ] allConds = conditions.split(UtilsStringChar.SYMBOL_ESCAPED_BACKSLASH_STRING + UtilsStringChar.SYMBOL_QUESTION_MARK_STRING);
				if (allConds != null) {
					for (int index = 0; index < allConds.length; index++) {
						if (allConds[index].startsWith("objectclass=")) {
							searchFilter = UtilsStringChar.SYMBOL_OPEN_BRACKET_STRING + allConds[index] + UtilsStringChar.SYMBOL_CLOSE_BRACKET_STRING;
						}
					}
				}
			}

			if (searchFilter == null) {
				searchFilter = "(objectclass=*)";
			}

			// Obtenemos la CRL por LDAP.
			result = UtilsLDAP.getCRLfromLDAP(urlServer, null, null, pathLDAP, searchFilter, null, connectionTimeout, readTimeout, true);

		} catch (CommonUtilsException e) {

			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL117, new Object[ ] { fullUri }), e);

		}

		return result;

	}

	/**
	 * Private method that gets a CRL from a HTTP URI.
	 * @param uri URI to the CRL through HTTP.
	 * @param readTimeout Read timeout for the connection in milliseconds.
	 * @param connectionTimeout Connection timeout in milliseconds.
	 * @return CRL obtained from the HTTP resource.
	 */
	private X509CRL getCRLFromHTTPURI(URI uri, int readTimeout, int connectionTimeout) {

		X509CRL result = null;

		String httpUri = null;

		try {

			// Obtenemos la URI completa.
			httpUri = uri.toString();

			// Descargamos la CRL en un array de bytes.
			byte[ ] buffer = UtilsHTTP.getDataFromURI(httpUri, connectionTimeout, readTimeout, null, null, null);

			if (buffer != null) {

				// Una vez leida la CRL creamos el objeto X509CRL.
				result = UtilsCRL.buildX509CRLfromByteArray(buffer);

			}

		} catch (CommonUtilsException e) {

			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL118, new Object[ ] { httpUri }), e);

		}

		return result;

	}

	/**
	 * Private method that gets a CRL from a FTP URI.
	 * @param uri URI to the CRL through FTP.
	 * @param readTimeout Read timeout for the connection in milliseconds.
	 * @param connectionTimeout Connection timeout in milliseconds.
	 * @return CRL obtained from the FTP resource.
	 */
	private X509CRL getCRLFromFTPURI(URI uri, int readTimeout, int connectionTimeout) {

		X509CRL result = null;
		String ftpUri = null;

		InputStream inStream = null;

		try {

			// Obtenemos la URL completa.
			ftpUri = uri.toString();

			// Se establecen las propiedades de timeout.
			System.setProperty(SUN_CONNECT_TIMEOUT_PROP, String.valueOf(connectionTimeout));
			System.setProperty(SUN_READ_TIMEOUT_PROP, String.valueOf(readTimeout));

			// Accedemos a la URL.
			URL url = new URL(ftpUri);
			URLConnection urlc = url.openConnection();
			inStream = urlc.getInputStream();
			// Creamos el objeto X509CRL a partir del stream.
			result = UtilsCRL.buildX509CRLfromByteArray(inStream);

		} catch (Exception e) {

			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL119, new Object[ ] { ftpUri }), e);

		} finally {

			UtilsResources.safeCloseInputStream(inStream);

		}

		return result;

	}

	/**
	 * Checks if the downloaded CRL is valid and consistent.
	 * @param crl {@link X509CRL} object that represents the CRL to analyze.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param checkIssuerOfCRL Flag that indicates if must be checked that the issuer of the CRL
	 * is defined how digital identity.
	 * @param validationResult Object from which extracts the possible issuer of the CRL to check.
	 * @param tsp Trust Service Provider from which search some TSP Service that verifies the crl as is issuer. If it
	 * is <code>null</code>, then this method uses the Digital Identities Processor builded from the CRL/OCSP Service.
	 * @param tslValidator TSL Validator to check if some CRL TSP Service is in accord with the qualified (or not) certificate.
	 * @return <code>true</code> if the CRL has been verified, otherwise <code>false</code>.
	 */
	private boolean checkCRLisValid(X509CRL crl, Date validationDate, boolean checkIssuerOfCRL, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		boolean result = true;

		// Comprobamos que la fecha de emisión es anterior a la de validación.
		if (crl.getThisUpdate() != null && crl.getThisUpdate().after(validationDate)) {
			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL123, new Object[ ] { validationDate.toString(), crl.getThisUpdate().toString() }));
			result = false;
		}

		// Comprobamos que la fecha de próxima actualización es posterior a la
		// de validación.
		if (crl.getNextUpdate() != null && crl.getNextUpdate().before(validationDate)) {
			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL124, new Object[ ] { validationDate.toString(), crl.getNextUpdate().toString() }));
			result = false;
		}

		// Si la CRL sigue siendo válida y se ha indicado que se compruebe su
		// emisor...
		if (result && checkIssuerOfCRL) {

			// Ahora trataremos de detectar el emisor de la CRL según las
			// identidades digitales disponibles. Inicialmente consideramos
			// que no lo detectamos.
			result = false;

			// Si ya detectamos el certificado a validar en la TSL, y tenemos
			// el servicio que representa a su emisor, comprobamos si es el
			// mismo emisor de la CRL.
			if (validationResult.getTSPServiceForDetect() != null) {

				// Construimos un procesador de identidad digital con
				// este.
				DigitalIdentitiesProcessor dipAux = new DigitalIdentitiesProcessor(validationResult.getTSPServiceForDetect().getServiceInformation().getAllDigitalIdentities());

				// Lo usamos para tratar de verificar la CRL.
				result = checkCRLisValidWithDigitalIdentitiesProcessor(crl, dipAux);

			}

			// Si no se ha conseguido comprobar aún...
			if (!result) {

				// Si se ha recibido un TSP en el que buscar servicios de CRL...
				if (tsp != null && tsp.isThereSomeTSPService() && tslValidator != null) {

					// Almacenamos en una variable si el certificado es
					// detectado o
					// no.
					boolean isCertQualified = validationResult.getMappingType() == ITSLValidatorResult.MAPPING_TYPE_QUALIFIED;

					// Obtenemos la lista de servicios.
					List<TSPService> tspServiceList = tsp.getAllTSPServices();

					// Recorremos la lista buscando servicios de tipo CRL que
					// concuerden
					// con si el certificado es "qualified" o no, hasta
					// encontrar
					// uno que verifique
					// la CRL, o que en su defecto, se acaben.
					for (int index = 0; !result && index < tspServiceList.size(); index++) {

						// Almacenamos en una variable el servicio a analizar en
						// esta vuelta.
						TSPService tspService = tspServiceList.get(index);

						// Si el servicio es de tipo CRL y es acorde con el tipo
						// del
						// certificado... (qualified o no)...
						if (tslValidator.checkIfTSPServiceTypeIsCRLCompatible(tspService, isCertQualified)) {

							// Construimos un procesador de identidad digital
							// con
							// este.
							DigitalIdentitiesProcessor dipAux = new DigitalIdentitiesProcessor(tspService.getServiceInformation().getAllDigitalIdentities());

							// Lo usamos para tratar de verificar la CRL.
							result = checkCRLisValidWithDigitalIdentitiesProcessor(crl, dipAux);

						}

					}

				}
				// Si se ha definido un Digital Identities Processor al crear el
				// validador para un servicio concreto del TSP...
				if (!result && dip != null) {

					// Usamos el establecido en la clase para tratar de validar
					// la
					// CRL.
					result = checkCRLisValidWithDigitalIdentitiesProcessor(crl, dip);

				}

			}

		}

		return result;

	}

	/**
	 * Checks if the downloaded CRL is valid with the input Digital Identities Processor.
	 * @param crl {@link X509CRL} object that represents the CRL to analyze.
	 * @param digIdProc Digital Identities Processor to use for the verification of the CRL.
	 * @return <code>true</code> if the CRL has been verified, otherwise <code>false</code>.
	 */
	private boolean checkCRLisValidWithDigitalIdentitiesProcessor(X509CRL crl, DigitalIdentitiesProcessor digIdProc) {

		// Inicialmente consideramos que la CRL no ha sido verificada aún.
		boolean result = false;

		// Con las identidades digitales obtenidas, vamos comprobando si
		// la CRL está emitida por alguna de estas.
		// Si había certificados, comprobamos si alguno firma la CRL.
		if (!result && digIdProc.isThereSomeX509CertificateDigitalIdentity()) {

			List<X509Certificate> x509CertList = digIdProc.getX509certList();
			for (X509Certificate x509issuerCRLCert: x509CertList) {
				try {
					crl.verify(x509issuerCRLCert.getPublicKey());
					result = true;
					break;
				} catch (Exception e) {
					continue;
				}
			}

		}

		// Si ninguno firmaba la CRL, y hay claves públicas en las identidades
		// digitales,
		// comprobamos si alguna firma la CRL.
		if (!result && digIdProc.isThereSomeX509PublicKeyDigitalIdentity()) {

			List<PublicKey> publicKeyList = digIdProc.getX509publicKeysList();
			for (PublicKey publicKeyIssuer: publicKeyList) {
				try {
					crl.verify(publicKeyIssuer);
					result = true;
					break;
				} catch (Exception e) {
					continue;
				}

			}

		}

		// IMPORTANTE: Para verificar la CRL, al tratarse de un recurso externo,
		// no podemos confiar
		// que en comparar el SubjectKeyIdentifier o el SubjectName sea
		// suficiente. Tan solo la daremos por válida
		// si lo verificamos por alguno de los dos métodos anteriores: X509 o
		// PublicKey.

		// // Si ninguna firmaba la CRL, y hay Subject Key Identifier entre las
		// // identidades digitales,
		// // comprobamos si alguna coincide con la extensión Authority Key
		// // Identifier de la CRL.
		// if (!result && dip.isThereSomeX509skiDigitalIdentity()) {
		//
		// AuthorityKeyIdentifier crlAki = null;
		// try {
		// crlAki = (AuthorityKeyIdentifier)
		// crl.getExtension(AuthorityKeyIdentifier.oid);
		// } catch (X509ExtensionInitException e) {
		// crlAki = null;
		// }
		//
		// if (crlAki != null) {
		// List<byte[ ]> skiList = dip.getX509ski();
		// for (byte[ ] skiIssuer: skiList) {
		// result = Arrays.equals(skiIssuer, crlAki.getKeyIdentifier());
		// if (result) {
		// break;
		// }
		// }
		// }
		//
		// }
		//
		// // Si no hubiera ninguna que coincidera, comprobamos si algún
		// // SubjectName de las identidades digitales
		// // coincide con el nombre del emisor de la CRL.
		// if (!result && dip.isThereSomeX509SubjectNameDigitalIdentity()) {
		//
		// String crlIssuer = null;
		// try {
		// crlIssuer = UtilsCertificado.dameIdEmisorCRL(crl);
		// } catch (AfirmaException e) {
		// crlIssuer = null;
		// }
		//
		// if (!UtilsStringChar.isNullOrEmptyTrim(crlIssuer)) {
		//
		// List<String> snList = dip.getX509SubjectNameList();
		// for (String snIssuer: snList) {
		//
		// String snIssuerCanonicalized =
		// UtilsCertificate.canonicalizarIdCertificado(snIssuer);
		// result = crlIssuer.equals(snIssuerCanonicalized);
		// if (result) {
		// break;
		// }
		//
		// }
		//
		// }
		//
		// }

		return result;

	}

	/**
	 * Method that searchs the certificate inside the CRL.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param crl {@link X509CRL} object that represents the CRL in which search the certificate
	 * to check its status.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	private void searchCertInCRL(X509Certificate cert, Date validationDate, X509CRL crl, TSLValidatorResult validationResult) {

		try {

			// Buscamos el certificado dentro de la CRL y obtenemos su
			// correspondiente CRLEntry.
			X509CRLEntry x509CRLEntry = UtilsCRL.searchCrlEntryForCertificate(cert, crl, null);

			// Si la entrada CRL es nula, significa que el certificado es
			// válido,
			// así que lo establecemos en el resultado.
			if (x509CRLEntry == null) {

				validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_VALID);

			} else {

				// Si no es nulo, es que el certificado puede estar revocado,
				// por lo
				// que hay que analizar los motivos y condiciones.

				// Primero comprobamos que si la fecha es anterior o posterior a
				// la
				// de validación.
				Date revocationDate = x509CRLEntry.getRevocationDate();
				if (revocationDate != null) {

					// Si la fecha de revocación es anterior a la de validación,
					// hay que
					// analizar los motivos de revocación, ya que si se trata
					// del
					// motivo "Eliminado de la CRL", el certificado sigue siendo
					// válido.
					if (revocationDate.before(validationDate)) {

						// Tratamos de obtener los motivos de revocación.
						CRLReason crlReason = x509CRLEntry.getRevocationReason();
						if (crlReason != null) {

							// Si la razón es "Eliminado de la CRL" se considera
							// válido el certificado.
							if (crlReason == CRLReason.REMOVE_FROM_CRL) {

								validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_VALID);

							} else {

								// En caso de ser cualquier otro motivo, lo
								// consideramos inválido.
								validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED);
								validationResult.setRevocationDate(revocationDate);
								validationResult.setRevocationReason(crlReason.ordinal());

							}

						} else {

							// Si no hemos obtenido las extensiones no críticas,
							// o no contiene el motivo de revocación,
							// directamente consideramos el certificado
							// inválido.
							validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED);
							validationResult.setRevocationDate(revocationDate);

						}

					} else {

						// Si la fecha de revocación es posterior a la actual,
						// el certificado es válido.
						validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_VALID);

					}

				}

			}

		} catch (CommonUtilsException e) {
			LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL125), e);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#searchRevocationValueCompatible(java.security.cert.X509Certificate, org.bouncycastle.cert.ocsp.BasicOCSPResp, java.security.cert.X509CRL, java.util.Date, es.gob.valet.tsl.parsing.impl.common.TSPService, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult)
	 */
	@Override
	public void searchRevocationValueCompatible(X509Certificate cert, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSPService tspService, TSLValidatorResult validationResult) {

		// Obtenemos los datos que identificarán al emisor de la CRL.
		extractCRLIssuerData(tspService);

		// Si hemos obtenido al menos una identidad digital, y
		// si la CRL está emitida por alguna de las entidades
		// digitales.
		if (dip.isThereSomeDigitalIdentity() && checkCRLisValid(crl, validationDate, true, validationResult, null, null)) {

			// Si es así, asignamos la CRL a aplicar.
			validationResult.setRevocationValueCRL(crl);
			// Realizamos el proceso de validación del certificado.
			searchCertInCRL(cert, validationDate, crl, validationResult);

		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod#validateCertificateUsingDistributionPoints(java.security.cert.X509Certificate, boolean, java.util.Date, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult, es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider, es.gob.valet.tsl.certValidation.impl.common.ATSLValidator)
	 */
	@Override
	public void validateCertificateUsingDistributionPoints(X509Certificate cert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator) {

		// Recuperamos el listado de Distribution Points de tipo CRL.
		CRLDistPoint crlDps = null;
		ASN1InputStream dIn = null;
		try {
			Extensions extensions = UtilsCertificate.getBouncyCastleCertificate(cert).getTBSCertificate().getExtensions();
			Extension ext = extensions.getExtension(Extension.cRLDistributionPoints);
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

				// Los vamos recorriendo uno a uno hasta encontrar una CRL que
				// se
				// pueda obtener...
				X509CRL crl = null;
				String uriSelected = null;
				for (int indexDp = 0; crl == null && indexDp < crlDpsArray.length; indexDp++) {

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
						List<URI> uriDistPointsList = new ArrayList<URI>();
						for (GeneralName gn: generalNames) {
							if (gn.getTagNo() == GeneralName.uniformResourceIdentifier) {
								String uriString = ((DERIA5String) gn.getName()).getString();
								try {
									uriDistPointsList.add(new URI(uriString));
								} catch (URISyntaxException e) {
									continue;
								}
							}
						}

						// Si al menos se ha obtenido alguna ruta, se
						// continúa:
						if (!uriDistPointsList.isEmpty()) {

							// Obtenemos las propiedades que determinan los
							// timeouts
							// de lectura y conexión configurados.
							int readTimeout = TSLProperties.getCrlTimeoutRead();
							int connectionTimeout = TSLProperties.getCrlTimeoutConnection();

							// Recorremos las URI e intentamos descargar la
							// CRL...
							for (int index = 0; crl == null && index < uriDistPointsList.size(); index++) {

								// Obtenemos la uri a analizar.
								URI uri = uriDistPointsList.get(index);

								// Tratamos de obtener la CRL.
								crl = downloadCRLFromSupplyPoint(uri, connectionTimeout, readTimeout);
								// Si la CRL es nula o no es válida respecto a
								// la fecha de validación, la descartamos.
								if (crl != null && !checkCRLisValid(crl, validationDate, !isTsaCertificate, validationResult, tsp, tslValidator)) {
									crl = null;
								}
								// Si no es nula, guardamos la URI.
								if (crl != null) {
									uriSelected = uri.toString();
								}

							}

						}

					}

				}

				// Si tras recorrer los distribution points hemos obtenido una
				// CRL,
				// NO la validamos ya que consideramos que al estar indicada en
				// el
				// distribution point
				// de un certificado detectado en una TSL, dicha información es
				// veraz.
				// Buscamos el certificado dentro de la CRL.
				if (crl != null) {

					// Asignamos la CRL a aplicar.
					validationResult.setRevocationValueCRL(crl);
					validationResult.setRevocationValueURL(uriSelected);
					// Buscamos el certificado dentro de esta.
					searchCertInCRL(cert, validationDate, crl, validationResult);

				}

			} else {

				LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL185));

			}

		} else {

			LOGGER.warn(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL185));

		}

	}

}
