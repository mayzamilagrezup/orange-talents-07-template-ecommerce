package br.com.zupacademy.mayza.ecommerce.util;

import org.springframework.stereotype.Component;

@Component
public class EmailFake implements Email {

    @Override
    public void envia(String corpo, String assunto, String emailInteressado,
                String emailVendedor) {
        System.out.println(corpo);
        System.out.println(assunto);
        System.out.println(emailInteressado);
        System.out.println(emailVendedor);
    }

}
