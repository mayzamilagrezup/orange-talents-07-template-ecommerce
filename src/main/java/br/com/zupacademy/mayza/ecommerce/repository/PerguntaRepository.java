package br.com.zupacademy.mayza.ecommerce.repository;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.pergunta.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
