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

}
