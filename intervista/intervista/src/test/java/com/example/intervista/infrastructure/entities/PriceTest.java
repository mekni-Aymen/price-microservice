package com.example.intervista.infrastructure.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import com.example.intervista.domain.model.PriceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceTest {

    private Price price;

    @BeforeEach
    public void setUp() {
        // Créer un objet Price avec des valeurs de test
        price = new Price(1, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-06-14T23:59:59"), 1L, 35455L, 1, 35.50, "EUR");
    }

    @Test
    public void testToEntity() {
        // Créer un PriceModel
        PriceModel priceModel = new PriceModel(1L, 1, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-06-14T23:59:59"), 1L, 35455L, 1, 35.50, "EUR");

        // Tester la conversion de PriceModel à Price
        Price priceEntity = Price.toEntity(priceModel);

        assertNotNull(priceEntity);
        assertEquals(priceEntity.getId(), priceModel.getId());
        assertEquals(priceEntity.getBrandId(), priceModel.getBrandId());
        assertEquals(priceEntity.getStartDate(), priceModel.getStartDate());
        assertEquals(priceEntity.getEndDate(), priceModel.getEndDate());
        assertEquals(priceEntity.getPriceList(), priceModel.getPriceList());
        assertEquals(priceEntity.getProductId(), priceModel.getProductId());
        assertEquals(priceEntity.getPrice(), priceModel.getPrice());
        assertEquals(priceEntity.getCurr(), priceModel.getCurr());
    }

    @Test
    public void testToModel() {
        // Tester la conversion de Price à PriceModel
        PriceModel priceModel = price.toModel();

        assertNotNull(priceModel);
        assertEquals(price.getId(), priceModel.getId());
        assertEquals(price.getBrandId(), priceModel.getBrandId());
        assertEquals(price.getStartDate(), priceModel.getStartDate());
        assertEquals(price.getEndDate(), priceModel.getEndDate());
        assertEquals(price.getPriceList(), priceModel.getPriceList());
        assertEquals(price.getProductId(), priceModel.getProductId());
        assertEquals(price.getPrice(), priceModel.getPrice());
        assertEquals(price.getCurr(), priceModel.getCurr());
    }

    @Test
    public void testConstructor() {
        // Tester le constructeur de Price
        Price priceTest = new Price(1, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-06-14T23:59:59"), 1L, 35455L, 1, 35.50, "EUR");

        assertNotNull(priceTest);
        assertEquals(1, priceTest.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), priceTest.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T23:59:59"), priceTest.getEndDate());
        assertEquals(1L, priceTest.getPriceList());
        assertEquals(35455L, priceTest.getProductId());
        assertEquals(1, priceTest.getPriority());
        assertEquals(35.50, priceTest.getPrice());
        assertEquals("EUR", priceTest.getCurr());
    }

    @Test
    public void testBuilder() {
        // Tester constrctor of Price with builder
        Price priceTest = Price.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T23:59:59"))
                .build();

        assertNotNull(priceTest);
        assertEquals(1, priceTest.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), priceTest.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T23:59:59"), priceTest.getEndDate());
        assertEquals(35455L, priceTest.getProductId());

    }
}
