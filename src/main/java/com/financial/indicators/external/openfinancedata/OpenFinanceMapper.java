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

        if (dto == null) {
            return data;
        }

        QuoteSummary quoteSummary = dto.getQuoteSummary();
        if (quoteSummary == null) {
            return data;
        }

        Result[] results = quoteSummary.getResult();
        if (results == null || results.length == 0 || results[0] == null) {
            return data;
        }

        Result first = results[0];

        // price
        FinancialData financialData = first.getFinancialData();
        if (financialData != null && financialData.getCurrentPrice() != null) {
            Double currentPrice = financialData.getCurrentPrice().getRaw();
            data.setPrice(currentPrice);
        }

        // EPS TTM - defaultKeyStatistics.trailingEps
        DefaultKeyStatistics defaultKeyStatistics = first.getDefaultKeyStatistics();
        if (defaultKeyStatistics != null && defaultKeyStatistics.getTrailingEps() != null) {
            Double trailingEps = defaultKeyStatistics.getTrailingEps().getRaw();
            data.setEpsTtm(trailingEps);
        }

        // Dividend TTM - summaryDetail.trailingAnnualDividendRate (fall back to lastDividendValue)
        SummaryDetail summaryDetail = first.getSummaryDetail();
        if (summaryDetail != null && summaryDetail.getTrailingAnnualDividendRate() != null) {
            Double trailingAnnualDividendRate = summaryDetail.getTrailingAnnualDividendRate().getRaw();
            data.setDividendTtm(trailingAnnualDividendRate);
        } else if (defaultKeyStatistics != null && defaultKeyStatistics.getLastDividendValue() != null) {
            // fallback: if lastDividendValue exists (might be a single distribution)
            Double lastDiv = defaultKeyStatistics.getLastDividendValue().getRaw();
            data.setDividendTtm(lastDiv);
        }

        // priceToBook and profitMargin from defaultKeyStatistics
        if (defaultKeyStatistics != null) {
            if (defaultKeyStatistics.getPriceToBook() != null) {
                data.setPriceToBook(defaultKeyStatistics.getPriceToBook().getRaw());
            }
            if (defaultKeyStatistics.getProfitMargins() != null) {
                data.setProfitMargin(defaultKeyStatistics.getProfitMargins().getRaw());
            }
        }

        // returnOnAssets, returnOnEquity from financialData
        if (financialData != null) {
            if (financialData.getReturnOnAssets() != null) {
                data.setReturnOnAssets(financialData.getReturnOnAssets().getRaw());
            }
            if (financialData.getReturnOnEquity() != null) {
                data.setReturnOnEquity(financialData.getReturnOnEquity().getRaw());
            }
        }

        return data;
    }
}
