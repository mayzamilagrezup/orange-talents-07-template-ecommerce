package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.RetornaGatewayPagamento;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.StatusRetornoPagseguro;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPagSeguroRequest implements RetornaGatewayPagamento {

    @NotBlank
    private String idTransacao;

    @NotNull
    private StatusRetornoPagseguro status;

    public PagamentoPagSeguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
