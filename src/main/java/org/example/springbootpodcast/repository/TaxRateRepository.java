package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.TaxRate;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TaxRateRepository {

    @Query("SELECT t FROM TaxRate t WHERE t.country = :country AND t.taxCode = :taxCode " +
           "AND (t.validUntil IS NULL OR t.validUntil >= :date)")
    Optional<TaxRate> findValidTaxRate(String country, String taxCode, LocalDate date);

    @Find
    Optional<TaxRate> findByCountryAndTaxCodeAndValidUntilIsNullOrValidUntilGreaterThanEqualOrderByValidUntilAsc(
            String country, String taxCode, LocalDate validUntil);

    @Find
    Optional<TaxRate> findById(Integer id);
}
