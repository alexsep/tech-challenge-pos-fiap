package br.com.fiap.techchallenge.application.usecases.cliente.buscar;

import br.com.fiap.techchallenge.domain.entity.Cliente;

public record BuscarClienteOutput(
        String id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static BuscarClienteOutput from(final Cliente cliente) {
        return new BuscarClienteOutput(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
