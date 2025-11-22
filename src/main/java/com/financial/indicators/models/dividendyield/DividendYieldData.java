package com.financial.indicators.models.dividendyield;

import com.financial.indicators.models.StockData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DividendYieldData extends StockData{
    
    private Double dividendTtm;

}
