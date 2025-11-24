package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockData {

    private String symbol;
    private Double price;

    // EPS TTM (trailing earnings per share)
    private Double epsTtm;

    // Dividend TTM (total dividends last 12 months) / or trailingAnnualDividendRate when available
    private Double dividendTtm;

    // Additional raw fields copied from fundamentals (not calculated here)
    private Double priceToBook;
    private Double profitMargin;      // profit margin (e.g. 0.15774)
    private Double returnOnAssets;    // e.g. 0.07919
    private Double returnOnEquity;    // e.g. 0.19022
}
