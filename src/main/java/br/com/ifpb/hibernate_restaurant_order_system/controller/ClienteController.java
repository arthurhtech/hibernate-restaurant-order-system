package br.com.ifpb.hibernate_restaurant_order_system.controller;

import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.service.ClienteService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    // Construtor para injetar o EntityManagerFactory (usado pelo Service)
    public ClienteController(EntityManagerFactory emf) {
        this.clienteService = new ClienteService(emf);
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.save(clienteRequestDTO);
        return ResponseEntity.status(201).body(clienteResponseDTO);
    }

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    // Endpoint para buscar um cliente específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        ClienteResponseDTO clienteResponseDTO = clienteService.findById(id);
        if(clienteResponseDTO !=null) {
            return ResponseEntity.ok(clienteResponseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar um cliente existente pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRDTO) {

        ClienteResponseDTO clienteResponseDTO = clienteService.updateById(id, clienteRDTO);

        if(clienteResponseDTO !=null) {
            return ResponseEntity.ok(clienteResponseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um cliente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = clienteService.deleteById(id);
        if(deleted) return ResponseEntity.noContent().build();
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
