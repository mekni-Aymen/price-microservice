package com.example.intervista.infrastructure.persistance.data.mongodb;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.DPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author A.Mekni
 */
@Repository("MongodDBPriceRepository")
@Slf4j
public class MongodDBPriceRepository implements PriceRepository<PriceModel,DPrice> {

    private final SpringDataMongoPriceRepository<DPrice> repository;

    public MongodDBPriceRepository(SpringDataMongoPriceRepository<DPrice> repository) {
        this.repository = repository;
    }


    @Override
    public List<PriceModel> findPriceProduct(Long productId, int brandId, LocalDateTime applicationDate) {

        return repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                productId, brandId, applicationDate, applicationDate)
                .stream()
                .map(DPrice::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PriceModel save(DPrice price){
        return Stream.of(repository.insert(price)).map(DPrice::toModel).findAny().get();
    }

}
