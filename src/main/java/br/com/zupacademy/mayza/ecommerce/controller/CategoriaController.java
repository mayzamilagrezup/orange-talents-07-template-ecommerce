package br.com.zupacademy.mayza.ecommerce.controller;

import br.com.zupacademy.mayza.ecommerce.controller.request.NovaCategoriaRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.Categoria;
import br.com.zupacademy.mayza.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = categoriaRepository.save(request.toCategoria(categoriaRepository));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).build();

    }
}
