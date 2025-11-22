package com.financial.indicators.service;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators indicators = new StockIndicators();

        indicators.setSymbol(data.getSymbol());
        indicators.setPrice(data.getPrice());

        // Earnings Yield = (EPS / Price) * 100
        if (data.getEps() != null && data.getPrice() != null) {
            indicators.setEarningsYield((data.getEps() / data.getPrice()) * 100);
        }

        // Dividend Yield = (dividendTtm / price) * 100
        if (data.getDividendTtm() != null && data.getPrice() != null) {
            indicators.setDividendYield((data.getDividendTtm() / data.getPrice()) * 100);
        }

        // Price/Earnings (P/L)
        if (data.getEps() != null && data.getEps() != 0) {
            indicators.setPriceEarnings(data.getPrice() / data.getEps());
        }

        return indicators;
    }
}
