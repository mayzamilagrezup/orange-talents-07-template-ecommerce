package br.com.zupacademy.mayza.ecommerce.controller.response;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Opiniao;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class DetalheProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Set<DetalheCaracteristicaProdutoResponse> caracteristicas;
    private Set<String> linkImagem;
    private Set<String> perguntas;
    private Set<DetalheOpiniaoProdutoResponse> opinioes;
    private Double mediaNotas;
    private Integer quantidadeNotas;

    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristica(DetalheCaracteristicaProdutoResponse::new);
        this.linkImagem = produto.mapeiaImagem(i -> i.getLink());
        this.perguntas = produto.mapeiaPerguntas(p -> p.getTitulo());
        this.opinioes = produto.mapeiaOpinioes(DetalheOpiniaoProdutoResponse::new);
        this.mediaNotas = produto.getOpinioes().stream().mapToDouble(Opiniao::getNota).average().orElse(0.0);
        this.quantidadeNotas = produto.getQuantidadeOpiniao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<DetalheCaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinkImagem() {
        return linkImagem;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<DetalheOpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }
}
