package com.example.spring_boot.domain.usecase.impl;

import com.example.spring_boot.domain.entity.Stock;
import com.example.spring_boot.domain.usecase.spec.IGetStocks;
import com.example.spring_boot.infrastructure.model.StockModel;
import com.example.spring_boot.infrastructure.spec.IStockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetStocks implements IGetStocks {

    private final IStockRepository I_STOCK_REPOSITORY;

    public GetStocks(IStockRepository iStockRepository) {
        I_STOCK_REPOSITORY = iStockRepository;
    }

    @Override
    public List<Stock> getStocksUseCase(String symbol, Date fromDate) {
        List<StockModel> stocks;
        if (symbol == null && fromDate == null) {
            stocks = I_STOCK_REPOSITORY.findAll();
        } else {
            stocks = I_STOCK_REPOSITORY.findBySymbolOrDateGreaterThanEqual(symbol, fromDate);
        };
        return stocks.stream().map(it -> new Stock(
                it.getId(),
                it.getDate(),
                it.getSymbol(),
                it.getVolume(),
                it.getVariation(),
                it.getSpread()
        )).collect(Collectors.toList());
    };
}
