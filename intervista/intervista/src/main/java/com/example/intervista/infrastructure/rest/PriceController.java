package com.example.intervista.infrastructure.rest;

import com.example.intervista.application.ManagerPrice;
import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.infrastructure.exception.ParameterNotFoundExcption;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author A.Mekni
 */

@AllArgsConstructor
@RestController
public class PriceController {

    private final ManagerPrice managerPrice;

    @GetMapping("/prices")
    public ResponseEntity<?> getPrice(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Integer brandId){

        if (applicationDate == null || productId == null || brandId == null){
            throw new ParameterNotFoundExcption("Erreur: Missing Parameter");
        }
        PriceModel priceOfProduct = managerPrice.getPriceOfProduct(productId, brandId, applicationDate);
        return ResponseEntity.status(HttpStatus.OK).body(priceOfProduct);
    }

    @GetMapping("/data/prices")
    public ResponseEntity<?> getPriceMongo(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Integer brandId){

        if (applicationDate == null || productId == null || brandId == null){
            throw new ParameterNotFoundExcption("Erreur: Missing Parameter");
        }
        PriceModel priceOfProduct = managerPrice.getPriceOfProductMongo(productId, brandId, applicationDate);
        return ResponseEntity.status(HttpStatus.OK).body(priceOfProduct);
    }
}

