package com.example.spring_boot.domain.entity;

import java.time.Instant;
import java.util.UUID;

public class Stock {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Stock(UUID id, Instant date, String symbol, Long volume, Double variation, Double spread) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.volume = volume;
        this.variation = variation;
        this.spread = spread;
    }
}
