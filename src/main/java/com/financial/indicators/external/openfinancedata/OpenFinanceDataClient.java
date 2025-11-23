package com.financial.indicators.external.openfinancedata;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.financial.indicators.external.openfinancedata.dto.FundamentalsResponse;

@Component
public class OpenFinanceDataClient {

    private final WebClient webClient;

    // injeta por tipo, simples
    public OpenFinanceDataClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public FundamentalsResponse getFundamentals(String symbol) {
        return webClient.get()
                .uri("/fundamentals/{symbol}", symbol)
                .retrieve()
                .bodyToMono(FundamentalsResponse.class)
                .block();
    }
}
