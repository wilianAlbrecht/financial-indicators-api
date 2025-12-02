package com.financial.indicators.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.financial.indicators.external.openfinancedata.dtos.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.dtos.HistoryDividendsDTO;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(FundamentalsDTO fundamentals, HistoryDividendsDTO dividends) {

        StockIndicators indicadores = new StockIndicators();

        // indicadores que ja vem do OpenFinanceData sem necessidade de calculos

        // Identificação
        indicadores.setSymbol(fundamentals.getSymbol());
        indicadores.setCurrentPrice(fundamentals.getCurrentPrice());

        // ========================= DIVIDENDOS ================================
        indicadores.setExDividendDate(fundamentals.getExDividendDate());
        indicadores.setFiveYearAvgDividendYield(fundamentals.getFiveYearAvgDividendYield());
        indicadores.setDividendForward(fundamentals.getDividendForward());
        indicadores.setLastDividendValue(fundamentals.getLastDividendValue());
        indicadores.setDividendRate(fundamentals.getDividendRate());
        indicadores.setDividendForward(fundamentals.getTrailingAnnualDividendRate());
        indicadores.setTrailingAnnualDividendYield(fundamentals.getTrailingAnnualDividendYield());
        indicadores.setDividendYield(fundamentals.getDividendYield());
        indicadores.setLastDividendDate(fundamentals.getLastDividendDate());

        // ======================== LUCROS (EPS) ================================
        indicadores.setTrailingEps(fundamentals.getTrailingEps());
        indicadores.setForwardEps(fundamentals.getForwardEps());

        // ======================= VALUATION ==================================
        indicadores.setPriceToBook(fundamentals.getPriceToBook());
        indicadores.setBookValue(fundamentals.getBookValue());
        indicadores.setEnterpriseValue(fundamentals.getEnterpriseValue());
        indicadores.setEnterpriseToRevenue(fundamentals.getEnterpriseToRevenue());
        indicadores.setEnterpriseToEbitda(fundamentals.getEnterpriseToEbitda());
        indicadores.setProfitMargins(fundamentals.getProfitMargins());

        // ======================= RENTABILIDADE ===============================
        indicadores.setReturnOnAssets(fundamentals.getReturnOnAssets());
        indicadores.setReturnOnEquity(fundamentals.getReturnOnEquity());

        // ======================= ESTRUTURA DE CAPITAL ========================
        indicadores.setTotalDebt(fundamentals.getTotalDebt());
        indicadores.setDebtToEquity(fundamentals.getDebtToEquity());
        indicadores.setSharesOutstanding(fundamentals.getSharesOutstanding());

        // ======================= CRESCIMENTO ================================
        indicadores.setRevenueGrowth(fundamentals.getRevenueGrowth());
        indicadores.setEarningsGrowth(fundamentals.getEarningsGrowth());

        // ======================= MARGENS ====================================
        indicadores.setGrossMargins(fundamentals.getGrossMargins());
        indicadores.setOperatingMargins(fundamentals.getOperatingMargins());
        indicadores.setEbitdaMargins(fundamentals.getEbitdaMargins());
        indicadores.setGrossProfits(fundamentals.getGrossProfits());

        // ======================= FLUXO DE CAIXA ==============================
        indicadores.setOperatingCashflow(fundamentals.getOperatingCashflow());
        indicadores.setFreeCashflow(fundamentals.getFreeCashflow());
        indicadores.setTotalCash(fundamentals.getTotalCash());
        indicadores.setTotalCashPerShare(fundamentals.getTotalCashPerShare());

        // ======================= RECEITAS ====================================
        indicadores.setTotalRevenue(fundamentals.getTotalRevenue());
        indicadores.setRevenuePerShare(fundamentals.getRevenuePerShare());

        // ======================= ANALISTAS ===================================
        indicadores.setTargetHighPrice(fundamentals.getTargetHighPrice());
        indicadores.setTargetLowPrice(fundamentals.getTargetLowPrice());
        indicadores.setTargetMeanPrice(fundamentals.getTargetMeanPrice());
        indicadores.setTargetMedianPrice(fundamentals.getTargetMedianPrice());
        indicadores.setRecommendationMean(fundamentals.getRecommendationMean());
        indicadores.setNumberOfAnalystOpinions(fundamentals.getNumberOfAnalystOpinions());

        // ======================= MERCADO / DADOS GERAIS ======================
        indicadores.setPreviousClose(fundamentals.getPreviousClose());
        indicadores.setFiftyTwoWeekHigh(fundamentals.getFiftyTwoWeekHigh());
        indicadores.setFiftyTwoWeekLow(fundamentals.getFiftyTwoWeekLow());
        indicadores.setBeta(fundamentals.getBeta());
        indicadores.setAverageVolume(fundamentals.getAverageVolume());
        indicadores.setVolume(fundamentals.getVolume());

        // indicadores que precisam de calculos

        // Earnings Yield
        if (!isNullOrZero(fundamentals.getTrailingEps()) && !isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setEarningsYield(
                    bdDivide(fundamentals.getTrailingEps(), fundamentals.getCurrentPrice()));
        }

        // dividend true TTM
        BigDecimal totalDividends = BigDecimal.ZERO;

        if (dividends != null && dividends.getDividends() != null) {

            for (int x = 0; x < dividends.getDividends().size(); x++) {

                if (dividends.getDividends().get(x) != null) {
                    totalDividends = totalDividends.add(dividends.getDividends().get(x).getAmount());
                }
            }
            indicadores.setDividendTtm(totalDividends);
        }

        // dividend Yield TTM calculado
        if (!isNullOrZero(totalDividends) && !isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setDividendYieldTtm(
                    bdDivide(totalDividends, fundamentals.getCurrentPrice()));
        }

        // Market Cap Recalculado
        if (!isNullOrZero(fundamentals.getCurrentPrice())
                && !isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setMarketCap(
                    bdMultiply(fundamentals.getCurrentPrice(), fundamentals.getSharesOutstanding()));
        }

        // FREE CASH FLOW YIELD
        if (!isNullOrZero(fundamentals.getFreeCashflow())
                && !isNullOrZero(indicadores.getMarketCap())) {

            indicadores.setFreeCashFlowYield(
                    bdDivide(fundamentals.getFreeCashflow(), indicadores.getMarketCap()));
        }

        // PEG Ratio
        if (!isNullOrZero(fundamentals.getCurrentPrice())
                && !isNullOrZero(fundamentals.getTrailingEps())
                && !isNullOrZero(fundamentals.getEarningsGrowth())) {

            BigDecimal pe = bdDivide(fundamentals.getCurrentPrice(), fundamentals.getTrailingEps());

            if (!isNullOrZero(pe)) {
                indicadores.setPegRatio(
                        bdDivide(pe, fundamentals.getEarningsGrowth()));
            }
        }

        // Price/Earnings (P/E)
        if (!isNullOrZero(fundamentals.getTrailingEps()) && !isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setPriceToEarnings(
                    bdDivide(fundamentals.getCurrentPrice(), fundamentals.getTrailingEps()));
        }

        // Price/Sales (P/S)
        if (!isNullOrZero(fundamentals.getTotalRevenue())
                && !isNullOrZero(fundamentals.getSharesOutstanding())
                && !isNullOrZero(fundamentals.getCurrentPrice())) {

            BigDecimal revenuePerShare = bdDivide(fundamentals.getTotalRevenue(), fundamentals.getSharesOutstanding());

            indicadores.setPriceToSales(
                    bdDivide(fundamentals.getCurrentPrice(), revenuePerShare));
        }

        // Cash Per Share
        if (!isNullOrZero(fundamentals.getTotalCash())
                && !isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setCashPerShare(
                    bdDivide(fundamentals.getTotalCash(), fundamentals.getSharesOutstanding()));
        }

        // Operating Cashflow Per Share
        if (!isNullOrZero(fundamentals.getOperatingCashflow())
                && !isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setOperatingCashflowPerShare(
                    bdDivide(fundamentals.getOperatingCashflow(), fundamentals.getSharesOutstanding()));
        }

        return indicadores;
    }

    // todo: fazer um interface util para BigDecimals

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
