package com.portfolio.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.security.PrivateKey;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "User-Holding")
public class UserHolding {
    private String stockId;
    private String stockName;
    private Long quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double profit;
}

