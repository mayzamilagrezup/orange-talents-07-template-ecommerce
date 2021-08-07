package br.com.zupacademy.mayza.ecommerce.controller.response;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Opiniao;

public class DetalheOpiniaoProdutoResponse {

    private String titulo;
    private String descricao;
    private Integer nota;

    public DetalheOpiniaoProdutoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
