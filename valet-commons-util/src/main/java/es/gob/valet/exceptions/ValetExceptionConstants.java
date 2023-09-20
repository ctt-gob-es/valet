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
 * <b>File:</b><p>es.gob.valet.exceptions.IValetException.java.</p>
 * <b>Description:</b><p> Class defining constants codes accepted for
 * the exceptions on the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>20/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.8, 19/09/2023.
 */
package es.gob.valet.exceptions;

/**
 * <p>Class defining constants codes accepted for
 * the exceptions on the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 19/09/2023.
 */
public class ValetExceptionConstants {

	// /** Constant attribute that represents the error code: Unexpected Error.
	// */
	// public static final String COD_000 = "COD_000";
	//
	// /** Constant attribute that represents the error code: Error in input
	// parameters WebServices. */
	// public static final String COD_001 = "COD_001";
	//
	// /** Constant attribute that represents the error code: Certificate with
	// not valid extensions. */
	// public static final String COD_024 = "COD_024";
	//
	// /** Constant attribute that represents the error code: The signature of
	// the certificate is not valid. */
	// public static final String COD_025 = "COD_025";
	//
	// /** Constant attribute that represents the error code: Unsupported
	// certificate type. */
	// public static final String COD_045 = "COD_045";
	//
	// /** Constant attribute that represents the error code: Implementation
	// verification method that does not exist. */
	// public static final String COD_046 = "COD_046";
	//
	// /** Constant attribute that represents the error code: OCSP validation
	// error. There is no hash algorithm. */
	// public static final String COD_047 = "COD_047";
	//
	// /** Constant attribute that represents the error code: Error sending OCSP
	// request. */
	// public static final String COD_048 = "COD_048";
	//
	// /** Constant attribute that represents the error code: Failed to validate
	// the certificate signer of the OCSP response. */
	// public static final String COD_049 = "COD_049";
	//
	// /** Constant attribute that represents the error code: Error in OCSP
	// response, no contains ExtendedKeyUsage extension, or do not trust the
	// responder. */
	// public static final String COD_050 = "COD_050";
	//
	// /** Constant attribute that represents the error code: Error in OCSP
	// response, sending identifiers response do not match. */
	// public static final String COD_053 = "COD_053";
	//
	// /** Constant attribute that represents the error code: Error in OCSP
	// response, trusted interval exceeded. */
	// public static final String COD_054 = "COD_054";
	//
	// /** Constant attribute that represents the error code: OCSP response
	// error. */
	// public static final String COD_055 = "COD_055";
	//
	// /** Constant attribute that represents the error code: Error accessing to
	// the provider for get the OCSP Response. */
	// public static final String COD_056 = "COD_056";
	//
	// /** Constant attribute that represents the error code: Error validating
	// certificate status. */
	// public static final String COD_057 = "COD_057";
	//
	/** Constant attribute that represents the error code: Failed to get info of the certificate. */
	public static final String COD_058 = "COD_058";
	//
	// /** Constant attribute that represents the error code: Error validating
	// the CRL. */
	// public static final String COD_060 = "COD_060";
	//
	// /** Constant attribute that represents the error code: Failed to obtain
	// the CRL. */
	// public static final String COD_061 = "COD_061";
	//
	// /** Constant attribute that represents the error code: Error related to
	// the configuration of an application. */
	// public static final String COD_062 = "COD_062";
	//
	// /** Constant attribute that represents the error code: The type of
	// certificate to validate is disabled for the specified policy. */
	// public static final String COD_063 = "COD_063";
	//
	// /** Constant attribute that represents the error code: The type of
	// certificate to validate is disabled. */
	// public static final String COD_064 = "COD_064";
	//
	// /** Constant attribute that represents the error code: The certificate
	// issuer is discharged or revoked in the system. */
	// public static final String COD_065 = "COD_065";
	//
	// /** Constant attribute that represents the error code: The certificate to
	// validate is not supported by the system. */
	// public static final String COD_066 = "COD_066";
	//
	// /** Constant attribute that represents the error code: Error CRL store.
	// */
	// public static final String COD_076 = "COD_076";
	//
	// /** Constant attribute that represents the error code: Provider
	// certificate revoked. */
	// public static final String COD_080 = "COD_080";
	//
	// /** Constant attribute that represents the error code: Internal server
	// error when is in process a validation. */
	// public static final String COD_083 = "COD_083";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the electronic signature of the event log file. */
	// public static final String COD_084 = "COD_084";
	//
	// /** Constant attribute that represents the error code: Error in the
	// process of signing the frames of the OCSPResponse. */
	// public static final String COD_085 = "COD_085";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the electronic signature of the SOAP response platform. */
	// public static final String COD_086 = "COD_086";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the Electronic Signature Server. */
	// public static final String COD_087 = "COD_087";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the CoSign Electronic Signature Server. */
	// public static final String COD_088 = "COD_088";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the CounterSign Electronic Signature Server. */
	// public static final String COD_089 = "COD_089";
	//
	// /** Constant attribute that represents the error code: Error in the
	// generation of the specific CounterSign Electronic Signature Server. */
	// public static final String COD_090 = "COD_090";
	//
	// /** Constant attribute that represents the error code: Error in the
	// authorization of the application for the generation of server signature
	// with a specific server certificate. */
	// public static final String COD_091 = "COD_091";
	//
	// /** Constant attribute that represents the error code: Failed Electronic
	// Signature Validation. */
	// public static final String COD_103 = "COD_103";
	//
	// /** Constant attribute that represents the error code: Error storing
	// document information in Custody. */
	// public static final String COD_106 = "COD_106";
	//
	// /** Constant attribute that represents the error code: Error deleting the
	// contents of the document from Custody. */
	// public static final String COD_107 = "COD_107";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// the document ID from Custody. */
	// public static final String COD_108 = "COD_108";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// contents of the document from Custody by its identifier. */
	// public static final String COD_109 = "COD_109";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// contents of the document from Custody by transaction ID. */
	// public static final String COD_110 = "COD_110";
	//
	// /** Constant attribute that represents the error code: Error updating the
	// store signature in Custody. */
	// public static final String COD_111 = "COD_111";
	//
	// /** Constant attribute that represents the error code: Error updating the
	// document hash in Custody. */
	// public static final String COD_112 = "COD_112";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// document hash in Custody. */
	// public static final String COD_113 = "COD_113";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// signature in Custody. */
	// public static final String COD_114 = "COD_114";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// electronic signature of one application from Custody. */
	// public static final String COD_115 = "COD_115";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// signature format. */
	// public static final String COD_116 = "COD_116";
	//
	// /** Constant attribute that represents the error code: Error getting the
	// authorization of execution of a Web Service for an application. */
	// public static final String COD_117 = "COD_117";
	//
	// /** Constant attribute that represents the error code: Error storing an
	// electronic signature. */
	// public static final String COD_118 = "COD_118";
	//
	// /** Constant attribute that represents the error code: Error initializing
	// the TreeCache. */
	// public static final String COD_152 = "COD_152";

