package com.financial.indicators.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.financial.indicators.config.JsonFormatter;
import com.financial.indicators.external.openfinancedata.OpenFinanceDataClient;
import com.financial.indicators.external.openfinancedata.dtos.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.dtos.HistoryDividendsDTO;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.service.IndicatorsService;

@RestController
public class IndicatorsController {

    private final OpenFinanceDataClient client;
    private final IndicatorsService service;

    public IndicatorsController(OpenFinanceDataClient client, IndicatorsService service) {
        this.client = client;
        this.service = service;
    }

    @GetMapping("/api/stock/{symbol}")
    public ResponseEntity<String> getIndicators(@PathVariable String symbol) {

        FundamentalsDTO fundamentals = client.getFundamentals(symbol);
        HistoryDividendsDTO dividends = client.getDividends(symbol);

        fundamentals.setSymbol(symbol);

        StockIndicators indicators = service.calculate(fundamentals, dividends);

        String json = JsonFormatter.stringify(indicators);

        return ResponseEntity.ok(json);
    }
}
