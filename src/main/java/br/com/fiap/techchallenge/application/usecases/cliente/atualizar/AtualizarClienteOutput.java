package br.com.fiap.techchallenge.application.usecases.cliente.atualizar;

import br.com.fiap.techchallenge.domain.entity.Cliente;

public record AtualizarClienteOutput(
        String id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static AtualizarClienteOutput from(final Cliente cliente) {
        return new AtualizarClienteOutput(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
