package br.com.ifpb.hibernate_restaurant_order_system.dto.pedido;
import lombok.Data;
import java.util.List;

@Data
public class PedidoRequestDTO {

    // Atributos necessários para criar um Pedido
    private Long clienteId;   
    private List<Long> pratoIds;

}
