package br.com.fiap.techchallenge.application.usecases.pedido.buscar;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoPorIdUseCase {

    private final PedidoGateway pedidoGateway;

    public BuscarPedidoPorIdUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public BuscarPedidoOutput execute(String id) {
        return this.pedidoGateway.findById(id)
                .map(BuscarPedidoOutput::from)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }
}
