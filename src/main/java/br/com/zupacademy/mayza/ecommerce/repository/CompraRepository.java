package br.com.zupacademy.mayza.ecommerce.repository;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
