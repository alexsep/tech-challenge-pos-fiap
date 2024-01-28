package br.com.fiap.techchallenge.infrastructure.persistence.util;

import br.com.fiap.techchallenge.infrastructure.persistence.entities.EntidadeEnum;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.GeradorNumeroSequencial;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.GeradorNumeroSequencialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeradorNumeroSequencialService {

    private final GeradorNumeroSequencialRepository geradorNumeroSequencialRepository;

    public GeradorNumeroSequencialService(GeradorNumeroSequencialRepository geradorNumeroSequencialRepository) {
        this.geradorNumeroSequencialRepository = geradorNumeroSequencialRepository;
    }


    public int buscarProximoNumeroSequencial(EntidadeEnum entidadeEnum) {

        Optional<GeradorNumeroSequencial> numeroSequencial =
                this.geradorNumeroSequencialRepository.findByEntidade(entidadeEnum);

        if (numeroSequencial.isPresent()) {
            var numeroSequencialAtual = numeroSequencial.get();

            final var proximoNumero = numeroSequencialAtual.incrementarNumero();

            final var numeroSequencialParaAtualizacao = GeradorNumeroSequencial.builder()
                    .id(numeroSequencialAtual.getId())
                    .proximoNumero(proximoNumero)
                    .entidade(numeroSequencialAtual.getEntidade())
                    .build();

            this.geradorNumeroSequencialRepository.save(numeroSequencialParaAtualizacao);

            return numeroSequencialAtual.getProximoNumero();

        }

        final var novoSequencial = GeradorNumeroSequencial.builder()
                .proximoNumero(1)
                .entidade(entidadeEnum)
                .build();


        this.geradorNumeroSequencialRepository.save(novoSequencial);
        return 1;

    }
}
