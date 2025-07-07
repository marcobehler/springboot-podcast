package org.example.springbootpodcast.dto;

import java.util.List;

public class ProductDTO {
    private Integer id;
    private String slug;
    private String title;
    private String description;
    private String currency;
    private Integer baseAmount;
    private Integer discountedAmount;
    private List<PackageDTO> packages;
    private List<TeamSizeDTO> teamSizes;
    private List<PricingOptionDTO> pricingOptions;

    public ProductDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Integer discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public List<PackageDTO> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageDTO> packages) {
        this.packages = packages;
    }

    public List<TeamSizeDTO> getTeamSizes() {
        return teamSizes;
    }

    public void setTeamSizes(List<TeamSizeDTO> teamSizes) {
        this.teamSizes = teamSizes;
    }

    public List<PricingOptionDTO> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOptionDTO> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }
}