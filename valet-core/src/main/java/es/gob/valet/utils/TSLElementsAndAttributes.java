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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.ifaces.ITSLElementsAndAttributes.java.</p>
 * <b>Description:</b><p>Class that contains the tokens of the differents elements and attributes used
 * in the XML implementations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.utils;


/** 
 * <p>Class that contains the tokens of the differents elements and attributes used
 * in the XML implementations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/11/2018.
 */
public class TSLElementsAndAttributes {

	/**
	 * Constant attribute that represents the attribute 'TSLTag'.
	 */
	public static final String ATTRIBUTE_TSL_TAG = "TSLTag";

	/**
	 * Constant attribute that represents the element 'TSLVersionIdentifier'.
	 */
	public static final String ELEMENT_TSL_VERSION_IDENTIFIER = "TSLVersionIdentifier";

	/**
	 * Constant attribute that represents the element 'TSLSequenceNumber'.
	 */
	public static final String ELEMENT_TSL_SEQUENCE_NUMBER = "TSLSequenceNumber";

	/**
	 * Constant attribute that represents the element 'TSLType'.
	 */
	public static final String ELEMENT_TSL_TYPE = "TSLType";

	/**
	 * Constant attribute that represents the element 'SchemeOperatorName'.
	 */
	public static final String ELEMENT_SCHEME_OPERATOR_NAME = "SchemeOperatorName";

	/**
	 * Constant attribute that represents the element 'SchemeOperatorAddress'.
	 */
	public static final String ELEMENT_SCHEME_OPERATOR_ADDRESS = "SchemeOperatorAddress";

	/**
	 * Constant attribute that represents the element 'PostalAddresses'.
	 */
	public static final String ELEMENT_ADDRESS_POSTALADDRESSES = "PostalAddresses";

	/**
	 * Constant attribute that represents the element 'PostalAddress'.
	 */
	public static final String ELEMENT_ADDRESS_POSTALADDRESS = "PostalAddress";

	/**
	 * Constant attribute that represents the element 'ElectronicAddress'.
	 */
	public static final String ELEMENT_ADDRESS_ELECTRONICADDRESS = "ElectronicAddress";

	/**
	 * Constant attribute that represents the element 'SchemeName'.
	 */
	public static final String ELEMENT_SCHEME_NAME = "SchemeName";

	/**
	 * Constant attribute that represents the element 'SchemeInformation'.
	 */
	public static final String ELEMENT_SCHEME_INFORMATION = "SchemeInformation";

	/**
	 * Constant attribute that represents the element 'SchemeInformationURI'.
	 */
	public static final String ELEMENT_SCHEME_INFORMATION_URI = "SchemeInformationURI";

	/**
	 * Constant attribute that represents the element 'StatusDeterminationApproach'.
	 */
	public static final String ELEMENT_STATUS_DETERMINATION_APPROACH = "StatusDeterminationApproach";

	/**
	 * Constant attribute that represents the element 'SchemeTypeCommunityRules'.
	 */
	public static final String ELEMENT_SCHEME_TYPE_COMMUNITY_RULES = "SchemeTypeCommunityRules";

	/**
	 * Constant attribute that represents the element 'SchemeTerritory'.
	 */
	public static final String ELEMENT_SCHEME_TERRITORY = "SchemeTerritory";

	/**
	 * Constant attribute that represents the element 'PolicyOrLegalNotice'.
	 */
	public static final String ELEMENT_POLICY_OR_LEGAL_NOTICE = "PolicyOrLegalNotice";

	/**
	 * Constant attribute that represents the element 'HistoricalInformationPeriod'.
	 */
	public static final String ELEMENT_HISTORICAL_INFORMATION_PERIOD = "HistoricalInformationPeriod";

	/**
	 * Constant attribute that represents the element 'PointersToOtherTSL'.
	 */
	public static final String ELEMENT_POINTER_TO_OTHER_TSL = "PointersToOtherTSL";

	/**
	 * Constant attribute that represents the element 'PointersToOtherTSL-TSLLocation'.
	 */
	public static final String ELEMENT_POINTER_TO_OTHER_TSL_TSLLOCATION = "PointersToOtherTSL-TSLLocation";

	/**
	 * Constant attribute that represents the element 'ListIssueDateTime'.
	 */
	public static final String ELEMENT_LIST_ISSUE_DATE_TIME = "ListIssueDateTime";

