package com.portfolio.application.controller;

import com.portfolio.application.model.FinalPortfolio;
import com.portfolio.application.model.UserHolding;
import com.portfolio.application.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @GetMapping("/{userId}")
    public ResponseEntity<FinalPortfolio> getAll(@PathVariable String userId){
        FinalPortfolio allHolding = portfolioService.getAllHoldings(userId);
        if (allHolding==null) {
            // Return a response with HTTP status 404 (Not Found) if the list is empty
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(allHolding);
    }
}
