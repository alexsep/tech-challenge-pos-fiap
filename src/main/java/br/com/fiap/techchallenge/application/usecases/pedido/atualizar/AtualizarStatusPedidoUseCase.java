package br.com.fiap.techchallenge.application.usecases.pedido.atualizar;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public AtualizarStatusPedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public AtualizarStatusPedidoOutput execute(AtualizarStatusPedidoCommand command) {
        Pedido pedido = this.pedidoGateway.findById(command.getIdPedido())
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));


        if (pedido.getStatus().equals(StatusPedido.FINALIZADO)) {
            throw new BusinessException("Pedido já foi finalizado");
        }

        switch (command.getNovoStatus()) {
            case EM_PREPARACAO -> pedido.iniciarPreparacao();
            case PRONTO -> pedido.finalizarPreparacao();
            case FINALIZADO -> pedido.finalizarPedido();
            default -> throw new BusinessException("Status inválido");
        }


        return AtualizarStatusPedidoOutput.from(this.pedidoGateway.save(pedido));


    }

}
