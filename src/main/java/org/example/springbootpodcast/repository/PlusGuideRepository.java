package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.PlusGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlusGuideRepository extends JpaRepository<PlusGuide, Integer> {
    Optional<PlusGuide> findBySlug(String slug);
}