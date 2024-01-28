package br.com.fiap.techchallenge.application.usecases.pedido.criar;

import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPagamentoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record RealizarPedidoOutput(
        String id,
        Integer numero,
        String clienteId,
        String nomeCliente,
        OffsetDateTime dataPedido,
        StatusPedido status,
        StatusPagamentoPedido statusPagamento,
        List<ProdutoPedidoOutput> produtos,
        BigDecimal totalPedido
) {
    public static RealizarPedidoOutput from(final Pedido pedido) {

        List<ProdutoPedidoOutput> produtos = pedido.getProdutos()
                .stream()
                .map(ProdutoPedidoOutput::from)
                .toList();

        final var valorTotal = produtos.stream()
                .map(ProdutoPedidoOutput::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RealizarPedidoOutput(
                pedido.getId(),
                pedido.getNumero(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                pedido.getCliente() != null ? pedido.getCliente().getNome() : null,
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getStatusPagamento(),
                produtos,
                valorTotal
        );
    }
}
