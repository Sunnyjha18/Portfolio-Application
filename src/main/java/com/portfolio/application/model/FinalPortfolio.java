package com.portfolio.application.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalPortfolio {
    private List<UserHolding> userHolding;
    private Long totalPortfolioHoldings;
    private Double totalBuyPrice;
    private Double totalRevenue;
}
