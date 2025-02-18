package com.example.intervista.domain.port;


import com.example.intervista.domain.model.PriceModel;

import java.time.LocalDateTime;

/**
 * @author A.Mekni
 */
public interface PriceRepository {
    PriceModel findPriceProduct(
            Long productId,
            int brandId,
            LocalDateTime applicationDate);
}