	/** Constant attribute that represents the error code: Cache can not be stopped because has not been created yet. */
	public static final String COD_153 = "COD_153";

	/** Constant attribute that represents the error code: Error getting / setting some type of object in the Cache. */
	public static final String COD_154 = "COD_154";

	/** Constant attribute that represents the error code: The path on which you plan to store some object in the Cache is badly constructed. */
	public static final String COD_155 = "COD_155";
	//
	// /** Constant attribute that represents the error code: Error in the CRLs
	// TreeCache. */
	// public static final String COD_156 = "COD_156";
	//
	// /** Constant attribute that represents the error code: Failed to generate
	// the upgrade signature. */
	// public static final String COD_157 = "COD_157";
	//
	// /** Constant attribute that represents the error code: Error custodying
	// the signature in upgrade process. */
	// public static final String COD_158 = "COD_158";
	//
	// /** Constant attribute that represents the error code: The signature to
	// update is not valid. */
	// public static final String COD_159 = "COD_159";
	//
	// /** Constant attribute that represents the error code: No format has been
	// provided to update. */
	// public static final String COD_160 = "COD_160";
	//
	// /** Constant attribute that represents the error code: The signing of
	// entry to detect its format is null. */
	// public static final String COD_162 = "COD_162";
	//
	// /** Constant attribute that represents the error code: Can not create a
	// PDF detector for the input signature. Must contain objects PdfPKCS7. */
	// public static final String COD_163 = "COD_163";
	//
	// /** Constant attribute that represents the error code: Can not create an
	// ODF detector for the input signature. Must contain signatures XMLDSIG. */
	// public static final String COD_164 = "COD_164";
	//
	// /** Constant attribute that represents the error code: Can not create a
	// ASN.1 detector for the input signature. Must be an ASN.1 object. */
	// public static final String COD_165 = "COD_165";
	//
	// /** Constant attribute that represents the error code: Can not create an
	// XML detector for the input signature. Must be an XML document. */
	// public static final String COD_166 = "COD_166";
	//
	// /** Constant attribute that represents the error code: Can not create a
	// S/MIME detector for the input signature. It must be a MIME message. */
	// public static final String COD_167 = "COD_167";
	//
	// /** Constant attribute that represents the error code: Error reading
	// input signature to create the detector. */
	// public static final String COD_168 = "COD_168";
	//
	// /** Constant attribute that represents the error code: Can not create a
	// specific format detector. Unrecognized type of signature. */
	// public static final String COD_169 = "COD_169";
	//
	// /** Constant attribute that represents the error code: Error creating a
	// detector explicitly. The type of signature can not be null. */
	// public static final String COD_170 = "COD_170";
	//
	// /** Constant attribute that represents the error code: Error creating a
	// specific detector. The input signature isn't the specific type. */
	// public static final String COD_171 = "COD_171";
	//
	// /** Constant attribute that represents the error code: Can not create a
	// detector for the type of specific signature. The signature is a type
	// doesn't support. */
	// public static final String COD_172 = "COD_172";
	//
	// /** Constant attribute that represents the error code: Error processing
	// the response of historical validation service. */
	// public static final String COD_173 = "COD_173";
	//
	// /** Constant attribute that represents the error code: The provider does
	// not exist in the politic or is disabled. */
	// public static final String COD_174 = "COD_174";
	//
	// /** Constant attribute that represents the error code: Error during the
	// management of alarms configured on the platform. */
	// public static final String COD_177 = "COD_177";
	//
	// /** Constant attribute that represents the error code: Error during
	// access or write to the schema of the alarms events module of the
	// database. */
	// public static final String COD_178 = "COD_178";
	//
	// /** Constant attribute that represents the error code: Error while
	// processing the security headers. */
	// public static final String COD_179 = "COD_179";
	//
	// /** Constant attribute that represents the error code: The given policy
	// does not exist or there are no defined policies in the system. */
	// public static final String COD_180 = "COD_180";
	//
	// /** Constant attribute that represents the error code: Error in the
	// Signing Policies TreeCache. */
	// public static final String COD_181 = "COD_181";

