package com.financial.indicators.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Configuração do Jackson para serializar BigDecimal sem notação científica,
 * mantendo como número (não string) no JSON
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        SimpleModule module = new SimpleModule();
        module.addSerializer(java.math.BigDecimal.class, new BigDecimalSerializer());
        
        objectMapper.registerModule(module);
        
        return objectMapper;
    }
}

