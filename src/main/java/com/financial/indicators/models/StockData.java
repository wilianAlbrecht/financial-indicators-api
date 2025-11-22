package com.financial.indicators.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class StockData {
    
    private String symbol;
    private Double currentValue;

}
