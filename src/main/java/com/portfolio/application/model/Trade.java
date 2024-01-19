package com.portfolio.application.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Data
@Entity
@Table(name = "Trade")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long tradeId;
    @Column(name = "stock_id", nullable = false)
    private String stockId;
    @Column(name = "user_account_id", nullable = false)
    private Long userAccountId;
    @Column(name = "trade_Type",nullable = false)
    private String tradeType;
    @Column(name = "quantity",nullable = false )
    private Long quantity;
    private Double price;

    public Trade(String userId, String stockId1, String buy, long l) {

    }
}
