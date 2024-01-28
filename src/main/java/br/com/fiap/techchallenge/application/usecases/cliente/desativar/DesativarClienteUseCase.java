package br.com.fiap.techchallenge.application.usecases.cliente.desativar;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DesativarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public DesativarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public DesativarClienteOutput execute(String id) {
        final var cliente = this.clienteGateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        cliente.desativar();

        return DesativarClienteOutput.from(this.clienteGateway.save(cliente));
    }
}
