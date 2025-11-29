package com.financial.indicators.models;

import java.math.BigDecimal;

import com.financial.indicators.config.StockIndicatorsSerializer;

import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = StockIndicatorsSerializer.class)
@Data
public class StockIndicators {

    private String symbol;

    private BigDecimal price;

    // ========================= DIVIDENDOS ================================
    private BigDecimal exDividendDate; 
    private BigDecimal fiveYearAvgDividendYield;   
    private BigDecimal dividendTtm;             
    private BigDecimal lastDividendValue;
    private BigDecimal dividendRate;
    private BigDecimal trailingAnnualDividendYield;
    private BigDecimal dividendYield;               
    private BigDecimal lastDividendDate;

    // ======================== LUCROS (EPS) ================================
    private BigDecimal epsTtm;
    private BigDecimal forwardEps;

    // ======================= VALUATION ==================================
    private BigDecimal priceToBook;                 
    private BigDecimal bookValue;               
    private BigDecimal enterpriseValue;
    private BigDecimal enterpriseToRevenue;
    private BigDecimal enterpriseToEbitda;
    private BigDecimal profitMargin;
    private BigDecimal marketCap;               

    // ======================= RENTABILIDADE ===============================
    private BigDecimal returnOnAssets;
    private BigDecimal returnOnEquity;

    // ======================= ESTRUTURA DE CAPITAL ========================
    private BigDecimal totalDebt;
    private BigDecimal debtToEquity;
    private BigDecimal sharesOutstanding;

    // ======================= CRESCIMENTO ================================
    private BigDecimal revenueGrowth;
    private BigDecimal earningsGrowth;

    // ======================= MARGENS ====================================
    private BigDecimal grossMargin;
    private BigDecimal operatingMargin;
    private BigDecimal ebitdaMargin;
    private BigDecimal grossProfits;

    // ======================= FLUXO DE CAIXA ==============================
    private BigDecimal operatingCashflow;
    private BigDecimal freeCashFlow;
    private BigDecimal totalCash;
    private BigDecimal totalCashPerShare;

    // ======================= RECEITAS ====================================
    private BigDecimal totalRevenue;
    private BigDecimal revenuePerShare;

    // ======================= ANALISTAS ===================================
    private BigDecimal targetHighPrice;
    private BigDecimal targetLowPrice;
    private BigDecimal targetMeanPrice;
    private BigDecimal targetMedianPrice;
    private BigDecimal recommendationMean;
    private BigDecimal numberOfAnalystOpinions;

    // ======================= MERCADO / DADOS GERAIS ======================
    private BigDecimal previousClose;
    private BigDecimal fiftyTwoWeekHigh;
    private BigDecimal fiftyTwoWeekLow;
    private BigDecimal beta;
    private BigDecimal averageVolume;
    private BigDecimal volume;

    // ======================== INDICADORES CALCULADOS ======================
    private BigDecimal earningsYield;
    private BigDecimal priceToEarnings;
    private BigDecimal priceToSales;
    private BigDecimal cashPerShare;                
    private BigDecimal operatingCashflowPerShare;
    private BigDecimal freeCashFlowYield;               
    private BigDecimal pegRatio;
}
