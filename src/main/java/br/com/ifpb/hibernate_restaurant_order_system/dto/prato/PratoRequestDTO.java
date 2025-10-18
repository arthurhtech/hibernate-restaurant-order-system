package br.com.ifpb.hibernate_restaurant_order_system.dto.prato;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PratoRequestDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
}
