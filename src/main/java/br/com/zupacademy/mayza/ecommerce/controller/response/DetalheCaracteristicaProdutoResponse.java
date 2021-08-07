package br.com.zupacademy.mayza.ecommerce.controller.response;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.CaracteristicaProduto;

public class DetalheCaracteristicaProdutoResponse {

    private String nome;
    private String descricao;

    public DetalheCaracteristicaProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
