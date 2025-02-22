package com.example.intervista.infrastructure.persistance.data.Jpa;

import com.example.intervista.infrastructure.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author A.Mekni
 */

public interface SpringDataJpaPriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long productId, int brandId, LocalDateTime startDate, LocalDateTime endDate);
}
