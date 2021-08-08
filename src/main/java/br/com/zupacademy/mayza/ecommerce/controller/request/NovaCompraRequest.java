package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.GatewayPagamento;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;
import br.com.zupacademy.mayza.ecommerce.validacao.IdValid;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    @NotNull
    private Integer quantidade;

    @NotNull
    @IdValid(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(Integer quantidade, Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Compra toCompra(UsuarioLogado usuarioLogado, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getById(idProduto);

        if (!produto.atualizaEstoque(quantidade)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto sem estoque para a quantia informada");
        }
        return new Compra(quantidade, usuarioLogado.get(), produto, gateway);
    }
}
