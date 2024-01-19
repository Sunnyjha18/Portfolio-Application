package com.portfolio.application.controller;


import com.portfolio.application.model.Stock;
import com.portfolio.application.service.StockService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/v1")

public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/stocks")
    public ResponseEntity<String> AllStocks(@RequestParam("file")MultipartFile csvFile){
        try{
            stockService.save(csvFile);
            return ResponseEntity.ok("Stock updated successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error in updating file to db");
        }
    }
    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> getOneStock(@PathVariable("stockId") String stockId){
        try{
            Stock stock =  stockService.getById(stockId);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}



/*
*
* @PostMapping("/update-stocks")
public ResponseEntity<String> updateStocks(@RequestParam("file") MultipartFile csvFile){
    try{
        updateStockService.updateStockFromCsvFile(csvFile);
        return ResponseEntity.ok("Stock Updated in Database Successfully");
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Error Updating the CSV File");
    }

}
* */