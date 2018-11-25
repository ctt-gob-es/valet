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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult.java.</p>
 * <b>Description:</b><p>Class that represents a TSL validation result.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;

import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSPService;

/**
 * <p>Class that represents a TSL validation result.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public class TSLValidatorResult implements ITSLValidatorResult {

	/**
	 * Attribute that represents the TSL Country/Region Code.
	 */
	private String tslCountryRegionCode = null;

	/**
	 * Attribute that represents the TSL Sequence Number.
	 */
	private int tslSequenceNumber = -1;

	/**
	 * Attribute that represents the TSL ETSI TS Specification and Version.
	 */
	private String tslEtsiSpecificationAndVersion = null;

	/**
	 * Attribute that represents the TSL Issue Date.
	 */
	private Date tslIssueDate = null;

	/**
	 * Attribute that represents the TSL Next Update Date.
	 */
	private Date tslNextUpdate = null;

	/**
	 * Attribute that represents the result of the validation, initially not detected.
	 */
	private int result = ITSLValidatorResult.RESULT_NOT_DETECTED;

	/**
	 * Attribute that represents an extension analyzer for the certificate to validate.
	 */
	private TSLCertificateExtensionAnalyzer tslCertExtAnalyzer = null;

	/**
	 * Attribute that represents the revocation date of the validated certificate (if it is revoked).
	 */
	private Date revocationDate = null;

	/**
	 * Attribute that represents the revocation reason of the validated certificate (if it is revoked).
	 */
	private int revocationReason = -1;

	/**
	 * Attribute that represents if the TSL used is from an European member.
	 */
	private boolean isEuropean = false;

	/**
	 * Attribute that represents the name of the TSP that has detected the certificate.
	 */
	private String tspName = null;

	/**
	 * Attribute that represents the name of the TSP Service used for detect the certificate.
	 */
	private String tspServiceNameForDetect = null;

	/**
	 * Attribute that represents the TSP Service used for detect the certificate.
	 */
	private TSPService tspServiceForDetect = null;

	/**
	 * Attribute that represents the name of the TSP Service used for validate the certificate.
	 */
	private String tspServiceNameForValidate = null;

	/**
	 * Attribute that represents the TSP Service used for validate the certificate.
	 */
	private TSPService tspServiceForValidate = null;

	/**
	 * Attribute that represents the mapping that indicates the type of the certificate.
	 */
	private int mappingType = ITSLValidatorResult.MAPPING_TYPE_UNKNOWN;

	/**
	 * Attribute that represents the mapping that indicates the classification of the certificate.
	 */
	private int mappingClassification = ITSLValidatorResult.MAPPING_CLASSIFICATION_OTHER_UNKNOWN;

	/**
	 * Attribute that represents the mapping that indicates if the certificate is in QSCD.
	 */
	private int mappingQSCD = ITSLValidatorResult.MAPPING_QSCD_UNKNOWN;

	/**
	 * Attribute that represents the issuer X509 certificate of the certificate to validate.
	 */
	private X509Certificate issuerCert = null;

	/**
	 * Attribute that represents the issuer name of the certificate to validate.
	 */
	private String issuerSubjectName = null;

	/**
	 * Attribute that represents the issuer Public Key of the certificate to validate.
	 */
	private PublicKey issuerPublicKey = null;

	/**
	 * Attribute that represents the issuer Subject Key Identifier of the certificate to validate in bytes.
	 */
	private byte[ ] issuerSKIbytes = null;

	/**
	 * Attribute that represents a map with the pairs <MappingName, MappingValue> calculated for the validated certificate.
	 */
	private Map<String, String> calculatedMappings = null;

	/**
	 * Attribute that represents the Basic OCSP Response selected how revocation value.
	 */
	private BasicOCSPResp basicOcspResponse = null;

	/**
	 * Attribute that represents the CRL selected how revocation value.
	 */
	private X509CRL x509crl = null;

	/**
	 * Constructor method for the class TSLValidatorResult.java.
	 */
	private TSLValidatorResult() {
		super();
	}

	/**
	 * Constructor method for the class TSLValidatorResult.java.
	 * @param cert X509v3 Certificate to validate.
	 * @param tslObject Object representation of the TSL that is going to be used in the
	 * validation process.
	 * @throws TSLValidationException In case of some error parsing the extensions
	 * of the input certificate.
	 */
	public TSLValidatorResult(X509Certificate cert, ITSLObject tslObject) throws TSLValidationException {
		this();
		try {
			tslCertExtAnalyzer = new TSLCertificateExtensionAnalyzer(cert);
		} catch (TSLCertificateValidationException e) {
			throw new TSLValidationException(IValetException.COD_187, e.getErrorDescription(), e);
		}
		if (tslObject != null) {
			tslCountryRegionCode = tslObject.getSchemeInformation().getSchemeTerritory();
			tslSequenceNumber = tslObject.getSchemeInformation().getTslSequenceNumber();
			tslEtsiSpecificationAndVersion = tslObject.getSpecification() + UtilsStringChar.SPECIAL_BLANK_SPACE_STRING + tslObject.getSpecificationVersion();
			tslIssueDate = tslObject.getSchemeInformation().getListIssueDateTime();
			tslNextUpdate = tslObject.getSchemeInformation().getNextUpdate();
		}
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslCountryRegionCode()
	 */
	@Override
	public String getTslCountryRegionCode() {
		return tslCountryRegionCode;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslSequenceNumber()
	 */
	@Override
	public int getTslSequenceNumber() {
		return tslSequenceNumber;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslEtsiSpecificationAndVersion()
	 */
	@Override
	public String getTslEtsiSpecificationAndVersion() {
		return tslEtsiSpecificationAndVersion;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslIssueDate()
	 */
	@Override
	public Date getTslIssueDate() {
		return tslIssueDate;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslNextUpdate()
	 */
	@Override
	public Date getTslNextUpdate() {
		return tslNextUpdate;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTslCertificateExtensionAnalyzer()
	 */
	@Override
	public TSLCertificateExtensionAnalyzer getTslCertificateExtensionAnalyzer() {
		return tslCertExtAnalyzer;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getResult()
	 */
	@Override
	public int getResult() {
		return result;
	}

	/**
	 * Sets the value of the attribute {@link #result}.
	 * @param resultParam The value for the attribute {@link #result}.
	 */
	public final void setResult(int resultParam) {
		this.result = resultParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#hasBeenDetectedTheCertificate()
	 */
	@Override
	public boolean hasBeenDetectedTheCertificate() {
		return result != ITSLValidatorResult.RESULT_NOT_DETECTED;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#hasBeenDetectedTheCertificateWithUnknownState()
	 */
	@Override
	public boolean hasBeenDetectedTheCertificateWithUnknownState() {
		return result == ITSLValidatorResult.RESULT_DETECTED_STATE_UNKNOWN;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#isEuropean()
	 */
	@Override
	public boolean isEuropean() {
		return isEuropean;
	}

	/**
	 * Sets the value of the attribute {@link #isEuropean}.
	 * @param isEuropeanParam The value for the attribute {@link #isEuropean}.
	 */
	public final void setEuropean(boolean isEuropeanParam) {
		this.isEuropean = isEuropeanParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTSPName()
	 */
	@Override
	public String getTSPName() {
		return tspName;
	}

	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspNameParam The value for the attribute {@link #tspName}.
	 */
	public final void setTSPName(String tspNameParam) {
		this.tspName = tspNameParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTSPServiceNameForDetect()
	 */
	@Override
	public String getTSPServiceNameForDetect() {
		return tspServiceNameForDetect;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceNameForDetect}.
	 * @param tspServiceNameForDetectParam The value for the attribute {@link #tspServiceNameForDetect}.
	 */
	public final void setTSPServiceNameForDetect(String tspServiceNameForDetectParam) {
		this.tspServiceNameForDetect = tspServiceNameForDetectParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceForDetect}.
	 * @return the value of the attribute {@link #tspServiceForDetect}.
	 */
	public final TSPService getTSPServiceForDetect() {
		return tspServiceForDetect;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceForDetect}.
	 * @param tspServiceForDetectParam The value for the attribute {@link #tspServiceForDetect}.
	 */
	public final void setTSPServiceForDetect(TSPService tspServiceForDetectParam) {
		this.tspServiceForDetect = tspServiceForDetectParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getTSPServiceNameForValidate()
	 */
	@Override
	public String getTSPServiceNameForValidate() {
		return tspServiceNameForValidate;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceNameForValidate}.
	 * @param tspServiceNameForValidateParam The value for the attribute {@link #tspServiceNameForValidate}.
	 */
	public final void setTSPServiceNameForValidate(String tspServiceNameForValidateParam) {
		this.tspServiceNameForValidate = tspServiceNameForValidateParam;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceForValidate}.
	 * @return the value of the attribute {@link #tspServiceForValidate}.
	 */
	public final TSPService getTSPServiceForValidate() {
		return tspServiceForValidate;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceForValidate}.
	 * @param tspServiceForValidateParam The value for the attribute {@link #tspServiceForValidate}.
	 */
	public final void setTSPServiceForValidate(TSPService tspServiceForValidateParam) {
		this.tspServiceForValidate = tspServiceForValidateParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getMappingType()
	 */
	@Override
	public int getMappingType() {
		return mappingType;
	}

	/**
	 * Sets the value of the attribute {@link #mappingType}.
	 * @param mappingTypeParam The value for the attribute {@link #mappingType}.
	 */
	public final void setMappingType(int mappingTypeParam) {
		this.mappingType = mappingTypeParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getMappingClassification()
	 */
	@Override
	public int getMappingClassification() {
		return mappingClassification;
	}

	/**
	 * Sets the value of the attribute {@link #mappingClassification}.
	 * @param mappingClassificationParam The value for the attribute {@link #mappingClassification}.
	 */
	public final void setMappingClassification(int mappingClassificationParam) {
		this.mappingClassification = mappingClassificationParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getMappingQSCD()
	 */
	@Override
	public int getMappingQSCD() {
		return mappingQSCD;
	}

	/**
	 * Sets the value of the attribute {@link #mappingQSCD}.
	 * @param mappingQSCDParam The value for the attribute {@link #mappingQSCD}.
	 */
	public final void setMappingQSCD(int mappingQSCDParam) {
		this.mappingQSCD = mappingQSCDParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getMappings()
	 */
	@Override
	public Map<String, String> getMappings() {
		return calculatedMappings;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#setMappings(java.util.Map)
	 */
	@Override
	public void setMappings(Map<String, String> mappings) {
		calculatedMappings = mappings;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getIssuerCert()
	 */
	public final X509Certificate getIssuerCert() {
		return issuerCert;
	}

	/**
	 * Sets the value of the attribute {@link #issuerCert}.
	 * @param issuerCertParam The value for the attribute {@link #issuerCert}.
	 */
	public final void setIssuerCert(X509Certificate issuerCertParam) {
		this.issuerCert = issuerCertParam;
	}

	/**
	 * Gets the value of the attribute {@link #issuerSubjectName}.
	 * @return the value of the attribute {@link #issuerSubjectName}.
	 */
	public final String getIssuerSubjectName() {
		return issuerSubjectName;
	}

	/**
	 * Sets the value of the attribute {@link #issuerSubjectName}.
	 * @param issuerSubjectNameParam The value for the attribute {@link #issuerSubjectName}.
	 */
	public final void setIssuerSubjectName(String issuerSubjectNameParam) {
		this.issuerSubjectName = issuerSubjectNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #issuerPublicKey}.
	 * @return the value of the attribute {@link #issuerPublicKey}.
	 */
	public final PublicKey getIssuerPublicKey() {
		return issuerPublicKey;
	}

	/**
	 * Sets the value of the attribute {@link #issuerPublicKey}.
	 * @param issuerPublicKeyParam The value for the attribute {@link #issuerPublicKey}.
	 */
	public final void setIssuerPublicKey(PublicKey issuerPublicKeyParam) {
		this.issuerPublicKey = issuerPublicKeyParam;
	}

	/**
	 * Gets the value of the attribute {@link #issuerSKIbytes}.
	 * @return the value of the attribute {@link #issuerSKIbytes}.
	 */
	public final byte[ ] getIssuerSKIbytes() {
		return issuerSKIbytes;
	}

	/**
	 * Sets the value of the attribute {@link #issuerSKIbytes}.
	 * @param issuerSKIbytesParam The value for the attribute {@link #issuerSKIbytes}.
	 */
	public final void setIssuerSKIbytes(byte[ ] issuerSKIbytesParam) {
		this.issuerSKIbytes = issuerSKIbytesParam;
	}

	/**
	 * Removes all the stored data about the issuer of the certificate to validate.
	 */
	public final void clearIssuerData() {
		setIssuerCert(null);
		setIssuerSubjectName(null);
		setIssuerPublicKey(null);
		setIssuerSKIbytes(null);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getRevocationValueBasicOCSPResponse()
	 */
	@Override
	public BasicOCSPResp getRevocationValueBasicOCSPResponse() {
		return basicOcspResponse;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#setRevocationValueBasicOCSPResponse(org.bouncycastle.cert.ocsp.BasicOCSPResp)
	 */
	@Override
	public void setRevocationValueBasicOCSPResponse(BasicOCSPResp bor) {
		basicOcspResponse = bor;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getRevocationValueCRL()
	 */
	@Override
	public X509CRL getRevocationValueCRL() {
		return x509crl;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#setRevocationValueCRL(java.security.cert.X509CRL)
	 */
	@Override
	public void setRevocationValueCRL(X509CRL crl) {
		x509crl = crl;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getRevocationDate()
	 */
	@Override
	public Date getRevocationDate() {
		return revocationDate;
	}

	/**
	 * Sets the value of the attribute {@link #revocationDate}.
	 * @param revocationDateParam The value for the attribute {@link #revocationDate}.
	 */
	public final void setRevocationDate(Date revocationDateParam) {
		this.revocationDate = revocationDateParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult#getRevocationReason()
	 */
	@Override
	public int getRevocationReason() {
		return revocationReason;
	}

	/**
	 * Sets the value of the attribute {@link #revocationReason}.
	 * @param revocationReasonParam The value for the attribute {@link #revocationReason}.
	 */
	public final void setRevocationReason(int revocationReasonParam) {
		this.revocationReason = revocationReasonParam;
	}

	/**
	 * Resets all the data except the certificate extension analyzer.
	 */
	public final void resetAllData() {

		setResult(ITSLValidatorResult.RESULT_NOT_DETECTED);
		setRevocationDate(null);
		setRevocationReason(-1);
		setEuropean(false);
		setTSPName(null);
		setTSPServiceNameForDetect(null);
		setTSPServiceForDetect(null);
		setTSPServiceNameForValidate(null);
		setTSPServiceForValidate(null);
		setMappingType(ITSLValidatorResult.MAPPING_TYPE_UNKNOWN);
		setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_OTHER_UNKNOWN);
		setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_UNKNOWN);
		clearIssuerData();
		setMappings(null);
		setRevocationValueBasicOCSPResponse(null);
		setRevocationValueCRL(null);

	}

}
