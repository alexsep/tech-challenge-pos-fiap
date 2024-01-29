package br.com.fiap.techchallenge.application.usecases.pedido.pagamento;

import java.math.BigDecimal;

public record GerarQrCodePagamentoOutput(
        String idPedido,
        BigDecimal totalPedido,
        String qrCode
) {

}