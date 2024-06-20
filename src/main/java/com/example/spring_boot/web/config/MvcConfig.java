package com.example.spring_boot.web.config;

import com.example.spring_boot.domain.usecase.impl.GetStocks;
import com.example.spring_boot.domain.usecase.spec.IGetStocks;
import com.example.spring_boot.infrastructure.spec.IStockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig {
    @Bean
    public IGetStocks IGetStocksUseCase(IStockRepository I_STOCK_REPOSITORY) {
        return new GetStocks(I_STOCK_REPOSITORY);
    }


}