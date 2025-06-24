package es.gob.valet.persistence.configuration.model.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.valet.persistence.configuration.model.entity.TslPendVal;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TslPendValRepository extends JpaRepository<TslPendVal, Long> {

}
