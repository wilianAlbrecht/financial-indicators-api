package com.financial.indicators.external.openfinancedata.mappers;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.HistoryDividendsDTO;
import com.financial.indicators.external.openfinancedata.dto.HistoryDividendsDTO.DividendsData;
import com.financial.indicators.models.StockData;

@Component
public class HistoryMapper {

    public StockData toStockData(StockData data, HistoryDividendsDTO history) {

        if (history != null &&
                history.getChart() != null &&
                history.getChart().getResult() != null &&
                history.getChart().getResult().length > 0 &&
                history.getChart().getResult()[0].getEvents() != null &&
                history.getChart().getResult()[0].getEvents().getDividends() != null) {

            Map<String, DividendsData> dividends = history.getChart()
                    .getResult()[0]
                    .getEvents()
                    .getDividends();

            BigDecimal dividendTtm = BigDecimal.ZERO;
            // soma todos os registros de dividendos
            for (DividendsData d : dividends.values()) {

                BigDecimal amount = safeParse(d.getAmount()); // converte String → BigDecimal

                dividendTtm = dividendTtm.add(amount); // soma ao total
            }

            data.setDividendTrueTtm(dividendTtm);
        }

        return data;
    }

    private BigDecimal safeParse(String value) {
        try {
            if (value == null || value.isBlank())
                return BigDecimal.ZERO;
            return new BigDecimal(value.replace(",", "."));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

}
