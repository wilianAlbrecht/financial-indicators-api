package com.financial.indicators.external.openfinancedata.mappers;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.DefaultKeyStatistics;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.FinancialData;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.QuoteSummary;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.Result;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.SummaryDetail;
import com.financial.indicators.models.StockData;

@Component
public class FundamentalsMapper {

    public StockData toStockData(StockData data, FundamentalsResponse fundamentals) {

        if (data == null || fundamentals == null) return data;

        QuoteSummary qs = fundamentals.getQuoteSummary();
        if (qs == null || qs.getResult() == null || qs.getResult().length == 0) return data;

        Result first = qs.getResult()[0];
        if (first == null) return data;

        SummaryDetail summary = first.getSummaryDetail();
        DefaultKeyStatistics stats = first.getDefaultKeyStatistics();
        FinancialData fin = first.getFinancialData();

        // =====================================================
        // PRICE
        // =====================================================
        if (fin != null && fin.getCurrentPrice() != null)
            data.setPrice(fin.getCurrentPrice().getRaw());

        // =====================================================
        // EPS (TTM)
        // =====================================================
        if (stats != null && stats.getTrailingEps() != null)
            data.setEpsTtm(stats.getTrailingEps().getRaw());

        // =====================================================
        // DIVIDENDS
        // =====================================================
        if (summary != null && summary.getTrailingAnnualDividendRate() != null)
            data.setDividendTtm(summary.getTrailingAnnualDividendRate().getRaw());
        else if (stats != null && stats.getLastDividendValue() != null)
            data.setDividendTtm(stats.getLastDividendValue().getRaw());

        if (summary != null && summary.getDividendRate() != null)
            data.setDividendRate(summary.getDividendRate().getRaw());

        // =====================================================
        // PRICE / BOOK
        // =====================================================
        if (stats != null && stats.getPriceToBook() != null)
            data.setPriceToBook(stats.getPriceToBook().getRaw());

        if (stats != null && stats.getBookValue() != null)
            data.setBookValue(stats.getBookValue().getRaw());

        // =====================================================
        // RETURN ON ASSETS / EQUITY
        // =====================================================
        if (fin != null && fin.getReturnOnAssets() != null)
            data.setReturnOnAssets(fin.getReturnOnAssets().getRaw());

        if (fin != null && fin.getReturnOnEquity() != null)
            data.setReturnOnEquity(fin.getReturnOnEquity().getRaw());

        // =====================================================
        // ENTERPRISE & SHARES
        // =====================================================
        if (stats != null && stats.getEnterpriseValue() != null)
            data.setEnterpriseValue(stats.getEnterpriseValue().getRaw());

        if (stats != null && stats.getSharesOutstanding() != null)
            data.setSharesOutstanding(stats.getSharesOutstanding().getRaw());

        // =====================================================
        // INCOME STATEMENT — Revenue / EBITDA
        // =====================================================
        if (fin != null && fin.getTotalRevenue() != null)
            data.setTotalRevenue(fin.getTotalRevenue().getRaw());

        if (fin != null && fin.getEbitda() != null)
            data.setEbitda(fin.getEbitda().getRaw());

        // =====================================================
        // MARGINS
        // =====================================================
        if (fin != null && fin.getGrossMargins() != null)
            data.setGrossMargin(fin.getGrossMargins().getRaw());

        if (fin != null && fin.getOperatingMargins() != null)
            data.setOperatingMargin(fin.getOperatingMargins().getRaw());

        // =====================================================
        // GROWTH
        // =====================================================
        if (fin != null && fin.getRevenueGrowth() != null)
            data.setRevenueGrowth(fin.getRevenueGrowth().getRaw());

        if (fin != null && fin.getEarningsGrowth() != null)
            data.setEarningsGrowth(fin.getEarningsGrowth().getRaw());

        // =====================================================
        // LIQUIDITY
        // =====================================================
        if (fin != null && fin.getQuickRatio() != null)
            data.setQuickRatio(fin.getQuickRatio().getRaw());

        if (fin != null && fin.getCurrentRatio() != null)
            data.setCurrentRatio(fin.getCurrentRatio().getRaw());

        // =====================================================
        // SUMMARY DETAIL — extended fields
        // =====================================================
        if (summary != null) {

            if (summary.getPreviousClose() != null)
                data.setPreviousClose(summary.getPreviousClose().getRaw());

            if (summary.getFiftyTwoWeekHigh() != null)
                data.setFiftyTwoWeekHigh(summary.getFiftyTwoWeekHigh().getRaw());

            if (summary.getFiftyTwoWeekLow() != null)
                data.setFiftyTwoWeekLow(summary.getFiftyTwoWeekLow().getRaw());

            if (summary.getAllTimeHigh() != null)
                data.setAllTimeHigh(summary.getAllTimeHigh().getRaw());

            if (summary.getAllTimeLow() != null)
                data.setAllTimeLow(summary.getAllTimeLow().getRaw());

            if (summary.getBeta() != null)
                data.setBeta(summary.getBeta().getRaw());

            if (summary.getAverageVolume() != null)
                data.setAverageVolume(summary.getAverageVolume().getRaw());

            if (summary.getRegularMarketVolume() != null)
                data.setRegularMarketVolume(summary.getRegularMarketVolume().getRaw());

            if (summary.getPriceToSalesTrailing12Months() != null)
                data.setPriceToSalesTrailing12Months(summary.getPriceToSalesTrailing12Months().getRaw());
        }

        // =====================================================
        // KEY STATISTICS — advanced ratios
        // =====================================================
        if (stats != null) {

            if (stats.getEnterpriseToRevenue() != null)
                data.setEnterpriseToRevenue(stats.getEnterpriseToRevenue().getRaw());

            if (stats.getEnterpriseToEbitda() != null)
                data.setEnterpriseToEbitda(stats.getEnterpriseToEbitda().getRaw());

            if (stats.getPriceToSalesTrailing12Months() != null)
                data.setPriceToSalesTrailing12Months(stats.getPriceToSalesTrailing12Months().getRaw());

            if (stats.getNetIncomeToCommon() != null)
                data.setNetIncomeToCommon(stats.getNetIncomeToCommon().getRaw());
        }

        return data;
    }
}
