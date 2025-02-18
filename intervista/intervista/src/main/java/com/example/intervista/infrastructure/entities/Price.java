package com.example.intervista.infrastructure.entities;

import com.example.intervista.domain.model.PriceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author A.Mekni
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Price")
public class Price {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Price(Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long productId, Integer priority, Double price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public static Price toEntity(PriceModel price) {
        Price priceEntity = new Price();
        priceEntity.setId(price.getId());
        priceEntity.setBrandId(price.getBrandId());
        priceEntity.setCurr(price.getCurr());
        priceEntity.setEndDate(price.getEndDate());
        priceEntity.setStartDate(price.getStartDate());
        priceEntity.setPrice(price.getPrice());
        priceEntity.setPriceList(price.getPriceList());
        priceEntity.setProductId(price.getProductId());
        priceEntity.setPriority(price.getPriority());
        return priceEntity;
    }

    public PriceModel toModel() {
        return new PriceModel(id, brandId, startDate, endDate, priceList, productId, priority, price, curr);
    }
}
