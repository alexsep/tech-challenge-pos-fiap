package br.com.fiap.techchallenge.application.usecases.pedido.buscar;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarPedidosUseCase {

    private final PedidoGateway pedidoGateway;

    public BuscarPedidosUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<BuscarPedidoOutput> execute() {
        List<BuscarPedidoOutput> pedidos = this.pedidoGateway.findAll()
                .stream()
                .map(BuscarPedidoOutput::from)
                .toList();


        return pedidos.stream()
                .filter(pedido -> !pedido.status().equals(StatusPedido.FINALIZADO))
                .sorted(Comparator.comparing(BuscarPedidoOutput::dataPedido))
                .toList();

    }


}
