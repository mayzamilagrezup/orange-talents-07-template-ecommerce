package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.controller.request.PagamentoPagSeguroRequest;
import br.com.zupacademy.mayza.ecommerce.controller.request.PagamentoPaypalRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.eventos.EventosNovaCompra;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.RetornaGatewayPagamento;
import br.com.zupacademy.mayza.ecommerce.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FechamentoCompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid PagamentoPagSeguroRequest request) {

        return processaPagamento(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    public ResponseEntity<?> processamentoPaypal(@PathVariable("id") Long idCompra, @Valid PagamentoPaypalRequest request) {

        return processaPagamento(idCompra, request);
    }

    private ResponseEntity<?> processaPagamento(Long idCompra, RetornaGatewayPagamento retornaGatewayPagamento) {

        Optional<Compra> compra = compraRepository.findById(idCompra);
        if (!compra.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        compra.get().adicionaTransacao(retornaGatewayPagamento);
        compraRepository.save(compra.get());
        eventosNovaCompra.processa(compra.get());
        return ResponseEntity.ok().build();
    }
}

