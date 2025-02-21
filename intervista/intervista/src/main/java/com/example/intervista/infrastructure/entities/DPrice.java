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

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private Long priceList;

    @Column(name = "product_id")
    private Long productId;

    @JsonIgnore
    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @Column(name = "curr")
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
