package com.example.intervista.infrastructure.entities;

import com.example.intervista.domain.model.PriceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author A.Mekni
 */

@Document(collection = "Price")
public class DPrice {

    @Id
    private String id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String curr;

    public DPrice(Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long productId, Integer priority, Double price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }
    public PriceModel toModel() {
        return new PriceModel( brandId, startDate, endDate, priceList, productId, priority, price, curr);
    }
}
