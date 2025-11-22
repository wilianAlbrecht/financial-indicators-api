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

        // lógica de dividendos
        Double dividendTtm = null;

        if (r.getTrailingAnnualDividendRate() != null) {
            dividendTtm = r.getTrailingAnnualDividendRate();
        } 
        else if (r.getDividendYield() != null) {
            // se vier porcentagem — converter para valor TTM:
            // dividendYield = dividend / price
            dividendTtm = r.getDividendYield() * r.getRegularMarketPrice();
        }

        data.setDividendTtm(dividendTtm);

        return data;
    }
}
