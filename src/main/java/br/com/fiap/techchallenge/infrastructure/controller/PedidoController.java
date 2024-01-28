package br.com.fiap.techchallenge.infrastructure.controller;

import br.com.fiap.techchallenge.application.usecases.pedido.atualizar.AtualizarStatusPedidoCommand;
import br.com.fiap.techchallenge.application.usecases.pedido.atualizar.AtualizarStatusPedidoOutput;
import br.com.fiap.techchallenge.application.usecases.pedido.atualizar.AtualizarStatusPedidoUseCase;
import br.com.fiap.techchallenge.application.usecases.pedido.buscar.BuscarPedidoOutput;
import br.com.fiap.techchallenge.application.usecases.pedido.buscar.BuscarPedidoPorIdUseCase;
import br.com.fiap.techchallenge.application.usecases.pedido.buscar.BuscarPedidosUseCase;
import br.com.fiap.techchallenge.application.usecases.pedido.criar.RealizarPedidoCommand;
import br.com.fiap.techchallenge.application.usecases.pedido.criar.RealizarPedidoOutput;
import br.com.fiap.techchallenge.application.usecases.pedido.criar.RealizarPedidoUseCase;
import br.com.fiap.techchallenge.application.usecases.pedido.pagamento.NotificacaoPagamentoCommand;
import br.com.fiap.techchallenge.application.usecases.pedido.pagamento.NotificarPagamentoPedidoUseCase;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private final RealizarPedidoUseCase realizarPedidoUseCase;
    private final BuscarPedidosUseCase buscarPedidosUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final NotificarPagamentoPedidoUseCase notificarPagamentoPedidoUseCase;

    public PedidoController(RealizarPedidoUseCase realizarPedidoUseCase,
                            BuscarPedidosUseCase buscarPedidosUseCase,
                            BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase,
                            AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase,
                            NotificarPagamentoPedidoUseCase notificarPagamentoPedidoUseCase) {
        this.realizarPedidoUseCase = realizarPedidoUseCase;
        this.buscarPedidosUseCase = buscarPedidosUseCase;
        this.buscarPedidoPorIdUseCase = buscarPedidoPorIdUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
        this.notificarPagamentoPedidoUseCase = notificarPagamentoPedidoUseCase;
    }

    @PostMapping(path = "/checkout")
    public ResponseEntity<RealizarPedidoOutput> fazerCheckout(@RequestBody RealizarPedidoCommand command,
                                                              UriComponentsBuilder uriBuilder) {
        RealizarPedidoOutput pedido = this.realizarPedidoUseCase.execute(command);

        final var uri = uriBuilder.path("/v1/pedidos/{id}").buildAndExpand(pedido.id()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<BuscarPedidoOutput>> listarPedidosAtivos() {
        return ResponseEntity.ok(this.buscarPedidosUseCase.execute());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BuscarPedidoOutput> buscar(@PathVariable String id) {
        return ResponseEntity.ok(this.buscarPedidoPorIdUseCase.execute(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AtualizarStatusPedidoOutput> atualizarStatusPedido(
            @PathVariable String id,
            @RequestBody @Valid AtualizarStatusPedidoCommand command) {

        if (!id.equals(command.getIdPedido())) {
            throw new BusinessException("Pedido inválido");
        }

        return ResponseEntity.ok(this.atualizarStatusPedidoUseCase.execute(command));
    }

    @PutMapping(path = "/notifications")
    public void notificacaoPagamento(
            @RequestBody @Valid NotificacaoPagamentoCommand command) {

        this.notificarPagamentoPedidoUseCase.execute(command);
    }


}
