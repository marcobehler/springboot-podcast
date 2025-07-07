package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPackageRepository extends JpaRepository<ProductPackage, Integer> {
    List<ProductPackage> findByPlusGuide(PlusGuide plusGuide);
}