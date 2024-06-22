package com.example.spring_boot.infrastructure.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "stocks")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    Date date;
    String symbol;
    Long volume;
    Double variation; // difference between highest and lowest price
    Double spread; // difference between opening and closing price
    Double high;
    Double low;

    public StockModel() {

    }


    public UUID getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Long getVolume() {
        return this.volume;
    }

    public StockModel(UUID id, java.util.Date date, String symbol, Long volume, Double variation, Double spread) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.volume = volume;
        this.variation = variation;
        this.spread = spread;
    }
}
