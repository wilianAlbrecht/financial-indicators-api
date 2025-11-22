package com.financial.indicators.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockData {
   
    private String symbol;
    private Double price;          // regularMarketPrice
    private Double eps;            // earningsPerShare
    private Double dividendTtm;    // trailingAnnualDividendRate (ou convertido do dividendYield)
}