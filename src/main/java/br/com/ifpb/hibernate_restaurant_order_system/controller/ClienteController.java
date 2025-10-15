package br.com.ifpb.hibernate_restaurant_order_system.controller;

import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.service.ClienteService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(EntityManagerFactory emf) {
        this.clienteService = new ClienteService(emf);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.save(clienteRequestDTO);
        return ResponseEntity.status(201).body(clienteResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        ClienteResponseDTO clienteResponseDTO = clienteService.findById(id);
        if(clienteResponseDTO !=null) {
            return ResponseEntity.ok(clienteResponseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
