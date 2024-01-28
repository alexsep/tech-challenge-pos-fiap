package br.com.fiap.techchallenge.application.usecases.produto.criar;

import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class CriarProdutoCommand {
    private String id;
    @NotNull
    private String nome;
    @NotNull
    private CategoriaProduto categoria;
    @NotNull
    @Min(1)
    private BigDecimal valor;
    @NotNull
    private String descricao;
    @NotEmpty
    private List<String> imagens;

    public CriarProdutoCommand(
            String id,
            String nome,
            CategoriaProduto categoria,
            BigDecimal valor,
            String descricao,
            List<String> imagens) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.imagens = imagens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }
}
