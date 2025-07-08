package es.gob.valet.persistence.configuration.services.impl;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.model.repository.ValetServerRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValetServerService implements IValetServerService {

    @Autowired
    private ValetServerRepository repository;

    @Override
    public ValetServer save(ValetServer entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ValetServer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ValetServer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}