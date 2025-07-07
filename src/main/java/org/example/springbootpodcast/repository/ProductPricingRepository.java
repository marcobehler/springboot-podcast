package org.example.springbootpodcast.repository;

import org.example.springbootpodcast.model.PlusGuide;
import org.example.springbootpodcast.model.ProductPackage;
import org.example.springbootpodcast.model.ProductPricing;
import org.example.springbootpodcast.model.ProductTeamSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPricingRepository extends JpaRepository<ProductPricing, Integer> {
    List<ProductPricing> findByPlusGuide(PlusGuide plusGuide);
    
    List<ProductPricing> findByPlusGuideAndIsActiveTrue(PlusGuide plusGuide);
    
    Optional<ProductPricing> findByPlusGuideAndPackageFieldAndTeamSize(
            PlusGuide plusGuide, 
            ProductPackage packageField, 
            ProductTeamSize teamSize);
}