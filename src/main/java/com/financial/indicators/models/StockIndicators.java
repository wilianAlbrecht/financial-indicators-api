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

    // ===================== NEW FIELDS (implemented now) =====================

    // Forward / Book / Native PS
    private Double forwardEps;
    private Double forwardPe;
    private Double bookValue;
    private Double priceToSalesTrailing12Months;

    // Dividend extras / returns
    private Double dividendRate;
    private Double ytdReturn;
    private Double qtdReturn;

    // Market / price fields
    private Double previousClose;
    private Double fiftyTwoWeekHigh;
    private Double fiftyTwoWeekLow;
    private Double allTimeHigh;
    private Double allTimeLow;
    private Double beta;
    private Double regularMarketVolume;
    private Double averageVolume;

    // Enterprise native ratios
    private Double enterpriseToRevenue;
    private Double enterpriseToEbitda;

    // Debt ratio native
    private Double debtToEquity;

    // revenue per share native
    private Double revenuePerShare;
    // Dividend Payout Ratio
    private Double dividendPayoutRatio;

    // Debt-to-Equity (D/E) recalculado manualmente
    private Double debtToEquityCalculated;

    // ROI
    private Double roi;

    // EV/FCFE
    private Double evFcfe;

    // ROIC Avançado
    private Double roicAdvanced;

    // Forward P/E (Price / ForwardEPS)
    private Double forwardPeCalculated;

    // DPS aprimorado (a partir do dividend rate ou TTM)
    private Double dps;
}
