package br.com.ifpb.hibernate_restaurant_order_system.DTO.Cliente;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

}
