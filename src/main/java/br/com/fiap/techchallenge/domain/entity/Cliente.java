package br.com.fiap.techchallenge.domain.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Cliente {

    private final String id;
    private final String nome;
    private final String cpf;
    private final String email;
    private Boolean ativo;
    private OffsetDateTime dataDesativacao;


    private Cliente(
            String id,
            String nome,
            String cpf,
            String email,
            Boolean ativo,
            OffsetDateTime dataDesativacao
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ativo = ativo;
        this.dataDesativacao = dataDesativacao;
    }

    public static Cliente novoCliente(final String id,
                                      final String nome,
                                      final String cpf,
                                      final String email,
                                      final Boolean ativo,
                                      final OffsetDateTime dataDesativacao
    ) {

        Objects.requireNonNull(nome, "'nome' não pode ser nulo");
        Objects.requireNonNull(cpf, "'cpf' não pode ser nulo");
        Objects.requireNonNull(email, "'email' não pode ser nulo");

        return new Cliente(
                id,
                nome,
                cpf,
                email,
                ativo,
                Boolean.FALSE.equals(ativo) && dataDesativacao == null ? OffsetDateTime.now() : dataDesativacao
        );
    }

    public Cliente desativar() {
        if (getDataDesativacao() == null) {
            this.dataDesativacao = OffsetDateTime.now();
        }

        this.ativo = false;
        return this;
    }

    public Cliente ativar() {
        this.dataDesativacao = null;
        this.ativo = true;

        return this;
    }


    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public OffsetDateTime getDataDesativacao() {
        return dataDesativacao;
    }

}
