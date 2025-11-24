package com.financial.indicators.external.openfinancedata.dto;

import lombok.Data;

@Data
public class FundamentalsResponse {

    private QuoteSummary quoteSummary;

    @Data
    public static class QuoteSummary {
        private Result[] result;
        private Object error;
    }

    @Data
    public static class Result {
        private SummaryDetail summaryDetail;
        private DefaultKeyStatistics defaultKeyStatistics;
        private FinancialData financialData;
        private Object earnings; // permanece placeholder
    }

    // ============================
    // SUMMARY DETAIL
    // ============================
    @Data
    public static class SummaryDetail {
        private Value trailingAnnualDividendRate;
        private Value trailingPE;
        private Value dividendYield;       // ADICIONADO
        private Value priceToBook;         // ADICIONADO
    }

    // ============================
    // DEFAULT KEY STATISTICS
    // ============================
    @Data
    public static class DefaultKeyStatistics {
        private Value trailingEps;
        private Value forwardEps;          // ADICIONADO
        private Value priceToBook;
        private Value profitMargins;
        private Value enterpriseValue;     // ADICIONADO
        private Value sharesOutstanding;   // ADICIONADO
        private Value forwardPE;           // ADICIONADO
        private Value lastDividendValue;
        private Value lastDividendDate;
    }

    // ============================
    // FINANCIAL DATA
    // ============================
    @Data
    public static class FinancialData {
        private Value currentPrice;
        private Value returnOnAssets;
        private Value returnOnEquity;
        private Value totalRevenue;
        private Value ebitda;

        private Value profitMargins;       // ADICIONADO
        private Value operatingMargins;    // ADICIONADO
        private Value grossMargins;        // ADICIONADO
        private Value revenueGrowth;       // ADICIONADO
        private Value earningsGrowth;      // ADICIONADO
        private Value quickRatio;          // ADICIONADO
        private Value currentRatio;        // ADICIONADO

        private String financialCurrency;
    }

    // ============================
    // VALUE WRAPPER
    // ============================
    @Data
    public static class Value {
        private Double raw;
        private String fmt;
        private String longFmt;
    }
}
