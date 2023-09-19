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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.ifaces.ITSLSignatureConstants.java.</p>
 * <b>Description:</b><p>Class that defines all the used constants for working with the TSL signatures.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.utils;

/**
 * <p>Class that defines all the used constants for working with the TSL signatures.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class TSLSignatureConstants {

	/**
	 * Constant attribute that represents the URI public static final String for a XMLDSig enveloped signature.
	 */
	public static final String URI_XMLDSIG_ENVELOPED_SIGNATURE = "http://www.w3.org/2000/09/xmldsig#enveloped-signature";

	/**
	 * Constant attribute that represents the URI public static final String for the canonicalization algorithm xmlexc-c14n.
	 */
	public static final String URI_CANONICALIZATION_ALGORITHM_XMLEXC_C14N = "http://www.w3.org/2001/10/xml-exc-c14n#";

	/**
	 * Constant Attribute that represents the token element 'Signature'.
	 */
	public static final String ELEMENT_SIGNATURE = "Signature";

	/**
	 * Constant Attribute that represents the token element 'SignedInfo'.
	 */
	public static final String ELEMENT_SIGNEDINFO = "SignedInfo";

	/**
	 * Constant Attribute that represents the token element 'SignatureMethod'.
	 */
	public static final String ELEMENT_SIGNATUREMETHOD = "SignatureMethod";

	/**
	 * Constant Attribute that represents the token element 'SignatureValue'.
	 */
	public static final String ELEMENT_SIGNATUREVALUE = "SignatureValue";

}
