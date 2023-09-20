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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsMappings.java.</p>
 * <b>Description:</b><p>Class containing methods to perform operations on the values of the mappings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>01/03/2023.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.commons.utils;

import java.io.Serializable;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;

/** 
 * <p>Class containing methods to perform operations on the values of the mappings.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public final class UtilsMappings implements Serializable {

	/**
		 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 8090305400064434549L;
	
	/**
	 * Method that obtains the string corresponding to the selected value in a Simple type mapping.
	 * @param infoCertCode. Selected value.
	 * @return 
	 */
	public static String getValueMapping(String infoCertCode) {
		Integer code = Integer.valueOf(infoCertCode);

		String result = null;

		switch (code) {
			case CertificateConstants.INFOCERT_CERT_VERSION:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_CERTVERSION);
				break;
			case CertificateConstants.INFOCERT_SUBJECT:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SUBJECT);
				break;
			case CertificateConstants.INFOCERT_ISSUER:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_ISSUER);
				break;
			case CertificateConstants.INFOCERT_SERIAL_NUMBER:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SERIALNUMBER);
				break;
			case CertificateConstants.INFOCERT_SIGALG_NAME:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SIGALGNAME);
				break;
			case CertificateConstants.INFOCERT_SIGALG_OID:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_SIGALGOID);
				break;
			case CertificateConstants.INFOCERT_VALID_FROM:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_VALIDFROM);
				break;
			case CertificateConstants.INFOCERT_VALID_TO:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_VALIDTO);
				break;
			case CertificateConstants.INFOCERT_CERTPOL_INFO_OIDS:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CERTPOLINFOOIDS);
				break;
			case CertificateConstants.INFOCERT_QC_STATEMENTS_OIDS:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATOIDS);
				break;
			case CertificateConstants.INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATEUTYPEOIDS);
				break;
			case CertificateConstants.INFOCERT_SUBJECT_ALT_NAME:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_SUBJECTALTNAME);
				break;
			case CertificateConstants.INFOCERT_IS_CA:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_BASICCONSTRAINTISCA);
				break;
			case CertificateConstants.INFOCERT_KEY_USAGE:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_KEYUSAGE);
				break;
			case CertificateConstants.INFOCERT_CRL_DISTRIBUTION_POINTS:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CRLDISTPOINT);
				break;
			case CertificateConstants.INFOCERT_AUTHORITY_INFORMATION_ACCESS:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_EXTENSION_AIA);
				break;
			case CertificateConstants.INFOCERT_SURNAME:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SURNAME);
				break;
			case CertificateConstants.INFOCERT_COMMON_NAME:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COMMON_NAME);
				break;
			case CertificateConstants.INFOCERT_GIVEN_NAME:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_GIVEN_NAME);
				break;
			case CertificateConstants.INFOCERT_COUNTRY:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COUNTRY);
				break;
			case CertificateConstants.INFOCERT_PSEUDONYM:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_PSEUDONYM);
				break;
			case CertificateConstants.INFOCERT_SUBJECT_SERIAL_NUMBER:
				result = Language.getResWebGeneral(WebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SERIALNUMBER);
				break;
			default:
				break;
		}

		return result;

	}
}
