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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsIdentifiersGenerator.java.</p>
 * <b>Description:</b><p>Utility class for generate uniques identifiers.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/02/2019.
 */
package es.gob.valet.commons.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * <p>Utility class for generate uniques identifiers.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/02/2019.
 */
public final class UtilsIdentifiersGenerator {

	/**
	 * Attribute that represents the object to format decimal numbers with two digits.
	 */
	private static DecimalFormat formatter2digits = new DecimalFormat("00");

	/**
	 * Attribute that represents the object to format decimal numbers with four digits.
	 */
	private static DecimalFormat formatter4digits = new DecimalFormat("0000");

	/**
	 * Attribute that represents the two digits unique identifier defined for this instance of ValET.
	 */
	private static String valetInstanceUniqueId2Digits = null;
	// Inicializamos su valor
	static {
		valetInstanceUniqueId2Digits = StaticValetConfig.getProperty(StaticValetConfig.AUDIT_TRANSACTION_NUMBER_UNIQUEID);
		int uniqueIdNumber = Integer.parseInt(valetInstanceUniqueId2Digits) % NumberConstants.NUM100;
		valetInstanceUniqueId2Digits = formatter2digits.format(uniqueIdNumber);
	}

	/**
	 * Attribute that represents the counter of numbered sequences generated at this loop.
	 */
	private static int counter = (int) (Math.random() * NumberConstants.NUM5000);

	/**
	 * Attribute that represents the current time millis representation in a string
	 * captured for last time.
	 */
	private static String currentTimeMillis = String.valueOf(Calendar.getInstance().getTimeInMillis());

	/**
	 * Constructor method for the class UtilsIdentifiersGenerator.java.
	 */
	private UtilsIdentifiersGenerator() {
		super();
	}

	/**
	 * Synchronized method that generates a unique identifier builded in characters
	 * and a ending number.
	 * @return String that represents the generated unique identifier.
	 */
	public static synchronized String generateNumbersUniqueId() {

		// Incrementamos el contador.
		counter++;

		// En caso de superar los 4 dígitos, volvemos a generarlo
		// de forma aleatoria, y también el tiempo base.
		if (counter >= NumberConstants.NUM10000) {
			counter = (int) (Math.random() * NumberConstants.NUM5000);
			currentTimeMillis = String.valueOf(Calendar.getInstance().getTimeInMillis());
		}

		// Para ahorrar en uso de cadenas, hacemos uso de StringBuilder.
		// Podemos usar StringBuilder a ser un método sincronizado y no tratarse
		// de una variable compartida entre distintos hilos.
		StringBuilder sb = new StringBuilder();
		sb.append(currentTimeMillis);
		sb.append(valetInstanceUniqueId2Digits);
		sb.append(formatter4digits.format(counter));

		// Pasamos a cadena el resultado.
		String result = sb.toString();

		// Vacíamos el StringBuilder.
		sb.setLength(0);
		sb.trimToSize();

		// Devolvemos el resultado.
		return result;

	}

}
