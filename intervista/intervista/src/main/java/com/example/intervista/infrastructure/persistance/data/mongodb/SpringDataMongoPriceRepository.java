package com.example.intervista.infrastructure.persistance.data.mongodb;

import com.example.intervista.infrastructure.entities.DPrice;
import com.example.intervista.infrastructure.entities.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author A.Mekni
 */
public interface SpringDataMongoPriceRepository extends MongoRepository<DPrice,Long>{
    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long productId, int brandId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    <S extends DPrice> S insert(S s);
}
