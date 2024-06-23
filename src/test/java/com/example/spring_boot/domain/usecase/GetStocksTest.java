package com.example.spring_boot.domain.usecase;

import com.example.spring_boot.domain.entity.Stock;
import com.example.spring_boot.domain.usecase.spec.IGetStocks;
import com.example.spring_boot.infrastructure.spec.IStockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetStocksTest {
    public IGetStocks myInterface;

    public GetStocksTest(IGetStocks myInterface) {
        this.myInterface = myInterface;
    }

    @Mock
    IStockRepository stocksRepository;


    @Test
    @DisplayName("Return all stocks when no params are given")
    public void getAllStocks() {
        List<Stock> expectedStocks = List.of();
        when(stocksRepository.findAll()).thenReturn(List.of());
        List<Stock> result = myInterface.getStocksUseCase(null, null);

        Assertions.assertEquals(expectedStocks, result);

    }
}
