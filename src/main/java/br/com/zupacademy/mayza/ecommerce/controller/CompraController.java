package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.controller.request.NovaCompraRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.email.Emails;
import br.com.zupacademy.mayza.ecommerce.repository.CompraRepository;
import br.com.zupacademy.mayza.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Emails emails;

    @PostMapping
    public ResponseEntity<String> compraProduto(@RequestBody @Valid NovaCompraRequest request,
                                               @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder) {

        Compra compra = compraRepository.save(request.toCompra(usuarioLogado, produtoRepository));
        emails.enviaEmailCompra(compra);

        return ResponseEntity.ok(compra.urlRedirecionamento(uriComponentsBuilder));
    }
}
