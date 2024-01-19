package com.portfolio.application.controller;

import com.portfolio.application.model.Trade;
import com.portfolio.application.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("api/v1/")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @PostMapping("/savetrade")
    public ResponseEntity<String> getTradeDetails(@RequestBody Trade trade ){
        if(trade==null){
            String msg = "required information not provided" + HttpStatus.NO_CONTENT;
            return ResponseEntity.ok(msg);
        }
        try{
            //recive the details and saving to db through service
            String msg =  tradeService.saveTrade(trade);
//            return ResponseEntity.ok("Data saved sucessfully!");
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        }catch (Exception e){
            String message = "the correct data is not provided. Giving "+ HttpStatus.BAD_REQUEST + " " + e.getMessage();
            e.printStackTrace();
            return ResponseEntity.ok(message);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
