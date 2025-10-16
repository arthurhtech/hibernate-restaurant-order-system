package br.com.ifpb.hibernate_restaurant_order_system.dto.prato;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PratoResponseDTO {

    private long id;
    private String nome;
    private String descricao;
    private double preco;

}
