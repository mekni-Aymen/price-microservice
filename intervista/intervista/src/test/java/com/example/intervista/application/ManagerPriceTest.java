package com.example.intervista.application;

import com.example.intervista.domain.model.PriceModel;
import com.example.intervista.domain.port.PriceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author A.Mekni
 */
class ManagerPriceTest {

    private ManagerPrice managerPrice;
    private PriceRepository repository;

    List<PriceModel> prices;

    PriceModel priceModel1;
    PriceModel priceModel2;
    PriceModel priceModel3;
    PriceModel priceModel4;


    @BeforeEach
    void setUp() {
        repository = mock(PriceRepository.class);
        //managerPrice = new ManagerPrice(repository, repositoryData);


        priceModel1 = new PriceModel(1, "2020-06-14 00:00:00", "2020-12-31 23:59:59", 1L, 35455L, 0, 35.50, "EUR");
        priceModel2 = new PriceModel(1, "2020-06-14 15:00:00", "2020-06-14 18:30:00", 2L, 35455L, 1, 25.45, "EUR");
        priceModel3 = new PriceModel(1, "2020-06-15 00:00:00", "2020-06-15 11:00:00", 3L, 35455L, 1, 30.50, "EUR");
        priceModel4 = new PriceModel(1, "2020-06-15 16:00:00", "2020-12-31 23:59:59", 4L, 35455L, 1, 38.95, "EUR");

        prices = new ArrayList<>();
        prices.add(priceModel1);
        prices.add(priceModel2);
        prices.add(priceModel3);
        prices.add(priceModel4);
    }

    @Test
    void testPriceProduct_In14At10am() {
        String date = "2020-06-14T10:00:00";

        Long productId = 35455L;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.parse(date);
        List<PriceModel> testList= new ArrayList<>();
        testList.add(priceModel1);
        testList.add(priceModel2);
        when(repository.findPriceProduct(productId, brandId, applicationDate)).thenReturn(prices);

        PriceModel priceOfProduct = managerPrice.getPriceOfProduct(productId, brandId, applicationDate);
        System.out.println(priceOfProduct.getPrice());
//        Assert.assertTrue(priceOfProduct);
        Assertions.assertThat(priceOfProduct.getPrice()).isEqualTo(priceModel1.getPrice());
        //Assertions.assertThat(priceOfProduct.getPrice()).isNotEqualTo(priceModel2.getPrice());
    }

    @Test
    void testPriceProduct_In14At4pm() {
        String date = "2020-06-14T16:00:00";

        Long productId = 35455L;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.parse(date);

        when(repository.findPriceProduct(productId, brandId, applicationDate)).thenReturn(prices);

        PriceModel priceOfProduct = managerPrice.getPriceOfProduct(productId, brandId, applicationDate);

        Assertions.assertThat(priceOfProduct.getPrice()).isEqualTo(priceModel2.getPrice());
        Assertions.assertThat(priceOfProduct.getPrice()).isNotEqualTo(priceModel1.getPrice());
    }

    @Test
    void testPriceProduct_In15At10am() {
        String date = "2020-06-14T10:00:00";

        Long productId = 35455L;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.parse(date);

        when(repository.findPriceProduct(productId, brandId, applicationDate)).thenReturn(prices);

        PriceModel priceOfProduct = managerPrice.getPriceOfProduct(productId, brandId, applicationDate);

        Assertions.assertThat(priceOfProduct.getPrice()).isEqualTo(priceModel3.getPrice());
        Assertions.assertThat(priceOfProduct.getPrice()).isNotEqualTo(priceModel1.getPrice());
    }

    @Test
    void testPriceProduct_In15At6pm() {
        String date = "2020-06-14T18:00:00";

        Long productId = 35455L;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.parse(date);

        when(repository.findPriceProduct(productId, brandId, applicationDate)).thenReturn(prices);

        PriceModel priceOfProduct = managerPrice.getPriceOfProduct(productId, brandId, applicationDate);

        Assertions.assertThat(priceOfProduct.getPrice()).isEqualTo(priceModel4.getPrice());
        Assertions.assertThat(priceOfProduct.getPrice()).isNotEqualTo(priceModel1.getPrice());
    }
}