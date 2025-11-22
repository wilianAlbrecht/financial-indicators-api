package com.financial.indicators.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.financial.indicators.external.brapi.BrapiClient;
import com.financial.indicators.external.brapi.BrapiMapper;
import com.financial.indicators.external.brapi.dto.BrapiResponse;
import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.service.IndicatorsService;

@RestController
public class IndicatorsController {

    private final BrapiClient client;
    private final BrapiMapper mapper;
    private final IndicatorsService service;

    public IndicatorsController(BrapiClient client, BrapiMapper mapper, IndicatorsService service) {
        this.client = client;
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/api/stock/{symbol}")
public ResponseEntity<StockIndicators> getIndicators(@PathVariable String symbol) {

    BrapiResponse.Result resultDto = client.fetchQuote(symbol);
    StockData stockData = mapper.toStockData(resultDto);
    StockIndicators indicators = service.calculate(stockData);

    return ResponseEntity.ok(indicators);
}
}
