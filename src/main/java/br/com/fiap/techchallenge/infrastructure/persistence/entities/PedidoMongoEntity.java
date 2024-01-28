package br.com.fiap.techchallenge.infrastructure.persistence.entities;

import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.domain.entity.ProdutoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPagamentoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.List;

@Document("Pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoMongoEntity {

    @Id
    private String id;
    private int numero;
    private ClienteMongoEntity cliente;
    private OffsetDateTime dataPedido;
    private StatusPedido status;
    private StatusPagamentoPedido statusPagamento;
    private List<ProdutoPedidoMongo> produtos;


    public static PedidoMongoEntity from(final Pedido pedido) {

        List<ProdutoPedidoMongo> produtos = pedido.getProdutos()
                .stream()
                .map(ProdutoPedidoMongo::from)
                .toList();


        final var cliente = pedido.getCliente() != null ? ClienteMongoEntity.from(pedido.getCliente()) : null;

        return new PedidoMongoEntity(
                pedido.getId(),
                1,
                cliente,
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getStatusPagamento(),
                produtos);
    }

    public Pedido toEntity() {

        List<ProdutoPedidoMongo> produtos = getProdutos();


        Pedido pedido = Pedido.novoPedido(
                getId(),
                getNumero(),
                getCliente() != null ? getCliente().toEntity() : null,
                getDataPedido(),
                getStatus(),
                getStatusPagamento());

        getProdutos().forEach(produtoPedidoMongo -> {
            ProdutoPedido produtoPedidoEntiy = produtoPedidoMongo.toEntity();
            pedido.adicionarProduto(produtoPedidoEntiy.getProduto(), produtoPedidoEntiy.getQuantidade());
        });


        return pedido;
    }
}

class ProdutoPedidoMongo {
    private final Produto produto;
    private final int quantidade;

    private ProdutoPedidoMongo(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public static ProdutoPedidoMongo from(ProdutoPedido produtoPedido) {
        return new ProdutoPedidoMongo(produtoPedido.getProduto(), produtoPedido.getQuantidade());
    }

    public ProdutoPedido toEntity() {
        return ProdutoPedido.novoProdutoPedido(produto, quantidade);
    }


}