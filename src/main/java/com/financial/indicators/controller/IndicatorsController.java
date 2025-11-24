package com.financial.indicators.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.financial.indicators.external.openfinancedata.OpenFinanceDataClient;
import com.financial.indicators.external.openfinancedata.dto.FinancialDataResponse;
import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;
import com.financial.indicators.external.openfinancedata.mappers.FinancialDataMapper;
import com.financial.indicators.external.openfinancedata.mappers.FundamentalsMapper;
import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.service.IndicatorsService;

@RestController
public class IndicatorsController {

    private final OpenFinanceDataClient client;
    private final FundamentalsMapper mapperFundamentals;
    private final FinancialDataMapper mapperFinancialData;
    private final IndicatorsService service;

    public IndicatorsController(OpenFinanceDataClient client, FundamentalsMapper mapperFundamentals, FinancialDataMapper mapperFinancialData, IndicatorsService service) {
        this.client = client;
        this.mapperFundamentals = mapperFundamentals;
        this.mapperFinancialData = mapperFinancialData;
        this.service = service;
    }

    @GetMapping("/api/stock/{symbol}")
    public ResponseEntity<StockIndicators> getIndicators(@PathVariable String symbol) {

        FundamentalsResponse fundamentals = client.getFundamentals(symbol);
        FinancialDataResponse financialData = client.getFinancialData(symbol);

        StockData data = new StockData();
        data.setSymbol(symbol);

        data = mapperFundamentals.toStockData(data, fundamentals);
        data = mapperFinancialData.toStockData(data, financialData);
        
        StockIndicators indicators = service.calculate(data);

        return ResponseEntity.ok(indicators);
    }
}
