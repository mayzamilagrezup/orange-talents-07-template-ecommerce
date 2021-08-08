package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.Pergunta;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Produto;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toPergunta(Produto produto, UsuarioLogado usuarioLogado) {
        return new Pergunta(titulo, usuarioLogado.get(), produto);
    }
}
