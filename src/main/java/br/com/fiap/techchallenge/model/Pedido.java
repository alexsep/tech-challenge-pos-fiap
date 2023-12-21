package br.com.fiap.techchallenge.model;

import br.com.fiap.techchallenge.model.enums.StatusPedido;

import java.time.OffsetDateTime;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private OffsetDateTime dataPedido;
    private StatusPedido status;
    private List<Produto> produtos;
}
