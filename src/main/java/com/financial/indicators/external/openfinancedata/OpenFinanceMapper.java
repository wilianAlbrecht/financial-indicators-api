package com.financial.indicators.external.openfinancedata;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.DefaultKeyStatistics;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.FinancialData;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.QuoteSummary;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.Result;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse.SummaryDetail;
import com.financial.indicators.models.StockData;

@Component
public class OpenFinanceMapper {

    public StockData toStockData(String symbol, FundamentalsResponse dto) {

        StockData data = new StockData();
        data.setSymbol(symbol);

        if (dto == null) return data;

        QuoteSummary qs = dto.getQuoteSummary();
        if (qs == null || qs.getResult() == null || qs.getResult().length == 0) return data;

        Result first = qs.getResult()[0];
        if (first == null) return data;

        SummaryDetail summaryDetail = first.getSummaryDetail();
        DefaultKeyStatistics stats = first.getDefaultKeyStatistics();
        FinancialData fin = first.getFinancialData();

        // ======================
        // PRICE
        // ======================
        if (fin != null && fin.getCurrentPrice() != null) {
            data.setPrice(fin.getCurrentPrice().getRaw());
        }

        // ======================
        // EPS TTM
        // ======================
        if (stats != null && stats.getTrailingEps() != null) {
            data.setEpsTtm(stats.getTrailingEps().getRaw());
        }

        // ======================
        // DIVIDEND TTM
        // ======================
        if (summaryDetail != null && summaryDetail.getTrailingAnnualDividendRate() != null) {
            data.setDividendTtm(summaryDetail.getTrailingAnnualDividendRate().getRaw());
        } else if (stats != null && stats.getLastDividendValue() != null) {
            data.setDividendTtm(stats.getLastDividendValue().getRaw());
        }

        // ======================
        // PRICE-TO-BOOK
        // ======================
        if (stats != null && stats.getPriceToBook() != null) {
            data.setPriceToBook(stats.getPriceToBook().getRaw());
        }

        // ======================
        // PROFIT MARGIN
        // ======================
        if (stats != null && stats.getProfitMargins() != null) {
            data.setProfitMargin(stats.getProfitMargins().getRaw());
        }

        // ======================
        // RETURN ON ASSETS & EQUITY
        // ======================
        if (fin != null && fin.getReturnOnAssets() != null) {
            data.setReturnOnAssets(fin.getReturnOnAssets().getRaw());
        }
        if (fin != null && fin.getReturnOnEquity() != null) {
            data.setReturnOnEquity(fin.getReturnOnEquity().getRaw());
        }

        // ======================
        // ENTERPRISE VALUE
        // ======================
        if (stats != null && stats.getEnterpriseValue() != null) {
            data.setEnterpriseValue(stats.getEnterpriseValue().getRaw());
        }

        // ======================
        // SHARES OUTSTANDING
        // ======================
        if (stats != null && stats.getSharesOutstanding() != null) {
            data.setSharesOutstanding(stats.getSharesOutstanding().getRaw());
        }

        // ======================
        // TOTAL REVENUE & EBITDA
        // ======================
        if (fin != null && fin.getTotalRevenue() != null) {
            data.setTotalRevenue(fin.getTotalRevenue().getRaw());
        }
        if (fin != null && fin.getEbitda() != null) {
            data.setEbitda(fin.getEbitda().getRaw());
        }

        // ======================
        // MARGINS
        // ======================
        if (fin != null && fin.getGrossMargins() != null) {
            data.setGrossMargin(fin.getGrossMargins().getRaw());
        }
        if (fin != null && fin.getOperatingMargins() != null) {
            data.setOperatingMargin(fin.getOperatingMargins().getRaw());
        }

        // EBITDA Margin estará disponível como:
        // ebitda / totalRevenue — calculamos no Calculator,
        // mas passamos os valores brutos aqui.

        // ======================
        // GROWTH
        // ======================
        if (fin != null && fin.getRevenueGrowth() != null) {
            data.setRevenueGrowth(fin.getRevenueGrowth().getRaw());
        }
        if (fin != null && fin.getEarningsGrowth() != null) {
            data.setEarningsGrowth(fin.getEarningsGrowth().getRaw());
        }

        // ======================
        // RATIOS
        // ======================
        if (fin != null && fin.getQuickRatio() != null) {
            data.setQuickRatio(fin.getQuickRatio().getRaw());
        }
        if (fin != null && fin.getCurrentRatio() != null) {
            data.setCurrentRatio(fin.getCurrentRatio().getRaw());
        }

        return data;
    }
}
