package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockData {

    private String symbol;
    private Double price;

    // =====================================================================
    // FUNDAMENTALS DATA
    // =====================================================================

    private Double epsTtm;
    private Double dividendTtm;

    private Double priceToBook;
    private Double profitMargin;
    private Double returnOnAssets;
    private Double returnOnEquity;

    private Double enterpriseValue;
    private Double sharesOutstanding;

    // forward / book
    private Double forwardEps;
    private Double forwardPe;
    private Double bookValue;
    private Double priceToSalesTrailing12Months; // native Yahoo

    // dividend extras
    private Double dividendRate;
    private Double trailingAnnualDividendYield; // NOT implementing as primary, but map if present

    // returns
    private Double ytdReturn;
    private Double qtdReturn;

    // =====================================================================
    // FINANCIAL DATA
    // =====================================================================

    private Double totalRevenue;
    private Double ebitda;

    private Double grossMargin;
    private Double operatingMargin;
    private Double ebitdaMargin; // opcional para debug

    private Double revenueGrowth;
    private Double earningsGrowth;

    private Double quickRatio;
    private Double currentRatio;

    private Double totalDebt;

    private Double totalCash;
    private Double totalCashPerShare;

    private Double freeCashFlow;
    private Double operatingCashflow;

    private Double grossProfits;

    // revenue per share (Yahoo sometimes provides)
    private Double revenuePerShare;

    // =====================================================================
    // ANALYST DATA (TARGETS / RATINGS)
    // =====================================================================

    private Double targetHighPrice;
    private Double targetLowPrice;
    private Double targetMeanPrice;
    private Double targetMedianPrice;

    private Double recommendationMean;
    private Double numberOfAnalystOpinions;

    // =====================================================================
    // MARKET / PRICE FIELDS
    // =====================================================================

    private Double previousClose;
    private Double fiftyTwoWeekHigh;
    private Double fiftyTwoWeekLow;
    private Double allTimeHigh;
    private Double allTimeLow;
    private Double beta;
    private Double regularMarketVolume;
    private Double averageVolume;

    // =====================================================================
    // ENTERPRISE RATIOS (native)
    // =====================================================================

    private Double enterpriseToRevenue;
    private Double enterpriseToEbitda;

    // =====================================================================
    // DEBT RATIO
    // =====================================================================

    private Double debtToEquity;

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
    private Double netIncomeToCommon;
}
