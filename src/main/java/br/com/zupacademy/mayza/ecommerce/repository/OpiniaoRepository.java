package br.com.zupacademy.mayza.ecommerce.repository;

import br.com.zupacademy.mayza.ecommerce.modelo.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
}
