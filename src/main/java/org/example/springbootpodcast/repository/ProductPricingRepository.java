package org.example.springbootpodcast.repository;

import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductPackage;
import org.example.springbootpodcast.model.ProductPricing;
import org.example.springbootpodcast.model.ProductTeamSize;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPricingRepository {
    @Find
    List<ProductPricing> findByPlusGuide(PlusGuide plusGuide);

    @Find
    List<ProductPricing> findByPlusGuideAndIsActiveTrue(PlusGuide plusGuide);

    @Find
    Optional<ProductPricing> findByPlusGuideAndPackageFieldAndTeamSize(
            PlusGuide plusGuide, 
            ProductPackage packageField, 
            ProductTeamSize teamSize);

    @Find
    Optional<ProductPricing> findById(Integer id);
}
