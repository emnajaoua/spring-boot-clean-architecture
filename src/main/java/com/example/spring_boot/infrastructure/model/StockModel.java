package com.example.spring_boot.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "stocks")
public class StockModel {
    @Id
    UUID id;
    Instant date;
    String symbol;
    Long volume;
    Double variation; // difference between highest and lowest price
    Double spread; // difference between opening and closing price
    Double high;
    Double low;
    Double openingPrice;
    Double closingPrice;
    Double adjClosePrice;

    public UUID getId() {
        return this.id;
    }

    public Instant getDate() {
        return this.date;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Long getVolume() {
        return this.volume;
    }

    public Double getOpen() {
        return this.openingPrice;
    }
}
