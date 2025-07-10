package org.example.springbootpodcast.service;

import org.example.springbootpodcast.dto.PackageDTO;
import org.example.springbootpodcast.dto.PricingOptionDTO;
import org.example.springbootpodcast.dto.ProductDTO;
import org.example.springbootpodcast.dto.TeamSizeDTO;
import org.example.springbootpodcast.model.*;
import org.example.springbootpodcast.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final PlusGuideRepository plusGuideRepository;
    private final ProductPackageRepository packageRepository;
    private final ProductTeamSizeRepository teamSizeRepository;
    private final ProductPricingRepository pricingRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TaxRateRepository taxRateRepository;
    private final RegionalDiscountRepository discountRepository;

    @Autowired
    public ProductService(PlusGuideRepository plusGuideRepository,
                          ProductPackageRepository packageRepository,
                          ProductTeamSizeRepository teamSizeRepository,
                          ProductPricingRepository pricingRepository,
                          ExchangeRateRepository exchangeRateRepository,
                          TaxRateRepository taxRateRepository,
                          RegionalDiscountRepository discountRepository) {
        this.plusGuideRepository = plusGuideRepository;
        this.packageRepository = packageRepository;
        this.teamSizeRepository = teamSizeRepository;
        this.pricingRepository = pricingRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.taxRateRepository = taxRateRepository;
        this.discountRepository = discountRepository;
    }

    /**
     * Fetches a PlusGuide product by its slug.
     */
    public ProductDTO getPlusGuideBySlug(Integer id) {
        PlusGuide plusGuide = plusGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlusGuide not found with id: " + id));

        return convertToProductDTO(plusGuide);
    }

    /**
     * Fetches a PlusGuide product by its slug with pricing adjusted for the user's country.
     */
    public ProductDTO getPlusGuideByIdWithCountryPricing(Integer id, String userCountry, String userCurrency) {
        PlusGuide plusGuide = plusGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlusGuide not found with id: " + id));

        ProductDTO productDTO = convertToProductDTO(plusGuide);
        
        // Apply country-specific pricing
        applyCountrySpecificPricing(productDTO, plusGuide, userCountry, userCurrency);
        
        return productDTO;
    }

    /**
     * Converts a PlusGuide entity to a ProductDTO.
     */
    private ProductDTO convertToProductDTO(PlusGuide plusGuide) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(plusGuide.getId());
        productDTO.setSlug(plusGuide.getSlug());
        productDTO.setTitle(plusGuide.getSlug()); // Using slug as title since PlusGuide doesn't have a title field
        productDTO.setDescription(plusGuide.getAsciiDoc()); // Using asciiDoc as description
        productDTO.setCurrency(plusGuide.getCurrency());
        productDTO.setBaseAmount(plusGuide.getAmount());
        productDTO.setDiscountedAmount(plusGuide.getDiscountedAmount());

        // Get packages for this PlusGuide
        List<ProductPackage> packages = packageRepository.findByPlusGuide(plusGuide);
        List<PackageDTO> packageDTOs = packages.stream()
                .map(this::convertToPackageDTO)
                .collect(Collectors.toList());
        productDTO.setPackages(packageDTOs);

        // Get team sizes for this PlusGuide
        List<ProductTeamSize> teamSizes = teamSizeRepository.findByPlusGuide(plusGuide);
        List<TeamSizeDTO> teamSizeDTOs = teamSizes.stream()
                .map(this::convertToTeamSizeDTO)
                .collect(Collectors.toList());
        productDTO.setTeamSizes(teamSizeDTOs);

        // Get pricing options for this PlusGuide
        List<ProductPricing> pricingOptions = pricingRepository.findByPlusGuideAndIsActiveTrue(plusGuide);
        List<PricingOptionDTO> pricingOptionDTOs = pricingOptions.stream()
                .map(pricing -> convertToPricingOptionDTO(pricing, packages, teamSizes))
                .collect(Collectors.toList());
        productDTO.setPricingOptions(pricingOptionDTOs);

        return productDTO;
    }

    /**
     * Converts a ProductPackage entity to a PackageDTO.
     */
    private PackageDTO convertToPackageDTO(ProductPackage packageEntity) {
        return new PackageDTO(
                packageEntity.getId(),
                packageEntity.getName(),
                packageEntity.getDescription()
        );
    }

    /**
     * Converts a ProductTeamSize entity to a TeamSizeDTO.
     */
    private TeamSizeDTO convertToTeamSizeDTO(ProductTeamSize teamSizeEntity) {
        return new TeamSizeDTO(
                teamSizeEntity.getId(),
                teamSizeEntity.getName(),
                teamSizeEntity.getDescription()
        );
    }

    /**
     * Converts a ProductPricing entity to a PricingOptionDTO.
     */
    private PricingOptionDTO convertToPricingOptionDTO(
            ProductPricing pricingEntity,
            List<ProductPackage> packages,
            List<ProductTeamSize> teamSizes) {
        
        PricingOptionDTO pricingOptionDTO = new PricingOptionDTO();
        pricingOptionDTO.setId(pricingEntity.getId());
        pricingOptionDTO.setTitle(pricingEntity.getTitle());
        pricingOptionDTO.setSubtitle(pricingEntity.getSubtitle());
        pricingOptionDTO.setAmount(pricingEntity.getAmount());
        pricingOptionDTO.setDiscountedAmount(pricingEntity.getDiscountedAmount());
        pricingOptionDTO.setCurrency(pricingEntity.getCurrency());

        // Find and set the package
        packages.stream()
                .filter(p -> p.getId().equals(pricingEntity.getPackageField().getId()))
                .findFirst()
                .ifPresent(p -> pricingOptionDTO.setPackageOption(convertToPackageDTO(p)));

        // Find and set the team size
        teamSizes.stream()
                .filter(t -> t.getId().equals(pricingEntity.getTeamSize().getId()))
                .findFirst()
                .ifPresent(t -> pricingOptionDTO.setTeamSize(convertToTeamSizeDTO(t)));

        // Set default values for pricing factors
        pricingOptionDTO.setExchangeRate(BigDecimal.ONE);
        pricingOptionDTO.setTaxRate(0.0);
        pricingOptionDTO.setRegionalDiscountPercentage(0.0);
        
        // Set final amount (without adjustments)
        Integer amount = pricingEntity.getDiscountedAmount() != null ? 
                pricingEntity.getDiscountedAmount() : pricingEntity.getAmount();
        pricingOptionDTO.setFinalAmount(new BigDecimal(amount));

        return pricingOptionDTO;
    }
    
    /**
     * Applies country-specific pricing to a ProductDTO.
     */
    private void applyCountrySpecificPricing(
            ProductDTO productDTO,
            PlusGuide plusGuide,
            String userCountry,
            String userCurrency) {
        
        // Get exchange rate
        BigDecimal exchangeRate = getExchangeRate(plusGuide.getCurrency(), userCurrency);
        
        // Get tax rate
        Double taxRate = getTaxRate(userCountry, plusGuide.getTaxCode());
        
        // Get regional discount
        Double discountPercentage = getRegionalDiscount(userCountry, plusGuide.getId().toString());
        
        // Apply pricing factors to each pricing option
        for (PricingOptionDTO pricingOption : productDTO.getPricingOptions()) {
            pricingOption.setExchangeRate(exchangeRate);
            pricingOption.setTaxRate(taxRate);
            pricingOption.setRegionalDiscountPercentage(discountPercentage);
            
            // Calculate final amount with all factors applied
            BigDecimal amount = pricingOption.getDiscountedAmount() != null ? 
                    new BigDecimal(pricingOption.getDiscountedAmount()) : 
                    new BigDecimal(pricingOption.getAmount());
            
            // Apply exchange rate
            amount = amount.multiply(exchangeRate);
            
            // Apply regional discount
            amount = amount.multiply(BigDecimal.ONE.subtract(
                    BigDecimal.valueOf(discountPercentage).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)));
            
            // Apply tax
            amount = amount.multiply(BigDecimal.ONE.add(
                    BigDecimal.valueOf(taxRate).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)));
            
            // Round to 2 decimal places
            amount = amount.setScale(2, RoundingMode.HALF_UP);
            
            pricingOption.setFinalAmount(amount);
            pricingOption.setCurrency(userCurrency);
        }
        
        // Update product currency
        productDTO.setCurrency(userCurrency);
    }

    /**
     * Gets the exchange rate from one currency to another.
     */
    private BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return BigDecimal.ONE;
        }
        
        return exchangeRateRepository.findLatestByFromAndTo(fromCurrency, toCurrency)
                .map(ExchangeRate::getRate)
                .orElse(BigDecimal.ONE); // Default to 1 if not found
    }

    /**
     * Gets the tax rate for a country and tax code.
     */
    private Double getTaxRate(String country, String taxCode) {
        return taxRateRepository.findValidTaxRate(country, taxCode, LocalDate.now())
                .map(TaxRate::getRate)
                .orElse(0.0); // Default to 0% if not found
    }

    /**
     * Gets the regional discount percentage for a country and product.
     */
    private Double getRegionalDiscount(String country, String productId) {
        // Try to find a country-specific discount
        Optional<RegionalDiscount> countryDiscount = 
                discountRepository.findFirstByCountriesContainingAndProductIdOrderByPercentDesc(country, productId);
        
        if (countryDiscount.isPresent()) {
            return countryDiscount.get().getPercent().doubleValue();
        }
        
        // If no country-specific discount, try to find a continent-specific discount
        String continent = mapCountryToContinent(country);
        Optional<RegionalDiscount> continentDiscount = 
                discountRepository.findFirstByContinentsContainingAndProductIdOrderByPercentDesc(continent, productId);
        
        return continentDiscount.map(discount -> discount.getPercent().doubleValue()).orElse(0.0);
    }

    /**
     * Maps a country code to a continent code.
     * This is a simplified implementation and would need to be expanded with actual mapping.
     */
    private String mapCountryToContinent(String country) {
        if (country == null) {
            return "";
        }
        
        if ("US".equals(country) || "CA".equals(country) || "MX".equals(country)) {
            return "NA"; // North America
        } else if ("GB".equals(country) || "DE".equals(country) || "FR".equals(country) || "IT".equals(country)) {
            return "EU"; // Europe
        } else if ("JP".equals(country) || "CN".equals(country) || "KR".equals(country)) {
            return "AS"; // Asia
        } else {
            return "OT"; // Other
        }
    }
}