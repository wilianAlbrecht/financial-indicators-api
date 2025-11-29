package com.financial.indicators.external.openfinancedata.dto;

import lombok.Data;

@Data
public class FundamentalsDTO {

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
    // SUMMARY DETAIL 
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

        private Value exDividendDate;
        private Value fiveYearAvgDividendYield;

        private Value fiftyDayAverage;
        private Value twoHundredDayAverage;

        private Value bid;
        private Value ask;
        private Value volume;

        private Value averageVolume10days;
        private Value averageDailyVolume10Day;
    }

    // ======================================================
    // DEFAULT KEY STATISTICS 
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
        private Value pegRatio;

        private Value lastFiscalYearEnd;
        private Value nextFiscalYearEnd;

        private Value mostRecentQuarter;
    }

    // ======================================================
    // FINANCIAL DATA
    // ======================================================
    @Data
    public static class FinancialData {

        private Value currentPrice;

        private Value freeCashflow;

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
        private Value totalCash;

        private Value totalCashPerShare;
        private Value totalDebt;

        private Value grossProfits;
        private Value revenuePerShare;
        
        private Value targetHighPrice;
        private Value targetLowPrice;

        private Value targetMeanPrice;
        private Value targetMedianPrice;

        private Value recommendationMean;
        private String recommendationKey;

        private Value numberOfAnalystOpinions;
        private Value ebitdaMargins;

        private Value debtToEquity;
        private Value operatingCashflow;


    }

    // ======================================================
    @Data
    public static class Value {
        private Double raw;
        private String fmt;
        private String longFmt;
    }
}
