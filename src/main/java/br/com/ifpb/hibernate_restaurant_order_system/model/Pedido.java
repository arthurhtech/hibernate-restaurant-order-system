package br.com.ifpb.hibernate_restaurant_order_system.model;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    // Um cliente pode ter vários pedidos
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false) // Especifica a chave FK na tabela pedidos
    private Cliente cliente;

    //Um pedido pode ter vários pratos e um prato pode estar em vários pedidos
    @ManyToMany
    @JoinTable(
            name = "pedido_prato",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id"))
    //Criação de uma tabela associativa
    private List<Prato> pratos = new ArrayList<>();

    public Pedido(LocalDate data, BigDecimal valorTotal) {
        this.data = data;
        this.valorTotal = valorTotal;
    }
}
