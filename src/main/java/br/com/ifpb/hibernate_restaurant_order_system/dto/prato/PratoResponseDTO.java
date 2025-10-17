package br.com.ifpb.hibernate_restaurant_order_system.dto.prato;

import br.com.ifpb.hibernate_restaurant_order_system.model.Prato;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PratoResponseDTO {

    private long id;
    private String nome;
    private String descricao;
    private double preco;

    public static PratoResponseDTO fromEntity(Prato prato) {
        if (prato == null) return null;
        return new PratoResponseDTO(
            prato.getId(),
            prato.getNome(),
            prato.getDescricao(),
            prato.getPreco()
        );
    }

}
