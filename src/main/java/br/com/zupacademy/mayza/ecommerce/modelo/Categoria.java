package br.com.zupacademy.mayza.ecommerce.modelo;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria() {
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }
}
