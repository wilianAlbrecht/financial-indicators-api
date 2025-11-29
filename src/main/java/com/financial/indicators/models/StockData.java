package com.financial.indicators.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StockData {

    private String symbol;

    // Preço é dinheiro
    private BigDecimal price;

    // =====================================================================
    // ========================= FUNDAMENTALS DATA ==========================
    // =====================================================================

    // Dividendos
    private BigDecimal exDividendDate;
    private BigDecimal fiveYearAvgDividendYield;
    private BigDecimal dividendForward;
    private BigDecimal lastDividendValue;

    // Lucros
    private BigDecimal epsTtm;

    // Valor patrimonial
    private BigDecimal priceToBook;
    private BigDecimal profitMargin;

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

    private BigDecimal grossMargin;
    private BigDecimal operatingMargin;
    private BigDecimal ebitdaMargin;

    private BigDecimal revenueGrowth;
    private BigDecimal earningsGrowth;

    private BigDecimal quickRatio;
    private BigDecimal currentRatio;

    private BigDecimal totalDebt;
    private BigDecimal totalCash;
    private BigDecimal totalCashPerShare;

    private BigDecimal operatingCashflow;
    private BigDecimal grossProfits;
    private BigDecimal freeCashFlow;

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


    // ===========================History Dividends Data=========================
    private BigDecimal dividendTrueTtm;

}
