package com.portfolio.application.service;

import com.portfolio.application.model.Stock;
import com.portfolio.application.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;


    private Boolean  firstLine = true;

    public void save(MultipartFile file) throws IOException {
        try{
            csvToDb(file.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  void csvToDb(InputStream is) throws IOException{
        try (InputStreamReader inputStreamReader = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
             String line  = null;
            // Read lines from the BufferedReader
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Read from InputStream: " + line);
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] columns = line.split(",");
                Stock s = new Stock();
                s.setStockId(columns[12]);
                s.setStockName(columns[0]);
                s.setOpenPrice(Double.parseDouble(columns[2]));
                s.setClosePrice(Double.parseDouble(columns[4]));
                s.setHighPrice(Double.parseDouble(columns[3]));
                s.setLowPrice(Double.parseDouble(columns[5]));
                stockRepository.save(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stock getById(String stockId){
            Optional<Stock> optionalStock = stockRepository.findById(stockId);
            if(optionalStock.isPresent()){
                return optionalStock.get();
            }else {
                throw new EntityNotFoundException("Stock with ID " + stockId + " not found");
            }
    }

    public Stock getStockDetailsByStockId(String stockId) {
        return stockRepository.findByStockId(stockId);
    }
}
