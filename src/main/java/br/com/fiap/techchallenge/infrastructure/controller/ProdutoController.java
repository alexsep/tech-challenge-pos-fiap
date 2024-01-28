package br.com.fiap.techchallenge.infrastructure.controller;

import br.com.fiap.techchallenge.application.usecases.produto.atualizar.AtualizarProdutoCommand;
import br.com.fiap.techchallenge.application.usecases.produto.atualizar.AtualizarProdutoOutput;
import br.com.fiap.techchallenge.application.usecases.produto.atualizar.AtualizarProdutoUseCase;
import br.com.fiap.techchallenge.application.usecases.produto.buscar.BuscarProdutoOutput;
import br.com.fiap.techchallenge.application.usecases.produto.buscar.BuscarProdutoPorIdUseCase;
import br.com.fiap.techchallenge.application.usecases.produto.criar.CriarProdutoCommand;
import br.com.fiap.techchallenge.application.usecases.produto.criar.CriarProdutoOutput;
import br.com.fiap.techchallenge.application.usecases.produto.criar.CriarProdutoUseCase;
import br.com.fiap.techchallenge.application.usecases.produto.deletar.DeletarProdutoUseCase;
import br.com.fiap.techchallenge.application.usecases.produto.listar.ListarProdutosOutput;
import br.com.fiap.techchallenge.application.usecases.produto.listar.ListarProdutosUseCase;
import br.com.fiap.techchallenge.infrastructure.persistence.enums.CategoriaProduto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    private final DeletarProdutoUseCase deletarProdutoUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;


    public ProdutoController(CriarProdutoUseCase criarProdutoUseCase,
                             BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase,
                             DeletarProdutoUseCase deletarProdutoUseCase, AtualizarProdutoUseCase atualizarProdutoUseCase, ListarProdutosUseCase listarProdutosUseCase) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
        this.deletarProdutoUseCase = deletarProdutoUseCase;
        this.atualizarProdutoUseCase = atualizarProdutoUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
    }


    @PostMapping
    public ResponseEntity<CriarProdutoOutput> salvar(
            @RequestBody @Valid CriarProdutoCommand criarProdutoCommand,
            UriComponentsBuilder uriBuilder
    ) {
        CriarProdutoOutput produtoOutput = criarProdutoUseCase.execute(criarProdutoCommand);
        final var uri = uriBuilder
                .path("/v1/produtos/{id}").buildAndExpand(produtoOutput.id())
                .toUri();

        return ResponseEntity.created(uri).body(produtoOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.deletarProdutoUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<AtualizarProdutoOutput> atualizar(
            @RequestBody @Valid AtualizarProdutoCommand command) {

        AtualizarProdutoOutput produtoOutput = atualizarProdutoUseCase.execute(command);

        return ResponseEntity.ok(produtoOutput);
    }

    @GetMapping
    public ResponseEntity<List<ListarProdutosOutput>> listarProdutos(
            @RequestParam(required = false) CategoriaProduto categoria) {
        return ResponseEntity.ok(this.listarProdutosUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarProdutoOutput> buscarProdutoPorId(@PathVariable String id) {
        return ResponseEntity.ok(this.buscarProdutoPorIdUseCase.execute(id));
    }

}
