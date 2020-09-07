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
 * <b>File:</b><p>es.gob.valet.i18n.utils.TomcatUtils.java.</p>
 * <b>Description:</b><p> Class with tomcat utilities.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 31/10/2019.
 */
package es.gob.valet.i18n.utils;

import java.io.File;

/** 
 * <p>Class with tomcat utilities.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 31/10/2019.
 */
public final class UtilsTomcat {

	/**
	 * Constant attribute that represents the name of messages directory inside configuration directory.
	 */
	public static final String TOMCAT_CONF_DIR = "tomcat.config.path";
	
	/**
	 * Constant attribute representing the name of the message directory within the configuration directory for VAlet
	 */
	public static final String VALET_CONF_DIR = "valet.config.path";
	

	/**
	 * Constructor method for the class UtilsTomcat.java.
	 */
	private UtilsTomcat() {
	}

	/**
	 * Method that returns the value of the system property jboss.server.config.dir.
	 * @return Value of the system property jboss.server.config.dir. Null if not exist.
	 */
	public static String getTomcatConfigDir() {
		return System.getProperty(TOMCAT_CONF_DIR);
	}

	

	/**
	 * Method that returns the value of the system property jboss.server.config.dir.
	 * @return Value of the system property jboss.server.config.dir. Null if not exist.
	 */
	public static String getValetConfigDir() {
		return System.getProperty(VALET_CONF_DIR);
	}

	/**
	 * Auxiliar method to create an absolute path to a file.
	 * @param pathDir Directory absolute path that contains the file.
	 * @param filename Name of the file.
	 * @return Absolute path of the file.
	 */
	public static String createAbsolutePath(final String pathDir, final String filename) {
		return pathDir + File.separator + filename;
	}

}
