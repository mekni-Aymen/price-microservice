package com.example.intervista.infrastructure.persistance;

import com.example.intervista.infrastructure.entities.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpringDataJpaPriceRepositoryTest {

    @Autowired
    private SpringDataJpaPriceRepository springDataJpaPriceRepository;

    private LocalDateTime applicationDate_14_10, applicationDate_14_16, applicationDate_12_10;

    @BeforeEach
    public void setUp() {
        // Initialisation de la date d'application pour les tests
        applicationDate_14_10 = LocalDateTime.parse("2020-06-14T10:00:00");
        applicationDate_12_10 = LocalDateTime.parse("2020-06-12T10:00:00");
        applicationDate_14_16 = LocalDateTime.parse("2020-06-14T16:00:00");
    }

    @Test
    public void testFindByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual() {

        // Perform a query with a product and a date that matches price
        List<Price> result = springDataJpaPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate_14_10, applicationDate_14_10);

        // Check that the result contains the expected element
        assertNotNull(result);
        assertEquals(1, result.size());  // Only one result expected
        assertEquals(35.50, result.get(0).getPrice());  // Check that the price is correct
        assertEquals(35455L, result.get(0).getProductId());  // Check that the product ID is correct
    }

    @Test
    public void testFindByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual_NoMatch() {

        // Perform a query with a date that does not match any price
        List<Price> result = springDataJpaPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate_12_10, applicationDate_12_10);

        // Check that the result is empty (no price found for this date)
        assertNotNull(result);
        assertTrue(result.isEmpty());  // No results expected
    }

    @Test
    public void testFindByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual_MultipleResults() {

        // Perform a query with a date that matches both prices
        List<Price> result = springDataJpaPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate_14_16, applicationDate_14_16);


        assertNotNull(result);                                                      // Check that two results are returned
        Assertions.assertThat(result).isNotEmpty();                                 // Check that the result not empty.
        assertEquals(2, result.size());                                     // Two expected results
        assertTrue(result.stream().anyMatch(price -> price.getPrice() == 35.50));   // Check that the expected results are correct
        assertTrue(result.stream().anyMatch(price -> price.getPrice() == 25.45));

    }

    @Test
    public void testFindAll_ValueEqual_4_Success() {
        // Perform a query with a date that matches both prices
        List<Price> result = springDataJpaPriceRepository.findAll();

        assertNotNull(result);                          // Check that the result not null.
        Assertions.assertThat(result).isNotEmpty();     // Check that the result not empty.
        assertEquals(4, result.size());         // Check that four results are returned
    }
}
