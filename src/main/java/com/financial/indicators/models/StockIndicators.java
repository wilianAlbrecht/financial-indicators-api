package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockIndicators {

    private String symbol;
    private Double price;

    // ====================================================
    // Fundamental Indicators (already implemented)
    // ====================================================
    private Double earningsYield; // (EPS / Price) * 100
    private Double dividendYield; // (DividendTTM / Price) * 100
    private Double priceEarnings; // Price / EPS

    private Double priceToBook; // raw
    private Double profitMargin; // raw (0.15774)
    private Double returnOnAssets; // ROA
    private Double returnOnEquity; // ROE

    // ====================================================
    // NEW Indicators from Fundamentals (now supported)
    // ====================================================

    // Valuation
    private Double psRatio; // Price / (Revenue per share)
    private Double evEbitda; // Enterprise Value / EBITDA
    private Double pegRatio; // PE / earningsGrowth

    // Market Metrics
    private Double marketCap; // price * sharesOutstanding
    private Double evRevenue; // EV / Revenue

    // Margins
    private Double grossMargin; // grossMargins.raw
    private Double operatingMargin; // operatingMargins.raw
    private Double ebitdaMargin; // EBITDA / Revenue

    // Growth
    private Double revenueGrowth; // revenueGrowth.raw
    private Double earningsGrowth; // earningsGrowth.raw

    // Liquidity Ratios
    private Double currentRatio; // currentRatio.raw
    private Double quickRatio; // quickRatio.raw

    private Double totalCash;
    private Double totalDebt;
    private Double cashPerShare;

    private Double freeCashFlow;
    private Double freeCashFlowYield;
    private Double operatingCashflowPerShare;

    private Double evFcf;
    private Double roic;

    private Double grossProfits;
    private Double grossProfitMargin;

    private Double targetHighPrice;
    private Double targetLowPrice;
    private Double targetMeanPrice;
    private Double targetMedianPrice;

    private Double recommendationMean;
    private Double numberOfAnalystOpinions;

}
