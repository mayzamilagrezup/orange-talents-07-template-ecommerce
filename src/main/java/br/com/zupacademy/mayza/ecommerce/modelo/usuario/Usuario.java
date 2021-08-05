package br.com.zupacademy.mayza.ecommerce.modelo.usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
