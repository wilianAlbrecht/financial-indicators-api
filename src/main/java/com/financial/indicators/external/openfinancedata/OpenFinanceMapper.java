package com.financial.indicators.external.openfinancedata;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;
import com.financial.indicators.models.StockData;

@Component
public class OpenFinanceMapper {

    public StockData toStockData(String symbol, FundamentalsResponse dto) {

        var result = dto.getQuoteSummary().getResult()[0];

        StockData data = new StockData();
        data.setSymbol(symbol);
        data.setPrice(result.getFinancialData().getCurrentPrice().getRaw());
        data.setEpsTtm(result.getDefaultKeyStatistics().getTrailingEps().getRaw());
        data.setDividendTtm(result.getSummaryDetail().getTrailingAnnualDividendRate().getRaw());
        data.setPriceEarnings(result.getSummaryDetail().getTrailingPE().getRaw());

        return data;
    }
}
