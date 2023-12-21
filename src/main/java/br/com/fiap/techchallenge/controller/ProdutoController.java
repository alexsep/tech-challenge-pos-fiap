package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.model.Produto;
import br.com.fiap.techchallenge.model.enums.CategoriaProduto;
import br.com.fiap.techchallenge.service.ProdutoService;
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

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(
            @RequestBody @Valid Produto produtoDTO,
            UriComponentsBuilder uriBuilder
    ) {
        final var produto = new Produto();
        final var uri = uriBuilder.path("/v1/produtos/{id}").buildAndExpand(1).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produtoDTO) {
        return ResponseEntity.ok(new Produto());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(@RequestParam(required = false) CategoriaProduto categoria) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new Produto());
    }

}
