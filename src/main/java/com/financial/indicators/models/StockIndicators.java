package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockIndicators {

    private String symbol;
    private Double price;

    private Double earningsYield;
    private Double dividendYield;
    private Double priceEarnings;  // P/L
}