package br.com.fiap.techchallenge.application.usecases.produto.listar;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ListarProdutosUseCase {

    private final ProdutoGateway produtoGateway;

    public ListarProdutosUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = Objects.requireNonNull(produtoGateway);
    }


    public List<ListarProdutosOutput> execute() {
        return this.produtoGateway.findAll()
                .stream()
                .map(ListarProdutosOutput::from)
                .toList();
    }
}
