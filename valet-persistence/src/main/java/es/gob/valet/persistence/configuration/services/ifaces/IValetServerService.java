package es.gob.valet.persistence.configuration.services.ifaces;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;

import java.util.List;
import java.util.Optional;

public interface IValetServerService {

    ValetServer save(ValetServer entity);

    Optional<ValetServer> findById(Long id);

    List<ValetServer> findAll();

    void deleteById(Long id);
}