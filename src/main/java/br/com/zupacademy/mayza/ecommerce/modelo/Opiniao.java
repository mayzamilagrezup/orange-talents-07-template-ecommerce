package br.com.zupacademy.mayza.ecommerce.modelo;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario consumidor;

    public Opiniao(Integer nota, String titulo, String descricao, Produto produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Deprecated
    public Opiniao() {
    }
}
