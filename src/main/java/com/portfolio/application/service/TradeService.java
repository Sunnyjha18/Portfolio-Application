package com.portfolio.application.service;

import com.portfolio.application.model.Stock;
import com.portfolio.application.model.Trade;
import com.portfolio.application.repository.StockRepository;
import com.portfolio.application.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private StockRepository stockRepository;



    public Boolean checkTrade(Trade trade){
        // check if valid inputs
        return (Objects.equals(trade.getTradeType(), "buy") || Objects.equals(trade.getTradeType(), "sell")) && trade.getQuantity() > 0;
    }
    public String saveTrade(Trade trade) {
        if(!checkTrade(trade))return "Inputs are not valid. Please enter the correct details";

        //find price by using stockId then save into tradeRepository
//        Optional<Stock> stock = stockRepository.findById(trade.getStockId());
//        if(Objects.equals(trade.getTradeType(), "buy")){
//            trade.setPrice(stock.get().getOpenPrice());
//        }else{
//            trade.setPrice(stock.get().getClosePrice());
//        }
//
//         tradeRepository.save(trade);
//         return "Data saved sucessfully!";

        Optional<Stock> stock = stockRepository.findById(trade.getStockId());
        if (stock.isPresent()) {
            if (Objects.equals(trade.getTradeType(), "buy")) {
                trade.setPrice(stock.get().getOpenPrice());
            } else {
                trade.setPrice(stock.get().getClosePrice());
            }
            tradeRepository.save(trade);
            return "Data saved successfully!";
        } else {
            return "Stock not found!";
        }

    }

    public List<Trade> getById(String id){
//        return tradeRepository.findByUserId(id);
        return null;
    }
}
