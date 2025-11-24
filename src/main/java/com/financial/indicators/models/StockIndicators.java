package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockIndicators {

    private String symbol;
    private Double price;

    // Calculated indicators
    private Double earningsYield;   // % -> (EPS / Price) * 100
    private Double dividendYield;   // % -> (DividendTTM / Price) * 100
    private Double priceEarnings;   // P/E -> Price / EPS

    // Pass-through / basic ratios (may be null if not available)
    private Double priceToBook;
    private Double profitMargin;     // raw (0.15774) -> you can format later as %
    private Double returnOnAssets;   // raw (0.07919)
    private Double returnOnEquity;   // raw (0.19022)
}
