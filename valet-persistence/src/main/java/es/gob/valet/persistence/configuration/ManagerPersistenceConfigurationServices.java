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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices.java.</p>
 * <b>Description:</b><p>Manager singleton instance for the use of the persistence services
 * of the configuration scheme.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>11/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 11/09/2018.
 */
package es.gob.valet.persistence.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslValetService;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;

/**
 * <p>Manager singleton instance for the use of the persistence services
 * of the configuration scheme.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 11/09/2018.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ManagerPersistenceConfigurationServices {

	/**
	 * Attribute that represents the unique singleton instance of the class.
	 */
	private static ManagerPersistenceConfigurationServices instance = null;

	/**
	 * Gets the unique singleton instance of the class.
	 * @return the unique singleton instance of the class.
	 */
	public static ManagerPersistenceConfigurationServices getInstance() {
		return instance;
	}

	/**
	 * Method that initializes the singleton unique instance.
	 */
	@PostConstruct
	public void init() {
		instance = this;
	}

	/**
	 * Method that destroy the singleton unique instance of this class.
	 */
	@PreDestroy
	public final void destroy() {
		instance = null;
	}

	/**
	 * Attribute that represents the services for the configuration persistence: TSL implementations/specifications.
	 */
	@Autowired
	private ICTslImplService cTslImplService;

	/**
	 * Attribute that represents the services for the configuration persistence: TSL mapping (country/region).
	 */
	@Autowired
	private ITslCountryRegionMappingService tslCountryRegionMappingService;

	/**
	 * Attribute that represents the services for the configuration persistence: TSL country/region.
	 */
	@Autowired
	private ITslCountryRegionService tslCountryRegionService;

	/**
	 * Attribute that represents the services for the configuration persistence: TSL Services.
	 */
	@Autowired
	private ITslValetService tslValetService;

	/**
	 * Attribute that represents the services for the configuration persistence: ValET Users.
	 */
	@Autowired
	private IUserValetService userValetService;

	/**
	 * Gets the value of the attribute {@link #cTslImplService}.
	 * @return the value of the attribute {@link #cTslImplService}.
	 */
	public final ICTslImplService getcTslImplService() {
		return cTslImplService;
	}

	/**
	 * Gets the value of the attribute {@link #tslCountryRegionMappingService}.
	 * @return the value of the attribute {@link #tslCountryRegionMappingService}.
	 */
	public final ITslCountryRegionMappingService getTslCountryRegionMappingService() {
		return tslCountryRegionMappingService;
	}

	/**
	 * Gets the value of the attribute {@link #tslCountryRegionService}.
	 * @return the value of the attribute {@link #tslCountryRegionService}.
	 */
	public final ITslCountryRegionService getTslCountryRegionService() {
		return tslCountryRegionService;
	}

	/**
	 * Gets the value of the attribute {@link #tslValetService}.
	 * @return the value of the attribute {@link #tslValetService}.
	 */
	public final ITslValetService getTslValetService() {
		return tslValetService;
	}

	/**
	 * Gets the value of the attribute {@link #userValetService}.
	 * @return the value of the attribute {@link #userValetService}.
	 */
	public final IUserValetService getUserValetService() {
		return userValetService;
	}

}
