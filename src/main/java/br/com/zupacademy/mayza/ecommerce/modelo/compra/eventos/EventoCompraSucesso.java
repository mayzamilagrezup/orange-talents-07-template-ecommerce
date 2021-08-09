package br.com.zupacademy.mayza.ecommerce.modelo.compra.eventos;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;

public interface EventoCompraSucesso {

    void processa(Compra compra);
}
