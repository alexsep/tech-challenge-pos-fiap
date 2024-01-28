package br.com.fiap.techchallenge.application.usecases.cliente.salvar;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class SalvarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public SalvarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }


    public SalvarClienteOutput execute(SalvarClienteCommand command) {
        final var cliente = Cliente.novoCliente(
                command.getId(),
                command.getNome(),
                command.getCpf(),
                command.getEmail(),
                Boolean.TRUE,
                null
        );

        return SalvarClienteOutput.from(clienteGateway.save(cliente));

    }
}
