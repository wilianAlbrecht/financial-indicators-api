package com.financial.indicators.service;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators ind = new StockIndicators();
        if (data == null)
            return ind;

        // ==============================================
        // BASIC FIELDS
        // ==============================================
        ind.setSymbol(data.getSymbol());
        ind.setPrice(data.getPrice());

        Double price = data.getPrice();
        Double eps = data.getEpsTtm();
        Double dividendTtm = data.getDividendTtm();
        Double shares = data.getSharesOutstanding();
        Double revenue = data.getTotalRevenue();
        Double ebitda = data.getEbitda();
        Double enterpriseValue = data.getEnterpriseValue();

        // ==============================================
        // BASIC INDICATORS
        // ==============================================

        if (eps != null && price != null && price != 0) {
            ind.setEarningsYield((eps / price) * 100);
        }

        if (dividendTtm != null && price != null && price != 0) {
            ind.setDividendYield((dividendTtm / price) * 100);
        }

        if (eps != null && eps != 0 && price != null) {
            ind.setPriceEarnings(price / eps);
        }

        // ==============================================
        // PASS-THROUGH FUNDAMENTALS
        // ==============================================
        ind.setPriceToBook(data.getPriceToBook());
        ind.setProfitMargin(data.getProfitMargin());
        ind.setReturnOnAssets(data.getReturnOnAssets());
        ind.setReturnOnEquity(data.getReturnOnEquity());
        ind.setCurrentRatio(data.getCurrentRatio());
        ind.setQuickRatio(data.getQuickRatio());
        ind.setGrossMargin(data.getGrossMargin());
        ind.setOperatingMargin(data.getOperatingMargin());
        ind.setRevenueGrowth(data.getRevenueGrowth());
        ind.setEarningsGrowth(data.getEarningsGrowth());

        // ==============================================
        // MARKET CAP
        // ==============================================
        if (price != null && shares != null) {
            ind.setMarketCap(price * shares);
        }

        // ==============================================
        // PRICE / SALES
        // ==============================================
        if (price != null && revenue != null && revenue != 0 && shares != null && shares != 0) {
            double revenuePerShare = revenue / shares;
            if (revenuePerShare != 0) {
                ind.setPsRatio(price / revenuePerShare);
            }
        }

        // ==============================================
        // EV / EBITDA
        // ==============================================
        if (enterpriseValue != null && ebitda != null && ebitda != 0) {
            ind.setEvEbitda(enterpriseValue / ebitda);
        }

        // ==============================================
        // EV / REVENUE
        // ==============================================
        if (enterpriseValue != null && revenue != null && revenue != 0) {
            ind.setEvRevenue(enterpriseValue / revenue);
        }

        // ==============================================
        // EBITDA MARGIN
        // ==============================================
        if (ebitda != null && revenue != null && revenue != 0) {
            ind.setEbitdaMargin(ebitda / revenue);
        }

        // ==============================================
        // PEG RATIO
        // ==============================================
        if (ind.getPriceEarnings() != null &&
                data.getEarningsGrowth() != null &&
                data.getEarningsGrowth() != 0) {

            ind.setPegRatio(ind.getPriceEarnings() / data.getEarningsGrowth());
        }

        // ==============================================
        // FINANCIAL DATA COPY (NEW)
        // ==============================================

        ind.setTotalCash(data.getTotalCash());
        ind.setTotalDebt(data.getTotalDebt());
        ind.setGrossProfits(data.getGrossProfits());
        ind.setRecommendationMean(data.getRecommendationMean());
        ind.setNumberOfAnalystOpinions(data.getNumberOfAnalystOpinions());
        ind.setTargetHighPrice(data.getTargetHighPrice());
        ind.setTargetLowPrice(data.getTargetLowPrice());
        ind.setTargetMeanPrice(data.getTargetMeanPrice());
        ind.setTargetMedianPrice(data.getTargetMedianPrice());
        ind.setFreeCashFlow(data.getFreeCashFlow());

        // ==============================================
        // CALCULATED FINANCIAL DATA INDICATORS (NEW)
        // ==============================================

        // Cash Per Share
        if (data.getTotalCash() != null &&
                shares != null && shares != 0) {

            ind.setCashPerShare(data.getTotalCash() / shares);
        }

        // Free Cash Flow Yield
        if (ind.getMarketCap() != null &&
                data.getFreeCashFlow() != null &&
                ind.getMarketCap() != 0) {

            ind.setFreeCashFlowYield(data.getFreeCashFlow() / ind.getMarketCap());
        }

        // Operating Cashflow Per Share
        if (data.getOperatingCashflow() != null &&
                shares != null && shares != 0) {

            ind.setOperatingCashflowPerShare(data.getOperatingCashflow() / shares);
        }

        // EV / FCF
        if (enterpriseValue != null &&
                data.getFreeCashFlow() != null &&
                data.getFreeCashFlow() != 0) {

            ind.setEvFcf(enterpriseValue / data.getFreeCashFlow());
        }

        // ROIC = FCF / EV
        if (data.getFreeCashFlow() != null &&
                enterpriseValue != null &&
                enterpriseValue != 0) {

            ind.setRoic(data.getFreeCashFlow() / enterpriseValue);
        }

        // Gross Profit Margin
        if (data.getGrossProfits() != null &&
                revenue != null &&
                revenue != 0) {

            ind.setGrossProfitMargin(data.getGrossProfits() / revenue);
        }

        // =============================================================
        // NOVOS CÁLCULOS – INDICADORES AVANÇADOS
        // =============================================================

        // ---------------------------------------
        // 1. Dividend Payout Ratio
        // ---------------------------------------
        if (eps != null && eps != 0 && dividendTtm != null) {
            ind.setDividendPayoutRatio(dividendTtm / eps);
        }

        // ---------------------------------------
        // 2. D/E (Debt-to-Equity) Recalculado
        // ---------------------------------------
        Double bookValue = data.getBookValue();
        Double equity = null;

        if (bookValue != null && shares != null) {
            equity = bookValue * shares;
        }

        Double totalDebt = data.getTotalDebt();
        if (totalDebt != null && equity != null && equity != 0) {
            ind.setDebtToEquityCalculated(totalDebt / equity);
        }

        // ---------------------------------------
        // 3. ROI (Return on Investment)
        // ROI = NetIncome / (Debt + Equity)
        // ---------------------------------------
        Double netIncome = data.getNetIncomeToCommon();

        if (netIncome != null && totalDebt != null && equity != null && (equity + totalDebt) != 0) {
            ind.setRoi(netIncome / (equity + totalDebt));
        }

        // ---------------------------------------
        // 4. EV / FCFE
        // FCFE ≈ FCF (aproximação simplificada)
        // ---------------------------------------
        Double fcf = data.getFreeCashFlow();
        if (enterpriseValue != null && fcf != null && fcf != 0) {
            ind.setEvFcfe(enterpriseValue / fcf);
        }

        // ---------------------------------------
        // 5. ROIC Avançado
        // ROIC = NOPAT / InvestedCapital
        // NOPAT ≈ NetIncome * (1 - TaxRate)
        // TaxRate fixo BR = 34%
        // ---------------------------------------
        Double taxRate = 0.34;

        if (netIncome != null && equity != null && totalDebt != null) {
            Double nopat = netIncome * (1 - taxRate);
            Double investedCapital = equity + totalDebt;

            if (investedCapital != 0) {
                ind.setRoicAdvanced(nopat / investedCapital);
            }
        }

        // ---------------------------------------
        // 6. Forward P/E
        // forwardPE = Price / ForwardEPS
        // ---------------------------------------
        Double forwardEps = data.getForwardEps();
        if (forwardEps != null && forwardEps != 0 && price != null) {
            ind.setForwardPeCalculated(price / forwardEps);
        }

        // ---------------------------------------
        // 7. DPS Aprimorado
        // DPS = DividendRate se existir → senão DividendTTM
        // ---------------------------------------
        Double dividendRate = data.getDividendRate();
        if (dividendRate != null) {
            ind.setDps(dividendRate);
        } else if (dividendTtm != null) {
            ind.setDps(dividendTtm);
        }

        return ind;
    }
}
