package br.com.fiap.techchallenge.application.usecases.produto.buscar;

import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;

import java.math.BigDecimal;
import java.util.List;

public record BuscarProdutoOutput(
        String id,
        String nome,
        CategoriaProduto categoria,
        BigDecimal valor,
        String descricao,
        List<String> imagens
) {
    public static BuscarProdutoOutput from(final Produto produto) {
        return new BuscarProdutoOutput(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getValor(),
                produto.getDescricao(),
                produto.getImagens()
        );
    }
}
