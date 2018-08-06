package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TSLValet;


/**
 * 
 * <p>Interface that provides CRUD functionality for the TslValet entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 26 jun. 2018.
 */
@Repository
public interface TslValetRepository  extends JpaRepository<TSLValet, Long> {
	/**
	  * Method that obtains from the persistence a TSL identified by its primary key. 
	 * @param id String that represents the primary key of the TSL in the persistence.
	 * @return Object that represents a TSL from the persistence. 
	 */
	TSLValet findByIdTslValet(Long id);
}
