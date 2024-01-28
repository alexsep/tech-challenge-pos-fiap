package br.com.fiap.techchallenge.infrastructure.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("NumeroSequencial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeradorNumeroSequencial {

    @Id
    private String id;
    private EntidadeEnum entidade;
    private int proximoNumero;


    public int incrementarNumero() {
        return this.proximoNumero += 1;
    }
}
