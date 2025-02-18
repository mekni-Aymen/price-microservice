package com.example.intervista.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author A.Mekni
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceModel {
    @JsonIgnore
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonIgnore
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String curr;

    public PriceModel(Integer brandId, String startDate, String endDate, Long priceList,
                      Long productId, Integer priority, double price, String curr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.brandId = brandId;
        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;

    }
}
