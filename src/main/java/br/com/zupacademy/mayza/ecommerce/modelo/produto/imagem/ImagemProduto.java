package br.com.zupacademy.mayza.ecommerce.modelo.produto.imagem;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;

import javax.persistence.*;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;
    private String link;

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public ImagemProduto() {
    }

}
