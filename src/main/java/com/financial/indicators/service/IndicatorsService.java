package com.financial.indicators.service;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators indicators = new StockIndicators();

        Double price = data.getPrice();
        Double eps = data.getEpsTtm();
        Double dividendTtm = data.getDividendTtm();

        indicators.setSymbol(data.getSymbol());
        indicators.setPrice(price);

        // Earnings Yield
        if (eps != null && price != null && eps != 0) {
            indicators.setEarningsYield((eps / price) * 100);
        }

        // Dividend Yield
        if (dividendTtm != null && price != null) {
            indicators.setDividendYield((dividendTtm / price) * 100);
        }

        // Price/Earnings
        if (eps != null && eps != 0) {
            indicators.setPriceEarnings(price / eps);
        }

        return indicators;
    }
}
