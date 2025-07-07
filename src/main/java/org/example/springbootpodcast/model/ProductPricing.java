package org.example.springbootpodcast.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "PRODUCT_PRICING")
public class ProductPricing {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plus_guide_id")
    private PlusGuide plusGuide;

    @Column(name = "guide_id")
    private Integer guideId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id", nullable = false)
    private ProductPackage packageField;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_size_id", nullable = false)
    private ProductTeamSize teamSize;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "discounted_amount")
    private Integer discountedAmount;

    @ColumnDefault("'USD'")
    @Column(name = "currency", length = 4)
    private String currency;

    @ColumnDefault("1")
    @Column(name = "is_active")
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlusGuide getPlusGuide() {
        return plusGuide;
    }

    public void setPlusGuide(PlusGuide plusGuide) {
        this.plusGuide = plusGuide;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ProductPackage getPackageField() {
        return packageField;
    }

    public void setPackageField(ProductPackage packageField) {
        this.packageField = packageField;
    }

    public ProductTeamSize getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(ProductTeamSize teamSize) {
        this.teamSize = teamSize;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}