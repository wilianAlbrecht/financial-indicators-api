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

    // =====================================================================
    // ANALYST DATA (TARGETS / RATINGS)
    // =====================================================================

    private Double targetHighPrice;
    private Double targetLowPrice;
    private Double targetMeanPrice;
    private Double targetMedianPrice;

    private Double recommendationMean;
    private Double numberOfAnalystOpinions;
}