	/** Constant attribute that represents the error code: Error managing an valET Scheduler. */
	public static final String COD_184 = "COD_184";

	/** Constant attribute that represents the error code: Error managing an valET Task. */
	public static final String COD_185 = "COD_185";

	// /** Constant attribute that represents the error code: Error managing an
	// Afirma configuration object. */
	// public static final String COD_186 = "COD_186";

	/** Constant attribute that represents the error code: Error working with TSLs.	*/
	public static final String COD_187 = "COD_187";

	// /** Constant attribute that represents the error code: Error working with
	// delegate administration. */
	// public static final String COD_188 = "COD_188";
	//
	// /** Constant attribute that represents the error code: The service is
	// unauthorized for the indicated application. */
	// public static final String COD_189 = "COD_189";
	//
	/** Constant attribute that represents the error code: Error managing Keystore. */
	public static final String COD_190 = "COD_190";

	/** Constant attribute that represents the error code: Error in the Configuration Cache. */
	public static final String COD_191 = "COD_191";

	// /** Constant attribute that represents the error code: Error with a
	// SSL+SNI connection. */
	// public static final String COD_192 = "COD_192";

	/** Constant attribute that represents the error code: Unknown host. */
	public static final String COD_193 = "COD_193";

	/** Constant attribute that represents the error code: Network connection timeout. */
	public static final String COD_194 = "COD_194";

	/** Constant attribute that represents the error code: Connection refused. */
	public static final String COD_195 = "COD_195";

	/** Constant attribute that represents the error code: Connection no available.	*/
	public static final String COD_196 = "COD_196";

	/** Constant attribute that represents the error code: Not found. */
	public static final String COD_197 = "COD_197";

	/** Constant attribute that represents the error code: Generic error. */
	public static final String COD_198 = "COD_198";

	/** Constant attribute that represents the error code: Error managing encoded (AES) messages. */
	public static final String COD_199 = "COD_199";

	/** Constant attribute that represents the error code: Commons utilities error. */
	public static final String COD_200 = "COD_200";

	/** Constant attribute that represents the error code: EMail error. */
	public static final String COD_201 = "COD_201";

	/** Constant attribute that represents the error code: Audit trace error. */
	public static final String COD_202 = "COD_202";

	/** Constant attribute that represents the error code: Error getting the list of versions of the enabled TSLs.	*/
	public static final String COD_203 = "COD_203";

	/** Constant attribute that represents the error code: Error checking TSL certificate.	*/
	public static final String COD_204 = "COD_204";
	
	/** Constant attribute that represents the error code: Error getting the certification chain and its revocation evidence	*/
	public static final String COD_205 = "COD_205";

}
