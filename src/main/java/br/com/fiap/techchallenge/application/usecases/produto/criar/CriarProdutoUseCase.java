package br.com.fiap.techchallenge.application.usecases.produto.criar;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CriarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public CriarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = Objects.requireNonNull(produtoGateway);
    }


    public CriarProdutoOutput execute(CriarProdutoCommand command) {
        final var produto = Produto.novoProduto(
                command.getId(),
                command.getNome(),
                command.getValor(),
                command.getDescricao(),
                command.getCategoria(),
                command.getImagens());

        return CriarProdutoOutput.from(produtoGateway.save(produto));
    }


}
