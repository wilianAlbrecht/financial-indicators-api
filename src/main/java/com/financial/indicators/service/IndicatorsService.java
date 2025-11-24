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

        indicators.setSymbol(data.getSymbol());
        indicators.setPrice(price);

        // =====================================================
        // BASIC INDICATORS (Already implemented)
        // =====================================================

        // Earnings Yield = (EPS / Price) * 100
        if (eps != null && price != null && price != 0) {
            indicators.setEarningsYield((eps / price) * 100.0);
        }

        // Dividend Yield = (DividendTTM / Price) * 100
        if (dividendTtm != null && price != null && price != 0) {
            indicators.setDividendYield((dividendTtm / price) * 100.0);
        }

        // Price/Earnings (P/E)
        if (eps != null && eps != 0 && price != null) {
            indicators.setPriceEarnings(price / eps);
        }

        // Pass-through ratios
        indicators.setPriceToBook(data.getPriceToBook());
        indicators.setProfitMargin(data.getProfitMargin());
        indicators.setReturnOnAssets(data.getReturnOnAssets());
        indicators.setReturnOnEquity(data.getReturnOnEquity());


        // =====================================================
        // NEW INDICATORS (Fundamentals Only)
        // =====================================================

        Double shares = data.getSharesOutstanding();
        Double revenue = data.getTotalRevenue();
        Double ebitda = data.getEbitda();
        Double enterpriseValue = data.getEnterpriseValue();

        Double grossMargin = data.getGrossMargin();
        Double operatingMargin = data.getOperatingMargin();
        Double revenueGrowth = data.getRevenueGrowth();
        Double earningsGrowth = data.getEarningsGrowth();

        Double currentRatio = data.getCurrentRatio();
        Double quickRatio = data.getQuickRatio();


        // ------------------------------
        // Price/Sales (P/S)
        // ------------------------------
        if (price != null && revenue != null && revenue != 0 && shares != null && shares != 0) {
            Double revenuePerShare = revenue / shares;
            if (revenuePerShare != 0) {
                indicators.setPsRatio(price / revenuePerShare);
            }
        }

        // ------------------------------
        // EV/EBITDA
        // ------------------------------
        if (enterpriseValue != null && ebitda != null && ebitda != 0) {
            indicators.setEvEbitda(enterpriseValue / ebitda);
        }

        // ------------------------------
        // Current Ratio
        // ------------------------------
        indicators.setCurrentRatio(currentRatio);

        // ------------------------------
        // Quick Ratio
        // ------------------------------
        indicators.setQuickRatio(quickRatio);

        // ------------------------------
        // Gross Margin
        // ------------------------------
        indicators.setGrossMargin(grossMargin);

        // ------------------------------
        // Operating Margin
        // ------------------------------
        indicators.setOperatingMargin(operatingMargin);

        // ------------------------------
        // EBITDA Margin = EBITDA / Revenue
        // ------------------------------
        if (ebitda != null && revenue != null && revenue != 0) {
            indicators.setEbitdaMargin(ebitda / revenue);
        }

        // ------------------------------
        // Revenue Growth
        // ------------------------------
        indicators.setRevenueGrowth(revenueGrowth);

        // ------------------------------
        // Earnings Growth
        // ------------------------------
        indicators.setEarningsGrowth(earningsGrowth);

        // ------------------------------
        // PEG Ratio = PE / EarningsGrowth
        // ------------------------------
        if (indicators.getPriceEarnings() != null &&
            earningsGrowth != null && earningsGrowth != 0) {
            indicators.setPegRatio(indicators.getPriceEarnings() / earningsGrowth);
        }

        // ------------------------------
        // Market Cap = Price * Shares Outstanding
        // ------------------------------
        if (price != null && shares != null) {
            indicators.setMarketCap(price * shares);
        }

        // ------------------------------
        // EV/Revenue
        // ------------------------------
        if (enterpriseValue != null && revenue != null && revenue != 0) {
            indicators.setEvRevenue(enterpriseValue / revenue);
        }

        return indicators;
    }
}
