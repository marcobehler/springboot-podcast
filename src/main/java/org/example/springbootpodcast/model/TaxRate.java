package org.example.springbootpodcast.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "TAX_RATES")
public class TaxRate {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country", nullable = false, length = 2)
    private String country;

    @Column(name = "vat_number", length = 20)
    private String vatNumber;

    @Column(name = "NAME", length = 256)
    private String name;

    @Column(name = "NOTES", length = 256)
    private String notes;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "valid_until")
    private LocalDate validUntil;

    @Column(name = "tax_code", nullable = false, length = 10)
    private String taxCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

}