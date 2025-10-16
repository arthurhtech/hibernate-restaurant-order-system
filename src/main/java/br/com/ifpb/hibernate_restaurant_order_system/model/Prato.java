package br.com.ifpb.hibernate_restaurant_order_system.model;

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
    private double preco;

    public Prato(long id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

}
