package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductPackage;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPackageRepository {
    @Find
    List<ProductPackage> findByPlusGuide(PlusGuide plusGuide);

    @Find
    Optional<ProductPackage> findById(Integer id);
}
