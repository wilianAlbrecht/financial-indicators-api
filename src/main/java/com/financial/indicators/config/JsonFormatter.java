package com.financial.indicators.config;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // Evita notação científica em BigDecimal

        // Serializadores customizados
        SimpleModule module = new SimpleModule();

        module.addSerializer(Double.class, new ToStringSerializer());
        module.addSerializer(Double.TYPE, new ToStringSerializer());
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        module.addSerializer(Long.class, new ToStringSerializer());
        module.addSerializer(Long.TYPE, new ToStringSerializer());
        module.addSerializer(Integer.class, new ToStringSerializer());
        module.addSerializer(Integer.TYPE, new ToStringSerializer());

        MAPPER.registerModule(module);
    }

    public static String stringify(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar JSON", e);
        }
    }
}
