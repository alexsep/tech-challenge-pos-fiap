package br.com.fiap.techchallenge.application.usecases.pedido.criar;

import br.com.fiap.techchallenge.domain.entity.Cliente;
import br.com.fiap.techchallenge.domain.entity.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RealizarPedidoCommand {

    private String clienteId;
    @NotNull
    private List<ProdutoPedidoCommand> produtos = new ArrayList<>();

    public RealizarPedidoCommand(String clienteId, List<ProdutoPedidoCommand> produtos) {
        this.clienteId = clienteId;
        this.produtos = produtos;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProdutoPedidoCommand> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoPedidoCommand> produtos) {
        this.produtos = produtos;
    }
}

class ProdutoPedidoCommand {

    @NotNull
    private String idProduto;
    private String nomeProduto;
    @NotNull
    @Min(1)
    private int quantidade;

    public ProdutoPedidoCommand(String idProduto, String nomeProduto, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }


    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
