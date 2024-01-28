package br.com.fiap.techchallenge.infrastructure.persistence.entities;

import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Document("Produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoMongoEntity {

    @Id
    private String id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private CategoriaProduto categoria;
    private List<String> imagens;

    public static ProdutoMongoEntity from(final Produto produto) {
        return new ProdutoMongoEntity(produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getImagens());
    }

    public Produto toEntity() {
        return Produto.novoProduto(
                getId(),
                getNome(),
                getValor(),
                getDescricao(),
                getCategoria(),
                getImagens());
    }

    public List<String> getImagens() {
        return Collections.unmodifiableList(imagens);
    }
}
