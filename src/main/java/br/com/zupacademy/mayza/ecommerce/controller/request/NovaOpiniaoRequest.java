package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.Opiniao;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaOpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toOpiniao(Produto produto, UsuarioLogado usuarioLogado) {
        return new Opiniao(nota, titulo, descricao, produto, usuarioLogado.get());
    }
}
