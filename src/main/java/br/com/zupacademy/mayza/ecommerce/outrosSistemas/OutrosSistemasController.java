package br.com.zupacademy.mayza.ecommerce.outrosSistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {


    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNotaFiscalRequest request) throws InterruptedException {
        System.out.println("criando nota " + request);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody NovaCompraRankingRequest request) throws InterruptedException {
        System.out.println("criando ranking" + request);
        Thread.sleep(150);
    }
}
