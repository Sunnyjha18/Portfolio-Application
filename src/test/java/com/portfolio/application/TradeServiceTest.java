package com.portfolio.application;

import com.portfolio.application.model.Stock;
import com.portfolio.application.model.Trade;
import com.portfolio.application.repository.StockRepository;
import com.portfolio.application.repository.TradeRepository;
import com.portfolio.application.service.TradeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    public void testCheckTradeValidInput() {
        Trade trade = new Trade();
        trade.setTradeType("buy");
        trade.setQuantity(10L);

        assertTrue(tradeService.checkTrade(trade));
    }

    @Test
    public void testCheckTradeInvalidInput() {
        Trade trade = new Trade();
        trade.setTradeType("invalid");
        trade.setQuantity((long) -5);

        assertFalse(tradeService.checkTrade(trade));
    }

    @Test
    public void testSaveTradeBuy() {
        Trade trade = new Trade();
        trade.setTradeType("buy");
        trade.setQuantity(10L);
        trade.setStockId("IN0020180033");
        trade.setUserAccountId(103L);
        trade.setTradeId(1L);

        // Mocking stock data
        Mockito.when(stockRepository.findById("IN0020180033"))
                .thenReturn(Optional.of(new Stock("IN0020180033", "SGBMAY26", 100.0, 90.0)));

        String result = tradeService.saveTrade(trade);

        assertEquals("Data saved successfully!", result);
        // You can add more assertions based on your specific use case
    }

    @Test
    public void testSaveTradeInvalidInput() {
        Trade trade = new Trade();
        trade.setTradeType("invalid");
        trade.setQuantity((long) -5);

        String result = tradeService.saveTrade(trade);

        assertEquals("Inputs are not valid. Please enter the correct details", result);
    }

    // Add more test cases as needed

}

