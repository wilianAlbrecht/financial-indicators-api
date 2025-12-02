package com.financial.indicators.external.openfinancedata;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.financial.indicators.external.openfinancedata.dtos.FundamentalsDTO;
import com.financial.indicators.external.openfinancedata.dtos.HistoryDividendsDTO;

@Component
public class OpenFinanceDataClient {

    private final WebClient webClient;

    // injeta por tipo, simples
    public OpenFinanceDataClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public FundamentalsDTO getFundamentals(String symbol) {
        return webClient.get()
                .uri("/fundamentals/{symbol}?mode=unified", symbol)
                .retrieve()
                .bodyToMono(FundamentalsDTO.class)
                .block();
    }

    public HistoryDividendsDTO getDividends(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/history/{symbol}")
                        .queryParam("range", "12mo")
                        .queryParam("interval", "1mo")
                        .queryParam("events", "div")
                        .queryParam("mode", "unified")
                        .build(symbol))
                .retrieve()
                .bodyToMono(HistoryDividendsDTO.class)
                .block();
    }

}
