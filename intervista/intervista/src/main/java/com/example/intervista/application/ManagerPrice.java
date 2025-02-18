package com.example.intervista.application;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author A.Mekni
 */
@Component
public class ManagerPrice {

private final PriceRepository repository;

    public ManagerPrice(PriceRepository repository) {
        this.repository = repository;
    }

    /**
     *
     * @param productId is id of the product what we are looking for
     * @param brandId
     * @param applicationDate
     * @return Price of product
     */
    public PriceModel getPriceOfProduct(Long productId, int brandId, LocalDateTime applicationDate) {
        return repository.findPriceProduct(productId,brandId,applicationDate);
    }
}
