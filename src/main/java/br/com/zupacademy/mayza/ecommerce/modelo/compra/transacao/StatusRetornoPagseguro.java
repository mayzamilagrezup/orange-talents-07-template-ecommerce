package br.com.zupacademy.mayza.ecommerce.modelo.compra.transacao;


public enum StatusRetornoPagseguro {
    SUCESSO,
    ERRO;

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.ERRO;
    }
}
