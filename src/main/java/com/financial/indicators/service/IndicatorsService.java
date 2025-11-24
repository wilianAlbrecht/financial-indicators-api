package com.financial.indicators.service;

import org.springframework.stereotype.Service;

import com.financial.indicators.models.StockData;
import com.financial.indicators.models.StockIndicators;

@Service
public class IndicatorsService {

    public StockIndicators calculate(StockData data) {

        StockIndicators ind = new StockIndicators();
        if (data == null) return ind;

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

        return ind;
    }
}
