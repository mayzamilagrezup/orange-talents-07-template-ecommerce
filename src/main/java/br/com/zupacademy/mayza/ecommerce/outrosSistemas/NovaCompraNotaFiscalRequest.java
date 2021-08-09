package br.com.zupacademy.mayza.ecommerce.outrosSistemas;

import javax.validation.constraints.NotNull;

public class NovaCompraNotaFiscalRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNotaFiscalRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
