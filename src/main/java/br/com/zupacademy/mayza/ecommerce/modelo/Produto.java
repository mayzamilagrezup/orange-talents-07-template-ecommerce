package br.com.zupacademy.mayza.ecommerce.modelo;

import br.com.zupacademy.mayza.ecommerce.controller.request.CaracteristicaProdutoRequest;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , mappedBy = "produto")
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @ManyToOne
    private Usuario usuarioLogado;

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, Collection<CaracteristicaProdutoRequest> caracteristicas, Usuario usuarioLogado) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream().map(c -> c.toCaracteristicaProduto(this)).collect(Collectors.toSet()));
        this.usuarioLogado = usuarioLogado;
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

}
