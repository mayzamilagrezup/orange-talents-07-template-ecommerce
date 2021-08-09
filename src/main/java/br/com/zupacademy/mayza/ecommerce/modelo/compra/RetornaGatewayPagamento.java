package br.com.zupacademy.mayza.ecommerce.modelo.compra;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao.Transacao;

public interface RetornaGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
