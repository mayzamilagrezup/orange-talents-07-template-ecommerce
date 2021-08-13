package br.com.zupacademy.mayza.ecommerce.modelo.compra;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.Transacao;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.urlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(RetornaGatewayPagamento gatewayPagamento) {
        Transacao novaTransacao = gatewayPagamento.toTransacao(this);
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public void finalizaCompra() {
        if (this.processadaComSucesso()){
            this.status = Status.FINALIZADA;
        }
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
