package br.com.ifpb.hibernate_restaurant_order_system.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResponseDTO {

    private long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}
