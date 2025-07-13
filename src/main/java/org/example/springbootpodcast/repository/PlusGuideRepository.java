package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.PlusGuide;

import java.util.Optional;

@Repository
public interface PlusGuideRepository {
    @Find
    Optional<PlusGuide> findById(Integer id);
}
