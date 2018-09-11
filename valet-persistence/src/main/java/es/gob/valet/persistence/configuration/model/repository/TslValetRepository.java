package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslValet;


/**
 * 
 * <p>Interface that provides CRUD functionality for the TslValet entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 26 jun. 2018.
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslValetRepository  extends JpaRepository<TslValet, Long> {
	/**
	  * Method that obtains from the persistence a TSL identified by its primary key. 
	 * @param id String that represents the primary key of the TSL in the persistence.
	 * @return Object that represents a TSL from the persistence. 
	 */
	TslValet findByIdTslValet(Long id);
	
	TslValet findByCountry(TslCountryRegion tslContryRegion);
	
	
}
