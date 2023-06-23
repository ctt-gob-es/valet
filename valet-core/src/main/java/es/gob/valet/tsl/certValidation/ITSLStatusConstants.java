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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.ifaces.ITSLSI_StatusConstants.java.</p>
 * <b>Description:</b><p> Interface defining all constants related to the outputs of procedures 4.3 and 4.4 inhe ETSI TS 119 615 v.1.1.1.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>27/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 24/07/2022.
 */
package es.gob.valet.tsl.certValidation;


/** 
 * <p>Interface defining all constants related to the outputs of procedures 4.3 and 4.4 inhe ETSI TS 119 615 v.1.1.1.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 24/07/2022.
 */
public interface ITSLStatusConstants {

	/**
	 * Constant attribute that represents the token 'PROCESS_PASSED'.
	 */
	String PROCESS_PASSED = "PROCESS_PASSED";
	/**
	 * Constant attribute that represents the token 'PROCESS_FAILED'.
	 */
	String PROCESS_FAILED = "PROCESS_FAILED";
	/**
	 * Constant attribute that represents the token 'PROCESS_PASSED_WITH_WARNING'.
	 */
	String PROCESS_PASSED_WITH_WARNING ="PROCESS_PASSED_WITH_WARNING";
	/**
	 * Constant attribute that represents the token 'WARNING_T1_DUPLICATION'.
	 */
	String SI_WARNING_T1_DUPLICATION = "WARNING_T1_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'ERROR_T1_DUPLICATION'.
	 */
	String SI_ERROR_T1_DUPLICATION = "ERROR_T1_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'WARNING_T2_DUPLICATION'.
	 */
	String SI_WARNING_T2_DUPLICATION = "WARNING_T2_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'ERROR_T2_DUPLICATION'.
	 */
	String SI_ERROR_T2_DUPLICATION = "ERROR_T2_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'WARNING_T3_DUPLICATION'.
	 */
	String SI_WARNING_T3_DUPLICATION = "WARNING_T3_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'ERROR_T3_DUPLICATION'.
	 */
	String SI_ERROR_T3_DUPLICATION = "ERROR_T3_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'ERROR_TSP_CONFLICT'.
	 */
	String SI_ERROR_TSP_CONFLICT = "ERROR_TSP_CONFLICT";
	/**
	 * Constant attribute that represents the token 'Not_Qualified'.
	 */
	String QC_NOT_QUALIFIED = "Not_Qualified";
	/**
	 * Constant attribute that represents the token 'INDETERMINATE'.
	 */
	String QC_INDETERMINATE = "INDETERMINATE";
	/**
	 * Constant attribute that represents the token 'No_confirmation_found_in_EUMSTL_C'.
	 */
	String QC_SUBSTATUS_ERROR_1 = "No_confirmation_found_in_EUMSTL_CC";	
	/**
	 * Constant attribute that represents the token 'ERROR_TSP_NAME_INCONSISTENCY_BETWEEN_CERT_AND_TL'.
	 */
	String QC_SUBSTATUS_ERROR_2 = "ERROR_TSP_NAME_INCONSISTENCY_BETWEEN_CERT_AND_TL";
	/**
	 * Constant attribute that represents the token 'ERROR_TL-SERVICE-ENTRYSDI_DUPLICATION_STATUS_CONFLICT'.
	 */
	String QC_SUBSTATUS_ERROR_3 = "ERROR_TL-SERVICE-ENTRYSDI_DUPLICATION_STATUS_CONFLICT";
	/**
	 * Constant attribute that represents the token 'ERROR_T1_TL_Inconsistency_in_applying_qualifiers'.
	 */
	String QC_SUBSTATUS_ERROR_4 = "ERROR_T1_TL_Inconsistency_in_applying_qualifiers";
	/**
	 * Constant attribute that represents the token 'WARNING'.
	 */
	String QC_SUBSTATUS_WARNING = "WARNING";
	/**
	 * Constant attribute that represents the token 'WARNING_T1_TL_Inconsistency_in_applying_qualifiers'.
	 */
	String QC_SUBSTATUS_WARNING_1="WARNING_T1_TL_Inconsistency_in_applying_qualifiers";
	/**
	 * Constant attribute that represents the token 'WARNING_CERT_Inconsistency_in_QcType_qualifiers_Non-compliance_with_EN319412-5'.
	 */
	String QC_SUBSTATUS_WARNING_2 ="WARNING_CERT_Inconsistency_in_QcType_qualifiers_Non-compliance_with_EN319412-5";
	/**
	 * Constant attribute that represents the token 'WARNING_T1_Not_Enough_Info_on_QC_Typ'.
	 */
	String QC_SUBSTATUS_WARNING_3= "WARNING_T1_Not_Enough_Info_on_QC_Type";
	/**
	 * Constant attribute that represents the token 'WARNING_T2_TL_Inconsistency_in_applying_qualifiers'.
	 */
	String QC_SUBSTATUS_WARNING_4 = "WARNING_T2_TL_Inconsistency_in_applying_qualifiers";
	/**
	 * Constant attribute that represents the token 'WARNING_T2_Not_Enough_Info_on_QC_Type'.
	 */
	String QC_SUBSTATUS_WARNING_5 = "WARNING_T2_Not_Enough_Info_on_QC_Type";
	/**
	 * Constant attribute that represents the token 'WARNING_T3_TL_Inconsistency_in_applying_qualifiers'.
	 */
	String QC_SUBSTATUS_WARNING_6 = "WARNING_T3_TL_Inconsistency_in_applying_qualifiers";
	/**
	 * Constant attribute that represents the token 'WARNING_T3_Not_Enough_Info_on_QC_Typ'.
	 */
	String QC_SUBSTATUS_WARNING_7 = "WARNING_T3_Not_Enough_Info_on_QC_Type";
	/**
	 * Constant attribute that represents the token 'WARNING_TL-SERVICE-ENTRY-SDI_DUPLICATION'.
	 */
	String QC_SUBSTATUS_WARNING_8 = "WARNING_TL-SERVICE-ENTRY-SDI_DUPLICATION";
	/**
	 * Constant attribute that represents the token 'QC_For_eSig'.
	 */
	String CHECK1_ESIG_QL = "QC_For_eSig";
	/**
	 * Constant attribute that represents the token 'Not_Qualified_For_eSig'.
	 */
	String CHECK1_ESIG_NOT_QL = "Not_Qualified_For_eSig";
	/**
	 * Constant attribute that represents the token 'INDET_QC_For_eSig'.
	 */
	String CHECK1_ESIG_IND_QL = "INDET_QC_For_eSig";
	/**
	 * Constant attribute that represents the token 'QC_For_eSeal'.
	 */
	String CHECK2_ESEAL_QL = "QC_For_eSeal";
	/**
	 * Constant attribute that represents the token 'INDET_QC_For_eSeal'.
	 */
	String CHECK2_ESEAL_NOT_QL = "INDET_QC_For_eSeal";
	/**
	 * Constant attribute that represents the token 'INDET_QC_For_eSeal'.
	 */
	String CHECK2_ESEAL_IND_QL = "INDET_QC_For_eSeal";
	/**
	 * Constant attribute that represents the token 'QWAC'.
	 */
	String CHECK3_QWAC = "QWAC";
	/**
	 * Constant attribute that represents the token 'Not_QWAC'.
	 */
	String CHECK3_NOT_QWAC = "Not_QWAC";
	/**
	 * Constant attribute that represents the token 'CHECK3_IND_QWAC'.
	 */
	String CHECK3_IND_QWAC = "INDET_QWAC";
	/**
	 * Constant attribute that represents the token 'ESIG'.
	 */
	String ESIG = "ESIG";
	/**
	 * Constant attribute that represents the token 'SEAL'.
	 */
	String ESEAL = "SEAL";
	/**
	 * Constant attribute that represents the token 'WSA'.
	 */
	String WSA ="WSA";
	/**
	 * Constant attribute that represents the token 'QSCD_INDETERMINATE'.
	 */
	String QSCD_INDETERMINATE = "QSCD_INDETERMINATE";
	/**
	 * Constant attribute that represents the token 'PROCESS_PASSED_WITH_WARNING'.
	 */
	String QSCD_STATUS_WARNING = "PROCESS_PASSED_WITH_WARNING";
	/**
	 * Constant attribute that represents the token 'WARNING_Inconsistency_in_applying_qualifiers_for_SSCD_status'.
	 */
	String QSCD_SUBSTATUS_WARNING_1 = "WARNING_Inconsistency_in_applying_qualifiers_for_SSCD_status";
	/**
	 * Constant attribute that represents the token 'WARNING_Inconsistency_in_applying_qualifiers_for_QSCD_status'.
	 */
	String QSCD_SUBSTATUS_WARNING_2 = "WARNING_Inconsistency_in_applying_qualifiers_for_QSCD_status";
	/**
	 * Constant attribute that represents the token 'QSCD_YES'.
	 */
	String QSCD_YES = "QSCD_YES";
	/**
	 * Constant attribute that represents the token 'QSCD_NO'.
	 */
	String QSCD_NO = "QSCD_NO";
	

	
}
