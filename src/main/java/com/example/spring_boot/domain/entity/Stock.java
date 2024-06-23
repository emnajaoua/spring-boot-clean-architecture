package com.example.spring_boot.domain.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.UUID;

public class Stock {
    UUID id;

    @Temporal(TemporalType.DATE)
    Date date;
    String symbol;
    Long volume;
    Double variation; // difference between highest and lowest price
    Double spread; // difference between opening and closing price
    Double high;
    Double low;
    Double openingPrice;
    Double closingPrice;
    Double adjClosePrice;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Stock(UUID id, Date date, String symbol, Long volume, Double variation, Double spread) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.volume = volume;
        this.variation = variation;
        this.spread = spread;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Date getDate() {
        return this.date;
    }

    public Long getVolume() {
        return this.volume;
    }

    public Double getVariation() {
        return this.variation;
    }

    public Double getSpread() {
        return this.spread;
    }
}
