package br.com.fiap.techchallenge.infrastructure.persistence.repository;

import br.com.fiap.techchallenge.infrastructure.persistence.entities.ProdutoMongoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<ProdutoMongoEntity, String> {

    List<ProdutoMongoEntity> findByCategoria(CategoriaProduto categoria);
}
