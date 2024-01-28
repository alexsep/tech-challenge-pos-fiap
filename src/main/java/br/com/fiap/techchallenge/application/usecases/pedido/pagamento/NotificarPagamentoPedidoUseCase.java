package br.com.fiap.techchallenge.application.usecases.pedido.pagamento;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NotificarPagamentoPedidoUseCase {

    private final PedidoGateway pedidoGateway;


    public NotificarPagamentoPedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public void execute(NotificacaoPagamentoCommand command) {
        Pedido pedido = this.pedidoGateway.findById(command.getIdPedido())
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));


        if (Boolean.TRUE.equals(command.getAproved())) {
            pedido.aprovarPagamento();
        } else {
            pedido.recusarPagamento();
        }

        this.pedidoGateway.save(pedido);


    }
}
