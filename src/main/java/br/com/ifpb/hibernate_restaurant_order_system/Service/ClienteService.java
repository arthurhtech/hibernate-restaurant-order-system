package br.com.ifpb.hibernate_restaurant_order_system.Service;

import br.com.ifpb.hibernate_restaurant_order_system.DTO.Cliente.ClienteRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import br.com.ifpb.hibernate_restaurant_order_system.Repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public void save(ClienteRequestDTO clienteRDTO) {
        Cliente cliente = new Cliente(clienteRDTO.getNome(),
                                      clienteRDTO.getCpf(),
                                      clienteRDTO.getTelefone(),
                                      clienteRDTO.getEmail()
                                      );
        clienteDAO.save(cliente);
    }

}
