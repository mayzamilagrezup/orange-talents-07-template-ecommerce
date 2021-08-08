package br.com.zupacademy.mayza.ecommerce.modelo.compra;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    private Usuario comprador;

    @ManyToOne
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Deprecated
    public Compra() {
    }

    public Compra(Integer quantidade, Usuario comprador, Produto produto, GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.produto = produto;
        this.gatewayPagamento = gatewayPagamento;
        this.status = status.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonoProduto() {
        return produto.getUsuario();
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.urlRetorno(this, uriComponentsBuilder);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", produto=" + produto +
                ", gatewayPagamento=" + gatewayPagamento +
                '}';
    }
}
