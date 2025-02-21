package com.example.intervista.domain.port;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author A.Mekni
 */
public interface PriceRepository<T,R> {

    List<T> findPriceProduct(Long productId, int brandId, LocalDateTime applicationDate);
    T save(R price);
}
