package br.com.fiap.techchallenge.application.usecases.cliente.listar;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarClientesUseCase {

    private final ClienteGateway clienteGateway;

    public ListarClientesUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public List<ListarClientesOutput> execute() {
        return this.clienteGateway.findAll()
                .stream()
                .map(ListarClientesOutput::from)
                .toList();
    }
}
