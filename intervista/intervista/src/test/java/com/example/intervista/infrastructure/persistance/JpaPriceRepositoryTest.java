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
        // Initialisation de la date d'application pour les tests
        applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");
    }

    @Test
    public void testFindPriceProduct_Success() {
        // (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
        // Données de test : un prix pour un produit
        Price price = new Price(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-14T23:59:59"),1L,35455L,0 , 35.50, "EUR");
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenReturn(Arrays.asList(price));

        // Appel de la méthode
        PriceModel result = jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);

        // Vérification du résultat
        assertNotNull(result);
        assertEquals(35455L, result.getProductId());
        assertEquals(1,result.getBrandId());
        assertEquals(35.50, result.getPrice());
        assertEquals(0, result.getPriority());
        assertEquals(1L,result.getPriceList());
    }

    @Test
    public void testFindPriceProduct_PriceNotFound() {
        // Cas où aucun prix n'est trouvé pour le produit
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenReturn(Arrays.asList());  // Pas de prix retourné

        // Vérifier que l'exception est bien lancée
        PriceNotFoundException thrown = assertThrows(PriceNotFoundException.class, () -> {
            jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);
        });

        // Vérification du message d'exception
        assertEquals("No price found for productId: 35455", thrown.getMessage());
    }

    @Test
    public void testFindPriceProduct_UnexpectedError() {
        // Simuler une erreur inattendue (par exemple, problème de base de données)
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                35455L, 1, applicationDate, applicationDate))
                .thenThrow(new RuntimeException("Database error"));

        // Vérifier que l'exception inattendue est bien lancée
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            jpaPriceRepository.findPriceProduct(35455L, 1, applicationDate);
        });

        // Vérification du message d'exception
        assertEquals("An unexpected error occurred while retrieving the price", thrown.getMessage());
    }

}