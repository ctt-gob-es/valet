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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.ifaces.ValidatorResultConstants.java.</p>
 * <b>Description:</b><p>Interface that represents a validation result using TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 19/09/2023.
 */
package es.gob.valet.tsl.certValidation.ifaces;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;

import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.tsl.certValidation.impl.common.TSLCertificateExtensionAnalyzer;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;

/**
 * <p>Interface that represents a validation result using TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 19/09/2023.
 */
public interface ITSLValidatorResult {

	/**
	 * Gets the TSL Country/Region Code.
	 * @return the TSL Country/Region Code.
	 */
	String getTslCountryRegionCode();

	/**
	 * Gets the TSL Sequence Number.
	 * @return the value of the TSL Sequence Number.
	 */
	int getTslSequenceNumber();

	/**
	 * Gets the TSL ETSI TS Specification and Version.
	 * @return the TSL ETSI TS Specification and Version.
	 */
	String getTslEtsiSpecificationAndVersion();

	/**
	 * Gets the TSL Issue Date.
	 * @return the TSL Issue Date.
	 */
	Date getTslIssueDate();

	/**
	 * Gets the TSL Next Update Date.
	 * @return the TSL Next Update Date.
	 */
	Date getTslNextUpdate();

	/**
	 * Gets the extension analyzer for the certificate to validate.
	 * @return the extension analyzer for the certificate to validate.
	 */
	TSLCertificateExtensionAnalyzer getTslCertificateExtensionAnalyzer();

	/**
	 * Gets the validation result. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITSLValidatorResult#RESULT_NOT_DETECTED}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_UNKNOWN}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_VALID}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_REVOKED}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS}</li>
	 *   <li>{@link ITSLValidatorResult#RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS}</li>
	 * </ul>
	 * @return int that represents the validation result.
	 */
	int getResult();

	/**
	 * Checks if the validation result value is obtained from a TSL-Service status.
	 * @return <code>null</code> if the certificate has not been detected or its status is unknown. <code>true</code>
	 * if the status has been determined by a TSL-Service status, otherwise <code>false</code>.
	 */
	Boolean isResultFromServiceStatus();

	/**
	 * Checks if the validation result value is obtained from a DP or AIA.
	 * @return <code>null</code> if the certificate has not been detected or its status is unknown. <code>true</code>
	 * if the status has been determined by a DP or AIA, otherwise <code>false</code>.
	 */
	Boolean isResultFromDPorAIA();

	/**
	 * Checks if the result is different of {@link ITSLValidatorResult#RESULT_NOT_DETECTED}.
	 * @return <code>true</code> if the result is different of {@link ITSLValidatorResult#RESULT_NOT_DETECTED},
	 * otherwise <code>false</code>.
	 */
	boolean hasBeenDetectedTheCertificate();

	/**
	 * Checks if the result is equal to {@link ITSLValidatorResult#RESULT_DETECTED_STATE_UNKNOWN}.
	 * @return <code>true</code> if the result is equal to {@link ITSLValidatorResult#RESULT_DETECTED_STATE_UNKNOWN},
	 * otherwise <code>false</code>.
	 */
	boolean hasBeenDetectedTheCertificateWithUnknownState();

	/**
	 * Check if the TSL used is from an European member.
	 * @return <code>true</code> if the TSL is from an European member, otherwise <code>false</code>.
	 */
	boolean isEuropean();

	/**
	 * Gets the name of the TSP which has detected the certificate.
	 * @return the name of the TSP which has detected the certificate, or <code>null</code> if
	 * the certificate has not been detected.
	 */
	String getTSPName();

	/**
	 * Gets the TSP which has detected the certificate.
	 * @return TSP which has detected the certificate, or <code>null</code> if
	 * the certificate has not been detected.
	 */
	TrustServiceProvider getTSP();

	/**
	 * Gets the name of the TSP Service which has detected the certificate.
	 * @return the name of the TSP Service which has detected the certificate, or <code>null</code> if
	 * the certificate has not been detected.
	 */
	String getTSPServiceNameForDetect();

	/**
	 * Gets the TSP Service which has detected the certificate.
	 * @return TSP Service which has detected the certificate, or <code>null</code> if
	 * the certificate has not been detected.
	 */
	TSPService getTSPServiceForDetect();

	/**
	 * Gets the name of the TSP Service History-Information which has detected the certificate.
	 * @return the name of the TSP Service History-Information which has detected the certificate,
	 * or <code>null</code> if the certificate has not been detected.
	 */
	String getTSPServiceHistoryInformationInstanceNameForDetect();

	/**
	 * Gets the TSP Service History-Information which has detected the certificate.
	 * @return TSP Service History-Information which has detected the certificate, or
	 * <code>null</code> if the certificate has not been detected.
	 */
	ServiceHistoryInstance getTSPServiceHistoryInformationInstanceForDetect();

