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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.CertificateChainValidator.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/03/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 03/04/2023.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Vector;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.i18n.messages.IRestGeneralMessages;
import es.gob.valet.rest.elements.TslRevocationStatus;
import es.gob.valet.rest.elements.json.ByteArrayB64;
import es.gob.valet.rest.elements.json.DateString;
import es.gob.valet.rest.services.ITslRestServiceRevocationEvidenceType;
import es.gob.valet.rest.services.ITslRestServiceRevocationStatus;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 03/04/2023.
 */
public class CertificateChainValidator {

	/**
	 * Constant attribute that represents a token for a TSP Service Name when the validation has been executed
	 * using the Distribution Point of the certificate to validate.
	 */
	public static final String TSP_SERVICE_NAME_FOR_DIST_POINT = "TSPService-Certificate-DistributionPoint";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(CertificateChainValidator.class);

	/**
	 * Constructor method for the class CertificateChainValidator.java. 
	 */
	public CertificateChainValidator() {
		super();
	}

	/**
	 * Method that validates the input x509 v3 certificate using the distribution point information on it.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param issuerCert Issuer certificate of the certificate to validate.
	 * @param certificateChain Certification chain of the certificate to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @return Object that stores the information obtained when validating the certification chain.
	 */

	public TslRevocationStatus validateCertificateUsingDistributionPointsCertificateChain(X509Certificate cert, X509Certificate issuerCert, Vector<X509Certificate> certificateChain, boolean isCACert, boolean isTsaCertificate, Date validationDate) {
		// Construimos el objeto que contendrá la información de
		// revocación.
		// Tratamos de validar el estado de revocación mediante los
		// puntos de distribución
		// establecidos en el propio certificado.
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL216, new Object[ ] { validationDate.toString() }));

		TSLValidatorResult result = new TSLValidatorResult();
		TslRevocationStatus tslRevocationStatus = new TslRevocationStatus();

		// por defecto indicamos que el estado de revocación es desconocido.
		result.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_UNKNOWN);
		result.setIssuerCert(issuerCert);

		try {
			validateCertificateChainUsingDistributionPoints(cert, certificateChain, isCACert, isTsaCertificate, validationDate, result);

			// Asignamos el resultado de comprobación de estado de
			// revocación.
			tslRevocationStatus.setRevocationStatus(result.getResult());
			tslRevocationStatus.setIsFromServStat(Boolean.FALSE);
			tslRevocationStatus.setUrl(result.getRevocationValueURL());

			String msg = null;

			// En función del resultado (sabemos que ha sido
			// detectado)...
			switch (tslRevocationStatus.getRevocationStatus()) {
				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_UNKNOWN:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG021);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					break;

				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_VALID:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG022);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					addRevocationInfoInResult(tslRevocationStatus, result);
					break;

				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG023);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					if (!tslRevocationStatus.getIsFromServStat()) {

						addRevocationInfoInResult(tslRevocationStatus, result);

					}
					break;

				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG024);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					break;

				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED_SERVICESTATUS:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG025);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					break;

				case ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_CERTCHAIN_NOTVALID_SERVICESTATUS:
					msg = Language.getResRestGeneral(IRestGeneralMessages.REST_LOG026);
					LOGGER.info(msg);
					tslRevocationStatus.setRevocationDesc(msg);
					break;
				default:
					break;
			}

		} catch (CRLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tslRevocationStatus;

	}

	/**
	 * Validates the certificate revocation status using the distribution points set on it. First try
	 * the OCSP distribution points, and then the CRL distribution points.
	 * @param cert X509v3 Certificate to validate.
	 * @param isCACert Flag that indicates if the input certificate has the Basic Constraints with the CA flag activated
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object in which is stored the validation result data.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 */
	private void validateCertificateChainUsingDistributionPoints(X509Certificate cert, Vector<X509Certificate> certificateChain, boolean isCACert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult) {

		// Creamos un validador mediante OCSP para analizar los distribution
		// points de este tipo (AIA).
		ITSLValidatorThroughSomeMethod tslValidatorMethod = new TSLValidatorThroughOCSP();
		boolean certValidated = tslValidatorMethod.validateCertificateChainUsingDistributionPoints(cert, certificateChain, isCACert, isTsaCertificate, validationDate, validationResult);

		// Si aún no se ha podido verificar el estado de revocación del
		// certificado, lo intentamos con los de tipo CRL.
		if (!certValidated && isCACert && !UtilsCertificate.isSelfSigned(cert)) {

			tslValidatorMethod = new TSLValidatorThroughCRL();
			tslValidatorMethod.validateCertificateChainUsingDistributionPoints(cert, certificateChain, isCACert, isTsaCertificate, validationDate, validationResult);

		}

	}

	/**
	 * Add the revocation information in the result.
	 * @param tslRevocationStatus TSL revocation status information to return.
	 * @param tslValidatorResult TSL validation process result to analyze.
	 * @param returnRevocationEvidence Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevStatus} is <code>true</code>).
	 * @throws IOException In case of some error decoding a Basic OCSP Response.
	 * @throws CRLException Incase of some error decoding a CRL.
	 */
	private void addRevocationInfoInResult(TslRevocationStatus tslRevocationStatus, ITSLValidatorResult tslValidatorResult) throws IOException, CRLException {

		// Establecemos la URL de donde se haya obtenido la evidencia de
		// revocación.
		tslRevocationStatus.setUrl(tslValidatorResult.getRevocationValueURL());
		// Consultamos si se ha obtenido mediante el DistributionPoint / AIA del
		// certificado.
		tslRevocationStatus.setDpAia(tslValidatorResult.isResultFromDPorAIA());

		// En función del tipo de evidencia...
		// Si es OCSP...
		if (tslValidatorResult.getRevocationValueBasicOCSPResponse() != null) {

			tslRevocationStatus.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_OCSP);
			tslRevocationStatus.setEvidence(new ByteArrayB64(tslValidatorResult.getRevocationValueBasicOCSPResponse().getEncoded()));

			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus().intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new DateString(tslValidatorResult.getRevocationDate()));
			}
		}
		// Si es CRL...
		else if (tslValidatorResult.getRevocationValueCRL() != null) {

			tslRevocationStatus.setEvidenceType(ITslRestServiceRevocationEvidenceType.REVOCATION_EVIDENCE_TYPE_CRL);
			tslRevocationStatus.setEvidence(new ByteArrayB64(tslValidatorResult.getRevocationValueCRL().getEncoded()));

			// Si el estado es revocado, devolvemos la razón y fecha.
			if (tslRevocationStatus.getRevocationStatus().intValue() == ITslRestServiceRevocationStatus.RESULT_DETECTED_REVSTATUS_REVOKED) {
				tslRevocationStatus.setRevocationReason(tslValidatorResult.getRevocationReason());
				tslRevocationStatus.setRevocationDate(new DateString(tslValidatorResult.getRevocationDate()));
			}
		}

	}

}
