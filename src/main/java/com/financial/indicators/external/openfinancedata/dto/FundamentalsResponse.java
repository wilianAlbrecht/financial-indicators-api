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
        private Object earnings; // placeholder se necessário
    }

    @Data
    public static class SummaryDetail {
        private Value trailingAnnualDividendRate;
        private Value trailingPE;
    }

    @Data
    public static class DefaultKeyStatistics {
        private Value trailingEps;
        private Value priceToBook;
        private Value profitMargins; // pode conter porcentagem como raw (ex: 0.15774)
        private Value lastDividendValue;
        private Value lastDividendDate;
    }

    @Data
    public static class FinancialData {
        private Value currentPrice;
        private Value returnOnAssets;   // ex: 0.07919
        private Value returnOnEquity;   // ex: 0.19022
        private Value totalRevenue;
        private Value ebitda;
        private String financialCurrency;
    }

    @Data
    public static class Value {
        private Double raw;
        private String fmt;
        private String longFmt;
    }
}
