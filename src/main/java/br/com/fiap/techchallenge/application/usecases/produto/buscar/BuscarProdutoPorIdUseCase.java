package br.com.fiap.techchallenge.application.usecases.produto.buscar;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarProdutoPorIdUseCase {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoPorIdUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = Objects.requireNonNull(produtoGateway);
    }


    public BuscarProdutoOutput execute(String id) {
        return this.produtoGateway.findById(id)
                .map(BuscarProdutoOutput::from)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
}
