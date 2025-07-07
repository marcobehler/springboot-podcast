package org.example.springbootpodcast.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "REGIONAL_DISCOUNTS")
public class RegionalDiscount {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "percent", nullable = false)
    private Short percent;

    @Column(name = "countries")
    private String countries;

    @Column(name = "continents")
    private String continents;

    @Column(name = "course_discount_code", length = 12)
    private String courseDiscountCode;

    @Column(name = "course_discount_valid_until")
    private Instant courseDiscountValidUntil;

    @Column(name = "max_redemptions")
    private Integer maxRedemptions;

    @Column(name = "product_id")
    private String productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getPercent() {
        return percent;
    }

    public void setPercent(Short percent) {
        this.percent = percent;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getContinents() {
        return continents;
    }

    public void setContinents(String continents) {
        this.continents = continents;
    }

    public String getCourseDiscountCode() {
        return courseDiscountCode;
    }

    public void setCourseDiscountCode(String courseDiscountCode) {
        this.courseDiscountCode = courseDiscountCode;
    }

    public Instant getCourseDiscountValidUntil() {
        return courseDiscountValidUntil;
    }

    public void setCourseDiscountValidUntil(Instant courseDiscountValidUntil) {
        this.courseDiscountValidUntil = courseDiscountValidUntil;
    }

    public Integer getMaxRedemptions() {
        return maxRedemptions;
    }

    public void setMaxRedemptions(Integer maxRedemptions) {
        this.maxRedemptions = maxRedemptions;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}