package br.com.zupacademy.mayza.ecommerce.modelo.usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime dataHora = LocalDateTime.now();

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                '}';
    }
}
