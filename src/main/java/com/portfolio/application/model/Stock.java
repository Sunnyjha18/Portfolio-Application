package com.portfolio.application.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "stocks")
public class Stock {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String stockId;
    @Column(name = "name" ,nullable = false)
    private String stockName;
    @Column(name = "open")
    private Double openPrice;
    @Column(name = "close")
    private Double closePrice;
    @Column(name = "high")
    private Double highPrice;
    @Column(name = "low")
    private Double lowPrice;

    public Stock(String yourStockId, String stockName, double v, double v1) {
        this.setStockId(yourStockId);
        this.stockName = stockName;
    }

//    public Stock(String in0020180033, String sgbmay26, double openPrice, double closePrice, Long tradeId, Long userAccountId) {
//    }
}
