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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.ifaces.ITSLValidator.java.</p>
 * <b>Description:</b><p>Interface that represents a TSL validator regardless it implementation.</p>
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

import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLValidationException;

/**
 * <p>Interface that represents a TSL validator regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public interface ITSLValidator {

	/**
	 * Validate the status of the input certificate with the information of the TSL.
	 * @param cert Certificate X509 v3 to validate.
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @return validation result object representation.
	 * @throws TSLArgumentException In case of the input certificate was <code>null</code>.
	 * @throws TSLValidationException In case of some error validating the certificate with the TSL.
	 */
	ITSLValidatorResult validateCertificateWithTSL(X509Certificate cert, boolean isTsaCertificate, Date validationDate, boolean checkStatusRevocation) throws TSLArgumentException, TSLValidationException;

	/**
	 * Verifies the revocation values for the input certificate with the information of the TSL, and then, use these
	 * to check the revocation status of the certificate.
	 * @param cert Certificate X509 v3 to validate.
	 * @param isTsaCertificate Flag that indicates if the input certificate has the id-kp-timestamping key purpose
	 * (<code>true</code>) or not (<code>false</code>).
	 * @param crls Array with the revocation values to analyze of type CRL.
	 * @param ocsps Array with the revocation values to analyze of type BasicOCSPResp.
	 * @param validationDate Validation date to check the certificate status revocation.
	 * @return validation result object representation.
	 * @throws TSLArgumentException In case of the input certificate, or the revocation values were <code>null</code>.
	 * @throws TSLValidationException In case of some error verifying the revocation values.
	 */
	ITSLValidatorResult verifiesRevocationValuesForX509withTSL(X509Certificate cert, boolean isTsaCertificate, X509CRL[ ] crls, BasicOCSPResp[ ] ocsps, Date validationDate) throws TSLArgumentException, TSLValidationException;

}
