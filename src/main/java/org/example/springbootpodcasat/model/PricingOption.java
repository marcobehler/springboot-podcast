package org.example.springbootpodcasat.model;

/**
 * Represents a pricing option for a product with various pricing factors.
 */
public class PricingOption {
    private String name;
    private String description;
    private String type; // e.g., "basic", "advanced"
    private double priceMultiplier; // Multiplier applied to base price
    private String currency;
    private double exchangeRate;
    private double regionalDiscountPercentage;
    private double taxRate;
    private double finalPrice; // Calculated final price after all adjustments

    public PricingOption() {
    }

    public PricingOption(String name, String description, String type, double priceMultiplier,
                        String currency, double exchangeRate, double regionalDiscountPercentage,
                        double taxRate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.priceMultiplier = priceMultiplier;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.regionalDiscountPercentage = regionalDiscountPercentage;
        this.taxRate = taxRate;
    }

    /**
     * Calculates the final price based on base price and all pricing factors.
     * 
     * @param basePrice The base price of the product
     * @return The calculated final price
     */
    public double calculateFinalPrice(double basePrice) {
        // Apply price multiplier (for basic vs advanced options)
        double price = basePrice * priceMultiplier;
        
        // Apply currency conversion
        price = price * exchangeRate;
        
        // Apply regional discount
        price = price * (1 - regionalDiscountPercentage / 100);
        
        // Apply tax
        price = price * (1 + taxRate / 100);
        
        // Round to 2 decimal places
        this.finalPrice = Math.round(price * 100.0) / 100.0;
        return this.finalPrice;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getRegionalDiscountPercentage() {
        return regionalDiscountPercentage;
    }

    public void setRegionalDiscountPercentage(double regionalDiscountPercentage) {
        this.regionalDiscountPercentage = regionalDiscountPercentage;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}