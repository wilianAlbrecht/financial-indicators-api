package com.financial.indicators.external.openfinancedata.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FundamentalsDTO {

    private String symbol;

    private BigDecimal currentPrice;

    // =====================================================================
    // ========================= FUNDAMENTALS DATA ==========================
    // =====================================================================

    // Dividendos
    private BigDecimal exDividendDate;
    private BigDecimal fiveYearAvgDividendYield;
    private BigDecimal dividendForward;
    private BigDecimal lastDividendValue;
    private BigDecimal trailingAnnualDividendRate;

    // Lucros
    private BigDecimal trailingEps;

    // Valor patrimonial
    private BigDecimal priceToBook;
    private BigDecimal profitMargins;

    // ROA / ROE (proporções)
    private BigDecimal returnOnAssets;
    private BigDecimal returnOnEquity;

    // Enterprise
    private BigDecimal enterpriseValue;
    private BigDecimal sharesOutstanding;

    // EPS forward e book value
    private BigDecimal forwardEps;
    private BigDecimal bookValue;

    private BigDecimal dividendRate;
    private BigDecimal trailingAnnualDividendYield;

    // =====================================================================
    // =========================== FINANCIAL DATA ============================
    // =====================================================================

    private BigDecimal totalRevenue;
    private BigDecimal ebitda;

    private BigDecimal grossMargins;
    private BigDecimal operatingMargins;
    private BigDecimal ebitdaMargins;

    private BigDecimal revenueGrowth;
    private BigDecimal earningsGrowth;

    private BigDecimal quickRatio;
    private BigDecimal currentRatio;

    private BigDecimal totalDebt;
    private BigDecimal totalCash;
    private BigDecimal totalCashPerShare;

    private BigDecimal operatingCashflow;
    private BigDecimal grossProfits;
    private BigDecimal freeCashflow;

    private BigDecimal revenuePerShare;

    // =====================================================================
    // ====================== ANALYST DATA (TARGETS / RATINGS) ==============
    // =====================================================================

    private BigDecimal targetHighPrice;
    private BigDecimal targetLowPrice;
    private BigDecimal targetMeanPrice;
    private BigDecimal targetMedianPrice;

    private BigDecimal recommendationMean;
    private BigDecimal numberOfAnalystOpinions;

    // =====================================================================
    // ========================== MARKET / PRICE FIELDS =====================
    // =====================================================================

    private BigDecimal previousClose;
    private BigDecimal fiftyTwoWeekHigh;
    private BigDecimal fiftyTwoWeekLow;
    private BigDecimal beta;
    private BigDecimal averageVolume;

    // =====================================================================
    // ======================= ENTERPRISE RATIOS =============================
    // =====================================================================

    private BigDecimal enterpriseToRevenue;
    private BigDecimal enterpriseToEbitda;

    // =====================================================================
    // ============================== DEBT RATIO ============================
    // =====================================================================

    private BigDecimal debtToEquity;

    private BigDecimal netIncomeToCommon;

    private BigDecimal lastDividendDate;
    private BigDecimal volume;
    private BigDecimal dividendYield;
    private BigDecimal marketCap;

}
