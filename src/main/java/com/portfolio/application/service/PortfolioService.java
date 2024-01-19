package com.portfolio.application.service;

import com.portfolio.application.model.FinalPortfolio;
import com.portfolio.application.model.Stock;
import com.portfolio.application.model.Trade;
import com.portfolio.application.model.UserHolding;
import com.portfolio.application.repository.StockRepository;
import com.portfolio.application.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortfolioService {
    @Autowired
    private StockService stockService;
    @Autowired
    private TradeRepository tradeRepository;

    public FinalPortfolio getAllHoldings(String userId){
        List<Trade> allTrade = tradeRepository.findByUserId(userId);
        Set<String> allstocksId  = new HashSet<>();
        for(Trade trade:allTrade){
//            System.out.println(trade.getStockId());
            assert false;
            allstocksId.add(trade.getStockId());
        }
//        for(String stock:allstocksId){
//            System.out.println(stock);
//        }

        List<UserHolding> allUserHolding = new ArrayList<>();
        Long totalholdings = 0L;
        Double totalBuyPrice = (double) 0L;
        Double totalRevenue = (double) 0L;
        for (String stock:allstocksId){
            UserHolding userHolding = new UserHolding();
            Stock stock1 = stockService.getStockDetailsByStockId(stock);
            userHolding.setStockId(stock1.getStockId());
            userHolding.setStockName(stock1.getStockName());
            userHolding.setCurrentPrice(stock1.getClosePrice());
            Optional<Long> quantityBuy = tradeRepository.findSumQuantityByStockIdAndTradeType(stock1.getStockId(), "buy");
            Optional<Long> quantitySell = tradeRepository.findSumQuantityByStockIdAndTradeType(stock1.getStockId(), "sell");
            //bad mai dekhte hai sell>buy
            if(quantitySell.get()>quantityBuy.get()){
                return null;
            }
            Long x = (Long) (quantityBuy.get()  - quantitySell.get());
            Long z = (Long) (quantityBuy.get() + quantitySell.get());
            userHolding.setQuantity(x);
            totalholdings+= (long) (x* userHolding.getCurrentPrice());
            userHolding.setCurrentPrice(stock1.getClosePrice());

            Optional<Double> avgpriceBuy = tradeRepository.findProductOfQuantityAndPriceByStockIdAndTradeType(stock1.getStockId(), "buy");
            avgpriceBuy = Optional.of(avgpriceBuy.get());
            System.out.println(avgpriceBuy);

            Optional<Double> avgpriceSell = tradeRepository.findProductOfQuantityAndPriceByStockIdAndTradeType(stock1.getStockId(), "sell");
            avgpriceSell = Optional.of(avgpriceSell.get());
            System.out.println(avgpriceSell);

            Double y = (avgpriceBuy.get()-avgpriceSell.get())/z;
            userHolding.setBuyPrice(y);
            totalBuyPrice+=y;

            Double returnValue = (userHolding.getCurrentPrice()!=null?userHolding.getCurrentPrice():0.0)- userHolding.getBuyPrice();
            userHolding.setProfit(returnValue);
            totalRevenue+=returnValue;
            System.out.println(userHolding);
            allUserHolding.add(userHolding);
        }
        FinalPortfolio finalPortfolio = new FinalPortfolio();
        finalPortfolio.setUserHolding(allUserHolding);
        finalPortfolio.setTotalPortfolioHoldings(totalholdings);
        finalPortfolio.setTotalBuyPrice( totalBuyPrice);
        finalPortfolio.setTotalRevenue(totalRevenue);

        return finalPortfolio;
    }

}
