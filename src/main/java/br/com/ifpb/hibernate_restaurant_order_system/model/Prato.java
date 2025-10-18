package br.com.ifpb.hibernate_restaurant_order_system.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    public Prato( String nome, String descricao, BigDecimal preco) {

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

}
