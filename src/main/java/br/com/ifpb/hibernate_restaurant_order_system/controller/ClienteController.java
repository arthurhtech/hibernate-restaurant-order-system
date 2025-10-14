package br.com.ifpb.hibernate_restaurant_order_system.controller;


import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@RequestBody ClienteRequestDTO clienteRequestDTO) {

        ClienteResponseDTO clienteResponseDTO = clienteService.save(clienteRequestDTO);

        return ResponseEntity.status(201).body(clienteResponseDTO);
    }

}
