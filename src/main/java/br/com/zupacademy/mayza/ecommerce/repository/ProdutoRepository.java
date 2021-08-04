package br.com.zupacademy.mayza.ecommerce.repository;

import br.com.zupacademy.mayza.ecommerce.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
