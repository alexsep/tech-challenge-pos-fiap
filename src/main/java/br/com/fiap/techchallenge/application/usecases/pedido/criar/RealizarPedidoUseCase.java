package br.com.fiap.techchallenge.application.usecases.pedido.criar;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.application.usecases.cliente.buscar.BuscarClienteOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.buscar.BuscarClienteUseCase;
import br.com.fiap.techchallenge.application.usecases.produto.buscar.BuscarProdutoPorIdUseCase;
import br.com.fiap.techchallenge.domain.entity.Cliente;
import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.domain.entity.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPagamentoPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class RealizarPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    public RealizarPedidoUseCase(PedidoGateway pedidoGateway, BuscarClienteUseCase buscarClienteUseCase, BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase) {
        this.pedidoGateway = pedidoGateway;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
    }


    public RealizarPedidoOutput execute(RealizarPedidoCommand command) {

        if (command.getProdutos() == null) {
            throw new BusinessException("É obrigatório informar ao menos 1 produto");
        }

        BuscarClienteOutput buscarClienteOutput = validarCliente(command.getClienteId());

        Cliente cliente = buscarClienteOutput == null ? null : Cliente.novoCliente(
                buscarClienteOutput.id(),
                buscarClienteOutput.nome(),
                buscarClienteOutput.cpf(),
                buscarClienteOutput.email(),
                buscarClienteOutput.ativo(),
                null
        );

        Pedido novoPedido = Pedido.novoPedido(
                null,
                null,
                cliente,
                OffsetDateTime.now(),
                StatusPedido.RECEBIDO,
                StatusPagamentoPedido.PENDENTE);


        final var produtosPedido = command.getProdutos();

        for (ProdutoPedidoCommand produto : produtosPedido) {
            final var produtoValido = this.buscarProdutoPorIdUseCase.execute(produto.getIdProduto());

            novoPedido.adicionarProduto(Produto.novoProduto(
                            produtoValido.id(),
                            produtoValido.nome(),
                            produtoValido.valor(),
                            produtoValido.descricao(),
                            produtoValido.categoria(),
                            produtoValido.imagens()),
                    produto.getQuantidade());
        }

        return RealizarPedidoOutput.from(this.pedidoGateway.save(novoPedido));


    }

    private BuscarClienteOutput validarCliente(String clienteId) {

        if (clienteId != null) {
            final var cliente = this.buscarClienteUseCase.execute(clienteId);
            if (Boolean.FALSE.equals(cliente.ativo())) {
                throw new BusinessException("Informe um cliente válido");
            }

            return cliente;
        }

        return null;
    }
}
