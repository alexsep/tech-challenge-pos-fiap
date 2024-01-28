package br.com.fiap.techchallenge.application.usecases.produto.atualizar;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public AtualizarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }


    public AtualizarProdutoOutput execute(final AtualizarProdutoCommand command) {
        this.produtoGateway.findById(command.getId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        final var produto = Produto.novoProduto(
                command.getId(),
                command.getNome(),
                command.getValor(),
                command.getDescricao(),
                command.getCategoria(),
                command.getImagens());

        return AtualizarProdutoOutput.from(this.produtoGateway.save(produto));
    }
}
