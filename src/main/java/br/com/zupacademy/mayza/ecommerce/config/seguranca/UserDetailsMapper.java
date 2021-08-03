package br.com.zupacademy.mayza.ecommerce.config.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeASystemUser);
}
