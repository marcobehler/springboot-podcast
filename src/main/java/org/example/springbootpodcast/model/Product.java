package org.example.springbootpodcast.model;

import java.util.List;

/**
 * Represents a programming course product with its details and pricing options.
 */
public class Product {
    private Long id;
    private String title;
    private String description;
    private String plusGuideReference;
    private double basePrice;
    private List<PricingOption> pricingOptions;

    public Product() {
    }

    public Product(Long id, String title, String description, String plusGuideReference, 
                  double basePrice, List<PricingOption> pricingOptions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.plusGuideReference = plusGuideReference;
        this.basePrice = basePrice;
        this.pricingOptions = pricingOptions;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlusGuideReference() {
        return plusGuideReference;
    }

    public void setPlusGuideReference(String plusGuideReference) {
        this.plusGuideReference = plusGuideReference;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }
}