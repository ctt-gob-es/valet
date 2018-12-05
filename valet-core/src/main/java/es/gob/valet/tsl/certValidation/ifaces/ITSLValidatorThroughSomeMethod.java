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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorThroughSomeMethod.java.</p>
 * <b>Description:</b><p>Interface that represents a certificate validation using TSL through some specific method.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.tsl.certValidation.ifaces;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.tsl.certValidation.impl.common.ATSLValidator;
import es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;

/**
 * <p>Interface that represents a certificate validation using TSL through some specific method.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public interface ITSLValidatorThroughSomeMethod {

	/**
	 * Method that validates the input X509v3 certificate using a TSL - TSP Service (CRL or OCSP).
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param tspService TSL - TSP Service from which extract the information to validate the certificate.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	void validateCertificate(X509Certificate cert, Date validationDate, TSPService tspService, TSLValidatorResult validationResult);

	/**
	 * Method that searchs if some of the input revocation values are verified by the input service, and
	 * then use it for check the revocation status of the certificate.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param basicOcspResponse Basic OCSP response to check if it is compatible with the TSL.
	 * @param crl CRL to check if is compatible with the TSL to check the revocation status of the certificate.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param tspService TSL - TSP Service from which extract the information to validate the certificate.
	 * @param validationResult Object where must be stored the validation result data.
	 */
	void searchRevocationValueCompatible(X509Certificate cert, BasicOCSPResp basicOcspResponse, X509CRL crl, Date validationDate, TSPService tspService, TSLValidatorResult validationResult);

	/**
	 * Method that validates the input x509 v3 certificate using the distribution point information on it.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param validationResult Object where must be stored the validation result data.
	 * @param tsp Trust Service Provider to use for checks the issuer of the CRL/OCSP Response.
	 * @param tslValidator TSL validator to verify if some TSP service is accomplished with the qualified (or not) certificate to check the CRL/OCSP Response.
	 */
	void validateCertificateUsingDistributionPoints(X509Certificate cert, boolean isTsaCertificate, Date validationDate, TSLValidatorResult validationResult, TrustServiceProvider tsp, ATSLValidator tslValidator);

}