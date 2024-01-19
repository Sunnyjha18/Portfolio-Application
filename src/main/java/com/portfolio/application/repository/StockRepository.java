package com.portfolio.application.repository;

import com.portfolio.application.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface StockRepository extends JpaRepository<Stock,String> {
    Stock findByStockId(String stockId);
}
