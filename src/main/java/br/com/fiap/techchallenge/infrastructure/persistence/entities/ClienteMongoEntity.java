package br.com.fiap.techchallenge.infrastructure.persistence.entities;

import br.com.fiap.techchallenge.domain.entity.Cliente;
import br.com.fiap.techchallenge.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document("Clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteMongoEntity {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private Boolean ativo;
    private OffsetDateTime dataDesativacao;

    public static ClienteMongoEntity from(final Cliente cliente) {
        return new ClienteMongoEntity(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo(),
                cliente.getDataDesativacao());
    }

    public Cliente toEntity() {
        return Cliente.novoCliente(
                getId(),
                getNome(),
                getCpf(),
                getEmail(),
                getAtivo(),
                getDataDesativacao());
    }

}
