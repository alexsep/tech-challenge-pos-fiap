package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.model.Cliente;
import br.com.fiap.techchallenge.service.ClienteService;
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

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(new Cliente());
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente,
                                          UriComponentsBuilder uriBuilder){

        final var uri = uriBuilder.path("/v1/clientes/{id}").buildAndExpand(1).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cliente);
    }

    @PutMapping(value = "/{id}/desativar")
    public ResponseEntity<Cliente> desativar(@PathVariable Integer id) {

        return ResponseEntity.ok(new Cliente());
    }


}
