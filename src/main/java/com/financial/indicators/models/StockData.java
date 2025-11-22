package com.financial.indicators.models;

import java.util.List;

import com.financial.indicators.external.brapi.dto.BrapiResponse;

import lombok.Data;

@Data
public class StockData {

    private String symbol;
    private Double price;
    private Double eps;

    // Lista de dividendos brutos vindos da BRAPI
    private List<BrapiResponse.Result.CashDividend> cashDividends;

    // TTM calculado na service
    private Double dividendTtm;
}
