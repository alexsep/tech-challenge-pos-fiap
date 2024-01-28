package br.com.fiap.techchallenge.domain.entity;

import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPagamentoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Pedido {

    private final String id;
    private final Integer numero;
    private final Cliente cliente;
    private final OffsetDateTime dataPedido;
    private final List<ProdutoPedido> produtos = new ArrayList<>();
    private StatusPedido status;
    private StatusPagamentoPedido statusPagamento;

    private Pedido(
            String id,
            Integer numero,
            Cliente cliente,
            OffsetDateTime dataPedido,
            StatusPedido status,
            StatusPagamentoPedido statusPagamento
    ) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.statusPagamento = statusPagamento;
    }

    public static Pedido novoPedido(String id,
                                    Integer numero,
                                    Cliente cliente,
                                    OffsetDateTime dataPedido,
                                    StatusPedido status,
                                    StatusPagamentoPedido statusPagamentoPedido) {

        Objects.requireNonNull(status, "'status' não pode ser nulo");


        return new Pedido(
                id,
                numero,
                cliente,
                dataPedido == null ? OffsetDateTime.now() : dataPedido,
                status,
                statusPagamentoPedido
        );

    }

    public Pedido adicionarProduto(Produto produto, int quantidade) {
        this.produtos.add(ProdutoPedido.novoProdutoPedido(produto, quantidade));
        return this;
    }

    public Pedido finalizarPedido() {
        this.status = StatusPedido.FINALIZADO;
        return this;
    }

    public Pedido iniciarPreparacao() {
        if (this.status.equals(StatusPedido.PRONTO) ||
                this.status.equals(StatusPedido.FINALIZADO)) {
            throw new BusinessException("Pedido já está pronto ou finalizado.");
        }

        this.status = StatusPedido.EM_PREPARACAO;
        return this;
    }

    public Pedido finalizarPreparacao() {
        if (this.status.equals(StatusPedido.FINALIZADO)) {
            throw new BusinessException("Pedido já está finalizado.");
        }

        this.status = StatusPedido.PRONTO;
        return this;
    }

    public Pedido aprovarPagamento() {
        if (this.statusPagamento.equals(StatusPagamentoPedido.APROVADO)) {
            return this;
        }

        this.statusPagamento = StatusPagamentoPedido.APROVADO;
        return this;

    }

    public Pedido recusarPagamento() {
        if (this.statusPagamento.equals(StatusPagamentoPedido.APROVADO)) {
            throw new BusinessException("Pagamento já foi aprovado.");
        }

        this.statusPagamento = StatusPagamentoPedido.RECUSADO;
        return this;

    }

    public boolean validarPedido() {
        return this.produtos != null && !this.produtos.isEmpty();
    }


    public String getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public OffsetDateTime getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public StatusPagamentoPedido getStatusPagamento() {
        return statusPagamento;
    }
}