	/**
	 * Constant attribute that represents the element 'NextUpdate'.
	 */
	public static final String ELEMENT_NEXT_UPDATE = "NextUpdate";

	/**
	 * Constant attribute that represents the element 'DistributionPoints'.
	 */
	public static final String ELEMENT_DISTRIBUTION_POINTS = "DistributionPoints";

	/**
	 * Constant attribute that represents the element 'TrustServiceProvider'.
	 */
	public static final String ELEMENT_TRUST_SERVICE_PROVIDER = "TrustServiceProvider";

	/**
	 * Constant attribute that represents the element 'TSPInformation-TSPAddress'.
	 */
	public static final String ELEMENT_TSPINFORMATION_ADDRESS = "TSPInformation-TSPAddress";

	/**
	 * Constant attribute that represents the element 'TSPInformation-TSPInformationURI'.
	 */
	public static final String ELEMENT_TSPINFORMATION_URI = "TSPInformation-TSPInformationURI";

	/**
	 * Constant attribute that represents the element 'TSPInformation-TSPName'.
	 */
	public static final String ELEMENT_TSPINFORMATION_NAME = "TSPInformation-TSPName";

	/**
	 * Constant attribute that represents the element 'TSPInformation-TSPTradeName'.
	 */
	public static final String ELEMENT_TSPINFORMATION_TRADENAME = "TSPInformation-TSPTradeName";

	/**
	 * Constant attribute that represents the element 'TSPInformation-TSPInformationExtensions'.
	 */
	public static final String ELEMENT_TSPINFORMATION_EXTENSIONS = "TSPInformation-TSPInformationExtensions";

	/**
	 * Constant attribute that represents the element 'TSPServices'.
	 */
	public static final String ELEMENT_TSPSERVICE_LIST = "TSPServices";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceTypeIdentifier'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_TYPE = "TSPServiceInformation-ServiceTypeIdentifier";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceName'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_NAMES = "TSPServiceInformation-ServiceName";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceDigitalIdentity'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_SERVICEDIGITALIDENTITY = "TSPServiceInformation-ServiceDigitalIdentity";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-Status'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_STATUS = "TSPServiceInformation-Status";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-StatusStartingTime'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_STATUS_STARTINGTIME = "TSPServiceInformation-StatusStartingTime";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-SchemeServiceDefinitionURI'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_SCHEMESERVICEDEFINITIONURI = "TSPServiceInformation-SchemeServiceDefinitionURI";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceSupplyPoints'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_SERVICESUPPLYPOINTS = "TSPServiceInformation-ServiceSupplyPoints";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-TSPServiceDefinitionURI'.
	 */
	public static final String ELEMENT_TSPSERVICE_INFORMATION_TSPSERVICEDEFINITIONURI = "TSPServiceInformation-TSPServiceDefinitionURI";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceHistoryInstance-ServiceTypeIdentifier'.
	 */
	public static final String ELEMENT_TSPSERVICE_HISTORY_TYPE = "TSPServiceInformation-ServiceHistoryInstance-ServiceTypeIdentifier";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceHistoryInstance-Status'.
	 */
	public static final String ELEMENT_TSPSERVICE_HISTORY_STATUS = "TSPServiceInformation-ServiceHistoryInstance-Status";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceHistoryInstance-ServiceName'.
	 */
	public static final String ELEMENT_TSPSERVICE_HISTORY_NAMES = "TSPServiceInformation-ServiceHistoryInstance-ServiceName";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceHistoryInstance-StatusStartingTime'.
	 */
	public static final String ELEMENT_TSPSERVICE_HISTORY_STATUS_STARTINGTIME = "TSPServiceInformation-ServiceHistoryInstance-StatusStartingTime";

	/**
	 * Constant attribute that represents the element 'TSPServiceInformation-ServiceHistoryInstance-ServiceDigitalIdentity'.
	 */
	public static final String ELEMENT_TSPSERVICE_HISTORY_SERVICEDIGITALIDENTITY = "TSPServiceInformation-ServiceHistoryInstance-ServiceDigitalIdentity";

	/**
	 * Constant attribute that represents the element 'Extension-ExpiredCertsRevocationInfo'.
	 */
	public static final String ELEMENT_EXTENSION_EXPIREDCERTSREVOCATIONINFO = "Extension-ExpiredCertsRevocationInfo";

