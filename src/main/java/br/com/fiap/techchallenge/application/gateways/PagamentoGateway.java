package br.com.fiap.techchallenge.application.gateways;

import java.math.BigDecimal;

public interface PagamentoGateway {

    String gerarQrCode(String idPedido, BigDecimal valor);
}
