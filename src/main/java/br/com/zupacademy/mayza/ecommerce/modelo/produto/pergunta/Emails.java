package br.com.zupacademy.mayza.ecommerce.modelo.produto.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {

    @Autowired
    private Email email;

    public void eviaEmailPergunta(Pergunta pergunta) {
        email.envia("Nova pergunta", "Produto: " +  pergunta.getProduto().getNome(),
                pergunta.getUsuarioInteressado().getLogin(), pergunta.getDonoProduto().getLogin());
    }
}


