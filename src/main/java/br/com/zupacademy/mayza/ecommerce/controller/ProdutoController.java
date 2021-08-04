package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.config.seguranca.UsuarioLogado;
import br.com.zupacademy.mayza.ecommerce.controller.request.NovoProdutoRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.Usuario;
import br.com.zupacademy.mayza.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.mayza.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.mayza.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Usuario usuario = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        Produto produto = produtoRepository.save(request.toProduto(categoriaRepository, usuario));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).build();
    }

}
