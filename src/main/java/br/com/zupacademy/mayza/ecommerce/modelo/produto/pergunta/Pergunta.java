package br.com.zupacademy.mayza.ecommerce.modelo.produto.pergunta;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private LocalDateTime instante = LocalDateTime.now();

    @ManyToOne
    private Usuario usuarioInteressado;

    @ManyToOne
    private Produto produto;

    public Pergunta(String titulo, Usuario usuarioInteressado, Produto produto) {
        this.titulo = titulo;
        this.usuarioInteressado = usuarioInteressado;
        this.produto = produto;
    }

    @Deprecated
    public Pergunta() {
    }

    public Usuario getUsuarioInteressado() {
        return usuarioInteressado;
    }

    public Usuario getDonoProduto() {
        return produto.getUsuario();
    }

    public Produto getProduto() {
        return produto;
    }
}
