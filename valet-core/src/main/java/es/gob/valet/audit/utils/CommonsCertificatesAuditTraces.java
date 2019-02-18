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
 * <b>File:</b><p>es.gob.valet.audit.utils.CommonsCertificatesAuditTraces.java.</p>
 * <b>Description:</b><p>Class that provides methods for registering the most commons audit traces associated to the certificates.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/02/2019.
 */
package es.gob.valet.audit.utils;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.audit.access.EventsCollector;
import es.gob.valet.audit.access.IEventsCollectorConstants;
import es.gob.valet.commons.utils.CryptographicConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsCrypto;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;

/**
 * <p>Class that provides methods for registering the most commons audit traces associated to the certificates.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/02/2019.
 */
public final class CommonsCertificatesAuditTraces {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(CommonsCertificatesAuditTraces.class);

	/**
	 * Attribute that represents the map that has the relations between the certificate operation id
	 * and the fields ids that must be covered.
	 */
	private static Map<Integer, String[ ]> operationFieldsNamesMap = new ConcurrentHashMap<Integer, String[ ]>();

	static {
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_CERT_INFO, new String[ ] { IEventsCollectorConstants.FIELD_NAME_CERT_ISSUER_COUNTRY, IEventsCollectorConstants.FIELD_NAME_CERT_ISSUER, IEventsCollectorConstants.FIELD_NAME_CERT_SUBJECT, IEventsCollectorConstants.FIELD_NAME_CERT_SERIAL_NUMBER, IEventsCollectorConstants.FIELD_NAME_CERT_VALID_FROM, IEventsCollectorConstants.FIELD_NAME_CERT_VALID_TO });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_CERT_ISTSA, new String[ ] { IEventsCollectorConstants.FIELD_NAME_CERT_ISTSA });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_CERT_BASICOCSPRESP_INFO, new String[ ] { IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_URL, IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_HA, IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_HASH });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_CERT_CRL_INFO, new String[ ] { IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_URL, IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_HA, IEventsCollectorConstants.FIELD_NAME_CERT_REVEVID_HASH });
		operationFieldsNamesMap.put(IEventsCollectorConstants.OPERATION_CERT_MAPPING_FIELDS, new String[ ] { IEventsCollectorConstants.FIELD_NAME_CERT_FIELDS });
	}

	/**
	 * Constructor method for the class CommonsCertificatesAuditTraces.java.
	 */
	private CommonsCertificatesAuditTraces() {
		super();
	}

	/**
	 * Gets the fields names related with the input certificate operation.
	 * @param operationId Operation id to search.
	 * @return fields names related with the input certificate operation.
	 */
	public static String[ ] getOperationFieldsNames(Integer operationId) {
		return operationFieldsNamesMap.get(operationId);
	}

	/**
	 * Registers a trace that shows the info that represents the certificate to analyze in the service.
	 * @param transactionId Audit transaction identifier.
	 * @param cert X509v3 certificate from which extracts the info.
	 */
	public static void addCertInfoOperationTrace(String transactionId, X509Certificate cert) {

		String certIssuerCountry = UtilsCertificate.getIssuerCountryOfTheCertificateString(cert);
		String certIssuer = null;
		try {
			certIssuer = UtilsCertificate.getCertificateIssuerId(cert);
		} catch (CommonUtilsException e) {
			certIssuer = IEventsCollectorConstants.FIELD_VALUE_ERROR;
		}
		String certSubject = null;
		try {
			certSubject = UtilsCertificate.getCertificateId(cert);
		} catch (CommonUtilsException e) {
			certIssuer = IEventsCollectorConstants.FIELD_VALUE_ERROR;
		}
		String certSerialNumber = cert.getSerialNumber().toString();
		String certValidFrom = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_JSON, cert.getNotBefore());
		String certValidTo = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_JSON, cert.getNotAfter());
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_CERT_INFO, certIssuerCountry, certIssuer, certSubject, certSerialNumber, certValidFrom, certValidTo);

	}

	/**
	 * Registers a trace that shows if the certificate is a TSA certificate.
	 * @param transactionId Audit transaction identifier.
	 * @param isTsaCert Flag that indicates if the certificate is a TSA certificate (<code>true</code>),
	 * or not (<code>false</code>).
	 */
	public static void addCertIsTsaCert(String transactionId, boolean isTsaCert) {
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_CERT_ISTSA, Boolean.toString(isTsaCert));
	}

	/**
	 * Registers a trace that shows the basic ocsp response revocation evidence used to validate the certificate.
	 * @param transactionId Audit transaction identifier.
	 * @param revocationValueURL URL from which has been obtained the revocation value.
	 * @param revocationValueBasicOCSPResponse Basic OCSP response.
	 */
	public static void addCertValidatedWithBasicOcspResponseTrace(String transactionId, String revocationValueURL, BasicOCSPResp revocationValueBasicOCSPResponse) {

		// Si no es nula, calculamos el hash de la evidencia.
		String hashAlgorithm = null;
		String hashRevEvidInBase64 = null;
		if (revocationValueBasicOCSPResponse != null) {
			try {
				hashRevEvidInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, revocationValueBasicOCSPResponse.getEncoded(), null);
				hashAlgorithm = CryptographicConstants.HASH_ALGORITHM_SHA512;
			} catch (CommonUtilsException | IOException e) {
				LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.CCAT_000), e);
			}
		}
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_CERT_BASICOCSPRESP_INFO, revocationValueURL, hashAlgorithm, hashRevEvidInBase64);

	}

	/**
	 * Registers a trace that shows the CRL revocation evidence used to validate the certificate.
	 * @param transactionId Audit transaction identifier.
	 * @param revocationValueURL URL from which has been obtained the revocation value.
	 * @param revocationValueCRL X509 CRL.
	 */
	public static void addCertValidatedWithCRLTrace(String transactionId, String revocationValueURL, X509CRL revocationValueCRL) {

		// Si no es nula, calculamos el hash de la evidencia.
		String hashAlgorithm = null;
		String hashRevEvidInBase64 = null;
		if (revocationValueCRL != null) {
			try {
				hashRevEvidInBase64 = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA512, revocationValueCRL.getEncoded(), null);
				hashAlgorithm = CryptographicConstants.HASH_ALGORITHM_SHA512;
			} catch (CommonUtilsException | CRLException e) {
				LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.CCAT_000), e);
			}
		}
		EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_CERT_BASICOCSPRESP_INFO, revocationValueURL, hashAlgorithm, hashRevEvidInBase64);

	}

	/**
	 * Registers a trace that shows the mappings fields obtained for a specific certificate.
	 * @param transactionId Audit transaction identifier.
	 * @param mappings Mappings obtained for the certificate.
	 */
	public static void addCertMappingFieldsTrace(String transactionId, Map<String, String> mappings) {

		// Si se han obtenido mapeos...
		if (mappings != null && !mappings.isEmpty()) {

			// Obtenemos el conjunto de keys ordenadas alfabéticamente.
			Set<String> keys = new TreeSet<String>(mappings.keySet());
			// Usamos un StringBuilder para construir el valor del campo.
			StringBuilder sb = new StringBuilder();
			// Recorremos los mapeos y vamos formando la cadena.
			for (String key: keys) {
				sb.append(key);
				sb.append(UtilsStringChar.SYMBOL_COMMA);
			}
			// Ajustamos al tamaño de la cadena.
			sb.trimToSize();
			// Eliminamos la última coma.
			sb.delete(sb.length() - 1, sb.length());

			// Se pinta la traza en auditoría.
			EventsCollector.addTrace(transactionId, IEventsCollectorConstants.OPERATION_CERT_MAPPING_FIELDS, sb.toString());

			// Limpiamos el StringBuilder.
			sb.setLength(0);
			sb.trimToSize();

		}

	}

}
