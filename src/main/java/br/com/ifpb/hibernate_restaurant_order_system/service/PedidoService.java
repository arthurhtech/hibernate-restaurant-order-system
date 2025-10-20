package br.com.ifpb.hibernate_restaurant_order_system.service;

import br.com.ifpb.hibernate_restaurant_order_system.dto.pedido.PedidoRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.pedido.PedidoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.model.Cliente;
import br.com.ifpb.hibernate_restaurant_order_system.model.Pedido;
import br.com.ifpb.hibernate_restaurant_order_system.model.Prato;
import br.com.ifpb.hibernate_restaurant_order_system.repository.ClienteDAO;
import br.com.ifpb.hibernate_restaurant_order_system.repository.PedidoDAO;
import br.com.ifpb.hibernate_restaurant_order_system.repository.PratoDAO;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PedidoService {

    private final PedidoDAO pedidoDAO;
    private final PratoDAO pratoDAO;
    private final ClienteDAO clienteDAO;

    public PedidoService(EntityManagerFactory emf) {
        this.pedidoDAO = new PedidoDAO(emf);
        this.pratoDAO = new PratoDAO(emf);
        this.clienteDAO = new ClienteDAO(emf);
    }

    public PedidoResponseDTO save(PedidoRequestDTO requestDTO) {
        
        // Buscar o cliente associado ao pedido
        Cliente cliente = clienteDAO.findById(requestDTO.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com ID: " + requestDTO.getClienteId());
        }

        // Buscar os pratos associados ao pedido
        List<Prato> pratos = requestDTO.getPratoIds().stream()
                .map(pratoDAO::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (pratos.size() != requestDTO.getPratoIds().size()) {
            throw new RuntimeException("Um ou mais pratos não foram encontrados.");
        }

        // Calcular o valor total do pedido
        BigDecimal valorTotal = pratos.stream()
                .map(Prato::getPreco)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        novoPedido.setPratos(pratos);
        novoPedido.setData(LocalDate.now());
        novoPedido.setValorTotal(valorTotal);
        
        Pedido pedidoSalvo = pedidoDAO.save(novoPedido);

        // Chama o método conversor privado para retornar a resposta
        return converterParaDTO(pedidoSalvo);
    }

    // Método para listar todos os pedidos
    public List<PedidoResponseDTO> findAll() {
        List<Pedido> pedidos = pedidoDAO.findAll();
        return pedidos.stream()
                      .map(this::converterParaDTO)
                      .collect(Collectors.toList());
    }

    //  Método para buscar um pedido por ID
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoDAO.findById(id);
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado com ID: " + id);
        }
        return converterParaDTO(pedido);
    }

    // Método conversor privado
    private PedidoResponseDTO converterParaDTO(Pedido pedido) {
        if (pedido == null) return null;

        // Converte a lista de pratos da entidade para uma lista de DTOs de prato
        List<PratoResponseDTO> pratosDto = pedido.getPratos().stream()
                .map(prato -> new PratoResponseDTO(
                        prato.getId(),
                        prato.getNome(),
                        prato.getDescricao(),
                        prato.getPreco()))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData().toString(),
                pedido.getValorTotal() == null ? null : pedido.getValorTotal(),
                pedido.getCliente().getId(),
                pratosDto
        );
    }
}