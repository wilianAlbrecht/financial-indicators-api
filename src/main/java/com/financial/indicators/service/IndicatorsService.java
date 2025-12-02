package com.financial.indicators.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.financial.indicators.external.openfinancedata.dtos.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.dtos.HistoryDividendsDTO;
import com.financial.indicators.models.StockIndicators;
import com.financial.indicators.utils.Utils;

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
        if (!Utils.isNullOrZero(fundamentals.getTrailingEps()) && !Utils.isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setEarningsYield(
                    Utils.bdDivide(fundamentals.getTrailingEps(), fundamentals.getCurrentPrice()));
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
        if (!Utils.isNullOrZero(totalDividends) && !Utils.isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setDividendYieldTtm(
                    Utils.bdDivide(totalDividends, fundamentals.getCurrentPrice()));
        }

        // Market Cap Recalculado
        if (!Utils.isNullOrZero(fundamentals.getCurrentPrice())
                && !Utils.isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setMarketCap(
                    Utils.bdMultiply(fundamentals.getCurrentPrice(), fundamentals.getSharesOutstanding()));
        }

        // FREE CASH FLOW YIELD
        if (!Utils.isNullOrZero(fundamentals.getFreeCashflow())
                && !Utils.isNullOrZero(indicadores.getMarketCap())) {

            indicadores.setFreeCashFlowYield(
                    Utils.bdDivide(fundamentals.getFreeCashflow(), indicadores.getMarketCap()));
        }

        // PEG Ratio
        if (!Utils.isNullOrZero(fundamentals.getCurrentPrice())
                && !Utils.isNullOrZero(fundamentals.getTrailingEps())
                && !Utils.isNullOrZero(fundamentals.getEarningsGrowth())) {

            BigDecimal pe = Utils.bdDivide(fundamentals.getCurrentPrice(), fundamentals.getTrailingEps());

            if (!Utils.isNullOrZero(pe)) {
                indicadores.setPegRatio(
                        Utils.bdDivide(pe, fundamentals.getEarningsGrowth()));
            }
        }

        // Price/Earnings (P/E)
        if (!Utils.isNullOrZero(fundamentals.getTrailingEps()) && !Utils.isNullOrZero(fundamentals.getCurrentPrice())) {
            indicadores.setPriceToEarnings(
                    Utils.bdDivide(fundamentals.getCurrentPrice(), fundamentals.getTrailingEps()));
        }

        // Price/Sales (P/S)
        if (!Utils.isNullOrZero(fundamentals.getTotalRevenue())
                && !Utils.isNullOrZero(fundamentals.getSharesOutstanding())
                && !Utils.isNullOrZero(fundamentals.getCurrentPrice())) {

            BigDecimal revenuePerShare = Utils.bdDivide(fundamentals.getTotalRevenue(), fundamentals.getSharesOutstanding());

            indicadores.setPriceToSales(
                    Utils.bdDivide(fundamentals.getCurrentPrice(), revenuePerShare));
        }

        // Cash Per Share
        if (!Utils.isNullOrZero(fundamentals.getTotalCash())
                && !Utils.isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setCashPerShare(
                    Utils.bdDivide(fundamentals.getTotalCash(), fundamentals.getSharesOutstanding()));
        }

        // Operating Cashflow Per Share
        if (!Utils.isNullOrZero(fundamentals.getOperatingCashflow())
                && !Utils.isNullOrZero(fundamentals.getSharesOutstanding())) {

            indicadores.setOperatingCashflowPerShare(
                    Utils.bdDivide(fundamentals.getOperatingCashflow(), fundamentals.getSharesOutstanding()));
        }

        return indicadores;
    }

}
