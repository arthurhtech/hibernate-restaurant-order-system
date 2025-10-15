package br.com.ifpb.hibernate_restaurant_order_system.service;

import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import br.com.ifpb.hibernate_restaurant_order_system.repository.ClienteDAO;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService(EntityManagerFactory emf) {
        this.clienteDAO = new ClienteDAO(emf);
    }

    public ClienteResponseDTO save(ClienteRequestDTO clienteRDTO) {
        Cliente cliente = new Cliente(clienteRDTO.getNome(),
                                      clienteRDTO.getCpf(),
                                      clienteRDTO.getTelefone(),
                                      clienteRDTO.getEmail()
                                      );

        Cliente clienteSalvo = clienteDAO.save(cliente);

        return new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getCpf(),
                clienteSalvo.getTelefone(),
                clienteSalvo.getEmail()
        );
    }

    public List<ClienteResponseDTO> findAll() {
        List<Cliente> clientes = clienteDAO.findAll();

        return clientes.stream()
                .map(this::converterCliente)
                .toList();
    }

    private ClienteResponseDTO converterCliente(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail());
    }
}

//
