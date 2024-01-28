package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Produto save(Produto produto);

    Optional<Produto> findById(String id);

    List<Produto> findAll();

    void deleteById(String id);
}
