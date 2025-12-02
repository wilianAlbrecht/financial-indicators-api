package com.financial.indicators.external.openfinancedata.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class HistoryDividendsDTO {

    private List<Dividends> dividends;

    @Data
    public static class Dividends {
        private BigDecimal amount;
    }

}
