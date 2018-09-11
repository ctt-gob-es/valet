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
 * <b>Project:</b><p></p>
 * <b>Date:</b><p>12/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 11/09/2018.
 */
package es.gob.valet.spring.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.gob.valet.persistence.ManagerPersistenceServices;

/**
 * <p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 11/09/2018.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("es.gob.valet")
@EntityScan("es.gob.valet.persistence.configuration.model.entity")
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "es.gob.valet.persistence.configuration.model.repository" )
public class ApplicationConfig {

	/**
	 * Attribute that represents the logger of this class.
	 */
	private Logger logger = Logger.getLogger(ApplicationConfig.class);

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

		// TODO
		// Añadir los elementos que haga falta inicializar al realizar el boot de la aplicación.

	}

	/**
	 * Method that destroy the singleton unique instance of this class.
	 */
	@PreDestroy
	public final void destroy() {
		managerPersistenceServices = null;
	}

}
