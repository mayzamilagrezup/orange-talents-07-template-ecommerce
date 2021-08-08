package br.com.zupacademy.mayza.ecommerce.modelo.email;

import br.com.zupacademy.mayza.ecommerce.modelo.compra.Compra;
import br.com.zupacademy.mayza.ecommerce.modelo.produto.Pergunta;
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

    public void enviaEmailCompra(Compra compra) {
        email.envia("Nova compra ... " + compra, "Você tem uma nova compra",
                compra.getComprador().getLogin(), compra.getDonoProduto().getLogin() );
    }
}


