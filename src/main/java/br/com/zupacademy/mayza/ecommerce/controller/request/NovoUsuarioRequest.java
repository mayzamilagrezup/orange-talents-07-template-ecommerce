package br.com.zupacademy.mayza.ecommerce.controller.request;

import br.com.zupacademy.mayza.ecommerce.modelo.usuario.SenhaLimpa;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;
import br.com.zupacademy.mayza.ecommerce.config.validacao.validator.UniqueValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValid(domainClass = Usuario.class, fieldName = "login", message = "Já existe um cadastro com o email informado")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(login, new SenhaLimpa(senha));
    }

}
