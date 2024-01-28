package br.com.fiap.techchallenge.application.usecases.cliente.salvar;

import br.com.fiap.techchallenge.domain.entity.Cliente;

public record SalvarClienteOutput(
        String id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static SalvarClienteOutput from(final Cliente cliente) {
        return new SalvarClienteOutput(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
