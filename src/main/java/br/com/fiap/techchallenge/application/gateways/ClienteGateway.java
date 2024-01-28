package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteGateway {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(String id);

    List<Cliente> findAll();

}
