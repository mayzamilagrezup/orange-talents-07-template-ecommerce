package br.com.zupacademy.mayza.ecommerce.modelo.produto;

import br.com.zupacademy.mayza.ecommerce.modelo.produto.imagem.ImagemProduto;
import br.com.zupacademy.mayza.ecommerce.seguranca.UsuarioLogado;
import br.com.zupacademy.mayza.ecommerce.controller.request.CaracteristicaProdutoRequest;
import br.com.zupacademy.mayza.ecommerce.modelo.Categoria;
import br.com.zupacademy.mayza.ecommerce.modelo.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private Set<Pergunta> perguntas = new HashSet<>();

    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private Set<Opiniao> opinioes = new HashSet<>();

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, Collection<CaracteristicaProdutoRequest> caracteristicas, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream().map(c -> c.toCaracteristicaProduto(this)).collect(Collectors.toSet()));
        this.usuario = usuario;
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean verificaDonoProduto(UsuarioLogado usuarioLogado) {
        return this.usuario.getLogin().equals(usuarioLogado.getUsername());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public <T> Set<T> mapeiaCaracteristica(Function<CaracteristicaProduto, T> funcaoMapear) {
        return this.caracteristicas.stream().map(funcaoMapear).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagem(Function<ImagemProduto, T> funcaoMapear) {
        return this.imagens.stream().map(funcaoMapear).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapear) {
        return this.perguntas.stream().map(funcaoMapear).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapear) {
        return this.opinioes.stream().map(funcaoMapear).collect(Collectors.toSet());
    }

    public Integer getQuantidadeOpiniao() {
        return opinioes.size();
    }

    public boolean atualizaEstoque(Integer quantidade) {
        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}