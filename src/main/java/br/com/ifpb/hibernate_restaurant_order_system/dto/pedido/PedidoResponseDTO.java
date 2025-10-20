package br.com.ifpb.hibernate_restaurant_order_system.dto.pedido;

import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoResponseDTO {

    // Atributos necessários para responder sobre um Pedido
    private Long id;
    private String data;
    private BigDecimal valorTotal;
    private Long clienteId;    
    private List<PratoResponseDTO> pratos;

}
