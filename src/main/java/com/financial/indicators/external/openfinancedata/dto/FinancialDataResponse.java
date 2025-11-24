package com.financial.indicators.external.openfinancedata.dto;

import lombok.Data;

@Data
public class FinancialDataResponse {

    private QuoteSummary quoteSummary;

    @Data
    public static class QuoteSummary {
        private Result[] result;
        private Object error;
    }

    @Data
    public static class Result {
        private FinancialData financialData;
    }

    @Data
    public static class FinancialData {

        private Value currentPrice;
        private Value targetHighPrice;
        private Value targetLowPrice;
        private Value targetMeanPrice;
        private Value targetMedianPrice;

        private Value recommendationMean;
        private Value numberOfAnalystOpinions;

        private Value totalCash;
        private Value totalCashPerShare;

        private Value ebitda;
        private Value totalDebt;

        private Value quickRatio;
        private Value currentRatio;

        private Value totalRevenue;
        private Value grossProfits;

        private Value freeCashflow;
        private Value operatingCashflow;

        private Value earningsGrowth;
        private Value revenueGrowth;

        private Value grossMargins;
        private Value operatingMargins;
        private Value ebitdaMargins;

        private String financialCurrency;
    }

    @Data
    public static class Value {
        private Double raw;
        private String fmt;
        private String longFmt;
    }
}
