package br.com.fiap.techchallenge.infrastructure.controller;

import br.com.fiap.techchallenge.application.usecases.cliente.atualizar.AtualizarClienteCommand;
import br.com.fiap.techchallenge.application.usecases.cliente.atualizar.AtualizarClienteOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.atualizar.AtualizarClienteUseCase;
import br.com.fiap.techchallenge.application.usecases.cliente.buscar.BuscarClienteOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.buscar.BuscarClienteUseCase;
import br.com.fiap.techchallenge.application.usecases.cliente.desativar.DesativarClienteOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.desativar.DesativarClienteUseCase;
import br.com.fiap.techchallenge.application.usecases.cliente.listar.ListarClientesOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.listar.ListarClientesUseCase;
import br.com.fiap.techchallenge.application.usecases.cliente.salvar.SalvarClienteCommand;
import br.com.fiap.techchallenge.application.usecases.cliente.salvar.SalvarClienteOutput;
import br.com.fiap.techchallenge.application.usecases.cliente.salvar.SalvarClienteUseCase;
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
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final SalvarClienteUseCase salvarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DesativarClienteUseCase desativarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;

    public ClienteController(SalvarClienteUseCase salvarClienteUseCase,
                             AtualizarClienteUseCase atualizarClienteUseCase,
                             DesativarClienteUseCase desativarClienteUseCase,
                             BuscarClienteUseCase buscarClienteUseCase,
                             ListarClientesUseCase listarClientesUseCase) {
        this.salvarClienteUseCase = salvarClienteUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.desativarClienteUseCase = desativarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    @PostMapping
    public ResponseEntity<SalvarClienteOutput> salvar(@RequestBody @Valid SalvarClienteCommand salvarClienteCommand,
                                                      UriComponentsBuilder uriBuilder) {

        SalvarClienteOutput clienteOutput = this.salvarClienteUseCase.execute(salvarClienteCommand);


        final var uri = uriBuilder.path("/v1/clientes/{id}").buildAndExpand(clienteOutput.id()).toUri();

        return ResponseEntity.created(uri).body(clienteOutput);
    }


    @PutMapping
    public ResponseEntity<AtualizarClienteOutput> atualizar(
            @RequestBody @Valid AtualizarClienteCommand atualizarClienteCommand) {

        final AtualizarClienteOutput clienteOutput = this.atualizarClienteUseCase.execute(atualizarClienteCommand);

        return ResponseEntity.ok(clienteOutput);
    }

    @PutMapping(value = "/{id}/desativar")
    public ResponseEntity<DesativarClienteOutput> desativar(@PathVariable String id) {
        final var clienteDesativado = this.desativarClienteUseCase.execute(id);

        return ResponseEntity.ok(clienteDesativado);
    }


    @GetMapping
    public ResponseEntity<List<ListarClientesOutput>> listar() {
        final var clientes = this.listarClientesUseCase.execute();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<BuscarClienteOutput> buscar(@PathVariable String id) {

        final var cliente = this.buscarClienteUseCase.execute(id);

        return ResponseEntity.ok(cliente);
    }


}
