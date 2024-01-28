package br.com.fiap.techchallenge.application.usecases.cliente.listar;

import br.com.fiap.techchallenge.domain.entity.Cliente;

public record ListarClientesOutput(
        String id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static ListarClientesOutput from(final Cliente cliente) {
        return new ListarClientesOutput(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