	/**
	 * Gets the name of the TSP Service which has validate the certificate.
	 * @return the name of the TSP Service which has validate the certificate, or <code>null</code> if
	 * the certificate has not been validated.
	 */
	String getTSPServiceNameForValidate();

	/**
	 * Gets the TSP Service which has validate the certificate.
	 * @return the TSP Service which has validate the certificate, or <code>null</code> if
	 * the certificate has not been validated.
	 */
	TSPService getTSPServiceForValidate();

	/**
	 * Gets the name of the TSP Service History-Information which has validate the certificate.
	 * @return the name of the TSP Service History-Information which has validate the
	 * certificate, or <code>null</code> if the certificate has not been validated.
	 */
	String getTSPServiceHistoryInformationInstanceNameForValidate();

	/**
	 * Gets the TSP Service History-Information which has validate the certificate.
	 * @return the TSP Service History-Information which has validate the certificate,
	 * or <code>null</code> if the certificate has not been validated.
	 */
	ServiceHistoryInstance getTSPServiceHistoryInformationInstanceForValidate();

	/**
	 * Gets the mapping type of the validated certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITSLValidatorResult#MAPPING_TYPE_UNKNOWN}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_TYPE_NONQUALIFIED}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_TYPE_QUALIFIED}</li>
	 * </ul>
	 * @return the mapping type of the validated certificate.
	 */
	int getMappingType();
	
	/**
	 * Gets the mapping  that indicates additional information returned by ETSI TS 119 615 v.1.1.1. 
	 */
	String getMappingETSIResult();

	/**
	 * Gets the mapping classification of the validated certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_OTHER_UNKNOWN}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_PERSON}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_LEGALPERSON}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_ESEAL}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_ESIG}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_WSA}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_CLASSIFICATION_TSA}</li>
	 * </ul>
	 * @return the mapping classification of the validated certificate.
	 */
	int getMappingClassification();

	/**
	 * Gets the mapping QSCD of the validated certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITSLValidatorResult#MAPPING_QSCD_UNKNOWN}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_QSCD_NO}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_QSCD_YES}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_QSCD_ASINCERT}</li>
	 *   <li>{@link ITSLValidatorResult#MAPPING_QSCD_YES_MANAGEDONBEHALF}</li>
	 * </ul>
	 * @return the mapping SSCD of the validated certificate.
	 */
	int getMappingQSCD();

	/**
	 * Gets all the mappings information calculated for the validated certificate.
	 * @return Map with the pairs <MappingName, MappingValue> calculated for the validated certificate.
	 */
	Map<String, String> getMappings();

	/**
	 * Sets the mappings calculated for the validated certificate.
	 * @param mappings Map with the pairs <MappingName, MappingValue> calculated for the validated certificate.
	 */
	void setMappings(Map<String, String> mappings);

	/**
	 * Gets the X509 Certificate of the issuer of the certificate to validate (if this has been found).
	 * @return the X509 Certificate of the issuer of the certificate to validate (if this has been found),
	 * otherwise <code>null</code>.
	 */
	X509Certificate getIssuerCert();

	/**
	 * Gets the Basic OCSP Response selected how revocation value.
	 * @return Basic OCSP Response selected how revocation value, <code>null</code> if there is not.
	 */
	BasicOCSPResp getRevocationValueBasicOCSPResponse();

	/**
	 * Sets the selected revocation value of type Basic OCSP Response.
	 * @param bor Basic OCSP Response to assign how the selected revocation value.
	 */
	void setRevocationValueBasicOCSPResponse(BasicOCSPResp bor);

	/**
	 * Gets the CRL selected how revocation value.
	 * @return CRL selected how revocation value, <code>null</code> if there is not.
	 */
	X509CRL getRevocationValueCRL();

	/**
	 * Sets the selected revocation value of type CRL.
	 * @param crl CRL to assign how the selected revocation value.
	 */
	void setRevocationValueCRL(X509CRL crl);

	/**
	 * Gets the URL from which the revocation value has been obtained.
	 * @return the URL from which the revocation value has been obtained.
	 */
	String getRevocationValueURL();

	/**
	 * Sets the URL from which the revocation value has been obtained.
	 * @param revValueUrl the URL from which the revocation value has been obtained.
	 */
	void setRevocationValueURL(String revValueUrl);

	/**
	 * Gets the revocation date of the certificate validate (if it is revoked).
	 * @return the revocation date of the certificate validate (if it is revoked), otherwise <code>null</code>.
	 */
	Date getRevocationDate();

	/**
	 * Gets the Revocation Reason for the certificate that has been validated.
	 * @return Revocation Reason integer representation for the certificate validated,
	 * or -1 if was not possible to validate or the certificate is not revoked.
	 */
	int getRevocationReason();

}
