package com.example.spring_boot.web.controller;

import com.example.spring_boot.domain.entity.Stock;
import com.example.spring_boot.domain.usecase.spec.IGetStocks;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/stocks")
@OpenAPIDefinition(info = @Info(title = "Stocks API", version = "1.0", description = "Test"))
public class StocksController {
    private final IGetStocks IGetStocksUseCase;

    public StocksController(IGetStocks IGetStocksUseCase) {
        this.IGetStocksUseCase = IGetStocksUseCase;
    }

    @GetMapping(value = "")
    public List<Stock> getStocks(@RequestParam(required = false) String symbol,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 @RequestParam(required = false) Date fromDate)
    {
        return IGetStocksUseCase.getStocksUseCase(symbol, fromDate);
    }
}
