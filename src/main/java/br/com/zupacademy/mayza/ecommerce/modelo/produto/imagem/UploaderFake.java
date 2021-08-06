package br.com.zupacademy.mayza.ecommerce.modelo.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake implements Uploader {

    @Override
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(i -> "http://bucket.io/" + i.getOriginalFilename()).collect(Collectors.toSet());
    }

}
