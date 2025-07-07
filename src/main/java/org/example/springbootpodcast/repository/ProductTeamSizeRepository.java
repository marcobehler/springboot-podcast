package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductTeamSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTeamSizeRepository extends JpaRepository<ProductTeamSize, Integer> {
    List<ProductTeamSize> findByPlusGuide(PlusGuide plusGuide);
}