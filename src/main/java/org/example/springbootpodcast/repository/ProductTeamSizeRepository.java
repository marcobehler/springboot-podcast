package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductTeamSize;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTeamSizeRepository {
    @Find
    List<ProductTeamSize> findByPlusGuide(PlusGuide plusGuide);

    @Find
    Optional<ProductTeamSize> findById(Integer id);
}
