package br.com.fiap.techchallenge.application.usecases.cliente.salvar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class SalvarClienteCommand {
    private String id;
    @NotNull
    private String nome;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
