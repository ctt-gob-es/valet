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
 * <b>File:</b><p>es.gob.valet.statistics.ValetStatistics.java.</p>
 * <b>Description:</b><p>Main class of the module ValET Standalone Statistics. .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.1, 01/09/2021.
 */
package es.gob.valet.statistics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.statistics.i18n.Language;
import es.gob.valet.statistics.i18n.StandaloneStatisticsLogConstants;
import es.gob.valet.statistics.tools.FileLogFilter;

/** 
 * <p>Main class of the module ValET Standalone Statistics.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 01/09/2021.
 */
public final class ValetStandaloneStatistics {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ValetStandaloneStatistics.class);

	/**
	 * Attribute that represents the property key used to indicate the event file date pattern.
	 */
	private static final String DATE_PATTERN_PROPERTY_KEY = "valet.lanzador.datePattern";

	/**
	 * Attribute that represents the property key used to indicate the log folder.
	 */
	private static final String LOG_FOLDER_PROPERTY_KEY = "valet.lanzador.logsFolder";
	/**
	 * Attribute that represents the property key used to indicate the event file base name.
	 */
	private static final String BASE_NAME_PROPERTY_KEY = "valet.lanzador.baseName";

	/**
	 * Attribute that represents the path to launcher file property.
	 */
	private static final String LAUNCHER_PROPERTY_PATH = "/configuration/lanzador.properties";

	/**
	 * Attribute that represents the path to the file to read.
	 */
	private static String pathLogFileToRead = null;

	/**
	 * Attribute that represents the launcher properties. 
	 */
	private static Properties launcherProp = new Properties();

	/**
	 * Constructor method for the class ValetStatistics.java. 
	 */
	private ValetStandaloneStatistics() {
		super();
	}

	/**
	 * Attribute that represents the property start date.
	 */
	private static Date dateStart;

	/**
	 *  Method that extracts information from the log file to obtain Valet statistics.
	 * @param args it should contain the path of the file to be processed.
	 * @throws ValetStatisticsException when an error has occurred.
	 */
	public static void main(String[ ] args) throws ValetStatisticsException {
		dateStart = new Date();
		loadProperties();

		System.getProperties();

		LOGGER.info(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG001, new Object[ ] { dateStart }));
		Date dateEnd;

		if (args.length>0 && args[0] != null) {
			pathLogFileToRead = args[0];
		}
		// In case there are not input parameter -> takes default value
		if (pathLogFileToRead == null) {
			setPathToDefaultFileToRead();
		}

		LOGGER.info(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG002, new Object[ ] { pathLogFileToRead }));
		EventFileReaderPentaho reader = new EventFileReaderPentaho(pathLogFileToRead);

		reader.readAndSave();
		dateEnd = new Date();

		// se calcula el tiempo de ejecución del proceso para mostrarlo en el
		// log.
		Double timeTotal = (dateEnd.getTime() - dateStart.getTime()) / new Double(NumberConstants.NUM1000);
		LOGGER.info(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG003, new Object[ ] { dateEnd }));
		LOGGER.info(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG004, new Object[ ] { timeTotal }));
	}

	/**
	 * Sets the path to the file to read to the default value. The default value is a log file which name contains yesterday's date in format yyyy-mm-dd.
	 * @throws ValetStatisticsException when log file has not been found. 
	 */
	private static void setPathToDefaultFileToRead() throws ValetStatisticsException {
		
		if (launcherProp != null) {
			String folderPath = launcherProp.getProperty(LOG_FOLDER_PROPERTY_KEY);
			
			LOGGER.debug(LOG_FOLDER_PROPERTY_KEY + ':' + folderPath);
			if (folderPath != null) {
				File folder = new File(folderPath);
				if (folder.isDirectory()) {
					String baseName = launcherProp.getProperty(BASE_NAME_PROPERTY_KEY);
					LOGGER.debug(BASE_NAME_PROPERTY_KEY + ':' + baseName);
					String datePattern = launcherProp.getProperty(DATE_PATTERN_PROPERTY_KEY);
					LOGGER.debug(DATE_PATTERN_PROPERTY_KEY + ':' + datePattern);
					LocalDate yesterday = LocalDate.now().minusDays(1);
					String formattedDay = yesterday.format(DateTimeFormatter.ofPattern(datePattern));

					FileLogFilter logfilter = new FileLogFilter(baseName, formattedDay);
					File[ ] files = folder.listFiles(logfilter);
					if (files != null && files.length == 1) {
						pathLogFileToRead = files[0].getAbsolutePath();
					}
					

					if (pathLogFileToRead == null) {
						LOGGER.error(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG006, new Object[ ] { formattedDay }));
						throw new ValetStatisticsException(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG006, new Object[ ] { formattedDay }));
					}
				} else {
					LOGGER.error(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG009, new Object[ ] { LOG_FOLDER_PROPERTY_KEY }));
				}
			}
		}
		
		
		
		
	}

	/**
	 * Loads properties from launcher file.
	 */
	private static void loadProperties() {
		InputStream in = ValetStandaloneStatistics.class.getResourceAsStream(LAUNCHER_PROPERTY_PATH);
		try {
			if (in != null) {
				launcherProp.load(in);
			}
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG008, new Object[ ] { LAUNCHER_PROPERTY_PATH }), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.VSS_LOG008, new Object[ ] { LAUNCHER_PROPERTY_PATH }), e);
				}
			}
		}
	}

}
