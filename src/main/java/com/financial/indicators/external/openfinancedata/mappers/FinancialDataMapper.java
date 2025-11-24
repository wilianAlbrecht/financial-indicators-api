package com.financial.indicators.external.openfinancedata.mappers;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.FinancialDataResponse;
import com.financial.indicators.models.StockData;

@Component
public class FinancialDataMapper {

    public StockData toStockData(StockData data, FinancialDataResponse dto) {

        var fin = dto.getQuoteSummary().getResult()[0].getFinancialData();

        // =====================================================
        // PRICE & TARGETS
        // =====================================================
        if (fin.getCurrentPrice() != null)
            data.setPrice(fin.getCurrentPrice().getRaw());

        if (fin.getTargetHighPrice() != null)
            data.setTargetHighPrice(fin.getTargetHighPrice().getRaw());

        if (fin.getTargetLowPrice() != null)
            data.setTargetLowPrice(fin.getTargetLowPrice().getRaw());

        if (fin.getTargetMeanPrice() != null)
            data.setTargetMeanPrice(fin.getTargetMeanPrice().getRaw());

        if (fin.getTargetMedianPrice() != null)
            data.setTargetMedianPrice(fin.getTargetMedianPrice().getRaw());

        if (fin.getRecommendationMean() != null)
            data.setRecommendationMean(fin.getRecommendationMean().getRaw());

        if (fin.getNumberOfAnalystOpinions() != null)
            data.setNumberOfAnalystOpinions(fin.getNumberOfAnalystOpinions().getRaw());

        // =====================================================
        // CASH
        // =====================================================
        if (fin.getTotalCash() != null)
            data.setTotalCash(fin.getTotalCash().getRaw());

        if (fin.getTotalCashPerShare() != null)
            data.setTotalCashPerShare(fin.getTotalCashPerShare().getRaw());

        // =====================================================
        // DEBT
        // =====================================================
        if (fin.getTotalDebt() != null)
            data.setTotalDebt(fin.getTotalDebt().getRaw());

        if (fin.getDebtToEquity() != null)
            data.setDebtToEquity(fin.getDebtToEquity().getRaw());

        // =====================================================
        // CASHFLOWS
        // =====================================================
        if (fin.getFreeCashflow() != null)
            data.setFreeCashFlow(fin.getFreeCashflow().getRaw());

        if (fin.getOperatingCashflow() != null)
            data.setOperatingCashflow(fin.getOperatingCashflow().getRaw());

        // =====================================================
        // INCOME STATEMENT
        // =====================================================
        if (fin.getTotalRevenue() != null)
            data.setTotalRevenue(fin.getTotalRevenue().getRaw());

        if (fin.getEbitda() != null)
            data.setEbitda(fin.getEbitda().getRaw());

        if (fin.getGrossProfits() != null)
            data.setGrossProfits(fin.getGrossProfits().getRaw());

        // =====================================================
        // GROWTH
        // =====================================================
        if (fin.getEarningsGrowth() != null)
            data.setEarningsGrowth(fin.getEarningsGrowth().getRaw());

        if (fin.getRevenueGrowth() != null)
            data.setRevenueGrowth(fin.getRevenueGrowth().getRaw());

        // =====================================================
        // MARGINS
        // =====================================================
        if (fin.getGrossMargins() != null)
            data.setGrossMargin(fin.getGrossMargins().getRaw());

        if (fin.getOperatingMargins() != null)
            data.setOperatingMargin(fin.getOperatingMargins().getRaw());

        if (fin.getEbitdaMargins() != null)
            data.setEbitdaMargin(fin.getEbitdaMargins().getRaw());

        // =====================================================
        // RATIOS
        // =====================================================
        if (fin.getQuickRatio() != null)
            data.setQuickRatio(fin.getQuickRatio().getRaw());

        if (fin.getCurrentRatio() != null)
            data.setCurrentRatio(fin.getCurrentRatio().getRaw());

        if (fin.getPriceToSalesTrailing12Months() != null)
            data.setPriceToSalesTrailing12Months(fin.getPriceToSalesTrailing12Months().getRaw());

        // =====================================================
        // PER SHARE
        // =====================================================
        if (fin.getRevenuePerShare() != null)
            data.setRevenuePerShare(fin.getRevenuePerShare().getRaw());

        return data;
    }
}
