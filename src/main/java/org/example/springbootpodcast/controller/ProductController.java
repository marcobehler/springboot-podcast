package org.example.springbootpodcast.controller;

import jakarta.persistence.EntityManager;
import org.example.springbootpodcast.model.Product;
import org.example.springbootpodcast.model.PricingOption;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for handling product-related requests.
 */
@Controller
public class ProductController {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public ProductController(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

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

    /**
     * Displays the admin page for creating a new product.
     *
     * @param model The Spring MVC model
     * @return The name of the Thymeleaf template to render
     */
    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        // Add any necessary attributes to the model
        return "admin";
    }

    /**
     * Handles the submission of the product creation form.
     *
     * @param params The form parameters
     * @param redirectAttributes For flash messages
     * @return Redirect to the admin page
     */
    @PostMapping("/admin/create-product")
    public String createProduct(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
        try {
            // Extract basic product information
            String title = params.get("title");
            String description = params.get("description");
            String slug = params.get("slug");
            String asciiDoc = params.get("asciiDoc");
            int amount = Integer.parseInt(params.getOrDefault("basePrice", "0"));
            Integer discountedAmount = params.get("discountedAmount") != null && !params.get("discountedAmount").isEmpty() 
                ? Integer.parseInt(params.get("discountedAmount")) 
                : null;
            int totalEapSlots = Integer.parseInt(params.getOrDefault("totalEapSlots", "20"));
            int takenEapSlots = Integer.parseInt(params.getOrDefault("takenEapSlots", "0"));
            String type = params.getOrDefault("type", "EAP");
            String currency = params.getOrDefault("currency", "USD");
            String taxCode = params.getOrDefault("taxCode", "eservice");

            // 1. Insert into PLUS_GUIDES table
            String plusGuideSql = "INSERT INTO PLUS_GUIDES (slug, ascii_doc, amount, discounted_amount, total_eap_slots, taken_eap_slots, type, currency, tax_code) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(plusGuideSql, slug, asciiDoc, amount, discountedAmount, totalEapSlots, takenEapSlots, type, currency, taxCode);

            // Get the ID of the newly inserted plus guide
            Integer plusGuideId = jdbcTemplate.queryForObject(
                "SELECT ID FROM PLUS_GUIDES WHERE slug = ?", Integer.class, slug);

            // 2. Process packages
            // Count how many packages were submitted by finding the highest index
            int packageCount = 0;
            while (params.containsKey("packages[" + packageCount + "].name")) {
                packageCount++;
            }

            for (int i = 0; i < packageCount; i++) {
                String packageName = params.get("packages[" + i + "].name");
                String packageDescription = params.get("packages[" + i + "].description");

                if (packageName != null && !packageName.isEmpty()) {
                    String packageSql = "INSERT INTO PRODUCT_PACKAGES (name, description, plus_guide_id) VALUES (?, ?, ?)";
                    jdbcTemplate.update(packageSql, packageName, packageDescription, plusGuideId);
                }
            }

            // 3. Process team sizes
            // Count how many team sizes were submitted
            int teamSizeCount = 0;
            while (params.containsKey("teamSizes[" + teamSizeCount + "].name")) {
                teamSizeCount++;
            }

            for (int i = 0; i < teamSizeCount; i++) {
                String teamSizeName = params.get("teamSizes[" + i + "].name");
                String teamSizeDescription = params.get("teamSizes[" + i + "].description");

                if (teamSizeName != null && !teamSizeName.isEmpty()) {
                    String teamSizeSql = "INSERT INTO PRODUCT_TEAM_SIZES (name, description, plus_guide_id) VALUES (?, ?, ?)";
                    jdbcTemplate.update(teamSizeSql, teamSizeName, teamSizeDescription, plusGuideId);
                }
            }

            // 4. Process pricing options
            // Count how many pricing options were submitted
            int pricingCount = 0;
            while (params.containsKey("pricing[" + pricingCount + "].packageId")) {
                pricingCount++;
            }

            for (int i = 0; i < pricingCount; i++) {
                String packageIdStr = params.get("pricing[" + i + "].packageId");
                String teamSizeIdStr = params.get("pricing[" + i + "].teamSizeId");
                String amountStr = params.get("pricing[" + i + "].amount");
                String discountedAmountStr = params.get("pricing[" + i + "].discountedAmount");
                String pricingTitle = params.get("pricing[" + i + "].title");
                String pricingSubtitle = params.get("pricing[" + i + "].subtitle");

                if (packageIdStr != null && !packageIdStr.isEmpty() && 
                    teamSizeIdStr != null && !teamSizeIdStr.isEmpty() && 
                    amountStr != null && !amountStr.isEmpty()) {

                    // Get the actual package and team size IDs from the database
                    String packageName = params.get("packages[" + packageIdStr + "].name");
                    String teamSizeName = params.get("teamSizes[" + teamSizeIdStr + "].name");

                    Integer packageId = jdbcTemplate.queryForObject(
                        "SELECT ID FROM PRODUCT_PACKAGES WHERE name = ? AND plus_guide_id = ?", 
                        Integer.class, packageName, plusGuideId);

                    Integer teamSizeId = jdbcTemplate.queryForObject(
                        "SELECT ID FROM PRODUCT_TEAM_SIZES WHERE name = ? AND plus_guide_id = ?", 
                        Integer.class, teamSizeName, plusGuideId);

                    int pricingAmount = Integer.parseInt(amountStr);
                    Integer pricingDiscountedAmount = discountedAmountStr != null && !discountedAmountStr.isEmpty() 
                        ? Integer.parseInt(discountedAmountStr) 
                        : null;

                    String pricingSql = "INSERT INTO PRODUCT_PRICING (plus_guide_id, package_id, team_size_id, amount, discounted_amount, title, subtitle, currency) " +
                                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    jdbcTemplate.update(pricingSql, plusGuideId, packageId, teamSizeId, pricingAmount, 
                                       pricingDiscountedAmount, pricingTitle, pricingSubtitle, currency);
                }
            }

            redirectAttributes.addFlashAttribute("message", "Product created successfully!");
            return "redirect:/admin";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating product: " + e.getMessage());
            return "redirect:/admin";
        }
    }
}
