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
 * <b>File:</b><p>es.gob.valet.commons.utils.UtilsFile.java.</p>
 * <b>Description:</b><p>Class that provides functionality for managing file.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.9, 17/10/2022.
 */
package es.gob.valet.commons.utils;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * <p>Class that provides functionality for managing file.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.9, 17/10/2022.
 */
public final class UtilsFile implements Serializable {
	
	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -3372718493036107648L;
	
	/**
	 * Constant attribute that represents 1 kilobyte.
	 */
	private static final long K = 1024;
	
	/**
	 * Constant attribute that represents 1 Megabyte.
	 */
	private static final long M = K * K;
	
	/**
	 * Constant attribute that represents 1 Gigabyte.
	 */
	private static final long G = M * K;
	
	/**
	 * Constant attribute that represents 1 Terabyte.
	 */
	private static final long T = G * K;
	
	/**
	 * Constructor method for the class UtilsFile.java. 
	 */
	private UtilsFile() {
	}

	/**
	 * Method that calculate size of file.
	 * 
	 * @param size contain size of file.
	 * @return size file.
	 */
	public static String getStringSizeLengthFile(long value) {
		final long[] dividers = new long[] { T, G, M, K, 1 };
	    final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	    if(value < 1)
	        throw new IllegalArgumentException("Invalid file size: " + value);
	    String result = null;
	    for(int i = 0; i < dividers.length; i++){
	        final long divider = dividers[i];
	        if(value >= divider){
	            result = format(value, divider, units[i]);
	            break;
	        }
	    }
	    return result;
	}
	
	/**
	 * Method that format number of bytes in String with two Integer and one decimal.
	 *  
	 * @param value parameter that represent value to evaluate.
	 * @param divider parameter that represent the divisor. This can be TB, GB, MB and KB.
	 * @param unit parameter that represent the unit to represent. This can be TB, GB, MB, KB and B.
	 * @return one String with value definitive transform.
	 */
	private static String format(final long value,
		    final long divider,
		    final String unit){
		    final double result =
		        divider > 1 ? (double) value / (double) divider : (double) value;
		    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
		}
}
