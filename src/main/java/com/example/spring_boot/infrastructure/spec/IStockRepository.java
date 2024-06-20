package com.example.spring_boot.infrastructure.spec;

import com.example.spring_boot.infrastructure.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface IStockRepository extends JpaRepository<StockModel, UUID> {
    List<StockModel> findBySymbolOrDateGreaterThanEqual(String symbol, Instant fromDate);
}
