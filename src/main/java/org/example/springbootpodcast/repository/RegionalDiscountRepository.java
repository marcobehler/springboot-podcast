package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.RegionalDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegionalDiscountRepository extends JpaRepository<RegionalDiscount, Integer> {
    
    @Query("SELECT r FROM RegionalDiscount r WHERE " +
           "(r.countries LIKE %:country% OR r.continents LIKE %:continent%) " +
           "AND (r.productId IS NULL OR r.productId = :productId) " +
           "AND (r.courseDiscountValidUntil IS NULL OR r.courseDiscountValidUntil >= :currentTime)")
    List<RegionalDiscount> findValidDiscounts(String country, String continent, String productId, Instant currentTime);
    
    Optional<RegionalDiscount> findFirstByCountriesContainingAndProductIdOrderByPercentDesc(String country, String productId);
    
    Optional<RegionalDiscount> findFirstByContinentsContainingAndProductIdOrderByPercentDesc(String continent, String productId);
}