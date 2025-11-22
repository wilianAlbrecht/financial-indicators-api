package com.financial.indicators.models.earningsYieldData;

import com.financial.indicators.models.StockData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EarningsYieldData extends StockData{

    private Double eps; //lucro por ação
    
}
