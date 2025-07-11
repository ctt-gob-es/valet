package es.gob.valet.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.form.ValetServerForm;
import es.gob.valet.persistence.configuration.model.entity.ValetServer;
import es.gob.valet.persistence.configuration.services.ifaces.IValetServerService;

@RestController
@RequestMapping("/valetServer")
public class ValetServerRestController {
	@Autowired
	private IValetServerService valetServerService;
	
	@PostMapping
	public ResponseEntity<ValetServer> createValetServer(@RequestBody ValetServerForm serverForm) {
		ValetServer entity = serverForm.toEntity();
		entity.setIdValetServer(null);
		ValetServer saved = valetServerService.save(entity);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<ValetServer> findById(@PathVariable Long id) {
        return valetServerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ValetServer>> findAll() {
        return ResponseEntity.ok(valetServerService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	valetServerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<ValetServer> updateValetServer(@PathVariable Long id, @RequestBody ValetServerForm serverForm) {
		if (!valetServerService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ValetServer entity = serverForm.toEntity();
		entity.setIdValetServer(id); // Garantizamos que se actualiza el existente
		ValetServer updated = valetServerService.save(entity);
		return ResponseEntity.ok(updated);
	}
}
