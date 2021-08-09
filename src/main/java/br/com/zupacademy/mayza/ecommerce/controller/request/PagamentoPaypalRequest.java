package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.RetornaGatewayPagamento;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.StatusTransacao;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PagamentoPaypalRequest implements RetornaGatewayPagamento {

    @NotBlank
    private String idTransacao;

    @Min(0)
    @Max(1)
    private Integer status;

    public PagamentoPaypalRequest(String idTransacao, Integer status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.ERRO :StatusTransacao.SUCESSO;
        return new Transacao(statusCalculado, idTransacao, compra);
    }
}
