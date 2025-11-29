package com.financial.indicators.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.financial.indicators.external.openfinancedata.OpenFinanceDataClient;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.mappers.FundamentalsMapper;
import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.service.IndicatorsService;

@RestController
public class IndicatorsController {

    private final OpenFinanceDataClient client;
    private final FundamentalsMapper mapperFundamentals;
    private final IndicatorsService service;

    public IndicatorsController(OpenFinanceDataClient client, FundamentalsMapper mapperFundamentals, IndicatorsService service) {
        this.client = client;
        this.mapperFundamentals = mapperFundamentals;
        this.service = service;
    }

    @GetMapping("/api/stock/{symbol}")
    public ResponseEntity<StockIndicators> getIndicators(@PathVariable String symbol) {

        FundamentalsDTO fundamentals = client.getFundamentals(symbol);

        StockData data = new StockData();
        data.setSymbol(symbol);

        data = mapperFundamentals.toStockData(data, fundamentals);
        
        StockIndicators indicators = service.calculate(data);

        return ResponseEntity.ok(indicators);
    }
}
