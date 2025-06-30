package org.example.springbootpodcast.controller;

import org.example.springbootpodcast.model.Product;
import org.example.springbootpodcast.model.PricingOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for handling product-related requests.
 */
@Controller
public class ProductController {

    // Mock exchange rates (currency to USD)
    private final Map<String, Double> exchangeRates = new HashMap<String, Double>() {{
        put("USD", 1.0);
        put("EUR", 0.85);
        put("GBP", 0.75);
        put("JPY", 110.0);
    }};

    // Mock regional discounts (percentage)
    private final Map<String, Double> regionalDiscounts = new HashMap<String, Double>() {{
        put("US", 0.0);
        put("EU", 5.0);
        put("UK", 7.5);
        put("JP", 10.0);
    }};

    // Mock tax rates (percentage)
    private final Map<String, Double> taxRates = new HashMap<String, Double>() {{
        put("US", 7.5);
        put("EU", 20.0);
        put("UK", 20.0);
        put("JP", 10.0);
    }};

    /**
     * Handles the request for the product page.
     *
     * @param productId The ID of the product to display
     * @param region The region code for pricing (default: US)
     * @param currency The currency code for pricing (default: USD)
     * @param model The Spring MVC model
     * @return The name of the Thymeleaf template to render
     */
    @GetMapping("/product")
    public String getProductPage(
            @RequestParam(required = false, defaultValue = "1") Long productId,
            @RequestParam(required = false, defaultValue = "US") String region,
            @RequestParam(required = false, defaultValue = "USD") String currency,
            Model model) {

        // Get exchange rate for the requested currency (default to 1.0 if not found)
        double exchangeRate = exchangeRates.getOrDefault(currency, 1.0);
        
        // Get regional discount for the requested region (default to 0.0 if not found)
        double regionalDiscount = regionalDiscounts.getOrDefault(region, 0.0);
        
        // Get tax rate for the requested region (default to 0.0 if not found)
        double taxRate = taxRates.getOrDefault(region, 0.0);

        // Create mock product based on productId
        Product product = createMockProduct(productId, currency, exchangeRate, regionalDiscount, taxRate);

        // Calculate final prices for all pricing options
        for (PricingOption option : product.getPricingOptions()) {
            option.calculateFinalPrice(product.getBasePrice());
        }

        // Add product to model
        model.addAttribute("product", product);
        model.addAttribute("region", region);
        model.addAttribute("currency", currency);

        return "product";
    }

    /**
     * Creates a mock product with the given ID and pricing factors.
     *
     * @param productId The ID of the product
     * @param currency The currency code
     * @param exchangeRate The exchange rate
     * @param regionalDiscount The regional discount percentage
     * @param taxRate The tax rate percentage
     * @return A mock product
     */
    private Product createMockProduct(Long productId, String currency, double exchangeRate, 
                                     double regionalDiscount, double taxRate) {
        // For this example, we'll create a single mock product regardless of the ID
        String title = "Modern Java Programming Masterclass";
        String description = "A comprehensive course covering Java fundamentals to advanced topics " +
                "including Spring Boot, microservices, and cloud deployment. Perfect for beginners " +
                "and intermediate developers looking to enhance their Java skills.";
        String plusGuideReference = "java-programming-guide-2023";
        double basePrice = 99.99;

        // Create pricing options
        List<PricingOption> pricingOptions = new ArrayList<>();

        // Basic option
        PricingOption basicOption = new PricingOption(
                "Basic Course",
                "Access to all core lectures and exercises",
                "basic",
                1.0, // Standard price multiplier
                currency,
                exchangeRate,
                regionalDiscount,
                taxRate
        );
        pricingOptions.add(basicOption);

        // Advanced option
        PricingOption advancedOption = new PricingOption(
                "Advanced Course",
                "Everything in Basic plus advanced topics, projects, and personal code reviews",
                "advanced",
                1.75, // 75% more expensive than basic
                currency,
                exchangeRate,
                regionalDiscount,
                taxRate
        );
        pricingOptions.add(advancedOption);

        // Premium option
        PricingOption premiumOption = new PricingOption(
                "Premium Course",
                "Everything in Advanced plus 1-on-1 mentoring sessions and lifetime access to updates",
                "premium",
                2.5, // 150% more expensive than basic
                currency,
                exchangeRate,
                regionalDiscount + 2.5, // Additional discount for premium
                taxRate
        );
        pricingOptions.add(premiumOption);

        return new Product(productId, title, description, plusGuideReference, basePrice, pricingOptions);
    }
}