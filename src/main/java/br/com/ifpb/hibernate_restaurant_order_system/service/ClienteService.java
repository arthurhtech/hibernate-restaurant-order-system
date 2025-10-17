package br.com.ifpb.hibernate_restaurant_order_system.service;

import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.cliente.ClienteResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.model.Cliente;
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

        return converterCliente(clienteSalvo);
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

    public ClienteResponseDTO findById(Long id) {

        Cliente cliente = clienteDAO.findById(id);

        if (cliente != null){
            return converterCliente(cliente);
        }
        return null;

    }

    public ClienteResponseDTO updateById(Long id, ClienteRequestDTO clienteRDTO) {
        Cliente cliente = clienteDAO.findById(id);

        if (cliente == null){
            return null;
        }

        cliente.setNome(clienteRDTO.getNome());
        cliente.setCpf(clienteRDTO.getCpf());
        cliente.setTelefone(clienteRDTO.getTelefone());
        cliente.setEmail(clienteRDTO.getEmail());

        Cliente clienteAtualizado = clienteDAO.update(cliente);

        return converterCliente(clienteAtualizado);
    }

    public boolean deleteById(Long id) {
        Cliente cliente = clienteDAO.findById(id);
        if (cliente == null){
            return false;
        }
        clienteDAO.delete(id);
        return true;
    }

}

//
