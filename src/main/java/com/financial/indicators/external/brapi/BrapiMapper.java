package com.financial.indicators.external.brapi;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.brapi.dto.BrapiResponse;
import com.financial.indicators.models.StockData;

@Component
public class BrapiMapper {

    public StockData toStockData(BrapiResponse.Result r) {

        StockData data = new StockData();

        data.setSymbol(r.getSymbol());
        data.setPrice(r.getRegularMarketPrice());
        data.setEps(r.getEarningsPerShare());

        // Apenas mapeia lista de dividendos (sem calcular)
        if (r.getDividendsData() != null) {
            data.setCashDividends(r.getDividendsData().getCashDividends());
        }

        return data;
    }
}
