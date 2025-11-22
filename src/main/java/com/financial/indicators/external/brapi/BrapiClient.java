package com.financial.indicators.external.brapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.financial.indicators.external.brapi.dto.BrapiResponse;

@Component
public class BrapiClient {

    private final WebClient webClient;

    @Value("${brapi.token}")
    private String token;

    @Value("${brapi.base-url}")
    private String baseUrl;

    public BrapiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public BrapiResponse.Result fetchQuote(String symbol) {

        String url = String.format(
            "%s/quote/%s?token=%s&fundamental=true&dividends=true",
            baseUrl,
            symbol,
            token
        );

        BrapiResponse response = webClient
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(BrapiResponse.class)
            .block();

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            throw new RuntimeException("BRAPI: Nenhum resultado retornado para o ticker " + symbol);
        }

        return response.getResults().get(0);
    }
}
