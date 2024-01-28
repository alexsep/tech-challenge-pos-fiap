package br.com.fiap.techchallenge.domain.entity;

import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Produto {

    private final String id;
    private final String nome;
    private final BigDecimal valor;
    private final String descricao;
    private final CategoriaProduto categoria;
    private final List<String> imagens = new ArrayList<>();

    private Produto(
            final String id,
            final String nome,
            final BigDecimal valor,
            final String descricao,
            final CategoriaProduto categoria,
            final List<String> imagens
    ) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagens.addAll(imagens);
    }


    public static Produto novoProduto(final String id,
                                      final String nome,
                                      final BigDecimal valor,
                                      final String descricao,
                                      final CategoriaProduto categoria,
                                      final List<String> imagens) {

        return new Produto(
                id,
                nome,
                valor,
                descricao,
                categoria,
                imagens
        );

    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public List<String> getImagens() {
        return Collections.unmodifiableList(imagens);
    }
}
