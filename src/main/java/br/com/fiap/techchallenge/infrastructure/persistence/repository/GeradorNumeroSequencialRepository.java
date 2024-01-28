package br.com.fiap.techchallenge.infrastructure.persistence.repository;

import br.com.fiap.techchallenge.infrastructure.persistence.entities.EntidadeEnum;
import br.com.fiap.techchallenge.infrastructure.persistence.entities.GeradorNumeroSequencial;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GeradorNumeroSequencialRepository extends MongoRepository<GeradorNumeroSequencial, String> {

    Optional<GeradorNumeroSequencial> findByEntidade(EntidadeEnum entidade);
}
