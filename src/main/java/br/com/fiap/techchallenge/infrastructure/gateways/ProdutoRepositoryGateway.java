package br.com.fiap.techchallenge.infrastructure.gateways;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.ProdutoMongoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto produto) {
        return this.produtoRepository.save(ProdutoMongoEntity.from(produto)).toEntity();
    }

    @Override
    public Optional<Produto> findById(String id) {
        return this.produtoRepository.findById(id)
                .map(ProdutoMongoEntity::toEntity);
    }

    @Override
    public List<Produto> findAll() {
        return this.produtoRepository.findAll()
                .stream()
                .map(ProdutoMongoEntity::toEntity)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        this.produtoRepository.deleteById(id);
    }
}
