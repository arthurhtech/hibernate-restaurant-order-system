package br.com.ifpb.hibernate_restaurant_order_system.dto.pedido;

import java.util.stream.Collectors;

import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoResponseDTO {

    // Atributos necessários para responder sobre um Pedido
    private Long id;
    private String data;
    private Double valorTotal;
    private Long clienteId;    
    private List<PratoResponseDTO> pratos;

    public static PedidoResponseDTO fromEntity(Pedido pedido) {
        if (pedido == null) return null;
        List<PratoResponseDTO> pratosDto = pedido.getPratos() == null ? List.of()
            : pedido.getPratos().stream()
                .map(PratoResponseDTO::fromEntity)
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getData() == null ? null : pedido.getData().toString(),
            pedido.getValorTotal() == null ? null : pedido.getValorTotal().doubleValue(),
            pedido.getCliente() == null ? null : pedido.getCliente().getId(),
            pratosDto
        );
    }

}
