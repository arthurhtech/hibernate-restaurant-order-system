package br.com.ifpb.hibernate_restaurant_order_system.service;

import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import br.com.ifpb.hibernate_restaurant_order_system.repository.ClienteDAO;
import jakarta.persistence.EntityManagerFactory;

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

}
