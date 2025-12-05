package com.financial.indicators.config;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializer customizado para BigDecimal que mantém o valor como número
 * (não string) mas evita notação científica usando toPlainString()
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        
        if (value == null) {
            gen.writeNull();
            return;
        }
        
        // Usa writeRawValue para escrever o número diretamente sem notação científica
        // Isso mantém como número no JSON, não como string
        gen.writeRawValue(value.toPlainString());
    }
}

