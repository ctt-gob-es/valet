package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;

@Repository
public interface ValetServerRepository extends JpaRepository<ValetServer, Long> {
    
}