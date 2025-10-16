package br.com.ifpb.hibernate_restaurant_order_system.dto.prato;

import lombok.Data;

@Data
public class PratoRequestDTO {

    private String nome;
    private String descricao;
    private double preco;
}
