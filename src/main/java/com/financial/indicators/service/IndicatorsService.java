package com.financial.indicators.service;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators indicators = new StockIndicators();

        if (data == null) {
            return indicators;
        }

        Double price = data.getPrice();
        Double eps = data.getEpsTtm();
        Double dividendTtm = data.getDividendTtm();

        // copy basic info
        indicators.setSymbol(data.getSymbol());
        indicators.setPrice(price);

        // Earnings Yield = (EPS / Price) * 100
        if (eps != null && price != null && price != 0) {
            Double earningsYield = (eps / price) * 100.0;
            indicators.setEarningsYield(earningsYield);
        }

        // Dividend Yield = (DividendTTM / Price) * 100
        if (dividendTtm != null && price != null && price != 0) {
            Double dividendYield = (dividendTtm / price) * 100.0;
            indicators.setDividendYield(dividendYield);
        }

        // Price/Earnings (P/E) = Price / EPS
        if (eps != null && eps != 0 && price != null) {
            Double pe = price / eps;
            indicators.setPriceEarnings(pe);
        }

        // Pass-through ratios (if available)
        indicators.setPriceToBook(data.getPriceToBook());
        indicators.setProfitMargin(data.getProfitMargin());
        indicators.setReturnOnAssets(data.getReturnOnAssets());
        indicators.setReturnOnEquity(data.getReturnOnEquity());

        return indicators;
    }
}
