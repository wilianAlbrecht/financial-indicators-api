package com.financial.indicators.external.openfinancedata.dto;

import lombok.Data;

@Data
public class FundamentalsResponse {

    private QuoteSummary quoteSummary;

    @Data
    public static class QuoteSummary {
        private Result[] result;
    }

    @Data
    public static class Result {
        private SummaryDetail summaryDetail;
        private DefaultKeyStatistics defaultKeyStatistics;
        private FinancialData financialData;
    }

    @Data
    public static class SummaryDetail {
        private Value trailingAnnualDividendRate;
        private Value trailingPE;
    }

    @Data
    public static class DefaultKeyStatistics {
        private Value trailingEps;
    }

    @Data
    public static class FinancialData {
        private Value currentPrice;
    }

    @Data
    public static class Value {
        private Double raw;
    }
}
