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
 * <b>File:</b><p>es.gob.valet.sprint.config.ApplicationConfig.java.</p>
 * <b>Description:</b><p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Project:</b><p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Date:</b><p>12/06/2018.</p>
 * @author Gobierno de España.
 * @version 2.2, 17/01/2024.
 */
package es.gob.valet.spring.config;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.gob.valet.cache.FactoryCacheValet;
import es.gob.valet.cache.exceptions.CacheValetException;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsDeploymentType;
import es.gob.valet.commons.utils.UtilsGrayLog;
import es.gob.valet.commons.utils.UtilsProviders;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.tasks.HiddenTasksManager;
import es.gob.valet.tasks.TasksManager;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.utils.UtilsCache;
import es.gob.valet.utils.UtilsProxy;

/**
 * <p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.11, 27/04/2022.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("es.gob.valet")
@EntityScan("es.gob.valet.persistence.configuration.model.entity")
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "es.gob.valet.persistence.configuration.model.repository")
public class ApplicationConfig {

	/**
	 * Constant attribute that represents the key for the property that determines the
	 * log4j properties file for the Valet WS Rest deployment.
	 */
	private static final String LOG4J_PROPERTIESFILE_VALET_REST = "log4j.configuration.valet.rest";

	/**
	 * Constant attribute that represents the key for the property that determines the
	 * log4j properties file for the Valet Administration Web deployment.
	 */
	private static final String LOG4J_PROPERTIESFILE_VALET_WEB = "log4j.configuration.valet.web";

	/**
	 * Attribute that represents the logger of this class.
	 */
	private static Logger logger = Logger.getLogger(ApplicationConfig.class);

	/**
	 * Attribute that forces the initialization of the manager for the persistence services.
	 */
	@SuppressWarnings("unused")
	@Autowired
	private ManagerPersistenceServices managerPersistenceServices;

	/**
	 * Method that initializes differents elements for this class and for the application boot.
	 */
	@PostConstruct
	public void init() {

		try {
			initializePlatform();
			logger.info(Language.getResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_001));
			// initializeProviders();
		} catch (Exception e) {
			logger.error(Language.getResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_002), e);
		}

	}

	/**
	 * Method that initialize all the functions of the platform.
	 */
	private void initializePlatform() {

		// Se indica que el fichero de configuración de log4j se recargue cada
		// cierto periodo de tiempo (10 segundos).
		// En función del desplegable, cogemos el fichero de configuración
		// que le corresponda.
		String log4jConfFile = null;
		if (UtilsDeploymentType.isDeployedServices()) {
			log4jConfFile = System.getProperty(LOG4J_PROPERTIESFILE_VALET_REST);
		} else {
			log4jConfFile = System.getProperty(LOG4J_PROPERTIESFILE_VALET_WEB);
		}
		DOMConfigurator.configureAndWatch(log4jConfFile, NumberConstants.NUM10000);
		// Despues de iniciar la configuración de log4j, iniciamos el logger.
		logger = Logger.getLogger(ApplicationConfig.class);

		logger.info(Language.getResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_000));

		// Inicializamos la conexión con GrayLog si es necesario.
		UtilsGrayLog.loadGrayLogConfiguration();

		// Se inicializan los providers necesarios.
		UtilsProviders.initializeProviders();

		// Se inicializa la caché.
		try {
			FactoryCacheValet.getCacheValetInstance();
		} catch (CacheValetException e) {
			logger.warn(Language.getResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_003), e);
			// TODO Aquí hay que enviar una alarma por no poder iniciar la
			// caché.
		}

		// Carga inicial de datos en la caché.
		long cacheInitializationTime = Calendar.getInstance().getTimeInMillis();
		ConfigurationCacheFacade.initializeConfigurationCache(false);

		// Cargamos la configuración del proxy.
		long startOperationTime = Calendar.getInstance().getTimeInMillis();
		UtilsProxy.loadProxyConfiguration();
		logger.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_006, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));

		// Cargamos las TSL.
		startOperationTime = Calendar.getInstance().getTimeInMillis();
		TSLManager.getInstance().reloadTSLCache();

		logger.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_004, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));

		// Cargamos el resto de objetos para la caché.
		UtilsCache.loadAllAdditionalCachedObjectList();

		// Mostramos en el log el tiempo empleado en cargar las caché de
		// configuración
		// de forma completa.
		logger.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_005, new Object[ ] { Calendar.getInstance().getTimeInMillis() - cacheInitializationTime }));

		// Inicializamos las tareas ocultas.
		HiddenTasksManager.reloadHiddenTasks();

		// Si se trata de una instancia de web admin...
		if (UtilsDeploymentType.isDeployedWebAdmin()) {

			// Cargamos las tareas de la administración.
			TasksManager.loadTasks();

		}

		// Escribimos en GrayLog el mensaje que indica que la plataforma a
		// inicializado.
		UtilsGrayLog.writeMessageInGrayLog(UtilsGrayLog.LEVEL_ERROR, UtilsGrayLog.TOKEN_VALUE_CODERROR_INITIALIZATION, UtilsGrayLog.getHostName(), Language.getFormatResCoreGeneral(ICoreGeneralMessages.INITIALIZATION_007, UtilsGrayLog.getHostName(), cacheInitializationTime));

	}

	/**
	 * Method that destroy the singleton unique instance of this class.
	 */
	@PreDestroy
	public final void destroy() {
	}

}
