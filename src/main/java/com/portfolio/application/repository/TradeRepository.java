package com.portfolio.application.repository;

import com.portfolio.application.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade,Long> {
    @Query("SELECT DISTINCT t FROM Trade t WHERE t.userAccountId = :userId")
    List<Trade> findByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(p.quantity) FROM Trade p WHERE p.stockId = :stockId AND p.tradeType = :tradeType")
    Optional<Long> findSumQuantityByStockIdAndTradeType(@Param("stockId") String stockId, @Param("tradeType") String tradeType);

    @Query("SELECT SUM(p.quantity* p.price) FROM Trade p WHERE p.stockId = :stockId AND p.tradeType = :tradeType")
    Optional<Double> findProductOfQuantityAndPriceByStockIdAndTradeType(@Param("stockId") String stockId, @Param("tradeType") String tradeType);


}
