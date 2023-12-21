package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.model.Pedido;
import br.com.fiap.techchallenge.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidosAtivos() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(new Pedido());
    }

    @PostMapping(path = "/checkout")
    public ResponseEntity<Pedido> fazerCheckout(@RequestBody Pedido pedidoDTO,
                                                UriComponentsBuilder uriBuilder) {
        final var uri = uriBuilder.path("/v1/pedidos/{id}").buildAndExpand(1).toUri();
        return ResponseEntity.created(uri).body(pedidoDTO);
    }
}
