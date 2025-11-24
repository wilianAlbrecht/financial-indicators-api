package com.financial.indicators.external.openfinancedata.mappers;

import org.springframework.stereotype.Component;

import com.financial.indicators.external.openfinancedata.dto.FinancialDataResponse;
import com.financial.indicators.models.StockData;

@Component
public class FinancialDataMapper {

    public StockData toStockData(StockData data, FinancialDataResponse dto) {

        if (data == null || dto == null ||
            dto.getQuoteSummary() == null ||
            dto.getQuoteSummary().getResult() == null ||
            dto.getQuoteSummary().getResult().length == 0 ||
            dto.getQuoteSummary().getResult()[0] == null ||
            dto.getQuoteSummary().getResult()[0].getFinancialData() == null) {

            return data;
        }

        var fin = dto.getQuoteSummary().getResult()[0].getFinancialData();

        // =====================================================
        // CASH
        // =====================================================
        if (fin.getTotalCash() != null) {
            data.setTotalCash(fin.getTotalCash().getRaw());
        }

        if (fin.getTotalCashPerShare() != null) {
            data.setTotalCashPerShare(fin.getTotalCashPerShare().getRaw());
        }

        // =====================================================
        // DEBT
        // =====================================================
        if (fin.getTotalDebt() != null) {
            data.setTotalDebt(fin.getTotalDebt().getRaw());
        }

        // =====================================================
        // CASHFLOWS
        // =====================================================
        if (fin.getFreeCashflow() != null) {
            data.setFreeCashFlow(fin.getFreeCashflow().getRaw());
        }

        if (fin.getOperatingCashflow() != null) {
            data.setOperatingCashflow(fin.getOperatingCashflow().getRaw());
        }

        // =====================================================
        // GROSS PROFITS
        // =====================================================
        if (fin.getGrossProfits() != null) {
            data.setGrossProfits(fin.getGrossProfits().getRaw());
        }

        // =====================================================
        // TARGET PRICES
        // =====================================================
        if (fin.getTargetHighPrice() != null) {
            data.setTargetHighPrice(fin.getTargetHighPrice().getRaw());
        }

        if (fin.getTargetLowPrice() != null) {
            data.setTargetLowPrice(fin.getTargetLowPrice().getRaw());
        }

        if (fin.getTargetMeanPrice() != null) {
            data.setTargetMeanPrice(fin.getTargetMeanPrice().getRaw());
        }

        if (fin.getTargetMedianPrice() != null) {
            data.setTargetMedianPrice(fin.getTargetMedianPrice().getRaw());
        }

        // =====================================================
        // ANALYST RATINGS
        // =====================================================
        if (fin.getRecommendationMean() != null) {
            data.setRecommendationMean(fin.getRecommendationMean().getRaw());
        }

        if (fin.getNumberOfAnalystOpinions() != null) {
            data.setNumberOfAnalystOpinions(fin.getNumberOfAnalystOpinions().getRaw());
        }

        return data;
    }
}
