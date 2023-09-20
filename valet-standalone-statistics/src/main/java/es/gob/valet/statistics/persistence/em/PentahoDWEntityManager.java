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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.em.PentahoDWEntityManager.java.</p>
 * <b>Description:</b><p>Class that implements the method that are used to interact with the persistence context of the pentaho database schema.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.3, 19/09/2023.
 */
package es.gob.valet.statistics.persistence.em;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import es.gob.valet.statistics.i18n.Language;
import es.gob.valet.statistics.i18n.StandaloneStatisticsLogConstants;

/** 
 * <p>Class that implements the method that are used to interact with the persistence context of the pentaho database schema.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 19/09/2023.
 */
public final class PentahoDWEntityManager implements Serializable {
	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = -4457890718768633009L;

	/**
	 * Constant attribute that represents the persistence unit name to connect with this entity manager. 
	 */
	private static final String PERSISTENCE_UNIT_NAME = "valet-standalone-statistics-persistence";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(PentahoDWEntityManager.class);

	/**
	 * Attribute that represents a class instance.
	 */
	private static PentahoDWEntityManager instance = null;

	/**
	 * Attribute that allows to interact with the persistence context associated to the transactional module.
	 */
	private transient EntityManager em = null;

	/**
	 * Attribute that allows to obtain an application-managed entity manager.
	 */
	private transient EntityManagerFactory emf = null;

	/**
	 * Method that obtains an instance of the class.
	 * @return the unique instance of the class.
	 * @throws EmergencyDDBBException If the method fails.
	 */
	public static PentahoDWEntityManager getInstance() {
		if (instance == null) {
			instance = new PentahoDWEntityManager();
		}
		return instance;
	}

	/**
	 * Constructor method for the class StandaloneEmergencyDDBBEntityManager.java.
	 * @throws EmergencyDDBBException If the method fails.
	 */
	private PentahoDWEntityManager() {
		createEntityManagerFactory();
		createEntityManager();
	
	}

	/**
	 * Method that creates the entity manager factory for the persistence database schema.
	 * @throws EmergencyDDBBException If the method fails.
	 */
	private void createEntityManagerFactory() {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			
		} catch (Exception e) {
			String msgError = Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.STAND_STATISTICS_LOG001, new Object[] {e.getCause(), e.getMessage()});
			LOGGER.error(msgError);
			e.printStackTrace();

		}
	}

	/**
	 * Method that creates the entity manager for the persistence database schema.
	 * @throws EmergencyDDBBException If the method fails.
	 */
	private void createEntityManager() {
		try {
			em = emf.createEntityManager();
			

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResStandaloneStatisticsGeneral(StandaloneStatisticsLogConstants.STAND_STATISTICS_LOG002, new Object[] {e.getCause(), e.getMessage()}));
			e.printStackTrace();
		}
	}

	/**
	 * Method that obtains the entity manager for the persistence database schema.
	 * @return the entity manager for the persistence database schema.
	 * @throws EmergencyDDBBException 
	 */
	public EntityManager getEntityManager() {
		if (!em.isOpen()) {
			if (!emf.isOpen()) {
				createEntityManagerFactory();
			}
			createEntityManager();
		}
		return em;
	}


	/**
	 * Method that creates an instance of Query for executing a named query (in the Java Persistence query language or in native SQL)
	 * and returns a unique result with the obtained entity.
	 * @param name Parameter that represents the name of a query defined in meta-data.
	 * @param parameters Map with all the parameters used for executing the named query.
	 * @return unique result with the detached entity.
	 */
	public Object namedQuerySingleResult(String name, Map<String, Object> parameters) {
		List<Object> listResult = namedQuery(name, parameters);
		if (listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}
	
	/**
	 * Method that creates an instance of Query for executing a named query (in the Java Persistence query language or in native SQL)
	 * and returns a list with the obtained entities.
	 * @param name Parameter that represents the name of a query defined in meta-data.
	 * @param parameters Map with all the parameters used for executing the named query.
	 * @return a list with the detached entities.
	 */
	@SuppressWarnings("unchecked")
	public List<Object> namedQuery(String name, Map<String, Object> parameters) {
		Query query = em.createNamedQuery(name);
		if (parameters != null) {
			Iterator<String> it = parameters.keySet().iterator();
			String elem;
			while (it.hasNext()) {
				elem = it.next();
				query.setParameter(elem, parameters.get(elem));
			}
		}
		return query.getResultList();
	}
	/**
	 * Method that executes an update or delete statement by a named query.
	 * @param namedQuery Parameter that represents the name of a query defined in metadata.
	 * @param parameters Map with all the parameters used for executing the named query.
	 */
	public void executeNamedQuery(String namedQuery, Map<String, Object> parameters) {

		Query query = em.createNamedQuery(namedQuery);
		if (parameters != null) {
			Iterator<String> it = parameters.keySet().iterator();
			String elem;
			while (it.hasNext()) {
				elem = it.next();
				query.setParameter(elem, parameters.get(elem));
			}
		}
		query.executeUpdate();
	}
	
	/**
	 * Method that obtains an object by primary key. Search for an entity of the specified class and primary key.
	 * If the entity instance is contained in the persistence context, it is returned from there.
	 * @param clazz Parameter that represents the entity class.
	 * @param id Parameter that represents the primary key.
	 * @return the found entity instance or null if the entity does not exist.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because JBoss Seam needs not final access method.
	public Object load(Class<?> clazz, Long id) {
		// CHECKSTYLE:ON
		return (Object) em.find(clazz, id);
	}


	/**
	 * Method that makes an instance managed and persistent.
	 * @param obj Parameter that represents the entity instance.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because JBoss Seam needs not final access method.
	public void persist(Object obj) {
		// CHECKSTYLE:ON
		em.persist(obj);
	}
	/**
	 * Removes the entity instance.
	 * @param entity Parameter that represents the entity instance.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because JBoss Seam needs not final access method.
	public void remove(Object entity) {
		// CHECKSTYLE:ON
		em.remove(entity);
	}
	
	/**
	 * Method to close the connections.
	 */
	public void close() {
		if (em != null && em.isOpen()) {
			em.close();
			em = null;
		}
		if (emf != null && emf.isOpen()) {
			emf.close();
			emf = null;
		}
	}

}
