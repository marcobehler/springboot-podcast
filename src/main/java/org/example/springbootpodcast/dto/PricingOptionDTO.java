package org.example.springbootpodcast.dto;

import java.math.BigDecimal;

public class PricingOptionDTO {
    private Integer id;
    private String title;
    private String subtitle;
    private Integer amount;
    private Integer discountedAmount;
    private String currency;
    private PackageDTO packageOption;
    private TeamSizeDTO teamSize;
    private BigDecimal exchangeRate;
    private Double taxRate;
    private Double regionalDiscountPercentage;
    private BigDecimal finalAmount;

    public PricingOptionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Integer discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PackageDTO getPackageOption() {
        return packageOption;
    }

    public void setPackageOption(PackageDTO packageOption) {
        this.packageOption = packageOption;
    }

    public TeamSizeDTO getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(TeamSizeDTO teamSize) {
        this.teamSize = teamSize;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getRegionalDiscountPercentage() {
        return regionalDiscountPercentage;
    }

    public void setRegionalDiscountPercentage(Double regionalDiscountPercentage) {
        this.regionalDiscountPercentage = regionalDiscountPercentage;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }
}