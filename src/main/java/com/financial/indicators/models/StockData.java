package com.financial.indicators.models;

import lombok.Data;

@Data
public class StockData {

    private String symbol;
    private Double price;

    // EPS TTM (trailing earnings per share)
    private Double epsTtm;

    // Dividend TTM (total dividends last 12 months)
    private Double dividendTtm;

    // ========== Fundamental Fields ==========
    private Double priceToBook;
    private Double profitMargin;
    private Double returnOnAssets;
    private Double returnOnEquity;

    private Double enterpriseValue;
    private Double sharesOutstanding;

    // ========== Financial Data ==========
    private Double totalRevenue;
    private Double ebitda;

    // ========== Margins ==========
    private Double grossMargin;
    private Double operatingMargin;

    // EBITDA Margin será calculado no service, não precisa ser salvo bruto
    // mas deixamos espaço se quiser usar em debug
    // private Double ebitdaMargin;

    // ========== Growth ==========
    private Double revenueGrowth;
    private Double earningsGrowth;

    // ========== Liquidity Ratios ==========
    private Double quickRatio;
    private Double currentRatio;
}
