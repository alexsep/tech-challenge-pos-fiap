package br.com.fiap.techchallenge.infrastructure.gateways;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.entity.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.ClienteMongoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryGateway(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(ClienteMongoEntity.from(cliente)).toEntity();
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return this.clienteRepository.findById(id)
                .map(ClienteMongoEntity::toEntity);
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll()
                .stream()
                .map(ClienteMongoEntity::toEntity)
                .toList();
    }
}
