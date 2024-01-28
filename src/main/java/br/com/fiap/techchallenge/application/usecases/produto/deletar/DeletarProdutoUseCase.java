package br.com.fiap.techchallenge.application.usecases.produto.deletar;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeletarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public DeletarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = Objects.requireNonNull(produtoGateway);
    }


    public void execute(String id) {
        this.produtoGateway.deleteById(id);
    }
}
