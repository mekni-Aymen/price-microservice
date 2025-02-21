package com.example.intervista.infrastructure.persistance.data.Jpa;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.DPrice;
import com.example.intervista.infrastructure.entities.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author A.Mekni
 */
@Repository("JpaPriceRepository")
@Slf4j
public class JpaPriceRepository implements PriceRepository<PriceModel,Price> {

    private final SpringDataJpaPriceRepository repository;

    public JpaPriceRepository(SpringDataJpaPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PriceModel> findPriceProduct(Long productId, int brandId, LocalDateTime applicationDate) {

        return repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                productId, brandId, applicationDate, applicationDate)
                .stream()
                .map(Price::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PriceModel save(Price price) {
        return Stream.of(repository.save(price)).map(Price::toModel).findAny().get();
    }

}
