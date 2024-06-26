package com.example.spring_boot.infrastructure.spec;

import com.example.spring_boot.infrastructure.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IStockRepository extends JpaRepository<StockModel, UUID> {
    List<StockModel> findBySymbolAndDateGreaterThanEqual(String symbol, Date fromDate);
}
