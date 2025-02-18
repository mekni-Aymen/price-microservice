package com.example.intervista.infrastructure.persistance;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.Price;
import com.example.intervista.infrastructure.exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author A.Mekni
 */
@Repository
@Slf4j
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataJpaPriceRepository repository;

    public JpaPriceRepository(SpringDataJpaPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceModel findPriceProduct(Long productId, int brandId, LocalDateTime applicationDate) {

        try {
            List<PriceModel> prices = repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                    productId, brandId, applicationDate, applicationDate)
                    .stream()
                    .map(Price::toModel)
                    .collect(Collectors.toList());

            return prices.stream()
                    //.max((p1, p2) -> Integer.compare(p1.getPriority(), p2.getPriority()))
                    .max(Comparator.comparingInt(PriceModel::getPriority))
                    .orElseThrow(() -> new PriceNotFoundException("No price found for productId: " + productId));
        } catch (PriceNotFoundException e) {
            log.error("No price found for productId: "+productId);
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving the price", e);
        }
    }

}
