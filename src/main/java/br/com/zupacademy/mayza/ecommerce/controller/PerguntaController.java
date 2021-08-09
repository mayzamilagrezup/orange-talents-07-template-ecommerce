package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.controller.request.NovaPerguntaRequest;
import br.com.zupacademy.mayza.ecommerce.util.Emails;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Pergunta;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.repository.PerguntaRepository;
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
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Emails email;

    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> adiciona(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request,
                                      @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Pergunta pergunta = perguntaRepository.save(request.toPergunta(produto.get(), usuarioLogado));
        email.eviaEmailPergunta(pergunta);
        return ResponseEntity.ok().build();
    }
}
