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
 * @version 1.10, 18/10/2022.
 */
package es.gob.valet.commons.utils;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * <p>Class that provides functionality for managing file.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.10, 18/10/2022.
 */
public final class UtilsFile implements Serializable {
	
	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -3372718493036107648L;
	
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
	public static String getStringSizeLengthFile(long size) {

		DecimalFormat df = new DecimalFormat("##");

		float sizeKb = 1024.0f;
		float sizeMb = sizeKb * sizeKb;
		float sizeGb = sizeMb * sizeKb;
		float sizeTerra = sizeGb * sizeKb;

		if (size < sizeMb)
			return df.format(size / sizeKb) + " Kb";
		else if (size < sizeGb)
			return df.format(size / sizeMb) + " Mb";
		else if (size < sizeTerra)
			return df.format(size / sizeGb) + " Gb";

		return "";
	}
}
