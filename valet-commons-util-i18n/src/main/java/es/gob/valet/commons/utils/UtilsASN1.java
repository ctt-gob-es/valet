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
 * <b>File:</b><p>es.gob.valet.commons.utils.ASN1Utilities.java.</p>
 * <b>Description:</b><p>Class that contains all utilities methods used in ASN1 Objects.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 06/11/2018.
 */
package es.gob.valet.commons.utils;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.X500Name;

import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;

/**
 * <p>Class that contains all utilities methods used in ASN1 Objects.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 06/11/2018.
 */
public final class UtilsASN1 {

	/**
	 * Constructor method for the class ASN1Utilities.java.
	 */
	private UtilsASN1() {
		super();
	}

	/**
	 * Method that obtains the content of a {@link X500Principal} object on string format.
	 * @param name Parameter that represents the object to be processed.
	 * @return the content of the {@link X500Principal} object on string format.
	 * @throws CommonUtilsException If the method fails.
	 */
	public static String toString(X500Principal name) throws CommonUtilsException {
		try {
			X500Name x500Name = X500Name.getInstance(name.getEncoded());
			String rfcName = (String) x500Name.toString();
			if (rfcName != null) {
				return rfcName;
			} else {
				return name.getName(X500Principal.RFC2253);
			}
		} catch (Exception e) {
			throw new CommonUtilsException(IValetException.COD_058, Language.getFormatResCommonsUtilGeneral(CommonUtilsException.UTILS_ASN1_CODE_001, e));
		}
	}

}
