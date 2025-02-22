package com.example.intervista.application;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.DPrice;
import com.example.intervista.infrastructure.entities.Price;
import com.example.intervista.infrastructure.exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * @author A.Mekni
 */
@Component
@Slf4j
public class ManagerPrice {

private final PriceRepository<PriceModel, Price> repository;

private final PriceRepository<PriceModel, DPrice> repositoryData;

    public ManagerPrice(PriceRepository<PriceModel, Price> repository, PriceRepository<PriceModel, DPrice> repositoryData) {
        this.repository = repository;
        this.repositoryData = repositoryData;
    }


    /**
     *
     * @param productId is id of the product what we are looking for
     * @param brandId
     * @param applicationDate
     * @return Price of product
     */
    public PriceModel getPriceOfProduct(Long productId, int brandId, LocalDateTime applicationDate) {

        try {
            List<PriceModel> prices = repository.findPriceProduct(productId,brandId,applicationDate);

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


    /**
     * In case of mongodb database
     * @param productId is id of the product what we are looking for
     * @param brandId
     * @param applicationDate
     * @return Price of product
     */
    public PriceModel getPriceOfProductMongo(Long productId, int brandId, LocalDateTime applicationDate) {

        try {
            List<PriceModel> prices = repositoryData.findPriceProduct(productId,brandId,applicationDate);

            return prices.stream()
                    //.max((p1, p2) -> Integer.compare(p1.getPriority(), p2.getPriority()))
                    .max(Comparator.comparingInt(PriceModel::getPriority))
                    .orElseThrow(() -> new PriceNotFoundException("No price found for productId: " + productId));
        } catch (PriceNotFoundException e) {
            log.error("No price found for productId: "+productId);
            throw e;
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new RuntimeException("An unexpected error occurred while retrieving the price", e);
        }
    }

}
