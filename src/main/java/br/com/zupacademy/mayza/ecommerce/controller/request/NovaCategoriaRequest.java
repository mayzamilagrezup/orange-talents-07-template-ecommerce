package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.Categoria;
import br.com.zupacademy.mayza.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.mayza.ecommerce.validator.IdValid;
import br.com.zupacademy.mayza.ecommerce.validator.UniqueValid;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValid(domainClass = Categoria.class, fieldName = "nome", message = "Já existe uma categoria com esse nome cadastrado.")
    private String nome;

    @IdValid(domainClass = Categoria.class, fieldName = "id", message = "Não existe uma categoria com o id informado", required=false)
    private Long idCategoriaMae;

    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toCategoria(CategoriaRepository categoriaRepository) {
       Categoria categoria = new Categoria(nome);

        if(idCategoriaMae != null) {
            Categoria categoriaMae = categoriaRepository.getById(idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
