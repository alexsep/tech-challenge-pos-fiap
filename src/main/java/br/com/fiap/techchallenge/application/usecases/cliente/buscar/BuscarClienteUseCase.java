package br.com.fiap.techchallenge.application.usecases.cliente.buscar;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public BuscarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }


    public BuscarClienteOutput execute(String id) {
        return this.clienteGateway.findById(id)
                .map(BuscarClienteOutput::from)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
