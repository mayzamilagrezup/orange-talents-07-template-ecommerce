package br.com.zupacademy.mayza.ecommerce.modelo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
