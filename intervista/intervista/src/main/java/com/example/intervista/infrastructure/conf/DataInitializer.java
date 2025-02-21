package com.example.intervista.infrastructure.conf;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.DPrice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author A.Mekni
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final PriceRepository<PriceModel,DPrice> repository;

    public DataInitializer(@Qualifier("MongodDBPriceRepository") PriceRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new DPrice(1, LocalDateTime.parse( "2020-06-14T00:00:00"), LocalDateTime.parse( "2020-12-31T23:59:59"), 1L, 35455L, 0, 35.50, "EUR"),
                new DPrice(1, LocalDateTime.parse( "2020-06-14T15:00:00"), LocalDateTime.parse( "2020-06-14T18:30:00"), 2L, 35455L, 1, 25.45, "EUR"),
                new DPrice(1, LocalDateTime.parse( "2020-06-15T00:00:00"), LocalDateTime.parse( "2020-06-15T11:00:00"), 3L, 35455L, 1, 30.50, "EUR"),
                new DPrice(1, LocalDateTime.parse( "2020-06-15T16:00:00"), LocalDateTime.parse( "2020-12-31T23:59:59"), 4L, 35455L, 1, 38.95, "EUR")
        ).forEach(repository::save);
    }
}
