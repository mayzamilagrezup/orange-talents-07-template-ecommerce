package br.com.zupacademy.mayza.ecommerce.modelo.email;

public interface Email {

    void envia(String corpo, String assunto, String emailInteressado, String emailVendedor);
}
