package com.example.spring_boot.domain.usecase.spec;

import com.example.spring_boot.domain.entity.Stock;

import java.util.Date;
import java.util.List;



public interface IGetStocks {
    List<Stock> getStocksUseCase(String symbol, Date fromDate);
}
