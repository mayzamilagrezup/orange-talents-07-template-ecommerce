package br.com.zupacademy.mayza.ecommerce.modelo.produto;

import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(titulo, pergunta.titulo) && Objects.equals(usuarioInteressado, pergunta.usuarioInteressado) && Objects.equals(produto, pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, usuarioInteressado, produto);
    }

    public String getTitulo() {
        return titulo;
    }
}
