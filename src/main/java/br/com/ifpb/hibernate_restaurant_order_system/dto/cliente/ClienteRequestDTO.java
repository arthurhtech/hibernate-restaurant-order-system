package br.com.ifpb.hibernate_restaurant_order_system.dto.cliente;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}
