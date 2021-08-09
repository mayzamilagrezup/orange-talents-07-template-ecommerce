package br.com.zupacademy.mayza.ecommerce.util;

public interface Email {

    void envia(String corpo, String assunto, String emailInteressado, String emailVendedor);
}
