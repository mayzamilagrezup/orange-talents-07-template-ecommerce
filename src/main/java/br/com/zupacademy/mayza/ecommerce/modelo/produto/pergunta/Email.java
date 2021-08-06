package br.com.zupacademy.mayza.ecommerce.modelo.produto.pergunta;

public interface Email {

    void envia(String corpo, String assunto, String emailInteressado, String emailVendedor);
}
