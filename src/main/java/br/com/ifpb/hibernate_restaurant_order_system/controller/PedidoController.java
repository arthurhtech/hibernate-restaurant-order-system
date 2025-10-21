package br.com.ifpb.hibernate_restaurant_order_system.controller;

import br.com.ifpb.hibernate_restaurant_order_system.dto.pedido.PedidoRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.pedido.PedidoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.service.PedidoService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Controller que recebe as requisições HTTP para as operações de Pedido.

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    // Injeção de dependência do PedidoService
    public PedidoController(EntityManagerFactory emf) {
        this.pedidoService = new PedidoService(emf);
    }

    // Endpoint para criar um novo pedido.
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@RequestBody PedidoRequestDTO requestDTO) {
        PedidoResponseDTO responseDTO = pedidoService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint para listar todos os pedidos.
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listAll() {
        List<PedidoResponseDTO> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }

    // Endpoint para buscar um pedido por ID.
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        try {
            PedidoResponseDTO responseDTO = pedidoService.findById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            // O service lança uma exceção se não encontrar
            return ResponseEntity.notFound().build();
        }
    }

    
}