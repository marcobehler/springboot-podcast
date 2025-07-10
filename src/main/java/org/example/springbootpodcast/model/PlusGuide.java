package org.example.springbootpodcast.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "PLUS_GUIDES")
public class PlusGuide {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Lob
    @Column(name = "ascii_doc", nullable = false)
    private String asciiDoc;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "discounted_amount")
    private Integer discountedAmount;

    @ColumnDefault("20")
    @Column(name = "total_eap_slots")
    private Short totalEapSlots;

    @ColumnDefault("0")
    @Column(name = "taken_eap_slots")
    private Short takenEapSlots;

    @ColumnDefault("'EAP'")
    @Column(name = "type")
    private String type;

    @ColumnDefault("(json_object())")
    @Column(name = "twitter_infos")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> twitterInfos;

    @Lob
    @Column(name = "screencasts_download_url")
    private String screencastsDownloadUrl;

    @ColumnDefault("'USD'")
    @Column(name = "currency", length = 4)
    private String currency;

    @ColumnDefault("'eservice'")
    @Column(name = "tax_code")
    private String taxCode;

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

    public String getAsciiDoc() {
        return asciiDoc;
    }

    public void setAsciiDoc(String asciiDoc) {
        this.asciiDoc = asciiDoc;
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

    public Short getTotalEapSlots() {
        return totalEapSlots;
    }

    public void setTotalEapSlots(Short totalEapSlots) {
        this.totalEapSlots = totalEapSlots;
    }

    public Short getTakenEapSlots() {
        return takenEapSlots;
    }

    public void setTakenEapSlots(Short takenEapSlots) {
        this.takenEapSlots = takenEapSlots;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getTwitterInfos() {
        return twitterInfos;
    }

    public void setTwitterInfos(Map<String, Object> twitterInfos) {
        this.twitterInfos = twitterInfos;
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

}