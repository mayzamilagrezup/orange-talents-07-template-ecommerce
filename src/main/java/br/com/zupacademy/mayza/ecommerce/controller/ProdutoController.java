package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.config.seguranca.UsuarioLogado;
import br.com.zupacademy.mayza.ecommerce.controller.request.NovaImagemRequest;
import br.com.zupacademy.mayza.ecommerce.controller.request.NovoProdutoRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.UploaderFake;
import br.com.zupacademy.mayza.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.mayza.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Produto produto = produtoRepository.save(request.toProduto(categoriaRepository, usuarioLogado));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).build();
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid NovaImagemRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!produto.get().verificaDonoProduto(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.get().associaImagens(links);
        produtoRepository.save(produto.get());
        return ResponseEntity.ok().build();
    }

}
