package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.controller.request.NovaOpiniaoRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.Opiniao;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.repository.OpiniaoRepository;
import br.com.zupacademy.mayza.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/{id}/opiniao")
    public ResponseEntity<?> adiciona(@PathVariable ("id") Long id, @RequestBody @Valid NovaOpiniaoRequest request,
                                      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Opiniao opiniao = opiniaoRepository.save(request.toOpiniao(produto.get(), usuarioLogado));
        return ResponseEntity.ok().build();
    }


}
