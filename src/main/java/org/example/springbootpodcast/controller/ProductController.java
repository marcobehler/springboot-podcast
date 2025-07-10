package org.example.springbootpodcast.controller;

import jakarta.persistence.EntityManager;
import org.example.springbootpodcast.dto.ProductDTO;
import org.example.springbootpodcast.service.ProductService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * Controller for handling product-related requests.
 */
@Controller
public class ProductController {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final ProductService productService;

    public ProductController(EntityManager entityManager, JdbcTemplate jdbcTemplate, ProductService productService) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
        this.productService = productService;
    }



    /**
     * Handles the request for a specific product by slug.
     *
     * @param slug The slug of the product to display
     * @param country The country code for pricing (default: US)
     * @param currency The currency code for pricing (default: USD)
     * @param model The Spring MVC model
     * @return The name of the Thymeleaf template to render
     */
    @GetMapping("/products/{slug}")
    public String getProductBySlug(
            @PathVariable String slug,
            @RequestParam(required = false, defaultValue = "US") String country,
            @RequestParam(required = false, defaultValue = "USD") String currency,
            Model model) {

        // Fetch the product using the ProductService
        ProductDTO product = productService.getPlusGuideBySlugWithCountryPricing(slug, country, currency);

        // Add product to model
        model.addAttribute("product", product);
        model.addAttribute("country", country);
        model.addAttribute("currency", currency);

        return "product";
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
