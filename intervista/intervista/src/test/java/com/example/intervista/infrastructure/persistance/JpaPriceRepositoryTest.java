package com.example.intervista.infrastructure.persistance;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import com.example.intervista.infrastructure.entities.Price;
import com.example.intervista.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author A.Mekni
 */

@ExtendWith(MockitoExtension.class)
class JpaPriceRepositoryTest {
    @Mock
    private SpringDataJpaPriceRepository repository;

    @InjectMocks
    private JpaPriceRepository jpaPriceRepository;

    LocalDateTime applicationDate;

    @BeforeEach
    public void setUp() {
        // Initializing the application date for testing
        applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");
    }

    @Test
    public void testFindPriceProduct_Success() {
        // Test data: one price for one product
        Price price = new Price(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-14T23:59:59"),1L,35455L,0 , 35.50, "EUR");
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenReturn(Arrays.asList(price));

        // Calling the method
        PriceModel result = jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);

        // Checking the result
        assertNotNull(result);
        assertEquals(35455L, result.getProductId());
        assertEquals(1,result.getBrandId());
        assertEquals(35.50, result.getPrice());
        assertEquals(0, result.getPriority());
        assertEquals(1L,result.getPriceList());
    }

    @Test
    public void testFindPriceProduct_PriceNotFound() {
        // Case where no price is found for the product
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenReturn(Arrays.asList());  // No price returned

        // Check that the exception is thrown correctly
        PriceNotFoundException thrown = assertThrows(PriceNotFoundException.class, () -> {
            jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);
        });

        // Checking the exception message
        assertEquals("No price found for productId: 35455", thrown.getMessage());
    }

    @Test
    public void testFindPriceProduct_UnexpectedError() {
        // Simulate an unexpected error (e.g. database problem)
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenThrow(new RuntimeException("Database error"));

        // Check that the unexpected exception is thrown
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);
        });

        //Checking the exception message
        assertEquals("An unexpected error occurred while retrieving the price", thrown.getMessage());
    }

}