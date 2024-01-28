package br.com.fiap.techchallenge.application.usecases.cliente.atualizar;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.entity.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }


    public AtualizarClienteOutput execute(AtualizarClienteCommand command) {
        final var clienteSalvo = this.clienteGateway.findById(command.getId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        final var cliente = Cliente.novoCliente(
                command.getId(),
                command.getNome(),
                command.getCpf(),
                command.getCpf(),
                clienteSalvo.getAtivo(),
                clienteSalvo.getDataDesativacao()
        );

        return AtualizarClienteOutput.from(this.clienteGateway.save(cliente));
    }
}
