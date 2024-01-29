package br.com.fiap.techchallenge.application.usecases.pedido.pagamento;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.application.usecases.pedido.buscar.BuscarPedidoOutput;
import br.com.fiap.techchallenge.application.usecases.pedido.buscar.BuscarPedidoPorIdUseCase;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPagamentoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class GerarQrCodePagamentoUseCase {

    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final PagamentoGateway pagamentoGateway;


    public GerarQrCodePagamentoUseCase(
            BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase
            , PagamentoGateway pagamentoGateway) {
        this.buscarPedidoPorIdUseCase = buscarPedidoPorIdUseCase;
        this.pagamentoGateway = pagamentoGateway;
    }


    public GerarQrCodePagamentoOutput execute(String idPedido) {
        BuscarPedidoOutput pedidoOutput = buscarPedidoPorIdUseCase.execute(idPedido);

        if (StatusPedido.FINALIZADO.equals(pedidoOutput.status())) {
            throw new BusinessException("Pedido já foi finalizado");
        }

        if (StatusPagamentoPedido.APROVADO.equals(pedidoOutput.statusPagamento())) {
            throw new BusinessException("Pagamento já foi realizado");
        }

        final var qrCode =
                this.pagamentoGateway.gerarQrCode(pedidoOutput.id(), pedidoOutput.totalPedido());

        return new GerarQrCodePagamentoOutput(
                pedidoOutput.id(),
                pedidoOutput.totalPedido(),
                qrCode);


    }
}
