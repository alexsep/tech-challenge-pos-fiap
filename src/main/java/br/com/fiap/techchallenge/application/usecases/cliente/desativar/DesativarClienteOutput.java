package br.com.fiap.techchallenge.application.usecases.cliente.desativar;

import br.com.fiap.techchallenge.domain.entity.Cliente;

public record DesativarClienteOutput(
        String id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static DesativarClienteOutput from(final Cliente cliente) {
        return new DesativarClienteOutput(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
