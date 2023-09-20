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
 * <b>File:</b><p>es.gob.valet.persistence.utils.ConstantsUtils.java.</p>
 * <b>Description:</b><p>Class to used methods common in the use class ConstantsDTO.java .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.persistence.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.ConstantsDTO;

/**
 * <p>Class that representation tree in interfaces with boostrap treeview.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class ConstantsUtils implements Serializable {
	
	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = -6582137056529325949L;
	
	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Certificate Version.
	 */
	public static final int INFOCERT_CERT_VERSION = 0;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Subject.
	 */
	public static final int INFOCERT_SUBJECT = 1;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Issuer.
	 */
	public static final int INFOCERT_ISSUER = 2;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Serial Number.
	 */
	public static final int INFOCERT_SERIAL_NUMBER = NumberConstants.NUM3;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Signature Algorithm Name.
	 */
	public static final int INFOCERT_SIGALG_NAME = NumberConstants.NUM4;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Signauture Algorithm OID.
	 */
	public static final int INFOCERT_SIGALG_OID = NumberConstants.NUM5;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Valid From.
	 */
	public static final int INFOCERT_VALID_FROM = NumberConstants.NUM6;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Valid To.
	 */
	public static final int INFOCERT_VALID_TO = NumberConstants.NUM7;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Certificate Policies Information OIDs.
	 */
	public static final int INFOCERT_CERTPOL_INFO_OIDS = NumberConstants.NUM8;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Qc Statements OIDs.
	 */
	public static final int INFOCERT_QC_STATEMENTS_OIDS = NumberConstants.NUM9;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Qc Statements ExtEu Type OIDs.
	 */
	public static final int INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS = NumberConstants.NUM10;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Subject Alternative Name.
	 */
	public static final int INFOCERT_SUBJECT_ALT_NAME = NumberConstants.NUM11;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Basic Constraints - Is CA.
	 */
	public static final int INFOCERT_IS_CA = NumberConstants.NUM12;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Key Usage.
	 */
	public static final int INFOCERT_KEY_USAGE = NumberConstants.NUM13;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: CRL Distribution Points.
	 */
	public static final int INFOCERT_CRL_DISTRIBUTION_POINTS = NumberConstants.NUM14;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Authority Information Access.
	 */
	public static final int INFOCERT_AUTHORITY_INFORMATION_ACCESS = NumberConstants.NUM15;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Surname.
	 */
	public static final int INFOCERT_SURNAME = NumberConstants.NUM16;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Common Name.
	 */
	public static final int INFOCERT_COMMON_NAME = NumberConstants.NUM17;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Given Name.
	 */
	public static final int INFOCERT_GIVEN_NAME = NumberConstants.NUM18;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Country.
	 */
	public static final int INFOCERT_COUNTRY = NumberConstants.NUM19;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Pseudonym.
	 */
	public static final int INFOCERT_PSEUDONYM = NumberConstants.NUM20;

	/**
	 * Constant attribute that represents the identifier to obtain the certificate info: Serial Number
	 */
	public static final int INFOCERT_SUBJECT_SERIAL_NUMBER = NumberConstants.NUM21;

	/**
	 * Method that gets string constant from multilanguage file.
	 *
	 * @param key Key for getting constant string from multilanguage file.
	 * @return Constants string.
	 */
	public static String getConstantsValue(String key) {
		return Language.getResPersistenceConstants(key);
	}
	
	/**
	 * Method that loads the options for a simple association.
	 * @return List of constants that represents the options for a simple association.
	 */
	public static List<ConstantsDTO> loadSimpleAssociationValues() {

		List<ConstantsDTO> result = new ArrayList<ConstantsDTO>();

		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_CERT_VERSION).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_CERTVERSION)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SUBJECT).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SUBJECT)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_ISSUER).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_ISSUER)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_COMMON_NAME).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COMMON_NAME)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_GIVEN_NAME).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_GIVEN_NAME)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SURNAME).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SURNAME)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_COUNTRY).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COUNTRY)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SUBJECT_SERIAL_NUMBER).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SERIALNUMBER)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_PSEUDONYM).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_PSEUDONYM)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SERIAL_NUMBER).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SERIALNUMBER)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SIGALG_NAME).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SIGALGNAME)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SIGALG_OID).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SIGALGOID)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_VALID_FROM).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_VALIDFROM)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_VALID_TO).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_VALIDTO)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_CERTPOL_INFO_OIDS).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CERTPOLINFOOIDS)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_QC_STATEMENTS_OIDS).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATOIDS)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATEUTYPEOIDS)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_SUBJECT_ALT_NAME).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_SUBJECTALTNAME)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_IS_CA).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_BASICCONSTRAINTISCA)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_KEY_USAGE).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_KEYUSAGE)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_CRL_DISTRIBUTION_POINTS).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CRLDISTPOINT)));
		result.add(new ConstantsDTO(Integer.valueOf(INFOCERT_AUTHORITY_INFORMATION_ACCESS).longValue(), Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_AIA)));
		
		return result;
	}
}