	/**
	 * Constant attribute that represents the element 'ExpiredCertsRevocationInfo'.
	 */
	public static final String ELEMENT_EXTENSION_EXPIREDCERTSREVOCATIONINFO_LOCALNAME = "ExpiredCertsRevocationInfo";

	/**
	 * Constant attribute that represents the element 'Extension-AdditionalServiceInformation'.
	 */
	public static final String ELEMENT_EXTENSION_ADDITIONALSERVICEINFORMATION = "Extension-AdditionalServiceInformation";

	/**
	 * Constant attribute that represents the element 'AdditionalServiceInformation'.
	 */
	public static final String ELEMENT_EXTENSION_ADDITIONALSERVICEINFORMATION_LOCALNAME = "AdditionalServiceInformation";

	/**
	 * Constant attribute that represents the element 'Extension-AdditionalServiceInformation-URI'.
	 */
	public static final String ELEMENT_EXTENSION_ADDITIONALSERVICEINFORMATION_URI = "Extension-AdditionalServiceInformation-URI";

	/**
	 * Constant attribute that represents the element 'Extension-Qualifications'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATIONS = "Extension-Qualifications";

	/**
	 * Constant attribute that represents the element 'Qualifications'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATIONS_LOCALNAME = "Qualifications";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement'.
	 */
	public static final Object ELEMENT_EXTENSION_QUALIFICATIONS_QUALIFICATION = "Extension-QualificationElement";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-Qualifier'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_QUALIFIER = "Extension-QualificationElement-Qualifier-URI";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-Qualifier'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_QUALIFIER_URI = "Extension-QualificationElement-Qualifier-URI";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-CriteriaList'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST = "Extension-QualificationElement-CriteriaList";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-CriteriaList-Assert'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST_ASSERT = "Extension-QualificationElement-CriteriaList-Assert";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-CriteriaList-KeyUsage'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST_KEYUSAGE = "Extension-QualificationElement-CriteriaList-KeyUsage";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-CriteriaList-KeyUsage-Name'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST_KEYUSAGE_NAME = "Extension-QualificationElement-CriteriaList-KeyUsage-Name";

	/**
	 * Constant attribute that represents the element 'Extension-QualificationElement-CriteriaList-PolicySet'.
	 */
	public static final String ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST_POLICYSET = "Extension-QualificationElement-CriteriaList-PolicySet";

	/**
	 * Constant attribute that represents the element 'TakenOverBy'.
	 */
	public static final String ELEMENT_EXTENSION_TAKENOVERBY_LOCALNAME = "TakenOverBy";

	/**
	 * Constant attribute that represents the element 'Extension-TakenOverBy-URI'.
	 */
	public static final String ELEMENT_EXTENSION_TAKENOVERBY_URI = "Extension-TakenOverBy-URI";

	/**
	 * Constant attribute that represents the element 'Extension-TakenOverBy-TSPName'.
	 */
	public static final String ELEMENT_EXTENSION_TAKENOVERBY_TSPNAME = "Extension-TakenOverBy-TSPName";

	/**
	 * Constant attribute that represents the element 'Extension-TakenOverBy-SchemeOperatorName'.
	 */
	public static final String ELEMENT_EXTENSION_TAKENOVERBY_SCHEMEOPERATORNAME = "Extension-TakenOverBy-SchemeOperatorName";

	/**
	 * Constant attribute that represents the element 'Extension-TakenOverBy-SchemeTerritory'.
	 */
	public static final String ELEMENT_EXTENSION_TAKENOVERBY_SCHEMETERRITORY = "Extension-TakenOverBy-SchemeTerritory";

	/**
	 * Constant attribute that represents the element 'DigitalId'.
	 */
	public static final String ELEMENT_DIGITAL_IDENTITY = "DigitalId";

	/**
	 * Constant attribute that represents the element 'ExtendedKeyUsage'.
	 */
	public static final String ELEMENT_OTHER_CRITERIA_EXTENDEDKEYUSAGE_LOCALNAME = "ExtendedKeyUsage";

	/**
	 * Constant attribute that represents the element 'CertSubjectDNAttribute'.
	 */
	public static final String ELEMENT_OTHER_CRITERIA_CERTSUBJECTDNATTRIBUTE_LOCALNAME = "CertSubjectDNAttribute";

	/**
	 * Constant attribute that represents the element 'Signature'.
	 */
	public static final String ELEMENT_SIGNATURE = "Signature";

}
