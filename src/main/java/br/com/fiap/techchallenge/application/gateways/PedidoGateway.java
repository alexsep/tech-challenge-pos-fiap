package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoGateway {

    Pedido save(Pedido pedido);
    Optional<Pedido> findById(String id);

    List<Pedido> findAll();
}
