package br.com.fiap.techchallenge.application.usecases.pedido.pagamento;

import jakarta.validation.constraints.NotNull;

public class NotificacaoPagamentoCommand {
    @NotNull
    private String idPedido;
    @NotNull
    private Boolean aproved;

    public NotificacaoPagamentoCommand(String idPedido, Boolean aproved) {
        this.idPedido = idPedido;
        this.aproved = aproved;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Boolean getAproved() {
        return aproved;
    }

    public void setAproved(Boolean aproved) {
        this.aproved = aproved;
    }
}
