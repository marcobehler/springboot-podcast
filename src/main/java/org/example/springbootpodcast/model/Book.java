package org.example.springbootpodcast.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "category")
    private String category;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "toc", nullable = false)
    private String toc;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "discounted_amount")
    private Integer discountedAmount;

    @Column(name = "tags")
    private String tags;

    @Column(name = "pages")
    private Integer pages;

    @ColumnDefault("b'0'")
    @Column(name = "eap")
    private Boolean eap;

    @Column(name = "vcs")
    private String vcs;

    @ColumnDefault("(json_object())")
    @Column(name = "twitter_infos")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> twitterInfos;

    @Lob
    @Column(name = "sample_url")
    private String sampleUrl;

    @Lob
    @Column(name = "download_url")
    private String downloadUrl;

    @Lob
    @Column(name = "screencasts_download_url")
    private String screencastsDownloadUrl;

    @ColumnDefault("'USD'")
    @Column(name = "currency", length = 4)
    private String currency;

    @ColumnDefault("'ebook'")
    @Column(name = "tax_code")
    private String taxCode;

    @Lob
    @Column(name = "thumbnail")
    private String thumbnail;

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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    public String getToc() {
        return toc;
    }

    public void setToc(String toc) {
        this.toc = toc;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getEap() {
        return eap;
    }

    public void setEap(Boolean eap) {
        this.eap = eap;
    }

    public String getVcs() {
        return vcs;
    }

    public void setVcs(String vcs) {
        this.vcs = vcs;
    }

    public Map<String, Object> getTwitterInfos() {
        return twitterInfos;
    }

    public void setTwitterInfos(Map<String, Object> twitterInfos) {
        this.twitterInfos = twitterInfos;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getScreencastsDownloadUrl() {
        return screencastsDownloadUrl;
    }

    public void setScreencastsDownloadUrl(String screencastsDownloadUrl) {
        this.screencastsDownloadUrl = screencastsDownloadUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}