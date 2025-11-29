package com.financial.indicators.external.openfinancedata.mappers;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO.DefaultKeyStatistics;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO.FinancialData;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO.QuoteSummary;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO.Result;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO.SummaryDetail;
import com.financial.indicators.models.StockData;

@Component
public class FundamentalsMapper {

    private BigDecimal toBig(FundamentalsDTO.Value v) {
        return (v == null || v.getRaw() == null)
                ? null
                : BigDecimal.valueOf(v.getRaw());
    }

    public StockData toStockData(StockData data, FundamentalsDTO fundamentals) {

        if (data == null || fundamentals == null)
            return data;

        QuoteSummary qs = fundamentals.getQuoteSummary();
        if (qs == null || qs.getResult() == null || qs.getResult().length == 0)
            return data;

        Result first = qs.getResult()[0];
        if (first == null)
            return data;

        SummaryDetail summary = first.getSummaryDetail();
        DefaultKeyStatistics stats = first.getDefaultKeyStatistics();
        FinancialData fin = first.getFinancialData();

        // =====================================================================
        // SUMMARY DETAIL
        // =====================================================================
        if (summary != null) {

            // PRICE
            data.setPreviousClose(toBig(summary.getPreviousClose()));
            data.setMarketCap(toBig(summary.getMarketCap()));

            // DIVIDENDS
            data.setDividendForward(toBig(summary.getTrailingAnnualDividendRate()));
            data.setTrailingAnnualDividendYield(toBig(summary.getTrailingAnnualDividendYield()));
            data.setDividendRate(toBig(summary.getDividendRate()));
            data.setDividendYield(toBig(summary.getDividendYield()));
            data.setExDividendDate(toBig(summary.getExDividendDate()));
            data.setFiveYearAvgDividendYield(toBig(summary.getFiveYearAvgDividendYield()));

            // RISK
            data.setBeta(toBig(summary.getBeta()));

            // PRICE EXTREMES
            data.setFiftyTwoWeekLow(toBig(summary.getFiftyTwoWeekLow()));
            data.setFiftyTwoWeekHigh(toBig(summary.getFiftyTwoWeekHigh()));

            // VOLUME
            data.setVolume(toBig(summary.getVolume()));
            data.setAverageVolume(toBig(summary.getAverageVolume()));
        }

        // =====================================================================
        // DEFAULT KEY STATISTICS
        // =====================================================================
        if (stats != null) {

            // EARNINGS
            data.setEpsTtm(toBig(stats.getTrailingEps()));
            data.setForwardEps(toBig(stats.getForwardEps()));

            // BOOK VALUE / P/B
            data.setPriceToBook(toBig(stats.getPriceToBook()));
            data.setBookValue(toBig(stats.getBookValue()));

            // PROFIT MARGINS
            data.setProfitMargin(toBig(stats.getProfitMargins()));

            // ENTERPRISE / SHARES
            data.setEnterpriseValue(toBig(stats.getEnterpriseValue()));
            data.setSharesOutstanding(toBig(stats.getSharesOutstanding()));

            // DIVIDENDS (fallback)
            data.setLastDividendValue(toBig(stats.getLastDividendValue()));
            data.setLastDividendDate(toBig(stats.getLastDividendDate()));

            // ENTERPRISE RATIOS
            data.setEnterpriseToRevenue(toBig(stats.getEnterpriseToRevenue()));
            data.setEnterpriseToEbitda(toBig(stats.getEnterpriseToEbitda()));

            // NET INCOME
            data.setNetIncomeToCommon(toBig(stats.getNetIncomeToCommon()));
        }

        // =====================================================================
        // FINANCIAL DATA
        // =====================================================================
        if (fin != null) {

            // PRICE
            data.setPrice(toBig(fin.getCurrentPrice()));

            // PROFITS
            data.setGrossProfits(toBig(fin.getGrossProfits()));
            data.setFreeCashFlow(toBig(fin.getFreeCashflow()));

            // DEBT / EQUITY
            data.setDebtToEquity(toBig(fin.getDebtToEquity()));

            // RETURNS
            data.setReturnOnAssets(toBig(fin.getReturnOnAssets()));
            data.setReturnOnEquity(toBig(fin.getReturnOnEquity()));

            // REVENUE / EBITDA
            data.setTotalRevenue(toBig(fin.getTotalRevenue()));
            data.setEbitda(toBig(fin.getEbitda()));

            // MARGINS
            data.setGrossMargin(toBig(fin.getGrossMargins()));
            data.setOperatingMargin(toBig(fin.getOperatingMargins()));
            data.setEbitdaMargin(toBig(fin.getEbitdaMargins()));

            // GROWTH
            data.setRevenueGrowth(toBig(fin.getRevenueGrowth()));
            data.setEarningsGrowth(toBig(fin.getEarningsGrowth()));

            // LIQUIDITY
            data.setQuickRatio(toBig(fin.getQuickRatio()));
            data.setCurrentRatio(toBig(fin.getCurrentRatio()));

            // CASH / DEBT
            data.setTotalCash(toBig(fin.getTotalCash()));
            data.setTotalCashPerShare(toBig(fin.getTotalCashPerShare()));
            data.setTotalDebt(toBig(fin.getTotalDebt()));
            data.setOperatingCashflow(toBig(fin.getOperatingCashflow()));

            // REVENUE PER SHARE
            data.setRevenuePerShare(toBig(fin.getRevenuePerShare()));

            // TARGET PRICES
            data.setTargetHighPrice(toBig(fin.getTargetHighPrice()));
            data.setTargetLowPrice(toBig(fin.getTargetLowPrice()));
            data.setTargetMeanPrice(toBig(fin.getTargetMeanPrice()));
            data.setTargetMedianPrice(toBig(fin.getTargetMedianPrice()));

            // RECOMMENDATIONS
            data.setRecommendationMean(toBig(fin.getRecommendationMean()));
            data.setNumberOfAnalystOpinions(toBig(fin.getNumberOfAnalystOpinions()));
        }

        return data;
    }
}
