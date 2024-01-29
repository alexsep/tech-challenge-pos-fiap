package br.com.fiap.techchallenge.application.usecases.pedido.criar;

import br.com.fiap.techchallenge.domain.entity.ProdutoPedido;

import java.math.BigDecimal;

public record ProdutoPedidoOutput(
        String nome,
        BigDecimal valor,
        Integer quantidade
) {

    public static ProdutoPedidoOutput from(ProdutoPedido produtoPedido) {
        return new ProdutoPedidoOutput(
                produtoPedido.getProduto().getNome(),
                produtoPedido.getProduto().getValor(),
                produtoPedido.getQuantidade()
        );
    }

    public BigDecimal calcularTotalItem(){
        return valor.multiply(new BigDecimal(quantidade));
    }
}
