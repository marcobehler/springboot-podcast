package org.example.springbootpodcast.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "EXCHANGE_RATES")
public class ExchangeRate {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "`from`", nullable = false, length = 3)
    private String from;

    @Column(name = "`to`", nullable = false, length = 4)
    private String to;

    @Column(name = "rate", nullable = false, precision = 30, scale = 6)
    private BigDecimal rate;

    @Column(name = "valid_at", nullable = false)
    private Instant validAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Instant getValidAt() {
        return validAt;
    }

    public void setValidAt(Instant validAt) {
        this.validAt = validAt;
    }

}