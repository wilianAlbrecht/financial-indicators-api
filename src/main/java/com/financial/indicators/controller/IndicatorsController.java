package com.financial.indicators.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.financial.indicators.external.openfinancedata.OpenFinanceDataClient;
import com.financial.indicators.external.openfinancedata.OpenFinanceMapper;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;
import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.service.IndicatorsService;

@RestController
public class IndicatorsController {

    private final OpenFinanceDataClient client;
    private final OpenFinanceMapper mapper;
    private final IndicatorsService service;

    public IndicatorsController(OpenFinanceDataClient client, OpenFinanceMapper mapper, IndicatorsService service) {
        this.client = client;
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/api/stock/{symbol}")
    public ResponseEntity<StockIndicators> getIndicators(@PathVariable String symbol) {

        FundamentalsResponse fundamentals = client.getFundamentals(symbol);

        StockData data = mapper.toStockData(symbol, fundamentals);

        StockIndicators indicators = service.calculate(data);

        return ResponseEntity.ok(indicators);
    }
}
