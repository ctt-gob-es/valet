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
 * <b>File:</b><p>es.gob.valet.statistics.tools.FileLogFilter.java.</p>
 * <b>Description:</b><p> Class to find the event file.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/11/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 04/11/2019.
 */
package es.gob.valet.statistics.tools;

import java.io.File;
import java.io.FilenameFilter;

/** 
 * <p>Class to find the event file.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 04/11/2019.
 */
public class FileLogFilter implements FilenameFilter {

	/**
	* Attribute that represents the base name of the file. 
	*/
	private String baseName;

	/**
	* Attribute that represents the creation time of the file. 
	*/
	private String creationTime;

	/**
	 * Constructor method for the class Bootstrap.java.
	 * @param name Base name of the file.
	 * @param date Creation time of the file
	 */
	public FileLogFilter(String name, String date) {
		this.baseName = name;
		this.creationTime = date;
	}

	/**
	 * {@inheritDoc}
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File directory, String filename) {
		  return filename.endsWith(baseName+creationTime); 
	}

}
