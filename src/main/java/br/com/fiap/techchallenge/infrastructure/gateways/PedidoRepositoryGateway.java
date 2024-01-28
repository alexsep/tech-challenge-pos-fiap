package br.com.fiap.techchallenge.infrastructure.gateways;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.entity.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.EntidadeEnum;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.PedidoMongoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.util.GeradorNumeroSequencialService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final GeradorNumeroSequencialService geradorNumeroSequencialService;

    public PedidoRepositoryGateway(PedidoRepository pedidoRepository, GeradorNumeroSequencialService geradorNumeroSequencialService) {
        this.pedidoRepository = pedidoRepository;
        this.geradorNumeroSequencialService = geradorNumeroSequencialService;
    }

    @Override
    public Pedido save(Pedido pedido) {

        final var numeroPedido =
                this.geradorNumeroSequencialService.buscarProximoNumeroSequencial(EntidadeEnum.PEDIDOS);

        PedidoMongoEntity pedidoMongoEntity = PedidoMongoEntity.from(pedido);
        pedidoMongoEntity.setNumero(numeroPedido);

        return this.pedidoRepository.save(pedidoMongoEntity).toEntity();
    }

    @Override
    public Optional<Pedido> findById(String id) {
        return this.pedidoRepository.findById(id).map(PedidoMongoEntity::toEntity);
    }

    @Override
    public List<Pedido> findAll() {
        return this.pedidoRepository.findAll()
                .stream()
                .map(PedidoMongoEntity::toEntity)
                .toList();
    }
}
