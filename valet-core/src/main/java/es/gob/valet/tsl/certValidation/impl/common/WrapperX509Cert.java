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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.tsl.certValidation.impl.common;

import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.Certificate;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public class WrapperX509Cert {

	/**
	 * Attribute that represents the X.509 Certificate (Bouncy Castle Provider). 
	 */
	private Certificate x509Certificate = null;
	
	/**
	 * Constructor method for the class WrapperX509Cert.java. 
	 */
	private WrapperX509Cert() {
		super();
	}
	
	/**
	 * Constructor method for the class WrapperX509Cert.java.
	 * @param cert X.509 certificate to wrap.
	 * @throws TSLCertificateValidationException In case of some error parsing
	 * the input certificate with Bouncy Castle provider. 
	 */
	public WrapperX509Cert(X509Certificate cert) throws TSLCertificateValidationException {
		this();
		try {
			x509Certificate = UtilsCertificate.getBouncyCastleCertificate(cert);
		} catch (CommonUtilsException e) {
			throw new TSLCertificateValidationException(IValetException.COD_187, e.getMessage(), e);
		}
	}
	
	// TODO Falta toda la lógica del wrapper.

}
