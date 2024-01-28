package br.com.fiap.techchallenge.application.usecases.pedido.atualizar;

import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class AtualizarStatusPedidoCommand {
    @NotNull
    private String idPedido;
    @NotNull
    private StatusPedido novoStatus;

    public AtualizarStatusPedidoCommand(String idPedido, StatusPedido novoStatus) {
        this.idPedido = idPedido;
        this.novoStatus = novoStatus;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public StatusPedido getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(StatusPedido novoStatus) {
        this.novoStatus = novoStatus;
    }
}
