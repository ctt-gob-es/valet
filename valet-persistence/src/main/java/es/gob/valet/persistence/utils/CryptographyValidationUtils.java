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
 * <b>File:</b><p>es.gob.valet.crypto.utils.CryptographyValidationUtils.java.</p>
 * <b>Description:</b><p>Class with utilities for the validation of objects for cryptography module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.persistence.utils;

import java.util.List;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.persistence.exceptions.CryptographyException;

/**
 * <p>Class with utilities for the validation of objects for cryptography module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public final class CryptographyValidationUtils {

	/**
	 * Constructor method for the class CryptographyValidationUtils.java.
	 */
	private CryptographyValidationUtils() {
		super();
	}

	/**
	 * Method that checks if an object is null or not.
	 * @param o Parameter that represents the object to check.
	 * @param msg Parameter that represents the log message if the object is null.
	 * @throws CryptographyException If the object is null.
	 */
	public static void checkIsNotNull(Object o, String msg) throws CryptographyException {
		if (o == null) {
			throw new CryptographyException(ValetExceptionConstants.COD_190, msg);
		}
	}

	/**
	 * Method that checks if a list is null or empty, or not.
	 * @param list Parameter that represents the list to check.
	 * @param msg Parameter that represents the log message if the list is null or empty.
	 * @throws CryptographyException If the list is null or empty.
	 */
	public static void checkIsNotNullAndNotEmpty(List<?> list, String msg) throws CryptographyException {
		if (list == null || list.size() == 0) {
			throw new CryptographyException(ValetExceptionConstants.COD_190, msg);
		}
	}

}
