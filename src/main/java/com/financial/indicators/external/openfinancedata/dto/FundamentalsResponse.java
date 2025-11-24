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
        private Object earnings;
    }

    // ======================================================
    // SUMMARY DETAIL (JSON REAL)
    // ======================================================
    @Data
    public static class SummaryDetail {

        private Value previousClose;
        private Value open;
        private Value dayLow;
        private Value dayHigh;

        private Value regularMarketPreviousClose;
        private Value regularMarketOpen;
        private Value regularMarketDayLow;
        private Value regularMarketDayHigh;

        private Value trailingAnnualDividendRate;
        private Value trailingAnnualDividendYield;

        private Value dividendRate;
        private Value dividendYield;

        private Value payoutRatio;

        private Value beta;

        private Value trailingPE;
        private Value forwardPE;

        private Value marketCap;

        private Value fiftyTwoWeekLow;
        private Value fiftyTwoWeekHigh;

        private Value allTimeHigh;
        private Value allTimeLow;

        private Value priceToSalesTrailing12Months;

        private Value averageVolume;
        private Value regularMarketVolume;

        private Value bid;
        private Value ask;
        private Value volume;
    }

    // ======================================================
    // DEFAULT KEY STATISTICS  (JSON REAL)
    // ======================================================
    @Data
    public static class DefaultKeyStatistics {

        private Value trailingEps;
        private Value forwardEps;

        private Value priceToBook;
        private Value bookValue;

        private Value profitMargins;

        private Value enterpriseValue;
        private Value sharesOutstanding;

        private Value forwardPE;
        private Value lastDividendValue;
        private Value lastDividendDate;

        private Value enterpriseToRevenue;
        private Value enterpriseToEbitda;

        private Value earningsQuarterlyGrowth;
        private Value revenueQuarterlyGrowth;

        private Value netIncomeToCommon;

        private Value priceToSalesTrailing12Months;
    }

    // ======================================================
    // FINANCIAL DATA (FUNDAMENTALS VERSION)
    // ======================================================
    @Data
    public static class FinancialData {

        private Value currentPrice;

        private Value returnOnAssets;
        private Value returnOnEquity;

        private Value totalRevenue;
        private Value ebitda;

        private Value grossMargins;
        private Value operatingMargins;

        private Value revenueGrowth;
        private Value earningsGrowth;

        private Value quickRatio;
        private Value currentRatio;

        private String financialCurrency;
    }

    // ======================================================
    @Data
    public static class Value {
        private Double raw;
        private String fmt;
        private String longFmt;
    }
}
