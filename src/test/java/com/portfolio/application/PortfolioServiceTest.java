package com.portfolio.application;

import com.portfolio.application.model.FinalPortfolio;
import com.portfolio.application.model.Stock;
import com.portfolio.application.model.Trade;
import com.portfolio.application.model.UserHolding;
import com.portfolio.application.repository.TradeRepository;
import com.portfolio.application.service.PortfolioService;
import com.portfolio.application.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class PortfolioServiceTest {

    @Mock
    private StockService stockService;

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    private List<Trade> mockTrades;

    @BeforeEach
    public void setUp() {
        mockTrades = new ArrayList<>();
        String userId = "103";
        // Add mock data for trades
        // Adjust the data based on your actual Trade entity structure
        mockTrades.add(new Trade(userId, "IN0020180033", "buy", 3L));
        mockTrades.add(new Trade(userId, "IN0020180033", "sell", 2L));
        // Add more mock trades as needed
    }

//    @Test
//    public void testGetAllHoldings() {
//        String userId = "103";
//        // Mock stockService.getStockDetailsByStockId
//        Mockito.when(stockService.getStockDetailsByStockId(any(String.class)))
//                .thenReturn(new Stock("IN0020180033", "SGBMAY26", 100.0, 90.0));
//
//        // Mock tradeRepository.findSumQuantityByStockIdAndTradeType
//        Mockito.when(tradeRepository.findSumQuantityByStockIdAndTradeType(eq("IN0020180033"), eq("buy")))
//                .thenReturn(Optional.of(10L));
//        Mockito.when(tradeRepository.findSumQuantityByStockIdAndTradeType(eq("IN0020180033"), eq("sell")))
//                .thenReturn(Optional.of(5L));
//
//        // Mock tradeRepository.findProductOfQuantityAndPriceByStockIdAndTradeType
//        Mockito.when(tradeRepository.findProductOfQuantityAndPriceByStockIdAndTradeType(eq("IN0020180033"), eq("buy")))
//                .thenReturn(Optional.of(1000.0)); // Adjust value based on your data
//        Mockito.when(tradeRepository.findProductOfQuantityAndPriceByStockIdAndTradeType(eq("IN0020180033"), eq("sell")))
//                .thenReturn(Optional.of(500.0)); // Adjust value based on your data
//
//        // Mock getCurrentPrice method if needed
//        Mockito.when(stockService.getStockDetailsByStockId("IN0020180033").getClosePrice())
//                .thenReturn(90.0); // Adjust value based on your data
//
//        // Mock tradeRepository.findByUserId
//        Mockito.when(tradeRepository.findByUserId(userId))
//                .thenReturn(mockTrades);
//
//        // Call the method to test
//        FinalPortfolio finalPortfolio = portfolioService.getAllHoldings(userId);
//
//        // Add assertions based on your expected output
//        assertEquals(1, finalPortfolio.getUserHolding().size());
//        assertEquals(100.0, finalPortfolio.getTotalBuyPrice());
//        assertEquals(500.0, finalPortfolio.getTotalRevenue());
//        // Add more assertions based on your expected output
//    }

    // Add more test cases as needed
}
