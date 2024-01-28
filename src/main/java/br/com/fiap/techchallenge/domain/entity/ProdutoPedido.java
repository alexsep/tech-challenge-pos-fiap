package br.com.fiap.techchallenge.domain.entity;

import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;

import java.util.Objects;

public class ProdutoPedido {
    private final Produto produto;
    private final int quantidade;

    private ProdutoPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public static ProdutoPedido novoProdutoPedido(
            Produto produto,
            int quantidade
    ) {
        Objects.requireNonNull(produto, "'produto' não pode ser nulo");
        if (quantidade <= 0) {
            throw new BusinessException("Quantidade deve ser maior que zero");
        }

        return new ProdutoPedido(produto, quantidade);

    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
