package br.com.fiap.techchallenge.infrastructure.persistence.util.database.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

@Component
public class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

    @Override
    public Date convert(OffsetDateTime offsetDateTime) {
        if (Objects.isNull(offsetDateTime)) {
            return null;
        }

        long epochMilli = offsetDateTime.toInstant().toEpochMilli();

        return new Date(epochMilli);

    }
}
