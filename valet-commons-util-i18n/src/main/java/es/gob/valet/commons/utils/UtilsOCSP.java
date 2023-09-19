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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsOCSP.java.</p>
 * <b>Description:</b><p>Utilities class that provides functionality to manage and work with OCSP request and responses.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.commons.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;

import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ValetExceptionConstants;

/**
 * <p>Utilities class that provides functionality to manage and work with OCSP request and responses.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public final class UtilsOCSP {

	/**
	 * Constructor method for the class UtilsOCSP.java.
	 */
	private UtilsOCSP() {
		super();
	}

	/**
	 * Method that obtains the OCSP response associated to the validation of certain certificate.
	 * @param cert Parameter that represents the certificate.
	 * @param ocspVals Parameter that represents the list of OCSP responses to process.
	 * @param date Date to be considered for the OCSP Response.
	 * @return an object that represents the OCSP response.
	 * @throws CommonUtilsException If the method fails.
	 */
	public static BasicOCSPResp getOCSPResponse(X509Certificate cert, BasicOCSPResp[ ] ocspVals, Date date) throws CommonUtilsException {
		BasicOCSPResp result = null;

		if (ocspVals != null) {
			int i = 0;
			while (i < ocspVals.length && result == null) {
				if (ocspVals[i] != null) {
					SingleResp[ ] responses = ocspVals[i].getResponses();
					if (responses != null) {
						int j = 0;
						while (j < responses.length && result == null) {
							if (isOCSPCertResponse(cert, responses[j], date)) {
								result = ocspVals[i];
							}
							j++;
						}
					}
				}
				i++;
			}
		}
		return result;
	}

	/**
	 * Method that checks if an OCSP response is related with certain certificate (<code>true</code>) or not (<code>false</code>).
	 * @param cert Parameter that represents the certificate.
	 * @param singleResp Parameter that represents the OCSP response.
	 * @param date Date to be considered for the OCSP Response.
	 * @return a boolean that indicates if an OCSP response is related with certain certificate (<code>true</code>) or not (<code>false</code>).
	 * @throws CommonUtilsException If the method fails.
	 */
	public static boolean isOCSPCertResponse(X509Certificate cert, SingleResp singleResp, Date date) throws CommonUtilsException {
		boolean result = false;
		if (date != null && date.before(singleResp.getThisUpdate())) {
			result = false;
		} else if (date != null && singleResp.getNextUpdate() != null && date.after(singleResp.getNextUpdate())) {
			result = false;
		} else if (singleResp.getCertID() != null) {

			try {
				CertificateID certificateId = singleResp.getCertID();
				if (certificateId.getSerialNumber().equals(cert.getSerialNumber())) {
					DigestCalculatorProvider bcDigestProvider = new BcDigestCalculatorProvider();
					DigestCalculator dc = bcDigestProvider.get(CertificateID.HASH_SHA1);
					X500Name issuerSubjectX500Name = UtilsCertificate.getBouncyCastleCertificate(cert).getIssuer();
					OutputStream dcOs = dc.getOutputStream();
					dcOs.write(issuerSubjectX500Name.getEncoded(ASN1Encoding.DER));
					dcOs.close();
					ASN1OctetString issuerNameHash = new DEROctetString(dc.getDigest());
					result = Arrays.equals(issuerNameHash.getOctets(), certificateId.getIssuerNameHash());
				}
			} catch (OperatorCreationException | IOException e) {
				throw new CommonUtilsException(ValetExceptionConstants.COD_200, e.getMessage(), e);
			}

		}
		return result;
	}

}
