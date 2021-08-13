package br.com.zupacademy.mayza.ecommerce.modelo.compra;

import br.com.zupacademy.mayza.ecommerce.controller.request.CaracteristicaProdutoRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.Categoria;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.StatusTransacao;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.Transacao;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.SenhaLimpa;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CompraTest {

    @Test
    public void deveAdicionarUmaTransacaoComSucesso() {

        //cenário
        Compra novaCompra = novaCompra();
        RetornaGatewayPagamento retornaGatewayPagamento = compra -> {
            return new Transacao(StatusTransacao.SUCESSO, "12345", novaCompra);
        };

        // ação
        novaCompra.adicionaTransacao(retornaGatewayPagamento);


        // resultado
        Set<Transacao> transacoes = novaCompra.getTransacoes()
                                            .stream()
                                            .filter(transacao -> transacao.getIdTransacaoGateway().equals("12345"))
                                            .collect(Collectors.toSet());

        Assertions.assertFalse(transacoes.isEmpty());
    }

    @Test
    public void deveAdicionarUmaTransacaoComErro() {

        //cenário
        Compra novaCompra = novaCompra();
        RetornaGatewayPagamento retornaGatewayPagamento = compra -> {
            return new Transacao(StatusTransacao.ERRO, "12345", novaCompra);
        };

        //ação
        novaCompra.adicionaTransacao(retornaGatewayPagamento);

        //resultado
        Set<Transacao> transacaos = novaCompra.getTransacoes()
                                                .stream()
                                                .filter(transacao -> transacao.getIdTransacaoGateway().equals("12345"))
                                                .collect(Collectors.toSet());

        Assertions.assertFalse(transacaos.isEmpty());
    }

    private Compra novaCompra() {
        Usuario comprador = new Usuario("eu@gmail.com", new SenhaLimpa("12345"));
        Usuario dono = new Usuario("ma@gmail.com", new SenhaLimpa("12345"));
        Categoria categoria = new Categoria("Categoria A");
        Collection<CaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();
        caracteristicas.add(new CaracteristicaProdutoRequest("Caracteristica 1", "descricao"));
        caracteristicas.add(new CaracteristicaProdutoRequest("Caracteristica 2", "descricao"));
        caracteristicas.add(new CaracteristicaProdutoRequest("Caracteristica 3", "descricao"));
        Produto produto = new Produto("Produto A", new BigDecimal(1000), 10, "descrição", categoria, caracteristicas, dono);

        return new Compra(12, comprador, produto, GatewayPagamento.pagseguro);
    }
}
