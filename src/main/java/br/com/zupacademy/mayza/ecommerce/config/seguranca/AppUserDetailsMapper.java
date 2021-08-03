package br.com.zupacademy.mayza.ecommerce.config.seguranca;

import br.com.zupacademy.mayza.ecommerce.modelo.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }

}