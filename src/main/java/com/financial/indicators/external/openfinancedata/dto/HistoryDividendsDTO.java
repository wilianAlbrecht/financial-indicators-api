package com.financial.indicators.external.openfinancedata.dto;

import java.util.Map;

import lombok.Data;

@Data
public class HistoryDividendsDTO {

    private Chart chart;

    @Data
    public static class Chart{
        private Result[] result;
        private Object error;
    }

    @Data
    public static class Result{
        private Events events;
    }

    @Data
    public static class Events{
        private Map<String, DividendsData> dividends;
    }

    @Data
    public static class DividendsData{
        private String date;
        private String amount;  
    }




    
}
