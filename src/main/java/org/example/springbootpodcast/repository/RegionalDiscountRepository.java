package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.RegionalDiscount;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegionalDiscountRepository {

    @Query("SELECT r FROM RegionalDiscount r WHERE " +
           "(r.countries LIKE CONCAT('%', :country, '%') OR r.continents LIKE CONCAT('%', :continent, '%')) " +
           "AND (r.productId IS NULL OR r.productId = :productId) " +
           "AND (r.courseDiscountValidUntil IS NULL OR r.courseDiscountValidUntil >= :currentTime)")
    List<RegionalDiscount> findValidDiscounts(String country, String continent, String productId, Instant currentTime);

    @Query("SELECT r FROM RegionalDiscount r WHERE r.countries LIKE CONCAT('%', :country, '%') " +
           "AND r.productId = :productId ORDER BY r.percent DESC")
    Optional<RegionalDiscount> findByCountriesLikeAndProductId(String country, String productId);

    @Query("SELECT r FROM RegionalDiscount r WHERE r.continents LIKE CONCAT('%', :continent, '%') " +
           "AND r.productId = :productId ORDER BY r.percent DESC")
    Optional<RegionalDiscount> findByContinentsLikeAndProductId(String continent, String productId);

    @Find
    Optional<RegionalDiscount> findById(Integer id);
}
