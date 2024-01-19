package com.portfolio.application;

import com.portfolio.application.model.Stock;
import com.portfolio.application.repository.StockRepository;
import com.portfolio.application.service.StockService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
    @Test
    public void testSave() throws IOException {
        // Mock MultipartFile
        InputStream inputStream = new ByteArrayInputStream("SYMBOL,SERIES,OPEN, HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN\n182D060624,TB,97.2,97.2,97.2,97.2,97.2,97.15,200,19440,15-Jan-2024,t2,IN002023Y375".getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

        // Mock stockRepository.save
        Mockito.when(stockRepository.save(any(Stock.class))).thenReturn(new Stock());

        stockService.save(file);

        // Verify that stockRepository.save was called
        Mockito.verify(stockRepository, Mockito.times(1)).save(any(Stock.class));
    }

    @Test
    public void testCsvToDb() throws IOException {
//        InputStream inputStream = new ByteArrayInputStream("header1,header2,header3\nvalue1,value2,value3".getBytes());
        InputStream inputStream = new ByteArrayInputStream("SYMBOL,SERIES,OPEN, HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN\n182D060624,TB,97.2,97.2,97.2,97.2,97.2,97.15,200,19440,15-Jan-2024,t2,IN002023Y375".getBytes());
        stockService.csvToDb(inputStream);

        // Verify that stockRepository.save was called
        Mockito.verify(stockRepository, Mockito.times(1)).save(any(Stock.class));
    }

    @Test
    public void testGetById() {
        String stockId = "IN0020180033";
        Stock expectedStock = new Stock();
        expectedStock.setStockId(stockId);

        // Mock stockRepository.findById
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        Stock actualStock = stockService.getById(stockId);

        assertEquals(expectedStock, actualStock);
    }

    @Test
    public void testGetByIdNotFound() {
        String stockId = "nonExistingStockId";

        // Mock stockRepository.findById
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> stockService.getById(stockId));
    }

    @Test
    public void testGetStockDetailsByStockId() {
        String stockId = "IN0020180033";
        Stock expectedStock = new Stock();
        expectedStock.setStockId(stockId);

        // Mock stockRepository.findByStockId
        Mockito.when(stockRepository.findByStockId(stockId)).thenReturn(expectedStock);

        Stock actualStock = stockService.getStockDetailsByStockId(stockId);

        assertEquals(expectedStock, actualStock);
    }

    // Add more test cases as needed
}

