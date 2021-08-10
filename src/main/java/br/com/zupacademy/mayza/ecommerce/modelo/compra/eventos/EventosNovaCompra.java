package br.com.zupacademy.mayza.ecommerce.modelo.compra.eventos;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.util.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventoCompraSucessos;

    @Autowired
    private Emails emails;

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventoCompraSucessos.forEach(evento -> evento.processa(compra));
            compra.finalizaCompra();
        } else {
            emails.enviaEmailCompraNaoProcessada(compra);
        }
    }
}
