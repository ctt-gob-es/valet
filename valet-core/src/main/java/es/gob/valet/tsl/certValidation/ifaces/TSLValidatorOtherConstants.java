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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorOtherConstants.java.</p>
 * <b>Description:</b><p>Interface that defines all the constants needed for validation and mapping process in TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.certValidation.ifaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bouncycastle.asn1.x509.qualified.ETSIQCObjectIdentifiers;

import es.gob.valet.utils.TSLOIDs;

/**
 * <p>Interface that defines all the constants needed for validation and mapping process in TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class TSLValidatorOtherConstants {

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the QCStatements
	 * that qualifies a certificate how qualified.
	 */
	public static final String[ ] QCSTATEMENTS_OIDS_FOR_QUALIFIED_CERTS_STRING_ARRAY = new String[ ] { ETSIQCObjectIdentifiers.id_etsi_qcs_QcCompliance.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the QCStatements
	 * that qualifies a certificate how qualified.
	 */
	public static final List<String> QCSTATEMENTS_OIDS_FOR_QUALIFIED_CERTS_LIST = new ArrayList<String>(Arrays.asList(QCSTATEMENTS_OIDS_FOR_QUALIFIED_CERTS_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate how qualified.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_QUALIFIED_CERTS_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC_WITH_SSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL_QSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL_QSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_WEB.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate how qualified.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_QUALIFIED_CERTS_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_QUALIFIED_CERTS_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate for ESig.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_ESIG_CERTS_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC_WITH_SSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL_QSCD.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate how ESig.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_ESIG_CERTS_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_ESIG_CERTS_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate for ESeal.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_ESEAL_CERTS_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL_QSCD.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate how ESeal.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_ESEAL_CERTS_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_ESEAL_CERTS_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate for WSA.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_WSA_CERTS_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_WEB.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that qualifies a certificate how WSA.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_WSA_CERTS_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_WSA_CERTS_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that sets a certificate in a QSCD.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_QSCD_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC_WITH_SSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL_QSCD.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL_QSCD.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that sets a certificate in a QSCD.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_QSCD_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_QSCD_STRING_ARRAY));

	/**
	 * Constant attribute that represents an array with the OIDs (String) of the Policy Identifiers
	 * that sets a certificate in a QSCD.
	 */
	public static final String[ ] POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_NO_QSCD_STRING_ARRAY = new String[ ] { TSLOIDs.OID_POLICY_IDENTIFIER_QCP_PUBLIC.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_LEGAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_NATURAL.getId(), TSLOIDs.OID_POLICY_IDENTIFIER_QCP_WEB.getId() };

	/**
	 * Constant attribute that represents a list with the OIDs (String) of the Policy Identifiers
	 * that sets a certificate in a QSCD.
	 */
	public static final List<String> POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_NO_QSCD_LIST = new ArrayList<String>(Arrays.asList(POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_NO_QSCD_STRING_ARRAY));

}
