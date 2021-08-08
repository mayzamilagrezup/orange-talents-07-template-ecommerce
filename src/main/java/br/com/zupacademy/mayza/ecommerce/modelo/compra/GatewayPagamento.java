package br.com.zupacademy.mayza.ecommerce.modelo.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro {
        @Override
        String urlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String uri = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + uri;
        }
    },
    paypal {
        @Override
        String urlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String uri = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + uri;
        }
    };

    abstract String urlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);

}
