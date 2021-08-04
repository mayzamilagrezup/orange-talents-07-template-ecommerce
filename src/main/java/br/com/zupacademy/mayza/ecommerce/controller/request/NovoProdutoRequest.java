package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.Categoria;
import br.com.zupacademy.mayza.ecommerce.modelo.Produto;
import br.com.zupacademy.mayza.ecommerce.modelo.Usuario;
import br.com.zupacademy.mayza.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.mayza.ecommerce.validator.IdValid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @IdValid(domainClass = Categoria.class, fieldName = "id", message = "Id de categoria inválido")
    private Long idCategoria;

    @Size(min = 3)
    @Valid
    @UniqueElements
    private List<CaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao, Long idCategoria, List<CaracteristicaProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toProduto(CategoriaRepository categoriaRepository, Usuario usuario) {
        Categoria categoria = categoriaRepository.getById(idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria, caracteristicas, usuario);
    }

}
