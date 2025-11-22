package com.financial.indicators.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.financial.indicators.external.brapi.dto.BrapiResponse;
import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators indicators = new StockIndicators();

        indicators.setSymbol(data.getSymbol());
        indicators.setPrice(data.getPrice());

        // -----------------------------
        // 1. Earnings Yield
        // -----------------------------
        if (data.getEps() != null && data.getPrice() != null) {
            indicators.setEarningsYield((data.getEps() / data.getPrice()) * 100);
        }

        // -----------------------------
        // 2. Dividend TTM (somatório dos últimos 12 meses)
        // -----------------------------
        Double dividendTtm = null;

        if (data.getCashDividends() != null && !data.getCashDividends().isEmpty()) {

            LocalDate now = LocalDate.now();
            LocalDate cutoff = now.minusYears(1);

            double total = data.getCashDividends()
                    .stream()
                    .filter(div -> div.getPaymentDate() != null) // precisa ter data
                    .filter(div -> {
                        try {
                            // parser robusto
                            LocalDate date = OffsetDateTime.parse(div.getPaymentDate()).toLocalDate();

                            return !date.isAfter(now) // não pode ser futuro
                                    && !date.isBefore(cutoff); // apenas últimos 12 meses

                        } catch (Exception e) {
                            return false; // ignora datas ruins
                        }
                    })
                    .mapToDouble(div -> div.getRate() != null ? div.getRate() : 0.0)
                    .sum();

            if (total > 0) {
                dividendTtm = total;
            }
        }

        data.setDividendTtm(dividendTtm);

        // -----------------------------
        // 3. Dividend Yield = TTM / price
        // -----------------------------
        if (dividendTtm != null && data.getPrice() != null) {
            indicators.setDividendYield((dividendTtm / data.getPrice()) * 100);
        }

        // -----------------------------
        // 4. Price/Earnings (P/L)
        // -----------------------------
        if (data.getEps() != null && data.getEps() != 0) {
            indicators.setPriceEarnings(data.getPrice() / data.getEps());
        }

        return indicators;
    }
}
