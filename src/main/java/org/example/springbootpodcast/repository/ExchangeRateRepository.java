package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    
    @Query("SELECT e FROM ExchangeRate e WHERE e.from = :from AND e.to = :to ORDER BY e.validAt DESC")
    Optional<ExchangeRate> findLatestByFromAndTo(String from, String to);
    
    Optional<ExchangeRate> findFirstByFromAndToOrderByValidAtDesc(String from, String to);
}