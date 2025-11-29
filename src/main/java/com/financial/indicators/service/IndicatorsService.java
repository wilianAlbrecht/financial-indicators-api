package com.financial.indicators.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators indicadores = new StockIndicators();

        // ===========================indicadores que ja vem do
        // OpenFinanceData============================//
        // ===========================sem necessidade de
        // calculos=========================================//

        // Identificação
        indicadores.setSymbol(data.getSymbol());
        indicadores.setPrice(data.getPrice());

        // ========================= DIVIDENDOS ================================
        indicadores.setExDividendDate(data.getExDividendDate());
        indicadores.setFiveYearAvgDividendYield(data.getFiveYearAvgDividendYield());
        indicadores.setDividendForward(data.getDividendForward());
        indicadores.setLastDividendValue(data.getLastDividendValue());
        indicadores.setDividendRate(data.getDividendRate());
        indicadores.setTrailingAnnualDividendYield(data.getTrailingAnnualDividendYield());
        indicadores.setDividendYield(data.getDividendYield());
        indicadores.setLastDividendDate(data.getLastDividendDate());

        // ======================== LUCROS (EPS) ================================
        indicadores.setEpsTtm(data.getEpsTtm());
        indicadores.setForwardEps(data.getForwardEps());

        // ======================= VALUATION ==================================
        indicadores.setPriceToBook(data.getPriceToBook());
        indicadores.setBookValue(data.getBookValue());
        indicadores.setEnterpriseValue(data.getEnterpriseValue());
        indicadores.setEnterpriseToRevenue(data.getEnterpriseToRevenue());
        indicadores.setEnterpriseToEbitda(data.getEnterpriseToEbitda());
        indicadores.setProfitMargin(data.getProfitMargin());

        // ======================= RENTABILIDADE ===============================
        indicadores.setReturnOnAssets(data.getReturnOnAssets());
        indicadores.setReturnOnEquity(data.getReturnOnEquity());

        // ======================= ESTRUTURA DE CAPITAL ========================
        indicadores.setTotalDebt(data.getTotalDebt());
        indicadores.setDebtToEquity(data.getDebtToEquity());
        indicadores.setSharesOutstanding(data.getSharesOutstanding());

        // ======================= CRESCIMENTO ================================
        indicadores.setRevenueGrowth(data.getRevenueGrowth());
        indicadores.setEarningsGrowth(data.getEarningsGrowth());

        // ======================= MARGENS ====================================
        indicadores.setGrossMargin(data.getGrossMargin());
        indicadores.setOperatingMargin(data.getOperatingMargin());
        indicadores.setEbitdaMargin(data.getEbitdaMargin());
        indicadores.setGrossProfits(data.getGrossProfits());

        // ======================= FLUXO DE CAIXA ==============================
        indicadores.setOperatingCashflow(data.getOperatingCashflow());
        indicadores.setFreeCashFlow(data.getFreeCashFlow());
        indicadores.setTotalCash(data.getTotalCash());
        indicadores.setTotalCashPerShare(data.getTotalCashPerShare());

        // ======================= RECEITAS ====================================
        indicadores.setTotalRevenue(data.getTotalRevenue());
        indicadores.setRevenuePerShare(data.getRevenuePerShare());

        // ======================= ANALISTAS ===================================
        indicadores.setTargetHighPrice(data.getTargetHighPrice());
        indicadores.setTargetLowPrice(data.getTargetLowPrice());
        indicadores.setTargetMeanPrice(data.getTargetMeanPrice());
        indicadores.setTargetMedianPrice(data.getTargetMedianPrice());
        indicadores.setRecommendationMean(data.getRecommendationMean());
        indicadores.setNumberOfAnalystOpinions(data.getNumberOfAnalystOpinions());

        // ======================= MERCADO / DADOS GERAIS ======================
        indicadores.setPreviousClose(data.getPreviousClose());
        indicadores.setFiftyTwoWeekHigh(data.getFiftyTwoWeekHigh());
        indicadores.setFiftyTwoWeekLow(data.getFiftyTwoWeekLow());
        indicadores.setBeta(data.getBeta());
        indicadores.setAverageVolume(data.getAverageVolume());
        indicadores.setVolume(data.getVolume());

        // ===========================indicadores que precisam de
        // calculos================================//

        indicadores.setDividendTtm(data.getDividendTrueTtm());

        // Earnings Yield
        if (!isNullOrZero(data.getEpsTtm()) && !isNullOrZero(data.getPrice())) {
            indicadores.setEarningsYield(
                    bdDivide(data.getEpsTtm(), data.getPrice()));
        }

        //dividend Yield TTM calculado
        if (!isNullOrZero(data.getDividendTrueTtm()) && !isNullOrZero(data.getPrice())) {
            indicadores.setDividendYieldTtm(
                    bdDivide(data.getDividendTrueTtm(), data.getPrice()));
        }

        // Market Cap Recalculado
        if (!isNullOrZero(data.getPrice())
                && !isNullOrZero(data.getSharesOutstanding())) {

            indicadores.setMarketCap(
                    bdMultiply(data.getPrice(), data.getSharesOutstanding()));
        }

        // FREE CASH FLOW YIELD
        if (!isNullOrZero(data.getFreeCashFlow())
                && !isNullOrZero(indicadores.getMarketCap())) {

            indicadores.setFreeCashFlowYield(
                    bdDivide(data.getFreeCashFlow(), indicadores.getMarketCap()));
        }

        // PEG Ratio
        if (!isNullOrZero(data.getPrice())
                && !isNullOrZero(data.getEpsTtm())
                && !isNullOrZero(data.getEarningsGrowth())) {

            BigDecimal pe = bdDivide(data.getPrice(), data.getEpsTtm());

            if (!isNullOrZero(pe)) {
                indicadores.setPegRatio(
                        bdDivide(pe, data.getEarningsGrowth()));
            }
        }

        // Price/Earnings (P/E)
        if (!isNullOrZero(data.getEpsTtm()) && !isNullOrZero(data.getPrice())) {
            indicadores.setPriceToEarnings(
                    bdDivide(data.getPrice(), data.getEpsTtm()));
        }

        // Price/Sales (P/S)
        if (!isNullOrZero(data.getTotalRevenue())
                && !isNullOrZero(data.getSharesOutstanding())
                && !isNullOrZero(data.getPrice())) {

            BigDecimal revenuePerShare = bdDivide(data.getTotalRevenue(), data.getSharesOutstanding());

            indicadores.setPriceToSales(
                    bdDivide(data.getPrice(), revenuePerShare));
        }

        // Cash Per Share
        if (!isNullOrZero(data.getTotalCash())
                && !isNullOrZero(data.getSharesOutstanding())) {

            indicadores.setCashPerShare(
                    bdDivide(data.getTotalCash(), data.getSharesOutstanding()));
        }

        // Operating Cashflow Per Share
        if (!isNullOrZero(data.getOperatingCashflow())
                && !isNullOrZero(data.getSharesOutstanding())) {

            indicadores.setOperatingCashflowPerShare(
                    bdDivide(data.getOperatingCashflow(), data.getSharesOutstanding()));
        }

        return indicadores;
    }

    private boolean isNullOrZero(BigDecimal v) {
        return v == null || v.compareTo(BigDecimal.ZERO) == 0;
    }

    private BigDecimal bdDivide(BigDecimal a, BigDecimal b) {
        return a.divide(b, 12, RoundingMode.HALF_UP); // precisão padrão
    }

    private BigDecimal bdMultiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

}
