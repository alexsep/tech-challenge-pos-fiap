package br.com.fiap.techchallenge.infrastructure.persistence.config;

import br.com.fiap.techchallenge.infrastructure.persistence.util.database.converter.DateToOffsetDateTimeConverter;
import br.com.fiap.techchallenge.infrastructure.persistence.util.database.converter.OffsetDateTimeToDateConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;


@Configuration
public class MongoConfig {

    @Bean
    @ConditionalOnMissingBean
    public MongoCustomConversions mongoConverter() {

        return new MongoCustomConversions(
                List.of(new DateToOffsetDateTimeConverter(),
                        new OffsetDateTimeToDateConverter()
                ));
    }
}
