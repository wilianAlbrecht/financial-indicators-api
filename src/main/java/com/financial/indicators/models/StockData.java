package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockData {
    private String symbol;
    private Double price;
    private Double epsTtm;
    private Double dividendTtm;
    private Double priceEarnings;
}
