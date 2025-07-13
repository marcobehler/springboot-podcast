package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.ExchangeRate;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository {

    @Query("SELECT e FROM ExchangeRate e WHERE e.from = :from AND e.to = :to ORDER BY e.validAt DESC")
    Optional<ExchangeRate> findLatestByFromAndTo(String from, String to);

    @Find
    Optional<ExchangeRate> findFirstByFromAndToOrderByValidAtDesc(String from, String to);

    @Find
    Optional<ExchangeRate> findById(Integer id);
}
