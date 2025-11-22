package com.financial.indicators.external.brapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class BrapiResponse {

    private List<Result> results;

    @Data
    public static class Result {
        private String symbol;
        private Double regularMarketPrice;
        private Double earningsPerShare;
        private Double dividendYield;
        private Double trailingAnnualDividendRate;
    }
}
