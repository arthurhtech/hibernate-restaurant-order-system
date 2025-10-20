package br.com.ifpb.hibernate_restaurant_order_system.controller;

import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.service.PratoService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;

    public PratoController(EntityManagerFactory emf) {
        this.pratoService = new PratoService(emf);
    }

    @PostMapping
    public ResponseEntity<PratoResponseDTO> save(@RequestBody PratoRequestDTO pratoRequestDTO) {
        PratoResponseDTO pratoResponseDTO = pratoService.save(pratoRequestDTO);
        return ResponseEntity.status(201).body(pratoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listAll() {
        return ResponseEntity.ok(pratoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> findById(@PathVariable Long id) {
        // (Segue o padrão do ClienteController: findById retorna DTO ou null)
        PratoResponseDTO pratoResponseDTO = pratoService.findById(id);

        if(pratoResponseDTO != null) {
            return ResponseEntity.ok(pratoResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> update(@PathVariable Long id, @RequestBody PratoRequestDTO pratoRequestDTO) {
        // (Segue o padrão do ClienteController: updateById retorna DTO ou null)
        PratoResponseDTO pratoResponseDTO = pratoService.updateById(id, pratoRequestDTO);

        if(pratoResponseDTO != null) {
            return ResponseEntity.ok(pratoResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> delete(@PathVariable Long id) {
        // (Segue o padrão do ClienteController: deleteById retorna boolean)
        boolean deleted = pratoService.deleteById(id);

        if(deleted) {
            return ResponseEntity.noContent().build(); // HTTP 204
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }
}
