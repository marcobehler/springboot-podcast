package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Integer> {
    
    @Query("SELECT t FROM TaxRate t WHERE t.country = :country AND t.taxCode = :taxCode " +
           "AND (t.validUntil IS NULL OR t.validUntil >= :currentDate)")
    Optional<TaxRate> findValidTaxRate(String country, String taxCode, LocalDate currentDate);
    
    Optional<TaxRate> findByCountryAndTaxCodeAndValidUntilIsNullOrValidUntilGreaterThanEqual(
            String country, String taxCode, LocalDate currentDate);
}